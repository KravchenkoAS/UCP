package Application.Controllers;

import Application.DTO.GetAllOrdersUserDTO;
import Application.DTO.OrderDTO;
import Application.DTO.Order_Cargo_RouteDTO;
import Application.Entites.*;
import Application.Entites.Users.Customer;
import Application.Repositories.*;
//import Application.Repositories.UserRepositories.UserRepository;
import Application.Repositories.UserRepositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    SegmentRepository segmentRepository;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(value = "/api/test/client/delivery/{username}/createOrder")
    public ResponseEntity<Order_Cargo_RouteDTO> createOrder(@PathVariable("username") String username,
                                                            @RequestBody Order_Cargo_RouteDTO orderCargoRouteDTO) {

        System.out.printf("createOrder");

        if (orderRepository.findByNameAndCustomer(orderCargoRouteDTO.getName(),
                customerRepository.findByUsername(username).get()) != null) {
            return new ResponseEntity<>(HttpStatus.valueOf("Груз с таким газванием уже существует. Придумайте другое название."));
        }
        Order newOrder = new Order(orderCargoRouteDTO.getName(), orderCargoRouteDTO.getDate(), orderCargoRouteDTO.getDocuments(),
                orderCargoRouteDTO.getContainer(), orderCargoRouteDTO.getExpress());
        Cargo newCargo = new Cargo(orderCargoRouteDTO.getWeight(),
                orderCargoRouteDTO.getLength(), orderCargoRouteDTO.getHeight(), orderCargoRouteDTO.getWidth(),
                orderCargoRouteDTO.getAmount(), orderCargoRouteDTO.getType(),
                orderCargoRouteDTO.getStack(), orderCargoRouteDTO.getExpress());
        Route newRoute = new Route(pointRepository.findById(orderCargoRouteDTO.getStartPoint()),
                pointRepository.findById(orderCargoRouteDTO.getEndPoint()));

        if (orderCargoRouteDTO.getType().equals("Наливной")) {
            newCargo.setVolume(orderCargoRouteDTO.getAmount());
        } else {
            newCargo.setVolume(orderCargoRouteDTO.getLength()
                    * orderCargoRouteDTO.getHeight()
                    * orderCargoRouteDTO.getWidth());
        }

        newOrder.setCargo(newCargo);
        newOrder.setRoute(newRoute);
        newCargo.setOrder(newOrder);

        newOrder.setCustomer(customerRepository.findUserByUsername(username));

        try {
            if (newOrder.getContainer()) {
                newOrder.setPrice(newOrder.getPrice() + 5);
            }
            if (newOrder.getDocuments()) {
                newOrder.setPrice(newOrder.getPrice() + 3);
            }
            newRoute.setPrice(newOrder.getPrice());
            newOrder.getCustomer().setCountOrders(newOrder.getCustomer().getCountOrders() + 1);
            cargoRepository.save(newCargo);
            routeRepository.save(newRoute);
            orderRepository.save(newOrder);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("...");

        return new ResponseEntity<>(orderCargoRouteDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(value = "/api/test/client/delivery/{username}")
    public List<GetAllOrdersUserDTO> getAllOrdersUser(@PathVariable("username") String username) {

        System.out.printf("getAllOrdersUser");

        List<GetAllOrdersUserDTO> getAllOrdersUserDTOList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAllByCustomer(customerRepository.findUserByUsername(username));

        for (Order order : orderList) {
            getAllOrdersUserDTOList.add(new GetAllOrdersUserDTO(order.getId_order(), null, order.getName(),
                    order.getStatus()));
        }

        System.out.println("...");
        return getAllOrdersUserDTOList;
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/client/delivery/delivery-details/{id_order}")
    public Order_Cargo_RouteDTO getOrder(@PathVariable("id_order") Long id_order) {

        System.out.printf("getOrder: " + id_order);
        Optional<Order> order = orderRepository.findById(id_order);
        System.out.println("...");
        if (order.isPresent()) {
            return Order_Cargo_RouteDTO.fromModel(order.get());
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/delivery/getAllOrCaRoDTO")
    public List<Order_Cargo_RouteDTO> getAllOrCaRoDTO() {

        System.out.printf("getAllOrCaRoDTO");

        Iterable<Order> orders = orderRepository.findAll();
        List<Order_Cargo_RouteDTO> orderDTOList = new ArrayList<>();
        for (Order order: orders) {
            orderDTOList.add(Order_Cargo_RouteDTO.fromModel(order));
        }

        System.out.println("...");
        return orderDTOList;
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/getAloneOrder/{id_order}")
    public OrderDTO getAloneOrder(@PathVariable("id_order") Long id_order) {

        System.out.printf("getAloneOrder: " + id_order);
        Optional<Order> order = orderRepository.findById(id_order);
        System.out.println("...");
        if (order.isPresent()) {
            return OrderDTO.fromModel(order.get());
        } else {
            return null;
        }
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/api/test/client/delivery/updateOrder/{username}/{id_order}")
    public ResponseEntity<Order_Cargo_RouteDTO> updateOrder(@PathVariable("username") String username,
                                                            @PathVariable("id_order") Long id_order,
                                                            @RequestBody Order_Cargo_RouteDTO order) {
        System.out.println("Update Order with ID = " + order.getId_order() + "...");

        Optional<Order> optionalOrder = orderRepository.findById(id_order);

        if (optionalOrder.isPresent()) {

            // Update container info
            optionalOrder.get().setName(order.getName());
            if (order.getContainer() && !optionalOrder.get().getContainer()) {
                optionalOrder.get().setPrice(optionalOrder.get().getPrice() + 5);
            } else if(optionalOrder.get().getPrice() > 3 && optionalOrder.get().getContainer() && !order.getContainer()) {
                optionalOrder.get().setPrice(optionalOrder.get().getPrice() - 5);
            }
            optionalOrder.get().setContainer(order.getContainer());

            // Update docs info
            if (order.getDocuments() && !optionalOrder.get().getDocuments()) {
                optionalOrder.get().setPrice(optionalOrder.get().getPrice() + 3);
            } else if(optionalOrder.get().getPrice() >= 3 && optionalOrder.get().getDocuments() && !order.getDocuments()) {
                optionalOrder.get().setPrice(optionalOrder.get().getPrice() - 3);
            }
            optionalOrder.get().setDocuments(order.getDocuments());

            // Update stack info
            if (optionalOrder.get().getCargo().getStack() != order.getStack()) {
                optionalOrder.get().getCargo().setStack(order.getStack());
            }

            // Update express info
            if (order.getExpress() && !optionalOrder.get().getExpress()) {
                optionalOrder.get().setPrice((float) (optionalOrder.get().getPrice() * 1.5));
            } else if(optionalOrder.get().getPrice() >= 3 && optionalOrder.get().getExpress() && !order.getExpress()) {
                optionalOrder.get().setPrice((float) (optionalOrder.get().getPrice() / 1.5));
            }
            optionalOrder.get().setExpress(order.getExpress());

            optionalOrder.get().getRoute().setPrice(optionalOrder.get().getPrice());
            return new ResponseEntity<>(Order_Cargo_RouteDTO.fromModel(orderRepository.save(optionalOrder.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @PutMapping("/api/test/order/updateOrderStatus/{id_order}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable("id_order") Long id_order,
                                                            @RequestBody OrderDTO order) {
        System.out.println("updateOrderStatus with ID = " + id_order + "...");

        Optional<Order> optionalOrder = orderRepository.findById(id_order);

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setStatus(order.getStatus());
            return new ResponseEntity<>(OrderDTO.fromModel(orderRepository.save(optionalOrder.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/api/test/client/delivery/delete/{id_order}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id_order") Long id_order) {
        System.out.println("Delete Order with ID = " + id_order + "...");

        Optional<Order> order = orderRepository.findById(id_order);

        if (order.isPresent()) {
            for (Dictionary dictionary : order.get().getRoute().getDictionaries()) {
                segmentRepository.delete(dictionary.getSegment());
            }
            orderRepository.deleteById(id_order);
            return new ResponseEntity<>("Заявка удалина", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/analyst/getAllOrders")
    public List<OrderDTO> getAllOrders() {

        System.out.printf("getAllOrders");

        Iterable<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order: orders) {
            orderDTOList.add(OrderDTO.fromModel(order));
        }

        System.out.println("...");
        return orderDTOList;
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping("/api/test/order/getStatus/{id_order}")
    public ResponseEntity<String> getStatus(@PathVariable("id_order") Long id_order) {
        System.out.printf("getStatus Order with ID = " + id_order);

        Optional<Order> order = orderRepository.findById(id_order);

        if (order.isPresent()) {
            System.out.println("...");
            return new ResponseEntity<>(order.get().getStatus(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
