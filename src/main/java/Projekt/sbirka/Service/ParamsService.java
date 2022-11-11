package Projekt.sbirka.Service;

import Projekt.sbirka.Entity.Params;
import Projekt.sbirka.Repository.ParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParamsService {
    @Autowired
    ParamsRepository repository;
    public Params getParamsById(int id) {
        return repository.findById(id).get();
    }
    public List<Params> getAllParams(){
        List<Params> params = new ArrayList();
        repository.findAll().forEach(Params -> params.add(Params));
        return params;
    }
    public void saveOrUpdate(Params params) {
        repository.save(params);
    }
    public void deleteParamsById(int id) {
        repository.deleteById(id);
    }
}
