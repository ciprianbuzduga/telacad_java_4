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
import ro.telacad.clinicamed.entity.TypeConsultation;
import ro.telacad.clinicamed.entity.TypeConsultation_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class TypeConsultationRepository extends BaseRepository<TypeConsultation> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeConsultationRepository() {
        super(TypeConsultation.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TypeConsultation> listTypeConsultations() {
        return getCriteria().listOrderBy(TypeConsultation_.name, true);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public TypeConsultation getById(Integer id) {
        return getCriteria().getByProp(TypeConsultation_.id, id);
    }
}
