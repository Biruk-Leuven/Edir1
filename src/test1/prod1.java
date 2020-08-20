package test1;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
public class prod1 {
@EJB
    private opns1Bean op;
//@ManagedProperty(value = "#{cart1}")
cart1 mycart;

public String getQuery(){
    return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("query");
}
public void checkIfQueryExists() throws IOException {
    if(op.checkIfQueryExists(getQuery())==0)
        FacesContext.getCurrentInstance().getExternalContext().redirect("error1.xhtml");

}
public ProdEntity getProd(){
    return op.returnProducts(getQuery());
}
    /*public List<ProdEntity> getProd(){
        return op.returnProducts(getQuery());
    }*/
public void addToCart(){
    String query=(String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("query");
 //mycart.add(op.returnProducts(query));

        mycart.add(op.returnProducts(query));

}
}
