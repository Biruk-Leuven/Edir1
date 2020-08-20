package test1;

import jva_Bean.UsersBean;
import jva_Bean.UsersEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Named
//@ViewScoped  implements Serializable
@RequestScoped
public class TimerController  {

    public ProdTime2Bean getPtb() {
        return ptb;
    }

    @EJB
    ProdTime2Bean ptb;
    //ProdTimeBean ptb;
    public void back() {

        //ptb.setTimer(50000);
        ptb.setTimer(new Date());
       // ptb.execute(ptb.t);
        FacesMessage facesMessage =
          new FacesMessage(FacesMessage.SEVERITY_INFO,"timer info sample: " + ptb.getTimeText(),null);
         FacesContext.getCurrentInstance().addMessage(null, facesMessage);

       /* //user check and forward based on session
        FacesContext context = FacesContext.getCurrentInstance();
        UsersEntity user = context.getApplication().evaluateExpressionGet(context, "#{user}", UsersEntity.class);

        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
        return "ItemList.xhtml";//function return should be string*/


      /*  FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("LoginPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
