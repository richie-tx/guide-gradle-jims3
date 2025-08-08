package pd.juvenilecase.facility.transactions;

import java.util.Date;

import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityReturnEscapeEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class SaveJuvenileFacilityReturnEscapeCommand implements ICommand{

	/**
	 * 
	 */
	public void execute(IEvent event) throws Exception {

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SaveJuvenileFacilityReturnEscapeEvent reqEvent = (SaveJuvenileFacilityReturnEscapeEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		IHome home= new Home();
		
		//update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNum());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNum(),reqEvent.getFacilitySequenceNum());
		
		if(facility!=null){
			//Sa Reason change 
			if(reqEvent.isObservationStatusChanged()){
				if(reqEvent.getSaReason()!=null || reqEvent.getSpecialAttentionCd()!=null){
					facility.setSaReasonCode(reqEvent.getSaReason());
					facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
						//persist Spl Attention Comments //task #34820		
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
			//facility.setDetentionStatus("R");
			//change it to Q task 148169
			facility.setDetentionStatus("Q");
			//
			facility.setReturnDate(reqEvent.getReturnDate());
			facility.setReturnStatus(reqEvent.getReturnStatus());
			facility.setReturnTime(reqEvent.getReturnTime());
			facility.setReturnReason(reqEvent.getReturnReason());
			//facility.setComments(reqEvent.getFacilityAdmissionComments());
			if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
				JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
				comments.setComments(reqEvent.getFacilityAdmissionComments());
				comments.setJuvenileNum(reqEvent.getJuvenileNum());
				comments.setDetentionId(facility.getOID());
				home= new Home();
				home.bind(comments); //insert the comments.
			}
			
			//delete escape and release related attributes.
			facility.setEscapeCode("");
			facility.setReleaseAuthorizedBy("");
			facility.setReleaseBy("");
			facility.setReleaseDate(null);
			facility.setReleaseTime("");
			facility.setReleaseTo("");
			facility.setReleaseReason("");  //Bug #62273
			facility.setOutcome("");	//Bug #62273
		}
		if(header!=null){
				header.setHeaderFacility(reqEvent.getDetainedFacility());
				//header.setFacilityStatus("R");
				//change it to Q task 148169
				header.setFacilityStatus("Q");
				//
				Date date = null;
				header.setRelocationDate(date);
				header.setRelocationTime("");
		}
		try{
			home.bind(facility);
			home.bind(header);
		}catch(Exception ex){
			ErrorResponseEvent evt = new ErrorResponseEvent();
 			evt.setException(ex);
 			evt.setMessage("****Exception while updating JJS_DETENTION / JJS HEADER****");
 			dispatch.postEvent(evt);
		}
		//US 39468 - update the detention status in JJS
		JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNum() );
		if(juvenile!=null)
		{
			juvenile.setDetentionStatusId("R");			
		}	
		respEvent.setSaveable(true);

		dispatch.postEvent(respEvent);
	}
}
