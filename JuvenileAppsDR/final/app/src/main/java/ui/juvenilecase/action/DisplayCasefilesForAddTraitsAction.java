package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayCasefilesForAddTraitsAction extends LookupDispatchAction
{
	public static final String ADD_MORE_TRAITS = "addMoreTraits";
	
	/**
	 * @roseuid 42A7347E02E7
	 */
	public DisplayCasefilesForAddTraitsAction()
	{

	}

	public ActionForward addTraits(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TraitsForm form = (TraitsForm) aForm;

		ActionForward forward = null;
		
		if (UIConstants.CONFIRM.equals(form.getAction()))
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
			form.setAction(UIConstants.VIEW);
		}
		else
		{
			forward = aMapping.findForward(ADD_MORE_TRAITS);
		}

		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,true,false);
		
		SearchJuvenileCasefilesEvent searchEvent =
			(SearchJuvenileCasefilesEvent) EventFactory.getInstance(
				JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

		searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);

		searchEvent.setJuvenileNum(juvenileNum);
		
		List casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);
		
		form.setSupervisionNum(null);
		
		form.setCasefiles(casefiles);
		
		return forward;
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	} 
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addMoreTraits", "addTraits");
		keyMap.put("button.addMoreAbuseInfo", "addTraits");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
}
