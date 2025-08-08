package pd.supervision.administerassessments;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.List;
import java.util.Iterator;
import mojo.km.utilities.CollectionUtil;
import mojo.km.messaging.IEvent;

/**
 * 
 * @roseuid 47ACD6E2008E
 */
public class AssessmentQuestionAnswer extends PersistentObject
{

	/**
	 * 
	 * @roseuid 47AC5CD600A8
	 * @return List
	 * @param requestEvent
	 */
	public static List findAll(IEvent requestEvent)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(requestEvent, AssessmentQuestionAnswer.class);
		return CollectionUtil.iteratorToList(iter);
	}
	private String answerId;
	private String answerText;
	/**
	 * Properties for assessment
	 */
	private Assessment assessment = null;
	private String assessmentId;
	private String questionGroupId;
	private String questionId;

	/**
	 * 
	 * @roseuid 47ACD6E2008E
	 */
	public AssessmentQuestionAnswer()
	{
	}

	/**
	 * 
	 * @return Returns the answerId.
	 */
	public String getAnswerId()
	{
		fetch();
		return answerId;
	}

	/**
	 * @return Returns the answerText.
	 */
	public String getAnswerText()
	{
		fetch();
		return answerText;
	}

	/**
	 * Gets referenced type pd.supervision.administerassessments.Assessment
	 */
	public Assessment getAssessment()
	{
		fetch();
		initAssessment();
		return assessment;
	}

	/**
	 * Get the reference value to class :: pd.supervision.administerassessments.Assessment
	 */
	public String getAssessmentId()
	{
		fetch();
		return assessmentId;
	}

	/**
	 * @return Returns the questionGroupId.
	 */
	public String getQuestionGroupId()
	{
		fetch();
		return questionGroupId;
	}

	/**
	 * @return Returns the questionId.
	 */
	public String getQuestionId()
	{
		fetch();
		return questionId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.administerassessments.Assessment
	 */
	private void initAssessment()
	{
		if (assessment == null)
		{
			assessment = (Assessment) new mojo.km.persistence.Reference(
					assessmentId, Assessment.class).getObject();
		}
	}

	/**
	 * 
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(String answerId)
	{
		if (this.answerId == null || !this.answerId.equals(answerId))
		{
			markModified();
		}
		this.answerId = answerId;
	}

	/**
	 * @param answerText The answerText to set.
	 */
	public void setAnswerText(String answer)
	{
		if (this.answerText == null || !this.answerText.equals(answer))
		{
			markModified();
		}
		this.answerText = answer;
	}

	/**
	 * set the type reference for class member assessment
	 */
	public void setAssessment(Assessment assessment)
	{
		if (this.assessment == null || !this.assessment.equals(assessment))
		{
			markModified();
		}
		if (assessment.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(assessment);
		}
		setAssessmentId("" + assessment.getOID());
		this.assessment = (Assessment) new mojo.km.persistence.Reference(
				assessment).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.administerassessments.Assessment
	 */
	public void setAssessmentId(String assessmentId)
	{
		if (this.assessmentId == null || !this.assessmentId.equals(assessmentId))
		{
			markModified();
		}
		assessment = null;
		this.assessmentId = assessmentId;
	}

	/**
	 * @param questionGroupId The questionGroupId to set.
	 */
	public void setQuestionGroupId(String questionGroupId)
	{
		if (this.questionGroupId == null || !this.questionGroupId.equals(questionGroupId))
		{
			markModified();
		}
		this.questionGroupId = questionGroupId;
	}

	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(String questionId)
	{
		if (this.questionId == null || !this.questionId.equals(questionId))
		{
			markModified();
		}
		this.questionId = questionId;
	}
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, AssessmentQuestionAnswer.class);
	    return CollectionUtil.iteratorToList(iter);
	}
}
