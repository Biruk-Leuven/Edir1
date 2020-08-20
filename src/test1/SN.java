package test1;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@JMSDestinationDefinition(
        name = "jms/dest",
        interfaceName = "javax.jms.Queue",
        destinationName = "Edir1Dest")
@Named("SN")
@RequestScoped
public class SN {
    @Inject
    private JMSContext context;
    //@Resource(lookup = "java:comp/jms/dest")
    @Resource(mappedName = "jms/dest")
    private Queue queue;



    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    private String messageText;
    public static final Logger logger=Logger.getLogger("test1.SN");
    public void sendMessage() {
        try {
            String text = "Message from sender: " + messageText;
            context.createProducer().send(queue, text);

            FacesMessage facesMessage =
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Sent message from sender: " + text,null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Throwable t) {
            logger.log(Level.SEVERE,
                    "SenderBean.sendMessage: Exception: {0}",
                    t.toString());
        }
    }
    public String getMessageText() {
        return messageText;
    }
}
