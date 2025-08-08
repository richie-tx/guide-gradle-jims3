<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 5/11/2012	RYoung		ER #73310 Display casefile Info used to create event --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDCalendarConstants" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>


<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarEventList.jsp</title>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleCalendarEventDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|136"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.heading" />&nbsp;<bean:message key="title.juvenileCasework" />&nbsp;- <bean:message key="title.calendarEvent" /> - Event&nbsp;<bean:message key="prompt.list" /></td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>


<%-- Begin Pagination Header Tag--%>

<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber" scope="request">		
  <input type="hidden" name="pager.offset" value="<%= offset %>">

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Select a hyperlink to view Calendar Event or Docket Event details.</li>
        <li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END Casefile Header TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">  	
  <tr>
    <td>	 	
      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="calendartab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
      <%--tabs end--%>
		
    	<table width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">	
    		<tr>
    			<td>
    				<table width='98%' align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">
            	<tr>
            		<td class="detailHead" colspan='7'>Calendar Events (<bean:write name="calendarEventListForm" property="eventDate" formatKey="date.format.mmddyyyy"/>)</td>
            	</tr>
    
    	        <logic:empty name="calendarEventListForm" property="dayEvents">
    	          <tr bgcolor='#cccccc'> 
    	            <td colspan="6" class='subHead'>No Events Available</td> 
    	          </tr> 
    	        </logic:empty>
    
                <logic:notEmpty name="calendarEventListForm" property="dayEvents">
  		        	<tr>
  		            	<td width="8%" class="formDeLabel"><bean:message key="prompt.time" /></td>
    						<logic:equal name ="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_JPO%>">
    							<td class='formDeLabel' nowrap='nowrap'><bean:message key='prompt.juvenile#'/></td>
    						</logic:equal>	
	  		            <td width="30%" class="formDeLabel"><bean:message key="prompt.locationUnit" /></td>
	  		            <td width="10%" class="formDeLabel"><bean:message key="prompt.type" /></td>
	  		            <td width="25%" class="formDeLabel"><bean:message key="prompt.service" /></td>
	  		            <td width="15%" class="formDeLabel"><bean:message key="prompt.service" /> <bean:message key="prompt.provider" /></td>
	        			<td width="10%" class="formDeLabel"><bean:message key="prompt.status" /></td>
  		          	</tr> 
  
    				<logic:equal name ="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_JUVENILE%>">
    					<logic:iterate indexId="indexer" id="eventsForDay" name="calendarEventListForm" property="dayEvents">
    						<%-- Begin Pagination item wrap --%>
    						<pg:item>		  
             					<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
              						<td>
        								<a href="/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>&currentJuvenileId=<bean:write name="calendarEventListForm" property='juvenileNum'/>">
        								<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/></a>
        							    <bean:write name="eventsForDay" property="juvenileAttendanceStatus" />
        						  	</td>
              				  		<td>
        							    <logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">				
        									<bean:write name="eventsForDay" property="location"/>
        								</logic:equal>
        							    <logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">				
        									<bean:write name="eventsForDay" property="familyLocation"/>
        									
        									<logic:notEmpty name="eventsForDay" property="familyLocationValidation">
												<logic:equal name="eventsForDay" property="familyLocationValidation" value="Y">
													<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
												</logic:equal>
												<logic:equal name="eventsForDay" property="familyLocationValidation" value="N">
													<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
												</logic:equal>
											</logic:notEmpty>
        									
        								</logic:equal>
        							    <logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">
        							    				
        									<bean:write name="eventsForDay" property="familyLocation"/>
        									
        									<logic:notEmpty name="eventsForDay" property="familyLocationValidation">
												<logic:equal name="eventsForDay" property="familyLocationValidation" value="Y">
													<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
												</logic:equal>
												<logic:equal name="eventsForDay" property="familyLocationValidation" value="N">
													<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
												</logic:equal>
											</logic:notEmpty>
        									
        								</logic:equal>
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">				
        									<bean:write name="eventsForDay" property="familyLocation"/>
        								</logic:equal>
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">				
        									<bean:write name="eventsForDay" property="familyLocation"/>
        								</logic:equal>
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">				
        									<bean:write name="eventsForDay" property="familyLocation"/>
        								</logic:equal>
        								
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">
        								  <logic:empty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>
        								  </logic:empty>
        								  <logic:notEmpty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>:&nbsp;&nbsp;<bean:write name="eventsForDay" property="instructionType"/>
        								  </logic:notEmpty>	
        								</logic:equal>
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">				
        									<logic:empty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>
        								  </logic:empty>
        								  <logic:notEmpty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>:&nbsp;&nbsp;<bean:write name="eventsForDay" property="instructionType"/>
        								  </logic:notEmpty>
        								</logic:equal>
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">				
        									<bean:write name="eventsForDay" property="facilityCd"/>
        								</logic:equal>    
        								<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">							  
             								<bean:write name="eventsForDay" property="facilityCd"/>
             							</logic:equal>  
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">    	
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">								 
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">				
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">
	       								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">
	       								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">
	       								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">
        								<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
        									<bean:write name="eventsForDay" property="serviceLocationName"/>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        								</logic:notEqual>
        							</td>
       		        				<td><bean:write name="eventsForDay" property="eventType"/></td>
            					    <td><bean:write name="eventsForDay" property="serviceName"/></td>
            					    <td><bean:write name="eventsForDay" property="serviceProviderName"/></td>
            					    <td><bean:write name="eventsForDay" property="eventStatus"/></td>
            					</tr>
            				</pg:item>
            				<%-- End Pagination item wrap --%>		
            		  	</logic:iterate>			
              		</logic:equal>	

          			<logic:equal name ="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_JPO%>">
          				<bean:define id="currentJPO" name="calendarEventListForm" property="officerId"/>

          				<logic:iterate indexId="indexer" id="eventsForDay" name="calendarEventListForm" property="dayEvents">
         					<%-- Begin Pagination item wrap --%>
         					<pg:item>		  
             					<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	                 				<td>
             							<a href="/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>&currentJuvenileId=<bean:write name="eventsForDay" property="juvenileNum"/>">									
             							<bean:write name="eventsForDay" property="eventDate" formatKey="time.format.hhmma"/></a>
             							<bean:write name="eventsForDay" property="juvenileAttendanceStatus" />
             					  	</td>
             					  	<td><bean:write name="eventsForDay" property="juvenileNum"/></td>	
                				  	<td>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">				
             								<bean:write name="eventsForDay" property="location"/>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">				
             								<bean:write name="eventsForDay" property="familyLocation"/>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">				
             								<bean:write name="eventsForDay" property="familyLocation"/>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">				
             								<bean:write name="eventsForDay" property="familyLocation"/>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">				
             								<bean:write name="eventsForDay" property="familyLocation"/>
             							</logic:equal>							  
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">				
             								<bean:write name="eventsForDay" property="familyLocation"/>
             							</logic:equal>							  
             														  
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">				
             								<logic:empty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>
        								  </logic:empty>
        								  <logic:notEmpty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>:&nbsp;&nbsp;<bean:write name="eventsForDay" property="instructionType"/>
        								  </logic:notEmpty>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">				
             								<logic:empty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>
        								  </logic:empty>
        								  <logic:notEmpty name="eventsForDay" property="instructionType">				
        									<bean:write name="eventsForDay" property="schoolDistrictName"/>/<bean:write name="eventsForDay" property="schoolName"/>:&nbsp;&nbsp;<bean:write name="eventsForDay" property="instructionType"/>
        								  </logic:notEmpty>
             							</logic:equal>
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">							  
             								<bean:write name="eventsForDay" property="facilityCd"/>
             							</logic:equal>         
             							<logic:equal name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">							  
             								<bean:write name="eventsForDay" property="facilityCd"/>
             							</logic:equal>         							  
           							    <logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">		
           							    <logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">						  
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">				
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.JOB_VISIT%>">
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.HOME_VISIT%>">
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">	
             							<logic:notEqual name="eventsForDay" property="eventTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">										  
             								<bean:write name="eventsForDay" property="serviceLocationName"/>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             							</logic:notEqual>
             					  	</td>
                 					<td><bean:write name="eventsForDay" property="eventType"/></td>
                 					<td><bean:write name="eventsForDay" property="serviceName"/></td>
             					  	<td><bean:write name="eventsForDay" property="serviceProviderName"/></td>	  
             					  	<td><bean:write name="eventsForDay" property="eventStatus"/></td>
             					</tr>
         					  </pg:item>
         				    <%-- End Pagination item wrap --%>	
              	  </logic:iterate>			
          			</logic:equal>	
        		  </logic:notEmpty>
            </table>
	  
        	  <logic:notEmpty name="calendarEventListForm" property="docketEvents">
          	  <!-- BEGIN Dockets TABLE -->
          		<div class='spacer'></div>
          		<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
          		  <tr>
            			<td class="detailHead">Docket Events ( <bean:write name="calendarEventListForm" property="eventDate" formatKey="date.format.mmddyyyy"/> )</td>
          		  </tr>
          		 <%--  <logic:equal name="docketEvent" property="recType" value="COURT"> --%>
          		  <tr>
            			<td>
            			  <table width='100%' cellpadding="2" cellspacing="1">
            				<tr class="formDeLabel" >
            				  <td nowrap='nowrap'><bean:message key="prompt.time" /></td>
            				  <td nowrap='nowrap'><bean:message key="prompt.hearingType" /></td> 
            				  <td nowrap='nowrap'><bean:message key="prompt.court" /></td>           				  			  
            				  <td nowrap='nowrap'><bean:message key="prompt.petition#" /></td>            				  
            				  <td nowrap='nowrap'><bean:message key="prompt.petitionAllegation" /></td>
            				  <td nowrap='nowrap'><bean:message key="prompt.courtResult" /></td>
            				  <td nowrap='nowrap'><bean:message key="prompt.courtDisposition" /></td>
            				  <td nowrap='nowrap'><bean:message key="prompt.attorneyName" /></td>
            				</tr>
            				<logic:iterate id="docketEvent" name="calendarEventListForm" property="docketEvents">
            					<tr class='alternateRow'>
            					  <td><a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.docketEventDetails"/>&eventType=<bean:write name="docketEvent" property="docketType"/>&eventId=<bean:write name="docketEvent" property="docketEventId"/>',300,800)">									  
	            					  <bean:write name="docketEvent" property="eventDateWithTime" formatKey="time.format.hhmma" /></a>
	            					</td>
	            				  <td><span title="<bean:write name="docketEvent" property="hearingTypeDesc"/>"><bean:write name="docketEvent" property="hearingType"/></span></td>	
	            				  <td><bean:write name="docketEvent" property="court"/></td>				  
            					  <td><bean:write name="docketEvent" property="petitionNumber"/></td>  
            					  <logic:equal name="docketEvent" property="recType" value="COURT">           					  
            					  	<td><span title="<bean:write name="docketEvent" property="petitionAllegationDesc"/>"><bean:write name="docketEvent" property="allegation"/></span></td>
            					  </logic:equal>
            					  <logic:notEqual name="docketEvent" property="recType" value="COURT">
            					  	<td></td>
            					  </logic:notEqual>
            					  <td><span title="<bean:write name="docketEvent" property="courtResultDesc"/>"><bean:write name="docketEvent" property="courtResult"/></span></td>
            					  <logic:equal name="docketEvent" property="recType" value="COURT"> 
            					  	<td><span title="<bean:write name="docketEvent" property="dispositionDesc"/>"><bean:write name="docketEvent" property="disposition"/></span></td>
            					  </logic:equal>
            					  <logic:notEqual name="docketEvent" property="recType" value="COURT">
            					  	<td></td>
            					  </logic:notEqual>
            					  <td><bean:write name="docketEvent" property="attorneyName"/></td>
            					</tr>
            				</logic:iterate>
            			  </table>
            			</td>
          		  </tr>
          		  <%-- </logic:equal> --%>
          		</table>
          		<!-- END Dockts TABLE -->
        	  </logic:notEmpty>
	 
            <%-- Begin Pagination navigation Row--%>
      			<table align="center">
      				<tr>
      					<td>
      						<pg:index>
      							<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
      								<tiles:put name="pagerUniqueName" value="pagerSearch"/>
      								<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
      							</tiles:insert>
      						</pg:index>
      					</td>
      				</tr>
      			</table>						
            <%-- End Pagination navigation Row--%>	  

            <%-- BEGIN BUTTON TABLE --%>
            <table width="100%" border='0'>  
              <tr>
                <td align='center'>
                  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
                  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                </td>
              </tr>
            </table>
            <%-- END BUTTON TABLE --%>

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

<div class='spacer'></div>


</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>