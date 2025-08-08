//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\party\\action\\DisplayPartyCreateUpdateAction.java

package ui.contact.party.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.FormCollectionsHelper;
import ui.contact.party.form.PartyForm;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayPartyCreateUpdateAction extends Action
{

	/**
	 * @roseuid 416D252C018D
	 */
	public DisplayPartyCreateUpdateAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4166D687009E
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		PartyForm partyForm = (PartyForm)aForm;
		
		String spn = partyForm.getSpn();
		partyForm.clear();
		
		FormCollectionsHelper helper = FormCollectionsHelper.getInstance();
		aRequest.getSession().setAttribute("commonForm", helper);

		String forwardName = null; 
		// check for create or update
		if (spn != null) // update
		{
			// Fetch the Party
			PartyResponseEvent party = fetchParty(spn);
			if (party == null)
			{			
				forwardName = UIConstants.FAILURE;
			}
			partyForm.setAction("update");
			forwardName = UIConstants.UPDATE_SUCCESS;
		}
		else
		{
			partyForm.setAction("create");
			forwardName = UIConstants.CREATE_SUCCESS;
		}
				
		return aMapping.findForward(forwardName);
	}
	
	/**
	 * @return PartyResponseEvent
	 */
	private PartyResponseEvent fetchParty(String spn)
	{		
		IDispatch dispatch =
			EventManager.getSharedInstance(EventManager.REQUEST);

		GetPartyDataEvent event =
			(GetPartyDataEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYDATA);
		event.setSpn(spn);
		event.setThinResponse(true);
		
		dispatch.postEvent(event);

		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();

		MessageUtil.processReturnException(replyEvent);
		
		Collection parties = MessageUtil.compositeToCollection(replyEvent, PartyResponseEvent.class);
		PartyResponseEvent party = null;
		if (!parties.isEmpty())
		{			
			party = (PartyResponseEvent)parties.iterator().next(); 
		}

		return party;
	}
	
	
	
}
