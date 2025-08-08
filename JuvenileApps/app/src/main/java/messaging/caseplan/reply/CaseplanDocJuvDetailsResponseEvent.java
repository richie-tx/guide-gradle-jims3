/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CaseplanDocJuvDetailsResponseEvent extends ResponseEvent {
	
	private String juvNum;
	private String juvFirstName;
	private String juvMiddleName;
	private String juvLastName;
	
	private Date dob;
	
	public CaseplanDocJuvDetailsResponseEvent() {
		
	}
	
	/**
	 * @return Returns the dob.
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob The dob to set.
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return Returns the juvNum.
	 */
	public String getJuvNum() {
		return juvNum;
	}
	/**
	 * @param juvNum The juvNum to set.
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}
	
	public String getJuvFirstName() {
		return juvFirstName;
	}
	public void setJuvFirstName(String juvFirstName) {
		this.juvFirstName = juvFirstName;
	}
	public String getJuvLastName() {
		return juvLastName;
	}
	public void setJuvLastName(String juvLastName) {
		this.juvLastName = juvLastName;
	}
	public String getJuvMiddleName() {
		return juvMiddleName;
	}
	public void setJuvMiddleName(String juvMiddleName) {
		this.juvMiddleName = juvMiddleName;
	}
	
	public String getFullNameFirst()
	{		
		StringBuffer sb = new StringBuffer();
		if(this.juvFirstName != null && this.juvFirstName.length() > 0) {
			sb.append(this.juvFirstName);
			sb.append(" ");
		}
		if(this.juvMiddleName != null && this.juvMiddleName.length() > 0) {
			sb.append(this.juvMiddleName);
			sb.append(" ");
		}
		if(this.juvLastName != null && this.juvLastName.length() > 0) {
			sb.append(this.juvLastName);
		}
		
		return sb.toString();
	}
}
