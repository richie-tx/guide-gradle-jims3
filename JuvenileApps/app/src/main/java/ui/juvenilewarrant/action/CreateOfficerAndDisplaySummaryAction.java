package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.PDOfficerProfileConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateOfficerAndDisplaySummaryAction extends LookupDispatchAction
{

    private static final String BACK_SERVICE = "backService";

    private static final String CANCEL_SERVICE = "cancelService";

    /**
     * public constructor
     */
    public CreateOfficerAndDisplaySummaryAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        form.clearOfficerInfo();
        String forwardStr = UIConstants.BACK;

        // If the warrant is EXECUTED, then must be in the Release to JP flow.
        if (PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(form.getWarrantStatusId()))
        {
            forwardStr = BACK_SERVICE;
        }
        else if (PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(form.getWarrantStatusId()))
        {
            forwardStr = UIConstants.RELEASE_TOJP;
        }

        return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        aRequest.setAttribute("warrantTypeUI", form.getWarrantTypeUI());

        String forwardStr = UIConstants.CANCEL;
        if (PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(form.getWarrantStatusId()))
        {
            forwardStr = CANCEL_SERVICE;
        }

        form.clear();
        return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        UpdateOfficerProfileEvent updateOfficerEvent = (UpdateOfficerProfileEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.UPDATEOFFICERPROFILE);

        updateOfficerEvent.setOfficerProfileId(jwForm.getOfficerOID());
        updateOfficerEvent.setFirstName(jwForm.getOfficerName().getFirstName());
        updateOfficerEvent.setMiddleName(jwForm.getOfficerName().getMiddleName());
        updateOfficerEvent.setLastName(jwForm.getOfficerName().getLastName());
        updateOfficerEvent.setWorkPhone(jwForm.getWorkPhone().getPhoneNumber());
        updateOfficerEvent.setPager(jwForm.getPager().getPhoneNumber());
        updateOfficerEvent.setCellPhone(jwForm.getCellPhone().getPhoneNumber());
        updateOfficerEvent.setEmail(jwForm.getEmail());
        updateOfficerEvent.setDepartmentId(jwForm.getOfficerAgencyId());
        updateOfficerEvent.setBadgeNum(jwForm.getOfficerBadgeNumber());
        updateOfficerEvent.setOtherIdNum(jwForm.getOfficerOtherIdNumber());
        updateOfficerEvent.setUserID(jwForm.getOfficerLogonId());
        updateOfficerEvent.setLogonId(jwForm.getOfficerLogonId());
        updateOfficerEvent.setOfficerTypeId(PDOfficerProfileConstants.LAW_ENFORCEMENT_OFFICER_ID);
        updateOfficerEvent.setStatusId(UIConstants.ACTIVE);        

        // get the officer OID to be send in create juv warrant
        CompositeResponse response = MessageUtil.postRequest(updateOfficerEvent);

        OfficerProfileResponseEvent officerData = (OfficerProfileResponseEvent) MessageUtil.filterComposite(response,
                OfficerProfileResponseEvent.class);

        if (officerData != null)
        {
            jwForm.setOfficerOID(officerData.getOfficerProfileId());
            if (UIConstants.WARRANT_SERVICE.equals(jwForm.getWarrantTypeUI()))
            {
                jwForm.setExecutorProperties(officerData);
            }
            else if (UIConstants.RELEASE_TOJP_SUCCESS.equals(jwForm.getWarrantTypeUI()))
            {
                jwForm.setTransferOfficerDepartmentName(officerData.getDepartmentName());
                if (officerData.getBadgeNum() != null && !officerData.getBadgeNum().equals(""))
                {
                    jwForm.setTransferOfficerIdType(PDOfficerProfileConstants.BADGE_NUM);
                    jwForm.setTransferOfficerId(officerData.getBadgeNum());
                }
                else
                {
                    jwForm.setTransferOfficerIdType(PDOfficerProfileConstants.ID_NUM);
                    jwForm.setTransferOfficerId(officerData.getOtherIdNum());
                }
            }
            jwForm.setOfficerProperties(officerData);
        }

        return this.computeForward(aMapping, jwForm);
    }

    /**
     * 
     * @param aMapping
     * @param jwForm
     * @return ActionForward forward
     */
    private ActionForward computeForward(ActionMapping aMapping, JuvenileWarrantForm jwForm)
    {
        String forward = null;

        // 03-03-05 CShimek - add edit if party being created is exectur
        // for warrant service status update
        if (UIConstants.WARRANT_SERVICE.equals(jwForm.getWarrantTypeUI()))
        {
            forward = UIConstants.WARRANT_SERVICE_SUCCESS;
        }
        else if (UIConstants.RELEASE_TOJP_SUCCESS.equals(jwForm.getWarrantTypeUI()))
        {
            forward = UIConstants.RELEASE_TOJP_SUCCESS;
        }
        else
        {
            forward = UIConstants.SUCCESS;
        }

        return aMapping.findForward(forward);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.back", "back");
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        return keyMap;
    }
}
