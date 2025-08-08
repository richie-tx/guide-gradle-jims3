/*
 * Created on Mar 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;

import mojo.km.messaging.IEvent;

/**
 * @author fsjodin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetConstraintByConstrainsIdAndConstrainsTypeEvent extends mojo.km.messaging.RequestEvent {
	private String _constrainsId;
	private String _constrainsType;
	
	


	/**
	 * @return
	 */
	public String getConstrainsId() {
		return _constrainsId;
	}

	/**
	 * @return
	 */
	public String getConstrainsType() {
		return _constrainsType;
	}

	/**
	 * @param string
	 */
	public void setConstrainsId(String string) {
		_constrainsId = string;
	}

	/**
	 * @param string
	 */
	public void setConstrainsType(String string) {
		_constrainsType = string;
	}

}
