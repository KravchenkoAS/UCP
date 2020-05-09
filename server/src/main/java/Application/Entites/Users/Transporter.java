package Application.Entites.Users;

import Application.Entites.Role;
import Application.Entites.Transport;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transporter", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Transporter /*extends User */ implements User /*, Serializable*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min=3, max = 50)
    private String username;

    //    @NaturalId
    @NotBlank(message = "E-mail не может быть пустым")
    @Size(max = 50)
    @Email
    private String email;

    @Size(min=1, max = 50)
    private String name;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min=6, max = 100)
    private String password;

    @Size(min=1, max = 100)
    private String surname;

    @NotNull
    private Boolean locked;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "transporter_transports",
            joinColumns = { @JoinColumn(name = "transporter_id_transporter") },
            inverseJoinColumns = { @JoinColumn(name = "transport_id_transport") })
    private Set<Transport> transports = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "fk_users_id_role"))
    private Role role;

    public Transporter() { super(); }

    public Transporter(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_user() {
        return id_user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Boolean getIsLock() {
        return getLocked();
    }

    @Override
    public void setIsLock(Boolean isLock) {
        setLocked(isLock);
    }

    @Override
    public Boolean getLocked() {
        return locked;
    }

    @Override
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
