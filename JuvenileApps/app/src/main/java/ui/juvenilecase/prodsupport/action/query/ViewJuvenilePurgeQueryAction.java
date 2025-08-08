package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SearchJuvenileCasefileListEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.referral.GetJuvenileReferralListEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and SERVEVENT_ID and displays the record
 */

public class ViewJuvenilePurgeQueryAction extends Action {
	private Logger log = Logger.getLogger("ViewJuvenilePurgeQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		regform.setReferralNum("");
		regform.clearSpecialProcessingResults();
		regform.clear();
		regform.setMsg("");

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			return mapping.findForward("error");
		}

		String juvenileId = regform.getFromJuvenileId();

		if (juvenileId == null || juvenileId.equals("")) {
			regform.setMsg("You must enter a valid Juvenile ID.");
			return mapping.findForward("error");
		}


		regform.clearSpecialProcessingResults();

		regform.setFromJuvenileId(juvenileId);

		// Log the query attempt
		log.info("Juvenile Purge Query - JuvID: " + juvenileId + " LogonId: " + SecurityUIHelper.getLogonId());

		ArrayList juveniledetails = retrieveJuvenileDetails(juvenileId, regform);

		if (juveniledetails != null && juveniledetails.size() > 0) {
			regform.setMaysidetailCount(juveniledetails.size());
			regform.setMaysidetails(juveniledetails);
		} else {
			regform.setMsg("Juvenile record not found.  Please retry search "
					+ juvenileId + ".");
			return mapping.findForward("error");
		}
		
		/* Display error messages via pop out box.  User must confirm each error message.
		
		1)	The juvenile must not have an active facility booking.  Locate the juveniles facility header record.
		If the juvenile�s JUVENILE_FACILITY_ADMISSION_HEADER.FacilityStatus is populated, display on screen notice:  Juvenile is currently booked in a facility.  Juvenile cannot be SEALED.

		2)	All associated referral numbers must be CLOSED to process the sealing.
		If the juvenile status = CLOSED, this indicates all referral numbers have been closed. If Juvenile Status is not equal to CLOSED, then display on screen notice:  
		Juvenile has one or more ACTIVE referral numbers.  All referrals must be CLOSED to process sealing.

		3)	All associated supervisions/casefiles must be CLOSED to process the sealing.  Locate the juvenile�s supervision history.
		If the juvenile has one or more supervisions not equal to CLOSED or CLOSING APPROVED {JUVENILE_CASEFILE.CaseStatus <> CLOSED}, then display on screen notice:  
		Juvenile has one or more supervisions not equal to CLOSED.  All casefiles must be CLOSED to process sealing.

		Casefile status = CLOSING PENDING or CLOSING SUBMITTED is considered active and must not be considered as a closed option.
		*/

		hasActiveCasefiles(juvenileId, regform);
		//hasActiveFacilityBooking(juvenileId, regform);
		hasActiveReferrals(juvenileId, regform);
		regform.setJuveniles(juveniledetails);
		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	/**
	 * 
	 * @param juvenileId
	 * @return
	 */
	private ArrayList retrieveJuvenileDetails( String juvenileId, ProdSupportForm form ){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		SearchJuvenileProfilesEvent juvProfileEvent = (SearchJuvenileProfilesEvent)
			EventFactory.getInstance( JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES ) ;
		juvProfileEvent.setJuvenileNum(juvenileId);

		ArrayList juveniles = (ArrayList) MessageUtil.postRequestListFilter(juvProfileEvent, JuvenileProfileDetailResponseEvent.class);

		for( int y=0;y<juveniles.size();y++){
		    
		    JuvenileProfileDetailResponseEvent juvResp = (JuvenileProfileDetailResponseEvent) juveniles.get(y);
		    form.setRectype(juvResp.getRecType());
		}
		return juveniles;	
	}
	
	/**
	 * 
	 * @param juvNum
	 * @return
	 */
	/*private void hasActiveFacilityBooking( String juvNum, ProdSupportForm regform ){
	    
	    JuvenileFacilityHeaderResponseEvent response = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvNum);
	    if( response != null ){
		
		if( response.getFacilityStatus() != null ){
		   
		    regform.setHasActiveFacility("Y");
		    
		}		    
	    }	    
	}*/
	
	/**
	 * 
	 * @param juvNum
	 * @return
	 */
	private void hasActiveReferrals( String juvNum, ProdSupportForm regform ){
	    
	    GetJuvenileReferralListEvent event = (GetJuvenileReferralListEvent) 
		    				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEREFERRALLIST);
		
	    /* commented this to improve performance
	     GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) 
			EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	     */
	    event.setJuvenileNum( juvNum );

		List referralResp = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);
		
		for( int x=0; x< referralResp.size();x++){
		    
		    JuvenileProfileReferralListResponseEvent responseEvt = (JuvenileProfileReferralListResponseEvent) referralResp.get(x);
		    if( "ACTIVE".equalsIgnoreCase( responseEvt.getReferralStatus())){
			
			regform.setHasActiveReferral("Y");
			break;
		    }
		    
		}
	}
	
	/**
	 * 
	 * @param juvNum
	 * @return
	 */
	private void hasActiveCasefiles( String juvNum ,ProdSupportForm form ){
	    
    	    	/*SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent)
    		EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
    	    	 */
	    	SearchJuvenileCasefileListEvent search = (SearchJuvenileCasefileListEvent)
			EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILECASEFILELIST);

		search.setJuvenileId( juvNum );
		List caseFileCollection = MessageUtil.postRequestListFilter(search,JuvenileProfileCasefileListResponseEvent.class);
		Iterator<JuvenileProfileCasefileListResponseEvent> listIter = caseFileCollection.iterator();
			
		while ( listIter.hasNext() ){
			    
			JuvenileProfileCasefileListResponseEvent caseResp = listIter.next();
			if(!"CLOSED".equalsIgnoreCase( caseResp.getCaseStatus() )){
			
			    form.setHasActiveCasefile("Y");
			    break;
			 }
		}		

	}
}
