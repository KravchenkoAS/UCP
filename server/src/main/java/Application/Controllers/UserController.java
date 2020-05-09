package Application.Controllers;

//import Application.DTO.UserDTO;
import Application.DTO.DefaultUserSettingDTO;
import Application.DTO.TransportDTO;
import Application.DTO.UsersDTO.CustomerDTO;
import Application.DTO.UsersDTO.StaffDTO;
import Application.DTO.UsersDTO.TransporterDTO;
import Application.DTO.UsersDTO.UserDTO;
import Application.Entites.RoleName;
import Application.Entites.Users.Customer;
import Application.Entites.Users.Staff;
import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
import Application.Messages.Response.ResponseMessage;
import Application.Repositories.RoleRepository;
//import Application.Repositories.UserRepositories.UserRepository;
import Application.Repositories.UserRepositories.CustomerRepository;
import Application.Repositories.UserRepositories.StaffRepository;
import Application.Repositories.UserRepositories.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransporterRepository transporterRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    @PutMapping(value = "/api/test/user/getUser/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username, @RequestBody String role) {

        System.out.printf("getUser - " + role);

        if (role.equals(RoleName.ROLE_CLIENT.name())) {
            Optional<Customer> customer = customerRepository.findByUsername(username);
            if (customer.isPresent()) {
                System.out.println("...");
                return new ResponseEntity<>(new CustomerDTO().fromModel(customer.get()), HttpStatus.OK);
            }
        } else if (role.equals(RoleName.ROLE_TRANSPORTER.name())) {
            Optional<Transporter> transporter = transporterRepository.findByUsername(username);
            if (transporter.isPresent()) {
                System.out.println("...");
                return new ResponseEntity<>(new TransporterDTO().fromModel(transporter.get()), HttpStatus.OK);
            }
        } else if (role.equals(RoleName.ROLE_ADMIN.name()) || role.equals(RoleName.ROLE_ANALYST.name())) {
            Optional<Staff> staff = staffRepository.findByUsername(username);
            if (staff.isPresent()) {
                System.out.println("...");
                return new ResponseEntity<>(new StaffDTO().fromModel(staff.get()), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    @PutMapping("/api/test/user/updateUser/{id_user}")
    public ResponseEntity<?> updateUser(@PathVariable("id_user") Long id_user, @RequestBody DefaultUserSettingDTO userDTO) {
        System.out.printf("Update User with ID = " + userDTO.getId_user());

        User user;
        if (userDTO.getRole().equals(RoleName.ROLE_CLIENT.name())) {
            user = customerRepository.findById(id_user).get();
        } else if (userDTO.getRole().equals(RoleName.ROLE_TRANSPORTER.name())) {
            user = transporterRepository.findById(id_user).get();
        } else if (userDTO.getRole().equals(RoleName.ROLE_ADMIN.name()) ||
                userDTO.getRole().equals(RoleName.ROLE_ANALYST.name())) {
            user = staffRepository.findById(id_user).get();
        } else {
            return new ResponseEntity<>("Ошибка аутентификации.", HttpStatus.BAD_REQUEST);
        }

        System.out.println(userDTO.getEmail());
        System.out.println(user.getEmail());

        if (user != null) {
            if (!userDTO.getEmail().equals(user.getEmail())) {
                if (!userDTO.getEmail().isEmpty() && !userDTO.getEmail().equals("")) {
                    boolean existsByEmail;
                    if (user instanceof Customer) {
                        existsByEmail = customerRepository.existsByEmail(userDTO.getEmail());
                    } else if (user instanceof Transporter) {
                        existsByEmail = transporterRepository.existsByEmail(userDTO.getEmail());
                    } else if (user instanceof Staff) {
                        existsByEmail = staffRepository.existsByEmail(userDTO.getEmail());
                    } else {
                        return new ResponseEntity<>("Ошибка аутентификации.", HttpStatus.BAD_REQUEST);
                    }
                    if (existsByEmail) {
                        return new ResponseEntity<>(new ResponseMessage("Такой email уже существует."),
                            HttpStatus.BAD_REQUEST);
                    } else {
                        user.setEmail(userDTO.getEmail());
                    }
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Введите email."),
                            HttpStatus.BAD_REQUEST);
                }
            }

            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());

            if (user instanceof Customer) {
                customerRepository.save((Customer) user);
            } else if (user instanceof Transporter) {
                transporterRepository.save((Transporter) user);
            } else if (user instanceof Staff) {
                staffRepository.save((Staff) user);
            } else {
                return new ResponseEntity<>("Ошибка аутентификации.", HttpStatus.BAD_REQUEST);
            }
            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
