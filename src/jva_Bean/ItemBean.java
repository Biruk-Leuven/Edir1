package jva_Bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "ItemEJB")
public class ItemBean {
    public ItemBean() {
    }
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    public List<ItemEntity> findItems(){
        TypedQuery<ItemEntity> query = em.createNamedQuery("findAllItems", ItemEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }

    public ItemEntity addNew(ItemEntity it) {

        em.persist(it);
        return it;
    }
    public boolean CheckItem(Long id)
    { ItemEntity it2;
        it2 = em.find(ItemEntity.class, id);
        if(it2 != null)
            return true;
        else
            return false;
    }
    //update borrow detail Amount-not used
    public List<ItemEntity> availableItems()
    {
        return em.createQuery("select it,sum(bd.noOfItems) as available from  ItemEntity it, BorrowDetailEntity bd where it.serialNo=bd.sno.serialNo order by it.serialNo").getResultList();
        //query.setParameter("amount", amount);
        //query.setParameter("borId", borId);
         //= query.getResultList();
        //return updateCount;
    }

}