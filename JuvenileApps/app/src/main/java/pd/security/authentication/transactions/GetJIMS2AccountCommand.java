//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.authentication.GetJIMS2AccountEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountView;

public class GetJIMS2AccountCommand implements ICommand
{

    /**
     * @roseuid 4399BF8D02EE
     */
    public GetJIMS2AccountCommand()
    {

    }

    /**
     * @param event
     * @roseuid 439711A8008C
     */
    public void execute(IEvent event)
    {
	GetJIMS2AccountEvent getJIMS2AccountEvent = (GetJIMS2AccountEvent) event;

	if (getJIMS2AccountEvent != null && getJIMS2AccountEvent.getJIMS2LogonId() != null && !getJIMS2AccountEvent.getJIMS2LogonId().isEmpty())
	{
	    JIMS2Account jims2Account = JIMS2Account.findByLogonId(getJIMS2AccountEvent.getJIMS2LogonId()); // for service providers.
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    if (jims2Account != null)
	    {
		JIMS2AccountView jims2AcctView = null;
		Iterator<JIMS2AccountView> jims2AcctViewItr = JIMS2AccountView.findAll("OID", jims2Account.getOID());
		if (jims2AcctViewItr.hasNext())
		{
		    jims2AcctView = jims2AcctViewItr.next();
		    ResponseContextFactory respFac = new ResponseContextFactory();
		    ResponseCreator aCreator = null;
		    try
		    {
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
		    }
		    catch (Exception e)
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("Error encountered accessing your JIMS2 Account. Exception is " + e);
			dispatch.postEvent(error);
			return;
		    }
		    JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) aCreator.create(jims2AcctView);
		    dispatch.postEvent(resp);
		}
	    }
	}
	else
	{
	    JIMS2AccountView jims2AcctView = null;
	    Iterator<JIMS2AccountView> jims2AcctViewItr = JIMS2AccountView.findAll("logonId", getJIMS2AccountEvent.getLogonId());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    if (jims2AcctViewItr.hasNext())
	    {
		jims2AcctView = jims2AcctViewItr.next();
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator = null;
		try
		{
		    aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
		}
		catch (Exception e)
		{
		    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		    error.setMessage("Error encountered accessing your JIMS2 Account. Exception is " + e);
		    dispatch.postEvent(error);
		    return;
		}
		JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) aCreator.create(jims2AcctView);
		dispatch.postEvent(resp);
	    }
	}
    }

    /**
     * @param event
     * @roseuid 439711A8008E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 439711A8009B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4399BF8D02FE
     */
    public void update(Object updateObject)
    {

    }
}