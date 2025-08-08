/*
 * Created on Oct 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.spnconsolidation.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnConsolidationErrorResponseEvent extends ResponseEvent{
	
	private String erroneousSpn;
	private boolean isBaseSpnError;
	
	
	/**
	 * @return Returns the isBaseSpnError.
	 */
	public boolean isBaseSpnError() {
		return isBaseSpnError;
	}
	/**
	 * @param isBaseSpnError The isBaseSpnError to set.
	 */
	public void setBaseSpnError(boolean isBaseSpnError) {
		this.isBaseSpnError = isBaseSpnError;
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
}
