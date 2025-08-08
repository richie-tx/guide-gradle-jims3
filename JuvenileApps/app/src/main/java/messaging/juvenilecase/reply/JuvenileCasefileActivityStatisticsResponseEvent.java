/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * 
 * @author anpillai
 */
@SuppressWarnings("unused")
public class JuvenileCasefileActivityStatisticsResponseEvent extends ResponseEvent implements IAddressable{
    
	private String activityName;
	private int activityCount;
	
	public String getActivityName()
	{
	    return activityName;
	}


	public void setActivityName(String activityName)
	{
	    this.activityName = activityName;
	}
	
	public int getActivityCount()
	{
	    return activityCount;
	}


	public void setActivityCount(int activityCount)
	{
	    this.activityCount = activityCount;
	}
	/*
	@Override
	public int compareTo(Object o)
	{
	    if(o==null)
		return -1;
	    JuvenileCasefileActivityStatisticsResponseEvent evt = (JuvenileCasefileActivityStatisticsResponseEvent) o;
	return activityName.compareToIgnoreCase(evt.activityName);	
	    //return 0;
	}*/
	

	
}

