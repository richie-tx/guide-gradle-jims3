package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileSchoolHistoryEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author Jims2
 */

public class PerformDeleteSchoolHistAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteSchoolHistAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();
	
		String schoolHistId = regform.getSchoolhistId();

		if (schoolHistId == null || schoolHistId.equals(" ")) {
			regform.setMsg("SchoolHistID was null.");
			return mapping.findForward("error");
		}
		
		/**Delete the JCSCHOOL record **/
		log.info("About to perform a SCHOOLHIST DELETE for SCHOOLHIST_ID=" + schoolHistId + " and LOGONID = " + logonid);
		DeleteProductionSupportJuvenileSchoolHistoryEvent deleteSchoolHistory =
			(DeleteProductionSupportJuvenileSchoolHistoryEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTJUVENILESCHOOLHISTORY);
		deleteSchoolHistory.setSchoolHistoryId(schoolHistId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteSchoolHistory);			
		
		/** Check if the record was correctly deleted **/
		ArrayList<JuvenileSchoolHistoryResponseEvent> schoolHistories = retrieveSchoolHistoryRecords(schoolHistId);
		if(schoolHistories.size() == 0 ){
			log.info("Sucessfully Performed a SCHOOLHIST DELETE for SCHOOLHIST_ID=" + schoolHistId + " and LOGONID = " + logonid);
			regform.setMsg("");
			return mapping.findForward("success");
		}else{
			log.info("ERROR: Could not delete SCHOOLHIST record for SCHOOLHIST_ID=" + schoolHistId + " and LOGONID = " + logonid);
			regform.setMsg("ERROR: Could not delete SCHOOLHIST record for SCHOOLHIST_ID= " + schoolHistId);
			return mapping.findForward("error");
		}

	}
	
	/**
	 * retrieve school history record based on school history id
	 * @return
	 */
	private ArrayList<JuvenileSchoolHistoryResponseEvent> retrieveSchoolHistoryRecords(String schoolHistId){
	
		//Search and populate the casefile records
		GetJuvenileSchoolByIDEvent getSchoolHistory = new GetJuvenileSchoolByIDEvent();
		getSchoolHistory.setSchoolId(schoolHistId);
		JuvenileSchoolHistoryResponseEvent resp = (JuvenileSchoolHistoryResponseEvent) MessageUtil.postRequest(getSchoolHistory, JuvenileSchoolHistoryResponseEvent.class);
		ArrayList<JuvenileSchoolHistoryResponseEvent> schoolHists = new ArrayList<JuvenileSchoolHistoryResponseEvent>();
		if(resp != null && resp.getSchoolId() != null && resp.getSchoolId() != ""){
			schoolHists.add(resp);
		}
		
		return schoolHists;
	}
	
}
