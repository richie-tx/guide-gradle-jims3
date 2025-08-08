//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\CodeTableController.java

package pd.codetable;

import java.util.ArrayList;
import java.util.Date;

/**
 * @stereotype control
 */
public class CodeTableController
{

	/**
	 * @roseuid 40F7EE3E03C2
	 */
	public CodeTableController()
	{

	}

	/**
	 * @stereotype design
	 */	
	public void getScarMarkCodes()
	{		
	}
	
	/**
	 * @stereotype design
	 */	
	public void getTattooCodes()
	{		
	}

	/**
	 * @stereotype design
	 * @param description
	 * @param activationDate
	 * @param code
	 * @roseuid 40F7E6B50152
	 */
	public void updateCodeTable(String description, Date activationDate, String code)
	{

	}

	/**
	 * @stereotype design
	 * @param judgeName
	 * @param court
	 */
	public void getJuvenileCourts(String judgeName, String court)
	{

	}
	
	
	/**
	 * @stereotype design
	 * @param department
	 */
	public void getJuvenileFacilities(String department)
	{

	}
	
	
	/**
	 * @stereotype design
	 * @param department
	 */
	public void getJuvenileHearingTypes(String code)
	{

	}
	
	/**
	 * @stereotype design
	 * @param code
	 */
	public void getJuvenileFacilityByCode(String code) 
	{

	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileAdmitReasons()
	{

	}
	
	/**
	 * @stereotype design
	 * @param department
	 */
	public void getNonDetentionJuvenileFacilities(String department)
	{

	}

	/**
	 * @stereotype design
	 */
	public void getScarsMarksTattoosCodes()
	{

	}

	/**
	 * @stereotype design
	 */
	public void getRaceEthnicityCodes()
	{

	}

	/**
	 * @stereotype design
	 * @param codeTableId
	 * @roseuid 40F7E6B501DE
	 */
	public void getCodeHistory(String codeTableId)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 40F7E6B502C4
	 */
	public void getCodeTableSearchInfo()
	{

	}

	/**
	 * @stereotype design
	 * @param codeTableId
	 * @roseuid 40F7E6B600B3
	 */
	public void getCodeTable(String codeTableId)
	{

	}

	/**
	 * @stereotype design
	 * @param activationDate
	 * @param status
	 * @param deleteUser
	 * @param inactivateEffectiveDate
	 * @param inactiveDate
	 * @param description
	 * @param code
	 * @roseuid 40F7E6B6017B
	 */
	public void updateCodeTableRecord(
		Date activationDate,
		String status,
		String deleteUser,
		Date inactivateEffectiveDate,
		Date inactiveDate,
		String description,
		String code)
	{

	}

	/**
	 * @stereotype design
	 * @param codeTableName
	 * @param codeTableId
	 * @roseuid 411292BC00D4
	 */
	//public void getCodeTableRecord(String codeTableName, String codeTableId)
	//{

	//}

	/**
	 * @stereotype design
	 * @roseuid 411292BC01FE
	 */
	public void getCodeTableRecords()
	{

	}

	/**
	 * @stereotype design
	 * @param code
	 * @param codeTableName
	 * @param codeTableId
	 * @roseuid 4112952C0328
	 */
	public void getCode(String code, String codeTableName, String codeTableId)
	{

	}

	/**
	 * @stereotype design
	 * @param code
	 * @param codeId
	 * @param codeTableName
	 * @roseuid 41644690033A
	 */
	public void deleteCode(String code, String codeId, String codeTableName)
	{

	}

	/**
	 * @stereotype design
	 * @param codeTableName
	 * @roseuid 41F798350362
	 */
	public void getCodes(String codeTableName)
	{

	}

	/**
	 * @stereotype design
	 * @param codetableRegId
	 */
	public void getCodetableRegistrationAttributes() 
	{

	}

	/**
	 * @stereotype design
	 * @param 
	 */
	public void getCodetableRegistrationAttributesAndTypes() 
	{

	}

	/**
	 * @stereotype design
	 * @param 
	 */
	public void updateCodetableRegistration() 
	{

	}

	/**
	 * @stereotype design
	 * @param 
	 */
	public void updateCodetableRegistrationAttributes() 
	{

	}
	
	/**
	 * @stereotype design
	 * @param 
	 */
	public void validateContextKeyOrEntityName() 
	{

	}

	/**
	 * @stereotype design
	 * @param 
	 */
	public void validateCompoundEntity() 
	{

	}

	/**
	 * @stereotype design
	 * @param codeTableName
	 * @param agencyId
	 * @roseuid 41F798350362
	 */
	public void getSupervisionCodes(String agencyId, String codeTableName)
	{

	}

	/**
	* This method will generate the command to be used from the appshell to 
	* do a one time initialization of frequently used code tables.
	@author Dhanashree
	@param status
	@param codeTableName
	@stereotype design
	*/
	public void initializeCommonCodeTableData(ArrayList codeTableNames)
	{
	}

	/**
	 * @param codeTableId
	 * @stereotype design
	 */
	public void hasCodeHistory(String codeTableId)
	{

	}
	/**
	 * @param offenseCode
	 * @param offenseLevel
	 * @param offenseDegree
	 * @param offenseLiteral
	 * @param penalCode
	 * @param stateOffenseCode
	 * @stereotype design
	 */
	public void getOffenseCodes(
		String offenseCode,
		String offenseLevel,
		String offenseDegree,
		String offenseLiteral,
		String penalCode,
		String stateOffenseCode)
	{

	}
	/**
	 * @param offenseCode
	 * @stereotype design
	 */
	public void getOffenseCode(String offenseCode)
	{

	}
	/**
	 * @stereotype design
	 */
	public void getMagistrates()
	{
	}
	/**
		 * @param codeTableName
		 * @stereotype design
		 */	
	public void getServiceTypeCd(String codeTableName)
	{

	}	
	
	/**
	 * @param codeTableName, group
	 * @stereotype design
	 */	
	public void getServiceTypeCdByGroup(String codeTableName, String group)
	{
	
	}	
	/**
	 * @stereotype design
	 */
	public void getJuvenileAdmissionTypeCd(String categoryId) {

	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileDispositionCode(String code) {

	}
	/**
	 * @stereotype design
	 */
	public void getJuvenileActivityTypeCodes() {

	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileActivityCodes() {

	}	
	
	/**
	 * @stereotype design
	 * @param codetableRegId
	 */
	public void getCodetableCompoundList(String codetableRegId) {

	}	
	
	/**
	 * @stereotype design
	 * @param codetableRegId
	 */
	public void getCodetableRecord(String codetableRegId) {

	}	
	
	/**
	 * @stereotype design
	 * @param codetableName
	 */
	public void searchCodetableRecords(String codetableName) {

	}	
	
	/**
	 * @stereotype design
	 * @param codetableName
	 */
	public void updateCodetableData(String codetableName) {

	}	
	/**
	 * @stereotype design
	 * 
	 */
	public void getJuvenileMedicationTypeCodes()
	{}
	
	/**
	 * @stereotype design
	 * @param codeTableRegId
	 */	
	public void getCodetableRegistrationAgencies(String codeTableRegId)
	{}
	
	/**
	 * @stereotype design
	 * @param codeTableRegId
	 */
	public void getNameType(){
		
	}

	/**
	 * @stereotype design
	 */
	public void getJuvenileRuleCompletionStatusCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetRiskAnalysisControlCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetJuvenileTechnicalVOPCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetJuvenileVOPSanctionCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetJuvenileCodeTableChildCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetJuvenileCourtDecisionCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetJuvenileOffenseCode(){	
	}
	
	/**
	 * @stereotype design
	 */
	public void getSpecialAttentionReasonCodes() {

	}	
	
	/**
	 * @stereotype design
	 */
	public void getServiceDeliveryWithoutFeeCode(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileReferralSource(String code){
		
	}
	/**
	 * @stereotype design
	 */
	public void getAllJuvenileReferralSources(){
		
	}
	
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileDSM5CodesWithTJJDID(String code){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileRefAssignmentType(String code){
		
	}
	/**
	 * @stereotype design
	 */
	public void getJuvenileDSM5Code(String code){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void getAttorneyNameAndBarNum(){	
	}
	
	/**
	 * @stereotype design
	 */
	public void getAllJuvenileOffenseCodes(){	
	}
	/**
	 * @stereotype design
	 */
	public void getAllOffenseCodes(){	
	}
	/**
	 * @stereotype design
	 */
	public void getAllJuvenileDispositionCode(String code) {

	}
	/**
	 * @stereotype design
	 */
	public void getJuvenileReleasedFromDetention(){	
	}
	
	/**
	 * @stereotype design
	 */
	public void getJuvenileReferralDecisionCode(String code) {

	}
	/**
	 * @stereotype design
	 */
	public void GetAllJuvenileCourtDecisionCodes(){
		
	}
	/**
	 * @stereotype design
	 */
	public void getAllAliasNameTypeCodes(){	
	}
	/**
	 * @stereotype design
	 */
	public void getAllMAYSIReasonCodes(){	
	}
	/**
	 * @stereotype design
	 */
	public void GetDrugTypeCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetDrugTestResultCodes(){
		
	}
	
	/**
	 * @stereotype design
	 */
	public void GetOverrideOptionCodes(){
		
	}
	/**
	 * @stereotype design
	 */
	public void getJuvenileDispositionCodeByValue(String code) {

	}
	
}