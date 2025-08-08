//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthDSMTestDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.GetMentalHealthDSMTestDataEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthDSMTestDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB1917025C
    */
   public GetMentalHealthDSMTestDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B30103
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthDSMTestDataEvent dEvent = (GetMentalHealthDSMTestDataEvent)event; 
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	JuvenileDSMIVResults dsmResults =  JuvenileDSMIVResults.find(dEvent.getTestId());
	DSMIVTestResponseEvent dsmRespEvent = getDSMIVTestResponseEvent(dsmResults);
	dispatch.postEvent(dsmRespEvent);
   }
   
   /****************************************************
    *	ER 75795 Axis prompts to Diagnosis prompts 		
    * 		old value					new value
    *		Axis I Primary Score		Diagnosis 1
    *		Axis I Secondary Score		Diagnosis 2
    *		Axis I Tertiary Score		Diagnosis 3
    *		Axis I Fourth				Diagnosis 4
    *		Axis I Fifth				Diagnosis 5
    *		Axis II Primary Score		Diagnosis 6
    *		Axis II Secondary Score		Diagnosis 7
    *		Axis III Primary Score		Diagnosis 8
    *		Axis III Secondary Score	Diagnosis 9   
    *       Axis IV Comments            Comments
    *   Added new field diagnosis10
    ****************************************************/
   
   /**
 * @param dsmResults
 * @return
 */
private DSMIVTestResponseEvent getDSMIVTestResponseEvent(JuvenileDSMIVResults dsmResults) {
	DSMIVTestResponseEvent dsmRespEvent = new DSMIVTestResponseEvent();
	dsmRespEvent.setAxis1FifthScore(dsmResults.getAxis1FifthScore());
	dsmRespEvent.setAxis1FourthScore(dsmResults.getAxis1FourthScore());
	dsmRespEvent.setAxis1PrimaryScore(dsmResults.getAxis1PrimaryScore());
	dsmRespEvent.setAxis1SecondaryScore(dsmResults.getAxis1SecondaryScore());
	dsmRespEvent.setAxis1TertiaryScore(dsmResults.getAxis1TertiaryScore());
	dsmRespEvent.setAxis2PrimaryScore(dsmResults.getAxis2PrimaryScore());
	dsmRespEvent.setAxis2SecondaryScore(dsmResults.getAxis2SecondaryScore());
	dsmRespEvent.setAxis3PrimaryScore(dsmResults.getAxis3PrimaryScore());
	dsmRespEvent.setAxis3SecondaryScore(dsmResults.getAxis3SecondaryScore());
	dsmRespEvent.setAxis4Comments(dsmResults.getAxis4Comments());
	dsmRespEvent.setAxis4EconomicProblems(dsmResults.isAxis4EconomicProblems());
	dsmRespEvent.setAxis4EducationalProblems(dsmResults.isAxis4EducationalProblems());
	dsmRespEvent.setAxis4HealthProblems(dsmResults.isAxis4HealthProblems());
	dsmRespEvent.setAxis4HousingProblems(dsmResults.isAxis4HousingProblems());
	dsmRespEvent.setAxis4LegalProblems(dsmResults.isAxis4LegalProblems());
	dsmRespEvent.setAxis4OccupationalProblems(dsmResults.isAxis4OccupationalProblems());
	dsmRespEvent.setAxis4PsychosocialProblems(dsmResults.isAxis4PsychosocialProblems());
	dsmRespEvent.setAxis4SocialEnvironmentProblems(dsmResults.isAxis4SocialEnvironmentProblems());
	dsmRespEvent.setAxis4SupportGroupProblems(dsmResults.isAxis4SupportGroupProblems());
	dsmRespEvent.setAxis4MentalHealthNeeds(dsmResults.isAxis4MentalHealthNeeds());
	dsmRespEvent.setAxis4MentalHealthTreatment(dsmResults.isAxis4MentalHealthTreatment());
	dsmRespEvent.setDsmivId(dsmResults.getDsmivId());
	dsmRespEvent.setAxis5Score(dsmResults.getAxis5Score());
	dsmRespEvent.setTestDate(dsmResults.getTestDate());
	dsmRespEvent.setTestId(dsmResults.getTestId());
	dsmRespEvent.setCreateDate(dsmResults.getCreateDate());
	dsmRespEvent.setMedicalDiagnosis(dsmResults.getMedicalDiagnosis());
	dsmRespEvent.setDiagnosis10(dsmResults.getDiagnosis10());
	return dsmRespEvent;
}

/**
    * @param event
    * @roseuid 45DB18B30112
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB18B30114
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB18B30116
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
