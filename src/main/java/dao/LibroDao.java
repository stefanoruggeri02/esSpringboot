package dao;

import model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroDao extends CrudRepository<Libro, Long> {
    Libro findById(long id);
}
