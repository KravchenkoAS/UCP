package Application.DTO;

import Application.Entites.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SegmentDetailsDTO implements Serializable {

    @JsonProperty("id_segment")
    private Long id_segment;

    @JsonProperty("type_delivery")
    private String type_delivery;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("transport")
    private String transport;

    @JsonProperty("amount_transport")
    private Integer amount_transport;

    @JsonProperty("distance")
    private Float distance;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("startPoint")
    private String startPoint;

    @JsonProperty("endPoint")
    private String endPoint;

    public SegmentDetailsDTO() {
    }

    public Long getId_segment() {
        return id_segment;
    }

    public void setId_segment(Long id_segment) {
        this.id_segment = id_segment;
    }

    public String getType_delivery() {
        return type_delivery;
    }

    public void setType_delivery(String type_delivery) {
        this.type_delivery = type_delivery;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Integer getAmount_transport() {
        return amount_transport;
    }

    public void setAmount_transport(Integer amount_transport) {
        this.amount_transport = amount_transport;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void init(Segment segment){
        this.setId_segment(segment.getId_segment());
        this.setType_delivery(segment.getTransport().getType_delivery().getName());
        this.setTransport(segment.getTransport().getName());
        this.setDistance(segment.getDistance());
        this.setPrice(segment.getPrice());
        this.setTime(segment.getTime());
        this.setStartPoint(segment.getStart_point().getCountry()
                + "/"
                + segment.getStart_point().getCity());
        this.setEndPoint(segment.getEnd_point().getCountry()
                + "/"
                + segment.getEnd_point().getCity());
        this.setAmount_transport(segment.getAmount_transport());
    }

    public static SegmentDetailsDTO fromModel(Segment segment) {
        SegmentDetailsDTO dto = new SegmentDetailsDTO();
        dto.init(segment);
        return dto;
    }
}
