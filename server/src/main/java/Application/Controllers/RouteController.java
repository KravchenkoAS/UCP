package Application.Controllers;

import Application.DTO.WayDTO;
import Application.DTO.RouteDTO;
import Application.DTO.WayDTO;
import Application.Entites.Dictionary;
import Application.Entites.Order;
import Application.Entites.Route;
import Application.Entites.Segment;
import Application.Repositories.DictionaryRepository;
import Application.Repositories.OrderRepository;
import Application.Repositories.RouteRepository;
import Application.Repositories.SegmentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN')")
    @GetMapping(value = "/api/test/route/getRoute/{id_order}")
    public RouteDTO getRoute(@PathVariable("id_order") Long id_order) {
        System.out.printf("getRoute - Order : " + id_order);

        Order order = orderRepository.findById(id_order).get();

        System.out.println("...");
        return RouteDTO.fromModel(
                routeRepository.findById(order.getRoute().getId_route()).get()
        );
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN')")
    @GetMapping(value = "/api/test/route/getDictionaryList/{id_route}")
    public List<WayDTO> getDictionaryList(@PathVariable("id_route") Long id_route) {
        System.out.printf("getDictionaryList - id_route: " + id_route);

        Optional<Route> route = routeRepository.findById(id_route);
        List<WayDTO> wayDTOList = new ArrayList<>();
        List<Dictionary> dictionaryList = new ArrayList<>();

        if (route.isPresent()) {
            int maxCountWay = 0;
            for (Dictionary dictionary : route.get().getDictionaries()) {
                if (maxCountWay < dictionary.getWay()) {
                    maxCountWay = dictionary.getWay();
                }
            }
            for (int i = 0; i < maxCountWay; i++){
                dictionaryList.clear();
                for (Dictionary dictionary : route.get().getDictionaries()) {
                    if (dictionary.getWay() == i + 1) {
                        dictionaryList.add(dictionary);
                    }
                }
                Collections.sort(dictionaryList);
                if (dictionaryList.size() > 0) {
                    wayDTOList.add(WayDTO.fromModel(dictionaryList));
                }
            }
        }

        if (wayDTOList.size() > 0) {
            System.out.println("...");
            return wayDTOList;
        } else {
            return null;
        }

    }

    @PreAuthorize("hasRole('ANALYST')")
    @DeleteMapping("/api/test/route/deleteWay/{id_route}/{way}")
    public ResponseEntity<String> deleteWay(@PathVariable("id_route") Long id_route,
                                            @PathVariable("way") Integer way) {
        System.out.println("Delete way = " + way + "...");

        Set<Dictionary> dictionaries = routeRepository.findById(id_route).get().getDictionaries();

        if (dictionaries.size() > 0) {
            for (Dictionary dictionary: dictionaries) {
                if(dictionary.getWay() == way) {

                    segmentRepository.delete(dictionary.getSegment());

                    System.out.println(dictionary.getId_dictionary());
                }
            }
            return new ResponseEntity<>("Путь удален", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PreAuthorize("hasRole('ANALYST')")
//    @DeleteMapping("/api/test/route/deleteOtherWays/{id_route}/{way}")
//    public ResponseEntity<WayDTO> deleteOtherWays(@PathVariable("id_route") Long id_route,
//                                            @PathVariable("way") Integer way) {
//        System.out.println("Save way = " + way + "...");
//
//        Set<Dictionary> dictionaries = routeRepository.findById(id_route).get().getDictionaries();
//
//        if (dictionaries.size() > 0) {
//            for (Dictionary dictionary: dictionaries) {
//                if(dictionary.getWay() != way) {
//                    dictionaryRepository.delete(dictionary);
//                }
//            }
//            return new ResponseEntity<>("Способ доставкы выбран", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/api/test/route/submit/{id_order}/{id_route}/{way}")
    public ResponseEntity<WayDTO> submit(@PathVariable("id_order") Long id_order,
                                         @PathVariable("id_route") Long id_route,
                                         @PathVariable("way") Integer way) {
        System.out.println("Submit way = " + way + "...");

        Set<Dictionary> dictionaries = routeRepository.findById(id_route).get().getDictionaries();
        List<Dictionary> dictionaryList = new ArrayList<>();

        if (dictionaries.size() > 0) {
            for (Dictionary dictionary : dictionaries) {
                if (dictionary.getWay() != way) {

                    segmentRepository.delete(dictionary.getSegment());

                    System.out.println(dictionary.getId_dictionary());
                } else if (dictionary.getWay() == way) {
                    dictionaryList.add(dictionary);
                }
            }

            if (dictionaryList.size() > 0) {
                Collections.sort(dictionaryList);

                WayDTO wayDTO = WayDTO.fromModel(dictionaryList);

                Route route = routeRepository.findById(id_route).get();
                route.setDistance(wayDTO.getDistance());
                route.setPrice(wayDTO.getPrice());
                route.setTime(wayDTO.getTime());
                routeRepository.save(route);

                Order order = orderRepository.findById(id_order).get();
                order.setStatus("Договор");
                order.setPrice(route.getPrice());
                orderRepository.save(order);

                return new ResponseEntity<>(WayDTO.fromModel(dictionaryList), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
