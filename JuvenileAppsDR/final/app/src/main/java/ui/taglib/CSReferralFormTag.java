package ui.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import messaging.contact.domintf.IPhoneNumber;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;
import ui.common.StringUtil;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.RefFormFieldOption;
import ui.supervision.programReferral.ReferralFormField;



public class CSReferralFormTag extends TagSupport
{
	public static final int QUESTION_INDEX = 0;
    public static final int ANSWER_INDEX = 1;
	
	private String name;
	private String property;
	private String requiredGif;
	private String calendarGif;
	private String type; // Valid values are input & summary
	
	private int columns=2;
	private int maxColumnsFound=columns;	
	private Collection refFormQuestionList=null;
	private boolean isQuestionInLastColumn = false;
	private List dependantFieldsList = new ArrayList();
	
	private static String DEPENDANT_FIELD_LABEL_ID = "fieldLabelComments";
	private static String DEPENDANT_FIELD_DATA_ID = "fieldComments";
	private static String SPAN_ID = "spanId";
	
	private boolean isDependantFieldShown = false;
	private StringBuffer initialValidationBf = null;
	
	private void renderBody(Collection refFormFieldsList, StringBuffer buffer) throws Exception
	{
		if(refFormFieldsList==null || refFormFieldsList.size()<=0)
			return;
		
		initialValidationBf = new StringBuffer();
		
		CSCProgRefUIHelper.sortRefFormFields((ArrayList)refFormFieldsList);
		refFormQuestionList = refFormFieldsList;
		
		maxColumnsFound=CSCProgRefUIHelper.maxColumnSizeForRefFormFields(refFormFieldsList,type);
		if((maxColumnsFound *2)>columns)
			columns=maxColumnsFound *2;	
		
		int questionCounter=1;
		
		ReferralFormField currentField=null;
		//Output results
		Iterator fieldIter=refFormFieldsList.iterator();
		int counter=0;
		int curRowSequence=-99;
		boolean startNewRow=true;
		boolean rowAlreadyOpen=false;
		while(fieldIter.hasNext()){
			startNewRow=true;
			currentField=(ReferralFormField)fieldIter.next();
		
			if(type.equalsIgnoreCase(UIConstants.INPUT))
			{
				if(curRowSequence == currentField.getRowSequence())
					startNewRow=false;
				else
					curRowSequence=currentField.getRowSequence();
			}
			else if(type.equalsIgnoreCase(UIConstants.SUMMARY))
			{
				if(curRowSequence == currentField.getSummaryRowSeq())
					startNewRow=false;
				else
					curRowSequence=currentField.getSummaryRowSeq();
			}
				
			if(startNewRow){
				if(rowAlreadyOpen){
					buffer.append("</tr>");
					
				}
				if(dependantFieldsList.contains(currentField.getFieldId()))
				{
					buffer.append("<tr id=" + DEPENDANT_FIELD_LABEL_ID + " class=hidden>"  );
				}
				else
				{
					buffer.append("<tr>"  );
				}
				rowAlreadyOpen=true;
			}
			
			String dependantFieldId = currentField.getChildFieldId();
			if(dependantFieldId!=null && !(dependantFieldId.trim().equalsIgnoreCase("")))
			{
				dependantFieldsList.add(dependantFieldId);
			}
			
			if(type.equalsIgnoreCase(UIConstants.INPUT))
			{
				renderInput(currentField,buffer,counter,questionCounter);
			}
			else if(type.equalsIgnoreCase(UIConstants.SUMMARY))
			{
				
				if(dependantFieldsList.contains(currentField.getFieldId()))
				{
					if(isDependantFieldShown)
					{
						renderSummary(currentField,buffer,questionCounter);
					}
				}
				else
				{
					renderSummary(currentField,buffer,questionCounter);
				}
				
			}
			counter++;
			questionCounter++;
		}
		if(rowAlreadyOpen)
			buffer.append("</tr>");
		
		if(type.equalsIgnoreCase(UIConstants.INPUT))
		{
			buffer.append(initialValidationBf);
		}
	}

		private void renderInput(ReferralFormField referralFormField, StringBuffer aBuffer,int aCounter, int aQuestionNumber) throws IOException, JspException
		{
			if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_RADIO)){
				renderRadio(referralFormField,aBuffer,aCounter, aQuestionNumber);	
			}
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXTBOX))
			{
				renderTextBox(referralFormField,aBuffer,aCounter, aQuestionNumber);	
			}
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX))
			{
				renderMceTextBox(referralFormField,aBuffer,aCounter, aQuestionNumber);	
			}
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXT))
			{
				if(referralFormField.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_DATE))
				{
					renderDate(referralFormField,aBuffer,aCounter, aQuestionNumber);
				}
				else
				if(referralFormField.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
				{
					renderPhone(referralFormField,aBuffer,aCounter, aQuestionNumber);
				}
				else
				{
					renderText(referralFormField,aBuffer,aCounter, aQuestionNumber);
				}
			}
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SELECT))
			{
				renderSelect(referralFormField,aBuffer,aCounter, aQuestionNumber);
			} 
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_HIDDEN)){
				renderHidden(referralFormField,aBuffer,aCounter, aQuestionNumber);	 
			} 
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_CHECKBOX)){
				renderCheckBox(referralFormField,aBuffer,aCounter, aQuestionNumber);	 
			} 
			else if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SINGLE_CHECKBOX))
			{
				renderSingleCheckBox(referralFormField,aBuffer,aCounter, aQuestionNumber);	 
			} 
			
		}// End Render Input

		
	
		private void renderSelect(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException, JspException
		{
			int[] questionAnswerColumnSpan = new int[2];
			
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
				aBuffer.append("</tr><tr>");
			
//			to check if any editable option exits
			boolean isEditableOptionExist = false;
			
			List possibleRespList = refFormQuestion.getResponseChoices();
			if(possibleRespList!=null && possibleRespList.size()>0)
			{
				Iterator respIter=possibleRespList.iterator();
				while(respIter.hasNext())
				{
					RefFormFieldOption option = (RefFormFieldOption)respIter.next();
					if(option.isOptionEdit())
					{
						isEditableOptionExist = true;
						break;
					}
				}
			}
			
			if(isEditableOptionExist)
			{
				StringBuffer tempBuffer = new StringBuffer();
				int optionCounter = 0; 
				
				aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				boolean answerMatches=refFormQuestion.answerMatchesChoice();
				aBuffer.append("<select name=\"");
				aBuffer.append(property +  "[" + aCounter + "].responseId\"");
				
				String methodName= "checkForEditOption" + aCounter;
				aBuffer.append(" onchange=" + methodName + "(this)>");
				if(refFormQuestion.getResponseChoices()!=null && refFormQuestion.getResponseChoices().size()>0)
				{
					Iterator i=refFormQuestion.getResponseChoices().iterator();
					
					while(i.hasNext())
					{
						RefFormFieldOption myAnswer=(RefFormFieldOption)i.next();
						
						aBuffer.append("<option value=\"" + myAnswer.getOptionId() + "\" "); 
						if(!answerMatches){
							if(myAnswer.isDefault())
								aBuffer.append(" selected ");
						}
						else if(answerMatches && refFormQuestion.getResponseId().equalsIgnoreCase(myAnswer.getOptionId()))
							aBuffer.append(" selected ");
						
						aBuffer.append(" >");
						aBuffer.append(myAnswer.getOptionDisplayText());
						aBuffer.append("</option>");
						
						if(myAnswer.isOptionEdit())
						{
							tempBuffer.append("<span id=" + SPAN_ID + " class=hidden>");
							tempBuffer.append("&nbsp;<input type=\"text\" name=\"" + property + "[" + aCounter + "].responseChoices[" + optionCounter + "].userEnteredOptionName\"");
	
							if(myAnswer.getUserEnteredOptionName()!=null)
							{
								tempBuffer.append(" value=\"");
								tempBuffer.append(myAnswer.getUserEnteredOptionName());
								tempBuffer.append("\""); 
							}
							tempBuffer.append("></span>");
	
							tempBuffer.append("<script> function " + methodName + "(theSelect){ " );
							tempBuffer.append("var theVal = theSelect.options[theSelect.selectedIndex].value;");
							tempBuffer.append("if(theVal == \"" + myAnswer.getOptionId() + "\"){");
							tempBuffer.append("show('" + SPAN_ID + "', 1, 'inline');");
							tempBuffer.append("} else{ ");
							tempBuffer.append("show('" + SPAN_ID + "', 0);}}</script>");
							
							if(answerMatches && refFormQuestion.getResponseId().equalsIgnoreCase(myAnswer.getOptionId()))
							{
								StringBuffer initialValBf = new StringBuffer();
								initialValBf.append("<script>");
								initialValBf.append("show('" + SPAN_ID + "', 1, 'inline');");
								initialValBf.append("</script>");
								
								this.initialValidationBf.append(initialValBf);
							}
						}
						optionCounter++;
					}
				}
				aBuffer.append("</select>");
				
				aBuffer.append(tempBuffer.toString());
				
				aBuffer.append("</td>");
			}
			else
			{
				aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				boolean answerMatches=refFormQuestion.answerMatchesChoice();
				aBuffer.append("<select name=\"");
				aBuffer.append(property +  "[" + aCounter + "].responseId");
				aBuffer.append("\" >");
				if(refFormQuestion.getResponseChoices()!=null && refFormQuestion.getResponseChoices().size()>0){
					Iterator i=refFormQuestion.getResponseChoices().iterator();
					while(i.hasNext()){
						RefFormFieldOption myAnswer=(RefFormFieldOption)i.next();
						
							aBuffer.append("<option value=\"" + myAnswer.getOptionId() + "\" "); 
							if(!answerMatches){
								if(myAnswer.isDefault())
									aBuffer.append(" selected ");
							}
							else if(answerMatches && refFormQuestion.getResponseId().equalsIgnoreCase(myAnswer.getOptionId()))
								aBuffer.append(" selected ");
							
							aBuffer.append(" >");
							aBuffer.append(myAnswer.getOptionDisplayText());
							aBuffer.append("</option>");
					
					}
				}
				aBuffer.append("</select>");
				aBuffer.append("</td>");
			}
		}

		
		
		private void renderMceTextBox(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth() + "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
			{
				if(dependantFieldsList.contains(refFormQuestion.getFieldId()))
				{
					aBuffer.append("</tr><tr id=" + DEPENDANT_FIELD_DATA_ID + " class=hidden>");
				}
				else
				{
					aBuffer.append("</tr><tr>");
				}
			}
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			aBuffer.append("<textarea rows=\""+ refFormQuestion.getUiControlSize() + "\" class=mceEditor style=\"width:100%\" name=\"");
			aBuffer.append(property +  "[" + aCounter + "].responseText\"");
			aBuffer.append(" ondblclick=\"myReverseTinyMCEFix(this)\">");
			
			if(refFormQuestion.getResponseText()!=null)
			{
				aBuffer.append(refFormQuestion.getResponseText());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}
		
		
	
		private void renderTextBox(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth() + "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
			{
				if(dependantFieldsList.contains(refFormQuestion.getFieldId()))
				{
					aBuffer.append("</tr><tr id=" + DEPENDANT_FIELD_DATA_ID + " class=hidden>");
				}
				else
				{
					aBuffer.append("</tr><tr>");
				}
			}
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			aBuffer.append("<textarea rows=\""+ refFormQuestion.getUiControlSize() + "\" style=\"width:100%\" name=\"");
			aBuffer.append(property +  "[" + aCounter + "].responseText\"");
			if(refFormQuestion.getTextLength()>0)
				aBuffer.append(" onkeyup=\"textCounter(this," + refFormQuestion.getTextLength()+")");
			aBuffer.append("\">");
			
			if(refFormQuestion.getResponseText()!=null)
			{
				aBuffer.append(refFormQuestion.getResponseText());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}
		 
		 	private void renderDate(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
			{
		 		String uniqueNumber = String.valueOf(refFormQuestion.getRowSequence()) + refFormQuestion.getColumnSequence();
				
				int[] questionAnswerColumnSpan = new int[2];
				if(refFormQuestion.isResponseNextLine())
				{
					questionAnswerColumnSpan[QUESTION_INDEX]=columns;
					questionAnswerColumnSpan[ANSWER_INDEX]=columns;
				}
				else
				{
					questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
				}
				
				
				if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
				}
				else
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
				}
				if(refFormQuestion.isRequiredImageShown())
				{
					aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
				}
				aBuffer.append(refFormQuestion.getFieldLabel());
				aBuffer.append("</td>");
				
				if(refFormQuestion.isResponseNextLine())
							aBuffer.append("</tr><tr>");
				
				aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				
				if(refFormQuestion.getUiControlSize()<=0)
				{
					aBuffer.append("<input type=\"text\" name=\"");
				}
				else
				{
					aBuffer.append("<input type=\"text\" size=\"" + refFormQuestion.getUiControlSize() + "\" maxlength=\"" +  refFormQuestion.getUiControlSize() +"\" name=\"");
				}
				
				aBuffer.append(property +  "[" + aCounter + "].responseText\"");

				if(refFormQuestion.getResponseText()!=null)
				{
					aBuffer.append("\" value=\"");
					aBuffer.append(refFormQuestion.getResponseText());
					aBuffer.append("\""); 
				}
					
				aBuffer.append(" title=\"(mm/dd/yyyy)\" id=\"responseText" + uniqueNumber + "\"");
				aBuffer.append("/>&nbsp;");
				
				aBuffer.append("<A HREF=\"#\" onClick=\"cal.select(document.getElementById('responseText" + uniqueNumber + "'), 'anchor" +  uniqueNumber + "', 'MM/dd/yyyy'); return false;\" NAME=\"anchor" + uniqueNumber + "\" ID=\"anchor" + uniqueNumber + "\" border=0>");
				aBuffer.append("<img src=\"" + calendarGif + "\" alt=\"calendarImage\" title=\"(mm/dd/yyyy)\" border=0 align=\"middle\"></A>");
						
				if(refFormQuestion.getFormatKey()!=null && !(refFormQuestion.getFormatKey().trim().equalsIgnoreCase("")))
					aBuffer.append("(" + refFormQuestion.getFormatKey() + ")");
				aBuffer.append("</td>");
			}
		 	
		 	
		 	
		 	
		 	
		private void renderText(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			if(refFormQuestion.getUiControlSize()<=0)
			{
				aBuffer.append("<input type=\"text\" + maxlength=\"" +  refFormQuestion.getTextLength() +"\" + name=\"");
			}
			else
			{
				aBuffer.append("<input type=\"text\" size=\"" + refFormQuestion.getUiControlSize() + "\" maxlength=\"" +  refFormQuestion.getTextLength() +"\" name=\"");
			}
			
			aBuffer.append(property +  "[" + aCounter + "].responseText\"");

				if(refFormQuestion.getResponseText()!=null)
				{
					aBuffer.append(" value=\"");
					aBuffer.append(refFormQuestion.getResponseText());
					aBuffer.append("\""); 
				}
			aBuffer.append("/>");
			if(refFormQuestion.getFormatKey()!=null && !(refFormQuestion.getFormatKey().trim().equalsIgnoreCase("")))
				aBuffer.append("(" + refFormQuestion.getFormatKey() + ")");
			aBuffer.append("</td>");
		}
		 
		
		
		private void renderPhone(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">&nbsp;");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
						aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
//			areaCode
			IPhoneNumber phoneNumber = refFormQuestion.getResponsePhoneNum();
			aBuffer.append("<input type=\"text\" size=\"3\" maxlength=\"3\" name=\"");
			aBuffer.append(property +  "[" + aCounter + "].responsePhoneNum.areaCode\"");
			if(phoneNumber!=null)
			{
				aBuffer.append(" value=\"" + phoneNumber.getAreaCode() + "\"");
			}
			aBuffer.append(" onkeyup=\"return autoTab(this, 3);\" />&nbsp;-&nbsp;");
//			prefix
			aBuffer.append("<input type=\"text\" size=\"3\" maxlength=\"3\" name=\"");
			aBuffer.append(property +  "[" + aCounter + "].responsePhoneNum.prefix\"");
			if(phoneNumber!=null)
			{
				aBuffer.append(" value=\"" + phoneNumber.getPrefix() + "\"");
			}
			aBuffer.append(" onkeyup=\"return autoTab(this, 3);\" />&nbsp;-&nbsp;");
//			fourDigit
			aBuffer.append("<input type=\"text\" size=\"4\" maxlength=\"4\" name=\"");
			aBuffer.append(property +  "[" + aCounter + "].responsePhoneNum.fourDigit\"");
			if(phoneNumber!=null)
			{
				aBuffer.append(" value=\"" + phoneNumber.getFourDigit() + "\"");
			}
			aBuffer.append(" onkeyup=\"return autoTab(this, 4);\" />");
		
			aBuffer.append("</td>");
		}
		
		
		
		private void renderSingleCheckBox(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException, JspException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			}
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
				aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			aBuffer.append("<input type=\"checkbox\" name=\"");
			String fieldName = property +  "[" + aCounter + "].responseId" ;
			aBuffer.append(fieldName + "\"" + " value=\"" + CSAdministerProgramReferralsConstants.UI_SINGLE_CHECKBOX_CHK_VAL + "\"");
			
			String responseId = refFormQuestion.getResponseId();
			
			if(!StringUtil.isEmpty(responseId))
			{
				aBuffer.append(" checked");
			}
			
			String childFieldId = refFormQuestion.getChildFieldId();
			
			if(!StringUtil.isEmpty(childFieldId))
			{
				String methodName = "checkDependancy" + childFieldId;
				aBuffer.append(" onclick=\"" + methodName + "(this.checked)\">");
				aBuffer.append("<script> function " + methodName + "(theVal){ if(theVal){ " );
				aBuffer.append("show('" + DEPENDANT_FIELD_LABEL_ID + "', 1, 'row');");
				aBuffer.append("show('" + DEPENDANT_FIELD_DATA_ID + "', 1, 'row');");
				
				aBuffer.append("} else{ ");
				aBuffer.append("show('" + DEPENDANT_FIELD_LABEL_ID + "', 0, 'row');");
				aBuffer.append("show('" + DEPENDANT_FIELD_DATA_ID + "', 0, 'row');}}</script>");
				
				if(!StringUtil.isEmpty(responseId))
				{
					StringBuffer initialValBuffer = new StringBuffer();
					
					initialValBuffer.append("<script>");
					initialValBuffer.append("show('" + DEPENDANT_FIELD_LABEL_ID + "', 1, 'row');");
					initialValBuffer.append("show('" + DEPENDANT_FIELD_DATA_ID + "', 1, 'row');");
					initialValBuffer.append("</script>");
					this.initialValidationBf.append(initialValBuffer);
				}
			}
			else
			{
				aBuffer.append(">");
			}
			aBuffer.append("</td>");
		}
		
		
		
		private void renderCheckBox(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException, JspException
		{
			int[] questionAnswerColumnSpan = new int[2];
			if(refFormQuestion.isResponseNextLine())
			{
				questionAnswerColumnSpan[QUESTION_INDEX]=columns;
				questionAnswerColumnSpan[ANSWER_INDEX]=columns;
			}
			else
			{
				questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
			}
			
			if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
			}
			else
			{
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
			}
			if(refFormQuestion.isRequiredImageShown())
			{
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			}
			
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");
			
			if(refFormQuestion.isResponseNextLine())
				aBuffer.append("</tr><tr>");
			
			aBuffer.append("<td colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
			
			aBuffer.append("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\">");
			aBuffer.append("<tr>");
			
			boolean answerMatches=refFormQuestion.answerMatchesChoice();
					
			if(refFormQuestion.getResponseChoices()!=null && refFormQuestion.getResponseChoices().size()>0)
			{
				int noOfResponseInARow = 0;
				if(refFormQuestion.getNoOfResponsesInARow()>0 && refFormQuestion.getNoOfResponsesInARow()< refFormQuestion.getResponseChoices().size())
				{
					noOfResponseInARow = refFormQuestion.getNoOfResponsesInARow();
				}
				
				Iterator it = refFormQuestion.getResponseChoices().iterator();			
				
				int index = 0;
				String[] originalSelectedRespIdsArr = refFormQuestion.getSelectedResponseIdsArr();
				refFormQuestion.setSelectedResponseIdsArr(null);
				int optionCount = 0;
				
				while(it.hasNext())
				{
					RefFormFieldOption possibleResponse = (RefFormFieldOption)it.next();
					
					aBuffer.append("<td class=\"formDeLabel\" width=\"20%\"> ");
					aBuffer.append(possibleResponse.getOptionDisplayText() + " ");
					aBuffer.append("</td>");
					
					
					
					if(possibleResponse.isOptionEdit())
					{
						aBuffer.append("<td class=\"formDe\" width=\"40%\">");
						aBuffer.append("<input type=\"text\" name=\"" + property + "[" + aCounter + "].responseChoices[" + optionCount + "].userEnteredOptionName\"");
						
						if(possibleResponse.getUserEnteredOptionName()!=null)
						{
							aBuffer.append(" value=\"");
							aBuffer.append(possibleResponse.getUserEnteredOptionName());
							aBuffer.append("\""); 
						}
					}
					else
					{
						aBuffer.append("<td class=\"formDe\">");
						aBuffer.append("<input type=\"checkbox\" name=\"");
						aBuffer.append(property +  "[" + aCounter + "].selectedResponseIdsArr" + "\"" + " value=\"" + possibleResponse.getOptionId() + "\"");
						
						if(!answerMatches)
						{
							if(possibleResponse.isDefault())
							{
								aBuffer.append(" checked");
							}
						}
						else					
						{
							if(originalSelectedRespIdsArr!=null)
							{
								for(int x=0; x < originalSelectedRespIdsArr.length; x++)
								{
									if((originalSelectedRespIdsArr[x] != null) && (possibleResponse.getOptionId().equalsIgnoreCase(originalSelectedRespIdsArr[x])))
									{
										aBuffer.append(" checked");
									}
								}
							}
						}
					}
					aBuffer.append(">");
					aBuffer.append("</td>");
					
					
					index++;
					if(index%noOfResponseInARow==0)
					{
						aBuffer.append("</tr>");
						aBuffer.append("<tr>");
					}
					optionCount++;
				}
			}
			aBuffer.append("</tr>");
			aBuffer.append("</table>");
			aBuffer.append("</td>");
		}
		 
		
		
		
		 
		 private void renderRadio(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException
			{
				int[] questionAnswerColumnSpan = new int[2];
				if(refFormQuestion.isResponseNextLine())
				{
					questionAnswerColumnSpan[QUESTION_INDEX]=columns;
					questionAnswerColumnSpan[ANSWER_INDEX]=columns;
				}
				else
				{
					questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
				}
				
				aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + ">");	
				if(refFormQuestion.isRequiredImageShown())
				{
					aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
				}
				aBuffer.append(refFormQuestion.getFieldLabel());
				aBuffer.append("</td>");
				
				if(refFormQuestion.isResponseNextLine())
							aBuffer.append("</tr><tr>");
				
				aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				Iterator i=refFormQuestion.getResponseChoices().iterator();
				boolean answerMatches=refFormQuestion.answerMatchesChoice();
				while(i.hasNext())
				{
					RefFormFieldOption myAnswer=(RefFormFieldOption)i.next();
					
					if(refFormQuestion.isEachResponseNewLine())
					{
						aBuffer.append("<div>");
					}
					
					aBuffer.append("<input type=\"radio\" name=\"");
					aBuffer.append(property +  "[" + aCounter + "].responseId");
					aBuffer.append("\" value=\"");
					aBuffer.append(myAnswer.getOptionId());
					aBuffer.append("\""); 
					if(!answerMatches)
						if(myAnswer.isDefault())
							aBuffer.append(" checked ");
					if(answerMatches && refFormQuestion.getResponseId().equalsIgnoreCase(myAnswer.getOptionId()))
						aBuffer.append(" checked ");
					aBuffer.append("/>");
					aBuffer.append(myAnswer.getOptionDisplayText());
					
					if(refFormQuestion.isEachResponseNewLine())
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
		 
		 
		 
		private void renderHidden(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException, JspException
		{
			aBuffer.append("<td class=formDeLabel colspan=" + columns + " align=" + "\"" + refFormQuestion.getQuestionAlign() + "\"" + " nowrap>");		
			aBuffer.append(refFormQuestion.getFieldLabel());
			aBuffer.append("</td>");		
		} 
		
		private void renderSummary(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aQuestionNumber)
		{
		 	
			if(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_HIDDEN))
			{
				aBuffer.append("<td class=formDeLabel colspan=" + columns + " align=" + "\"" + refFormQuestion.getQuestionAlign() + "\"" + " nowrap>");		
				aBuffer.append(refFormQuestion.getFieldLabel());
				aBuffer.append("</td>");		
			}
			else
			{
				int[] questionAnswerColumnSpan = new int[2];
				
				if (refFormQuestion.isResponseNextLine()) {
					questionAnswerColumnSpan[QUESTION_INDEX] = columns;
					questionAnswerColumnSpan[ANSWER_INDEX] = columns;
				} 
				else
				{
					questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
				}
				
				if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
				}
				else
				{
					aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
				}
		
				aBuffer.append(refFormQuestion.getFieldLabel());
				aBuffer.append("</td>");
		
				if(refFormQuestion.isResponseNextLine())
				{
					if(dependantFieldsList.contains(refFormQuestion.getFieldId()))
					{
						aBuffer.append("</tr><tr id=" + DEPENDANT_FIELD_DATA_ID + " class=hidden>");
					}
					else
					{
						aBuffer.append("</tr><tr>");
					}
				}
				
				
				if((refFormQuestion.getSummaryColumnWidth()!=null) && (!(refFormQuestion.getSummaryColumnWidth().equalsIgnoreCase(""))))
				{
					aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + " width=\"" + refFormQuestion.getSummaryColumnWidth() + "%\">");
				}
				else
				{
					aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
				}
				
				
				if(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_RADIO) || 
						(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SELECT)))
				{
					if ((refFormQuestion.getResponseId() != null) && (!refFormQuestion.getResponseId().equals(""))) {
						Iterator i = refFormQuestion.getResponseChoices().iterator();
						while (i.hasNext()) {
							RefFormFieldOption possibleAnswer = (RefFormFieldOption) i.next();
//					(possibleAnswer.getOptionValue() == "") for "Please Select" response option for UI_CNTRL_TYPE_SELECT
							if ((possibleAnswer.getOptionId().equalsIgnoreCase(refFormQuestion.getResponseId())) && (!(possibleAnswer.getOptionValue().equalsIgnoreCase(""))))
							{
								if(possibleAnswer.isOptionEdit())
								{
									if(!StringUtil.isEmpty(possibleAnswer.getUserEnteredOptionName()))
									{
										aBuffer.append(possibleAnswer.getUserEnteredOptionName());
									}
								}
								else
								{
									aBuffer.append(possibleAnswer.getOptionDisplayText());
								}
							}
						}
					} else {
						aBuffer.append(" ");
					}
				}					
				
				if((refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXT)) ||
						(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXTBOX))||
						(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX)))
				{
					if(refFormQuestion.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
					{
						refFormQuestion.setResponseText(refFormQuestion.getResponsePhoneNum().getFormattedPhoneNumber());
					}
					if(refFormQuestion.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_CURRENCY))
					{
						refFormQuestion.setResponseText(refFormQuestion.getResponseCurrency());
					}
					if ((refFormQuestion.getResponseText() != null) && (!(refFormQuestion.getResponseText().equalsIgnoreCase("")))) 
					{
						aBuffer.append(refFormQuestion.getResponseText());
					} 
					else 
					{
						aBuffer.append(" ");
					}
				}
				else
				if(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_CHECKBOX))
				{
					if((refFormQuestion.getSelectedResponseIdsArr() != null) && (refFormQuestion.getSelectedResponseIdsArr().length > 0))
					{
						String[] selectedRespArr = refFormQuestion.getSelectedResponseIdsArr();
						for(int index=0; index < selectedRespArr.length; index++)
						{
							String respId = selectedRespArr[index];
							Iterator i = refFormQuestion.getResponseChoices().iterator();
							while (i.hasNext()) 
							{
								RefFormFieldOption possibleAnswer = (RefFormFieldOption) i.next();
								if (possibleAnswer.getOptionId().equalsIgnoreCase(respId))
								{
									if(possibleAnswer.isOptionEdit())
									{
										String userEnteredName = possibleAnswer.getUserEnteredOptionName();
										if(userEnteredName!=null && !userEnteredName.trim().equalsIgnoreCase(""))
										{
											aBuffer.append(possibleAnswer.getUserEnteredOptionName());
											if(index<selectedRespArr.length-1)
											{
												aBuffer.append(",&nbsp;");
											}
										}
									}
									else
									{
										aBuffer.append(possibleAnswer.getOptionDisplayText());
										if(index<selectedRespArr.length-1)
										{
											aBuffer.append(",&nbsp;");
										}
									}
								}
							}
						}
					}
					
					Iterator i = refFormQuestion.getResponseChoices().iterator();
					while (i.hasNext()) 
					{
						RefFormFieldOption possibleAnswer = (RefFormFieldOption) i.next();
						if(possibleAnswer.isOptionEdit())
						{
							String userEnteredName = possibleAnswer.getUserEnteredOptionName();
							if(userEnteredName!=null && !userEnteredName.trim().equalsIgnoreCase(""))
							{
								aBuffer.append(",&nbsp;");
								aBuffer.append(possibleAnswer.getUserEnteredOptionName());
								aBuffer.append("&nbsp;");
							}
						}
					}
					
					aBuffer.append("&nbsp;");
				}
				else
				if(refFormQuestion.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SINGLE_CHECKBOX))
				{
					String responseId = refFormQuestion.getResponseId();
					if(responseId!=null && responseId.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_SINGLE_CHECKBOX_CHK_VAL))
					{
						aBuffer.append("Yes");
						aBuffer.append("&nbsp;");
						
						String childFieldId = refFormQuestion.getChildFieldId();
						if(!StringUtil.isEmpty(childFieldId))
						{
							isDependantFieldShown = true;
							
							StringBuffer initialValBuffer = new StringBuffer();
							initialValBuffer.append("<script>");
							initialValBuffer.append("show('" + DEPENDANT_FIELD_LABEL_ID + "', 1, 'row');");
							initialValBuffer.append("show('" + DEPENDANT_FIELD_DATA_ID + "', 1, 'row');");
							initialValBuffer.append("</script>");
							this.initialValidationBf.append(initialValBuffer);
						}
					}
					else
					{
						aBuffer.append("No");
						aBuffer.append("&nbsp;");
						
						String childFieldId = refFormQuestion.getChildFieldId();
						if(!StringUtil.isEmpty(childFieldId))
						{
							isDependantFieldShown = false;
						}
					}
				}
						
			aBuffer.append("</td>");
		}
			
		if(dependantFieldsList.contains(refFormQuestion.getFieldId()) && isDependantFieldShown)
		{
			aBuffer.append(this.initialValidationBf);
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
			return Tag.SKIP_BODY;
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
	
	
	private Collection findAllFieldsInRow(int aRowSequence, int aColumnSequence)
	{
		isQuestionInLastColumn = true;
				
		ArrayList myArrayList=new ArrayList();
		
		if(type.equalsIgnoreCase(UIConstants.INPUT))
		{
			Iterator questIterator = refFormQuestionList.iterator();
			while(questIterator.hasNext())
			{
				ReferralFormField currField = (ReferralFormField)questIterator.next();
				if(currField!=null && currField.getRowSequence()==aRowSequence)
				{
					myArrayList.add(currField);
					
					if(currField.getColumnSequence() > aColumnSequence)
					{
						isQuestionInLastColumn = false;
					}
				}
			}
		}
		else if(type.equalsIgnoreCase(UIConstants.SUMMARY))
		{
			Iterator questIterator = refFormQuestionList.iterator();
			while(questIterator.hasNext())
			{
				ReferralFormField currField = (ReferralFormField)questIterator.next();
				if(currField!=null && currField.getSummaryRowSeq()==aRowSequence)
				{
					myArrayList.add(currField);
					
					if(currField.getSummaryColSeq() > aColumnSequence)
					{
						isQuestionInLastColumn = false;
					}
				}
			}
		}
		return myArrayList;
	}
	
	
	
	private int[] getQuestionAnswerColumnSpan(ReferralFormField refFormQuestion)
	{
		int questionColumnSpan = 1;
		int answerColumnSpan = 1;
		int[] questionAnswerCoumnArr = new int[2];
		questionAnswerCoumnArr[QUESTION_INDEX] = questionColumnSpan;
		questionAnswerCoumnArr[ANSWER_INDEX] = answerColumnSpan;
		
		if(type.equalsIgnoreCase(UIConstants.INPUT))
		{
			if(refFormQuestion.getColumnSequence()>0)
			{
				Collection questionsOnSameRowList =findAllFieldsInRow(refFormQuestion.getRowSequence(), refFormQuestion.getColumnSequence());
				int numberOfQuestionsOnSameRow = questionsOnSameRowList.size();
				
	//			if all the columns are occupied by the questions
				if((numberOfQuestionsOnSameRow*2) == columns)
				{
					return questionAnswerCoumnArr;
				}
	//			if number of QuestionsOnSameRow < columns
				else
				if((numberOfQuestionsOnSameRow*2) < columns)
				{	
					if (refFormQuestion.isResponseNextLine())
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
				if(refFormQuestion.getSummaryColSeq()>0)
				{
					Collection questionsOnSameRowList =findAllFieldsInRow(refFormQuestion.getSummaryRowSeq(), refFormQuestion.getSummaryColSeq());
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
						if (refFormQuestion.isResponseNextLine())
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
	
	
	
	/*private void renderCheckBoxInARow(ReferralFormField refFormQuestion, StringBuffer aBuffer, int aCounter, int aQuestionNumber) throws IOException, JspException
	{
		int[] questionAnswerColumnSpan = new int[2];
		if(refFormQuestion.isResponseNextLine())
		{
			questionAnswerColumnSpan[QUESTION_INDEX]=columns;
			questionAnswerColumnSpan[ANSWER_INDEX]=columns;
		}
		else
		{
			questionAnswerColumnSpan = getQuestionAnswerColumnSpan(refFormQuestion);
		}
		
		if((refFormQuestion.getQuestionColumnWidth()!=null) && (!(refFormQuestion.getQuestionColumnWidth().equalsIgnoreCase(""))))
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap" + " width=\"" + refFormQuestion.getQuestionColumnWidth()+ "%\">");
		}
		else
		{
			aBuffer.append("<td class=formDeLabel colspan=" + questionAnswerColumnSpan[QUESTION_INDEX] + " nowrap>");
		}
		if(refFormQuestion.isRequiredImageShown())
		{
			aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
		}
		aBuffer.append(refFormQuestion.getFieldLabel());
		aBuffer.append("</td>");
		
		if(refFormQuestion.isResponseNextLine())
			aBuffer.append("</tr><tr>");
		
		aBuffer.append("<td class=formDe colspan=" + questionAnswerColumnSpan[ANSWER_INDEX] + ">");
		
		boolean answerMatches=refFormQuestion.answerMatchesChoice();
				
		if(refFormQuestion.getResponseChoices()!=null && refFormQuestion.getResponseChoices().size()>0)
		{
			Iterator it = refFormQuestion.getResponseChoices().iterator();			
			
			int index = 0;
			String[] originalSelectedRespIdsArr = refFormQuestion.getSelectedResponseIdsArr();
			refFormQuestion.setSelectedResponseIdsArr(null);
			
			while(it.hasNext())
			{
				RefFormFieldOption possibleResponse = (RefFormFieldOption)it.next();
				
				aBuffer.append("<input type=\"checkbox\" name=\"");
				aBuffer.append(property +  "[" + aCounter + "].selectedResponseIdsArr" + "\"" + " value=\"" + possibleResponse.getOptionId() + "\"");
				
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
						if((originalSelectedRespIdsArr[x] != null) && (possibleResponse.getOptionId().equalsIgnoreCase(originalSelectedRespIdsArr[x])))
						{
							aBuffer.append(" checked");
						}
					}
				}
				aBuffer.append(">");
				aBuffer.append(possibleResponse.getOptionDisplayText() + " ");
				index++;
				
			}
		}
		aBuffer.append("</td>");
	}*/
	
	
	
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
	 * @return the refFormQuestionList
	 */
	public Collection getRefFormQuestionList() {
		return refFormQuestionList;
	}

	/**
	 * @param refFormQuestionList the refFormQuestionList to set
	 */
	public void setRefFormQuestionList(Collection refFormQuestionList) {
		this.refFormQuestionList = refFormQuestionList;
	}

	/**
	 * @return the calendarGif
	 */
	public String getCalendarGif() {
		return calendarGif;
	}

	/**
	 * @param calendarGif the calendarGif to set
	 */
	public void setCalendarGif(String calendarGif) {
		this.calendarGif = calendarGif;
	}
}
