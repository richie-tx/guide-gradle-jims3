package ui.security.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author hrodriguez - Created on Apr 5, 2004
 * 
 * This form contains all the attributes needed for adding, 
 * updating, deleting and retrieving a role group.
 */
public class RoleGroupForm extends ActionForm
{
	private String action;
	private String roleGroupId;
	private String roleId;
	private String roleGroupCreator;
	private String roleGroupName;

	private Collection availableRoles;
	private Collection associatedRoles;
	private String[] rolesToAssociate;
	private String[] unassociatedRoles;

	/**
	 * Constructor for the RoleGroupForm 
	 */
	public RoleGroupForm()
	{
		super();
	}
	/**
	 * @param aRequest
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		/*this.roleGroupId = null;
		this.availableRoles = null;
		this.associatedRoles = null;*/
		this.rolesToAssociate = null;
		this.unassociatedRoles = null;
	}
	/**
	 * @param aRequest
	 */
	public void clear()
	{
		this.roleGroupId = null;
		this.roleId = null;
		this.roleGroupName = null;		
		this.availableRoles = null;
		this.associatedRoles = null;
		this.rolesToAssociate = null;
		this.unassociatedRoles = null;
	}

	/**
	 * @return String roleGroupId
	 */
	public String getRoleGroupId()
	{
		return roleGroupId;
	}

	/**
	 * @param string
	 */
	public void setRoleGroupId(String string)
	{
		if (string != null && !string.equals(roleGroupId))
		{
			this.availableRoles = null;
			this.associatedRoles = null;
		}
		roleGroupId = string;
	}

	/**
	 * @return String roleId
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
	 * @return Collection associatedRoles
	 */
	public Collection getAssociatedRoles()
	{
		return associatedRoles;
	}

	/**
	 * @return Collection availableRoles
	 */
	public Collection getAvailableRoles()
	{
		return availableRoles;
	}

	/**
	 * @param collection
	 */
	public void setAssociatedRoles(Collection collection)
	{
		associatedRoles = collection;
	}

	/**
	 * @param collection
	 */
	public void setAvailableRoles(Collection collection)
	{
		availableRoles = collection;
	}

	/**
	 * @return String roleGroupCreator
	 */
	public String getRoleGroupCreator()
	{
		return roleGroupCreator;
	}

	/**
	 * @return String roleGroupName
	 */
	public String getRoleGroupName()
	{
		return roleGroupName;
	}

	/**
	 * @param string
	 */
	public void setRoleGroupCreator(String string)
	{
		roleGroupCreator = string;
	}

	/**
	 * @param string
	 */
	public void setRoleGroupName(String string)
	{
		roleGroupName = string;
	}

	/**
	 * @return String[] rolesToAssociate
	 */
	public String[] getRolesToAssociate()
	{
		return rolesToAssociate;
	}

	/**
	 * @return String[] unassociatedRoles
	 */
	public String[] getUnassociatedRoles()
	{
		return unassociatedRoles;
	}

	/**
	 * @param string
	 */
	public void setRolesToAssociate(String[] string)
	{
		rolesToAssociate = string;
	}

	/**
	 * @param string
	 */
	public void setUnassociatedRoles(String[] string)
	{
		unassociatedRoles = string;
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

}
