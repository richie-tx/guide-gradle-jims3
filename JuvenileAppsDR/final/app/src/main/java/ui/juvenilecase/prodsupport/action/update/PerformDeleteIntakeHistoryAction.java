package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.productionsupport.DeleteProductionSupportIntakeHistoryEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileSchoolHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportIntakeHistoryEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.referral.JJSSVIntakeHistory;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author Jims2
 */

public class PerformDeleteIntakeHistoryAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteIntakeHistoryAction"); 
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	    ProdSupportForm regform = (ProdSupportForm) aForm;
	    String forward = "success";
		
		String clrChk = aRequest.getParameter("clr");
		List<JJSSVIntakeHistory> intakeHistoryRecords = regform.getIntakeHistoryRecords();
		String intakeHistoryId	  = regform.getIntakeHistoryId();
		JJSSVIntakeHistory updatedIntakeHistory =  regform.getIntakeHistoryRecord();
		
		
//		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
//		    regform.setMsg("");
		    for (JJSSVIntakeHistory intakeHistory : intakeHistoryRecords){
			if (intakeHistory.getOID().equals(intakeHistoryId)){
			    regform.setIntakeHistoryRecord(intakeHistory);			  
			}
		    }		    
//		   
//		    return aMapping.findForward("error");
//		}
		
		DeleteProductionSupportIntakeHistoryEvent deleteIntakeHistoryEvent =
			(DeleteProductionSupportIntakeHistoryEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTINTAKEHISTORY);
		deleteIntakeHistoryEvent.setIntakeHistoryId(intakeHistoryId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteIntakeHistoryEvent);	
		return aMapping.findForward(forward);
		 
	    }
	
}
