//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitSupervisionConditionCreateAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.SaveConditionAssociateToCourtPoliciesEvent;
import messaging.supervisionoptions.SaveConditionAssociateToDepartmentPoliciesEvent;
import messaging.supervisionoptions.SaveSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class SubmitSupervisionConditionCreateAction extends Action
{

	/**
	 * @roseuid 42F7C4A200FA
	 */
	public SubmitSupervisionConditionCreateAction()
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
		//long timeStart = System.currentTimeMillis();

		SupervisionConditionForm form = (SupervisionConditionForm) aForm;

		SaveSupervisionConditionEvent event = new SaveSupervisionConditionEvent();
		event.setConditionId(form.getConditionId());
		event.setAgencyId(form.getAgencyId());
		event.setName(form.getConditionName());

		if (form.isSpecialCondition())
		{
			event.setDescription(form.getConditionLiteral());
		}
		if (UIConstants.JUV.equalsIgnoreCase(form.getAgencyId())){
		    
		    // Set this way for UJAC used in CaseWork
		    event.setDescription(form.getConditionLiteral());
		    
		}else{
		   
		    // Richard Young added only for Web Focus for PTS and CSC
			String cleanDesc = UIUtil.replaceASCIIWithLit(form.getConditionLiteral());
			event.setDescription(cleanDesc);
		}
		
        event.setSpanishDescription(form.getConditionSpanishLiteral());
        event.setUnformattedDesc(UIUtil.removeXMLtags(form.getConditionLiteral(),true));
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

		String[] supervisionTypes = form.getSelSupervisionTypes();
		if (supervisionTypes != null && supervisionTypes.length > 0)
		{
			for (int i = 0; i < supervisionTypes.length; i++)
			{
				event.addSupervisionType(supervisionTypes[i]);
			}
		}

		event.setStandard(form.getStandard());
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
		event.setSeverityLevelId(form.getSeverityLevelId());

		String[] docs = form.getSelDocumentIds();
		if (docs != null && docs.length > 0)
		{

			for (int i = 0; i < docs.length; i++)
			{
				event.addDocument(docs[i]);
			}
		}

		event.setJurisdictionId(form.getJurisdictionId());

		String[] eveTypes = form.getSelectedEventTypeIds();
		if (eveTypes != null && eveTypes.length > 0)
		{
			for (int i = 0; i < eveTypes.length; i++)
			{
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

		// normal courts
		Collection vereConsolidated = new ArrayList();
		Collection veres = form.getVariableElementResponseEvents();

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

		//make sure VariableElementTypeId is set
		if (vereConsolidated != null && vereConsolidated.size() > 0)
		{
			Iterator vereIter = vereConsolidated.iterator();

			while (vereIter.hasNext())
			{
				VariableElementResponseEvent vere = (VariableElementResponseEvent) vereIter.next();

				//set variableelementtypeId as it has not been set as yet											
				setVERTypeId(form.getDetailDictionaryNameIdHashMap(), vere);
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
		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Create/Update of Supervision Condition Failed");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
		}
		DuplicationNameErrorEvent duplicateException =
			(DuplicationNameErrorEvent) MessageUtil.filterComposite(response, DuplicationNameErrorEvent.class);
		if (duplicateException != null)
		{
			this.sendToErrorPage(aRequest, "error.name.exist");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
		}

		ConditionDetailResponseEvent condRespEvent =
			(ConditionDetailResponseEvent) MessageUtil.filterComposite(response, ConditionDetailResponseEvent.class);
		String conditionId = null;
		if (condRespEvent != null)
		{
			conditionId = condRespEvent.getConditionId();
		}

		if (UIConstants.CREATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction()))
		{
			form.setConditionId(conditionId);
		}

		// save court policies if copy
		// create event to post
		if (UIConstants.COPY.equals(form.getAction()))
		{
			SaveConditionAssociateToCourtPoliciesEvent reqEvent = new SaveConditionAssociateToCourtPoliciesEvent();
			reqEvent.setConditionId(conditionId);

			// add courtPolicyIds into the event            
			Collection associatedPolicies = form.getAssociatedCourtPolicies();

			if (associatedPolicies != null)
			{
				Iterator it = associatedPolicies.iterator();
				while (it.hasNext())
				{
					AssociateBean asscBean = (AssociateBean) it.next();
					// make sure it is not in the remove list
					if(isAssociated(form.getRemovedAssociations(), asscBean)){
						reqEvent.addPolicyId(asscBean.getObjId());
					}
				}
			}
			// post the event
			dispatch.postEvent(reqEvent);
			response = (CompositeResponse) dispatch.getReply();
			returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
		        this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Copy of Associated Court Policies Failed");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
			}

			// create event to post
			SaveConditionAssociateToDepartmentPoliciesEvent reqDepEvent =
				new SaveConditionAssociateToDepartmentPoliciesEvent();
			reqDepEvent.setConditionId(conditionId);

			// add deptPolicyIds into the event            
			associatedPolicies = form.getAssociatedDeptPolicies();
			if (associatedPolicies != null)
			{
				Iterator it = associatedPolicies.iterator();
				while (it.hasNext())
				{
					AssociateBean asscBean = (AssociateBean) it.next();
					if(isAssociated(form.getRemovedAssociations(), asscBean)){
						reqDepEvent.addPolicyId(asscBean.getObjId());
					}
				}
			}
			// post the event
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqDepEvent);
			response = (CompositeResponse) dispatch.getReply();
			returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
		        this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Copy of Associated Department Policies Failed");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
			}
		}
		form.getTasks().setCourtId(null);
		form.getTasks().setConditionId(form.getConditionId());
		// TODO We need to figure out how to stage a cstask #JIMS200053629
		//SupervisionOptionsHelper.processUpdatedCSTaskConfiguration(form.getTasks());
		form.setPageType(UIConstants.CONFIRM);

		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) for Supervision Condition insert/update : " + (timeEnd - timeStart));
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
	}

	private boolean isAssociated(Collection removedAssociations, AssociateBean asscBean){
		boolean result = true;
		for(Iterator iter = removedAssociations.iterator(); iter.hasNext(); ){
			AssociateBean removeBean = (AssociateBean)iter.next();
			if(removeBean.getObjId().equals(asscBean.getObjId()) &&
				removeBean.getObjType().equals(asscBean.getObjType()))
			{
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	private void setVERTypeId(Map detailDictionaryNameIdMapping, VariableElementResponseEvent vere)
	{
		//get variable element type id
		String variableElementName = vere.getName();
		VariableElementTypeResponseEvent varElementTypeRE =
			(VariableElementTypeResponseEvent) detailDictionaryNameIdMapping.get(variableElementName.toUpperCase());

		String varElementId = "";
		if (varElementTypeRE != null)
		{
			varElementId = varElementTypeRE.getVariableElementTypeId();
			vere.setVariableElementTypeId(varElementId);
			if (varElementTypeRE.isEnumration())
			{
				// set code description if variableElement is of type enumeration
				vere.setValueByValueId();
			}
		}
	}
	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
	    saveErrors(aRequest, errors);
	}
	protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
	    saveErrors(aRequest, errors);
	}

}
