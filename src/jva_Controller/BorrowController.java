package jva_Controller;

import jva_Bean.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
//@SessionScoped//implements Serializable
//@ViewScoped //implements Serializable
@RequestScoped

public class BorrowController {


    @EJB
    private BorrowPackageBean bpb;
    static long xx;
    //lists
    private List<ItemEntity> itemList=new ArrayList<>();
    private List<BorrowDetailEntity> bodList=new ArrayList<>();
    private List<Long> cartItems = new ArrayList<>();//list to add phones

    private List<BorrowDetailEntity> bodEditList=new ArrayList<>();

    private List<ReturnListEntity> rList=new ArrayList<>();
    //normal list-
    private List<BorrowListEntity> bList=new ArrayList<>();
    //requestlist
    private List<BorrowListEntity> bReqList=new ArrayList<>();
  //approvedlist
    private List<BorrowListEntity> bListApp=new ArrayList<>();
    //deliveredlist
    private List<BorrowListEntity> bListDel=new ArrayList<>();

    //borrowedlists-requested
    public String viewReqLists() {
        bReqList=bpb.retrieveByStatus("Requested");
        return "RequestList.xhtml";
    }
    //approvedlists
    public String viewAppLists() {
        bListApp=bpb.retrieveByStatus("Approved");
        return "ApproveList.xhtml";
    }
    //deliveredlists
    public String viewDelLists() {
        bListDel=bpb.retrieveByStatus("Delivered");
        //return bListDel;
        return "DeliveredLists.xhtml";
    }
    //returnedlists
    public String viewRetLists() {
        rList = bpb.findReturnLists();
        return "ReturnList.xhtml";
    }
    public List<BorrowListEntity> getbReqList() {
        bReqList = bpb.retrieveByStatus("Requested");
        return bReqList;
    }
    public List<BorrowListEntity> getbListApp() {
        bListApp=bpb.retrieveByStatus("Approved");
        return bListApp;
    }
    public List<BorrowListEntity> getbListDel() {
        bListDel=bpb.retrieveByStatus("Delivered");
        return bListDel;
    }
    //general getters
    public List<BorrowListEntity> getbList() {
        bList = bpb.findBLists();
        return bList;
    }
    public List<BorrowDetailEntity> getBodList() {
        bodList = bpb.findBDetails();
        return bodList;
    }
    //simple view bodetails
    public String viewSimpleBodList() {
        bodList = bpb.findBDetails();
        return "BorrowDetailEditable.xhtml";
    }
    //bod editable list page
    public String viewBDEditLists() {
        //bodEditList=bpb.retrieveBDetailsByBorId(3);
        //bodEditList=bpb.retrieveBDetailsByBorId(returnBorID());
        returnBorID();
        bodEditList=bpb.retrieveBDetailsByBorId(xx);
        return "BorrowDetailEditable.xhtml";
    }
    //bod editable lists
    public List<BorrowDetailEntity> getBodEditList() {
       // bodEditList=bpb.retrieveBDetailsByBorId(returnBorID());
        returnBorID();
        bodEditList=bpb.retrieveBDetailsByBorId(xx);
        return bodEditList;
    }
    public List<ReturnListEntity> getrList() {
        rList = bpb.findReturnLists();
        return rList;
    }
    public List<ItemEntity> getItemList() {
        return itemList;
    }

    //entitiy objects
    private ItemEntity it=new ItemEntity();
    private BorrowDetailEntity bod=new BorrowDetailEntity();
    private BorrowListEntity b=new BorrowListEntity();
    private MemberEntity m = new MemberEntity();
    private CoordinatorEntity c = new CoordinatorEntity();
    private ReturnListEntity r=new ReturnListEntity();

    public ItemEntity getIt(){
        return it;
    }
    public BorrowDetailEntity getBod(){return bod;}
    public BorrowListEntity getB(){return b;}
    public MemberEntity getM(){return m;}
    public CoordinatorEntity getC(){return c;}


    @PostConstruct
    public void init() {
       bpb.initializeBean();
    }

    //for displaying lists of borrow
   /* public String viewBLs() {
        return "success";
        //return "UserList.xhtml";
    }*/

    public void addToCart(){
        bpb.addItem(it);
        bpb.addItemBD(bod);
    }
    public void addBDToCart() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String sn = (String) map.get("sn");
        cartItems.add(Long.parseLong(sn));
        context.addMessage(null, new FacesMessage("no of phone nos"+cartItems.size()));
        //FacesContext context = FacesContext.getCurrentInstance();
        //Map map = context.getExternalContext().getRequestParameterMap();
       // String dno = "11";
        //String borId ="6";
        //String sn = (String) map.get("sn");
        //String noItms = (String) map.get("noItms");
       // bod.setDno();
       // bod.setBL();
       /* bod.setNoOfItems(Integer.parseInt(noItms));
        it.setSerialNo(Long.parseLong(sn));
        bod.setSno(it);
        bpb.addItemBD(bod);*/
        //context.addMessage(null, new FacesMessage("this item list is added." + sn+noItms+"total="+countBDItems()));
        //context.addMessage(null, new FacesMessage("this item list is added."));

    }
    public int countBDItems(){
       return bpb.countBDItems();
    }
    //inserting to the tables
    public String addNewBLAndBD() {
        //persist Member
        b=bpb.addToDB(b);
        bList=bpb.findBLists();
        bpb.emty();
        return "success";
       // return "UserList.xhtml";
    }
    //chaiorman approves the resquest
    public void approveStatus(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String selectedBorId = (String) map.get("id");
        String status="Approved";
        bpb.updateBL(Long.parseLong(selectedBorId),status);
        context.addMessage(null, new FacesMessage("the database is approved."+selectedBorId));

    }
    //storekeeper delivers the approved item
    public void deliverStatus() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String selectedBorId = (String) map.get("id");
        String status = "Delivered";
        java.util.Date today=new java.util.Date();
        bpb.updateBLDelivered(Long.parseLong(selectedBorId), status, new java.sql.Date(today.getTime()));
        context.addMessage(null, new FacesMessage("this item is delivered." + selectedBorId));
    }
    //storekeeper reveived the delivered items
    public void returnStatus() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String selectedBorId = (String) map.get("id");
        String status = "Returned";
        bpb.updateBL(Long.parseLong(selectedBorId), status);
        context.addMessage(null, new FacesMessage("this item is Returned." + selectedBorId));

        //at the same time adding to return table
        //set borid
        b.setBorId(Long.parseLong(selectedBorId));
        r.setBorId(b);
                     // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    //LocalDateTime now = LocalDateTime.now();
                     //dtf.format(now);
        //set return date
        Date now=new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        r.setReturnDate(sqlDate);
        //set corid-using user name
        c.setUserId(929);
        r.setCorId(c);
        //set return id-auto generated
        //r.setRetId(1);
        r=bpb.insertToReturn(r);
        context.addMessage(null, new FacesMessage("added to return table too"));
    }

    /*//returning selected editable borid from borlist
    public long returnBorID() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String BorId = (String) map.get("id");
        return Long.parseLong(BorId);
    }*/
    //returning selected editable borid from borlist
    public void returnBorID() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String BorId = (String) map.get("id");
        if(BorId!=null)
        xx=Long.parseLong(BorId);
    }
    //change amount
    public void changeAmount(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String selectedBDNo = (String) map.get("BDid");
        String selectedAmount=(String) map.get("BDAmount");
        //long x=2;
        //bpb.updateBDAmount(Long.parseLong(selectedBDNo),Integer.parseInt(selectedAmount));
        bpb.updateBDAmount(Long.parseLong(selectedBDNo),Integer.parseInt(selectedAmount));
        //call view lists to render the change

        //context.addMessage(null, new FacesMessage("the amount is changed in DB."+selectedBDNo+selectedAmount+returnBorID()));
        context.addMessage(null, new FacesMessage("the amount is changed in DB."+selectedBDNo+selectedAmount+xx));
        //return "error1.xhtml";
    }
    //update the edited borrow details
    public void saveChange(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String selectedBDNo = (String) map.get("BDid");
        String selectedAmount=(String) map.get("BDAmount");

        //bpb.updateBDAmount(x,Integer.parseInt(selectedAmount));
        int c=bpb.updateAmount(Long.parseLong(selectedBDNo),Integer.parseInt(selectedAmount));
        context.addMessage(null, new FacesMessage("the amount is changed in DB."+c+selectedAmount+selectedBDNo));
        //return "error1.xhtml";
    }

    //taking amount from form
    private int updatedNoOfItems;
    public int getUpdatedNoOfItems() {
        return updatedNoOfItems;
    }

    public void setUpdatedNoOfItems(int updatedNoOfItems) {
        this.updatedNoOfItems = updatedNoOfItems;
    }

    public String check(){
        int exp=b.getNoRemainingDays();
        if(exp>=0)
            return "Remaining days="+exp;
        else
            return "extra days you have used="+(exp*-1);
    }
    public long returnCostBorID() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String borId = (String) map.get("borId");
        return Long.parseLong(borId);
    }
    public void calcCost() {
        FacesContext context = FacesContext.getCurrentInstance();
        long x=returnCostBorID();

        //bpb.updateBDAmount(x,Integer.parseInt(selectedAmount));
        double c = bpb.calcTotalCost(x);//.updateAmount(Long.parseLong(selectedBDNo), Integer.parseInt(selectedAmount));
        context.addMessage(null, new FacesMessage("totalcosts for borrow request:." + x + ":is="+c+ ":birr"));
        //return "error1.xhtml";
    }
}
