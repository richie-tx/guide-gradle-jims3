//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\registergenericaccount\\HandleJIMS2AccountSelectionAction.java

package ui.security.authentication.jims2Account;

import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import ui.security.authentication.form.LoginForm;
import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UIJIMS2AccountHelper
{

    /**
     * @roseuid 456220750072
     */
    public UIJIMS2AccountHelper()
    {

    }

    /**
     * @param jaForm
     * @param resp
     */
    public static void setNonGenericUser(JIMS2AccountForm jaForm, JIMS2AccountResponseEvent resp)
    {
	jaForm.setJimsLogonId(resp.getJimsLogonId());
	jaForm.setFirstName(resp.getFirstName());
	jaForm.setMiddleName(resp.getMiddleName());
	jaForm.setLastName(resp.getLastName());
	jaForm.setDepartmentId(resp.getDepartmentId());
	jaForm.setDepartmentName(resp.getDepartmentName());
	jaForm.setJims2LogonId(resp.getJIMS2LogonId()); //79250
	jaForm.setUserType("N");
	jaForm.setAccountStatus(resp.getStatus());
	if (resp.getActivateDate() != null)
	{
	    jaForm.setActivateDate(DateUtil.dateToString(resp.getActivateDate(), UIConstants.DATE_FMT_1));
	}
	jaForm.setActivatedBy(resp.getActivatedBy());
	if (resp.getInactivateDate() != null)
	{
	    jaForm.setInactivateDate(DateUtil.dateToString(resp.getInactivateDate(), UIConstants.DATE_FMT_1));
	}
	jaForm.setInactivatedBy(resp.getInactivatedBy());
	jaForm.setJims2AccountId(resp.getJIMS2AccountId());
    }

    /**
     * @param jaForm
     * @param resp
     */
    public static void setGenericUser(JIMS2AccountForm jaForm, JIMS2AccountResponseEvent resp)
    {
	String userType = resp.getJIMS2AccountTypeId();
	setNonGenericUser(jaForm, resp);
	jaForm.setBadgeNumber(resp.getBadgeNum());
	jaForm.setOtherIdNumber(resp.getOtherIdNum());
	jaForm.setEmployeeId(resp.getEmployeeId());
	jaForm.setServiceProviderName(resp.getServiceProviderName());
	jaForm.setPassword(resp.getJimsPassword());
	jaForm.setJims2Password(resp.getJIMS2Password());
	jaForm.setPasswordQuestion(resp.getForgottenPasswdPhrase());
	jaForm.setPasswordQuestionId(resp.getForgottenPasswdPhraseId());
	jaForm.setPasswordAnswer(resp.getPasswordAnswer());
	jaForm.setUserType(userType);
    }

    /**
     * overloaded version for the non service providers
     * @param request
     * @param jaForm
     * @return
     */
    public static CreateJIMS2AccountEvent prepareCreateRequestEvent(CreateJIMS2AccountEvent request, LoginForm jaForm)
    {
	request.setFirstName(jaForm.getFirstName());
	request.setLastName(jaForm.getLastName());
	request.setMiddleName(jaForm.getMiddleName());
	//request.setJIMS2LogonId(jaForm.getJI); //#79250 user-story
	request.setDepartmentId(jaForm.getDepartmentId());
	//request.setJIMS2Password(jaForm.getPassword());
	//request.setPasswordQuestionId(jaForm.getPasswordQuestionId());
	request.setUserID(jaForm.getLogonId());
	request.setStatus(UIConstants.ACTIVE_STATUS_ID);
	request.setJIMS2AccountTypeId(jaForm.getAccountTypeId()); //87188
	request.setLogonId(jaForm.getJimsUserId());
	//request.setAnswer(jaForm.getPasswordAnswer());
	request.setJIMS2AccountTypeOID(null);
	return request;
    }

    /**
     * Over loaded version for the generic accounts
     * 
     * @param request
     * @param jaForm
     * @return
     */
    public static CreateJIMS2AccountEvent prepareCreateRequestEvent(CreateJIMS2AccountEvent request, JIMS2AccountForm jaForm)
    {
	request.setFirstName(jaForm.getFirstName());
	request.setLastName(jaForm.getLastName());
	request.setMiddleName(jaForm.getMiddleName());
	request.setJIMS2LogonId(jaForm.getJims2LogonId());
	request.setDepartmentId(jaForm.getDepartmentId());
	request.setJIMS2Password(jaForm.getPassword());
	request.setPasswordQuestionId(jaForm.getPasswordQuestionId());
	request.setUserID(jaForm.getJimsLogonId());
	request.setStatus(UIConstants.ACTIVE_STATUS_ID);
	request.setJIMS2AccountTypeId(jaForm.getUserType());
	request.setLogonId(jaForm.getJimsLogonId());
	request.setAnswer(jaForm.getPasswordAnswer());
	request.setJIMS2AccountTypeOID(jaForm.getUserAccountId());
	return request;
    }

}
