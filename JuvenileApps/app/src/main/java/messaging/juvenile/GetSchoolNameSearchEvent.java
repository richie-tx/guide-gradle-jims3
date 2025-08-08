/*
 * Created on Aug 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSchoolNameSearchEvent extends RequestEvent {

	private String schoolDescription;
	
	/**
	 * @return Returns the schoolDescription.
	 */
	public String getSchoolDescription() {
		return schoolDescription;
	}
	/**
	 * @param schoolDescription The schoolDescription to set.
	 */
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
	}
}
