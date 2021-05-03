/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.telacad.clinicamed.entity.MedicalSpeciality;
import ro.telacad.clinicamed.entity.Role;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.form.RegisterForm;
import ro.telacad.clinicamed.service.MedicalSpecialityService;
import ro.telacad.clinicamed.service.RoleService;
import ro.telacad.clinicamed.service.UsersService;
import ro.telacad.clinicamed.web.util.FacesMessageUtil;
import ro.telacad.clinicamed.web.util.RequestContextUtil;
import ro.telacad.clinicamed.web.util.SessionUtil;

@Component
@ManagedBean
@SessionScoped
public class LoginView implements Serializable {
    
    private String username;
    private String password;
    private Integer idRole;
    private RegisterForm registerForm;
    private List<MedicalSpeciality> medicalSpecialities;
    private boolean loggedIn;
    
    @Autowired
    private UsersService usersService;
    @Autowired
    private MedicalSpecialityService medicalSpecialityService;
    @Autowired
    private RoleService roleService;
    
    @PostConstruct
    public void init() {
        registerForm = new RegisterForm();
        medicalSpecialities = medicalSpecialityService.listSpeciality();
    }
    
    public String login(String username) {
        Users user = usersService.getByUsernameAndPassword(username, password);
        if(user != null) {  
            SessionUtil.setUserId(user.getId());
            SessionUtil.setUserName(username);
            idRole = user.getIdRole().getId();
            loggedIn = true;
            return "main.xhtml?faces-redirect=true";
        } else {
            FacesMessageUtil.addMessageError("Logare nereusita!", 
                    "User si parola introduse gresit, sau nu exista userul! Va rugam sa va inregistrati!");
            loggedIn = false;
            return "login";
        }
    }
    
    public String logout() {
        SessionUtil.invalidateSession();
        loggedIn = false;
        return "login";
    }
    
    public void onShowDlgRegister() {
        registerForm = new RegisterForm();
    }
    
    public void register() {
        Users users = usersService.getByUsername(registerForm.getUsername());
        if(users != null) {
            FacesMessageUtil.addMessageWarn("Userul " + username + " exista deja inregistrat!");
        } else {
            users = new Users();
            users.setCnp(registerForm.getCnp());
            users.setEmail(registerForm.getEmail());
            users.setFirstName(registerForm.getFirstname());
            MedicalSpeciality medicalSpeciality = medicalSpecialityService.getById(registerForm.getIdSpeciality());
            users.setIdMedicalSpeciality(medicalSpeciality);
            Role rol = roleService.getDoctorRole();
            users.setIdRole(rol);
            users.setLastName(registerForm.getLastname());
            users.setPassword(registerForm.getPassword());
            users.setRegisterDate(new Date());
            users.setRegistrationNumber(registerForm.getNumarRegistru());
            users.setTelephon(registerForm.getTelephon());
            users.setEmail(registerForm.getEmail());
            users.setUserName(registerForm.getUsername());
            
            try {
                usersService.save(users);
                username = registerForm.getUsername();
                password = registerForm.getPassword();
                FacesMessageUtil.addMessageInfo("Inregistrare cu succes! Va rugam sa va autentificati in aplicatie.");
                RequestContextUtil.hideDialog("dlgRegister");
            } catch (Exception e) {
                FacesMessageUtil.addMessageSaveError();
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterForm getRegisterForm() {
        return registerForm;
    }

    public List<MedicalSpeciality> getMedicalSpecialities() {
        return medicalSpecialities;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
     public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
