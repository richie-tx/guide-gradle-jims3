/*
 * Created on Jun 24, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments;


/**
 * @author cc_bjangay
 *
 */
public class UpdateSCSInterviewAssessmentEvent extends UpdateCSAssessmentEvent
{
	private String scsInterviewId;
	

	/**
	 * @return the scsInterviewId
	 */
	public String getScsInterviewId() {
		return scsInterviewId;
	}

	/**
	 * @param scsInterviewId the scsInterviewId to set
	 */
	public void setScsInterviewId(String scsInterviewId) {
		this.scsInterviewId = scsInterviewId;
	}
	
	
	
}
