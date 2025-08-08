package messaging.scheduling;

import mojo.km.messaging.RequestEvent;
import java.util.Date;
/**
 * Responsible for housing data that will be sent to control command CalculateNextDateCommand
 *@author Design detail addin
 *@version 1.0
 */
public class CalculateNextDateEvent extends RequestEvent {
    public CalculateNextDateEvent() { }

    public Date getInitialDate(){
            return initialDate;
        }

    public void setInitialDate(Date initialDate){
            this.initialDate = initialDate;
        }

    public String getFrequency(){
            return frequency;
        }

    public void setFrequency(String frequency){
            this.frequency = frequency;
        }

    private Date initialDate;
    private String frequency;
}
