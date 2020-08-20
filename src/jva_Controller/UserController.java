package jva_Controller;

import jva_Bean.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("userController")
//@RequestScoped
@SessionScoped
public class UserController implements Serializable{

    @EJB
    private UsersBean usersBean;
    //entitiy objects
    private UsersEntity u = new UsersEntity();
    private Address ad = new Address();
    private PhoneNoEntity ph ;
    private MemberEntity m = new MemberEntity();
    private CoordinatorEntity c = new CoordinatorEntity();

    //private PhoneNoEntity ph[]=new PhoneNoEntity[2];
    private List<Long> phoneNo = new ArrayList<>();//list to add phones

    //for retrieving values to page
    private List<UsersEntity> userList = new ArrayList<>();
    private List<PhoneNoEntity> phonList = new ArrayList<>();
    private List<MemberEntity> memList = new ArrayList<>();
    private List<CoordinatorEntity> corList = new ArrayList<>();

    public List<UsersEntity> getUserList() {
        userList = usersBean.findUsers();
        return userList;
    }

    public List<MemberEntity> getMemList() {
        memList = usersBean.findMembers();
        return memList;
    }

    public List<CoordinatorEntity> getCorList() {
        corList = usersBean.findCoords();
        return corList;
    }

    @PostConstruct
    public void init() {
        ad = new Address();//creating address space during startup
        ph=new PhoneNoEntity();
        //m=new MemberEntity();
    }

    //for displaying options
    public String viewUsers() {
        userList=usersBean.findUsers();
        return "UserList.xhtml";
    }

    public void addPhoneToLists(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(!usersBean.checkPhone(this.ph.getPhoneNo())) {
            phoneNo.add(this.ph.getPhoneNo());
            context.addMessage(null, new FacesMessage("no of phone nos" + phoneNo.size()));
        }
        else
        context.addMessage(null, new FacesMessage("duplicated key, change the phone no"));
    }
    public void addPhoneToDB(long id){
        //phoneNo.add(ph.getPhoneNo());
        FacesContext context = FacesContext.getCurrentInstance();
        for (Long i : phoneNo) {
            ph = new PhoneNoEntity();
            ph.setPhoneNo(i);
            ph.setUserId(id);//the id of member/coordinator passed
            ph = usersBean.addNewPhone(ph);//persist phone
             }
        phoneNo.clear();
        // phonList = usersBean.findPhones();//retrive list of phones
    }
    //register by checking duplicate key
    public String addNewUser() {
        //FacesContext context = FacesContext.getCurrentInstance();
       // if(!usersBean.checkUser(u.getUserId())) {//no need of duplicate key
            //set the address add the content to the member and user table
            m.setAddress(ad);
            m=usersBean.addNewMember(m);
            //list all users
            userList=usersBean.findUsers();
            //memList=usersBean.findMembers();
            addPhoneToDB(m.getUserId());
            return "UserList.xhtml";
    }
    public String addNewUserCord() {
        //persist Coordinator
        c.setAddress(ad);
        c = usersBean.addNewCoordinator(c);
       //to list coords
        //corList = usersBean.findCoords();
        //list all users
        userList=usersBean.findUsers();
        addPhoneToDB(c.getUserId());
        return "UserList.xhtml";
    }
    public UserController() {
    }

    public UsersEntity getU() {
        return u;
    }
    public MemberEntity getM(){
        return m;
    }
    public CoordinatorEntity getC(){
        return c;
    }
    public Address getAd() {
        return ad;
    }

    public PhoneNoEntity getPh() {
        return ph;
    }



}
