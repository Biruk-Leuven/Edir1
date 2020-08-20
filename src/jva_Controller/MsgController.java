package jva_Controller;

import jva_Bean.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class MsgController {
    @EJB
    private MsgBean mb;



    //normal list
    private List<MessageListEntity> mList=new ArrayList<>();



    private List<MessageListEntity> mListContent=new ArrayList<>();
    public List<MessageListEntity> getmList() {
        mList=mb.listMessages();
        return mList;
    }
    //view lists
    public String viewMLists() {
        mList=mb.listMessages();
        return "MsgList.xhtml";
    }
    public String viewMsgContent() {
        mListContent=mb.retrieveById(returnMsgID());
        return "MsgContent.xhtml";
    }
    public List<MessageListEntity> getmListContent() {
        mListContent=mb.retrieveById(returnMsgID());
        return mListContent;
    }
    public long returnMsgID() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String msgId = (String) map.get("id");
        return Long.parseLong(msgId);
    }
}
