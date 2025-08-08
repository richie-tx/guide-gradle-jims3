/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.adminstaff.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.contact.domintf.IName;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.UIAdminStaffHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminStaffSearchForm extends ActionForm {
	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	
	
	// FORM SPECIFIC VARIABLES
	private String userId="";
	private IName name=new Name();
	private String cjad="";
	private String positionName="";
	//JIMS200040641
	private String officerTypeId="";
	private String officerTypeDesc="";
	private String divisionId="";
	private String divisionDesc="";
	private String programUnitId="";
	private String programUnitDesc="";
	private String programSectionId="";
	private String programSectionDesc="";
	private String workgroupName="";
	
	private String organizationId="";
	
	private String userSearchUserId="";
	private IName userSearchName=new Name();
	private String userSearchCjad="";
	private String userSearchOffTypeId="";
	private String userSearchOffTypeDesc="";
	private String userSearchWorkgroupName="";
	
	private ArrayList foundPositions=null;  // an arrayList of Position Objects
	private ArrayList foundUsers=null;  // an arrayList of Position Objects
	
	private static Collection organizations=null;  //group of organizations
	private static Collection cstsOfficerTypes=null;  //group of organizations
	
	public void clearDefaultFormValues(){
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}

	public void clearPosSearch(){
		userId="";
		name=new Name();;
		cjad="";
		positionName="";
		//JIMS200040641
		officerTypeId="";
		officerTypeDesc="";
		divisionId="";
		divisionDesc="";
		programUnitId="";
		programUnitDesc="";
		programSectionId="";
		programSectionDesc="";
		organizationId="";
		workgroupName="";
		foundPositions=new ArrayList();
		foundUsers=new ArrayList();
	}
	
	public void clearUserSearch(){
		userSearchUserId="";
		userSearchName=new Name();;
		userSearchCjad="";
		userSearchOffTypeDesc="";
		userSearchOffTypeId="";
		userSearchWorkgroupName="";
		foundUsers=new ArrayList();
	}
	
	public void clearAll(){
		clearDefaultFormValues();
		clearPosSearch();
		clearUserSearch();
	}
	
	public void clearFoundPositions(){
		foundPositions=new ArrayList();
	}
	
	public void clearFoundUsers(){
		foundUsers=new ArrayList();
	}
	
	public String getPosSearchString(){
		StringBuffer myFinalBuf=new StringBuffer();
		boolean oneEntered=false;
		if(getUserId()!=null && !(getUserId().equals(""))){
			myFinalBuf.append("User ID: <b>" + getUserId() + "</b> ");
			oneEntered=true;
		}
		if(getName()!=null){
			if (getName().getLastName()!=null && !(getName().getLastName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("Last Name: <b>" + getName().getLastName() + "</b> ");
			}
			if (getName().getFirstName()!=null && !(getName().getFirstName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("First Name: <b>" + getName().getFirstName() + "</b> ");
			}
			if (getName().getMiddleName()!=null && !(getName().getMiddleName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("Middle Name: <b>" + getName().getMiddleName() + "</b> ");
			}
		}
		if(getCjad()!=null && !(getCjad().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("CJAD: <b>" + getCjad() + "</b> ");
		}
		if(getOfficerTypeDesc()!=null && !(getOfficerTypeDesc().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("CSTS Officer Type: <b>" + getOfficerTypeDesc() + "</b> ");
		}
		if(getPositionName()!=null && !(getPositionName().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Position Name: <b>" + getPositionName() + "</b> ");
		}
		
		if(getDivisionDesc()!=null && !(getDivisionDesc().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Division: <b>" + getDivisionDesc() + "</b> ");
		}
		if(getProgramUnitDesc()!=null && !(getProgramUnitDesc().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Program Unit: <b>" + getProgramUnitDesc() + "</b> ");
		}
		if(getProgramSectionDesc()!=null && !(getProgramSectionDesc().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Program Section: <b>" + getProgramSectionDesc() + "</b> ");
		}
		if(getWorkgroupName()!=null && !(getWorkgroupName().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Workgroup: <b>" + getWorkgroupName() + "</b> ");
		}
		
		return myFinalBuf.toString();
	}
	
	
	public String getUserSearchString(){
		StringBuffer myFinalBuf=new StringBuffer();
		boolean oneEntered=false;
		if(getUserSearchUserId()!=null && !(getUserSearchUserId().equals(""))){
			oneEntered=true;
			myFinalBuf.append("User ID: <b>" + getUserSearchUserId() + "</b> ");
		}
		if(getName()!=null){
			if (getUserSearchName().getLastName()!=null && !(getUserSearchName().getLastName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("Last Name: <b>" + getUserSearchName().getLastName() + "</b> ");
			}
			if (getUserSearchName().getFirstName()!=null && !(getUserSearchName().getFirstName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("First Name: <b>" + getUserSearchName().getFirstName() + "</b> ");
			}
			if (getUserSearchName().getMiddleName()!=null && !(getUserSearchName().getMiddleName().equals(""))){
				if(oneEntered)
					myFinalBuf.append(", ");
				else{
					oneEntered=true;
				}
				myFinalBuf.append("Middle Name: <b>" + getUserSearchName().getMiddleName() + "</b> ");
			}
		}
		if(getUserSearchCjad()!=null && !(getUserSearchCjad().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("CJAD: <b>" + getUserSearchCjad() + "</b> ");
		}
		
		if(getUserSearchWorkgroupName()!=null && !(getUserSearchWorkgroupName().equals(""))){
			if(oneEntered)
				myFinalBuf.append(", ");
			else{
				oneEntered=true;
			}
			myFinalBuf.append("Workgroup: <b>" + getUserSearchWorkgroupName() + "</b> ");
		}
		
		return myFinalBuf.toString();
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
	 * @return Returns the cjad.
	 */
	public String getCjad() {
		return cjad;
	}
	/**
	 * @param cjad The cjad to set.
	 */
	public void setCjad(String cjad) {
		this.cjad = cjad;
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
	 * @return Returns the officerTypeDesc.
	 */
	
	//JIMS200040641
	public String getOfficerTypeDesc() {
		return officerTypeDesc;
	}
	/**
	 * @param officerTypeDesc The officerTypeDesc to set.
	 */
	public void setOfficerTypeDesc(String officerTypeDesc) {
		this.officerTypeDesc = officerTypeDesc;
	}
	/**
	 * @return Returns the officerTypeId.
	 */
	public String getOfficerTypeId() {
		return officerTypeId;
	}
	/**
	 * @param officerTypeId The officerTypeId to set.
	 */
	public void setOfficerTypeId(String officerTypeId) {
		this.officerTypeId = officerTypeId;
		officerTypeDesc="";
		Collection myList=getCSTSOfficerTypes();
		if(myList!=null && myList.size()>0){
			officerTypeDesc=SimpleCodeTableHelper.getDescrByCode((List)myList,officerTypeId);
		}
	}
	
	public Collection getCSTSOfficerTypes(){
			 return ComplexCodeTableHelper.getCSTSOfficerTypes(SecurityUIHelper.getUserAgencyId());
		
	}
	
	public static Collection getStaticOfficerTypes() {
		if(cstsOfficerTypes==null || cstsOfficerTypes.size()<1){
			cstsOfficerTypes=ComplexCodeTableHelper.getCSTSOfficerTypes(SecurityUIHelper.getUserAgencyId());
		}
		return cstsOfficerTypes;
	}
	
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	
	
	
	
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
		divisionDesc=UIAdminStaffHelper.getOrganizationName(divisionId,organizations);
	}
	
	
	/**
	 * @return Returns the divisionDesc.
	 */
	public String getDivisionDesc() {
		return divisionDesc;
	}
	/**
	 * @param divisionDesc The divisionDesc to set.
	 */
	public void setDivisionDesc(String divisionDesc) {
		this.divisionDesc = divisionDesc;
	}
	/**
	 * @return Returns the foundPositions.
	 */
	public ArrayList getFoundPositions() {
		return foundPositions;
	}
	/**
	 * @param foundPositions The foundPositions to set.
	 */
	public void setFoundPositions(ArrayList foundPositions) {
		this.foundPositions = foundPositions;
	}
	/**
	 * @return Returns the name.
	 */
	public IName getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(IName name) {
		this.name = name;
	}
	/**
	 * @return Returns the positionDesc.
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @param positionDesc The positionDesc to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @return Returns the programSectionId.
	 */
	public String getProgramSectionId() {
		return programSectionId;
		
	}
	/**
	 * @param programSectionId The programSectionId to set.
	 */
	public void setProgramSectionId(String programSectionId) {
		this.programSectionId = programSectionId;
		programSectionDesc=UIAdminStaffHelper.getOrganizationName(programSectionId,organizations);
	}
	/**
	 * @return Returns the programSectionDesc.
	 */
	public String getProgramSectionDesc() {
		return programSectionDesc;
	}
	/**
	 * @param programSectionDesc The programSectionDesc to set.
	 */
	public void setProgramSectionDesc(String programSectionDesc) {
		this.programSectionDesc = programSectionDesc;
	}
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
		programUnitDesc=UIAdminStaffHelper.getOrganizationName(programUnitId,organizations);
	}
	/**
	 * @return Returns the programUnitDesc.
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}
	/**
	 * @param programUnitDesc The programUnitDesc to set.
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
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
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the workgroupDesc.
	 */
	public String getWorkgroupName() {
		return workgroupName;
	}
	/**
	 * @param workgroupDesc The workgroupDesc to set.
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}
	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl() {
		return emptyColl;
	}
	/**
	 * @return Returns the foundUsers.
	 */
	public ArrayList getFoundUsers() {
		return foundUsers;
	}
	/**
	 * @param foundUsers The foundUsers to set.
	 */
	public void setFoundUsers(ArrayList foundUsers) {
		this.foundUsers = foundUsers;
	}
	/**
	 * @return Returns the userSearchCjad.
	 */
	public String getUserSearchCjad() {
		return userSearchCjad;
	}
	/**
	 * @param userSearchCjad The userSearchCjad to set.
	 */
	public void setUserSearchCjad(String userSearchCjad) {
		this.userSearchCjad = userSearchCjad;
	}
	/**
	 * @return Returns the userSearchName.
	 */
	public IName getUserSearchName() {
		return userSearchName;
	}
	/**
	 * @param userSearchName The userSearchName to set.
	 */
	public void setUserSearchName(IName userSearchName) {
		this.userSearchName = userSearchName;
	}
	/**
	 * @return Returns the userSearchUserId.
	 */
	public String getUserSearchUserId() {
		return userSearchUserId;
	}
	/**
	 * @param userSearchUserId The userSearchUserId to set.
	 */
	public void setUserSearchUserId(String userSearchUserId) {
		this.userSearchUserId = userSearchUserId;
	}
	/**
	 * @return Returns the userSearchWorkgroupName.
	 */
	public String getUserSearchWorkgroupName() {
		return userSearchWorkgroupName;
	}
	/**
	 * @param userSearchWorkgroupName The userSearchWorkgroupName to set.
	 */
	public void setUserSearchWorkgroupName(String userSearchWorkgroupName) {
		this.userSearchWorkgroupName = userSearchWorkgroupName;
	}
	/**
	 * @return Returns the organizations.
	 */
	public Collection getOrganizations() {
		if(organizations==null || organizations.size()<1){
			organizations=UIAdminStaffHelper.getActiveOrganizationalHeirarchy();
		}
		return organizations;
	}
	
	/**
	 * @return Returns the organizations.
	 */
	public static Collection getStaticOrganizations() {
		if(organizations==null || organizations.size()<1){
			organizations=UIAdminStaffHelper.getActiveOrganizationalHeirarchy();
		}
		return organizations;
	}
	/**
	 * @param organizations The organizations to set.
	 */
	public void setOrganizations(Collection aOrganizations) {
		organizations = aOrganizations;
	}
	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		return organizationId;
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
	 * @return Returns the userSearchOffTypeDesc.
	 */
	public String getUserSearchOffTypeDesc() {
		return userSearchOffTypeDesc;
	}
	/**
	 * @param userSearchOffTypeDesc The userSearchOffTypeDesc to set.
	 */
	public void setUserSearchOffTypeDesc(String userSearchOffTypeDesc) {
		this.userSearchOffTypeDesc = userSearchOffTypeDesc;
	}
	/**
	 * @return Returns the userSearchOffTypeId.
	 */
	public String getUserSearchOffTypeId() {
		return userSearchOffTypeId;
	}
	/**
	 * @param userSearchOffTypeId The userSearchOffTypeId to set.
	 */
	public void setUserSearchOffTypeId(String userSearchOffTypeId) {
		this.userSearchOffTypeId = userSearchOffTypeId;
		userSearchOffTypeDesc="";
		Collection myList=getCSTSOfficerTypes();
		if(myList!=null && myList.size()>0){
			userSearchOffTypeDesc=SimpleCodeTableHelper.getDescrByCode((List)myList,userSearchOffTypeId);
		}
	}
}// END CLASS
