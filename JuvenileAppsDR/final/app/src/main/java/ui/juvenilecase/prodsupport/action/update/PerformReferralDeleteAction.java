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
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.GetProductionSupportAssociatedJuvenileEventReferralsEvent;
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

public class PerformReferralDeleteAction extends Action {
	
	private Logger log = Logger.getLogger("PerformReferralDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String logonid = SecurityUIHelper.getLogonId();
		String juvprogrefId = regform.getJuvprogrefId();
		
		if (juvprogrefId==null || juvprogrefId.equals("")){
			regform.setMsg("PerformReferralDeleteAction - JuvProgRefID was null.");
			return (mapping.findForward("error"));
		}
		
		
		log.info(" BEGIN DELETE REFERRAL - LOGON ID: "+ logonid);
		
		/** First, log all the IDs of the dependent files that will be deleted automatically via RI **/ 
		writeLogEntries(regform, logonid);

		/**
		 *  Here is where the delete gets performed. 
		 **/	
		
		deleteProgramReferralById(juvprogrefId);
		 
		/**
		 *  Check if the delete of the Program referral Record was successful.
		 **/			
		if(getProgramReferralsById(juvprogrefId)){
			log.info("REFERRAL DELETE FAILED for JUVPROGREF_ID = " + regform.getJuvprogrefId() + " and LOGONID: " + logonid);
			regform.setMsg("Error - The referral was not deleted. PerformReferralDeleteAction.java (87)");
			return mapping.findForward("error");
			
		}else{
			log.info("Performed a REFERRAL DELETE for JUVPROGREF_ID = " + regform.getJuvprogrefId() + " and LOGONID: " + logonid);
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
	
		if (regform.getJuvprogrefs()!=null){
			Iterator iter = regform.getJuvprogrefs().iterator();
			while (iter.hasNext()){
				ProductionSupportJuvenileProgramReferralResponseEvent next = (ProductionSupportJuvenileProgramReferralResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Juvenile Program Referral Record - LogonId: " + logonid + " CSJUVPROGREF  - CSJUVPROGREF ID: "
					+ next.getJuvenileProgramReferralId());
			}
		}
		
		if (regform.getProgrefcomments()!=null){
			Iterator iter = regform.getProgrefcomments().iterator();
			while (iter.hasNext()){
				ProgramReferralCommentResponseEvent next = (ProgramReferralCommentResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Juvenile Program Referral Comment Record - LogonId: " + logonid + " CSPRGREFCOMMENTS - CSPRGREFCOMMENTS ID: "
					+ next.getProgramReferralCommentId());
			}
		}
			
		if (regform.getEventreferrals()!=null){
			Iterator iter = regform.getEventreferrals().iterator();
			while (iter.hasNext()){
				ProgramAssociatedJuvenileEventReferralResponseEvent next = (ProgramAssociatedJuvenileEventReferralResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Associated Juvenile Event Referrals - LogonId: " + logonid + " CSEVENTREFERRAL - CSEVENTREFERRAL ID: "
					+ next.getEventReferralId());
			}
		}
			
		if (regform.getCsprogrfasgnhists()!=null){
			Iterator iter = regform.getCsprogrfasgnhists().iterator();
			while (iter.hasNext()){
				ProgramReferralAssignmentHistoryResponseEvent next = (ProgramReferralAssignmentHistoryResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Program Referral Assignment Histories - LogonId: " + logonid + " CSPROGRFASNHIST-  CSPROGRFASGNHIST ID: "
					+ next.getProgramReferralAssignmentHistoryId());
			}		
		}	
		
		if (regform.getServattends()!=null){
			Iterator iter = regform.getServattends().iterator();
			while (iter.hasNext()){
				ServiceEventAttendanceResponseEvent next = (ServiceEventAttendanceResponseEvent)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Program Referral Service Attendance Events - LogonId: " + logonid + " CSSERVATTEND- SERVATTEND ID: "
					+ next.getServiceEventAttendanceId());
			}		
		}	
		
		if (regform.getCaleventconts()!=null){
			Iterator iter = regform.getCaleventconts().iterator();
			while (iter.hasNext()){
				CalendarEventContextResponse next = (CalendarEventContextResponse)iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Program Referral Calendar Event Context - LogonId: " + logonid + " JCCALEVENTCONT-  CALEVENTCONT ID: "
					+ next.getCalendarEventContextId());
			}		
		}	
	}
	
	/**
	 * Check if Program Referral Record exists
	 * @param referralNum
	 * @return
	 */
	private boolean getProgramReferralsById(String referralNum){
		boolean isReferral = false;
		ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvprogrefs = null;
		
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		for(ProductionSupportJuvenileProgramReferralResponseEvent event: juvprogrefs){
			if(event != null && event.getJuvenileProgramReferralId() != null){
			isReferral=true;
			break;
			}
		}
		
		return isReferral;
	}
	
	/**
	 * CDelete a program referral based on referral number
	 * @param referralNum
	 * @return
	 */
	private void deleteProgramReferralById(String referralNum){
		
		// Get and set Associated JuvProgRefs
		DeleteProductionSupportJuvenileReferralEvent deleteJuvenileProgramRerralsEvent = (DeleteProductionSupportJuvenileReferralEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTJUVENILEREFERRAL );
		deleteJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(deleteJuvenileProgramRerralsEvent);

	}
}
