/*
 * Created on Aug 1, 2006
 *
 */
package ui.supervision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;
import mojo.km.utilities.DateUtil;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm.BasicEmplymentInfo;

/**
 * @author jjose
 *
 */
public class BasicSupervisee implements Comparable{
	
	private String superviseeId;
	private String spn;
	private IName superviseeName;
	private String superviseeNamePtr;
	private String courts;
	private String con;
	private IName officerName;
	private String officerPosition;
	private String probationOfficerId;
	private Date dateOfBirth;
	private String raceId;
	private String race;
	private String sexId;
	private String sex;
	private ISocialSecurity ssn;
	private static Collection raceList;
	private static Collection sexList;
	private String supervisionPeriodId;
	private String CJIS;
	
	private IAddress address=new Address();
	private String contactMethodId;
	private String contactReason;
	private String FBI;
	private String SID;
	private String contactMethod;
	private String unit;
	private boolean inComplianceInd=true;
	private Date nextContactDate;
	private BasicEmplymentInfo employmentInfo=new BasicEmplymentInfo();
	private IPhoneNumber homePhone=new PhoneNumber("");
	private Collection cases;
	private boolean hasPartialInfo;
	
	
	public int compareTo(Object aBasicSup)
	{
		BasicSupervisee basicSup = (BasicSupervisee) aBasicSup;
		int comparisonResult = 0;
		String newString = "";
		
		if (basicSup.getSuperviseeName() == null)
		{
			return 1;
		}
		if(this.getSuperviseeName()==null){
			return -1;
		}

		int myComp=(this.getSuperviseeName().getLastName().compareTo(basicSup.getSuperviseeName().getLastName()));
		if(myComp==0){
			return (this.getSuperviseeName().getFirstName().compareTo(basicSup.getSuperviseeName().getFirstName()));
		}
		else
			return myComp;
	}

	
	public BasicSupervisee(){
		setAllLists();
	}
	
	
	
	private void setAllLists(){
		//UICasenotesLoadCoadTables.getInstance().setBasicSupervisee(this);
        sexList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SEX);
        raceList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE);
	}
	
	public void clearAll()
	{
		// Form specific fields
		superviseeId="";
		courts="";
		spn="";
		superviseeName=new Name();
		con="";
		officerName=new Name();
		dateOfBirth=null;
		raceId="";
		race="";
		sexId="";
		sex="";
		ssn=new SocialSecurity("");
		supervisionPeriodId="";
		cases=new ArrayList();
		CJIS="";
		
		address=new Address();
		contactMethodId="";
		contactReason="";
		FBI="";
		SID="";
		contactMethod="";
		unit="";
		inComplianceInd=true;
		nextContactDate=null;
		employmentInfo=new BasicEmplymentInfo();
		homePhone=new PhoneNumber("");
		officerPosition = "";
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
		Collection contactMethodList=CodeHelper.getCodes(PDCodeTableConstants.CONTACT_METHOD);
		if(contactMethodList !=null &&  contactMethodList.size()>0){
			contactMethod=CodeHelper.getCodeDescriptionByCode(contactMethodList,contactMethodId);
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
	 * @return Returns the raceId.
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param raceId The raceId to set.
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
		if(raceId==null || raceId.equals("")){
			race="";
			return;
		}
			
		if(getRaceList() !=null &&  getRaceList().size()>0){
			race=CodeHelper.getCodeDescriptionByCode(getRaceList(),raceId);
		}
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
	 * @return Returns the sexId.
	 */
	public String getSexId() {
		return sexId;
	}
	/**
	 * @param sexId The sexId to set.
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
		if(sexId==null || sexId.equals("")){
			sex="";
			return;
		}
			
		if(getSexList() !=null &&  getSexList().size()>0){
			sex=CodeHelper.getCodeDescriptionByCode(getSexList(),sexId);
		}
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
	public void setSsn(ISocialSecurity ssn) {
		this.ssn = ssn;
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
		if(sexList==null)
			setAllLists();
		return sexList;
	}
	/**
	 * @param sexList The sexList to set.
	 */
	public void setSexList(Collection aSexList) {
		sexList = aSexList;
	}
    /**
     * @return Returns the courts.
     */
    public String getCourts() {
        return courts;
    }
    /**
     * @param courts The courts to set.
     */
    public void setCourts(String courts) {
        this.courts = courts;
    }

	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	/**
	 * @return Returns the cases.
	 */
	public Collection getCases() {
		return cases;
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
	 * @return Returns the dateOfBirth.
	 */
	public void setNextContactDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			nextContactDate=null;
		try{
			nextContactDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			nextContactDate=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getNextContactDateAsStr() {
		if(nextContactDate==null){
			return "";
		}
		try{
			return DateUtil.dateToString(nextContactDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
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
    /**
     * @return Returns the hasPartialInfo.
     */
    public boolean isHasPartialInfo() {
        return hasPartialInfo;
    }
    /**
     * @param hasPartialInfo The hasPartialInfo to set.
     */
    public void setHasPartialInfo(boolean hasPartialInfo) {
        this.hasPartialInfo = hasPartialInfo;
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
}
