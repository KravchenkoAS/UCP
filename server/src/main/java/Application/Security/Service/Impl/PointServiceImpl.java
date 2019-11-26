package Application.Security.Service.Impl;

import Application.Entites.Point;
import Application.Repositories.PointRepository;
import Application.Security.Service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public Point findPointById_point(Long id_point) {
        //Point point = pointRepository.findAll()
        return null;
    }
}
