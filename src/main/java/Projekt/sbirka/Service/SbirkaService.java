package Projekt.sbirka.Service;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Entity.Sbirka;
import Projekt.sbirka.Repository.ModelyRepository;
import Projekt.sbirka.Repository.SbirkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SbirkaService {
    @Autowired
    SbirkaRepository repository;
    public Sbirka getSbirkaById(int id) {
        return repository.findById(id).get();
    }
    public List<Sbirka> getAllSbirka(){
        List<Sbirka> sbirka = new ArrayList<Sbirka>();
        repository.findAll().forEach(Sbirka -> sbirka.add(Sbirka));
        return sbirka;
    }
    public void saveOrUpdate(Sbirka sbirka) {
        repository.save(sbirka);
    }
    public void deleteSbirkaById(int id) {
        repository.deleteById(id);
    }
}
