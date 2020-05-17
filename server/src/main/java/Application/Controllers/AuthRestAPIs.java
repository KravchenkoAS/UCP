package Application.Controllers;

import Application.Entites.Role;
import Application.Entites.RoleName;
import Application.Entites.Users.Customer;
import Application.Entites.Users.Transporter;
import Application.Messages.Request.LoginForm;
import Application.Messages.Request.SignUpForm;
import Application.Messages.Response.JwtResponse;
import Application.Messages.Response.ResponseMessage;
import Application.Repositories.RoleRepository;
import Application.Repositories.UserRepositories.CustomerRepository;
import Application.Repositories.UserRepositories.TransporterRepository;
import Application.Security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    TransporterRepository transporterRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.isAccountNonLocked());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой логин уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (transporterRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой логин уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой email уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (transporterRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой email уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        String strRole = signUpRequest.getRole();
        Role role = null;


        switch (strRole) {

            case "transporter":
                System.out.println("transporter");
                Role transporterRole = roleRepository.findByName(RoleName.ROLE_TRANSPORTER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                role = transporterRole;

                break;
            default:
                Role clientRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                role = clientRole;
        }


        // Creating user's account
        if (role.getName().equals(RoleName.ROLE_TRANSPORTER)) {
            Transporter transporter = new Transporter(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
            transporter.setRole(role);
            transporter.setIsLock(true);
            transporterRepository.save(transporter);

        } else {
            Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
            customer.setRole(role);
            customer.setIsLock(true);
            customerRepository.save(customer);

        }

        return new ResponseEntity<>(new ResponseMessage("Регистрация прошла успешно!"), HttpStatus.OK);
    }

}