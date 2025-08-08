/*
 * Created on May 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.taglib;

import javax.servlet.jsp.tagext.TagSupport;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.Rule;
import ui.supervision.CommonUtilites;
import messaging.codetable.reply.ICode;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RuleLiteralTag extends TagSupport
{
	
	private String name;
	private String property;
	private Object beanObj;
	
	private int levelDeep=2;  // Default must remain 2 to support compatibility with existing pages

	/**
	 * 
	 */
	public RuleLiteralTag()
	{
		super();
	}


	public int doStartTag() throws JspException
	{
		try
		{
			Rule rule = (Rule) this.getReadObject();

			this.generateRuleHTML(rule);
		}
		catch (Exception e)
		{
			// TODO Needs proper exception handling
			//throw new JspException(e.getMessage());
		}
		return Tag.SKIP_BODY;
	}
	
	
	private void generateRuleHTML(Rule rule)
	{
		if ( rule != null )
		{
			String ruleLiteral = rule.getRuleLiteral();
			if ( ruleLiteral != null && ruleLiteral.length() > 0 )
			{
				try
				{
					// get all htmlElements associated with this condition
					Map varElementsIdHtmlMap = getRuleHtmlElements(rule);
					if ( varElementsIdHtmlMap.isEmpty() )
					{
						renderOption( rule.getRuleLiteral() );
					}
					else
					{
						renderOption( renderRuleLiteral(rule, varElementsIdHtmlMap) );
					}
				}
				catch ( IOException e )
				{
					System.out.println(" can't render " + ruleLiteral);
				}
			}
		}
	}
		
	/**
	 * @param collection
	 * @return
	 */
	private Map getRuleHtmlElements(Rule rule) throws IOException
	{
		Collection collection = rule.getVariables();
		Map varElements = new HashMap();
		String elementTypeCategory = null;
		String elementTypeDetailGroup = null;
		String fieldName="";
		if (collection != null)
		{
			Iterator elementIterator = collection.iterator();
			while (elementIterator.hasNext())
			{
				VariableElementResponseEvent element = (VariableElementResponseEvent) elementIterator.next();
				StringBuffer elementText = new StringBuffer();
				String id = rule.getConditionId() + "_" + element.getName() + "_" + "ID";
				fieldName="value(" +id+")";
				elementText.append("<b>");
				if(element.isReference())
					elementText.append("[ ");
				else
					elementText.append("<< ");
				elementText.append(element.getName());
				if(element.isReference())
					elementText.append(" ]");
				else
					elementText.append(" >>");
				elementText.append("</b>");
				String value = element.getValue();
				if(value == null){
					value = "";
				}
				if (element.isFixed() || element.isReference())
				{
//					elementText.append(" ");
//					elementText.append(value);
//					elementText.append(" ");
					
					String actualValue = null;
					if (element.isEnumeration()) {
						value = element.getValueId();
						actualValue = element.getValue();
					} else {
						//					elementText.append(value);
						//					elementText.append(" ");
						elementTypeCategory = element.getEnumerationTypeId();
						elementTypeDetailGroup = element.getValueType();
						//String
						// actualValue=CommonUtilites.getVariableActualText(elementTypeCategory,elementTypeDetailGroup,
						// value);
						value=CommonUtilites.getDefaultUnderlineSpaces(value,10);
						actualValue = CommonUtilites
								.getVariableFormattedText(elementTypeCategory,
										elementTypeDetailGroup, value,false);
					}
					elementText.append(actualValue);
					
				}
				else
				{
					boolean isEnum = element.isEnumeration();
					
					if (isEnum) {
						elementText.append(this.renderEnumElmentValues(element,
								fieldName));
					} else {
						elementTypeCategory = element.getEnumerationTypeId();
						elementTypeDetailGroup = element.getValueType();
						
						String preValue = CommonUtilites.getVariablePreText(
								elementTypeCategory, elementTypeDetailGroup,
								value,false);
						String actualValue = CommonUtilites
								.getVariableActualText(elementTypeCategory,
										elementTypeDetailGroup, value,true);
						String postValue = CommonUtilites.getVariablePostText(
								elementTypeCategory, elementTypeDetailGroup,
								value,false);
						String preJSValue = CommonUtilites
								.getJSVariablePreText(elementTypeCategory,
										elementTypeDetailGroup, actualValue,
										element.getName(), fieldName,
										"id" + id, levelDeep);
						String postJSValue = CommonUtilites
								.getJSVariablePostText(elementTypeCategory,
										elementTypeDetailGroup, actualValue,
										element.getName(), fieldName,
										"id" + id,levelDeep);
						String reqValue= CommonUtilites.getJSVariableRequiredText(elementTypeCategory,
								elementTypeDetailGroup, actualValue,
								element.getName(), fieldName,
								"id" + id);

						// PRE EVALUATION
						elementText.append(preJSValue);
						elementText.append(preValue);
						elementText
								.append("<input type=\"text\" name=\"");
						elementText.append(fieldName);
						elementText.append("\" id=\"");
						elementText.append(id);
						elementText.append("\" value=\"");
						//elementText.append("\" maxlenght=\"25\" size=\"25\"
						// value=\"");
						elementText.append(actualValue);
						elementText.append("\">");
						elementText.append(postValue);
						elementText.append(postJSValue);
						elementText.append(reqValue);
						// POST EVALUATION
					
					
					
////					if (isEnum)
////					{
////						elementText.append(this.renderEnumElmentValues(element, id));
////					}
//					else
//					{
//						elementText.append("<input type=\"text\" name=\"value(");
//						elementText.append(id);
//						elementText.append(")\" value=\"");
//						//elementText.append("\" maxlenght=\"25\" size=\"25\" value=\"");
//						elementText.append(value);
//						elementText.append("\">");

					}
				}
				varElements.put(element.getName(), elementText.toString());
			}
		}
		return varElements;
	}

	/**
	 * @param string
	 * @param collection
	 * @param string2
	 */
	private String renderEnumElmentValues(VariableElementResponseEvent element, String id) throws IOException
	{
		StringBuffer ret = new StringBuffer();
		// get enumerated values
		element.setCodeValues(CodeHelper.getCodes(element.getCodeTableName(),true));
		Collection possibleValues = element.getCodeValues();
		if (possibleValues != null && possibleValues.size() > 0)
		{
			Iterator iter = possibleValues.iterator();
			if (element.getEnumerationTypeId() != null && element.getEnumerationTypeId().equalsIgnoreCase("R"))
			{
				ret.append("<BR>");
				ret.append("<table width=\"60%\" border=\"0\" cellspacing=\"1\" valign=\"middle\" align=\"center\">");
				boolean selected;
				String selectedString = "";
				while (iter.hasNext())
				{
					ICode code = (ICode) iter.next();
					selected = code.getCode().equalsIgnoreCase(element.getValueId());
					if (selected)
					{
						selectedString = " CHECKED ";
						selected = false;
					}

					ret.append("<tr valign=\"middle\" align=\"left\">");
					ret.append("<td><input type=radio  name=");
					ret.append(id);
					ret.append(
						" value=\"" + code.getCode() + "\"" + selectedString + ">" + code.getDescription() + "</td>");
					ret.append("</tr>");
				}
				ret.append("</table>");
			}
			else
			{
				ret.append("<select name=\"");
				ret.append(id);
				ret.append("\" >");
				boolean selected;
				while (iter.hasNext())
				{
					ICode code = (ICode) iter.next();
					selected = code.getCode().equalsIgnoreCase(element.getValueId());
					ret.append(this.renderListOption(code.getCode(), code.getDescription(), selected));
				}
				ret.append(" </select> ");
			}

		}

		return ret.toString();
	}


	private String renderListOption(String id, String description, boolean selected) throws IOException
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<OPTION ");

		if (selected == true)
		{
			buffer.append("SELECTED");
		}

		buffer.append(" value=\"");
		buffer.append(id);
		buffer.append("\">");
		buffer.append(description);
		buffer.append("</OPTION>");
		return buffer.toString();
	}


	private void renderOption(String htmlText)
	{
		try
		{
			pageContext.getOut().print(htmlText);
		}
		catch (IOException e)
		{

		}
	}


	private Object getReadObject() throws Exception
	{
		String propertyName = getProperty();
		
		if ( property != null && ! property.equals("") )
		{
			StringBuffer buffer = new StringBuffer(propertyName);
			String firstChar = String.valueOf(buffer.charAt(0)).toUpperCase();
			buffer.replace(0, 1, firstChar);
			buffer.insert(0, "get");
			String getterName = buffer.toString();
			Method method = beanObj.getClass().getMethod(getterName, (Class[]) null);
			if (method == null)
			{
				return null;
			}
			return method.invoke(beanObj, (Object[]) null);
		}
		else
		{
			return beanObj;
		}
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
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;

		beanObj = pageContext.getAttribute(this.name);
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
	}
		
	/**
	 * @param conditionLiteral
	 * @param varElements
	 */
	private String renderRuleLiteral(Rule rule, Map varElementsIdHtmlMap)
	{
		Collection varElements = rule.getVariables();
		String ruleLiteral = rule.getRuleLiteral();
		VariableElementResponseEvent varElement = null;
		String varElementName = null;
		String selectedValue = null;
		String token = null;

		if (varElements != null)
		{
			Iterator iter = varElements.iterator();
			if (iter != null && iter.hasNext())
			{
				while (iter.hasNext())
				{
					varElement = (VariableElementResponseEvent) iter.next();
					varElementName = varElement.getName();
					selectedValue = "";
					token = null;

					if (varElement.isReference())
					{
						token = "[" + varElementName + "]";
						selectedValue = (String) varElement.getValue();
					}
					else
					{
						token = "{{" + varElementName + "}}";
						selectedValue = (String) varElementsIdHtmlMap.get(varElementName);
					}
					token=UIUtil.regExSpecCharEscapeFix(token);
					try
					{
						RE regex = new RE(token, RE.REG_ICASE);
						if ( selectedValue != null )
						{
							// The dollar sign denotes an anchor in RegEx.
							selectedValue = selectedValue.replace('$', '^');
							ruleLiteral = regex.substituteAll(ruleLiteral, selectedValue);
							ruleLiteral = ruleLiteral.replace('^', '$');
						}
						else
						{
							// ruleLiteral = regex.substituteAll(ruleLiteral, selectedValue);
						}
					}
					catch (REException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return ruleLiteral;

	}
		
	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		property = string;
	}

}
