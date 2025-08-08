//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitDepartmentPolicyAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.SaveDepartmentPolicyAssociateToConditionsEvent;
import messaging.supervisionoptions.SaveDepartmentPolicyEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class SubmitDepartmentPolicyAction extends Action
{

	/**
	 * @roseuid 42F7C49F0251
	 */
	public SubmitDepartmentPolicyAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A0802E0
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentPolicyForm form = (DepartmentPolicyForm) aForm;

		SaveDepartmentPolicyEvent event = new SaveDepartmentPolicyEvent();
		event.setDepartmentId(form.getPolicyId());
		event.setAgencyId(form.getAgencyId());
		event.setName(form.getName());
		event.setDescription(form.getDepartmentPolicy());
		event.setEffectiveDate(DateUtil.stringToDate(form.getEffectiveDate(), "MM/dd/yyyy"));
		String inactiveDate = form.getInactiveDate();

		if (inactiveDate != null && inactiveDate.length() > 0)
		{
			Date date = new Date();
			try
			{
				date = DateUtil.stringToDate(inactiveDate, "MM/dd/yyyy");
			}
			catch (ParseRuntimeException prte)
			{
				prte.printStackTrace();
			}

			event.setInactiveDate(date);
		}

		event.setNotes(form.getNotes());
		String group3 = form.getGroup3Id();
		String group2 = form.getGroup2Id();
		String group1 = form.getGroup1Id();
		if (group3 != null && !group3.equals(""))
		{
			event.setGroupId(group3);
		}
		else
			if (group2 != null && !group2.equals(""))
			{
				event.setGroupId(group2);
			}
			else
				if (group1 != null && !group1.equals(""))
				{
					event.setGroupId(group1);
				}

		Collection selCourts = form.getSelectedCourts();
		Iterator it = selCourts.iterator();
		while (it.hasNext())
		{
			CourtBean courtBean = (CourtBean) it.next();
			Iterator courts = courtBean.getCourts().iterator();
			while (courts.hasNext())
			{
				CourtResponseEvent cre = (CourtResponseEvent) courts.next();
				event.addCourtId(cre.getCourtId());
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ReturnException returnException =
			(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
		}

		// save associations, if copy
		DepartmentPolicyDetailResponseEvent polRespEvent =
			(DepartmentPolicyDetailResponseEvent) MessageUtil.filterComposite(
				response,
				DepartmentPolicyDetailResponseEvent.class);
		String policyId = null;
		if (polRespEvent != null)
		{
			policyId = polRespEvent.getDepartmentId();
		}

		if (UIConstants.CREATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction()))
		{
			form.setPolicyId(policyId);
		}

		// create event to post
		if (UIConstants.COPY.equals(form.getAction()))
		{
			// create event to post
			SaveDepartmentPolicyAssociateToConditionsEvent reqEvent =
				new SaveDepartmentPolicyAssociateToConditionsEvent();
			reqEvent.setPolicyId(policyId);

			// add courtPolicyIds into the event            
			Collection associatedConditions = form.getAssociatedConditions();
			if (associatedConditions != null)
			{
				Iterator conds = associatedConditions.iterator();
				while (conds.hasNext())
				{
					AssociateBean asscBean = (AssociateBean) conds.next();
					// make sure it is not in the remove list
					if(isAssociated(form.getRemovedAssociations(), asscBean)){
						reqEvent.addConditionId(asscBean.getObjId());
					}
				}
			}
			// post the event
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqEvent);
			response = (CompositeResponse) dispatch.getReply();
			returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
			}

		}

		form.setPageType(UIConstants.CONFIRM);

		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
	}

	private boolean isAssociated(Collection removedAssociations, AssociateBean asscBean){
		boolean result = true;
		for(Iterator iter = removedAssociations.iterator(); iter.hasNext(); ){
			AssociateBean removeBean = (AssociateBean)iter.next();
			if(removeBean.getObjId().equals(asscBean.getObjId()))
			{
				result = false;
				break;
			}
		}
		return result;
	}
}
