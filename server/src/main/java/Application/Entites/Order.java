package Application.Entites;

import Application.Entites.Users.Customer;
import Application.Entites.Users.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders", uniqueConstraints =  @UniqueConstraint(
        name = "orders_id_user_name",
        columnNames = {
                "name", "id_user"
        }
))
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    @NotBlank(message = "Название не может быть пустым")
    @JoinColumn(name = "name")
    @Size(min=2, max = 25)
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Size(min=3, max = 50)
    private String status;

    @Column(name = "date_of_dispatch")
    private LocalDate date_of_dispatch;

    @NotNull(message = "Цена не может быть пустым")
    private Float price;

    private Boolean isDocuments;

    private Boolean isContainer;

    private Boolean express;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_orders_id_user"))
    private Customer customer;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_route", foreignKey = @ForeignKey(name = "fk_orders_id_route"))
    private Route route;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private Cargo cargo;

    public Order() {
    }

    public Order(@NotBlank(message = "Название не может быть пустым") @Size(min = 2, max = 25) String name,
                 LocalDate date_of_dispatch, Boolean isDocuments, Boolean isContainer, Boolean express) {
        this.name = name;
        this.date_of_dispatch = date_of_dispatch;
        this.isDocuments = isDocuments;
        this.isContainer = isContainer;
        this.express = express;

        this.price = Float.valueOf(0);
        this.date = LocalDate.now();
        this.status = "Обрабатывается";
    }

    public Long getId_order() {
        return id_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate_of_dispatch() {
        return date_of_dispatch;
    }

    public void setDate_of_dispatch(LocalDate date_of_dispatch) {
        this.date_of_dispatch = date_of_dispatch;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Boolean getDocuments() {
        return isDocuments;
    }

    public void setDocuments(Boolean documents) {
        isDocuments = documents;
    }

    public Boolean getContainer() {
        return isContainer;
    }

    public void setContainer(Boolean container) {
        isContainer = container;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Boolean getExpress() {
        return express;
    }

    public void setExpress(Boolean express) {
        this.express = express;
    }
}
