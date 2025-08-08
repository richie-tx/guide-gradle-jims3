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
public class GenerateSignCaseplanEvent extends RequestEvent {
	
	private String caseplanId;
	private byte[] report;
	private boolean signed;

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
	 * @return Returns the report.
	 */
	public byte[] getReport() {
		return report;
	}
	/**
	 * @param report The report to set.
	 */
	public void setReport(byte[] report) {
		this.report = report;
	}
	/**
	 * @return Returns the signed.
	 */
	public boolean isSigned() {
		return signed;
	}
	/**
	 * @param signed The signed to set.
	 */
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
}
