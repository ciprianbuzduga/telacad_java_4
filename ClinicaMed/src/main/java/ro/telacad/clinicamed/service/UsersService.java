/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.repo.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;
    
    public Users getByUsername(String username) {
        return usersRepo.getUserByUsernameAndPassword(username, null);
    }
    
    public Users getByUsernameAndPassword(String username, String password) {
        return usersRepo.getUserByUsernameAndPassword(username, password);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Users save(Users user) {
        return usersRepo.save(user);
    }
    
    public List<Users> list() {
        return usersRepo.list();
    }
    
    public Users getUserById(Integer id) {
        return usersRepo.getUserById(id);
    }
}
