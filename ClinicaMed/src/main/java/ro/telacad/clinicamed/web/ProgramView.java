/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.telacad.clinicamed.entity.ProgramJob;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.form.ProgramForm;
import ro.telacad.clinicamed.service.ProgramJobService;
import ro.telacad.clinicamed.service.UsersService;
import ro.telacad.clinicamed.web.util.FacesMessageUtil;
import ro.telacad.clinicamed.web.util.Utils;

@Component
@Named
@ViewScoped
public class ProgramView implements Serializable {
    
    @Autowired
    private ProgramJobService programJobService;
    @Autowired
    private UsersService usersService;
    
    private List<ProgramJob> programJobs;
    private ProgramForm programForm;
    private List<Users> users;
    
    @PostConstruct
    public void init() {
        programJobs = programJobService.list();
        users = usersService.list();
        programForm = new ProgramForm();
    }
    
    public int sortByUser(Object o1, Object o2) {
        Users a1 = (Users) o1;
        Users a2 = (Users) o2;
        return Utils.sortByUserFirstLastName(a1, a2);
    }
    
    public void save() {
        ProgramJob pj = new ProgramJob();
        pj.setDay(programForm.getDay());
        pj.setJobDescription(programForm.getJobDescription());
        pj.setEndTime(programForm.getEndTime());
        pj.setStartTime(programForm.getStartTime());
        Users user = usersService.getUserById(programForm.getIdUser());
        pj.setUser(user);
        try {
            programJobService.save(pj);
            programJobs = programJobService.list();
            FacesMessageUtil.addMessageSaveOk();
        } catch (Exception e) {
            FacesMessageUtil.addMessageSaveError();
            e.printStackTrace();
        }
    }

    public List<ProgramJob> getProgramJobs() {
        return programJobs;
    }

    public ProgramForm getProgramForm() {
        return programForm;
    }

    public List<Users> getUsers() {
        return users;
    }
}
