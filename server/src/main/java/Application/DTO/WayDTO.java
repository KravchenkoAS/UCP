package Application.DTO;

import Application.Entites.Dictionary;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WayDTO implements Serializable {

    @JsonProperty("way")
    private Integer way;

    @JsonProperty("id_route")
    private Long id_route;

    @JsonProperty("id_segment")
    private List<Long> id_segment;

    @JsonProperty("distance")
    private Float distance;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("price")
    private Float price;

    public WayDTO() {
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Long getId_route() {
        return id_route;
    }

    public void setId_route(Long id_route) {
        this.id_route = id_route;
    }

    public List<Long> getId_segment() {
        return id_segment;
    }

    public void setId_segment(List<Long> id_segment) {
        this.id_segment = id_segment;
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

    public void init(List<Dictionary> dictionaryList) {
        this.setWay(dictionaryList.get(0).getWay());
        this.setId_route(dictionaryList.get(0).getRoute().getId_route());
        List<Long> list = new ArrayList<>();
        Integer time = 0;
        Float distance  = Float.valueOf(0);
        Float price = Float.valueOf(0);
         for (Dictionary dictionary: dictionaryList) {
             list.add(dictionary.getSegment().getId_segment());
             time += dictionary.getSegment().getTime();
             distance += dictionary.getSegment().getDistance();
             price += dictionary.getSegment().getPrice();
         }
         this.setId_segment(list);
         this.setTime(time);
         this.setDistance(distance);
         this.setPrice(price);
    }

    public static WayDTO fromModel(List<Dictionary> dictionaryList) {
        WayDTO dto = new WayDTO();
        dto.init(dictionaryList);
        return dto;
    }
}
