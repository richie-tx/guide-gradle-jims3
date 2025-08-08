/*
 * Created on Jul 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_bjangay
 *
 */
public class GetSCSVersionAssessmentEvent extends RequestEvent
{
	private String defendantId;
	private int versionNumber;
	
	
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the versionNumber
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber the versionNumber to set
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}
