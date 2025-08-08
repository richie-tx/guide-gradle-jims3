<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/18/2008	 Aswin Widjaja - Create JSP -->
<!-- 12/21/2010  L Deen        - Activity #68540 -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
<title><bean:message key="title.heading" /> - csCalendar - rescheduleFV.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
		
		<script type="text/javascript">
		   function validateField(theForm){
		       clearAllValArrays();		      
		       customValRequired("eventDateStr", "<bean:message key='errors.required' arg0='Event Date'/>","");
		       addMMDDYYYYDateValidation("eventDateStr","Event Date must be in date format (mm/dd/yyyy). ");
		       return validateCustomStrutsBasedJS(theForm);
		  }
		</script>       
		
	</head>
	<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
		<html:form action="/displayCSFVEventUpdate" target="content">
			<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|57">
			<div align="center">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="calendarTab"/>
										</tiles:insert>	
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">																
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td align="center">
										<!--header start-->
										<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true">
										</tiles:insert>	
										<!--header end-->
									</td>
								</tr>
								<tr>
									<td valign="top" align="center">										
										<!--casefile tabs start-->
										<table width="98%" border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
											</tr>
											<tr>
												<td valign="top">
													<!--tabs start-->
													<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
														<tiles:put name="tab" value="CalendarTab"/> 
													</tiles:insert>	
													<!--tabs end-->
												</td>
											</tr>
											<tr>
												<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
											</tr>
										</table>
										<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
											</tr>
											<tr>
												<td valign="top" align="center">
													<!-- BEGIN HEADING TABLE -->
													<table width="100%">
														<tr>
															<td align="center" class="header">CSCD - Reschedule Field Visit 
															</td>
														</tr>
													</table>
													<!-- END HEADING TABLE -->
													<!-- BEGIN INSTRUCTION TABLE -->
													<table width="98%" border="0">
														<tr>
															<td>
																<ul>
																	<span class=hidden id=summaryInstructions>
																		<li>Enter required fields. Click Next </li>
																	</span>
																</ul>
															</td>
														</tr>
													</table>
													<!-- END INSTRUCTION TABLE -->
													
													<table width='98%' cellpadding="2" cellspacing="1"  class="borderTableBlue">
														<tr>
															<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.4.diamond"/>Reschedule Date</td>
															<td class="formDe">
																<SCRIPT LANGUAGE="JavaScript" ID="js1">
																	var cal1 = new CalendarPopup();
																	cal1.showYearNavigation();
																</SCRIPT>
																<html:text name="csCalendarFVForm" property="eventDateStr" size="10" maxlength="10"/>
																<A HREF="#" onClick="cal1.select(document.forms[0].eventDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><img border=0 src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A>
																
															</td>
														</tr>
													</table>
													<div class="spacer4px"></div>
													
													<!-- BEGIN DETAIL TABLE -->
													<tiles:insert page="fieldVisitDetailsTile.jsp" flush="true">
														<tiles:put name="fieldVisit" beanName="csCalendarFVForm" beanProperty="currentFieldVisit" />
													</tiles:insert>
													<!-- END DETAIL TABLE -->
													
													<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%">
														<tr>
															<td align="center">
																<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																<html:submit property="submitAction" onclick="return validateField(this.form)"><bean:message key="button.next" /></html:submit>
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
			<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
