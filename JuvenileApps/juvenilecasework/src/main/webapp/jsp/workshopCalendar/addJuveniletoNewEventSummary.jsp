<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 06/07/2006		AWidjaja Create JSP--%>
<%-- 10/27/2011		CShimek	 Added onclick script to Generate Document button to prevent duplicate selection --%>
<%-- 02/22/2016		RCapestani Bug 33590 MJCW: School Adjudication Notification (Calendar) Report - Remove "BFO" From Generate Document Button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="ui.common.UIUtil"%>
<%@ page import="naming.UIConstants"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features"%>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>

<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title><bean:message key="title.heading" />&nbsp;<bean:message
		key="title.juvenileCasework" /> - addJuveniletoNewEventSummary.jsp</title>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"
	topmargin='0' leftmargin="0">
	<html:form action="/submitJuvenileServiceEvent" target="content">

		<logic:equal name="pageType" value="summary">
			<input type="hidden" name="helpFile"
				value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|140">
		</logic:equal>
		<logic:equal name="pageType" value="confirmation">
			<input type="hidden" name="helpFile"
				value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|274">
		</logic:equal>
		<logic:equal name="pageType" value="maxYouthLimit">
			<input type="hidden" name="helpFile"
				value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|274">
		</logic:equal>
		<%-- BEGIN HEADING TABLE --%>
		<table width='100%'>
			<%-- <logic:equal name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
		<logic:equal name="pageType" value="summary">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for<br>Past Pre-Scheduled Event(s) Summary</td></tr>
		</logic:equal>
		<logic:equal name="pageType" value="confirmation">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for<br>Past Pre-Scheduled Event(s) Confirmation</td></tr>
		</logic:equal>
	</logic:equal>

	<logic:notEqual name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
		<logic:equal name="pageType" value="summary">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Calendar Events Summary</td></tr>
		</logic:equal>
	
		<logic:equal name="pageType" value="confirmation">
			<tr><td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Calendar Events Confirmation</td></tr>
		</logic:equal>
	</logic:notEqual> --%>
			<tr>
				<td align="center" class="header"><bean:message
						key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for
					Event Summary<br></td>
			</tr>
			<logic:equal name="pageType" value="confirmation">
				<tr>
					<td align="center" class="confirm">Juvenile has been added to
						the event</td>
				</tr>
				<logic:equal name="scheduleNewEventForm"
					property="programReferralNew" value="true">
					<tr>
						<td align='center' class="confirm">The following Program
							Referral has been created.</td>
					</tr>
				</logic:equal>
			</logic:equal>
		</table>
		<%-- END HEADING TABLE --%>
		<%-- BEGIN ERROR TABLE --%>
		<table width='98%' align='center'>
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
		<%-- END ERROR TABLE --%>
		<div class='spacer'></div>
		<%-- BEGIN INSTRUCTION TABLE --%>
		<%-- <logic:equal name="pageType" value="summary">
	<table width="98%">
		<tr>
			<td>	
				<ul>
				    <!-- <li>Select a hyperlinked date to view the Event details.</li> 
					<li>Select hyperlinked Event ID to view conflicting event.</li>-->
						<logic:equal name="scheduleNewEventForm" property="currentService.serviceTypeCategory" value="<%=UIConstants.PRESCHEDULED_SERVICE_TYPE%>">		
							<logic:notEmpty name="scheduleNewEventForm" property="programReferral"> 
								<li>Select <b>Add conflicting event</b> checkbox, if applicable, to add event regardless of conflict</li>
								<li>Select the <b>Finish</b> button to view the confirmation.</li>
							</logic:notEmpty>						
							<logic:empty name="scheduleNewEventForm" property="programReferral"> 
								<li>Select the <b>Create New Referral</b> button to create a new Referral.</li>
							</logic:empty>						
						</logic:equal>
					<!-- <li>Select <b>Back</b> button to reschedule selected event.</li> -->
				</ul>
			</td>
		</tr>
	</table>
</logic:equal> --%>

		<%-- <logic:equal name="pageType" value="confirmation">
	<table width="98%">
		<tr>
			<td>	
				<ul>
			    <li>Select a hyperlinked date to view the Event details.</li>
				</ul>
			</td>
		</tr>
	</table>
</logic:equal> --%>
		<%-- END INSTRUCTION TABLE --%>
		<%-- BEGIN Casefile Header TABLE --%>
		<table align="center" width='98%' border="0" cellpadding="2"
			cellspacing="0" class="borderTableBlue">
			<%-- added Caseload Manager name and reformatted header per ER JIMS200027187 mjt 16feb2006  --%>
			<tr>
				<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message
						key="prompt.details" /></td>
			</tr>
			<tr>
				<td valign='top' align='center'>
					<table cellpadding='2' cellspacing='1' width='100%'>
						<tr>
							<td class="formDeLabel">Juvenile Number</td>
							<td class="formDe"><bean:write name="scheduleNewEventForm"
									property="juvenileNum" /></td>
							<td class="formDeLabel">Juvenile Name</td>
							<td class="formDe"><bean:write name="scheduleNewEventForm"
									property="juvenileFullName" /></td>
							<td class="formDeLabel">DOB</td>
							<td class="formDe"><bean:write name="scheduleNewEventForm"
									property="birthDate" /></td>
							<td class="formDeLabel">Casefile ID</td>
							<td class="formDe"><bean:write name="scheduleNewEventForm"
									property="caseFileId" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%-- END Casefile Header TABLE --%>
		<div class='spacer'></div>
		<%--begin of blue tabs--%>
		<table align="center" width='98%' cellpadding="2" cellspacing="0"
			border="0">


			<%-- BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
			<tr>
				<td><logic:notEmpty name="scheduleNewEventForm"
						property="programReferral">
						<table align="center" width='98%' border="0" cellpadding="2"
							cellspacing="0" class="borderTableBlue">
							<tr>
								<td class='detailHead'><bean:message
										key="prompt.programReferral" />&nbsp;<bean:message
										key="prompt.details" /></td>
							</tr>
							<tr>
								<td valign='top' align='center'>
									<table cellpadding='2' cellspacing='1' width='100%'>
										<tr>
											<td class="formDeLabel"><bean:message
													key="prompt.beginDate" /></td>
											<td class="formDe"><bean:write
													name="scheduleNewEventForm"
													property="programReferral.beginDateStr" /></td>
											<td class="formDeLabel"><bean:message
													key="prompt.assignedHours" /></td>
											<td class="formDe"><bean:write
													name="scheduleNewEventForm"
													property="programReferral.assignedHours" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
													key="prompt.courtOrdered" /></td>
											<bean:define id="progRef" name="scheduleNewEventForm"
												property="programReferral" />
											<td class="formDe"><jims2:displayBoolean name="progRef"
													property="courtOrdered" trueValue="Yes" falseValue="No" /></td>
											<td class="formDeLabel" nowrap='nowrap'><bean:message
													key="prompt.referralStatus" /></td>
											<td class="formDe"><bean:write
													name="scheduleNewEventForm"
													property="programReferral.referralState.description" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message
													key="prompt.comments" /></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4">
												<div class='scrollingDiv100'>
													<table>
														<logic:notEmpty name="scheduleNewEventForm"
															property="programReferral.referralComments">
															<logic:iterate id="refComment"
																name="scheduleNewEventForm"
																property="programReferral.referralComments">
																<tr style="height: 100%">
																	<td><bean:write name="refComment"
																			property="commentText" /> [<bean:write
																			name="refComment" property="commentsDate"
																			formatKey="datetime.format.mmddyyyyHHmm" /> - <bean:write
																			name="refComment" property="userName" /> ]</td>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
														<logic:notEmpty name="scheduleNewEventForm"
															property="programReferral.comments">
															<tr style="height: 100%">
																<td><bean:write name="scheduleNewEventForm"
																		property="programReferral.comments" /> [<bean:write
																		name="scheduleNewEventForm"
																		property="programReferral.currentDate"
																		formatKey="datetime.format.mmddyyyyHHmm" /> - <bean:write
																		name="scheduleNewEventForm"
																		property="programReferral.currentUserName" /> ]</td>
															</tr>
														</logic:notEmpty>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</logic:notEmpty></td>
			</tr>
			<%-- END PROGRAM REFERRAL DETAILS TABLE --%>
			<tr>
				<td>
					<%-- BEGIN EVENT DETAIL TABLE --%>
					<table align="center" width="98%" cellpadding="2" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td class="detailHead">Selected Event</td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<table width='100%' cellpadding="4" cellspacing="1">
									<tr bgcolor='#cccccc'>
										<td class="subHead">Event ID</td>
										<td class="subHead">Event Date</td>
										<td class="subHead">Event Time</td>
										<td class="subHead">Max Attendance</td>
										<td class="subHead">Location Unit</td>
										<td class="subHead">Instructor</td>
										<td class="subHead">Service Provider</td>
										<td class="subHead">Service Name</td>
									</tr>
									<tr>
										<td class="formDe"><bean:write
												name="scheduleNewEventForm" property="eventId" /></td>
										<td class="formDe"><bean:write
												name="scheduleNewEventForm"
												property="currentService.currentEvent.eventDateStr" /></td>
										<td class="formDe"><bean:write
												name="scheduleNewEventForm"
												property="currentService.currentEvent.eventTime" /></td>
										<td class="formDe"><bean:write
												name="scheduleNewEventForm"
												property="currentService.currentEvent.maxAttendance" /></td>
										<td class="formDe"><bean:write
												name="scheduleNewEventForm"
												property="currentService.location" /></td>
										<td class="formDe" nowrap="nowrap"><bean:write
												name="scheduleNewEventForm"
												property="currentService.currentEvent.instructorName" /></td>
										<td class="formDe" nowrap="nowrap"><bean:write
												name="scheduleNewEventForm"
												property="currentService.serviceProvider" /></td>
										<td class="formDe" nowrap="nowrap"><bean:write
												name="scheduleNewEventForm"
												property="currentService.service" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
			<tr>
				<td>
					<%-- BEGIN BUTTON TABLE --%>
					<div class='spacer'></div>
					<table width="100%">
						<tr>
							<td align="center"><logic:equal name="pageType"
									value="summary">
									<input type="submit" name="submitAction"
										id="resetActionSummary" data-href='Back'
										value="<bean:message key='button.back'/>">
									<logic:notEmpty name="scheduleNewEventForm"
										property="programReferral">
										<%--Bug fix #28488  starts--%>
										<html:submit property="submitAction"
											styleId="disableSummaryFinish">
											<bean:message key="button.finish" />
										</html:submit>
										<%--Bug fix #28488  ends--%>
									</logic:notEmpty>
									<logic:empty name="scheduleNewEventForm"
										property="programReferral">
										<html:submit property="submitAction">
											<bean:message key="button.createNewReferral" />
										</html:submit>
									</logic:empty>

									<input type="submit" name="submitAction"
										id="resetActionSummary2" data-href='Cancel'
										value="<bean:message key='button.cancel'/>">
								</logic:equal> <logic:equal name="pageType" value="confirmation">
									<div id="docPastEvents">
										<input type="button"
											value="<bean:message key="button.documentAttendance"/>"
											onClick="goNav('/<msp:webapp/>handleCalendarEventDetails.do?submitAction=<bean:message key="button.documentAttendance"/>&from=workshopCalendar&currentJuvenileId=<bean:write name="scheduleNewEventForm" property="juvenileNum"/>&eventId=<bean:write name="scheduleNewEventForm" property="eventId"/>&preScheEvt=true');" />
									</div><!-- "scheduleNewEventForm"
										property= -->
								</logic:equal> <%-- <logic:equal name="scheduleNewEventForm"
										property="action"
									value="attendanceConfirm">
									<logic:equal name="scheduleNewEventForm"
										property="maxyouthLimit" value="false">
										<jims2:isAllowed
											requiredFeatures='<%=Features.JCW_WC_JUV_ADD%>'>
											<html:submit property="submitAction">
												<bean:message key="button.addanotherYouthToSession" />
											</html:submit>
											<!-- add feature -->
										</jims2:isAllowed>
									</logic:equal>
								</logic:equal> --%>
								<logic:equal name="pageType" value="maxYouthLimit">
										<jims2:isAllowed
											requiredFeatures='<%=Features.JCW_WC_JUV_ADD%>'>
											<html:submit property="submitAction">
												<bean:message key="button.addanotherYouthToSession" />
											</html:submit>
										</jims2:isAllowed>
								</logic:equal>

								<div class='spacer'></div> <html:submit property="submitAction">
									<bean:message key="button.returnToCalendar" />
								</html:submit>
						</tr>
					</table> <%-- END BUTTON TABLE --%>
				</td>
			</tr>
		</table>
		<%-- BEGIN BLUE BORDER TABS TABLE --%>
		</td>
		</tr>
		</table>
		<input type="hidden" name="docGenInd" id="DocGenerated" value="">
		<html:hidden name="scheduleNewEventForm" property="eventDate"
			styleId='eventDate' />
	</html:form>

	<%-- <logic:equal name="pageType" value="confirmation">
<html:form action="/handleCalendarEventDetails?eventId=<bean:write name="scheduleNewEventForm.currentService.currentEvent" property="currentService.currentEvent.eventId"/>" target="content">
	<html:submit property="submitAction"><bean:message key="button.documentAttendance"/></html:submit>
</html:form>			
</logic:equal> --%>

	<div align='center'>
		<script type="text/javascript">
			renderBackToTop()
		</script>
	</div>
</body>
</html:html>