package Application.Security.Service;

import Application.Entites.Cargo;
import Application.Entites.Order;
import Application.Entites.User;

import java.util.Optional;

public interface CargoService {

    Cargo addCargo(Cargo cargo);
    Cargo findByName(String name);
    Cargo findByOrder(Order order);
    Optional<Cargo> findById_order(Long id_order);
    Cargo editCargo(Cargo cargo);
    Cargo deleteCargo(Cargo cargo);
    Iterable<Cargo> getAll();

}
