package ui.juvenilecase.prodsupport.action.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.GetProductionSupportAssociatedJuvenileEventReferralsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileAttendanceEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentsbyDateEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.programreferral.reply.ProgramAssociatedJuvenileEventReferralResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import messaging.calendar.reply.CalendarEventContextResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
 * 
 * CSJUVPROGREF has child records in:
 * 		CSPRGREFCOMMENTS
 * 		CSEVENTREFERRAL
 * 		CSPROGRFASNHIST
 */

public class ReferralCommentDeleteQueryAction extends Action {

	private Logger log = Logger.getLogger("ReferralCommentDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
	
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
		
		
		String juvprogrefId = regform.getJuvprogrefId();
		String commentDate = regform.getCommentDate();
		String formattedCourtDate=null;
		 if (commentDate != null && !commentDate.isEmpty())
		    {
			Date cortDate = DateUtil.stringToDate(commentDate, DateUtil.DATE_FMT_1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			formattedCourtDate = sdf.format(cortDate);
			//jjsCLCrtEvent.setCourtDate(formattedCourtDate);
		    }
		
		try {
		    Integer.parseInt(juvprogrefId);
		} catch (Exception e ){
		    regform.setMsg("You must enter a valid Program Referral ID.");
			return (mapping.findForward("error"));
		}
		//Clear the form, then reset the key value
		regform.clearAllResults();		
		regform.setJuvprogrefId(juvprogrefId);
		regform.setCommentDate(commentDate);
		
		//Log the query attempt
		log.info("["+new Date()+"] ProdSupport Retrieve Query Delete Referral (LogonId: "+SecurityUIHelper.getLogonId().toUpperCase()+") - Referral Query ID: " + juvprogrefId);	
		
		/**
		 * Search for juvprogrefs
		 */
		/*ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvprogrefs = getProgramReferralsById(juvprogrefId);

		if (juvprogrefs!=null && juvprogrefs.size() > 0){
			for(ProductionSupportJuvenileProgramReferralResponseEvent event: juvprogrefs){
				if(event != null && event.getJuvenileProgramReferralId() != null){
					regform.setJuvprogrefCount(juvprogrefs.size());
					regform.setJuvprogrefs(juvprogrefs);
					break;
				}else{
					regform.setJuvprogrefCount(0);
					regform.setJuvprogrefs(null);
				}
			}			
		}	
		else
		{
			regform.setMsg("No program referral records found for referral "+juvprogrefId+".");
			return mapping.findForward("error");
		}*/
		
		/**
		 * Search for progrefcomments
		 */
		ArrayList progrefcomments = getProgramReferralCommentsByIdandDate(juvprogrefId,formattedCourtDate);
		if (progrefcomments!=null){
			regform.setProgrefcommentsCount(progrefcomments.size());		
			regform.setProgrefcomments(progrefcomments);
		}

		
		
		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	

	/**
	 * Retrieve the Program Referral Comments
	 * @param referralNum
	 * @return
	 */
	
	public static ArrayList getProgramReferralCommentsByIdandDate(String referralNum,String commentDate){
		ArrayList juvenileProgramReferralCommentsList= null;
		
		// Get referral comments
		GetProductionSupportJuvenileProgramReferralCommentsbyDateEvent getJuvenileProgramRerralCommentsEvent = (GetProductionSupportJuvenileProgramReferralCommentsbyDateEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALCOMMENTSBYDATE );
		getJuvenileProgramRerralCommentsEvent.setJuvenileProgramReferralNum(referralNum );
		getJuvenileProgramRerralCommentsEvent.setCommentDate(commentDate);
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralCommentsEvent);
		juvenileProgramReferralCommentsList = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProgramReferralCommentResponseEvent.class);
		
		return juvenileProgramReferralCommentsList;
	}
	
	
	
	
}
