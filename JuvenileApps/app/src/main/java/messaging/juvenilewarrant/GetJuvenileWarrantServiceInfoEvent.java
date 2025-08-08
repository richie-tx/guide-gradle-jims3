//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantServiceInfoEvent.java

/*
 * Created on Feb 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantServiceInfoEvent extends RequestEvent 
{
   private String warrantNum;
   
   /**
    * @roseuid 420A63F103C8
    */
   public GetJuvenileWarrantServiceInfoEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

}
