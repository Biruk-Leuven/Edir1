package test1;

import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;


@RequestScoped
@Named
public class cart2Controller  {


    @EJB
    private cart2Bean cb;


    // this object will be "automatically" filled with the values from our inputform on the xhtml page
    private ProdEntity pr=new ProdEntity(); // getter+setter

    public ProdEntity getPr() {
        return pr;
    }

    // we have unique actions for everything... they are referenced from the xhtml page
    public void addC() {
        // do your yearLevel logic here
        cb.addProductToCart(pr);

        //return "studentInfo.xhtml"; // forward to the next page
    }

    public String addP() {
        // do your yearLevel logic here
        cb.checkOut();

        return "index.xhtml"; // forward to the next page
    }


}

