package Projekt.sbirka.Service;

import Projekt.sbirka.Entity.Modely;
import Projekt.sbirka.Entity.Users;
import Projekt.sbirka.Repository.ModelyRepository;
import Projekt.sbirka.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository repository;
    public Users getUsersById(int id) {
        return repository.findById(id).get();
    }
    public List<Users> getAllUsers(){
        List<Users> users = new ArrayList<Users>();
        repository.findAll().forEach(Users -> users.add(Users));
        return users;
    }
    public void saveOrUpdate(Users users) {
        repository.save(users);
    }
    public void deleteUsersById(int id) {
        repository.deleteById(id);
    }
}
