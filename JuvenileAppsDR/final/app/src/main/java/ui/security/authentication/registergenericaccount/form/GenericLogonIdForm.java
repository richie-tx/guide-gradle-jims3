package ui.security.authentication.registergenericaccount.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cshimek - Created on Sept 21, 2006
 * 
 * This form contains all the attributes needed for inquiring, creating and updating generic Logon security records 
 */
public class GenericLogonIdForm extends ActionForm
{

	private String currentPassword;	
	private String departmentId;
	private String newPassword;
	private String reenterPassword;
	private String logonId;
	private String genericLogonId;
	private String action;
	private String searchLogonId;
	private String statusId;
	private int searchResultsCount;
	
	private Collection users;

	/**
	 * Constructor for the GenericLogonIdForm 
	 */
	public GenericLogonIdForm()
	{
		super();
	}
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
	}
	/**
	 * @param aRequest
	 */
	public void clear()
	{
		this.currentPassword = null;
		this.departmentId = null;
		this.newPassword = null;
		this.reenterPassword = null;
		this.logonId = null;
		this.genericLogonId = null;
		this.action = null;
		this.statusId = null;
		this.searchLogonId = null;
		this.searchResultsCount = 0;
		// collections 
		this.users = new ArrayList();	
	}

	/**
	 * @return Returns the currentPassword.
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}
	/**
	 * @param currentPassword The currentPassword to set.
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the newPassword.
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword The newPassword to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return Returns the reenterPassword.
	 */
	public String getReenterPassword() {
		return reenterPassword;
	}
	/**
	 * @param currentPassword The reenterPassword to set.
	 */
	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}

	/**
	 * @return Returns the agencies.
	 */
	public Collection getUsers() {
		users = MessageUtil.processEmptyCollection(users);		
		return users;
	}
	/**
	 * @param users The users to set.
	 */
	public void setUsers(Collection users) {
		this.users = users;
	}		
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the genericLogonId.
	 */
	public String getGenericLogonId() {
		return genericLogonId;
	}
	/**
	 * @param genericLogonId The genericLogonId to set.
	 */
	public void setGenericLogonId(String genericLogonId) {
		this.genericLogonId = genericLogonId;
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
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the searchResultsCount.
	 */
	public int getSearchResultsCount() {
		return searchResultsCount;
	}
	/**
	 * @param searchResultsCount The searchResultsCount to set.
	 */
	public void setSearchResultsCount(int searchResultsCount) {
		this.searchResultsCount = searchResultsCount;
	}
	/**
	 * @return Returns the searchLogonId.
	 */
	public String getSearchLogonId() {
		return searchLogonId;
	}
	/**
	 * @param searchLogonId The searchLogonId to set.
	 */
	public void setSearchLogonId(String searchLogonId) {
		this.searchLogonId = searchLogonId;
	}
}
