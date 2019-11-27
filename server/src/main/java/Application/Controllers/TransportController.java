package Application.Controllers;

import Application.DTO.TransportDTO;
import Application.Entites.Transport;
import Application.Repositories.FuelRepository;
import Application.Repositories.TransportRepository;
import Application.Repositories.Type_deliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TransportController {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private Type_deliveryRepository typeDeliveryRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/test/transport/updateTransport")
    public ResponseEntity<TransportDTO> updateTransport(@RequestBody TransportDTO transportDTO) {
        System.out.println("updateTransport");

        Optional<Transport> transport = transportRepository.findByName(transportDTO.getName());

        if(!transport.isPresent()) {
            transport.get().setName(transportDTO.getName());
        }

        transport.get().setCoefficient(transportDTO.getCoefficient());
        transport.get().setFuel_consumption(transportDTO.getFuel_consumption());
        transport.get().setMax_volume(transportDTO.getMax_volume());
        transport.get().setMax_weight(transportDTO.getMax_weight());
        transport.get().setSpeed(transportDTO.getSpeed());
        transport.get().setPrice(transportDTO.getPrice());
        transport.get().setFuel(fuelRepository.findByName(transportDTO.getFuel()));
        transport.get().setType_delivery(typeDeliveryRepository.findByName(transportDTO.getType_delivery()));

        System.out.println("...");
        return new ResponseEntity<>(TransportDTO.fromModel(transportRepository.save(transport.get())), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/test/transport/deleteTransport/{id_transport}")
    public ResponseEntity<String> deleteTransport(@PathVariable("id_transport") Long id_transport) {
        System.out.println("Delete Transport with ID = " + id_transport + "...");

        if (transportRepository.findById(id_transport).isPresent()) {
            transportRepository.deleteById(id_transport);
            return new ResponseEntity<>("Транспорт удален", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ANALYST') or hasRole('ADMIN') or hasRole('CLIENT')")
    @GetMapping(value = "/api/test/transport/getAllTransports")
    public List<TransportDTO> getAllTransports() {

        System.out.printf("getAllTransports");

        Iterable<Transport> transports = transportRepository.findAll();
        List<TransportDTO> transportDTOList = new ArrayList<>();
        for (Transport transport: transports) {
            transportDTOList.add(TransportDTO.fromModel(transport));
        }

        System.out.println("...");
        return transportDTOList;
    }
}
