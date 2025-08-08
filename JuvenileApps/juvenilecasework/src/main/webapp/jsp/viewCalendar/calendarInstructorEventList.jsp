<!DOCTYPE HTML>
<!------------------MODIFICATIONS ---------------------------->
<!-- March 2009 - ugopinath - create jsp -->
<!-- jul 20 2009 - cshimek  #61004 added timeout.js  -->

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
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>
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

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarInstructorEventList.jsp</title>

<script type="text/javascript">
function onloadForm(tform)
{ 
  var i = document.getElementsByName( "pager.offset" ) ;
  i[0].value = "0" ;
}
</script> 

</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload='javascript:onloadForm(this);' >
<html:form action="/handleViewCalendar" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|350">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define>  
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" 
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">	

<input type="hidden" name="pager.offset" value="<%=offset%>">
 
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.calendarEvent" />&nbsp;<bean:message key="title.search" /> Result <bean:message key="prompt.list" /></td>
  </tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center">
  <tr>
    <td>
      <ul>
        <li>Select a hyperlink to view Event details.</li>
        <li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
	
<bean:define id="pagResPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define>

<logic:notEmpty name="calendarEventListForm" property="individualDayEvents">	
<bean:size id="size" name="calendarEventListForm" property="individualDayEvents"/>
<bean:write name="size" /> calendar event(s) found.
</logic:notEmpty>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign='top' align='center'>	

      <%-- for an 'all events' search, calendar and docket events are
			     shown in two different tables, but both sets of data can not
					 have struts paging, so here, the docket list goes into a
					 scrolling table; for a docket-only search, we allow paging
			--%>
      <logic:notEqual name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.DOCKET_SEARCH%>">	

        <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
          <tr>
            <td class="detailHead"><bean:message key="prompt.calendar" />&nbsp;<bean:message key="prompt.events" /> for Instructor : <bean:write name="calendarEventListForm" property="searchEvent.instructorName"/></td>
          </tr>
          <tr>
            <td>
              <table width='100%' cellpadding="2" cellspacing="1" align="center">
          			<logic:empty name="calendarEventListForm" property="individualDayEvents">
          				<tr> 
          					<td colspan="6">No Calendar Events Available</td> 
          				</tr> 
          			</logic:empty>
  
        	        <logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
  
            			<tr bgcolor='#cccccc' class='subHead'>
 	                	<td>
 	                		<bean:message key="prompt.dateTime" />
            					<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
            				</td>
            				
 	              		<td>
 	              			<bean:message key="prompt.eventType" />
            					<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventType" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4"  />
            		    </td>
            		    <td>
           					<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
        							<bean:message key="prompt.programName" />    
                			<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="programName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5"  />
         						</logic:notEmpty>
         						
           				</td>       
 	                	<td>
 	                		<bean:message key="prompt.serviceName" />
            					<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="6"  />
            			</td>
            			<td>
	                		<bean:message key="prompt.location" />
           					<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceLocationName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="7"  />
           				</td>
            				   				            				
            				
            				
            			</tr>
            			
            				<logic:iterate id="eventsForDay" name="calendarEventListForm" property="individualDayEvents" indexId="index">
          						<pg:item>
	  									<tr class="<% out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">  
		     								<td>
	  											<%-- this check is used to see if this event is prescheduled event. the idea is 
  										     	 that non-prescheduled events do not have a service name, so set 'jname' parm 
  											     as prescheduled so the Action will know how to handle it 
	  											--%>
                   				<logic:notEmpty name="eventsForDay" property="serviceName">
           									<a href="/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>&selectedValue=<bean:write name="eventsForDay" property='juvenileNum'/>&jname=<%=PDCalendarConstants.CALENDAR_TYPE_PRESCHEDULED%> ">
           									<bean:write name="eventsForDay" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
                   				</logic:notEmpty>
  
  									  		<%-- otherwise this is a non-prescheduled event type, pass the juvenile name in the query string --%>
                 		  		<logic:empty name="eventsForDay" property="serviceName">
           									<a href="/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>&jname=<bean:write name='eventsForDay' property='juvenileLastName' />:<bean:write name='eventsForDay' property='juvenileFirstName' />:<bean:write name='eventsForDay' property='juvenileMiddleName' />">
           									<bean:write name="eventsForDay" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
                   				</logic:empty>
         								</td> 								
       								<td><bean:write name="eventsForDay" property="eventType"/></td>       								
      								<td>
      									<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
      										<bean:write name="eventsForDay" property="programName"/>
      									</logic:notEmpty>					   						
   									</td>
									<td><bean:write name="eventsForDay" property="serviceName"/></td>
          								
       								<td><bean:write name="eventsForDay" property="serviceLocationName"/></td>
       							</tr>															
       						</pg:item>
       					</logic:iterate>	
        											
        				</logic:notEmpty>		
                </table>
              </td>
            </tr>
          </table>	  
          <!-- END DETAIL TABLE -->
  
          <%-- Begin Pagination navigation Row--%>
          <table align="center">
          	<tr>
          		<td>
          			<pg:index>
          				<tiles:insert page="/jsp/jimsPagination.jsp" flush="false">
          					<tiles:put name="pagerUniqueName" value="pagerSearch2"/>
          					<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
          				</tiles:insert>
          			</pg:index>
          		</td>
          	</tr>
          </table>			
          <%-- End Pagination navigation Row--%>
          </logic:notEqual>
			<%-- not a docket search --%>  			
						
      <!-- BEGIN BUTTON TABLE -->
      <div class='spacer'></div>
    	<table width="100%" align="center">
        <tr>
          <td align="center">
            <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
            <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
          </td>
        </tr>
      </table>
      <!-- END BUTTON TABLE -->

    </td>
  </tr>
</table>

</pg:pager>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

