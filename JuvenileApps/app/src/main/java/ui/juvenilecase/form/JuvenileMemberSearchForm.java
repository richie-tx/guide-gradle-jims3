/*
 * Created on Oct 4, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.common.Name;
import ui.common.SocialSecurity;
import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author jjose
 * 
 */
public class JuvenileMemberSearchForm extends ActionForm
{
    private static List emptyColl = new ArrayList();

    private boolean listsSet = false;

    //	Drop Downs
    private String searchById = "";

    private String sexId = "";

    private String statusId = "";

    private String relationshipId = "";

    // Fields

    private String sex = "";

    private String status = "";

    private Name name = new Name();

    private String dateOfBirth = "";

    private String action = "";

    private boolean update = false;

    private boolean delete = false;

    private String juvenileNumber = "";

    private String selectedValue = "";

    private SocialSecurity ssn = new SocialSecurity("");

    private String relationship = "";

    private boolean deceased = false;

    private String constellationNumber = "";

    private boolean popUp;

    // Collections and Drop Down Lists
    private static List searchByTypeList = emptyColl;

    private static List sexList = emptyColl;

    private static List statusList = emptyColl;

    private static List relationshipList = emptyColl;

    private List memberSearchResults = emptyColl;

    private List selectedMembers = emptyColl;

    private String suspiciousMemberMatch;
    
    private String driverLicenseNum;
    

    public JuvenileMemberSearchForm()
    {
	UIJuvenileLoadCodeTables.getInstance().setJuvenileMemberSearchForm(this);

    }

    public void clearSearchResults()
    {
	memberSearchResults = emptyColl;
	selectedMembers = emptyColl;
    }

    public void clear()
    {
	searchById = "";
	sexId = "";
	statusId = "";
	relationshipId = "";
	driverLicenseNum = "";

	// Fields
	sex = "";
	status = "";
	name = new Name();
	dateOfBirth = "";
	action = "";
	update = false;
	delete = false;
	//	 juvenileNumber="";
	selectedValue = "";
	ssn = new SocialSecurity("");
	relationship = "";
	deceased = false;
	constellationNumber = "";

	// Collections and Drop Down Lists
	memberSearchResults = emptyColl;
	selectedMembers = emptyColl;
    }

    /** BEGIN GETTERS AND SETTERS FOR JuvenileMemberSearchForm */

    /**
     * @return
     */
    public List getSearchByTypeList()
    {
	return searchByTypeList;
    }

    /**
     * @return
     */
    public List getSexList()
    {
	return sexList;
    }

    /**
     * @return
     */
    public List getStatusList()
    {
	return statusList;
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
    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @return
     */
    public boolean isDelete()
    {
	return delete;
    }

    /**
     * @return
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @return
     */
    public boolean isListsSet()
    {
	return listsSet;
    }

    /**
     * @return
     */
    public List getMemberSearchResults()
    {
	return memberSearchResults;
    }

    /**
     * @return
     */
    public Name getName()
    {
	return name;
    }

    /**
     * @return
     */
    public String getSearchById()
    {
	return searchById;
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
	return sex;
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
    public String getStatus()
    {
	return status;
    }

    /**
     * @return
     */
    public String getStatusId()
    {
	return statusId;
    }

    /**
     * @return
     */
    public boolean isUpdate()
    {
	return update;
    }

    /**
     * @param collection
     */
    public void setSearchByTypeList(List collection)
    {
	searchByTypeList = collection;
    }

    /**
     * @param collection
     */
    public void setSexList(List collection)
    {
	sexList = collection;
    }

    /**
     * @param collection
     */
    public void setStatusList(List collection)
    {
	statusList = collection;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
	action = string;
    }

    /**
     * @param string
     */
    public void setDateOfBirth(String string)
    {
	dateOfBirth = string;
    }

    /**
     * @param b
     */
    public void setDelete(boolean b)
    {
	delete = b;
    }

    /**
     * @param string
     */
    public void setJuvenileNumber(String string)
    {
	juvenileNumber = string;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b)
    {
	listsSet = b;
    }

    /**
     * @param collection
     */
    public void setMemberSearchResults(List collection)
    {
	memberSearchResults = collection;
    }

    /**
     * @param name
     */
    public void setName(Name name)
    {
	this.name = name;
    }

    /**
     * @param string
     */
    public void setSearchById(String string)
    {
	searchById = string;
    }

    /**
     * @param string
     */
    public void setSelectedValue(String string)
    {
	selectedValue = string;
    }

    /**
     * @param string
     */
    public void setSex(String string)
    {
	sex = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
	sexId = string;
    }

    /**
     * @param string
     */
    public void setStatus(String string)
    {
	status = string;
    }

    /**
     * @param string
     */
    public void setStatusId(String string)
    {
	statusId = string;
    }

    /**
     * @param b
     */
    public void setUpdate(boolean b)
    {
	update = b;
    }

    /**
     * @return
     */
    public SocialSecurity getSsn()
    {
	return ssn;
    }

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
	ssn = security;
    }

    /**
     * @return
     */
    public Collection getRelationshipList()
    {
	return relationshipList;
    }

    /**
     * @return
     */
    public boolean isDeceased()
    {
	return deceased;
    }

    /**
     * @return
     */
    public String getRelationship()
    {
	return relationship;
    }

    /**
     * @return
     */
    public String getRelationshipId()
    {
	return relationshipId;
    }

    /**
     * @param collection
     */
    public void setRelationshipList(List collection)
    {
	relationshipList = collection;
    }

    /**
     * @param b
     */
    public void setDeceased(boolean b)
    {
	deceased = b;
    }

    /**
     * @param string
     */
    public void setRelationship(String string)
    {
	relationship = string;
    }

    /**
     * @param string
     */
    public void setRelationshipId(String string)
    {
	relationshipId = string;
    }

    /**
     * @return
     */
    public String getConstellationNumber()
    {
	return constellationNumber;
    }

    /**
     * @param string
     */
    public void setConstellationNumber(String string)
    {
	constellationNumber = string;
    }

    /**
     * @return
     */
    public Collection getSelectedMembers()
    {
	return selectedMembers;
    }

    /**
     * @param collection
     */
    public void setSelectedMembers(List collection)
    {
	selectedMembers = collection;
    }

    /**
     * @return
     */
    public boolean isPopUp()
    {
	return popUp;
    }

    /**
     * @param b
     */
    public void setPopUp(boolean b)
    {
	popUp = b;
    }

    public String getSuspiciousMemberMatch()
    {
	return suspiciousMemberMatch;
    }

    public void setSuspiciousMemberMatch(String suspiciousMemberMatch)
    {
	this.suspiciousMemberMatch = suspiciousMemberMatch;
    }
    
    
   

    /** BEGIN GETTERS AND SETTERS FOR JuvenileMemberSearchForm */

    public String getDriverLicenseNum()
    {
        return driverLicenseNum;
    }

    public void setDriverLicenseNum(String driverLicenseNum)
    {
        this.driverLicenseNum = driverLicenseNum;
    }




    /** BEGIN of MemberSearchResult CLASS */
    public static class MemberSearchResult implements Comparable
    {

	private String sex = "";

	private Name name = new Name();

	private String dateOfBirth = "";

	private String juvenileNumber = "";

	private String selectedValue = "";

	private String status = "";

	private SocialSecurity ssn = new SocialSecurity("");

	private String relationshipId = "";

	private String relationship = "";

	private boolean deceased = false;

	private boolean checked = false;

	private String memberNumber = "";

	private String suspiciousMember;

	private String ethnicityCd = "";

	private String ethnicity = "";

	private String juvenileName = "";

	private List associatedJuvenilesList = new ArrayList();

	private boolean over21; //US #43116

	private SocialSecurity completeSSN = new SocialSecurity(""); //US 39892

	public int compareTo(Object obj) throws ClassCastException
	{
	    MemberSearchResult evt = (MemberSearchResult) obj;
	    int result = 0;
	    if (memberNumber != null && evt.getMemberNumber() != null)
	    {
		try
		{
		    result = Integer.valueOf(memberNumber).compareTo(Integer.valueOf(evt.getMemberNumber()));
		}
		catch (NumberFormatException e)
		{
		    result = 0;
		}
	    }
	    return result;
	}

	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
	    return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}

	/**
	 * @return
	 */
	public Name getName()
	{
	    return name;
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
	    return sex;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
	    return status;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
	    dateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
	    juvenileNumber = string;
	}

	/**
	 * @param name
	 */
	public void setName(Name name)
	{
	    this.name = name;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
	    selectedValue = string;
	}

	/**
	 * @param string
	 */
	public void setSex(String string)
	{
	    sex = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
	    status = string;
	}

	/**
	 * @return
	 */
	public boolean isDeceased()
	{
	    return deceased;
	}

	/**
	 * @return
	 */
	public String getRelationship()
	{
	    return relationship;
	}

	/**
	 * @return
	 */
	public SocialSecurity getSsn()
	{
	    return ssn;
	}

	/**
	 * @param b
	 */
	public void setDeceased(boolean b)
	{
	    deceased = b;
	}

	/**
	 * @param string
	 */
	public void setRelationship(String string)
	{
	    relationship = string;
	}

	/**
	 * @param security
	 */
	public void setSsn(SocialSecurity security)
	{
	    ssn = security;
	}

	/**
	 * @return
	 */
	public boolean isChecked()
	{
	    return checked;
	}

	/**
	 * @return
	 */
	public String getRelationshipId()
	{
	    return relationshipId;
	}

	/**
	 * @param b
	 */
	public void setChecked(boolean b)
	{
	    checked = b;
	}

	/**
	 * @param string
	 */
	public void setRelationshipId(String string)
	{
	    relationshipId = string;
	}

	/**
	 * @return
	 */
	public String getMemberNumber()
	{
	    return memberNumber;
	}

	/**
	 * @param string
	 */
	public void setMemberNumber(String string)
	{
	    memberNumber = string;
	}

	/**
	 * @return
	 */
	public String getSuspiciousMember()
	{
	    return suspiciousMember;
	}

	/**
	 * @param string
	 */
	public void setSuspiciousMember(String string)
	{
	    suspiciousMember = string;
	}

	/**
	 * @return the ethnicityCd
	 */
	public String getEthnicityCd()
	{
	    return ethnicityCd;
	}

	/**
	 * @param ethnicityCd
	 *            the ethnicityCd to set
	 */
	public void setEthnicityCd(String ethnicityCd)
	{
	    this.ethnicityCd = ethnicityCd;
	}

	/**
	 * @return the ethnicity
	 */
	public String getEthnicity()
	{
	    return ethnicity;
	}

	/**
	 * @param ethnicity
	 *            the ethnicity to set
	 */
	public void setEthnicity(String ethnicity)
	{
	    this.ethnicity = ethnicity;
	}

	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName()
	{
	    return juvenileName;
	}

	/**
	 * @param juvenileName
	 *            the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName)
	{
	    this.juvenileName = juvenileName;
	}

	/**
	 * @return the associatedJuvenilesList
	 */
	public List getAssociatedJuvenilesList()
	{
	    return associatedJuvenilesList;
	}

	/**
	 * @param associatedJuvenilesList
	 *            the associatedJuvenilesList to set
	 */
	public void setAssociatedJuvenilesList(List associatedJuvenilesList)
	{
	    this.associatedJuvenilesList = associatedJuvenilesList;
	}

	public boolean isOver21()
	{
	    return over21;
	}

	public void setOver21(boolean over21)
	{
	    this.over21 = over21;
	}

	public SocialSecurity getCompleteSSN()
	{
	    return completeSSN;
	}

	public void setCompleteSSN(SocialSecurity completeSSN)
	{
	    this.completeSSN = completeSSN;
	}

	
	
	

	
	

    }
    /** END of MemberSearchResult CLASS */

}// END-CLASS
