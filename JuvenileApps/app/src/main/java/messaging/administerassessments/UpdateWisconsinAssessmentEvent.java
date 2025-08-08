// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\UpdateWisconsinAssessmentEvent.java

package messaging.administerassessments;

public class UpdateWisconsinAssessmentEvent extends UpdateCSAssessmentEvent {
    private boolean isInitial;

    private String levelOfSupervision;
    
    private int needsLevel;

    private int needsScore;
    
    private int riskLevel;

    private int riskScore;

    /**
     * @roseuid 4791040E0337
     */
    public UpdateWisconsinAssessmentEvent() {
    }

    /**
     * @return Returns the levelOfSupervision.
     */
    public String getLevelOfSupervision() {
        return levelOfSupervision;
    }
    /**
     * @return Returns the needsLevel.
     */
    public int getNeedsLevel() {
        return needsLevel;
    }

    /**
     * @return Returns the needsScore.
     */
    public int getNeedsScore() {
        return needsScore;
    }
    /**
     * @return Returns the riskLevel.
     */
    public int getRiskLevel() {
        return riskLevel;
    }

    /**
     * @return Returns the riskScore.
     */
    public int getRiskScore() {
        return riskScore;
    }

    /**
     * @return Returns the isInitial.
     */
    public boolean getIsInitial() {
        return isInitial;
    }

    /**
     * @param isInitial
     *            The isInitial to set.
     */
    public void setIsInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }

    /**
     * @param levelOfSupervision
     *            The levelOfSupervision to set.
     */
    public void setLevelOfSupervision(String levelOfSupervision) {
        this.levelOfSupervision = levelOfSupervision;
    }
    /**
     * @param needsLevel The needsLevel to set.
     */
    public void setNeedsLevel(int needsLevel) {
        this.needsLevel = needsLevel;
    }

    /**
     * @param needsScore
     *            The needsScore to set.
     */
    public void setNeedsScore(int needsScore) {
        this.needsScore = needsScore;
    }
    /**
     * @param riskLevel The riskLevel to set.
     */
    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * @param riskScore
     *            The riskScore to set.
     */
    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }
}
