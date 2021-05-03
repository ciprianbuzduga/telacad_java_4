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
import ro.telacad.clinicamed.entity.MedicalSpeciality;
import ro.telacad.clinicamed.entity.MedicalSpeciality_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class MedicalSpecialityRepository extends BaseRepository<MedicalSpeciality> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicalSpecialityRepository() {
        super(MedicalSpeciality.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MedicalSpeciality> listSpeciality() {
        return getCriteria().listOrderBy(MedicalSpeciality_.name, true);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public MedicalSpeciality getById(Integer id) {
        if(id == null) return null;
        return getCriteria().getByProp(MedicalSpeciality_.id, id);
    }
}
