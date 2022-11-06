package Projekt.sbirka.Service;

import Projekt.sbirka.Entity.Pics;
import Projekt.sbirka.Repository.PicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PicsService {
    @Autowired
    PicsRepository repository;

    public Pics getPicsById(int id) {
        return repository.findById(id).get();
    }
    public List<Pics> getAllPics(){
        List<Pics> pics = new ArrayList<Pics>();
        repository.findAll().forEach(Pics -> pics.add(Pics));
        return pics;
    }
    public void saveOrUpdate(Pics pics) {
        repository.save(pics);
    }
    public void deletePicsById(int id) {
        repository.deleteById(id);
    }
}
