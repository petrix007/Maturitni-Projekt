package Projekt.sbirka.Controller;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Entity.Users;
import Projekt.sbirka.Service.ModelyService;
import Projekt.sbirka.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sbrk")
public class UsersController {
    @Autowired
    UsersService usersService;
    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public Users getUsers(@PathVariable("id") int id) {
        return usersService.getUsersById(id);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable("id") int id) {
        usersService.deleteUsersById(id);
    }
    @PostMapping("/users")
    public void addUsers(@RequestBody Users users) {
        usersService.saveOrUpdate(users);
    }
    @PutMapping("/users")
    public void updateUsers(@RequestBody Users users) {
        usersService.saveOrUpdate(users);
    }
}
