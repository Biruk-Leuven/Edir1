package test1;

import jva_Bean.UsersEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "opns1EJB")

public class opns1Bean {
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    public List<ProdEntity> retrieveProducts(){
        //TypedQuery<UsersEntity> query = em.createNamedQuery("findAllUsers", UsersEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
       // return query.getResultList();
        return  em.createQuery("select p from ProdEntity p").getResultList();
    }
    public int checkIfQueryExists(String query){
        List<ProdEntity> pds=em.createQuery("select p.id,p.name,p.price,p.mark from ProdEntity p where p.name= :name").setParameter("name",query).getResultList();
         return pds.size();
    }
    public ProdEntity returnProducts(String query) {
        try {
            ProdEntity pr=  (ProdEntity) em.createQuery("select  p.id,p.name,p.price,p.mark from ProdEntity p where p.mark= :name").setParameter("name",query).getSingleResult();
            return pr;
        }
        catch (NoResultException ex) {
            return null;
        }


    }
    /*public List<ProdEntity>  returnProducts(String query){
        List<ProdEntity> pr= em.createQuery("select p from ProdEntity p where p.mark=:mark").setParameter("mark",query).getResultList();
        return pr;
    }*/
    public opns1Bean() {
    }
}
