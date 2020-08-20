package test1;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;
//import java.util.Timer;
import java.util.logging.Logger;
@Startup
@Stateless(name = "ProdTimeEJB")
public class ProdTimeBean {
    @Resource
    TimerService ts;



    private String timeText;
    public static final Logger logger=Logger.getLogger("test1.ProdTimeBean");
    public ProdTimeBean() {
    }
    @PostConstruct
    public  void init(){
        ts.createTimer(1000,9000,"ere please");
    }
        /*TimerConfig tg=new TimerConfig();
        tg.setInfo("calader prog timer demo");
        ScheduleExpression sc=new ScheduleExpression();
        sc.hour("*").minute("*").second("10");
        ts.createCalendarTimer(sc,tg);
    }*/
   /* public  void setTimer(long interval){
    ts.createTimer(interval,"setting programmatic timeout");
    }*/
   /* @Timeout
    public void programaticTimeout(Timer t){
 logger.info("@time out info="+ new Date());
    }*/

    //@Schedule(second = "*/10",minute = "*",hour = "*")
   /* public void scheduleTime(final Timer t){
    logger.info("@time out info when scheduled="+ new Date());
}*/
    //@Schedule(second = "*/10",minute = "*",hour = "*")
/*public void execute(Timer t){
        logger.info("addis ababa....");
        logger.info("Biruk execute time="+ new Date());
        logger.info("______________________________");
        //timeText="bikaka execute time="+ new Date();

    }*/
   /* public String getTimeText() {
        return timeText;
    }*/
}