//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateMentalHealthDSMIVCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import pd.juvenile.Juvenile;
import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;
import messaging.mentalhealth.CreateMentalHealthDSMIVEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 *
 */
public class CreateMentalHealthDSMIVCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD0602A9
    */
   public CreateMentalHealthDSMIVCommand() 
   {
    
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
    *   Added new field	diagnosis10, medicalDiagnosis
    ****************************************************/
   /**
    * @param event
    * @roseuid 45D49C84003F
    */
   public void execute(IEvent event) 
   {
	CreateMentalHealthDSMIVEvent requestEvent = (CreateMentalHealthDSMIVEvent)event;
	JuvenileDSMIVResults juvDSMIVResults = new JuvenileDSMIVResults();
	juvDSMIVResults.setTestSessId(requestEvent.getTestSessId());	
	juvDSMIVResults.setTestDate(requestEvent.getTestDate());
	juvDSMIVResults.setAxis1PrimaryScore(requestEvent.getAxisIPrimaryScore());
	juvDSMIVResults.setAxis1SecondaryScore(requestEvent.getAxisISecondaryScore());
	juvDSMIVResults.setAxis1TertiaryScore(requestEvent.getAxisITertiaryScore());
	juvDSMIVResults.setAxis2PrimaryScore(requestEvent.getAxisIIPrimaryScore());
	juvDSMIVResults.setAxis2SecondaryScore(requestEvent.getAxisIISecondaryScore());
	juvDSMIVResults.setAxis3PrimaryScore(requestEvent.getAxisIIIPrimaryScore());
	juvDSMIVResults.setAxis3SecondaryScore(requestEvent.getAxisIIISecondaryScore());
	juvDSMIVResults.setAxis4Comments(requestEvent.getAxisIVComments());
	juvDSMIVResults.setAxis1FourthScore(requestEvent.getAxisIFourth());
	juvDSMIVResults.setAxis1FifthScore(requestEvent.getAxisIFifth());
	juvDSMIVResults.setAxis5Score(requestEvent.getAxisVScore());
	juvDSMIVResults.setAxis4EducationalProblems(requestEvent.isEducationalProblems());
	juvDSMIVResults.setAxis4HousingProblems(requestEvent.isHousingProblems());
	juvDSMIVResults.setAxis4HealthProblems(requestEvent.isHealthCareAccessProblems());
	juvDSMIVResults.setAxis4OccupationalProblems(requestEvent.isOccupationalProblems());
	juvDSMIVResults.setAxis4LegalProblems(requestEvent.isLegalSystemProblems());
	juvDSMIVResults.setAxis4PsychosocialProblems(requestEvent.isOtherPsychoEnvProblems());
	juvDSMIVResults.setAxis4SocialEnvironmentProblems(requestEvent.isSocialEnvProblems());
	juvDSMIVResults.setAxis4SupportGroupProblems(requestEvent.isPrimarySuppGrpProblems());
	juvDSMIVResults.setAxis4EconomicProblems(requestEvent.isEconomicProblems());
	juvDSMIVResults.setAxis4MentalHealthNeeds(requestEvent.isMentalHealthNeeds());
	juvDSMIVResults.setAxis4MentalHealthTreatment(requestEvent.isMentalHealthTreatment());
	juvDSMIVResults.setDsmivId(requestEvent.getDsmivId());
	juvDSMIVResults.setMedicalDiagnosis(requestEvent.getMedicalDiagnosis());
	juvDSMIVResults.setDiagnosis10(requestEvent.getDiagnosis10());
	
	String juvId = requestEvent.getJuvenileNum();
	// Profile stripping fix task 97544
	//Juvenile juvenile = Juvenile.find(juvId);
	Juvenile juvenile = Juvenile.findJCJuvenile(juvId);
	//
	if (juvenile!=null)
		juvenile.setMentalHealthServices(requestEvent.isMentalHealthNeeds());
	
   }
   
   /**
    * @param event
    * @roseuid 45D49C84004A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D49C84004C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D49C84004E
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
