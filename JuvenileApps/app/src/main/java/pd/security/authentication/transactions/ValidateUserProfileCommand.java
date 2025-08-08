// Source file://u.s 79250
// C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\ValidateUserCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.authentication.ValidateUserProfileEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDContactConstants;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountView;
import pd.security.authentication.PDSecurityAuthenticationHelper;

public class ValidateUserProfileCommand implements ICommand
{

    /**
     * @roseuid 4399C1230009
     */
    public ValidateUserProfileCommand()
    {

    }

    /**
     * @param event
     * @roseuid 439711A901B9
     */
    public void execute(IEvent event)
    {
	ValidateUserProfileEvent validateEvent = (ValidateUserProfileEvent) event;

	String userId = validateEvent.getLogonId();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	UserProfile vUser = UserProfile.find(userId);
	if (vUser != null)
	{
	    String deptId = vUser.getDepartmentId();
	    String userStatus = vUser.getUserStatus();
	    String genericInd = vUser.getGenericUserTypeId();
	    if (userStatus.equals("I"))
	    {
		LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		String message = PDSecurityAuthenticationHelper.getInactiveUserMessage(vUser);
		error.setMessage(message);
		dispatch.postEvent(error);
		return;
	    }
	    Department dept = Department.find(deptId);
	    if (dept == null)
	    {
		LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		error.setMessage("Department not found.");
		dispatch.postEvent(error);
		return;
	    }
	    String status = dept.getStatusId();
	    if (!status.equals("A"))
	    {
		LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		String message = PDSecurityAuthenticationHelper.getInactiveDepartmentMessage(dept);
		error.setMessage(message);
		dispatch.postEvent(error);
		return;
	    }
	    if (genericInd.equals("N"))
	    {
		Iterator<JIMS2AccountView> jims2AccountTypes = JIMS2AccountView.findAll("logonId", userId);
		if (!jims2AccountTypes.hasNext())
		{
		    /* JIMS2AccountView jims2AccountType = (JIMS2AccountView) jims2AccountTypes.next();
		     if (jims2AccountType != null)
		     {
		    String acctId = jims2AccountType.getOID();
		    JIMS2Account j2acct = JIMS2Account.find(acctId);
		    if (j2acct != null)
		    {
		    //U.S #79250
		        String accountStatus = j2acct.getStatus();
		        if (accountStatus.equals(PDContactConstants.ACTIVE))
		        {*/
		    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		    String message = "User Account does not exist for this logon Id "+ userId;
		    error.setMessage(message);
		    dispatch.postEvent(error);
		    return;
		 }
	    }
	}

    }

    /**
     * @param event
     * @roseuid 439711A901C4
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 439711A901C6
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4399C1230018
     */
    public void update(Object updateObject)
    {

    }
}
