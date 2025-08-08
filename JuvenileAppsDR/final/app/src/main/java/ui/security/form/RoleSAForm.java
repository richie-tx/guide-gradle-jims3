package ui.security.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletRequest;

import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cShimek - Created on 03/31/2005  
 * 
 * This form contains all the attributes needed for creteing, 
 * updating, deleting and retrieving a SA Roles.
 */
public class RoleSAForm extends ActionForm
{
	private String action;
	private String agencyId;
	private String agencyName;
	private String agencySearchResultSize;
	private String agencyType;
	private String agencyTypeId;
	private String allSecAdminNameInd;
	private String allSecurityAdmin;
	private String backCreate;
	private String department;
	private String errorMessage; 
	private String featureCategory;
	private String featureCategoryId;
	private String featureName;
	private String featuresSearched;
	private String featureSearchResultSize;
	private String jmcRep;
	private String jmcRepId; 
	private String roleDescription;
	private String roleId;
	private String roleName;
	private String[] selectedAgencies;
	private String[] selectedFeatures;
	private String searchResultSize;
	private String setcicId;
	private String setcicDescription;
	private String subSystemName;
	private String userID;

	private boolean updateFeatureFlag;
	private boolean updateAgencyFlag;

//	collections for display and shopping cart 	
	private Collection agencyIds;
	private Collection allSecurityAdminRoles;	
	private Collection availableFeatures;
	private Collection currentFeatures;	
	private Collection features;
	private Collection featuresSelected;
	private Collection jmcAgencies;
	private Collection notAssociatedFeatures;
	private Collection roles;
	private Collection roleNames;
	private Collection userGroups;
	private Collection userNames;
	private Collection users;

// collections for drop downs
	private Collection agencies;
	private Collection agencyTypes;
	private Collection jmcRepresentatives;
	private Collection setcicAccesses;
	private Collection jims2Subsystems;	
	
	
	/**
	* Constructor for the RoleSAForm 
	*/
	public RoleSAForm()
	{
		super();
	}

	/**
	 * @param array
	 */
	public void clearStringArray(String[] array)
	{
		if (array == null)
		{
			return;
		}
		for (int i = 0; i < array.length; i++)
		{
			array[i] = "";
		}
	}
	/** 
	 * @see ActionForm#reset(ActionMapping, ServletRequest)
	 */
	public void reset(ActionMapping aMapping, ServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		clear();
	}	

	/**
	 * @param event
	 */
	public void clear()
	{
		this.action = null;
		this.agencyId = null;
		this.agencyName = null;
		this.agencySearchResultSize = null;
		this.agencyType = null;
		this.agencyTypeId = null;
		this.allSecAdminNameInd = null;
		this.allSecurityAdmin = null;
		this.backCreate = null;
		this.department = null;
		this.errorMessage = null;
		this.featureCategory = null;
		this.featureName = null;
		this.featuresSearched = null;
		this.featureSearchResultSize = null;
		this.jmcRep = null;		
		this.jmcRepId = null;
		this.roleDescription = null;
		this.roleId = null;
		this.roleName = null;
		this.searchResultSize = null;
		clearStringArray(selectedAgencies);
		clearStringArray(selectedFeatures);
		this.setcicId = null;
		this.setcicDescription = null;
		this.subSystemName = null;
		this.userID = null;

/** clear collections used in FIND */
		this.agencies = null;
		this.agencyIds = null;
		this.allSecurityAdminRoles = null;
		this.availableFeatures = null;
		this.currentFeatures = null;
		this.features = null;
		this.featuresSelected = null;
		this.jmcAgencies = null;
		this.notAssociatedFeatures = null;
		this.roles = null;
		this.roleNames = null;
		this.users = null;
		this.userGroups = null;
		this.userNames = null;		
	}



	/**
	 * @return String userId
	 */
	public String getUserID()
	{
		return userID;
	}

	/**
	 * @param string
	 */
	public void setUserID(String string)
	{
		userID = string;
	}

	/**
	 * @return String roleDescription
	 */
	public String getRoleDescription()
	{
		return roleDescription;
	}

	/**
	 * @param string
	 */
	public void setRoleDescription(String string)
	{
		roleDescription = string;
	}

	/**
	 * @return Collection agencies
	 */
	public Collection getAgencies()
	{
		agencies = MessageUtil.processEmptyCollection(agencies);
		Collections.sort((ArrayList) agencies);
		return agencies;
	}

	/**
	 * @param collection
	 */
	public void setAgencies(Collection collection)
	{
		agencies = collection;
	}

	/**
	 * @return String roleName
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		roleName = string;
	}

	/**
	 * @return String agencyName
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @return String agencyId
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @return String action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @return String[] selectedSystemActivities
	 */
	public String[] getSelectedFeatures()
	{
		return selectedFeatures;
	}

	/**
	 * @param strings
	 */
	public void setSelectedFeatures(String[] strings)
	{
		selectedFeatures = strings;
	}

	/**
	 * @return String searchResultSize
	 */
	public String getSearchResultSize()
	{
		return searchResultSize;
	}

	/**
	 * @param string
	 */
	public void setSearchResultSize(String string)
	{
		searchResultSize = string;
	}
	/**
	 * @return Collection jmcRepresentatives
	 */
	public Collection getJmcRepresentatives()
	{
		jmcRepresentatives = MessageUtil.processEmptyCollection(jmcRepresentatives);
		return jmcRepresentatives;
	}

	/**
	 * @return Collection setcicAccesses
	 */
	public Collection getSetcicAccesses()
	{
		setcicAccesses = MessageUtil.processEmptyCollection(setcicAccesses);
		return setcicAccesses;
	}

	/**
	 * @return String setcicId
	 */
	public String getSetcicId()
	{
		return setcicId;
	}

	/**
	 * @return String subSystemName
	 */
	public String getSubSystemName()
	{
		return subSystemName;
	}

	/**
	 * @return Collection userNames
	 */
	public Collection getUserNames()
	{
		userNames = MessageUtil.processEmptyCollection(userNames);
		return userNames;
	}

	/**
	 * @param collection
	 */
	public void setJmcRepresentatives(Collection collection)
	{
		jmcRepresentatives = collection;
	}

	/**
	 * @param collection
	 */
	public void setSetcicAccesses(Collection collection)
	{
		setcicAccesses = collection;
	}

	/**
	 * @param string
	 */
	public void setSetcicId(String string)
	{
		setcicId = string;
	}

	/**
	 * @param string
	 */
	public void setSubSystemName(String string)
	{
		subSystemName = string;
	}

	/**
	 * @param collection
	 */
	public void setUserNames(Collection collection)
	{
		userNames = collection;
	}

	/**
	 * @return String agencyType
	 */
	public String getAgencyType()
	{
		return agencyType;
	}

	/**
	 * @return Collection agencyTypes
	 */
	public Collection getAgencyTypes()
	{
		agencyTypes = MessageUtil.processEmptyCollection(agencyTypes);
		return agencyTypes;
	}

	/**
	 * @return String department
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * @return String featureName
	 */
	public String getFeatureName()
	{
		return featureName;
	}

	/**
	 * @return Collection features
	 */
	public Collection getFeatures()
	{
		features = MessageUtil.processEmptyCollection(features);
		return features;
	}

	/**
	 * @return Collection featuresSelected
	 */
	public Collection getFeaturesSelected()
	{
		featuresSelected = MessageUtil.processEmptyCollection(featuresSelected);
		return featuresSelected;
	}

	/**
	 * @return String jmcRepId
	 */
	public String getJmcRepId()
	{
		return jmcRepId;
	}

	/**
	 * @param string
	 */
	public void setAgencyType(String string)
	{
		agencyType = string;
	}

	/**
	* @param collection
	*/
	public void setAgencyTypes(Collection collection)
	{
		agencyTypes = collection;
	}

	/**
	 * @param string
	 */
	public void setDepartment(String string)
	{
		department = string;
	}

	/**
	 * @param string
	 */
	public void setFeatureName(String string)
	{
		featureName = string;
	}

	/**
	 * @param collection
	 */
	public void setFeatures(Collection collection)
	{
		features = collection;
	}

	/**
	 * @param collection
	 */
	public void setFeaturesSelected(Collection collection)
	{
		featuresSelected = collection;
	}

	/**
	 * @param string
	 */
	public void setJmcRepId(String string)
	{
		jmcRepId = string;
	}

	/**
	 * @return
	 */
	public Collection getAvailableFeatures()
	{
		availableFeatures = MessageUtil.processEmptyCollection(availableFeatures);
		return availableFeatures;
	}

	/**
	 * @param collection
	 */
	public void setAvailableFeatures(Collection collection)
	{
		availableFeatures = collection;
	}

	/**
	 * @return
	 */
	public String getAgencySearchResultSize()
	{
		return agencySearchResultSize;
	}

	/**
	 * @return
	 */
	public String getFeatureSearchResultSize()
	{
		return featureSearchResultSize;
	}

	/**
	 * @param string
	 */
	public void setAgencySearchResultSize(String string)
	{
		agencySearchResultSize = string;
	}

	/**
	 * @param string
	 */
	public void setFeatureSearchResultSize(String string)
	{
		featureSearchResultSize = string;
	}

	/**
	 * @return
	 */
	public Collection getRoleNames()
	{
		roleNames = MessageUtil.processEmptyCollection(roleNames);
		return roleNames;
	}

	/**
	 * @param collection
	 */
	public void setRoleNames(Collection collection)
	{
		roleNames = collection;
	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypeId(String string)
	{
		agencyTypeId = string;
	}

	/**
	 * @return
	 */
	public String getSetcicDescription()
	{
		return setcicDescription;
	}

	/**
	 * @param string
	 */
	public void setSetcicDescription(String string)
	{
		setcicDescription = string;
	}

	/**
	 * @return
	 */
	public String getJmcRep()
	{
		return jmcRep;
	}

	/**
	 * @param string
	 */
	public void setJmcRep(String string)
	{
		jmcRep = string;
	}

	/**
	 * @return
	 */
	public Collection getJmcAgencies()
	{
		jmcAgencies = MessageUtil.processEmptyCollection(jmcAgencies);
		return jmcAgencies;
	}

	/**
	 * @param collection
	 */
	public void setJmcAgencies(Collection collection)
	{
		jmcAgencies = collection;
	}

	/**
	 * @return
	 */
	public String getFeaturesSearched()
	{
		return featuresSearched;
	}

	/**
	 * @param string
	 */
	public void setFeaturesSearched(String string)
	{
		featuresSearched = string;
	}

	/**
	 * @return 
	 */
	public String getBackCreate()
	{
		return backCreate;
	}

	/**
	 * @param string
	 */
	public void setBackCreate(String string)
	{
		backCreate = string;
	}

	/**
	 * @return
	 */
	public String getAllSecurityAdmin()
	{
		return allSecurityAdmin;
	}

	/**
	 * @param string
	 */
	public void setAllSecurityAdmin(String string)
	{
		allSecurityAdmin = string;
	}

	/**
	 * @return
	 */
	public Collection getAgencyIds()
	{
		agencyIds = MessageUtil.processEmptyCollection(agencyIds);
		return agencyIds;
	}

	/**
	 * @param collection
	 */
	public void setAgencyIds(Collection collection)
	{
		agencyIds = collection;
	}

	/**
	 * @return
	 */
	public boolean isUpdateAgencyFlag()
	{
		return updateAgencyFlag;
	}

	/**
	 * @return
	 */
	public boolean isUpdateFeatureFlag()
	{
		return updateFeatureFlag;
	}

	/**
	 * @param b
	 */
	public void setUpdateAgencyFlag(boolean b)
	{
		updateAgencyFlag = b;
	}

	/**
	 * @param b
	 */
	public void setUpdateFeatureFlag(boolean b)
	{
		updateFeatureFlag = b;
	}

	/**
	 * @return
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @param string
	 */
	public void setRoleId(String string)
	{
		roleId = string;
	}
	/**
	 * @return
	 */
	public Collection getCurrentFeatures()
	{
		currentFeatures = MessageUtil.processEmptyCollection(currentFeatures);
		return currentFeatures;
	}

	/**
	 * @param collection
	 */
	public void setCurrentFeatures(Collection collection)
	{
		currentFeatures = collection;
	}
	/**
	 * @return
	 */
	public String getAllSecAdminNameInd()
	{
		return allSecAdminNameInd;
	}

	/**
	 * @param string
	 */
	public void setAllSecAdminNameInd(String string)
	{
		allSecAdminNameInd = string;
	}

	/**
	 * @return
	 */
	public String getFeatureCategory()
	{
		return featureCategory;
	}

	/**
	 * @param string
	 */
	public void setFeatureCategory(String string)
	{
		featureCategory = string;
	}

	/**
	 * @return
	 */
	public Collection getRoles()
	{
		roles = MessageUtil.processEmptyCollection(roles);
		return roles;
	}

	/**
	 * @param collection
	 */
	public void setRoles(Collection collection)
	{
		roles = collection;
	}

	/**
	 * @return
	 */
	public Collection getUsers()
	{
		users = MessageUtil.processEmptyCollection(users);
		return users;
	}

	/**
	 * @param collection
	 */
	public void setUsers(Collection collection)
	{
		users = collection;
	}

	/**
	 * @return
	 */
	public Collection getNotAssociatedFeatures()
	{
		notAssociatedFeatures = MessageUtil.processEmptyCollection(notAssociatedFeatures);
		return notAssociatedFeatures;
	}

	/**
	 * @param collection
	 */
	public void setNotAssociatedFeatures(Collection collection)
	{
		notAssociatedFeatures = collection;
	}

	/**
	 * @return
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param string
	 */
	public void setErrorMessage(String string)
	{
		errorMessage = string;
	}

	/**
	 * @return
	 */
	public Collection getUserGroups()
	{
		userGroups = MessageUtil.processEmptyCollection(userGroups);
		return userGroups;
	}

	/**
	 * @param collection
	 */
	public void setUserGroups(Collection collection)
	{
		userGroups = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelectedAgencies()
	{
		return selectedAgencies;
	}

	/**
	 * @param strings
	 */
	public void setSelectedAgencies(String[] strings)
	{
		selectedAgencies = strings;
	}

	/**
	 * @return
	 */
	public Collection getAllSecurityAdminRoles()
	{
		allSecurityAdminRoles = MessageUtil.processEmptyCollection(allSecurityAdminRoles);
		return allSecurityAdminRoles;
	}

	/**
	 * @param collection
	 */
	public void setAllSecurityAdminRoles(Collection collection)
	{
		allSecurityAdminRoles = collection;
	}

	/**
	 * @return Returns the jims2Subsystems.
	 */
	public Collection getJims2Subsystems() {
		return jims2Subsystems;
	}
	/**
	 * @param jims2Subsystems The jims2Subsystems to set.
	 */
	public void setJims2Subsystems(Collection jims2Subsystems) {
		this.jims2Subsystems = jims2Subsystems;
	}
	/**
	 * @return Returns the featureCategoryId.
	 */
	public String getFeatureCategoryId() {
		return featureCategoryId;
	}
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setFeatureCategoryId(String featureCategoryId) {
		this.featureCategoryId = featureCategoryId;
	}
}
