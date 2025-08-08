package messaging.interviewinfo;

import java.util.ArrayList;

import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author bschwartz
 */
public class UpdateJuvenileInsuranceEvent extends RequestEvent 
{
   private String juvenileNum;
   private ArrayList updateEvents = new ArrayList();
   
   /**
    * @roseuid 43F379B80006
    */
   public UpdateJuvenileInsuranceEvent() 
   {
    
   }
   
   /**
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public String getJuvenileNum()
   {
      return juvenileNum;
   }
   
   /**
    * Sets the value of the juvenileNum property.
    * ++
    * @param aJuvenileNum the new value of the juvenileNum property
    */
   public void setJuvenileNum(String aJuvenileNum)
   {
      juvenileNum = aJuvenileNum;
   }
  
	/**
	 * @return
	 */
	public ArrayList getUpdateEvents()
	{
		return updateEvents;
	}
	
	/**
	 * @param list
	 */
	public void addUpdateEvents( JuvenileInsuranceResponseEvent evt )
	{
		updateEvents.add( evt );
	}

}