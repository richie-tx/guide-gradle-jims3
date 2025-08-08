package ui.juvenilecase.prodsupport.action.update;

import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.productionsupport.DeleteProductionSupportJpoAssignmentHistoryEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 * This action performs the delete of assignment history after a casefile has 
 * had its OFFICER_ID changed. 
 */
public class PerformModifyJpoAction extends Action {

	private Logger log = Logger.getLogger("PerformModifyJpoAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String logonid = SecurityUIHelper.getLogonId(); // For readability in the logs.
		String casefileId = regform.getCasefileId();
		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("PerformModifyJpoAction.java - Casefile ID was null.");
			return (mapping.findForward("error"));
		}
		/** Delete Selected AssignmentHist records **/
		String [] selectedIds = regform.getSelectedHists();	
		if (selectedIds!=null && selectedIds.length > 0){			
			for(String selectedId:selectedIds){
				DeleteProductionSupportJpoAssignmentHistoryEvent deleteAssignmentEvent = new DeleteProductionSupportJpoAssignmentHistoryEvent();
				deleteAssignmentEvent.setAssignmentHistoryId(selectedId);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(deleteAssignmentEvent);
				log.info("[" + new Date() + "] ProdSupport (" + logonid
						+ ") - Performed a MODIFY JPO DELETE JPOASSNMNMNTHIST_ID="+
						selectedIds.toString());	
			}		
		}else{
			regform.setMsg("Error - At least one item must be selected.");
			return mapping.findForward("error");	
		}
		
		/** Flag the assignmenthist records that were deleted **/
		Iterator iter = regform.getAssnmnthists().iterator();
		
		while (iter.hasNext()){
			JPOAssignmentHistoryResponseEvent next = (JPOAssignmentHistoryResponseEvent) iter.next();
			
			for (int i=0; i<selectedIds.length; i++){
				
				if (next.getJpoAssignmentHistoryId().equals(selectedIds[i]))
					next.setWasChecked(true);
			}
		}		
		regform.setMsg("");
		return mapping.findForward("success");
	}
}
