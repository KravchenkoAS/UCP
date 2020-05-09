package Application.Entites.Users;

import Application.Entites.Order;
import Application.Entites.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Customer /*extends User */ implements User /*, Serializable*/ {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "fk_users_id_role"))
    private Role role;

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
    private Set<Order> orders;

    private Integer countOrders;

    public Customer() { super(); }

    public Customer(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.countOrders = 0;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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

    public Integer getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(Integer countOrders) {
        this.countOrders = countOrders;
    }
}
