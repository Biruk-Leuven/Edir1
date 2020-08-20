package jva_Controller;

import jva_Bean.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named("borrowRequestController")
@SessionScoped
public class BorrowRequestController implements Serializable {




    @EJB
    private BorrowRequestBean brBean;
    static long xx;

    public String getCheckName() {
        return checkName;
    }
    public void changeName(ValueChangeEvent e){
        checkName=e.getNewValue().toString();
    }

    //lists
    //private List<String> names;
    private String checkName;


    private List<String> itemList=new ArrayList<>();
    private List<BorrowDetailEntity> bodList=new ArrayList<>();
    private List<BorrowListEntity> bList=new ArrayList<>();

   // private List<Long> cartItems = new ArrayList<>();//list to add phones
    //normal list-
   private BorrowListEntity bl=new BorrowListEntity();
    private MemberEntity m = new MemberEntity();
    private CoordinatorEntity c = new CoordinatorEntity();

    public ItemEntity getIt(){
        return it;
    }
    public BorrowDetailEntity getBod(){return bod;}
    public BorrowListEntity getBl() {
        return bl;
    }
    public MemberEntity getM(){return m;}
    public CoordinatorEntity getC(){return c;}

    @PostConstruct
    public void init() {
        itemList = brBean.retrieveItemName();
        bod=new BorrowDetailEntity();
        it=new ItemEntity();
        m=new MemberEntity();
        //m=new MemberEntity();
    }


    public List<String> getItemList() {
        itemList = brBean.retrieveItemName();
        return itemList;
    }
    //general getters
    public List<BorrowListEntity> getbList() {
        bList = brBean.findBLists();
        return bList;
    }
    public List<BorrowDetailEntity> getBodList() {
        bodList = brBean.findBDetails();
        return bodList;
    }
   /* //simple view bodetails
    public String viewSimpleBodList() {
        bodList = bpb.findBDetails();
        return "BorrowDetailEditable.xhtml";
    }
    //simple view bodetails
    public String viewSimpleBLList() {
        bodList = bpb.
        return "RequestList.xhtml";
    }*/

    //public List<ItemEntity> getItemList() {
     //   return itemList;
    //}

    //entitiy objects
    private ItemEntity it=new ItemEntity();
    private BorrowDetailEntity bod=new BorrowDetailEntity();





   // @PostConstruct
   // public void init() {
    //    bpb.initializeBean();
  //  }


   /* public void addToCart(){
        bpb.addItem(it);
        bpb.addItemBD(bod);
    }*/
  //test
   public String addToBDLists2(){return "error1.xhtml";}
   //adding to lists
    public void addToBDLists(){
        FacesContext context = FacesContext.getCurrentInstance();

        it.setSerialNo(this.it.getSerialNo());
        bod.setSno(it);
         bod.setNoOfItems(this.bod.getNoOfItems());
         bodList.add(bod);
         //bod.setBL();
        // bod.setDno();
            context.addMessage(null, new FacesMessage("no of BDs" + bodList.size()));
      }
    public void addBDToDB(long borId){
        //phoneNo.add(ph.getPhoneNo());
        FacesContext context = FacesContext.getCurrentInstance();
        for (BorrowDetailEntity i : bodList) {
            bod = new BorrowDetailEntity();
            bod.setSno(i.getSno());
            bod.setNoOfItems(i.getNoOfItems());
            //bod.setDno();//generated
            bl.setBorId(borId);
            bod.setBL(bl);
            bod =brBean.addBorrowDetail(bod) ;
        }
        bodList.clear();
        //list all bds
        //bodList=brBean.findBDetails();
    }
    //register by checking duplicate key
    public String addNewBorrowList() {
        //FacesContext context = FacesContext.getCurrentInstance();
        // if(!usersBean.checkUser(u.getUserId())) {//no need of duplicate key
        //set the address add the content to the member and user table
        //bl.setBorId();generated
        //bl.setIssueDate();
        //bl.setNoOfDays();
        //bl.setReason();
        //bl.setStatus();
       // bl.setMemId();//?
        bl=brBean.addBorrowList(bl);
        //list all bls
        bList=brBean.findBLists();
        //memList=usersBean.findMembers();
        addBDToDB(bl.getBorId());
        return "RequestList.xhtml";
    }




}
