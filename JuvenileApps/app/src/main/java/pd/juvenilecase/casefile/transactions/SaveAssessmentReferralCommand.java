package pd.juvenilecase.casefile.transactions;

import messaging.casefile.SaveAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.casefile.JuvenileAssessments;

/**
 * 
 * @author sthyagarajan
 *
 */
public class SaveAssessmentReferralCommand implements ICommand{

	
	/**
	 * Default constructor.
	 */
	public SaveAssessmentReferralCommand() {

	}

	public void execute(IEvent event) {
		
			SaveAssessmentReferralEvent requestEvent = (SaveAssessmentReferralEvent) event;
			String assessmentId= requestEvent.getAssessmentIDNumber();
			JuvenileAssessments gangAssessment=null;
			//update assessment
				if(assessmentId!=null){
					gangAssessment = JuvenileAssessments.find(assessmentId);
				
					if(gangAssessment!=null){
						gangAssessment.setOID(assessmentId);
						gangAssessment.setRecommendationsId(requestEvent.getRecommendationsId());
						gangAssessment.setAcceptedStatus(requestEvent.getAcceptedStatus());
						gangAssessment.setAssessmentStatus(requestEvent.getStatus());
						gangAssessment.setRejectionReason(requestEvent.getRejectionReason());
						gangAssessment.setAssessmentConclusion(requestEvent.getConclusion());
						gangAssessment.setParentNotified(requestEvent.getParentNotified());
						gangAssessment.setParentNotifiedGangAssReq(requestEvent.getParentNotifiedGangAssReq());
					}
				}
				else{ //save assessment
					gangAssessment = new JuvenileAssessments();
					gangAssessment.setEntryDate(requestEvent.getReferralDate());
					
					gangAssessment.setPlacementFacilityId(requestEvent.getPlacementFacilityId());
					gangAssessment.setGangNameId(requestEvent.getGangNameId());
					gangAssessment.setCliqueSetId(requestEvent.getCliqueSetId());
					gangAssessment.setReasonForReferralId(requestEvent.getReasonForReferralId());
					gangAssessment.setLvlOfGangInvolvementId(requestEvent.getLvlOfGangInvolvementId());
				
					gangAssessment.setAssessmentReferralTypeId(requestEvent.getAssessmentTypeId());
					gangAssessment.setPersonMakingReferral(requestEvent.getPersonMakingReferral());
					gangAssessment.setAssessmentComments(requestEvent.getComments());
					
					gangAssessment.setOtherCliqueSet(requestEvent.getOtherCliqueSet());
					gangAssessment.setOtherGangName(requestEvent.getOtherGangName());
					gangAssessment.setOtherReasonforReferral(requestEvent.getOtherReasonForReferral());
					gangAssessment.setDescHybrid(requestEvent.getDescHybrid());
					gangAssessment.setJuvenileId(requestEvent.getJuvenileNum());
					gangAssessment.setAssessmentStatus(requestEvent.getStatus());
					gangAssessment.setParentNotified(requestEvent.getParentNotified());
					gangAssessment.setParentNotifiedGangAssReq(requestEvent.getParentNotifiedGangAssReq());
				}
			
			if(gangAssessment!=null){
				IHome home = new Home();
				home.bind(gangAssessment);
				
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				AssessmentReferralResponseEvent respEvent = new AssessmentReferralResponseEvent();
				respEvent.setAssessmentIDNumber(gangAssessment.getOID());
				dispatch.postEvent(respEvent);
			}
		
		}
}
