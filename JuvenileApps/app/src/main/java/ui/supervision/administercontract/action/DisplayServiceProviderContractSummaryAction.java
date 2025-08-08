//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractValueSummaryAction.java

package ui.supervision.administercontract.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractServiceResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.administercontract.form.ContractForm;

public class DisplayServiceProviderContractSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0E021D
    */
   public DisplayServiceProviderContractSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A660120
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	ContractForm contractForm = (ContractForm) aForm;
	   	String action = contractForm.getAction();
	   	String forward = UIConstants.SUCCESS;
	   	if(UIConstants.UPDATE_CONITNUE.equalsIgnoreCase(action)){
	   		double totalServiceProvidervalue = 0.0;
	   		String spValue = contractForm.getServiceProviderValue();
	   		if(spValue != null && !spValue.equals("")){
	   			totalServiceProvidervalue = Double.parseDouble(spValue);
	   		}
	   		Object[] obj = contractForm.getServiceProviderServices();
	   		for(int i=0; i<obj.length; i++){
	   			ContractServiceResponseEvent cResp = (ContractServiceResponseEvent) obj[i];
	   			totalServiceProvidervalue += Double.parseDouble(cResp.getSPValue());
	   		}
	   		if(totalServiceProvidervalue > Double.parseDouble(contractForm.getTotalValue())){
	   			ActionErrors errors = new ActionErrors();
	   			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.contract.reallocate.serviceprovidervalue"));
	   			saveErrors(aRequest, errors);
	   			forward = UIConstants.UPDATE_CONTACT_FAILURE;
	   		}else{
	   			forward = UIConstants.UPDATE_CONITNUE;
	   		}
	   	}
	    if(action.equals("")){
	    	contractForm.setAction("summary");
	    }
	   	return aMapping.findForward(forward);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A660120
    */
   public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
	   contractForm.setServiceProviderValue("");
	   return aMapping.findForward(UIConstants.RESET_SUCCESS);
   }

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.next","next");	
		keyMap.put("button.reset","reset");
		return keyMap;
	}
}