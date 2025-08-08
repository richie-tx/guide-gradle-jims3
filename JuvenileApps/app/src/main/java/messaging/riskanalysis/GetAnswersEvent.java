package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetAnswersEvent extends RequestEvent
{
	//indicates whether to return only answers that do not have a subordinate question attached.
	private boolean returnAnswersWithASubordinateQuestionAttached; 
	private boolean returnAnswersBasedOnQuestionId;
	private boolean returnAnswerBasedOnAnswerId;
	private boolean returnAnswerBasedOnSubordinateQuestionId;
	private boolean returnAnswerBasedOnAnswerText;
	private boolean excludeSingleAnswerBasedOnId;
	private String	questionId;
	private String	answerId;
	private String	answerText;
	
	public GetAnswersEvent ()
	{
		
	}

	public void setReturnAnswersWithASubordinateQuestionAttached(
			boolean returnAnswersWithASubordinateQuestionAttached) {
		this.returnAnswersWithASubordinateQuestionAttached = returnAnswersWithASubordinateQuestionAttached;
	}

	public boolean isReturnAnswersWithASubordinateQuestionAttached() {
		return returnAnswersWithASubordinateQuestionAttached;
	}

	public void setReturnAnswersBasedOnQuestionId(
			boolean returnAnswersBasedOnQuestionId) {
		this.returnAnswersBasedOnQuestionId = returnAnswersBasedOnQuestionId;
	}

	public boolean isReturnAnswersBasedOnQuestionId() {
		return returnAnswersBasedOnQuestionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setReturnAnswerBasedOnAnswerId(boolean returnAnswerBasedOnAnswerId) {
		this.returnAnswerBasedOnAnswerId = returnAnswerBasedOnAnswerId;
	}

	public boolean isReturnAnswerBasedOnAnswerId() {
		return returnAnswerBasedOnAnswerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setReturnAnswerBasedOnSubordinateQuestionId(
			boolean returnAnswerBasedOnSubordinateQuestionId) {
		this.returnAnswerBasedOnSubordinateQuestionId = returnAnswerBasedOnSubordinateQuestionId;
	}

	public boolean isReturnAnswerBasedOnSubordinateQuestionId() {
		return returnAnswerBasedOnSubordinateQuestionId;
	}

	public void setReturnAnswerBasedOnAnswerText(
			boolean returnAnswerBasedOnAnswerText) {
		this.returnAnswerBasedOnAnswerText = returnAnswerBasedOnAnswerText;
	}

	public boolean isReturnAnswerBasedOnAnswerText() {
		return returnAnswerBasedOnAnswerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setExcludeSingleAnswerBasedOnId(boolean excludeSingleAnswerBasedOnId) {
		this.excludeSingleAnswerBasedOnId = excludeSingleAnswerBasedOnId;
	}

	public boolean isExcludeSingleAnswerBasedOnId() {
		return excludeSingleAnswerBasedOnId;
	}

	
}
