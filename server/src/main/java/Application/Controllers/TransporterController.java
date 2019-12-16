package Application.Controllers;

import Application.Backpack.Backpack;
import Application.Backpack.Item;
import Application.DTO.BackpackDTO;
import Application.DTO.ItemDTO;
import Application.DTO.OrderDTO;
import Application.DTO.TransportDTO;
import Application.Entites.Order;
import Application.Entites.Transport;
import Application.Entites.Transporter;
import Application.Entites.User;
import Application.Repositories.OrderRepository;
import Application.Repositories.TransportRepository;
import Application.Repositories.TransporterRepository;
import Application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TransporterController {

    @Autowired
    private TransporterRepository transporterRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/transporter/getTransportsTransporter/{username}")
    public List<TransportDTO> getTransportsTransporter(@PathVariable("username") String username) {

        System.out.printf("getTransportsTransporter");

        Optional<User> user = userRepository.findByUsername(username);
        Iterable<Transporter> transporters = transporterRepository.findAll();
        Transporter transporterGet = new Transporter();

        for (Transporter transporter : transporters) {
            if (transporter.getId_user() == user.get().getId_user()) {
                transporterGet = transporter;
            }
        }
        if (transporterGet.getTransports().size() > 0) {
            List<TransportDTO> transportDTOList = new ArrayList<>();
            for (Transport transport : transporterGet.getTransports()) {
                transportDTOList.add(TransportDTO.fromModel(transport));
            }
            System.out.println("...");
            return transportDTOList;
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @DeleteMapping(value = "/api/test/transporter/deleteTransportTransporter/{username}/{id_transport}")
    public ResponseEntity<?> deleteTransportTransporter(@PathVariable("username") String username,
                                                        @PathVariable("id_transport") Long id_transport) {

        System.out.printf("deleteTransportTransporter");

        Optional<User> user = userRepository.findByUsername(username);
        Iterable<Transporter> transporters = transporterRepository.findAll();
        Transporter transporterDel = new Transporter();

        for (Transporter transporter : transporters) {
            if (transporter.getId_user() == user.get().getId_user()) {
                transporterDel = transporter;
            }
        }
        if (transporterDel.getTransports().size() > 0) {
            Optional<Transport> transport = transportRepository.findById(id_transport);
            transporterDel.getTransports().remove(transport.get());
            transporterRepository.save(transporterDel);

            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/transporter/addTransportTransporter/{username}/{id_transport}")
    public ResponseEntity<?> addTransportTransporter(@PathVariable("username") String username,
                                                     @PathVariable("id_transport") Long id_transport) {

        System.out.printf("addTransportTransporter");

        Optional<User> user = userRepository.findByUsername(username);
        Iterable<Transporter> transporters = transporterRepository.findAll();
        Transporter transporterAdd = new Transporter();

        for (Transporter transporter : transporters) {
            if (transporter.getId_user() == user.get().getId_user()) {
                transporterAdd = transporter;
            }
        }
        Optional<Transport> transport = transportRepository.findById(id_transport);
        if (transport.isPresent()) {
            Set<Transport> set = transporterAdd.getTransports();
            set.add(transport.get());
            transporterAdd.setTransports(set);
            transporterRepository.save(transporterAdd);

            System.out.println("...");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @PostMapping("api/test/backpack/calculateBackpack/{id_transport}")
    public ResponseEntity<?> calculateBackpack(@PathVariable("id_transport") Long id_transport,
                                                         @RequestBody List<Item> items) {
        System.out.printf("calculateBackpack");

        Optional<Transport> transportOptional = transportRepository.findById(id_transport);
        Backpack backpack = new Backpack(Double.valueOf(transportOptional.get().getMax_weight()));
        backpack.makeAllSets(items);

        if (backpack.getBestItems() != null && backpack.getBestItems().size() > 0) {
            System.out.println("...");
            return new ResponseEntity<>(BackpackDTO.fromModel(backpack), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Нет решения!", HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @PostMapping(value = "/api/test/backpack/getItems")
    public List<ItemDTO> getItems(@RequestBody List<OrderDTO> orderDTOList) {
        System.out.printf("getItems");

        if (orderDTOList.size() > 0) {
            List<Order> orderList = new ArrayList<>();
            for (OrderDTO orderDTO : orderDTOList) {
                orderList.add(orderRepository.findById(orderDTO.getId_order()).get());
            }
            if (orderList.size() > 0) {
                List<ItemDTO> itemDTOList = new ArrayList<>();
                for (Order order : orderList) {
                    if(order.getStatus().compareTo("Договор") == 0) {
                        itemDTOList.add(ItemDTO.fromModel(order));
                    }
                }
                System.out.println("...");
                return itemDTOList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_TRANSPORTER')")
    @PostMapping(value = "/api/test/backpack/submit")
    public ResponseEntity<?> submit(@RequestBody List<ItemDTO> itemDTOList) {
        System.out.printf("submit");

        if (itemDTOList.size() > 0) {
            List<Order> orderList = new ArrayList<>();
            for (ItemDTO itemDTO : itemDTOList) {
                Optional<Order> order = orderRepository.findById(itemDTO.getId_order());
                if (order.isPresent()) {
                    orderList.add(order.get());
                }
            }
            if (orderList.size() > 0) {
                for (Order order : orderList) {
                    order.setStatus("Выполняется");
                    orderRepository.save(order);
                }
                System.out.println("...");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
