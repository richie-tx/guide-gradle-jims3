/**
 * 
 */
package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityReturnTempReleaseEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.referral.JJSReferral;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * @author sthyagarajan
 *
 */
public class SaveJuvenileFacilityReturnTempReleaseCommand implements ICommand{

	/**
	 * 
	 */
	public SaveJuvenileFacilityReturnTempReleaseCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		SaveJuvenileFacilityReturnTempReleaseEvent reqEvent = (SaveJuvenileFacilityReturnTempReleaseEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		IHome home= new Home();

		//update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNum());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNum(),reqEvent.getFacilitySequenceNum());
		
		if(facility!=null){
			if(reqEvent.isObservationStatusChanged()){
				//SA Reason change 
				if(reqEvent.getSaReason()!=null || reqEvent.getSpecialAttentionCd()!=null || reqEvent.getSaReasonOtherComments()!=null){
					facility.setSaReasonCode(reqEvent.getSaReason());
					facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
					//persist Spl Attention Comments //task #34820 bug fix:40650		
					String saReasonOtherComments = reqEvent.getSaReasonOtherComments();
					String juvenileNum = reqEvent.getJuvenileNum();
					String detentionId = facility.getOID();
					if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && detentionId!=null){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(saReasonOtherComments);
						splAttentionComments.setJuvenileNum(juvenileNum);
						splAttentionComments.setDetentionId(detentionId);
						home.bind(splAttentionComments); //insert the comments.
					}	
				}
			}
			
			//Return Details
			/**Update the current Admission_Release record with the system and user-defined Return attributes. **/
			/**The FacilityStatus of the JUVENILE_FACILITY_ADMISSION_HEADER reverts to the Detention type associated with the AdmitReason on the current Admission_Release record. **/
			if(facility.getAdmitReason()!=null && !facility.getAdmitReason().isEmpty()){
				
			    JuvenileAdmitReasons admit =  JuvenileAdmitReasons.find("code",facility.getAdmitReason());
			    if ( admit!= null ){
				facility.setDetentionStatus(admit.getDetentionType());
			    }			    
			}
			facility.setReturnDate(reqEvent.getReturnDate());
			facility.setReturnStatus(reqEvent.getReturnStatus());
			facility.setReturnTime(reqEvent.getReturnTime());
			facility.setReturnReason(reqEvent.getReturnReason());
			//facility.setComments(reqEvent.getFacilityAdmissionComments());
			// add facility comments for new recs.added for #51737
			if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
				JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
				comments.setComments(reqEvent.getFacilityAdmissionComments());
				comments.setJuvenileNum(reqEvent.getJuvenileNum());
				comments.setDetentionId(facility.getOID());
				home= new Home();
				home.bind(comments); //insert the comments.
			}
		
			
			/**Delete the TempRelease and Release attributes from the current admission record. **/
			facility.setReleaseAuthorizedBy("");
			facility.setReleaseBy("");
			facility.setReleaseDate(null);
			facility.setReleaseTime("");
			facility.setReleaseTo("");
			facility.setTempReleaseOtherComments("");
			facility.setTempReleaseReasonCd("");
			facility.setOutcome("");
			facility.setReleaseReason("");  //Bug #62273
		}
		try{
			home.bind(facility);
		}catch(Exception ex){
			ErrorResponseEvent evt = new ErrorResponseEvent();
 			evt.setException(ex);
 			evt.setMessage("****Exception while updating JJS_DETENTION****");
 			dispatch.postEvent(evt);
		}
		if(header!=null)
		{
			if(facility.getDetentionStatus()!=null && !facility.getDetentionStatus().isEmpty())
			{
				header.setFacilityStatus(facility.getDetentionStatus());
				//reset the temp release flag to null once kid is back
				if(header.getReleaseDecisionStatus()!=null&&header.getFacilityStatus()!=null)
				    {
					if(header.getReleaseDecisionStatus().equals("A"))    				    
						header.setReleaseDecisionStatus(null);
				    }
				if(header.getDistReleaseDecisionStatus()!=null&&header.getFacilityStatus()!=null)
				    {								     
				    	if(header.getDistReleaseDecisionStatus().equals("A"))    				    
				    	    header.setDistReleaseDecisionStatus(null);    				    
				    }
				try{				    
					home.bind(header);
				}
				catch(Exception ex){
					ErrorResponseEvent evt = new ErrorResponseEvent();
		 			evt.setException(ex);
		 			evt.setMessage("****Exception while updating JJS HEADER****");
		 			dispatch.postEvent(evt);
				}
			}
		}
		
		/** 
		 * M204 Legacy Update:  
		   Delete the FinalReleaseDate from the JUVENILE_JJS_REFERRAL record for the BookingReferral of the JUVENILE_FACILITY_ADMISSION_RELEASE record
		 */
		
		GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
		jjsEvt.setJuvenileNum(reqEvent.getJuvenileNum());
		jjsEvt.setReferralNum(reqEvent.getBookingRefNum());
		
		Iterator<JJSReferral> referralItr = JJSReferral.findAll(jjsEvt);
		if(referralItr.hasNext()) {
			JJSReferral jjsReferral = referralItr.next();
			if(jjsReferral.getFinalReleaseDate()==null || (jjsReferral.getFinalReleaseDate()!=null)){
				jjsReferral.setFinalReleaseDate(null);
				try{
					home.bind(jjsReferral);
				}catch(Exception ex){
					ErrorResponseEvent evt = new ErrorResponseEvent();
		 			evt.setException(ex);
		 			evt.setMessage("****M204 Exception while updating JJS_REFERRAL****");
		 			dispatch.postEvent(evt);
				}
				
			}
		}
		//US 39468 - update the detention status in JJS
		JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNum() );
		if(juvenile!=null)
		{
			juvenile.setDetentionStatusId(facility.getDetentionStatus());			
		}	
		respEvent.setSaveable(true);
		dispatch.postEvent(respEvent);
		
	}

}
