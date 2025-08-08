/*
 * Created on Jun 1, 2004
 */
package ui.contact.agency.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import ui.contact.AgencyDepartmentForm;

/**
 * @author dwilliamson
 *
 * This form contains all the attributes needed to 
 * add, update, delete, and retrieve an agency and its
 * divisions and contacts.
 */
public class AgencyForm extends AgencyDepartmentForm
{
	String action;
	String agencyId;
	String agencyIdPrompt;
	String agencyName;
	String agencyNamePrompt;
	String agencyType;
	String agencyTypeId;
		
	String errorMessage;
	String jmcRep;
	String originalAgencyName;
	String pageType;
	
	Collection agencies;
	Collection agencyTypes;
	Collection jmcReps;
	
	/**
	 * Constructor for the RoleGroupForm 
	 */
	public AgencyForm()
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
		super.clear();
		
		agencyNamePrompt = null;
		
		agencyIdPrompt = null;
		agencies = null;

		agencyName = null;
		agencyType = null;
		agencyId = null;
		action = null;
		agencyTypeId = null;
		
		jmcRep = null;
	
		pageType = null;
		originalAgencyName = null;
		
		errorMessage = null;
		
		//no need to clear the agencyTypes & jmcReps since they don't really change
	}

	/**
	 * @return
	 */
	public String getAgencyIdPrompt()
	{
		return agencyIdPrompt;
	}

	/**
	 * @return
	 */
	public String getAgencyNamePrompt()
	{
		return agencyNamePrompt;
	}

	/**
	 * @return
	 */
	public Collection getAgencyTypes()
	{
		return agencyTypes;
	}

	/**
	 * @param string
	 */
	public void setAgencyIdPrompt(String string)
	{
		agencyIdPrompt = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyNamePrompt(String string)
	{
		agencyNamePrompt = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypes(Collection collection)
	{
		agencyTypes = collection;
	}


	/**
	 * @return
	 */
	public Collection getAgencies()
	{
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
	 * @return
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
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

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
	public String getAgencyType()
	{
		return agencyType;
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
	public void setAgencyId(String string)
	{
		agencyId = string.toUpperCase();
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyType(String string)
	{
		agencyType = string;
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
	public String getOriginalAgencyName()
	{
		return originalAgencyName;
	}

	/**
	 * @param string
	 */
	public void setOriginalAgencyName(String string)
	{
		originalAgencyName = string;
	}

	/**
	 * @return
	 */
	public String getPageType()
	{
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string)
	{
		pageType = string;
	}

	/**
	 * @return
	 */
	public Collection getJmcReps()
	{
		return jmcReps;
	}

	/**
	 * @param collection
	 */
	public void setJmcReps(Collection collection)
	{
		jmcReps = collection;
	}

}
