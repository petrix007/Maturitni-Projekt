package Projekt.sbirka.Controller;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Entity.Params;
import Projekt.sbirka.Service.ModelyService;
import Projekt.sbirka.Service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sbrk")
public class ModelyController {
    @Autowired
    ModelyService modelyService;
    @GetMapping("/modely")
    public List<Modely> getAllModely(){
        return modelyService.getAllModely();
    }
    @GetMapping("/modely/{id}")
    public Modely getModely(@PathVariable("id") int id) {
        return modelyService.getModelyById(id);
    }
    @DeleteMapping("/modely/{id}")
    public void deleteModely(@PathVariable("id") int id) {
        modelyService.deleteModelyById(id);
    }
    @PostMapping("/modely")
    public void addModely(@RequestBody Modely modely) {
        modelyService.saveOrUpdate(modely);
    }
    @PutMapping("/modely")
    public void updateModely(@RequestBody Modely modely) {
        modelyService.saveOrUpdate(modely);
    }
}
