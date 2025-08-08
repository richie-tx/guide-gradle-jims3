//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\AddRemoveContractAssociationAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.messaging.exception.ReturnException;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleServiceProviderContractRemoveAction extends Action 
{
   
   /**
    * @roseuid 451C4D930219
    */
   public HandleServiceProviderContractRemoveAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A6600F0
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	    final String responseEventName = UIConstants.CONTRACT_RESPONSE_EVENT;
	    final String responseEventItemId = UIConstants.CONTRACT_RESPONSE_EVENT_ID;
   	    ContractForm contractForm = (ContractForm) aForm;
		String[] selectedContractsId = { contractForm.getContractId() };
	
		IShoppingCart sCart = new ShoppingCartImpl();
		try
		{
			Collection availableContracts =
				sCart.addToAvailableShoppingItemList(
					responseEventName,
					responseEventItemId,
					selectedContractsId,
					contractForm.getCurrentContracts(),
					contractForm.getAvailableContracts());
	
			contractForm.setAvailableContracts(new ArrayList(availableContracts));
	
			Collection currentContracts = 
				sCart.removeFromShoppingCart(
						responseEventName,
						responseEventItemId,
						selectedContractsId, 
						contractForm.getCurrentContracts(), 
						availableContracts);
			contractForm.setCurrentContracts(new ArrayList(currentContracts));
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		return aMapping.findForward(UIConstants.SUCCESS);
   }
}
