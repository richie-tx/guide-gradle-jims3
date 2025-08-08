// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\UpdateSCSAssessmentEvent.java

package messaging.administerassessments;

public class UpdateSCSAssessmentEvent extends UpdateCSAssessmentEvent {
    private String comments;

    private String primaryClassCd;

    private String secondaryClassCd;

    private int siTotal;

    private int ccTotal;

    private int esTotal;

    private int lsTotal;

    private String scsId;

    /**
     * @roseuid 4791040E02D9
     */
    public UpdateSCSAssessmentEvent() {

    }

    /**
     * @return Returns the ccTotal.
     */
    public int getCcTotal() {
        return ccTotal;
    }

    /**
     * @param ccTotal
     *            The ccTotal to set.
     */
    public void setCcTotal(int ccTotal) {
        this.ccTotal = ccTotal;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the esTotal.
     */
    public int getEsTotal() {
        return esTotal;
    }

    /**
     * @param esTotal
     *            The esTotal to set.
     */
    public void setEsTotal(int esTotal) {
        this.esTotal = esTotal;
    }

    /**
     * @return Returns the lsTotal.
     */
    public int getLsTotal() {
        return lsTotal;
    }

    /**
     * @param lsTotal
     *            The lsTotal to set.
     */
    public void setLsTotal(int isTotal) {
        this.lsTotal = isTotal;
    }

    /**
     * @return Returns the primaryClassCd.
     */
    public String getPrimaryClassCd() {
        return primaryClassCd;
    }

    /**
     * @param primaryClassCd
     *            The primaryClassCd to set.
     */
    public void setPrimaryClassCd(String primaryClassCd) {
        this.primaryClassCd = primaryClassCd;
    }

    /**
     * @return Returns the secondaryClassCd.
     */
    public String getSecondaryClassCd() {
        return secondaryClassCd;
    }

    /**
     * @param secondaryClassCd
     *            The secondaryClassCd to set.
     */
    public void setSecondaryClassCd(String secondaryClassCd) {
        this.secondaryClassCd = secondaryClassCd;
    }

    /**
     * @return Returns the siTotal.
     */
    public int getSiTotal() {
        return siTotal;
    }

    /**
     * @param siTotal
     *            The siTotal to set.
     */
    public void setSiTotal(int siTotal) {
        this.siTotal = siTotal;
    }

    /**
     * @return Returns the scsId.
     */
    public String getScsId() {
        return scsId;
    }

    /**
     * @param scsId
     *            The scsId to set.
     */
    public void setScsId(String scsId) {
        this.scsId = scsId;
    }
}
