package Application.Entites;

import Application.DTO.SegmentCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "segment")
public class Segment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_segment;

    @NotNull(message = "Расстояние не может быть пустым")
    private Float distance;

    @NotNull(message = "Цена не может быть пустым")
    private Float price;

    @NotNull(message = "Время не может быть пустым")
    private Integer time;

    @NotNull(message = "Количество транспорта не может быть пустым")
    private Integer amount_transport;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_start_point", foreignKey = @ForeignKey(name = "fk_segment_id_start_point"))
    private Point start_point;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_end_point", foreignKey = @ForeignKey(name = "fk_segment_id_end_point"))
    private Point end_point;

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transport", foreignKey = @ForeignKey(name = "fk_segment_id_transport"))
    private Transport transport;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="segment", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Dictionary> dictionaries = new HashSet<>();

    public Segment() {
    }

    public Segment(@NotNull(message = "Расстояние не может быть пустым") Float distance,
                   @NotNull(message = "Цена не может быть пустым") Float price,
                   @NotNull(message = "Время не может быть пустым") Integer time,
                   @NotNull(message = "Количество транспорта не может быть пустым") Integer amount_transport,
                   Point start_point, Point end_point, Transport transport, Set<Dictionary> dictionaries) {
        this.distance = distance;
        this.price = price;
        this.time = time;
        this.amount_transport = amount_transport;
        this.start_point = start_point;
        this.end_point = end_point;
        this.transport = transport;
        this.dictionaries = dictionaries;
    }

    public Long getId_segment() {
        return id_segment;
    }

    public void setId_segment(Long id_segment) {
        this.id_segment = id_segment;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Point getStart_point() {
        return start_point;
    }

    public void setStart_point(Point start_point) {
        this.start_point = new Point(start_point.getId_point(), start_point.getCity(), start_point.getCountry());
    }

    public Point getEnd_point() {
        return end_point;
    }

    public void setEnd_point(Point end_point) {
        this.end_point = new Point(end_point.getId_point(), end_point.getCity(), end_point.getCountry());

    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Integer getAmount_transport() {
        return amount_transport;
    }

    public void setAmount_transport(Integer amount_transport) {
        this.amount_transport = amount_transport;
    }

    public Set<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Set<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public void convertFromSegmentCreateDTO(SegmentCreateDTO segmentCreateDTO) {
        this.setDistance(segmentCreateDTO.getDistance());
        this.setPrice(segmentCreateDTO.getPrice());
        this.setTime(segmentCreateDTO.getTime());
        this.setAmount_transport(segmentCreateDTO.getAmount_transport());
    }
}
