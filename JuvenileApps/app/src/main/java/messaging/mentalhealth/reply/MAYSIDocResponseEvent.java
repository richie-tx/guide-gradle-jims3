/*
 * Created on Jul 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MAYSIDocResponseEvent extends ResponseEvent {
	private String juvenileNum;
	private Object maysiDoc;
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the maysiDoc.
	 */
	public Object getMaysiDoc() {
		return maysiDoc;
	}
	/**
	 * @param maysiDoc The maysiDoc to set.
	 */
	public void setMaysiDoc(Object maysiDoc) {
		this.maysiDoc = maysiDoc;
	}
}
