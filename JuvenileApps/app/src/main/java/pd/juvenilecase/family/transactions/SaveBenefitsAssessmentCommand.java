//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilefamily\\transactions\\SaveBenefitsAssessmentCommand.java

package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.family.SaveBenefitsAssessmentEvent;
import messaging.juvenilecase.reply.IndividualIncomeDeterminationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDTaskConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.BenefitsAssessmentSourceCode;
import pd.juvenilecase.family.IndividualIncomeDetermination;
import pd.task.Task;

public class SaveBenefitsAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4370FD9200EC
    */
   public SaveBenefitsAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
 * @throws Exception
    * @roseuid 436A39B30052
    */
   public void execute(IEvent event) throws Exception 
   {
		SaveBenefitsAssessmentEvent evt = (SaveBenefitsAssessmentEvent)event;
		
		BenefitsAssessment ben = BenefitsAssessment.find( evt.getAssessmentId() );

		if ( ben.isCompleted() )
		{
			throw new IllegalStateException( "Save BenefitsAssessment(" + evt.getAssessmentId() + ") for Juvenile '" + ben.getJuvenileId() + "' is already completed." );
		}

		if ( ! ben.getGuardianId().equals(evt.getGuardianId()) )
		{
			throw new IllegalArgumentException( "Save BenefitsAssessment(" + evt.getAssessmentId() + ") with Event-GuardianId '" + evt.getGuardianId() + "' does not match BenefitsAssessment-GuardianId of '" + ben.getGuardianId() + "'." );
		}

		ben.setIsEligibleForMedicaid( evt.isEligibleForMedicaid() );
		ben.setIsReceivingMedicaid( evt.isReceivingMedicaid() );
		ben.setIsEligibleForTitleIVe( evt.isEligibleForTitleIVe() );
		ben.setIsReceivingTitleIVe( evt.isReceivingTitleIVe() );
   
		ben.setIsLegalResident( evt.isLegalResident() );
   
		ben.setOneParentIsStepparent( evt.getOneParentIsStepparent() );
		ben.setDeathOrAbsence( evt.getDeathOrAbsence() );
		ben.setIncapacityOrDisabilityOfParent( evt.getIncapacityOrDisabilityOfParent() );
		ben.setPrimaryWageEarnerUnderemployment( evt.getPrimaryWageEarnerUnderemployment() );
		ben.setPweWorkedLessThen100Hours( evt.getPweWorkedLessThen100Hours() );
		ben.setPweIncomeLessThanUnderemployedLimit( evt.getPweIncomeLessThanUnderemployedLimit() );
		ben.setPweRelationshipToJuvenileId( evt.getPweRelationshipToJuvenileId() );
		ben.setPweWorkedSteadyLessThan100Hours( evt.getPweWorkedSteadyLessThan100Hours() );
		ben.setPweWorkedIrregularLessThan100HoursAvg( evt.getPweWorkedIrregularLessThan100HoursAvg() );
		ben.setPweGrossMonthlyIncomeForOver100Hours( evt.getPweGrossMonthlyIncomeForOver100Hours() );
		ben.setWasChildLivingWithParent( evt.getWasChildLivingWithParent() );
		ben.setAfdcIncomeLimitsMet( evt.getAfdcIncomeLimitsMet() );
		ben.setAfdcIncomeStepparentsMonthlyGross( evt.getAfdcIncomeStepparentsMonthlyGross() );
		ben.setAfdcIncomeStepparentWorkRelatedExpenses( evt.getAfdcIncomeStepparentWorkRelatedExpenses() );
		ben.setAfdcIncomeStepparentOtherMonthlyIncome( evt.getAfdcIncomeStepparentOtherMonthlyIncome() );
		ben.setAfdcIncomeStepparentMonthyPaymentsToDependent( evt.getAfdcIncomeStepparentMonthyPaymentsToDependent() );
		ben.setAfdcIncomeStepparentMonthyAlimonyChildSupport( evt.getAfdcIncomeStepparentMonthyAlimonyChildSupport() );
		ben.setAfdcIncomeStepparentNoncertifiedCount( evt.getAfdcIncomeStepparentNoncertifiedCount());
		
		ben.setUnder10KLimit( evt.getUnder10KLimit() );
		ben.setChildMeetsEligibilityCriteria( evt.getChildMeetsEligibilityCriteria() );
		ben.setOrderContainsBestInterestLanguage( evt.getOrderContainsBestInterestLanguage() );
		ben.setResonableEffortsMadeWithin60Days( evt.getResonableEffortsMadeWithin60Days() );
		ben.setOrdersIncludeResponsibilityForCareAndPlacement( evt.getOrdersIncludeResponsibilityForCareAndPlacement() );
		ben.setChildMeetsAFDCAndOrderRequirements( evt.getChildMeetsAFDCAndOrderRequirements() );
	
		ben.setPweRelationshipToJuvenile( evt.getPweRelationshipToJuvenile() );
		ben.setAfdcIncomeCertifiedGroupSize( evt.getAfdcIncomeCertifiedGroupSize() );
		ben.setAfdcIncomeCertifiedGroupParentsSize( evt.getAfdcIncomeCertifiedGroupParentsSize() );
		ben.setAfdcIncomeCertifiedGroupLimit( evt.getAfdcIncomeCertifiedGroupLimit() );
		ben.setAfdcIncomeStepparentCountableEarnedMonthy( evt.getAfdcIncomeStepparentCountableEarnedMonthy() );
		ben.setAfdcIncomeStepparentTotalCountableMonthy( evt.getAfdcIncomeStepparentTotalCountableMonthy() );
		ben.setAfdcIncomeStepparentAllowanceAmount( evt.getAfdcIncomeStepparentAllowanceAmount() );
		ben.setAfdcIncomeStepparentAppliedIncome( evt.getAfdcIncomeStepparentAppliedIncome() );
		ben.setAfdcIncomeTotalMonthy( evt.getAfdcIncomeTotalMonthy() );
		ben.setAfdcIncomeTotalCountable( evt.getAfdcIncomeTotalCountable() );
	
		ben.setTitleIVeOfficerId( evt.getTitleIVeOfficerId() );
		ben.setTitleIVeOfficerName( evt.getTitleIVeOfficerName() );
	
		Iterator iidReplies = evt.getAfdcIncomeWorksheetItems().iterator();
		while ( iidReplies.hasNext() )
		{
			IndividualIncomeDeterminationResponseEvent iidReply = (IndividualIncomeDeterminationResponseEvent)iidReplies.next();
			if ( iidReply.getMemberId() == null || iidReply.getMemberId().trim().length() == 0 )
			{
				// Non-member record. See if it's been filled in.
				if ( iidReply.getName() != null && iidReply.getName().trim().length() > 0 )
				{
					IndividualIncomeDetermination iid = new IndividualIncomeDetermination();
					iid.setMemberId( null );
					iid.setName( iidReply.getName() );
					
					iid.setAge( iidReply.getAge() );
					iid.setRelationshipToJuvenileId( iidReply.getRelationshipToJuvenileId() );
					iid.setComments( iidReply.getComments() );
					iid.setIncomeSourceId( iidReply.getIncomeSourceId() );
					iid.setGrossMonthyIncome( iidReply.getGrossMonthyIncome() );
					ben.insertAFDCIncomeWorksheetItems( iid );
				}
			}
			else
			{
				// Member record.
				Iterator items = ben.getAFDCIncomeWorksheetItems().iterator();
				while ( items.hasNext() )
				{
					IndividualIncomeDetermination iid = (IndividualIncomeDetermination)items.next();
					if ( iidReply.getMemberId().equals(iid.getMemberId()) )
					{
						iid.setComments( iidReply.getComments() );
						iid.setIncomeSourceId( iidReply.getIncomeSourceId() );
						iid.setGrossMonthyIncome( iidReply.getGrossMonthyIncome() );
						break;					
					}
				}
			}
		}

		Collection sources = ben.getSourcesForAFDCInformation();
		sources.clear();
		Iterator srcReplies = evt.getSourcesForAFDCInformation().iterator();
		while ( srcReplies.hasNext() )
		{
			String source = (String)srcReplies.next();
			BenefitsAssessmentSourceCode code = new BenefitsAssessmentSourceCode();
			code.setChildId( source );
			code.setParentId( ben.getOID().toString() );
			sources.add( code );
		}
		
		ben.completeEntry();

		JuvenileCasefile casefile = ben.getCasefile();
		String jpoId = casefile.getProbationOfficerId();  //.getUserID();
		OfficerProfile officer = OfficerProfile.find(jpoId);
		String officerLogonId = "";
		if(officer != null){
			officerLogonId = officer.getLogonId();
		}
		// Profile stripping fix task 97536
		//Juvenile juv = casefile.getJuvenile();
		JuvenileCore juv = casefile.getJuvenile();
		
		StringBuffer juvNameBuffer = new StringBuffer();
		String firstName=juv.getFirstName();
		String middleName=juv.getMiddleName();
		String lastName=juv.getLastName();
		if(juv.getLastName()==null){
			lastName="";
		}
		if(juv.getFirstName()==null){
			firstName="";
		}
		if(juv.getMiddleName()==null){
			middleName="";
		}
		else if(juv.getLastName().equals("") && juv.getFirstName().equals("")){
			juvNameBuffer = juvNameBuffer.append(juv.getMiddleName());
		}
		else if(juv.getLastName().equals("") && !juv.getFirstName().equals("")){
			juvNameBuffer = juvNameBuffer.append(", " + juv.getFirstName()).append(" ").append(juv.getMiddleName());
		}
		else if((!juv.getLastName().equals("") && juv.getFirstName().equals(""))){
			juvNameBuffer = juvNameBuffer.append(juv.getLastName());
			if(!juv.getMiddleName().equals("")){
				juvNameBuffer.append(", " + juv.getMiddleName());
			}
		}
		String juvName = juvNameBuffer.toString();
		String casefileId = ben.getCasefileId();

		// task creation
		HashMap map = new HashMap();
		map.put("review", "true");
		map.put("currentAssessment.assessmentId",evt.getAssessmentId());
		Task.createTask(officerLogonId, PDTaskConstants.CREATE_TASK_REVIEW_BENEFITS_ASSESSMENT,this.getTaskTitle(juvName,casefileId), map);
		//taken out for US 14459
		/*JuvenileCasefile myCasefile=JuvenileCasefile.find(evt.getCasefileId());
		if(myCasefile!=null){
			myCasefile.setIsBenefitsAssessmentNeeded(false);
		}*/
   
   }
   
  /**
   * @param juvName
   * @param casefileId
   */
   private String getTaskTitle(String juvName, String casefileId) {
	   StringBuffer title = new StringBuffer("Review Benefits Assessment for Juvenile ");
	   title.append(juvName);
	   title.append(" and Supervision Number: ");
	   title.append(casefileId);
	   return title.toString();
   }
   
  /**
    * @param event
    * @roseuid 436A39B3005A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 436A39B3005C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 436A39B3005E
    */
   public void update(Object anObject) 
   {
    
   }
   
}
