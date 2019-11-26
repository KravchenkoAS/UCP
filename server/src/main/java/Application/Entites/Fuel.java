package Application.Entites;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "fuel", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
public class Fuel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fuel;

    @NotBlank(message = "Название не может быть пустым")
    @Size(min=3, max = 50)
    private String name;

    @NotNull(message = "Цена не может быть пустым")
    private Float price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="fuel", fetch = FetchType.LAZY)
    private Set<Transport> transports;

    public Fuel() {
    }

    public Long getId_fuel() {
        return id_fuel;
    }

    public void setId_fuel(Long id_fuel) {
        this.id_fuel = id_fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }
}
