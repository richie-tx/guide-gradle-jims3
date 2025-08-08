<!DOCTYPE HTML>
<!------------------MODIFICATIONS ---------------------------->
<!-- March 2007 - daw - create jsp -->
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
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarJuvenileEventList.jsp</title>

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
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
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
            <td class="detailHead"><bean:message key="prompt.calendar" />&nbsp;<bean:message key="prompt.events" /></td>
          </tr>
          <tr>
            <td>
              <table width='100%' cellpadding="2" cellspacing="1" align="center">
          			<logic:empty name="calendarEventListForm" property="individualDayEvents">
          				<tr> 
          					<td colspan="6" align="left">No Calendar Events Available</td> 
          				</tr> 
          			</logic:empty>
  
      	        <logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
  
	            		<tr bgcolor='#cccccc' class='subHead'>
 	                	<td width="13%" align="left">
 	                		<bean:message key="prompt.dateTime" />
	            				<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
	            			</td>
	            			<td width="20%">
	            				<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
	          						<bean:message key="prompt.instructorName" />    
	 	                			<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="instructorName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2"  />
	          					</logic:notEmpty>
	          					<logic:empty name="calendarEventListForm" property="serviceProviderId">
	          						<bean:message key="prompt.juvenileName" />    
	 	                			<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="juvenileLastName" primarySortType="STRING" secondPropSort="juvenileFirstName" secondarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2"  />
	          					</logic:empty>
	            			</td>
 	                	<td width="10%">
 	                		<bean:message key="prompt.locationUnit" />&nbsp;<bean:message key="prompt.id" />
 	                		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="finalLocation" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3"  />
					  				</td>
 	              		<td width="12%">
 	              			<bean:message key="prompt.eventType" />
	            				<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventType" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4"  />
	            			</td>
 	                	<td width="15%">
 	                		<bean:message key="prompt.serviceName" />
	            				<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5"  />
	            			</td>
	            			<td width="18%">
	            				<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
		        					<bean:message key="prompt.programName" />    
		                			<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="programName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="6"  />
	          					</logic:notEmpty>
	          					<logic:empty name="calendarEventListForm" property="serviceProviderId">
	          						<bean:message key="prompt.serviceProviderName" /> 
	            					<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="6"  />
	          					</logic:empty>
	            			</td>          				            				
	            			<td width="5%">
	            				<bean:message key="prompt.status" />
	            				<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventStatusCode" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="7"  />
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
		           							<bean:write name="eventsForDay" property="juvenileAttendanceStatus"/>
                   				</logic:notEmpty>
	  
			  									<%-- otherwise this is a non-prescheduled event type, pass the juvenile name in the query string --%>
                 		  		<logic:empty name="eventsForDay" property="serviceName">
		           							<a href="/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventsForDay" property='eventId'/>&jname=<bean:write name='eventsForDay' property='juvenileLastName' />:<bean:write name='eventsForDay' property='juvenileFirstName' />:<bean:write name='eventsForDay' property='juvenileMiddleName' />">
		           							<bean:write name="eventsForDay" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
					 									<bean:write name="eventsForDay" property="juvenileAttendanceStatus"/>                  				
                   				</logic:empty>
		         						</td>
	          								
		         						<td>
	           							<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
		         								<bean:write name="eventsForDay" property="instructorName"/>
		         							</logic:notEmpty>
	          									
		         							<logic:empty name="calendarEventListForm" property="serviceProviderId">
														<logic:notEmpty name="eventsForDay" property="juvenileLastName">
		       										<bean:write name="eventsForDay" property="juvenileLastName" />, <bean:write name="eventsForDay" property="juvenileFirstName" /> <bean:write name="eventsForDay" property="juvenileMiddleName" />
                           			<%-- 28may2008 - mjt - appended as a part of the URL, 
            	            				 the expression, "&rrand=<%out.print((Math.random()*246));,
                	        		 		 is a hack used to generate a wild random number - this keeps
                    	    		 		 the browser from going to the cache to get an already-generated PDF
 	                     				 	--%>
			  											<logic:notEmpty name="eventsForDay" property="juvenileNum">
		         										<a href="/<msp:webapp/>handleViewCalendar.do?submitAction=<bean:message key="button.generateUpdatedSchedule"/>&selectedValue=<bean:write name="eventsForDay" property='juvenileNum'/>&rrand=<%out.print((Math.random()*246));%>">
		         										<img src="/<msp:webapp/>images/popup.gif" alt="Select For Future Events" title="Select For Future Events" border='0'></a>
			  											</logic:notEmpty>
		       									</logic:notEmpty>
		          						</logic:empty>
		         						</td>
	          								
		       							<td>
			       							<logic:equal name="eventsForDay" property="eventType" value="JOB VISIT">
			       								<bean:write name="eventsForDay" property="location"/>
			       							</logic:equal>
			       							<logic:notEqual name="eventsForDay" property="eventType" value="JOB VISIT">
			       								<bean:write name="eventsForDay" property="finalLocation"/>
			       							</logic:notEqual>
		       							</td>
		       							<td><bean:write name="eventsForDay" property="eventType"/></td>
		       							<td><bean:write name="eventsForDay" property="serviceName"/></td>
		      							<td>
		      								<logic:notEmpty name="calendarEventListForm" property="serviceProviderId">
		      									<bean:write name="eventsForDay" property="programName"/>
		      								</logic:notEmpty>
			
								   				<logic:empty name="calendarEventListForm" property="serviceProviderId">
		      									<bean:write name="eventsForDay" property="serviceProviderName"/>
													</logic:empty>
			   								</td>	
		       							<td>		       								
		       								<span title="<bean:write name="eventsForDay" property="eventStatus"/>">
		       									<bean:write name="eventsForDay" property="eventStatusCode"/>
		       								</span>
		       							</td>
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
  
					<logic:empty name="calendarEventListForm" property="serviceProviderId">
		        <logic:notEqual name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.CALENDAR_SEARCH%>">	
		  
		    		  <logic:notEmpty name="calendarEventListForm" property="docketEvents">
		            <bean:size id="docsize" name="calendarEventListForm" property="docketEvents" />
		            <bean:write name="docsize" /> docket event(s) found.
		    		  </logic:notEmpty>
		
		          <logic:greaterEqual name="docsize" value='2' >
	            	<bean:define id="docketScrollingRequired" value="true" />
		          </logic:greaterEqual>
		      	  
		  				<!-- BEGIN Dockets TABLE -->
		      		<div class='spacer'></div>
		      		<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">		  
		      		  <tr>
		        			<td class="detailHead">Docket Events</td>
		      		  </tr>
		      
		      		  <logic:empty name="calendarEventListForm" property="docketEvents">
		              <tr><td colspan="6">No Docket Events Available</td></tr> 
		      		  </logic:empty>	
		  
		      		  <logic:notEmpty name="calendarEventListForm" property="docketEvents">
		        		  <tr>
		          			<td>
		  					<div class="scrollingDiv200">

		          			  <table width='100%' cellpadding="2" cellspacing="1" align="center">
		            				<tr class="formDeLabel" >
		            				  <td nowrap='nowrap'><bean:message key="prompt.time" />
		          				  		<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="eventDateWithTime" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="11"  />
		            				  </td>		
		            				   <td><bean:message key="prompt.juvenileName" />
		          							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="juvenileName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="20"  />	
		            				  </td>		  
		            				  <td nowrap='nowrap'><bean:message key="prompt.petition#" />
		          							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="12"  />	
		            				  </td>
		            				  <td nowrap='nowrap'><bean:message key="prompt.court" />
		          							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="court" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="13"  />	
		            				  </td>
		            				  <td nowrap='nowrap'><bean:message key="prompt.allegation" />
		           							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="allegation" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="14"  />
		            				  </td>
		            				  <td><bean:message key="prompt.courtResult" />
		          							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="courtResult" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="15"  />
		            				  </td>
		            				  <td><bean:message key="prompt.courtDisposition" />
		          							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="disposition" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="16"  />
		            				  </td>
		            				</tr>
		            				
		            				<logic:iterate id="docketEvent" name="calendarEventListForm" property="docketEvents" indexId="index">	  
	      								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
	            					  <td>
	              					  <a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key="button.docketEventDetails"/>&eventType=<bean:write name="docketEvent" property="docketType"/>&eventId=<bean:write name="docketEvent" property="docketEventId"/>',340,640)">					  
                						<bean:write name="docketEvent" property="eventDateWithTime" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
	            					  </td>	
	            					  <td>															  
	            						  <logic:notEmpty name="docketEvent" property="juvenileName">
	              						  <bean:write name="docketEvent" property="juvenileName"/>	
	         										<a href="/<msp:webapp/>handleViewCalendar.do?submitAction=<bean:message key="button.generateUpdatedSchedule"/>&selectedValue=<bean:write name="docketEvent" property='juvenileNumber'/>">
	         										<img src="/<msp:webapp/>images/popup.gif" alt="Select For Future Events" title="Select For Future Events" border='0'></a>
	        									</logic:notEmpty>					
	            					  </td>				  
	            					  <td><bean:write name="docketEvent" property="petitionNumber"/></td>
	            					  <td><bean:write name="docketEvent" property="court"/></td>
	            					  <td><bean:write name="docketEvent" property="allegation"/></td>
	            					  <td><bean:write name="docketEvent" property="courtResult"/></td>
	            					  <td><bean:write name="docketEvent" property="disposition"/></td>
	            					</tr>
		            				</logic:iterate>
		          			  </table>
		  
			  				</div>
		          			</td>
		        		  </tr>		  
		         	  </logic:notEmpty>
		       		</table>
		       		<!-- END Dockets TABLE -->
		        </logic:notEqual>
	        </logic:empty>
	
	      </logic:notEqual>
				<%-- not a docket search --%>  			

				<%-- we've made the docket search type in its own section
				     so that we can offer struts paging. 
				 --%>  			
				<logic:empty name="calendarEventListForm" property="serviceProviderId">
		      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.DOCKET_SEARCH%>">	
						<!-- BEGIN Dockets TABLE -->
		    		<div class='spacer'></div>
		    		<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">		  
		    		  <tr>
		      			<td class="detailHead">Docket Events</td>
		    		  </tr>
		    
		    		  <logic:empty name="calendarEventListForm" property="docketEvents">
		            <tr><td colspan="6" align="left">No Docket Events Available</td></tr> 
		    		  </logic:empty>	
		
		    		  <logic:notEmpty name="calendarEventListForm" property="docketEvents">
		      		  <tr>
		        			<td>
		        			  <table width='100%' cellpadding="2" cellspacing="1" align="center">
		          				<tr class="formDeLabel" >
		          				  <td nowrap='nowrap'><bean:message key="prompt.date" />/<bean:message key="prompt.time" />
		        				  		<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="eventDateWithTime" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="11"  />
		          				  </td>		
		          				   <td><bean:message key="prompt.juvenileName" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="juvenileName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="20"  />	
		          				  </td>	
		          				  <td><bean:message key="prompt.hearingType" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="hearingType" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="20"  />	
		          				  </td>	 
		          				  <td nowrap='nowrap'><bean:message key="prompt.court" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="court" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="13"  />	
		          				  </td> 
		          				  <td nowrap='nowrap'><bean:message key="prompt.petition#" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="12"  />	
		          				  </td>		          				  
		          				  <td nowrap='nowrap'><bean:message key="prompt.allegation" />
		         							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="allegation" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="14"  />
		          				  </td>
		          				  <td><bean:message key="prompt.courtResult" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="courtResult" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="15"  />
		          				  </td>
		          				  <td><bean:message key="prompt.courtDisposition" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="disposition" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="16"  />
		          				  </td>
		          				  <td><bean:message key="prompt.attorneyName" />
		        							<jims2:sortResults beanName="calendarEventListForm" results="docketEvents" primaryPropSort="attorneyName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="16"  />
		          				  </td>
		          				</tr>
		
		          				<logic:iterate id="docketEvent" name="calendarEventListForm" property="docketEvents" indexId="index">	  
		       							<pg:item>
		      								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%"> 
		            					  <td>
		              					  <a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayViewCalendarDetails.do?submitAction=<bean:message key="button.docketEventDetails"/>&eventType=<bean:write name="docketEvent" property="docketType"/>&eventId=<bean:write name="docketEvent" property="docketEventId"/>',340,640)">					  
		                						<bean:write name="docketEvent" property="eventDateWithTime" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
		            					  </td>	
		            					  <td>															  
		            						  <logic:notEmpty name="docketEvent" property="juvenileName">
		              						  <bean:write name="docketEvent" property="juvenileName"/>	
		         										<a href="/<msp:webapp/>handleViewCalendar.do?submitAction=<bean:message key="button.generateUpdatedSchedule"/>&selectedValue=<bean:write name="docketEvent" property='juvenileNumber'/>">
		         										<img src="/<msp:webapp/>images/popup.gif" alt="Select For Future Events" title="Select For Future Events" border='0'></a>
		        									</logic:notEmpty>					
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
		       							</pg:item>
		          				</logic:iterate>
		        			  </table>
		
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
		
		        			</td>
		      		  </tr>		  
		       	  </logic:notEmpty>
		     		</table>
		     		<!-- END Dockets TABLE -->
		      </logic:equal>
		  </logic:empty>   
		  <%-- no service provider ID  --%>
						
      <!-- BEGIN BUTTON TABLE -->
      <div class='spacer'></div>
    	<table width="100%">
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

