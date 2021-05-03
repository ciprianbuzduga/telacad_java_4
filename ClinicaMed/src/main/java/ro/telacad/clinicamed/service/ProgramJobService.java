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
import ro.telacad.clinicamed.entity.ProgramJob;
import ro.telacad.clinicamed.repo.ProgramJobRepository;

@Service
public class ProgramJobService {
    
    @Autowired
    private ProgramJobRepository programJobRepo;
    
    public List<ProgramJob> list() {
        return programJobRepo.list();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public ProgramJob save(ProgramJob programJob) {
        return programJobRepo.save(programJob);
    }
}
