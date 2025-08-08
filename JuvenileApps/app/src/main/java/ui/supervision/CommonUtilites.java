/*
 * Created on Aug 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision;

import java.util.Date;

import ui.common.UIUtil;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonUtilites {

	
	public static String VARIABLE_TEXT_PRE="PRE";
	public static String VARIABLE_TEXT_ACTUAL="ACTUAL";
	public static String VARIABLE_TEXT_POST="POST";
	public static final String currencyUSSymbol="$";
	public static final String emptyString="";
	
	public static String getJSVariableRequiredText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,  String aFieldName, String aInputBoxId, String uniqueNameRef){
		StringBuffer strOutputBuffer=new StringBuffer();
		strOutputBuffer.append("<script>");
		strOutputBuffer.append("customValRequired('" + aInputBoxId + "','" + aFieldName + " is required')");
		strOutputBuffer.append("</script>");
		return strOutputBuffer.toString();
	}
	
	
	/**
	 * 
	 * @return
	 */
	private static String getJSVariableText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aTextLocation, String aFieldName, String aInputBoxId, String uniqueNameRef, int levelDeep){
		if(aElemTypeCategory==null || aElemTypeCategory.equals("") || aElemTypeDetailGroup==null || aElemTypeDetailGroup.equals("")){
			if(aTextLocation.equals(VARIABLE_TEXT_PRE) || aTextLocation.equals(VARIABLE_TEXT_POST))
				return "";
			else
				return aValueText;
		}
		String strOutputText="";
		StringBuffer strOutputBuffer=new StringBuffer();
		if(aTextLocation.equals(VARIABLE_TEXT_PRE)){
			if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHA)){
				strOutputBuffer.append("<script>");
					strOutputBuffer.append("addAlphaValidation('" + aInputBoxId + "','" + aFieldName + " must be alphabetic')");
				strOutputBuffer.append("</script>");
			}
			else if(aElemTypeDetailGroup.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC)){
				strOutputBuffer.append("<script>");
				if(aElemTypeCategory.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS))
					strOutputBuffer.append("addAlphaNumericnSpaceValidation('" + aInputBoxId + "','" + aFieldName + " must be alphanumeric.')");
				else
					strOutputBuffer.append("addAlphaNumericnSpacewSymbolsValidation('" + aInputBoxId + "','" + aFieldName + " must be alphanumeric.')");
				strOutputBuffer.append("</script>");
				
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY)){
				strOutputBuffer.append("<script>");
					strOutputBuffer.append("addCurrencyValidation('" + aInputBoxId + "','" + aFieldName + " is not a valid currency. Please note no commas or dollar signs are allowed.  Example: for $1,000 enter 1000.00 .')");
				strOutputBuffer.append("</script>");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){
				strOutputBuffer.append("<script>");
					strOutputBuffer.append("addMMDDYYYYDateValidation('" + aInputBoxId + "','" + aFieldName + " is invalid.  Valid format is mm/dd/yyyy.')");
					strOutputBuffer.append("</script>");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_ENUMERATED_LIST)){
				
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_FREETEXT)){
				strOutputBuffer.append("<script>");
					strOutputBuffer.append("addDB2FreeTextValidation('" + aInputBoxId + "','" + aFieldName + " must be alphanumeric with no leading spaces and the following symbols apostrophe, back slash, forward slash, .  & ; ,  ( ) along with spaces are allowed.')");
				strOutputBuffer.append("</script>");
			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC)){
				strOutputBuffer.append("<script>");
					strOutputBuffer.append("addNumericValidation('" + aInputBoxId + "','" + aFieldName + " must be numeric.')");
				strOutputBuffer.append("</script>");
			}
//			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_PHONE)){
//				strOutputBuffer.append("<script>");
//					strOutputBuffer.append("addCurrencyValidation('" + aInputBoxId + "','<bean:message key=\"errors.currency\" arg0=\"" + aFieldName + "\"/>')");
//				strOutputBuffer.append("</script>");
//			}
			else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_TIME)){
				strOutputBuffer.append("<script>");
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_12HOUR))
						strOutputBuffer.append("add12HrTimeValidation('" + aInputBoxId + "','" + aFieldName + " is an invalid 12 hour time format.  Valid format is XX:XX ex: 12:34')");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_24HOUR))
						strOutputBuffer.append("add24HrTimeValidation('" + aInputBoxId + "','" + aFieldName + " is an invalid 24 hour time format.  Valid format is XX:XX ex: 14:22')");
				strOutputBuffer.append("</script>");
			}
			return strOutputBuffer.toString();
		}
		else if(aTextLocation.equals(VARIABLE_TEXT_POST)){
			if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){
				
				strOutputBuffer.append("<A HREF=\"#\" onClick=\"cusVarCal.select(document.getElementsByName('" + aInputBoxId + "')[0],'an" + uniqueNameRef + "','MM/dd/yyyy'); ");
				strOutputBuffer.append("return false;\" NAME=\"an"+ uniqueNameRef + "\" ID=\"an"+ uniqueNameRef + "\" border=\"0\">");
				switch(levelDeep){
					case 2: strOutputBuffer.append("<img src=\"../../images/calendar2.gif\" alt=\"calendarImage\" title=\"(mm/dd/yyyy)\" border=0> </A>");
								break;
					case 3: strOutputBuffer.append("<img src=\"../../../images/calendar2.gif\" alt=\"calendarImage\" title=\"(mm/dd/yyyy)\" border=0> </A>");
					break;
					default: strOutputBuffer.append("<img src=\"../../images/calendar2.gif\" alt=\"calendarImage\" title=\"(mm/dd/yyyy)\" border=0> </A>");
					break;
				
				}
			}
			return strOutputBuffer.toString();
		}
		else{
			return aValueText;
		}
	}
	
	
	public static String getJSVariablePreText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aFieldName, String aInputBoxId, String uniqueNameRef, int levelDeep){
		return getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_PRE,aFieldName,aInputBoxId,uniqueNameRef, levelDeep);
	}
	
	public static String getJSVariablePostText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,String aFieldName, String aInputBoxId, String uniqueNameRef, int levelDeep){
		return getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_POST,aFieldName,aInputBoxId,uniqueNameRef, levelDeep);
	}
	
	public  static String getDefaultUnderlineSpaces(String aValueText, int aNumOfSpaces){
		if (aValueText==null || aValueText.trim().equals("")){
			StringBuffer strBuf=new StringBuffer();
			int x=0;
			while(x<aNumOfSpaces && aNumOfSpaces >0){
				strBuf.append("_");
				x++;
			}
			return strBuf.toString();
		}
		else
			return aValueText;
	}
	
	/**
	 * The variables text with no formatting symbols
	 * @param aElemTypeCategory
	 * @param aElemTypeDetailGroup
	 * @param aValueText
	 * @return
	 */
	public static String getJSVariableActualText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aFieldName, String aInputBoxId, String uniqueNameRef, int levelDeep){
		return getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_ACTUAL,aFieldName,aInputBoxId,uniqueNameRef,levelDeep);
	}
	
	public static String getJSVariableFormattedText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aFieldName, String aInputBoxId, String uniqueNameRef, int levelDeep){
		return (getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_PRE,aFieldName,aInputBoxId,uniqueNameRef, levelDeep)+ 
				getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_ACTUAL,aFieldName,aInputBoxId,uniqueNameRef,levelDeep) + 
				getJSVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_POST,aFieldName,aInputBoxId,uniqueNameRef,levelDeep));
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	private static String getVariableText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText, String aTextLocation, boolean aIsForEditing){
		if(aIsForEditing) { // if the variable is for editing we don't want to do any formatting to it.
			if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){
				// FIX the case where the month may be off by one.
				if(aValueText!=null){
					int myIndex=aValueText.indexOf('/');
					if(myIndex==1){
						aValueText="0" + aValueText;
					}
					
				}
			}
			return aValueText;
		}
		
		if(aElemTypeCategory==null || aElemTypeCategory.equals("") || aElemTypeDetailGroup==null || aElemTypeDetailGroup.equals("")){
			if(aTextLocation.equals(VARIABLE_TEXT_PRE) || aTextLocation.equals(VARIABLE_TEXT_POST))
				return "";
			else
				return aValueText; 
		}
		try{
			if(aTextLocation.equals(VARIABLE_TEXT_PRE)){
				if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY)){
					return CommonUtilites.currencyUSSymbol;
				}
				else{
					return emptyString;
				}
			}
			else if(aTextLocation.equals(VARIABLE_TEXT_POST)){
				return emptyString;
			}
			else{
				if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY)){
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_DEFAULT))
						return UIUtil.formatCurrency(aValueText,UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_COMMAS))
						return UIUtil.formatCurrency(aValueText,UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_CURRENCY_NOCOMMAS))
						return UIUtil.formatCurrency(aValueText,UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT,false,"");
				}
				else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){ 
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DAY_N_SUFFIX))
						return UIUtil.getDayOfMonthWithSuffix(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DAY_ONLY)){
						return UIUtil.getDayOfMonth(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));}
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_DEFAULT)){
							Date myDate=DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1);
						if(myDate!=null)
							return DateUtil.dateToString(myDate,UIConstants.DATE_FMT_1);
						else
							return aValueText;
					}
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_FORMAL))
						return UIUtil.getFormalDateWithSuffix(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_FULLMONTHNAME))
						return UIUtil.getMonthLiteral(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_REVERSE_FORMAL))
						return UIUtil.getFormalReversedDate(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_DATE_YEAR_ONLY))
						return UIUtil.getYear(DateUtil.stringToDate(aValueText,UIConstants.DATE_FMT_1));
				}
				else if(aElemTypeCategory.equals(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC)){
					if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_COMMAS))
						return UIUtil.formatNumber(aValueText,UIConstants.NUMBER_DEFAULT_POSITIVE_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_DECIMAL_DEFAULT))
						return UIUtil.formatNumber(aValueText,UIConstants.DECIMAL_DEFAULT_DECIMAL_FORMAT,false,"");
					else if(aElemTypeDetailGroup.equals(UIConstants.DISPLAY_PRESENTATION_TYPE_NUMERIC_NOCOMMAS))
						return UIUtil.formatNumber(aValueText,UIConstants.NUMBER_NOCOMMAS_POSITIVE_FORMAT,false,"");
				}
				return aValueText;
			}
		}
		catch(Exception e){
			if(aTextLocation.equals(VARIABLE_TEXT_PRE) || aTextLocation.equals(VARIABLE_TEXT_POST))
				return "";
			else
				return aValueText;
				//return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
	}
	
	
	public static String getVariablePreText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,boolean aIsForEditing){
		return getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_PRE,aIsForEditing);
	}
	
	public static String getVariablePostText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,boolean aIsForEditing){
		return getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_POST,aIsForEditing);
	}
	
	/**
	 * The variables text with no formatting symbols
	 * @param aElemTypeCategory
	 * @param aElemTypeDetailGroup
	 * @param aValueText
	 * @return
	 */
	public static String getVariableActualText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,boolean aIsForEditing){
		return getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_ACTUAL,aIsForEditing);
	}
	
	public static String getVariableFormattedText(String aElemTypeCategory,String aElemTypeDetailGroup, String aValueText,boolean aIsForEditing){
		return (getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_PRE,false)+ 
				getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_ACTUAL,aIsForEditing) + 
				getVariableText(aElemTypeCategory,aElemTypeDetailGroup, aValueText, VARIABLE_TEXT_POST,false));
	}
	
	
}// END CLASS
