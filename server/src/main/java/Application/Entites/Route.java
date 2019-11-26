package Application.Entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_route;

    @Column(name = "distance")
    private Float distance;

    @NotNull
    @Column(name = "time")
    private Integer time;

    @NotNull
    private Float price;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_point", foreignKey = @ForeignKey(name = "fk_route_start_point"))
    private Point start_point;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_point", foreignKey = @ForeignKey(name = "fk_route_end_point"))
    private Point end_point;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="route", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @OneToMany(mappedBy="route", fetch = FetchType.LAZY)
    private Set<Dictionary> dictionaries;

    public Route() {
    }

    public Route(Optional<Point> start_point, Optional<Point> end_point) {
        this.start_point = start_point.get();
        this.end_point = end_point.get();
        this.price = Float.valueOf(0);
        this.time = 0;
    }

    public Route(Optional<Point> start_point, Optional<Point> end_point, Order newOrder) {
        this.start_point = start_point.get();
        this.end_point = end_point.get();
        this.orders.add(newOrder);
        this.price = Float.valueOf(0);
        this.time = 0;
    }

    public Long getId_route() {
        return id_route;
    }

    public void setId_route(Long id_route) {
        this.id_route = id_route;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Point getStart_point() {
        return start_point;
    }

    public void setStart_point(Point start_point) {
        this.start_point = start_point;
    }

    public Point getEnd_point() {
        return end_point;
    }

    public void setEnd_point(Point end_point) {
        this.end_point = end_point;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Set<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
