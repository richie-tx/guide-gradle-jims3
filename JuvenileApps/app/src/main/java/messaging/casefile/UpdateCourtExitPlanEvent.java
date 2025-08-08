//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateCasefileClosingEvent.java

package messaging.casefile;

import java.util.Collection;
import mojo.km.messaging.RequestEvent;

public class UpdateCourtExitPlanEvent extends RequestEvent 
{
	private String controllingReferralId;
	private String petitionNumber;
	private String casefileClosingStatus;
	private String casefileClosingInfoId;
	private String prevCasefileClosingInfoId;
	private Collection responses;
	private String exitPlanTemplateLocation;
	
	private String supervisionNum;
   
	/**
	 * 
	 * @return
	 */
   public String getControllingReferralId() {
		return controllingReferralId;
	}
   
   /**
    * 
    * @param controllingReferralId
    */
	public void setControllingReferralId(String controllingReferralId) {
		this.controllingReferralId = controllingReferralId;
	}
	/**
    * @roseuid 439601E001BB
    */
   public UpdateCourtExitPlanEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getCasefileClosingInfoId()
	{
		return casefileClosingInfoId;
	}

	/**
	 * @return
	 */
	public String getCasefileClosingStatus()
	{
		return casefileClosingStatus;
	}

	/**
	 * @return
	 */
	public String getPetitionNumber()
	{
		return petitionNumber;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingInfoId(String string)
	{
		casefileClosingInfoId = string;
	}

	/**
	 * @param string
	 */
	public void setCasefileClosingStatus(String string)
	{
		casefileClosingStatus = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNumber(String string)
	{
		petitionNumber = string;
	}

	/**
	 * @return
	 */
	public Collection getResponses()
	{
		return responses;
	}

	/**
	 * @param collection
	 */
	public void setResponses(Collection collection)
	{
		responses = collection;
	}

	/**
	 * @return
	 */
	public String getExitPlanTemplateLocation()
	{
		return exitPlanTemplateLocation;
	}

	/**
	 * @param string
	 */
	public void setExitPlanTemplateLocation(String string)
	{
		exitPlanTemplateLocation = string;
	}

	/**
	 * @return Returns the supervisionNum.
	 */
	public String getSupervisionNum() {
		return supervisionNum;
	}
	/**
	 * @param supervisionNum The supervisionNum to set.
	 */
	public void setSupervisionNum(String supervisionNum) {
		this.supervisionNum = supervisionNum;
	}

	/**
	 * @return the prevCasefileClosingInfoId
	 */
	public String getPrevCasefileClosingInfoId() {
		return prevCasefileClosingInfoId;
	}

	/**
	 * @param prevCasefileClosingInfoId the prevCasefileClosingInfoId to set
	 */
	public void setPrevCasefileClosingInfoId(String prevCasefileClosingInfoId) {
		this.prevCasefileClosingInfoId = prevCasefileClosingInfoId;
	}
}
