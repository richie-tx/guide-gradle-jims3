//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\HandleOfficerProfileSelectionAction.java

package ui.contact.officer.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.OfficerTrainingResponseEvent;
import messaging.contact.officer.reply.TrainingTopicsResponseEvent;
import messaging.officer.GetAllTrainingTopicsEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.officer.GetOfficerTrainingEvent;
import messaging.officer.UpdateOfficerTrainingEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.contact.UIContactHelper;
import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class HandleOfficerProfileSelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E6766D016E
	 */
	public HandleOfficerProfileSelectionAction()
	{

	}
	
	 protected Map getKeyMethodMap()
	    {
	     	Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.addTraining", "addOfficerTraining");
		
		return keyMap;
	     
	    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA70334
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		if (!SecurityUIHelper.isLoggedIn())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		String action = aRequest.getParameter("action");
		String officerProfileId = aRequest.getParameter("officerProfileId");

		if (action == null || action.equals(""))
		{
			return aMapping.findForward(UIConstants.FAILURE);
		}

		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.setAction(action);
		
		if (SecurityUIHelper.getUserTypeId().equalsIgnoreCase("MA"))
		{
			officerForm.setJuvLocations(CodeHelper.getLocationCodes("JUV"));
		} else {
			officerForm.setJuvLocations(CodeHelper.getLocationCodes());
		}
		officerForm.setJuvUnits(UIContactHelper.getJuvUnitCodes()); 

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfileEvent officerEvent = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
		officerEvent.setOfficerProfileId(officerProfileId);
		dispatch.postEvent(officerEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		OfficerProfileResponseEvent officerResponseEvent = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		UIContactHelper.setOfficerProfileForm(officerResponseEvent, officerForm);

		// set the original value of critical data
		setOriginalValueOfCriticalData(officerResponseEvent, officerForm);
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
		// 06/21/2007 part of defect#41297
		// not sure why UserId is being set with current logonId but added action != view so numberViewable could be set correctly		
//		if (officerForm.isOfficer() && !action.equalsIgnoreCase(UIConstants.VIEW))
//		{
//			officerForm.setUserId(SecurityUIHelper.getLogonId());
//		}
		
		refreshOfficerTraining(officerForm, officerProfileId);
		

		if (action.equalsIgnoreCase(UIConstants.INACTIVATE))
		{
			forward = aMapping.findForward(UIConstants.DELETE_SUCCESS);
		}
		else
			if (action.equalsIgnoreCase(UIConstants.VIEW))
			{
				setNumberViewable(officerForm);
				forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
			}
			else
				if (action.equalsIgnoreCase("AddTraining"))
				{
				    addOfficerTraining(officerForm,officerProfileId);
				    setNumberViewable(officerForm);
				    
				    refreshOfficerTraining(officerForm, officerProfileId);
				    officerForm.setAction(UIConstants.VIEW);
				    forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
				}
			else
				if (action.equalsIgnoreCase(UIConstants.UPDATE))
				{
				    if (SecurityUIHelper.isUserSA())
					{
						officerForm.setSA(true);
					}
				    else
					if (SecurityUIHelper.isUserMA())
					{
						officerForm.setMA(true);
					}
					
					forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
				}
				else
					if (action.equalsIgnoreCase(UIConstants.CANCEL))
					{
						forward = aMapping.findForward(UIConstants.CANCEL);
					}
					else
					{
						forward = aMapping.findForward(UIConstants.FAILURE);
					}
		return forward;
	}

	/**
	 * @param officerResponseEvent
	 * @param officerForm
	 */
	private void setOriginalValueOfCriticalData(
		OfficerProfileResponseEvent officerResponseEvent,
		OfficerForm officerForm)
	{
		officerForm.setOriginalBadgeNumber(officerResponseEvent.getBadgeNum());
		officerForm.setOriginalOtherIdNumber(officerResponseEvent.getOtherIdNum());
		officerForm.setOriginalUserId(officerResponseEvent.getUserId());
	}

	/**
	 * @param aRequest
	 * @param errorKey
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}

	/**
	 * @param officerForm
	 */
	private void setNumberViewable(OfficerForm officerForm)
	{
		officerForm.setNumberViewable("N");
		String userType = SecurityUIHelper.getUserTypeId();
  		if (userType.equalsIgnoreCase("MA") || userType.equalsIgnoreCase("SA") || userType.equalsIgnoreCase("ASA") || userType.equalsIgnoreCase("LA")  )
  		{
  			officerForm.setNumberViewable("Y");
  		}
  		if (officerForm.getNumberViewable().equals("N") && officerForm.getUserId()!= null && !officerForm.getUserId().equals(""))
  		{
  			if(SecurityUIHelper.getLogonId()!= null && SecurityUIHelper.getLogonId().equalsIgnoreCase(officerForm.getUserId()))
  			{	
  				officerForm.setNumberViewable("Y");
  			}	
  		}
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public void addOfficerTraining(OfficerForm form, String officerProfileId)
	{

	    UpdateOfficerTrainingEvent uptEvent = new UpdateOfficerTrainingEvent();
	    uptEvent.setTrainingTopicsId(Integer.parseInt(form.getTrainingTopicId()));
	    uptEvent.setTrainingBeginDate(DateUtil.stringToDate(form.getTrainingBegDate(),DateUtil.DATE_FMT_1));
	    uptEvent.setTrainingEndDate(DateUtil.stringToDate(form.getTrainingEndDate(),DateUtil.DATE_FMT_1));
	    uptEvent.setTrainingHours(form.getTrainingHours());
	    uptEvent.setOfficerId(Integer.parseInt(officerProfileId));
	    
	    MessageUtil.postRequest(uptEvent);	    
	    
	}

	public void refreshOfficerTraining(OfficerForm form, String officerProfileId){
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    GetOfficerTrainingEvent getTraining = (GetOfficerTrainingEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERTRAINING);
	    getTraining.setOfficerProfileId(officerProfileId);
	    dispatch.postEvent(getTraining);
		
	    Collection<OfficerTrainingResponseEvent> officerTrainings = MessageUtil.postRequestListFilter(getTraining, OfficerTrainingResponseEvent.class);
	    form.setOfficerTraining(officerTrainings);
	}

}