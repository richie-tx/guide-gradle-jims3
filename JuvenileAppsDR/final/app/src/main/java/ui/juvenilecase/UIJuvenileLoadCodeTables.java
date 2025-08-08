/*
 * Created on Sep 26, 2005
 *
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.ResidentialExitPlanForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UIJuvenileLoadCodeTables
{

		static private UIJuvenileLoadCodeTables instance = null;
		static private List abuseTypeList;
		static private List abuseLevelList;
		static private List relationshipToJuvenileList;
		static private List activeRelationshipToJuvenileList;
		static private List sexList;
		static private List nationalityList;
		static private List languageList;
		static private List raceList;
		static private List originalRaceList;
		static private List ethnicityList;
		static private List causeOfDeathList;
		static private List inHomeStatusList;
		static private List involvementLevelList;
		static private List stateList;
		static private List driversLicenseClassList;
		static private List traitList;
		static private List memberTraitList;
		static private List traitStatusList;
		static private List traitLevelList;
		
		static private List maritalStatusList;
		static private List streetTypeList;
		static private List streetNumSuffixList;
		static private List addressTypeList;
		static private List countyList;
		static private List isUSCitizenList;
		static private List contactTypeList;
		static private List employmentStatusList;
		static private List salaryRateList;
		static private List benefitEligibilityList;
		static private List insuranceTypeList;
		static private List informationBasisList;
		
		static private List interviewTypeList;
		static private List recordsInventoryList;
		static private List interviewQuestionCategory;
		
		// Casefile Closing related
		static private List hearingTypeList;
		static private List recommendedCourtDispositionTypeList;
		static private List supervisionOutcomesList;
		static private List activeSupervisionOutcomesList;
		static private List facilityList;
		static private List facilityReleaseReasonList;
		static private List levelOfCareList;
		static private List permanencyPlanList;
		
		static private List youthLivesWithList;
		
		/**
		 * <p>name: getInstance description: Other classes call this method to get the LoadManageDepartmentCodeTables
		 * @return Instance
		 */
		public synchronized static UIJuvenileLoadCodeTables getInstance(){
		   if (instance == null) {
			  instance = new UIJuvenileLoadCodeTables();
		   }
		   return instance;
		}


		/** <p>name: LoadManageDepartmentCodeTables description: constructor 
		 */
		private UIJuvenileLoadCodeTables(){
		   this.load();
		}

		/** 
		 * @description: loads the codeTables
		 */
		private void load(){
			boolean sort=true; 
			abuseLevelList=CodeHelper.getCodes(PDCodeTableConstants.ABUSE_LEVEL,sort);
			abuseTypeList=CodeHelper.getCodes(PDCodeTableConstants.ABUSE_TYPE,sort);
			relationshipToJuvenileList=CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE,sort);
			activeRelationshipToJuvenileList = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.RELATIONSHIP_JUVENILE,sort);
			sexList=CodeHelper.getCodes(PDCodeTableConstants.SEX,sort);
			nationalityList=CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH,sort);
			languageList=CodeHelper.getCodes(PDCodeTableConstants.LANGUAGE,sort);
			raceList=CodeHelper.getCodes(PDCodeTableConstants.RACE,sort);
			originalRaceList=CodeHelper.getCodes(PDCodeTableConstants.RACE,sort);
			ethnicityList=CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_ETHNICITY,sort);
			stateList=CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,sort);
			driversLicenseClassList=CodeHelper.getCodes(PDCodeTableConstants.DRIVERS_LICENSE_CLASS,sort);
			traitList=CodeHelper.getCodes(PDCodeTableConstants.FAMILY_TRAIT,sort);			
			traitStatusList=CodeHelper.getCodes(PDCodeTableConstants.FAMILY_TRAIT_STATUS,sort);
			memberTraitList=CodeHelper.getCodes(PDCodeTableConstants.MEMBER_TRAIT_TYPE,sort);
			traitLevelList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.FAMILY_TRAIT_LEVEL,sort);//ER changes 11009
			maritalStatusList=CodeHelper.getCodes(PDCodeTableConstants.MARITAL_STATUS,sort);
			streetTypeList=CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,sort);
			streetNumSuffixList=CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX,sort);
			countyList=CodeHelper.getCodes(PDCodeTableConstants.COUNTY,sort);
			isUSCitizenList = CodeHelper.getCodes(PDCodeTableConstants.ISUSCITIZEN,sort);
			addressTypeList=CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE,sort);
			contactTypeList=CodeHelper.getCodes(PDCodeTableConstants.PHONE_TYPE,sort);
			employmentStatusList=CodeHelper.getCodes(PDCodeTableConstants.EMPLOYMENT_STATUS,sort);
			salaryRateList=CodeHelper.getCodes(PDCodeTableConstants.SALARY_RATE,sort);
			benefitEligibilityList=CodeHelper.getCodes(PDCodeTableConstants.MEMBER_BENEFIT_ELIGIBILITY_TYPE,sort);
			insuranceTypeList=CodeHelper.getCodes(PDCodeTableConstants.INSURANCE_TYPE,sort);
			causeOfDeathList=CodeHelper.getCodes(PDCodeTableConstants.CAUSE_OF_DEATH,sort);
			//inHomeStatusList=CodeHelper.getCodes(PDCodeTableConstants.AGENCY_ACCESS_TYPE);
			inHomeStatusList=new ArrayList();
			involvementLevelList=CodeHelper.getCodes(PDCodeTableConstants.INVOLVEMENT_LEVEL,sort);
			// Court Closing lists
			recommendedCourtDispositionTypeList=CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,sort);
			supervisionOutcomesList=CodeHelper.getCodes(PDCodeTableConstants.SUPERVISIONOUTCOME,sort);
			activeSupervisionOutcomesList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISIONOUTCOME,sort);
			facilityList=CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,sort);
			facilityReleaseReasonList=CodeHelper.getCodes(PDCodeTableConstants.FACILITY_RELEASON_REASON,sort);
			levelOfCareList=CodeHelper.getCodes(PDCodeTableConstants.LEVELOFCARE,sort);
			permanencyPlanList=CodeHelper.getCodes(PDCodeTableConstants.PERMANENCYPLAN,sort);
			
			//Juvenile Interview lists 
			interviewTypeList = CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_TYPE, true);
			recordsInventoryList = CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_RECORD, true);
			interviewQuestionCategory = CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_QUESTION_CATEGORY);
			youthLivesWithList = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.YOUTH_LIVES_WITH, true);
			
		}
		
			/** 
			 * @description: loads the codeTables
			 * @param departmentForm 
			 */
			public void setCommonAppForm(CommonAppForm aForm){
				if(aForm.isListsSet())
								return;
				aForm.setRecommendedCourtDispositionTypeList(recommendedCourtDispositionTypeList);
				
				CommonAppForm.setRelationshipToJuvList(relationshipToJuvenileList);
				CommonAppForm.setAbuseLevelList(abuseLevelList);
				CommonAppForm.setInformationBasisLevels(traitStatusList);
	//			aForm.setAbuseTypeList(abuseTypeList);
				CommonAppForm.setHearingTypeList(hearingTypeList);
				aForm.setListsSet(true);
			}
	
			/** 
			 * @description: loads the codeTables
			 * @param departmentForm 
			 */
			public void setJuvenileCasefileClosingForm(CasefileClosingForm aForm){
				if(aForm.isListsSet())
								return;
				aForm.setSupervisionOutcomesList(supervisionOutcomesList);
				aForm.setActiveSupervisionOutcomesList(activeSupervisionOutcomesList);
				
				List temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
				List temp2 = new ArrayList();
				List temp3 = new ArrayList();
				for (int x =0; x< temp1.size(); x++)
				{
					JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) temp1.get(x);
					if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(joscre.getParentId()) ) {
						temp2.add(joscre);
					}
					if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId()) ) {
						temp3.add(joscre);
					}
				}
				aForm.setOptionalSupervisionOutcomeDescList(temp2);
				aForm.setRequiredSupervisionOutcomeDescList(temp3);
				temp1 = null;
				temp2 = null;
				temp3 = null;				
				
				aForm.setListsSet(true);
			}
			
			/** 
			 * @description: loads the codeTables
			 * @param departmentForm 
			 */
			public void setResidentialExitPlanForm(ResidentialExitPlanForm aForm){
				aForm.setFacilityList(facilityList);
				aForm.setFacilityReleaseReasonList(facilityReleaseReasonList);
				aForm.setLevelOfCareList(levelOfCareList);
				aForm.setPermanencyPlanList(permanencyPlanList);
				aForm.setListsSet(true);
			}
	
		/** 
		 * @description: loads the codeTables
		 * @param departmentForm 
		 */
		public void setJuvenileFamilyForm(JuvenileFamilyForm aForm){
			if(aForm.isListsSet())
				return;
			aForm.setCauseOfDeathList(causeOfDeathList);
			aForm.setRelationshipToJuvenileList(relationshipToJuvenileList);
			aForm.setActiveRelationshipToJuvenileList(activeRelationshipToJuvenileList);
			aForm.setSexList(sexList);
			aForm.setStateList(stateList);
			aForm.setTraitLevelList(traitLevelList);
			aForm.setTraitStatusList(traitStatusList);
			aForm.setTraitDescList(traitList);
			aForm.setInvolvementLevelList(involvementLevelList);
			aForm.setYouthLivesWith(youthLivesWithList);
			aForm.setListsSet(true);
			
		}
		
		public void setJuvenileMemberForm(JuvenileMemberForm aForm){
			if(aForm.isListsSet())
							return;
			aForm.setCauseOfDeathList(causeOfDeathList);
			aForm.setDriversLicenseClassList(driversLicenseClassList);
			aForm.setEthnicityList(ethnicityList);
			aForm.setInvolvementLevelList(involvementLevelList);
			aForm.setLanguageList(languageList);
			aForm.setMaritalStatusList(maritalStatusList);
			aForm.setNationalityList(nationalityList);
			aForm.setRelationshipToJuvenileList(relationshipToJuvenileList);
			aForm.setSexList(sexList);
			aForm.setStateList(stateList);
			aForm.setTraitLevelList(traitLevelList);
			aForm.setTraitStatusList(traitStatusList);
			aForm.setTraitDescList(memberTraitList);
			aForm.setAddressTypeList(addressTypeList);
			aForm.setStreetTypeList(streetTypeList);
			aForm.setStreetNumSuffixList(streetNumSuffixList);
			aForm.setCountyList(countyList);
			aForm.setContactTypeList(contactTypeList);
			aForm.setEmploymentStatusList(employmentStatusList);
			aForm.setSalaryRateList(salaryRateList);
			aForm.setBenefitEligibilityList(benefitEligibilityList);
			aForm.setInsuranceTypeList(insuranceTypeList);
			aForm.setIsUSCitizenList(isUSCitizenList);
			aForm.setListsSet(true);
			
		}
		
	public void setJuvenileMemberSearchForm(JuvenileMemberSearchForm aForm){
			if(aForm.isListsSet())
						return;
				aForm.setRelationshipList(relationshipToJuvenileList);
				aForm.setSexList(sexList);
				aForm.setListsSet(true);
			}
	public void setJuvenileMainForm(JuvenileMainForm aForm){
	
	}
	
	public void setJuvenileInterviewForm(JuvenileInterviewForm aForm)
	{
		if(aForm.isListsSet())
			return;
		
		
		
		aForm.setListsSet(true);
		
	}
	
	public void setScheduleNewEventForm(ScheduleNewEventForm aForm)
	{		
		if (!aForm.getCurrentService().isInterviewListSet()){
			aForm.getCurrentService().setInterviewTypeList(interviewTypeList);				
			aForm.getCurrentService().setStreetTypeList(streetTypeList);
			aForm.getCurrentService().setAddressTypeList(addressTypeList);
			aForm.getCurrentService().setCountyList(countyList);
			aForm.getCurrentService().setStateList(stateList);
			aForm.getCurrentService().setInterviewListSet(true);
			aForm.getCurrentService().setStreetNumSuffixList(streetNumSuffixList);
		}
	}
}// END_CLASS
