package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import messaging.juvenilewarrant.UpdateJuvenileAssociateEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import ui.juvenilewarrant.form.JuvenileAssociateForm;

/**
 * @author ryoung
 *
 */
public class SubmitWarrantAssociateCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 41E6CCFB01E4
	 */
	public SubmitWarrantAssociateCreateUpdateAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", UIConstants.FINISH);
		buttonMap.put("button.update", UIConstants.UPDATE);
		buttonMap.put("button.back", UIConstants.BACK);
		return buttonMap;
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
		String forwardStr = UIConstants.CONFIRM_CREATE_SUCCESS;
		if (jaForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)){
			forwardStr = UIConstants.CONFIRM_UPDATE_SUCCESS;			
			UpdateJuvenileAssociateEvent updateEvent = jaForm.getUpdateAssociateCompositeEvent();
			CompositeResponse replyEvent = MessageUtil.postRequest(updateEvent);			
		} else {
			CreateJuvenileAssociateEvent createEvent = jaForm.getCreateAssociateCompositeEvent();
			MessageUtil.postRequest(createEvent);
		}
		
		return aMapping.findForward(forwardStr);
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
		UpdateJuvenileAssociateEvent updateEvent = jaForm.getUpdateAssociateCompositeEvent();
		
		CompositeResponse replyEvent = MessageUtil.postRequest(updateEvent);
				
		return aMapping.findForward(UIConstants.CONFIRM_UPDATE_SUCCESS);
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
		String action = jaForm.getAction();
		if (action.equalsIgnoreCase(UIConstants.CREATE)){
			Collection addrs = jaForm.getAssociateAddresses();
// add check and logic to handle addrs being null			
			if (addrs != null){
// add second address if only 1 exists in collection
				if (addrs.size() == 1){
					Collection blankAddress = new ArrayList();
					JuvenileAssociateAddressResponseEvent evt = new JuvenileAssociateAddressResponseEvent();
			// TODO Change to use (U)NPROCESSED constant
					evt.setAddressStatus("U");	
					blankAddress.add(evt);			
					Iterator addr = blankAddress.iterator();
					while (addr.hasNext())
					{
						JuvenileAssociateAddressResponseEvent newAddress = (JuvenileAssociateAddressResponseEvent) addr.next();
						addrs.add(newAddress);
					}
//					jaForm.setAssociateAddresses(addresses);
				}
			}	
		}
		return aMapping.findForward(UIConstants.BACK);
	}
}
