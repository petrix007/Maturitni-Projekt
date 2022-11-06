package Projekt.sbirka.Repository;

import Projekt.sbirka.Entity.Pics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicsRepository extends CrudRepository<Pics, Integer> {
}
