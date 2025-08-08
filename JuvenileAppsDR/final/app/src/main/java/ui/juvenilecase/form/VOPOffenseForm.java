/*
 * NM Created on June 8, 2023
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;

import org.apache.struts.action.ActionForm;


public class VOPOffenseForm extends ActionForm
{
	private String juvenileNum;
	private String referralNum;
	private Date referralDate;
	private String referralDateStr;
	private String vopOffenseCode;
	private String vopOffenseCodeDesc;
	private String offenseCharge;
	private String offenseChargeDesc;
	private String offenseChargeDate;
	private String offenseChargeDateStr;
	private String offenseDate; //hold the JJS_MS_OFFENSE.offensedate for the VOPOffense Date in the db
	private int inCCountyOrigPetitionedRefNum;
	private int adultIndicator;
	private String adultIndicatorStr;
	private String locationIndicator;
	private String confirmMsg;
	private String errMessage;
	private String validateMsg;
	private String petitionNum;
	private String petitionNumVOP; //Added for US 170026, 170161
	private String petitionAllegation;
	private String petitionAllegationDesc;
	private Collection offensesVOPs;
	private Collection<PetitionResponseEvent> inCountyPetitions;
	private JuvenileProfileReferralListResponseEvent refForVop;
	private String selectedPetition;
	
	private Collection<JuvenileProfileReferralListResponseEvent> referralsVOP; //hold the referrals with VOP eligible Offenses
	private Collection existingVOPs; //hold records from JCVOP table
	private String selectedRef4VOP;  
	private String selectedSubSevType4VOP; //holds the selectedSubSevType for the referral for VOP
	private Collection<PetitionResponseEvent> petitionsForRef; //added for US 158623
	//offense search
	private List offenseResultList;
	private Collection codetableDataList;
	private String alphaCodeId;
	private String shortDesc;
	private String dpsCodeId;
	private String categoryId;
	private String selectedValue;//holds offense Search value 
	private long offenseCollectionSize;
	private boolean offensesAvailable;
	private String casefileId;
	private List<String>casefileIds;
	
	public void clear( )
	{
		this.juvenileNum = null;
		this.referralNum = null;
		this.setReferralDate(null);
		this.referralDateStr = null;
		this.confirmMsg = null;
		this.vopOffenseCode = null;
		this.vopOffenseCodeDesc = null;
		this.errMessage = "";
		this.offenseCharge = null;
		this.offenseChargeDesc = "";
		this.offenseChargeDate = null;
		this.offenseChargeDateStr = null;
		this.inCCountyOrigPetitionedRefNum = 0;
		this.adultIndicator = 0;
		this.locationIndicator = null;
		this.validateMsg = "";
		this.casefileId = null;
		this.casefileIds = new ArrayList<>();
	}


	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum()
	{
	    return juvenileNum;
	}


	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum)
	{
	    this.juvenileNum = juvenileNum;
	}


	/**
	 * @return the referralNum
	 */
	public String getReferralNum()
	{
	    return referralNum;
	}


	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum)
	{
	    this.referralNum = referralNum;
	}


	


	/**
	 * @return the referralDateStr
	 */
	public String getReferralDateStr()
	{
	    return referralDateStr;
	}


	/**
	 * @param referralDateStr the referralDateStr to set
	 */
	public void setReferralDateStr(String referralDateStr)
	{
	    this.referralDateStr = referralDateStr;
	}


	/**
	 * @return the vopOffenseCode
	 */
	public String getVopOffenseCode()
	{
	    return vopOffenseCode;
	}


	/**
	 * @param vopOffenseCode the vopOffenseCode to set
	 */
	public void setVopOffenseCode(String vopOffenseCode)
	{
	    this.vopOffenseCode = vopOffenseCode;
	}


	/**
	 * @return the vopOffenseCodeDesc
	 */
	public String getVopOffenseCodeDesc()
	{
	    return vopOffenseCodeDesc;
	}


	/**
	 * @param vopOffenseCodeDesc the vopOffenseCodeDesc to set
	 */
	public void setVopOffenseCodeDesc(String vopOffenseCodeDesc)
	{
	    this.vopOffenseCodeDesc = vopOffenseCodeDesc;
	}


	/**
	 * @return the offenseCharge
	 */
	public String getOffenseCharge()
	{
	    return offenseCharge;
	}


	/**
	 * @param offenseCharge the offenseCharge to set
	 */
	public void setOffenseCharge(String offenseCharge)
	{
	    this.offenseCharge = offenseCharge;
	}


	/**
	 * @return the offenseChargeDesc
	 */
	public String getOffenseChargeDesc()
	{
	    return offenseChargeDesc;
	}


	/**
	 * @param offenseChargeDesc the offenseChargeDesc to set
	 */
	public void setOffenseChargeDesc(String offenseChargeDesc)
	{
	    this.offenseChargeDesc = offenseChargeDesc;
	}



	/**
	 * @return the offenseChargeDateStr
	 */
	public String getOffenseChargeDateStr()
	{
	    return offenseChargeDateStr;
	}


	/**
	 * @param offenseChargeDateStr the offenseChargeDateStr to set
	 */
	public void setOffenseChargeDateStr(String offenseChargeDateStr)
	{
	    this.offenseChargeDateStr = offenseChargeDateStr;
	}


	/**
	 * @return the inCCountyOrigPetitionedRefNum
	 */
	public int getInCCountyOrigPetitionedRefNum()
	{
	    return inCCountyOrigPetitionedRefNum;
	}


	/**
	 * @param inCCountyOrigPetitionedRefNum the inCCountyOrigPetitionedRefNum to set
	 */
	public void setInCCountyOrigPetitionedRefNum(int inCCountyOrigPetitionedRefNum)
	{
	    this.inCCountyOrigPetitionedRefNum = inCCountyOrigPetitionedRefNum;
	}


	/**
	 * @return the adultIndicator
	 */
	public int getAdultIndicator()
	{
	    return adultIndicator;
	}


	/**
	 * @param adultIndicator the adultIndicator to set
	 */
	public void setAdultIndicator(int adultIndicator)
	{
	    this.adultIndicator = adultIndicator;
	}


	/**
	 * @return the locationIndicator
	 */
	public String getLocationIndicator()
	{
	    return locationIndicator;
	}


	/**
	 * @param locationIndicator the locationIndicator to set
	 */
	public void setLocationIndicator(String locationIndicator)
	{
	    this.locationIndicator = locationIndicator;
	}


	/**
	 * @return the confirmMsg
	 */
	public String getConfirmMsg()
	{
	    return confirmMsg;
	}


	/**
	 * @param confirmMsg the confirmMsg to set
	 */
	public void setConfirmMsg(String confirmMsg)
	{
	    this.confirmMsg = confirmMsg;
	}


	/**
	 * @return the errMessage
	 */
	public String getErrMessage()
	{
	    return errMessage;
	}


	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage)
	{
	    this.errMessage = errMessage;
	}


	/**
	 * @return the validateMsg
	 */
	public String getValidateMsg()
	{
	    return validateMsg;
	}


	/**
	 * @param validateMsg the validateMsg to set
	 */
	public void setValidateMsg(String validateMsg)
	{
	    this.validateMsg = validateMsg;
	}


	public Collection<JuvenileProfileReferralListResponseEvent> getReferralsVOP()
	{
	    return referralsVOP;
	}


	public void setReferralsVOP(Collection<JuvenileProfileReferralListResponseEvent> referralsVOP)
	{
	    this.referralsVOP = referralsVOP;
	}


	public Collection getExistingVOPs()
	{
	    return existingVOPs;
	}


	public void setExistingVOPs(Collection existingVOPs)
	{
	    this.existingVOPs = existingVOPs;
	}


	public Collection getOffensesVOPs()
	{
	    return offensesVOPs;
	}


	public void setOffensesVOPs(Collection offensesVOPs)
	{
	    this.offensesVOPs = offensesVOPs;
	}


	public Collection<PetitionResponseEvent> getInCountyPetitions()
	{
	    return inCountyPetitions;
	}


	public void setInCountyPetitions(Collection<PetitionResponseEvent> inCountyPetitions)
	{
	    this.inCountyPetitions = inCountyPetitions;
	}


	public String getPetitionNum()
	{
	    return petitionNum;
	}


	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}


	public String getPetitionAllegation()
	{
	    return petitionAllegation;
	}


	public void setPetitionAllegation(String petitionAllegation)
	{
	    this.petitionAllegation = petitionAllegation;
	}


	public JuvenileProfileReferralListResponseEvent getRefForVop()
	{
	    return refForVop;
	}


	public void setRefForVop(JuvenileProfileReferralListResponseEvent refForVop)
	{
	    this.refForVop = refForVop;
	}


	public String getSelectedPetition()
	{
	    return selectedPetition;
	}


	public void setSelectedPetition(String selectedPetition)
	{
	    this.selectedPetition = selectedPetition;
	}


	public String getOffenseChargeDate()
	{
	    return offenseChargeDate;
	}


	public void setOffenseChargeDate(String offenseChargeDate)
	{
	    this.offenseChargeDate = offenseChargeDate;
	}


	public String getSelectedRef4VOP()
	{
	    return selectedRef4VOP;
	}


	public void setSelectedRef4VOP(String selectedRef4VOP)
	{
	    this.selectedRef4VOP = selectedRef4VOP;
	}


	public Collection<PetitionResponseEvent> getPetitionsForRef()
	{
	    return petitionsForRef;
	}


	public void setPetitionsForRef(Collection<PetitionResponseEvent> petitionsForRef)
	{
	    this.petitionsForRef = petitionsForRef;
	}


	public List getOffenseResultList()
	{
	    return offenseResultList;
	}


	public void setOffenseResultList(List offenseResultList)
	{
	    this.offenseResultList = offenseResultList;
	}


	public Collection getCodetableDataList()
	{
	    return codetableDataList;
	}


	public void setCodetableDataList(Collection codetableDataList)
	{
	    this.codetableDataList = codetableDataList;
	}


	public String getAlphaCodeId()
	{
	    return alphaCodeId;
	}


	public void setAlphaCodeId(String alphaCodeId)
	{
	    this.alphaCodeId = alphaCodeId;
	}


	public String getShortDesc()
	{
	    return shortDesc;
	}


	public void setShortDesc(String shortDesc)
	{
	    this.shortDesc = shortDesc;
	}


	public String getDpsCodeId()
	{
	    return dpsCodeId;
	}


	public void setDpsCodeId(String dpsCodeId)
	{
	    this.dpsCodeId = dpsCodeId;
	}


	public String getCategoryId()
	{
	    return categoryId;
	}


	public void setCategoryId(String categoryId)
	{
	    this.categoryId = categoryId;
	}


	public String getSelectedValue()
	{
	    return selectedValue;
	}


	public void setSelectedValue(String selectedValue)
	{
	    this.selectedValue = selectedValue;
	}

	    /**
	     * @return offensesAvailable
	     */
	    public boolean isOffensesAvailable()
	    {
		return offensesAvailable;
	    }

	    /**
	     * @param offensesAvailable
	     */
	    public void setOffensesAvailable(boolean offensesAvailable)
	    {
		this.offensesAvailable = offensesAvailable;
	    }
	   /**
	     * @return offenseCollectionSize
	     */
	    public long getOffenseCollectionSize()
	    {

		if (isOffensesAvailable())
		{
		    setOffenseCollectionSize(offensesVOPs.size() - 1);
		}
		else
		{
		    setOffenseCollectionSize(0);
		}

		return offenseCollectionSize;
	    }

	    /**
	     * @param offenseCollectionSize
	     */
	    public void setOffenseCollectionSize(long offenseCollectionSize)
	    {
		this.offenseCollectionSize = offenseCollectionSize;
	    }


	    public String getAdultIndicatorStr()
	    {
	        return adultIndicatorStr;
	    }


	    public void setAdultIndicatorStr(String adultIndicatorStr)
	    {
	        this.adultIndicatorStr = adultIndicatorStr;
	    }


	    public String getSelectedSubSevType4VOP()
	    {
		return selectedSubSevType4VOP;
	    }


	    public void setSelectedSubSevType4VOP(String selectedSubSevType4VOP)
	    {
		this.selectedSubSevType4VOP = selectedSubSevType4VOP;
	    }


	    public String getOffenseDate()
	    {
		return offenseDate;
	    }


	    public void setOffenseDate(String offenseDate)
	    {
		this.offenseDate = offenseDate;
	    }


	    public String getPetitionAllegationDesc()
	    {
	        return petitionAllegationDesc;
	    }


	    public void setPetitionAllegationDesc(String petitionAllegationDesc)
	    {
	        this.petitionAllegationDesc = petitionAllegationDesc;
	    }


	    public Date getReferralDate()
	    {
		return referralDate;
	    }


	    public void setReferralDate(Date referralDate)
	    {
		this.referralDate = referralDate;
	    }


	    public String getPetitionNumVOP()
	    {
	        return petitionNumVOP;
	    }


	    public void setPetitionNumVOP(String petitionNumVOP)
	    {
	        this.petitionNumVOP = petitionNumVOP;
	    }


	    public String getCasefileId()
	    {
	        return casefileId;
	    }


	    public void setCasefileId(String casefileId)
	    {
	        this.casefileId = casefileId;
	    }


	    public List<String> getCasefileIds()
	    {
	        return casefileIds;
	    }


	    public void setCasefileIds(List<String> casefileIds)
	    {
	        this.casefileIds = casefileIds;
	    }
	    
	    

	    


	   

}