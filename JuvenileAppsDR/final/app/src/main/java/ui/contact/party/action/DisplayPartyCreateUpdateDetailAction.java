/*
 * Created on Oct 25, 2004
 *
 * 05/11/2006  Hien Rodriguez  This action only use to display View Party Details now. 
 *   It's called from PASO & AOOC used cases.
 */
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

import ui.contact.party.form.PartyForm;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayPartyCreateUpdateDetailAction extends Action
{
	/**
	 * @roseuid 416D252F017D
	 */
	public DisplayPartyCreateUpdateDetailAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4166D68700BD
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		PartyForm partyForm = (PartyForm) aForm;
		
		// fetch the party based on the given criteria
		GetPartyDataEvent request =
			(GetPartyDataEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYDATA);
		request.setOID(partyForm.getPartyOid());
		// do not get all the history data
		request.setThinResponse(true);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// check for errors
		MessageUtil.processReturnException(response);
		
		// clear any previous search results
		partyForm.clear();
		
		// process results from lookup
		Collection parties = MessageUtil.compositeToCollection(response, PartyResponseEvent.class);
		if (!parties.isEmpty())
		{
			PartyResponseEvent party = (PartyResponseEvent)parties.iterator().next();
			partyForm.setPartyData(party);
		}
		
		
		String action = partyForm.getAction();
		String secondaryAction = partyForm.getSecondaryAction();

		if (action.equals(UIConstants.VIEW))
		{
			forward = aMapping.findForward("viewSuccess");
		}
		else
		{
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}
}
