package pd.juvenilewarrant;

import java.util.Date;
import java.util.Iterator;
import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.OffenseCode;

/**
 * @author dnikolis
 */
public class Charge extends PersistentObject
{
    private String offenseCodeId;

    private String courtId;

    private String sequenceNum;

    private Date offenseDate;

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
    private JuvenileOffenseCode juvOffenseCode = null;

    private OffenseCode offenseCode = null;

    private String referralNum;

    private String warrantNum;

    private String chargeDescription;

    /**
     * @roseuid 41C1AFAB02EE
     */
    public Charge()
    {
    }

    public ChargeResponseEvent valueObject(boolean isJJS)
    {
        ChargeResponseEvent response = new ChargeResponseEvent();
        response.setTopic(PDJuvenileWarrantConstants.CHARGE_EVENT_TOPIC);
        response.setChargeId(response.getChargeId());

        response.setSequenceNum(this.getSequenceNum());

        if (this.getCourtId() != null && this.getCourtId().equals("") == false)
        {
            JuvenileCourt courtCode = this.getCourt();
            response.setCourtId(courtCode.getCode());
            response.setCourt(courtCode.getDescription());
        }

        if (isJJS == true && this.getOffenseCodeId() != null && this.getOffenseCodeId().equals("") == false)
        {
            JuvenileOffenseCode juvOffCode = this.getJuvOffenseCode();
            if (juvOffCode != null)
            {
                response.setOffenseCodeId(juvOffCode.getOffenseCode());
                response.setOffense(juvOffCode.getLongDescription());
                response.setDegree(juvOffCode.getDegree());
                response.setLevel(juvOffCode.getLevel());
            }

        }
        else
        {
            OffenseCode offCode = this.getOffenseCode();
            if (offCode != null)
            {
                response.setOffenseCodeId(offCode.getOffenseCodeId());
                response.setOffense(offCode.getDescription());
                response.setDegree(offCode.getDegree());
                response.setLevel(offCode.getLevel());

            }
        }
        response.setOffenseDate(this.getOffenseDate());
        response.setPetitionNum(this.getPetitionNum());

        return response;
    }

    /**
     * @return
     */
    private OffenseCode getOffenseCode()
    {
        fetch();
        initOffenseCode();
        return offenseCode;
    }

    /**
     *  
     */
    private void initOffenseCode()
    {
        if (offenseCode == null)
        {
            offenseCode = (OffenseCode) new mojo.km.persistence.Reference(offenseCodeId,
                    OffenseCode.class).getObject();
        }

    }

    /**
     * Access method for the chargeId property.
     * 
     * @return the current value of the chargeId property
     */
    public String getChargeId()
    {
        return (String) getOID();
    }

    /**
     * Gets referenced type pd.codetable.criminal.JuvenileCourt
     * 
     * @return court
     */
    public JuvenileCourt getCourt()
    {
        fetch();
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
     * Gets referenced type pd.codetable.criminal.JuvenileOffenseCode
     * 
     * @return offenseCode
     */
    public JuvenileOffenseCode getJuvOffenseCode()
    {
        fetch();
        initJuvOffenseCode();
        return juvOffenseCode;
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
        return offenseCodeId;
    }

    /**
     * @return Returns the offenseDate.
     */
    public Date getOffenseDate()
    {
        return offenseDate;
    }

    /**
     * Access method for the petitionNum property.
     * 
     * @return the current value of the petitionNum property
     */
    public String getPetitionNum()
    {
        fetch();
        return petitionNum;
    }

    /**
     * Access method for the warrantNum property.
     * 
     * @return the current value of the warrantNum property
     */
    public String getWarrantNum()
    {
        fetch();
        return warrantNum;
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
    private void initJuvOffenseCode()
    {
        if (juvOffenseCode == null)
        {
            juvOffenseCode = JuvenileOffenseCode.find("offenseCode",offenseCodeId);
        	    
        }
    }

    /**
     * Sets the value of the chargeId property.
     * 
     * @param aChargeId
     *            the new value of the chargeId property
     */
    public void setChargeId(String aChargeId)
    {
        super.setOID(aChargeId);
    }

    /**
     * set the type reference for class member court
     */
    public void setCourt(JuvenileCourt acourt)
    {
        if (this.court == null || !this.court.equals(acourt))
        {
            markModified();
        }
        setCourtId((String) acourt.getOID());
        this.court = (JuvenileCourt) new mojo.km.persistence.Reference(acourt).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
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
     * set the type reference for class member offenseCode
     */
    public void setOffenseCode(JuvenileOffenseCode aoffenseCode)
    {
        if (this.juvOffenseCode == null || !this.juvOffenseCode.equals(aoffenseCode))
        {
            markModified();
        }
        setOffenseCodeId((String) aoffenseCode.getOID());
        this.juvOffenseCode = (JuvenileOffenseCode) new mojo.km.persistence.Reference(
                aoffenseCode).getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.criminal.JuvenileOffenseCode
     */
    public void setOffenseCodeId(String aoffenseCodeId)
    {
        if (this.offenseCodeId == null || !this.offenseCodeId.equals(aoffenseCodeId))
        {
            markModified();
        }
        juvOffenseCode = null;
        this.offenseCodeId = aoffenseCodeId;
    }

    /**
     * @param offenseDate
     *            The offenseDate to set.
     */
    public void setOffenseDate(Date offenseDate)
    {
        this.offenseDate = offenseDate;
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
     * Sets the value of the warrantNum property.
     * 
     * @param aWarrantNum
     */
    public void setWarrantNum(String aWarrantNum)
    {
        if (this.warrantNum == null || !this.warrantNum.equals(aWarrantNum))
        {
            markModified();
        }
        warrantNum = aWarrantNum;
    }

    /**
     * @return Charge charge
     * @param chargeId
     */
    static public Charge find(String chargeId)
    {
        Charge charge = null;
        IHome home = new Home();
        charge = (Charge) home.find(chargeId, Charge.class);
        return charge;
    }

    /**
     * @return Iterator charges
     * @param attrName
     * @param attrValue
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        Iterator charges = null;
        charges = home.findAll(attrName, attrValue, Charge.class);
        return charges;
    }
    
    /**
     * 
     * @param attrName
     * @param attrValue
     * @return
     */
    static public Charge find(String attrName, String attrValue)
    {
        IHome home = new Home();
        Charge charge = null;
        charge = (Charge) home.find(attrName, attrValue, Charge.class);
        return charge;
    }

    /**
     * @return String referralNum
     */
    public String getReferralNum()
    {
        fetch();
        return referralNum;
    }

    /**
     * @param aReferralNum
     */
    public void setReferralNum(String aReferralNum)
    {
        if (this.referralNum == null || !this.referralNum.equals(aReferralNum))
        {
            markModified();
        }
        referralNum = aReferralNum;
    }

    /**
     * @return
     */
    public String getSequenceNum()
    {
        return sequenceNum;
    }

    /**
     * @param string
     */
    public void setSequenceNum(String string)
    {
        sequenceNum = string;
    }

    /**
     * @return
     */
    public String getChargeDescription()
    {
        fetch();
        return chargeDescription;
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

}