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
import ro.telacad.clinicamed.entity.Appointments;
import ro.telacad.clinicamed.entity.Appointments_;
import ro.telacad.clinicamed.entity.Users_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class AppointmentsRepository extends BaseRepository<Appointments> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentsRepository() {
        super(Appointments.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Appointments> listAppointmentsByUserId(Integer userId) {
        if(userId == null) return null;
        JpaCriteria<Appointments> crit = getCriteria();
        crit.addEqual(Appointments_.user, Users_.id, userId);
        return crit.listOrderBy(Appointments_.day, false);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Appointments getById(Integer id) {
        return getCriteria().getByProp(Appointments_.id, id);
    }
}
