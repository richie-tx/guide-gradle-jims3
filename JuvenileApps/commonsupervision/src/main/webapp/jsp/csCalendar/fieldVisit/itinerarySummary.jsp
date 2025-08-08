<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisit - itineraryCreate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

		</head>
		<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
			<html:form action="/submitCSFVItineraryUpdate" target="content">
				<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|28">
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
									<tr>
										<td valign=top align=center>
											<!-- BEGIN HEADING TABLE -->
											<table width=100%>
												<tr>
													<td align="center" class="header">CSCD - Update Field Visit Itinerary - Summary</td>
												</tr>
											</table>
											<!-- END HEADING TABLE -->
											<!-- BEGIN INSTRUCTION TABLE -->
											<table width="98%" border="0">
												<tr>
													<td>
														<ul>
															<li>Click Finish to complete Itinerary Update.</li>
														</ul>
													</td>
												</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->
											<!-- BEGIN DETAIL TABLE -->
											<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
												<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
												<tiles:put name="displayEvents" value="false"/>
												<tiles:put name="expanded" value="true"/> 
											</tiles:insert>
											<!-- End DETAIL TABLE -->
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>
											</table>
											<!-- END BUTTON TABLE -->
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
	<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
