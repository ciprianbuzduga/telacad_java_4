/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.telacad.clinicamed.entity.MedicalSpeciality;
import ro.telacad.clinicamed.repo.MedicalSpecialityRepository;

@Service
public class MedicalSpecialityService {
    
    @Autowired
    private MedicalSpecialityRepository medicalSpecialityRepo;
    
    public List<MedicalSpeciality> listSpeciality() {
        return medicalSpecialityRepo.listSpeciality();
    }
    
    public MedicalSpeciality getById(Integer id) {
        return medicalSpecialityRepo.getById(id);
    }
}
