package Application.DTO;

import Application.Entites.Type_delivery;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeDeliveryDTO {

    @JsonProperty("id_type_delivery")
    private Long id_type_delivery;

    @JsonProperty("name")
    private String name;

    public TypeDeliveryDTO() {
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

    public void init(Type_delivery type_delivery) {
        this.setId_type_delivery(type_delivery.getId_type_delivery());
        this.setName(type_delivery.getName());
    }

    public static TypeDeliveryDTO fromModel(Type_delivery type_delivery) {
        TypeDeliveryDTO dto = new TypeDeliveryDTO();
        dto.init(type_delivery);
        return dto;
    }
}
