package Application.Controllers;

import Application.DTO.FuelDTO;
import Application.DTO.TransportDTO;
import Application.Entites.Fuel;
import Application.Entites.Transport;
import Application.Repositories.FuelRepository;
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
public class FuelController {

    @Autowired
    private FuelRepository fuelRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/fuel/getAllFuels")
    public List<FuelDTO> getAllFuels() {

        System.out.println("getAllFuels...");
        Iterable<Fuel> fuels = fuelRepository.findAll();
        List<FuelDTO> fuelDTOList = new ArrayList<>();
        for (Fuel fuel: fuels) {
            fuelDTOList.add(FuelDTO.fromModel(fuel));
        }

        System.out.println("...");
        return fuelDTOList;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/test/fuel/updateFuel/{id_fuel}")
    public ResponseEntity<FuelDTO> updateFuel(@PathVariable("id_fuel") Long id_fuel,
                                           @RequestBody FuelDTO fuelDTO) {
        System.out.println("Update Fuel with ID = " + id_fuel);

        Optional<Fuel> optionalFuel = fuelRepository.findById(id_fuel);

        if (optionalFuel.isPresent()) {
            optionalFuel.get().setPrice(fuelDTO.getPrice());
            System.out.println("...");
            return new ResponseEntity<>(FuelDTO.fromModel(fuelRepository.save(optionalFuel.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @PostMapping("api/test/fuel/createFuel")
    public ResponseEntity<FuelDTO> createFuel(@RequestBody FuelDTO fuelDTO) {
        System.out.println("createFuel");

        Fuel fuel = new Fuel();
        fuel.setName(fuelDTO.getName());
        fuel.setPrice(fuelDTO.getPrice());

        System.out.println("...");
        return new ResponseEntity<>(FuelDTO.fromModel(fuelRepository.save(fuel)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/test/fuel/deleteFuel/{id_fuel}")
    public ResponseEntity<String> deleteFuel(@PathVariable("id_fuel") Long id_fuel) {
        System.out.println("Delete Fuel with ID = " + id_fuel + "...");

        if (fuelRepository.findById(id_fuel).isPresent()) {
            fuelRepository.deleteById(id_fuel);
            return new ResponseEntity<>("Выбраный тип топлива удален", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
