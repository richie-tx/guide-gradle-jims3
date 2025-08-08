/*
 * Created on May 31, 2023
 */
package messaging.juvenilecase.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;


public class JuvenileReferralVOPResponseEvent extends ResponseEvent 
{
	private String juvenileNum;
	private String referralNum;
	private Date referralDate;
	private String referralDateStr;
	private String vopOffenseCode;
	private String vopOffenseCodeDesc;
	private String offenseCharge;
	private String offenseChargeDesc;
	private Date offenseChargeDate;
	private String offenseChargeDateStr;
	private int inCCountyOrigPetitionedRefNum;
	private int adultIndicator;
	private String locationIndicator;
	private String adultIndicatorStr; //added for BUG 163195
	private Collection offenses; //added for US 163617
	private long offenseCollectionSizeJCVOP;//to add offenses from JJS_MS_Offense to the JCVOP info US 163617
	private boolean offensesAvailableJCVOP; //to add offenses from JJS_MS_Offense to the JCVOP info for US 163617
	private String OID;
	private String vopOffensePetitionNum; //Added for US 170026, 170161
	private String petitionNum; //Added for US 170026, 170161
	
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public Date getReferralDate()
	{
	    return referralDate;
	}
	public void setReferralDate(Date referralDate)
	{
	    this.referralDate = referralDate;
	}
	public String getReferralDateStr()
	{
	    return referralDateStr;
	}
	public void setReferralDateStr(String referralDateStr)
	{
	    this.referralDateStr = referralDateStr;
	}
	public String getVopOffenseCode()
	{
	    return vopOffenseCode;
	}
	public void setVopOffenseCode(String vopOffenseCode)
	{
	    this.vopOffenseCode = vopOffenseCode;
	}
	public String getVopOffenseCodeDesc()
	{
	    return vopOffenseCodeDesc;
	}
	public void setVopOffenseCodeDesc(String vopOffenseCodeDesc)
	{
	    this.vopOffenseCodeDesc = vopOffenseCodeDesc;
	}
	public String getOffenseCharge()
	{
	    return offenseCharge;
	}
	public void setOffenseCharge(String offenseCharge)
	{
	    this.offenseCharge = offenseCharge;
	}
	public String getOffenseChargeDesc()
	{
	    return offenseChargeDesc;
	}
	public void setOffenseChargeDesc(String offenseChargeDesc)
	{
	    this.offenseChargeDesc = offenseChargeDesc;
	}
	public Date getOffenseChargeDate()
	{
	    return offenseChargeDate;
	}
	public void setOffenseChargeDate(Date offenseChargeDate)
	{
	    this.offenseChargeDate = offenseChargeDate;
	}
	public String getOffenseChargeDateStr()
	{
	    return offenseChargeDateStr;
	}
	public void setOffenseChargeDateStr(String offenseChargeDateStr)
	{
	    this.offenseChargeDateStr = offenseChargeDateStr;
	}
	public int getInCCountyOrigPetitionedRefNum()
	{
	    return inCCountyOrigPetitionedRefNum;
	}
	public void setInCCountyOrigPetitionedRefNum(int inCCountyOrigPetitionedRefNum)
	{
	    this.inCCountyOrigPetitionedRefNum = inCCountyOrigPetitionedRefNum;
	}
	public int getAdultIndicator()
	{
	    return adultIndicator;
	}
	public void setAdultIndicator(int adultIndicator)
	{
	    this.adultIndicator = adultIndicator;
	}
	public String getLocationIndicator()
	{
	    return locationIndicator;
	}
	public void setLocationIndicator(String locationIndicator)
	{
	    this.locationIndicator = locationIndicator;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
	public String getAdultIndicatorStr()
	{
	    return adultIndicatorStr;
	}
	
	public void setAdultIndicatorStr(String adultIndicatorStr)
	{
	    this.adultIndicatorStr = adultIndicatorStr;
	    
	}
	public Collection getOffenses()
	{
	    return offenses;
	}
	public void setOffenses(Collection offenses)
	{
	    this.offenses = offenses;
	}
	public long getOffenseCollectionSizeJCVOP()
	{
	if (isOffensesAvailableJCVOP())
	{
	    setOffenseCollectionSizeJCVOP(offenses.size() - 1);
	}
	else
	{
	    setOffenseCollectionSizeJCVOP(0);
	}

	return offenseCollectionSizeJCVOP;
  
	}
	public void setOffenseCollectionSizeJCVOP(long offenseCollectionSizeJCVOP)
	{
	    this.offenseCollectionSizeJCVOP = offenseCollectionSizeJCVOP;
	}
	public boolean isOffensesAvailableJCVOP()
	{
	    return offensesAvailableJCVOP;
	}
	public void setOffensesAvailableJCVOP(boolean offensesAvailableJCVOP)
	{
	    this.offensesAvailableJCVOP = offensesAvailableJCVOP;
	}
	public String getOID()
	{
	    return OID;
	}
	public void setOID(String oID)
	{
	    OID = oID;
	}
	public String getVopOffensePetitionNum()
	{
	    return vopOffensePetitionNum;
	}
	public void setVopOffensePetitionNum(String vopOffensePetitionNum)
	{
	    this.vopOffensePetitionNum = vopOffensePetitionNum;
	}
	public String getPetitionNum()
	{
	    return petitionNum;
	}
	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}
	
	
}