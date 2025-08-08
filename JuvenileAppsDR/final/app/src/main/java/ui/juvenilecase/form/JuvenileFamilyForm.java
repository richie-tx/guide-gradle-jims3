/*
 * Created on Sep 19, 2005
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;

/**
 * @author jjose
 */
//********************************************************************************
//*  CAUTION - This form contains 5 CLASS definitions which may contain variables
//*            with the same name as those on the form or one of the other classes
//********************************************************************************
public class JuvenileFamilyForm extends ActionForm
{
    private static Collection emptyColl = new ArrayList();

    private boolean listsSet = false;

    private boolean hasActiveConstellation = false;

    private Constellation currentConstellation = new Constellation();
    private Constellation currentActiveConstellation = new Constellation();

    private JuvenileMemberForm currentMember = new JuvenileMemberForm();

    private Trait currentTrait = new Trait();

    private String action = "";

    private String secondaryAction = "";

    private boolean update = false;

    private boolean delete = false;

    private String selectedValue = "";

    private String juvenileNumber = "";
    private boolean isClosedJuvStatus = false;

    private Guardian currentGuardian = new Guardian();

    private boolean popUp = false;

    private String nextGuardian = null;

    private String currentFamilyNumber = "";

    private String primayContactMemberNumber = "";

    private String youthLivesWithId = "";

    private String youthLivesWithDesc = "";

    // Collections and LISTS
    private Collection constellationList = emptyColl;

    // loaded using UIJuvenileLoadCodeTables
    private static Collection youthLivesWith;

    private static Collection relationshipToJuvenileList;

    private static Collection activeRelationshipToJuvenileList;

    private static Collection sexList;

    private static Collection causeOfDeathList;

    private static Collection stateList;

    private static Collection traitDescList;

    private static Collection traitStatusList;

    private static Collection traitLevelList;

    private static Collection involvementLevelList;

    //added for User Story 43892
    private int totalDetentionVisits = 0;

    private boolean daVisit = false;
    private boolean visitorCapRemoved = false;
    
    private boolean youthHasConstellation = false;

    public JuvenileFamilyForm()
    {
	emptyColl = new ArrayList();
	currentConstellation = new Constellation();
	currentActiveConstellation = new Constellation();
	currentMember = new JuvenileMemberForm();
	action = "";
	update = false;
	delete = false;
	this.isClosedJuvStatus = false;
	constellationList = new ArrayList();
	involvementLevelList = new ArrayList();
	youthLivesWith = new ArrayList();
	UIJuvenileLoadCodeTables.getInstance().setJuvenileFamilyForm(this);
	//setFake();
    }

    public void clear()
    {
	currentConstellation = new Constellation();
	currentMember = new JuvenileMemberForm();
	update = false;
	delete = false;
	this.isClosedJuvStatus = false;
	constellationList = new ArrayList();
	currentTrait = new Trait();
	currentGuardian = new Guardian();
	hasActiveConstellation = false;
	this.youthLivesWithId = "";
	popUp = false;
    }

    private void setFake()
    {
	ConstellationList test = new ConstellationList();
	test.setFamilyNumber("testFamNum");
	test.setGuardianNames("testGuardianNames");
	test.setEntryDate("testEntryDate");
	constellationList.add(test);

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
    public Collection getConstellationList()
    {
	return constellationList;
    }

    /**
     * @return
     */
    public Constellation getCurrentConstellation()
    {
	return currentConstellation;
    }

    /**
     * @return
     */
    public JuvenileMemberForm getCurrentMember()
    {
	return currentMember;
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
    public boolean isUpdate()
    {
	return update;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
	action = string;
    }

    public boolean getIsClosedJuvStatus()
    {
	return this.isClosedJuvStatus;
    }

    public void setIsClosedJuvStatus(boolean isClosedJuvStatus)
    {
	this.isClosedJuvStatus = isClosedJuvStatus;
    }

    /**
     * @param collection
     */
    public void setConstellationList(Collection collection)
    {
	constellationList = collection;
    }

    /**
     * @param constellation
     */
    public void setCurrentConstellation(Constellation constellation)
    {
	currentConstellation = constellation;
    }

    /**
     * @param form
     */
    public void setCurrentMember(JuvenileMemberForm form)
    {
	currentMember = form;
    }

    /**
     * @param b
     */
    public void setDelete(boolean b)
    {
	delete = b;
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
    public String getSelectedValue()
    {
	return selectedValue;
    }

    /**
     * @param string
     */
    public void setSelectedValue(String string)
    {
	selectedValue = string;
    }

    /**
     * @return
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param string
     */
    public void setJuvenileNumber(String string)
    {
	juvenileNumber = string;
    }

    /**
     * @return
     */
    public Collection getCauseOfDeathList()
    {
	return causeOfDeathList;
    }

    /**
     * @return
     */
    public Collection getRelationshipToJuvenileList()
    {
	return relationshipToJuvenileList;
    }

    /**
     * @return the activeRelationshipToJuvenileList
     */
    public Collection getActiveRelationshipToJuvenileList()
    {
	return activeRelationshipToJuvenileList;
    }

    public Collection getYouthLivesWith()
    {
	return youthLivesWith;
    }

    /**
     * @return
     */
    public Collection getSexList()
    {
	return sexList;
    }

    /**
     * @return
     */
    public Collection getStateList()
    {
	return stateList;
    }

    /**
     * @return
     */
    public Collection getTraitLevelList()
    {
	return traitLevelList;
    }

    /**
     * @return
     */
    public Collection getTraitDescList()
    {
	return traitDescList;
    }

    /**
     * @return
     */
    public Collection getTraitStatusList()
    {
	return traitStatusList;
    }

    /**
     * @param collection
     */
    public void setCauseOfDeathList(Collection collection)
    {
	causeOfDeathList = collection;
    }

    /**
     * @param collection
     */
    public void setRelationshipToJuvenileList(Collection collection)
    {
	relationshipToJuvenileList = collection;
    }

    public void setYouthLivesWith(Collection collection)
    {
	youthLivesWith = collection;
    }

    /**
     * @param activeRelationshipToJuvenileList
     *            the activeRelationshipToJuvenileList to set
     */
    public void setActiveRelationshipToJuvenileList(Collection collection)
    {
	activeRelationshipToJuvenileList = collection;
    }

    /**
     * @param collection
     */
    public void setSexList(Collection collection)
    {
	sexList = collection;
    }

    /**
     * @param collection
     */
    public void setStateList(Collection collection)
    {
	stateList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitLevelList(Collection collection)
    {
	traitLevelList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitDescList(Collection collection)
    {
	traitDescList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitStatusList(Collection collection)
    {
	traitStatusList = collection;
    }

    /**
     * @return
     */
    public boolean isListsSet()
    {
	return listsSet;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b)
    {
	listsSet = b;
    }

    /**
     * @return
     */
    public Collection getInvolvementLevelList()
    {
	return involvementLevelList;
    }

    /**
     * @param collection
     */
    public void setInvolvementLevelList(Collection collection)
    {
	involvementLevelList = collection;
    }

    /**
     * @return
     */
    public Trait getCurrentTrait()
    {
	return currentTrait;
    }

    /**
     * @param trait
     */
    public void setCurrentTrait(Trait trait)
    {
	currentTrait = trait;
    }

    /**
     * @return
     */
    public Guardian getCurrentGuardian()
    {
	return currentGuardian;
    }

    /**
     * @param guardian
     */
    public void setCurrentGuardian(Guardian guardian)
    {
	currentGuardian = guardian;
    }

    public String getYouthLivesWithId()
    {
	return youthLivesWithId;
    }

    public void setYouthLivesWithId(String youthLivesWithId)
    {
	this.youthLivesWithId = youthLivesWithId;
    }

    public void clearConstMemberDeleteCheckBoxes(boolean defaultValue)
    {
	if (currentConstellation != null && currentConstellation.getMemberList() != null)
	{
	    Collection myMembers = currentConstellation.getMemberList();
	    MemberList currentMember = null;
	    Iterator iter = myMembers.iterator();
	    while (iter.hasNext())
	    {
		currentMember = (MemberList) iter.next();
		currentMember.setDelete(defaultValue);
	    }
	}
    }

    public void clearConstMemberDeceasedCheckBoxes(boolean defaultValue)
    {
	if (currentConstellation != null && currentConstellation.getMemberList() != null)
	{
	    Collection myMembers = currentConstellation.getMemberList();
	    MemberList currentMember = null;
	    Iterator iter = myMembers.iterator();
	    while (iter.hasNext())
	    {
		currentMember = (MemberList) iter.next();
		currentMember.setDeceased(defaultValue);
	    }
	}
    }

    public void clearConstMemberGuardianCheckBoxes(boolean defaultValue)
    {
	if (currentConstellation != null && currentConstellation.getMemberList() != null)
	{
	    Collection myMembers = currentConstellation.getMemberList();
	    MemberList currentMember = null;
	    Iterator iter = myMembers.iterator();
	    while (iter.hasNext())
	    {
		currentMember = (MemberList) iter.next();
		currentMember.setGuardian(defaultValue);
	    }
	}
    }

    public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
    {
	Object obj = request.getAttribute("clearConstMemberDeleteCheckBoxesFalse");
	if (obj != null)
	{
	    clearConstMemberDeleteCheckBoxes(false);
	}
	obj = null;
	obj = request.getParameter("clearConstMemberDeleteCheckBoxesFalse");
	if (obj != null)
	{
	    clearConstMemberDeleteCheckBoxes(false);
	}

	obj = request.getAttribute("clearConstMemberDeleteCheckBoxesTrue");
	if (obj != null)
	{
	    clearConstMemberDeleteCheckBoxes(true);
	}
	obj = null;
	obj = request.getParameter("clearConstMemberDeleteCheckBoxesTrue");
	if (obj != null)
	{
	    clearConstMemberDeleteCheckBoxes(true);
	}

	obj = request.getAttribute("clearConstMemberDeceasedCheckBoxes");
	if (obj != null)
	{
	    clearConstMemberDeceasedCheckBoxes(false);
	}
	obj = null;
	obj = request.getParameter("clearConstMemberDeceasedCheckBoxes");
	if (obj != null)
	{
	    clearConstMemberDeceasedCheckBoxes(false);
	}

	obj = request.getAttribute("clearConstMemberGuardianCheckBoxes");
	if (obj != null)
	{
	    clearConstMemberGuardianCheckBoxes(false);
	}
	obj = null;
	obj = request.getParameter("clearConstMemberGuardianCheckBoxes");
	if (obj != null)
	{
	    clearConstMemberGuardianCheckBoxes(false);
	}

    }

    /**
     * @return
     */
    public String getSecondaryAction()
    {
	return secondaryAction;
    }

    /**
     * @param string
     */
    public void setSecondaryAction(String string)
    {
	secondaryAction = string;
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

    /**
     * @return
     */
    public String getNextGuardian()
    {
	return nextGuardian;
    }

    /**
     * @param string
     */
    public void setNextGuardian(String string)
    {
	nextGuardian = string;
    }

    /**
     * @return
     */
    public boolean isHasActiveConstellation()
    {
	return hasActiveConstellation;
    }

    /**
     * @param b
     */
    public void setHasActiveConstellation(boolean b)
    {
	hasActiveConstellation = b;
    }

    /**
     * @return Returns the currentFamilyNumber.
     */
    public String getCurrentFamilyNumber()
    {
	return currentFamilyNumber;
    }

    /**
     * @param currentFamilyNumber
     *            The currentFamilyNumber to set.
     */
    public void setCurrentFamilyNumber(String currentFamilyNumber)
    {
	this.currentFamilyNumber = currentFamilyNumber;
    }

    /**
     * @return Returns the currentActiveConstellation.
     */
    public Constellation getCurrentActiveConstellation()
    {
	return currentActiveConstellation;
    }

    /**
     * @param currentActiveConstellation
     *            The currentActiveConstellation to set.
     */
    public void setCurrentActiveConstellation(Constellation currentActiveConstellation)
    {
	this.currentActiveConstellation = currentActiveConstellation;
    }

    /**
     * @return the primayContactMemberNumber
     */
    public String getPrimayContactMemberNumber()
    {
	return primayContactMemberNumber;
    }

    /**
     * @param primayContactMemberNumber
     *            the primayContactMemberNumber to set
     */
    public void setPrimayContactMemberNumber(String primayContactMemberNumber)
    {
	this.primayContactMemberNumber = primayContactMemberNumber;
    }

    public int getTotalDetentionVisits()
    {
	return totalDetentionVisits;
    }

    public void setTotalDetentionVisits(int totalDetentionVisits)
    {
	this.totalDetentionVisits = totalDetentionVisits;
    }

    public boolean isDaVisit()
    {
	return daVisit;
    }

    public void setDaVisit(boolean daVisit)
    {
	this.daVisit = daVisit;
    }

    public boolean isVisitorCapRemoved()
    {
	return visitorCapRemoved;
    }

    public void setVisitorCapRemoved(boolean visitorCapRemoved)
    {
	this.visitorCapRemoved = visitorCapRemoved;
    }

    public String getYouthLivesWithDesc()
    {
	return CodeHelper.getCodeDescription(PDCodeTableConstants.YOUTH_LIVES_WITH, youthLivesWithId);
    }

    public void setYouthLivesWithDesc(String youthLivesWithDesc)
    {
	this.youthLivesWithDesc = youthLivesWithDesc;
    }
    
    public boolean getYouthHasConstellation()
    {
	 return this.youthHasConstellation;
    }

    public void setYouthHasConstellation(boolean youthHasConstellation)
    {
	this.youthHasConstellation = youthHasConstellation;
    }

    // ************************************************************
    // *                     BEGIN TRAIT CLASS
    // ************************************************************    
    public static class Trait implements Comparable
    {
	private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);

	private String traitId = "";

	private String traitDesc = "";

	private String traitStatus = "";

	private String traitStatusId = "";

	private String traitLevelId = "";

	private String traitLevel = "";

	private String traitComments = "";

	private String traitDescId = "";

	public int compareTo(Object obj) throws ClassCastException
	{
	    if (obj == null)
		return -1;
	    Trait evt = (Trait) obj;
	    int result = 0;
	    try
	    {
		if (this.entryDate != null || evt.getEntryDate() != null)
		{
		    if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
			return 1; // this makes any null objects go to
		    // the bottom change this to -1 if you
		    // want the top of the list
		    if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
			return -1; // this makes any null objects go to
		    // the bottom change this to 1 if you
		    // want the top of the list
		    Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
		    Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
		    if (currDate == null)
			return -1;
		    if (incomingDate == null)
			return 1;
		    // backwards in order to get list to show up most recent first
		    result = incomingDate.compareTo(currDate);
		}
	    }
	    catch (NumberFormatException e)
	    {
		result = 0;
	    }

	    return result;
	}

	/**
	 * @return
	 */
	public String getEntryDate()
	{
	    return entryDate;
	}

	/**
	 * @return
	 */
	public String getTraitDesc()
	{
	    return traitDesc;
	}

	/**
	 * @return
	 */
	public String getTraitId()
	{
	    return traitId;
	}

	/**
	 * @return
	 */
	public String getTraitLevel()
	{
	    return traitLevel;
	}

	/**
	 * @return
	 */
	public String getTraitLevelId()
	{
	    return traitLevelId;
	}

	/**
	 * @return
	 */
	public String getTraitStatus()
	{
	    return traitStatus;
	}

	/**
	 * @return
	 */
	public String getTraitStatusId()
	{
	    return traitStatusId;
	}

	/**
	 * @param string
	 */
	public void setEntryDate(String string)
	{
	    entryDate = string;
	}

	/**
	 * @param string
	 */
	public void setTraitDesc(String string)
	{
	    traitDesc = string;
	}

	/**
	 * @param string
	 */
	public void setTraitId(String string)
	{
	    traitId = string;

	}

	/**
	 * @param string
	 */
	public void setTraitLevel(String string)
	{
	    traitLevel = string;
	}

	/**
	 * @param string
	 */
	public void setTraitLevelId(String string)
	{
	    traitLevelId = string;
	    if (traitLevelId == null || traitLevelId.equals(""))
	    {
		traitLevel = "";
		return;
	    }
	    if (JuvenileFamilyForm.traitLevelList != null && JuvenileFamilyForm.traitLevelList.size() > 0)
	    {
		traitLevel = CodeHelper.getCodeDescription(PDCodeTableConstants.FAMILY_TRAIT_LEVEL, traitLevelId); //bug fix
	    }
	}

	/**
	 * @param string
	 */
	public void setTraitStatus(String string)
	{
	    traitStatus = string;
	}

	/**
	 * @param string
	 */
	public void setTraitStatusId(String string)
	{
	    traitStatusId = string;
	    if (traitStatusId == null || traitStatusId.equals(""))
	    {
		traitStatus = "";
		return;
	    }
	    if (JuvenileFamilyForm.traitStatusList != null && JuvenileFamilyForm.traitStatusList.size() > 0)
	    {
		traitStatus = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.traitStatusList, traitStatusId);
	    }
	}

	/**
	 * @return
	 */
	public String getTraitComments()
	{
	    return traitComments;
	}

	/**
	 * @param string
	 */
	public void setTraitComments(String string)
	{
	    traitComments = string;
	}

	/**
	 * @return
	 */
	public String getMemberTraitDescId()
	{
	    return traitDescId;

	}

	/**
	 * @param string
	 */
	public void setMemberTraitDescId(String string)
	{
	    traitDescId = string;
	    if (traitDescId == null || traitDescId.equals(""))
	    {
		traitDesc = "";
		return;
	    }

	    if (JuvenileMemberForm.traitDescList != null && JuvenileMemberForm.traitDescList.size() > 0)
	    {
		traitDesc = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.traitDescList, traitDescId);
	    }

	}

	/**
	 * @return
	 */
	public String getTraitDescId()
	{
	    return traitDescId;

	}

	/**
	 * @param string
	 */
	public void setTraitDescId(String string)
	{
	    traitDescId = string;
	    if (traitDescId == null || traitDescId.equals(""))
	    {
		traitDesc = "";
		return;
	    }

	    if (JuvenileFamilyForm.traitDescList != null && JuvenileFamilyForm.traitDescList.size() > 0)
	    {
		traitDesc = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.traitDescList, traitDescId);
	    }

	}

    }

    // ************************************************************
    // *                     END TRAIT CLASS
    // ************************************************************ 

    // ************************************************************
    // *                 BEGIN MEMBERLIST CLASS
    // ************************************************************    
    public static class MemberList implements Comparable
    {
	private String memberNumber = "";

	private Name memberName = new Name();

	private String relationshipToJuv = "";

	private String relationshipToJuvId = "";

	private String youthLivesWithId = "";

	private boolean deceased = false;

	private boolean incarcerated = false;

	private SocialSecurity socialSecurity = new SocialSecurity("");

	private boolean guardian = false;

	private boolean primaryCareGiver = false; // ER Changes 11063

	private boolean update = false;

	private boolean delete = false;

	private String familyConstellationMemberNum = "";

	private String inHomeStatusAsStr = "";

	private boolean inHomeStatus = false;

	private String involvementLevelId;

	private String involvementLevel;

	private String dateOfBirth = "";

	private String driverLicenseNum;
	private String driverLicenseState = "";
	private String driverLicenseStateId = "";

	private String stateIssuedIdNum;
	private String stateIssuedIdState = "";
	private String stateIssuedIdStateId = "";
	private String primaryCareGiverAsStr;
	private String detentionHearingAsStr;
	private boolean detentionHearing;
	private String detentionVisitationAsStr;
	private boolean detentionVisitation;

	private MemberAddress currentAddress = new MemberAddress();

	private String suspiciousMember;

	private String parentalRightsTerminatedAsStr;

	private boolean parentalRightsTerminated;

	private String primaryContactAsStr;

	private boolean primaryContact;

	//added for User Story 27023
	private Date confirmedDate;

	//added for User Story 43892
	private boolean over21 = false;

	//US 39892
	private SocialSecurity completeSSN = new SocialSecurity("");

	public MemberList()
	{
	    memberNumber = "";
	    memberName = new Name();
	    inHomeStatusAsStr = "";
	    relationshipToJuv = "";
	    relationshipToJuvId = "";
	    deceased = false;
	    incarcerated = false;
	    socialSecurity = new SocialSecurity("");
	    guardian = false;
	    update = false;
	    delete = false;
	    familyConstellationMemberNum = "";
	    currentAddress = new MemberAddress();
	    suspiciousMember = "";
	    dateOfBirth = "";
	    driverLicenseNum = "";
	    driverLicenseStateId = "";
	    driverLicenseState = "";
	    stateIssuedIdState = "";
	    stateIssuedIdStateId = "";
	    stateIssuedIdNum = "";
	    parentalRightsTerminatedAsStr = "";
	    detentionHearingAsStr = "";
	    primaryCareGiverAsStr = "";
	    detentionVisitationAsStr = "";
	    primaryContactAsStr = "";
	    primaryContact = false;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
	    MemberList evt = (MemberList) obj;
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
	public boolean isDeceased()
	{
	    return deceased;
	}

	/**
	 * @return
	 */
	public boolean isIncarcerated()
	{
	    return incarcerated;
	}

	public String getDeceasedYesNo()
	{
	    if (this.isDeceased())
		return "YES";
	    else
		return "NO";
	}

	public String getIncarceratedYesNo()
	{
	    if (this.isIncarcerated())
		return "YES";
	    else
		return "NO";
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
	public boolean isGuardian()
	{
	    return guardian;
	}

	public String getGuardianYesNo()
	{
	    if (this.isGuardian())
		return "YES";
	    else
		return "NO";
	}

	/**
	 * @return
	 */
	public Name getMemberName()
	{
	    return memberName;
	}

	public String getPrimaryGuardianYesNo()
	{
	    if (this.isGuardian() && this.isPrimaryContact())
		return "YES";
	    else
		return "NO";
	}

	/**
	 * @return
	 */
	public String getMemberNumber()
	{
	    return memberNumber;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuv()
	{
	    return relationshipToJuv;
	}

	/**
	 * @return
	 */
	public SocialSecurity getSocialSecurity()
	{
	    return socialSecurity;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
	    return update;
	}

	/**
	 * @param b
	 */
	public void setDeceased(boolean b)
	{
	    deceased = b;
	}

	/**
	 * @param b
	 */
	public void setIncarcerated(boolean b)
	{
	    incarcerated = b;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
	    delete = b;
	}

	/**
	 * @param b
	 */
	public void setGuardian(boolean b)
	{
	    guardian = b;
	}

	/**
	 * @param name
	 */
	public void setMemberName(Name name)
	{
	    memberName = name;
	}

	/**
	 * @param string
	 */
	public void setMemberNumber(String string)
	{
	    memberNumber = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuv(String string)
	{
	    relationshipToJuv = string;
	}

	/**
	 * @param security
	 */
	public void setSocialSecurity(SocialSecurity security)
	{
	    socialSecurity = security;
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
	public String getRelationshipToJuvId()
	{
	    return relationshipToJuvId;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvId(String string)
	{
	    relationshipToJuvId = string;
	    if (relationshipToJuvId == null || relationshipToJuvId.equals(""))
	    {
		relationshipToJuv = "";
		return;
	    }
	    else
	    {
		relationshipToJuv = CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE, relationshipToJuvId);
	    }
	}

	/**
	 * @param string
	 */
	public void setFamilyConstellationMemberNum(String string)
	{

	    familyConstellationMemberNum = string;
	}

	/**
	 * @return
	 */
	public String getFamilyConstellationMemberNum()
	{
	    return familyConstellationMemberNum;
	}

	/**
	 * @return
	 */
	public boolean isInHomeStatus()
	{
	    return inHomeStatus;
	}

	/**
	 * @return
	 */
	public String getInvolvementLevel()
	{
	    return involvementLevel;
	}

	/**
	 * @return
	 */
	public String getInvolvementLevelId()
	{
	    return involvementLevelId;
	}

	/**
	 * @param b
	 */
	public void setInHomeStatus(boolean b)
	{
	    inHomeStatus = b;
	    inHomeStatusAsStr = Boolean.toString(inHomeStatus);

	}

	/**
	 * @param string
	 */
	public void setInvolvementLevel(String string)
	{
	    involvementLevel = string;
	}

	/**
	 * @param string
	 */
	public void setInvolvementLevelId(String string)
	{
	    involvementLevelId = string;
	    if (involvementLevelId == null || involvementLevelId.equals(""))
	    {
		involvementLevel = "";
		return;
	    }
	    if (JuvenileFamilyForm.involvementLevelList != null && JuvenileFamilyForm.involvementLevelList.size() > 0)
	    {
		involvementLevel = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.involvementLevelList, involvementLevelId);
	    }
	}

	/**
	 * @return
	 */
	public MemberAddress getCurrentAddress()
	{
	    return currentAddress;
	}

	/**
	 * @param address
	 */
	public void setCurrentAddress(MemberAddress address)
	{
	    currentAddress = address;
	}

	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
	    return dateOfBirth;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
	    dateOfBirth = string;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseNum()
	{
	    return driverLicenseNum;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseNum(String string)
	{
	    driverLicenseNum = string;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseState()
	{
	    return driverLicenseState;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseStateId()
	{
	    return driverLicenseStateId;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseState(String string)
	{
	    driverLicenseState = string;
	}

	public String getYouthLivesWithId()
	{
	    return youthLivesWithId;
	}

	public void setYouthLivesWithId(String youthLivesWithId)
	{
	    this.youthLivesWithId = youthLivesWithId;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseStateId(String string)
	{
	    driverLicenseStateId = string;
	    if (driverLicenseStateId == null || driverLicenseStateId.equals(""))
	    {
		driverLicenseState = "";
		return;
	    }
	    if (JuvenileFamilyForm.stateList != null && JuvenileFamilyForm.stateList.size() > 0)
	    {
		driverLicenseState = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.stateList, driverLicenseStateId);
	    }
	}

	/**
	 * @return
	 */
	public String getStateIssuedIdState()
	{
	    return stateIssuedIdState;
	}

	/**
	 * @return
	 */
	public String getStateIssuedIdStateId()
	{
	    return stateIssuedIdStateId;
	}

	/**
	 * @param string
	 */
	public void setStateIssuedIdState(String string)
	{
	    stateIssuedIdState = string;
	}

	/**
	 * @param string
	 */
	public void setStateIssuedIdStateId(String string)
	{
	    stateIssuedIdStateId = string;
	    if (stateIssuedIdStateId == null || stateIssuedIdStateId.equals(""))
	    {
		stateIssuedIdState = "";
		return;
	    }
	    if (JuvenileFamilyForm.stateList != null && JuvenileFamilyForm.stateList.size() > 0)
	    {
		stateIssuedIdState = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.stateList, stateIssuedIdStateId);
	    }
	}

	/**
	 * @return
	 */
	public String getStateIssuedIdNum()
	{
	    return stateIssuedIdNum;
	}

	/**
	 * @param string
	 */
	public void setStateIssuedIdNum(String string)
	{
	    stateIssuedIdNum = string;
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
	 * @return Returns the inHomeStatusAsStr.
	 */
	public String getInHomeStatusAsStr()
	{
	    return inHomeStatusAsStr;
	}

	/**
	 * @param inHomeStatusAsStr
	 *            The inHomeStatusAsStr to set.
	 */
	public void setInHomeStatusAsStr(String aInHomeStatusAsStr)
	{
	    setInHomeStatus(false);
	    this.inHomeStatusAsStr = aInHomeStatusAsStr;
	    if (inHomeStatusAsStr != null && !inHomeStatusAsStr.equals(""))
	    {
		inHomeStatus = (Boolean.valueOf(inHomeStatusAsStr).booleanValue());
	    }
	}

	/**
	 * @return the parentalRightsTerminatedAsStr
	 */
	public String getParentalRightsTerminatedAsStr()
	{
	    return parentalRightsTerminatedAsStr;
	}

	/**
	 * @param parentalRightsTerminatedAsStr
	 *            the parentalRightsTerminatedAsStr to set
	 */
	public void setParentalRightsTerminatedAsStr(String aParentalRightsTerminatedAsStr)
	{
	    //			setParentalRightsTerminated(false);
	    this.parentalRightsTerminatedAsStr = aParentalRightsTerminatedAsStr;
	    if (parentalRightsTerminatedAsStr != null && !parentalRightsTerminatedAsStr.equals(""))
	    {
		parentalRightsTerminated = (Boolean.valueOf(parentalRightsTerminatedAsStr).booleanValue());
	    }
	}

	/**
	 * @return the parentalRightsTerminated
	 */
	public boolean isParentalRightsTerminated()
	{
	    return parentalRightsTerminated;
	}

	/**
	 * @param parentalRightsTerminated
	 *            the parentalRightsTerminated to set
	 */
	public void setParentalRightsTerminated(boolean b)
	{

	    parentalRightsTerminated = b;
	    parentalRightsTerminatedAsStr = Boolean.toString(parentalRightsTerminated);
	}

	/**
	 * @return the detentionHearingAsStr
	 */
	public String getDetentionHearingAsStr()
	{
	    return detentionHearingAsStr;
	}

	/**
	 * @param detentionHearingAsStr
	 *            the detentionHearingAsStr to set
	 */
	public void setDetentionHearingAsStr(String aDetentionHearingAsStr)
	{
	    this.detentionHearingAsStr = aDetentionHearingAsStr;
	    if (detentionHearingAsStr != null && !detentionHearingAsStr.equals(""))
	    {
		detentionHearing = (Boolean.valueOf(detentionHearingAsStr).booleanValue());
	    }
	}

	/**
	 * @return the detentionHearing
	 */
	public boolean isDetentionHearing()
	{
	    return detentionHearing;
	}

	/**
	 * @param detentionHearing
	 *            the detentionHearing to set
	 */
	public void setDetentionHearing(boolean b)
	{

	    detentionHearing = b;
	    detentionHearingAsStr = Boolean.toString(detentionHearing);
	}

	/**
	 * @return the detentionVisitationAsStr
	 */
	public String getDetentionVisitationAsStr()
	{
	    return detentionVisitationAsStr;
	}

	/**
	 * @param detentionVisitationAsStr
	 *            the detentionVisitationAsStr to set
	 */
	public void setDetentionVisitationAsStr(String aDetentionVisitationAsStr)
	{
	    this.detentionVisitationAsStr = aDetentionVisitationAsStr;
	    if (detentionVisitationAsStr != null && !detentionVisitationAsStr.equals(""))
	    {
		detentionVisitation = (Boolean.valueOf(detentionVisitationAsStr).booleanValue());
	    }
	}

	/**
	 * @return the detentionVisitation
	 */
	public boolean isDetentionVisitation()
	{
	    return detentionVisitation;
	}

	/**
	 * @param detentionVisitation
	 *            the detentionVisitation to set
	 */
	public void setDetentionVisitation(boolean b)
	{

	    detentionVisitation = b;
	    detentionVisitationAsStr = Boolean.toString(detentionVisitation);
	}

	/**
	 * @return the primaryContactAsStr
	 */
	public String getPrimaryContactAsStr()
	{
	    return primaryContactAsStr;
	}

	/**
	 * @param primaryContactAsStr
	 *            the primaryContactAsStr to set
	 */
	public void setPrimaryContactAsStr(String aPrimaryContactAsStr)
	{
	    this.primaryContactAsStr = aPrimaryContactAsStr;
	    if (primaryContactAsStr != null && !primaryContactAsStr.equals(""))
	    {
		primaryContact = (Boolean.valueOf(primaryContactAsStr).booleanValue());
	    }
	}

	/**
	 * @return the primaryContact
	 */
	public boolean isPrimaryContact()
	{
	    return primaryContact;
	}

	/**
	 * @param primaryContact
	 *            the primaryContact to set
	 */
	public void setPrimaryContact(boolean b)
	{

	    primaryContact = b;
	    primaryContactAsStr = Boolean.toString(primaryContact);
	}

	public boolean isPrimaryCareGiver()
	{
	    return primaryCareGiver;
	}

	public void setPrimaryCareGiver(boolean b)
	{
	    primaryCareGiver = b;
	    primaryCareGiverAsStr = Boolean.toString(primaryCareGiver);
	}

	public String getPrimaryCareGiverAsStr()
	{
	    return primaryCareGiverAsStr;
	}

	public void setPrimaryCareGiverAsStr(String asPrimaryCareGiverAsStr)
	{
	    this.primaryCareGiverAsStr = asPrimaryCareGiverAsStr;
	    if (primaryCareGiverAsStr != null && !primaryCareGiverAsStr.equals(""))
	    {
		primaryCareGiver = (Boolean.valueOf(primaryCareGiverAsStr).booleanValue());
	    }
	}

	public Date getConfirmedDate()
	{
	    return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate)
	{
	    this.confirmedDate = confirmedDate;
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

    // ************************************************************
    // *                 END MEMBERLIST CLASS
    // ************************************************************   

    // ************************************************************
    // *                 BEGIN GUARDIAN CLASS 
    // ************************************************************
    public static class Guardian implements Comparable
    {
	private String memberNumber = "";
	private Name name = new Name();
	private Name juvenileName = new Name();
	private String primaryContact = "";

	private String relationshipToJuv = "";
	private String relationshipToJuvId = "";
	private String deceased = "";
	private String incarcerated = "";
	private String constellationMemberId = "";
	private String jobTitle = ""; // removed at request of ER on

	// 04/25/2006 then added back in
	// at request of defect 35650

	private String employerName = "";
	private String annualNetIncome = "";
	private String tanfAfdc = "";
	private String foodStamps = "";
	private String rentExpenses = "";
	private String utilitiesExpenses = "";
	private String groceryExpenses = "";
	private String ssi = "";
	private String lifeInsurancePremium = "";
	private String medicalExpenses = "";
	private String schoolExpenses = "";
	private String totalExpenses = "";
	private String propertyValue = "";
	private String intangibleValue = "";
	private String savings = "";
	private String otherIncome = "";
	private String otherAdultIncome = "";
	private String childSupportPaid = "";
	private String childSupportReceived = "";
	private String notes = "";
	private String numberLivingInHome = "";
	private String numberOfDependents = "";
	private String numberInFamily = "";

	private Name childSupportPayorName = new Name();

	private boolean active = false;
	private boolean update = false;
	private boolean delete = false;

	private String financialId = "";
	private String entryDate = "";
	private String timeStampEntryDate = "";
	private String consRelationId = ""; // dont know where this came

	// from but added because PD
	// needs it

	private JuvenileMemberForm.MemberEmployment employmentInfo = null;

	private Collection employmentInfoList = null;

	//used in Request Attorney Appointment since 
	//the use case is only interested in guardian's financial info 
	//of those guardian(s) living with the juvenile
	private boolean inHomeStatus = false;

	//used in Request Attorney Appointment 
	//We let the user choose which one should be considered as the official one 
	//for the particular constellation
	private boolean financialInfoSelected = false;

	private boolean primaryCareGiver;

	//added for US 27023
	private String champus;
	private String VABenefits;
	private String countyPaidFC;

	public int compareTo(Object obj) throws ClassCastException
	{
	    if (obj == null)
		return -1;
	    Guardian evt = (Guardian) obj;
	    int result = 0;
	    try
	    {
		if (this.entryDate != null || evt.getEntryDate() != null)
		{
		    if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
			return 1; // this makes any null objects go to
		    // the bottom change this to -1 if you
		    // want the top of the list
		    if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
			return -1; // this makes any null objects go to
		    // the bottom change this to 1 if you
		    // want the top of the list
		    Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
		    Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
		    if (currDate == null)
			return -1;
		    if (incomingDate == null)
			return 1;
		    result = incomingDate.compareTo(currDate); // backwards
		    // in order to get list to show
		    // most recent first
		}

	    }
	    catch (NumberFormatException e)
	    {
		result = 0;
	    }

	    return result;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvId()
	{
	    return relationshipToJuvId;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvId(String string)
	{
	    relationshipToJuvId = string;
	    relationshipToJuv = "";
	    if (relationshipToJuvId == null || relationshipToJuvId.equals(""))
	    {
		return;
	    }
	    if (JuvenileFamilyForm.relationshipToJuvenileList != null && JuvenileFamilyForm.relationshipToJuvenileList.size() > 0)
	    {
		relationshipToJuv = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.relationshipToJuvenileList, relationshipToJuvId);
	    }
	    if (relationshipToJuv == "")
	    {
		if (JuvenileFamilyForm.activeRelationshipToJuvenileList != null && JuvenileFamilyForm.activeRelationshipToJuvenileList.size() > 0)
		{
		    relationshipToJuv = CodeHelper.getCodeDescriptionByCode(JuvenileFamilyForm.activeRelationshipToJuvenileList, relationshipToJuvId);
		}
	    }
	}

	/**
	 * @return
	 */
	public boolean isActive()
	{
	    return active;
	}

	/**
	 * @return
	 */
	public String getAnnualNetIncome()
	{
	    return annualNetIncome;
	}

	/**
	 * @return
	 */
	public Name getChildSupportPayorName()
	{
	    return childSupportPayorName;
	}

	/**
	 * @return
	 */
	public String getConstellationMemberId()
	{
	    return constellationMemberId;
	}

	/**
	 * @return
	 */
	public String getDeceased()
	{
	    return deceased;
	}

	/**
	 * @return
	 */
	public String getIncarcerated()
	{
	    return incarcerated;
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
	public String getFoodStamps()
	{
	    return foodStamps;
	}

	/**
	 * @return
	 */
	public String getGroceryExpenses()
	{
	    return groceryExpenses;
	}

	/**
	 * @return
	 */
	public String getIntangibleValue()
	{
	    return intangibleValue;
	}

	/**
	 * @return
	 */
	//		public String getJobTitle()
	//		{
	//			return jobTitle;
	//		}
	/**
	 * @return
	 */
	public String getLifeInsurancePremium()
	{
	    return lifeInsurancePremium;
	}

	/**
	 * @return
	 */
	public String getMedicalExpenses()
	{
	    return medicalExpenses;
	}

	/**
	 * @return
	 */
	public String getMemberNumber()
	{
	    return memberNumber;
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
	public String getNumberLivingInHome()
	{
	    return numberLivingInHome;
	}

	/**
	 * @return
	 */
	public String getNumberOfDependents()
	{
	    return numberOfDependents;
	}

	/**
	 * @return
	 */
	public String getOtherIncome()
	{
	    return otherIncome;
	}

	/**
	 * @return
	 */
	public String getPropertyValue()
	{
	    return propertyValue;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuv()
	{
	    return relationshipToJuv;
	}

	/**
	 * @return
	 */
	public String getRentExpenses()
	{
	    return rentExpenses;
	}

	/**
	 * @return
	 */
	public String getSavings()
	{
	    return savings;
	}

	/**
	 * @return the champus
	 */
	public String getChampus()
	{
	    return champus;
	}

	/**
	 * @param champus
	 *            the champus to set
	 */
	public void setChampus(String champus)
	{
	    this.champus = champus;
	}

	/**
	 * @return the vABenefits
	 */
	public String getVABenefits()
	{
	    return VABenefits;
	}

	/**
	 * @param vABenefits
	 *            the vABenefits to set
	 */
	public void setVABenefits(String vABenefits)
	{
	    VABenefits = vABenefits;
	}

	/**
	 * @return the countyPaidFC
	 */
	public String getCountyPaidFC()
	{
	    return countyPaidFC;
	}

	/**
	 * @param countyPaidFC
	 *            the countyPaidFC to set
	 */
	public void setCountyPaidFC(String countyPaidFC)
	{
	    this.countyPaidFC = countyPaidFC;
	}

	/**
	 * @return
	 */
	public String getSchoolExpenses()
	{
	    return schoolExpenses;
	}

	/**
	 * @return
	 */
	public String getTanfAfdc()
	{
	    return tanfAfdc;
	}

	/**
	 * @return
	 */
	/*
	 * public String getTotalExpenses() { return totalExpenses; }
	 */
	/**
	 * @return
	 */
	public String getTotalExpenses()
	{
	    double totalExpenses = UIUtil.getDoubleFromString(utilitiesExpenses) + UIUtil.getDoubleFromString(lifeInsurancePremium) + UIUtil.getDoubleFromString(schoolExpenses) + UIUtil.getDoubleFromString(rentExpenses) + UIUtil.getDoubleFromString(groceryExpenses) + UIUtil.getDoubleFromString(medicalExpenses) + UIUtil.getDoubleFromString(childSupportPaid);

	    return UIUtil.formatCurrency(Double.toString(totalExpenses), null, true, "");
	}

	public double getTotalAnnualExpenses()
	{
	    double totalMonthlyExpenses = UIUtil.getDoubleFromString(utilitiesExpenses) + UIUtil.getDoubleFromString(lifeInsurancePremium) + UIUtil.getDoubleFromString(schoolExpenses) + UIUtil.getDoubleFromString(rentExpenses) + UIUtil.getDoubleFromString(groceryExpenses) + UIUtil.getDoubleFromString(medicalExpenses) + UIUtil.getDoubleFromString(childSupportPaid);
	    return totalMonthlyExpenses * 12;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
	    return update;
	}

	/**
	 * @return
	 */
	public String getUtilitiesExpenses()
	{
	    return utilitiesExpenses;
	}

	/**
	 * @param b
	 */
	public void setActive(boolean b)
	{
	    active = b;
	}

	/**
	 * @param string
	 */
	public void setAnnualNetIncome(String string)
	{
	    annualNetIncome = string;
	}

	/**
	 * @param name
	 */
	public void setChildSupportPayorName(Name name)
	{
	    childSupportPayorName = name;
	}

	/**
	 * @param string
	 */
	public void setConstellationMemberId(String string)
	{
	    constellationMemberId = string;
	}

	/**
	 * @param string
	 */
	public void setIncarcerated(String string)
	{
	    incarcerated = string;
	}

	/**
	 * @param string
	 */
	public void setDeceased(String string)
	{
	    deceased = string;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
	    delete = b;
	}

	/**
	 * @param b
	 */
	public void setFoodStamps(String string)
	{
	    foodStamps = string;
	}

	/**
	 * @param string
	 */
	public void setGroceryExpenses(String string)
	{
	    groceryExpenses = string;
	}

	/**
	 * @param string
	 */
	public void setIntangibleValue(String string)
	{
	    intangibleValue = string;
	}

	/**
	 * @param string
	 */
	//		public void setJobTitle(String string)
	//		{
	//			jobTitle = string;
	//		}
	/**
	 * @param string
	 */
	public void setLifeInsurancePremium(String string)
	{
	    lifeInsurancePremium = string;
	}

	/**
	 * @param string
	 */
	public void setMedicalExpenses(String string)
	{
	    medicalExpenses = string;
	}

	/**
	 * @param string
	 */
	public void setMemberNumber(String string)
	{
	    memberNumber = string;
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
	public void setNumberLivingInHome(String string)
	{
	    numberLivingInHome = string;
	}

	/**
	 * @param string
	 */
	public void setNumberOfDependents(String string)
	{
	    numberOfDependents = string;
	}

	/**
	 * @param string
	 */
	public void setOtherIncome(String string)
	{
	    otherIncome = string;
	}

	/**
	 * @param string
	 */
	public void setPropertyValue(String string)
	{
	    propertyValue = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuv(String string)
	{
	    relationshipToJuv = string;
	}

	/**
	 * @param string
	 */
	public void setRentExpenses(String string)
	{
	    rentExpenses = string;
	}

	/**
	 * @param string
	 */
	public void setSavings(String string)
	{
	    savings = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolExpenses(String string)
	{
	    schoolExpenses = string;
	}

	/**
	 * @param string
	 */
	public void setTanfAfdc(String string)
	{
	    tanfAfdc = string;
	}

	/**
	 * @param string
	 */
	public void setTotalExpenses(String string)
	{
	    totalExpenses = string;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
	    update = b;
	}

	/**
	 * @param string
	 */
	public void setUtilitiesExpenses(String string)
	{
	    utilitiesExpenses = string;
	}

	/**
	 * @return
	 */
	public String getNumberInFamily()
	{
	    return numberInFamily;
	}

	/**
	 * @param string
	 */
	public void setNumberInFamily(String string)
	{
	    numberInFamily = string;
	}

	/**
	 * @return
	 */
	public String getFinancialId()
	{
	    return financialId;
	}

	/**
	 * @param string
	 */
	public void setFinancialId(String string)
	{
	    financialId = string;
	}

	/**
	 * @return
	 */
	public String getEntryDate()
	{
	    return entryDate;
	}

	/**
	 * @param string
	 */
	public void setEntryDate(String string)
	{
	    entryDate = string;
	}

	/**
	 * @return
	 */
	public Name getJuvenileName()
	{
	    return juvenileName;
	}

	/**
	 * @param name
	 */
	public void setJuvenileName(Name name)
	{
	    juvenileName = name;
	}

	/**
	 * @return
	 */
	public String getConsRelationId()
	{
	    return consRelationId;
	}

	/**
	 * @param string
	 */
	public void setConsRelationId(String string)
	{
	    consRelationId = string;
	}

	/**
	 * @return
	 */
	public String getTimeStampEntryDate()
	{
	    return timeStampEntryDate;
	}

	/**
	 * @param string
	 */
	public void setTimeStampEntryDate(String string)
	{
	    timeStampEntryDate = string;
	}

	/**
	 * @return
	 */
	public String getChildSupportPaid()
	{
	    return childSupportPaid;
	}

	/**
	 * @return
	 */
	public String getChildSupportReceived()
	{
	    return childSupportReceived;
	}

	/**
	 * @param string
	 */
	public void setChildSupportPaid(String string)
	{
	    childSupportPaid = string;
	}

	/**
	 * @param string
	 */
	public void setChildSupportReceived(String string)
	{
	    childSupportReceived = string;
	}

	/**
	 * @return
	 */
	public String getNotes()
	{
	    return notes;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
	    notes = string;
	}

	/**
	 * @return
	 */
	public String getOtherAdultIncome()
	{
	    return otherAdultIncome;
	}

	/**
	 * @param string
	 */
	public void setOtherAdultIncome(String string)
	{
	    otherAdultIncome = string;
	}

	/**
	 * @return Returns the employerName.
	 */
	public String getEmployerName()
	{
	    return employerName;
	}

	/**
	 * @param employerName
	 *            The employerName to set.
	 */
	public void setEmployerName(String employerName)
	{
	    this.employerName = employerName;
	}

	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle()
	{
	    return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle)
	{
	    this.jobTitle = jobTitle;
	}

	/**
	 * @return Returns the employmentHistory.
	 */
	public JuvenileMemberForm.MemberEmployment getEmploymentInfo()
	{
	    return employmentInfo;
	}

	/**
	 * @param employmentHistory
	 *            The employmentHistory to set.
	 */
	public void setEmploymentInfo(JuvenileMemberForm.MemberEmployment employmentHistory)
	{
	    this.employmentInfo = employmentHistory;
	}

	/**
	 * @return Returns the employments.
	 */
	public Collection getEmploymentInfoList()
	{
	    return employmentInfoList;
	}

	/**
	 * @param employments
	 *            The employments to set.
	 */
	public void setEmploymentInfoList(Collection employments)
	{
	    this.employmentInfoList = employments;
	}

	/**
	 * @return Returns the inHomeStatus.
	 */
	public boolean isInHomeStatus()
	{
	    return inHomeStatus;
	}

	/**
	 * @param inHomeStatus
	 *            The inHomeStatus to set.
	 */
	public void setInHomeStatus(boolean inHomeStatus)
	{
	    this.inHomeStatus = inHomeStatus;
	}

	/**
	 * @return Returns the isFinancialInfoSelected.
	 */
	public boolean isFinancialInfoSelected()
	{
	    return financialInfoSelected;
	}

	/**
	 * @param isFinancialInfoSelected
	 *            The isFinancialInfoSelected to set.
	 */
	public void setFinancialInfoSelected(boolean financialInfoSelected)
	{
	    this.financialInfoSelected = financialInfoSelected;
	}

	/**
	 * @return Returns the ssi.
	 */
	public String getSsi()
	{
	    return ssi;
	}

	/**
	 * @param ssi
	 *            The ssi to set.
	 */
	public void setSsi(String ssi)
	{
	    this.ssi = ssi;
	}

	/**
	 * @return the primaryContact
	 */
	public String getPrimaryContact()
	{
	    return primaryContact;
	}

	/**
	 * @param primaryContact
	 *            the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact)
	{
	    this.primaryContact = primaryContact;
	}

	public boolean isPrimaryCareGiver()
	{
	    return primaryCareGiver;
	}

	public void setPrimaryCareGiver(boolean primaryCareGiver)
	{
	    this.primaryCareGiver = primaryCareGiver;
	}

    }

    // *****************************************************
    // *              END GUARDIAN CLASS 
    // *****************************************************

    // *****************************************************
    // *         BEGIN CONSTELLATIONLIST CLASS 
    // *****************************************************
    public static class ConstellationList implements Comparable
    {
	private String familyNumber = "";

	private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);

	private String guardianNames = "";

	private boolean active = false;

	private boolean update = false;

	private boolean delete = false;

	public ConstellationList()
	{
	    familyNumber = "";
	    entryDate = "";
	    guardianNames = "";
	    active = false;
	    update = false;
	    delete = false;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
	    ConstellationList evt = (ConstellationList) obj;
	    int result = 0;
	    if (familyNumber != null && evt.getFamilyNumber() != null)
	    {
		try
		{
		    result = Integer.valueOf(familyNumber).compareTo(Integer.valueOf(evt.getFamilyNumber()));
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
	public boolean isActive()
	{
	    return active;
	}

	public String getActiveYesNo()
	{
	    if (this.isActive())
		return "YES";
	    else
		return "NO";
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
	public String getEntryDate()
	{
	    return entryDate;
	}

	/**
	 * @return
	 */
	public String getFamilyNumber()
	{
	    return familyNumber;
	}

	/**
	 * @return
	 */
	public String getGuardianNames()
	{
	    return guardianNames;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
	    return update;
	}

	/**
	 * @param b
	 */
	public void setActive(boolean b)
	{
	    active = b;
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
	public void setEntryDate(String string)
	{
	    entryDate = string;
	}

	/**
	 * @param string
	 */
	public void setFamilyNumber(String string)
	{
	    familyNumber = string;
	}

	/**
	 * @param string
	 */
	public void setGuardianNames(String string)
	{
	    guardianNames = string;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
	    update = b;
	}

    }

    // *****************************************************
    // *         END CONSTELLATIONLIST CLASS 
    // *****************************************************

    // *****************************************************
    // *         BEGIN CONSTELLATION CLASS 
    // *****************************************************
    public static class Constellation
    {

	private String familyNumber = "";

	private boolean active = false;

	private boolean update = false;

	private boolean delete = false;

	private int guardianSelected = 0;

	private Collection traitList = emptyColl;

	private Collection memberList = emptyColl;

	private Collection guardiansList = emptyColl;

	public Constellation()
	{
	    familyNumber = "";
	    active = false;
	    update = false;
	    delete = false;
	    traitList = new ArrayList();
	    memberList = new ArrayList();
	    guardiansList = new ArrayList();
	    //setFake();

	}

	private void setFake()
	{
	    familyNumber = "testFamNum";
	    MemberList test = new MemberList();
	    test.getMemberName().setFirstName("testFirstName");
	    test.getMemberName().setLastName("testLastName");
	    test.getMemberName().setMiddleName("testMiddleName");
	    test.setMemberNumber("testMemNum");
	    test.setRelationshipToJuv("testRelationship");
	    test.getSocialSecurity().setSSN("111223333");
	    memberList.add(test);
	    Trait temp = new Trait();
	    temp.setEntryDate("testEntryDate");
	    temp.setTraitDesc("testTraitDesc");
	    temp.setTraitLevel("testTraitLevel");
	    temp.setTraitStatus("testStatus");
	    traitDescList.add(temp);

	}

	/**
	 * @return
	 */
	public boolean isActive()
	{
	    return active;
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
	public String getFamilyNumber()
	{
	    return familyNumber;
	}

	/**
	 * @return
	 */
	public Collection getMemberList()
	{
	    return memberList;
	}

	/**
	 * @return
	 */
	public Collection getTraitList()
	{
	    return traitList;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
	    return update;
	}

	/**
	 * @param b
	 */
	public void setActive(boolean b)
	{
	    active = b;
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
	public void setFamilyNumber(String string)
	{
	    familyNumber = string;
	}

	/**
	 * @param collection
	 */
	public void setMemberList(Collection collection)
	{
	    memberList = collection;
	}

	/**
	 * @param collection
	 */
	public void setTraitList(Collection collection)
	{
	    traitList = collection;
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
	public int getGuardianSelected()
	{
	    return guardianSelected;
	}

	/**
	 * @return
	 */
	public Collection getGuardiansList()
	{
	    return guardiansList;
	}

	/**
	 * @param i
	 */
	public void setGuardianSelected(int i)
	{
	    guardianSelected = i;
	}

	/**
	 * @param collection
	 */
	public void setGuardiansList(Collection collection)
	{
	    guardiansList = collection;
	}

    }
    // ************************************************
    // *            END CONSTELLATION CLASS 
    // ************************************************

}// END_CLASS
