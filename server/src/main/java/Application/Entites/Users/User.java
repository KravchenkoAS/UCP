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

    Role getRole();

    void setRole(Role role);

    Long getId_user();

    void setId_user(Long id_user);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    Boolean getLocked();

    void setLocked(Boolean locked);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getSurname();

    void setSurname(String surname);

    Boolean getIsLock();

    void setIsLock(Boolean isLock);
}

