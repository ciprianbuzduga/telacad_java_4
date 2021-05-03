/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.repo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.telacad.clinicamed.entity.Patients;
import ro.telacad.clinicamed.entity.Patients_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PatientsRepository extends BaseRepository<Patients> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientsRepository() {
        super(Patients.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Patients getByCnp(String cnp) {
        return getCriteria().getByProp(Patients_.cnp, cnp);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Patients getById(Integer id) {
        return getCriteria().getByProp(Patients_.id, id);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Patients> list() {
        return getCriteria().listOrderBy(Patients_.firstName, true);
    }
}
