/*
 * Created on Feb 18, 2008
 *
 */
package messaging.administerassessments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
  */
public class UpdateCSAssessmentEvent extends RequestEvent {
    private Date assessmentDate;
    
    private String assessmentTypeCd;

    private String defendantId;

    private String masterAssessmentId;
    
//	collection of CSCAnswer objects
    private Collection questionAnswers;
    
    private boolean isAssessmentIncomplete;
    
    /**
     * @return Returns the assessmentDate.
     */
    public Date getAssessmentDate() {
        return assessmentDate;
    }
    /**
     * @return Returns the assessmentTypeCd.
     */
    public String getAssessmentTypeCd() {
        return assessmentTypeCd;
    }
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId() {
        return defendantId;
    }
    /**
     * @return Returns the masterAssessmentId.
     */
    public String getMasterAssessmentId() {
        return masterAssessmentId;
    }
    /**
     * @return Returns the questionAnswers.
     */
    public Collection getQuestionAnswers() {
        return questionAnswers;
    }

    /**
     * @param assessmentDate The assessmentDate to set.
     */
    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }
    /**
     * @param assessmentTypeCd The assessmentTypeCd to set.
     */
    public void setAssessmentTypeCd(String assessmentTypeCd) {
        this.assessmentTypeCd = assessmentTypeCd;
    }
    /**
     * @param defendantId The defendantId to set.
     */
    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
    }
    /**
     * @param masterAssessmentId The masterAssessmentId to set.
     */
    public void setMasterAssessmentId(String masterAssessmentId) {
        this.masterAssessmentId = masterAssessmentId;
    }
    /**
     * @param questionAnswers The questionAnswers to set.
     */
    public void setQuestionAnswers(Collection questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
    /**
     * @param answer
     */
    public void addQuestionAnswer(CSCAnswer answer){
        if (questionAnswers == null){
            questionAnswers = new ArrayList();
        }
        questionAnswers.add(answer);
    }
	/**
	 * @return the isAssessmentIncomplete
	 */
	public boolean isAssessmentIncomplete() {
		return isAssessmentIncomplete;
	}
	/**
	 * @param isAssessmentIncomplete the isAssessmentIncomplete to set
	 */
	public void setAssessmentIncomplete(boolean isAssessmentIncomplete) {
		this.isAssessmentIncomplete = isAssessmentIncomplete;
	}
    
}
