<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- THIS JSP IS USED FOR DETAILS AND SUMMARY SCREENS FOR CREATE, UPDATE, RESCHEDULE, DELETE AND RESULTS -->
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - csCalendar - otherEvent - otherEventDetails.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
		
		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		
	</head>
	<body topmargin="0" leftmargin="0">
		<html:form action="/submitCSOtherEventUpdate" target="content">
			<div align="center">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign=top>
							<table width=100% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign=top>
										<!--tabs start-->
										<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="calendarTab"/>
										</tiles:insert>	
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
								<logic:notEmpty name="csCalendarOtherForm" property="superviseeId">								
								<%--<logic:equal name="csCalendarOtherForm" property="context" value="S"> 
								slin: if the event has a SPN associated render the supervisee header and tabs - this supercedes the calendar context
								--%>
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
											<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
											<!-- END SUPERVISEE INFORMATION TABLE  -->
										</td>
									</tr>	
									<!-- BEGIN GREEN TABS TABLE -->		
									<tr>
										<td valign="top" align="center"> 
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
												</tr>						
												<tr>
													<td valign="top">
														<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
															<tiles:put name="tab" value="CalendarTab"/> 
														</tiles:insert>					
													</td>
												</tr>
												<tr>
													<td  bgcolor=#33cc66><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
												</tr>
											</table>
										</td>
									</tr>
									<!-- END GREEN TABS TABLE -->				
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN GREEN BORDER TABLE -->					
											<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">								
								<%--</logic:equal>	--%>	
								</logic:notEmpty>
								<tr>
									<td valign=top align=center>
										<!--header area start-->

										<!-- BEGIN HEADING TABLE -->
										<table width=100%>
											<tr>
												<td align="center" class="header">CSCD -
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="create">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|43">
														Create</logic:equal>
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="update">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|37">
														Update</logic:equal>
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="reschedule">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|39">
														Reschedule</logic:equal>
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="delete">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|40">
														Delete</logic:equal>
													Other Event
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterResults">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|42">
														Results</logic:equal>
													<logic:equal name="csCalendarOtherForm" property="activityFlow" value="view">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|35">
														Details</logic:equal>
													<logic:equal name="state" value="summary">Summary</logic:equal>
												</td>
											</tr>
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
											<logic:equal name="state" value="summary">
												<tr>
													<td>
														<ul>
															<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
														</ul>
													</td>
												</tr>
											</logic:equal>
										</table>
										<!-- END INSTRUCTION TABLE -->
										<!-- BEGIN DETAIL TABLE -->
											<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr>
													<td class="detailHead">
														<bean:write name="csCalendarOtherForm" property="eventTypeDescription"/> 
														Information
													</td>
												</tr>
												<tr>
													<td>
														<table width='100%' cellpadding="4" cellspacing="1">
															<tr>
																<td class="formDeLabel" nowrap width=1%>Event Date</td>
																<td class="formDe" width="40%">
																	<bean:write name="csCalendarOtherForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
																</td>
																<td class="formDeLabel" nowrap width=1%>Event Name</td>
																<td class="formDe">
																	<bean:write name="csCalendarOtherForm" property="eventName"/>
																	
																	</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>Start Time</td>
																<td class="formDe">
																		<bean:write name="csCalendarOtherForm" property="startTime"/> <logic:notEmpty name="csCalendarOtherForm" property="startTime"><bean:write name="csCalendarOtherForm" property="AMPMId1"/></logic:notEmpty>
																	<td class="formDeLabel" nowrap>End Time</td>
																	<td class="formDe">
																		<bean:write name="csCalendarOtherForm" property="endTime"/> <logic:notEmpty name="csCalendarOtherForm" property="endTime"><bean:write name="csCalendarOtherForm" property="AMPMId2"/></logic:notEmpty>
																	</td>
																</tr>
																<logic:notEmpty name="csCalendarOtherForm" property="outcomeCd" >
																	<tr>
																		<td class="formDeLabel" nowrap width=20%>Outcome</td>
																		<td class="formDe" colspan=3><bean:write name="csCalendarOtherForm" property="outcomeDesc"/></td>
																	</tr>
																</logic:notEmpty>
																<tr>
																	<td colspan=4 class=formDeLabel>
																		Purpose
																	</td>
																</tr>
																<tr>
																	<td colspan=4 class=formDe>
																		<bean:write name="csCalendarOtherForm" property="purpose"/>&nbsp;
																	</td>
																</tr>
																
																<logic:notEmpty name="csCalendarOtherForm" property="narrative" >
																	<tr id=narrativeLabelRow>
																		<td colspan=4 class=formDeLabel>Narrative</td>
																	</tr>
																	<tr id=narrativeLabel>
																		<td colspan=4 class=formDe>
																			<bean:write name="csCalendarOtherForm" property="narrative" filter="false"/>
																		</td>
																	</tr>
																</logic:notEmpty>
																
																
															</table>
														</td>
													</tr>
												</table>
												<div class=spacer4px></div>
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%">
												<tr>
													<td align="center">
													
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														
														<logic:equal name="state" value="summary">
															<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
														</logic:equal>
														
														<logic:equal name="csCalendarOtherForm" property="activityFlow" value="view">
															
															<!-- Events with RESCHEDULED outcome cannot be updated -->
															<logic:notEqual name="csCalendarOtherForm" property="outcomeCd" value="RE">
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_UPDATE%>'>
																<input type="submit" value="<bean:message key='button.update'/>" name="submitAction" id="updateButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventSelection.do',false);" >
																</jims2:isAllowed>
																<!-- Events with CLOSED status cannot be rescheduled or cannot enter results.--> 
																<logic:notEqual name="csCalendarOtherForm" property="statusCd" value="C">
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OTR_CREATE%>'>
																	<input type="submit" value="<bean:message key='button.enterResults'/>" name="submitAction" id="enterResultsButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventSelection.do',false);" >
																</jims2:isAllowed>	
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OT_RESCHEDULE%>'>
																	<input type="submit" value="<bean:message key='button.reschedule'/>" name="submitAction" id="rescheduleButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventSelection.do',false);" >
																</jims2:isAllowed>
																</logic:notEqual>
																
																<logic:equal name="csCalendarOtherForm" property="statusCd" value="O">	
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OOT_DELETE%>'>	
																	<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" id="deleteButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventSelection.do',false);" >
																</jims2:isAllowed>
																</logic:equal>	
																
																<logic:equal name="csCalendarOtherForm" property="statusCd" value="C">	
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_COT_DELETE%>'>	
																	<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" id="deleteButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSOtherEventSelection.do',false);" >
																</jims2:isAllowed>
																</logic:equal>	
																
															</logic:notEqual>
															<input type="hidden" name="eventLoaded" value="true">
															
														</logic:equal>
														
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
														
													</td>
												</tr>
											</table>
											
											<!-- END BUTTON TABLE -->
											<!-- END DETAIL TABLE -->
										<logic:notEmpty name="csCalendarOtherForm" property="superviseeId">
										</td>
									</tr>
								</table>
							</logic:notEmpty>
							<br>
							</td>
						</tr>
					</table><br>
					<!--casefile tabs end-->

				</td>
			</tr>
		</table>
		<!-- END  TABLE -->
	</div>
	<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
