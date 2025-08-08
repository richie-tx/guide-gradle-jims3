/*
 * Created on Apr 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;

import mojo.km.messaging.RequestEvent;

/**
 * @author fsjodin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetConstraintByConstrainerAndConstrainsEvent
	extends RequestEvent
{
	private String _constrainsId;
	private String _constrainsType;
	private String _constrainerId;
	private String _constrainerType;
	
	


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

	/**
	 * @return
	 */
	public String getConstrainerId() {
		return _constrainerId;
	}

	/**
	 * @return
	 */
	public String getConstrainerType() {
		return _constrainerType;
	}

	/**
	 * @param string
	 */
	public void setConstrainerId(String string) {
		_constrainerId = string;
	}

	/**
	 * @param string
	 */
	public void setConstrainerType(String string) {
		_constrainerType = string;
	}
}
