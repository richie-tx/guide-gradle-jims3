//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantServicesEvent.java

/*
 * Created on Feb 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantServicesEvent extends RequestEvent 
{
   private String warrantNum;
   
   /**
    * @roseuid 420A63FB02AF
    */
   public GetJuvenileWarrantServicesEvent() 
   {
    
   }
   
   /**
    * @param warrantNum
    * @roseuid 420A614400CE
    */
   public void setWarrantNum(String warrantNum) 
   {
   		this.warrantNum = warrantNum; 
   }
   
   /**
    * @return String
    * @roseuid 420A614400D0
    */
   public String getWarrantNum() 
   {
    	return this.warrantNum;
   }
}
