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
public class GenerateNewCasePlanVersionEvent extends RequestEvent {

	private String casefileId;
	private String existingCaseplanId;
	
	/**
	 * @return Returns the casefileId.
	 */
	public  String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public  void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the existingCaseplanId.
	 */
	public  String getExistingCaseplanId() {
		return existingCaseplanId;
	}
	/**
	 * @param existingCaseplanId The existingCaseplanId to set.
	 */
	public  void setExistingCaseplanId(String existingCaseplanId) {
		this.existingCaseplanId = existingCaseplanId;
	}
}
