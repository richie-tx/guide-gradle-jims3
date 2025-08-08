//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\HandleWarrantAssociateAddressesAction.java

package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileAssociateForm;
import ui.juvenilewarrant.helper.JuvenileAssociateAddressRequestAssembler;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleWarrantAssociateAddressesAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4216080E03B9
	 */
	public HandleWarrantAssociateAddressesAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", UIConstants.NEXT);
		buttonMap.put("button.update", UIConstants.UPDATE);
		buttonMap.put("button.add", UIConstants.ADD);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
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
	public ActionForward add(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		/** default value to failure in case no warrantNum present */
		String success = UIConstants.ADD_SUCCESS;
		JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
		// get the address in newAddress and add it to the addresses collection
		JuvenileAssociateAddressResponseEvent newAddr = jaForm.getNewAddress();
		if (!newAddr.isBlank())
		{
			jaForm.populateAddressCodeDescriptionsFromIds(newAddr);
			jaForm.insertAssociateAddress(newAddr);
			// clear the values from new address
			jaForm.setNewAddress(null);
		}
		return aMapping.findForward(success);
	}

	/**
		 * 
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{		
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;

		JuvenileAssociateAddressRequestAssembler assembler =
			new JuvenileAssociateAddressRequestAssembler(
				aRequest,
				"associateAddresses",
				jaForm.getAssociateAddresses(),
				false);
		assembler.assemble();
		return aMapping.findForward(UIConstants.CONTINUE_SUCCESS);
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
		//ActionForward forward = new ActionForward();

		/** default value to failure in case no warrantNum present */
		String success = UIConstants.FAILURE;
		//JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;

		success = UIConstants.CONTINUE_SUCCESS;
		return aMapping.findForward(success);
	}
}
