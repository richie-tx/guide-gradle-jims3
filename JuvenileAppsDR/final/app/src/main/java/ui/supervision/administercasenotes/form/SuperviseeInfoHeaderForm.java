/*
 * Created on Aug 1, 2006
 *
 */
package ui.supervision.administercasenotes.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.supervision.CaseInfo;

/**
 * @author jjose
 *
 */
public class SuperviseeInfoHeaderForm extends ActionForm {
	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	// Form specific fields
	private String superviseeId;
	private String superviseeNamePtr;
	private String spn;
	private ISocialSecurity ssn;
	private IName superviseeName;
	private IName officerName;
	private String officerPosition;
	private String probationOfficerId;
	private boolean inComplianceInd=true;
	private String supervisionPeriodId;
	private String con;
	private Date dateOfBirth;
	private Date lastAssessmentDate;
	private Date lastContactDate;
	private Date lastContactTime;
	private String los;
	private Date nextContactDate;
	private Date nextContactTime;
	private String raceId;
	private String race;
	private String sexId;
	private String sex;
	private String contactMethodId;
	private String contactMethod;
	private String contactReason;
	private String SID;
	private String FBI;
	private String CJIS;
	private String unit;
	private IAddress address=new Address();
	private BasicEmplymentInfo employmentInfo=new BasicEmplymentInfo();
	private Collection cases= new ArrayList();
	private IPhoneNumber homePhone=new PhoneNumber("");
	
	// Collections (make static)
	private static Collection raceList;
	private static Collection sexList;
	private static Collection contactMethodList;

	
	public void clearAll()
	{
			action="";
			secondaryAction="";
			update=false;
			delete=false;
			selectedValue="";
			
			// Form specific fields
			spn="";
			superviseeId="";
			superviseeName=new Name();
			officerName=new Name();
			probationOfficerId="";
			supervisionPeriodId="";
			con="";
			dateOfBirth=null;
			lastAssessmentDate=null;
			lastContactDate=null;
			lastContactTime=null;
			los="";
			nextContactDate=null;
			nextContactTime=null;
			raceId="";
			race="";
			sexId="";
			sex="";
			ssn=new SocialSecurity("");
			contactMethodId="";
			contactMethod="";
			contactReason="";
			SID="";
			FBI="";
			CJIS="";
			inComplianceInd=true;
			officerPosition = "";
	}
	
	public SuperviseeInfoHeaderForm(){
		setAllLists();
	}
	
	public static class BasicEmplymentInfo{
		private String employmentId;
		private String employerName;
		private IPhoneNumber phone=new PhoneNumber("");
		private String title;
		/**
		 * @return Returns the employerName.
		 */
		public String getEmployerName() {
			return employerName;
		}
		
		
		/**
		 * @param employerName The employerName to set.
		 */
		public void setEmployerName(String employerName) {
			this.employerName = employerName;
		}
		/**
		 * @return Returns the employmentId.
		 */
		public String getEmploymentId() {
			return employmentId;
		}
		/**
		 * @param employmentId The employmentId to set.
		 */
		public void setEmploymentId(String employmentId) {
			this.employmentId = employmentId;
		}
		/**
		 * @return Returns the phone.
		 */
		public IPhoneNumber getPhone() {
			return phone;
		}
		/**
		 * @param phone The phone to set.
		 */
		public void setPhone(IPhoneNumber phone) {
			this.phone = phone;
		}
		/**
		 * @return Returns the title.
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title The title to set.
		 */
		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	
	
	private void setAllLists(){
		//UICasenotesLoadCoadTables.getInstance().setSuperviseeHeaderInfoForm(this);
        sexList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SEX);
        raceList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE);
        contactMethodList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_METHOD);
	}
	/**
	 * @return Returns the contactMethodList.
	 */
	public Collection getContactMethodList() {
		if(contactMethodList==null)
			setAllLists();
		return contactMethodList;
	}
	/**
	 * @param contactMethodList The contactMethodList to set.
	 */
	public void setContactMethodList(Collection aContactMethodList) {
		contactMethodList = aContactMethodList;
	}
	/**
	 * @return Returns the raceList.
	 */
	public Collection getRaceList() {
		if(raceList==null)
			setAllLists();
		return raceList;
	}
	/**
	 * @param raceList The raceList to set.
	 */
	public void setRaceList(Collection aRaceList) {
		raceList = aRaceList;
	}
	/**
	 * @return Returns the sexList.
	 */
	public Collection getSexList() {
		return sexList;
	}
	/**
	 * @param sexList The sexList to set.
	 */
	public void setSexList(Collection aSexList) {
		if(aSexList==null)
			setAllLists();
		sexList = aSexList;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the cJIS.
	 */
	public String getCJIS() {
		return CJIS;
	}
	/**
	 * @param cjis The cJIS to set.
	 */
	public void setCJIS(String cjis) {
		CJIS = cjis;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public void setDateOfBirthAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			dateOfBirth=null;
		try{
			dateOfBirth=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			dateOfBirth=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getDateOfBirthAsStr() {
		if(dateOfBirth==null){
			return "";
		}
		try{
			return DateUtil.dateToString(dateOfBirth, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the contactReason.
	 */
	public String getContactReason() {
		return contactReason;
	}
	/**
	 * @param contactReason The contactReason to set.
	 */
	public void setContactReason(String contactReason) {
		this.contactReason = contactReason;
	}
	/**
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId() {
		return contactMethodId;
	}
	/**
	 * @param contactMethodId The contactMethodId to set.
	 */
	public void setContactMethodId(String string) {
		this.contactMethodId = string;
		if(contactMethodId==null || contactMethodId.equals("")){
			contactMethod="";
			return;
		}
			
		if(getContactMethodList() !=null &&  getContactMethodList().size()>0){
			contactMethod=CodeHelper.getCodeDescriptionByCode(getContactMethodList(),contactMethodId);
		}
	}
	/**
	 * @return Returns the fBI.
	 */
	public String getFBI() {
		return FBI;
	}
	/**
	 * @param fbi The fBI to set.
	 */
	public void setFBI(String fbi) {
		FBI = fbi;
	}
	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}
	/**
	 * @return Returns the raceId.
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param raceId The raceId to set.
	 */
	public void setRaceId(String string) {
		this.raceId = string;
		if(raceId==null || raceId.equals("")){
			race="";
			return;
		}
			
		if(getRaceList() !=null &&  getRaceList().size()>0){
			race=CodeHelper.getCodeDescriptionByCode(getRaceList(),raceId);
		}
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the sexId.
	 */
	public String getSexId() {
		return sexId;
		
	}
	/**
	 * @param sexId The sexId to set.
	 */
	public void setSexId(String string) {
		sexId = string;
		if(sexId==null || sexId.equals("")){
			sex="";
			return;
		}
			
		if(getSexList() !=null &&  getSexList().size()>0){
			sex=CodeHelper.getCodeDescriptionByCode(getSexList(),sexId);
		}
	}
	/**
	 * @return Returns the sID.
	 */
	public String getSID() {
		return SID;
	}
	/**
	 * @param sid The sID to set.
	 */
	public void setSID(String sid) {
		SID = sid;
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
	/**
	 * @return Returns the ssn.
	 */
	public ISocialSecurity getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(ISocialSecurity aSsn) {
		this.ssn = aSsn;
	}
	/**
	 * @return Returns the superviseeName.
	 */
	public IName getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName The superviseeName to set.
	 */
	public void setSuperviseeName(IName superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return Returns the supervisionPeriod.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	/**
	 * @param supervisionPeriod The supervisionPeriod to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriod) {
		this.supervisionPeriodId = supervisionPeriod;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	/**
	 * @return Returns the con.
	 */
	public String getCon() {
		return con;
	}
	/**
	 * @param con The con to set.
	 */
	public void setCon(String con) {
		this.con = con;
	}
	/**
	 * @return Returns the contactMethod.
	 */
	public String getContactMethod() {
		return contactMethod;
	}
	/**
	 * @param contactMethod The contactMethod to set.
	 */
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	/**
	 * @return Returns the officerName.
	 */
	public IName getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName The officerName to set.
	 */
	public void setOfficerName(IName officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return Returns the race.
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return Returns the address.
	 */
	public IAddress getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(IAddress address) {
		this.address = address;
	}
	/**
	 * @return Returns the cases.
	 */
	public Collection getCases() {
		return cases;
	}
	
	/**
	 * @return Returns the cases.
	 */
	public Collection getFilteredBySupIdCases() {
		ArrayList myList=new ArrayList();
		if(cases==null || cases.size()<=0)
			return myList; 
		if(supervisionPeriodId==null || supervisionPeriodId.equals("")){
			return cases;
		}
		Iterator iter=cases.iterator();
		while(iter.hasNext()){
			CaseInfo myCaseInfo=(CaseInfo)iter.next();
			if(myCaseInfo.getSupPeriodId()!=null && supervisionPeriodId.equals(myCaseInfo.getSupPeriodId())){
				myList.add(myCaseInfo);
			}
		}
		return myList;
	}
	/**
	 * @param cases The cases to set.
	 */
	public void setCases(Collection cases) {
		this.cases = cases;
	}
	/**
	 * @return Returns the employmentInfo.
	 */
	public BasicEmplymentInfo getEmploymentInfo() {
		return employmentInfo;
	}
	/**
	 * @param employmentInfo The employmentInfo to set.
	 */
	public void setEmploymentInfo(BasicEmplymentInfo employmentInfo) {
		this.employmentInfo = employmentInfo;
	}
	/**
	 * @return Returns the homePhone.
	 */
	public IPhoneNumber getHomePhone() {
		return homePhone;
	}
	/**
	 * @param homePhone The homePhone to set.
	 */
	public void setHomePhone(IPhoneNumber homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the lastAssessmentDate
	 */
	public Date getLastAssessmentDate() {
		return lastAssessmentDate;
	}

	/**
	 * @param lastAssessmentDate the lastAssessmentDate to set
	 */
	public void setLastAssessmentDate(Date lastAssessmentDate) {
		this.lastAssessmentDate = lastAssessmentDate;
	}
	
	/**
	 * @return the lastContactDate
	 */
	public Date getLastContactDate() {
		return lastContactDate;
	}

	/**
	 * @param lastContactDate the lastContactDate to set
	 */
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	/**
	 * @return the lastContactTime
	 */
	public Date getLastContactTime() {
		return lastContactTime;
	}

	/**
	 * @param lastContactTime the lastContactTime to set
	 */
	public void setLastContactTime(Date lastContactTime) {
		this.lastContactTime = lastContactTime;
	}
	
	/**
	 * @return Returns the LOS.
	 */
	public String getLos() {
		return los;
	}
	
	/**
	 * @param los The LOS to set.
	 */
	public void setLos(String los) {
		this.los = los;
	}
	
	/**
	 * @return Returns the nextContactDate.
	 */
	public Date getNextContactDate() {
		return nextContactDate;
	}
	/**
	 * @param nextContactDate The nextContactDate to set.
	 */
	public void setNextContactDate(Date nextContactDate) {
		this.nextContactDate = nextContactDate;
	}
	
	/**
	 * @return Sets the nextContactDate as a String.
	 */
	public void setNextContactDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			nextContactDate=null;
		try{
			nextContactDate=DateUtil.stringToDate(aStringDate, UIConstants.DATETIME_FMT_1);
		}
		catch(Exception e){
			nextContactDate=null;
		}
	}
	
	/**
	 * @return Returns the next Contact Date as String with the time appended.
	 */
	public String getNextContactDateAsStr() {
		if(nextContactDate==null){
			return "";
		}
		try{
			String dateTime = DateUtil.dateToString(nextContactDate, UIConstants.DATE_FMT_1);
			dateTime += "11:00 AM";
			return dateTime;
		}
		catch(Exception e){
			return "";
		}
	}
	/**
	 * @return the nextContactTime
	 */
	public Date getNextContactTime() {
		return nextContactTime;
	}

	/**
	 * @param nextContactTime the nextContactTime to set
	 */
	public void setNextContactTime(Date nextContactTime) {
		this.nextContactTime = nextContactTime;
	}

	/**
	 * @return Returns the unit.
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit The unit to set.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return Returns the inComplianceInd.
	 */
	public boolean isInComplianceInd() {
		return inComplianceInd;
	}
	/**
	 * @param inComplianceInd The inComplianceInd to set.
	 */
	public void setInComplianceInd(boolean inComplianceInd) {
		this.inComplianceInd = inComplianceInd;
	}
	/**
	 * @return Returns the probationOfficerId.
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId The probationOfficerId to set.
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	public String getSuperviseeNamePtr() {
		return superviseeNamePtr;
	}

	public void setSuperviseeNamePtr(String superviseeNamePtr) {
		this.superviseeNamePtr = superviseeNamePtr;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}
}// END CLASS
