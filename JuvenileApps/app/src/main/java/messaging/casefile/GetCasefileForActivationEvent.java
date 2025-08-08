package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * 
 */
public class GetCasefileForActivationEvent extends RequestEvent 
{
	private String supervisionNum;
   
	/**
	* 
	*/
	public GetCasefileForActivationEvent() 
	{
	}
   
	/**
	* @param supervisionNum
	*/
	public void setSupervisionNumber(String aSupervisionNum) 
	{
		this.supervisionNum = aSupervisionNum;
	}
   
	/**
	* @return String
	*/
	public String getSupervisionNum() 
	{
		return this.supervisionNum;
	}

}
