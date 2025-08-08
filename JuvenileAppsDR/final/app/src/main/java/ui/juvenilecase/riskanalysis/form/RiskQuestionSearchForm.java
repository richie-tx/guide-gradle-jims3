package ui.juvenilecase.riskanalysis.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
/**
 * @author palcocer
 *
 */
public class RiskQuestionSearchForm extends ActionForm
{
	
	private List questions;
	public String questionId;
	
	
	public RiskQuestionSearchForm()
	{
		
	}


	public void setQuestions(List questions) {
		this.questions = questions;
	}


	public List getQuestions() {
		return questions;
	}
	
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String getQuestionId() {
		return questionId;
	}
	
	
	
}
