package ui.juvenilecase.referral.action;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.referral.form.LegacyCourtOrdersForm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.referral.GetLegacyCourtOrderDetailsEvent;
import messaging.referral.reply.LegacyCourtOrderDetailsResponseEvent;

import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileReferralControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DisplayLegacyCourtOrderDetailsAction extends JIMSBaseAction {

	public DisplayLegacyCourtOrderDetailsAction() {
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayCourtOrdersDetail(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LegacyCourtOrdersForm lcoForm = (LegacyCourtOrdersForm) aForm;
		String petitionNum = aRequest.getParameter("petitionNum");
		if (petitionNum == null || petitionNum.equals("")) {
			// some problem
			aMapping.findForward(UIConstants.FAILURE);
		}

		GetLegacyCourtOrderDetailsEvent LegCourtOrdDet = (GetLegacyCourtOrderDetailsEvent) EventFactory
				.getInstance(JuvenileReferralControllerServiceNames.GETLEGACYCOURTORDERDETAILS);
		LegCourtOrdDet.setCourtOrderNumber(lcoForm.getCourtOrderID());

		LegacyCourtOrderDetailsResponseEvent courtOrderDet = (LegacyCourtOrderDetailsResponseEvent) MessageUtil
				.postRequest(LegCourtOrdDet,
						LegacyCourtOrderDetailsResponseEvent.class);

		if (courtOrderDet != null) {
			lcoForm.setAction("view");
			lcoForm.setAngerManagementCounseling(courtOrderDet
					.isAngerManagementCounseling());
			lcoForm.setAnyOtherRulesSetByHCJPD(courtOrderDet
					.isAnyOtherRulesSetByHCJPD());
			lcoForm.setAttorneyFee(courtOrderDet.getAttorneyFee());
			lcoForm.setBirthDate(courtOrderDet.getBirthDate());
			lcoForm.setCavgsConduct(courtOrderDet.isCAvgSConduct());
			lcoForm.setChildRemovedFrom(courtOrderDet.getChildRemovedFrom());
			lcoForm.setChildSupport(courtOrderDet.getChildSupport());
			lcoForm.setCjpoInstructions(courtOrderDet.getCjpoInstructions());
			lcoForm.setCommitmentDate(courtOrderDet.getCommitmentDate());
			lcoForm.setCommittedToTYC(courtOrderDet.isCommittedToTYC());
			lcoForm.setCommunityService(courtOrderDet.getCommunityService());
			lcoForm.setContinuouslyDetainedDate(courtOrderDet
					.getContinuouslyDetainedDate());
			lcoForm.setCourtCost(courtOrderDet.getCourtCost());
			lcoForm.setCourtCostWaived(courtOrderDet.isCourtCostWaived());
			lcoForm.setCourtDate(courtOrderDet.getCourtDate());
			lcoForm.setCourtOrderID(courtOrderDet.getCourtOrderID());
			lcoForm.setCustodyToCJPO(courtOrderDet.isCustodyToCJPO());
			lcoForm.setCustodyToFather(courtOrderDet.isCustodyToFather());
			lcoForm.setCustodyToGuardian(courtOrderDet.isCustodyToGuardian());
			lcoForm.setCustodyToMHMRA(courtOrderDet.isCustodyToMHMRA());
			lcoForm.setCustodyToMother(courtOrderDet.isCustodyToMother());
			lcoForm.setCustodyToOther(courtOrderDet.isCustodyToOther());
			lcoForm.setCustodyToParents(courtOrderDet.isCustodyToParents());
			lcoForm.setCustodyToRelative(courtOrderDet.isCustodyToRelative());
			lcoForm.setCustodyToTDPRS(courtOrderDet.isCustodyToTDPRS());
			lcoForm.setDaysInDetention(courtOrderDet.getDaysInDetention());
			lcoForm.setDeadlyWeapon(courtOrderDet.isDeadlyWeapon());
			lcoForm.setDeadlyWeaponDesc(courtOrderDet.getDeadlyweaponDesc());
			lcoForm.setDeferredSORegistration(courtOrderDet
					.isDeferredSORegistration());
			lcoForm.setDegree(courtOrderDet.getDegree());
			lcoForm.setDelinquentConduct(courtOrderDet.isDelinquentConduct());
			lcoForm.setDenyTDL(courtOrderDet.isDenyTDL());
			lcoForm.setDeterminatePlacement(courtOrderDet
					.isDeterminatePlacement());
			lcoForm.setDeterminateProbation(courtOrderDet
					.isDeterminateProbation());
			lcoForm
					.setDeterminateReason1(courtOrderDet
							.getDeterminateReason1());
			lcoForm
					.setDeterminateReason2(courtOrderDet
							.getDeterminateReason2());
			lcoForm
					.setDeterminateReason3(courtOrderDet
							.getDeterminateReason3());
			lcoForm
					.setDeterminateReason4(courtOrderDet
							.getDeterminateReason4());
			lcoForm
					.setDeterminateReason5(courtOrderDet
							.getDeterminateReason5());
			lcoForm.setDispositionDate(courtOrderDet.getDispositionDate());
			lcoForm.setDrugFreeYouth(courtOrderDet.isDrugFreeYouth());
			lcoForm.setEducationalSpecialist(courtOrderDet
					.isEducationalSpecialist());
			lcoForm.setFamilyManagementCounseling(courtOrderDet
					.isFamilyManagementCounseling());
			lcoForm.setFelony1(courtOrderDet.isFelony1());
			lcoForm.setFelony2(courtOrderDet.isFelony2());
			lcoForm.setFelony3(courtOrderDet.isFelony3());
			lcoForm.setFelonyCapital(courtOrderDet.isFelonyCapital());
			lcoForm.setFelonyStateJail(courtOrderDet.isFelonyStateJail());
			lcoForm.setFireSetters(courtOrderDet.isFireSetters());
			lcoForm.setGangCaseload(courtOrderDet.isGangCaseload());
			lcoForm.setGangWorkshop(courtOrderDet.isGangWorkshop());
			lcoForm.setGedProgram(courtOrderDet.isGedProgram());
			lcoForm.setGjApprovalDate(courtOrderDet.getGjApprovalDate());
			lcoForm.setGjApproved(courtOrderDet.isGjApproved());
			lcoForm.setGjBeyondReasonableDoubt(courtOrderDet
					.isGjBeyondReasonableDoubt());
			lcoForm.setGjChildsAge(courtOrderDet.getGjChildsAge());
			lcoForm.setGjDelinquentConduct(courtOrderDet
					.isGjDelinquentConduct());
			lcoForm.setGjNumber(courtOrderDet.getGjNumber());
			lcoForm.setGjStateReferral(courtOrderDet.isGjStateReferral());
			lcoForm.setGjWaiverDate(courtOrderDet.getGjWaiverDate());
			lcoForm.setHearingTypeCode(courtOrderDet.getHearingTypeCode());
			lcoForm.setHearingTypeDescription(courtOrderDet
					.getHearingTypeDescription());
			lcoForm.setIndividualManagementCounseling(courtOrderDet
					.isIndividualManagementCounseling());
			lcoForm.setInNeedOfRehab(courtOrderDet.isInNeedOfRehab());
			lcoForm.setInNeedOfSupervision(courtOrderDet
					.isInNeedOfSupervision());
			lcoForm.setIntensiveSupervisionProgram(courtOrderDet
					.isIntensiveSupervisionProgram());
			lcoForm.setIntensiveSupervisionProgramTimeFrame(courtOrderDet
					.getIntensiveSupervisionProgramTimeFrame());
			lcoForm.setGjStateReferral(courtOrderDet.isGjStateReferral());
			lcoForm.setJointCustody(courtOrderDet.isJointCustody());
			lcoForm.setJudgementDate(courtOrderDet.getJudgementDate());
			lcoForm.setJudgementTYCDate(courtOrderDet.getJudgementTYCDate());
			lcoForm.setJudgeName(courtOrderDet.getJudgeName());
			lcoForm.setJuvenileCourt(courtOrderDet.getJuvenileCourt());
			lcoForm.setJuvenileCourtName(courtOrderDet.getJuvenileCourtName());
			lcoForm.setJuvenileOffenseCode(courtOrderDet
					.getJuvenileOffenseCode());
			lcoForm.setJuvenileOffenseCodeDescription(courtOrderDet
					.getJuvenileOffenseCodeDescription());
			lcoForm.setJuvenileProbationPlacement(courtOrderDet
					.isJuvenileProbationPlacement());
			lcoForm.setLetterOfApology(courtOrderDet.isLetterOfApology());
			lcoForm.setMentor(courtOrderDet.isMentor());
			lcoForm.setMhmraAssessment(courtOrderDet.isMhmraAssessment());
			lcoForm.setMisdemeanorA(courtOrderDet.isMisdemeanorA());
			lcoForm.setMisdemeanorB(courtOrderDet.isMisdemeanorB());
			lcoForm.setMisdemeanorC(courtOrderDet.isMisdemeanorC());
			lcoForm.setNoContactCoActors(courtOrderDet.isNoContactCoActors());
			lcoForm.setNoContactComplainant(courtOrderDet
					.isNoContactComplainant());
			lcoForm.setNoContactGangMembers(courtOrderDet
					.isNoContactGangMembers());
			lcoForm.setNoDelinquentConduct(courtOrderDet
					.isNoDelinquentConduct());
			lcoForm.setNoDispositionNecessary(courtOrderDet
					.isNoDispositionNecessary());
			lcoForm.setOffenseDate(courtOrderDet.getOffenseDate());
			lcoForm.setOtherRulesSetByHCJPD(courtOrderDet
					.getOtherRulesSetByHCJPD());
			lcoForm.setParentGuardianCustodian(courtOrderDet
					.getParentGuardianCustodian());
			lcoForm.setPeerPressure(courtOrderDet.isPeerPressure());
			lcoForm.setPlacementOutsideHome(courtOrderDet
					.isPlacementOutsideHome());
			lcoForm.setPreviousRulesInEffect(courtOrderDet
					.isPreviousRulesInEffect());
			lcoForm.setPriorRestitutionEnded(courtOrderDet
					.isPriorRestitutioniEnded());
			lcoForm.setProbationBeginDate(courtOrderDet.getProbationBeginDate());
			lcoForm.setProbationEndDate(courtOrderDet.getProbationEndDate());
			lcoForm.setProbationExtended(courtOrderDet.isProbationExtended());
			lcoForm.setProbationRevoked(courtOrderDet.isProbationRevoked());
			lcoForm.setRandomDrugScreens(courtOrderDet.isRandomDrugScreens());
			lcoForm.setReasonableEffortsMade(courtOrderDet
					.isReasonableEffortsMade());
			lcoForm.setRespondentAttorneyName(courtOrderDet
					.getRespondentAttorneyName());
			lcoForm.setResponsiblePartyAttorneyFee(courtOrderDet
					.getResponsiblePartyAttorneyFee());
			lcoForm.setResponsiblePartyChildSupport(courtOrderDet
					.getResponsiblePartyChildSupport());
			lcoForm.setResponsiblePartyCourtCost(courtOrderDet
					.getResponsiblePartyCourtCost());
			lcoForm.setResponsiblePartyRestitution(courtOrderDet
					.getResponsiblePartyRestitution());
			lcoForm.setResponsiblePartyRestitution2(courtOrderDet
					.getResponsiblePartyRestitution2());
			lcoForm.setResponsiblePartySupervisoryFee(courtOrderDet
					.getResponsiblePartySupervisoryFee());
			lcoForm.setRestitutionAmountOrdered(courtOrderDet
					.getRestitutionAmountOrdered());
			lcoForm.setRestitutionAmountOrdered2(courtOrderDet
					.getRestitutionAmountOrdered2());
			lcoForm.setRestitutionPayeeCity(courtOrderDet
					.getRestitutionPayeeCity());
			lcoForm.setRestitutionPayeeCity2(courtOrderDet
					.getRestitutionPayeeCity2());
			lcoForm.setRestitutionPayeeFirstName(courtOrderDet
					.getRestitutionPayeeFirstName());
			lcoForm.setRestitutionPayeeFirstName2(courtOrderDet
					.getRestitutionPayeeFirstName2());
			lcoForm.setRestitutionPayeeLastName(courtOrderDet
					.getRestitutionPayeeLastName());
			lcoForm.setRestitutionPayeeLastName2(courtOrderDet
					.getRestitutionPayeeLastName2());
			lcoForm.setRestitutionPayeePhoneNum(courtOrderDet
					.getRestitutionPayeePhoneNum());
			lcoForm.setRestitutionPayeePhoneNum2(courtOrderDet
					.getRestitutionPayeePhoneNum2());
			lcoForm.setRestitutionPayeeState(courtOrderDet
					.getRestitutionPayeeState());
			lcoForm.setRestitutionPayeeState2(courtOrderDet
					.getRestitutionPayeeState2());
			lcoForm.setRestitutionPayeeStreetAddress(courtOrderDet
					.getRestitutionPayeeStreetAddress());
			lcoForm.setRestitutionPayeeStreetAddress2(courtOrderDet
					.getRestitutionPayeeStreetAddress2());
			lcoForm.setRestitutionPayeeZipCode(courtOrderDet
					.getRestitutionPayeeZipCode());
			lcoForm.setRestitutionPayeeZipCode2(courtOrderDet
					.getRestitutionPayeeZipCode2());
			lcoForm.setRestitutionPaymentTimeFrame(courtOrderDet
					.getRestitutionPaymentTimeFrame());
			lcoForm.setRestitutionPaymentTimeFrame2(courtOrderDet
					.getRestitutionPaymentTimeFrame2());
			lcoForm.setRestitutionStartDate(courtOrderDet
					.getRestitutionStartDate());
			lcoForm.setRestitutionStartDate2(courtOrderDet
					.getRestitutionStartDate2());
			lcoForm.setRestitutionTotal(courtOrderDet.getRestitutionTotal());
			lcoForm.setRestitutionTotal2(courtOrderDet.getRestitutionTotal2());
			lcoForm.setSexOffenderBloodSample(courtOrderDet
					.isSexOffenderBloodSample());
			lcoForm.setSexOffenderCounseling(courtOrderDet
					.isSexOffenderCounseling());
			lcoForm.setSexOffenderPolygraph(courtOrderDet
					.isSexOffenderPolygraph());
			lcoForm.setSexOffenderRegistration(courtOrderDet
					.isSexOffenderRegistration());
			lcoForm.setSexOffenderRegistrationNonPublic(courtOrderDet
					.isSexOffenderRegistrationNonPublic());
			lcoForm.setStateAttorneyName(courtOrderDet.getStateAttorneyName());
			lcoForm.setSupervisoryFee(courtOrderDet.getSupervisoryFee());
			lcoForm.setTdcOutreach(courtOrderDet.isTdcOutreach());
			lcoForm.setThumbprintOrdered(courtOrderDet.isThumbprintOrdered());
			lcoForm.setTitleIIIChild(courtOrderDet.isTitleIIIChild());
			lcoForm.setTxDLRestrictions(courtOrderDet.getTxDLRestrictions());
			lcoForm.setTycDetention(courtOrderDet.isTycDetention());
			lcoForm.setTycSentenceLength(courtOrderDet.getTycSentenceLength());
			lcoForm.setViolatedCourtOrders(courtOrderDet.isViolatedCourtOrders());
			lcoForm.setWeekdayCurfewTimes(courtOrderDet
							.getWeekdayCurfewTimes());
			lcoForm.setWeekendCurfewHours(courtOrderDet.getWeekendCurfewHours());

		}

		String forward = null;
		// lcoForm.setPetitionNum("200600800J");
		forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "displayCourtOrdersDetail");
	}
}
