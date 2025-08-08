package pd.juvenilecase;

import messaging.juvenilecase.GetJuvenileCasefileRiskNeedsLevelEvent;

/**
 * @stereotype control
 */
public class JuvenileCaseController
{

    /**
     * @roseuid 4382191F000F
     */
    public JuvenileCaseController()
    {

    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02AF
     */
    public void getJuvenileCasefile(String supervisionNumber)
    {

    }
    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02AF
     */
    public void getJuvenileByCasefileActivity()
    {

    }

    /**
     * @stereotype design
     * @param casefileId
     * @param officerProfileId
     * @roseuid 43821A0E02AF
     */
    public void getJPOAssignmentHistory(String casefileId, String officerProfileId)
    {

    }

    /**
     * @stereotype design
     * @param casefileId
     * @roseuid 43821A0E02AF
     */
    public void getCasefileAssignmentHistory(String casefileId)
    {

    }

    /**
     * @stereotype design
     * @param sequenceNum
     * @param daLogNum
     * @roseuid 43821A0F0040
     */
    public void getJuvenileCasefilePetition(String sequenceNum, String daLogNum)
    {

    }

    /**
     * @stereotype design
     * @param referralNum
     * @param petitionNum
     * @roseuid 43821A0F002E
     */
    public void getJuvenileCasefilePetitions(String referralNum, String petitionNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param referralNum
     * @roseuid 43821A0F002E
     */
    public void getJuvenileCasefilePetitionDispositions(String juvenileNum, String referralNum)
    {

    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getJuvenileCasefileReferrals(String supervisionNumber)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E037B
     */
    public void getJuvenileCasefiles(String juvenileNum)
    {

    }
    
    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E037B
     */
    public void getJuvenileCasefileDetails(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E036C
     */
    public void getJuvenileTraits(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileTraitsByType(String juvenileNum, String traitType)
    {

    }

    /**
     * @stereotype design
     * @param traitType
     * @roseuid 43821A0E0399
     */
    public void getJuvenileTraitTypes(String traitType)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E029F
     */
    public void processJuvenileCasefileAssignments()
    {

    }

    /**
     * @stereotype design
     * @param comments
     * @param juvenileNum
     * @param juvenileTrait
     * @param supervisionNum
     * @roseuid 43821A0E03D8
     */
    public void saveJuvenileTraits(String juvenileNum, String supervisionNum)
    {

    }

    /**
     * @stereotype design
     * @param status
     * @param supervisionType
     * @param supervisionNumber
     * @param juvenileNumber
     * @param lastName
     * @param middleName
     * @param firstName
     * @param type
     * @roseuid 43821A0E02C0
     */
    public void searchJuvenileCasefiles(int status, int supervisionType, String supervisionNumber, int juvenileNumber, String lastName, String middleName, String firstName, String type)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param juvenileId
     *            ,parentId
     * @roseuid 43821A0E03AA ER Changes JIMS200074578
     */
    public void getJuvenileTraitsByParentType(String juvenileId, String parentId)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param juvenileId
     *            ,parentId
     * @roseuid 43821A0E03AA ER Changes JIMS200074578
     */
    public void getJuvenileTraitsByJuvenileId(String juvenileId)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param juvenileNum
     *            ,categoryType
     * @roseuid 43821A0E03AA
     */
    public void getTraitParentByCategory(String juvenileId, String parentId)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @roseuid 43821A0E03AA
     */
    public void GetTraitChildByCategory(String traitCategoryName)
    {

    }

    /**
     * @stereotype design
     */
    public void GetJPCourtReferrals(String juvenileId, String casefileId)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @roseuid 43821A0E03AA
     */
    public void getAllSupervisionCategoryTypes()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @roseuid 43821A0E03AA
     */
    public void getAssignmentsByCasefileId(String casefileId)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param referralNum
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileDetentionFacilities(String referralNum, String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param referralNum
     * @roseuid 43821A0E03AA
     */
    public void getJJSCourt(String juvenileNumber, String referralNum)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileAlertDetails()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileDetentionFacilitiesByJuvenileId(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param facilityCode
     */
    public void GetJuvenileFacilityHistoricalReceipts(String juvenileNum, String facilityCode)
    {

    }

    /**
     * @stereotype design
     * @param officerId
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileCaseLoadByOfficer(String officerId)
    {

    }

    /**
     * @stereotype design
     * @param officerId
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileCaseLoadByOfficerID(String officerId)
    {

    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02AF
     */
    public void getJuvenileCasefileByCasefileId(String supervisionNumber)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileDetentionFacilitiesByCode(String facilityCode)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getFacilityCurrentObservation()
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getFacilityCurrentPop()
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getFacilityCurrentPopRpt(String headerFacility)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getCasefilesForReferrals(String juvenileNum, String referralNum)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getReferralsByCasefileId(String juvenileNum, String caseFileId)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getAllReferralsByCasefileId(String juvenileNum, String caseFileId)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getJJSDetentionEventByDateEvent(String juvenileNum, String referralNum, String petitionNum)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getJJSDetentionEventByDate(String juvenileNum, String startDate, String endDate)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void deleteJJSDetention(String juvenileNum, String referralNum, String chainNum)
    {

    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void saveJuvenileRiskNeedLevel(String juvenileNum, String supervisionNum)
    {

    }
    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E02E0
     */
    public void saveJuvenileRiskNeedLevelCustom(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getJJSJuvenile(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getJJSJuvenileInfo(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getCourtActivityByYouth(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getCasefileWithReferral(String juvenileNum, String refferalNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getCasefileWithReferrals(String juvenileNum, String refferalNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getAllCasefilesForReferrals(String juvenileNum, String refferalNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getAllCasefilesForJuvenile(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getActiveCasefilesByJuvenileId(String juvenileId)
    {
    }

    //98670

    /**
     * @stereotype design
     * @param supervisionNumber
     * @roseuid 43821A0E02E0
     */
    public void getDistrictCourtActivityByYouth(String juvenileId)
    {
    }

    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void getSupervisionTypeTJJDMap(String supervisionTypeId)
    {
    }

    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void saveProductionSupportJuvenileRiskNeedLevel(String pactId)
    {
    }

    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void saveProdSupportLastAttorney(String attorneyId)
    {
    }

    /**
     * @stereotype design
     * @roseuid 43821A0E02E0
     */
    public void saveJJSLastAttorney()
    {
    }
    /**
     * @stereotype design
     * @roseuid 43821A0E02E0
     */
    public void updateJJSLastAttorneyGAL()
    {
    }

    /**
     * @stereotype design
     * @roseuid 43821A0E02E0
     */
    public void getActiveCasefileReferrals(String juvenileNum)
    {
    }
    /**
     * @stereotype design
     * @roseuid 43821A0E02E0
     */
    public void getCasefileReferrals(String juvenileNum,String casefileId)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E036C
     */
    public void getCurrentJuvenileDSTTraits(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param petitionnum
     * @roseuid 43821A0E036C
     */
    public void getDetailsbyPetitionNum(String petitionNum)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param referralNum
     * @roseuid 43821A0E03AA
     */
    public void getJJSCourtbyOID()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param referralNum
     * @roseuid 43821A0E03AA
     */
    public void getJJSDetentionbyOID()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @param facilityOID
     * @roseuid 43821A0E03AA
     */
    public void updateJuvenileTraitstoFormer(String juvenileNum, String traitType, String OID, String originaladmitOID)
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @param traitType
     * @param facilityOID
     * @roseuid 43821A0E03AA
     */
    public void GetJuvenileCasefileControllingReferrals(String juvenileNum, String controllingReferral)
    {

    }

    /**
     * @stereotype design
     * @roseuid 43821A0E02E0
     */
    public void getAllCasefileReferralList(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void getRiskAnalysisByJuvenileId(String juvenileId)
    {
    }

    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void getCurrentFacilityByJuvenileId(String juvenileId)
    {
    }

    /**
     * @stereotype design
     */
    public void getJJSReferralDetails(String juvenileNum)
    {
    }
    
    /**
     * @stereotype design
     */
    public void getJJSMsReferralDetails(String juvenileNum)
    {
    }
    
    /**
     * @stereotype design
     * @param headerFacility
     * @roseuid 43821A0E02E0
     */
    public void GetFacilityCurrentPopRpt(String headerFacility)
    {
    }
    
    /**
     * @stereotype design
     * @param headerFacility
     * @roseuid 43821A0E02E0
     */
    public void GetFacilityPopTotalRpt(String headerFacility)
    {
	
    }
    
    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void getJJSDetentionBySupervisionNum(String supervisionNum)
    {
    }
    
    /**
     * @stereotype design
     * @param supervisionTypeId
     * @roseuid 43821A0E02E0
     */
    public void getJJSHeaderBySupervisionNum(String supervisionNum)
    {
    }
    
    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 43821A0E02E0
     */
    public void getJuvenileCurrentPactScores(String juvenileNum)
    {
    }
    
    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getCustomFacilityAdmitReasonPop()
    {
    }
    
    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileCasefileRiskNeedsLevel()
    {
    }
    
    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void saveJJSJuvenileFamilyInfo()
    {
    }
    
    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void GetAllJuvenileCasefiles()
    {
    }
    
    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void GetJuvenileReferralPactRiskNeedsLevel(String juvenileNum, String referralNum)
    {
    }

    
}
