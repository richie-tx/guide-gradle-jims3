//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.GetProductionSupportMaysiAssessmentEvent;
import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class GetProductionSupportMaysiAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProductionSupportMaysiAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		GetProductionSupportMaysiAssessmentEvent maysiAssessmentEvent = (GetProductionSupportMaysiAssessmentEvent) event;
		Iterator<JuvenileMAYSIMetadata> maysiAssessmentIter = null;
		if(maysiAssessmentEvent.getJuvenileId() != null && maysiAssessmentEvent.getReferralNumber() != null ){
			maysiAssessmentIter = JuvenileMAYSIMetadata.findAll(maysiAssessmentEvent);
		}else if(maysiAssessmentEvent.getMaysiAssessmentId() != null){
			JuvenileMAYSIMetadata maysiAssessment = JuvenileMAYSIMetadata.find(Integer.toString(maysiAssessmentEvent.getMaysiAssessmentId()));
			ArrayList<JuvenileMAYSIMetadata> maysiCollection = new ArrayList<JuvenileMAYSIMetadata>();
			maysiCollection.add(maysiAssessment);
			maysiAssessmentIter = maysiCollection.iterator();
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(maysiAssessmentIter != null && maysiAssessmentIter.hasNext()){
			JuvenileMAYSIMetadata maysiAssessment = (JuvenileMAYSIMetadata)maysiAssessmentIter.next();
			if(maysiAssessment != null){
				MAYSIDetailsResponseEvent maysiDetailsResponseEvent = new MAYSIDetailsResponseEvent();
				maysiDetailsResponseEvent.setAssessmentId(maysiAssessment.getOID());
				if(maysiAssessment.getAssessmentOption() != null){
					maysiDetailsResponseEvent.setAssessmentOption(maysiAssessment.getAssessmentOption().getCode());
				}
				maysiDetailsResponseEvent.setAssessOfficerId(maysiAssessment.getRequestingOfficerId());
				maysiDetailsResponseEvent.setAssessmentDate(maysiAssessment.getRequestDate());
				maysiDetailsResponseEvent.setReferralNumber(maysiAssessment.getReferralNumber());
				maysiDetailsResponseEvent.setAdministered(maysiAssessment.isAdministered());
				maysiDetailsResponseEvent.setHasPreviousMAYSI(maysiAssessment.isHasPreviousMAYSI());
				maysiDetailsResponseEvent.setLocationUnitId(maysiAssessment.getLocationUnitId());
				maysiDetailsResponseEvent.setLengthOfStayId(maysiAssessment.getLengthOfStayId());
				maysiDetailsResponseEvent.setFacilityTypeId(maysiAssessment.getFacilityTypeId());
				maysiDetailsResponseEvent.setJuvenileNum(maysiAssessment.getJuvenileNumber());
				maysiDetailsResponseEvent.setReasonNotDoneId(maysiAssessment.getReasonNotDoneId());	
				maysiDetailsResponseEvent.setOtherReasonNotDone(maysiAssessment.getOtherReasonNotDone());
				maysiDetailsResponseEvent.setSexId(maysiAssessment.getSexId());
				maysiDetailsResponseEvent.setRaceId(maysiAssessment.getRaceId());
				maysiDetailsResponseEvent.setTestAge(String.valueOf(maysiAssessment.getTestAge()));			
				if(maysiAssessment.getCreateUserID() != null){
					maysiDetailsResponseEvent.setCreateUser(maysiAssessment.getCreateUserID());
				}
				if(maysiAssessment.getCreateTimestamp() != null){
					maysiDetailsResponseEvent.setCreateDate(new Date(maysiAssessment.getCreateTimestamp().getTime()));
				}
				if(maysiAssessment.getUpdateUserID() != null){
					maysiDetailsResponseEvent.setUpdateUser(maysiAssessment.getUpdateUserID());
				}
				if(maysiAssessment.getUpdateTimestamp() != null){
					maysiDetailsResponseEvent.setUpdateDate(new Date(maysiAssessment.getUpdateTimestamp().getTime()));
				}
				if(maysiAssessment.getCreateJIMS2UserID() != null){
					maysiDetailsResponseEvent.setCreateJims2User(maysiAssessment.getCreateJIMS2UserID());
				}
				if(maysiAssessment.getUpdateJIMS2UserID() != null){
					maysiDetailsResponseEvent.setUpdateJims2User(maysiAssessment.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(maysiDetailsResponseEvent);
			}
		}
	}
	
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
