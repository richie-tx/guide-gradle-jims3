/*
 * Created on Feb 27, 2007
 *
 * TODO This tag is almost an exact copy of the struts Orginal OptionsCollectionTag though modified to pull out
 * code table values if passed in.
 */
/*
 * $Id: OptionsCollectionTag.java 56513 2004-11-03 19:20:47Z niallp $ 
 *
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ui.taglib;




import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.SelectTag;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import ui.common.CodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;


/**
 * Tag for creating multiple &lt;select&gt; options from a collection. The
 * collection may be part of the enclosing form, or may be independent of
 * the form. Each element of the collection must expose a 'label' and a
 * 'value', the property names of which are configurable by attributes of
 * this tag.
 * <p>
 * The collection may be an array of objects, a Collection, an Enumeration,
 * an Iterator, or a Map.
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *
 * @version $Rev: 56513 $ $Date: 2004-11-03 19:20:47 +0000 (Wed, 03 Nov 2004) $
 * @since Struts 1.1
 */
public class CodeTableTag extends TagSupport {

    // ----------------------------------------------------- Instance Variables

   

    /**
     * Should the label values be filtered for HTML sensitive characters?
     */
    protected boolean filter = true;

    public boolean getFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    /**
     * The name of the bean property containing the label.
     */
    protected String label = "description";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

  

    /**
     * The name of the bean property containing the value.
     */
    protected String value = "code";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    protected boolean sort=true;
    
	/**
	 * @return Returns the sort.
	 */
	public boolean getSort() {
		return sort;
	}
	/**
	 * @param sort The sort to set.
	 */
	public void setSort(boolean sort) {
		this.sort = sort;
	}
	
	
    
    protected String sortOrder="ASC";
    
    /**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
    
    protected boolean sortCode=false;
    
    /**
	 * @return Returns the sortCode.
	 */
	public boolean getSortCode() {
		return sortCode;
	}
	/**
	 * @param sortCode The sortCode to set.
	 */
	public void setSortCode(boolean sortCode) {
		this.sortCode = sortCode;
	}
    
    /**
     * The name of the property to get the codeTableFrom.
     */
    protected String codeTableName = null;

    public String getCodeTableName() {
        return codeTableName;
    }

    public void setCodeTableName(String codeTableName) {
        this.codeTableName = codeTableName;
    }
    
    protected boolean simpleCodeTable=true;
    
    /**
	 * @return Returns the simpleCodeTable.
	 */
	public boolean getSimpleCodeTable() {
		return simpleCodeTable;
	}
	/**
	 * @param simpleCodeTable The simpleCodeTable to set.
	 */
	public void setSimpleCodeTable(boolean simpleCodeTable) {
		this.simpleCodeTable = simpleCodeTable;
	}
    
    
    

    // --------------------------------------------------------- Public Methods

    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Acquire the select tag we are associated with
        SelectTag selectTag = (SelectTag) pageContext.getAttribute(Constants.SELECT_KEY);

        if (selectTag == null) {
            JspException e = new JspException("No parent select tag");
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        // Acquire the collection containing our options
       // Object collection = RequestUtils.lookup(pageContext, name, property, null);
        
        Object collection = null;
        if(sort){
        	if(sortCode){
        		collection=SimpleCodeTableHelper.getCodesSortedByCode(this.codeTableName);
        	}
        	else{
        		collection=SimpleCodeTableHelper.getCodesSortedByDesc(this.codeTableName);
        	}
        	if(sortOrder.equals("DESC")){
        		if(collection!=null && collection instanceof List){
        			Collections.reverse((List)collection);
        		}
        	}
        }
        else{
        	collection=SimpleCodeTableHelper.getUnsortedCodeRespEvts(this.codeTableName);
        }
        

        if (collection == null) {
            JspException e =
                new JspException("No code table valus available");
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }
        StringBuffer sb = new StringBuffer();
        
        
        this.generateOptions(sb,collection, selectTag);

        TagUtils.getInstance().write(pageContext, sb.toString());

        return SKIP_BODY;
    }

    /**
     * Used to gneerate the option values for the select tag
     * @param sb -- result returned in string buffer
     * @param collection
     */
    public  void generateOptions(StringBuffer sb, Object collection, SelectTag selectTag) throws JspException{
//    	 Acquire an iterator over the options collection
        Iterator iter = getIterator(collection);
      

        // Render the options
        while (iter.hasNext()) {

            Object bean = iter.next();
            Object beanLabel = null;
            Object beanValue = null;

            // Get the label for this option
            try {
                beanLabel = PropertyUtils.getProperty(bean, label);
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
                beanValue = PropertyUtils.getProperty(bean, value);
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

            // Render this option
            addOption(sb, stringLabel, stringValue, selectTag.isMatched(stringValue));
        }
    }
    
    
    
    
    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        filter = true;
        label = "label";
        value = "description";
    }

    // ------------------------------------------------------ Protected Methods

    /**
     * Add an option element to the specified StringBuffer based on the
     * specified parameters.
     *<p>
     * Note that this tag specifically does not support the
     * <code>styleId</code> tag attribute, which causes the HTML
     * <code>id</code> attribute to be emitted.  This is because the HTML
     * specification states that all "id" attributes in a document have to be
     * unique.  This tag will likely generate more than one <code>option</code>
     * element element, but it cannot use the same <code>id</code> value.  It's
     * conceivable some sort of mechanism to supply an array of <code>id</code>
     * values could be devised, but that doesn't seem to be worth the trouble.
     *
     * @param sb StringBuffer accumulating our results
     * @param value Value to be returned to the server for this option
     * @param label Value to be shown to the user for this option
     * @param matched Should this value be marked as selected?
     */
    protected void addOption(StringBuffer sb, String label, String value, boolean matched) {

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

    /**
     * Return an iterator for the options collection.
     *
     * @param collection Collection to be iterated over
     *
     * @exception JspException if an error occurs
     */
    protected Iterator getIterator(Object collection) throws JspException {

        if (collection.getClass().isArray()) {
            collection = Arrays.asList((Object[]) collection);
        }

        if (collection instanceof Collection) {
            return (((Collection) collection).iterator());

        } else if (collection instanceof Iterator) {
            return ((Iterator) collection);

        } else if (collection instanceof Map) {
            return (((Map) collection).entrySet().iterator());

        } else {
            throw new JspException("Unable to get iterator");
        }
    }

	
	

}

