/*
 * Created on Mar 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar;


/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DeleteCSFVEvent extends mojo.km.messaging.PersistentEvent {

	private String fvEventid;

		/**
	 * @return Returns the fvEventid.
	 */
	public String getFvEventid() {
		return fvEventid;
	}

	/**
	 * @param fvEventid
	 *            The fvEventid to set.
	 */
	public void setFvEventid(String fvEventid) {
		this.fvEventid = fvEventid;
	}
}
