package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import ui.juvenilecase.referral.JuvenileReferralOffenseBean;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a thinly populated version of a referral for displaying
 * only needed data required data.  
 * 
 * @author glyons
 */
public class JJSReferralResponseEvent extends ResponseEvent implements Comparable
{
	private String juvenileNum;
	private String referralNum;
	private Date closeDate;
	private Date courtDate;
	private String courtId;
	private String courtDispositionCd;
	private String courtDispositionDesc;
	private String courtResultDesc;
	private Date intakeDate;
	private String intakeDecision;
	private String sequenceNum;	
	private String transactionNum;
	private String PIACode;
	private String PIADescription;
	private Date referralDate;
	private String levelOfCare;
	private String daLogNum;
	private Date probationStartDate;
	private Date probationEndDate;
	private String referralTypeInd;
	private String courtPetitionAllegation; //BUG 156574
	// user story 11029/13220 changes 
	private Map<Date,String> dispositions =  new TreeMap<Date,String>();
	
	//US 71173
	private String supervisionType;
	private String jpo;
	private String offenseList;	
	private String refSource;
	private String supervisionTypeDescr;
	private String jpoDescr;
	private String intakeDecisionDescr;
	private String terminationDate;

	//US 71177
	 private Collection<JuvenileReferralOffenseBean> offenses = new ArrayList<JuvenileReferralOffenseBean>();
	
	public JJSReferralResponseEvent() {
		PIACode = "";
	}

	/**
	 * @return courtId
	 */
	public String getCourtId()
	{
		return courtId;
	}
	
	/**
	 * 
	 * @param aCourtId
	 */
	public void setCourtId(String aCourtId)
	{
		courtId = aCourtId;
	}
	
	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	public void setJuvenileNum(String aJuvenileNum)
	{
		juvenileNum = aJuvenileNum;
	}
	/**
	 * @return referralNum
	 */
	public String getReferralNum()
	{
		return referralNum;
	}

	/**
	 * @param string
	 */
	public void setReferralNum(String aReferralNum)
	{
		referralNum = aReferralNum;
	}

	/**
	 * @return closeDate
	 */
	public Date getCloseDate()
	{
		return closeDate;
	}

	/**
	 * @return courtDate
	 */
	public Date getCourtDate()
	{
		return courtDate;
	}

	/**
	 * @return courtDispositionDesc
	 */
	public String getCourtDispositionDesc()
	{
		return courtDispositionDesc;
	}

	/**
	 * @return courtResultDesc
	 */
	public String getCourtResultDesc()
	{
		return courtResultDesc;
	}

	/**
	 * @return intakeDate
	 */
	public Date getIntakeDate()
	{
		return intakeDate;
	}

	/**
	 * @return intakeDecision
	 */
	public String getIntakeDecision()
	{
		return intakeDecision;
	}

	/**
	 * @return sequenceNum
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @return transactionNum
	 */
	public String getTransactionNum()
	{
		return transactionNum;
	}

	/**
	 * @param aCloseate
	 */
	public void setCloseDate(Date aCloseate)
	{
		closeDate = aCloseate;
	}

	/**
	 * @param aCourtDate
	 */
	public void setCourtDate(Date aCourtDate)
	{
		courtDate = aCourtDate;
	}

	/**
	 * @param aCourtDispositionDesc
	 */
	public void setCourtDispositionDesc(String aCourtDispositionDesc)
	{
		courtDispositionDesc = aCourtDispositionDesc;
	}

	/**
	 * @param aCourtResultDesc
	 */
	public void setCourtResultDesc(String aCourtResultDesc)
	{
		courtResultDesc = aCourtResultDesc;
	}

	/**
	 * @param aIntakeDate
	 */
	public void setIntakeDate(Date aIntakeDate)
	{
		intakeDate = aIntakeDate;
	}

	/**
	 * @param aIntakeDecision
	 */
	public void setIntakeDecision(String aIntakeDecision)
	{
		intakeDecision = aIntakeDecision;
	}

	/**
	 * @param aSequenceNum
	 */
	public void setSequenceNum(String aSequenceNum)
	{
		sequenceNum = aSequenceNum;
	}

	/**
	 * @param aTransactionNum
	 */
	public void setTransactionNum(String aTransactionNum)
	{
		transactionNum = aTransactionNum;
	}

	/**
	 * @return Returns the levelOfCare.
	 */
	public String getLevelOfCare() {
		return levelOfCare;
	}
	/**
	 * @param levelOfCare The levelOfCare to set.
	 */
	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}
	/**
	 * @return Returns the pIACode.
	 */
	public String getPIACode() {
		return PIACode;
	}
	/**
	 * @param code The pIACode to set.
	 */
	public void setPIACode(String code) {
		if(code != null) {
			PIACode = code;
		}
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * returns the latest referral
	 */
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		if(this.referralDate==null)
			return 1;
		JJSReferralResponseEvent evt = (JJSReferralResponseEvent)obj;
		return referralDate.compareTo(evt.getReferralDate());
	}
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}

	public Date getProbationEndDate() {
		return probationEndDate;
	}

	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}

	public Date getProbationStartDate() {
		return probationStartDate;
	}

	public void setProbationStartDate(Date probationStartDate) {
		this.probationStartDate = probationStartDate;
	}

	public String getPIADescription() {
		return PIADescription;
	}

	public void setPIADescription(String description) {
		PIADescription = description;
	}

	public String getCourtDispositionCd() {
		return courtDispositionCd;
	}

	public void setCourtDispositionCd(String courtDispositionCd) {
		this.courtDispositionCd = courtDispositionCd;
	}

	public String getReferralTypeInd() {
		return referralTypeInd;
	}

	public void setReferralTypeInd(String referralTypeInd) {
		this.referralTypeInd = referralTypeInd;
	}

	/**
	 * @param dispositions the dispositions to set
	 */
	public void setDispositions(Map<Date,String> dispositions) {
		this.dispositions = dispositions;
	}

	/**
	 * @return the dispositions
	 */
	public Map<Date,String> getDispositions() {
		return dispositions;
	}

	/**
	 * @return the refSource
	 */
	public String getRefSource()
	{
	    return refSource;
	}

	/**
	 * @param refSource the refSource to set
	 */
	public void setRefSource(String refSource)
	{
	    this.refSource = refSource;
	}

	/**
	 * @return the offenseList
	 */
	public String getOffenseList()
	{
	    return offenseList;
	}

	/**
	 * @param offenseList the offenseList to set
	 */
	public void setOffenseList(String offenseList)
	{
	    this.offenseList = offenseList;
	}

	/**
	 * @return the supervisionType
	 */
	public String getSupervisionType()
	{
	    return supervisionType;
	}

	/**
	 * @param supervisionType the supervisionType to set
	 */
	public void setSupervisionType(String supervisionType)
	{
	    this.supervisionType = supervisionType;
	}

	/**
	 * @return the jpo
	 */
	public String getJpo()
	{
	    return jpo;
	}

	/**
	 * @param jpo the jpo to set
	 */
	public void setJpo(String jpo)
	{
	    this.jpo = jpo;
	}

	/**
	 * @return the supervisionTypeDescr
	 */
	public String getSupervisionTypeDescr()
	{
	    return supervisionTypeDescr;
	}

	/**
	 * @param supervisionTypeDescr the supervisionTypeDescr to set
	 */
	public void setSupervisionTypeDescr(String supervisionTypeDescr)
	{
	    this.supervisionTypeDescr = supervisionTypeDescr;
	}

	/**
	 * @return the jpoDescr
	 */
	public String getJpoDescr()
	{
	    return jpoDescr;
	}

	/**
	 * @param jpoDescr the jpoDescr to set
	 */
	public void setJpoDescr(String jpoDescr)
	{
	    this.jpoDescr = jpoDescr;
	}

	/**
	 * @return the intakeDecisionDescr
	 */
	public String getIntakeDecisionDescr()
	{
	    return intakeDecisionDescr;
	}

	/**
	 * @param intakeDecisionDescr the intakeDecisionDescr to set
	 */
	public void setIntakeDecisionDescr(String intakeDecisionDescr)
	{
	    this.intakeDecisionDescr = intakeDecisionDescr;
	}

	/**
	 * @return the offenses
	 */
	public Collection<JuvenileReferralOffenseBean> getOffenses()
	{
	    return offenses;
	}

	/**
	 * @param offenses the offenses to set
	 */
	public void setOffenses(Collection<JuvenileReferralOffenseBean> offenses)
	{
	    this.offenses = offenses;
	}

	public String getCourtPetitionAllegation()
	{
	    return courtPetitionAllegation;
	}

	public void setCourtPetitionAllegation(String courtPetitionAllegation)
	{
	    this.courtPetitionAllegation = courtPetitionAllegation;
	}
	
	public String getTerminationDate()
	{
	    return terminationDate;
	}

	public void setTerminationDate(String TerminationDate)
	{
	    this.terminationDate = TerminationDate;
	}


}