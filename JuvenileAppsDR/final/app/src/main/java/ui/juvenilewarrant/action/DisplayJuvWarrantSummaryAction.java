package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.officer.ValidateOfficerEvent;
import messaging.user.GetUserEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.PDOfficerProfileConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 *  
 */
public class DisplayJuvWarrantSummaryAction extends JIMSBaseAction
{
    /**
     * Public constructor
     */
    public DisplayJuvWarrantSummaryAction()
    {

	return;
    }

    public void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.next", UIConstants.NEXT);
        buttonMap.put("button.go", UIConstants.GO);
        buttonMap.put("button.findDepartment", "findDepartment");
        buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
        buttonMap.put("button.link", "link");
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        String forwardStr = null;
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        String warrantTypeUI=form.getWarrantTypeUI();
        
        if (warrantTypeUI != null && !warrantTypeUI.equals(""))
        {
        	form.setWarrantTypeId(form.getWarrantTypeUI().toUpperCase());
        }

        // TODO Use the constants throughout JW
        if (form.isJOT())
        {
            forwardStr = this.processJOTForm(form);
            String userId = form.getUserId();
            if(userId != null && !userId.equals(""))
            {
	            Object obj = getUserResponse(userId);
	            UserResponseEvent resp = (UserResponseEvent) obj;
	            if (forwardStr.equals("invalidLawEnforcement"))
	            {
	                ActionErrors errors = new ActionErrors();
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userLoginId.found", form.getUserId()));
	                saveErrors(aRequest, errors);
	                forwardStr = "invalidLawEnforcement";
	                form.setOfficerAgencyId("");
	            } else if (!resp.getGenericUserType().equalsIgnoreCase("N"))
	            {
	                ActionErrors errors = new ActionErrors();
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.genericUserId"));
	                saveErrors(aRequest, errors);
	                forwardStr = "invalidLawEnforcement";
	            }
            }
        }
        else if (form.isJJS())
        {
            forwardStr = this.processJJSForm(form, aRequest);
        }
        else
        {
            // TODO Handle this error condition
        }

        form.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());
        form.setBackForward(forwardStr);       
        return aMapping.findForward(forwardStr);
    }

    public ActionForward findDepartment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        jwForm.setOfficerAgencyId("");
        jwForm.setOfficerAgencyName("");
        jwForm.setOfficerId("");
        jwForm.setOfficerIdType("");
        String returnString = UIConstants.FIND_SUCCESS;
        String userId = jwForm.getUserId();
        Object obj = getUserResponse(userId);
        ActionErrors errors = new ActionErrors();
        if (obj != null)
        {
            UserResponseEvent resp = (UserResponseEvent) obj;
            CompositeResponse compResp = validateOfficerByUserId(jwForm);
            OfficerProfileResponseEvent ore = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compResp,
                    OfficerProfileResponseEvent.class);
            if (ore != null)
            {
                jwForm.setAgencyId(ore.getDepartmentId());
                jwForm.setOfficerAgencyId(ore.getDepartmentId());
                jwForm.setOfficerAgencyName(ore.getDepartmentName());
                returnString = UIConstants.FIND_SUCCESS;
            }
            else if (!resp.getGenericUserType().equalsIgnoreCase("N"))
            {
                jwForm.setAgencyId("");
                jwForm.setOfficerAgencyId("");
                jwForm.setOfficerAgencyName("");
                jwForm.setAction("");
                jwForm.setSearch("userSearch");
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.genericUserId"));
                saveErrors(aRequest, errors);
                returnString = "failure";
            }
            else
            {
                jwForm.setAgencyId("");
                jwForm.setOfficerAgencyId("");
                jwForm.setOfficerAgencyName("");
                jwForm.setAction("");
                jwForm.setSearch("userSearch");
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officerProfile.notfound"));
                saveErrors(aRequest, errors);
                returnString = "failure";
            }
        }
        else
        {
            jwForm.setAgencyId("");
            jwForm.setOfficerAgencyId("");            
            jwForm.setOfficerAgencyName("");
            jwForm.setAction("");
            jwForm.setSearch("userSearch");
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userLoginId.found", jwForm.getUserId()));
            saveErrors(aRequest, errors);
            returnString = "failure";
        }
        return aMapping.findForward(returnString);
    }

    public ActionForward validateDepartmentCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        DepartmentResponseEvent respEvent = UIJuvenileWarrantHelper.fetchDepartment(jwForm.getOfficerAgencyId());
        if (respEvent == null)
        {
            jwForm.setOfficerAgencyName("");
            this.sendToErrorPage(aRequest, "error.no.department.found");
        }
     /*   else
        {
            jwForm.setOfficerAgencyName(respEvent.getDepartmentName());
            jwForm.setSearch("officerSearch");
            this.sendToErrorPage(aRequest, "error.generic", "Officer Profile not found. Click next to access the Create Officer Profile page.");
        }
*/        return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);
    }

    // TODO Generalize this when doing the ER for charges and sequenceNum
    private List getSelectedCharges(Collection charges, String[] selectedCharges)
    {
        List chargesSelected = new ArrayList();
        if (selectedCharges != null)
        {
            for (int i = 0; i < selectedCharges.length; i++)
            {
                Iterator j = charges.iterator();
                while (j.hasNext())
                {
                    ChargeResponseEvent charge = (ChargeResponseEvent) j.next();
                    if (charge.getSequenceNum().equals(selectedCharges[i]))
                    {
                        chargesSelected.add(charge);
                    }
                }
            }
        }
        return chargesSelected;
    }

    private String processJOTForm(JuvenileWarrantForm aForm)
    {
        // TODO Use constants instead of literals

        String forwardStr = null;

        // Set the charges selected in the previous page
        //Collection charges = CodeHelper.getSelectedCodes(aForm.getCharges(),
        // aForm.getSelectedCharges());
        List charges = this.getSelectedCharges(aForm.getCharges(), aForm.getSelectedCharges());
        aForm.setChargesSelected(charges);

        /*
         * We need to check for the officer to be present based on the userID or
         * officer ID and agency entered on the create page. If an officer is
         * found, get the additional officer fields and display the summary
         * page, ELSE we need to display the create party pages and display the
         * warrant summary
         */

        CompositeResponse compositeResponse = null;

        if (aForm.getSearch().equalsIgnoreCase("userSearch"))
        {
            compositeResponse = this.validateOfficerByUserId(aForm);
        }
        else
        {
            compositeResponse = this.validateOfficerByOfficerId(aForm);
        }

        OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(
                compositeResponse, OfficerProfileResponseEvent.class);

        UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse,
                UserResponseEvent.class);

        InvalidUserResponseEvent invalidUser = (InvalidUserResponseEvent) MessageUtil.filterComposite(compositeResponse,
                InvalidUserResponseEvent.class);

        if (userResponse != null)
        {
            // execute if a valid user does not have an officer record
            forwardStr = UIConstants.LAW_ENFORCEMENT;
            this.setForm(userResponse, aForm);
        }
        else if (officerResponse != null)
        {
            // execute if user has a valid officer record
            forwardStr = UIConstants.SUCCESS;
            aForm.setOfficerProperties(officerResponse);
        }
        else if (invalidUser != null)
        {
            // execute if user and officer are not valid
            forwardStr = UIConstants.INVALID_LAW_ENFORCEMENT;
        }
        else
        {
            // no information is found for this officer
            forwardStr = UIConstants.LAW_ENFORCEMENT;
        }

        return forwardStr;
    }

    private CompositeResponse validateOfficerByUserId(JuvenileWarrantForm aForm)
    {
        ValidateOfficerEvent officerEvent = (ValidateOfficerEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICER);

        officerEvent.setLogonId(aForm.getUserId());
        officerEvent.setValidateByUserId(true);

        return MessageUtil.postRequest(officerEvent);
    }

    private CompositeResponse validateOfficerByOfficerId(JuvenileWarrantForm aForm)
    {
        ValidateOfficerEvent officerEvent = (ValidateOfficerEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICER);

        officerEvent.setDepartmentId(aForm.getOfficerAgencyId());
        officerEvent.setOfficerId(aForm.getOfficerId());
        officerEvent.setOfficerIdType(aForm.getOfficerIdTypeId());
        aForm.clearOfficerName();
        if (aForm.getOfficerIdTypeId().equalsIgnoreCase(PDOfficerProfileConstants.BADGE_NUM_ID))
        {
            aForm.setOfficerBadgeNumber(aForm.getOfficerId());
        }
        if (aForm.getOfficerIdTypeId().equalsIgnoreCase(PDOfficerProfileConstants.OTHER_NUM_ID))
        {
            aForm.setOfficerOtherIdNumber(aForm.getOfficerId());
        }
        officerEvent.setValidateByUserId(false);
        
        return MessageUtil.postRequest(officerEvent);
    }

    private void setForm(UserResponseEvent response, JuvenileWarrantForm aForm)
    {
        aForm.setOfficerAgencyId(response.getDepartmentId());
        aForm.setOfficerAgencyName(response.getDepartmentName());
        /*
         * 13 july 2006 - mjt - for Defect 32769 - the next line of code was:
         * form.setOfficerName(new Name(userData.getFirstName(), "",
         * userData.getLastName()));
         */
        IName officerName = new Name();
        officerName.setFirstName(response.getFirstName());
        officerName.setMiddleName(response.getMiddleName());
        officerName.setLastName(response.getLastName());
        aForm.setOfficerName(officerName);
        aForm.setWorkPhone(new PhoneNumber(response.getPhoneNum()));
        aForm.setEmail(response.getEmail());
        aForm.setOfficerLogonId(response.getLogonId());
    }

    /**
     * @param compositeResponse
     * @param aForm
     * @return
     
    private String getForward(CompositeResponse composite, JuvenileWarrantForm form)
    {
        OfficerProfileResponseEvent officerData = (OfficerProfileResponseEvent) MessageUtil.filterComposite(composite,
                OfficerProfileResponseEvent.class);
        String forwardStr = null;

        if (officerData == null)
        {
            UserResponseEvent userData = (UserResponseEvent) MessageUtil.filterComposite(composite, UserResponseEvent.class);
            if (userData != null)
            {
                form.setOfficerAgencyId(userData.getDepartmentId());
                form.setOfficerAgencyName(userData.getDepartmentName());
                /*
                 * 13 july 2006 - mjt - for Defect 32769 - the next line of code
                 * was: form.setOfficerName(new Name(userData.getFirstName(),
                 * "", userData.getLastName()));
                 
                IName officerName = new Name();
                officerName.setFirstName(userData.getFirstName());
                officerName.setMiddleName(userData.getMiddleName());
                officerName.setLastName(userData.getLastName());
                form.setOfficerName(officerName);
                form.setWorkPhone(new PhoneNumber(userData.getPhoneNum()));
                form.setEmail(userData.getEmail());
                form.setOfficerLogonId(userData.getLogonId());
            }

            forwardStr = UIConstants.LAW_ENFORCEMENT;
        }
        else
        {
            forwardStr = UIConstants.SUCCESS;
            form.setOfficerProperties(officerData);
        }

        return forwardStr;
    }*/

    private String processJJSForm(JuvenileWarrantForm aForm, HttpServletRequest aRequest)
    {
        String forward = UIConstants.SUCCESS;

        /*
         * SSN is now editable for initiate JJS warrant types (VOP and OIC).
         * Since Struts auto call setSSN1(), setSSN2() and setSSN3() upon
         * submission the form, the jwForm.ssn exists, but not valid
         * (jwForm.ssn.valid is still set to false). The only way to make it
         * valid is to create a new SocialSecurity object.
         */
        aForm.setSsn(new SocialSecurity(aForm.getSSN1(), aForm.getSSN2(), aForm.getSSN3()));

        aForm.refreshSchoolDescriptions();

        String[] selectedCharges = new String[1];
        selectedCharges[0] = aForm.getChargeSeqNum();
        aForm.setSelectedCharges(selectedCharges);

        this.setCharges(aForm);

        return forward;
    }

    /**
     * @param aForm
     */
    private void setCharges(JuvenileWarrantForm aForm)
    {
        List chrgsSelected = new ArrayList();
        String[] selectedCharges = aForm.getSelectedCharges();
        for (int i = 0; i < selectedCharges.length; i++)
        {
            String selectedChargeValue = selectedCharges[i];

            // (DAG) Avoid problem where selectedChargeValue contains a blank
            // value
            if ((selectedChargeValue != null) && (!selectedChargeValue.equals("")))
            {
                // TODO These response events should be generalized in a single
                // IncidentResponseEvent
                Iterator j = aForm.getCharges().iterator();

                while (j.hasNext())
                {
                    /*
                     * Charges could be either JOT or JJS Charges. Check to see
                     * what type of Response Objects were received. JOT will use
                     * SequenceNum as its identifier, while JJS will use a
                     * concatenated key defined in the JJSChargeResponseEvent
                     * getUniqueId method.
                     */
                    PetitionResponseEvent petitionEvent = (PetitionResponseEvent) j.next();

                    if (petitionEvent.getSequenceNum().equals(selectedChargeValue))
                    {
                        chrgsSelected.add(petitionEvent);
                    }

                }
            }
        }
        aForm.setChargesSelected(chrgsSelected);

    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return forward
     * @throws Exception
     */
    public ActionForward go(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            throws Exception
    {
        ActionForward forward = aMapping.findForward(UIConstants.GO);

        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
      
        form.refreshSchool();

        return forward;
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return forward
     * @throws Exception
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
            throws Exception
    {
        ActionForward forward = aMapping.findForward(UIConstants.VIEW_DETAIL);
        return forward;
    }    
    private Object getUserResponse(String logonId)
    {
        GetUserEvent requestEvent = (GetUserEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSER);
        requestEvent.setLogonId(logonId);
        CompositeResponse compositeResponse = MessageUtil.postRequest(requestEvent);
        return MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
    }

}
