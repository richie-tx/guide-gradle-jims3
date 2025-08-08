package messaging.scheduling;

import mojo.km.messaging.ResponseEvent;
import java.util.Date;
/**
 * Responsible for housing data that will be returned to boundry command DisplayNextDateCommand
 *@author Design detail addin
 *@version 1.0
 */
public class DisplayNextDateEvent extends ResponseEvent {
    public DisplayNextDateEvent() { }

    public Date getDate(){
            return date;
        }

    public void setDate(Date date){
            this.date = date;
        }

    private Date date;
}
