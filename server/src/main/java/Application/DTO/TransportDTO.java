package Application.DTO;

import Application.Entites.Transport;
import Application.Repositories.FuelRepository;
import Application.Repositories.Type_deliveryRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class TransportDTO {

    @JsonProperty("id_transport")
    private Long id_transport;

    @JsonProperty("type_delivery")
    private String type_delivery;

    @JsonProperty("name")
    private String name;

    @JsonProperty("speed")
    private Float speed;

    @JsonProperty("max_volume")
    private Float max_volume;

    @JsonProperty("max_weight")
    private Float max_weight;

    @JsonProperty("max_width")
    private Float max_width;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("coefficient")
    private Float coefficient;

    @JsonProperty("fuel_consumption")
    private Float fuel_consumption;

    @JsonProperty("fuel")
    private String fuel;

    @JsonProperty("crewCost")
    private Float crewCost;

    public TransportDTO() {
    }

    public Long getId_transport() {
        return id_transport;
    }

    public void setId_transport(Long id_transport) {
        this.id_transport = id_transport;
    }

    public String getType_delivery() {
        return type_delivery;
    }

    public void setType_delivery(String type_delivery) {
        this.type_delivery = type_delivery;
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

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
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

    public void init(Transport transport) {
        this.setId_transport(transport.getId_transport());
        this.setName(transport.getName());
        this.setType_delivery(transport.getType_delivery().getName());
        this.setCoefficient(transport.getCoefficient());
        this.setPrice(transport.getPrice());
        this.setFuel_consumption(transport.getFuel_consumption());
        this.setMax_volume(transport.getMax_volume());
        this.setMax_weight(transport.getMax_weight());
        this.setSpeed(transport.getSpeed());
        this.setFuel(transport.getFuel().getName());
        this.setMax_width(transport.getMax_width());
        this.setCrewCost(transport.getCrewCost());
    }

    public static TransportDTO fromModel(Transport transport) {
        TransportDTO dto = new TransportDTO();
        dto.init(transport);
        return dto;
    }

    public static Transport parse(Transport transport, TransportDTO transportDTO, FuelRepository fuelRepository,
                                  Type_deliveryRepository typeDeliveryRepository) {

        transport.setCoefficient(transportDTO.getCoefficient());
        transport.setFuel_consumption(transportDTO.getFuel_consumption());
        transport.setMax_volume(transportDTO.getMax_volume());
        transport.setMax_weight(transportDTO.getMax_weight());
        transport.setSpeed(transportDTO.getSpeed());
        transport.setPrice(transportDTO.getPrice());
        transport.setFuel(fuelRepository.findByName(transportDTO.getFuel()));
        transport.setType_delivery(typeDeliveryRepository.findByName(transportDTO.getType_delivery()));
        transport.setMax_width(transportDTO.getMax_width());
        transport.setCrewCost(transportDTO.getCrewCost());

        return transport;
    }
}
