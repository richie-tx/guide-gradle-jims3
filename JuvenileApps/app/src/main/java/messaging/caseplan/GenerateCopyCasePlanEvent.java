/*
 * Created on Jun 6, 2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateCopyCasePlanEvent extends RequestEvent {

	private String casefileId;
	private String copyCaseplanId;
	private String copyFromCasefileId;
	
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
	public  String getCopyCaseplanId() {
		return copyCaseplanId;
	}
	/**
	 * @param existingCaseplanId The existingCaseplanId to set.
	 */
	public  void setCopyCaseplanId(String copyCaseplanId) {
		this.copyCaseplanId = copyCaseplanId;
	}
	
	/**
	 * @return Returns the casefileId.
	 */
	public  String getCopyFromCasefileId() {
		return copyFromCasefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public  void setCopyFromCasefileId(String copyFromCasefileId) {
		this.copyFromCasefileId = copyFromCasefileId;
	}
}
