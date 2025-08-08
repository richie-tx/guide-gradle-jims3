/*
 * Created on Mar 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SuperviseeBean {
	private String spn;
	private IName name;
	private String defendantName;
	
	public String getDefendantName() {
		return defendantName;
	}
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	public SuperviseeBean() {
		name=new NameBean();
		
	}
	/**
	 * @return Returns the name.
	 */
	public IName getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(IName name) {
		this.name = name;
	}
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
}
