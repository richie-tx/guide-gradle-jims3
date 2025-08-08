package ui.juvenilecase.riskanalysis.form;

import java.util.ArrayList;
import java.util.List;

import ui.juvenilecase.riskanalysis.form.objects.Question;

import org.apache.struts.action.ActionForm;
/**
 * @author cShimek
 *
 */
public class RiskQuestionDetailsPopupForm extends ActionForm
{

	public RiskQuestionDetailsPopupForm()
	{
		
	}
	//Question -
	private Question question = new Question();
	
	//Answers
	private List answerList = new ArrayList();
	
	private String showCategory;
	public void clearForm() 
    {
    	this.showCategory = "";
		//Lists -
		if (answerList != null)
			answerList.clear();
    	
    	//Question -
    	getQuestion().setQuestionName(null);
    	getQuestion().setQuestonEntryDate(null);
    	getQuestion().setQuestionText(null);
    	getQuestion().setUiControlType(null);
    	getQuestion().setCollapsibleHeader(null);
    	getQuestion().setUiDisplayOrder(null);
    	getQuestion().setAllowFutureDates(null);
    	getQuestion().setNumericOnly(null);
    	getQuestion().setQuestionInitialAction(null);
    	getQuestion().setRequired(null);
    	getQuestion().setControlCode(null);
    	getQuestion().setDefaultToSystemDate(null);
    	getQuestion().setAnswerSource(null);
    	getQuestion().setHardcoded(null);
    	getQuestion().setRiskCategoryId(null);
    	getQuestion().setAllowPrint(null);
    	
    }
	
	public void clearAnswers() 
	{
		answerList.clear();
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
	
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public List getAnswerList() {
		return answerList;
	}

	public String getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(String showCategory) {
		this.showCategory = showCategory;
	}
	
}