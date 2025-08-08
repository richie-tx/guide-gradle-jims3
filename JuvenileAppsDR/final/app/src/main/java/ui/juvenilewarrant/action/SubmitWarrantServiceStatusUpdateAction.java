package ui.juvenilewarrant.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.UpdateJuvenileWarrantServiceEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Address;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author hrodriguez
 *  
 */
public class SubmitWarrantServiceStatusUpdateAction extends LookupDispatchAction
{

    /**
     * @roseuid 41FFDD2E0148
     */
    public SubmitWarrantServiceStatusUpdateAction()
    {

    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();

        buttonMap.put("button.finish", "finish");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.mainPage", "mainPage");
        return buttonMap;
    }

    public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {
         	
       return aMapping.findForward(UIConstants.MAIN_PAGE);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41FFC5E60277
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        boolean badAddress = false;
        Address selectedAddress = null;
        
        UpdateJuvenileWarrantServiceEvent updateEvent = (UpdateJuvenileWarrantServiceEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.UPDATEJUVENILEWARRANTSERVICE);

        // Get existing associate address if associate address was selected
        if (!jwForm.getAddressId().equals("newAddress") && !jwForm.getSelectedAssociateAddressId().equals(""))
        {
            selectedAddress = UIJuvenileHelper.getAddressFromAddressId(jwForm.getSelectedAssociateAddressId());            
           
                if (selectedAddress != null)
                {
                    jwForm.setStreetNum(selectedAddress.getStreetNum());
                    jwForm.setStreetName(selectedAddress.getStreetName());
                    jwForm.setStreetType(selectedAddress.getStreetTypeId());
                    jwForm.setApartmentNum(selectedAddress.getAptNum());
                    jwForm.setCity(selectedAddress.getCity());
                    jwForm.setState(selectedAddress.getStateId());
                    jwForm.setZipCode(selectedAddress.getZipCode());
                    jwForm.setZipCode2(selectedAddress.getAdditionalZipCode());
                    jwForm.setCounty(selectedAddress.getCountyId());
                    jwForm.setAddressType(selectedAddress.getAddressTypeId());
                    jwForm.setAddressId(jwForm.getSelectedAssociateAddressId());
                    if (jwForm.getCurrentServiceBadAddress().equalsIgnoreCase("yes"))
                    {
                        badAddress = true;
                    }
                }
        }

        updateEvent.setServiceStatus(jwForm.getCurrentWarrantServiceStatus());
        updateEvent.setComments(jwForm.getCurrentServiceAttemptComments());
        updateEvent.setZipCode(jwForm.getZipCode());
        updateEvent.setAdditionalZipCode(jwForm.getZipCode2());
        if (jwForm.getCostAirFare() != null && jwForm.getCostAirFare().equals("") == false)
        {
            updateEvent.setAirFare(new Double(jwForm.getCostAirFare().trim()));
        }
        if (jwForm.getCostMileage() != null && jwForm.getCostMileage().equals("") == false)
        {
            updateEvent.setMileage(new Double(jwForm.getCostMileage().trim()));
        }
        if (jwForm.getCostPerDiem() != null && jwForm.getCostPerDiem().equals("") == false)
        {
            updateEvent.setPerDiem(new Double(jwForm.getCostPerDiem().trim()));
        }
        updateEvent.setApartmentNumber(jwForm.getApartmentNum());

        updateEvent.setCity(jwForm.getCity());
        updateEvent.setState(jwForm.getState());
        updateEvent.setCounty(jwForm.getCounty());
        updateEvent.setStreetName(jwForm.getStreetName());
        updateEvent.setStreetNumber(jwForm.getStreetNum());
        updateEvent.setStreetType(jwForm.getStreetType());
        updateEvent.setAddressType(jwForm.getAddressType());

        updateEvent.setWarrantNumber(jwForm.getWarrantNum());
        updateEvent.setOfficerId(jwForm.getOfficerOID());
        updateEvent.setOfficerDepartmentId(jwForm.getOfficerAgencyId());
        updateEvent.setAddressId(jwForm.getAddressId());
        
        updateEvent.setBadAddress(badAddress);
        updateEvent.setServiceTimeStamp(jwForm.getCurrentServiceDate());

        CompositeResponse response = MessageUtil.postRequest(updateEvent);

        JuvenileWarrantResponseEvent jwEvent = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
                JuvenileWarrantResponseEvent.class);

        jwForm.setWarrantStatusId(jwEvent.getWarrantStatusId());
        jwForm.setWarrantActivationStatusId(jwEvent.getWarrantActivationStatusId());
        
        //send email to jpo of record
        if(jwForm.getAddressId().equals("newAddress")){
            
            JuvenileAssociateBean associate = UIJuvenileWarrantHelper.GetJuvenileFamilyMemberById(jwForm.getJuvenileNum(), jwForm.getSelectedAssociateId());
            
            UIJuvenileWarrantHelper.SendEmailWarrantAddressUpdate(associate, jwForm);            
        }

        jwForm.setAction(UIConstants.CONFIRM);

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }
}
