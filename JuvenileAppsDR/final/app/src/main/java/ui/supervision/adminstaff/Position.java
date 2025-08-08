/*
 * Created on Mar 12, 2007
 *
 */
package ui.supervision.adminstaff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IPhoneNumber;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.User;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author jjose
 *
 */
public class Position implements Comparable{
	private Collection courts=null;  // contains CourtBeans which in turn contain CourtResponseEvents
	private User createdBy=null;
	private String divisionDesc="";
	private String divisionId="";
	private boolean hasCaseload=true;
	private boolean isNOA=false; // no officer assigned
	private boolean isRetired=false;
	private String jobTitleDesc="";
	private String jobTitleId="";
	private String locationDesc="";
	private String locationDetails="";
	private String locationId="";
	private String officerTypeDesc="";
	private String officerTypeId="";
	private String organizationId="";
	private Collection organizations=null;  //group of organizations
	private IPhoneNumber phone=null;
	private Date positionAssignedDate=new Date();
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	private String positionId="";
	private String positionName="";
	private String positionStatusDesc="";
	private String positionStatusId="";
	private String positionTypeDesc="";
	private String positionTypeId="";
	private String probOfficerInd="";
	private String programSectionDesc="";
	private String programSectionId="";
	private String programUnitDesc="";
	private String programUnitId="";	
	private Collection selectedCourts=null;
	
	private ArrayList subordinates=null;
	private Position supervisor=null;
	private User user=null;
	
	private String effectiveDateAsStr;
	private String retirementDateAsStr;
	
	public String getRetirementDateAsStr() {
		return retirementDateAsStr;
	}


	public void setRetirementDateAsStr(String retirementDateAsStr) {
		this.retirementDateAsStr = retirementDateAsStr;
	}


	public void addSubordinate(Position aSubPosition){
		if(aSubPosition!=null){
			if(subordinates==null){
				subordinates=new ArrayList();
			}
			subordinates.add(aSubPosition);
		}
	}
	
	
	private void clearAll(){
		positionId="";
		positionAssignedDate=new Date();
		hasCaseload=true;
		probOfficerInd="";
		positionName="";
		positionTypeId="";
		positionTypeDesc="";
		positionStatusId="";
		positionStatusDesc="";
		officerTypeId="";
		officerTypeDesc="";
		jobTitleId="";
		jobTitleDesc="";
		divisionId="";
		divisionDesc="";
		programUnitId="";
		programUnitDesc="";
		programSectionId="";
		programSectionDesc="";
		phone=new PhoneNumber("","","");
		locationId="";
		locationDesc="";
		locationDetails="";
		courts=new ArrayList();  // contains CourtBeans which in turn contain CourtResponseEvents
		selectedCourts=new ArrayList();
		supervisor=new Position();
		user=new User();
		createdBy=new User();
		isRetired=false;
		
		subordinates=new ArrayList();
	}
	
	public int compareTo(Object o) {
		int incomingIsGreater=1;
		int incomingIsLess=-1;
		if(o==null || !(o instanceof Position)){
			return incomingIsLess;
		}
		Position incoming=(Position)o;
		if(incoming.getUser()==null){
			return incomingIsLess;
		}
		if(this.user!=null)
		{
			if(incoming.getUser().getUserName()==null){
				return incomingIsLess;
			}
			else{
				if(this.user.getUserName()==null){
					return incomingIsGreater;
				}
				else{
					return ((Name)this.user.getUserName()).compareTo(incoming.getUser().getUserName());
				}
			}
		}
		else{
			return incomingIsGreater;
		}
	}
	
	public String getAssignedDateAsDateStr(){
		if(positionAssignedDate!=null){
			return DateUtil.dateToString(positionAssignedDate,UIConstants.DATE_FMT_1);
		}
		return "";
	}
	
	public String getAssignedDateAsTimeStr(){
		if(positionAssignedDate!=null){
			return DateUtil.dateToString(positionAssignedDate,UIConstants.TIME_FMT_1);
		}
		return "";
	}
	
	
	public String getCaseloadAsYESNO(){
		if(hasCaseload)
			return UIConstants.YES_FULL_TEXT;
		else
			return UIConstants.NO_FULL_TEXT;
	}

	/**
	 * @return Returns the courts.
	 */
	public Collection getCourts() {
		if(courts==null || courts.size()<1){
			courts=UIAdminStaffHelper.getCourts();
		}
		return courts;
	}
	/**
	 * @return Returns the createdBy.
	 */
	public User getCreatedBy() {
		return createdBy;
	}
	
	public Collection getCSTSOfficerTypes(){
		return ComplexCodeTableHelper.getCSTSOfficerTypes(SecurityUIHelper.getUserAgencyId());
	}

	/**
	 * @return Returns the divisionDesc.
	 */
	public String getDivisionDesc() {
		return divisionDesc;
	}
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @return Returns the jobTitleDesc.
	 */
	public String getJobTitleDesc() {
		return jobTitleDesc;
	}
	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId() {
		return jobTitleId;
	}

	
	public Collection getJobTitles(){
		return ComplexCodeTableHelper.getSupervisionStaffJobTitles(SecurityUIHelper.getUserAgencyId());
	}
	/**
	 * @return Returns the locationDesc.
	 */
	public String getLocationDesc() {
		//Needed on PositionInfoTile.jsp
		List aList = CollectionUtil.iteratorToList(this.getLocations().iterator());
		LocationResponseEvent lre = null;
		for (int i = 0; i < aList.size(); i++) {
			lre = (LocationResponseEvent) aList.get(i);
			if (lre.getLocationId().equals(this.getLocationId())){
				this.locationDesc = lre.getLocationName();
				break;
			}
		}
		return locationDesc;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails() {
		return locationDetails;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	public Collection getLocations(){
		//return ComplexCodeTableHelper.getLocationCodes();
		return ComplexCodeTableHelper.getCSInhouseLocationCodes();
	}	
	/**
	 * @return Returns the officerTypeDesc.
	 */
	public String getOfficerTypeDesc() {
		return officerTypeDesc;
	}
	/**
	 * @return Returns the officerTypeId.
	 */
	public String getOfficerTypeId() {
		return officerTypeId;
	}
	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		if(programSectionId!=null && !programSectionId.equals("")){
			return programSectionId;
		}
		else if(programUnitId!=null && !programUnitId.equals("")){
			return programUnitId;
		}
		else{
			return divisionId;
		}
	}
	
	
	/**
	 * @return Returns the organizations.
	 */
	public Collection getOrganizations() {
		if(organizations==null || organizations.size()<1){
			organizations=AdminStaffSearchForm.getStaticOrganizations();
		}
		return organizations;
	}
	/**
	 * @return Returns the phone.
	 */
	public IPhoneNumber getPhone() {
		if(phone==null)
			phone=new PhoneNumber("","","");
		return phone;
	}
	/**
	 * @return Returns the positionAssignedDate.
	 */
	public Date getPositionAssignedDate() {
		return positionAssignedDate;
	}
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName() {
		return positionName;
	}
	
	public String getPositionStatusDesc() {
		return positionStatusDesc;
	}
	
	public Collection getPositionStatuses(){
		return ComplexCodeTableHelper.getSupervisionStaffStatus(SecurityUIHelper.getUserAgencyId());
	}
	/**
	 * @return Returns the positionStatusId.
	 */
	public String getPositionStatusId() {
		return positionStatusId;
	}
	/**
	 * @return Returns the positionTypeDesc.
	 */
	public String getPositionTypeDesc() {
		return positionTypeDesc;
	}
	/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}
	
	public Collection getPositionTypes(){
		return ComplexCodeTableHelper.getSupervisionStaffPositionTypes(SecurityUIHelper.getUserAgencyId());
	}
	/**
	 * @return Returns the probOfficerInd.
	 */
	public String getProbOfficerInd() {
		return probOfficerInd;
	}
	/**
	 * @return Returns the programSectionDesc.
	 */
	public String getProgramSectionDesc() {
		return programSectionDesc;
	}
	/**
	 * @return Returns the programSectionId.
	 */
	public String getProgramSectionId() {
		return programSectionId;
	}
	/**
	 * @return Returns the programUnitDesc.
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @return Returns the selectedCourts.
	 */
	public Collection getSelectedCourts() {
		if(selectedCourts==null || selectedCourts.size()<1){
			return null;
		}
		else
			return selectedCourts;
	}
	/**
	 * @return Returns the subordinates.
	 */
	public ArrayList getSubordinates() {
		return subordinates;
	}
	/**
	 * @return Returns the supervisor.
	 */
	public Position getSupervisor() {
		if(supervisor==null)
			supervisor=new Position();
		return supervisor;
	}
	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		if(user==null)
			user=new User();
		return user;
	}
	
	public boolean isDivisionManager(){
		boolean returnVal=false;
		if(positionTypeId!=null){
			if(positionTypeId.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR)){
				returnVal=true;
			}
		}
		return returnVal;
	}
	/**
	 * @return Returns the hasCaseload.
	 */
	public boolean isHasCaseload() {
		return hasCaseload;
	}
	
	public boolean isHasStaffSubordinates(){
		if(subordinates!=null && subordinates.size()>0)
			return true;
		else
			return false;
	}
	/**
	 * @return Returns the isNOA.
	 */
	public boolean isNOA() {
		return isVacant();
	}
	
	public boolean isPosSupervisor(){
		boolean returnVal=false;
		if(positionTypeId!=null){
			if(positionTypeId.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR) || 
					positionTypeId.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP)) {
				returnVal=true;
			}
		}
		return returnVal;
	}
	
	/**
	 * @return Returns the isRetired.
	 */
	public boolean isRetired() {
		if(this.getPositionStatusId()!=null && this.getPositionStatusId().equals(PDCodeTableConstants.STAFF_STATUS_RETIRED))
			return true;
		else
			return false;
	}
	
	public boolean isVacant(){
		if(user==null || user.getUserId()==null || user.getUserId().trim().equals("")){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * @param courts The courts to set.
	 */
	public void setCourts(Collection courts) {
		this.courts = courts;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @param divisionDesc The divisionDesc to set.
	 */
	public void setDivisionDesc(String divisionDesc) {
		this.divisionDesc = divisionDesc;
	}
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
		divisionDesc=UIAdminStaffHelper.getOrganizationName(divisionId,organizations);
	}
	/**
	 * @param hasCaseload The hasCaseload to set.
	 */
	public void setHasCaseload(boolean hasCaseload) {
		this.hasCaseload = hasCaseload;
	}
	/**
	 * @param jobTitleDesc The jobTitleDesc to set.
	 */
	public void setJobTitleDesc(String jobTitleDesc) {
		this.jobTitleDesc = jobTitleDesc;
	}
	/**
	 * @param jobTitleId The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
		/*jobTitleDesc="";
		Collection myList=getJobTitles();
		if(myList!=null && myList.size()>0){
			//jobTitleDesc=SimpleCodeTableHelper.getDescrByCode((List)myList,jobTitleId);
		    jobTitleDesc=this.getCodeDescriptionFromAlpha((List) myList, jobTitleId);
		}*/
	}
	private String getCodeDescriptionFromAlpha(List myList, String alphaCode){
	    String desc = null;
	    if (myList != null && myList.size() > 0){
	        Iterator iter = myList.iterator();
	        CodeResponseEvent cre = null;
	        while (iter.hasNext()){
	            cre = (CodeResponseEvent) iter.next();
	            if (cre.getSupervisionCode().equals(alphaCode)){
	                desc = cre.getDescription();
	                break;
	            }
	        }
	    }
	    return desc;
	}
	/**
	 * @param locationDesc The locationDesc to set.
	 */
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @param officerTypeDesc The officerTypeDesc to set.
	 */
	public void setOfficerTypeDesc(String officerTypeDesc) {
		this.officerTypeDesc = officerTypeDesc;
	}
	/**
	 * @param officerTypeId The officerTypeId to set.
	 */
	public void setOfficerTypeId(String officerTypeId) {
		this.officerTypeId = officerTypeId;
		/*officerTypeDesc="";
		Collection myList=getCSTSOfficerTypes();
		if(myList!=null && myList.size()>0){
			officerTypeDesc=SimpleCodeTableHelper.getDescrByCode((List)myList,officerTypeId);
		}*/
	}
	/**
	 * @param organizationId The organizationId to set.
	 */
	public void setOrganizationId(String aOrgId) {
		this.organizationId = aOrgId;
		setDivisionId("");
		setProgramUnitId("");
		setProgramSectionId("");
		if(aOrgId!=null && !aOrgId.equals("")){
			Collection aOrganizations=getOrganizations();
			if(aOrganizations == null || aOrganizations.size()<1 || aOrgId==null || aOrgId.equals("")){
				return;
			}	
			Iterator organizationIter = aOrganizations.iterator();
			while(organizationIter.hasNext())
			{
				OrganizationResponseEvent eachOrg = (OrganizationResponseEvent) organizationIter.next();
				if(aOrgId.equals(eachOrg.getOrganizationId()))
				{
					divisionId=(eachOrg.getOrganizationId());
					divisionDesc=eachOrg.getDescription();
					
					return;
				}
				if(eachOrg.getChildren()!=null && eachOrg.getChildren().size()>0){
					Iterator organizationIter2Child = eachOrg.getChildren().iterator();
					while(organizationIter2Child.hasNext())
					{
						OrganizationResponseEvent eachOrgChild2 = (OrganizationResponseEvent) organizationIter2Child.next();
						if(aOrgId.equals(eachOrgChild2.getOrganizationId()))
						{
							divisionId=(eachOrg.getOrganizationId());
							divisionDesc=eachOrg.getDescription();
							programUnitId=(eachOrgChild2.getOrganizationId());
							programUnitDesc=eachOrgChild2.getDescription();
							return;
						}
						if(eachOrgChild2.getChildren()!=null && eachOrgChild2.getChildren().size()>0){
							Iterator organizationIter3Child = eachOrgChild2.getChildren().iterator();
							while(organizationIter3Child.hasNext())
							{
								OrganizationResponseEvent eachOrgChild3 = (OrganizationResponseEvent) organizationIter3Child.next();
								if(aOrgId.equals(eachOrgChild3.getOrganizationId()))
								{
									divisionId=(eachOrg.getOrganizationId());
									divisionDesc=eachOrg.getDescription();
									programUnitId=(eachOrgChild2.getOrganizationId());
									programUnitDesc=eachOrgChild2.getDescription();
									programSectionId=(eachOrgChild3.getOrganizationId());
									programSectionDesc=(eachOrgChild3.getDescription());
									return;
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * @param organizations The organizations to set.
	 */
	public void setOrganizations(Collection organizations) {
		this.organizations = organizations;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(IPhoneNumber phone) {
		this.phone = phone;
	}
	/**
	 * @param positionAssignedDate The positionAssignedDate to set.
	 */
	public void setPositionAssignedDate(Date positionAssignedDate) {
		this.positionAssignedDate = positionAssignedDate;
	}
	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @param positionStatusDesc The positionStatusDesc to set.
	 */
	public void setPositionStatusDesc(String positionStatusDesc) {
		this.positionStatusDesc = positionStatusDesc;
	}
	/**
	 * @param positionStatusId The positionStatusId to set.
	 */
	public void setPositionStatusId(String positionStatusId) {
		this.positionStatusId = positionStatusId;
		/*positionStatusDesc="";
		Collection myPosStatus=getPositionStatuses();
		if(myPosStatus!=null && myPosStatus.size()>0){
			//positionStatusDesc=SimpleCodeTableHelper.getDescrByCode((List)myPosStatus,positionStatusId);
			positionStatusDesc = this.getCodeDescriptionFromAlpha((List) myPosStatus, positionStatusId);
		}*/
	}
	/**
	 * @param positionTypeDesc The positionTypeDesc to set.
	 */
	public void setPositionTypeDesc(String positionTypeDesc) {
		this.positionTypeDesc = positionTypeDesc;
	}
	/**
	 * @param positionTypeId The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
		/*positionTypeDesc="";
		Collection myList=getPositionTypes();
		if(myList!=null && myList.size()>0){
			//positionTypeDesc=SimpleCodeTableHelper.getDescrByCode((List)myList,positionTypeId);
		    positionTypeDesc = this.getCodeDescriptionFromAlpha((List) myList, positionTypeId);
		}*/
	}
	/**
	 * @param probOfficerInd The probOfficerInd to set.
	 */
	public void setProbOfficerInd(String probOfficerInd) {
		this.probOfficerInd = probOfficerInd;
	}
	/**
	 * @param programSectionDesc The programSectionDesc to set.
	 */
	public void setProgramSectionDesc(String programSectionDesc) {
		this.programSectionDesc = programSectionDesc;
	}
	/**
	 * @param programSectionId The programSectionId to set.
	 */
	public void setProgramSectionId(String programSectionId) {
		this.programSectionId = programSectionId;
		programSectionDesc=UIAdminStaffHelper.getOrganizationName(programSectionId,organizations);
	}
	/**
	 * @param programUnitDesc The programUnitDesc to set.
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
		programUnitDesc=UIAdminStaffHelper.getOrganizationName(programUnitId,organizations);
	}
	/**
	 * @param isRetired The isRetired to set.
	 */
	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	/**
	 * @param selectedCourts The selectedCourts to set.
	 */
	public void setSelectedCourts(Collection selectedCourts) {
		this.selectedCourts = selectedCourts;
	}
	/**
	 * @param subordinates The subordinates to set.
	 */
	public void setSubordinates(ArrayList subordinates) {
		this.subordinates = subordinates;
	}
	/**
	 * @param supervisor The supervisor to set.
	 */
	public void setSupervisor(Position supervisor) {
		this.supervisor = supervisor;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}


	public String getEffectiveDateAsStr() {
		return effectiveDateAsStr;
	}


	public void setEffectiveDateAsStr(String effectiveDateAsStr) {
		this.effectiveDateAsStr = effectiveDateAsStr;
	}

}// END CLASS
