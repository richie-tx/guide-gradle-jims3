/*
 * Created on Oct 28, 2004
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class ChargeResponseEvent extends ResponseEvent implements ICode, Comparable
{
    private String chargeId;

    private String referralNum;

    private String court;

    private String courtId;

    private String petitionNum;

    private String offense;
    
    private String offenseShortDesc;

    private Date offenseDate;

    private String offenseCodeId;

    private String sequenceNum;

    private String level;

    private String degree;

    public String getCode()
    {

        return this.getOffenseCodeId();
    }

    public String getDescription()
    {

        return this.getOffense();
    }

    /**
     * @return
     */
    public String getChargeId()
    {
        return chargeId;
    }

    /**
     * @return
     */
    public String getSequenceNum()
    {
        return sequenceNum;
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
     * @return Returns the offenseDate.
     */
    public Date getOffenseDate()
    {
        return offenseDate;
    }

    /**
     * @return
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }

    /**
     * @param string
     */
    public void setChargeId(String string)
    {
        chargeId = string;
    }

    /**
     * @param string
     */
    public void setSequenceNum(String string)
    {
        sequenceNum = string;
    }

    /**
     * @param string
     */
    public void setCourt(String string)
    {
        court = string;
    }

    /**
     * @param string
     */
    public void setCourtId(String string)
    {
        courtId = string;
    }

    /**
     * @param string
     */
    public void setOffense(String string)
    {
        offense = string;
    }

    /**
     * @param string
     */
    public void setOffenseCodeId(String string)
    {
        offenseCodeId = string;
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
     * @param string
     */
    public void setPetitionNum(String string)
    {
        petitionNum = string;
    }

    /**
     * @return
     */
    public String getReferralNum()
    {
        return referralNum;
    }

    /**
     * @param string
     */
    public void setReferralNum(String string)
    {
        referralNum = string;
    }

    /**
     * @return
     */
    public String getDegree()
    {
        return degree;
    }

    /**
     * @return
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * @param string
     */
    public void setDegree(String string)
    {
        degree = string;
    }

    /**
     * @param string
     */
    public void setLevel(String string)
    {
        level = string;
    }

    /**
     * Sort in order of descending severity
     * 
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object petitionObj)
    {
        String sequenceNum = ((ChargeResponseEvent) petitionObj).getSequenceNum();
        int sequenceNumInt = 0;
        if (sequenceNum != null && sequenceNum.equals("") == false)
        {
            sequenceNumInt = Integer.parseInt(sequenceNum);
        }

        int thisSequenceNumInt = 0;
        if (this.sequenceNum != null && this.sequenceNum.equals("") == false)
        {
            thisSequenceNumInt = Integer.parseInt(this.sequenceNum);
        }

        int result = 0;

        if (thisSequenceNumInt < sequenceNumInt)
        {
            result = -1;
        }
        else if (thisSequenceNumInt > sequenceNumInt)
        {
            result = 1;
        }
        return result;
    }

	/**
	 * @return the offenseShortDesc
	 */
	public String getOffenseShortDesc() {
		return offenseShortDesc;
	}

	/**
	 * @param offenseShortDesc the offenseShortDesc to set
	 */
	public void setOffenseShortDesc(String offenseShortDesc) {
		this.offenseShortDesc = offenseShortDesc;
	}

}