/*
 * Created on May 5, 2004
 */
package ui.taglib;

import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author cshimek
 */
/*
 * Copyright 1999,2004 The Apache Software Foundation.
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

/**
 * JSP Tag <b>currentTime</b>, used to get the current time in
 * milliseconds since Jan 1, 1970 GMT.
 * <p>
 * JSP Tag Lib Descriptor
 * <p><pre>
 * &lt;name&gt;currentTime&lt;/name&gt;
 * &lt;tagclass&gt;org.apache.taglibs.datetime.CurrentTimeTag&lt;/tagclass&gt;
 * &lt;bodycontent&gt;empty&lt;/bodycontent&gt;
 * &lt;info&gt;Gets the current time in milliseconds since Jan 1, 1970 GMT.&lt;/info&gt;
 * </pre>
 *
 * @author Glenn Nielsen
 */

public class HeadingDate extends TagSupport
{

	/**
	 * Method called at end of Tag to output current time in milliseconds.
	 *
	 * @return EVAL_PAGE
	 */
	public final int doEndTag() throws JspException
	{
	Calendar calendar = Calendar.getInstance();
	
	try {
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		String[] days = {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
		String currentDate = days[calendar.get(Calendar.DAY_OF_WEEK)] 
		+ " " +  months[calendar.get(Calendar.MONTH)] 
		+ " " +  calendar.get(Calendar.DATE)
		 + ", " + calendar.get(Calendar.YEAR); 
		pageContext.getOut().write(currentDate);
	} catch(Exception e) {
		throw new JspException("IO Error: " + e.getMessage());
	}

	return EVAL_PAGE;
	}

}
