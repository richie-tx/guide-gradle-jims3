//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseSelectOffenseAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.OffenseHelper;
import ui.common.form.OffenseSearchForm;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;

public class DisplayOutOfCountyCaseSelectOffenseAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4443EFD20067
	 */
	public DisplayOutOfCountyCaseSelectOffenseAction()
	{

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	    ActionForward forward = new ActionForward();
		OffenseSearchForm myForm = (OffenseSearchForm) aForm;
		myForm.setOffenseResultList(null);
		CompositeResponse myResp=OffenseHelper.retrieveOffenseCodes(myForm);
		
		//RRY added to filter the over the records over the count
		if (MessageUtil.filterComposite(myResp, CountInfoMessage.class) != null){
		    sendToErrorPage(aRequest, "error.max.limit.exceeded");
		    forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}else{
		    
		    myForm.setOffenseResultList(MessageUtil.compositeToCollection(myResp,
					OffenseCodeResponseEvent.class));
		    forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
			if(myForm.getOffenseResultList()==null || myForm.getOffenseResultList().size()<=0){
				sendToErrorPage(aRequest,"error.no.search.results.found");
			}
		}
		
		
		return forward;
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
		OffenseSearchForm myForm = (OffenseSearchForm) aForm;

		myForm.clearSearchFields();
	
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}
	
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		OffenseSearchForm searchForm = (OffenseSearchForm) aForm;
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) UICommonSupervisionHelper.getOOCForm(aRequest,true);

		// set the newly selected offense code, if any
		String newOffenseCode = searchForm.getSelectedValue();
		if (newOffenseCode != null && !newOffenseCode.equals(""))
		{
			ooc.setOffenseId(newOffenseCode);
		}
		
		String action = ooc.getAction();
		if (action.equals(UIConstants.CREATE))
		{
			forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
		}
		else
			if (action.equals(UIConstants.UPDATE))
			{
				forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
			}
			else
				if (action.equals(UIConstants.PRETRIAL_CREATE))
				{
					forward = aMapping.findForward(UIConstants.CREATE_PRETRIAL_SUCCESS);
				}
				else
					if (action.equals(UIConstants.PRETRIAL_UPDATE))
					{
						forward = aMapping.findForward(UIConstants.UPDATE_PRETRIAL_SUCCESS);
					}
					else
					{
						forward = aMapping.findForward(UIConstants.FAILURE);
					}
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
