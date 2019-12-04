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

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @PostMapping("api/test/transport/updateTransport")
    public ResponseEntity<TransportDTO> updateTransport(@RequestBody TransportDTO transportDTO) {
        System.out.println("updateTransport");

        Optional<Transport> transportOptional = transportRepository.findByName(transportDTO.getName());
        Transport transport = new Transport();

        if(!transportOptional.isPresent()) {
            transport.setName(transportDTO.getName());
        } else {
            transport = transportOptional.get();
        }

        transport.setCoefficient(transportDTO.getCoefficient());
        transport.setFuel_consumption(transportDTO.getFuel_consumption());
        transport.setMax_volume(transportDTO.getMax_volume());
        transport.setMax_weight(transportDTO.getMax_weight());
        transport.setSpeed(transportDTO.getSpeed());
        transport.setPrice(transportDTO.getPrice());
        transport.setFuel(fuelRepository.findByName(transportDTO.getFuel()));
        transport.setType_delivery(typeDeliveryRepository.findByName(transportDTO.getType_delivery()));

        System.out.println("...");
        return new ResponseEntity<>(TransportDTO.fromModel(transportRepository.save(transport)), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
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

    @PreAuthorize("hasRole('ANALYST') or hasRole('ADMIN') or hasRole('CLIENT') or hasRole('ROLE_TRANSPORTER')")
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
