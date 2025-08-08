package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenile.SaveJuvenileContactEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.juvenilecase.form.JuvenileContactForm;

public class SubmitJuvenileContactCreateAction extends Action
{
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    /**
     * @roseuid 42AF40A0031C
     */
    public SubmitJuvenileContactCreateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42AF3EE00021
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileContactForm jcForm = (JuvenileContactForm) aForm;

        SaveJuvenileContactEvent request = (SaveJuvenileContactEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.SAVEJUVENILECONTACT);
        if(jcForm.getAction().equals(UIConstants.UPDATE)){
        	request.setContactNum(jcForm.getContactNum());
        }
        else{
        	request.setContactNum(null);
        }
        request.setAddressType(jcForm.getAddressTypeId());
        request.setApartmentNum(jcForm.getApartmentNum());
        request.setCity(jcForm.getCity());
        request.setCurrentAgencyInvolvementId(jcForm.getCurrentAgencyInvolvementId());
        request.setJuvenileNum(jcForm.getJuvenileNum());
        request.setPreviousAgencyInvolvementId(jcForm.getPreviousAgencyInvolvementId());
        request.setState(jcForm.getStateId());
        request.setStreetName(jcForm.getStreetName());
        request.setStreetNum(jcForm.getStreetNum());
        request.setStreetNumSuffix(jcForm.getStreetNumSuffixId());
        request.setStreetType(jcForm.getStreetTypeId());
        request.setZipCode(jcForm.getZipCode());
        request.setAdditionalZipCode(jcForm.getAdditionalZipCode());
        request.setEMail(jcForm.getEMail());
        request.setPhonePriorityInd(jcForm.getPhonePriorityInd());
        request.setEntryDate(DateUtil.stringToDate(jcForm.getEntryDate(), DATE_FORMAT));

        request.setCounty(jcForm.getCountyId());
        request.setAgencyName(jcForm.getAgencyNameId());
        request.setRelationshipId(jcForm.getRelationshipId());
        request.setDetentionVisit(jcForm.isDetentionVisit());
        request.setAgeOver21(jcForm.isAgeOver21());
        request.setDriverLicenseNum(jcForm.getDriverLicenseNum());
        request.setDriverLicenseStateId(jcForm.getDriverLicenseStateId());
        request.setDriverLicenseClassId(jcForm.getDriverLicenseClassId());
        request.setDriverLicenseExpirationDate(jcForm.getDriverLicenseExpirationDate());
        request.setIssuedByStateId(jcForm.getIssuedByStateId());
        request.setStateIssuedIdNum(jcForm.getStateIssuedIdNum());
        request.setPassportNum(jcForm.getPassportNum());
        request.setCountryOfIssuanceId(jcForm.getCountryOfIssuanceId());
        request.setPassportExpirationDate(jcForm.getPassportExpirationDate());
        request.setContactMemberComments(jcForm.getContactMemberComments().replaceAll("\\s+", " ") );
        
        Name contactName = jcForm.getContactName();
        if (contactName != null)
        {
            request.setFirstName(contactName.getFirstName());
            request.setMiddleName(contactName.getMiddleName());
            request.setLastName(contactName.getLastName());
        }
        request.setPrefixId(jcForm.getTitleId());
        PhoneNumber phoneNum = jcForm.getCellPhone();
        if (phoneNum != null)
        {
            request.setCellPhone(phoneNum.getPhoneNumber());
        }
        phoneNum = jcForm.getFax();
        if (phoneNum != null)
        {
            request.setFax(phoneNum.getPhoneNumber());
        }
        phoneNum = jcForm.getWorkPhone();
        if (phoneNum != null)
        {
            request.setWorkPhone(phoneNum.getPhoneNumber());
            if (phoneNum.getExt() != null) {
            	request.setWorkPhoneExtn(phoneNum.getExt());	
            }
        }
        request.setValidated(jcForm.getValidated());

        CompositeResponse response = MessageUtil.postRequest(request);

        JuvenileContactResponseEvent respEvent = (JuvenileContactResponseEvent) MessageUtil.filterComposite(response,
                JuvenileContactResponseEvent.class);
        if (respEvent != null)
        {
            jcForm.setContactNum(respEvent.getContactNum());
        }

        jcForm.setSecondaryAction(UIConstants.CONFIRM);
        return aMapping.findForward(UIConstants.SUCCESS);
    }
}
