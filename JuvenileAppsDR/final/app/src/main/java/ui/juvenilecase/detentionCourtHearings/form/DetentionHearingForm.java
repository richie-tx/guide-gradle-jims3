package ui.juvenilecase.detentionCourtHearings.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JuvenileCaseHelper;

public class DetentionHearingForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private String date; // can be a hearing date/court date
	private String facility;
	private String facilityDesc;
	private Collection<JuvenileFacilityResponseEvent> facilities;
	private List<DocketEventResponseEvent> detentionSearchResults = new ArrayList<DocketEventResponseEvent>();
	
	private List<DocketEventResponseEvent> updatedDocketList = new ArrayList<DocketEventResponseEvent>();
	private Map<String,DocketEventResponseEvent> dktSearchResultsMap;
	private Map<String,DocketEventResponseEvent> updateDocketMap;// to keep track of processing updates
	private Map<String,DocketEventResponseEvent> insertDocketMap; // to keep track of processing inserts
	
	 private int searchResultSize;
	 
	private Collection<JuvenileCourtDecisionCodeResponseEvent> courtDecisions;
	private Map<String,JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap;
	private String courtResult;
	private String transferTo;
	private String resetTo;
	private String resetReason;
	private String hearingType;
	private String attorneyName;
	private String attorneyNameHistory;
	private String barNo;
	private String galBarNumber;
	private String galName;
	private String courtId;
	private String connectionCode;
	
	
	//juvenile details
	private String juvenileNumber;
	private String juvenileName;
	private String raceId;
	private String gender;
	private String age;
	
	// detentionHearing Display Fields.
	
	private String docketTime;
	private String assistantDA;
	private String admitDate;
	private String probableCauseDate;
	private String courtDate;
	private String petitionNum;
	private String juvProbationOfficerCd;
	private String dcktEvtId;
	private String barNum;
	
	private String refereeCourt;
	private String assignedJudge;
	private String validateMsg;
	private String errMessage;
	private String message;
	private String selectedValue;
	private String[] selectedDocketsToUpdate;
	private String selectedDockets;
	private Collection<JSONObject> holidaysList; //used in jquery
	private Collection<JSONObject> detentionCourtDecisions; //used in jquery

	private boolean onlyResultChanged;
	private Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList;
	 
	private String cursorPosition;
	    //Bug #69865
	private String pagerOffset;
	private String pageNum;
	
	
	public void reset(){
		detentionSearchResults = new ArrayList<DocketEventResponseEvent>();
		updatedDocketList = new ArrayList<DocketEventResponseEvent>();
		this.setErrMessage(UIConstants.EMPTY_STRING);
		this.setMessage(UIConstants.EMPTY_STRING);
		pagerOffset = null;
	}
	
	
		
	public String getAttorneyNameHistory() {
		return attorneyNameHistory;
	}



	public void setAttorneyNameHistory(String attorneyNameHistory) {
		this.attorneyNameHistory = attorneyNameHistory;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public Map<String, DocketEventResponseEvent> getDktSearchResultsMap() {
		return dktSearchResultsMap;
	}



	public void setDktSearchResultsMap(
			Map<String, DocketEventResponseEvent> dktSearchResultsMap) {
		this.dktSearchResultsMap = dktSearchResultsMap;
	}



	public Map<String, DocketEventResponseEvent> getUpdateDocketMap()
	{
	    if( updateDocketMap == null){
		
		this.updateDocketMap = new HashMap<String,DocketEventResponseEvent>();
	    }
	    return updateDocketMap;
	}



	public void setUpdateDocketMap(Map<String, DocketEventResponseEvent> updateDocketMap)
	{
	    this.updateDocketMap = updateDocketMap;
	}



	public Map<String, DocketEventResponseEvent> getInsertDocketMap()
	{
	    if( insertDocketMap == null){
		
		this.insertDocketMap = new HashMap<String,DocketEventResponseEvent>();
	    }
	    return insertDocketMap;
	}



	public void setInsertDocketMap(Map<String, DocketEventResponseEvent> insertDocketMap)
	{
	    this.insertDocketMap = insertDocketMap;
	}



	/**
	 * @return the courtId
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId the courtId to set
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNumber(String juvenileNum) {
		this.juvenileNumber = juvenileNum;
	}
	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return the race
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param race the race to set
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * @return the resetTo
	 */
	public String getResetTo() {
		return resetTo;
	}
	/**
	 * @param resetTo the resetTo to set
	 */
	public void setResetTo(String resetTo) {
		this.resetTo = resetTo;
	}
	/**
	 * @return the resetReason
	 */
	public String getResetReason() {
		return resetReason;
	}
	/**
	 * @param resetReason the resetReason to set
	 */
	public void setResetReason(String resetReason) {
		this.resetReason = resetReason;
	}
	/**
	 * @return the hearingType
	 */
	public String getHearingType() {
		return hearingType;
	}
	/**
	 * @param hearingType the hearingType to set
	 */
	public void setHearingType(String hearingType) {
		this.hearingType = hearingType;
	}
	/**
	 * @return the attorneyName
	 */
	public String getAttorneyName() {
		return attorneyName;
	}
	/**
	 * @param attorneyName the attorneyName to set
	 */
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}
	/**
	 * @return the barNo
	 */
	public String getBarNo() {
		return barNo;
	}
	/**
	 * @param barNo the barNo to set
	 */
	public void setBarNo(String barNo) {
		this.barNo = barNo;
	}
	/**
	 * @return the courtDate
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the facilities
	 */
	public Collection<JuvenileFacilityResponseEvent> getFacilities() {
		return facilities;
	}
	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(Collection<JuvenileFacilityResponseEvent> facilities) {
		this.facilities = facilities;
	}
	/**
	 * @return the facility
	 */
	public String getFacility() {
		return facility;
	}
	/**
	 * @param facility the facility to set
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}
	/**
	 * @return the transferToCourt
	 */
	public String getTransferTo() {
		return transferTo;
	}
	/**
	 * @param transferToCourt the transferToCourt to set
	 */
	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}
	/**
	 * @return the connectionCode
	 */
	public String getConnectionCode() {
		return connectionCode;
	}
	/**
	 * @param connectionCode the connectionCode to set
	 */
	public void setConnectionCode(String connectionCode) {
		this.connectionCode = connectionCode;
	}
	
	/**
	 * @return the courtDecisions
	 */
	public Collection<JuvenileCourtDecisionCodeResponseEvent> getCourtDecisions() {
		return courtDecisions;
	}
	/**
	 * @param courtDecisions the courtDecisions to set
	 */
	public void setCourtDecisions(Collection<JuvenileCourtDecisionCodeResponseEvent> courtDecisions) {
		this.courtDecisions = courtDecisions;
	}
	/**
	 * @return the courtResult
	 */
	public String getCourtResult() {
		return courtResult;
	}
	/**
	 * @param courtResult the courtResult to set
	 */
	public void setCourtResult(String courtResult) {
		this.courtResult = courtResult;
	}
	/**
	 * @return the assistantDA
	 */
	public String getAssistantDA() {
		return assistantDA;
	}
	/**
	 * @param assistantDA the assistantDA to set
	 */
	public void setAssistantDA(String assistantDA) {
		this.assistantDA = assistantDA;
	}
	
	/**
	 * @return the admitDate
	 */
	public String getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	/**
	 * @return the probableCauseDate
	 */
	public String getProbableCauseDate() {
		return probableCauseDate;
	}
	/**
	 * @param probableCauseDate the probableCauseDate to set
	 */
	public void setProbableCauseDate(String probableCauseDate) {
		this.probableCauseDate = probableCauseDate;
	}
	/**
	 * @return the courtDate
	 */
	public String getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return the petitionNum
	 */
	public String getPetitionNum() {
		return petitionNum;
	}
	/**
	 * @param petitionNum the petitionNum to set
	 */
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	/**
	 * @return the juvProbationOfficerCd
	 */
	public String getJuvProbationOfficerCd() {
		return juvProbationOfficerCd;
	}
	/**
	 * @param juvProbationOfficerCd the juvProbationOfficerCd to set
	 */
	public void setJuvProbationOfficerCd(String juvProbationOfficerCd) {
		this.juvProbationOfficerCd = juvProbationOfficerCd;
	}



	public int getSearchResultSize()
	{
	    return searchResultSize;
	}



	public void setSearchResultSize(int searchResultSize)
	{
	    this.searchResultSize = searchResultSize;
	}



	/**
	 * @return the facilityDesc
	 */
	public String getFacilityDesc() {
		return facilityDesc;
	}



	/**
	 * @param facilityDesc the facilityDesc to set
	 */
	public void setFacilityDesc(String facilityDesc) {
		this.facilityDesc = facilityDesc;
	}



	/**
	 * @return the docketTime
	 */
	public String getDocketTime() {
		return docketTime;
	}



	/**
	 * @param docketTime the docketTime to set
	 */
	public void setDocketTime(String docketTime) {
		this.docketTime = docketTime;
	}



	/**
	 * @return the refereeCourt
	 */
	public String getRefereeCourt() {
		return refereeCourt;
	}



	/**
	 * @param refereeCourt the refereeCourt to set
	 */
	public void setRefereeCourt(String refereeCourt) {
		this.refereeCourt = refereeCourt;
	}



	/**
	 * @return the assignedJudge
	 */
	public String getAssignedJudge() {
		return assignedJudge;
	}



	/**
	 * @param assignedJudge the assignedJudge to set
	 */
	public void setAssignedJudge(String assignedJudge) {
		this.assignedJudge = assignedJudge;
	}



	public String getValidateMsg() {
		return validateMsg;
	}



	public void setValidateMsg(String validateMsg) {
		this.validateMsg = validateMsg;
	}

	

	public String getMessage()
	{
	    return message;
	}



	public void setMessage(String message)
	{
	    this.message = message;
	}



	public String getErrMessage() {
		return errMessage;
	}



	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}



	public Collection<AttorneyNameAndAddressResponseEvent> getAttorneyDataList() {
		return attorneyDataList;
	}



	public void setAttorneyDataList(
			Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList) {
		this.attorneyDataList = attorneyDataList;
	}



	public String getSelectedValue() {
		return selectedValue;
	}



	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}



	public String getDcktEvtId() {
		return dcktEvtId;
	}



	public void setDcktEvtId(String docketEventId) {
		this.dcktEvtId = docketEventId;
	}



	public String getBarNum() {
		return barNum;
	}



	public void setBarNum(String barNum) {
		this.barNum = barNum;
	}



	public String getCursorPosition() {
		return cursorPosition;
	}



	public void setCursorPosition(String cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
	
	
	public String[] getSelectedDocketsToUpdate()
	{
	    return selectedDocketsToUpdate;
	}



	public void setSelectedDocketsToUpdate(String[] selectedDocketsToUpdate)
	{
	    this.selectedDocketsToUpdate = selectedDocketsToUpdate;
	}



	public String getSelectedDockets()
	{
	    return selectedDockets;
	}



	public void setSelectedDockets(String selectedDockets)
	{
	    this.selectedDockets = selectedDockets;
	}



	public void clearHearingInfo(){
		
		assistantDA = "";
		docketTime = "";
	}

	public boolean isOnlyResultChanged()
	{
	    return onlyResultChanged;
	}



	public void setOnlyResultChanged(boolean onlyResultChanged)
	{
	    this.onlyResultChanged = onlyResultChanged;
	}



	public Collection<JSONObject> getHolidaysList()
	{
	    return holidaysList;
	}

	public void setHolidaysList(Collection<JSONObject> holidaysList)
	{
	    this.holidaysList = holidaysList;
	}

	
	public Collection<JSONObject> getDetentionCourtDecisions()
	{
	    return detentionCourtDecisions;
	}


	public void setDetentionCourtDecisions(Collection<JSONObject> courtResults)
	{
	    this.detentionCourtDecisions = courtResults;
	}

	public String getPagerOffset()
	{
	    return pagerOffset;
	}



	public void setPagerOffset(String pagerOffset)
	{
	    this.pagerOffset = pagerOffset;
	}



	public String getPageNum()
	{
	    return pageNum;
	}



	public void setPageNum(String pageNum)
	{
	    this.pageNum = pageNum;
	}



	public Map<String, JuvenileCourtDecisionCodeResponseEvent> getCourtDecisionsMap()
	{
	    if( courtDecisionsMap == null){
		
		this.courtDecisionsMap = new HashMap<String,JuvenileCourtDecisionCodeResponseEvent>();
		initCourtDecisionMap();
	    }
	    return courtDecisionsMap;
	}



	public void setCourtDecisionsMap(Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap)
	{
	    this.courtDecisionsMap = courtDecisionsMap;
	}
	
	private void initCourtDecisionMap(){
	    
	    List crtDecisionList =  JuvenileCaseHelper.getCourtDecisionsNew();
	    for(int i =0;i< crtDecisionList.size();i++){
		
		JuvenileCourtDecisionCodeResponseEvent resp = 
			(JuvenileCourtDecisionCodeResponseEvent) crtDecisionList.get(i);
		courtDecisionsMap.put(resp.getCode(), resp);
	    }
	}
	
	    public List<DocketEventResponseEvent> getUpdatedDocketList()
	{
	    return updatedDocketList;
	}



	public void setUpdatedDocketList(List<DocketEventResponseEvent> updatedDocketList)
	{
	    this.updatedDocketList = updatedDocketList;
	}

	    /**
	     * currentlyViewedList
	     * @param list
	     * @param pagerOffset
	     * @return List<DocketEventResponseEvent>-Sub List
	     */
	    private List<DocketEventResponseEvent> currentlyViewedList(List<DocketEventResponseEvent> list, String pagerOffset)
	    {
		if (pagerOffset != null && pagerOffset.length() > 0)
		{
		    try
		    {
			int from = Integer.parseInt(pagerOffset);
			int to = Integer.parseInt(pagerOffset) + 4;
			if (to > list.size())
			{
			    to = list.size();
			}
			return list.subList(from, to);
		    }
		    catch (NumberFormatException nfe)
		    { //empty
		    }
		    catch (IndexOutOfBoundsException obe)
		    { //empty
			return list.subList(0, 6);
		    }
		    catch (IllegalArgumentException iae)
		    { //empty
			return list.subList(0, 6);
		    }
		}
		return list;
	    }

	    /**
	     * Reset - called every submit
	     */
	    public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	    {
		this.setErrMessage(UIConstants.EMPTY_STRING);
		this.setMessage(UIConstants.EMPTY_STRING);
		String pagerOffset = (String) aRequest.getParameter("pager.offset");
		if (detentionSearchResults.size() > 6)
		{
		    if (pagerOffset != null && !pagerOffset.isEmpty())
		    { // do the sub listing only when there is the pagination.
			if (detentionSearchResults != null && !detentionSearchResults.isEmpty())
			{
			      this.updatedDocketList=new ArrayList<DocketEventResponseEvent>(); //initialize the list everytime.
			      setUpdatedDocketList(new ArrayList<DocketEventResponseEvent>(currentlyViewedList((List<DocketEventResponseEvent>) detentionSearchResults, pagerOffset)));
			}
		    }
		}
		else
		{	
		    //API link - https://www.javatips.net/api/EclipseTrader-master/org.apache.commons.collections/src/org/apache/commons/collections/list/LazyList.java
		    Factory factory = new Factory() {
			public Object create()
			{
			    return new DocketEventResponseEvent();
			}
		    };
		    detentionSearchResults = LazyList.decorate(detentionSearchResults, factory);
		    this.updatedDocketList=new ArrayList<DocketEventResponseEvent>(); //initialize the list everytime.
		    setUpdatedDocketList(detentionSearchResults);
		}
	    }



	    public List<DocketEventResponseEvent> getDetentionSearchResults()
	    {
	        return detentionSearchResults;
	    }



	    public void setDetentionSearchResults(List<DocketEventResponseEvent> detentionSearchResults)
	    {
	        this.detentionSearchResults = detentionSearchResults;
	    }



	    public String getGalBarNumber()
	    {
	        return galBarNumber;
	    }



	    public void setGalBarNumber(String galBarNumber)
	    {
	        this.galBarNumber = galBarNumber;
	    }



	    public String getGalName()
	    {
	        return galName;
	    }



	    public void setGalName(String galName)
	    {
	        this.galName = galName;
	    }
	        
	
}
