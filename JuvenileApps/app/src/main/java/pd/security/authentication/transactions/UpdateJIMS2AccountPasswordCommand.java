//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\UpdateJIMS2AccountPasswordCommand.java

package pd.security.authentication.transactions;

import pd.security.PDSecurityHelper;
import messaging.authentication.UpdateJIMS2AccountPasswordEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.caching.generic.CacheManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.Token;
import mojo.km.security.UpdatePasswordResponse;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;

public class UpdateJIMS2AccountPasswordCommand implements ICommand
{

    /**
     * @roseuid 4399CCC100A3
     */
    public UpdateJIMS2AccountPasswordCommand()
    {

    }

    /**
     * @param event
     * @roseuid 439842680237
     */
    public void execute(IEvent event)
    {
	UpdateJIMS2AccountPasswordEvent cjaEvent = (UpdateJIMS2AccountPasswordEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String email = cjaEvent.getJIMS2LogonId();
	String currentPassword = cjaEvent.getCurrentPassword();
	String newPassword = cjaEvent.getNewPassword();
	String userId = cjaEvent.getSecurityManagerUserId();
	if (email != null && !email.isEmpty() && newPassword != null && !newPassword.isEmpty())
	{
	    // call the web-service 86322
	    Token token = PDSecurityHelper.getToken();
	    if (token != null)
	    {
		SecurityManagerBaseResponse<UpdatePasswordResponse> securityManagerBaseResponse = SecurityManagerWebServiceHelper.updatePasswordServiceProvider(token, email, currentPassword, newPassword, userId);
		UpdatePasswordResponse passwordResponse = securityManagerBaseResponse.getData();
		if (passwordResponse != null)
		{
		    if (securityManagerBaseResponse.isIsSuccess() && securityManagerBaseResponse.isRecFound())
		    {
			if (passwordResponse.isAlreadyusedpassword())
			{
			    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			    error.setMessage("The New Password already exists. Please try an another one.");
			    dispatch.postEvent(error);
			    return;
			}
			else
			    if (passwordResponse.isInvaliduser())
			    {
				LoginErrorResponseEvent error = new LoginErrorResponseEvent();
				error.setMessage("The User(" + cjaEvent.getJIMS2LogonId() + ") Does Not Exist - Please contact Help desk");
				dispatch.postEvent(error);
				return;
			    }
		    }
		    else
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("Service Error, could not reset password. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator");
			dispatch.postEvent(error);
			return;
		    }
		}
	    }
	}
	//86322
	/*//See if there is an existing JIMS2Account with the same JIMS2LogonId
	j2Logon = JIMS2Account.findByLogonId(cjaEvent.getJIMS2LogonId());
	if (j2Logon == null)
	{
		LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		error.setMessage("The JIMS2 User ID (" + cjaEvent.getJIMS2LogonId() + ") Does Not Exist - Please try another");
		dispatch.postEvent(error);
	}
	else
	{
		//j2Logon.setPasswordQuestionId(cjaEvent.getPasswordQuestionId());
		j2Logon.setJIMS2LogonId(cjaEvent.getJIMS2LogonId());
		j2Logon.setPassword(cjaEvent.getJIMS2Password());
		//j2Logon.setAnswer(cjaEvent.getAnswer());
	}*/
    }

    /**
     * @param event
     * @roseuid 439842680239
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 43984268023B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4399CCC100B2
     */
    public void update(Object updateObject)
    {

    }
}