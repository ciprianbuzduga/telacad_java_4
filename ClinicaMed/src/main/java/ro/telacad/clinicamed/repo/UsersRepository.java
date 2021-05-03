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
import ro.telacad.clinicamed.entity.Role_;
import ro.telacad.clinicamed.entity.Users;
import ro.telacad.clinicamed.entity.Users_;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UsersRepository extends BaseRepository<Users> {
    
    @PersistenceContext(unitName = "ClinicaMed-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersRepository() {
        super(Users.class);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users getUserByUsernameAndPassword(String username, String password) {
        JpaCriteria<Users> criteria = getCriteria();
        criteria.addEqual(Users_.userName, username);
        
        if(password != null) {
            criteria.addEqual(Users_.password, password);
        }
        
        return criteria.get();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
     public Users getUserById(Integer id) {
        JpaCriteria<Users> criteria = getCriteria();
        criteria.addEqual(Users_.id, id);
 
        return criteria.get();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Users> list() {
        JpaCriteria<Users> crit = getCriteria();
        crit.addNotEqual(Users_.idRole, Role_.id, RoleRepository.ROLE_ADMIN);
        
        return crit.listOrderBy(Users_.firstName, true);
    }
}
