/*
 * Created on Dec 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.spnsplit.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnSplitErrorResponseEvent extends ResponseEvent { //implements Comparable {
    private String erroneousSpn;
    private String validSpn;
    private String statusId;
    private String errorCodeId;
    private String errorResponseId;    
    private String msg;
    
    public String getValidSpn() {
		return validSpn;
	}
	public void setValidSpn(String validSpn) {
		this.validSpn = validSpn;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getErrorCodeId() {
		return errorCodeId;
	}
	public void setErrorCodeId(String errorCodeId) {
		this.errorCodeId = errorCodeId;
	}
	public String getErrorResponseId() {
		return errorResponseId;
	}
	public void setErrorResponseId(String errorResponseId) {
		this.errorResponseId = errorResponseId;
	}    

    /**
     * @return Returns the erroneousSpn.
     */
    public String getErroneousSpn() {
    	return erroneousSpn;
    }
    /**
     * @param erroneousSpn The erroneousSpn to set.
     */
    public void setErroneousSpn(String erroneousSpn) {
    	this.erroneousSpn = erroneousSpn;
    }
    	/**
    	 * @return Returns the msg.
    	 */
    	public String getMsg() {
    		return msg;
    	}
    	/**
    	 * @param msg The msg to set.
    	 */
    	public void setMsg(String msg) {
    		this.msg = msg;
    	}
}
