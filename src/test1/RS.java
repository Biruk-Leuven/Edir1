package test1;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("RS")
@RequestScoped
public class RS {
    @Inject
    private JMSContext context;
    //@Resource(lookup = "java:comp/jms/dest")
    @Resource(mappedName = "jms/dest")
    private Queue queue;
    public static final Logger logger=Logger.getLogger("test1.RS");

    public String getMessageText() {
        return messageText;
    }

    private String messageText;
    public void getMessage() {
        try {
            JMSConsumer receiver = context.createConsumer(queue);
            String text = receiver.receiveBody(String.class);
            messageText=text; //to display the content on textbox
            if (text != null) {
                FacesMessage facesMessage =
                        new FacesMessage(FacesMessage.SEVERITY_INFO,"received msg from sender: " + text,null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else {
                FacesMessage facesMessage =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"No message received after 1 second",null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (Throwable t) {
            logger.log(Level.SEVERE,
                    "ReceiverBean.getMessage: Exception: {0}",
                    t.toString());
        }
       // return "receivedMsg.xhtml";
    }
}
