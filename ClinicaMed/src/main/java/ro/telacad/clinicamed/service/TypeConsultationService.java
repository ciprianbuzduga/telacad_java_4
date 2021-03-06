/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.telacad.clinicamed.entity.TypeConsultation;
import ro.telacad.clinicamed.repo.TypeConsultationRepository;

@Service
public class TypeConsultationService {
    
    @Autowired
    private TypeConsultationRepository typeConsultationRepo;
    
     public List<TypeConsultation> listTypeConsultations() {
        return typeConsultationRepo.listTypeConsultations();
    }
    
    public TypeConsultation getById(Integer id) {
        return typeConsultationRepo.getById(id);
    }
}
