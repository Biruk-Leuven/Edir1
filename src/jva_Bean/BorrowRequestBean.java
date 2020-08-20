package jva_Bean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "BorrowRequestEJB")
public class BorrowRequestBean {

    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    private List<ItemEntity> Itms;
    private List<BorrowDetailEntity> borDetailItms;

    private BorrowDetailEntity bod=new BorrowDetailEntity();
    private BorrowListEntity bl=new BorrowListEntity();
    private ReturnListEntity r=new ReturnListEntity();
   /*
    @PostConstruct
    public void initializeBean(){
        Itms=new ArrayList<ItemEntity>();
        borDetailItms=new ArrayList<BorrowDetailEntity>();
    }*/
    //this is like adding to carts
    public void addItem(ItemEntity itm){
        if(!Itms.contains(itm))
            Itms.add(itm);
    }
    public void addItemBD(BorrowDetailEntity borDetailItm){
        if(!borDetailItms.contains(borDetailItm))
            borDetailItms.add(borDetailItm);
    }
    public int countBDItems(){
        if(borDetailItms==null|| borDetailItms.isEmpty())
            return 0;
        return borDetailItms.size();
    }
    public void removeItem(ItemEntity itm){
        if(Itms.contains(itm))
            Itms.remove(itm);
    }
    public void removeItemBD(BorrowDetailEntity borDetailItm){
        if(borDetailItms.contains(borDetailItm))
            borDetailItms.remove(borDetailItm);
    }
    public int countItems(){
        if(Itms==null|| Itms.isEmpty())
            return 0;
        return Itms.size();
    }


    public List<BorrowListEntity> findBLists(){
        TypedQuery<BorrowListEntity> query = em.createNamedQuery("findAllBLs", BorrowListEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }

    public List<BorrowDetailEntity> findBDetails(){
        TypedQuery<BorrowDetailEntity> query = em.createNamedQuery("findAllBDLs", BorrowDetailEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
//List<String> x;
    public List<String> retrieveItemName() {
        Query query = this.em.createQuery("select it.name From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }

    public BorrowDetailEntity addBorrowDetail(BorrowDetailEntity bd)
    {
        em.persist(bd);
        return bd;
    }
    public BorrowListEntity addBorrowList(BorrowListEntity bl)
    {
        em.persist(bl);
        return bl;
    }

    public void emty(){
        borDetailItms.clear();
    }
    public BorrowRequestBean() {
    }
}
