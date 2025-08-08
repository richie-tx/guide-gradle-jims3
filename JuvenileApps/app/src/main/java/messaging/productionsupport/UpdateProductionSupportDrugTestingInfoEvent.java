package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportDrugTestingInfoEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId;
	private String drugTestingId;
	private String testDate;
	private String testTime;
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportDrugTestingInfoEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	
	/**
	 * @return the mergeToCasefileId
	 */
	public String getMergeToCasefileId() {
		return mergeToCasefileId;
	}
	
	/**
	 * @param mergeToCasefileId the mergeToCasefileId to set
	 */
	public void setMergeToCasefileId(String mergeToCasefileId) {
		this.mergeToCasefileId = mergeToCasefileId;
	}

	public String getDrugTestingId()
	{
	    return drugTestingId;
	}

	public void setDrugTestingId(String drugTestingId)
	{
	    this.drugTestingId = drugTestingId;
	}

	public String getTestDate()
	{
	    return testDate;
	}

	public void setTestDate(String testDate)
	{
	    this.testDate = testDate;
	}

	public String getTestTime()
	{
	    return testTime;
	}

	public void setTestTime(String testTime)
	{
	    this.testTime = testTime;
	}
	
	

	
	
	
	
	
   
}
