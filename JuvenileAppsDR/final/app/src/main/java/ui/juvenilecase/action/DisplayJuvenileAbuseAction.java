package ui.juvenilecase.action;

import java.util.Collection;
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

import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileAbuseAction extends LookupDispatchAction
{
	/**
	 * @roseuid 42B1A29E006D
	 */
	public DisplayJuvenileAbuseAction()
	{

	}

	/**
	 * @param juvenileNum
	 * @param form
	 */
	private void setForm(String juvenileNum, JuvenileAbuseForm form, TraitsForm traitsForm)
	{
		form.clear();
		
		Collection abuses = UIJuvenileHelper.fetchJuvenileAbuses(juvenileNum);
		form.setAbuses(abuses);
		form.setTraitsForm(traitsForm);
//		if (form.getAbuseTypes().isEmpty())
//		{
//			Collection abuseTypes=traitsForm.getRootTraitTypes();
//			form.setAbuseTypes(abuseTypes);
//		}
		if (form.getJuvenileRelationships().isEmpty())
		{
			Collection juvenileRealtionships = CodeHelper.getRelationshipsToJuvenileCodes();
			form.setJuvenileRelationships(juvenileRealtionships);
		}
		if (form.getAbuseLevels().isEmpty())
		{
			Collection abuseLevels = CodeHelper.getJuvenileAbuseLevelCodes();
			form.setAbuseLevels(abuseLevels);
		}
	}
	//epic 109828
	private void setFormdual(String juvenileNum, JuvenileAbuseForm form, TraitsForm traitsForm)
	{
		form.clear();		
		//get dual status
		Collection dualstatuses = UIJuvenileHelper.fetchJuvenileDualstatuses(juvenileNum);
		//Collection dualstatuses = UIJuvenileHelper.fetchJuvenileAbuses(juvenileNum);
		form.setDualstatuses(dualstatuses);
		form.setTraitsForm(traitsForm);
//		if (form.getAbuseTypes().isEmpty())
//		{
//			Collection abuseTypes=traitsForm.getRootTraitTypes();
//			form.setAbuseTypes(abuseTypes);
//		}
		/*if (form.getJuvenileRelationships().isEmpty())
		{
			Collection juvenileRealtionships = CodeHelper.getRelationshipsToJuvenileCodes();
			form.setJuvenileRelationships(juvenileRealtionships);
		}
		//change as per dual status
		if (form.getAbuseLevels().isEmpty())
		{
			Collection abuseLevels = CodeHelper.getJuvenileAbuseLevelCodes();
			form.setAbuseLevels(abuseLevels);
		}*/
		if (form.getPlacementYears().isEmpty())
		{
			//Collection years = CodeHelper.getJuvenileAbuseLevelCodes();
			Collection years = CodeHelper.getPlacementYears();
			form.setPlacementYears(years);
		}
	}
	//
	
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
					
	public ActionForward addMoreAbuse(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward("successCasefileSelect");
			return forward;
		}
	public ActionForward addMoreDualStatus(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward("successCasefileSelect");
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
			
			traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_ABUSE);
			traitsForm.clear();
			String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,true,false);
			traitsForm.setJuvenileNumber(juvenileNum);
			traitsForm.setUICasefile(false);  // mark the fact that we are coming in from profile not casefile
			traitsForm.initializeTraitForm(true);

			// Abuse SPECIFIC STUFF
			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
			this.setForm(juvenileNum, form, traitsForm);
			return forward;
		}
		//epic 109828
		public ActionForward dualLink(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward(UIConstants.DUALSUCCESS);
			TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
			
			traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_DUALSTATUS);
			//traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_ABUSE);
			traitsForm.clear();
			String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,true,false);
			traitsForm.setJuvenileNumber(juvenileNum);
			traitsForm.setUICasefile(false);  // mark the fact that we are coming in from profile not casefile
			traitsForm.initializeTraitForm(true);

			// Abuse SPECIFIC STUFF
			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
			this.setFormdual(juvenileNum, form, traitsForm);
			return forward;
		}

		//


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
			return link(aMapping,aForm,aRequest,aResponse);	
		}
		//epic 109828
			public ActionForward dual(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
			
				TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);
				traitsForm.setAction(UIConstants.VIEW);
				return dualLink(aMapping,aForm,aRequest,aResponse);	
			}
		//
	
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.addMoreAbuseInfo", "addMoreAbuse");
		keyMap.put("button.tab", "tab");
		keyMap.put("button.dual", "dual");
		keyMap.put("button.addMoreDualStatusInfo", "addMoreDualStatus");
		return keyMap;
	}
}
