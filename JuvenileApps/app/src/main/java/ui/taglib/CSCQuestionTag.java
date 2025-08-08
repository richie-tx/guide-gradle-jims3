/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import naming.UIConstants;

import org.apache.struts.util.ResponseUtils;

import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.CSCQuestionResponse;
import ui.common.UIUtil;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCQuestionTag extends TagSupport
{
    public static final int QUESTION_INDEX = 0;
    public static final int ANSWER_INDEX = 1;
	
	private String name;
	private String property;
	private String requiredGif;
	private String type; // Valid values are input & summary
	
	private int columns=2;
	private int maxColumnsFound=columns;	 
	private Collection questionGroupList=null;
	private boolean isQuestionInLastColumn = false;

	
	private void renderBody(Collection aQuestionGroup, StringBuffer buffer) throws Exception
		{
			
			if(aQuestionGroup==null || aQuestionGroup.size()<=0)
				return;
			
			UIUtil.sortCSCQuestionGroup((ArrayList)aQuestionGroup, type);	
			
			questionGroupList = aQuestionGroup;
			maxColumnsFound=UIUtil.maxColumnSizeForCSCQuestions(aQuestionGroup,type);
			if((maxColumnsFound *2)>columns)
				columns=maxColumnsFound *2;	
			Iterator iGroup=aQuestionGroup.iterator();
			int groupCounter=0;
			int questionCounter=1;
			while(iGroup.hasNext()){
				CSCQuestionGroup currentQuestionGroup=(CSCQuestionGroup)iGroup.next();
				
				if(currentQuestionGroup.isGroupTextDetailHeader())
				{
					buffer.append("<tr class=detailHead>");
					buffer.append("<td colspan=" + columns + " align=" + currentQuestionGroup.getGroupTextAlign() + ">");
					buffer.append(currentQuestionGroup.getGroupText());
				    buffer.append("</td>");
				    buffer.append("</tr>");
				}
				
				Collection aQuestions=currentQuestionGroup.getQuestions();
			
				if (aQuestions != null)
				{
					CSCQuestion currentQuestion=null;
					//Output results
					Iterator i=aQuestions.iterator();
					int counter=0;
					int curRowSequence=-99;
					boolean startNewRow=true;
					boolean rowAlreadyOpen=false;
					while(i.hasNext()){
						startNewRow=true;
						currentQuestion=(CSCQuestion)i.next();
					
						if(type.equalsIgnoreCase(UIConstants.INPUT))
						{
							if(curRowSequence == currentQuestion.getRowSequence())
								startNewRow=false;
							else
								curRowSequence=currentQuestion.getRowSequence();
						}
						else if(type.equalsIgnoreCase(UIConstants.SUMMARY))
						{
							if(curRowSequence == currentQuestion.getSummaryRowSeq())
								startNewRow=false;
							else
								curRowSequence=currentQuestion.getSummaryRowSeq();
						}
							
						if(startNewRow){
							if(rowAlreadyOpen){
								buffer.append("</tr>");
								
							}
							buffer.append("<tr>"  );
							rowAlreadyOpen=true;
						}
						if(type.equalsIgnoreCase(UIConstants.INPUT)){
							renderInput(currentQuestion,buffer,counter,groupCounter,questionCounter);
						}
						else if(type.equalsIgnoreCase(UIConstants.SUMMARY)){
							renderSummary(currentQuestion,buffer,questionCounter);
						}
						counter++;
						questionCounter++;
					}
					if(rowAlreadyOpen)
						buffer.append("</tr>");
				}
				groupCounter++;
			}
			
		}

		private void renderInput(CSCQuestion aQuestion, StringBuffer aBuffer,int aCounter,int aGroupCounter, int aQuestionNumber) throws IOException, JspException{
			if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_RADIO)){
				renderRadio(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_MCE_TEXTBOX)){
				renderMceTextBox(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SPELL_CHECK)){
				renderSpellCheck(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXTBOX)){
				renderTextBox(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXT)){
				renderText(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SELECT)){
				renderSelect(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	 
			} 
			else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_HIDDEN)){
				renderHidden(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	 
			} 
			else if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX)){
				renderCheckBox(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	 
			} 
			
		}// End Render Input

	private Collection findAllQuestionsInRow(int aRowSequence, int aColumnSequence)
	{
		isQuestionInLastColumn = true;
				
		ArrayList myArrayList=new ArrayList();
		ArrayList tempArr;
		
		if(type.equalsIgnoreCase(UIConstants.INPUT))
		{
			Iterator groupIterator = questionGroupList.iterator();
			while(groupIterator.hasNext())
			{
				CSCQuestionGroup questionGrp = (CSCQuestionGroup)groupIterator.next();
				tempArr = (ArrayList)questionGrp.getQuestions();
			
				for(int loopX=0; loopX<tempArr.size(); loopX++)
				{
					CSCQuestion currQ=(CSCQuestion)tempArr.get(loopX);
					if(currQ!=null && currQ.getRowSequence()==aRowSequence)
					{
						myArrayList.add(currQ);
						
						if(aColumnSequence < currQ.getColumnSequence())
						{
							isQuestionInLastColumn = false;
						}
					}
				}
			}
		}
		else if(type.equalsIgnoreCase(UIConstants.SUMMARY))
		{
			Iterator groupIterator = questionGroupList.iterator();
			while(groupIterator.hasNext())
			{
				CSCQuestionGroup questionGrp = (CSCQuestionGroup)groupIterator.next();
				tempArr = (ArrayList)questionGrp.getQuestions();
			
				for(int loopX=0; loopX<tempArr.size(); loopX++)
				{
					CSCQuestion currQ=(CSCQuestion)tempArr.get(loopX);
					if(currQ!=null && currQ.getSummaryRowSeq()==aRowSequence)
					{
						myArrayList.add(currQ);
						
						if(aColumnSequence < currQ.getSummaryColSeq())
						{
							isQuestionInLastColumn = false;
						}
					}
				}
			}
		}
		
		return myArrayList;
	}
	
	
	
	private int[] getQuestionAnswerColumnSpan(CSCQuestion aQuestion)
	{
		int questionColumnSpan = 1;
		int answerColumnSpan = 1;
		int[] questionAnswerCoumnArr = new int[2];
		questionAnswerCoumnArr[QUESTION_INDEX] = questionColumnSpan;
		questionAnswerCoumnArr[ANSWER_INDEX] = answerColumnSpan;
		
		if(type.equalsIgnoreCase(UIConstants.INPUT))
		{
			if(aQuestion.getColumnSequence()>0)
			{
				Collection questionsOnSameRowList =findAllQuestionsInRow(aQuestion.getRowSequence(), aQuestion.getColumnSequence());
				int numberOfQuestionsOnSameRow = questionsOnSameRowList.size();
				
	//			if all the coumns are occupied the questions
				if((numberOfQuestionsOnSameRow*2) == columns)
				{
					return questionAnswerCoumnArr;
				}
	//			if number of QuestionsOnSameRow < columns
				else
				if((numberOfQuestionsOnSameRow*2) < columns)
				{	
					if (aQuestion.isResponseNextLine())
					{
						questionAnswerCoumnArr[QUESTION_INDEX] = columns;
						questionAnswerCoumnArr[ANSWER_INDEX] = columns;
					}
					else	
					if(!isQuestionInLastColumn)
					{
						return questionAnswerCoumnArr;
					}
	//				if it is the last question in the column
					else
					{
						answerColumnSpan = answerColumnSpan + (columns - (numberOfQuestionsOnSameRow*2));
						questionAnswerCoumnArr[ANSWER_INDEX] = answerColumnSpan;
						return questionAnswerCoumnArr;
					}
				}
				
			}
		}
		else
			if(type.equalsIgnoreCase(UIConstants.SUMMARY))
			{
				if(aQuestion.getSummaryColSeq()>0)
				{
					Collection questionsOnSameRowList =findAllQuestionsInRow(aQuestion.getSummaryRowSeq(), aQuestion.getSummaryColSeq());
					int numberOfQuestionsOnSameRow = questionsOnSameRowList.size();
					
		//			if all the coumns are occupied the questions
					if((numberOfQuestionsOnSameRow*2) == columns)
					{
						return questionAnswerCoumnArr;
					}
		//			if number of QuestionsOnSameRow < columns
					else
					if((numberOfQuestionsOnSameRow*2) < columns)
					{	
						if (aQuestion.isResponseNextLine())
						{
							questionAnswerCoumnArr[QUESTION_INDEX] = columns;
							questionAnswerCoumnArr[ANSWER_INDEX] = columns;
						}
						else	
						if(!isQuestionInLastColumn)
						{
							return questionAnswerCoumnArr;
						}
		//				if it is the last question in the column
						else
						{
							answerColumnSpan = answerColumnSpan + (columns - (numberOfQuestionsOnSameRow*2));
							questionAnswerCoumnArr[ANSWER_INDEX] = answerColumnSpan;
							return questionAnswerCoumnArr;
						}
					}
					
				}
			}
		return questionAnswerCoumnArr;
	}
	
	

	
	private void renderHidden(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException, JspException
	{
		aBuffer.append("<td class=formDeLabel colspan=" + columns + " align=" + "\"" + aQuestion.getQuestionAlign() + "\">");		
		aBuffer.append(aQuestion.getQuestionText());
		aBuffer.append("</td>");		
	}


	
	private void renderCheckBox(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException, JspException
	{
		int[] questionAnswerColumnSpan = new int[2];
		if(aQuestion.isResponseNextLine())
		{
			questionAnswerColumnSpan[QUESTION_INDEX]=columns;
			questionAnswerColumnSpan[ANSWER_INDEX]=columns;
		}
		else
		{
			questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
		}
		
		if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "\">");
		}
		else
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + ">");
		}
		if(aQuestion.isRequiredImageShown())
		{
			aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
		}
		aBuffer.append(aQuestion.getQuestionText());
		aBuffer.append("</td>");
		
		if(aQuestion.isResponseNextLine())
			aBuffer.append("</tr><tr>");
		
		aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
		
		boolean answerMatches=aQuestion.answerMatchesChoice();
				
		if(aQuestion.getResponseChoices()!=null && aQuestion.getResponseChoices().size()>0)
		{
			Iterator it = aQuestion.getResponseChoices().iterator();			
			
			int index = 0;
			String[] originalSelectedRespIdsArr = aQuestion.getSelectedResponseIdsArr();
			aQuestion.setSelectedResponseIdsArr(null);
			
			while(it.hasNext())
			{
				CSCQuestionResponse possibleResponse = (CSCQuestionResponse)it.next();
				
				aBuffer.append("<input type=\"checkbox\" name=\"");
				aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].selectedResponseIdsArr" + "\"" + " value=\"" + possibleResponse.getResponseId() + "\"");
				
				if(!answerMatches)
				{
					if(possibleResponse.isDefault())
					{
						aBuffer.append(" checked");
					}
				}
				else					
				{
					for(int x=0; x < originalSelectedRespIdsArr.length; x++)
					{
						if((originalSelectedRespIdsArr[x] != null) && (possibleResponse.getResponseId().equalsIgnoreCase(originalSelectedRespIdsArr[x])))
						{
							aBuffer.append(" checked");
						}
					}
				}
				aBuffer.append(">");
				aBuffer.append(possibleResponse.getResponseDisplayText() + " ");
				index++;
			}
		}
		aBuffer.append("</td>");
	}

	
	
	private void renderSelect(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException, JspException
	{
		int[] questionAnswerColumnSpan = new int[2];
		if(aQuestion.isResponseNextLine())
		{
			questionAnswerColumnSpan[QUESTION_INDEX]=columns;
			questionAnswerColumnSpan[ANSWER_INDEX]=columns;
		}
		else
		{
			questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
		}
		
		
		if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "\">");
		}
		else
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + ">");
		}
		if(aQuestion.isRequiredImageShown())
		{
			aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
		}
		aBuffer.append(aQuestion.getQuestionText());
		aBuffer.append("</td>");
		
		if(aQuestion.isResponseNextLine())
			aBuffer.append("</tr><tr>");
		
		aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
		boolean answerMatches=aQuestion.answerMatchesChoice();
		
//		validating to add tabindex, if the question text is integer (i.e. tab index is added only for SCS questions)
		String questionText = aQuestion.getQuestionText();
		try
		{
			Integer intg = new Integer(questionText);
			
			aBuffer.append("<select tabindex="); //slin: setting the tabindex to the question text allows easier data entry for specific assessments eg: scs where the question text is the int question number.
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append(" name=\"");
		}
		catch(Exception ex)
		{
			aBuffer.append("<select name=\"");
		}
		
		aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseId");
		aBuffer.append("\" >");
		if(aQuestion.getResponseChoices()!=null && aQuestion.getResponseChoices().size()>0){
			Iterator i=aQuestion.getResponseChoices().iterator();
			while(i.hasNext()){
				CSCQuestionResponse myAnswer=(CSCQuestionResponse)i.next();
				
				
					aBuffer.append("<option value=\"" + myAnswer.getResponseId() + "\" ");
					if(!answerMatches){
						if(myAnswer.isDefault())
							aBuffer.append(" selected ");
					}
					else if(answerMatches && aQuestion.getResponseId().equalsIgnoreCase(myAnswer.getResponseId()))
						aBuffer.append(" selected ");
					
					aBuffer.append(" >");
					aBuffer.append(myAnswer.getResponseDisplayText());
					aBuffer.append("</option>");
			
			}
		}
		aBuffer.append("</select>");
		aBuffer.append("</td>");
	}

	
	 protected void addOption(StringBuffer sb, String label, String value, boolean matched, boolean filter) {

        sb.append("<option value=\"");
        if (filter) {
            sb.append(ResponseUtils.filter(value));
        } else {
            sb.append(value);
        }
        sb.append("\"");
        if (matched) {
            sb.append(" selected=\"selected\"");
        }
       
        
        sb.append(">");
        
        if (filter) {
            sb.append(ResponseUtils.filter(label));
        } else {
            sb.append(label);
        }
        
        sb.append("</option>\r\n");

    }

		private void renderText(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(aQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
			}
			
			
			if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + ">");
			}
			if(aQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			}
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			
			if(aQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			if(aQuestion.getUiControlSize()<=0)
			{
				aBuffer.append("<input type=\"text\" name=\"");
			}
			else
			{
				int size=aQuestion.getUiControlSize()+2;
				aBuffer.append("<input type=\"text\" size=\"" + size + "\" maxlength=\"" +  aQuestion.getUiControlSize() +"\" name=\"");
			}
			
			aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseText\"");

				if(aQuestion.getResponseText()!=null)
				{
					aBuffer.append(" value=\"");
					aBuffer.append(aQuestion.getResponseText());
					aBuffer.append("\""); 
				}
			aBuffer.append("/>");
			if(aQuestion.getFormatKey()!=null && !(aQuestion.getFormatKey().trim().equalsIgnoreCase("")))
				aBuffer.append("(" + aQuestion.getFormatKey() + ")");
			aBuffer.append("</td>");
		}
		
		
		
		/**
		 * 
		 * @param aQuestion
		 * @param aBuffer
		 * @param aCounter
		 * @param aGroupCounter
		 * @param aQuestionNumber
		 * @throws IOException
		 */
		private void renderMceTextBox(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(aQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
			}
			
			if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=\"formDeLabel spacer\" colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=\"formDeLabel spacer\" colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(aQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			
			if(aQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");

			aBuffer.append("<textarea rows=\""+ aQuestion.getUiControlSize() + "\" class=mceEditor style=\"width:100%\" name=\"");
			aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseText\"");
			aBuffer.append(" onkeyup=\"textLimit(this, 500);\" ondblclick=\"myReverseTinyMCEFix(this)\">");
			
			if(aQuestion.getResponseText()!=null)
			{
				aBuffer.append(aQuestion.getResponseText());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}										
		

		
		/**
		 * 
		 * @param aQuestion
		 * @param aBuffer
		 * @param aCounter
		 * @param aGroupCounter
		 * @param aQuestionNumber
		 * @throws IOException
		 */
		private void renderSpellCheck(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			String textAreaPropertyName = property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseText";
				
			if(aQuestion.isResponseNextLine()){
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			} else {
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
			}
			
			aBuffer.append("<td class=\"formDeLabel spacer\" colspan=\"" + questionAnswerColumnSpan[QUESTION_INDEX] + "\" ");
			if(aQuestion.getQuestionColumnWidth() != null && aQuestion.getQuestionColumnWidth().length() > 0){
				aBuffer.append(" width=\"" + aQuestion.getQuestionColumnWidth()+ "%\">");
			} else {
				aBuffer.append(">");
			}
			
			if(aQuestion.isRequiredImageShown()) {
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("<script language=\"JavaScript\">var rsTCIntspellCheckerButtonId"+aCounter+" = new RSStandardInterface(\""+textAreaPropertyName+"\");</script> <img src=\"../../../images/spellingButton.png\" style=\"cursor:pointer;\" onClick=\"popUpCheckSpellingspellCheckerButtonId('rsTCIntspellCheckerButtonId"+aCounter+"')\"/>");
			aBuffer.append("</td>");
			
			if(aQuestion.isResponseNextLine())
				aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=\"formDe\" colspan=\"" + questionAnswerColumnSpan[ANSWER_INDEX] + "\">");

			aBuffer.append("<textarea rows=\""+ aQuestion.getUiControlSize() + "\" style=\"width:100%\" name=\"");
			aBuffer.append(textAreaPropertyName+"\"");
			aBuffer.append(" onkeyup=\"textLimit(this, 500);\" />");
			
			if(aQuestion.getResponseText()!=null)
			{
				aBuffer.append(aQuestion.getResponseText());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}										

		
		
		
		/**
		 * 
		 * @param aQuestion
		 * @param aBuffer
		 * @param aCounter
		 * @param aGroupCounter
		 * @param aQuestionNumber
		 * @throws IOException
		 */
		private void renderTextBox(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(aQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
			}
			
			if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + ">");
			}
			if(aQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			}
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			
			if(aQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
		
			aBuffer.append("<textarea rows=\""+ aQuestion.getUiControlSize() + "\" style=\"width:100%\" name=\"");
			aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseText\"");
			
			if(aQuestion.getTextLength()>0)
			{
				aBuffer.append(" onmouseout=\"textLimit(this," + aQuestion.getTextLength()+")\"");			
				aBuffer.append(" onkeyup=\"textLimit(this," + aQuestion.getTextLength()+")\"");
			}	
			aBuffer.append(">");
			
			
			if(aQuestion.getResponseText()!=null)
			{
				aBuffer.append(aQuestion.getResponseText());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}
		
		
		
		/**
		 * 
		 * @param aQuestion
		 * @param aBuffer
		 * @param aCounter
		 * @param aGroupCounter
		 * @param aQuestionNumber
		 * @throws IOException
		 */
		private void renderRadio(CSCQuestion aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(aQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
			}
			
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + ">");	
			if(aQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			}
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			
			if(aQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			Iterator i=aQuestion.getResponseChoices().iterator();
			boolean answerMatches=aQuestion.answerMatchesChoice();
			while(i.hasNext())
			{
				CSCQuestionResponse myAnswer=(CSCQuestionResponse)i.next();
				
				if(aQuestion.isEachResponseNewLine())
				{
					aBuffer.append("<div>");
				}
				
				aBuffer.append("<input type=\"radio\" name=\"");
				aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].responseId");
				aBuffer.append("\" value=\"");
				aBuffer.append(myAnswer.getResponseId());
				aBuffer.append("\""); 
				if(!answerMatches)
					if(myAnswer.isDefault())
						aBuffer.append(" checked ");
				if(answerMatches && aQuestion.getResponseId().equalsIgnoreCase(myAnswer.getResponseId()))
					aBuffer.append(" checked ");
				aBuffer.append("/>");
				aBuffer.append(myAnswer.getResponseDisplayText());
				
				if(aQuestion.isEachResponseNewLine())
				{
					aBuffer.append("</div>");
				}
				else
				{
					aBuffer.append("&nbsp;");
				}
			}
			aBuffer.append("</td>");
		}
	
	
		
		private void renderSummary(CSCQuestion aQuestion, StringBuffer aBuffer, int aQuestionNumber)
		{
			
			if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_HIDDEN))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + columns + " align=" + "\"" + aQuestion.getQuestionAlign() + "\">");		
				aBuffer.append(aQuestion.getQuestionText());
				aBuffer.append("</td>");		
			}
			else
			{
				int[] questionAnswerColumnSpan = new int[2];
				
				if (aQuestion.isResponseNextLine()) {
					questionAnswerColumnSpan[QUESTION_INDEX] = columns;
					questionAnswerColumnSpan[ANSWER_INDEX] = columns;
				} 
				else
				{
					questionAnswerColumnSpan = getQuestionAnswerColumnSpan(aQuestion);
				}
				
				if((aQuestion.getQuestionColumnWidth()!=null) && (!(aQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + aQuestion.getQuestionColumnWidth()+ "\">");
				}
				else
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + ">");
				}
		
				aBuffer.append(aQuestion.getQuestionText());
				aBuffer.append("</td>");
		
				if (aQuestion.isResponseNextLine())
					aBuffer.append("</tr><tr>");
				
				
				if((aQuestion.getSummaryRespColumnWidth()!=null) && (!(aQuestion.getSummaryRespColumnWidth().equalsIgnoreCase(""))))
				{
					aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + " nowrap" + " width=\"" + aQuestion.getSummaryRespColumnWidth()+ "\">");
				}
				else
				{
					aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				}
				
					if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_RADIO) ||
							(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SELECT)))
					{
						if ((aQuestion.getResponseId() != null) && (!aQuestion.getResponseId().equals(""))) {
							Iterator i = aQuestion.getResponseChoices().iterator();
							while (i.hasNext()) {
								CSCQuestionResponse possibleAnswer = (CSCQuestionResponse) i.next();
//			(possibleAnswer.getResponseValue() == "") for "Please Select" response option for UI_CNTRL_TYPE_SELECT
								if ((possibleAnswer.getResponseId().equalsIgnoreCase(aQuestion.getResponseId())) && (!(possibleAnswer.getResponseValue().equalsIgnoreCase(""))))
								{
									aBuffer.append(possibleAnswer.getResponseDisplayText());
								}
							}
						} else {
							aBuffer.append(" ");
						}
					}					
					else
						if((aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXT)) ||
								(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SPELL_CHECK)) ||
								(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXTBOX)) ||
								(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_MCE_TEXTBOX)))
						{
							if ((aQuestion.getResponseText() != null) && (!(aQuestion.getResponseText().equalsIgnoreCase("")))) 
							{
								aBuffer.append(aQuestion.getResponseText());
							} 
							else 
							{
								aBuffer.append(" ");
							}
						}
						else
							if(aQuestion.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX))
							{
								if((aQuestion.getSelectedResponseIdsArr() != null) && (aQuestion.getSelectedResponseIdsArr().length > 0))
								{
									String[] selectedRespArr = aQuestion.getSelectedResponseIdsArr();
									for(int index=0; index < selectedRespArr.length; index++)
									{
										String respId = selectedRespArr[index];
										Iterator i = aQuestion.getResponseChoices().iterator();
										while (i.hasNext()) 
										{
											CSCQuestionResponse possibleAnswer = (CSCQuestionResponse) i.next();
											if (possibleAnswer.getResponseId().equalsIgnoreCase(respId))
											{
												aBuffer.append(possibleAnswer.getResponseDisplayText());
												aBuffer.append("&nbsp;");
											}
										}
									}
								}
								else
								{
									aBuffer.append(" ");
								}
							}
							
				aBuffer.append("</td>");
			}
	}// End render Summary
		
		
		
	public int doStartTag() throws JspException
		{
			try
			{
				Collection beanProperty = (Collection) this.getReadObject();
				
				StringBuffer buffer = new StringBuffer();
				this.renderBody(beanProperty, buffer);
				JspWriter writer = pageContext.getOut();
				writer.print(buffer.toString());
			}
			catch (Exception e)
			{
				// TODO Needs proper exception handling
				throw new JspException(e.getMessage());
			}
			//return Tag.SKIP_BODY;
			return Tag.EVAL_BODY_INCLUDE;
			
		}
	
	private Object getReadObject() throws Exception
	{
		// attempt to locate bean in the page context
		Object beanObj = pageContext.getAttribute(this.name);

		if (beanObj == null)
		{
			// attempt to locate bean in the request context
			beanObj = pageContext.getRequest().getAttribute(this.name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the session context
			beanObj = pageContext.getSession().getAttribute(this.name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the application context
			beanObj = pageContext.getServletContext().getAttribute(this.name);
		}

		StringBuffer buffer = new StringBuffer(this.property);
		String firstChar = String.valueOf(buffer.charAt(0)).toUpperCase();
		buffer.replace(0, 1, firstChar);
		buffer.insert(0, "get");
	
		String getterName = buffer.toString();
	
		Method method = beanObj.getClass().getMethod(getterName, (Class[]) null);

		return method.invoke(beanObj, (Object[]) null);
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getProperty()
	{
		return property;
	}

	

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		property = string;
	}

	
	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

	/**
	 * @return
	 */
	public int getColumns()
	{
		return columns;
	}

	/**
	 * @param i
	 */
	public void setColumns(int i)
	{
		columns = i;
	}	
	/**
	 * @return Returns the requiredGif.
	 */
	public String getRequiredGif() {
		return requiredGif;
	}
	/**
	 * @param requiredGif The requiredGif to set.
	 */
	public void setRequiredGif(String requiredGif) {
		this.requiredGif = requiredGif;
	}
	
	/**
	 * @return Returns the isQuestionInLastColumn.
	 */
	public boolean isQuestionInLastColumn() {
		return isQuestionInLastColumn;
	}
	/**
	 * @param isQuestionInLastColumn The isQuestionInLastColumn to set.
	 */
	public void setQuestionInLastColumn(boolean isQuestionInLastColumn) {
		this.isQuestionInLastColumn = isQuestionInLastColumn;
	}
	/**
	 * @return Returns the maxColumnsFound.
	 */
	public int getMaxColumnsFound() {
		return maxColumnsFound;
	}
	/**
	 * @param maxColumnsFound The maxColumnsFound to set.
	 */
	public void setMaxColumnsFound(int maxColumnsFound) {
		this.maxColumnsFound = maxColumnsFound;
	}
	/**
	 * @return Returns the questionGroupList.
	 */
	public Collection getQuestionGroupList() {
		return questionGroupList;
	}
	/**
	 * @param questionGroupList The questionGroupList to set.
	 */
	public void setQuestionGroupList(Collection questionGroupList) {
		this.questionGroupList = questionGroupList;
	}
}
