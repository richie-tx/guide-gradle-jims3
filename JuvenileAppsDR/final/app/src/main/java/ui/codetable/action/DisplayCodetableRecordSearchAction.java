//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\DisplayCodetableRecordSearchAction.java

package ui.codetable.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.codetable.form.CodetableForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayCodetableRecordSearchAction extends LookupDispatchAction
{

	/**
	 * @roseuid 45B95943011E
	 */
	public DisplayCodetableRecordSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45B94F4F02F5
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		cForm.setPromptCodetableName("");
		cForm.setPromptCodetableDescription("");
		/*
		 * ISecurityManager securityManager =
		 * (ISecurityManager)ContextManager.getSession().get("ISecurityManager"); Iterator iter =
		 * securityManager.getFeatures().iterator(); while(iter.hasNext()){ iter.next(); }
		 */
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
}
