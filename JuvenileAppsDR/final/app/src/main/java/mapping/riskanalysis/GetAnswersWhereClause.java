/*
 * Created on Sep 27, 2006
 *
 */
package mapping.riskanalysis;

import java.util.Date;
import messaging.riskanalysis.GetAnswersEvent;
import mojo.km.messaging.IEvent;


/**
 * @author palcocer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAnswersWhereClause {

	public String GetAnswers( IEvent anEvent )
	{
		GetAnswersEvent getAnswersEvent = (GetAnswersEvent)anEvent;
		StringBuffer whereClause = new StringBuffer();
		
		if (getAnswersEvent.isReturnAnswersWithASubordinateQuestionAttached()) {
			//returns only answers with subordinate question attached
			whereClause.append(" SUBORDQUEST_ID IS NOT NULL AND SUBORDQUEST_ID > 0");
		}
		else if (getAnswersEvent.isReturnAnswersBasedOnQuestionId()) 
		{
			//returns answers based on the question ID
			whereClause.append(" RISKQUESTION_ID = ").append(getAnswersEvent.getQuestionId());
		}
		else if (getAnswersEvent.isReturnAnswerBasedOnAnswerId()) 
		{
			//returns answers based on the RISKWEIGHTRES_ID 
			whereClause.append(" RISKWEIGHTRES_ID = ").append(getAnswersEvent.getAnswerId());
		}
		else if (getAnswersEvent.isReturnAnswerBasedOnSubordinateQuestionId()) 
		{
			//returns only answers with subordinate question attached
			whereClause.append(" SUBORDQUEST_ID = ").append(getAnswersEvent.getQuestionId());;
		}
		else if (getAnswersEvent.isReturnAnswerBasedOnAnswerText()) 
		{
			//returns only a single answer based on the answer text
			whereClause.append(" ANSWERTEXT LIKE '%").append(getAnswersEvent.getAnswerText()).append("%'");
			
			if (getAnswersEvent.isExcludeSingleAnswerBasedOnId()) {
				whereClause.append(" AND RISKWEIGHTRES_ID NOT IN (").append(getAnswersEvent.getAnswerId()).append(")");
			}
			 
		} 
		else 
		{
			//returns all questions
			whereClause.append(" RISKWEIGHTRES_ID IS NOT NULL");
		}

		return whereClause.toString();
	}

}
