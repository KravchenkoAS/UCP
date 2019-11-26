package Application.Entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "dictionary")
public class Dictionary implements Serializable, Comparable<Dictionary> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_dictionary;

        @NotNull(message = "Номер пути не может быть пустым")
        private Integer way;

        @NotNull(message = "Номер сегмента не может быть пустым")
        private Integer sequence;

        @JsonManagedReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_route", foreignKey = @ForeignKey(name = "fk_dictionary_id_route"))
        private Route route;

        @JsonManagedReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_segment", foreignKey = @ForeignKey(name = "fk_dictionary_id_segment"))
        private Segment segment;

        public Dictionary() {
        }

        public Long getId_dictionary() {
                return id_dictionary;
        }

        public void setId_dictionary(Long id_dictionary) {
                this.id_dictionary = id_dictionary;
        }

        public Integer getWay() {
                return way;
        }

        public void setWay(Integer way) {
                this.way = way;
        }

        public Integer getSequence() {
                return sequence;
        }

        public void setSequence(Integer sequence) {
                this.sequence = sequence;
        }

        public Route getRoute() {
                return route;
        }

        public void setRoute(Route route) {
                this.route = route;
        }

        public Segment getSegment() {
                return segment;
        }

        public void setSegment(Segment segment) {
                this.segment = new Segment(segment.getDistance(),
                        segment.getPrice(), segment.getTime(), segment.getAmount_transport(),
                        segment.getStart_point(), segment.getEnd_point(), segment.getTransport(),
                        segment.getDictionaries());
        }

        @Override
        public int compareTo(Dictionary o) {
                return sequence.compareTo(o.sequence);
        }
}
