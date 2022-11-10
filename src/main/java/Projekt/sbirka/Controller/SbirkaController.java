package Projekt.sbirka.Controller;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Entity.Sbirka;
import Projekt.sbirka.Service.ModelyService;
import Projekt.sbirka.Service.SbirkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sbk")
public class SbirkaController {
    @Autowired
    SbirkaService sbirkaService;
    @GetMapping("/sbirka")
    public List<Sbirka> getAllSbirka(){
        return sbirkaService.getAllSbirka();
    }
    @GetMapping("/sbirka/{id}")
    public Sbirka getSbirka(@PathVariable("id") int id) {
        return sbirkaService.getSbirkaById(id);
    }
    @DeleteMapping("/sbirka/{id}")
    public void deleteSbirka(@PathVariable("id") int id) {
        sbirkaService.deleteSbirkaById(id);
    }
    @PostMapping("/sbirka")
    public void addSbirka(@RequestBody Sbirka sbirka) {
        sbirkaService.saveOrUpdate(sbirka);
    }
    @PutMapping("/sbirka")
    public void updateSbirka(@RequestBody Sbirka sbirka) {
        sbirkaService.saveOrUpdate(sbirka);
    }
}
