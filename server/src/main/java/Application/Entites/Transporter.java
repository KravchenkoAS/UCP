package Application.Entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transporter")
public class Transporter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transporter;

    private Long id_user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "transporter_transports",
            joinColumns = { @JoinColumn(name = "transporter_id_transporter") },
            inverseJoinColumns = { @JoinColumn(name = "transport_id_transport") })
    private Set<Transport> transports = new HashSet<>();

    public Transporter() {
    }

    public Long getId_transporter() {
        return id_transporter;
    }

    public void setId_transporter(Long id_transporter) {
        this.id_transporter = id_transporter;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Set<Transport> getTransports() {
        return transports;
    }

    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }

}
