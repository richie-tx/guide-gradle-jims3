package pd.juvenilecase.referral.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import messaging.referral.GetLegacyCourtOrderDetailsEvent;
import messaging.referral.reply.LegacyCourtOrderDetailsResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetLegacyCourtOrderDetailsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetLegacyCourtOrderDetailsEvent event = (GetLegacyCourtOrderDetailsEvent) anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		String courtOrderID = event.getCourtOrderID();
		
		if (courtOrderID!=null){
			
			Statement stmt = null;
			Connection con = null;
			ResultSet rs = null;

			String query = 	"SELECT * FROM [CourtOrderDB].[dbo].[dbo_tblHistorical] " +
				"where [CourtOrder_ID]="+courtOrderID+";";
			
			//System.err.println(query);
			
			try {

				con = GetLegacyCourtOrdersCommand.getMySqlConnection();

				stmt = con.createStatement();

				rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					
					LegacyCourtOrderDetailsResponseEvent response = 
						new LegacyCourtOrderDetailsResponseEvent();
					
					response.setCourtOrderID(rs.getString("CourtOrder_ID"));
					response.setCourtDate(DateUtil.stringToDate(rs.getString("CourtDate"),"yyyyMMdd"));
					response.setJuvenileCourt(rs.getString("Court"));
					response.setHearingTypeDescription(rs.getString("Type"));
					response.setBirthDate(rs.getTimestamp("BirthDate"));
					response.setJudgementDate(rs.getTimestamp("JudgementDate"));
					response.setDispositionDate(rs.getTimestamp("DispositionDate")); 
					response.setJudgementTYCDate(rs.getTimestamp("JudgementDate_TYC")); 
					response.setCommitmentDate(rs.getTimestamp("CommitmentTYC_Date"));
					response.setStateAttorneyName(rs.getString("StateAttorney"));
					response.setRespondentAttorneyName(rs.getString("RespondentAttorney"));
					response.setProbationBeginDate(rs.getTimestamp("ProbationBegins"));
					response.setProbationEndDate(rs.getTimestamp("ProbationEnds"));
					response.setJuvenileOffenseCodeDescription(rs.getString("Offense1"));
					response.setOffenseDate(rs.getTimestamp("Offense1Date"));
					response.setTechnicalViolation(rs.getBoolean("TechnicalViolation"));				
					
					response.setMisdemeanorA(rs.getBoolean("MisdemeanorA"));
					response.setMisdemeanorB(rs.getBoolean("MisdemeanorB"));
					response.setMisdemeanorC(rs.getBoolean("MisdemeanorC"));
					response.setFelony1(rs.getBoolean("Felony1st"));
					response.setFelony2(rs.getBoolean("Felony2nd"));
					response.setFelony3(rs.getBoolean("Felony3rd"));
					response.setFelonyStateJail(rs.getBoolean("FelonySJ"));
					response.setFelonyCapital(rs.getBoolean("FelonyCapital"));
					
					response = setDegree(response); //sets the value of "Degree" based on the previous 8 fields
					
					response.setLetterOfApology(rs.getBoolean("SR_LetterOfApology"));
					response.setSexOffenderRegistrationNonPublic(rs.getBoolean("SR_RegisterAsSexOffender_NonPublic"));
					response.setDelinquentConduct(rs.getBoolean("ChildFoundtoHaveEngagedInDelinquentConduct"));
					response.setNoDelinquentConduct(rs.getBoolean("ChildFoundNotToHaveEngagedInDelinquentConduct"));
					response.setInNeedOfSupervision(rs.getBoolean("ChildFoundtoBeInNeedOfSupervision"));
					response.setInNeedOfRehab(rs.getBoolean("ChildInNeedOfRehibilitation")); //misspelled in the database
					response.setProbationRevoked(rs.getBoolean("ChildsprobationIsRevoked"));
					response.setTitleIIIChild(rs.getBoolean("TitleIIIChild"));
					response.setReasonableEffortsMade(rs.getBoolean("ReasonableEffortsWereMade"));
					response.setPlacementOutsideHome(rs.getBoolean("PlacementOutsideTheHome"));
					response.setDeadlyWeapon(rs.getBoolean("DeadlyWeapon_IfMarkedDescribeTypeOfWeapon"));
					response.setDeadlyweaponDesc(rs.getString("DescribeWeapon"));
					response.setCommittedToTYC(rs.getBoolean("ChildCommittedTo_TYC"));
					response.setNoDispositionNecessary(rs.getBoolean("NoDispositionNecessary"));
					response.setCustodyToParents(rs.getBoolean("CustodyToParents"));
					response.setCustodyToMother(rs.getBoolean("CustodyToMother"));
					response.setCustodyToFather(rs.getBoolean("CustodyToFather"));
					response.setCustodyToRelative(rs.getBoolean("CustodyToRelative"));
					response.setCustodyToCJPO(rs.getBoolean("CustodyToCJPO"));
					response.setJointCustody(rs.getBoolean("JointCustody"));
					response.setCustodyToMHMRA(rs.getBoolean("CustodyToMHMRA"));
					response.setCustodyToGuardian(rs.getBoolean("CustodyToGuardian_Custodian"));
					response.setCustodyToOther(rs.getBoolean("CustodyToOther"));
					response.setParentGuardianCustodian(rs.getString("NameOfParent_GuardianOrCustodian"));
					response.setCommunityService(rs.getString("CommunityService_Resitution_HoursAndTerms")); //misspelled in database
					response.setResponsiblePartyAttorneyFee(rs.getString("ResponsiblePartyAttorneyFees"));
					response.setChildSupport(rs.getString("ChildSupportAmountAndPayeeNameAddress"));
					response.setTxDLRestrictions(rs.getString("TexasDriversLicenseRestrictions"));
					response.setThumbprintOrdered(rs.getBoolean("ChildsThumbprintOrdered"));
				    response.setJuvenileProbationPlacement(rs.getBoolean("PlacedWithJuvProbationForPlacement"));				          
				    response.setRestitutionStartDate(rs.getTimestamp("RestStartDate"));
				    response.setRestitutionAmountOrdered(rs.getString("RestAmtOrd"));    
				    response.setRestitutionPaymentTimeFrame(rs.getString("RestPymtTimeframe"));      
				    response.setCAvgSConduct(rs.getBoolean("SR_C_Avg_S_Conduct"));   
					response.setRandomDrugScreens(rs.getBoolean("SR_RandomDrugScreens"));
					response.setFireSetters(rs.getBoolean("SR_Firesetters"));
				    response.setTdcOutreach(rs.getBoolean("SR_TDC_Outreach"));
				    response.setGedProgram(rs.getBoolean("SR_GED_Program"));
				    response.setPeerPressure(rs.getBoolean("SR_PeerPressure"));
				    response.setMentor(rs.getBoolean("SR_Mentor"));      
				    response.setIndividualManagementCounseling(rs.getBoolean("SR_IndMgmtCounseling"));
				    response.setFamilyManagementCounseling(rs.getBoolean("SR_FamMgmtCounseling"));
				    response.setAngerManagementCounseling(rs.getBoolean("SR_AngerMgmtCounseling"));
				    response.setSexOffenderCounseling(rs.getBoolean("SR_SexOffenderCounseling"));
				    response.setSexOffenderRegistration(rs.getBoolean("SR_RegisterAsSexOffender"));
				    response.setNoContactComplainant(rs.getBoolean("SR_NoContact_Complainant"));
				    response.setNoContactCoActors(rs.getBoolean("SR_NoContact_CoActors"));
					response.setNoContactGangMembers(rs.getBoolean("SR_NoContact_GangMembers")); 
					response.setPreviousRulesInEffect(rs.getBoolean("AllPreviousRulesRemainInEffect"));   
					response.setDrugFreeYouth(rs.getBoolean("SR_DrugFreeYouth"));      
					response.setCustodyToTDPRS(rs.getBoolean("CustodyToTDPRS"));
				    response.setEducationalSpecialist(rs.getBoolean("SR_EducationalSpecialist"));    
				    response.setProbationExtended(rs.getBoolean("ProbationExtended"));
				    response.setViolatedCourtOrders(rs.getBoolean("DeclaredtoHaveViolatedtheOrdersOfTheCourt"));
				    response.setGangCaseload(rs.getBoolean("SR_GangCaseload"));
				    response.setLetterOfApology(rs.getBoolean("SR_LetterOfApology"));
					response.setSexOffenderBloodSample(rs.getBoolean("SexOffenderBloodSample"));
				    response.setDeterminateProbation(rs.getBoolean("DeterminateDisposition_Probation"));
				    response.setDeterminatePlacement(rs.getBoolean("DeterminateDisposition_ProbationPlacementOutsideHome"));
				    response.setStatusOffense(rs.getBoolean("StatusOffense"));
					response.setDeterminateReason1(rs.getString("DeterminateDisPosition_SpecifyReason0"));	      
					response.setDeterminateReason2(rs.getString("DeterminateDisPosition_SpecifyReason1"));
					response.setDeterminateReason3(rs.getString("DeterminateDisPosition_SpecifyReason2"));
					response.setDeterminateReason4(rs.getString("DeterminateDisPosition_SpecifyReason3"));
					response.setDeterminateReason5(rs.getString("DeterminateDisPosition_SpecifyReason4"));			      
					response.setMhmraAssessment(rs.getBoolean("SR_MhmraAssessment"));
				    response.setCourtCostWaived(rs.getBoolean("CourtCostWaived_Determinant"));
				    response.setAnyOtherRulesSetByHCJPD(rs.getBoolean("AnyOtherRulesSetByHCJPD"));			  
				    response.setChildRemovedFrom(rs.getString("ChildRemovedFrom"));      
					response.setSexOffenderPolygraph(rs.getBoolean("SR_SexOffenderPolygraph"));
					response.setSexOffenderRegistrationNonPublic(rs.getBoolean("SR_RegisterAsSexOffender_NonPublic"));		    
					response.setDaysInDetention(rs.getInt("DeterminateDisposition_TYC_NoOfDaysInDetention"));
					response.setDenyTDL(rs.getBoolean("DPS_ToDenyIssuanceOfTDL"));
					response.setGjApprovalDate(rs.getTimestamp("GJ_ApproveDate"));
					response.setGjApproved(rs.getBoolean("GJ_DidApprove"));
					response.setGjBeyondReasonableDoubt(rs.getBoolean("GJ_BeyondReasonableDoubt"));
					response.setGjChildsAge(rs.getString("GJ_ChildsAge"));
					response.setGjStateReferral(rs.getBoolean("GJ_StRefPet"));
					response.setGjWaiverDate(rs.getTimestamp("GJ_WaiverDate"));
					response.setAttorneyFee(rs.getString("AttorneyFeeAmountAndPayeeNameAddress"));
					response.setCjpoInstructions(rs.getString("CJPO_Referral"));
					response.setContinuouslyDetainedDate(rs.getTimestamp("DeterminateDisposition_TYC_InitialDetentionDate"));
					response.setCourtCost(rs.getString("CourtCostAmountAndPayeeNameAddress"));
					response.setGangWorkshop(rs.getBoolean("SR_Gang"));
					response.setGjDelinquentConduct(rs.getBoolean("GJ_PetAlleged"));
					response.setGjNumber(rs.getString("GJ_Name"));
					response.setDeferredSORegistration(rs.getBoolean("SR_Tutoring"));
					response.setIntensiveSupervisionProgram(rs.getBoolean("SOS"));
					response.setIntensiveSupervisionProgramTimeFrame(rs.getString("SOS_Timeframe"));
					response.setOtherRulesSetByHCJPD(rs.getString("SR_Other"));
					response.setResponsiblePartyCourtCost(rs.getString("ResponsiblePartyCourtCosts"));
					response.setResponsiblePartyChildSupport(rs.getString("ResponsiblePartyChildSupport"));
					response.setResponsiblePartyRestitution(rs.getString("ResponsiblePartyRest"));
					response.setResponsiblePartyRestitution2(rs.getString("ResponsiblePartyRest2"));
					response.setTycDetention(rs.getBoolean("DeterminateDisposition_TYC"));
					response.setResponsiblePartyCommunityService(rs.getString("ResponsiblePartyCommunityService"));
					response.setSupervisoryFee(rs.getString("SupervisoryFeeAmountAndPayeeNameAddress"));
					response.setResponsiblePartySupervisoryFee(rs.getString("ResponsiblePartySupervisoryFees"));
					response.setWeekdayCurfewTimes(rs.getString("ROP_TimeInSun_Th"));
					response.setWeekendCurfewHours(rs.getString("ROP_TimeInFri_Sat"));
					response.setPriorRestitutioniEnded(rs.getBoolean("PriorOrders"));	
					response.setTycSentenceLength(rs.getString("DeterminateDisposition_TYC_TimeToBeServed"));
		
					response.setRestitutionPayeeFirstName(rs.getString("RestitutionAmountAndPayeeNameAddress"));				
					response.setRestitutionPayeeLastName(rs.getString("RestitutionPayeeLastName"));
					response.setRestitutionPayeePhoneNum(rs.getString("RestitutionPayeePhone"));
					response.setRestitutionPayeeCity(rs.getString("RestitutionPayeeCity"));
					response.setRestitutionPayeeState(rs.getString("RestitutionPayeeState"));
					response.setRestitutionAmountOrdered(rs.getString("RestAmtOrd"));
					response.setRestitutionPayeeStreetAddress(rs.getString("RestitutionPayeeAddress"));
					response.setRestitutionPayeeZipCode(rs.getString("RestitutionPayeeZip"));
					response.setRestitutionTotal(rs.getString("RestTotalAmount"));
					response.setRestitutionPaymentTimeFrame(rs.getString("RestPymtTimeframe"));
					response.setRestitutionStartDate(rs.getTimestamp("RestStartDate"));
					
					response.setRestitutionPayeeFirstName2(rs.getString("RestitutionAmountAndPayeeNameAddress2"));
					response.setRestitutionPayeeLastName2(rs.getString("RestitutionPayeeLastName2"));
					response.setRestitutionPayeePhoneNum2(rs.getString("RestitutionPayeePhone2"));
					response.setRestitutionPayeeCity2(rs.getString("RestitutionPayeeCity2"));
					response.setRestitutionPayeeState2(rs.getString("RestitutionPayeeState2"));
					response.setRestitutionAmountOrdered2(rs.getString("RestAmtOrd2"));
					response.setRestitutionPayeeStreetAddress2(rs.getString("RestitutionPayeeAddress2"));
					response.setRestitutionPayeeZipCode2(rs.getString("RestitutionPayeeZip2"));
					response.setRestitutionTotal2(rs.getString("RestTotalAmount2"));
					response.setRestitutionPaymentTimeFrame2(rs.getString("RestPymtTimeframe2"));
					response.setRestitutionStartDate2(rs.getTimestamp("RestStartDate2"));
					
					dispatch.postEvent(response);
					
				}
			} catch (Exception e) {

				e.printStackTrace();
			} finally {

				try {
					stmt.close();
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			
		}
		
		
		
	}

	
	private LegacyCourtOrderDetailsResponseEvent setDegree (LegacyCourtOrderDetailsResponseEvent response){
		
		if (response.isMisdemeanorA())
			response.setDegree("Misdemeanor A");
		else if (response.isMisdemeanorB())
			response.setDegree("Misdemeanor B");
		else if (response.isMisdemeanorC())
			response.setDegree("Misdemeanor C");
		else if (response.isFelony1())
			response.setDegree("Felony 1st");
		else if (response.isFelony2())
			response.setDegree("Felony 2nd");
		else if (response.isFelony3())
			response.setDegree("Felony 3rd");
		else if (response.isFelonyCapital())
			response.setDegree("Felony Capital");
		else if (response.isFelonyStateJail())
			response.setDegree("Felony State Jail");
		
		return response;
	}
	
	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
