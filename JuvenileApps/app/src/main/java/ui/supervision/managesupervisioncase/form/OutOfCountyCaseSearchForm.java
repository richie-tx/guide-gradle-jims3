/*
 * Created on Apr 25, 2006
 */
package ui.supervision.managesupervisioncase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.domintf.managesupervisioncase.IParty;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeTableHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.supervision.UICommonSupervisionHelper;

/**
 * @author hrodriguez
 */

public class OutOfCountyCaseSearchForm extends ActionForm
{
	private String action = "";

	//	Fields
	private String caseNum;
	private Collection caseList;
	private String cdi;
	private String cjis;
	private String connectionId;
	private Date dateOfBirth;
	private String dateOfBirthAsString;
	private String driverLicenseNum;
	private String fbiNum;
	private String firstName;
	private String lastName;
	private String middleName;
	private Name name = new Name();
	private Collection partyList;
	private String raceId;
	private String secondaryAction = "";
	private String selectedValue = "";
	private String sexId;
	private String sid;
	private String spn;
	private SocialSecurity ssn = new SocialSecurity("");
	private String ssn1;
	private String ssn2;
	private String ssn3;
	private String stateId;
	private String partyOid;
	private String currentNamePtr;
	private String currentNameSeqNum;


	/**
	 */
	public OutOfCountyCaseSearchForm()
	{
	}

	public void clear(boolean clearParties, boolean clearCases)
	{
		// Never clear the action
		//		action = "";
		caseNum = "";
		cdi = "";
		cjis = "";
		dateOfBirth = null;
		driverLicenseNum = "";
		fbiNum = "";
		firstName = "";
		lastName = "";
		middleName = "";
		name = new Name();
		sid = "";
		spn = "";
		partyOid = "";
		ssn1 = "";
		ssn2 = "";
		ssn3 = "";
		ssn = new SocialSecurity("");

		//	Drop Downs
		connectionId = "";
		raceId = "";
		sexId = "";
		stateId = "";

		// Collections 
		if (clearCases)
		{
			clearCaseList();
		}
		if (clearParties)
		{
			clearPartyList();
		}
	}

	public void clearCaseList()
	{
		caseList = new ArrayList();
	}
	
	public void clearPartyList()
	{
		partyList = new ArrayList();
	}
	
	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public Collection getCaseList()
	{
		return caseList;
	}

	/**
	 * @return
	 */
	public String getCaseListSize()
	{
		return ""+caseList.size();
	}

	/**
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @return
	 */
	public String getCdi()
	{
		return cdi;
	}

	/**
	 * @return
	 */
	public String getCjis()
	{
		return cjis;
	}

	/**
	 * @return
	 */
	public String getConnection()
	{
		return CodeTableHelper.getDescrByCode(getConnectionList(),connectionId);
	}

	/**
	 * @return
	 */
	public String getConnectionId()
	{
		return connectionId;
	}

	/**
	 * @return
	 */
	public List getConnectionList()
	{
		return ComplexCodeTableHelper.getConnectionCodes();
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getDateOfBirthAsString()
	{
		if (dateOfBirth == null)
		{
			return "";
		}
		else
		{
			return DateUtil.dateToString(dateOfBirth, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getDriverLicenseNum()
	{
		return driverLicenseNum;
	}

	/**
	 * @return
	 */
	public String getFbiNum()
	{
		return fbiNum;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public Name getName()
	{
		return new Name(firstName, middleName, lastName);
		//return name;
	}

	/**
	 * @return
	 */
	public Collection getPartyList()
	{
		return partyList;
	}

	/**
	 * @return
	 */
	public String getPartyListSize()
	{
		return ""+partyList.size();
	}

	/**
	 * @return
	 */
	public String getRace()
	{
		return CodeTableHelper.getDescrByCode(getRaceList(),raceId);
	}

	/**
	 * @return
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @return
	 */
	public List getRaceList()
	{
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE);
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public String getSex()
	{
		return CodeTableHelper.getDescrByCode(getSexList(),sexId);
	}

	/**
	 * @return
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public List getSexList()
	{
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SEX);
	}

	/**
	 * @return
	 */
	public String getSid()
	{
		return sid;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return UICommonSupervisionHelper.getSpn(spn);
	}

	/**
	 * @return
	 */
	public SocialSecurity getSsn()
	{
		if (ssn.getSSN().equals("") || ssn.getSSN().length() < 9)
		{
			ssn.setSSN1(ssn1);
			ssn.setSSN2(ssn2);
			ssn.setSSN3(ssn3);
		}
		
		return ssn;
	}

	/**
	 * @return
	 */
	public String getSsn1()
	{
		return ssn1;
	}

	/**
	 * @return
	 */
	public String getSsn2()
	{
		return ssn2;
	}

	/**
	 * @return
	 */
	public String getSsn3()
	{
		return ssn3;
	}

	/**
	 * @return
	 */
	public String getState()
	{
		return CodeTableHelper.getDescrByCode(getStateList(),stateId);
	}

	/**
	 * @return
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * @return
	 */
	public List getStateList()
	{
		return SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.STATE_ABBR);
	}

	/**
	 * @param value
	 */
	public void setAction(String value)
	{
		action = value;
	}

	/**
	 * @param collection
	 */
	public void setCaseList(Collection collection)
	{
		caseList = collection;
	}

	/**
	 * @param value
	 */
	public void setCaseNum(String value)
	{
		caseNum = value;
	}

	/**
	 * @param value
	 */
	public void setCdi(String value)
	{
		cdi = value;
	}

	/**
	 * @param value
	 */
	public void setCjis(String value)
	{
		cjis = value;
	}

	/**
	 * @param value
	 */
	public void setConnectionId(String value)
	{
		connectionId = value;
	}

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date)
	{
		dateOfBirth = date;
	}

	/**
	 * @param value
	 */
	public void setDriverLicenseNum(String value)
	{
		driverLicenseNum = value;
	}

	/**
	 * @param value
	 */
	public void setFbiNum(String value)
	{
		fbiNum = value;
	}

	/**
	 * @param value
	 */
	public void setFirstName(String value)
	{
		firstName = value;
	}

	/**
	 * @param value
	 */
	public void setLastName(String value)
	{
		lastName = value;
	}

	/**
	 * @param value
	 */
	public void setMiddleName(String value)
	{
		middleName = value;
	}

	/**
	 * @param name
	 */
	public void setName(Name name)
	{
		this.name = name;
	}

	/**
	 * @param collection
	 */
	public void setPartyList(Collection collection)
	{
		partyList = collection;
	}

	/**
	 * @param value
	 */
	public void setRaceId(String value)
	{
		raceId = value;
	}

	/**
	 * @param value
	 */
	public void setSecondaryAction(String value)
	{
		secondaryAction = value;
	}

	/**
	 * @param value
	 */
	public void setSelectedValue(String value)
	{
		selectedValue = value;
	}

	/**
	 * @param value
	 */
	public void setSexId(String value)
	{
		sexId = value;
	}

	/**
	 * @param value
	 */
	public void setSid(String value)
	{
		sid = value;
	}

	/**
	 * @param value
	 */
	public void setSpn(String value)
	{
		spn = value;
	}

	/**
	 * @param security
	 */
	public void setSsn(SocialSecurity security)
	{
		ssn = security;
	}

	/**
	 * @param value
	 */
	public void setSsn1(String value)
	{
		ssn1 = value;
		ssn.setSSN1(value);
	}

	/**
	 * @param value
	 */
	public void setSsn2(String value)
	{
		ssn2 = value;
		ssn.setSSN2(value);
	}

	/**
	 * @param value
	 */
	public void setSsn3(String value)
	{
		ssn3 = value;
		ssn.setSSN3(value);
	}

	/**
	 * @param value
	 */
	public void setStateId(String value)
	{
		stateId = value;
	}

	/**
	 * Access method for the getPartyOid property.
	 * 
	 * @return   the current value of the getPartyOid property
	 */
	public String getPartyOid()
	{
		return partyOid;
	}
   
	/**
	 * Sets the value of the getPartyOid property.
	 * 
	 * @param anOid the new value of the getPartyOid property
	 */
	public void setPartyOid(String anOid)
	{
		partyOid = anOid;
	}
   
	/**
	 * @return the currentNamePtr
	 */
	public String getCurrentNamePtr() {
		return currentNamePtr;
	}

	/**
	 * @param currentNamePtr the currentNamePtr to set
	 */
	public void setCurrentNamePtr(String currentNamePtr) {
		this.currentNamePtr = currentNamePtr;
	}

	public void setPartyInfo(IParty party)
	{
		setPartyOid(party.getPartyOid());
		setFirstName(party.getPartyFirstName());
		setMiddleName(party.getPartyMiddleName());
		setLastName(party.getPartyLastName());
		setDateOfBirth(party.getPartyDateOfBirth());
		setRaceId(party.getPartyRaceId());
		setSexId(party.getPartySexId());
		setSpn(party.getPartySpn());
		setSid(party.getPartySID());
		SocialSecurity ssn = new SocialSecurity(party.getPartySSN());
		setSsn(ssn);
	}

	public void setDateOfBirthAsString(String dateOfBirthAsString) {
		
		this.dateOfBirthAsString = dateOfBirthAsString;
		if(dateOfBirthAsString != null && !dateOfBirthAsString.equals("")){
			
			this.setDateOfBirth(DateUtil.stringToDate(dateOfBirthAsString, UIConstants.DATE_FMT_1));
		}
	}

	public void setCurrentNameSeqNum(String currentNameSeqNum) {
		this.currentNameSeqNum = currentNameSeqNum;
	}

	public String getCurrentNameSeqNum() {
		return currentNameSeqNum;
	}
}
