/*
 * Created on Dec 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderSearchForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCSCServiceProviderSelectionAction extends JIMSBaseAction {
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		keyMap.put("button.update","update");
		
	}
	
	
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		
		ServiceProviderSearchForm myForm=(ServiceProviderSearchForm)aForm;
		aRequest.setAttribute("selectedValue",myForm.getSelectedValue());
		return aMapping.findForward(UIConstants.UPDATE);
	}
}// END CLASS
