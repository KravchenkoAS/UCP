package Application.Controllers;

import Application.Entites.Point;
import Application.Repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/client/delivery/getAllPoints")
    public Iterable<Point> getAllPoints() {

        System.out.printf("getAllPoints");

        System.out.println("...");
        return pointRepository.findAll();
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST') or hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @GetMapping(value = "/api/test/getPoint/{id_point}")
    public Optional<Point> getPoint(@PathVariable("id_point") String id_point) {
        System.out.printf("getPoint: " + id_point);
        System.out.println("...");
        return pointRepository.findById(Long.valueOf(id_point));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_TRANSPORTER')")
    @PostMapping("api/test/point/createPoint")
    public ResponseEntity<Point> createPoint(@RequestBody Point point) {
        System.out.println("createPoint...");
        return new ResponseEntity<>(pointRepository.save(point), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/test/point/deletePoint/{id_point}")
    public ResponseEntity<String> deletePoint(@PathVariable("id_point") Long id_point) {
        System.out.println("Delete Point with ID = " + id_point + "...");

        if (pointRepository.findById(id_point).isPresent()) {
            pointRepository.deleteById(id_point);
            return new ResponseEntity<>("Точка удален", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
