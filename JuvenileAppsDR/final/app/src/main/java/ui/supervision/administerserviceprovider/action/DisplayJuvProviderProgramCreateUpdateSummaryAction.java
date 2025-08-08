//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateUpdateProviderProgramSummaryAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.util.DateUtil;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayJuvProviderProgramCreateUpdateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D301F1
	 */
	public DisplayJuvProviderProgramCreateUpdateSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FEB03B4
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		int flag = 0;
		//check if program name has been updated
		if(sp.getActionType().equals("updateProgram"))
		{
			Collection coll = sp.getPrograms();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				ProviderProgramResponseEvent resp = (ProviderProgramResponseEvent)iter.next();
				if(resp.getProviderProgramId().equals(sp.getCurrentProgram().getProgramId()))
				{
					ActionErrors errors = new ActionErrors();
					if(!resp.getProgramCodeId().equalsIgnoreCase(sp.getCurrentProgram().getProgramCode()))
					{
						ServiceProviderErrorResponseEvent codeError = UIServiceProviderHelper.validateProgramCode(sp);
						if(codeError != null)
						{
						//ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(codeError.getMessage(),"Duplicate code"));		
						saveErrors(aRequest, errors);
						flag ++;
						//return aMapping.findForward("failure");
						}
					}
					if(!resp.getProgramName().equalsIgnoreCase(sp.getCurrentProgram().getProgramName()))
					{
						ServiceProviderErrorResponseEvent nameError = UIServiceProviderHelper.validateProgramName(sp);
						if(nameError != null)
						{
							//ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(nameError.getMessage(),"Duplicate name"));		
							saveErrors(aRequest, errors);
							flag ++;
							//return aMapping.findForward("failure");
						}
					}
					if(flag > 0)
					{
						return aMapping.findForward("failure");
					}
				}
			}
		}
		if(sp.getActionType().equals("addProgram"))
		{
			int codeflag = 0;
			ActionErrors errors = new ActionErrors();
			ServiceProviderErrorResponseEvent codeError = UIServiceProviderHelper.validateProgramCode(sp);
			if(codeError != null)
			{
				//ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(codeError.getMessage(),"Duplicate code"));		
				saveErrors(aRequest, errors);
				codeflag ++;
				//return aMapping.findForward("failure");
			}
			ServiceProviderErrorResponseEvent nameError = UIServiceProviderHelper.validateProgramName(sp);
			if(nameError != null)
			{
				//ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(nameError.getMessage(),"Duplicate name"));		
				saveErrors(aRequest, errors);
				codeflag ++;
				//return aMapping.findForward("failure");
			}
			if(codeflag > 0)
			{
				return aMapping.findForward("failure");
			}
			
		}
		//added for US #11376
		sp.getCurrentProgram().setProgramLocationStr(CodeHelper.getCodeDescription("JUVENILE_PROGRAM_LOCATION", sp.getCurrentProgram().getProgramLocationCd()));
		sp.getCurrentProgram().setProgramCategoryStr(CodeHelper.getCodeDescription("JUVENILE_PROGRAM_CATEGORY", sp.getCurrentProgram().getProgramCategoryCd()));
		sp.getCurrentProgram().setProgramFundSourceStr(CodeHelper.getCodeDescription("JUVENILE_SOURCE_FUND", sp.getCurrentProgram().getProgramFundSourceCd()));
		
		//US 174929
		UIServiceProviderHelper.populateFormWithSelectedSupervisionCategories(sp);
		
		
		return aMapping.findForward(UIConstants.SUCCESS);
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
	public ActionForward inactivate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		if(sp.getCurrentProgram().getCurrentSourceFund().getProgramSourceFundCd()==null || sp.getCurrentProgram().getCurrentSourceFund().getProgramSourceFundCd()=="")
		{
			sp.getCurrentProgram().setFundStartDate(null);
		}
		else
		{
			if(sp.getCurrentProgram().getEndDate()!=null && !sp.getCurrentProgram().getEndDate().equals(""))
				sp.getCurrentProgram().setFundEndDate(sp.getCurrentProgram().getEndDate());
			else if(sp.getCurrentProgram().getFundEndDate()==null || sp.getCurrentProgram().getFundEndDate().equals(""))
				sp.getCurrentProgram().setFundEndDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
		}
		
		//US 174929
		UIServiceProviderHelper.populateFormWithSelectedSupervisionCategories(sp);
				
		sp.setActionType("inactivateProgram");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();	
		keyMap.put("button.next","next");	
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.inactivate","inactivate");
		return keyMap;
	}
}
