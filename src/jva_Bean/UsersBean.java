package jva_Bean;

import test1.ProdEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless(name = "UsersEJB")
public class UsersBean {
    public UsersBean() {
    }
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    public List<UsersEntity> findUsers(){
        TypedQuery<UsersEntity> query = em.createNamedQuery("findAllUsers", UsersEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
    public List<PhoneNoEntity> findPhones(){
        TypedQuery<PhoneNoEntity> query = em.createNamedQuery("findAllPhones", PhoneNoEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
    public List<MemberEntity> findMembers(){
        TypedQuery<MemberEntity> query = em.createNamedQuery("findAllMems", MemberEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }
    public List<CoordinatorEntity> findCoords(){
        TypedQuery<CoordinatorEntity> query = em.createNamedQuery("findAllCoords", CoordinatorEntity.class);
        //TypedQuery<ItemEntity> query = em.createQuery("Select it From ItemEntity it", ItemEntity.class);
        return query.getResultList();
    }

    public UsersEntity addNew(UsersEntity u) {
        //Address ad=new Address();
        //u.setAddress(ad);
        em.persist(u);
        return u;
    }
    public PhoneNoEntity addNewPhone(PhoneNoEntity p)
    {
        em.persist(p);
        return p;
    }
    public MemberEntity addNewMember(MemberEntity m)
    {
        em.persist(m);
        return m;
    }
    public CoordinatorEntity addNewCoordinator(CoordinatorEntity c)
    {
        em.persist(c);
        return c;
    }
    public boolean checkUser(Long id)
    { UsersEntity ue;
        ue = em.find(UsersEntity.class, id);
        if(ue!= null)
            return true;
        else
            return false;
    }
    public boolean checkPhone(Long id)
    { PhoneNoEntity pe;
        pe = em.find(PhoneNoEntity.class, id);
        if(pe!= null)
            return true;
        else
            return false;
    }
    public int userExists(String uName,String uPassword) {
            /*Query us=  em.createQuery("select  u from UsersEntity u where u.userName= :uName and u.password=:uPassword");
            us.setParameter("uName",uName );
            us.setParameter("uPassword",uPassword);
            us.getSingleResult();*/
        TypedQuery<UsersEntity> query = em.createQuery("select  u from UsersEntity u where u.userName= :uName and u.password=:uPassword", UsersEntity.class);
        query.setParameter("uName",uName );
        query.setParameter("uPassword",uPassword);
       List<UsersEntity> ch= query.getResultList();//.getSingleResult();
        return ch.size();
    }
}
