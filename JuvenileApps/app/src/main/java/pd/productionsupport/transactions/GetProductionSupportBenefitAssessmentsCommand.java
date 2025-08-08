package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportBenefitAssessmentsEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.family.BenefitsAssessment;

public class GetProductionSupportBenefitAssessmentsCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportBenefitAssessmentsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportBenefitAssessmentsEvent getAssessmentsEvent = (GetProductionSupportBenefitAssessmentsEvent) event;
		Iterator benefitAssessmentsIter = BenefitsAssessment.findAll("casefileId",getAssessmentsEvent.getCasefileId());
		
		while(benefitAssessmentsIter != null && benefitAssessmentsIter.hasNext()){
			BenefitsAssessment benefitAssessmentResponse = (BenefitsAssessment)benefitAssessmentsIter.next();	
			ProductionSupportBenefitsAssessmentsResponseEvent assessmentResponseEvent = new ProductionSupportBenefitsAssessmentsResponseEvent();
			
			assessmentResponseEvent.setAssessmentId(benefitAssessmentResponse.getOID());
			assessmentResponseEvent.setGuardianId(benefitAssessmentResponse.getGuardianId());
			assessmentResponseEvent.setCasefileId(benefitAssessmentResponse.getCasefileId());			
			assessmentResponseEvent.setJuvenileId(benefitAssessmentResponse.getJuvenileId());
			assessmentResponseEvent.setFirstName(benefitAssessmentResponse.getJuvenile().getFirstName());
			assessmentResponseEvent.setLastName(benefitAssessmentResponse.getJuvenile().getLastName());
			assessmentResponseEvent.setRequestDate(benefitAssessmentResponse.getRequestDate());
			assessmentResponseEvent.setEntryDate(benefitAssessmentResponse.getEntryDate());
			assessmentResponseEvent.setTitleIVeOfficerId(benefitAssessmentResponse.getTitleIVeOfficerId());
			assessmentResponseEvent.setTitleIVeOfficerName(benefitAssessmentResponse.getTitleIVeOfficerName());
			assessmentResponseEvent.setPweRelationshipToJuvenile(benefitAssessmentResponse.getPweRelationshipToJuvenile());
			assessmentResponseEvent.setRequesterName(benefitAssessmentResponse.getRequesterName());
			
			if(benefitAssessmentResponse.getCreateUserID() != null){
				assessmentResponseEvent.setCreateUserID(benefitAssessmentResponse.getCreateUserID());
			}
			if(benefitAssessmentResponse.getCreateTimestamp() != null){
				assessmentResponseEvent.setCreateDate(new Date(benefitAssessmentResponse.getCreateTimestamp().getTime()));
			}
			if(benefitAssessmentResponse.getUpdateUserID() != null){
				assessmentResponseEvent.setUpdateUser(benefitAssessmentResponse.getUpdateUserID());
			}
			if(benefitAssessmentResponse.getUpdateTimestamp() != null){
				assessmentResponseEvent.setUpdateDate(new Date(benefitAssessmentResponse.getUpdateTimestamp().getTime()));
			}
			if(benefitAssessmentResponse.getCreateJIMS2UserID() != null){
				assessmentResponseEvent.setCreateJIMS2UserID(benefitAssessmentResponse.getCreateJIMS2UserID());
			}
			if(benefitAssessmentResponse.getUpdateJIMS2UserID() != null){
				assessmentResponseEvent.setUpdateJIMS2UserID(benefitAssessmentResponse.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(assessmentResponseEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
