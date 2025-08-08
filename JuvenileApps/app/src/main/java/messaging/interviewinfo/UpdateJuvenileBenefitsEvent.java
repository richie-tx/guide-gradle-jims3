package messaging.interviewinfo;

import java.util.ArrayList;

import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileBenefitsEvent extends RequestEvent 
{
   private String juvenileNum;
   private ArrayList updateEvents = new ArrayList();
   
   //added for User Story 27022
   private String action;
   
   /**
    * @roseuid 43F379B403DF
    */
   public UpdateJuvenileBenefitsEvent() 
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
    * 
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
   public void addUpdateEvents( JuvenileBenefitResponseEvent evt )
   {
	   updateEvents.add( evt );
   }

public String getAction() {
	return action;
}

public void setAction(String action) {
	this.action = action;
}

   
}