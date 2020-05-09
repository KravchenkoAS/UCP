package Application.Security.Service;

import Application.Entites.Users.Customer;
import Application.Entites.Users.Staff;
import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
//import Application.Repositories.UserRepositories.UserRepository;
import Application.Repositories.UserRepositories.CustomerRepository;
import Application.Repositories.UserRepositories.StaffRepository;
import Application.Repositories.UserRepositories.TransporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransporterRepository transporterRepository;
    @Autowired
    StaffRepository staffRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        User user;

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isPresent()) {
            user = customer.get();
        } else {
            Optional<Transporter> transporter = transporterRepository.findByUsername(username);
            if (transporter.isPresent()) {
                user = transporter.get();
            } else {
                Staff staff = staffRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

                user = staff;

            }
        }
        return UserPrinciple.build(user);
    }
}
