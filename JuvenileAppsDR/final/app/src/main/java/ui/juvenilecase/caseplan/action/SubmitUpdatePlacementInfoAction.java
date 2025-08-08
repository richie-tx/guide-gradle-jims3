package ui.juvenilecase.caseplan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.SavePlacementInformationEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class SubmitUpdatePlacementInfoAction extends LookupDispatchAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;

        aRequest.setAttribute("status", "confirm");
        SavePlacementInformationEvent placement = (SavePlacementInformationEvent) EventFactory
                .getInstance(JuvenileCasePlanControllerServiceNames.SAVEPLACEMENTINFORMATION);
        placement.setCasefileID(form.getCasefileId());

        boolean isPlacementWithoutGoals = false;

        if (form.getCaseplanExist().equalsIgnoreCase("Y"))
        {
            placement.setCaseplanID(form.getCurrentCaseplan().getCaseplanId());
        }
        else
        {
            placement.setCaseplanID("0");
            isPlacementWithoutGoals = true;
        }
        CaseplanForm.PlacementInfo info = form.getCurrentPlacementInfo();
        placement.setEntryDate(DateUtil.stringToDate(info.getEntryDate(),"MM/dd/yyyy"));
        if (info.getClosestFacilityAvailable().equalsIgnoreCase("YES"))
            placement.setClosestFacilityAvail(true);
        else
            placement.setClosestFacilityAvail(false);
        if (info.getLeastRestrictiveEnv().equalsIgnoreCase("YES"))
            placement.setLeastRestrEnvAvail(true);
        else
            placement.setLeastRestrEnvAvail(false);
        if (info.getProximityConsidered().equalsIgnoreCase("YES"))
            placement.setProxToChildsSD(true);
        else
            placement.setProxToChildsSD(false);

        placement.setReasonPlacementReqd(info.getReasonChildRequiresPlacement());
        placement.setSpecificServices(info.getSpecificServicesProvided());
        placement.setWhyOutsideTexas(info.getReasonChildIsPlacedOutsideOfTexas());
        placement.setFacilityId(info.getFacilityId());
        placement.setFacilityRelReasonId(info.getFacilityReleaseReasonId());
        placement.setExpReleaseDate(DateUtil.stringToDate(info.getExpectedReleaseDate(), "MM/dd/yyyy"));
        placement.setLevelOfCareId(info.getLevelOfCareId());
        placement.setPermanencyPlanId(info.getPermanencyPlanId());
        placement.setSpecialNotes(info.getSpecialNotes());
        placement.setPlacementID(info.getPlacementID());
        CaseplanDetailsResponseEvent cpEvt = (CaseplanDetailsResponseEvent) MessageUtil.postRequest(placement,
                CaseplanDetailsResponseEvent.class);

        // notification when caseplan is created without goals
        if (isPlacementWithoutGoals)
        {
            JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest, true);
            casefileForm.populateJuvenileCasefileForm(form.getCasefileId());
            if (cpEvt != null)
            {
                if (cpEvt.getCaseplanID() != null)
                {
                    CaseplanDetailsResponseEvent nevt = new CaseplanDetailsResponseEvent();
                    nevt.setSubject("Develop Caseplan " + form.getCasefileId());
                    nevt.setNotificationMessage("Goals have not been created for " + casefileForm.getJuvenileFullName()
                            + " for casefile # " + form.getCasefileId());
                    nevt.setIdentity(casefileForm.getProbationOfficerLogonId());
                    sendNotification(nevt, UIConstants.PROCESS_CASEPLAN_GOALS_NEEDED);
                }
            }
        }

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    public void sendNotification(CaseplanDetailsResponseEvent nevt, String topic)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
        notificationEvent.setNotificationTopic(topic);
        notificationEvent.setSubject(nevt.getSubject());
        notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
        notificationEvent.addContentBean(nevt);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
    }

}