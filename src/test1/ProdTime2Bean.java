package test1;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@Singleton(name = "ProdTime2EJB")
@Startup
public class ProdTime2Bean {
    public ProdTime2Bean() {
    }
    @Resource
    TimerService ts;
    public static final Logger logger=Logger.getLogger("test1.ProdTime2Bean");
    private String timeText;
    @PostConstruct
    public  void init(){
       //ts.createTimer(5000,9000,"ere please");
        TimerConfig tg=new TimerConfig();
        tg.setInfo("initial schedule time");
        ScheduleExpression sc=new ScheduleExpression();
        sc.hour("*").minute("*").second("*");
        ts.createCalendarTimer(sc,tg);
    }
    /*TimerConfig tg=new TimerConfig();
    tg.setInfo("calader prog timer dsemo");
    ScheduleExpression sc=new ScheduleExpression();
    sc.hour("*").minute("*").second("10");
    ts.createCalendarTimer(sc,tg);
}*/
   // public  void setTimer(long interval){
   //     ts.createTimer(interval,"setting this interval for timeout");
   // }
    public  void setTimer(Date interval){
        ts.createTimer(interval,"starting date");//createTimer(,"setting this interval for timeout");
    }
   // @Timeout
   /* public void programaticTimeout(Timer t){
        logger.info("when clciked BK-time out info="+ new Date());
    }*/

    //@Schedule(second = "*/10",minute = "*",hour = "*")
   /* public void scheduleTime(final Timer t){
    logger.info("@time out info when scheduled="+ new Date());
}*/
    //@Schedule(second = "*/10",minute = "*",hour="1-15",dayOfWeek="Thu",dayOfMonth="1-7",month="*",year="*")
    @Schedule(second = "*/10",minute = "*",hour = "*")
    public void execute(Timer t){
        FacesContext context = FacesContext.getCurrentInstance();
        logger.info("____________You have a meeting every__________________");


        //Date t=new Date().;

            Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int today=cal.get(Calendar.DAY_OF_WEEK);
        int meetingDay=8;
        int remainingDay=meetingDay-today;
        timeText="You have a meeting every first week of the month="+ remainingDay;
        //context.addMessage(null, new FacesMessage("____________You have a meeting every__________________"));
        //getTimeText();
    }
    public String getTimeText() {
        return timeText;
    }
}
