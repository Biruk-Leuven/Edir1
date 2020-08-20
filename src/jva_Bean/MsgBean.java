package jva_Bean;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless(name = "MsgEJB")
public class MsgBean {
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    private List<MessageListEntity> msgList;

    private MessageListEntity msg=new MessageListEntity();

    public MsgBean() {
    }
    public List<MessageListEntity> listMessages() {
        TypedQuery<MessageListEntity> query = this.em.createQuery("select ms From MessageListEntity ms", MessageListEntity.class);
        //query.setParameter("borrowStatus", "%" + name + "%");
        query.setMaxResults(20);
        return query.getResultList();
    }
    public List<MessageListEntity>  retrieveById(long id) {
        TypedQuery<MessageListEntity> query = this.em.createQuery("select m From MessageListEntity m where m.msgNo=:msgId", MessageListEntity.class);
        query.setParameter("msgId",id);
        query.setMaxResults(20);
        return query.getResultList();
    }


}
