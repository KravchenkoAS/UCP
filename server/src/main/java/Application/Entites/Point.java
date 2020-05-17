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

}
