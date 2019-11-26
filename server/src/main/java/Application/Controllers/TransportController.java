package Application.Controllers;

import Application.DTO.TransportDTO;
import Application.Entites.Transport;
import Application.Repositories.TransportRepository;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/test/transport/updateTransport/{id_transport}")
    public ResponseEntity<TransportDTO> updateTransport(@PathVariable("id_transport") Long id_transport,
                                                    @RequestBody TransportDTO transportDTO) {
        System.out.println("Update Transport with ID = " + transportDTO.getId_transport() + "...");

        Optional<Transport> optionalTransport = transportRepository.findById(id_transport);

        if (optionalTransport.isPresent()) {
            //ДОБАВИТЬ ИЗМЕНЕНИЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            return new ResponseEntity<>(TransportDTO.fromModel(transportRepository.save(optionalTransport.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/test/transport/delete/{id_transport}")
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
