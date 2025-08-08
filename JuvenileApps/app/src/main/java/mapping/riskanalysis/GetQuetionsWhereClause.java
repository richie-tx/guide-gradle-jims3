/*
 * Created on Sep 27, 2006
 *
 */
package mapping.riskanalysis;

import messaging.riskanalysis.GetQuestionsEvent;
import mojo.km.messaging.IEvent;


/**
 * @author palcocer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetQuetionsWhereClause {

	public String GetQuetions( IEvent anEvent )
	{
		GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent)anEvent;
		StringBuffer whereClause = new StringBuffer();
		
		if (getQuestionsEvent.isReturnQuestionsNotAttachedToCategory()) {
			//returns only questions not attached to a category
			whereClause.append(" RISKCATEGORY_ID IS NULL OR RISKCATEGORY_ID = 0");
		} 
		else if (getQuestionsEvent.isReturnSingleQuestionBasedOnId()) {
			//returns only a single question based on the question ID
			whereClause.append(" RISKQUESTION_ID = ").append(getQuestionsEvent.getQuestionId());
		} 
		else if (getQuestionsEvent.isReturnSingleQuestionBasedOnQuestionName()) {
			//returns only a single question based on the question name
// 04/23/2012 revised to equal per defect 73238 and 04			
//			whereClause.append("upper (QUESTIONNAME) LIKE '%").append(getQuestionsEvent.getQuestionName()).append("%'");
			whereClause.append(" QUESTIONNAME = '").append(getQuestionsEvent.getQuestionName().toUpperCase()).append("'");
			if (getQuestionsEvent.isExcludeSingleQuestionBasedOnId()) {
				whereClause.append(" AND RISKQUESTION_ID NOT IN (").append(getQuestionsEvent.getQuestionId()).append(")");
			}
			
		} 
		else if (getQuestionsEvent.isReturnSingleQuestionBasedOnQuestionText()) {
			//returns only a single question based on the question text
// 04/23/2012 revised to equal per defect 73238 			
//			whereClause.append(" QUESTIONTEXT LIKE '%").append(getQuestionsEvent.getQuestionText()).append("%'");
			whereClause.append(" QUESTIONTEXT = '").append(getQuestionsEvent.getQuestionText()).append("'");
			if (getQuestionsEvent.isExcludeSingleQuestionBasedOnId()) {
				whereClause.append(" AND RISKQUESTION_ID NOT IN (").append(getQuestionsEvent.getQuestionId()).append(")");
			}
			
		} 
		else 
		{
			//returns all questions
			whereClause.append(" RISKQUESTION_ID IS NOT NULL");
		}

		return whereClause.toString();
	}

}
