//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractSelectAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayContractSelectAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 451C4F0F0086
    */
   public DisplayContractSelectAction() 
   {
    
   }
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.createContract","create");
		keyMap.put("button.back","back");		
		return keyMap;
	}
	
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward create(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	    ContractForm contractForm = (ContractForm) aForm;
   	    contractForm.setContractNum("");
   	    contractForm.setContractName("");
   	    contractForm.setStartDate("");
   	    contractForm.setEndDate("");
   	    contractForm.setContractTypeId("");
   	    contractForm.setGlAccountKeyId("");
   	    contractForm.setProgramFundingDesc("");
   	    contractForm.setTracerNumberRangeFrom("");
   	    contractForm.setTracerNumberRangeTo("");
   	    contractForm.setTotalValue("");
   	    contractForm.setAction("create");
   	    return aMapping.findForward(UIConstants.CREATE_SUCCESS);
   }
   
	/**
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
   	    ContractForm contractForm = (ContractForm) aForm;
   	    Collection currentContracts = contractForm.getCurrentContracts();
   	    contractForm.clear();
	 	if(contractForm.getShowServiceProviderInfo().equals("Y")){
	 		contractForm.setCurrentContracts(currentContracts);
	 	}
   	    return aMapping.findForward(UIConstants.CANCEL);
   }   

   /**
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
	    return aMapping.findForward(UIConstants.BACK);
   }	   
	
}
