package Application.Controllers;

import Application.DTO.TypeDeliveryDTO;
import Application.Entites.Type_delivery;
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
public class TypeDeliveryController {

    @Autowired
    private Type_deliveryRepository typeDeliveryRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN')")
    @GetMapping(value = "/api/test/type_delivery/getAllTypeDeliveries")
    public List<TypeDeliveryDTO> getAllTypeDeliveries() {

        System.out.printf("getAllTypeDeliveries");

        Iterable<Type_delivery> type_deliveries = typeDeliveryRepository.findAll();
        List<TypeDeliveryDTO> typeDeliveryDTOList = new ArrayList<>();

        for (Type_delivery type_delivery: type_deliveries) {
            typeDeliveryDTOList.add(TypeDeliveryDTO.fromModel(type_delivery));
        }

        System.out.println("...");
        return typeDeliveryDTOList;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/test/type_delivery/createTypeDelivery")
    public ResponseEntity<TypeDeliveryDTO> createTypeDelivery(@RequestBody TypeDeliveryDTO typeDeliveryDTO) {
        System.out.println("createTypeDelivery");

        Type_delivery type_delivery = new Type_delivery();
        type_delivery.setName(typeDeliveryDTO.getName());

        System.out.println("...");
        return new ResponseEntity<>(TypeDeliveryDTO.fromModel(typeDeliveryRepository.save(type_delivery)), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/test/type_delivery/deleteTypeDelivery/{id_type_delivery}")
    public ResponseEntity<String> deleteTypeDelivery(@PathVariable("id_type_delivery") Long id_type_delivery) {
        System.out.println("Delete TypeDelivery with ID = " + id_type_delivery + "...");

        if (typeDeliveryRepository.findById(id_type_delivery).isPresent()) {
            typeDeliveryRepository.deleteById(id_type_delivery);
            return new ResponseEntity<>("Тип доставки удален", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
