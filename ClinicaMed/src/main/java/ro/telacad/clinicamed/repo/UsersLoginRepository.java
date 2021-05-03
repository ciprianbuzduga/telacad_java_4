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
import ro.telacad.clinicamed.entity.UsersLogin;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UsersLoginRepository extends BaseRepository<UsersLogin> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersLoginRepository() {
        super(UsersLogin.class);
    }
}
