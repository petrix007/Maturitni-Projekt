package Projekt.sbirka.Service;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Repository.ModelyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelyService {
    @Autowired
    ModelyRepository repository;
    public Modely getModelyById(int id) {
        return repository.findById(id).get();
    }
    public List<Modely> getAllModely(){
        List<Modely> modely = new ArrayList<Modely>();
        repository.findAll().forEach(Modely -> modely.add(Modely));
        return modely;
    }
    public void saveOrUpdate(Modely modely) {
        repository.save(modely);
    }
    public void deleteModelyById(int id) {
        repository.deleteById(id);
    }
}
