package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SearchJuvenileCasefileListEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileReferralListEvent; 
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and SERVEVENT_ID and displays the record
 */

public class ViewJuvenileUnpurgeQueryAction extends Action {
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


		//regform.clearSpecialProcessingResults();

		regform.setFromJuvenileId(juvenileId);

		// Log the query attempt
		log.info("Juvenile Un-Purge Query - JuvID: " + juvenileId + " LogonId: " + SecurityUIHelper.getLogonId());

		ArrayList juveniledetails = retrieveJuvenileDetails(juvenileId, regform);

		if (juveniledetails != null && juveniledetails.size() > 0) {
		    regform.setJuveniles(juveniledetails);
		} else {
			regform.setMsg("Juvenile record not found.  Please retry search "
					+ juvenileId + ".");
			return mapping.findForward("error");
		}
		
		
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
