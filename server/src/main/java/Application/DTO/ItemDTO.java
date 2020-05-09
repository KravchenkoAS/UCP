package Application.DTO;

import Application.Entites.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    @JsonProperty("id_order")
    private Long id_order;

    @JsonProperty("name")
    private String name;

    @JsonProperty("volume")
    private Float volume;

    @JsonProperty("price")
    private Float price;

    public ItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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
        Float volume = order.getCargo().getVolume() * order.getCargo().getNumber();
        volume = (float) (Math.ceil(Double.valueOf(volume * 100)) / 100);
        this.setVolume(volume);
        this.setPrice(order.getPrice());
    }

    public static ItemDTO fromModel(Order order) {
        ItemDTO dto = new ItemDTO();
        dto.init(order);
        System.out.println(dto.getPrice());
        return dto;
    }
}
