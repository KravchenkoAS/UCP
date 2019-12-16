package Application.Controllers;

import Application.DTO.GetAllOrdersUserDTO;
import Application.DTO.UserDTO;
import Application.Entites.Role;
import Application.Entites.RoleName;
import Application.Entites.User;
import Application.Messages.Response.ResponseMessage;
import Application.Repositories.RoleRepository;
import Application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    @GetMapping(value = "/api/test/user/getUser/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {

        System.out.printf("getUser");

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            System.out.println("...");
            return new ResponseEntity<>(UserDTO.fromModel(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    @PutMapping("/api/test/user/updateUser/{id_user}")
    public ResponseEntity<?> updateUser(@PathVariable("id_user") Long id_user, @RequestBody UserDTO userDTO) {
        System.out.printf("Update User with ID = " + userDTO.getId_user());

//        if (userRepository.existsByUsername(userDTO.getUsername())) {
//            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой логин уже существует!"),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        if (userRepository.existsByEmail(userDTO.getEmail())) {
//            return new ResponseEntity<>(new ResponseMessage("Fail -> Такой email уже существует!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        Optional<User> user = userRepository.findById(id_user);

        if (user.isPresent()) {
            if (!userDTO.getEmail().isEmpty() && !userDTO.getEmail().equals("")) {
                user.get().setEmail(userDTO.getEmail());
            }
            if (!userDTO.getUsername().isEmpty() && !userDTO.getUsername().equals("")) {
                user.get().setUsername(userDTO.getUsername());
            }
            if (!userDTO.getName().isEmpty() && !userDTO.getName().equals("")) {
                user.get().setName(userDTO.getName());
            }
            if (!userDTO.getSurname().isEmpty() && !userDTO.getSurname().equals("")) {
                user.get().setSurname(userDTO.getSurname());
            }

            userRepository.save(user.get());
            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/api/test/user/getAllUser/")
    public List<UserDTO> getAllUser() {

        System.out.printf("getAllUser");

        List<User> userList = userRepository.findAll();

        if (userList.size() > 0) {
            List<UserDTO> userDTOList = new ArrayList<>();
            for (User user : userList) {
                userDTOList.add(UserDTO.fromModel(user));
            }
            System.out.println("...");
            return userDTOList;
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/api/test/user/blockUser/{id_user}")
    public ResponseEntity<String> blockUser(@PathVariable("id_user") Long id_user) {

        System.out.printf("blockUser: " + id_user);

        Optional<User> user = userRepository.findById(id_user);

        if (user.isPresent()) {
            user.get().setActive(!user.get().getActive());
            userRepository.save(user.get());
            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/api/test/user/changeRole/{id_user}")
    public ResponseEntity<?> changeRole(@PathVariable("id_user") Long id_user, @RequestBody String newRole) {

        System.out.printf("changeRole: " + id_user);

        Optional<User> user = userRepository.findById(id_user);

        if (user.isPresent()) {
            Set<Role> roles = new HashSet<>();
            if (RoleName.ROLE_ADMIN.toString() == newRole) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
                roles.add(adminRole);
            } else if (RoleName.ROLE_ANALYST.toString() == newRole) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ANALYST).get();
                roles.add(adminRole);
            } else if (RoleName.ROLE_CLIENT.toString() == newRole) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_CLIENT).get();
                roles.add(adminRole);
            }

            if (roles.size() > 0) {
                user.get().setRoles(roles);
                userRepository.save(user.get());
                System.out.println("...");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
