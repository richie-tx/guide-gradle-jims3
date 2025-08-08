// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\SubmitAddCasenoteAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteConditionsEvent;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

public class SubmitAddCasenoteAction extends JIMSBaseAction {

	/**
	 * @roseuid 473B8C210200
	 */
	public SubmitAddCasenoteAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.finishCasenoteLater", "finishCasenoteLater");

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 473B7556007D
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		UpdateCasenoteConditionsEvent ucEvent = this.prepareRequestEvent(compForm, false);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(ucEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(returnException.getMessage()));
			saveErrors(aRequest, errors);
			forward = aMapping.findForward(UIConstants.FAILURE);
		} else {
			compForm.setConfirmMessage("Casenote successfully saved.");
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 473B7556007D
	 */
	public ActionForward finishCasenoteLater(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		UpdateCasenoteConditionsEvent ucEvent = this.prepareRequestEvent(compForm, true);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(ucEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(returnException.getMessage()));
			saveErrors(aRequest, errors);
			forward = aMapping.findForward(UIConstants.FAILURE);
		} else {
			compForm.setConfirmMessage("Casenote successfully saved as draft.");
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		return forward;
	}

	/**
	 * @param compForm
	 * @return
	 */
	private UpdateCasenoteConditionsEvent prepareRequestEvent(ComplianceForm compForm, boolean isDraft) {
		UpdateCasenoteConditionsEvent ucEvent = (UpdateCasenoteConditionsEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.UPDATECASENOTECONDITIONS);

		UpdateCasenoteEvent uEvent = new UpdateCasenoteEvent();
		uEvent.setAgencyId(compForm.getAgencyId());
		uEvent.setCasenoteStatusId("D");
		uEvent.setCasenoteTypeCodeId("723");
		uEvent.setCollateralId(compForm.getCollateralId());
		uEvent.setContactMethodId(compForm.getContactMethodId());
		uEvent.setNotes(compForm.getCasenoteText());
		uEvent.setSaveAsDraft(isDraft);
		uEvent.setSuperviseeId(compForm.getSuperviseeId());
		String casenoteTime = compForm.getCasenoteTime();
		if (compForm.getAMPMId().equalsIgnoreCase("PM")){
			String[] theTime = compForm.getCasenoteTime().split(":");
			int iHr = Integer.parseInt(theTime[0]);
			if (iHr < 12){
				iHr = iHr + 12;
			}	
			String sHr = Integer.toString(iHr);
			casenoteTime = sHr + ":" + theTime[1];
		}		
		uEvent.setEntryDate(UIAdministerComplianceHelper.convertToDateTime(compForm.getCasenoteDateAsString(), casenoteTime));

		Collection subjects = new ArrayList();
		for (int i = 0; i < compForm.getSubjectIds().length; i++) {
			subjects.add(compForm.getSubjectIds()[i]);
		}
		uEvent.setSubjects(subjects);
		uEvent.setCasenoteTypeId(compForm.getCasenoteTypeId());
		
		String associateIds[] = compForm.getSelectedAssociateIds();
		Collection associates = new ArrayList();
		for (int i = 0; i < associateIds.length; i++) {
			if(associateIds[i]!=null && !associateIds[i].equals("") )
				associates.add(associateIds[i]);
		}
		uEvent.setAssociates(associates);	
		
		ucEvent.addRequest(uEvent);
		int len = compForm.getSelectedConditions().size();
		int[] sprOrderConditionIds = new int[len];
		int[] conditionIds = new int[len];
		String[] caseNumbers = new String[len];
		int[] orderChainNumbers = new int[len];
		
		for (int x = 0; x < len; x++) {
			ComplianceConditionResponseEvent ccrEvent = (ComplianceConditionResponseEvent) compForm
					.getSelectedConditions().get(x);
			sprOrderConditionIds[x] = Integer.parseInt(ccrEvent.getSprOrderConditionId());
			conditionIds[x] = Integer.parseInt(ccrEvent.getConditionId());
			caseNumbers[x] = ccrEvent.getCaseNumber();
			orderChainNumbers[x] = ccrEvent.getOrderChainNumber();
		}
		ucEvent.setSprOrderConditionIds(sprOrderConditionIds);
		ucEvent.setCaseNumbers(caseNumbers);
	    ucEvent.setConditionIds(conditionIds);
	    ucEvent.setOrderChainNumbers(orderChainNumbers);
		return ucEvent;
	}

	/**
	 * Create Date from date and time Strings
	 * 
	 * @return Date
	 * @pre strDate != null
	 * @pre strTime != null
	 * @pre strDate = mm/dd/yyyy format
	 * @pre strDate.length() == 10
	 * @pre strTime.length() <= 5 (HH:MM)
	 */
	public Date convertToDateTime(String strDate, String strTime) {

		Date date = null;
		try {
			int year = new Integer(strDate.substring(6, 10)).intValue();
			int month = new Integer(strDate.substring(0, 2)).intValue();
			int day = new Integer(strDate.substring(3, 5)).intValue();
			int hours = new Integer(strTime.substring(0, 2)).intValue();
			int minutes = new Integer(strTime.substring(3, 5)).intValue();
			int seconds = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setLenient(false);
			calendar.set(year, month - 1, day, hours, minutes, seconds);
			date = calendar.getTime();
		} catch (NumberFormatException e) {
		} catch (IndexOutOfBoundsException e) {
		} catch (IllegalArgumentException e) {
			//This exception is thrown if calendar was created with an invalid
			// date.
		}
		return date;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */	
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		return super.cancel(aMapping,aForm,aRequest,aResponse);
	}	
}
