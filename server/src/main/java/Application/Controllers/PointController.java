package Application.Controllers;

import Application.Entites.Point;
import Application.Repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PointController {

    @Autowired
    PointRepository pointRepository;

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST')  ")
    @GetMapping(value = "/api/test/client/delivery/getAllPoints")
    public Iterable<Point> getAllPoints() {

        System.out.printf("getAllPoints");

//        List<Point> pointList = new ArrayList<>();
//        pointRepository.findAll().forEach(pointList::add);
//
//        for (Point point: pointList) {
//            System.out.println(point.toString());
//        }

        System.out.println("...");
        return pointRepository.findAll();
    }

    @PreAuthorize("hasRole('CLIENT') or hasRole('ANALYST')")
    @GetMapping(value = "/api/test/getPoint/{id_point}")
    public Optional<Point> getPoint(@PathVariable("id_point") String id_point) {
        System.out.printf("getPoint: " + id_point);
        System.out.println("...");
        return pointRepository.findById(Long.valueOf(id_point));
    }
}
