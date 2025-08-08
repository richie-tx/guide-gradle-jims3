/*
 * Project: JIMS
 * Class:   pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetSocialElementCodeTableRecordsEvent;
import messaging.codetable.reply.SocialElementCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.person.SocialElementCode;

/**
 *  
 * @author  athorat
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetSocialElementCodeTableRecordsCommand implements mojo.km.context.ICommand
{

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event The event.
	 */
	public void execute(IEvent event) throws Exception
	{
		GetSocialElementCodeTableRecordsEvent requestEvent = (GetSocialElementCodeTableRecordsEvent) event;
		if (requestEvent.getElement() != null && requestEvent.getElement().length() > 0)
		{
			Iterator iter = SocialElementCode.findAll("element", requestEvent.getElement());
			this.sendSocialElementCodes(iter);
		}
	} //end of pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand.execute

	/**
	 *  
	 * @param iter The iter.
	 */
	public void sendSocialElementCodes(Iterator iter)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iter.hasNext())
		{
			SocialElementCode code = (SocialElementCode) iter.next();
			SocialElementCodeResponseEvent codeReply = new SocialElementCodeResponseEvent();
			codeReply.setTopic(PDCodeTableConstants.getSocialElementCodeTableTopic(code.getElement()));
			codeReply.setInactiveInd(code.getInactiveInd());
			codeReply.setElement(code.getElement());
			codeReply.setElementDescription(code.getElementDescription());
			codeReply.setReportGroup(code.getReportGroup());
			codeReply.setCode(code.getCode());
			codeReply.setSocialElementCodeId(code.getSocialElementCodeId());
			codeReply.setDescription(code.getCodeDescription());
			dispatch.postEvent(codeReply);
		}
	} //end of pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand.sendSocialElementCodes

	/**
	 *  
	 * @param event The event.
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	} //end of pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand.onRegister

	/**
	 *  
	 * @param event The event.
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	} //end of pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand.onUnregister

	/**
	 *  
	 * @param updateObject The update object.
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	} //end of pd.codetable.transactions.GetSocialElementCodeTableRecordsCommand.update

} // end GetSocialElementCodeTableRecordsCommand
