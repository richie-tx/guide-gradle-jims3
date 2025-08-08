//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetProbationStatusForRiskAssessmentEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
	* @roseuid 4278C831024E
	*/
   public GetProbationStatusForRiskAssessmentEvent() 
   {    
   }
   
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

  }
