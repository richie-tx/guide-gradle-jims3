package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class JuvenileOffenderTrackingChargeResponseEvent extends ResponseEvent implements ICode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chargeSeqNum;
	private String court;
	private String courtId;
	private String daLogNum;
	private String offense;
	private String offenseCodeId;
	private Date offenseDate;
	private String petitionNum;
	private String cjisNum;
	private String cjisSuffixNum;
	private String lawEnforcementAgency;
	private Double totalPropertyLossAmount;
	private String ncicOffenseCode;
	
	private String alcoholOrDrugInfluence;
	private boolean gangRelated;
	private boolean weaponUsed;
	private String offenseDegree;
	private String levelOfOffense;
	private String weaponType;
	private String weaponTypeId; //added for user-story 11449
	private String charge;
	private String gangIndicator; //bug 26194 - renamed to gangIndicator as warrants already took the name gang related.
	private String prostIndicator;
	private String rejIndicator;
	private String chargeId;
	private String recType;

	

	/**
	 * @return
	 */
	public String getChargeSeqNum()
	{
		return chargeSeqNum;
	}

	/**
	 * @return
	 */
	public String getCourt()
	{
		return court;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @return
	 */
	public String getDaLogNum()
	{
		return daLogNum;
	}

	/**
	 * @return
	 */
	public String getOffense()
	{
		return offense;
	}

	/**
	 * @return
	 */
	public String getOffenseCodeId()
	{
		return offenseCodeId;
	}

	/**
	 * @return
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}

	/**
	 * @param chargeSeqNum
	 */
	public void setChargeSeqNum(String chargeSeqNum)
	{
		this.chargeSeqNum = chargeSeqNum;
	}

	/**
	 * @param court
	 */
	public void setCourt(String court)
	{
		this.court = court;
	}

	/**
	 * @param courtId
	 */
	public void setCourtId(String courtId)
	{
		this.courtId = courtId;
	}

	/**
	 * @param daLogNum
	 */
	public void setDaLogNum(String daLogNum)
	{
		this.daLogNum = daLogNum;
	}

	/**
	 * @param offense
	 */
	public void setOffense(String chargeCode)
	{
		this.offense = chargeCode;
	}

	/**
	 * @param offenseCodeId
	 */
	public void setOffenseCodeId(String chargeCodeId)
	{
		this.offenseCodeId = chargeCodeId;
	}

	/**
	 * @param petitionNum
	 */
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}
	/**
	 * @return cjisNum
	 */
	public String getCJISNum()
	{
		return cjisNum;
	}

	/**
	 * @return cjisSuffixNum
	 */
	public String getCJISSuffixNum()
	{
		return cjisSuffixNum;
	}

	/**
	 * @return lawEnforcementAgency
	 */
	public String getLawEnforcementAgency()
	{
		return lawEnforcementAgency;
	}

	/**
	 * @return totalPropertyLossAmount
	 */
	public Double getTotalPropertyLossAmount()
	{
		return totalPropertyLossAmount;
	}

	/**
	 * @param string
	 */
	public void setCJISNum(String string)
	{
		cjisNum = string;
	}

	/**
	 * @param string
	 */
	public void setCJISSuffixNum(String string)
	{
		cjisSuffixNum = string;
	}

	/**
	 * @param string
	 */
	public void setLawEnforcementAgency(String string)
	{
		lawEnforcementAgency = string;
	}

	/**
	 * @param string
	 */
	public void setTotalPropertyLossAmount(Double value)
	{
		totalPropertyLossAmount = value;
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode()
	{
		return this.getChargeSeqNum();
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getDescription()
	 */
	public String getDescription()
	{
		return this.getOffense();
	}

	/**
	 * @return
	 */
	public String getNcicOffenseCode()
	{
		return ncicOffenseCode;
	}

	/**
	 * @param string
	 */
	public void setNcicOffenseCode(String string)
	{
		ncicOffenseCode = string;
	}

	/**
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return Returns the alcoholOrDrugInfluence.
	 */
	public String getAlcoholOrDrugInfluence() {
		return alcoholOrDrugInfluence;
	}
	/**
	 * @param alcoholOrDrugInfluence The alcoholOrDrugInfluence to set.
	 */
	public void setAlcoholOrDrugInfluence(String alcoholOrDrugInfluence) {
		this.alcoholOrDrugInfluence = alcoholOrDrugInfluence;
	}
	/**
	 * @return Returns the gangRelated.
	 */
	public boolean isGangRelated() {
		return gangRelated;
	}
	/**
	 * @param gangRelated The gangRelated to set.
	 */
	public void setGangRelated(boolean gangRelated) {
		this.gangRelated = gangRelated;
	}
	/**
	 * @return Returns the weaponUsed.
	 */
	public boolean isWeaponUsed() {
		return weaponUsed;
	}
	/**
	 * @param weaponUsed The weaponUsed to set.
	 */
	public void setWeaponUsed(boolean weaponUsed) {
		this.weaponUsed = weaponUsed;
	}
	/**
	 * @return Returns the offenseDegree.
	 */
	public String getOffenseDegree() {
		return offenseDegree;
	}
	/**
	 * @param offenseDegree The offenseDegree to set.
	 */
	public void setOffenseDegree(String offenseDegree) {
		this.offenseDegree = offenseDegree;
	}
	/**
	 * @return Returns the levelOfOffense.
	 */
	public String getLevelOfOffense() {
		return levelOfOffense;
	}
	/**
	 * @param levelOfOffense The levelOfOffense to set.
	 */
	public void setLevelOfOffense(String levelOfOffense) {
		this.levelOfOffense = levelOfOffense;
	}
	/**
	 * @return Returns the weaponType.
	 */
	public String getWeaponType() {
		return weaponType;
	}
	/**
	 * @param weaponType The weaponType to set.
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	/**
	 * @return Returns the charge.
	 */
	public String getCharge() {
		return charge;
	}
	/**
	 * @param charge The charge to set.
	 */
	public void setCharge(String charge) {
		this.charge = charge;
	}

	/**
	 * @return weaponTypeId
	 */
	public String getWeaponTypeId() {
		return weaponTypeId;
	}

	/**
	 * @param weaponTypeId
	 */
	public void setWeaponTypeId(String weaponTypeId) {
		this.weaponTypeId = weaponTypeId;
	}

	/**
	 * @return gangRelated
	 */
	public String getGangIndicator() {
		return gangIndicator;
	}

	/**
	 * @param gangRelated
	 */
	public void setGangIndicator(String gangIndicator) {
		this.gangIndicator = gangIndicator;
	}
	//task 149503
	public String getProstIndicator()
	{
	    return prostIndicator;
	}

	public void setProstIndicator(String prostIndicator)
	{
	    this.prostIndicator = prostIndicator;
	}
	//
	public String getChargeId()
	{
	    return chargeId;
	}

	public void setChargeId(String chargeId)
	{
	    this.chargeId = chargeId;
	}
	public String getRejIndicator()
	{
	    return rejIndicator;
	}

	public void setRejIndicator(String rejIndicator)
	{
	    this.rejIndicator = rejIndicator;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	
	
}