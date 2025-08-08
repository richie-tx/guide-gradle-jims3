/*
 * Created on Apr 6, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.TraitsForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJuvenileSpecialInterestAction extends LookupDispatchAction
{

	//extends LookupDispatchAction
	private final static String VIEW = "view";

	/**
	 * @roseuid 42B1A2B00196
	 */
	public DisplayJuvenileSpecialInterestAction()
	{

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

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B350171
	 */
	public ActionForward link(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
		traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SPECIAL_INTERESTS);
		traitsForm.clear();
		String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,true,false);
		traitsForm.setJuvenileNumber(juvenileNum);
		traitsForm.initializeTraitForm(true);
		traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));

		return forward;
	}

	/**
			 * @param aMapping
			 * @param aForm
			 * @param aRequest
			 * @param aResponse
			 * @return ActionForward
			 * @roseuid 42B03B350171
			 */
			public ActionForward tab(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
		
			TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
			traitsForm.setAction(UIConstants.VIEW);
			traitsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
			return link(aMapping,aForm,aRequest,aResponse);	
		}

		/* (non-Javadoc)
			 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
			 */
			protected Map getKeyMethodMap()
			{
				Map keyMap = new HashMap();
				keyMap.put("button.link", "link");
				keyMap.put("button.back", "back");
				keyMap.put("button.cancel", "cancel");
				keyMap.put("button.tab", "tab");
				return keyMap;
			}

}

