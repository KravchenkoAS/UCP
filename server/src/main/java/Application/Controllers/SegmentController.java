package Application.Controllers;

import Application.DTO.SegmentCreateDTO;
import Application.DTO.SegmentDetailsDTO;
import Application.Entites.Dictionary;
import Application.Entites.Order;
import Application.Entites.Segment;
import Application.Entites.Transport;
import Application.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SegmentController {

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @PreAuthorize("hasRole('ANALYST')")
    @PostMapping(value = "/api/test/analyst/calculate")
    public ResponseEntity<SegmentCreateDTO> calculate(@RequestBody SegmentCreateDTO segmentCreateDTO) {

        System.out.printf("calculate ");

        Optional<Transport> transport = transportRepository.findById(segmentCreateDTO.getId_transport());

        if (transport.isPresent()){
            Float time = segmentCreateDTO.getDistance() / transport.get().getSpeed() / 12;
            segmentCreateDTO.setTime((int)Math.ceil(time));

            Float consumption = transport.get().getFuel_consumption() * segmentCreateDTO.getDistance() / 100;
            Float priceFuelForOneTransport = consumption * transport.get().getFuel().getPrice();
            double AllPriceSegment = (priceFuelForOneTransport
                    + transport.get().getPrice() * transport.get().getCoefficient()
                    ) * segmentCreateDTO.getAmount_transport();
            AllPriceSegment = Math.round(AllPriceSegment * 100.0) / 100.0;
            segmentCreateDTO.setPrice(Float.valueOf(String.valueOf(AllPriceSegment)));

            System.out.println("...");

            return new ResponseEntity<>(segmentCreateDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ANALYST')")
    @PostMapping(value = "/api/test/analyst/saveAllSegments/{id_order}",
            consumes = {      "application/json" })
    public ResponseEntity<String> saveAllSegments(@PathVariable("id_order") Long id_order,
                                                  @RequestBody ArrayList<SegmentCreateDTO> segments) {

        System.out.println("saveAllSegments for Order ID = " + id_order);

        Optional<Order> order = orderRepository.findById(id_order);
        List<Segment> segmentList = new ArrayList<>();
        if(order.isPresent()) {
            for (SegmentCreateDTO segment: segments) {
                if(Long.valueOf(segment.getStartPoint()) ==
                        order.get().getRoute().getStart_point().getId_point()) {
                    Segment firstSegment = new Segment();
                    firstSegment.convertFromSegmentCreateDTO(segment);

                   firstSegment.setStart_point(pointRepository.findById(
                           Long.valueOf(segment.getStartPoint())).get());

                    firstSegment.setEnd_point(pointRepository.findById(Long.valueOf(
                            segment.getEndPoint())).get());

                    firstSegment.setTransport(transportRepository.findById(
                            Long.valueOf(segment.getId_transport())).get());
                    segmentList.add(firstSegment);
                    break;
                }
            }

            for (int i = 0; i < segments.size(); i++) {
                for (SegmentCreateDTO segment: segments) {
                    if (Long.valueOf(segment.getStartPoint()) ==
                            segmentList.get(segmentList.size()-1).getEnd_point().getId_point()) {
                        Segment nextSegment = new Segment();
                        nextSegment.convertFromSegmentCreateDTO(segment);

                        nextSegment.setStart_point(pointRepository.findById(
                                Long.valueOf(segment.getStartPoint())).get());

                        nextSegment.setEnd_point(pointRepository.findById(Long.valueOf(
                                segment.getEndPoint())).get());

                        nextSegment.setTransport(transportRepository.findById(
                                Long.valueOf(segment.getId_transport())).get());
                        segmentList.add(nextSegment);
                    }
                }
            }

            if (segmentList.size() == segments.size()) {
                for (Segment segment: segmentList) {
                    System.out.println(segment.getStart_point().getCity() + " - " +
                            segment.getEnd_point().getCity());
                }
            } else {
                return new ResponseEntity<>("Неправильно построен маршрут. Проверьте еще раз!!!",
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Неправильно построен маршрут. Проверьте еще раз!!!",
                    HttpStatus.BAD_REQUEST);
        }

        if(saveDictionaries(id_order, segmentList)) {
            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ошибка сохранения маршрута.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    boolean saveDictionaries(Long id_order, List<Segment> segmentList){
        Optional<Order> order = orderRepository.findById(id_order);

        int maxNumberWay = 0;
        if (!order.get().getRoute().getDictionaries().isEmpty()){
            for (Dictionary dictionary: order.get().getRoute().getDictionaries()) {
                if (dictionary.getWay() > maxNumberWay) {
                    maxNumberWay = dictionary.getWay();
                }
            }
        }

        maxNumberWay++;
        for (int i = 0; i < segmentList.size(); i++) {
            Dictionary dictionary = new Dictionary();
            dictionary.setRoute(order.get().getRoute());
            dictionary.setSegment(segmentList.get(i));
            dictionary.setSequence(i+1);
            dictionary.setWay(maxNumberWay);
            segmentRepository.save(segmentList.get(i));
            dictionaryRepository.save(dictionary);
        }
        return true;
    }

    @PreAuthorize("hasRole('ANALYST') or hasRole('ADMIN') or hasRole('CLIENT')")
    @PostMapping(value = "/api/test/segment/getSegments")
    public List<SegmentDetailsDTO> getSegments(@RequestBody List<Long> list) {
        System.out.printf("getSegments");

        List<SegmentDetailsDTO> segmentCreateDTOList = new ArrayList<>();

        for (Long id_segment: list) {
            Optional<Segment> segment = segmentRepository.findById(id_segment);
            if (segment.isPresent()) {
                segmentCreateDTOList.add(SegmentDetailsDTO.fromModel(segment.get()));
            }
        }

        System.out.println("...");
        return segmentCreateDTOList;
    }

}
