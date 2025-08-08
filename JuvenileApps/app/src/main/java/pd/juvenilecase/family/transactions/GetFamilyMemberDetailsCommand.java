/*
 * Created on Sep 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import mojo.km.util.MessageUtil;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 */
public class GetFamilyMemberDetailsCommand implements ICommand, ReadOnlyTransactional
{

    /**
	 * 
	 */
    public GetFamilyMemberDetailsCommand()
    {
	super();
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
	GetFamilyMemberDetailsEvent requestEvent = (GetFamilyMemberDetailsEvent) event;
	FamilyMemberDetailResponseEvent reply = null;

	if (requestEvent.getMemberNum() != null)
	{
	    FamilyMember familyMember = FamilyMember.find(requestEvent.getMemberNum());

	    if (familyMember != null)
	    {

		reply = JuvenileFamilyHelper.getFamilyMemberDetailResponseEvent(familyMember);

		JuvenileFamilyHelper.sendMaritalStatusList(requestEvent.getMemberNum());
		JuvenileFamilyHelper.sendAssociatedJuvList(requestEvent.getMemberNum());
		MessageUtil.postReply(reply);
	    }


	    familyMember = null;
	    reply = null;
	    requestEvent = null;
	}
	else
	{

	    ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
	    errRespEvt.setMessage("Error: Unable to find Suspiscious Family member");
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    dispatch.postEvent(errRespEvt);
	}
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
    }

}
