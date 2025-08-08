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
 * @author mchowdhury
 * description getter and setter for casefileclosingdetails
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


public class GetResponseEvent extends RequestEvent 
{
   private String referenceId;
   private String responseType;
   private String responseTemplateLocation;
   
   /**
    * @roseuid 439601DB0242
    */
   public GetResponseEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getResponseType()
	{
		return responseType;
	}
	
	/**
	 * @param string
	 */
	public void setResponseType(String string)
	{
		responseType = string;
	}

	/**
	 * @return
	 */
	public String getReferenceId()
	{
		return referenceId;
	}
	
	/**
	 * @param string
	 */
	public void setReferenceId(String string)
	{
		referenceId = string;
	}

	/**
	 * @return
	 */
	public String getResponseTemplateLocation()
	{
		return responseTemplateLocation;
	}
	
	/**
	 * @param string
	 */
	public void setResponseTemplateLocation(String string)
	{
		responseTemplateLocation = string;
	}

}
