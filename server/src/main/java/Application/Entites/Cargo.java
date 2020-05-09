package Application.Entites;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cargo;

    @NotBlank(message = "Тип не может быть пустым")
    private String type;

    @NotNull(message = "Вес не может быть пустым")
    private Float weight;

    @NotNull(message = "Объем не может быть пустым")
    private Float volume;

    private Float length;

    private Float height;

    private Float width;

    @NotNull(message = "Количество груза не может быть пустым")
    private Float number;

    private Boolean stack; //штабилировать

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    public Cargo() {
    }

    public Cargo(Float weight, Float length,
                 Float height, Float width, Float number,
                 @NotBlank String type, Boolean stack, Boolean express) {
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.number = number;
        this.type = type;
        this.stack = stack;
        this.volume = Float.valueOf(0);
    }

    public Long getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Long id_cargo) {
        this.id_cargo = id_cargo;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Boolean getStack() {
        return stack;
    }

    public void setStack(Boolean stack) {
        this.stack = stack;
    }

}

