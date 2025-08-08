//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class UpdateUrinalysisEvent extends RequestEvent 
{
   
	private String defendantId;
	private String lName;
	private String mName;	
	private String fName;
	private String poi;
	private String dob; //1969-06-03
	private String sex;
	private String race;
	private String crt;
	
	public String getDefendantId() {
		return defendantId;
	}
	public void setDefendantId( String defendantId) {
		this.defendantId = defendantId;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		this.poi = poi;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getCrt() {
		return crt;
	}
	public void setCrt(String crt) {
		this.crt = crt;
	}
	
	
}
