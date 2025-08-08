/*
 * Created on Feb 20, 2008
 *
 */
package messaging.administerassessments;

import java.io.Serializable;

/**
 * @author cc_bjangay
 *  
 */
public class CSCAnswer implements Serializable {
    private String questionGroupId;

    private String questionId;

    private String responseId;

    private String responseText;

    /**
     * @return Returns the questionGroupId.
     */
    public String getQuestionGroupId() {
        return questionGroupId;
    }

    /**
     * @return Returns the questionId.
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @return Returns the responseId.
     */
    public String getResponseId() {
        return responseId;
    }

    /**
     * @return Returns the responseText.
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * @param questionGroupId
     *            The questionGroupId to set.
     */
    public void setQuestionGroupId(String questionGroupId) {
        this.questionGroupId = questionGroupId;
    }

    /**
     * @param questionId
     *            The questionId to set.
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @param responseId
     *            The responseId to set.
     */
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    /**
     * @param responseText
     *            The responseText to set.
     */
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
