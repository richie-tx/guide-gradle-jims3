package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;

/*
 * 
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayCodetableRegistrationSearchAction extends JIMSBaseAction
{

	public DisplayCodetableRegistrationSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		cForm.setPromptCodetableRegisterName("");
		cForm.setCodetableDescription("");
		cForm.setCodetableType("");
		cForm.setAgencyName("");
		cForm.setAgencyCode("");
		cForm.setAgencyId("");
		cForm.setAvailableAgencies(emptyColl);
		cForm.setCurrentAgencies(emptyColl);
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		Iterator iter = securityManager.getFeatures().iterator();
		while (iter.hasNext())
		{
			iter.next();
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	protected void addButtonMapping(Map keyMap)
	{

		keyMap.put("button.link", "link");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
	}
}
