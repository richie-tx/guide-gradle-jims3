package messaging.riskanalysis.reply;

import mojo.km.messaging.ResponseEvent;

public class GetAnswerResponseEvent extends ResponseEvent implements Comparable{
	
	private String response;
	private String riskAnswerId;
	private int weight;
	private int riskQuestionId;
	private String riskQuestionName;
	private int sortOrder;
	private String subordinateQuestionId;
	private String subordinateQuestionName;
	private String action;
	private String suggestedCasePlainDomiainId;
	private String AnswerEntryDate;
	
	public void getQuestionsReponseEvent () {
		
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setRiskQuestionId(int riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}

	public int getRiskQuestionId() {
		return riskQuestionId;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSubordinateQuestionId(String subordinateQuestionId) {
		this.subordinateQuestionId = subordinateQuestionId;
	}

	public String getSubordinateQuestionId() {
		return subordinateQuestionId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setSuggestedCasePlainDomiainId(String suggestedCasePlainDomiainId) {
		this.suggestedCasePlainDomiainId = suggestedCasePlainDomiainId;
	}

	public String getSuggestedCasePlainDomiainId() {
		return suggestedCasePlainDomiainId;
	}

	public void setSubordinateQuestionName(String subordinateQuestionName) {
		this.subordinateQuestionName = subordinateQuestionName;
	}

	public String getSubordinateQuestionName() {
		return subordinateQuestionName;
	}

	public void setAnswerEntryDate(String answerEntryDate) {
		AnswerEntryDate = answerEntryDate;
	}

	public String getAnswerEntryDate() {
		return AnswerEntryDate;
	}

	public void setRiskAnswerId(String riskAnswerId) {
		this.riskAnswerId = riskAnswerId;
	}

	public String getRiskAnswerId() {
		return riskAnswerId;
	}

	public void setRiskQuestionName(String riskQuestionName) {
		this.riskQuestionName = riskQuestionName;
	}

	public String getRiskQuestionName() {
		return riskQuestionName;
	}
	/** Sort in ascending order of sortOrder.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object answerEvent)
	{
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

		if (answerEvent==null)
			return AFTER;
		final int sortOrd = ((GetAnswerResponseEvent)answerEvent).getSortOrder();
		final int thisSortOrd = sortOrder;
		if (sortOrd < thisSortOrd ) {
			return AFTER;
		}
		if (sortOrd > thisSortOrd ) {
			return BEFORE;
		}
		return EQUAL;
	}
}
