//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetMAYSIDetailsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMAYSIDetailsEvent extends RequestEvent
{
	public String assessmentId ;
	public String subAssessId ;
	public String maysiDetailId ;

	/**
	 * @roseuid 42790E0700EA
	 */
	public GetMAYSIDetailsEvent()
	{

	}

	/**
	 * @return assessmentId
	 */
	public String getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessmentId
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @return maysiDetailId
	 */
	public String getMaysiDetailId() {
		return maysiDetailId;
	}

	/**
	 * @param maysiDetailId
	 */
	public void setMaysiDetailId(String maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}

	/**
	 * @return subAssessId
	 */
	public String getSubAssessId() {
		return subAssessId;
	}

	/**
	 * @param subAssessId
	 */
	public void setSubAssessId(String subAssessId) {
		this.subAssessId = subAssessId;
	}

	
	
}
