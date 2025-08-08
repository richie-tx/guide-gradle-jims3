<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 12/2006	MTobler		Create jsp --%>
<%-- 7/17/2007	LDeen		Defect #43737 correcting validation messages --%>
<%-- 10/26/2007	CShimek     Defect #46480 corrected date validation messages form interview to event --%>
<%-- 05/20/2010	LDeen	    Activity #65570 revised entry date field to force user to use calendar --%>
<%-- 10/24/2012 DGibler		#73746 MJCW: Add Street Number suffix field --%>
<%-- 12/28/2012 CShimek		#74832 Added Sex Offender to School Adjudication --%>

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

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarScheduleNewNonInterviewEvent.jsp</title>
<html:base />

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

<html:javascript formName="scheduleCalendarEvent"/>


</head>
<html:base />
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
	<tr>
		<td>
			<ul>
				<li>Provide the information required in the Event Details section.</li>
				<li>Enter date through the calendar icon.</li>
				<li>If event recurs, select Yes to <b>Recurring Event</b> and define the Pattern and Range, if applicable.</li>
				<li>Select the Next button to view the Summary page.</li>
				<li>Select the Spell Check icon for the Comments field to check spelling.</li>
			</ul>
		</td>    
	</tr>  
</table>
<div class="required">*All date fields must be entered via calendar.</div>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END Casefile Header TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAILS TABLE --%>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>
	  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="calendartab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert> 
<%-- BEGIN BLUE BORDER DETIALS TABLE --%>
			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>		  
						<%-- this table houses the drop down that controls which data tables get displayed. 
							 the various tables that follow are hidden and are shown depending upon the item chosen here 
						--%>
						<div class='spacer'></div>	
<%-- BEGIN EVENT SELECT TABLE --%>							  
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
<%-- END EVENT SELECT TABLE --%>							
					</td>
				</tr>		

				<logic:equal name="scheduleNewEventForm" property="action" value="displayDetails">
					<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.NONINTERVIEW_SERVICE_TYPE%>">	
						<tr>
							<td bgcolor="#ffffff"><div class='spacer'></div></td>
						</tr>			   
						<tr id='groupTable'>
							<td>
								<div class='spacer'></div>
<%-- BEGIN EVENT DETAILS INPUT TABLE --%>																
								<table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">  
									<tr>
										<td class="detailHead" colspan='2'><span id="eventDetails">Event Details</span></td>
									</tr>
									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">	
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventDate"/></td>
											<td class='formDe' nowrap='nowrap'>
												<html:text styleId="entryDate" name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr" size="10" maxlength="10" readonly="true" />
											</td> 
										</tr>
										</logic:notEqual>
									</logic:notEqual>
					
									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.JOB_VISIT%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
									  	<tr>			
											<td class='formDe' colspan='2'>
												<html:select name="scheduleNewEventForm" property="currentService.location" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.locationsList" value="locationId" label="locationName" />
												</html:select>
											</td>
										</tr>	
									</logic:equal>

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
									  	<tr>			
											<td class='formDe' colspan='2'>
												<html:select name="scheduleNewEventForm" property="currentService.memberAddressId" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.familyMemberLocations" value="memberAddressId" label="memberNameAddress" />
												</html:select>	
											</td>
										</tr>	
									</logic:equal>

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.HOME_VISIT%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
										<tr>			
											<td class='formDe' colspan='2'> 
												<html:select name="scheduleNewEventForm" property="currentService.memberAddressId" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.familyMemberLocations" value="memberAddressId" label="memberNameAddress" />
												</html:select>	
											</td>
										</tr>	
									</logic:equal>

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.CURFEW_CHECK%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
									  	<tr>			
											<td class='formDe' colspan='2'>
												<html:select name="scheduleNewEventForm" property="currentService.memberAddressId" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.familyMemberLocations" value="memberAddressId" label="memberNameAddress" />
												</html:select>	
											</td>
										</tr>	
									</logic:equal>				

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
									  	<tr>			
											<td class='formDe' colspan='2'>
												<html:select name="scheduleNewEventForm" property="currentService.memberAddressId" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.familyMemberLocations" value="memberAddressId" label="memberNameAddress" />
												</html:select>	
											</td>
										</tr>	
									</logic:equal>				

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PHONE_CURFEW_CHECK%>">				
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap' colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationMemberName"/></td>
										</tr>
									  	<tr>			
											<td class='formDe' colspan='2'>
												<html:select name="scheduleNewEventForm" property="currentService.memberAddressId" onchange="">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="scheduleNewEventForm" property="currentService.familyMemberLocations" value="memberAddressId" label="memberNameAddress" />
												</html:select>	
											</td>
										</tr>	
									</logic:equal>				

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_VISIT%>">	
										<tr id='schoolLocation'>								
											<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.schoolName" /></td>
											<td class='formDe' nowrap='nowrap'>
												<html:select name="scheduleNewEventForm" property="currentService.schoolId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="currentService.schools" value="schoolId" label="school"/>				
												</html:select>
											</td>								
										</tr>												
									</logic:equal>

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">	
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateOfContact"/></td>
											<td class='formDe' nowrap='nowrap'>
												<html:text styleId="entryDate" name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr" size="10" maxlength="10" readonly="true" />
											</td> 
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.contactTime"/></td>
							          		<td class="formDe">
								            	<html:select property="currentService.currentEvent.eventTime">
								  					<html:option key="select.generic" value="" />
								  					<html:optionsCollection name="scheduleNewEventForm" property="currentService.currentEvent.workDays" value="description" label="description" />
								  		  	  </html:select>
											</td>	
										</tr>
										<tr id='schoolLocation'>								
											<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.schoolName" /></td>
											<td class='formDe' nowrap='nowrap'>
												<html:select name="scheduleNewEventForm" property="currentService.schoolId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="currentService.schools" value="schoolId" label="school"/>				
												</html:select>
											</td>								
										</tr>	
										<tr>
											<td class="formDeLabel" valign='middle' nowrap='nowrap'><bean:message key="prompt.personContacted" /></td>
											<td valign='top' class='formDe'>
												<table border='0' cellspacing='1'>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
					  									<td class='formDe'><html:text property="currentService.contactLastName" size="40" maxlength="75" /></td>
					  								</tr>
					  								<tr>
					  									<td class='formDeLabel'><bean:message key="prompt.first" /></td>
														<td class='formDe'><html:text property="currentService.contactFirstName" size="25" maxlength="50" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.sexOffender"/> <bean:message key="prompt.registrant"/></td>
											<td class='formDe'>
												<bean:message key="prompt.yes"/><html:radio name="scheduleNewEventForm" property="currentService.sexOffenderRegistrant" value="true"></html:radio>
												<bean:message key="prompt.no"/><html:radio name="scheduleNewEventForm" property="currentService.sexOffenderRegistrant" value="false"></html:radio>
												<html:hidden property="currentService.sexOffenderRegistrantStr" value="" styleId="sexOffenderRegistrantStrId" />
											</td>
										</tr>
										<tr id="restrictionRow" class="hidden">
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.restrictionsOther"/></td>
											<td class='formDe'><html:text property="currentService.restrictionsOther" size="35" maxlength="35" styleId="restrictionId" /></td>
										</tr>
										<tr>
											<td class="detailHead" colspan="2"><bean:message key="prompt.offenses" /></td>
										</tr>
										<%  int iCtr = -1; %>
										<logic:iterate id="offs" name="scheduleNewEventForm" property="offenseInvolvedWeaponList" indexId="ctr">
											<tr>
												<td class="formDeLabel" colspan="2" align="left">
													(<bean:write name="offs" property="referralNumber" /> - <bean:write name="offs" property="offenseCategoryDescription" />) <bean:write name="offs" property="offenseDescription" />
												</td>
											</tr>
											<% iCtr++; %>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.weaponInvolved"/></td>
												<td class='formDe' data-idx="<%=ctr%>">
													<bean:message key="prompt.yes"/><html:radio name="offs" property="weaponInvolved" value="true" indexed="true" />
													<bean:message key="prompt.no"/><html:radio name="offs" property="weaponInvolved" value="false" indexed="true" />
												</td>
											</tr>
											<tr id="weaponRow<%out.print(iCtr); %>" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.typeOfWeapon"/></td>
												<td class='formDe' data-idx="<%=ctr%>">  
													<html:select name="offs" property="typeOfWeaponId" indexed="true">
														<html:option value=""><bean:message key="select.generic" /></html:option>  		
														<html:optionsCollection name="scheduleNewEventForm" property="typeOfWeapons" value="code" label="description" />	            	  	
													</html:select>
												</td>
											</tr>
											<tr id="weaponDescRow<%out.print(iCtr); %>" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.weaponDescription"/></td>  <%-- styleId="weaponDescId<%out.print(iCtr); %>" --%>
												<td class='formDe' colspan="3"><html:text name="offs" property="weaponDescription" size="55" maxlength="55" styleId='<%="weaponDescId"+ ctr %>' indexed="true"/></td>
											</tr>
											<html:hidden name="scheduleNewEventForm" property="weaponInvolvedStr" value=" "/>
											<html:hidden name="scheduleNewEventForm" property="typeOfWeaponId" value=" "/>
											<html:hidden name="scheduleNewEventForm" property="typeOfWeaponDescription" value=" "/>
											<html:hidden name="scheduleNewEventForm" property="weaponDescription" value=" "/>
										</logic:iterate>
									</logic:equal>
									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
									
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.controllingReferral"/></td>
											<td class='formDe' nowrap='nowrap'>
											 	 <html:select property="currentService.controllingReferral" name="scheduleNewEventForm" styleId="cntrlRef">
											          <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
											          <html:optionsCollection property="currentService.referrals" value="referralNumberWithPetition" label="referralNumberWithSeverity"  name="scheduleNewEventForm"/>
										    	   </html:select>
											
											</td> 
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventDate"/></td>
											<td class='formDe' nowrap='nowrap'>
												<html:text styleId="courtDate" name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr" size="10" maxlength="10" readonly="true" />
											</td> 
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventTime"/></td>
							          		<td class="formDe">
								            	<html:select property="currentService.currentEvent.eventTime" styleId="courtTime">
								  					<html:option key="select.generic" value="" />
								  					<html:optionsCollection name="scheduleNewEventForm" property="currentService.currentEvent.workDays" value="description" label="description" />
								  		  	  </html:select>
											</td>	
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/>My Office hours are</td>
											<td class='formDe' nowrap='nowrap'>
												<html:text styleId="officerHours" name="scheduleNewEventForm" property="officerHours" size="50" maxlength="50" />
											</td> 
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.phone"/></td>
											<td class="formDe" colspan="3">
											  <html:text name="scheduleNewEventForm" property="officerPhoneAreaCode" styleId="officerPhoneAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
												<html:text name="scheduleNewEventForm" property="officerPhonePrefix"  styleId="officerPhonePrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
													<html:text name="scheduleNewEventForm" property="officerPhoneMain"  styleId="officerPhoneMain" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" />
													<bean:message key="prompt.ext"/> <html:text name="scheduleNewEventForm" property="officerPhoneExtn" styleId="officerPhoneExtn" size="4" maxlength="6" onkeyup="return autoTab(this, 6);" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.fax"/></td>
											<td class="formDe" colspan="3">
											  <html:text name="scheduleNewEventForm" property="faxAreaCode" styleId="faxAreaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
												<html:text name="scheduleNewEventForm" property="faxPrefix" styleId="faxPrefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
													<html:text name="scheduleNewEventForm" property="faxMain" styleId="faxMain" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" />
											</td>
										</tr>
										
									</logic:equal>

									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.PLACEMENT_VISIT%>">
										<tr id='locationList'>	
											<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventLocation" /></td>
											<td class='formDe' nowrap='nowrap'>
												<html:select name="scheduleNewEventForm" property="currentService.facilityId">
													<html:option value=""><bean:message key="select.generic" /></html:option>  		
													<html:optionsCollection name="scheduleNewEventForm" property="activeFacilityList" value="code" label="description" />	            	  	
												</html:select>								
											</td>
										</tr>
									</logic:equal>
									<!-- Added for 12533 user story -->
									<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>">
										<tr id='locationList'>	
											<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventLocation" /></td>
											<td class='formDe' nowrap='nowrap'>
												<html:select name="scheduleNewEventForm" property="currentService.facilityId">
													<html:option value=""><bean:message key="select.generic" /></html:option>  		
													<html:optionsCollection name="scheduleNewEventForm" property="activeFacilityList" value="code" label="description" />	            	  	
												</html:select>								
											</td>
										</tr>
									</logic:equal>		
						
									<jims2:switch name="scheduleNewEventForm" property="currentService.serviceTypeCode">
										<jims2:case value="<%=UIConstants.PLACEMENT_VISIT%>" />
										<jims2:case value="<%=UIConstants.FACILITY_PARENT_ORIENTATION%>" />
										<jims2:case value="<%=UIConstants.JOB_VISIT%>" />
										<jims2:case value="<%=UIConstants.HOME_DIAGNOSTIC_VISIT%>" />
										<jims2:case value="<%=UIConstants.HOME_VISIT%>" />
										<jims2:case value="<%=UIConstants.CURFEW_CHECK%>" />
										<jims2:case value="<%=UIConstants.FACE_TO_FACE_CURFEW_CHECK%>" />
										<jims2:case value="<%=UIConstants.PHONE_CURFEW_CHECK%>" />
										<jims2:case value="<%=UIConstants.SCHOOL_VISIT%>" />
										<jims2:case value="<%=UIConstants.SCHOOL_ADJUDICATION%>" />
										<jims2:case value="<%=UIConstants.APPOINTMENT_LETTER%>" />
										<jims2:default>
											<tr id="locationList">	
												<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.eventLocation" /></td>
												<td class="formDe" nowrap='nowrap'>
													<html:select name="scheduleNewEventForm" property="currentService.locationId" onchange="">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="scheduleNewEventForm" property="currentService.locationsList" value="juvLocationUnitId" label="locationUnitName" />
													</html:select>
												</td>
											</tr>
										</jims2:default>
									</jims2:switch>

									<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">
										<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventTime"/></td>
								          	<td class="formDe">
									            <html:select property="currentService.currentEvent.eventTime">
									  				<html:option key="select.generic" value="" />
									  				<html:optionsCollection name="scheduleNewEventForm" property="currentService.currentEvent.workDays" value="description" label="description" />
									  		    </html:select>
											</td>	
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength"/></td>
									        <td class="formDe">
						      					<html:select styleId="sessionLength" name="scheduleNewEventForm" property="currentService.currentEvent.sessionLength" size="1" >
						      						<html:option value="">Please Select</html:option>
						      						<jims2:codetable codeTableName="<%=PDCodeTableConstants.SESSION_LENGTH_INTERVAL%>" />
						      					</html:select>
						          			</td>	
										</tr>
										</logic:notEqual>
									</logic:notEqual>
									
								</table>	
<%-- END EVENT DETAILS INPUT TABLE --%>										
							</td>			
						</tr>
					</logic:equal>
	  				<div class='spacer'></div>
					<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">
						<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.APPOINTMENT_LETTER%>">
			<%-- comments field --%>		
  						<tr>
  							<td bgcolor="#ffffff"><div class='spacer'></div></td>
  						</tr>			   
						<tr>
							<td colspan='4'>
								<div class='spacer'></div>
<%-- BEGIN COMMENTS TABLE --%>								
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
<%-- END COMMENTS TABLE --%>									
							</td>
						</tr>
     	 				<tr>
     	 					<td height="3"><div class='spacer'></div></td>
     	 				</tr>
     	 			
			<%-- end comments field --%>
			<%-- recurring events section --%>
						<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCode" value="<%=UIConstants.SCHOOL_ADJUDICATION%>">		
							
							<tr>
								<td>
<%-- BEGIN RECURRING EVENT INPUT TABLE --%>								
									<table width='100%'>
										<tr>
											<td colspan='2'>
<%-- BEGIN RECURRING EVENT SELECT TABLE --%>												
												<table align="center" width='98.5%' cellpadding="2" cellspacing="1" class="borderTableGrey">
													<tr>
														<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.recurringEvent" />?</td>
														<td class="formDe" nowrap='nowrap'>
															<bean:message key="prompt.yes"/><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="true"></html:radio>
															<bean:message key="prompt.no"/><html:radio name="scheduleNewEventForm" property="currentService.currentEvent.recurringEvent" value="false"></html:radio>
														</td>
													</tr>
												</table>
<%-- END RECURRING EVENT SELECT TABLE --%>													
											</td>
										</tr>
										<tr id='recurrenceTable' class='hidden'>
											<td colspan='2'>
<%-- BEGIN RECURRING EVENT PATTERN TABLE --%>
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
																<html:option value="0"><bean:message key="prompt.january" /></html:option>					
																<html:option value="1"><bean:message key="prompt.february" /></html:option>
																<html:option value="2"><bean:message key="prompt.march" /></html:option>
																<html:option value="3"><bean:message key="prompt.april" /></html:option>
																<html:option value="4"><bean:message key="prompt.may" /></html:option>						
																<html:option value="5"><bean:message key="prompt.june" /></html:option>									
																<html:option value="6"><bean:message key="prompt.july" /></html:option>
																<html:option value="7"><bean:message key="prompt.august" /></html:option>
																<html:option value="8"><bean:message key="prompt.september" /></html:option>
																<html:option value="9"><bean:message key="prompt.october" /></html:option>
																<html:option value="10"><bean:message key="prompt.november" /></html:option>
																<html:option value="11"><bean:message key="prompt.december" /></html:option>						
															</html:select>
															<html:text name="scheduleNewEventForm" property="currentService.currentEvent.yearlyDay" size="2" maxlength="2"/>                    
									      				</td>
								    				</tr>

		    						  				<tr id='yearlyRow2' class='hidden'>
		      											<td nowrap='nowrap' class='formDe'>
  															<html:radio name="scheduleNewEventForm" property="currentService.currentEvent.yearlyRadio" value="false"/>
  																Every&nbsp;The&nbsp;
				  											<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekNumber" >
				  												<html:option value="1"><bean:message key="prompt.first" /></html:option>					
				  												<html:option value="2"><bean:message key="prompt.second" /></html:option>
				  												<html:option value="3"><bean:message key="prompt.third" /></html:option>
				  												<html:option value="4"><bean:message key="prompt.fourth" /></html:option>
				  												<html:option value="5"><bean:message key="prompt.last" /></html:option>
				  											</html:select>	
															<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyWeekDay" >
																<html:option value="0"><bean:message key="prompt.sunday" /></html:option>					
																<html:option value="1"><bean:message key="prompt.monday" /></html:option>
																<html:option value="2"><bean:message key="prompt.tuesday" /></html:option>
																<html:option value="3"><bean:message key="prompt.wednesday" /></html:option>
																<html:option value="4"><bean:message key="prompt.thursday" /></html:option>
																<html:option value="5"><bean:message key="prompt.friday" /></html:option>						
																<html:option value="6"><bean:message key="prompt.saturday" /></html:option>									
															</html:select>
															&nbsp;	of  &nbsp;
															<html:select name="scheduleNewEventForm" property="currentService.currentEvent.yearlyMonthNumber1" >
																<html:option value="0"><bean:message key="prompt.january" /></html:option>					
																<html:option value="1"><bean:message key="prompt.february" /></html:option>
																<html:option value="2"><bean:message key="prompt.march" /></html:option>
																<html:option value="3"><bean:message key="prompt.april" /></html:option>
																<html:option value="4"><bean:message key="prompt.may" /></html:option>						
																<html:option value="5"><bean:message key="prompt.june" /></html:option>									
																<html:option value="6"><bean:message key="prompt.july" /></html:option>
																<html:option value="7"><bean:message key="prompt.august" /></html:option>
																<html:option value="8"><bean:message key="prompt.september" /></html:option>
																<html:option value="9"><bean:message key="prompt.october" /></html:option>
																<html:option value="10"><bean:message key="prompt.november" /></html:option>
																<html:option value="11"><bean:message key="prompt.december" /></html:option>						
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
												</table> 
<%-- END RECURRING EVENT PATTERN TABLE --%>				
											</td>
										</tr>

									</table>
									
<%-- END RECURRING EVENT INPUT TABLE --%>										
								</td>
							</tr>
							</logic:notEqual>
						 </logic:notEqual>
				
					</logic:notEqual>	
					<div class='spacer'></div>
					<tr>
											<td>&nbsp;&nbsp;</td></tr>
				 </table>	
					<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
					<table width="100%"> 
						<tr id='editButtons'>
			  				<td align="center">
			  		  			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
			  		  			<logic:notEqual name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
			  						<html:submit property="submitAction" styleId="nonIntValidateNext"><bean:message key="button.next" /></html:submit>
			  					</logic:notEqual>			
			  		  			<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">				
			  						<logic:notEmpty name="scheduleNewEventForm" property="preScheduledEvents">
			  							<html:submit property="submitAction" styleId="nonIntValidateNext"><bean:message key="button.next" /></html:submit>
			  						</logic:notEmpty>
			  		  			</logic:equal>			
			  		  			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			  				</td>
						</tr>
					</table>
<%-- END BUTTON TABLE --%>	
				</logic:equal>

			
<%-- END BLUE BORDER DETAILS TABLE --%>			
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
<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>