package jva_Bean;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Stateful(name = "BorrowPackageEJB")
@StatefulTimeout(value = 25,unit = TimeUnit.SECONDS)
public class BorrowPackageBean {
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    private List<ItemEntity> Itms;
    private List<BorrowDetailEntity> borDetailItms;

    private BorrowDetailEntity bod=new BorrowDetailEntity();
    private BorrowListEntity bl=new BorrowListEntity();
    private ReturnListEntity r=new ReturnListEntity();
    public BorrowPackageBean() {
    }
    @PostConstruct
    public void initializeBean(){
        Itms=new ArrayList<ItemEntity>();
        borDetailItms=new ArrayList<BorrowDetailEntity>();
    }
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

    public List<ReturnListEntity> findReturnLists(){
        TypedQuery<ReturnListEntity> query = em.createNamedQuery("findAllRLs",ReturnListEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
    public List<BorrowListEntity> retrieveByStatus(String name) {
        TypedQuery<BorrowListEntity> query = this.em.createQuery("select bl From BorrowListEntity bl where bl.status LIKE :borrowStatus", BorrowListEntity.class);
        query.setParameter("borrowStatus", "%" + name + "%");
        query.setMaxResults(20);
        return query.getResultList();
    }
    public List<BorrowDetailEntity> findBDetails(){
        TypedQuery<BorrowDetailEntity> query = em.createNamedQuery("findAllBDLs", BorrowDetailEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
    public List<BorrowDetailEntity> retrieveBDetailsByBorId(long borId) {
        TypedQuery<BorrowDetailEntity> query = this.em.createQuery("select bd From BorrowDetailEntity bd where bd.BL.borId=:ID", BorrowDetailEntity.class);
        query.setParameter("ID", borId);
        query.setMaxResults(20);
        return query.getResultList();
    }
    //update BorrowList-updating status to approved..
    public void updateBL(long updateId,String statusName)
    { BorrowListEntity bl;
        bl = em.find(BorrowListEntity.class, updateId);
        if(bl!= null) {
            bl.setStatus(statusName);
            em.persist(bl);
        }
    }
    //update BorrowList-updating status to delivered/..
    public void updateBLDelivered(long updateId, String statusName, Date dDate)
    { BorrowListEntity bl;
        bl = em.find(BorrowListEntity.class, updateId);
        if(bl!= null) {
            bl.setStatus(statusName);
            bl.setDeliveryDate(dDate);
            em.persist(bl);
        }
    }
    public void updateBDAmount(long updateId,int amt)
    { BorrowDetailEntity b;
        b = em.find(BorrowDetailEntity.class, updateId);
        if(b!= null) {
            b.setNoOfItems(amt);
            em.persist(b);
        }
    }
    //update borrow detail Amount-not used
    public int updateAmount(long borId,int amount)
    {
        Query query = em.createQuery("UPDATE BorrowDetailEntity b SET b.noOfItems=:amount WHERE b.BL.borId=:borId");
        query.setParameter("amount", amount);
        query.setParameter("borId", borId);
        int updateCount = query.executeUpdate();
        return updateCount;
    }


    //inserting to Return table during delivery
    public ReturnListEntity insertToReturn(ReturnListEntity r)
    { em.persist(r);
        return r;
    }
    public BorrowListEntity addToDB(BorrowListEntity bl) {

        em.persist(bl);
        return bl;
    }
    public void emty(){
        borDetailItms.clear();
    }

    public double calcTotalCost(long id) {
        //Query query = this.em.createQuery("select sum(bd.sno.borrowUnitPrice * bd.noOfItems) From BorrowDetailEntity bd where bd.BL.borId=:borId group by bd.BL.borId");

        Query query = this.em.createQuery("select sum(bd.sno.borrowUnitPrice) From BorrowDetailEntity bd where bd.BL.borId=:borId group by bd.BL.borId");
        query.setParameter("borId",id);
        //query.executeUpdate();
        //query.setMaxResults(20);
        return (double)query.getSingleResult();//its double result
    }
    //update borrow detail Amount-not used

    /*public Double getTotalPrice(){
        if(borItms==null|| borItms.isEmpty())
            return 0.0;
        Double tot=0.0;
        for(ItemEntity itm:borItms){
            tot = tot + itm.getBorrowUnitPrice();
        }
        return tot;
    }
    public void emty(){
        borItms.clear();
    }
    @Remove
    //here persist bordetail and borlist then clear arraylists
    public void checkOut(){
        for(ItemEntity itm:borItms){

            em.persist(itm);
        }

        borItms.clear();
    }
    public List<ItemEntity> findItems(){
        TypedQuery<ItemEntity> query = em.createNamedQuery("findAllItems", ItemEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }

    public ItemEntity addNew(ItemEntity it) {
        em.persist(it);
        return it;
    }*/
}
