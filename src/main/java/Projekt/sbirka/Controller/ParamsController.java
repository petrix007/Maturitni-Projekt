package Projekt.sbirka.Controller;

import Projekt.sbirka.Entity.Params;
import Projekt.sbirka.Service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/prs")
public class ParamsController {
    @Autowired
    ParamsService paramsService;
    @GetMapping("/params")
    public List<Params> getAllParams(){
        return paramsService.getAllParams();
    }
    @GetMapping("/params/{id}")
    public Params getParams(@PathVariable("id") int id) {
        return paramsService.getParamsById(id);
    }
    @DeleteMapping("/params/{id}")
    public void deleteParams(@PathVariable("id") int id) {
        paramsService.deleteParamsById(id);
    }
    @PostMapping("/params")
    public void addParams(@RequestBody Params params) {
        paramsService.saveOrUpdate(params);
    }
    @PutMapping("/params")
    public void updateParams(@RequestBody Params params) {
        paramsService.saveOrUpdate(params);
    }
}
