package Application.DTO;

import Application.Entites.Point;
import Application.Entites.Route;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RouteDTO implements Serializable {

    @JsonProperty("id_route")
    private Long id_route;

    @JsonProperty("distance")
    private Float distance;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("startPoint")
    private Long startPoint;

    @JsonProperty("endPoint")
    private Long endPoint;

    public RouteDTO() {
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

    public Long getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Long startPoint) {
        this.startPoint = startPoint;
    }

    public Long getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Long endPoint) {
        this.endPoint = endPoint;
    }

    public void init(Route route) {
        this.setId_route(route.getId_route());
        this.setDistance(route.getDistance());
        this.setTime(route.getTime());
        this.setPrice(route.getPrice());
        this.setStartPoint(route.getStart_point().getId_point());
        this.setEndPoint(route.getEnd_point().getId_point());
    }

    public static RouteDTO fromModel(Route route) {
        RouteDTO dto = new RouteDTO();
        dto.init(route);
        return dto;
    }
}
