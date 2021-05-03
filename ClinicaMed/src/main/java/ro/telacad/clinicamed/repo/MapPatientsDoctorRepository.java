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
import ro.telacad.clinicamed.entity.MapPatientsDoctor;
import ro.telacad.clinicamed.entity.MapPatientsDoctor_;
import ro.telacad.clinicamed.entity.Patients_;
import ro.telacad.clinicamed.entity.Users_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class MapPatientsDoctorRepository extends BaseRepository<MapPatientsDoctor> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapPatientsDoctorRepository() {
        super(MapPatientsDoctor.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MapPatientsDoctor> listPatientsByUserId(Integer idUser) {
        JpaCriteria<MapPatientsDoctor> crit = getCriteria();
        crit.addEqual(MapPatientsDoctor_.idUser, Users_.id, idUser);
        return crit.listOrderBy(MapPatientsDoctor_.idPatient, Patients_.firstName, true);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public MapPatientsDoctor getPatientsDoctorByCnp(Integer idUser, String cnp) {
        if(idUser == null || cnp == null) return null;
        
        JpaCriteria<MapPatientsDoctor> crit = getCriteria();
        crit.addEqual(MapPatientsDoctor_.idPatient, Patients_.cnp, cnp);
        crit.addEqual(MapPatientsDoctor_.idUser, Users_.id, idUser);
        
        return getCriteria().get();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> listCnpsNotUser(Integer userId, String likeCnp) {
        JpaCriteria<MapPatientsDoctor> crit = getCriteria(String.class);
        
        crit.addNotEqual(MapPatientsDoctor_.idUser, Users_.id, userId);
        crit.addLike(MapPatientsDoctor_.idPatient, Patients_.cnp, likeCnp);
        
        return crit.selectDisctinct(MapPatientsDoctor_.idPatient, Patients_.cnp);
    }
}
