<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<title><bean:message key="title.heading" /> - csCalendar - otherEvent - otherEventsList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>	
		<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>		
	</head>
	<body topmargin="0" leftmargin="0" onload="document.forms[0].reset()">
		<html:form action="/handleCSOtherEventSelection" target="content">
			<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|34">
			<input type="hidden" name="context" value="<bean:write name='csCalendarOtherForm' property='context'/>">
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
								<logic:equal name="csCalendarOtherForm" property="context" value="S">
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
								</logic:equal>
								<tr>
									<td valign="top" align=center>
										<!-- BEGIN HEADING TABLE -->
										<table width=100% cellpadding=1 cellspacing=0>
											<tr>
												<td align="center" class="header">CSCD - Other Events - 
													<bean:write name="csCalendarOtherForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
												</td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0" cellpadding=1 cellspacing=0>
											<logic:equal name="state" value="confirm">
												<tr>
													<td class="confirm">
														<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterResults">
															The event results successfully saved.
														</logic:equal>														
														
														<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterResults">
															The event has been successfully 														
															<logic:equal name="csCalendarOtherForm" property="activityFlow" value="create">
																created.
															</logic:equal>
															<logic:equal name="csCalendarOtherForm" property="activityFlow" value="update">
																saved.
															</logic:equal>
															<logic:equal name="csCalendarOtherForm" property="activityFlow" value="reschedule">
																rescheduled.
															</logic:equal>
															<logic:equal name="csCalendarOtherForm" property="activityFlow" value="delete">
																deleted.
															</logic:equal>
														</logic:notEqual>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="state" value="summary">
											<tr>
											   <td class=confirm>
											Multiple other events results successfully saved.
											   </td>
											</tr>
											</logic:equal>
											
											<logic:equal name="state" value="success">
										       <tr>
												<td align="center" class="confirm"><div><span class='cautionText boldText2px'>A Casenote exists referencing this event that will need to be deleted.</span></div>
                                                </td>
											   </tr>
										    </logic:equal>
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
											<tr>
											 <logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
												<td>
													<ul>
													<li>Select radio button for specific event and appropriate button.</li>
													<li>Click Event Type hyperlink to view event details.</li>
													<li>Click Add New Event to add new other event.</li>
													<li>Click Supervisee hyperlink to view supervisee details.</li>
													</ul>
												</td>
												</logic:notEqual>
												<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterMultipleResults">
												<li> Select checkbox to select events. Click Enter Results.</li>
												</logic:equal>
											</tr>

										</table>
										<!-- END INSTRUCTION TABLE -->
										
										<tiles:insert page="otherEventsListTile.jsp" flush="true">
											<tiles:put name="csCalendarOtherForm" beanName="csCalendarOtherForm" />
										</tiles:insert>
										
										<!-- END DETAIL TABLE -->
									</td>
								</tr>
							</table><br>
						</td>
					</tr>
				</table><br>
			</td>
		</tr>
	</table>
</div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<br>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
