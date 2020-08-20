package jva_Controller;

import jva_Bean.UsersBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class Login {
    private String username;
    private String password;
    @EJB
    private UsersBean usersBean;
    //method to check the availability of user
    /*public boolean userCheck(String uName,String uPassword){
        if(usersBean.userExists(uName,uPassword)>=1)
            return true;
        else
            return false;
    }*/
    public void login() {

        FacesContext context = FacesContext.getCurrentInstance();
       // if(userCheck(this.username,this.password)==true){
        if(usersBean.userExists(this.username,this.password)>=1){
            context.getExternalContext().getSessionMap().put("user", username);
            try {
                context.getExternalContext().redirect("MainReg.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else  {
            //Send an error message on Login Failure
            context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));

        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("LoginPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
