package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetQuestionsEvent extends RequestEvent
{
	//indicates whether to return only questions not attached to category
	private boolean returnQuestionsNotAttachedToCategory; //Used in the Risk Quesions section, only questions not attached to a category should show up
	private boolean returnSingleQuestionBasedOnId;
	private boolean returnSingleQuestionBasedOnQuestionName;
	private boolean returnSingleQuestionBasedOnQuestionText;
	private boolean excludeSingleQuestionBasedOnId;
	private String	questionId;
	private String	questionName;
	private String	questionText;
	
	public GetQuestionsEvent() 
	{
	    
	}

	public void setReturnSingleQuestionBasedOnId(
			boolean returnSingleQuestionBasedOnId) {
		this.returnSingleQuestionBasedOnId = returnSingleQuestionBasedOnId;
	}

	public boolean isReturnSingleQuestionBasedOnId() {
		return returnSingleQuestionBasedOnId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setReturnQuestionsNotAttachedToCategory(
			boolean returnQuestionsNotAttachedToCategory) {
		this.returnQuestionsNotAttachedToCategory = returnQuestionsNotAttachedToCategory;
	}

	public boolean isReturnQuestionsNotAttachedToCategory() {
		return returnQuestionsNotAttachedToCategory;
	}

	public void setReturnSingleQuestionBasedOnQuestionName(
			boolean returnSingleQuestionBasedOnQuestionName) {
		this.returnSingleQuestionBasedOnQuestionName = returnSingleQuestionBasedOnQuestionName;
	}

	public boolean isReturnSingleQuestionBasedOnQuestionName() {
		return returnSingleQuestionBasedOnQuestionName;
	}

	public void setReturnSingleQuestionBasedOnQuestionText(
			boolean returnSingleQuestionBasedOnQuestionText) {
		this.returnSingleQuestionBasedOnQuestionText = returnSingleQuestionBasedOnQuestionText;
	}

	public boolean isReturnSingleQuestionBasedOnQuestionText() {
		return returnSingleQuestionBasedOnQuestionText;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setExcludeSingleQuestionBasedOnId(
			boolean excludeSingleQuestionBasedOnId) {
		this.excludeSingleQuestionBasedOnId = excludeSingleQuestionBasedOnId;
	}

	public boolean isExcludeSingleQuestionBasedOnId() {
		return excludeSingleQuestionBasedOnId;
	}

	 
}
