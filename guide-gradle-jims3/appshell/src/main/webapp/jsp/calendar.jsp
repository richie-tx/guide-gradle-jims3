<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<html:html>  
<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
    <title>JIMS2 - Calendaring</title>  
</head>

<body topmargin="0" leftmargin="0" rightmargin="0">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
    <tr><td colspan="3">&nbsp;</td></tr>    
    <tr>
    	<td align="center" valign="top">
    		<jims2:calendar startMonthYearParamName="current" 
    			calendarStyleSheet="redMiniCalendarSkin" 
    			weekDayViewType="FIRSTLETTER" 
    			showPreviousNext="false"/>
    	</td>
    	<td>&nbsp;</td>
    	<td align="center">
    		<jims2:calendar startMonthYear="122005" 
    			sessionAttributeName="sample"
    			serviceEvent="mojo.km.messaging.calendaring.GetCalendarEventsAttendenceEvent" 
    			eventLink="http://www.google.com"/>    			
    	</td>
    </tr>
    <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
    	<td colspan="2"/></td>
    	<td align="center">Using a different CalendarDayDisplay class to show the attendence current and 
    		maximum for the Calendar Event
    	</td>
    </tr>
    <tr>
    	<td colspan="2"/>
    	<td align="center"> 
    		<jims2:calendar startMonthYear="122005"
    			calendarStyleSheet="greenCalendarSkin" 
    			dayDisplayClass="mojo.struts.taglib.calendar.CalendarEventDayAttendeesPresentation"
    			sessionAttributeName="sample"
    			serviceEvent="mojo.km.messaging.calendaring.GetCalendarEventsAttendenceEvent" 
    			eventLink="http://www.google.com"/>       		    			
    	</td>
    </tr>
</table>
</body>
</html:html>

