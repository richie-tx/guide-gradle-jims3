package pd.contact.officer;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
* @roseuid 42E67D3C02B2
*/
public class WorkDay extends PersistentObject {
	/**
	* Properties for day
	* @referencedType pd.codetable.Code
	* @contextKey DAY_CODE
	* @detailerDoNotGenerate true
	*/
	private Code day = null;
	private String dayId;
	private String dayOff;
	/**
	* Properties for endTime
	* @referencedType pd.codetable.Code
	* @contextKey WORK_DAY
	* @detailerDoNotGenerate true
	*/
	private Code endTime = null;
	private String endTimeId;
	private String officerProfileId;
	/**
	* Properties for startTime
	* @referencedType pd.codetable.Code
	* @contextKey WORK_DAY
	* @detailerDoNotGenerate true
	*/
	private Code startTime = null;
	private String startTimeId;
	private String workScheduleId;
	/**
	* @roseuid 434D5F7703BB
	*/
	public WorkDay() {
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getDay() {
		fetch();
		initDay();
		return day;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getDayId() {
		fetch();
		return dayId;
	}
	/**
	* @return 
	*/
	public String getDayOff() {
		fetch();
		return dayOff;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getEndTime() {
		fetch();
		initEndTime();
		return endTime;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getEndTimeId() {
		fetch();
		return endTimeId;
	}
	/**
	* @return 
	*/
	public String getOfficerProfileId() {
		fetch();
		return officerProfileId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStartTime() {
		fetch();
		initStartTime();
		return startTime;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStartTimeId() {
		fetch();
		return startTimeId;
	}
	/**
	* @return 
	*/
	public String getWorkScheduleId() {
		fetch();
		return workScheduleId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initDay() {
		if (day == null) {
			day = (Code) new mojo.km.persistence.Reference(dayId, Code.class, "DAY_CODE").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initEndTime() {
		if (endTime == null) {
			endTime = (Code) new mojo.km.persistence.Reference(endTimeId, Code.class, "WORK_DAY").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStartTime() {
		if (startTime == null) {
			startTime = (Code) new mojo.km.persistence.Reference(startTimeId, Code.class, "WORK_DAY").getObject();
		}
	}
	/**
	* set the type reference for class member day
	*/
	public void setDay(Code day) {
		if (this.day == null || !this.day.equals(day)) {
			markModified();
		}
		if (day.getOID() == null) {
			new mojo.km.persistence.Home().bind(day);
		}
		setDayId("" + day.getOID());
		day.setContext("DAY_CODE");
		this.day = (Code) new mojo.km.persistence.Reference(day).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setDayId(String dayId) {
		if (this.dayId == null || !this.dayId.equals(dayId)) {
			markModified();
		}
		day = null;
		this.dayId = dayId;
	}
	/**
	* @param string
	*/
	public void setDayOff(String string) {
		if (this.dayOff == null || !this.dayOff.equals(string)) {
			markModified();
		}
		dayOff = string;
	}
	/**
	* set the type reference for class member endTime
	*/
	public void setEndTime(Code endTime) {
		if (this.endTime == null || !this.endTime.equals(endTime)) {
			markModified();
		}
		if (endTime.getOID() == null) {
			new mojo.km.persistence.Home().bind(endTime);
		}
		setEndTimeId("" + endTime.getOID());
		endTime.setContext("WORK_DAY");
		this.endTime = (Code) new mojo.km.persistence.Reference(endTime).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setEndTimeId(String endTimeId) {
		if (this.endTimeId == null || !this.endTimeId.equals(endTimeId)) {
			markModified();
		}
		endTime = null;
		this.endTimeId = endTimeId;
	}
	/**
	* @param string
	*/
	public void setOfficerProfileId(String string) {
		if (this.officerProfileId == null || !this.officerProfileId.equals(string)) {
			markModified();
		}
		officerProfileId = string;
	}
	/**
	* set the type reference for class member startTime
	*/
	public void setStartTime(Code startTime) {
		if (this.startTime == null || !this.startTime.equals(startTime)) {
			markModified();
		}
		if (startTime.getOID() == null) {
			new mojo.km.persistence.Home().bind(startTime);
		}
		setStartTimeId("" + startTime.getOID());
		startTime.setContext("WORK_DAY");
		this.startTime = (Code) new mojo.km.persistence.Reference(startTime).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStartTimeId(String startTimeId) {
		if (this.startTimeId == null || !this.startTimeId.equals(startTimeId)) {
			markModified();
		}
		startTime = null;
		this.startTimeId = startTimeId;
	}
	/**
	* @param string
	*/
	public void setWorkScheduleId(String string) {
		if (this.workScheduleId == null || !this.workScheduleId.equals(string)) {
			markModified();
		}
		workScheduleId = string;
	}
	/**
	* @return pd.contact.WorkDay
	* @param workScheduleId
	* @roseuid 42E65EA60111
	*/
	static public WorkDay find(String workScheduleId) {
		WorkDay workDay = null;
		IHome home = new Home();
		workDay = (WorkDay) home.find(workScheduleId, WorkDay.class);
		return workDay;
	}
	/**
	* @roseuid 42E65EA6010F
	*/
	static public Iterator findAll() {
		IHome home = new Home();
		Iterator iter = home.findAll(WorkDay.class);
		return iter;
	}
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator iter = home.findAll(event, WorkDay.class);
		return iter;
	}
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 42E65EA6010F
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, WorkDay.class);
	}	
}