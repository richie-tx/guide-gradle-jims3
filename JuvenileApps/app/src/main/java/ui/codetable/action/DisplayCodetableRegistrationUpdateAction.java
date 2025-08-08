/*
 * Created on Oct 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;
import ui.common.CodeHelper;

/**
 * @author Nagalla
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayCodetableRegistrationUpdateAction extends JIMSBaseAction
{

	/**
	 * @roseuid
	 */
	public DisplayCodetableRegistrationUpdateAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.create", "create");
		keyMap.put("button.update", "update");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward create(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		List codetableTypes = CodeHelper.getCodetableTypeCodes();
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		cForm.resetForCreate();
		cForm.setCodetableTypeList(codetableTypes);
		cForm.setAvailableAgencies(emptyColl);
		cForm.setCurrentAgencies(emptyColl);
		cForm.setAgencyName("");
		cForm.setAgencyCode("");
		cForm.setOpType("create");
		return aMapping.findForward(UIConstants.CREATE);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		cForm.setOpType("update");
		List codetableTypes = CodeHelper.getCodetableTypeCodes();
		cForm.setCodetableTypeList(codetableTypes);
		cForm.setCodetableNameOrig(cForm.getCodetableName());
		cForm.setCodetableDescriptionOrig(cForm.getCodetableDescription());
		cForm.setCodetableTypeOrig(cForm.getCodetableType());
		cForm.setCodetableContextKeyOrig(cForm.getCodetableContextKey());
		cForm.setCodetableEntityNameOrig(cForm.getCodetableEntityName());
		cForm.setAvailableAgencies(cForm.getAvailableAgencies());
		return aMapping.findForward(UIConstants.UPDATE);
	}

}
