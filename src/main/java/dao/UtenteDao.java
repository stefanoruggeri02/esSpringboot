package dao;

import model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Long> {
    Utente findById(long id);

    @Query("select s from Utente s where username= :username and password = :password")
    public Utente login(String username, String password);
}
