package Application.Controllers;

import Application.DTO.TypeDeliveryDTO;
import Application.Entites.Type_delivery;
import Application.Repositories.Type_deliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
