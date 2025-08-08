<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/01/2008	 Aswin Widjaja - Create JSP -->
<%-- 06/05/2008	 Leslie Deen	#52283 Add Help File --%>
<!-- 08/23/2010  D Williamson   #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
<!-- 09/27/2011  RCapestani - Activity #71306 add code to display reschedule reason text -->
<!-- 04/12/2012  TSVines        #72079 Removed update button on the Closed Group Office Visits details accessible form the hyper-link -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
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
<title>Common Supervision - csCalendar/groupOfficeVisit/groupOfficeVisitDetails.jsp</title>

		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
		<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
		
	</head>
	<body topmargin=0 leftmargin="0">
		<html:form action="/submitCSGVUpdate" target="content">
			<div align="center">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top">
							<table width=100% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="caseloadTab"/>
										</tiles:insert>	
									</td>
								</tr>
								<tr>
									<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header">
											<bean:message key="title.CSCD" />&nbsp;-&nbsp;
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="create">Create
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|49">
											</logic:equal>
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="update">Update
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|16">
											</logic:equal>
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">Reschedule
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|18">
											</logic:equal>
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="delete">Delete
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|24">
											</logic:equal>
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="updateResults">Update</logic:equal>
											Group Office Visit
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">Results
											    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|14">
											</logic:equal> 
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="updateResults">Results
											    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|22">
											</logic:equal>	
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="addAttendees">- Add Attendees
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|20">
											</logic:equal>
											
											<logic:equal name="csCalendarOVForm" property="activityFlow" value="view">Details
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|12">
											</logic:equal>
											
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
													<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button</li>
												</ul>
											</td>
										</tr>
										</logic:equal>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<tiles:insert page="superviseeListTile.jsp" flush="true">
										<tiles:put name="superviseeList" beanName="csCalendarOVForm" beanProperty="superviseeList" />
									</tiles:insert>
									<div class=spacer4px></div>
									
									<!-- BEGIN DETAIL TABLE -->
									<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Group Office Visit Information</td>
										</tr>
										<tr>
											<td colspan=2>
												<table width='100%' cellpadding="4" cellspacing="1">
													<tr>
														<td class="formDeLabel" nowrap width="1%">Office Visit Date</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventDate" formatKey="date.format.mmddyyyy"/></td>
														<td class="formDeLabel" nowrap width="1%">Event Name</td>
															<td class="formDe">
																<bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventName"/>
															</td>
															
														
													</tr>
													<tr>
														<td class="formDeLabel" nowrap>Start Time</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.startTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.startTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId1"/></logic:notEmpty>
														</td>
														<td class="formDeLabel" nowrap>End Time</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.endTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.endTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId2"/></logic:notEmpty>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" colspan=4>Purpose of Group Office Visit</td>
													</tr>
													
													<tr class=formDe>
														<td colspan=4>
															<bean:write name="csCalendarOVForm" property="currentOfficeVisit.purpose" filter="false"/>&nbsp;
														</td>
													</tr>
													<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">
															<tr>
																<td class="formDeLabel" colspan="4"><bean:message key="prompt.rescheduledReason"/> - <bean:message key="prompt.casenote"/></td>
															</tr>
															<tr class="formDe">
																<td colspan="4" align="left">
																	<bean:write name="csCalendarOVForm" property="currentOfficeVisit.rescheduleReason" filter="false"/>																							
																</td>
															</tr>
													</logic:equal>
													<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd" value="<%=PDCodeTableConstants.DEFAULT_CS_EVENT_OUTCOME%>">
														<logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd">
															<tr>
																<td class="formDeLabel">Outcome</td>
																<td class=formDe colspan=3>
																	<bean:write name="csCalendarOVForm" property="currentOfficeVisit.outcomeDesc"/>
																	
																</td>
															</tr>
														
															<tr>
																<td class="formDeLabel" colspan=4>Narrative</td>
															</tr>
															<tr class=formDe>
																<td colspan=4>
																	<bean:write name="csCalendarOVForm" property="currentOfficeVisit.narrative" filter="false"/>
																</td>
															</tr>
														</logic:notEmpty>
													</logic:notEqual>
														
												</table>
											</td>
										</tr>
									</table>
									<!-- BEGIN DETAIL TABLE -->
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr class="buttonBar">
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													
												<logic:equal name="csCalendarOVForm" property="activityFlow" value="view">
													<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.status" value="C">
														<html:submit property="submitAction"><bean:message key="button.enterResults" /></html:submit>
													</logic:notEqual>
													
													<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do',false);" >
														
													<!-- Check status; if closed; Hide "Update Selected Results" button -->
													<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.status" value="C">
														<input type="submit" value="<bean:message key='button.updateSelectedResults'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do',false);" >
													</logic:notEqual>
												</logic:equal>
												<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="view">	
													<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
												</logic:notEqual>
												<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
			</table><br>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
