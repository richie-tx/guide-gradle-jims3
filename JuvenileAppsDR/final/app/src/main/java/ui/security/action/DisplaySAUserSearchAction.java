/*
 * Created on May 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.action;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodesEvent;
import mojo.km.messaging.EventFactory;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.security.form.SAUsersForm;

public class DisplaySAUserSearchAction extends Action
{
	/**
	 * 
	 */
	public DisplaySAUserSearchAction()
	{
	}

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		if (!ui.security.SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		String forward = UIConstants.SUCCESS;
		SAUsersForm saForm = (SAUsersForm) aForm;

		aForm.reset(aMapping, aRequest);
		saForm.clear();

		/** FETCH USER_TYPE CODETABLE */
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
		codesRequestEvent.setCodeTableName(PDCodeTableConstants.USER_TYPE);
		Collection userTypes = (Collection) this.fetchUserTypes(codesRequestEvent);
		if (userTypes != null)
		{
			saForm.setUserTypes(userTypes);
		}

		forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);
	}

	private Collection fetchUserTypes(GetCodesEvent codesRequestEvent)
	{
		return CodeHelper.getUserTypes();
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//		dispatch.postEvent(codesRequestEvent);
//
//		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
//		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
//		MessageUtil.processReturnException(dataMap);
//
//		Collection userTypes = (Collection) dataMap.get(PDCodeTableConstants.getCodeTableTopic(PDCodeTableConstants.USER_TYPE));
//		return userTypes;
	}
}