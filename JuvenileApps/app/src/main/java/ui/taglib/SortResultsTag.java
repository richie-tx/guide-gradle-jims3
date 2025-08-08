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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;

import ui.common.SorterObj;



/**
 * Tag for 
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *
 * @version $Rev: 56513 $ $Date: 2004-11-03 19:20:47 +0000 (Wed, 03 Nov 2004) $
 * @since Struts 1.1
 */
public class SortResultsTag extends TagSupport {

    // ----------------------------------------------------- Instance Variables

   public static final String SORT_ASC="ASC";

   public static final String SORT_DESC="DESC";
   
   public static final String SORT_TYPE_STRING="STRING";
   public static final String SORT_TYPE_INTEGER="INTEGER";
   public static final String SORT_TYPE_LONG="LONG";
   public static final String SORT_TYPE_DOUBLE="DOUBLE";
   public static final String SORT_TYPE_DATE="DATE";
   public static final String SORT_TYPE_TIME="TIME";
   public static final String SORT_TYPE_TIME_AM_PM="TIME_AM_PM";
   
   
    /**
     * Should the label values be filtered for HTML sensitive characters?
     */
    protected boolean defaultSort = false;

    protected String defaultSortOrder=SORT_ASC;
   
    /**
     * The name of the bean property containing the label.
     */
    protected String beanName = "";

   
    protected String results = "";

   
    protected String primaryPropSort = "";
    protected String secondPropSort = "";
    protected String finalPropSort = "";
	
    protected String primarySortType = "";
    protected String secondarySortType = "";
    protected String finalSortType = "";
    
    protected String sortId = "";
    
    protected int levelDeep=2;
    
    protected boolean hideMe=false;
    
    

    

    // --------------------------------------------------------- Public Methods

    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

    	try{
	        
	       
	       // Object myObj= pageContext.getAttribute("justin");
	        String baseUri;
	       StringBuffer uri = null;
				baseUri = ((HttpServletRequest)pageContext.getRequest())
					.getRequestURI();
				int i = baseUri.indexOf('?');
				if (i != -1)
					baseUri = baseUri.substring(0, i);

			if (uri == null)
				uri = new StringBuffer(baseUri.length() + 32);
			else
				uri.setLength(0);
			uri.append(baseUri);

			StringBuffer sb = new StringBuffer();
			JspWriter writer = pageContext.getOut();
			String sortIdParam =pageContext.getRequest().getParameter("sortId");
			String sortDirectionParam =pageContext.getRequest().getParameter("sortDir");
			String paginationItem=pageContext.getRequest().getParameter("pager.offset");
			boolean pagerInUse=false;
			if(paginationItem!=null){
				pagerInUse=true;
			}
			if(sortDirectionParam==null){
				sortDirectionParam=getDefaultSortOrder();
			}
			String trueBaseURI= baseUri + "?sortId=" + getSortId();
	        String upLink= trueBaseURI + "&sortDir=" + SORT_ASC;
	        String downLink= trueBaseURI + "&sortDir=" + SORT_DESC;
	        String upArrowImage="<img src='../../images/arrow_up.gif' border='0' hspace='0' vspace='0'>";
	        String downArrowImage="<img src='../../images/arrow_down.gif' border='0' hspace='0' vspace='0'>";
	        if(levelDeep==1){
	        	upArrowImage="<img src='../images/arrow_up.gif' border='0' hspace='0' vspace='0'>";
		        downArrowImage="<img src='../images/arrow_down.gif' border='0' hspace='0' vspace='0'>";
	        }
	        else if(levelDeep==3){
	        	upArrowImage="<img src='../../../images/arrow_up.gif' border='0' hspace='0' vspace='0'>";
		        downArrowImage="<img src='../../../images/arrow_down.gif' border='0' hspace='0' vspace='0'>";
	        }
	        else if(levelDeep==4){
	        	upArrowImage="<img src='../../../../images/arrow_up.gif' border='0' hspace='0' vspace='0'>";
		        downArrowImage="<img src='../../../../images/arrow_down.gif' border='0' hspace='0' vspace='0'>";
	        }
	      //  sb.append("<a href='#' onclick="changeFormActionURL(document.forms[0].name, '/<msp:webapp/>juvenileProfileSearchSortItems.do?id=juvenileProfileSearchByJuvenileNumber&order=ascending',true)">
	       if(!isHideMe()){
		        sb.append("&nbsp;<a href='" + upLink + "'>");
		        sb.append(upArrowImage);
		        sb.append("</a>&nbsp;");
		        sb.append("<a href='" + downLink + "'>");
		        sb.append(downArrowImage);
		        sb.append("</a>&nbsp;");
	       }
			if ((sortIdParam == null && defaultSort && !pagerInUse) || (sortIdParam!=null && sortIdParam.equals(getSortId()) && !sortIdParam.equals(""))) {
	//		      Acquire an iterator over the options collection and resort the collection
				 // Acquire the collection containing our list
				
					Iterator iter = null; 
		        	Object formBean=getBeanFormObject();
		        	
		        	//This enables the tag to be used with no reference to any form.
		        	if( results != null && !results.trim().equals("") ) {
		        		Object resultList = TagUtils.getInstance().lookup(pageContext, beanName, results, null);
		        		iter = getIterator(resultList);
		        	} else {
		        		iter = getIterator(formBean);
		        	}
				        
			        
			        ArrayList myThingsToSort=new ArrayList();
			        while (iter!=null && iter.hasNext()) {
			            Object bean = iter.next();
			            Object beanSortVal = null;
			            Object secBeanSortVal = null;
			            Object finalBeanSortVal = null;
			            SorterObj sorterObj=new SorterObj();
			            sorterObj.setActualObject(bean);
			            if(secondPropSort!=null && !secondPropSort.equals("") && secondarySortType!=null && !secondarySortType.equals("")){
			            	sorterObj.setUseSecSort(true);
			            }			           
			            else{
			            	sorterObj.setUseSecSort(false);
			            }
			            
			            if(finalPropSort!=null && !finalPropSort.equals("") && finalPropSort!=null && !finalPropSort.equals(""))
			            {
			            	sorterObj.setUseFinalSort(true);
			            }
			            else{
			            	sorterObj.setUseFinalSort(false);
			            }
			            if(sortDirectionParam.equals(SORT_ASC))
			            	sorterObj.setSortAsc(true);
			            else
			            	sorterObj.setSortAsc(false);
			            // Get the label for this option
			            try {
			                beanSortVal = PropertyUtils.getProperty(bean, primaryPropSort);
			                beanSortVal=convertToProperType(beanSortVal, getPrimarySortType());
			                sorterObj.setPrimaryPropertyValue(beanSortVal);
			                sorterObj.setPrimarySortType(getPrimarySortType());
			                if(sorterObj.isUseSecSort()){
			                	secBeanSortVal= PropertyUtils.getProperty(bean, secondPropSort);
			                	beanSortVal=convertToProperType(secBeanSortVal, getSecondarySortType());
			                	sorterObj.setSecondaryPropertyValue(beanSortVal);
			                	sorterObj.setSecondarySortType(getSecondarySortType());
			                }
			                if(sorterObj.isUseFinalSort()){
			                	finalBeanSortVal= PropertyUtils.getProperty(bean, finalPropSort);
			                	beanSortVal=convertToProperType(finalBeanSortVal, getFinalSortType());
			                	sorterObj.setFinalPropertyValue(beanSortVal);
			                	sorterObj.setFinalSortType(getFinalSortType());
			                }
			                myThingsToSort.add(sorterObj);
			            } catch (IllegalAccessException e) {
			                JspException jspe =
			                    new JspException("Cannot access the property to be sorted");
			                TagUtils.getInstance().saveException(pageContext, jspe);
			                throw jspe;
			            } catch (InvocationTargetException e) {
			                Throwable t = e.getTargetException();
			                JspException jspe =
			                    new JspException("Cannot access the property to be sorted");
			                TagUtils.getInstance().saveException(pageContext, jspe);
			                throw jspe;
			            } catch (NoSuchMethodException e) {
			                JspException jspe =
			                    new JspException("Cannot access the property to be sorted");
			                TagUtils.getInstance().saveException(pageContext, jspe);
			                throw jspe;
			            }
			        }
			        iter=null;
			       // Do the actual sort here
			        Collections.sort((List)myThingsToSort);
			        ArrayList finalList=getSortedList(myThingsToSort);
			        
			        //Setting the sorted list back to where it originally comes from
			        if(results != null && !results.trim().equals("")) {
			        	PropertyUtils.setProperty(formBean,results,finalList);
			        } else {
			        	List t = (List)formBean;
				        t.clear();
				        t.addAll(finalList);
			        }
			} // END IF
			
			TagUtils.getInstance().write(pageContext, sb.toString());
	     //   
    	}// END TRY
    	catch(Exception e){
    		// ignore all errors
    		System.out.println(e.toString());
    	}

        return SKIP_BODY;
    }
    
    
    
    private ArrayList getSortedList(ArrayList aThingsSorted){
    	if(aThingsSorted==null){
    		return null;
    	}
    	else if(aThingsSorted.size()<1){
    		return aThingsSorted;
    	}
    	ArrayList myNewSortedList=new ArrayList();
    	for(int loopX=0;loopX<aThingsSorted.size();loopX++){
    		myNewSortedList.add(((SorterObj)(aThingsSorted.get(loopX))).getActualObject());
    	}
    	return myNewSortedList;
    }
    
    private Object convertToProperType(Object aBeanVal, String aSortType){
    	if(aSortType==null)
    		aSortType=SORT_TYPE_STRING;
    	 if(aBeanVal==null)
			aBeanVal="";
    	if(aSortType.equals(SORT_TYPE_STRING)){
    		 if(aBeanVal instanceof String){
    		 	
    		 }
    		 else if(aBeanVal instanceof Date){
    		 	aBeanVal=getStringFromDate((Date)aBeanVal,UIConstants.DATE_FMT_1);
    		 }
    		 else if(aBeanVal instanceof Timestamp){
    		 	aBeanVal=getStringFromTimeStamp((Timestamp)aBeanVal);
    		 }
    		 else if(aBeanVal instanceof Boolean){
    		 	Boolean boolObj=(Boolean)aBeanVal;
    		 	boolean boolVal=boolObj.booleanValue();
    		 	if(boolVal)
    		 	aBeanVal="TRUE";
    		 	else
    		 		aBeanVal="FALSE";
    		 }
    	}
    	else if(aSortType.equals(SORT_TYPE_INTEGER)){
	   		 if(aBeanVal instanceof String){
	   		 	aBeanVal=getIntFromString((String)aBeanVal);
	   		 }
	   		
	   		 else if(aBeanVal instanceof Date){
	   		 	aBeanVal=getIntFromString("");
	   		 }
	   		 else if(aBeanVal instanceof Timestamp){
	   			aBeanVal=getIntFromString("");
	   		 }
    	}
    	else if(aSortType.equals(SORT_TYPE_LONG)){
	   		 if(aBeanVal instanceof String){
	   		 	aBeanVal=getLongFromString((String)aBeanVal);
	   		 }
	   		
	   		 else if(aBeanVal instanceof Date){
	   		 	aBeanVal=getLongFromString("");
	   		 }
	   		 else if(aBeanVal instanceof Timestamp){
	   			aBeanVal=getLongFromString("");
	   		 }
   	}
    	else if(aSortType.equals(SORT_TYPE_DOUBLE)){
	   		 if(aBeanVal instanceof String){
	   		 	aBeanVal=getDoubleFromString((String)aBeanVal);
	   		 }
	   		
	   		 else if(aBeanVal instanceof Date){
	   		 	aBeanVal=getDoubleFromString("");
	   		 }
	   		 else if(aBeanVal instanceof Timestamp){
	   			aBeanVal=getDoubleFromString("");
	   		 }
    	}
    	else if(aSortType.equals(SORT_TYPE_DATE)){
	   		 if(aBeanVal instanceof String){
	   		 	aBeanVal=getDateFromString((String)aBeanVal,UIConstants.DATE_FMT_1);
	   		 }
	   		 else if(aBeanVal instanceof Date){
	   		 	
	   		 }
	   		 else if(aBeanVal instanceof Timestamp){
	   		 	Timestamp aTimestamp=(Timestamp)aBeanVal;
	   			aBeanVal=new Date(aTimestamp.getTime());
	   		 }
    	}
    	else if(aSortType.equals(SORT_TYPE_TIME)){
	   		 if(aBeanVal instanceof String){
	   		 	aBeanVal=getTimestampFromString((String)aBeanVal);
	   		 }
	   		 else if(aBeanVal instanceof Date){
	   		 	Date aDate=(Date)aBeanVal;
	   			aBeanVal=new Timestamp(aDate.getTime());
	   		 }
	   		 else if(aBeanVal instanceof Timestamp){
	   		 	
	   		 }
    	} 
    	else if(aSortType.equals(SORT_TYPE_TIME_AM_PM)) {
    		aBeanVal = DateUtil.stringToDate((String)aBeanVal, "hh:mm a");
    	}
    	return aBeanVal;
    }
    
    public static Long getLongFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return (new Long(0));
		else {
			try{
				return (new Long(Long.parseLong(aValue)));
			}
			catch(Exception e){
				return (new Long(0));
			}
		}
	}
    
    public static Integer getIntFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return (new Integer(0));
		else {
			try{
				return (new Integer(Integer.parseInt(aValue)));
			}
			catch(Exception e){
				return (new Integer(0));
			}
		}
	}

	public static Double getDoubleFromString(String aValue) {
		if (aValue == null || aValue.trim().equals(""))
			return (new Double(0.0));
		else {
			try{
				return (new Double(Double.parseDouble(aValue)));
			}
			catch(Exception e){
				return (new Double(0.0));
			}
		}
	}
    
    public static Date getDateFromString(String dateAsString, String format) {
		Date date = null;
		if (dateAsString == null || dateAsString.equals("")) {
			dateAsString="01/01/1600";
		}
		if (format == null || format.equals(""))
			format = UIConstants.DATE_FMT_1;
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			date = fmt.parse(dateAsString);
		} catch (ParseException e) {
			try{
				return fmt.parse("01/01/1600");
			}
			catch(Exception ex){
				return null;
			}
		}

		return date;

	}
    
    /**
	 * @deprecated use DateUtil.dateToString
	 */
	public static String getStringFromDate(Date aDate, String format) {
		String strDate = null;
		if (aDate == null) {
			return "";
		}
		if (format == null || format.equals(""))
			format = UIConstants.DATE_FMT_1;
		try{
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			strDate = fmt.format(aDate);
		}
		catch(Exception e){
			strDate="";
		}
		return strDate;

	}

	/**
	 * @deprecated use DateUtil.dateToString
	 */
	public static String getStringFromTimeStamp(Timestamp aTimeStamp) {
		if (aTimeStamp == null) {
			return "";
		}
		return aTimeStamp.toString();
	}

	/**
	 * @deprecated use DateUtil.dateToString
	 */
	public static Timestamp getTimestampFromString(String aStrTimeStamp) {
		if (aStrTimeStamp == null || aStrTimeStamp.trim().equals("")) {
			return new Timestamp(1600,1,1,11,59,0,0);
		}
		try {
			return Timestamp.valueOf(aStrTimeStamp);
		} catch (IllegalArgumentException e) {
		
			try {
				return Timestamp.valueOf("1600-01-01 " + aStrTimeStamp);
			} catch (IllegalArgumentException ex) {
				System.out.println(e.toString());
				return new Timestamp(1600,1,1,11,59,0,0);
			}
		}
	}

	/**
	 * @deprecated use DateUtil.dateToString
	 * 
	 * This method parses a given java.util.Date and returns the time in the
	 * hh:mm format
	 * 
	 * @param date
	 * @return String representing the time in hh:mm format.
	 */
	public static String parseTime(Date aDate) {
		if (aDate == null) {
			return "";
		}
		String timeFormat = "hh:mm";
		String timeFormat1="";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		try{
			timeFormat1 = sdf.format(aDate);
		}
		catch(Exception e){
			timeFormat1="";
		}
		return timeFormat1;
	}

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        defaultSort = false;
        defaultSortOrder=SORT_ASC;
        beanName = "";
        results = "";
		primaryPropSort = "";
		secondPropSort = "";
		primarySortType = "";
		secondarySortType = "";
		sortId = "";
		levelDeep=2;
		hideMe=false;
    }

    // ------------------------------------------------------ Protected Methods

  

   

	/**
	 * @return Returns the beanName.
	 */
	public String getBeanName() {
		return beanName;
	}
	/**
	 * @param beanName The beanName to set.
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	/**
	 * @return Returns the defaultSort.
	 */
	public boolean isDefaultSort() {
		return defaultSort;
	}
	/**
	 * @param defaultSort The defaultSort to set.
	 */
	public void setDefaultSort(boolean defaultSort) {
		this.defaultSort = defaultSort;
	}
	/**
	 * @return Returns the defaultSortOrder.
	 */
	public String getDefaultSortOrder() {
		return defaultSortOrder;
	}
	/**
	 * @param defaultSortOrder The defaultSortOrder to set.
	 */
	public void setDefaultSortOrder(String defaultSortOrder) {
		defaultSortOrder=defaultSortOrder.toUpperCase();
		if(defaultSortOrder.equals(SORT_ASC) || defaultSortOrder.equals(SORT_DESC)){
			this.defaultSortOrder = defaultSortOrder;
		}
		else{
			this.defaultSortOrder=SORT_ASC;
		}
	}
	
	/**
	 * @return Returns the results.
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(String results) {
		this.results = results;
	}
	/**
	 * @return Returns the sortId.
	 */
	public String getSortId() {
		return sortId;
	}
	/**
	 * @param sortId The sortId to set.
	 */
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	
	/**
	 * @return Returns the primaryPropSort.
	 */
	public String getPrimaryPropSort() {
		return primaryPropSort;
	}
	/**
	 * @param primaryPropSort The primaryPropSort to set.
	 */
	public void setPrimaryPropSort(String primaryPropSort) {
		this.primaryPropSort = primaryPropSort;
	}
	/**
	 * @return Returns the secondPropSort.
	 */
	public String getSecondPropSort() {
		return secondPropSort;
	}
	/**
	 * @param secondPropSort The secondPropSort to set.
	 */
	public void setSecondPropSort(String secondPropSort) {
		this.secondPropSort = secondPropSort;
	}
	
	/**
	 * @return Returns the finalPropSort.
	 */
	public String getFinalPropSort() {
		return finalPropSort;
	}
	/**
	 * @param finalPropSort The finalPropSort to set.
	 */
	public void setFinalPropSort(String finalPropSort) {
		this.finalPropSort = finalPropSort;
	}
	
	private Object getBeanFormObject() throws Exception
	{
		// attempt to locate bean in the page context
		Object beanObj = pageContext.getAttribute(getBeanName());

		if (beanObj == null)
		{
			// attempt to locate bean in the request context
			beanObj = pageContext.getRequest().getAttribute(getBeanName());
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the session context
			beanObj = pageContext.getSession().getAttribute(getBeanName());
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the application context
			beanObj = pageContext.getServletContext().getAttribute(getBeanName());
		}

		return beanObj;
	}
	/**
	 * @return Returns the primarySortType.
	 */
	public String getPrimarySortType() {
		return primarySortType;
	}
	/**
	 * @param primarySortType The primarySortType to set.
	 */
	public void setPrimarySortType(String primarySortType) {
		primarySortType=primarySortType.toUpperCase();
		if(primarySortType.equals(SORT_TYPE_DATE) || primarySortType.equals(SORT_TYPE_DOUBLE) || primarySortType.equals(SORT_TYPE_INTEGER) || primarySortType.equals(SORT_TYPE_TIME) || primarySortType.equals(SORT_TYPE_STRING) || primarySortType.equals(SORT_TYPE_LONG) || primarySortType.equals(SORT_TYPE_TIME_AM_PM)){
			this.primarySortType = primarySortType;
		}
		else{
			this.primarySortType=SORT_TYPE_STRING;
		}
	}
	/**
	 * @return Returns the secondarySortType.
	 */
	public String getSecondarySortType() {
		
		return secondarySortType;
	}
	/**
	 * @param secondarySortType The secondarySortType to set.
	 */
	public void setSecondarySortType(String secondarySortType) {
		secondarySortType=secondarySortType.toUpperCase();
		if(secondarySortType.equals(SORT_TYPE_DATE) || secondarySortType.equals(SORT_TYPE_DOUBLE) || secondarySortType.equals(SORT_TYPE_INTEGER) || secondarySortType.equals(SORT_TYPE_TIME) || secondarySortType.equals(SORT_TYPE_STRING)|| primarySortType.equals(SORT_TYPE_LONG)){
			this.secondarySortType = secondarySortType;
		}
		else{
			this.secondarySortType=SORT_TYPE_STRING;
		}
	}
	
	/**
	 * @return Returns the finalSortType.
	 */
	public String getFinalSortType() {
		
		return finalSortType;
	}
	/**
	 * @param finalSortType The finalSortType to set.
	 */
	public void setFinalSortType(String finalSortType) {
		finalSortType=finalSortType.toUpperCase();
		if(finalSortType.equals(SORT_TYPE_DATE) || finalSortType.equals(SORT_TYPE_DOUBLE) || finalSortType.equals(SORT_TYPE_INTEGER) || finalSortType.equals(SORT_TYPE_TIME) || finalSortType.equals(SORT_TYPE_STRING)|| finalSortType.equals(SORT_TYPE_LONG)){
			this.finalSortType = finalSortType;
		}
		else{
			this.finalSortType=SORT_TYPE_STRING;
		}
	}
	/**
	 * @return Returns the hideMe.
	 */
	public boolean isHideMe() {
		return hideMe;
	}
	/**
	 * @param hideMe The hideMe to set.
	 */
	public void setHideMe(boolean hideMe) {
		this.hideMe = hideMe;
	}
	
	 /**
     * Return an iterator for the options collection.
     *
     * @param collection Collection to be iterated over
     *
     * @exception JspException if an error occurs
     */
    protected Iterator getIterator(Object collection) throws JspException {

    	if(collection==null){
    		return null;
    	}
    	
        if (collection.getClass().isArray()) {
            collection = Arrays.asList((Object[]) collection);
        }

        if (collection instanceof Collection) {
            return (((Collection) collection).iterator());

        } else if (collection instanceof Iterator) {
            return ((Iterator) collection);

    } else if (collection instanceof List) {
    	 return (((List) collection).iterator());
    }
        else if (collection instanceof Map) {
            return (((Map) collection).entrySet().iterator());

        } else {
            throw new JspException("Unable to get iterator");
        }
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

