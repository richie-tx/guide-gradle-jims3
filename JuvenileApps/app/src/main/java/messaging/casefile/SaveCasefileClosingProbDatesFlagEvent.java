/*Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\GetCasefileClosingDetailsEvent.java
 *
 *  * Created on Deecember 06, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author anpillai
 * description getter and setter for casefileclosingdetails
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


public class SaveCasefileClosingProbDatesFlagEvent extends RequestEvent 
{
   private String supervisionNumber;
   
   /**
    * @roseuid 439601DB0242
    */
   public SaveCasefileClosingProbDatesFlagEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	}
	
	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	}

}
