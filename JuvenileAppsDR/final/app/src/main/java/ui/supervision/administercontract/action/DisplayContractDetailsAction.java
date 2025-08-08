//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractCreateUpdateSummaryAction.java

package ui.supervision.administercontract.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.administercontract.form.ContractForm;

public class DisplayContractDetailsAction extends Action
{
   
   /**
    * @roseuid 451C4F0C00E4
    */
   public DisplayContractDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A6502A7
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
	   
      	Iterator iter = contractForm.getAvailableContracts().iterator();
	    while (iter.hasNext())
		{
			ContractResponseEvent responseEvent = (ContractResponseEvent) iter.next();
			if (responseEvent.getContractId().equals(contractForm.getContractId()))
			{
				contractForm.setContractNum(responseEvent.getNumber());
				contractForm.setContractName(responseEvent.getContractName());
				contractForm.setStartDate(DateUtil.dateToString(responseEvent.getStartDate(), UIConstants.DATE_FMT_1));
				contractForm.setEndDate(DateUtil.dateToString(responseEvent.getEndDate(), UIConstants.DATE_FMT_1));
				contractForm.setContractTypeId(responseEvent.getContractTypeId());
				contractForm.setGlAccountKey(responseEvent.getGlAccountKeyDesc());
				contractForm.setGlAccountKeyId(responseEvent.getGlAccountKeyId());
				contractForm.setProgramFundingDesc(responseEvent.getProgramFundingDescription());
				contractForm.setTracerNumberRangeFrom(responseEvent.getTracerNumberFrom());
				contractForm.setTracerNumberRangeTo(responseEvent.getTracerNumberTo());
				contractForm.setTotalValue(responseEvent.getTotalValue());
				contractForm.setRenewalNum(responseEvent.getRenewalNum());
				break;
			}
		}
	    return aMapping.findForward(UIConstants.VIEW_SUCCESS);
   }
}
