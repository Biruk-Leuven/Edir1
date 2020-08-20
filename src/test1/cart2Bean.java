package test1;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful(name = "cart2EJB")
@StatefulTimeout(value = 25,unit = TimeUnit.SECONDS)
public class cart2Bean  {
    @PersistenceContext(unitName = "EdirPersistenceUnit1")
    private EntityManager em;
    public cart2Bean() {
    }
    private List<ProdEntity> products;

    @PostConstruct
    private void initializeBean(){
        products = new ArrayList<>();
    }

   // @Override
    public void addProductToCart(ProdEntity product) {
        products.add(product);

    }

    //@Override
  // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void checkOut() {
        for(ProdEntity product:products){
            em.persist(product);
        }
        products.clear();

    }
}
