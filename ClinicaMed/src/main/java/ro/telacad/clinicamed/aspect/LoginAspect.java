/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.aspect;

import java.io.Serializable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.service.UsersLoginService;
import ro.telacad.clinicamed.service.UsersService;

@Component
@Aspect
public class LoginAspect implements Serializable {
    
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersLoginService usersLoginService;
    
    /**
     * Logheaza daca s-a facut un login cu succes pentru userul respectiv
     */
    @Around("execution(** ro.telacad.clinicamed.web.LoginView.login(..))")
    public String loginIntercept(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Aspect login...");
        String methReturn = (String) pjp.proceed();
        Object[] params = pjp.getArgs();
        if(params != null) {
            String username = (String) params[0];
            Users user = usersService.getByUsername(username);
            if(user != null) {
                if(!methReturn.equals("login")) {
                    usersLoginService.logLoggin(user, true);
                } else {
                    usersLoginService.logLoggin(user, false);
                }
            } 
        }
        return methReturn;
    }
}
