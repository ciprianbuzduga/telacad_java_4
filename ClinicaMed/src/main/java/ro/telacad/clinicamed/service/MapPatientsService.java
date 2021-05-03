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
import ro.telacad.clinicamed.entity.MapPatientsDoctor;
import ro.telacad.clinicamed.repo.MapPatientsDoctorRepository;

@Service
public class MapPatientsService {
    
    @Autowired
    private MapPatientsDoctorRepository mapPatientsDoctorRepo;
    
    public List<MapPatientsDoctor> listPatientsByUserId(Integer idUser) {
        return mapPatientsDoctorRepo.listPatientsByUserId(idUser);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public MapPatientsDoctor save(MapPatientsDoctor mpd) {
        return mapPatientsDoctorRepo.save(mpd);
    }
    
    public MapPatientsDoctor getPatientsDoctorByCnp(Integer idUser, String cnp) {
        return mapPatientsDoctorRepo.getPatientsDoctorByCnp(idUser, cnp);
    }
    
    public List<String> listCnpsNotUser(Integer userId, String likeCnp) {
        return mapPatientsDoctorRepo.listCnpsNotUser(userId, likeCnp);
    }
}
