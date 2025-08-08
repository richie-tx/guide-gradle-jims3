<!DOCTYPE HTML>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.PDCalendarConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarCancelFutureEventListSummary.jsp</title>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleFutureCalendarEventsCancel" target="content">

<body topmargin="0" leftmargin="0">
<form name="calendarCancelFutureEventListForm">

<!-- BEGIN HEADING TABLE -->
<table width='100%' align="center">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Cancel 
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
        Juvenile
      </logic:equal>		
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
        Service Provider
      </logic:equal>		
  		Future Event List
      <logic:equal name="calendarEventListForm" property="action" value="summary">	
	      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
				<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|202">    		
      	  </logic:equal>
      	  <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
		       <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|182">
      	  </logic:equal>	
        Summary
      </logic:equal>		
      <logic:equal name="calendarEventListForm" property="action" value="confirmation">
      	  <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
				<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|201">    		
      	  </logic:equal>
      	  <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
		       <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|181">
      	  </logic:equal>
        Confirmation
      </logic:equal>		
		</td>
  </tr>

  <logic:equal name="calendarEventListForm" property="action" value="confirmation">
    <tr>
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
	  		<td class='confirm'>Future Events have been cancelled and Notifications sent.</td>
	  	</logic:equal>
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
	  		<td class='confirm'>Future Events have been cancelled.</td>
	  	</logic:equal>
    </tr>
  </logic:equal>		
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" align="center">
  <tr>
    <td>
      <ul>
        <logic:equal name="calendarEventListForm" property="action" value="summary">
          <li>The following events will be cancelled.</li>
          <li>Select <b>Finish</b> button to Cancel the listed Events.</li>
          <li>Select <b>Back</b> button to return to previous page.</li>
        </logic:equal>		
  
        <logic:equal name="calendarEventListForm" property="action" value="confirmation">
          <li>Select the <b>Return to Calendar Search</b> button to return to Calendar Search screen.</li>
        </logic:equal>		
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign='top' align='center'>
			
      <!-- BEGIN Cancel Juvenile Events Summary TABLE -->
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
        <div class='spacer'></div>
        <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
          <tr>
            <td class="detailHead">Future Events to Cancel for: <bean:write name="juvenileProfileHeader" property="juvenileName"/></td>
          </tr>
          <tr>
            <td>
              <table width='100%' cellpadding="2" cellspacing="1" align="center">
                <tr bgcolor='#cccccc' class="subhead">
                  <td><bean:message key="prompt.dateTime" /></td>
  								<td><bean:message key="prompt.eventType" /></td>
                  <td><bean:message key="prompt.programName" /></td>
                  <td><bean:message key="prompt.serviceName" /></td>
                </tr>

        				<logic:empty name="calendarEventListForm" property="selectedCancelEventsList">
                  <tr bgcolor="#cccccc">
                    <td colspan='4' class="subHead">No events(s) found.</td>
                  </tr>
        				</logic:empty>
	
  							<!-- this will be the logic:iterate section -->
        				<logic:notEmpty name="calendarEventListForm" property="selectedCancelEventsList">
      						<logic:iterate indexId="index" id="namesIndex" name="calendarEventListForm" property="selectedCancelEventsList"> 
    								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" height="100%">
       								<td><bean:write name="namesIndex" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></td>
       								<td><bean:write name="namesIndex" property="eventType"/></td>
               				<td><bean:write name="namesIndex" property="programName"/></td> 
                      <td><bean:write name="namesIndex" property="serviceName"/></td>
                    </tr>
	    						</logic:iterate>
	        			</logic:notEmpty>							
              </table>
            </td>
          </tr>
        </table>
      </logic:equal>
			
      <!-- BEGIN Cancel SP Events Summary TABLE -->
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
        <div class='spacer'></div>
        <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
          <tr>
            <td class="detailHead">Future Events to Cancel for Service Provider: <bean:write name="calendarEventListForm" property="searchEvent.serviceProviderName"/></td>
          </tr>
          <tr>
            <td> 
              <table width='100%' cellpadding="2" cellspacing="1" align="center">
              	<tr bgcolor='#cccccc' class='subHead'>
                  <td nowrap='nowrap'><bean:message key="prompt.dateTime" /></td>
                  <td><bean:message key="prompt.programName" /></td>
                  <td><bean:message key="prompt.serviceName" /></td>
                  <td><bean:message key="prompt.eventType" /></td>
                  <td><bean:message key="prompt.locationUnit" /></td>
                </tr>

		        		<logic:empty name="calendarEventListForm" property="selectedCancelEventsList">
                  <tr bgcolor="#cccccc">
                    <td colspan='4' class="subHead">No events(s) found.</td>
                  </tr>
		        		</logic:empty>
								  
			  				<!-- this will be the logic:iterate section -->
		        		<logic:notEmpty name="calendarEventListForm" property="selectedCancelEventsList">
		      				<logic:iterate indexId="indexer" id="namesIndex" name="calendarEventListForm" property="selectedCancelEventsList"> 
			     					<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" height="100%">
		       						<td><bean:write name="namesIndex" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></td>       								
	               			<td><bean:write name="namesIndex" property="programName"/></td> 
	                    <td><bean:write name="namesIndex" property="serviceName"/></td>
	                    <td><bean:write name="namesIndex" property="eventType"/></td>
	                    <td><bean:write name="namesIndex" property="serviceLocationName"/></td>
	                  </tr>
			      			</logic:iterate>
			        	</logic:notEmpty>							
              </table>
            </td>
          </tr>
        </table>
      </logic:equal>

      <!-- BEGIN BUTTON TABLE -->
      <div class='spacer'></div>
			<table width="100%" align="center">
        <tr>
          <td align="center">
            <logic:equal name="calendarEventListForm" property="action" value="summary">
              <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
              <html:submit property="submitAction"><bean:message key="button.finish"></bean:message></html:submit>
              <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
            </logic:equal>

            <logic:equal name="calendarEventListForm" property="action" value="confirmation">
	            <html:submit property="submitAction"><bean:message key="button.returnToCalendarSearch"></bean:message></html:submit>
            </logic:equal> 
          </td>
        </tr>
      </table>
      <!-- END BUTTON TABLE -->

    </td>
  </tr>
</table>
</html:form>

<div><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
