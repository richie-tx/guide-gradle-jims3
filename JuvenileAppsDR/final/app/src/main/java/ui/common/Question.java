/*
 * Created on Dec 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Question implements Comparable
{
	public static String RESPONSE_VAR_TYPE_NUMERIC="NUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA_NUMERIC="ALPHA_NUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA="ALPHA";
	public static String RESPONSE_VAR_TYPE_DATE="DATE";
	public static String RESPONSE_VAR_TYPE_INTEGER="INTEGER";
	public static String RESPONSE_VAR_TYPE_MONETARY="MONETARY";
	
	public static String UI_CNTRL_TYPE_TEXTBOX="TEXTBOX";
	public static String UI_CNTRL_TYPE_TEXT="TEXT";
	public static String UI_CNTRL_TYPE_RADIO="RADIO";
	public static String UI_CNTRL_TYPE_SELECT="SELECT";
	
	
	// The order the question should be presented
	private int rowSequence=0;
	private int columnSequence=0;
	private String questionId="";
	private String questionText="";
	
	private String uiControlType="";
	private int uiControlSize=2;
	private boolean responseNextLine=false;
	private String validationDesc="";
	private boolean isRequired=false;
	private boolean isRenderQuesNum=false;
	private String responseDataType=RESPONSE_VAR_TYPE_ALPHA_NUMERIC;
	private String formatKey="";
	private String textLength="";
	private String dependsOnQuestionId="";
	private String dependsOnResponseId="";
	// If the question this question depends on has been answered then this question is valid- regardless of what the
	// answer was- basically if entered this question is valid
	private boolean dependsOnQuestionAnswered=false;
	
	private String response="";
	private String responseId="";
	
	private Collection responseChoices=null;

	private static HashMap tempRows=new HashMap();

	public Question(){
		//testQuestion();
		//int randomQ=Math.round(Float.parseFloat(Double.toString(6*(Math.random()))));
		
//		switch(randomQ){
//			 case(0): testQuestionText();
//			break;
//			 case (1): testQuestionText();
//			break;
//			 case (2): testQuestionRadio();
//			break;
//			 case (3): testQuestionSelect();
//			break;
//			 case (4): testQuestionTextBox();
//			break;
//			 case (5): testQuestionTextBox();
//			break;
//			 default:
//				testQuestionRadio();
//				break;
//		}
		
	}
	
	private void testQuestion(){
		int questionNumber=Math.round(Float.parseFloat(Double.toString(100*(Math.random()))));
			//	groupId=Math.round(Float.parseFloat(Double.toString(10*(Math.random()))));
			//	groupSequence=Math.round(Float.parseFloat(Double.toString(5*(Math.random()))));
				rowSequence=questionNumber;
				if(tempRows.containsKey(Integer.toString(questionNumber))){
					String getColumn=(String)tempRows.get(Integer.toString(questionNumber));
					int newColumn=Integer.parseInt(getColumn) + 1;
					columnSequence=newColumn;
					tempRows.put(Integer.toString(questionNumber),Integer.toString(columnSequence));
				}
				else {
					columnSequence=1;
					tempRows.put(Integer.toString(questionNumber),"1");
					int TorF=Math.round(Float.parseFloat(Double.toString(4*(Math.random()))));
					if(TorF>2)
						responseNextLine=true;
					
				}
				questionId=Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random())))));
				questionText="This is question number: " + questionNumber;
				int randomQ=Math.round(Float.parseFloat(Double.toString(5*(Math.random()))));
				if(randomQ>3)
					isRequired=true;
				else 
					isRequired=false;
				if(randomQ==1 || randomQ==3)
					questionText= questionText + " some more really long test text for which to see how well the screen will look when this questioin is finally rendered this should be long enough dont you think";
				else if (randomQ==2 || randomQ==4)
					questionText= questionText + " some medium length text to see how well the screen will look when this question text rendered";
						
	}
	
	private void testQuestionSelect(){
					uiControlType=Question.UI_CNTRL_TYPE_SELECT;
					responseChoices=new ArrayList();
					uiControlSize=Math.round(Float.parseFloat(Double.toString(4*(Math.random()))));
					QuestionResponse myFirstAnswer=new QuestionResponse();
					myFirstAnswer.setResponseDisplayText("Please Select");
					myFirstAnswer.setResponseValue("");
					myFirstAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
					myFirstAnswer.setDefault(false);
					responseChoices.add(myFirstAnswer);
					QuestionResponse mySecondAnswer=new QuestionResponse();
					mySecondAnswer.setResponseDisplayText("1st Choice");
					mySecondAnswer.setResponseValue("Choice1");
					mySecondAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
					mySecondAnswer.setDefault(true);
					responseChoices.add(mySecondAnswer);
					QuestionResponse myThirdAnswer=new QuestionResponse();
					myThirdAnswer.setResponseDisplayText("2nd Choice");
					myThirdAnswer.setResponseValue("Choice2");
					myThirdAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
					myThirdAnswer.setDefault(false);
					responseChoices.add(myThirdAnswer);
					
				}
	
	private void testQuestionTextBox(){
				uiControlType=Question.UI_CNTRL_TYPE_TEXTBOX;
				responseChoices=new ArrayList();
				responseDataType=Question.RESPONSE_VAR_TYPE_ALPHA_NUMERIC;
				uiControlSize=Math.round(Float.parseFloat(Double.toString(4*(Math.random()))));
				QuestionResponse myFirstAnswer=new QuestionResponse();
				myFirstAnswer.setResponseValue("Comments Go Here");
				myFirstAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
				myFirstAnswer.setDefault(true);
				responseChoices.add(myFirstAnswer);
			}
	
	private void testQuestionText(){
			uiControlType=Question.UI_CNTRL_TYPE_TEXT;
			responseChoices=new ArrayList();
			
			uiControlSize=Math.round(Float.parseFloat(Double.toString(15*(Math.random()))));
			QuestionResponse myFirstAnswer=new QuestionResponse();
			
			myFirstAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
			myFirstAnswer.setDefault(true);
			responseChoices.add(myFirstAnswer);
			int randomQ=Math.round(Float.parseFloat(Double.toString(5*(Math.random()))));
			switch(randomQ){
				 case(0): formatKey="999";
						responseDataType=Question.RESPONSE_VAR_TYPE_NUMERIC;
						myFirstAnswer.setResponseValue("345");
				break;
				 case (1): formatKey="$999.99";
						responseDataType=Question.RESPONSE_VAR_TYPE_MONETARY;
						myFirstAnswer.setResponseValue("274.05");
				break;
				 case (2): formatKey="";
				responseDataType=Question.RESPONSE_VAR_TYPE_ALPHA_NUMERIC;
				myFirstAnswer.setResponseValue("");
				break;
				 case (3): formatKey="mm/dd/yyyy";
				responseDataType=Question.RESPONSE_VAR_TYPE_DATE;
				myFirstAnswer.setResponseValue("12/09/2005");
				break;
				 case (4): formatKey="9";
				responseDataType=Question.RESPONSE_VAR_TYPE_INTEGER;
				myFirstAnswer.setResponseValue("1");
				break;
				 case (5): formatKey="";
				responseDataType=Question.RESPONSE_VAR_TYPE_ALPHA_NUMERIC;
				myFirstAnswer.setResponseValue("");
				break;
				 default:
				formatKey="";
			responseDataType=Question.RESPONSE_VAR_TYPE_ALPHA_NUMERIC;
			myFirstAnswer.setResponseValue("");
					break;
			}
		}
	
	private void testQuestionRadio(){
		uiControlType=Question.UI_CNTRL_TYPE_RADIO;
		responseChoices=new ArrayList();
		QuestionResponse myFirstAnswer=new QuestionResponse();
		myFirstAnswer.setResponseDisplayText("Yes");
		myFirstAnswer.setResponseValue("TRUE");
		myFirstAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
		myFirstAnswer.setDefault(false);
		responseChoices.add(myFirstAnswer);
		QuestionResponse mySecondAnswer=new QuestionResponse();
		mySecondAnswer.setResponseDisplayText("No");
		mySecondAnswer.setResponseValue("FALSE");
		mySecondAnswer.setResponseId(Integer.toString(Math.round(Float.parseFloat(Double.toString(100*(Math.random()))))));
		mySecondAnswer.setDefault(true);
		responseChoices.add(mySecondAnswer);
	}

	public int compareTo(Object aIncomingObj)
		{
			if(aIncomingObj==null) 
				return 1;
			Question incomingQuestion=(Question)aIncomingObj;
			if(this.getRowSequence()==incomingQuestion.getRowSequence()){
				if(this.getColumnSequence() < incomingQuestion.getColumnSequence())
					return -1;
				else if(this.getColumnSequence()> incomingQuestion.getColumnSequence())
					return 1;
				else
					return 0;
			}
			else if(this.getRowSequence() < incomingQuestion.getRowSequence())
				return -1;
			else			
				return 1;
		}

	/**
	 * @return
	 */
	public String getResponse()
	{
		if(response==null)
			return "";
		return response;
	}

	/**
	 * @return
	 */
	public Collection getResponseChoices()
	{
		return responseChoices;
	}

	/**
	 * @return
	 */
	public String getDependsOnResponseId()
	{
		return dependsOnResponseId;
	}

	/**
	 * @return
	 */
	public boolean isDependsOnQuestionAnswered()
	{
		return dependsOnQuestionAnswered;
	}

	/**
	 * @return
	 */
	public String getDependsOnQuestionId()
	{
		return dependsOnQuestionId;
	}

	

	/**
	 * @return
	 */
	public boolean isRequired()
	{
		return isRequired;
	}

	/**
	 * @return
	 */
	public String getQuestionId()
	{
		return questionId;
	}

	/**
	 * @return
	 */
	public int getRowSequence()
	{
		return rowSequence;
	}

	/**
	 * @return
	 */
	public String getQuestionText()
	{
		return questionText;
	}

	/**
	 * @return
	 */
	public String getResponseDataType()
	{
		return responseDataType;
	}

	/**
	 * @return
	 */
	public int getUiControlSize()
	{
		return uiControlSize;
	}

	/**
	 * @return
	 */
	public String getUiControlType()
	{
		return uiControlType;
	}

	/**
	 * @return
	 */
	public String getValidationDesc()
	{
		return validationDesc;
	}

	/**
	 * @param string
	 */
	public void setResponse(String string)
	{
		response = string;
	}

	/**
	 * @param collection
	 */
	public void setResponseChoices(Collection collection)
	{
		responseChoices = collection;
	}

	/**
	 * @param string
	 */
	public void setDependsOnResponseId(String string)
	{
		dependsOnResponseId = string;
	}

	/**
	 * @param b
	 */
	public void setDependsOnQuestionAnswered(boolean b)
	{
		dependsOnQuestionAnswered = b;
	}

	/**
	 * @param string
	 */
	public void setDependsOnQuestionId(String string)
	{
		dependsOnQuestionId = string;
	}

	

	/**
	 * @param b
	 */
	public void setRequired(boolean b)
	{
		isRequired = b;
	}

	/**
	 * @param string
	 */
	public void setQuestionId(String string)
	{
		questionId = string;
	}

	/**
	 * @param i
	 */
	public void setRowSequence(int i)
	{
		rowSequence = i;
	}

	/**
	 * @param string
	 */
	public void setQuestionText(String string)
	{
		questionText = string;
	}

	/**
	 * @param string
	 */
	public void setResponseDataType(String string)
	{
		responseDataType = string;
	}

	/**
	 * @param i
	 */
	public void setUiControlSize(int i)
	{
		uiControlSize = i;
	}

	/**
	 * @param string
	 */
	public void setUiControlType(String string)
	{
		uiControlType = string;
	}

	/**
	 * @param string
	 */
	public void setValidationDesc(String string)
	{
		validationDesc = string;
	}
	
	public boolean answerMatchesChoice(){
		if(response==null || response.trim().equalsIgnoreCase("")|| responseChoices==null || responseChoices.size()<1)
			return false;
		Iterator i=responseChoices.iterator();
		boolean hasCodeTableDropDown=false;
		while(i.hasNext()){
			QuestionResponse myAnswer=(QuestionResponse)i.next();
			if(myAnswer.getCodeTableName()!=null && !(myAnswer.getCodeTableName().trim().equals(""))){
				hasCodeTableDropDown=true;
			}
			if(myAnswer.getResponseValue().equalsIgnoreCase(response))
				return true;
		}
		if(hasCodeTableDropDown && response!=null && !response.trim().equals("")){
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public String getFormatKey()
	{
		return formatKey;
	}

	/**
	 * @param string
	 */
	public void setFormatKey(String string)
	{
		formatKey = string;
	}

	/**
	 * @return
	 */
	public int getColumnSequence()
	{
		return columnSequence;
	}

	/**
	 * @param i
	 */
	public void setColumnSequence(int i)
	{
		columnSequence = i;
	}

	/**
	 * @return
	 */
	public boolean isResponseNextLine()
	{
		return responseNextLine;
	}

	/**
	 * @param b
	 */
	public void setResponseNextLine(boolean b)
	{
		responseNextLine = b;
	}

	

	/**
	 * @return
	 */
	public String getResponseId()
	{
		return responseId;
	}

	/**
	 * @param string
	 */
	public void setResponseId(String string)
	{
		responseId = string;
	}

	/**
	 * @return Returns the isRenderQuesNum.
	 */
	public boolean isRenderQuesNum() {
		return isRenderQuesNum;
	}
	/**
	 * @param isRenderQuesNum The isRenderQuesNum to set.
	 */
	public void setRenderQuesNum(boolean isRenderQuesNum) {
		this.isRenderQuesNum = isRenderQuesNum;
	}
	/**
	 * @return Returns the textLength.
	 */
	public String getTextLength() {
		return textLength;
	}
	/**
	 * @param textLength The textLength to set.
	 */
	public void setTextLength(String textLength) {
		this.textLength = textLength;
	}
}// END CLASS
