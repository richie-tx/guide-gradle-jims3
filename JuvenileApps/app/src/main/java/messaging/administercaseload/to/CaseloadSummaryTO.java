package messaging.administercaseload.to;

import messaging.administercaseload.domintf.ICaseloadSummary;
import messaging.contact.domintf.IName;

/**
 * @author Jim Fisher
 */
public class CaseloadSummaryTO implements ICaseloadSummary
{

    private String cjad;
    private int countTotal;

    private double ind;

    private int indCount;

    private double los0;

    private int los0Count;

    private double los1;

    private int los1Count;

    private double los2;

    private int los2Count;

    private double los3;

    private int los3Count;

    private double los4;

    private int los4Count;

    private IName officerName;

    private String officerPositionId;

    private String positionName;

    private String probationOfficerInd;

    private String supervisorPositionId;

    private double workload;

    /**
     * @return Returns the cjad.
     */
    public String getCjad()
    {
        return cjad;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#getCountTotal()
     */
    public int getCountTotal()
    {
        return this.countTotal;
    }

    /**
     * @return Returns the ind.
     */
    public double getInd()
    {
        return ind;
    }

    /**
     * @return Returns the indCount.
     */
    public int getIndCount()
    {
        return indCount;
    }

    /**
     * @return Returns the los0.
     */
    public double getLos0()
    {
        return los0;
    }

    /**
     * @return Returns the los0Count.
     */
    public int getLos0Count()
    {
        return los0Count;
    }

    /**
     * @return Returns the los1.
     */
    public double getLos1()
    {
        return los1;
    }

    /**
     * @return Returns the los1Count.
     */
    public int getLos1Count()
    {
        return los1Count;
    }

    /**
     * @return Returns the los2.
     */
    public double getLos2()
    {
        return los2;
    }

    /**
     * @return Returns the los2Count.
     */
    public int getLos2Count()
    {
        return los2Count;
    }

    /**
     * @return Returns the los3.
     */
    public double getLos3()
    {
        return los3;
    }

    /**
     * @return Returns the los3Count.
     */
    public int getLos3Count()
    {
        return los3Count;
    }

    /**
     * @return Returns the los4.
     */
    public double getLos4()
    {
        return los4;
    }

    /**
     * @return Returns the los4Count.
     */
    public int getLos4Count()
    {
        return los4Count;
    }

    /**
     * @return Returns the officerName.
     */
    public IName getOfficerName()
    {
        return officerName;
    }

    /**
     * @return Returns the officerPositionId.
     */
    public String getOfficerPositionId()
    {
        return officerPositionId;
    }

    /**
     * @return Returns the positionName.
     */
    public String getPositionName()
    {
        return positionName;
    }

    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd()
    {
        return probationOfficerInd;
    }

    /**
     * @return Returns the supervisorPositionId.
     */
    public String getSupervisorPositionId()
    {
        return supervisorPositionId;
    }

    /**
     * @return Returns the workload.
     */
    public double getWorkload()
    {
        return workload;
    }

    /**
     * @param cjad
     *        The cjad to set.
     */
    public void setCjad(String cjad)
    {
        this.cjad = cjad;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setCountTotal(int)
     */
    public void setCountTotal(int count)
    {
        this.countTotal = count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setInd(double)
     */
    public void setInd(double ind)
    {
        this.ind = ind;
    }

    /**
     * @param indCount
     *        The indCount to set.
     */
    public void setIndCount(int indCount)
    {
        this.indCount = indCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setLos0(double)
     */
    public void setLos0(double los0)
    {
        this.los0 = los0;
    }

    /**
     * @param los0Count
     *        The los0Count to set.
     */
    public void setLos0Count(int los0Count)
    {
        this.los0Count = los0Count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setLos1(double)
     */
    public void setLos1(double los1)
    {
        this.los1 = los1;
    }

    /**
     * @param los1Count
     *        The los1Count to set.
     */
    public void setLos1Count(int los1Count)
    {
        this.los1Count = los1Count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setLos2(double)
     */
    public void setLos2(double los2)
    {
        this.los2 = los2;
    }

    /**
     * @param los2Count
     *        The los2Count to set.
     */
    public void setLos2Count(int los2Count)
    {
        this.los2Count = los2Count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setLos3(double)
     */
    public void setLos3(double los3)
    {
        this.los3 = los3;
    }

    /**
     * @param los3Count
     *        The los3Count to set.
     */
    public void setLos3Count(int los3Count)
    {
        this.los3Count = los3Count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see messaging.administercaseload.domintf.ICaseloadSummary#setLos4(double)
     */
    public void setLos4(double los4)
    {
        this.los4 = los4;

    }

    /**
     * @param los4Count
     *        The los4Count to set.
     */
    public void setLos4Count(int los4Count)
    {
        this.los4Count = los4Count;
    }

    /**
     * @param officerName
     *        The officerName to set.
     */
    public void setOfficerName(IName officerName)
    {
        this.officerName = officerName;
    }

    /**
     * @param officerPositionId
     *        The officerPositionId to set.
     */
    public void setOfficerPositionId(String officerPositionId)
    {
        this.officerPositionId = officerPositionId;
    }

    /**
     * @param positionName
     *        The positionName to set.
     */
    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    /**
     * @param probationOfficerInd
     *        The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd)
    {
        this.probationOfficerInd = probationOfficerInd;
    }

    /**
     * @param supervisorPositionId
     *        The supervisorPositionId to set.
     */
    public void setSupervisorPositionId(String supervisorPositionId)
    {
        this.supervisorPositionId = supervisorPositionId;
    }

    /**
     * @param workload
     *        The workload to set.
     */
    public void setWorkload(double workload)
    {
        this.workload = workload;
    }
}
