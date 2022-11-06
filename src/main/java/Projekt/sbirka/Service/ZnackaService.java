package Projekt.sbirka.Service;
import java.util.ArrayList;
import java.util.List;

import Projekt.sbirka.Entity.Znacka;
import Projekt.sbirka.Repository.ZnackaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ZnackaService {
    @Autowired
    ZnackaRepository repository;
    public Znacka getZnackaById(int id) {
        return repository.findById(id).get();
    }
    public List<Znacka> getAllZnacka(){
        List<Znacka> znacka = new ArrayList<Znacka>();
        repository.findAll().forEach(Znacka -> znacka.add(Znacka));
        return znacka;
    }
    public void saveOrUpdate(Znacka znacka) {
        repository.save(znacka);
    }
    public void deleteZnackaById(int id) {
        repository.deleteById(id);
    }

}
