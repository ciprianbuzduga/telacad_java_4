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
import ro.telacad.clinicamed.entity.ProgramJob;
import ro.telacad.clinicamed.entity.ProgramJob_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ProgramJobRepository extends BaseRepository<ProgramJob>{

    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    public ProgramJobRepository() {
        super(ProgramJob.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProgramJob> list() {
        return getCriteria().listOrderBy(ProgramJob_.day, true);
    }
}
