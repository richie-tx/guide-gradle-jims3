package ui.security.form;

import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import messaging.security.reply.RoleResponseEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.PropertyCopier;

import org.apache.struts.action.ActionMapping;

/**
 * @author hrodriguez - Created on Apr 5, 2004  
 * 
 * This form contains all the attributes needed for adding, 
 * updating, deleting and retrieving a role.
 */
public class RoleForm extends GenericSecurityForm
{

	private String action;
	private String agencyCode;
	private String agencyFindErrorMessage;
	private String agencyId;
	private String agencyName;
	private String agencySearchResultSize;
	private String agencyTypeId;
	private String errorMessage;
	private String everyOneRoleName;
	private String featureCategory;
	private String featureCategoryId;
	private String featureFindErrorMessage;	
	private String featureName;
	private String featureSearchResultSize;
	private String firstName;
	private String jmcRepId;
	private String lastName;
	private String masterAdmin; 
	private String originalRoleName;
	private String refreshButton;
	private String roleCreator;
	private String roleCreatorId;
	private String roleDescription;
	private String roleId;
	private String roleName;
	private String roleType;
	private String roleTypeId;
	private String featuresSearched;	
	private String searchResultSize;
	private String[] selectedAgencies;
	private String[] selectedFeatures; 
	private String setcicAccessId;	
	private String subSystemName;
	private String userID;
	private boolean agencyUpdatable = false;

	private Collection agencyTypes;	
	private Collection availableFeatures;
	private Collection currentFeatures;
	private Collection everyoneRoles;
	private Collection features;
	private Collection jims2Subsystems;	
	private Collection jmcReps;	
	private Collection notAssociatedFeatures;
	private Collection roles;
	private Collection roleTypes;
	private Collection roleNames;	
	private Collection selectedRoleNames;
	private Collection setcicAccesses;
	private Collection userNames;

	/**
	* Constructor for the RoleForm 
	*/
	public RoleForm()
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
	 * @param event
	 */
	public void reset(ActionMapping arg0, ServletRequest arg1)
	{
		super.reset(arg0, arg1);
	}
	
	/**
	 * @param event
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		String actionRequest = aRequest.getParameter("action");
		if (actionRequest != null && "modify".equals(actionRequest))
		{
			RoleResponseEvent roleEvent = (RoleResponseEvent) aRequest.getAttribute("roleEvent");
			PropertyCopier.copyProperties(roleEvent, this);
		}
	}
	
	/**
	 * @param event
	 */
	public void clear()
	{
		super.clear();
		
		this.action = null;
		this.agencyCode = null;		
		this.agencyFindErrorMessage = null;
		this.agencyId = null;
		this.agencyName = null;
		this.agencySearchResultSize = null;
		this.agencyTypeId = null;
		this.errorMessage = null;
		this.everyOneRoleName = null;
		this.featureCategory = null;
		this.featureCategoryId = null;
		this.featureFindErrorMessage = null;
		this.featureName = null;
		this.featuresSearched = null;
		this.featureSearchResultSize = null;
		this.firstName = null;
		this.jmcRepId = null;
		this.lastName = null;
		this.masterAdmin = null;
		this.refreshButton = null;
		this.roleCreator = null;
		this.roleCreatorId = null;
		this.roleDescription = null;
		this.roleId = null;
		this.roleName = null;
		this.roles = null;
		this.roleType = null;		
		this.roleTypeId = null;
		this.searchResultSize = null;
		clearStringArray(selectedAgencies);
		clearStringArray(selectedFeatures);
		this.setcicAccessId = null;
		this.subSystemName = null;
		this.userID = null;
		
// collections
		this.agencyTypes = null;
		this.availableFeatures = null;
		this.currentFeatures = null;
		this.everyoneRoles = null;
		this.features = null;
//		this.jims2Subsystems = null;
		this.jmcReps = null;
		this.notAssociatedFeatures = null;
		this.roleNames = null;
		this.roles = null;
		this.roleTypes = null;
		this.selectedRoleNames = null;
		this.setcicAccesses = null;
		this.userNames = null;
	}

	/**
	 * @return String roleId
	 */
	public String getRoleId()
	{
		return roleId;
	}

	/**
	 * @return String roleType
	 */
	public String getRoleType()
	{
		return roleType;
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
	public void setRoleId(String string)
	{
		roleId = string;
	}

	/**
	 * @param string
	 */
	public void setRoleType(String string)
	{
		roleType = string;
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
	 * @return String roleTypeId
	 */
	public String getRoleTypeId()
	{
		return roleTypeId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setRoleTypeId(String string)
	{
		roleTypeId = string;
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
	 * @return Collection roleTypes
	 */
	public Collection getRoleTypes()
	{
		roleTypes = MessageUtil.processEmptyCollection(roleTypes);	
		return roleTypes;
	}

	/**
	 * @param collection
	 */
	public void setRoleTypes(Collection collection)
	{
		roleTypes = collection;
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
	 * @return String roleCreator
	 */
	public String getRoleCreator()
	{
		return roleCreator;
	}

	/**
	 * @return String roleCreatorId
	 */
	public String getRoleCreatorId()
	{
		return roleCreatorId;
	}

	/**
	 * @param string
	 */
	public void setRoleCreator(String string)
	{
		roleCreator = string;
	}

	/**
	 * @param string
	 */
	public void setRoleCreatorId(String string)
	{
		roleCreatorId = string;
	}

	/**
	 * @return Collection users
	 */
	public Collection getUserNames()
	{
		userNames = MessageUtil.processEmptyCollection(userNames);			
		return userNames;
	}

	/**
	 * @param collection
	 */
	public void setUserNames(Collection collection)
	{
		userNames = collection;
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
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
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
	 * @return
	 */
	public Collection getSelectedRoleNames()
	{
		selectedRoleNames = MessageUtil.processEmptyCollection(selectedRoleNames);	
		return selectedRoleNames;
	}

	/**
	 * @param collection
	 */
	public void setRoleNames(Collection collection)
	{
		roleNames = collection;
	}

	/**
	 * @param collection
	 */
	public void setSelectedRoleNames(Collection collection)
	{
		selectedRoleNames = collection;
	}

	/**
	 * @return
	 */
	public String getMasterAdmin()
	{
		return masterAdmin;
	}

	/**
	 * @param string
	 */
	public void setMasterAdmin(String string)
	{
		masterAdmin = string;
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
	public Collection getAgencyTypes()
	{
		agencyTypes = MessageUtil.processEmptyCollection(agencyTypes);	
		return agencyTypes;
	}

	/**
	 * @param collection
	 */
	public void setAgencyTypes(Collection collection)
	{
		agencyTypes = collection;
	}

	/**
	 * @return
	 */
	public String getFeatureName()
	{
		return featureName;
	}

	/**
	 * @return
	 */
	public Collection getFeatures()
	{
		features = MessageUtil.processEmptyCollection(features);	
		return features;
	}

	/**
	 * @return
	 */
	public String getSubSystemName()
	{
		return subSystemName;
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
	 * @param string
	 */
	public void setSubSystemName(String string)
	{
		subSystemName = string;
	}

	/**
	 * @return
	 */
	public String getJmcRepId()
	{
		return jmcRepId;
	}

	/**
	 * @return
	 */
	public Collection getJmcReps()
	{
		jmcReps = MessageUtil.processEmptyCollection(jmcReps);			
		return jmcReps;
	}

	/**
	 * @return
	 */
	public String getSetcicAccessId()
	{
		return setcicAccessId;
	}

	/**
	 * @return
	 */
	public Collection getSetcicAccesses()
	{
		setcicAccesses = MessageUtil.processEmptyCollection(setcicAccesses);			
		return setcicAccesses;
	}

	/**
	 * @param string
	 */
	public void setJmcRepId(String string)
	{
		jmcRepId = string;
	}

	/**
	 * @param collection
	 */
	public void setJmcReps(Collection collection)
	{
		jmcReps = collection;
	}

	/**
	 * @param string 
	 */
	public void setSetcicAccessId(String string)
	{
		setcicAccessId = string;
	}

	/**
	 * @param collection
	 */
	public void setSetcicAccesses(Collection collection)
	{
		setcicAccesses = collection;
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
	 * @return
	 */
	public Collection getCurrentFeatures()
	{
		currentFeatures = MessageUtil.processEmptyCollection(currentFeatures);			
		return currentFeatures;
	}

	/**
	 * @return
	 */
	public String getFeatureCategory()
	{
		return featureCategory;
	}

	/**
	 * @return
	 */
	public String getFeatureSearchResultSize()
	{
		return featureSearchResultSize;
	}

	/**
	 * @param collection
	 */
	public void setAvailableFeatures(Collection collection)
	{
		availableFeatures = collection;
	}

	/**
	 * @param collection
	 */
	public void setCurrentFeatures(Collection collection)
	{
		currentFeatures = collection;
	}

	/**
	 * @param string
	 */
	public void setFeatureCategory(String string)
	{
		featureCategory = string;
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
	public String getAgencySearchResultSize()
	{
		return agencySearchResultSize;
	}

	/**
	 * @param string
	 */
	public void setAgencySearchResultSize(String string)
	{
		agencySearchResultSize = string;
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
	public String getEveryOneRoleName()
	{
		return everyOneRoleName;
	}

	/**
	 * @param string
	 */
	public void setEveryOneRoleName(String string)
	{
		everyOneRoleName = string;
	}

	/**
	 * @return
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
	public Collection getEveryoneRoles()
	{
		everyoneRoles = MessageUtil.processEmptyCollection(everyoneRoles);			
		return everyoneRoles;
	}

	/**
	 * @param collection
	 */
	public void setEveryoneRoles(Collection collection)
	{
		everyoneRoles = collection;
	}
	/**
	 * @return
	 */
	public String getOriginalRoleName()
	{
		return originalRoleName;
	}

	/**
	 * @param string
	 */
	public void setOriginalRoleName(String string)
	{
		originalRoleName = string;
	}
	/**
	 * @return
	 */
	public boolean isAgencyUpdatable()
	{
		return agencyUpdatable;
	}

	/**
	 * @param b
	 */
	public void setAgencyUpdatable(boolean b)
	{
		agencyUpdatable = b;
	}

	/**
	 * @return
	 */
	public String getAgencyFindErrorMessage()
	{
		return agencyFindErrorMessage;
	}

	/**
	 * @return
	 */
	public String getFeatureFindErrorMessage()
	{
		return featureFindErrorMessage;
	}

	/**
	 * @param string
	 */
	public void setAgencyFindErrorMessage(String string)
	{
		agencyFindErrorMessage = string;
	}

	/**
	 * @param string
	 */
	public void setFeatureFindErrorMessage(String string)
	{
		featureFindErrorMessage = string;
	}
	
    /**
     * @return Returns the refreshButton.
     */
    public String getRefreshButton() {
        return refreshButton;
    }
    /**
     * @param refreshButton The refreshButton to set.
     */
    public void setRefreshButton(String refreshButton) {
        this.refreshButton = refreshButton;
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
	/**
	 * @return Returns the jims2Subsystems.
	 */
	public Collection getJims2Subsystems() {
		jims2Subsystems = MessageUtil.processEmptyCollection(jims2Subsystems);			
		return jims2Subsystems;
	}
	/**
	 * @param jims2Subsystems The jims2Subsystems to set.
	 */
	public void setJims2Subsystems(Collection jims2Subsystems) {
		this.jims2Subsystems = jims2Subsystems;
	}
	/**
	 * @return Returns the agencyCode.
	 */
	public String getAgencyCode() {
		return agencyCode;
	}
	/**
	 * @param agencyCode The agencyCode to set.
	 */
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
}
