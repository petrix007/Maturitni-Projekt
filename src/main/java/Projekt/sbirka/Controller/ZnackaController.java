package Projekt.sbirka.Controller;
import java.util.List;

import Projekt.sbirka.Entity.Znacka;
import Projekt.sbirka.Service.ZnackaService;
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
@RequestMapping(path = "/znck")
public class ZnackaController {
    @Autowired
    ZnackaService znackaService;
    @GetMapping("/znacky")
    public List<Znacka> getAllZnacka(){
        return znackaService.getAllZnacka();
    }
    @GetMapping("/znacky/{id}")
    public Znacka getZnacka(@PathVariable("id") int id) {
        return znackaService.getZnackaById(id);
    }
    @DeleteMapping("/znacky/{id}")
    public void deleteZnacka(@PathVariable("id") int id) {
        znackaService.deleteZnackaById(id);
    }
    @PostMapping("/znacky")
    public void addZnacka(@RequestBody Znacka znacka) {
        znackaService.saveOrUpdate(znacka);
    }
    @PutMapping("/znacky")
    public void updateZnacka(@RequestBody Znacka znacka) {
        znackaService.saveOrUpdate(znacka);
    }

}
