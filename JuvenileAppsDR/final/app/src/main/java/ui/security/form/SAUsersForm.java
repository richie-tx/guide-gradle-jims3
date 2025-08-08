/*
 * Created on May 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.form;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.utilities.PropertyCopier;
import naming.PDSecurityConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ui.common.CodeHelper;
import ui.common.UIUtil;

public class SAUsersForm extends ActionForm
{

	private String lastName;
	private String firstName;
	private String middleName;
	private String logonId;
	private String agencyName;
	private String userTypeId;
	private String searchResultSize;
	
	private Collection userType;
	private Collection userTypes;
	private Collection users;
	private Collection userIndex;
	
	private UserResponseforUserAdministrationEvent selectedUser;
	private String selectedLogonId;
	
	private Collection selDepartments = new ArrayList();
	private Collection allDepartments = new ArrayList();
	private String[] selectedDepartments;
	private String[] selectedUserTypes;
	private String selectedUserTypeId;
	
	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
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
	public String getLogonId()
	{
		return logonId;
	}
	/*
	 * @param collection
	 */
	public void setAgencyName(String string)
	{

		agencyName = string;
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
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
		 * @return
		 */
	public Collection getUserType()
	{
		return userType;
	}

	/**
	 * @param collection
	 */
	public void setUserType(Collection collection)
	{
		userType = collection;
	}

	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		
		String actionRequest = aRequest.getParameter("action");
		/*if (actionRequest != null && "create".equals(actionRequest))
		{
			this.clear();
			this.action = actionRequest;
		}
		else*/
		if (actionRequest != null && "modify".equals(actionRequest))
		{
			AgencyResponseEvent AgencyEvent = (AgencyResponseEvent) aRequest.getAttribute("AgencyEvent");
			PropertyCopier.copyProperties(AgencyEvent, this);
		}
		selectedDepartments = null;
		selectedUserTypes = null;
		
	}
	/**
	 * @param event
	 */
	public void clear()
	{
		this.agencyName = null;
		this.logonId = null;
		this.firstName = null;
		this.lastName = null;
		this.middleName = null;
		this.userTypes = null;
		this.userType=null;
		this.users=null;
		this.searchResultSize=null;
		this.userTypeId=null;
		this.userIndex=null;
		selDepartments.clear();
		allDepartments.clear();
		selectedUserTypeId = null;
		selectedLogonId = null;
		selectedDepartments = null;
		selectedUserTypes = null;
	}

	/**
	 * @param userTypes
	 */
	public void setUserTypes(Collection userTypes)
	{
		this.userTypes = userTypes;
	}

	/**
	 * @return
	 */
	public Collection getUserTypes()
	{
		if(userTypes == null) {
			userTypes = new ArrayList();
		}
		return userTypes;
	}
	/** 
	 * @see ActionForm#reset(ActionMapping, javax.servlet.ServletRequest)
	 */
//	public void reset(ActionMapping aMapping, ServletRequest aRequest)
//	{
//		super.reset(aMapping, aRequest);
//		clear();
//	}

	public void clearUsers(){
		users.clear();
	}
	
	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
	}

	/**
	 * @return
	 */
	public Collection getUsers()
	{
		if(users == null) {
			users = new ArrayList();
		}
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
	 * @return
	 */
	public Collection getUserIndex()
	{
		return userIndex;
	}

	/**
	 * @param collection
	 */
	public void setUserIndex(Collection collection)
	{
		userIndex = collection;
	}
	
	
	/**
	 * @return
	 */
	public UserResponseforUserAdministrationEvent getSelectedUser()
	{
		return selectedUser;
	}

	/**
	 * @param event
	 */
	public void setSelectedUser(UserResponseforUserAdministrationEvent event)
	{
		selectedUser = event;
	}

	/**
	 * @return
	 */
	public String getSelectedLogonId()
	{
		return selectedLogonId;
	}

	/**
	 * @param string
	 */
	public void setSelectedLogonId(String string)
	{
		selectedLogonId = string;
	}

	/**
	 * @return
	 */
	public Collection getAllDepartments()
	{
		return allDepartments;
	}

	/**
	 * @return
	 */
	public String[] getSelectedDepartments()
	{
		return selectedDepartments;
	}

	/**
	 * @param collection
	 */
	public void setAllDepartments(Collection collection)
	{
		allDepartments = collection;
	}

	/**
	 * @param strings
	 */
	public void setSelectedDepartments(String[] strings)
	{
		selectedDepartments = strings;
	}

	/**
	 * @return
	 */
	public Collection getSelDepartments()
	{
		return selDepartments;
	}

	/**
	 * @param collection
	 */
	public void setSelDepartments(Collection collection)
	{
		selDepartments = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelectedUserTypes()
	{
		return selectedUserTypes;
	}

	/**
	 * @param strings
	 */
	public void setSelectedUserTypes(String[] strings)
	{
		selectedUserTypes = strings;
	}
	
	public String getUserTypeIdLA(){
		return PDSecurityConstants.USER_TYPE_LIASON;
	}
	
	public String getUserTypeIdASA(){
		return PDSecurityConstants.USER_TYPE_ASA;
	}

	public String getUserTypeIdSA(){
		return PDSecurityConstants.USER_TYPE_SA;
	}

	public String getUserTypeIdMA(){
		return PDSecurityConstants.USER_TYPE_MA;
	}
	
	/**
	 * @return
	 */
	public String getSelectedUserTypeId()
	{
		return selectedUserTypeId;
	}

	/**
	 * @param string
	 */
	public void setSelectedUserTypeId(String string)
	{
		selectedUserTypeId = string;
	}
	
	/**
	 * @return
	 */
	public String getSelectedUserTypeDesc()
	{
		if(selectedUserTypeId != null){
			Iterator ite = CodeHelper.getUserTypes().iterator();
			CodeResponseEvent evt = UIUtil.findCodeResponseEvent(ite, selectedUserTypeId);
			return evt.getDescription();
		}
		return "Not Available";
	}
	

	public boolean isSelUserTypeMA(){
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_MA.equals(selectedUserTypeId)){
			result = true;
		}
		return result;
	}
	
	public boolean isSelUserTypeLA(){
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_LIASON.equals(selectedUserTypeId)){
			result = true;
		}
		return result;
	}

	public boolean isSelUserTypeASA(){
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_ASA.equals(selectedUserTypeId)){
			result = true;
		}
		return result;
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
