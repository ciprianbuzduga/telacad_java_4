/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.telacad.clinicamed.entity.Role;
import ro.telacad.clinicamed.repo.RoleRepository;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;
    
    public Role getAdminRole() {
        return roleRepo.getAdminRole();
    }
    
    public Role getManagerRole() {
        return roleRepo.getManagerRole();
    }
    
    public Role getDoctorRole() {
        return roleRepo.getDoctorRole();
    }
    
}
