package Application.Entites;

import Application.Entites.Users.Customer;
import Application.Entites.Users.Staff;
import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @NotBlank
    @Column(length = 60)
    private RoleName name;

    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private Set<Customer> customers;

    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private Set<Staff> staff;

    @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private Set<Transporter> transporters;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId_name() {
        return id_role;
    }

    public void setId_name(Long id_role) {
        this.id_role = id_role;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Transporter> getTransporters() {
        return transporters;
    }

    public void setTransporters(Set<Transporter> transporters) {
        this.transporters = transporters;
    }
}
