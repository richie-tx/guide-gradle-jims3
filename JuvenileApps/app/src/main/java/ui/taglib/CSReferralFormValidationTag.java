package ui.taglib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import naming.UIConstants;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.ReferralFormField;

public class CSReferralFormValidationTag extends TagSupport
{
	private static final String FORM_NAME="theForm";
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
	
	
	
	private void renderBody(Collection refFormFieldsList, StringBuffer buffer) throws Exception
	{
		if(refFormFieldsList==null || refFormFieldsList.size()<=0)
			return;
		
		CSCProgRefUIHelper.sortRefFormFields((ArrayList)refFormFieldsList);
		
		int fieldCounter = 0;
		Iterator fieldIter = refFormFieldsList.iterator();
		
		while(fieldIter.hasNext())
		{
			ReferralFormField currentField=(ReferralFormField)fieldIter.next();
			
			String aFieldName = null;
			String aInputBoxId = null;
			
			aFieldName = currentField.getFieldLabel();
			
			if(currentField.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
			{
				aInputBoxId = property + "[" + fieldCounter + "].responsePhoneNum" ;
			}
			else
			{
				aInputBoxId = property + "[" + fieldCounter + "].responseText" ;
			}

			String resultString = getJSVariableValidationFunctions(currentField, aFieldName, aInputBoxId);
			buffer.append(resultString);
			fieldCounter++;
		}
	}
	
	
	

	/**
	 * 
	 * @return
	 */
	private String getJSVariableValidationFunctions(ReferralFormField referralFormField, String aFieldName, String aInputBoxId)
	{
		String aElemTypeCategory = referralFormField.getValidationElementType();
		String aElemTypeDetailGroup = referralFormField.getValidationElementDetailType();
		
		if(aElemTypeCategory==null || aElemTypeCategory.equals(""))
		{
			return "";
		}
		
		StringBuffer strOutputBuffer=new StringBuffer();
		
		if(referralFormField.isRequired())
		{
			strOutputBuffer.append("customValRequired('" + aInputBoxId + "','" + aFieldName + " is required.');");
		}
		
		if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC))
		{
			int maxTextLength = referralFormField.getTextLength();
			
			if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS))
			{
				if(maxTextLength>0)
				{
					strOutputBuffer.append("customValMaxLength('" + aInputBoxId + "','" + aFieldName + " cannot be greater than " + maxTextLength + " characters.','" + maxTextLength + "');");
				}
				strOutputBuffer.append("addAlphaNumericnSpaceValidation('" + aInputBoxId + "','" + aFieldName + " must be alphanumeric.');");
			}
//			ALPHANUMERIC_WITH_SYMBOLS
			else
			{
				if(referralFormField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX))
				{
					if(maxTextLength>0)
					{
						int validateLength = maxTextLength*3;
						strOutputBuffer.append("customValMaxLength('" + aInputBoxId + "','" + aFieldName + " cannot be greater than " + maxTextLength + " characters.','" + validateLength + "');");
					}
					strOutputBuffer.append("addDefinedTinyMCEFieldMask(\"" + aInputBoxId + "\",\"" + aFieldName + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).\");");
				}
				else
				{
					if(maxTextLength>0)
					{
						strOutputBuffer.append("customValMaxLength('" + aInputBoxId + "','" + aFieldName + " cannot be greater than " + maxTextLength + " characters.','" + maxTextLength + "');");
					}
					strOutputBuffer.append("addDefinedDB2Mask(\"" + aInputBoxId + "\",\"" + aFieldName + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).\");");
				}
			}
		}
		else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_INTEGER))
		{
			
			strOutputBuffer.append("addNumericValidation('" + aInputBoxId + "','" + aFieldName + " must be an integer.','');");
			
//				validate for range if mentioned
			if(referralFormField.isRangeValidationExists())
			{
				int minValue = referralFormField.getMinValue();
				int maxValue = referralFormField.getMaxValue();
				
				strOutputBuffer.append("customValIntegerRange('" + aInputBoxId + "','" + aFieldName + " is not in the range " + minValue + " through " + maxValue + ".','" + minValue + "','" + maxValue + "');");
			}
		}
//		NUMERIC - refers to any numeric number - includes integer, and float numbers
		else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC))
		{
			strOutputBuffer.append("customValFloat('" + aInputBoxId + "','" + aFieldName + " must be numeric.','');");
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
			strOutputBuffer.append("addCurrencyWithNWithoutCommasValidation('" + aInputBoxId + "','" + aFieldName + " is not a valid currency entry. Valid format are like XX,XXX.XX, XXXXX.XX or XXXXX ex: 11,525.00');");
		}
		else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_FREETEXT))
		{
			strOutputBuffer.append("addDB2FreeTextValidation('" + aInputBoxId + "','" + aFieldName + " contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).');");
		}
		else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_TIME))
		{
			strOutputBuffer.append("add24HrTimeValidation('" + aInputBoxId + "','" + aFieldName + " is an invalid 24 hour time format.  Valid format is XX:XX ex: 14:22');");	
		}
		else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_PHONE))
		{
			strOutputBuffer.append("if(!validatePhone('" + aInputBoxId + ".areaCode','" + aInputBoxId + ".prefix','" + aInputBoxId + ".fourDigit','','" + aFieldName + "'," + FORM_NAME + "))");
			strOutputBuffer.append("{return false;}");
			
			strOutputBuffer.append("addNumericValidation('" + aInputBoxId + ".areaCode','" + aFieldName + " Area Code must be an integer.','');");
			strOutputBuffer.append("customValMinLength('" + aInputBoxId + ".areaCode','" + aFieldName + " Area Code can not be less than 3 characters.','3');");
			strOutputBuffer.append("addNumericValidation('" + aInputBoxId + ".prefix','" + aFieldName + " Prefix must be an integer.','');");
			strOutputBuffer.append("customValMinLength('" + aInputBoxId + ".prefix','" + aFieldName + " Prefix can not be less than 3 characters.','3');");
			strOutputBuffer.append("addNumericValidation('" + aInputBoxId + ".fourDigit','" + aFieldName + " Last 4 Digits must be an integer.','');");
			strOutputBuffer.append("customValMinLength('" + aInputBoxId + ".fourDigit','" + aFieldName + " Last 4 Digits can not be less than 4 characters.','4');");
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
