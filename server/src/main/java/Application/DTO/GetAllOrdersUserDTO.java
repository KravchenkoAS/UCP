package Application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllOrdersUserDTO {
    @JsonProperty("id_order")
    private Long id_order;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    public GetAllOrdersUserDTO(Long id_order, String type, String name, String status) {
        this.id_order = id_order;
        this.type = type;
        this.name = name;
        this.status = status;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
