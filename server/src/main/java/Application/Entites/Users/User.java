package Application.Entites.Users;

import Application.Entites.Order;
import Application.Entites.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public interface  User extends Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id_user;
//
//    public Long getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(Long id_user) {
//        this.id_user = id_user;
//    }
//    Long id_user;

//    @Size(min=1, max = 50)
//    private String name;

//    @NotBlank(message = "Логин не может быть пустым")
//    @Size(min=3, max = 50)
//    private String username;
//
////    @NaturalId
//    @NotBlank(message = "E-mail не может быть пустым")
//    @Size(max = 50)
//    @Email
//    private String email;

//    @NotBlank(message = "Пароль не может быть пустым")
//    @Size(min=6, max = 100)
//    private String password;
//
//    @Size(min=1, max = 100)
//    private String surname;
//
//    @NotNull
//    private Boolean locked;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "fk_users_id_role"))
//    private Role role;

//    public User() {}

//    public User(String password) {
//        this.password = password;
//    }

//    public Long getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(Long id_user) {
//        this.id_user = id_user;
//    }

//    public Boolean getLocked() {
//        return locked;
//    }
//
//    public void setLocked(Boolean locked) {
//        this.locked = locked;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
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
//    public Boolean getIsLock() {
//        return locked;
//    }
//
//    public void setIsLock(Boolean isLock) {
//        locked = isLock;
//    }

    public abstract Role getRole();
//    {
//        return role;
//    }

    public abstract void setRole(Role role);
//    {
//        this.role = role;
//    }

    public abstract Long getId_user();

    public abstract void setId_user(Long id_user);

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract Boolean getLocked();

    public abstract void setLocked(Boolean locked);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getSurname();

    public abstract void setSurname(String surname);

    public abstract Boolean getIsLock();

    public abstract void setIsLock(Boolean isLock);
}

