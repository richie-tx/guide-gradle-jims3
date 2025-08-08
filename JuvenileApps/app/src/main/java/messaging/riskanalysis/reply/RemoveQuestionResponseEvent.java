package messaging.riskanalysis.reply;

import mojo.km.messaging.ResponseEvent;

public class RemoveQuestionResponseEvent extends ResponseEvent{
	
	private boolean questionsAndAnswersDeleted;
	
	public void getQuestionsReponseEvent () {
		
	}
	
	public void setQuestionsAndAnswersDeleted(boolean questionsAndAnswersDeleted) {
		this.questionsAndAnswersDeleted = questionsAndAnswersDeleted;
	}

	public boolean isQuestionsAndAnswersDeleted() {
		return questionsAndAnswersDeleted;
	}

}
