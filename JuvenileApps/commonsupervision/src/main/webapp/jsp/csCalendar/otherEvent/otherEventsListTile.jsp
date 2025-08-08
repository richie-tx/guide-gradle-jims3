
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page import="naming.Features" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript">
	function showButtons(status, outcome){
		if(outcome=="RE") {
			show("buttonsRow", 0, "row")
		} else {
			show("buttonsRow", 1, "row")
			
			if (status=="O"){
				show("rescheduleButton", 1, "inline" )
				show("resultsButton", 1, "inline" )
			}else {
				show("rescheduleButton", 0)
				show("resultsButton", 0)
			}
		}
		
	}

	function checkForm()
	{
	 	
		var myOption = (-1);

		if( document.getElementsByName("selectedEventId").length >= 1 )
		{
			for( i = 0; i < document.getElementsByName("selectedEventId").length; i++ )
			{
				if( document.getElementsByName("selectedEventId")[ i ].checked )
				{
					myOption = i;
				}
			}
		}
		if(myOption == (-1)){
			alert("Please Select an Event");
			return false;
			}
		else
			return true;	
	}

</script>
						
<tiles:importAttribute name="csCalendarOtherForm"/>											
		<%-- Begin Pagination Header Tag--%>
		<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
		<pg:pager
			index="center"
			maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			maxIndexPages="10"
			export="offset,currentPageNumber=pageNumber"
			scope="request">
			<input type="hidden" name="pager.offset" value="<%= offset %>">
		<%-- End Pagination header stuff --%>
		
		<!-- BEGIN DETAIL TABLE -->
		<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr class="detailHead" >
				<td >Other Events List for 
					<bean:write name="csCalendarOtherForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
				</td>
				<td align=right >
				<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
				<a href="/<msp:webapp/>handleCSOtherEventSelection.do?submitAction=Link">Enter Results For Multiple Events</a>
				</logic:notEqual>
				</td>
			</tr>
			  <tr>		
			  	<td colspan=2>		
					<table width='100%' cellpadding="2" cellspacing="1" class="notFirstColumn" id="unique_id">
						<tr class="formDeLabel">
							<td width=1%><logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults"> <input type=checkbox name=checkAllOtherEvents onclick="toggleCheckAll(this, 'selectedEventId')" title="Check/Uncheck All"></logic:equal></td>
							<td nowrap>Event Type
								<jims2:sortResults beanName="csCalendarOtherForm" results="events" primaryPropSort="eventTypeDesc" primarySortType="STRING" sortId="1" levelDeep="3"/></td>
							<td nowrap>Event Name
								<jims2:sortResults beanName="csCalendarOtherForm" results="events" primaryPropSort="eventName" primarySortType="STRING" sortId="2" levelDeep="3"/></td>
							<td nowrap>Time
								<jims2:sortResults beanName="csCalendarOtherForm" results="events" primaryPropSort="startTime" primarySortType="TIME_AM_PM" defaultSort="true" defaultSortOrder="ASC"  sortId="3" levelDeep="3"/></td>
							<td nowrap>Supervisee						
								<jims2:sortResults beanName="csCalendarOtherForm" results="events" primaryPropSort="sortLastName" primarySortType="STRING" sortId="4" levelDeep="3"/></td>
						
							<td nowrap>Outcome
								<jims2:sortResults beanName="csCalendarOtherForm" results="events" primaryPropSort="outcomeDesc" primarySortType="STRING" sortId="5" levelDeep="3"/></td>
						</tr>
						
						<%
						int RecordCounter = 0; 
						String bgcolor = ""; 
						RecordCounter = 0; 
						bgcolor = ""; %> 
						
						<!-- Note: Polygraph is a different flow -->
						<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
						<logic:notEmpty name="csCalendarOtherForm" property="events">
					
							<logic:iterate id="eventsIter" name="csCalendarOtherForm" property="events">
								<pg:item>
								<tr class= <% RecordCounter++;
									bgcolor = "alternateRow";                      
									if (RecordCounter % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);  %>  >
									<bean:define id="eventId" name="eventsIter" property="eventId"/>
									<td>
									<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
										<input type="radio" name="selectedEventId" 
											value="<bean:write name='eventsIter' property='eventId'/>"
											onclick="showButtons('<bean:write name='eventsIter' property='status'/>', '<bean:write name='eventsIter' property='outcome'/>')">
									</logic:notEqual>
									<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
									<input type="checkbox" name="selectedEventId" 
											value="<bean:write name='eventsIter' property='eventId'/>"
											onclick="">
									</logic:equal>
									</td>
									<td>
										<a href="/<msp:webapp/>handleCSOtherEventSelection.do?submitAction=View&eventId=<bean:write name='eventsIter' property='eventId'/>">
										
											<bean:write name="eventsIter" property="eventTypeDesc"/>
										
										
										</a>
									</td>
									<td><bean:write name="eventsIter" property="eventName"/></td>
									<td><bean:write name="eventsIter" property="startTime"/> - <bean:write name="eventsIter" property="endTime"/></td>
									<td>
								 
										<logic:notEmpty name="eventsIter" property="partyEvent">
											<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name='eventsIter' property='superviseeId'/>">
										   <bean:write name="eventsIter" property="partyEvent.lastName"/>, 
											<bean:write name="eventsIter" property="partyEvent.middleName"/> 
											<bean:write name="eventsIter" property="partyEvent.firstName"/>
											</a>
										</logic:notEmpty>	
									</td>
									<td>
										<logic:empty name="eventsIter" property="outcome">
											SCHEDULED
										</logic:empty>
										<logic:notEmpty name="eventsIter" property="outcome">
											<bean:write name="eventsIter" property="outcomeDesc"/>
										</logic:notEmpty>
									</td>
								</tr>
								</pg:item>
							</logic:iterate>
						</logic:notEmpty>
					</logic:notEqual>	
					<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
						<logic:notEmpty name="csCalendarOtherForm" property="filteredEvents">
					
							<logic:iterate id="eventsIter" name="csCalendarOtherForm" property="filteredEvents">
								<pg:item>
								<tr class= <% RecordCounter++;
									bgcolor = "alternateRow";                      
									if (RecordCounter % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);  %>  >
									<bean:define id="eventId" name="eventsIter" property="eventId"/>
									<td>
									<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
										<input type="radio" name="selectedEventId" 
											value="<bean:write name='eventsIter' property='eventId'/>"
											onclick="showButtons('<bean:write name='eventsIter' property='status'/>', '<bean:write name='eventsIter' property='outcome'/>')">
									</logic:notEqual>
									<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
									<input type="checkbox" name="selectedEventId" 
											value="<bean:write name='eventsIter' property='eventId'/>"
											onclick="">
									</logic:equal>
									</td>
									<td>
										<a href="/<msp:webapp/>handleCSOtherEventSelection.do?submitAction=View&eventId=<bean:write name='eventsIter' property='eventId'/>">
										
											<bean:write name="eventsIter" property="eventTypeDesc"/>
										
										
										</a>
									</td>
									<td><bean:write name="eventsIter" property="eventName"/></td>
									<td><bean:write name="eventsIter" property="startTime"/> - <bean:write name="eventsIter" property="endTime"/></td>
									<td>
								 
										<logic:notEmpty name="eventsIter" property="partyEvent">
											<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name='eventsIter' property='superviseeId'/>">
										   <bean:write name="eventsIter" property="partyEvent.lastName"/>, 
											<bean:write name="eventsIter" property="partyEvent.middleName"/> 
											<bean:write name="eventsIter" property="partyEvent.firstName"/>
											</a>
										</logic:notEmpty>	
									</td>
									<td>
										<logic:empty name="eventsIter" property="outcome">
											SCHEDULED
										</logic:empty>
										<logic:notEmpty name="eventsIter" property="outcome">
											<bean:write name="eventsIter" property="outcomeDesc"/>
										</logic:notEmpty>
									</td>
								</tr>
								</pg:item>
							</logic:iterate>
						</logic:notEmpty>
					</logic:equal>				
					</table>
					
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
				  
				</td>
			</tr>
		</table>
		<!-- End DETAIL TABLE -->
		
		<!-- BEGIN BUTTON TABLE -->
		<table border="0" width="100%">
			<tr id=buttonsRow class=hidden>
				<td align="center">
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_UPDATE%>'>
					<html:submit property="submitAction"><bean:message key="button.update" /></html:submit>
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OTR_CREATE%>'>
					<html:submit property="submitAction" styleId="resultsButton"><bean:message key="button.enterResults" /></html:submit>
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_RESCHEDULE%>'>
					<html:submit property="submitAction" styleId="rescheduleButton" styleClass="hidden"><bean:message key="button.reschedule" /></html:submit>
					</jims2:isAllowed>
				</td>
			</tr>
			<tr>
				<td align="center">
					<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_CREATE%>'>
					<input type="submit" value="<bean:message key='button.addNewEvent'/>" name="submitAction" 
						onclick="changeFormActionURL(this.form, 
							'/<msp:webapp/>displayAddCSEvent.do',false);"  >
					</jims2:isAllowed>
					</logic:notEqual>
					<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OTR_CREATE%>'>
						<input type="submit" value="<bean:message key='button.enterResults'/>" name="submitAction" id="enterResultsButton" onclick="return checkForm() && changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventMultipleResults.do?submitAction=Link',false);" >
					</jims2:isAllowed>	
					</logic:equal>
					<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
				</td>
			</tr>
		</table>
		<!-- END BUTTON TABLE -->
		<!-- END DETAIL TABLE -->								
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

