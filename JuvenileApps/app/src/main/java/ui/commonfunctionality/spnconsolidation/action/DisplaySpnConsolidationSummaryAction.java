//Source file:
//C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnconsolidation\\action\\DisplaySpnConsolidationSummaryAction.java

package ui.commonfunctionality.spnconsolidation.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.party.GetPartyEvent;
import messaging.spnconsolidation.reply.SpnConsolidationErrorResponseEvent;
import messaging.supervisionorder.GetDefendantSupervisionOrdersEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.commonfunctionality.spnconsolidation.form.SpnConsolidationForm;
import ui.supervision.UICommonSupervisionHelper;

public class DisplaySpnConsolidationSummaryAction extends LookupDispatchAction {

	/**
	 * @roseuid 43F4FB9803BE
	 */
	public DisplaySpnConsolidationSummaryAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		SpnConsolidationForm spnConForm = (SpnConsolidationForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		GetPartyEvent requestEvent = (GetPartyEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTY);

		Collection spnColl = new ArrayList();
		
		String baseSpn = UICommonSupervisionHelper.padSpn( spnConForm.getBaseSpn() );
		String aliasSpn = UICommonSupervisionHelper.padSpn( spnConForm.getAliasSpn() );
		
		spnColl.add( baseSpn );
		requestEvent.setSpns( spnColl );

		dispatch.postEvent( requestEvent );
		
		

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		boolean invalid_alias_order_state = false;
		
		// error handling
	 	Collection errors = MessageUtil.compositeToCollection(response, SpnConsolidationErrorResponseEvent.class);
		if(errors != null && errors.size() > 0){
			StringBuffer sb = new StringBuffer();
			boolean isFirst = true;
			for(Iterator iter = errors.iterator(); iter.hasNext(); ){
				SpnConsolidationErrorResponseEvent spnErrorEvent = (SpnConsolidationErrorResponseEvent)iter.next();
				sb.append(spnErrorEvent.getErroneousSpn());
				if(isFirst){
					sb.append(". ");
					isFirst = false;
				}
			}
			invalid_alias_order_state = true;
			sendToErrorPage(aRequest,"error.noparty.found", "" + sb.toString());
			forward =  aMapping.findForward(UIConstants.FAILURE);
		}
		

		// Clears out Base Spn
		 spnColl = new ArrayList();
		
		// Now check error for alias
		spnColl.add( aliasSpn );
		requestEvent.setSpns(spnColl);

		dispatch.postEvent( requestEvent );
		
		

		CompositeResponse responseA = (CompositeResponse) dispatch.getReply();
		
		// error handling for Alias Spn
		// Should not find a party
	 	Collection errorsA = MessageUtil.compositeToCollection(responseA, SpnConsolidationErrorResponseEvent.class);
		if(errorsA.size() <= 0){
			StringBuffer sb = new StringBuffer();
				sb.append( aliasSpn );
					sb.append(". ");
			
			invalid_alias_order_state = true;
			sendToErrorPage(aRequest,"error.party.found", "" + sb.toString());
			forward =  aMapping.findForward(UIConstants.FAILURE);
		}
		
			//determine if there are any non active or inactive orders
		GetDefendantSupervisionOrdersEvent 
			get_alias_orders_event = new GetDefendantSupervisionOrdersEvent();
		
			get_alias_orders_event.setDefendantId( aliasSpn );
			
			//retrieve alias orders
		List < SupervisionOrderDetailResponseEvent > alias_order_responses = 
			MessageUtil.postRequestListFilter( get_alias_orders_event, 
				SupervisionOrderDetailResponseEvent.class );
		
			//check alias orders to see if any are in invalid state
		int num_orders = alias_order_responses.size();
		
		for ( int i=0; i<num_orders; i++ )
		{
			SupervisionOrderDetailResponseEvent this_order = 
					alias_order_responses.get( i );
			
				//determine if any if any of alias orders 
				//are in non active or inactive state
			if (( !"A".equals( this_order.getOrderStatusId()))
					&& ( !"I".equals( this_order.getOrderStatusId() )))
			{
				sendToErrorPage(aRequest,"error.invalidaliasorderstate.found", 
						spnConForm.getAliasSpn());
				forward =  aMapping.findForward(UIConstants.FAILURE);
				invalid_alias_order_state= true;
				break;
			}
		}
		
		if ( !invalid_alias_order_state )
		{
			
	   	// process party list
			Collection parties = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
			Iterator partyIter = parties.iterator();
	
			while (partyIter.hasNext()) {
				PartyListResponseEvent partyList = (PartyListResponseEvent) partyIter.next();
				String spn = partyList.getSpn();
				
				if (spn.equals( aliasSpn )) {
					spnConForm.setFromDateOfBirth(partyList.getDateOfBirth());
					spnConForm.setFromJailIndicator(partyList.getJailInd());
					spnConForm.setFromPartyName(partyList.getFirstName() + " " + partyList.getLastName());
					spnConForm.setFromRaceId(partyList.getRaceId());
					spnConForm.setFromSexId(partyList.getSexId());
					spnConForm.setFromJailInd(partyList.getJailInd()); 
				}
				else if (spn.equals( baseSpn )) {
					    // set the padded spns back to form
					    spnConForm.setBaseSpn( baseSpn );
					    spnConForm.setAliasSpn( aliasSpn );
					    
					    spnConForm.setToDateOfBirth(partyList.getDateOfBirth());
						spnConForm.setToJailIndicator(partyList.getJailInd());
						spnConForm.setToPartyName(partyList.getFirstName() + " " + partyList.getLastName());
						spnConForm.setToRaceId(partyList.getRaceId());
						spnConForm.setToSexId(partyList.getSexId());
						spnConForm.setToJailInd(partyList.getJailInd());
				}
	
			}
		}
				
		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL_MAIN_PAGE_HOME);
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg,param));
		saveErrors(aRequest, errors);
	}

}
