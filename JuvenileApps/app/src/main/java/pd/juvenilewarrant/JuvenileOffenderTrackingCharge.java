package pd.juvenilewarrant;

import java.util.Date;
import java.util.Iterator;

import messaging.codetable.reply.ICode;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.OffenseCode;
import pd.contact.agency.Department;

/**
 * @author ryoung
 */
public class JuvenileOffenderTrackingCharge extends PersistentObject
{
    private String cJISNum;

    private String cJISSuffixNum;

    private Double totalPropertyLossAmount;

    /**
     * Properties for lawEnforcementAgency
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Department lawEnforcementAgency = null;

    private String lawEnforcementAgencyId;

    private String daLogNum;

    private String transactionNum;
    
    private String courtId;

    /**
     * Properties for court
     * 
     * @referencedType pd.codetable.criminal.JuvenileCourt
     * @detailerDoNotGenerate true
     */
    private JuvenileCourt court = null;

    private String petitionNum;

    /**
     * Properties for offenseCode
     * 
     * @referencedType pd.codetable.criminal.JuvenileOffenseCode
     * @detailerDoNotGenerate true
     */
    private OffenseCode offenseCode = null;

    private String chargeSeqNum;
    
    private Date offenseDate;
    
    private int offenseIDate;
    
    private String chargeDescription;

    private String ncicOffenseCode;

    /**
     * Properties for Rights of Parents
     * 
     * @detailerDoNotGenerate true
     */
    private String weaponTypeId;

    private Code weaponType;

    private String unlistedWeapon;

    private String drugInfluence;

    private String caseTypeGroup;
    
    private String recType;
    
    /**
	 * Properties for Assigned Referrals
	 */
	private boolean alcoholInfluence;
	
	private String gangRelated; //bug 26194
	
	private String prostInd;
	private String rejIndicator;
	
    

    

    /**
     * @roseuid 41C1AFBE02AF
     */
    public JuvenileOffenderTrackingCharge()
    {
    }

    /**
     * Access method for the chargeSeqNum property.
     * 
     * @return the current value of the chargeSeqNum property
     */
    public String getChargeSeqNum()
    {
        fetch();
        return chargeSeqNum;
    }

    public PetitionResponseEvent valueObject()
    {
        PetitionResponseEvent petitionEvent = new PetitionResponseEvent();
        petitionEvent.setTopic(PDJuvenileWarrantConstants.PETITION_EVENT_TOPIC);

        petitionEvent.setOffenseCodeId(this.getOffenseCodeId());

        if ((this.getCourtId() != null) && (this.getCourtId().equals("") == false))
        {
            ICode courtCode = this.getCourt();
            if (courtCode != null)
            {
                petitionEvent.setCourtId(courtCode.getCode());
                petitionEvent.setCourt(courtCode.getDescription());
            }
            else
            {
                petitionEvent.setCourt("Court Not Found: " + this.getCourtId());
            }
        }
        if (this.ncicOffenseCode == null || this.ncicOffenseCode.equals(""))
        {
            petitionEvent.setOffense(this.getChargeDescription());
            petitionEvent.setOffenseCodeId(this.getOffenseCodeId());
        }
        else
        {
            OffenseCode offenseCode = this.getOffenseCode();
            if (offenseCode != null)
            {
                petitionEvent.setLevel(offenseCode.getLevel());
                petitionEvent.setDegree(offenseCode.getDegree());
            }
            petitionEvent.setOffense(this.getChargeDescription());
        }
        petitionEvent.setSequenceNum(this.getChargeSeqNum());
        petitionEvent.setDaLogNum(this.getDaLogNum());
        petitionEvent.setPetitionNum(this.getPetitionNum());
        petitionEvent.setCJISNum(this.getCJISNum());
        petitionEvent.setCJISSuffixNum(this.getCJISSuffixNum());
        petitionEvent.setTotalPropertyLossAmount(this.getTotalPropertyLossAmount());
        petitionEvent.setOffenseDate( DateUtil.IntToDate(this.getOffenseIDate(), DateUtil.DATE_FMT_2));
        Department dept = this.getLawEnforcementAgency();
        if (dept != null)
        {
            petitionEvent.setLawEnforcementAgency(dept.getDepartmentName());
        }
        petitionEvent.setNcicOffenseCode(this.getOffenseCodeId());

        return petitionEvent;
    }

    /**
     * Gets referenced type pd.codetable.criminal.JuvenileCourt
     * 
     * @return JuvenileCourt court
     */
    public JuvenileCourt getCourt()
    {
        initCourt();
        return court;
    }

    /**
     * Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
     * 
     * @return String courtId
     */
    public String getCourtId()
    {
        fetch();
        return courtId;
    }

    /**
     * Access method for the daLogNum property.
     * 
     * @return String dalogNum
     */
    public String getDaLogNum()
    {
        fetch();
        return daLogNum;
    }

    /**
     * Gets referenced type pd.codetable.criminal.JuvenileOffenseCode
     * 
     * @return JuvenileOffenseCode offenseCode
     */
    public OffenseCode getOffenseCode()
    {
        initOffenseCode();
        return offenseCode;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.criminal.JuvenileOffenseCode
     * 
     * @return String offenseCodeId
     */
    public String getOffenseCodeId()
    {
        fetch();
        return this.ncicOffenseCode;
    }

    /**
     * Access method for the petitionNum property.
     * 
     * @return String petitionNum
     */
    public String getPetitionNum()
    {
        fetch();
        return petitionNum;
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.criminal.JuvenileCourt
     */
    private void initCourt()
    {
        if (court == null)
        {
            court = (JuvenileCourt) new mojo.km.persistence.Reference(courtId,
                    JuvenileCourt.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.criminal.JuvenileOffenseCode
     */
    private void initOffenseCode()
    {
        if (offenseCode == null)
        {
            offenseCode = (OffenseCode) new mojo.km.persistence.Reference(this.ncicOffenseCode,
                    OffenseCode.class).getObject();
        }
    }

    /**
     * Sets the value of the chargeSeqNum property.
     * 
     * @param aChargeSeqNum
     *            the new value of the chargeSeqNum property
     */
    public void setChargeSeqNum(String aChargeSeqNum)
    {
        if (this.chargeSeqNum == null || !this.chargeSeqNum.equals(aChargeSeqNum))
        {
            markModified();
        }
        chargeSeqNum = aChargeSeqNum;
    }

    /**
     * set the type reference for class member court
     * 
     * @param court
     */
    public void setCourt(JuvenileCourt acourt)
    {
        if (this.court == null || !this.court.equals(acourt))
        {
            markModified();
        }
        setCourtId("" + acourt.getOID());
        this.court = (JuvenileCourt) new mojo.km.persistence.Reference(acourt).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
     * 
     * @param courtId
     */
    public void setCourtId(String acourtId)
    {
        if (this.courtId == null || !this.courtId.equals(acourtId))
        {
            markModified();
        }
        court = null;
        this.courtId = acourtId;
    }

    /**
     * Sets the value of the daLogNum property.
     * 
     * @param aDaLogNum
     *            the new value of the daLogNum property
     */
    public void setDaLogNum(String aDaLogNum)
    {
        if (this.daLogNum == null || !this.daLogNum.equals(aDaLogNum))
        {
            markModified();
        }
        daLogNum = aDaLogNum;
    }

    /**
     * set the type reference for class member offenseCode
     * 
     * @param offenseCode
     */
    public void setOffenseCode(pd.codetable.criminal.JuvenileOffenseCode aoffenseCode)
    {
        if (this.offenseCode == null || !this.offenseCode.equals(aoffenseCode))
        {
            markModified();
        }
        setOffenseCodeId("" + aoffenseCode.getOID());
        this.offenseCode = (OffenseCode) new mojo.km.persistence.Reference(aoffenseCode)
                .getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.criminal.JuvenileOffenseCode
     * 
     * @param offenseCodeId
     */
    public void setOffenseCodeId(String aNcicOffenseCode)
    {
        if (this.ncicOffenseCode == null || !this.ncicOffenseCode.equals(aNcicOffenseCode))
        {
            markModified();
        }
        offenseCode = null;
        this.ncicOffenseCode = aNcicOffenseCode;
    }

    /**
     * Sets the value of the petitionNum property.
     * 
     * @param aPetitionNum
     *            the new value of the petitionNum property
     */
    public void setPetitionNum(String aPetitionNum)
    {
        if (this.petitionNum == null || !this.petitionNum.equals(aPetitionNum))
        {
            markModified();
        }
        petitionNum = aPetitionNum;
    }

    /**
     * @return Iterator JuvenileOffenderTrackingCharge
     * @param event
     * @roseuid 4107B06C01EB
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, JuvenileOffenderTrackingCharge.class);
    }

    /**
     * @return JuvenileOffenderTrackingCharge charge
     * @param chargeSeqNum
     */
    static public JuvenileOffenderTrackingCharge find(String chargeSeqNum)
    {
        JuvenileOffenderTrackingCharge charge = null;
        IHome home = new Home();
        charge = (JuvenileOffenderTrackingCharge) home.find(chargeSeqNum, JuvenileOffenderTrackingCharge.class);
        return charge;
    }

    /**
     * 
     * @return cjis
     */
    public String getCJISNum()
    {
        fetch();
        return cJISNum;
    }

    /**
     * 
     * @param aCJISNum
     */
    public void setCJISNum(String aCJISNum)
    {
        if (this.cJISNum == null || !this.cJISNum.equals(aCJISNum))
        {
            markModified();
        }
        cJISNum = aCJISNum;
    }

    /**
     * 
     * @return CJISSuffix
     */
    public String getCJISSuffixNum()
    {
        fetch();
        return cJISSuffixNum;
    }

    /**
     * 
     * @param aCJISSuffix
     */
    public void setCJISSuffixNum(String aCJISSuffixNum)
    {
        if (this.cJISSuffixNum == null || !this.cJISSuffixNum.equals(aCJISSuffixNum))
        {
            markModified();
        }
        cJISSuffixNum = aCJISSuffixNum;
    }

    /**
     * 
     * @return agency
     */
    public String getLawEnforcementAgencyId()
    {
        fetch();
        return lawEnforcementAgencyId;
    }

    /**
     * 
     * @param aAgency
     */
    public void setLawEnforcementAgencyId(String aLawEnforcementAgency)
    {
        if (this.lawEnforcementAgencyId == null || !this.lawEnforcementAgencyId.equals(aLawEnforcementAgency))
        {
            markModified();
        }
        lawEnforcementAgencyId = aLawEnforcementAgency;
    }

    /**
     * Gets referenced type pd.contact.agency
     * 
     * @return Agency
     * @roseuid 4107DFB60134
     */
    public Department getLawEnforcementAgency()
    {
        fetch();
        initLawEnforcementAgency();
        return lawEnforcementAgency;
    }

    /**
     * Initialize class relationship to class pd.contact.agency.Department
     */
    private void initLawEnforcementAgency()
    {
        if (lawEnforcementAgency == null)
        {
            lawEnforcementAgency = Department.find(lawEnforcementAgencyId); //87191//(Department) new mojo.km.persistence.Reference(lawEnforcementAgencyId,
                    //pd.contact.agency.Department.class).getObject();
        }
    }

    /**
     * 
     * @return totalPropertyLossAmount
     */
    public Double getTotalPropertyLossAmount()
    {
        fetch();
        return totalPropertyLossAmount;
    }

    /**
     * 
     * @param aTotalPropertyLossAmount
     */
    public void setTotalPropertyLossAmount(Double aTotalPropertyLossAmount)
    {
        if (this.totalPropertyLossAmount == null || !this.totalPropertyLossAmount.equals(aTotalPropertyLossAmount))
        {
            markModified();
        }
        totalPropertyLossAmount = aTotalPropertyLossAmount;
    }

    /**
     * @return
     */
    public String getChargeDescription()
    {
        fetch();
        return chargeDescription;
    }

    public Date getOffenseDate()
    {
	fetch();
        return offenseDate;
    }

    public void setOffenseDate(Date aOffenseDate)
    {
	if (this.offenseDate == null || !this.offenseDate.equals( aOffenseDate ))
        {
            markModified();
        }
        this.offenseDate = aOffenseDate;
    }

    /**
     * @param string
     */
    public void setChargeDescription(String string)
    {
        if (this.chargeDescription == null || !this.chargeDescription.equals(string))
        {
            markModified();
        }
        chargeDescription = string;
    }

    /**
     * @return Returns the drugInfluence.
     */
    public String getDrugInfluence()
    {
        fetch();
        return drugInfluence;
    }

    /**
     * @param drugInfluence
     *            The drugInfluence to set.
     */
    public void setDrugInfluence(String string)
    {
        if (this.drugInfluence == null || !this.drugInfluence.equals(drugInfluence))
        {
            markModified();
        }
        drugInfluence = string;
    }

    /**
     * @return Returns the unlistedWeapon.
     */
    public String getUnlistedWeapon()
    {
        fetch();
        return unlistedWeapon;
    }

    /**
     * @param unlistedWeapon
     *            The unlistedWeapon to set.
     */
    public void setUnlistedWeapon(String string)
    {
        if (this.unlistedWeapon == null || !this.unlistedWeapon.equals(string))
        {
            markModified();
        }
        unlistedWeapon = string;
    }

    /**
     * @return Returns the weaponTypeId.
     */
    public String getWeaponTypeId()
    {
        fetch();
        return weaponTypeId;
    }

    /**
     *  
     */
    public void initWeaponType()
    {
        if (weaponType == null)
        {
            weaponType = (Code) new mojo.km.persistence.Reference(this.weaponTypeId,
                    Code.class, PDCodeTableConstants.WEAPON_TYPE).getObject();
        }
    }

    /**
     * @return Returns the weaponTypeId.
     */
    public Code getWeaponType()
    {
        initWeaponType();
        return weaponType;
    }

    /**
     * @param weaponTypeId
     *            The weaponTypeId to set.
     */
    public void setWeaponTypeId(String aWeaponTypeId)
    {
        if (this.weaponTypeId == null || !this.weaponTypeId.equals(aWeaponTypeId))
        {
            markModified();
        }
        weaponType = null;
        this.weaponTypeId = aWeaponTypeId;
    }

    /**
     * @param weaponTypeId
     *            The weaponTypeId to set.
     */
    public void setWeaponType(Code aWeaponType)
    {
        setWeaponTypeId(aWeaponType.getCode());
    }

    /**
     * @return Returns the caseTypeGroup.
     */
    public String getCaseTypeGroup()
    {
        fetch();
        return caseTypeGroup;
    }

    /**
     * @param caseTypeGroup
     *            The caseTypeGroup to set.
     */
    public void setCaseTypeGroup(String aCaseTypeGroup)
    {
        if (this.caseTypeGroup == null || !this.caseTypeGroup.equals(aCaseTypeGroup))
        {
            markModified();
        }
        this.caseTypeGroup = aCaseTypeGroup;
    }

	/**
	 * @return Returns the alcoholInfluence.
	 */
	public boolean isAlcoholInfluence() {
		fetch();
		return alcoholInfluence;
	}
	/**
	 * @param alcoholInfluence The alcoholInfluence to set.
	 */
	public void setAlcoholInfluence(boolean alcoholInfluence) {
		if (this.alcoholInfluence != alcoholInfluence)
        {
            markModified();
        }
		this.alcoholInfluence = alcoholInfluence;
	}
	
	public String getTransactionNum() {
		fetch();
		return transactionNum;
	}

	public void setTransactionNum(String aTransactionNum) {
		if (this.transactionNum != aTransactionNum)
        {
            markModified();
        }
		this.transactionNum = aTransactionNum;
	}

	public String getGangRelated() {
		fetch();
		return gangRelated;
	}

	public void setGangRelated(String gangRelated) {
		if (this.gangRelated != gangRelated)
        {
            markModified();
        }
		this.gangRelated = gangRelated;
	}

	public int getOffenseIDate()
	{
	    fetch();
	    return offenseIDate;
	}

	public void setOffenseIDate(int offenseIDate)
	{
	    this.offenseIDate = offenseIDate;
	}
	//task 149503
	public String getProstInd()
	{
	    fetch();
	    return prostInd;
	}

	public void setProstInd(String prostInd)
	{
	    if (this.prostInd != prostInd)
	        {
	            markModified();
	        }
	    this.prostInd = prostInd;
	}
	//
	public String getRejIndicator()
	{
	    fetch();
	    return rejIndicator;
	}

	public void setRejIndicator(String rejIndicator)
	{
	    if (this.rejIndicator != rejIndicator)
	        {
	            markModified();
	        }
	    this.rejIndicator = rejIndicator;
	}

	public String getRecType()
	{
	    fetch();
	    return recType;
	}

	public void setRecType(String recType)
	{
	    if ( this.recType == null
		    || !this.recType.equals(recType) ){
		markModified();
	    }
	    this.recType = recType;
	}
	
	
	
}
