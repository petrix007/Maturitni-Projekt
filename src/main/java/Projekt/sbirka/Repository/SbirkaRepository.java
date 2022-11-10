package Projekt.sbirka.Repository;

import Projekt.sbirka.Entity.Sbirka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SbirkaRepository extends CrudRepository<Sbirka, Integer> {
}
