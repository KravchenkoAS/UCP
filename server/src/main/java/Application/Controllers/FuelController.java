package Application.Controllers;

import Application.DTO.FuelDTO;
import Application.Entites.Fuel;
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT') or hasRole('ANALYST') ")
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
    public ResponseEntity<Fuel> updateFuel(@PathVariable("id_fuel") Long id_fuel,
                                           @RequestBody Fuel fuel) {
        System.out.println("Update Fuel with ID = " + fuel.getId_fuel() + "...");

        Optional<Fuel> optionalFuel = fuelRepository.findById(id_fuel);

        if (optionalFuel.isPresent()) {
            return new ResponseEntity<>(fuelRepository.save(fuel),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
