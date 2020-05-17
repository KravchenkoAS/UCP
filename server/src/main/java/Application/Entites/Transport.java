package Application.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transport", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
public class Transport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transport;

    @NotBlank(message = "Название не может быть пустым")
    @Size(min=3, max = 50)
    private String name;

    @NotNull(message = "Скорость не может быть пустым")
    private Float speed;

    @NotNull(message = "Максимальный объем не может быть пустым")
    private Float max_volume;

    @NotNull(message = "Максимальный вес не может быть пустым")
    private Float max_weight;

    private Float max_width;

    @NotNull(message = "Цена не может быть пустым")
    private Float price;

    @NotNull(message = "Коэффициент не может быть пустым")
    private Float coefficient;

    @NotNull(message = "Расход топлива не может быть пустым")
    private Float fuel_consumption;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="transport", fetch = FetchType.LAZY)
    private Set<Segment> segments;

    @NotNull
    private Float crewCost;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_delivery", foreignKey = @ForeignKey(name = "fk_transport_id_type_delivery"))
    private Type_delivery type_delivery;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuel", foreignKey = @ForeignKey(name = "fk_transport_id_fuel"))
    private Fuel fuel;

    public Transport() {
    }

    public Long getId_transport() {
        return id_transport;
    }

    public void setId_transport(Long id_transport) {
        this.id_transport = id_transport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getMax_volume() {
        return max_volume;
    }

    public void setMax_volume(Float max_volume) {
        this.max_volume = max_volume;
    }

    public Float getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(Float max_weight) {
        this.max_weight = max_weight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }

    public Float getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Float fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public Set<Segment> getSegments() {
        return segments;
    }

    public void setSegments(Set<Segment> segments) {
        this.segments = segments;
    }

    public Type_delivery getType_delivery() {
        return type_delivery;
    }

    public void setType_delivery(Type_delivery type_delivery) {
        this.type_delivery = type_delivery;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Float getMax_width() {
        return max_width;
    }

    public void setMax_width(Float max_width) {
        this.max_width = max_width;
    }

    public Float getCrewCost() {
        return crewCost;
    }

    public void setCrewCost(Float crewCost) {
        this.crewCost = crewCost;
    }

}
