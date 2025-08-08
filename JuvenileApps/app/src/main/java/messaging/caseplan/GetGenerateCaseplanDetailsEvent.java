
/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetGenerateCaseplanDetailsEvent extends RequestEvent {
	private String caseplanId;
	private String casefileId;
	private String priorServices;
	private String contactInfo;
	private String supervisionLevelId;
	/**
	 * @return Returns the caseplanId.
	 */
	public String getCaseplanId() {
		return caseplanId;
	}
	/**
	 * @param caseplanId The caseplanId to set.
	 */
	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}
	
	/**
	 * @return Returns the caseplanId.
	 */
	public String getSupervisionLevelId() {
		return supervisionLevelId;
	}
	/**
	 * @param caseplanId The caseplanId to set.
	 */
	public void setSupervisionLevelId(String supervisionLevelId) {
		this.supervisionLevelId = supervisionLevelId;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the priorServices.
	 */
	public String getPriorServices() {
		return priorServices;
	}
	/**
	 * @param priorServices The priorServices to set.
	 */
	public void setPriorServices(String priorServices) {
		this.priorServices = priorServices;
	}
	/**
	 * @return Returns the caseplanId.
	 */
	public String getContactInfo() {
		return contactInfo;
	}
	/**
	 * @param caseplanId The caseplanId to set.
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
}
