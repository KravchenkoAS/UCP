package Application.Entites;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "type_delivery", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
public class Type_delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type_delivery;

    @NotBlank(message = "Название не может быть пустым")
    @Size(min=3, max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="type_delivery", fetch = FetchType.LAZY)
    private Set<Transport> transports;

    public Type_delivery() {
    }

    public Long getId_type_delivery() {
        return id_type_delivery;
    }

    public void setId_type_delivery(Long id_type_delivery) {
        this.id_type_delivery = id_type_delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }
}
