package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.SaveCourtPolicyAssociateToConditionsEvent;
import messaging.supervisionoptions.SaveCourtPolicyEvent;
import messaging.supervisionoptions.reply.CourtPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
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
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;

public class SubmitCourtPolicyCreateAction extends Action
{

	/**
	 * @roseuid 42F7C4A200FA
	 */
	public SubmitCourtPolicyCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A3A02C1
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		CourtPolicyForm form = (CourtPolicyForm) aForm;

		SaveCourtPolicyEvent event = new SaveCourtPolicyEvent();
		if (!form.getAction().equals(UIConstants.COPY) && !form.getAction().equals(UIConstants.CREATE))
		{
			event.setCourtPolicyId(form.getPolicyId());
		}
		event.setAgencyId(form.getAgencyId());
		event.setName(form.getCourtPolicyName());
		event.setDescription(form.getCourtPolicyLiteral());
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

		event.setDepartmentPolicy(form.isDepartmentPolicy());

		event.setStandard(form.isStandard());
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

		String[] eveTypes = form.getSelectedEventTypeIds();
		if (eveTypes != null && eveTypes.length > 0)
		{
			for (int i = 0; i < eveTypes.length; i++)
			{
				if (eveTypes[i] != null)
					event.addEventType(eveTypes[i]);
			}
		}

		String eventCount = form.getEventCount();
		if (eventCount != null && !eventCount.equals(""))
		{
			event.setEventCountValue(Integer.parseInt(eventCount));
		}
		String periodValue = form.getPeriodValue();
		if (periodValue != null && !periodValue.equals(""))
		{
			event.setEventPeriodValue(Integer.parseInt(periodValue));
		}
		event.setEventPeriodTypeId(form.getPeriodId());
		event.setNewCourtSelection(true);

		//normal courts
		Collection vereConsolidated = new ArrayList();
		Collection veres = form.getVariableElementResponseEvents();
		//String[] selExceptionCourts = form.getSelExceptionCourts();
		//List selExcCrts = Arrays.asList(selExceptionCourts);

		if (veres != null && veres.size() > 0)
		{
			Collection selCourts = form.getSelectedCourts();
			Iterator it = selCourts.iterator();
			while (it.hasNext())
			{
				CourtBean courtBean = (CourtBean) it.next();
				Iterator courts = courtBean.getCourts().iterator();
				while (courts.hasNext())
				{
					CourtResponseEvent cre = (CourtResponseEvent) courts.next();

					//if(!selExcCrts.contains(cre.getCourtNumber())){ // normal court
					if (!cre.isExceptionCourt())
					{ // normal court
						Iterator vereIt = veres.iterator();
						while (vereIt.hasNext())
						{
							VariableElementResponseEvent vere = (VariableElementResponseEvent) vereIt.next();
							VariableElementResponseEvent cloned = (VariableElementResponseEvent) vere.clone();
							cloned.setCourtId(cre.getCourtId());
							vereConsolidated.add(cloned);
						}
					}
				}
			}
		}
		// for exception courts 
		Collection courtVarElemBeans = form.getExceptionCourtVarElemBeans();
		if (courtVarElemBeans != null)
		{
			Iterator iter = courtVarElemBeans.iterator();
			while (iter.hasNext())
			{
				CourtVariableElementBean cveBean = (CourtVariableElementBean) iter.next();
				// add all the variable response events into the final collection
				vereConsolidated.addAll(cveBean.getVariableElements());
			}
		}
		event.setVariableElements(vereConsolidated);

		//TODO out of scope
		//event.setTaskSubject();
		//event.setTaskDueBy();
		//event.setEmailNotificationTo();

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
		CourtPolicyDetailResponseEvent polRespEvent =
			(CourtPolicyDetailResponseEvent) MessageUtil.filterComposite(
				response,
				CourtPolicyDetailResponseEvent.class);
		String policyId = null;
		if (polRespEvent != null)
		{
			policyId = polRespEvent.getPolicyId();
		}
		if (UIConstants.CREATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction()))
		{
			form.setPolicyId(policyId);
		}
		// create event to post
		if (UIConstants.COPY.equals(form.getAction()))
		{
			SaveCourtPolicyAssociateToConditionsEvent reqEvent = new SaveCourtPolicyAssociateToConditionsEvent();
			reqEvent.setPolicyId(policyId);

			// add courtPolicyIds into the event            
			Collection associatedConditions = form.getAssociatedConditions();

			if (associatedConditions != null)
			{
				Iterator it = associatedConditions.iterator();
				while (it.hasNext())
				{
					AssociateBean asscBean = (AssociateBean) it.next();
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
		form.getTasks().setCourtId(form.getPolicyId());
		form.getTasks().setConditionId(null);
		// TODO We need to figure out how to stage a cstask #JIMS200053629
		// SupervisionOptionsHelper.processUpdatedCSTaskConfiguration(form.getTasks());
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
