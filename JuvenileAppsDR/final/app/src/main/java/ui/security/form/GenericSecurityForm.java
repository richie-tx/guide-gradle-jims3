/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.form;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;

/**
 * @author mchowdhury
 *
 * This form contains all the sorting collection methods
 */
public class GenericSecurityForm extends ActionForm
{
	private Collection agencies;
	private Collection availableAgencies;
	private Collection currentAgencies;	
	private Collection users;
	private Collection userGroups;


	/**
	* Constructor for the GenericForm 
	*/
	public GenericSecurityForm(){
		super();
	}
	
	/**
	 * @return
	 */
	public Collection getAgencies()
	{
		agencies = MessageUtil.processEmptyCollection(agencies);
		return agencies;
	}

	/**
	 * @return
	 */
	public Collection getAvailableAgencies()
	{
		availableAgencies = MessageUtil.processEmptyCollection(availableAgencies);
		return availableAgencies;
	}

	/**
	 * @return
	 */
	public Collection getCurrentAgencies()
	{
		currentAgencies = MessageUtil.processEmptyCollection(currentAgencies);
		return currentAgencies;
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
	public void setAgencies(Collection collection)
	{
		agencies = collection;
	}

	/**
	 * @param collection
	 */
	public void setAvailableAgencies(Collection collection)
	{
		availableAgencies = collection;
	}

	/**
	 * @param collection
	 */
	public void setCurrentAgencies(Collection collection)
	{
		currentAgencies = collection;
	}

	/**
	 * @param collection
	 */
	public void setUserGroups(Collection collection)
	{
		userGroups = collection;
	}

	/**
	 * @param collection
	 */
	public void setUsers(Collection collection)
	{
		users = collection;
	}
	
	/**
	 * @param event
	 */
	public void clear(){
	   this.agencies = null;
	   this.availableAgencies = null;
	   this.currentAgencies = null;
	   this.users = null;
	   this.userGroups = null;
	}

	/**
	 * @param agencies
	 * @return
	 */
	protected Collection sortAgencies(Collection agencies)
	{
		SortedMap map = new TreeMap();
		Iterator iter = agencies.iterator();
		while(iter.hasNext()){
		   AgencyResponseEvent agency = (AgencyResponseEvent) iter.next();      
		   map.put(agency.getAgencyName()+agency.getAgencyId(),agency);
		} 
		return map.values();  
	}
	
	/**
	 * @param userGroups
	 * @return
	 */
	public Collection sortUserGroups(Collection userGroups)
	{
		SortedMap map = new TreeMap();
		Iterator iter = userGroups.iterator();
		while(iter.hasNext()){
		   UserGroupResponseEvent userGroup = (UserGroupResponseEvent) iter.next();      
		   map.put(userGroup.getName()+userGroup.getUserGroupId(),userGroup);
		} 
		return map.values();  
	}
	
	/**
	 * @param users
	 * @return
	 */
	public Collection sortUsers(Collection users)
	{
		SortedMap map = new TreeMap();
		Iterator iter = users.iterator();
		while(iter.hasNext()){
		   SecurityUserResponseEvent user = (SecurityUserResponseEvent) iter.next();   
		   map.put(user.getLastName()+user.getFirstName()+user.getLogonId(),user);
		} 
		return map.values();  
	}
	
	/**
	 * @param departments
	 * @return
	 */
	protected Collection sortDepartments(Collection departments)
	{
		SortedMap map = new TreeMap();
		Iterator iter = departments.iterator();
		while(iter.hasNext()){
		   DepartmentResponseEvent department = (DepartmentResponseEvent) iter.next();   
		   map.put(department.getDepartmentName()+department.getDepartmentId(),department);
		} 
		return map.values();  
	}

}
