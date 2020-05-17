package Application.Controllers;

import Application.DTO.ChangePasswordDTO;
//import Application.DTO.EmployeeDTO;
//import Application.DTO.UserDTO;
import Application.DTO.DefaultUserSettingDTO;
import Application.DTO.UsersDTO.StaffDTO;
import Application.Entites.Role;
import Application.Entites.RoleName;
import Application.Entites.Users.Customer;
import Application.Entites.Users.Staff;
import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
import Application.Repositories.RoleRepository;
//import Application.Repositories.UserRepositories.UserRepository;
import Application.Repositories.UserRepositories.CustomerRepository;
import Application.Repositories.UserRepositories.StaffRepository;
import Application.Repositories.UserRepositories.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SettingController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private TransporterRepository transporterRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/api/test/admin/saveUsers")
    public ResponseEntity<?> saveUsers(@RequestBody List<DefaultUserSettingDTO> defaultUserSettingDTOS) {

        System.out.printf("saveUsers");

        if (defaultUserSettingDTOS.size() > 0) {

            List<Staff> staffs = new ArrayList<>();
            List<Transporter> transporters = new ArrayList<>();
            List<Customer> customers = new ArrayList<>();

            for (DefaultUserSettingDTO userSettingDTO : defaultUserSettingDTOS) {
                if (userSettingDTO.getRole().equals(RoleName.ROLE_ADMIN.name()) ||
                    userSettingDTO.getRole().equals(RoleName.ROLE_ANALYST.name())) {

                    Optional<Staff> staff = staffRepository.findById(userSettingDTO.getId_user());
                    if (staff.isPresent()) {
                        if (staff.get().getLocked() != userSettingDTO.getActive()) {
                            staff.get().setLocked(userSettingDTO.getActive());
                            staffs.add(staff.get());
                        }

                        if (!staff.get().getRole().getName().name().equals(userSettingDTO.getRole())) {
                            if (userSettingDTO.getRole().equals(RoleName.ROLE_ADMIN.name())) {
                                staff.get().setRole(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                            } else {
                                staff.get().setRole(roleRepository.findByName(RoleName.ROLE_ANALYST).get());
                            }
                        }
                    }
                } else if (userSettingDTO.getRole().equals(RoleName.ROLE_TRANSPORTER.name())) {
                    Optional<Transporter> transporter = transporterRepository.findById(userSettingDTO.getId_user());
                    if (transporter.get().getLocked() != userSettingDTO.getActive()) {
                        transporter.get().setLocked(userSettingDTO.getActive());
                        transporters.add(transporter.get());
                    }
                } else if (userSettingDTO.getRole().equals(RoleName.ROLE_CLIENT.name())) {
                    Optional<Customer> customer = customerRepository.findById(userSettingDTO.getId_user());
                    System.out.println("f");

                    if (customer.isPresent()) {
                        System.out.println("s");
                        if (customer.get().getLocked() != userSettingDTO.getActive()) {
                            System.out.println("a");
                            customer.get().setLocked(userSettingDTO.getActive());
                            customers.add(customer.get());
                        }
                    }
                }
            }

            staffRepository.saveAll(staffs);
            customerRepository.saveAll(customers);
            transporterRepository.saveAll(transporters);
            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Проверьте изменения.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('TRANSPORTER')")
    @PutMapping(value = "/api/test/user/updatePassword/{id_user}")
    public ResponseEntity<?> updatePassword(@PathVariable("id_user") Long id_user,
                                            @RequestBody ChangePasswordDTO changePasswordDTO) {

        System.out.println("Update password with ID = " + id_user);

        User user;
        if (changePasswordDTO.getRole().equals(RoleName.ROLE_CLIENT.name())) {
            user = customerRepository.findById(id_user).get();
        } else if (changePasswordDTO.getRole().equals(RoleName.ROLE_TRANSPORTER.name())) {
            user = transporterRepository.findById(id_user).get();
        } else if (changePasswordDTO.getRole().equals(RoleName.ROLE_ADMIN.name()) ||
                changePasswordDTO.getRole().equals(RoleName.ROLE_ANALYST.name())) {
            user = staffRepository.findById(id_user).get();
        } else {
            return new ResponseEntity<>("Ошибка аутентификации. Пользователь не найден.", HttpStatus.BAD_REQUEST);
        }

        if (!encoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            return new ResponseEntity<>("Неверынй пароль.", HttpStatus.BAD_REQUEST);
        } else {
            user.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));

            if (user.getRole().getName().equals(RoleName.ROLE_CLIENT)) {
                customerRepository.save((Customer) user);
            } else if (user.getRole().getName().equals(RoleName.ROLE_TRANSPORTER)) {
                transporterRepository.save((Transporter) user);
            } else if (user.getRole().getName().equals(RoleName.ROLE_ADMIN) ||
                    user.getRole().getName().equals(RoleName.ROLE_ANALYST)) {
                staffRepository.save((Staff) user);
            } else {
                return new ResponseEntity<>("Ошибка аутентификации. Пароль не сохранен.", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/api/test/admin/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody StaffDTO staffDTO) {

        System.out.printf("createEmployee: ");

        if (staffRepository.existsByEmail(staffDTO.getEmail())) {
            return new ResponseEntity<>("Такой e-mail уже существует!", HttpStatus.CONFLICT);
        }
        if (staffRepository.existsByUsername(staffDTO.getUsername())) {
            return new ResponseEntity<>("Такой логин уже существует!", HttpStatus.CONFLICT);
        }

        Staff staff = staffDTO.parse();

        Role role;
        switch (staffDTO.getRole()) {
            case "ADMIN":
                role = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
                break;
            case "ANALYST":
                role = roleRepository.findByName(RoleName.ROLE_ANALYST).get();
                break;
            default: return new ResponseEntity<>("Не указана роль (тип) сотрудника!", HttpStatus.NOT_FOUND);
        }

        staff.setPassword(encoder.encode(staff.getPassword()));
        staff.setRole(role);
        staffRepository.save(staff);
        System.out.println("...");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/api/test/admin/getAllUsers")
    public ResponseEntity<?> getAllUser() {

        System.out.printf("getAllUser");

        List<Staff> staffList = staffRepository.findAll();
        List<Customer> customerList = customerRepository.findAll();
        List<Transporter> transporterList = transporterRepository.findAll();

        if (staffList.size() > 0 || customerList.size() > 0 || transporterList.size() > 0) {
            List<DefaultUserSettingDTO> userDTOList = new ArrayList<>();
            for (Staff staff : staffList) {
                System.out.println("Staff");
                userDTOList.add(new DefaultUserSettingDTO().fromModel(staff));
            }
            for (Customer customer : customerList) {
                userDTOList.add(new DefaultUserSettingDTO().fromModel(customer));
            }
            for (Transporter transporter : transporterList) {
                userDTOList.add(new DefaultUserSettingDTO().fromModel(transporter));
            }
            System.out.println("...");
            return new ResponseEntity<>(userDTOList, HttpStatus.OK);
        } else {
            System.out.println("NOT_FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
