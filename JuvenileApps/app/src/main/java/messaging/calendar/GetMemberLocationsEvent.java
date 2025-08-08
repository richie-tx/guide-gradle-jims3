//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetMemberLocationsEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetMemberLocationsEvent extends RequestEvent 
{
   public String juvenileNum;
   public String constellationId;
   
   /**
    * @roseuid 45702FF800A5
    */
   public GetMemberLocationsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 456F2D87011A
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum; 
   }
   
   /**
    * @return String
    * @roseuid 456F2D87011C
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
/**
 * @return Returns the constellationId.
 */
public String getConstellationId() {
	return constellationId;
}
/**
 * @param constellationId The constellationId to set.
 */
public void setConstellationId(String constellationId) {
	this.constellationId = constellationId;
}
}
