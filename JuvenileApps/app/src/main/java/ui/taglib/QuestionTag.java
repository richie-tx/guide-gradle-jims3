/*
 * Created on Dec 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import ui.common.CodeHelper;
import ui.common.Question;
import ui.common.QuestionGroup;
import ui.common.QuestionResponse;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuestionTag extends TagSupport
{
	
	private String name;
	private String property;
	private String requiredGif;
	private String type; // Valid values are input & summary
	private int columns=2;
	private int maxColumnsFound=columns;
	
	private Collection tempQuestions=null;
	
	
	private void renderBody(Collection aQuestionGroup, StringBuffer buffer) throws Exception
		{
			
			if(aQuestionGroup==null || aQuestionGroup.size()<=0)
				return;
			maxColumnsFound=UIUtil.maxColumnSizeForQuestions(aQuestionGroup);
			if((maxColumnsFound *2)>columns)
				columns=maxColumnsFound *2;	
			Iterator iGroup=aQuestionGroup.iterator();
			int groupCounter=0;
			int questionCounter=1;
			while(iGroup.hasNext()){
				QuestionGroup currentQuestionGroup=(QuestionGroup)iGroup.next();
				Collection aQuestions=currentQuestionGroup.getQuestions();
				if (aQuestions != null)
				{
					Question currentQuestion=null;
					//Output results
					Iterator i=aQuestions.iterator();
					int counter=0;
					int curRowSequence=-99;
					boolean startNewRow=true;
					boolean rowAlreadyOpen=false;
					while(i.hasNext()){
						startNewRow=true;
						currentQuestion=(Question)i.next();
						if(curRowSequence == currentQuestion.getRowSequence())
							startNewRow=false;
						else
							curRowSequence=currentQuestion.getRowSequence();
						if(startNewRow){
							if(rowAlreadyOpen){
								buffer.append("</tr>");
								
							}
							buffer.append("<tr>"  );
							rowAlreadyOpen=true;
						}
						if(type.equalsIgnoreCase("input")){
							renderInput(currentQuestion,buffer,counter,groupCounter,questionCounter);
						}
						else if(type.equalsIgnoreCase("summary")){
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

		private void renderInput(Question aQuestion, StringBuffer aBuffer,int aCounter,int aGroupCounter, int aQuestionNumber) throws IOException, JspException{
			if(aQuestion.getUiControlType().equalsIgnoreCase(Question.UI_CNTRL_TYPE_RADIO)){
				renderRadio(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(Question.UI_CNTRL_TYPE_TEXTBOX)){
				renderTextBox(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(Question.UI_CNTRL_TYPE_TEXT)){
				renderText(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	
			}
			else if(aQuestion.getUiControlType().equalsIgnoreCase(Question.UI_CNTRL_TYPE_SELECT)){
				renderSelect(aQuestion,aBuffer,aCounter, aGroupCounter, aQuestionNumber);	 
			} 
			
		}// End Render Input

	private Collection findAllQuestionsInRow(int aRowSequence){
		ArrayList myArrayList=new ArrayList();
		if(aRowSequence <=0 || tempQuestions==null || tempQuestions.size()<=1) 
			return myArrayList;
		ArrayList tempArr=(ArrayList)tempQuestions;
		for(int loopX=0; loopX<tempArr.size(); loopX++){
			Question currQ=(Question)tempArr.get(loopX);
			if(currQ!=null && currQ.getRowSequence()==aRowSequence)
			{
				myArrayList.add(currQ);
			}
		}
		return myArrayList;
	}
	
	
	

	private int getColumnSpan(Question aQuestion){
		int tempColumns;
		tempColumns=columns/2;
		if(aQuestion.getColumnSequence()>0){
			Collection questionsOnSameRow=findAllQuestionsInRow(aQuestion.getRowSequence());
			int myNumOfQuestions=questionsOnSameRow.size();
			if(myNumOfQuestions<=1)
			{
				if(aQuestion.isResponseNextLine())
					return tempColumns*2;
				else
					return tempColumns;
			}
			else{
				return (tempColumns/(myNumOfQuestions));
			}
			
		}
		else
		{
			if(aQuestion.isResponseNextLine())
				return tempColumns*2;
			else
				return tempColumns;
		}
	}


	private void renderSelect(Question aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException, JspException
	{
		int columnSpan=getColumnSpan(aQuestion);
		aBuffer.append("<td class=formDeLabel colspan=" + columnSpan + ">");
		if(aQuestion.isRenderQuesNum())
			aBuffer.append("Question " + aQuestionNumber + ". ");
		if(aQuestion.isRequired())
			aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
		aBuffer.append(aQuestion.getQuestionText());
		aBuffer.append("</td>");
		if(aQuestion.isResponseNextLine() && columnSpan==columns)
			aBuffer.append("</tr><tr>");
		aBuffer.append("<td class=formDe colspan=" + columnSpan + ">");
		boolean answerMatches=aQuestion.answerMatchesChoice();

		aBuffer.append("<select name=\"");
		aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].response");
		aBuffer.append("\" >");
		if(aQuestion.getResponseChoices()!=null && aQuestion.getResponseChoices().size()>0){
			Iterator i=aQuestion.getResponseChoices().iterator();
			while(i.hasNext()){
				QuestionResponse myAnswer=(QuestionResponse)i.next();
				if(myAnswer.getCodeTableName()!=null && !(myAnswer.getCodeTableName().trim().equals(""))){
					List codeTableList=CodeHelper.getActiveCodesInM204(myAnswer.getCodeTableName(),true); // SimpleCodeTableHelper.getCodesSortedByDesc(myAnswer.getCodeTableName());bug fix #26692
					if(codeTableList!=null && codeTableList.size()>0){
						Iterator codeItem=codeTableList.iterator();
						while(codeItem.hasNext()){
							Object bean=codeItem.next();
							 Object beanLabel = null;
					            Object beanValue = null;

					            // Get the label for this option
					            try {
					                beanLabel = PropertyUtils.getProperty(bean, "description");
					                if (beanLabel == null) {
					                    beanLabel = "";
					                }
					            } catch (IllegalAccessException e) {
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            } catch (InvocationTargetException e) {
					                Throwable t = e.getTargetException();
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            } catch (NoSuchMethodException e) {
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            }

					            // Get the value for this option
					            try {
					                beanValue = PropertyUtils.getProperty(bean, "code");
					                if (beanValue == null) {
					                    beanValue = "";
					                }
					            } catch (IllegalAccessException e) {
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            } catch (InvocationTargetException e) {
					                Throwable t = e.getTargetException();
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            } catch (NoSuchMethodException e) {
					                JspException jspe =
					                    new JspException("Cannot access the label value");
					                TagUtils.getInstance().saveException(pageContext, jspe);
					                throw jspe;
					            }

					            String stringLabel = beanLabel.toString();
					            String stringValue = beanValue.toString();
					            boolean tripSelected=false;
					            if(answerMatches && aQuestion.getResponse().equalsIgnoreCase(stringValue)){
					            	tripSelected=true;
					            }
							addOption(aBuffer, stringLabel, stringValue, tripSelected, true);
						}
					}
				}
				else{
					aBuffer.append("<option value=\"" + myAnswer.getResponseValue() + "\" ");
					if(!answerMatches){
						if(myAnswer.isDefault())
							aBuffer.append(" selected ");
					}
					else if(answerMatches && aQuestion.getResponse().equalsIgnoreCase(myAnswer.getResponseValue()))
						aBuffer.append(" selected ");
					aBuffer.append(" >");
					aBuffer.append(myAnswer.getResponseDisplayText());
					aBuffer.append("</option>");
				}
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

		private void renderText(Question aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int columnSpan=getColumnSpan(aQuestion);
			aBuffer.append("<td class=formDeLabel colspan=" + columnSpan + ">");
			if(aQuestion.isRenderQuesNum())
				aBuffer.append("Question " + aQuestionNumber + ". ");
			if(aQuestion.isRequired())
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			if(aQuestion.isResponseNextLine() && columnSpan==columns)
						aBuffer.append("</tr><tr>");			aBuffer.append("<td class=formDe colspan=" + columnSpan + ">");
			//boolean answerMatches=aQuestion.answerMatchesChoice();
			int maxlength=aQuestion.getUiControlSize()+1;
			aBuffer.append("<input type=\"text\" size=\"" + aQuestion.getUiControlSize() + "\" maxlength=\"" + maxlength +"\" name=\"");
			aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].response");
//			if(answerMatches){
//				aBuffer.append(" value=\"");
//				aBuffer.append(aQuestion.getResponse());
//				aBuffer.append("\""); 
//			}
//			else{
				if(aQuestion.getResponse()==null || aQuestion.getResponse().trim().equalsIgnoreCase(""))
				{
					if(aQuestion.getResponseChoices()!=null && aQuestion.getResponseChoices().size()>0){
						Iterator i=aQuestion.getResponseChoices().iterator();
						QuestionResponse myAnswer=(QuestionResponse)i.next();
						i=null;
						if(myAnswer.isDefault())
							{
								aBuffer.append("\" value=\"");
								aBuffer.append(myAnswer.getResponseValue());
								aBuffer.append("\""); 	
							}
					}
					else {
						aBuffer.append("\" value=\"");
						aBuffer.append("\""); 
					}
				}
				else {
					aBuffer.append("\" value=\"");
					aBuffer.append(aQuestion.getResponse());
					aBuffer.append("\""); 
				}
//			}
			aBuffer.append("/>");
			if(aQuestion.getFormatKey()!=null && !(aQuestion.getFormatKey().trim().equalsIgnoreCase("")))
				aBuffer.append("(" + aQuestion.getFormatKey() + ")");
			aBuffer.append("</td>");
		}
		
		private void renderSummary(Question aQuestion, StringBuffer aBuffer, int aQuestionNumber){
			int columnSpan=getColumnSpan(aQuestion);
				aBuffer.append("<td class=formDeLabel colspan=" + (columnSpan) + ">");
				if(aQuestion.isRenderQuesNum())
					aBuffer.append("Question " + aQuestionNumber + ". ");
				aBuffer.append(aQuestion.getQuestionText());
				aBuffer.append("</td>");
				if(aQuestion.isResponseNextLine() && columnSpan==columns)
						aBuffer.append("</tr><tr>");
				aBuffer.append("<td class=formDe colspan=" + (columnSpan) + ">");
				
				boolean answerMatches=aQuestion.answerMatchesChoice();
				if(answerMatches){
					Iterator i=aQuestion.getResponseChoices().iterator();
					boolean isFound=false;
					String ansCodeTableName=null;
					String descCodeTableVal=null;
					boolean foundCodeTable=false;
					while(i.hasNext()){
						QuestionResponse myAnswer=(QuestionResponse)i.next();
						if(myAnswer.getCodeTableName()!=null && !(myAnswer.getCodeTableName().trim().equals(""))){
							foundCodeTable=true;
							ansCodeTableName=myAnswer.getCodeTableName();
						}
						if(myAnswer.getResponseValue().equals(aQuestion.getResponse())){
							aBuffer.append(myAnswer.getResponseDisplayText());
							isFound=true;
							i=null;
							break;
						}
					}
					if (!isFound && foundCodeTable && aQuestion.getResponse()!=null && !aQuestion.getResponse().equals("")){
						descCodeTableVal=SimpleCodeTableHelper.getDescrByCode(ansCodeTableName,aQuestion.getResponse());
						aBuffer.append(descCodeTableVal);
					}
					else if(!isFound){
						aBuffer.append(aQuestion.getResponse());
					}
				}
				else
					aBuffer.append(aQuestion.getResponse());
				aBuffer.append("&nbsp;</td>");
		}// End render Summary

		private void renderTextBox(Question aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int columnSpan=getColumnSpan(aQuestion);
			String textAreaPropertyName = property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].response";
			aBuffer.append("<td class=formDeLabel  colspan=" + (columnSpan) + ">");
			if(aQuestion.isRenderQuesNum())
				aBuffer.append("Question " + aQuestionNumber + ". ");
			if(aQuestion.isRequired())
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("<script language=\"JavaScript\">var rsTCIntspellCheckerButtonId"+aCounter+" = new RSStandardInterface(\""+textAreaPropertyName+"\");</script> <img src=\"../../images/spellingButton.png\" style=\"cursor:pointer;\" onClick=\"popUpCheckSpellingspellCheckerButtonId('rsTCIntspellCheckerButtonId"+aCounter+"')\"/>");
			aBuffer.append("</td>");
			if(aQuestion.isResponseNextLine() && columnSpan==columns)
						aBuffer.append("</tr><tr>");
			aBuffer.append("<td class=formDe colspan=" + (columnSpan) + ">");
		
			aBuffer.append("<textarea rows=\""+ aQuestion.getUiControlSize() + "\" style=\"width:100%\" name=\"");
			aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].response");
			if(aQuestion.getTextLength()!=null && !aQuestion.getTextLength().equals(""))
				aBuffer.append("\" onkeyup=\"textCounter(this," + aQuestion.getTextLength()+")");
			aBuffer.append("\">");
			if(aQuestion.getResponse()==null || aQuestion.getResponse().trim().equalsIgnoreCase(""))
			{
				if(aQuestion.getResponseChoices()!=null && aQuestion.getResponseChoices().size()>0){
					Iterator i=aQuestion.getResponseChoices().iterator();
					QuestionResponse myAnswer=(QuestionResponse)i.next();
					i=null;
					if(myAnswer.isDefault())
						{
							aBuffer.append(myAnswer.getResponseValue());
						}
				}
			}
			else {
				aBuffer.append(aQuestion.getResponse());
			}
			
			aBuffer.append("</textarea>");
			aBuffer.append("</td>");
		}
				
		private void renderRadio(Question aQuestion, StringBuffer aBuffer, int aCounter, int aGroupCounter, int aQuestionNumber) throws IOException
		{
			int columnSpan=getColumnSpan(aQuestion);
			aBuffer.append("<td class=formDeLabel colspan=" + columnSpan + ">");
			if(aQuestion.isRenderQuesNum())
				aBuffer.append("Question " + aQuestionNumber + ". ");
			if(aQuestion.isRequired())
				aBuffer.append("<img src=\"" + requiredGif + "\" title=\"Required\" alt=\"required icon\">");
			aBuffer.append(aQuestion.getQuestionText());
			aBuffer.append("</td>");
			if(aQuestion.isResponseNextLine() && columnSpan==columns)
						aBuffer.append("</tr><tr>");
			aBuffer.append("<td class=formDe colspan=" + columnSpan + ">");
			Iterator i=aQuestion.getResponseChoices().iterator();
			boolean answerMatches=aQuestion.answerMatchesChoice();
			while(i.hasNext()){
				QuestionResponse myAnswer=(QuestionResponse)i.next();
				aBuffer.append(myAnswer.getResponseDisplayText());
				aBuffer.append("<input type=\"radio\" name=\"");
				aBuffer.append(property +  "[" + aGroupCounter + "]" + ".questions"  + "[" + aCounter + "].response");
				aBuffer.append("\" value=\"");
				aBuffer.append(myAnswer.getResponseValue());
				aBuffer.append("\""); 
				if(!answerMatches)
					if(myAnswer.isDefault())
						aBuffer.append(" checked ");
				if(answerMatches && aQuestion.getResponse().equalsIgnoreCase(myAnswer.getResponseValue()))
					aBuffer.append(" checked ");
				aBuffer.append("/>&nbsp;");
			}
			aBuffer.append("</td>");
		}
	
	
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
	public String getRequiredGif()
	{
		return requiredGif;
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
	public void setRequiredGif(String string)
	{
		requiredGif = string;
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

}// END CLASS
