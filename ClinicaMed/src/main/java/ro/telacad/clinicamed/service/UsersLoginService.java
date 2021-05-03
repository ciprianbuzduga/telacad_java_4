/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.entity.UsersLogin;
import ro.telacad.clinicamed.repo.UsersLoginRepository;

@Service
public class UsersLoginService {
    
    @Autowired
    private UsersLoginRepository usersLoginRepo;

    @Transactional(propagation = Propagation.REQUIRED)
    public void logLoggin(Users user, boolean success) {
        UsersLogin usersLogin = new UsersLogin();
        usersLogin.setLoginDate(new Date());
        usersLogin.setLoginOk(success ? 1 : 0);
        usersLogin.setUser(user);
        
        usersLoginRepo.save(usersLogin);
    }
}
