/*
 * Created on Jun 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.activities.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileActivityResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileActivityStatisticsResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.UIUtil;


/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActivitiesForm extends ActionForm
{
	
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	private boolean allowUpdates = false;
	private String vendorActivity;
	
	private Collection activityResults;
	private Collection activityCodes;

	private Collection activityCategoryList;
	private String selectedCategoryId;
	
	private Collection activityTypeList;
	private String selectedTypeId;
	
	private Collection activityList;
	private String selectedDescriptionId;
	
	private Date activityDate;	
	private String categoryId;
	private String categoryDesc;
	private String typeId;
	private String typeDesc;
	private String activityId;
	private String codeId;	
	private String activityDesc;
	private String comments;
	private String updateComments;
	private String activityDateAsStr;
	private String activityAddress;
	
	private Date startDate;
	private String startDateAsStr;
//	private String startDateAsString;
	private Date endDate;
	private String endDateAsStr;
//	private String endDateAsString;
	
	// newly added activities that have not been persisted
	private Collection newActivities;
	private String activityTypeIdForReload;
	private String activityDescForReload;
	private String activityCatIdForReload;
	
	private String fromFacility; //added for US 38802
	
	private String activityTimeStr;
	private String activityEndTimeStr;
	private String supervisionTypeId2;
	private String supervisionTypeDesc;
	private String locationId;
	private String locationDesc;
	private String officerFirstName;
	private String officerLastName;
	private List<JuvenileCasefileActivityResponseEvent> juvenilebycasefileActivity;	
	private String selectSystemActivities;
	private String juvenileNumber;
	private String selectclosedCasefiles;	
	private int listTotal;		
	private List<JuvenileCasefileActivityStatisticsResponseEvent> statistics;	
	private int total;
	private int actCounter;
	private Collection  location;
	private Collection supervisionTypes;
	private String latitude;
	private String longitude;
	private String updateBtnAllowed;
	private String progrefStatus;	
	
	
	public String getFromFacility() {
	    return fromFacility;
	}
	public void setFromFacility(String fromFacility) {
	    this.fromFacility = fromFacility;
	}
	public ActivitiesForm()
	{
		emptyColl = new ArrayList();
		listsSet = false;
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
		newActivities= new ArrayList();
		initialize();
	
	}
	public void clearAll()
	{
	    activityResults = null;
	    activityCodes = null;
	    activityCategoryList = null;
		selectedCategoryId = "";
		activityTypeList = null;
		selectedTypeId = "";
		activityList = null;
		selectedDescriptionId = "";
		activityDate = null;	
		categoryId = "";
		categoryDesc = "";
		typeId = "";
		typeDesc = "";
		activityId = "";
		activityDesc = "";
		comments = "";
		activityDateAsStr = "";
		selectedValue="";
//		startDate = new Date();
		endDate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DATE, -30);
		startDate = rightNow.getTime(); 
		startDateAsStr = "";
		endDateAsStr = "";
		newActivities= new ArrayList();
		activityTimeStr = null;
		supervisionTypeId2="";
		locationId="";
		officerFirstName="";
		officerLastName="";	
		juvenilebycasefileActivity= new ArrayList();
		latitude = "";
		longitude = "";
	}
	
	public void clear()
	{
	    	activityResults = null;
	    	//activityCodes = null;
	    	activityCategoryList = null;
		selectedCategoryId = "";
		activityTypeList = null;
		selectedTypeId = "";
		activityList = null;
		selectedDescriptionId = "";
		activityDate = null;	
		categoryId = "";
		categoryDesc = "";
		typeId = "";
		typeDesc = "";
		activityId = "";
		activityDesc = "";
		comments = "";
		activityDateAsStr = "";
		selectedValue="";
		startDate=null;
		endDate=null;
		startDateAsStr = "";
		endDateAsStr = "";
		newActivities= new ArrayList();
		activityTimeStr = null;
		supervisionTypeId2="";
		locationId="";
		officerFirstName="";
		officerLastName="";	
		juvenilebycasefileActivity= new ArrayList();
		juvenileNumber="";
	}
	
	/**
	 * @return Returns the activityResults.
	 */
	public Collection getActivityResults() {
		return activityResults;
	}
	public Collection getActivityCodes() {
		return activityCodes;
	}	
	/**
	 * @param activityResults The activityResults to set.
	 */
	public void setActivityResults(Collection activityResults) {
		if(activityResults != null) {
			Collections.sort((List)activityResults);
			this.activityResults = activityResults;
		}
	}
	public void setActivityCodes(Collection activityCodes) {
		this.activityCodes = activityCodes;
	}	
	/**
	 * @return Returns the activityCategoryList.
	 */
	public Collection getActivityCategoryList() {
		return activityCategoryList;
	}
	/**
	 * @param activityCategoryList The activityCategoryList to set.
	 */
	public void setActivityCategoryList(Collection activityCategoryList) {
		this.activityCategoryList = activityCategoryList;
	}
	/**
	 * @return Returns the activityTypeList.
	 */
	public Collection getActivityTypeList() {
		return activityTypeList;
	}
	/**
	 * @param activityTypeList The activityTypeList to set.
	 */
	public void setActivityTypeList(Collection activityTypeList) {
		this.activityTypeList = activityTypeList;
	}
	/**
	 * @return Returns the selectedCategoryId.
	 */
	public String getSelectedCategoryId() {
		return selectedCategoryId;
	}
	/**
	 * @param selectedCategoryId The selectedCategoryId to set.
	 */
	public void setSelectedCategoryId(String selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}
	/**
	 * @return Returns the selectedTypeId.
	 */
	public String getSelectedTypeId() {
		return selectedTypeId;
	}
	/**
	 * @param selectedTypeId The selectedTypeId to set.
	 */
	public void setSelectedTypeId(String selectedTypeId) {
		this.selectedTypeId = selectedTypeId;
	}
	/**
	 * @return Returns the selectedDescriptionId.
	 */
	public String getSelectedDescriptionId() {
		return selectedDescriptionId;
	}
	/**
	 * @param selectedDescriptionId The selectedDescriptionId to set.
	 */
	public void setSelectedDescriptionId(String selectedDescriptionId) {
		this.selectedDescriptionId = selectedDescriptionId;
	}
	/**
	 * @return Returns the activityDescriptionList.
	 */
	public Collection getActivityList() {
		return activityList;
	}
	/**
	 * @param activityDescriptionList The activityDescriptionList to set.
	 */
	public void setActivityList(Collection activityList) {
		this.activityList = activityList;
	}
	public void setAction(String string)
	{
		action = string;
	}
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}
	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}
	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}
	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl() {
		return emptyColl;
	}
	/**
	 * @param emptyColl The emptyColl to set.
	 */
	public static void setEmptyColl(Collection emptyColl) {
		ActivitiesForm.emptyColl = emptyColl;
	}
	/**
	 * @return Returns the activityDate.
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	/**
	 * @param activityDate The activityDate to set.
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	/**
	 * @return Returns the activityDesc.
	 */
	public String getActivityDesc() {
		return activityDesc;
	}
	/**
	 * @param activityDesc The activityDesc to set.
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	/**
	 * @return Returns the activityId.
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId The activityId to set.
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return Returns the categoryDesc.
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}
	/**
	 * @param categoryDesc The categoryDesc to set.
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		if(categoryId != null && !(categoryId.trim().equals("")) ) {
   			setCategoryDesc(CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes("ACTIVITY_CATEGORY",true), categoryId));
   		}		
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @param activityTypeIdForReload The activityTypeIdForReload to set.
	 */
	public void setActivityTypeIdForReload(String activityTypeIdForReload) {
		this.activityTypeIdForReload = activityTypeIdForReload;
	}
	/**
	 * @param activityDescForReload The activityDescForReload to set.
	 */
	public void setActivityDescForReload(String activityDescForReload) {
		this.activityDescForReload = activityDescForReload;
	}
	
	/**
	 * @param activityCatIdForReload The activityCatIdForReload to set.
	 */
	public void setActivityCatIdForReload(String activityCatIdForReload) {
		this.activityCatIdForReload = activityCatIdForReload;
	}
	/**
	 * @return Returns the typeDesc.
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	/**
	 * @param typeDesc The typeDesc to set.
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	/**
	 * @return Returns the typeId.
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId The typeId to set.
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
		if(typeId != null && !(typeId.trim().equals("")) ) {
   			setTypeDesc(CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes("ACTIVITY_TYPE",true), typeId));
   		}		
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
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @return Returns the activityTypeIdForReload.
	 */
	public String getActivityTypeIdForReload() {
		return activityTypeIdForReload;
	}
	/**
	 * @return Returns the activityDescForReload.
	 */
	public String getActivityDescForReload() {
		return activityDescForReload;
	}
	/**
	 * @return Returns the activityCatIdForReload.
	 */
	public String getActivityCatIdForReload() {
		return activityCatIdForReload;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	
	public String getActivityDateAsStr() {
		return activityDateAsStr;
	}
	/**
	 * @param supervisionEndDateStr The supervisionEndDateStr to set.
	 */
	public void setActivityDateAsStr(String activityDateAsStr) {
		this.activityDateAsStr = activityDateAsStr;
		
		if(activityDateAsStr != null && activityDateAsStr.length() > 0)
		{
			activityDate = UIUtil.getDateFromString(activityDateAsStr, UIConstants.DATE_FMT_1);
			
		}
	}
	/**
	 * @return Returns the codeId.
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId The codeId to set.
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
		if(codeId != null && !(codeId.trim().equals("")) ) {
   			setActivityDesc(CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes("ACTIVITY_CODE",true), codeId));
   		}		
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the endDateAsStr.
	 */
	public String getEndDateAsStr() {
        if (endDate != null)
        {
        	endDateAsStr = DateUtil.dateToString(endDate, UIConstants.DATE_FMT_1);
        }		
		return endDateAsStr;		
	}
	/**
	 * @param endDateAsStr The endDateAsStr to set.
	 */
	public void setEndDateAsStr(String endDateAsStr) {
		this.endDateAsStr = endDateAsStr;
		if(endDateAsStr != null && endDateAsStr.length() > 0)
		{
			endDate = UIUtil.getDateFromString(endDateAsStr, UIConstants.DATE_FMT_1);
		}	
		else {
			endDate = null;
		}
	}
	/**
	 * @return Returns the startDateAsStr.
	 */
	public String getStartDateAsStr() {
        if (startDate != null)
        {
            startDateAsStr = DateUtil.dateToString(startDate, UIConstants.DATE_FMT_1);
        }		
		return startDateAsStr;
	}
	/**
	 * @param startDateAsStr The startDateAsStr to set.
	 */
	public void setStartDateAsStr(String startDateAsStr) {
		this.startDateAsStr = startDateAsStr;
		if(startDateAsStr != null && startDateAsStr.length() > 0)
		{
			startDate = UIUtil.getDateFromString(startDateAsStr, UIConstants.DATE_FMT_1);
		}	
		else {
			startDate = null;
		}
	}
	
	/**
	 * @return
	 */
	public Collection getNewActivities( )
	{
		if( this.newActivities == null )
		{
			this.newActivities = new ArrayList( ) ;
		}
		return this.newActivities ;
	}
	
	public Collection getWorkDays()
	{
		//Work days code table has to be sorted by ID, not just string comparison.
		return CodeHelper.getWorkDayCodes();
	}

	/**
	 * @param collection
	 */
	public void setNewActivities( Collection collection )
	{
		newActivities = collection ;
	}
	
	/**
	 * 
	 */
	public void clearNewActivities( )
	{
		this.newActivities = new ArrayList( ) ;
		selectedValue = UIConstants.EMPTY_STRING ;
	}
	/**
	 * @return Returns the activitiesForReload.
	 */
	public String getVendorActivity() {
		return vendorActivity;
	}
	public void setVendorActivity(String vendorActivity) {
		this.vendorActivity = vendorActivity;
	}
	public String getActivityTimeStr()
	{
	    return activityTimeStr;
	}
	public void setActivityTimeStr(String activityTimeStr)
	{
	    this.activityTimeStr = activityTimeStr;
	}
	public String getSupervisionTypeId2()
	{
	    return supervisionTypeId2;
	}
	public void setSupervisionTypeId2(String supervisionTypeId2)
	{
	    this.supervisionTypeId2 = supervisionTypeId2;
	}
	public String getLocationId()
	{
	    return locationId;
	}
	public void setLocationId(String locationId)
	{
	    this.locationId = locationId;
	}
	public String getOfficerFirstName()
	{
	    return officerFirstName;
	}
	public void setOfficerFirstName(String officerFirstName)
	{
	    this.officerFirstName = officerFirstName;
	}
	
	public String getOfficerLastName()
	{
	    return officerLastName;
	}
	public void setOfficerLastName(String officerLastName)
	{
	    this.officerLastName = officerLastName;
	}
	/**
	     * Returns the collection code response events for the locations
	     * 
	     * @return
	     */
	    public Collection getLocation()
	    {
	        return this.location;
	    }
	    /**
	     * Returns the collection code response events for the supervision types
	     * 
	     * @return
	     */
	    public Collection getSupervisionTypes()
	    {
	        return this.supervisionTypes;
	    }
	    
	    public void initialize(){
		//this.location = CodeHelper.getLocationCodes(); //commented for 181049 and 181050
		this.location =(Collection)ComplexCodeTableHelper.getActiveJuvenileLocationUnits();
		this.supervisionTypes = CodeHelper.getSupervisionTypes();
	    }
	  
		public int getListTotal()
		{
		    return listTotal;
		}
		public void setListTotal(int listTotal)
		{
		    this.listTotal = listTotal;
		}
		public List<JuvenileCasefileActivityResponseEvent> getJuvenilebycasefileActivity()
		{
		    return juvenilebycasefileActivity;
		}
		public void setJuvenilebycasefileActivity(List<JuvenileCasefileActivityResponseEvent> juvenilebycasefileActivity)
		{
		    this.juvenilebycasefileActivity = juvenilebycasefileActivity;
		}
		public String getSelectSystemActivities()
		{
		    return selectSystemActivities;
		}
		public void setSelectSystemActivities(String selectSystemActivities)
		{
		    this.selectSystemActivities = selectSystemActivities;
		}
		public String getJuvenileNumber()
		{
		    return juvenileNumber;
		}
		public void setJuvenileNumber(String juvenileNumber)
		{
		    this.juvenileNumber = juvenileNumber;
		}
		
		public String getSelectclosedCasefiles()
		{
		    return selectclosedCasefiles;
		}
		public void setSelectclosedCasefiles(String selectclosedCasefiles)
		{
		    this.selectclosedCasefiles = selectclosedCasefiles;
		}		
		public int getTotal()
		{
		    return total;
		}
		public void setTotal(int total)
		{
		    this.total = total;
		}
		public int getActCounter()
		{
		    return actCounter;
		}
		public void setActCounter(int actCounter)
		{
		    this.actCounter = actCounter;
		}
		public List<JuvenileCasefileActivityStatisticsResponseEvent> getStatistics()
		{
		    return statistics;
		}
		public void setStatistics(List<JuvenileCasefileActivityStatisticsResponseEvent> statistics)
		{
		    this.statistics = statistics;
		}
		public String getUpdateComments()
		{
		    return updateComments;
		}
		public void setUpdateComments(String updateComments)
		{
		    this.updateComments = updateComments;
		}
		public String getActivityEndTimeStr()
		{
		    return activityEndTimeStr;
		}
		public void setActivityEndTimeStr(String activityEndTimeStr)
		{
		    this.activityEndTimeStr = activityEndTimeStr;
		}
		public String getActivityAddress()
		{
		    return activityAddress;
		}
		public void setActivityAddress(String activityAddress)
		{
		    this.activityAddress = activityAddress;
		}
		
		
		public String getLatitude()
		{
		    return latitude;
		}
		public void setLatitude(String latitude)
		{
		    this.latitude = latitude;
		}
		public String getLongitude()
		{
		    return longitude;
		}
		public void setLongitude(String longitude)
		{
		    this.longitude = longitude;
		}
		

		public String getSupervisionTypeDesc()
		{
		    if ( this.supervisionTypeId2 != null 
			    && this.supervisionTypeId2.length() > 0
			    && this.supervisionTypes != null
			    && this.supervisionTypes.size() > 0 ){
			for ( CodeResponseEvent supervisionType :(Collection<CodeResponseEvent>) supervisionTypes ){
			    if (  this.supervisionTypeId2.equals( supervisionType.getCode() ) ){
				this.supervisionTypeDesc = supervisionType.getDescription();
			    }
			}
			
		    }
		    return supervisionTypeDesc;
		}
		public void setSupervisionTypeDesc(String supervisionTypeDesc)
		{
		    this.supervisionTypeDesc = supervisionTypeDesc;
		}
		public String getLocationDesc()
		{
		    if ( this.locationId != null 
			    && this.locationId.length() > 0 
			    && this.location != null
			    && this.location.size() > 0 ) {
            	    for (LocationResponseEvent locationResp : (Collection<LocationResponseEvent>) location)
                	    {
                		if (this.locationId.equals(locationResp.getLocationId())) 
                		{
                		    if (locationResp.getLocationName() == null)//added for US 181049 and 181050
                		    {
                			this.locationDesc = locationResp.getLocationUnitName();
                			break;
                		    }
                		    else
                		    {
                			this.locationDesc = locationResp.getLocationName();
                			break;
                		    }
                		}
                	    }
		    }
		    return locationDesc;
		}
		public void setLocationDesc(String locationDesc)
		{
		    this.locationDesc = locationDesc;
		}
		public String getUpdateBtnAllowed()
		{
		    return updateBtnAllowed;
		}
		public void setUpdateBtnAllowed(String updateBtnAllowed)
		{
		    this.updateBtnAllowed = updateBtnAllowed;
		}
		public String getProgrefStatus()
		{
		    return progrefStatus;
		}
		public void setProgrefStatus(String progrefStatus)
		{
		    this.progrefStatus = progrefStatus;
		}
		
		
		
}
