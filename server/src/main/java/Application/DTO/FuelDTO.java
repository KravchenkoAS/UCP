package Application.DTO;

import Application.Entites.Fuel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FuelDTO {

    @JsonProperty("id_fuel")
    private Long id_fuel;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("name")
    private String name;

    public FuelDTO() {
    }

    public Long getId_fuel() {
        return id_fuel;
    }

    public void setId_fuel(Long id_fuel) {
        this.id_fuel = id_fuel;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(Fuel fuel) {
        this.setId_fuel(fuel.getId_fuel());
        this.setName(fuel.getName());
        this.setPrice(fuel.getPrice());
    }

    public static FuelDTO fromModel(Fuel fuel) {
        FuelDTO dto = new FuelDTO();
        dto.init(fuel);
        return dto;
    }
}
