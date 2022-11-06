package Projekt.sbirka.Controller;
import java.util.List;

import Projekt.sbirka.Entity.Pics;
import Projekt.sbirka.Service.PicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/ps")
public class PicsController {
    @Autowired
    PicsService picsService;
    @GetMapping("/pics")
    public List<Pics> getAllPics(){
        return picsService.getAllPics();
    }
    @GetMapping("/pics/{id}")
    public Pics getPics(@PathVariable("id") int id) {
        return picsService.getPicsById(id);
    }
    @DeleteMapping("/pics/{id}")
    public void deletePics(@PathVariable("id") int id) {
        picsService.deletePicsById(id);
    }
    @PostMapping("/pics")
    public void addPics(@RequestBody Pics pics) {
        picsService.saveOrUpdate(pics);
    }
    @PutMapping("/pics")
    public void updatePics(@RequestBody Pics pics) {
        picsService.saveOrUpdate(pics);
    }

}
