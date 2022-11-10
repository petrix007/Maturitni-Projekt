package Projekt.sbirka.Repository;

import Projekt.sbirka.Entity.Modely;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelyRepository extends CrudRepository<Modely, Integer> {
}
