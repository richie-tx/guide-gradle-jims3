/*
 * Created on Oct 28, 2004
 */
package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class ChargeRequestEvent extends RequestEvent
{
    public String chargeId;

    public String courtId;

    public String offenseCodeId;

    public String petitionNum;

    public String warrantNum;

    public String sequenceNum;

    public String chargeDescription;

    public String degree;

    public String level;

    public Date offenseDate;

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
    public String getCourtId()
    {
        return courtId;
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
     * @return
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @param chargeId
     */
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }

    /**
     * @param courtId
     */
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }

    /**
     * @param offenseCodeId
     */
    public void setOffenseCodeId(String chargeCodeId)
    {
        this.offenseCodeId = chargeCodeId;
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
     * @param petitionNum
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    /**
     * @param warrantNum
     */
    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
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
        return chargeDescription;
    }

    /**
     * @param string
     */
    public void setChargeDescription(String string)
    {
        chargeDescription = string;
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

}