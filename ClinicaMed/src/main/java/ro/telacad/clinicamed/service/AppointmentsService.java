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
import ro.telacad.clinicamed.entity.Appointments;
import ro.telacad.clinicamed.repo.AppointmentsRepository;

@Service
public class AppointmentsService {
    
    @Autowired
    private AppointmentsRepository appointmentsRepository;
    
    public List<Appointments> listAppointmentsByUserId(Integer userId) {
        return appointmentsRepository.listAppointmentsByUserId(userId);
    }
    
    public Appointments getById(Integer id) {
        return appointmentsRepository.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Appointments save(Appointments app) {
        return appointmentsRepository.save(app);
    }
}
