//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\CalculateSCSTotalsEvent.java

package messaging.administerassessments;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;


public class CalculateSCSTotalsEvent extends RequestEvent 
{
//	collection of SCS QuestionAnswer objects
    private Collection questionAnswers;
    
   /**
    * @roseuid 4791038F0345
    */
   public CalculateSCSTotalsEvent() 
   {
    
   }   
   
/**
 * @return Returns the questionAnswers.
 */
public Collection getQuestionAnswers() {
	return questionAnswers;
}
/**
 * @param questionAnswers The questionAnswers to set.
 */
public void setQuestionAnswers(Collection questionAnswers) {
	this.questionAnswers = questionAnswers;
}
}
