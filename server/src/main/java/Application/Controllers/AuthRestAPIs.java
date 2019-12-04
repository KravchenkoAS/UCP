package Application.Controllers;

import Application.Entites.Role;
import Application.Entites.RoleName;
import Application.Entites.Transporter;
import Application.Entites.User;
import Application.Messages.Request.LoginForm;
import Application.Messages.Request.SignUpForm;
import Application.Messages.Response.JwtResponse;
import Application.Messages.Response.ResponseMessage;
import Application.Repositories.RoleRepository;
import Application.Repositories.TransporterRepository;
import Application.Repositories.UserRepository;
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
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    TransporterRepository transporterRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой логин уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой email уже существует!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "analyst":
                    System.out.println("analyst");
                    Role analystRole = roleRepository.findByName(RoleName.ROLE_ANALYST)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(analystRole);

                    break;
                case "transporter":
                    System.out.println("transporter");
                    Role transporterRole = roleRepository.findByName(RoleName.ROLE_TRANSPORTER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(transporterRole);

                    break;
                default:
                    Role clientRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(clientRole);
            }
        });

        user.setRoles(roles);
        user.setActive(true);
        userRepository.save(user);

        for (Role role : user.getRoles()) {
            if (role.getName() == RoleName.ROLE_TRANSPORTER) {
                Transporter transporter = new Transporter();
                transporter.setId_user(userRepository.findByUsername(user.getUsername()).get().getId_user());
                transporterRepository.save(transporter);
            }
        }

        return new ResponseEntity<>(new ResponseMessage("Регистрация прошла успешно!"), HttpStatus.OK);
    }

}