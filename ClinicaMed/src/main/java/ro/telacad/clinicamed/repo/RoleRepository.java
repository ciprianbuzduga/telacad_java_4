/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.telacad.clinicamed.entity.Role;
import ro.telacad.clinicamed.entity.Role_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RoleRepository extends BaseRepository<Role> {
    
    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_MANAGER = 2;
    public static final Integer ROLE_DOCTOR = 3;
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleRepository() {
        super(Role.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role getAdminRole() {
        return getCriteria().getByProp(Role_.id, ROLE_ADMIN);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role getManagerRole() {
        return getCriteria().getByProp(Role_.id, ROLE_MANAGER);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Role getDoctorRole() {
        return getCriteria().getByProp(Role_.id, ROLE_DOCTOR);
    }
}
