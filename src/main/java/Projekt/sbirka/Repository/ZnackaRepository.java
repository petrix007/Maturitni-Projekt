package Projekt.sbirka.Repository;

import Projekt.sbirka.Entity.Znacka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ZnackaRepository extends CrudRepository<Znacka, Integer>  {

}