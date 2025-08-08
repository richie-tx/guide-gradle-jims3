package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.officer.ValidateOfficerEvent;
import messaging.officer.ValidateOfficerOrUserEvent;
import messaging.user.GetUserProfileEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
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
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;

/**
 * @author ldeen
 *  
 */
public class DisplayReleaseToJPSummaryAction extends JIMSBaseAction
{

    /**
     * @roseuid 41FFE150031C
     */
    public DisplayReleaseToJPSummaryAction()
    {

    }

    /**
     * @param buttonMap
     */
    public void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.next", UIConstants.NEXT);
        buttonMap.put("button.cancel", UIConstants.CANCEL);
        buttonMap.put("button.findDepartment", "findDepartment");
        buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
        buttonMap.put("button.select", "select");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41FFC64F0011
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

        String forwardStr = null;
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        //this.setForm(jwForm);
        if (UIConstants.SUMMARY.equals(jwForm.getWarrantTypeUI()))
        {
            jwForm.setAction(UIConstants.CONFIRM);
        }
        else
        {
            jwForm.initTransferCustodyDate();
            jwForm.setAction(UIConstants.SUMMARY);
        }
        CompositeResponse compositeResponse = null;

        if (jwForm.getSearch().equalsIgnoreCase("userSearch"))
        {
            String userId = jwForm.getTransferOfficerLogonId();
            jwForm.setUserId(userId);
            compositeResponse = this.validateOfficerByUserId(jwForm);
        }
        else
        {
            compositeResponse = this.validateOfficer(jwForm);
        }
        
        OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(
                compositeResponse, OfficerProfileResponseEvent.class);

        UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse,
                UserResponseEvent.class);

        InvalidUserResponseEvent invalidUser = (InvalidUserResponseEvent) MessageUtil.filterComposite(
                compositeResponse, InvalidUserResponseEvent.class);
              
        if (userResponse != null)
        {
            // execute if a valid user does not have an officer record
            if (!userResponse.getGenericUserType().equalsIgnoreCase("N"))
            {
                //execute if invalid Generic User.
                forwardStr = UIConstants.INVALID_LAW_ENFORCEMENT;
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.genericUserId"));
                saveErrors(aRequest, errors);
            }
            else
            {
            jwForm.clearOfficerInfo();
            this.copyUserDataToForm(userResponse, jwForm);
            forwardStr = UIConstants.LAW_ENFORCEMENT;
            }
        }
        else if (officerResponse != null)
        {
            // execute if user has a valid officer record
            String officerStatusCode = jwForm.getOfficerOID();
            jwForm.setOfficerProperties(officerResponse);
            jwForm.setExecutorProperties(officerResponse);
            String transferOfficerIdType = jwForm.getTransferOfficerIdType();
            if (transferOfficerIdType.equals("B"))
            {
                jwForm.setTransferOfficerIdType("BADGE NUMBER");
            }
            else if (transferOfficerIdType.equals("O"))
            {
                jwForm.setTransferOfficerIdType("OTHER ID");
            }
            forwardStr = UIConstants.SUCCESS;
        }
        else if (invalidUser != null)
        {
            // execute if user and officer are not valid
            forwardStr = UIConstants.INVALID_LAW_ENFORCEMENT;
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userLoginId.found", jwForm.getUserId()));
            saveErrors(aRequest, errors);
        }
        else
        {
            // execute if user and officer are not valid
            jwForm.clearOfficerInfo();
            String transferOfficerId = jwForm.getTransferOfficerId();
            String transferOfficerIdType = jwForm.getTransferOfficerIdType();
            jwForm.setOfficerId(transferOfficerId);
            jwForm.setOfficerIdType(transferOfficerIdType);
            jwForm.setOfficerAgencyId( jwForm.getTransferOfficerDepartmentId() );

            compositeResponse = this.validateOfficer(jwForm);
            forwardStr = UIConstants.LAW_ENFORCEMENT;;
        }
        jwForm.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());
        jwForm.setBackForward(forwardStr);
        jwForm.setWarrantTypeUI( UIConstants.RELEASE_TOJP_SUCCESS );
        return aMapping.findForward(forwardStr);
    }

    public ActionForward findDepartment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        
        jwForm.setTransferOfficerId("");
        jwForm.setTransferOfficerIdType("");
        jwForm.setTransferOfficerDepartmentId("");
        jwForm.setTransferOfficerDepartmentName("");
        jwForm.setUserId("");
        String forwardStr = UIConstants.FIND_SUCCESS;
        String userId = jwForm.getTransferOfficerLogonId();
        UserResponseEvent resp = getUserResponse(userId);
        ActionErrors errors = new ActionErrors();
        if (resp != null)
        {
            CompositeResponse compResp = this.validateOfficer(jwForm);
            OfficerProfileResponseEvent ore = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compResp,
                    OfficerProfileResponseEvent.class);
            if (ore != null)
            {
	            getForward(compResp, jwForm);
	            jwForm.setAction("userDeptTransfer");
	            String transferOfficerDepartmentId = jwForm.getTransferOfficerDepartmentId();
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
                forwardStr = "failure";
            }
            else
            {
                jwForm.setTransferOfficerDepartmentName("");
                jwForm.setTransferOfficerDepartmentId("");
                jwForm.setSearch("userSearch");
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officerProfile.notfound"));
                saveErrors(aRequest, errors);
                forwardStr = "failure";
            }
        }
        else
        {
            CompositeResponse compositeResponse = this.validateOfficer(jwForm);
            InvalidUserResponseEvent invalidUser = (InvalidUserResponseEvent) MessageUtil.filterComposite(compositeResponse,
                    InvalidUserResponseEvent.class);
            jwForm.setAgencyId("");
            jwForm.setTransferOfficerDepartmentName("");
            jwForm.setAction("");
            jwForm.setSearch("userSearch");
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.department.found", "Duplicate name"));
            saveErrors(aRequest, errors);
            forwardStr = "failure";
        }
        return aMapping.findForward(forwardStr);
    }

    public ActionForward validateDepartmentCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.setUserId("");
        DepartmentResponseEvent respEvent = UIJuvenileWarrantHelper.fetchDepartment(jwForm.getTransferOfficerDepartmentId());
        if (respEvent == null)
        {
            this.sendToErrorPage(aRequest, "error.no.department.found");
            jwForm.setTransferOfficerDepartmentName("");
            jwForm.setSearch("officerSearch");
            jwForm.setAction("officerDeptTransfer");
            jwForm.setTransferOfficerDepartmentId("");
        }
        else
        {
            jwForm.setTransferOfficerDepartmentName(respEvent.getDepartmentName());
            jwForm.setSearch("officerSearch");
            jwForm.setAction("officerDeptTransfer");
        }
        return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);

    }

    public ActionForward select(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        StringTokenizer stok = new StringTokenizer(jwForm.getSelectedValue(), "+");
        int counter = 0;
        String deptId = "";
        String deptName = "";
        while (stok.hasMoreTokens())
        {
            String str = stok.nextToken();
            if (counter == 0)
                deptId = str;
            else if (counter == 1)
                deptName = str;
            counter++;
        }

        jwForm.setSearch("officerSearch");
        jwForm.setAction("officerDepartment");
        jwForm.setDepartments(new ArrayList());
        String warrantTypeUI = jwForm.getWarrantTypeUI();
        String forwardStr = null;
        if (warrantTypeUI.equals("arr"))
        {
            jwForm.setOfficerAgencyId(deptId);
            jwForm.setOfficerAgencyName(deptName);
            forwardStr = UIConstants.VALIDATE_JOT_DEPARTMENT_SUCCESS;
        }
        else if (warrantTypeUI.equals("dta"))
        {
            jwForm.setOfficerAgencyId(deptId);
            jwForm.setOfficerAgencyName(deptName);
            forwardStr = UIConstants.VALIDATE_JOT_DEPARTMENT_SUCCESS;
        }
        else if (warrantTypeUI.equals("pc"))
        {
            jwForm.setOfficerAgencyId(deptId);
            jwForm.setOfficerAgencyName(deptName);
            forwardStr = UIConstants.VALIDATE_JOT_DEPARTMENT_SUCCESS;
        }
        else if (warrantTypeUI.equals("warrantService"))
        {
            jwForm.setOfficerAgencyId(deptId);
            jwForm.setOfficerAgencyName(deptName);
            forwardStr = UIConstants.VALIDATE_SERVICE_DEPARTMENT_SUCCESS;
        }
        else
        {    
            jwForm.setTransferOfficerDepartmentId(deptId);
	        jwForm.setTransferOfficerDepartmentName(deptName);
	        forwardStr = UIConstants.VALIDATE_DEPARTMENT_SUCCESS;
    	}
        return aMapping.findForward(forwardStr);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        jwForm.setSearch("officerSearch");
        jwForm.setDepartments(new ArrayList());
        String warrantTypeUI = jwForm.getWarrantTypeUI();
        String forwardStr = null;
        if (warrantTypeUI.equals("arr"))
        {
            jwForm.setOfficerAgencyName("");
            forwardStr = UIConstants.CANCEL_JOT;
        }
        else if (warrantTypeUI.equals("dta"))
        {
            jwForm.setOfficerAgencyName("");
            forwardStr = UIConstants.CANCEL_JOT;
        }
        else if (warrantTypeUI.equals("pc"))
        {
            jwForm.setOfficerAgencyName("");
            forwardStr = UIConstants.CANCEL_JOT;
        }
        else if (warrantTypeUI.equals("warrantService"))
        {
            jwForm.setOfficerAgencyName("");
            forwardStr = UIConstants.CANCEL_SERVICE;
        }
        else
        {
            jwForm.setTransferOfficerDepartmentName("");
            forwardStr = UIConstants.CANCEL;
        }
        return aMapping.findForward(forwardStr);
    }

    /**
     * @param compositeResponse
     * @param aForm
     * @return
     */
    private String getForward(CompositeResponse response, JuvenileWarrantForm aForm)
    {
        OfficerProfileResponseEvent officerData = (OfficerProfileResponseEvent) MessageUtil.filterComposite(response,
                OfficerProfileResponseEvent.class);
        String forwardStr = null;

        if (officerData == null)
        {
            UserResponseEvent userData = (UserResponseEvent) MessageUtil.filterComposite(response, UserResponseEvent.class);
            forwardStr = "lawEnforcementInfo";
            aForm.setWarrantTypeUI(UIConstants.RELEASE_TOJP_SUCCESS);
            if (userData != null)
            {
                aForm.clearOfficerInfo();
                this.copyUserDataToForm(userData, aForm);
            }
            else
            {
                // Defect #29636 Added to erase details forwarded to create
                // page.
                aForm.clearOfficerInfo();
                aForm.setOfficerAgencyId(aForm.getTransferOfficerDepartmentId());
                aForm.setOfficerAgencyName(aForm.getTransferOfficerDepartmentName());
                if (aForm.getTransferOfficerIdType().equals(PDOfficerProfileConstants.BADGE_NUM_ID))
                {
                    aForm.setOfficerBadgeNumber(aForm.getTransferOfficerId());
                }
                else
                {
                    aForm.setOfficerOtherIdNumber(aForm.getTransferOfficerId());
                }
            }
        }
        else
        {
            forwardStr = UIConstants.SUCCESS;
            this.setOfficerProperties(officerData, aForm);
        }

        return forwardStr;
    }

    /**
     * @param officerData
     * @param aForm
     */
    private void setOfficerProperties(OfficerProfileResponseEvent officerData, JuvenileWarrantForm aForm)
    {
        aForm.setTransferOfficerDepartmentId(officerData.getDepartmentId());
        aForm.setTransferOfficerDepartmentName(officerData.getDepartmentName());
        aForm.setOfficerOID(officerData.getOfficerProfileId());
        String badgeNo = officerData.getBadgeNum();
        if (badgeNo != null && !badgeNo.equals(""))
        {
            aForm.setTransferOfficerIdType(PDOfficerProfileConstants.BADGE_NUM);
            aForm.setTransferOfficerId(badgeNo);
        }
        else
        {
            aForm.setTransferOfficerIdType(PDOfficerProfileConstants.ID_NUM);
            aForm.setTransferOfficerId(officerData.getOtherIdNum());
        }

        aForm.setOfficerName(new Name(officerData.getFirstName(), "", officerData.getLastName()));
        aForm.setWorkPhone(new PhoneNumber(officerData.getWorkPhone()));
        aForm.setCellPhone(new PhoneNumber(officerData.getCellPhone()));
        aForm.setPager(new PhoneNumber(officerData.getPager()));
        aForm.setEmail(officerData.getEmail());

    }

    /**
     * @param userData
     * @param theForm
     */
    private void copyUserDataToForm(UserResponseEvent userData, JuvenileWarrantForm theForm)
    {
        theForm.setOfficerAgencyId(userData.getDepartmentId());
        theForm.setOfficerAgencyName(userData.getDepartmentName());
        theForm.setAgencyId(userData.getAgencyId());
        theForm.setOfficerName(new Name(userData.getFirstName(), "", userData.getLastName()));
        theForm.setWorkPhone(new PhoneNumber(userData.getPhoneNum()));
        theForm.setEmail(userData.getEmail());
        theForm.setOfficerLogonId(userData.getLogonId());
    }

    /**
     * 
     * @param aForm
     * @return
     */
    private CompositeResponse validateOfficer(JuvenileWarrantForm aForm)
    {

        ValidateOfficerOrUserEvent validateOfficerEvent = (ValidateOfficerOrUserEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERORUSER);

        validateOfficerEvent.setLogonId(aForm.getTransferOfficerLogonId());
        validateOfficerEvent.setDepartmentId(aForm.getTransferOfficerDepartmentId());
        validateOfficerEvent.setOfficerId(aForm.getTransferOfficerId());
        validateOfficerEvent.setOfficerIdType(aForm.getTransferOfficerIdType());

        String officerId = aForm.getOfficerId();

        if (aForm.getTransferOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.BADGE_NUM_ID))
        {
            aForm.setOfficerBadgeNumber(officerId);
        }
        if (aForm.getTransferOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.OTHER_NUM_ID))
        {
            aForm.setOfficerOtherIdNumber(officerId);
        }

        return MessageUtil.postRequest(validateOfficerEvent);
    }
    
    private CompositeResponse validateOfficerByUserId(JuvenileWarrantForm aForm)
    {
        ValidateOfficerEvent officerEvent = (ValidateOfficerEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICER);

        officerEvent.setLogonId(aForm.getUserId());
        officerEvent.setValidateByUserId(true);

        return MessageUtil.postRequest(officerEvent);
    }

    private UserResponseEvent getUserResponse(String logonId)
    {
        GetUserProfileEvent userProfile = (GetUserProfileEvent) EventFactory
                .getInstance(UserControllerServiceNames.GETUSERPROFILE);
        userProfile.setLogonId(logonId);
        CompositeResponse response = MessageUtil.postRequest(userProfile);
        return (UserResponseEvent) MessageUtil.filterComposite(response, UserResponseEvent.class);
    }

}
