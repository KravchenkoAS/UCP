package Application.DTO;

import Application.Entites.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    @JsonProperty("id_order")
    private Long id_order;

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("price")
    private Double price;

    public ItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public void init(Order order) {
        this.setId_order(order.getId_order());
        this.setName(order.getName());
        Double weight = Double.valueOf(order.getCargo().getWeight() * order.getCargo().getNumber());
        this.setWeight(weight);
        this.setPrice(Double.valueOf(order.getPrice()));
    }

    public static ItemDTO fromModel(Order order) {
        ItemDTO dto = new ItemDTO();
        dto.init(order);
        return dto;
    }
}
