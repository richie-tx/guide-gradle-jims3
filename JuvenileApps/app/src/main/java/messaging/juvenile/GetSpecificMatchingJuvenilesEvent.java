/*
 * Created on Jul 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSpecificMatchingJuvenilesEvent extends RequestEvent {
	
	String juvA;
	String juvB;

	/**
	 * @return Returns the juvA.
	 */
	public String getJuvA() {
		return juvA;
	}
	/**
	 * @param juvA The juvA to set.
	 */
	public void setJuvA(String juvA) {
		this.juvA = juvA;
	}
	/**
	 * @return Returns the juvB.
	 */
	public String getJuvB() {
		return juvB;
	}
	/**
	 * @param juvB The juvB to set.
	 */
	public void setJuvB(String juvB) {
		this.juvB = juvB;
	}
}
