/*
 * Created on July 05, 2005
 */
package ui.contact;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * @author mchowdhury
 *
 * This form is the generic form for agency and department.
 */
public class AgencyDepartmentForm extends ActionForm
{
	/**
	 * Constructor for the AgencyDepartment Form
	 */
	public AgencyDepartmentForm()
	{
		super();
	}
	
	/**
	 * 
	 */
	public void clear()
	{	
	}
	
	/**
	 * @see ActionForm#reset(ActionMapping, HttpServletRequest)
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
	}
}
