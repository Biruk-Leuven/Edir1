package jva_Controller;

import jva_Bean.ItemBean;
import jva_Bean.ItemEntity;
import test1.ProdTimeBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("itemController")
@RequestScoped
public class ItemController {
    @EJB
    private ItemBean itemBean;
    private ItemEntity it=new ItemEntity();

    private List<ItemEntity> itemList = new ArrayList<>();



    private List<ItemEntity> itemListBD=new ArrayList<>();

    public List<ItemEntity> getItemList() {
        itemList =itemBean.findItems();
        return itemList;

    }

    /*public List<ItemEntity> getItemListBD() {
        itemListBD=itemBean.availableItems();
        return itemListBD;
    }
    public String viewItemListBD() {
        itemListBD=itemBean.availableItems();
        return "ItemList.xhtml";
    }*/
    public String viewItem(){
        return "ItemListNew.xhtml";
    }

    //simple registration
    public String addNewItem() {

            it = itemBean.addNew(it);
            itemList = itemBean.findItems();
            return "ItemListNew.xhtml";

    }
    //register by checking duplicate key
    public String addNewItem2() {

        if(!itemBean.CheckItem( it.getSerialNo())) {
            it = itemBean.addNew(it);
            itemList = itemBean.findItems();
            return "ItemListNew.xhtml";
        }
        else
            return "error1.xhtml";


    }
    public ItemController(){}

    public ItemEntity getIt() {
        return it;
    }


    //customizing the validator for item category
    public void checkCategory(FacesContext context, UIComponent component,Object value){
        String s=(String)value;
        int f=-1;
        String catList[]={"Tent","Kitchen","General","Food"};
        for(String i:catList)
            if(s.equalsIgnoreCase(i)){
                f++;
            }
       if(f==-1) {
           FacesMessage fm = new FacesMessage();
           fm.setSummary("wrong category name, use from the list please");
           //we can define this way too
           //FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "category name error","choose from the list...");
           throw new ValidatorException(fm);
       }
    }


    //timer test controller using back button
   /* @EJB
    ProdTimeBean ptb;
    public void back() {

        ptb.setTimer(3000);
    }*/


    }

