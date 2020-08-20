package test1;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class index1 {
    @EJB
    private opns1Bean op;
    public List<ProdEntity> getProducts(){
        return op.retrieveProducts();
    }

}
