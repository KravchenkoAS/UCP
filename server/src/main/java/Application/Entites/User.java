package Application.Entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "user", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "username"
//        }),
//        @UniqueConstraint(columnNames = {
//                "email"
//        }),
//        @UniqueConstraint(columnNames = {
//                "login"
//        })
//})
//public class User{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
////    @Column(name = "username")
//    @Size(min=3, max = 50)
//    private String username;
//
//    @NotBlank
////    @7(name = "login")
//    @Size(min=3, max = 50)
//    private String login;
//
//    @NaturalId
//    @NotBlank
////    @Column(name = "email")
//    @Size(max = 50)
//    @Email
//    private String email;
//
//    @NotBlank
////    @Column(name = "password")
//    @Size(min=6, max = 100)
//    private String password;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();
//
////    @JsonManagedReference
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
////    private Set<Role> roles = new HashSet<>();
//
////    @Column(name = "name")
//    @Size(min=3, max = 50)
//    private String name;
//
////    @Column(name = "surname")
//    @Size(min=3, max = 50)
//    private String surname;
//
////    @Column(name = "active")
//    private Boolean active;
//
//    public User() {}
//
//    public User(@NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 3, max = 50) String login, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 100) String password, Set<Role> roles, @Size(min = 3, max = 50) String name, @Size(min = 3, max = 50) String surname, Boolean active) {
//        this.username = username;
//        this.login = login;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//        this.name = name;
//        this.surname = surname;
//        this.active = active;
//    }
//
//    public User(String name, String username, String email, String password) {
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//    }
//
//    public Long getId_user() {
//        return id;
//    }
//
//    public void setId_user(Long id_user) {
//        this.id = id_user;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min=1, max = 50)
    private String name;

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NotBlank(message = "E-mail не может быть пустым")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min=6, max = 100)
    private String password;

    @Size(min=1, max = 100)
    private String surname;

    @NotNull
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

