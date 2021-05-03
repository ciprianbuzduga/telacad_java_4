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
import ro.telacad.clinicamed.entity.Patients;
import ro.telacad.clinicamed.repo.PatientsRepository;

@Service
public class PatientsService {
    
    @Autowired
    private PatientsRepository patientsRepo;
    
    public Patients getByCnp(String cnp) {
        return patientsRepo.getByCnp(cnp);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Patients save(Patients patients) {
        return patientsRepo.save(patients);
    }
    
    public Patients getById(Integer id) {
        return patientsRepo.getById(id);
    }
    
    public List<Patients> list() {
        return patientsRepo.list();
    }
}
