/*
 * Created on Aug 1, 2006
 *
 */
package ui.supervision.administercasenotes.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.ISocialSecurity;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;

/**
 * @author jjose
 * 
 */
public class SuperviseeSearchForm extends ActionForm
{

	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String fromPage = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	private String selectedCaseValue = "";

	private String searchBy="SPN";
	private String spn;
	private String superviseeId = "";
	private IName superviseeName;
	private String supervisionPeriod="A";
	private String supervisionPeriodId="";
	private Date dateOfBirth;
	private String raceId;
	private String race;
	private String sexId;
	private String sex;
	private ISocialSecurity ssn;
	private String dlStateId;
	private String dlState;
	private String dlNum;
	private String SID;
	private String FBI;
	private String CJIS;
	private Collection searchResults; // collection of BasicSupervisee Info
	private Collection searchSupPeriodsResults; // collection of SupervisionPeriod Info Objects
	private Collection activeCases;

	// Code Table Collections (make static)
	private static Collection raceList;
	private static Collection sexList;
	private static Collection dlStateList;
	private String caseNum;
	private String cdi;

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCdi() {
		return cdi;
	}

	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	public void clearAll()
	{
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";

		// Form specific fields
		spn = "";
		superviseeName = new Name();
		supervisionPeriod="A";
		dateOfBirth = null;
		raceId = "";
		race = "";
		sexId = "";
		sex = "";
		ssn = new SocialSecurity("");
		dlStateId = "";
		dlState = "";
		dlNum = "";
		SID = "";
		FBI = "";
		CJIS = "";
		searchResults = null;
		searchSupPeriodsResults=null;
		supervisionPeriodId="";
		activeCases = null;
		caseNum = "";
		cdi = "";
		searchBy="SPN";
		
	}

	public SuperviseeSearchForm() {
		setAllLists();
	}

	
	
	
	
	
	
	private void setAllLists()
	{
		//UICasenotesLoadCoadTables.getInstance().setSuperviseeSearchForm(this);
        sexList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SEX);
        raceList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.RACE);
        dlStateList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.STATE_ABBR);
	}

	/**
	 * @return Returns the dlStateList.
	 */
	public Collection getDlStateList()
	{
		if (dlStateList == null)
			setAllLists();
		return dlStateList;
	}

	/**
	 * @param dlStateList
	 *            The dlStateList to set.
	 */
	public void setDlStateList(Collection aDlStateList)
	{
		dlStateList = aDlStateList;
	}

	/**
	 * @return Returns the raceList.
	 */
	public Collection getRaceList()
	{
		if (raceList == null)
			setAllLists();
		return raceList;
	}

	/**
	 * @param raceList
	 *            The raceList to set.
	 */
	public void setRaceList(Collection aRaceList)
	{
		raceList = aRaceList;
	}

	/**
	 * @return Returns the sexList.
	 */
	public Collection getSexList()
	{
		if (sexList == null)
			setAllLists();
		return sexList;
	}

	/**
	 * @param sexList
	 *            The sexList to set.
	 */
	public void setSexList(Collection aSexList)
	{
		sexList = aSexList;
	}

	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl()
	{
		return emptyColl;
	}

	/**
	 * @param emptyColl
	 *            The emptyColl to set.
	 */
	public static void setEmptyColl(Collection emptyColl)
	{
		SuperviseeSearchForm.emptyColl = emptyColl;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return Returns the cJIS.
	 */
	public String getCJIS()
	{
		return CJIS;
	}

	/**
	 * @param cjis
	 *            The cJIS to set.
	 */
	public void setCJIS(String cjis)
	{
		CJIS = cjis;
	}

	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @param delete
	 *            The delete to set.
	 */
	public void setDelete(boolean delete)
	{
		this.delete = delete;
	}

	/**
	 * @return Returns the dlNum.
	 */
	public String getDlNum()
	{
		return dlNum;
	}

	/**
	 * @param dlNum
	 *            The dlNum to set.
	 */
	public void setDlNum(String dlNum)
	{
		this.dlNum = dlNum;
	}

	/**
	 * @return Returns the dlStateId.
	 */
	public String getDlStateId()
	{
		return dlStateId;
	}

	/**
	 * @param dlStateId
	 *            The dlStateId to set.
	 */
	public void setDlStateId(String string)
	{
		this.dlStateId = string;
		if (dlStateId == null || dlStateId.equals(""))
		{
			dlState = "";
			return;
		}

		if (getDlStateList() != null && getDlStateList().size() > 0)
		{
			dlState = CodeHelper.getCodeDescriptionByCode(getDlStateList(), dlStateId);
		}
	}

	/**
	 * @return Returns the fBI.
	 */
	public String getFBI()
	{
		return FBI;
	}

	/**
	 * @param fbi
	 *            The fBI to set.
	 */
	public void setFBI(String fbi)
	{
		FBI = fbi;
	}

	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet()
	{
		return listsSet;
	}

	/**
	 * @param listsSet
	 *            The listsSet to set.
	 */
	public void setListsSet(boolean listsSet)
	{
		this.listsSet = listsSet;
	}

	/**
	 * @return Returns the raceId.
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @param raceId
	 *            The raceId to set.
	 */
	public void setRaceId(String string)
	{
		this.raceId = string;
		if (raceId == null || raceId.equals(""))
		{
			race = "";
			return;
		}

		if (getRaceList() != null && getRaceList().size() > 0)
		{
			race = CodeHelper.getCodeDescriptionByCode(getRaceList(), raceId);
		}
	}

	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @param secondaryAction
	 *            The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction)
	{
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @param selectedValue
	 *            The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue)
	{
		this.selectedValue = selectedValue;
	}

	/**
	 * 
	 * @return
	 */
	public String getSelectedCaseValue() {
		return selectedCaseValue;
	}

	/**
	 * 
	 * @param selectedCaseValue
	 */
	public void setSelectedCaseValue(String selectedCaseValue) {
		this.selectedCaseValue = selectedCaseValue;
	}

	/**
	 * @return Returns the sexId.
	 */
	public String getSexId()
	{
		return sexId;

	}

	/**
	 * @param sexId
	 *            The sexId to set.
	 */
	public void setSexId(String string)
	{
		sexId = string;
		if (sexId == null || sexId.equals(""))
		{
			sex = "";
			return;
		}

		if (getSexList() != null && getSexList().size() > 0)
		{
			sex = CodeHelper.getCodeDescriptionByCode(getSexList(), sexId);
		}
	}

	/**
	 * @return Returns the sID.
	 */
	public String getSID()
	{
		return SID;
	}

	/**
	 * @param sid
	 *            The sID to set.
	 */
	public void setSID(String sid)
	{
		SID = sid;
	}

	/**
	 * @return Returns the spn.
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param spn
	 *            The spn to set.
	 */
	public void setSpn(String spn)
	{
		this.spn = spn;
	}

	/**
	 * @return Returns the ssn.
	 */
	public ISocialSecurity getSsn()
	{
		return ssn;
	}

	/**
	 * @param ssn
	 *            The ssn to set.
	 */
	public void setSsn(ISocialSecurity ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @return Returns the superviseeName.
	 */
	public IName getSuperviseeName()
	{
		return superviseeName;
	}

	/**
	 * @param superviseeName
	 *            The superviseeName to set.
	 */
	public void setSuperviseeName(IName superviseeName)
	{
		this.superviseeName = superviseeName;
	}

	/**
	 * @return Returns the supervisionPeriod.
	 */
	public String getSupervisionPeriod()
	{
		return supervisionPeriod;
	}

	/**
	 * @param supervisionPeriod
	 *            The supervisionPeriod to set.
	 */
	public void setSupervisionPeriod(String supervisionPeriod)
	{
		this.supervisionPeriod = supervisionPeriod;
	}

	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param update
	 *            The update to set.
	 */
	public void setUpdate(boolean update)
	{
		this.update = update;
	}

	/**
	 * @return Returns the dlState.
	 */
	public String getDlState()
	{
		return dlState;
	}

	/**
	 * @param dlState
	 *            The dlState to set.
	 */
	public void setDlState(String dlState)
	{
		this.dlState = dlState;
	}

	/**
	 * @return Returns the race.
	 */
	public String getRace()
	{
		return race;
	}

	/**
	 * @param race
	 *            The race to set.
	 */
	public void setRace(String race)
	{
		this.race = race;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public Collection getSearchResults()
	{
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(Collection searchResults)
	{
		this.searchResults = searchResults;
	}

	/**
	 * @return Returns the sex.
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param sex
	 *            The sex to set.
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
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
	
	
	public boolean getSupervisionPeriodAsBool(){
		if(this.supervisionPeriod!=null && this.supervisionPeriod.equalsIgnoreCase("A")){
			return true;
		}
		else
			return false;
	}
	/**
	 * @return Returns the searchSupPeriodsResults.
	 */
	public Collection getSearchSupPeriodsResults() {
		return searchSupPeriodsResults;
	}
	/**
	 * @param searchSupPeriodsResults The searchSupPeriodsResults to set.
	 */
	public void setSearchSupPeriodsResults(Collection searchSupPeriodsResults) {
		this.searchSupPeriodsResults = searchSupPeriodsResults;
	}
	/**
	 * @return Returns the searchBy.
	 */
	public String getSearchBy() {
		return searchBy;
	}
	/**
	 * @param searchBy The searchBy to set.
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
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

	public void clearSearchCriteria() {
	    this.dateOfBirth = null;
	    this.CJIS = "";
	    this.dlNum = "";
	    this.dlStateId = "";
	    this.FBI = "";
	    this.raceId = "";
	    this.sexId = "";
	    this.SID = "";
	    this.ssn = new SocialSecurity("");		
	    this.spn = "";
	    this.superviseeName = new Name();	    
	}

	/**
	 * @return the activeCases
	 */
	public Collection getActiveCases() {
		return activeCases;
	}

	/**
	 * @param activeCases the activeCases to set
	 */
	public void setActiveCases(Collection activeCases) {
		this.activeCases = activeCases;
	}

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
}// END CLASS
