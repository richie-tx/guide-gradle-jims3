package ui.taglib;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.supervision.CommonUtilites;
import messaging.codetable.reply.ICode;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;

/**
 * @author athorat
 *  
 */
public class ConditionTag extends TagSupport
{
    private String formname;

    private String property;

    private Object beanObj;

    private String monitorFreqCodes;

    private int levelDeep = 2; // Default must remain 2 to support compatibility
                               // with existing pages

    /**
     *  
     */
    public ConditionTag()
    {
        super();
    }

    public int doStartTag() throws JspException
    {
        try
        {
            Collection conditions = (Collection) this.getReadObject(this.getProperty());

            this.generateConditionsHTML(conditions);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO Needs proper exception handling
            //throw new JspException(e.getMessage());
        }
        return Tag.SKIP_BODY;
    }

    /**
     * @param beanProperty
     */
    private void generateConditionsHTML(Collection conditions)
    {
        if (conditions != null)
        {
            Iterator conditionIterator = conditions.iterator();
            StringBuffer html = new StringBuffer();
            int count = 1;
            String rowClass = "";
            int counter = 100;
            int condCounter = -1;
            while (conditionIterator.hasNext())
            {
                condCounter++;
                counter++;
                // for each condition
                ConditionDetailResponseEvent condition = (ConditionDetailResponseEvent) conditionIterator.next();
                String conditionLiteral = condition.getDescription();
                if ((conditionLiteral != null && conditionLiteral.length() > 0) || condition.isSpecialCondition())
                {
                    try
                    {
                        html.append("<tr class=");

                        
                        if (count % 2 == 1)
                            rowClass = "normalRow ";
                        else
                            rowClass = "alternateRow ";
                        String tdClass="";
                        if(condition.getStatus()!=null && condition.getStatus().trim().equalsIgnoreCase(PDCodeTableConstants.STATUS_INACTIVE)){
                        	tdClass="class=inactiveCondition title='Inactive Condition'";
                        }
                        else if(!condition.isSpecialCondition() && condition.isNonCourtApplicable()){
                        	tdClass="class=wrongCourtCondition title='Condition does not apply to court(s)'";
                        }
                        html.append(rowClass);
                        html.append(">");
                        html.append("<td width=1%");

                        // like condition
                        if (condition.getLikeConditionInd())
                        {
                            html.append(" class=impactedOrder title='This is a Like Condition and Impacts other order(s)'");
                        }
                       
                        html.append(" nowrap>");
                        html.append("<b>");
                        html.append(String.valueOf(condition.getSequenceNum()));
                        html.append("</b>");
                        html.append("</td>");

                        // for PASO
                        html.append("<td ");
                        html.append(tdClass);
                        html.append(">");
                        
                        // special conditions will never have variable elements to show in PASO
                        if (condition.isSpecialCondition())
                        {
                            html.append(condition.getResolvedDescription());
                        }
                        else
                        {
                            // get all htmlElements associated with this
                            // condition
                            Map varElementsIdHtmlMap = this.getHtmlElements(condition, counter, condCounter);
                            String conditionText = null;
                            if (!varElementsIdHtmlMap.isEmpty())
                            {
                                conditionText = this.renderConditionLiteral(condition, varElementsIdHtmlMap);
                                //
                            }
                            else
                            {
                                conditionText = condition.getDescription();
                            }
                            html.append(conditionText);
                        }
                        html.append("</td></tr>");

                        count++;
                    }
                    catch (IOException e)
                    {
                        System.out.println(" can't render " + conditionLiteral);
                    }

                }
            }
            renderOption(html.toString());
        }
    }

    /**
     * @param conditionLiteral
     * @param varElements
     */
    private String renderConditionLiteral(ConditionDetailResponseEvent condition, Map varElementsIdHtmlMap)
    {
        Collection varElements = condition.getVariableElements();
        String ruleLiteral = condition.getDescription();
        VariableElementResponseEvent varElement = null;
        String varElementName = null;
        String selectedValue = null;
        String token = null;
        RE regex = null;

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
                        //selectedValue = (String) varElement.getValue();
                        selectedValue = (String) varElementsIdHtmlMap.get(varElementName);
                    }
                    else
                    {
                        token = "{{" + varElementName + "}}";
                        selectedValue = (String) varElementsIdHtmlMap.get(varElementName);
                    }
                    token = UIUtil.regExSpecCharEscapeFix(token);
                    regex = null;
                    try
                    {
                        regex = new RE(token, RE.REG_ICASE);
                        if (selectedValue == null)
                        {
                            selectedValue = "";
                        }
                        //The dollar sign denotes an anchor in RegEx.
                        if (selectedValue != null)
                        {
                            selectedValue = selectedValue.replace('$', '^');
                            ruleLiteral = regex.substituteAll(ruleLiteral, selectedValue);
                            ruleLiteral = ruleLiteral.replace('^', '$');
                        }
                        else
                        {
                            // ruleLiteral = regex.substituteAll(ruleLiteral,
                            // selectedValue);
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
     * @param collection
     * @return
     */
    private Map getHtmlElements(ConditionDetailResponseEvent condition, int aCounter, int aCondCounter)
            throws IOException
    {

        Collection collection = condition.getVariableElements();
        Map varElements = new HashMap();
        String elementTypeCategory = null;
        String elementTypeDetailGroup = null;
        int counter = 100;
        int realIndex = -1;
        String fieldName = "";
        if (collection != null)
        {
            Iterator elementIterator = collection.iterator();
            while (elementIterator.hasNext())
            {
                realIndex++;
                counter++;
                VariableElementResponseEvent element = (VariableElementResponseEvent) elementIterator.next();
                StringBuffer elementText = new StringBuffer();
                String id = condition.getConditionId() + "_" + element.getName() + "_" + "ID";
                //	fieldName="value(" + id + ")";
                fieldName = "conditionSelectedList[" + aCondCounter + "].variableElements[" + realIndex + "].value";
                //Element name should not be displayed for fixed and reference
                // variables
                //				elementText.append("<b>");
                //				elementText.append("<< ");
                //				elementText.append(element.getName());
                //				elementText.append(" >>");
                //				elementText.append("</b>");
                String value = element.getValue();
                if (value == null)
                {
                    value = "";
                }
                if (element.isFixed() && !element.isReference())
                {
                    elementText.append(" ");
                    String actualValue = null;
                    if (element.isEnumeration())
                    {
                        value = element.getValueId();
                        actualValue = element.getValue();
                        fieldName = "conditionSelectedList[" + aCondCounter + "].variableElements[" + realIndex
                                + "].valueId";
                    }
                    else
                    {
                        //					elementText.append(value);
                        //					elementText.append(" ");
                        elementTypeCategory = element.getEnumerationTypeId();
                        elementTypeDetailGroup = element.getValueType();
                        //String
                        // actualValue=CommonUtilites.getVariableActualText(elementTypeCategory,elementTypeDetailGroup,
                        // value);
                        if (value.equals("") &&element.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS)){
                        } else {
                        	value = CommonUtilites.getDefaultUnderlineSpaces(value, 10);
                        	actualValue = CommonUtilites.getVariableFormattedText(elementTypeCategory,
                                elementTypeDetailGroup, value, false);
                        }
                    }

                    // PRE EVALUATION

                    elementText.append(actualValue);

                    // POST EVALUATION
                }
                else
                {
                    elementText.append("<span class=\"formDeLabel detailsFormLabel\">");
                    if (element.isReference())
                        elementText.append("[ ");
                    else
                        elementText.append("<< ");
                    elementText.append(element.getName());
                    if (element.isReference())
                        elementText.append(" ]");
                    else
                        elementText.append(" >>");
                    elementText.append("</span> ");

                    boolean isEnum = element.isEnumeration();
                    if (isEnum)
                    {
                        fieldName = "conditionSelectedList[" + aCondCounter + "].variableElements[" + realIndex
                                + "].valueId";
                        elementText.append(this.renderEnumElmentValues(element, fieldName));
                    }
                    else
                    { 
                        elementTypeCategory = element.getEnumerationTypeId();
                        elementTypeDetailGroup = element.getValueType();
                        String preValue = CommonUtilites.getVariablePreText(elementTypeCategory,
                                elementTypeDetailGroup, value, false);
                        String actualValue = CommonUtilites.getVariableActualText(elementTypeCategory,
                                elementTypeDetailGroup, value, true);
                        String postValue = CommonUtilites.getVariablePostText(elementTypeCategory,
                                elementTypeDetailGroup, value, false);
                        String preJSValue = CommonUtilites.getJSVariablePreText(elementTypeCategory,
                                elementTypeDetailGroup, actualValue, element.getName(), fieldName, "id"
                                        + Integer.toString(aCounter) + Integer.toString(counter), levelDeep);
                        String postJSValue = CommonUtilites.getJSVariablePostText(elementTypeCategory,
                                elementTypeDetailGroup, actualValue, element.getName(), fieldName, "id"
                                        + Integer.toString(aCounter) + Integer.toString(counter), levelDeep);
                        //ER 50835 - Make all fields optional on Set Details page.
                        String reqValue= CommonUtilites.getJSVariableRequiredText(elementTypeCategory,
								elementTypeDetailGroup, actualValue,
								element.getName(), fieldName,
								"id" + Integer.toString(aCounter) + Integer.toString(counter));

                        // PRE EVALUATION
                        elementText.append(preJSValue);
                        elementText.append(preValue);
                        elementText.append("<input type=\"text\" name=\"");
                        elementText.append(fieldName);
                        elementText.append("\" id=\"");
                        elementText.append(element.getName());
                        elementText.append("\" value=\"");
                        // value=\"");
                        elementText.append(actualValue);
                        elementText.append("\"");

                        elementText.append(" maxlength=\"400\"");
                        
                        if (element.getEnumerationTypeId()!=null){
                        	//if the data type is a date, render a custom attribute so it can be easily validated
                        	if (element.getEnumerationTypeId().equals(UIConstants.FORMAT_PRESENTATION_TYPE_DATE)){
                            	elementText.append(" elementType=\"date\"");	
                            }	
                        }
                        
                        //comments and claimaint address are never required, thus do not display as required
                        if (!element.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS) && !element.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS)){
                        	elementText.append(" class=detailsFormElement>");
                        }else elementText.append(">"); 
                       
                        elementText.append(postValue);
                        elementText.append(postJSValue);
                        //ER 50835 - Make all fields optional on Set Details page.
                        if (!element.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_COMMENTS) && !element.getName().equals(UIConstants.VARIABLE_ELEMENT_NAME_CLAIMAINT_ADDRESS)){
                        //slin: reference fields must be filled in before activation... if (!element.isReference()) {
                           elementText.append(reqValue);
                        //}
                        // POST EVALUATION
                        }

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
    private String renderEnumElmentValues(VariableElementResponseEvent element, String fieldName) throws IOException
    {
        StringBuffer ret = new StringBuffer();
        // get enumerated values
        element.setCodeValues(CodeHelper.getCodes(element.getCodeTableName(), true));
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
                    ret.append("<td><input class=\"detailsFormElement\" type=radio  name=\"");
                    ret.append(fieldName);
                    ret.append("\" value=\"" + code.getCode() + "\"" + selectedString + ">" + code.getDescription()
                            + "</td>");
                    ret.append("</tr>");
                }
                ret.append("</table>");
            }
            else
            {
                ret.append("<select class=\"detailsFormElement\" name=\"");
                ret.append(fieldName);
                ret.append("\" id=\"");
                ret.append(element.getName());
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
            JspWriter writer = pageContext.getOut();
            StringBuffer buffer = new StringBuffer();
            buffer.append(htmlText);
            writer.print(buffer.toString());
        }
        catch (IOException e)
        {

        }
    }

    private Object getReadObject(String propertyName) throws Exception
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

    /**
     * @return
     */
    public String getFormname()
    {
        return formname;
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
    public void setFormname(String string)
    {
        formname = string;

        beanObj = pageContext.getAttribute(this.formname);
        if (beanObj == null)
        {
            // attempt to locate bean in the request context
            beanObj = pageContext.getRequest().getAttribute(this.formname);
        }

        if (beanObj == null)
        {
            // attempt to locate bean in the session context
            beanObj = pageContext.getSession().getAttribute(this.formname);
        }

        if (beanObj == null)
        {
            // attempt to locate bean in the application context
            beanObj = pageContext.getServletContext().getAttribute(this.formname);
        }
    }

    /**
     * @param string
     */
    public void setProperty(String string)
    {
        property = string;
    }

    private String getVarElementId(ConditionDetailResponseEvent condition, VariableElementResponseEvent varElement)
    {
        return condition.getConditionId() + "_" + varElement.getName() + "_" + "ID";
    }

	/**
	 * @return Returns the levelDeep.
	 */
	public int getLevelDeep() {
		return levelDeep;
	}
	/**
	 * @param levelDeep The levelDeep to set.
	 */
	public void setLevelDeep(int levelDeep) {
		this.levelDeep = levelDeep;
	}
}
