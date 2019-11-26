package Application.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "point", uniqueConstraints = { @UniqueConstraint(
                name = "uk_point_city_country",
                columnNames = {
                "city", "country"
        })
})
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_point;

    @NotBlank(message = "Название города не может быть пустым")
    private String city;

    @NotBlank(message = "Название страны не может быть пустым")
    private String country;

    //OneToMan
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "start_point")
//    private Set<Route> start_point = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "end_point")
//    private Set<Route> end_point = new HashSet<>();

    public Point() {
    }

    public Point(Long id_point, @NotBlank String city, @NotBlank String country) {
        this.id_point = id_point;
        this.city = city;
        this.country = country;
    }

    public Long getId_point() {
        return id_point;
    }

    public void setId_point(Long id_point) {
        this.id_point = id_point;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public Set<Route> getStart_point() {
//        return start_point;
//    }
//
//    public void setStart_point(Set<Route> start_point) {
//        this.start_point = start_point;
//    }
//
//    public Set<Route> getEnd_point() {
//        return end_point;
//    }
//
//    public void setEnd_point(Set<Route> end_point) {
//        this.end_point = end_point;
//    }
}
