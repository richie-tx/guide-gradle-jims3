/*
 * Created on Mar 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.taglib;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import naming.UIConstants;
import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCQuestionValidationTag extends TagSupport
{
	private String name;
	private String property;
	
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
			throw new JspException(e.getMessage());
		}
		return Tag.SKIP_BODY;
	}
	
	
	
	private void renderBody(Collection aQuestionGroup, StringBuffer buffer) throws Exception
	{
		if(aQuestionGroup==null || aQuestionGroup.size()<=0)
			return;
		
		int groupCounter = 0;
		Iterator iGroup=aQuestionGroup.iterator();
		
		while(iGroup.hasNext())
		{
			CSCQuestionGroup currentQuestionGroup=(CSCQuestionGroup)iGroup.next();
			
			Collection aQuestions=currentQuestionGroup.getQuestions();
			int questCounter = 0;
			
			if (aQuestions != null && (aQuestions.size() > 0))
			{
				Iterator i=aQuestions.iterator();
				
				while(i.hasNext())
				{
					CSCQuestion question = (CSCQuestion)i.next();
					String aFieldName = null;
					String aInputBoxId = null;
					if(currentQuestionGroup.isGroupTextDetailHeader())
					{
						aFieldName = currentQuestionGroup.getGroupText() + " (" + question.getQuestionText() + ")";
					}
					else
					{
						aFieldName = question.getQuestionText();
					}
					
					String uiControlType = question.getUiControlType();
					if((uiControlType.equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_RADIO)) ||
					(uiControlType.equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SELECT)) ||
					(uiControlType.equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX)))
					{
						 aInputBoxId = property + "[" + groupCounter + "].questions[" + questCounter + "].responseId" ;
					}
					else
					{
						aInputBoxId = property + "[" + groupCounter + "].questions[" + questCounter + "].responseText" ;
					}
					
					String resultString = getJSVariableValidationFunctions(question, aFieldName, aInputBoxId);
					buffer.append(resultString);
					questCounter++;
				}
			}
			groupCounter++;
		}
	}
	
	
	

	/**
	 * 
	 * @return
	 */
	private String getJSVariableValidationFunctions(CSCQuestion question, String aFieldName, String aInputBoxId)
	{
		String aElemTypeCategory = question.getValidationElementType();
		String aElemTypeDetailGroup = question.getValidationElementDetailType();
		
		if(aElemTypeCategory==null || aElemTypeCategory.equals(""))
		{
			return "";
		}
		
		StringBuffer strOutputBuffer=new StringBuffer();
		
		if(question.isRequired())
		{
			strOutputBuffer.append("customValRequired('" + aInputBoxId + "','" + aFieldName + " is required.');");
		}
		
			if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC))
			{
				int maxTextLength = question.getTextLength();
				
				if(maxTextLength>0)
				{
					strOutputBuffer.append("customValMaxLength('" + aInputBoxId + "','" + aFieldName + " cannot be greater than " + maxTextLength + " characters.','" + maxTextLength + "');");
				}
				if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS))
				{
					strOutputBuffer.append("addAlphaNumericnSpaceValidation('" + aInputBoxId + "','" + aFieldName + " must be alphanumeric.');");
				}
				else
				{
					strOutputBuffer.append("addDefinedDB2Mask(\"" + aInputBoxId + "\",\"" + aFieldName + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).\");");
				}
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_INTEGER))
			{
				
				strOutputBuffer.append("customValInteger('" + aInputBoxId + "','" + aFieldName + " must be an integer.','');");
				
//				validate for range if mentioned
				if(question.isRangeValidationExists())
				{
					int minValue = question.getMinValue();
					int maxValue = question.getMaxValue();
					
					strOutputBuffer.append("customValIntegerRange('" + aInputBoxId + "','" + aFieldName + " is not in the range " + minValue + " through " + maxValue + ".','" + minValue + "','" + maxValue + "');");
				}
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC))
			{
				strOutputBuffer.append("addNumericValidation('" + aInputBoxId + "','" + aFieldName + " must be numeric.');");
				
//				validate for range if mentioned
				if(question.isRangeValidationExists())
				{
					int minValue = question.getMinValue();
					int maxValue = question.getMaxValue();
					
					strOutputBuffer.append("customValIntegerRange('" + aInputBoxId + "','" + aFieldName + " is not in the range " + minValue + " through " + maxValue + ".','" + minValue + "','" + maxValue + "');");
				}
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHA))
			{
				strOutputBuffer.append("addAlphaValidation('" + aInputBoxId + "','" + aFieldName + " must be alphabetic');");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE))
			{
				strOutputBuffer.append("addMMDDYYYYDateValidation('" + aInputBoxId + "','" + aFieldName + " is invalid.  Valid format is mm/dd/yyyy.');");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY))
			{
				strOutputBuffer.append("addCurrencyValidation('" + aInputBoxId + "','" + aFieldName + " is not a valid currency entry. No commas or dollar signs are allowed.');");
			}
			
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ENUMERATED_LIST))
			{
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_FREETEXT))
			{
				strOutputBuffer.append("addDB2FreeTextValidation('" + aInputBoxId + "','" + aFieldName + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).');");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_TIME))
			{
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_12HOUR))
						strOutputBuffer.append("add12HrTimeValidation('" + aInputBoxId + "','" + aFieldName + " is an invalid 12 hour time format.  Valid format is XX:XX ex: 12:34');");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_24HOUR))
						strOutputBuffer.append("add24HrTimeValidation('" + aInputBoxId + "','" + aFieldName + " is an invalid 24 hour time format.  Valid format is XX:XX ex: 14:22');");
			}
			return strOutputBuffer.toString();
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the property.
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property The property to set.
	 */
	public void setProperty(String property) {
		this.property = property;
	}
}
