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
import ro.telacad.clinicamed.entity.Appointments;
import ro.telacad.clinicamed.entity.Patients;
import ro.telacad.clinicamed.entity.TypeConsultation;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.form.AppointmentForm;
import ro.telacad.clinicamed.service.AppointmentsService;
import ro.telacad.clinicamed.service.PatientsService;
import ro.telacad.clinicamed.service.TypeConsultationService;
import ro.telacad.clinicamed.service.UsersService;
import ro.telacad.clinicamed.web.util.FacesMessageUtil;
import ro.telacad.clinicamed.web.util.SessionUtil;
import ro.telacad.clinicamed.web.util.Utils;

@Component
@Named
@ViewScoped
public class AppointmentsView implements Serializable {
    
    @Autowired
    private AppointmentsService appointmentsService;
    @Autowired
    private TypeConsultationService typeConsultationService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private UsersService usersService;
    
    private List<Appointments> appointments;
    private List<TypeConsultation> typeConsultations;
    private List<Patients> patientses;
    private AppointmentForm appointmentForm;
    
    @PostConstruct
    public void init() {
        appointments = appointmentsService.listAppointmentsByUserId(SessionUtil.getUserId());
        typeConsultations = typeConsultationService.listTypeConsultations();
        patientses = patientsService.list();
        appointmentForm = new AppointmentForm();
    }

    public int sortByPatient(Object o1, Object o2) {
        Patients p1 = (Patients) o1;
        Patients p2 = (Patients) o2;
        return Utils.sortByPatientFirstLastName(p1, p2);
    }
    
    public void showDlgAppointment(Appointments app) {
        appointmentForm = new AppointmentForm();
        if(app != null) {
            appointmentForm.setId(app.getId());
            appointmentForm.setDay(app.getDay());
            appointmentForm.setDescription(app.getDescription());
            appointmentForm.setPatientId(app.getPatient().getId());
            appointmentForm.setTypeConsultationId(app.getConsultation().getId());
            appointmentForm.setStartTime(app.getStartTime());
            appointmentForm.setEndTime(app.getEndTime());
        }
    }
    
    public void save() {
        Appointments app = appointmentsService.getById(appointmentForm.getId());
        if(app == null) {
            app = new Appointments();
        }
        TypeConsultation tc = typeConsultationService.getById(appointmentForm.getTypeConsultationId());
        app.setConsultation(tc);
        app.setDay(appointmentForm.getDay());
        app.setDescription(appointmentForm.getDescription());
        Patients p = patientsService.getById(appointmentForm.getPatientId());
        app.setPatient(p);
        Users u = usersService.getUserById(SessionUtil.getUserId());
        app.setUser(u);
        app.setStartTime(appointmentForm.getStartTime());
        app.setEndTime(appointmentForm.getEndTime());
        
        try {
            appointmentsService.save(app);
            appointments = appointmentsService.listAppointmentsByUserId(SessionUtil.getUserId());
            FacesMessageUtil.addMessageSaveOk();
        } catch (Exception e) {
            FacesMessageUtil.addMessageSaveError();
            e.printStackTrace();
        }
    }
    
    public List<Appointments> getAppointments() {
        return appointments;
    }

    public List<TypeConsultation> getTypeConsultations() {
        return typeConsultations;
    }

    public AppointmentForm getAppointmentForm() {
        return appointmentForm;
    }

    public List<Patients> getPatientses() {
        return patientses;
    }
}
