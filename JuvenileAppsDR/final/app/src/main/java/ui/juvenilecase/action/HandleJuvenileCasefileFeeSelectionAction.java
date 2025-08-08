//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefileFeeSelectionAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.party.GetPartyDataByBarNumEvent;
import messaging.referral.GetJuvenileCasefileFeePayorDetailsEvent;
import messaging.referral.GetJuvenileCasefileFeeReceiptEvent;
import messaging.referral.reply.JuvenileFeePayorResponseEvent;
import messaging.referral.reply.JuvenileFeeReceiptResponseEvent;
import messaging.referral.reply.JuvenileFeeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileReferralControllerServiceNames;
import naming.PartyControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.PhoneNumber;
import ui.juvenilecase.form.PetitionDetailsForm;


public class HandleJuvenileCasefileFeeSelectionAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view", "view");
		keyMap.put("button.viewAll", "viewAll");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
	}	
   
   /**
    * @roseuid 467FB5C80014
    */
   public HandleJuvenileCasefileFeeSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 467AD34C0177
    */
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	PetitionDetailsForm form = (PetitionDetailsForm) aForm;
   	String selectedNum=form.getSelectedTransactionNum();
   	String selectedCaseNum=form.getSelectedCaseNum();
   	String selectedCodeId = form.getSelectedCodeId();
   	String viewAll = form.getViewAllFeePayments();
   	Collection coll = form.getFeePayments();
   	Iterator iter = coll.iterator();
   	PetitionDetailsForm.FeeReceipt receipt;
   	Collection receipts = new ArrayList();
   	while(iter.hasNext())
   	{
   		JuvenileFeeResponseEvent feeResp = (JuvenileFeeResponseEvent)iter.next();
   		GetJuvenileCasefileFeeReceiptEvent evt = (	GetJuvenileCasefileFeeReceiptEvent)EventFactory.getInstance(
					JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEFEERECEIPT);
   		if(viewAll.equals("No"))
   		{
   			
	   		if(feeResp.getTransactionNum().equals(selectedNum) && feeResp.getCodeId().equals(selectedCodeId))
	   		{
	   			//Get the receipt and payor details   			
	   			evt.setPetitionNum(selectedCaseNum);
	   			evt.setTransactionNum(selectedNum);	   			
	   			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   	   		dispatch.postEvent(evt);
		   	   	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		   	   	Collection feeReceipts = MessageUtil.compositeToCollection(replyEvent, JuvenileFeeReceiptResponseEvent.class);
		   	   	Iterator receiptsIter = feeReceipts.iterator();
		   	   	while(receiptsIter.hasNext())
		   	   	{
		   	   		JuvenileFeeReceiptResponseEvent resp = (JuvenileFeeReceiptResponseEvent)receiptsIter.next();
		   	   		receipts.add(fillFeeReceiptDetails(resp, feeResp));
		   	   	}
	   		}
			
   		}
   		else
   		{
   			evt.setPetitionNum(form.getPetitionNum());   			
   			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   	   		dispatch.postEvent(evt);
	   	   	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
	   	   	Object obj = MessageUtil.filterComposite(replyEvent, JuvenileFeeReceiptResponseEvent.class);
	   	   	if(obj != null)
	   	   	{
	   	   		JuvenileFeeReceiptResponseEvent resp = (JuvenileFeeReceiptResponseEvent)obj;	   	   		
	   	   		receipts.add(fillFeeReceiptDetails(resp, feeResp));
	   	   	}
   			
   		}
   	}
   	form.setFeeReceipts(receipts);
   	ActionForward forward = aMapping.findForward("viewSuccess");
    return forward;
   }
   private PetitionDetailsForm.FeeReceipt fillFeeReceiptDetails(JuvenileFeeReceiptResponseEvent resp, JuvenileFeeResponseEvent feeResp)
   {
		PetitionDetailsForm.FeeReceipt receipt = new PetitionDetailsForm.FeeReceipt();
		receipt.setReceiptNum(resp.getReceiptNum());
  		receipt.setFeeType(feeResp.getFeeType());	   	   		
  		receipt.setPayorType(feeResp.getPayorType());
  		receipt.setReceivedDate(feeResp.getReceivedDate());
  		receipt.setPaidDate(resp.getPaidDate());
  		receipt.setTransactionNum(feeResp.getTransactionNum());
  		receipt.setPetitionNum(feeResp.getCaseNum());
  		receipt.setAmtPaid(feeResp.getTotalPaid());
  		receipt.setFeeStatus(feeResp.getStatus());
  		receipt.setCodeId(feeResp.getCodeId());
  		if(feeResp.getPayorId()!= null && feeResp.getPayorType()!=null )
  		{
	  		GetJuvenileCasefileFeePayorDetailsEvent evt = (GetJuvenileCasefileFeePayorDetailsEvent)EventFactory.getInstance(
					JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEFEEPAYORDETAILS);
	  		evt.setPayorId(feeResp.getPayorId());
	  		evt.setPayorType(feeResp.getPayorType());
	  		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		   	dispatch.postEvent(evt);
	   	   	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
	   		Object obj = MessageUtil.filterComposite(replyEvent, JuvenileFeePayorResponseEvent.class);
	   		if(obj != null)
	   		{
	   			JuvenileFeePayorResponseEvent payor = (JuvenileFeePayorResponseEvent)obj;
	   			if(feeResp.getPayorType().equalsIgnoreCase("Attorney"))
	   			{
	   				GetPartyDataByBarNumEvent thisEvent = (GetPartyDataByBarNumEvent)EventFactory.getInstance(PartyControllerServiceNames.GETPARTYDATABYBARNUM);
	   				thisEvent.setBarNum(feeResp.getPayorId());
	   				dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   				dispatch.postEvent(thisEvent);
	   				CompositeResponse response = (CompositeResponse) dispatch.getReply();
	   				Object partyObj = MessageUtil.filterComposite(response, PartyResponseEvent.class);
	   		   		if(partyObj != null)
	   		   		{
	   		   			PartyResponseEvent party = (PartyResponseEvent)partyObj; 
	   		   			receipt.setPayor(party.getName());
	   		   		}
	   			}
	   			else
	   				receipt.setPayor(payor.getPayor());
	   			receipt.setPayorAddress(payor.getPayorAddress());
	   			if(payor.getPhone()!=null)
	   				receipt.setPayorPhone(new PhoneNumber(payor.getPhone().toString()));
	   		}
  		}
  		return receipt;
   }
}
