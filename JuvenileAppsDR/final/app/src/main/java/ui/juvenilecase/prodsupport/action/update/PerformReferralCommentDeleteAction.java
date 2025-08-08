package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralCommentEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.GetProductionSupportAssociatedJuvenileEventReferralsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.programreferral.reply.ProgramAssociatedJuvenileEventReferralResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 *         This action performs the deletes necessary to remove a Juvenile
 *         Casefile and verifies that all records were deleted before returning
 *         the user to a summary screen.
 */

public class PerformReferralCommentDeleteAction extends Action {
	
	private Logger log = Logger.getLogger("PerformReferralDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String logonid = SecurityUIHelper.getLogonId();
		String commentId = regform.getCommentId();
		if (commentId == null || commentId.equals("")) {
		    commentId = request.getParameter("commentId");
		}
		
		if (commentId==null || commentId.equals("")){
			regform.setMsg("PerformReferralCommentDeleteAction - commentId was null.");
			return (mapping.findForward("error"));
		}
		
		
		log.info(" BEGIN DELETE REFERRAL - LOGON ID: "+ logonid);
		ArrayList<ProgramReferralCommentResponseEvent> juvprogrefcomments = null;
		juvprogrefcomments=getProgramReferralsById(commentId);
		regform.setReferralComments(juvprogrefcomments);
		
		/** First, log all the IDs of the dependent files that will be deleted automatically via RI **/ 
		writeLogEntries(regform, logonid);

		/**
		 *  Here is where the delete gets performed. 
		 **/	
		
		deleteProgramReferralCommentById(commentId);// pass oid
		ArrayList<ProgramReferralCommentResponseEvent> juvprogrefcommentafterdelete = null;
		juvprogrefcommentafterdelete=getProgramReferralsById(commentId);
		 
		/**
		 *  Check if the delete of the Program referral Record was successful.
		 **/			
		if(juvprogrefcommentafterdelete.size()>0){//check oid
			log.info("REFERRAL DELETE FAILED for JUVPROGREF_ID = " + regform.getJuvprogrefId() + " and LOGONID: " + logonid);
			regform.setMsg("Error - The referral was not deleted. PerformReferralDeleteAction.java (87)");
			return mapping.findForward("error");
			
		}else{
			log.info("Performed a REFERRAL COMMENT DELETE for JUVPROGREF_ID = " + regform.getJuvprogrefId() + " and LOGONID: " + logonid);
			regform.setMsg("");
			return mapping.findForward("success");
		}
		

		
		
	}
	
	/**
	 * LOG ALL THE RECORDS THAT WILL BE CASCADE DELETED BY DELETING THE REFERRAL
	 * @param regform
	 * @param logonid
	 */
	private void writeLogEntries(ProdSupportForm regform, String logonid){
	
		
		if (regform.getProgrefcomments()!=null){
			Iterator iter = regform.getProgrefcomments().iterator();
			while (iter.hasNext()){
				ProgramReferralCommentResponseEvent next = (ProgramReferralCommentResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Juvenile Program Referral Comment Record - LogonId: " + logonid + " CSPRGREFCOMMENTS - CSPRGREFCOMMENTS ID: "
					+ next.getProgramReferralCommentId());
			}
		}			
		
	}
	
	/**
	 * Check if Program Referral Record exists
	 * @param referralNum
	 * @return
	 */
	private ArrayList<ProgramReferralCommentResponseEvent> getProgramReferralsById(String referralId){
		boolean isReferral = false;
		ArrayList<ProgramReferralCommentResponseEvent> juvprogrefcomments = null;
		
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralCommentEvent getJuvenileProgramRerralCommentEvent = (GetProductionSupportJuvenileProgramReferralCommentEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALCOMMENT );
		getJuvenileProgramRerralCommentEvent.setReferralId(referralId);
		CompositeResponse juvenileProgramReferralCommentResp = MessageUtil.postRequest(getJuvenileProgramRerralCommentEvent);
		juvprogrefcomments = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralCommentResp, ProgramReferralCommentResponseEvent.class);
		/*for(ProgramReferralCommentResponseEvent event: juvprogrefcomments){
			if(event != null && event.getProgramReferralCommentId() != null){
			isReferral=true;
			break;
			}
		}*/
		
		return juvprogrefcomments;
	}
	
	/**
	 * CDelete a program referral based on referral number
	 * @param referralNum
	 * @return
	 */
	private void deleteProgramReferralCommentById(String referralNum){
		
		// Get and set Associated JuvProgRefs
	    DeleteProductionSupportJuvenileReferralCommentEvent deleteJuvenileProgramRerralsEvent = (DeleteProductionSupportJuvenileReferralCommentEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTJUVENILEREFERRALCOMMENT );
		deleteJuvenileProgramRerralsEvent.setReferralcommentId(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(deleteJuvenileProgramRerralsEvent);

	}
}
