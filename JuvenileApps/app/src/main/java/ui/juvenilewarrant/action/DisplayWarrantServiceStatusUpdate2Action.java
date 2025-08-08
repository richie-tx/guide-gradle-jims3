package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
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

import pd.contact.officer.OfficerProfile;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 *  
 */
public class DisplayWarrantServiceStatusUpdate2Action extends JIMSBaseAction
{

    /**
     * @roseuid 41FFDCD302BF
     */
    public DisplayWarrantServiceStatusUpdate2Action()
    {

    }

    public void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.next", UIConstants.NEXT);
        buttonMap.put("button.findDepartment", "findDepartment");
        buttonMap.put("button.validateDepartmentCode", "validateDepartmentCode");
        buttonMap.put("button.link","link");
        buttonMap.put("button.reset", "reset");	
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41FFC5E60198
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        String forwardStr = null;

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;


        /*
         * We need to check for the officer to be present based on the userID or
         * officer ID and agency entered on the create page. If an officer is
         * found, get the additional officer fields and display the summary
         * page, ELSE we need to display the create party pages and display the
         * warrant summary
         */

        CompositeResponse compositeResponse = null;

        if (jwForm.getSearch().equalsIgnoreCase("userSearch"))
        {
            compositeResponse = this.validateOfficerByUserId(jwForm);
        }
        else
        {
            //Verify dept before proceeding
            DepartmentResponseEvent respEvent = UIJuvenileWarrantHelper.fetchDepartment(jwForm.getOfficerAgencyId());
            if (respEvent == null)
            {
                jwForm.setOfficerAgencyName("");
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.department.found"));
                saveErrors(aRequest, errors);
                return aMapping.findForward("failure");
            }
            compositeResponse = this.validateOfficerByOfficerId(jwForm);
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
            jwForm.setOfficerProperties(officerResponse);
            jwForm.setExecutorProperties(officerResponse);
            
            List addressList = UIJuvenileWarrantHelper.getFamilyMemberAddresses(jwForm.getJuvenileNum()); 
            jwForm.setAssociateServiceAddresses(addressList);
            
            List<JuvenileAssociateBean> familyMemberList = new ArrayList();
            
            List familyAssociates = jwForm.getAssociates();
            
            //re-initialize the associates list
            jwForm.setAssociates(new ArrayList());
            
            Iterator iter = familyAssociates.iterator();

          	while(iter.hasNext()) {
          	    
          	    JuvenileAssociateBean famAssociate = (JuvenileAssociateBean) iter.next();
          	    
          	    List memberAddresses = UIJuvenileWarrantHelper.getFamilyMemberAddresses(famAssociate.getAssociateNum());  
          	    memberAddresses = UIJuvenileHelper.sortMemberAddressList(memberAddresses);
          	    
          	    MemberAddress associateAddress = new MemberAddress();
          	    
          	    if(memberAddresses.size() > 0){
          		
          		 associateAddress = (MemberAddress) memberAddresses.get(0); //get most recent address - sorted from top to bottom
               	    
                   	 List<MemberAddress> memberAddressesFiltered = new ArrayList<MemberAddress>();
                   	    
                   	 memberAddressesFiltered.add(associateAddress);
                   	  
                   	 famAssociate.setAssociateAddresses(memberAddressesFiltered);
          	    }
          	   
          	    familyMemberList.add(famAssociate);
    
          	}          		
            
            jwForm.setAssociates(familyMemberList);                  
            
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
            // execute if user or officer will be created
            if (jwForm.getOfficerAgencyId() == null || jwForm.getOfficerAgencyId().equals(""))
            {
                jwForm.setOfficerAgencyId(jwForm.getAgencyId());
            }
            forwardStr = UIConstants.LAW_ENFORCEMENT;
        }
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
                jwForm.setOfficerAgencyName("");
                jwForm.setAction("");
                jwForm.setSearch("userSearch");
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.genericUserId"));
                saveErrors(aRequest, errors);
                returnString = "failure";
            }
            else
            {
                jwForm.setSearch("userSearch");
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.officerProfile.notfound"));
                saveErrors(aRequest, errors);
                jwForm.setAgencyId(resp.getDepartmentId());
                jwForm.setOfficerAgencyId(resp.getDepartmentId());
                jwForm.setOfficerAgencyName(resp.getDepartmentName());
                //jwForm.setOfficerAgencyName("not found");
                returnString = "failure";
            }
        }
        else
        {
            jwForm.setAgencyId("");
            jwForm.setOfficerAgencyName("");
            jwForm.setAction("");
            jwForm.setSearch("userSearch");
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.department.found"));
            saveErrors(aRequest, errors);
            returnString = "failure";
        }
        return aMapping.findForward(returnString);
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

    public ActionForward validateDepartmentCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.setValidDeptCode(false);
        DepartmentResponseEvent respEvent = UIJuvenileWarrantHelper.fetchDepartment(jwForm.getOfficerAgencyId());
        if (respEvent == null)
        {
            jwForm.setOfficerAgencyName("");
            this.sendToErrorPage(aRequest, "error.no.department.found");
        }
        else
        {
            jwForm.setOfficerAgencyName(respEvent.getDepartmentName());
            jwForm.setSearch("officerSearch");
            jwForm.setValidDeptCode(true);
        }
        return aMapping.findForward(UIConstants.VALIDATE_DEPARTMENT_SUCCESS);
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

    private UserResponseEvent getUserResponse(String logonId)
    {
        UserResponseEvent userResponse = null;
        if (logonId != null)
        {
            GetUserEvent requestEvent = (GetUserEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSER);
            requestEvent.setLogonId(logonId);

            CompositeResponse compositeResponse = MessageUtil.postRequest(requestEvent);

            // should only be 1 user because search based on unique logonId
            userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
        }

        return userResponse;
    }
    
   /**
    * 
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
   	public ActionForward reset(
   		ActionMapping aMapping,
   		ActionForm aForm,
   		HttpServletRequest aRequest,
   		HttpServletResponse aResponse)
   	{
   		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
   		
   		jwForm.setOfficerId("");
   		jwForm.setOfficerAgencyId("");
   		jwForm.setOfficerAgencyName("");
   		jwForm.setOfficerIdTypeId("");
   		
   		return aMapping.findForward(UIConstants.RESET_SUCCESS);
   	}	
}