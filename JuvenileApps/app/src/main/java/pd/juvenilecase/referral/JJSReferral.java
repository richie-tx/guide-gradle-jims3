package pd.juvenilecase.referral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import ui.common.CodeHelper;

/**
* @roseuid 43319902003F
*/
public class JJSReferral extends PersistentObject
{
	/**
	* Properties for courtResult
	*/
	private JuvenileDispositionCode courtResult = null;
	private String courtResultId;
	private Date intakeDate;
	private Date referralDate;
	/**
	* Properties for court
	*/
	private JuvenileCourt court = null;
	private String courtId;
	private String sequenceNum;
	private String intakeDecisionId;
	private String referralNum;
	/**
	* Properties for courtDisposition
	*/
	private JuvenileDispositionCode courtDisposition = null;
	/**
	* Properties for intakeDecision
	*/
	private Code intakeDecision = null;
	/**
	* Properties for intakeDecision
	*/
	private JuvenileReferralDispositionCode intakeDecisionComplex = null;
	private Date closeDate;
	private Date courtDate;
	private String courtDispositionId;
	private String transactionNum;
	private String juvenileNum;
	private String daLogNum;
	private String probJPOId;
	private String probLevel;
	private String probUnit;
	private String ctAssignJPOId;
	private String ctAssignLevel;
	private String ctAssignUnit;
	private String inAssignJPOId;
	private Date assignDate;
	private String probationViolation;
	private String secureHours;
	private String referralSevTotal;
	private String offenseTotal;
	private String totalPetitions;
	private Date cirDate;
	private String decisionType;
	

	private String PIACode;
	private String PIADescription;
	private String courtDecisionSubgroupIndicator;
	
	/**
	* Properties for Level of Care
	*/
	private String levelOfCareId;
	private Code levelOfCare = null;
	private Date probationStartDate;
	private Date probationEndDate;
	private String referralTypeInd;
	//task 171521
	private String countyREFD;
	private String delComments;
	private String referralSource;
	private String referralOfficer;
	
	//added for facility admit
	private Date finalReleaseDate;
	private Date admitDate;
	
	private String jpoId;
	private String level;
	private String unit;
	
	private Date lcDate;
	private String lcUser;
	private Date lcTime;
	
	//US 71173
	private Collection offenses = null;	
	private Date TJJDReferralDate;
	private String recType;
	private Date actionDate;
	private String actionOperator;
	private Date dispositionDate;
	
	//sealing
	private Date sealedDate;
	
	private String sealedComments;
	private Date PDADate;	
	private int refExcludedReporting;
	private String sAbuse;
	private String tjpcDisp;
	
	private String tjpcSeqNum; //US 187922
	private String severityType; //US 184961	
	
	/**
	* @roseuid 43319902003F
	*/
	public JJSReferral()
	{
	}
	/**
	* @roseuid 433194DF00FF
	*/
	public void find()
	{
		fetch();
	}
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileDispositionCode
	*/
	public void setCourtResultId(String courtResultId)
	{
		this.courtResultId = courtResultId;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileDispositionCode
	*/
	public String getCourtResultId()
	{
		fetch();
		return courtResultId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileDispositionCode
	*/
	private void initCourtResult()
	{
		if (courtResult == null)
		{
			courtResult =
				JuvenileDispositionCode.find("codeAlpha", courtResultId);
				
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.JuvenileDispositionCode
	*/
	public JuvenileDispositionCode getCourtResult()
	{
		initCourtResult();
		return courtResult;
	}

	/**
		* Access method for the intakeDate property.
		* @return the current value of the intakeDate property
		*/
	public Date getIntakeDate()
	{
		fetch();
		return intakeDate;
	}

	/**
		* Sets the value of the intakeDate property.
		* @param aIntakeDate the new value of the intakeDate property
		*/
	public void setIntakeDate(Date aIntakeDate)
	{
		intakeDate = aIntakeDate;
	}
	/**
		* Access method for the closeDate property.
		* @return the current value of the closeDate property
		*/
	public Date getCloseDate()
	{
		fetch();
		return closeDate;
	}
	/**
	* Sets the value of the closeDate property.
	* @param aCloseDate the new value of the closeDate property
	*/
	public void setCloseDate(Date aCloseDate)
	{
		closeDate = aCloseDate;
	}
	/**
		* Access method for the courtDate property.
		* @return the current value of the courtDate property
		*/
	public Date getCourtDate()
	{
		fetch();
		return courtDate;
	}
	/**
	* Sets the value of the courtDate property.
	* @param aCourtDate the new value of the courtDate property
	*/
	public void setCourtDate(Date aCourtDate)
	{
		courtDate = aCourtDate;
	}
	/**
	* set the type reference for class member courtResult
	*/
	public void setCourtResult(JuvenileDispositionCode courtResult)
	{
		setCourtResultId("" + courtResult.getOID());
		this.courtResult =
			(JuvenileDispositionCode) new mojo.km.persistence.Reference(courtResult).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setCourtId(String courtId)
	{
		this.courtId = courtId;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getCourtId()
	{
		fetch();
		return courtId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileCourt
	*/
	private void initCourt()
	{
		if (court == null)
		{
			court =
				(JuvenileCourt) new mojo
					.km
					.persistence
					.Reference(courtId, JuvenileCourt.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.JuvenileCourt
	*/
	public JuvenileCourt getCourt()
	{
		initCourt();
		return court;
	}
	/**
	* set the type reference for class member court
	*/
	public void setCourt(JuvenileCourt court)
	{
		setCourtId("" + court.getOID());
		this.court = (JuvenileCourt) new mojo.km.persistence.Reference(court).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileDispositionCode
	*/
	public void setCourtDispositionId(String courtDispositionId)
	{
		this.courtDispositionId = courtDispositionId;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileDispositionCode
	*/
	public String getCourtDispositionId()
	{
		fetch();
		return courtDispositionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileDispositionCode
	*/
	private void initCourtDisposition()
	{
		if (courtDisposition == null)
		{
			courtDisposition =
				JuvenileDispositionCode.find("codeAlpha", courtDispositionId);
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.JuvenileDispositionCode
	*/
	public JuvenileDispositionCode getCourtDisposition()
	{
		initCourtDisposition();
		return courtDisposition;
	}
	/**
	* set the type reference for class member courtDisposition
	*/
	public void setCourtDisposition(JuvenileDispositionCode courtDisposition)
	{
		setCourtDispositionId("" + courtDisposition.getOID());
		this.courtDisposition =
			(JuvenileDispositionCode) new mojo
				.km
				.persistence
				.Reference(courtDisposition)
				.getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setIntakeDecisionId(String intakeDecisionId)
	{
		this.intakeDecisionId = intakeDecisionId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getIntakeDecisionId()
	{
		fetch();
		return intakeDecisionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initIntakeDecision()
	{
		if (intakeDecision == null)
		{
			intakeDecision =
				(Code) new mojo
					.km
					.persistence
					.Reference(intakeDecisionId, Code.class, "REFERRAL.DECISION")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getIntakeDecision()
	{
		fetch();
		initIntakeDecision();
		return intakeDecision;
	}
	/**
	* set the type reference for class member intakeDecision
	*/
	public void setIntakeDecision(Code intakeDecision)
	{
	    if(intakeDecision!=null){
		setIntakeDecisionId("" + intakeDecision.getOID());
		this.intakeDecision = (Code) new mojo.km.persistence.Reference(intakeDecision).getObject();
	    }else{
		this.intakeDecision = intakeDecision;
	    }
	}
	
	/**
	* Initialize class relationship to class pd.codetable.criminal.JuvenileReferralDispositionCode
	*/
	private void initIntakeDecisionComplex()
	{
		if (intakeDecisionComplex == null)
		{
			intakeDecisionComplex =
				(JuvenileReferralDispositionCode) new mojo
					.km
					.persistence
					.Reference(intakeDecisionId, JuvenileReferralDispositionCode.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.JuvenileReferralDispositionCode
	*/
	public JuvenileReferralDispositionCode getIntakeDecisionComplex()
	{
		initIntakeDecisionComplex();
		return intakeDecisionComplex;
	}
	/**
	* set the type reference for class member courtDisposition
	*/
	public void setIntakeDecisionComplex(JuvenileReferralDispositionCode intakeDecisionComplex)
	{
		setIntakeDecisionId("" + intakeDecisionComplex.getOID());
		this.intakeDecisionComplex =
			(JuvenileReferralDispositionCode) new mojo
				.km
				.persistence
				.Reference(intakeDecisionComplex)
				.getObject();
	}
	
	/**
		* Access method for the referralNum property.
		* @return the current value of the referralNum property
		*/
	public String getReferralNum()
	{
		fetch();
		return referralNum;
	}
	/**
	* Sets the value of the referralNum property.
	* @param aReferralNum the new value of the referralNum property
	*/
	public void setReferralNum(String aReferralNum)
	{
		referralNum = aReferralNum;
	}
	/**
		* Access method for the juvenileNum property.
		* @return the current value of the juvenileNum property
		*/
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}
	/**
	* Sets the value of the juvenileNum property.
	* @param aJuvenileNum the new value of the juvenileNum property
	*/
	public void setJuvenileNum(String aJuvenileNum)
	{
		juvenileNum = aJuvenileNum;
	}
	/**
		* Access method for the sequenceNum property.
		* @return the current value of the sequenceNum property
		*/
	public String getSequenceNum()
	{
		fetch();
		return sequenceNum;
	}
	/**
	* Sets the value of the sequenceNum property.
	* @param aSequenceNum the new value of the sequenceNum property
	*/
	public void setSequenceNum(String aSequenceNum)
	{
		sequenceNum = aSequenceNum;
	}
	/**
		* Access method for the transactionNum property.
		* @return the current value of the transactionNum property
		*/
	public String getTransactionNum()
	{
		fetch();
		return transactionNum;
	}
	/**
	* Sets the value of the transactionNum property.
	* @param aTransactionNum the new value of the transactionNum property
	*/
	public void setTransactionNum(String aTransactionNum)
	{
		transactionNum = aTransactionNum;
	}
	/**
		* @roseuid 42A99B980107
		* @return iterator
		*/
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
		System.out.println("grantedFeature: " + grantedFeature );
		if (grantedFeature)
		    return home.findAll(event, JJSReferral.class);
		else
		    return filterSealedPurged(home.findAll(event,JJSReferral.class));
	}
	public static Iterator findAllWithoutFilter(IEvent event)
	{
		IHome home = new Home();		
		 return home.findAll(event, JJSReferral.class);	
	}
	
	 private static Iterator filterSealedPurged(Iterator iter)
	    {
		ArrayList<JJSReferral> referrals = new ArrayList<JJSReferral>();
		while (iter.hasNext())
		{
		    //my view contains both court records and detention
		    JJSReferral ref = (JJSReferral) iter.next();
		    if ( ref != null && ("REFERRAL".equalsIgnoreCase(ref.getRecType()))){
			referrals.add(ref);
		    }
		}
		return referrals.iterator();
	    }
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
		if (grantedFeature)
		    return home.findAll(attributeName, attributeValue,JJSReferral.class);
		else
		    return filterSealedPurged(home.findAll(attributeName, attributeValue,JJSReferral.class));
	}		
	/**
	 * @return Returns the pIACode.
	 */
	public String getPIACode() {
		fetch();
		return PIACode;
	}
	/**
	 * @param code The pIACode to set.
	 */
	public void setPIACode(String aPIACode) {
		this.PIACode = aPIACode;
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		fetch();
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date aReferralDate) {
		if (this.referralDate == null || !this.referralDate.equals(aReferralDate))
		{
			markModified();
		}
		this.referralDate = aReferralDate;
	}
	
	/**
	 * @return Returns the courtDecisionSubgroupIndicator.
	 */
	public String getCourtDecisionSubgroupIndicator() {
		fetch();
		
		initCourtDisposition();	
		if(this.courtDisposition != null)
		    this.courtDecisionSubgroupIndicator = this.courtDisposition.getSubGroupInd();
		else
		    this.courtDecisionSubgroupIndicator = "";
		return this.courtDecisionSubgroupIndicator;
	}
	/**
	 * @param courtDecisionSubgroupIndicator The courtDecisionSubgroupIndicator to set.
	 */
	public void setCourtDecisionSubgroupIndicator(String courtDecisionSubgroupIndicator) {
		this.courtDecisionSubgroupIndicator = courtDecisionSubgroupIndicator;
	}
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		fetch();
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the probJPOId.
	 */
	public String getProbJPOId() {
		fetch();
		return probJPOId;
	}
	/**
	 * @param probJPOId The probJPOId to set.
	 */
	public void setProbJPOId(String probJPOId) {
		this.probJPOId = probJPOId;
	}
		/**
	 * @return Returns the ctAssignJPOId.
	 */
	public String getCtAssignJPOId() {
		fetch();
		return ctAssignJPOId;
	}
	/**
	 * @param ctAssignJPOId The ctAssignJPOId to set.
	 */
	public void setCtAssignJPOId(String ctAssignJPOId) {
		this.ctAssignJPOId = ctAssignJPOId;
	}
		/**
	 * @return Returns the inAssignJPOId.
	 */
	public String getInAssignJPOId() {
		fetch();
		return inAssignJPOId;
	}
	/**
	 * @param inAssignJPOId The inAssignJPOId to set.
	 */
	public void setInAssignJPOId(String inAssignJPOId) {
		this.inAssignJPOId = inAssignJPOId;
	}
	/**
	 * @return Returns the levelOfCareId.
	 */
	public String getLevelOfCareId() {
		fetch();
		return levelOfCareId;
	}
	/**
	 * @param levelOfCareId The levelOfCareId to set.
	 */
	public void setLevelOfCareId(String levelOfCareId) {
		this.levelOfCareId = levelOfCareId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLevelOfCare()
	{
		if (levelOfCare == null)
		{
			levelOfCare =
				(Code) new mojo
					.km
					.persistence
					.Reference(levelOfCareId, Code.class, "JUVENILE_LEVEL_CARE")
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getLevelOfCare()
	{
		fetch();
		initLevelOfCare();
		return levelOfCare;
	}
	/**
	* set the type reference for class member levelOfCare
	*/
	public void setLevelOfCare(Code levelOfCare)
	{
		setLevelOfCareId("" + levelOfCare.getOID());
		this.levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCare).getObject();
	}
	public Date getProbationEndDate() {
		fetch();
		return probationEndDate;
	}
	public void setProbationEndDate(Date aProbationEndDate) {
	    if (this.probationEndDate == null || !this.probationEndDate.equals(aProbationEndDate))
		{
			markModified();
		}
	    this.probationEndDate = aProbationEndDate;
	}
	public Date getProbationStartDate() {
		fetch();
		return probationStartDate;
	}
	public void setProbationStartDate(Date aProbationStartDate) {
	    if (this.probationStartDate == null || !this.probationStartDate.equals(aProbationStartDate))
		{
			markModified();
		}
	    this.probationStartDate = aProbationStartDate;
	    
	}
	public String getPIADescription() {
		fetch();
		//US 71184
		if(this.PIACode!=null)
		    PIADescription=CodeHelper.getCodeDescription(PDCodeTableConstants.REFERRAL_PIA_CASE_TYPE, this.PIACode);
		else
		    PIADescription = ""; 
		return PIADescription;
	}
	public void setPIADescription(String description) {
		this.PIADescription = description;
	}
	public String getReferralTypeInd() {
		fetch();
		return referralTypeInd;
	}
	public void setReferralTypeInd(String referralTypeInd) {
		this.referralTypeInd = referralTypeInd;
	}
	/**
	 * @param referralSource the referralSource to set
	 */
	public void setReferralSource(String referralSource) {
		if (this.referralSource == null || !this.referralSource.equals(referralSource))
		{
			markModified();
		}
		this.referralSource = referralSource;
	}
	/**
	 * @return the referralSource
	 */
	public String getReferralSource() {
		fetch();
		return referralSource;
	}
	/**
	 * @param referralOfficer the referralOfficer to set
	 */
	public void setReferralOfficer(String referralOfficer) {
		if (this.referralOfficer == null || !this.referralOfficer.equals(referralOfficer))
		{
			markModified();
		}
		this.referralOfficer = referralOfficer;
	}
	/**
	 * @return the referralOfficer
	 */
	public String getReferralOfficer() {
		return referralOfficer;
	}
	/**
	 * @param finalReleaseDate the finalReleaseDate to set
	 */
	public void setFinalReleaseDate(Date finalReleaseDate) {
		if (this.finalReleaseDate == null || !this.finalReleaseDate.equals(finalReleaseDate))
		{
			markModified();
		}
		this.finalReleaseDate = finalReleaseDate;
	}
	/**
	 * @return the finalReleaseDate
	 */
	public Date getFinalReleaseDate() {
		fetch();
		return finalReleaseDate;
	}
	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		fetch();
		return admitDate;
	}
	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		if (this.admitDate == null || !this.admitDate.equals(admitDate))
		{
			markModified();
		}
		this.admitDate = admitDate;
	}
	/**
	 * @return the jpoid
	 */
	public String getJpoid() {
		fetch();
		return jpoId;
	}
	/**
	 * @param jpoid the jpoid to set
	 */
	public void setJpoid(String jpoid) {
		if (this.jpoId == null || !this.jpoId.equals(jpoid))
		{
			markModified();
		}
		this.jpoId = jpoid;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		fetch();
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		if (this.level == null || !this.level.equals(level))
		{
			markModified();
		}
		this.level = level;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		fetch();
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		if (this.unit == null || !this.unit.equals(unit))
		{
			markModified();
		}
		this.unit = unit;
	}
	/**
	 * @return the probLevel
	 */
	public String getProbLevel() {
		fetch();
		return probLevel;
	}
	/**
	 * @param probLevel the probLevel to set
	 */
	public void setProbLevel(String probLevel) {
		if (this.probLevel == null || !this.probLevel.equals(probLevel))
		{
			markModified();
		}
		this.probLevel = probLevel;
	}
	
	/**
	 * @return the probUnit
	 */
	public String getProbUnit() {
		fetch();
		return probUnit;
	}
	/**
	 * @param probUnit the probUnit to set
	 */
	public void setProbUnit(String probUnit) {
		if (this.probUnit == null || !this.probUnit.equals(probUnit))
		{
			markModified();
		}
		this.probUnit = probUnit;
	}
	/**
	 * @return the ctAssignLevel
	 */
	public String getCtAssignLevel() {
		fetch();
		return ctAssignLevel;
	}
	/**
	 * @param ctAssignLevel the ctAssignLevel to set
	 */
	public void setCtAssignLevel(String ctAssignLevel) {
		if (this.ctAssignLevel == null || !this.ctAssignLevel.equals(ctAssignLevel))
		{
			markModified();
		}
		this.ctAssignLevel = ctAssignLevel;
	}
	/**
	 * @return the ctAssignUnit
	 */
	public String getCtAssignUnit() {
		fetch();
		return ctAssignUnit;
	}
	/**
	 * @param ctAssignUnit the ctAssignUnit to set
	 */
	public void setCtAssignUnit(String ctAssignUnit) {
		if (this.ctAssignUnit == null || !this.ctAssignUnit.equals(ctAssignUnit))
		{
			markModified();
		}
		this.ctAssignUnit = ctAssignUnit;
	}
	
	/**
	 * @return the decisionType
	 */
	public String getDecisionType() {
		fetch();
		return decisionType;
	}
	/**
	 * @param decisionType the decisionType to set
	 */
	public void setDecisionType(String decisionType) {
		if (this.decisionType == null || !this.decisionType.equals(decisionType))
		{
			markModified();
		}
		this.decisionType = decisionType;
	}
	/**
	 * @return the jpoId
	 */
	public String getJpoId() {
		fetch();
		return jpoId;
	}
	/**
	 * @param jpoId the jpoId to set
	 */
	public void setJpoId(String jpoId) {
		if (this.jpoId == null || !this.jpoId.equals(jpoId))
		{
			markModified();
		}
		this.jpoId = jpoId;
	}
	/**
	 * @return the cirDate
	 */
	public Date getCirDate() {
		fetch();
		return cirDate;
	}
	/**
	 * @param cirDate the cirDate to set
	 */
	public void setCirDate(Date cirDate) {
		if (this.cirDate == null || !this.cirDate.equals(cirDate))
		{
			markModified();
		}
		this.cirDate = cirDate;
	}
	/**
	 * @return the assignDate
	 */
	public Date getAssignDate() {
		fetch();
		return assignDate;
	}
	/**
	 * @param assignDate the assignDate to set
	 */
	public void setAssignDate(Date assignDate) {
		if (this.cirDate == null || !this.cirDate.equals(cirDate))
		{
			markModified();
		}
		this.assignDate = assignDate;
	}
	/**
	 * @return the probationViolation
	 */
	public String getProbationViolation() {
		fetch();
		return probationViolation;
	}
	/**
	 * @param probationViolation the probationViolation to set
	 */
	public void setProbationViolation(String probationViolation) {
		if (this.probationViolation == null || !this.probationViolation.equals(probationViolation))
		{
			markModified();
		}
		this.probationViolation = probationViolation;
	}
	/**
	 * @return the secureHours
	 */
	public String getSecureHours() {
		fetch();
		return secureHours;
	}
	/**
	 * @param secureHours the secureHours to set
	 */
	public void setSecureHours(String secureHours) {
		if (this.secureHours == null || !this.secureHours.equals(secureHours))
		{
			markModified();
		}
		this.secureHours = secureHours;
	}
	/**
	 * @return the referralSevTotal
	 */
	public String getReferralSevTotal() {
		fetch();
		return referralSevTotal;
	}
	/**
	 * @param referralSevTotal the referralSevTotal to set
	 */
	public void setReferralSevTotal(String referralSevTotal) {
		if (this.referralSevTotal == null || !this.referralSevTotal.equals(referralSevTotal))
		{
			markModified();
		}
		this.referralSevTotal = referralSevTotal;
	}
	/**
	 * @return the offenseTotal
	 */
	public String getOffenseTotal() {
		fetch();
		return offenseTotal;
	}
	/**
	 * @param offenseTotal the offenseTotal to set
	 */
	public void setOffenseTotal(String offenseTotal) {
		if (this.offenseTotal == null || !this.offenseTotal.equals(offenseTotal))
		{
			markModified();
		}
		this.offenseTotal = offenseTotal;
	}
	/**
	 * @return the totalPetitions
	 */
	public String getTotalPetitions() {
		fetch();
		return totalPetitions;
	}
	/**
	 * @param totalPetitions the totalPetitions to set
	 */
	public void setTotalPetitions(String totalPetitions) {
		if (this.totalPetitions == null || !this.totalPetitions.equals(totalPetitions))
		{
			markModified();
		}
		this.totalPetitions = totalPetitions;
	}
	public Date getLcDate()
	{
	    fetch();
	    return lcDate;
	}
	public void setLcDate(Date lcDate)
	{
	    if (this.lcDate == null || !this.lcDate.equals(lcDate))
		{
			markModified();
		}
	    this.lcDate = lcDate;
	}
	public String getLcUser()
	{
	    fetch();
	    return lcUser;
	}
	public void setLcUser(String lcUser)
	{
	    if (this.lcUser == null || !this.lcUser.equals(lcUser))
		{
			markModified();
		}
	    this.lcUser = lcUser;
	}
	public Date getLcTime()
	{
	    fetch();
	    return lcTime;
	}
	public void setLcTime(Date lcTime)
	{
	    if (this.lcTime == null || !this.lcTime.equals(lcTime))
		{
			markModified();
		}
	    this.lcTime = lcTime;
	}
	  /**
	* Clears all pd.juvenilecase.referral.JJSOffense from class relationship collection.
	* @roseuid 4277CAAC037A
	*/
	public void clearOffenses()
	{
		initOffenses();
		offenses.clear();
	}
		
	/**
	* Initialize class relationship implementation for pd.juvenilecase.referral.JJSOffense
	*/
	private void initOffenses()
	{
		if (offenses == null)
		{
		    offenses = new mojo.km.persistence.ArrayList(JJSOffense.class, "referralNum", "" + getReferralNum());
		}				
	}
	
	/**
	* insert a pd.juvenilecase.referral.JJSOffense into class relationship collection.
	* @roseuid 4277CAAC033C
	* @param anObject
	*/
	public void insertOffenses(JJSOffense anObject)
	{
	    initOffenses();
	    offenses.add(anObject);
	}
	/**
	* Sets the value of the offenses property.
	* @param aOffenses the new value of the offenses property
	*/
	public void setOffenses(Collection aOffenses)
	{
		if (this.offenses == null || !this.offenses.equals(aOffenses))
		{
			markModified();
		}
		offenses = aOffenses;
	}
	/**
	* Access method for the assignments property.
	* @return the current value of the assignments property
	*/
	public Collection getAssignments()
	{
		fetch();
		initOffenses();
		return offenses;
	}
	
	/**
	 * @return Returns the TJJDReferralDate.
	 */
	public Date getTJJDReferralDate() {
		fetch();
		return TJJDReferralDate;
	}
	/**
	 * @param TJJDReferralDate The TJJDReferralDate to set.
	 */
	public void setTJJDReferralDate(Date aTJJDReferralDate) {
		if (this.TJJDReferralDate == null || !this.TJJDReferralDate.equals(aTJJDReferralDate))
		{
			markModified();
		}
		this.TJJDReferralDate = aTJJDReferralDate;
	}
	

	/**
	 * @return Returns the recType.
	 */
	public String getRecType() {
		fetch();
		return recType;
	}
	/**
	 * @param recType The aRecType to set.
	 */
	public void setRecType(String aRecType) {
		if (this.recType == null || !this.recType.equals(aRecType))
		{
			markModified();
		}
		this.recType = aRecType;
	}
	
	/**
	* Access method for the actionDate property.
	* @return the current value of the actionDate property
	*/
        public Date getActionDate()
        {
        	fetch();
        	return actionDate;
        }
        /**
        * Sets the value of the actionDate property.
        * @param aCloseDate the new value of the actionDate property
        */
        public void setActionDate(Date aActionDate)
        {
            actionDate = aActionDate;
        }
        public Date getDispositionDate()
	{
            fetch();
	    return dispositionDate;
	}
	public void setDispositionDate(Date aDispositionDate)
	{
	    dispositionDate = aDispositionDate;
	}
        
        /**
	 * @return Returns the actionOperator.
	 */
	public String getActionOperator() {
		fetch();
		return actionOperator;
	}
	/**
	 * @param actionOperator The aActionOperator to set.
	 */
	public void setActionOperator(String aActionOperator) {
		if (this.actionOperator == null || !this.actionOperator.equals(aActionOperator))
		{
			markModified();
		}
		this.actionOperator = aActionOperator;
	}
	public Date getSealedDate()
	{
	    return sealedDate;
	}
	public void setSealedDate(Date sealedDate)
	{
	    this.sealedDate = sealedDate;
	}
	public String getSealedComments()
	{
	    return sealedComments;
	}
	public void setSealedComments(String sealedComments)
	{
	    this.sealedComments = sealedComments;
	}
	public Date getPDADate()
	{
	    fetch();
	    return PDADate;
	}
	public void setPDADate(Date pDADate)
	{
	    PDADate = pDADate;
	}
	
	public int getRefExcludedReporting()
	{
	    return refExcludedReporting;
	}
	public void setRefExcludedReporting(int refExcludedReporting)
	{
	    if ( this.refExcludedReporting == 0
			|| this.refExcludedReporting != refExcludedReporting) {
		    markModified();
		}
	    this.refExcludedReporting = refExcludedReporting;
	}
	//task 171521
	public String getCountyREFD()
	{
	    return countyREFD;
	}
	public void setCountyREFD(String countyREFD)
	{
	    this.countyREFD = countyREFD;
	}

	public String getdelComments()
	{
	    return delComments;
	}
	public void setDelComments(String delComments)
	{
	    this.delComments = delComments;
	}

	public String getsAbuse()
	{
	    fetch();
	    return sAbuse;
	}
	public void setsAbuse(String sAbuse)
	{
	    if ( this.sAbuse == null
		    || !this.sAbuse.equals(sAbuse)){
		markModified();
	    }
	    this.sAbuse = sAbuse;
	}
	
	 public String getTJPCDisp()
	 {
	     fetch();
	     return this.tjpcDisp;
	 }

	 public void setTJPCDisp(String tjpcDisp)
	 {
	     if ( this.tjpcDisp == null || !this.tjpcDisp.equals(tjpcDisp)){
			markModified();
		}
	        this.tjpcDisp = tjpcDisp;
	 }
	public String getTjpcSeqNum()
	{
	    return tjpcSeqNum;
	}
	public void setTjpcSeqNum(String tjpcSeqNum)
	{
	    this.tjpcSeqNum = tjpcSeqNum;
	}
	public String getSeverityType()
	{
	    return severityType;
	}
	public void setSeverityType(String severityType)
	{
	    this.severityType = severityType;
	}
	 
}
