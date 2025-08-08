package messaging.administercaseload.domintf;

import messaging.contact.domintf.IName;

/**
 * @author Jim Fisher
 */
public interface ICaseloadSummary
{
    /**
     * @return Returns the cjad.
     */
    String getCjad();

    int getCountTotal();

    /**
     * @return Returns the ind.
     */
    double getInd();

    int getIndCount();

    /**
     * @return Returns the los0.
     */
    double getLos0();

    int getLos0Count();

    /**
     * @return Returns the los1.
     */
    double getLos1();

    int getLos1Count();

    /**
     * @return Returns the los2.
     */
    double getLos2();

    int getLos2Count();

    /**
     * @return Returns the los3.
     */
    double getLos3();

    int getLos3Count();

    /**
     * @return Returns the los4.
     */
    double getLos4();

    int getLos4Count();

    /**
     * @return Returns the officerName.
     */
    IName getOfficerName();

    /**
     * @return Returns the officerPositionId.
     */
    String getOfficerPositionId();

    /**
     * @return Returns the positionName.
     */
    String getPositionName();

    String getProbationOfficerInd();

    /**
     * @return Returns the supervisorPositionId.
     */
    String getSupervisorPositionId();

    double getWorkload();

    /**
     * @param cjad
     *        The cjad to set.
     */
    void setCjad(String cjad);

    void setCountTotal(int count);

    /**
     * @param ind
     *        The ind to set.
     */
    void setInd(double ind);

    void setIndCount(int aCount);

    /**
     * @param los0
     *        The los0 to set.
     */
    void setLos0(double los0);

    void setLos0Count(int aCount);

    /**
     * @param los1
     *        The los1 to set.
     */
    void setLos1(double los1);

    void setLos1Count(int aCount);

    /**
     * @param los2
     *        The los2 to set.
     */
    void setLos2(double los2);

    void setLos2Count(int aCount);

    /**
     * @param los3
     *        The los3 to set.
     */
    void setLos3(double los3);

    void setLos3Count(int aCount);

    /**
     * @param los4
     *        The los4 to set.
     */
    void setLos4(double los4);

    void setLos4Count(int aCount);

    /**
     * @param officerName
     *        The officerName to set.
     */
    void setOfficerName(IName officerName);

    /**
     * @param officerPositionId
     *        The officerPositionId to set.
     */
    void setOfficerPositionId(String officerPositionId);

    /**
     * @param positionName
     *        The positionName to set.
     */
    void setPositionName(String positionName);

    /**
     * @param probationOfficerInd
     */
    void setProbationOfficerInd(String probationOfficerInd);

    /**
     * @param supervisorPositionId
     *        The supervisorPositionId to set.
     */
    void setSupervisorPositionId(String supervisorPositionId);

    /**
     * @param workload
     */
    void setWorkload(double workload);
}