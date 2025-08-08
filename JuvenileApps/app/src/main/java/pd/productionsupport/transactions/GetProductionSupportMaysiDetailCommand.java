//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

public class GetProductionSupportMaysiDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProductionSupportMaysiDetailCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		GetProductionSupportMaysiDetailEvent maysiDetailEvent = (GetProductionSupportMaysiDetailEvent) event;
		Iterator<JuvenileMAYSI> maysiIter = null;
		if(maysiDetailEvent.getJuvenileId() != null){
			maysiIter = JuvenileMAYSI.findAll(maysiDetailEvent);
		}else if(maysiDetailEvent.getMaysiDetailId() != null){
			JuvenileMAYSI maysi = JuvenileMAYSI.find(Integer.toString(maysiDetailEvent.getMaysiDetailId()));
			ArrayList<JuvenileMAYSI> maysiColl = new ArrayList<JuvenileMAYSI>();
			maysiColl.add(maysi);
			maysiIter = maysiColl.iterator();
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(maysiIter != null && maysiIter.hasNext()){
			JuvenileMAYSI maysi = (JuvenileMAYSI)maysiIter.next();
			if(maysi != null){
				MAYSIDetailsResponseEvent maysiDetailsResponseEvent = new MAYSIDetailsResponseEvent();
				maysiDetailsResponseEvent.setMaysiDetailId(maysi.getOID());		
				maysiDetailsResponseEvent.setJuvenileNum(String.valueOf(maysi.getJuvenileNumber()));
				maysiDetailsResponseEvent.setScreenDate(maysi.getScreenDate());
				maysiDetailsResponseEvent.setReferralNumber(maysi.getReferralNum());
				maysiDetailsResponseEvent.setTestAge(String.valueOf(maysi.getTestAge()));
	
				maysiDetailsResponseEvent.setSex(String.valueOf(maysi.getGender()));
				maysiDetailsResponseEvent.setEthnicity(maysi.getEthnicBackground());
				if(maysi.getLengthOfStay() != null){
					maysiDetailsResponseEvent.setLengthOfStay(maysi.getLengthOfStay().getCode());
				}
				maysiDetailsResponseEvent.setPriorPreviousMaysi(maysi.getPreviousMAYSI());
				if(maysi.getFacilityType() != null){
					maysiDetailsResponseEvent.setFacilityType(maysi.getFacilityType().getCode());
				}
				maysiDetailsResponseEvent.setFacilityName(maysi.getFacilityNameString());
				maysiDetailsResponseEvent.setLocationUnitId(maysi.getFacilityNameId());
				maysiDetailsResponseEvent.setAngryIrritable(maysi.getAngryIrritable());
				maysiDetailsResponseEvent.setThoughtDisturbance(maysi.getThoughtDisturbance());
				maysiDetailsResponseEvent.setSomaticComplaint(maysi.getSomaticComplaint());
				maysiDetailsResponseEvent.setAlcoholDrug(maysi.getAlcoholDrug());
				
				maysiDetailsResponseEvent.setSuicideIdetaion(maysi.getSuicideIdeation());
				maysiDetailsResponseEvent.setTraumaticExpression(maysi.getTraumaticExpression());
				maysiDetailsResponseEvent.setDepressionAnxiety(maysi.getDepressionAnxiety());
				maysiDetailsResponseEvent.setAdministered(maysi.getAdminister());
				maysiDetailsResponseEvent.setReasonNotDone(maysi.getReasonNotDoneId());
				maysiDetailsResponseEvent.setSubAssessComplete(maysi.getSubsequentAssessmentComplete());
				maysiDetailsResponseEvent.setAssessmentFound(maysi.getAssessmentFound());
				
				if(maysi.getCreateUserID() != null){
					maysiDetailsResponseEvent.setCreateUser(maysi.getCreateUserID());
				}
				if(maysi.getCreateTimestamp() != null){
					maysiDetailsResponseEvent.setCreateDate(new Date(maysi.getCreateTimestamp().getTime()));
				}
				if(maysi.getUpdateUserID() != null){
					maysiDetailsResponseEvent.setUpdateUser(maysi.getUpdateUserID());
				}
				if(maysi.getUpdateTimestamp() != null){
					maysiDetailsResponseEvent.setUpdateDate(new Date(maysi.getUpdateTimestamp().getTime()));
				}
				if(maysi.getCreateJIMS2UserID() != null){
					maysiDetailsResponseEvent.setCreateJims2User(maysi.getCreateJIMS2UserID());
				}
				if(maysi.getUpdateJIMS2UserID() != null){
					maysiDetailsResponseEvent.setUpdateJims2User(maysi.getUpdateJIMS2UserID());
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
