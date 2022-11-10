package Projekt.sbirka.Repository;

import Projekt.sbirka.Entity.Params;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamsRepository extends CrudRepository<Params, Integer> {
}
