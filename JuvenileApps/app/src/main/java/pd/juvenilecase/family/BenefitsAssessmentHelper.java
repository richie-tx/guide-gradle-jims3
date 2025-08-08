package pd.juvenilecase.family;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentGuardianResponseEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentReviewResponseEvent;
import messaging.juvenilecase.reply.IndividualIncomeDeterminationResponseEvent;
import messaging.juvenilecase.reply.RequestedBenefitsAssessmentResponseEvent;

import pd.codetable.Code;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.km.util.Name;

/**
 *
 */
public class BenefitsAssessmentHelper extends JuvenileFamilyHelper
{

	public static BenefitsAssessmentDetailResponseEvent getBenefitsAssessmentDetailResponseEvent( BenefitsAssessment ben )
	{
	    	// Profile stripping fix task 97538
		//Juvenile juv = ben.getJuvenile();
	    	JuvenileCore juv = ben.getJuvenile();
	    	//
		BenefitsAssessmentDetailResponseEvent reply = new BenefitsAssessmentDetailResponseEvent();
		
		reply.setAssessmentId( ben.getOID().toString() );
		reply.setCasefileId(ben.getCasefileId());
		reply.setJuvenileId( ben.getJuvenileId() );
		reply.setFirstName( juv.getFirstName() );
		reply.setLastName( juv.getLastName() );

		reply.setGuardianId( ben.getGuardianId() );
		reply.setGuardianFirstName( ben.getGuardian().getTheFamilyMember().getFirstName() );
		reply.setGuardianLastName( ben.getGuardian().getTheFamilyMember().getLastName() );
		reply.setGuardianFamilyMemberId(ben.getGuardian().getTheFamilyMemberId());

		reply.setRequestDate( ben.getRequestDate() );
		reply.setEntryDate( ben.getEntryDate() );
   
		reply.setEligibleForMedicaid( ben.isEligibleForMedicaid() );
		reply.setReceivingMedicaid( ben.isReceivingMedicaid() );
		reply.setEligibleForTitleIVe( ben.isEligibleForTitleIVe() );
		reply.setReceivingTitleIVe( ben.isReceivingTitleIVe() );
   
		reply.setLegalResident( ben.isLegalResident() );
		reply.setOneParentIsStepparent( ben.getOneParentIsStepparent() );
   
		reply.setDeathOrAbsence( ben.getDeathOrAbsence() );
		reply.setIncapacityOrDisabilityOfParent( ben.getIncapacityOrDisabilityOfParent() );
		reply.setPrimaryWageEarnerUnderemployment( ben.getPrimaryWageEarnerUnderemployment() );
		reply.setPweWorkedLessThen100Hours( ben.getPweWorkedLessThen100Hours() );
		reply.setPweIncomeLessThanUnderemployedLimit( ben.getPweIncomeLessThanUnderemployedLimit() );
		reply.setPweRelationshipToJuvenileId( ben.getPweRelationshipToJuvenileId() );
		reply.setPweWorkedSteadyLessThan100Hours( ben.getPweWorkedSteadyLessThan100Hours() );
		reply.setPweWorkedIrregularLessThan100HoursAvg( ben.getPweWorkedIrregularLessThan100HoursAvg() );
		reply.setPweGrossMonthlyIncomeForOver100Hours( ben.getPweGrossMonthlyIncomeForOver100Hours() );
		reply.setWasChildLivingWithParent( ben.getWasChildLivingWithParent() );
		reply.setAfdcIncomeLimitsMet( ben.getAfdcIncomeLimitsMet() );
		reply.setAfdcIncomeStepparentsMonthlyGross( ben.getAfdcIncomeStepparentsMonthlyGross() );
		reply.setAfdcIncomeStepparentWorkRelatedExpenses( ben.getAfdcIncomeStepparentWorkRelatedExpenses() );
		reply.setAfdcIncomeStepparentOtherMonthlyIncome( ben.getAfdcIncomeStepparentOtherMonthlyIncome() );
		reply.setAfdcIncomeStepparentMonthyPaymentsToDependent( ben.getAfdcIncomeStepparentMonthyPaymentsToDependent() );
		reply.setAfdcIncomeStepparentMonthyAlimonyChildSupport( ben.getAfdcIncomeStepparentMonthyAlimonyChildSupport() );
		reply.setAfdcIncomeStepparentNoncertifiedCount(ben.getAfdcIncomeStepparentNoncertifiedCount());
		reply.setUnder10KLimit( ben.getUnder10KLimit() );
		reply.setChildMeetsEligibilityCriteria( ben.getChildMeetsEligibilityCriteria() );
		reply.setOrderContainsBestInterestLanguage( ben.getOrderContainsBestInterestLanguage() );
		reply.setResonableEffortsMadeWithin60Days( ben.getResonableEffortsMadeWithin60Days() );
		reply.setOrdersIncludeResponsibilityForCareAndPlacement( ben.getOrdersIncludeResponsibilityForCareAndPlacement() );
		reply.setChildMeetsAFDCAndOrderRequirements( ben.getChildMeetsAFDCAndOrderRequirements() );
		
		reply.setPweRelationshipToJuvenile( ben.getPweRelationshipToJuvenile() );
		reply.setAfdcIncomeCertifiedGroupSize( ben.getAfdcIncomeCertifiedGroupSize() );
		reply.setAfdcIncomeCertifiedGroupParentsSize( ben.getAfdcIncomeCertifiedGroupParentsSize() );
		reply.setAfdcIncomeCertifiedGroupLimit( ben.getAfdcIncomeCertifiedGroupLimit() );
		reply.setAfdcIncomeStepparentCountableEarnedMonthy( ben.getAfdcIncomeStepparentCountableEarnedMonthy() );
		reply.setAfdcIncomeStepparentTotalCountableMonthy( ben.getAfdcIncomeStepparentTotalCountableMonthy() );
		reply.setAfdcIncomeStepparentAllowanceAmount( ben.getAfdcIncomeStepparentAllowanceAmount() );
		reply.setAfdcIncomeStepparentAppliedIncome( ben.getAfdcIncomeStepparentAppliedIncome() );
		reply.setAfdcIncomeTotalMonthy( ben.getAfdcIncomeTotalMonthy() );
		reply.setAfdcIncomeTotalCountable( ben.getAfdcIncomeTotalCountable() );
		
		
		if ( ben.getEligibiltyType() != null )
		{
			reply.setEligibiltyType( ben.getEligibiltyType().getDescription() );
		}
		
		Iterator sources = ben.getSourcesForAFDCInformation().iterator();
		while ( sources.hasNext() )
		{
			Code code = (Code)sources.next();
			reply.getSourcesForAFDCInformation().add( code.getCode() );
		}

		Iterator incDeters = ben.getAFDCIncomeWorksheetItems().iterator();
		while ( incDeters.hasNext() )
		{
			IndividualIncomeDetermination incDeter = (IndividualIncomeDetermination)incDeters.next();
			IndividualIncomeDeterminationResponseEvent incDeterReply = new IndividualIncomeDeterminationResponseEvent();
			
			incDeterReply.setMemberId( incDeter.getMemberId() );
			String gName=incDeter.getName();
			if(gName!=null){
				
				incDeterReply.setName( gName );
			}
			else{
				if(incDeter.getMemberId()!=null && reply.getJuvenileId()!=null && incDeter.getMemberId().equals(reply.getJuvenileId())){
					Name myName=new Name(juv.getFirstName(),juv.getMiddleName(),juv.getLastName());
					incDeterReply.setName(myName.getFormattedName());
				}
				else
					incDeterReply.setName("");
			}
			incDeterReply.setAge( incDeter.getAge() );
			incDeterReply.setRelationshipToJuvenileId( incDeter.getRelationshipToJuvenileId() );
			incDeterReply.setComments( incDeter.getComments() );
			incDeterReply.setIncomeSourceId( incDeter.getIncomeSourceId() );
			incDeterReply.setGrossMonthyIncome( incDeter.getGrossMonthyIncome() );
			
			reply.getAfdcIncomeWorksheetItems().add(incDeterReply);
		}
		
		Iterator reviews = ben.getReviewComments().iterator();
		while ( reviews.hasNext() )
		{
			BenefitsAssessmentReview review = (BenefitsAssessmentReview)reviews.next();
			BenefitsAssessmentReviewResponseEvent reviewEvt = new BenefitsAssessmentReviewResponseEvent();
			
			reviewEvt.setEntryDate( review.getEntryDate() );
			reviewEvt.setComments( review.getComments() );
			reply.getReviewComments().add( reviewEvt );
		}

		reply.setTitleIVeOfficerId( ben.getTitleIVeOfficerId() );
		reply.setTitleIVeOfficerName( ben.getTitleIVeOfficerName() );

		
		return reply;
	}


	public static RequestedBenefitsAssessmentResponseEvent getRequestedBenefitsAssessmentResponseEvent( BenefitsAssessment ben )
	{
	    	// Profile stripping fix task 97538
		//Juvenile juv = ben.getJuvenile();
	    	JuvenileCore juv = ben.getJuvenile();
	    	//
		RequestedBenefitsAssessmentResponseEvent reply = new RequestedBenefitsAssessmentResponseEvent();
			
		reply.setAssessmentId(ben.getOID().toString());
		reply.setJuvenileNumber(ben.getJuvenileId());
		if(juv.getFirstName()!=null)
			reply.setFirstName(juv.getFirstName());
		else
			reply.setFirstName("");
		if(juv.getLastName()!=null)
			reply.setLastName(juv.getLastName());
		else
			reply.setLastName("");
		reply.getGuardians().addAll( BenefitsAssessmentHelper.getGuardiansForJuvenile(juv) );
		reply.setCasefileId(ben.getCasefileId());
		reply.setJpoID(ben.getProbationOfficerId());
		if(ben.getProbationOfficerName()!=null)
			reply.setJpoName(ben.getProbationOfficerName());
		else
			reply.setJpoName("");
		reply.setRequestDate(ben.getRequestDate());
		if(ben.getRequesterName()!=null)
			reply.setRequesterName(ben.getRequesterName());
		else
			reply.setRequesterName("");
		return reply;
	}
	



	//public static Collection getGuardiansForJuvenile( Juvenile juv )
	public static Collection getGuardiansForJuvenile( JuvenileCore juv )
	{
		Collection replys = new ArrayList();
		
		Iterator constellations = juv.getFamilyConstellationList().iterator();
		while ( constellations.hasNext() )
		{
			FamilyConstellation constellation = (FamilyConstellation)constellations.next();
			if ( constellation.isActive() )
			{
				Iterator members = constellation.getFamilyConstellationMembers().iterator();
				while ( members.hasNext() )
				{
					FamilyConstellationMember constellationMember = (FamilyConstellationMember)members.next();
					if ( constellationMember.isGuardian() )
					{
						FamilyMember member = constellationMember.getTheFamilyMember();
						
						BenefitsAssessmentGuardianResponseEvent guardianReply = new BenefitsAssessmentGuardianResponseEvent();
						
						guardianReply.setMemberId( constellationMember.getTheFamilyMemberId().toString() );
						guardianReply.setConstellationMemberId( constellationMember.getOID().toString() );
						guardianReply.setFamilyId( constellation.getOID().toString() );
						if(member.getLastName()!=null)
							guardianReply.setLastName( member.getLastName() );
						else
							guardianReply.setLastName("");
						if(member.getFirstName()!=null)
							guardianReply.setFirstName( member.getFirstName() );
						else
							guardianReply.setFirstName("");
						
						guardianReply.setRelationshipToJuvenile( constellationMember.getRelationshipToJuvenile().getDescription() );
						
						/*Iterator phones = member.getPhoneNumbers().iterator();
						if ( phones.hasNext() )
						{
							try{
								FamilyMemberPhone phone = (FamilyMemberPhone)phones.next();
								guardianReply.setPhoneType(phone.getPhoneType().getDescription());
								guardianReply.setPhoneNumber(phone.getPhoneMaster().getPhoneNumber());
							}
							catch(Exception e){
								// Modify this to plan for a null pointer exception
							}
						}*/
						
						replys.add( guardianReply );
					}
				}
				break;
			}
		}
		return replys;
	}
}
