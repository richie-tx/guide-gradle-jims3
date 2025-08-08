<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 12/2006	MTobler		Create jsp --%>
<%-- 7/17/2007	LDeen		Defect #43737 correcting validation messages --%>
<%-- 10/17/2007	CShimek		Defect #46099 corrected address validation button to remain on this page and reformatted address Information block to standard layout --%>
<%-- 05/20/2010	LDeen	    Activity #65570 revised entry date field to force user to use calendar --%>
<%-- 12/14/2011	CShimek		Defect #72173 revised comment validation from 255 to 350 to match auto input truncation length --%>
<%-- 10/24/2012 DGibler		#73746 MJCW: Add Street Number suffix field --%>
<%-- 01/30/2013	CShimek		Made minor display changes to comments and recurring event tables to match changes made to calendarScheduleNewNonInterview.jsp --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="ui.common.UIUtil" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarScheduleNewInterviewEvent.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<html:base />


<html:javascript formName="scheduleCalendarEvent"/>



</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>
<%
String sServiceTypeCode = request.getParameter("currentService.serviceTypeCode");
%>
<script type="text/javascript">
var serviceTypeCategory = "";
<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">
	serviceTypeCategory = "I";
</logic:equal>
<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>"> 
	serviceTypeCategory = "P"
</logic:equal>	
<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>"> 
	serviceTypeCategory = "N"
</logic:equal>	
var serviceTypeCode = '<%=sServiceTypeCode%>'; 
</script>
<html:form action="/handleScheduleEvent" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|142"> 
<input type="hidden" name="uniqueId" value=""/>

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.scheduleNewCalendarEvent" /></td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END Heading TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
<logic:notEqual name="scheduleNewEventForm" property="action" value="displayDetails">
  <tr>
    <td>
      <ul>
        <li>Select an <b>Event Type</b> from the dropdown selection list.</li>
      </ul>
		</td>
  </tr>		
</logic:notEqual>		
<logic:equal name="scheduleNewEventForm" property="action" value="displayDetails">  						
  <tr>
    <td>
      <ul>
        <li>Provide the information required in the Interview Event Details section.</li>
        <li>Enter date through the calendar icon.</li>
        <li>If event recurs, select Yes to <b>Recurring Event?</b> and define the Pattern and Range, if applicable.</li>
        <li>Select the Next button to view the Events Conflicts and Details page.</li>
      </ul>
     </td>
  </tr>
</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END Casefile Header TABLE --%>



<%-- BEGIN Detail TABLE --%>
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

			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
				  <td>		  
						<%-- this table houses the drop down that controls which data tables get displayed. 
							 the various tables that follow are hidden and are shown depending upon the item chosen here 
						--%>
						<div class='spacer'></div>
				    <table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">      
							<tr> <%-- table titlebar --%>
								<td class="detailHead" colspan='2'><bean:message key="prompt.eventType" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.select" /></td>
								<td class="formDe">
									<html:select  name="scheduleNewEventForm" property="currentService.serviceTypeCode" styleId="curSerServiceTypeCode">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeCode" label="description" />					
									</html:select>
								</td>               
							</tr>
			      </table>
					</td>
			  </tr>

<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.INTERVIEW_SERVICE_TYPE%>">										
 		<%-- interview type table ... shown when an interview type is selected above --%>
		<tr>
			<td bgcolor="#ffffff"><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
		</tr>			   
    <tr id='interviewTable'>
      <td>
			  
        <table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue"> <%-- Interview Event table --%> 		   
				  <tr>
            <td class="detailHead" colspan='4'><bean:message key="prompt.interview" />&nbsp;<bean:message key="prompt.eventDetails" /></td>
          </tr>
				  <tr>
	          <td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.interviewDate" /></td>
						<td class='formDe'>
							<html:text styleId="entryDate" name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr" size="10" maxlength="10" readonly="true" />
						</td> 								
           </tr>
				  
          <tr>
            <td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.interviewTime" /></td>
            <td class="formDe">
              <html:select property="currentService.currentEvent.eventTime">
    						<html:option key="select.generic" value="" />
    						<html:optionsCollection name="scheduleNewEventForm" property="currentService.currentEvent.workDays" value="description" label="description" />
     				  </html:select>
   				  </td>	
          </tr>

          <tr>
            <td class='formDeLabel' nowrap='nowrap' valign='top'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.personsInterviewed" /></td> 
            <td class='formDe' colspan="3">
              <html:select name="scheduleNewEventForm" property="currentService.selectedIntervieweeArray" size="5" multiple="true">
                <html:option value=""><bean:message key="select.generic" /></html:option>
                <logic:iterate id="intervieweeIter" name="scheduleNewEventForm" property="currentService.intervieweeList">
        					<option value="<bean:write name='intervieweeIter' property='formattedName'/>">
        						<bean:write name="intervieweeIter" property="formattedName"/>
        						<logic:notEmpty name="intervieweeIter" property="relationship">
        							(<bean:write name="intervieweeIter" property="relationship"/>)
        						</logic:notEmpty>
        					</option>
        				</logic:iterate>
              </html:select>
            </td> 
          </tr> 

          <tr>
            <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.interviewType" /></td> 
            <td class='formDe' colspan='3'> 
               <html:select name="scheduleNewEventForm" property="currentService.interviewTypeId" >
                <html:option value=""><bean:message key="select.generic" /></html:option>
                <html:optionsCollection name="scheduleNewEventForm" property="currentService.interviewTypeList" value="code" label="description" />
              </html:select> 
            </td>
          </tr>
	 
  			<tr> 
  	        <td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.interviewLocation" /></td> 
  	        <td class='formDe' colspan='3'> 
  	          <html:select name="scheduleNewEventForm" property="currentService.locationId" styleId="serviceLocationId">
  	            <html:option value=""><bean:message key="select.generic" /></html:option>
  	            <html:optionsCollection name="scheduleNewEventForm" property="currentService.locationsList" value="juvLocationUnitId" label="locationUnitName" />
  							<option value="<%=UIConstants.USER_ENTERED_CUSTOM_ADDRESS%>"
									<logic:equal name="scheduleNewEventForm" property="currentService.locationId" value="<%=UIConstants.USER_ENTERED_CUSTOM_ADDRESS%>">
										selected
									</logic:equal>
  							>
								Enter a new address
							</option>
              </html:select>
            </td> 
          </tr> 
  				<tr>
  					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength"/></td>
            		<td class="formDe"><html:select styleId="sessionLength" name="scheduleNewEventForm" property="currentService.currentEvent.sessionLength" size="1" >
        				<html:option value="">Please Select</html:option>
        					<jims2:codetable codeTableName="<%=PDCodeTableConstants.SESSION_LENGTH_INTERVAL%>" />
        				</html:select>
					</td>	
  				</tr>

					<%-- BEGIN ADDRESS INFORMATION SECTION --%>
					<tr id="newAddressSection" class='hidden'>
						<td colspan='4'>
							<table border='0' width='100%' cellpadding='0' cellspacing='0'>
								<tr class='detailHead' id="newAddressHeader">
									<td colspan="4">
										<table width="100%">
											<tr>
												<td class='detailHead' colspan="1"><bean:message key="prompt.addressInfo" /></td>
												<td align="right" class="detailHead"> 
													<input type='button' value="<bean:message key='button.validate' />" name="submitAction" onClick="return validateAddrAction('scheduleNewEventForm','/<msp:webapp/>validateCalendarInterviewAddress.do','', 'currentService.newAddress','/jsp/caseTabCalendar/calendarScheduleNewInterviewEvent.jsp', true);"></input>
													<html:button property="org.apache.struts.taglib.html.BUTTON" 
													 onclick="return displayResearchWindow();">
													  <bean:message key="button.research"></bean:message>
													</html:button>	
												</td>
												<td class="detailHead" nowrap='nowrap'><bean:message key="prompt.addressStatus"/>:</td>
												<td class="errorAlert">
													<%-- TODO replace logic tags with code table values --%>
													<logic:equal name="scheduleNewEventForm" property="addressStatus" value="">
														UNPROCESSED
												   </logic:equal>       	    
												   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="U">
														UNPROCESSED
												   </logic:equal>
												   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="Y">
														VALID
												   </logic:equal>
												   <logic:equal name="scheduleNewEventForm" property="addressStatus" value="N">
														INVALID
												   </logic:equal>
												</td>		  
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan='4'>
										<table width="100%" border="0" cellpadding='2' cellspacing='1'>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetNum" /></td>
												<td class='formDeLabel'><bean:message key="prompt.streetNum" />&nbsp;<bean:message key="prompt.suffix" /></td>
												<td class='formDeLabel' colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetName" /></td>
											</tr>
											<tr>
												<td class='formDe'><html:text size="10" maxlength="10" property="currentService.newAddress.streetNum" /></td>
												<td class='formDe'>
													<html:select property="currentService.newAddress.streetNumSuffixCode" size="1">
														<html:option key="select.generic" value="" />
														<html:optionsCollection property="currentService.streetNumSuffixList" value="code" label="description"/>
													</html:select>
												</td>
												<td class='formDe' colspan="2"><html:text size="30" maxlength="50" property="currentService.newAddress.streetName" /></td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.streetType" /></td>
												<td class='formDeLabel' colspan="2"><bean:message key="prompt.aptSuite" /></td>
											</tr>
											<tr>									
												<td class='formDe'><html:select property="currentService.newAddress.streetTypeCode">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="currentService.streetTypeList" value="code" label="description"/>				
												</html:select></td>
												<td class='formDe' colspan="2"><html:text size="10" maxlength="10" property="currentService.newAddress.aptNum" /></td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.city" /></td>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.state" /></td>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.zipCode" /></td>	
											</tr>
											<tr>												
												<td class='formDe'><html:text size="15" maxlength="25" property="currentService.newAddress.city" /></td>
												<td class='formDe'><html:select property="currentService.newAddress.stateCode" size="1" styleId="state1" onchange="javascript:enableCounty(this, 'county1')">
													<option value="" selected='selected'>Please Select</option>
													<html:optionsCollection property="currentService.stateList" value="code" label="description"/>				
												</html:select></td>
												<td class='formDe'><html:text size="5" maxlength="5" property="currentService.newAddress.zipCode" /> - <html:text size="4" maxlength="4" property="currentService.newAddress.additionalZipCode" /></td>								
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.addressType" /></td>
												<td class='formDeLabel' colspan="2"><bean:message key="prompt.county" /></td>
											</tr>
											<tr>	
												<td class='formDe'><html:select property="currentService.newAddress.addressTypeCode">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="currentService.addressTypeList" value="code" label="description"/>				
												</html:select></td>								
												<td colspan="2" class='formDe'>
												  <span class="hidden" id="emptySelect1"> 
													  <select disabled='disabled'>
															<option>Please Select</option>
														</select> 
													</span> 
													<span class="visible" id="county1Span">
													  <html:select property="currentService.newAddress.countyCode" styleId="county1">
    													<option value="" selected='selected'>Please Select</option>
    													<html:optionsCollection property="currentService.countyList" value="code" label="description"/>				
    												</html:select>
													</span>
												</td>								
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<%-- END ADDRESS INFORMATION SECTION --%>				  
	    
         </table> 
				 <%-- Interview Event table --%>
       </td>
     </tr>
</logic:equal>
	  
<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
			<%-- comments field --%>		
  		<tr><td bgcolor="#ffffff"><div class='spacer'></div></td></tr>			   
			<tr>
		    <td colspan='4'>
				  <div class='spacer'></div>
					<table align="center" width='98%' border='0' cellpadding="2" cellspacing="1" class="borderTableBlue">
						<tr>
							<td class='detailHead' nowrap='nowrap'><bean:message key="prompt.comments" />&nbsp;
      					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
      						<tiles:put name="tTextField" value="currentService.currentEvent.comments"/>
      						<tiles:put name="tSpellCount" value="spellBtn1" />
    						</tiles:insert>  
								(Max. characters allowed: 350)
              </td>
						</tr>
		        <tr>
							<td class='formDe'><html:textarea name="scheduleNewEventForm" property="currentService.currentEvent.comments" styleId="curSerCurEvntComments" style="width:100%" rows="3"/></td>
						</tr>
					</table>
			    </td>
			</tr>
			<%-- end comments field --%>

			
			<%-- recurring events section --%>
      <tr><td bgcolor='#ffffff'><div class='spacer'></div></td></tr>
			<tr>
				<td>
					<table width='100%'>
						<tr>
							<td colspan='2'>
								<table align="center" width='98.5%' cellpadding="2" cellspacing="1" class="borderTableGrey">
									<tr>
										<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.recurringEvent" />?</td>
										<td class="formDe" nowrap='nowrap'>
											<bean:message key="prompt.yes"/><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="true"></html:radio>
											<bean:message key="prompt.no"/><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="false"></html:radio>
										</td>
									</tr>
								</table>
							</td>
						</tr>
			
						<tr id='recurrenceTable' class='hidden'>
							<td colspan='2'>
								<%-- Recurrence Pattern inner table --%>
	      				<table align="center" width='98.5%' cellpadding="2" cellspacing="1" class="borderTableGrey">
	      					<tr>
		    						<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.recurrencePattern" /></td>
		    					</tr>

	      					<tr>
	      						<td nowrap='nowrap' class='formDe'>
									<html:select name="scheduleNewEventForm" property="currentService.currentEvent.recurrencePattern" styleId="recurrencePattern">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:option value="D"><bean:message key="prompt.daily" /></html:option>
										<html:option value="W"><bean:message key="prompt.weekly" /></html:option>
										<html:option value="M"><bean:message key="prompt.monthly" /></html:option>
										<html:option value="Y"><bean:message key="prompt.yearly" /></html:option>									
									</html:select>
      					    </td>
			      			</tr>
			      
	      					<tr id='dailyRow1' class='hidden'>
	      						<td nowrap='nowrap' align="left">							
									<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.dailyRadio" value="true" />
									Every&nbsp;<html:text name="scheduleNewEventForm" property="currentService.currentEvent.dailyRecurrenceInterval" size="3" maxlength="3"/>
									&nbsp;day(s).
								</td>
	      					</tr>

        					<tr id='dailyRow2' class='hidden'>
	      						<td class='formDe'>
									<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.dailyRadio" value="false"/>Every weekday
  								</td>
	      					</tr>
			      
	      					<tr id='weeklyRow1' class='hidden'>
	      						<td nowrap='nowrap' align="left">
											Recur every <html:text name="scheduleNewEventForm" property="currentService.currentEvent.weeklyRecurrenceInterval" size="3" maxlength="3"/>
											week(s) on:
										</td>
	      					</tr>
									
	      					<tr id='weeklyRow2' class='hidden'>
										<td nowrap='nowrap' class='formDe'>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="0"><bean:message key="prompt.sun" /></html:checkbox>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="1"><bean:message key="prompt.mon" /></html:checkbox>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="2"><bean:message key="prompt.tue" /></html:checkbox>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="3"><bean:message key="prompt.wed" /></html:checkbox>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="4"><bean:message key="prompt.thu" /></html:checkbox>
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="5"><bean:message key="prompt.fri" /></html:checkbox>											
											<html:checkbox name="scheduleNewEventForm" property="currentService.currentEvent.weeklyDay" value="6"><bean:message key="prompt.sat" /></html:checkbox>																						
										</td>
	      					</tr>
			      
	      					<tr id='monthlyRow1' class='hidden'>
	      						<td nowrap='nowrap' align="left">
											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRadio" value="true"/>
											Day&nbsp;
											<html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyDay" size="2" maxlength="2"/>
											&nbsp;of every&nbsp;
											<html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRecurrenceInterval1" size="2" maxlength="2"/>
											&nbsp;month(s).
										</td>
	      					</tr>
									
	      					<tr id='monthlyRow2' class='hidden'>
        						<td nowrap='nowrap' class='formDe'>
											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRadio" value="false" />
											The&nbsp;
											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.monthlyWeekNumber" >
												<html:option value="1"><bean:message key="prompt.first" /></html:option>					
												<html:option value="2"><bean:message key="prompt.second" /></html:option>
												<html:option value="3"><bean:message key="prompt.third" /></html:option>
												<html:option value="4"><bean:message key="prompt.fourth" /></html:option>
												<html:option value="5"><bean:message key="prompt.last" /></html:option>
											</html:select>						

											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.monthlyWeekDay" >
												<html:option value="0"><bean:message key="prompt.sunday" /></html:option>					
												<html:option value="1"><bean:message key="prompt.monday" /></html:option>
												<html:option value="2"><bean:message key="prompt.tuesday" /></html:option>
												<html:option value="3"><bean:message key="prompt.wednesday" /></html:option>
												<html:option value="4"><bean:message key="prompt.thursday" /></html:option>
												<html:option value="5"><bean:message key="prompt.friday" /></html:option>						
												<html:option value="6"><bean:message key="prompt.saturday" /></html:option>									
											</html:select>						
											of every 
											<html:text name="scheduleNewEventForm" property="currentService.currentEvent.monthlyRecurrenceInterval2" size="2" maxlength="2"/>
											&nbsp;month(s).
	      						</td>
	      					</tr>
			      
									<tr id='yearlyRow1' class='hidden'>
										<td nowrap='nowrap' align="left">
											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.yearlyRadio" value="true"/>
											Every&nbsp;
											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyMonthNumber" >
												<html:option value="0">
													<bean:message key="prompt.january" />
												</html:option>					
												<html:option value="1">
													<bean:message key="prompt.february" />
												</html:option>
												<html:option value="2">
													<bean:message key="prompt.march" />
												</html:option>
												<html:option value="3">
													<bean:message key="prompt.april" />
												</html:option>
												<html:option value="4">
													<bean:message key="prompt.may" />
												</html:option>						
												<html:option value="5">
													<bean:message key="prompt.june" />
												</html:option>									
												<html:option value="6">
													<bean:message key="prompt.july" />
												</html:option>
												<html:option value="7">
													<bean:message key="prompt.august" />
												</html:option>
												<html:option value="8">
													<bean:message key="prompt.september" />
												</html:option>
												<html:option value="9">
													<bean:message key="prompt.october" />
												</html:option>
												<html:option value="10">
													<bean:message key="prompt.november" />
												</html:option>
												<html:option value="11">
													<bean:message key="prompt.december" />
												</html:option>						
											</html:select>
											<html:text name="scheduleNewEventForm" property="currentService.currentEvent.yearlyDay" size="2" maxlength="2"/>                    
			      				</td>
			    				</tr>

		      				<tr id='yearlyRow2' class='hidden'>
		      					<td nowrap='nowrap' class='formDe'>
  											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.yearlyRadio" value="false"/>
  											Every&nbsp;The&nbsp;
  											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekNumber" >
  												<html:option value="1">
  													<bean:message key="prompt.first" />
  												</html:option>					
  												<html:option value="2">
  													<bean:message key="prompt.second" />
  												</html:option>
  												<html:option value="3">
  													<bean:message key="prompt.third" />
  												</html:option>
  												<html:option value="4">
  													<bean:message key="prompt.fourth" />
  												</html:option>
  												<html:option value="5">
  													<bean:message key="prompt.last" />
  												</html:option>
  											</html:select>	
											
								
											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekDay" >
												<html:option value="0">
													<bean:message key="prompt.sunday" />
												</html:option>					
												<html:option value="1">
													<bean:message key="prompt.monday" />
												</html:option>
												<html:option value="2">
													<bean:message key="prompt.tuesday" />
												</html:option>
												<html:option value="3">
													<bean:message key="prompt.wednesday" />
												</html:option>
												<html:option value="4">
													<bean:message key="prompt.thursday" />
												</html:option>
												<html:option value="5">
													<bean:message key="prompt.friday" />
												</html:option>						
												<html:option value="6">
													<bean:message key="prompt.saturday" />
												</html:option>									
											</html:select>
											&nbsp;	of  &nbsp;
								
											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyMonthNumber1" >
												<html:option value="0">
													<bean:message key="prompt.january" />
												</html:option>					
												<html:option value="1">
													<bean:message key="prompt.february" />
												</html:option>
												<html:option value="2">
													<bean:message key="prompt.march" />
												</html:option>
												<html:option value="3">
													<bean:message key="prompt.april" />
												</html:option>
												<html:option value="4">
													<bean:message key="prompt.may" />
												</html:option>						
												<html:option value="5">
													<bean:message key="prompt.june" />
												</html:option>									
												<html:option value="6">
													<bean:message key="prompt.july" />
												</html:option>
												<html:option value="7">
													<bean:message key="prompt.august" />
												</html:option>
												<html:option value="8">
													<bean:message key="prompt.september" />
												</html:option>
												<html:option value="9">
													<bean:message key="prompt.october" />
												</html:option>
												<html:option value="10">
													<bean:message key="prompt.november" />
												</html:option>
												<html:option value="11">
													<bean:message key="prompt.december" />
												</html:option>						
											</html:select>					
			      				</td>
			      			</tr>
			      					
			      			<tr id='rangeRow1' class='hidden'>
			      				<td class='formDeLabel'><bean:message key="prompt.2.diamond"/>Range of Recurrence</td>
			      			</tr>
									<tr id='rangeRow3' class='hidden'>
										<td class='formDe'>
											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.frequencyRadio" value="true"/>
											End after&nbsp;
											<html:text name="scheduleNewEventForm" property="currentService.currentEvent.recurrenceFrequency" size="2" maxlength="2"/>
											&nbsp;occurrences&nbsp;&nbsp;
										  
											<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.frequencyRadio" value="false"/>
											End by&nbsp;
											<html:text styleId="endDate" name="scheduleNewEventForm" property="currentService.currentEvent.recurrenceEndDate" size="10" maxlength="10"/>
											
										</td>
									</tr>											
								</table> <%-- Recurrence Pattern inner table --%>				
							</td>
						</tr>	
					</table>
				</td>
			</tr>

		 </logic:notEqual>
		 
				<tr>
					<td>
	          <%-- BEGIN BUTTON TABLE --%>
					  <div class='spacer'></div>
						<table width="100%"> 
						  <tr id='editButtons'>
	  						<td align="center">
	  						  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 

	  						  <logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
  	  							<html:submit property="submitAction" styleId="intValidateNext"><bean:message key="button.next" /></html:submit>
	  						  </logic:notEqual>			

	  						  <logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">				
  	  							<logic:notEmpty name="scheduleNewEventForm" property="preScheduledEvents">
  	  								<html:submit property="submitAction" styleId="intValidateNext"><bean:message key="button.next" /></html:submit>
  	  							</logic:notEmpty>
	  						  </logic:equal>			

	  						  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	  						</td>
						  </tr>
						</table>
	    			<%-- END BUTTON TABLE --%>	

<%-- </logic:equal>  --%>

					</td>
				</tr>			
			</table>	
	  </td>
	</tr>			
</table>	
<%-- Begin Detail Table --%>

<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<table width='100%'>
  <tr>
  	<td>
  	  <html:hidden property="validStreetNum" value="" />
	  <html:hidden property="validStreetName" value="" />
  	  <html:hidden property="validZipCode" value="" />
  	  <html:hidden property="validAddrNum" value="" />
   	  <html:hidden property="inputPage" value="" />
  	  <html:hidden property="currentAddressInd" value="" />
  	</td>
  </tr>	  
</table>
<%-- END HIDDEN FIELDS FOR ADDRESS VALIDATION --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

