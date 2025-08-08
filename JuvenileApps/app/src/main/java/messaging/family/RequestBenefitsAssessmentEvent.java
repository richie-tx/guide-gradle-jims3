package messaging.family;

import mojo.km.messaging.RequestEvent;

public class RequestBenefitsAssessmentEvent extends RequestEvent 
{
    public String juvenileNum;

	public String casefileId;

	private String requesterName;

	
   
   
   /**
    * @roseuid 4370F7400027
    */
   public RequestBenefitsAssessmentEvent() 
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
	public String getCasefileId()
	{
		return casefileId;
	}
	
	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}
	
	
	/**
	 * @return Returns the requesterName.
	 */
	public String getRequesterName() {
		return requesterName;
	}
	/**
	 * @param requesterName The requesterName to set.
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
}
