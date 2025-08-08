<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->
<!-- 06/08/2011  Richard Capestani Knape 66799 added error table -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisitDetails.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
			
	</head>
	<body topmargin=0 leftmargin="0">
		<html:form action="/submitCSFVEventUpdate" target="content">
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
										<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="calendarTab"/>
										</tiles:insert>	
										<!--tabs end-->
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
									<td align=center>
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
										<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
											</tr>
											<tr>
												<td valign=top align=center>
													<!-- BEGIN HEADING TABLE -->
													<table width=100%>
														<tr>
															<td align="center" class="header">CSCD -
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="create">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|31">
																	Create
																</logic:equal>
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="update">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|59">
																	Update
																</logic:equal>
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="delete">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|53">
																	Delete
																</logic:equal>
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|60">
																	Reschedule
																</logic:equal>
																Field Visit
																
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="enterResults">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|61">
																	Results
																</logic:equal>
																
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="view">
																	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|54">
																	Details
																</logic:equal>
																
																<logic:equal name="status" value="summary">
																	Summary
																</logic:equal>
																
																</td>
																	</tr>
																</table>
																<!-- END HEADING TABLE -->
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
																<!-- BEGIN INSTRUCTION TABLE -->
																<table width="98%" border="0">
																	<tr>
																		<td>
																			<ul>
																				<span class=hidden id=summaryInstructions>
																					<li>Verify that information is correct and select Finish button.</li>
																					<li>If any changes are needed, select Back button.</li>
																				</span>
																			</ul>
																		</td>
																	</tr>
																</table>
																<!--
																<script>
																	if (action.indexOf("Summary")>1){
																		show("summaryInstructions", 1, "inline")
																	}
																</script>-->
																<!-- END INSTRUCTION TABLE -->
																<!-- BEGIN DETAIL TABLE -->
																<tiles:insert page="../../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
																	<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
																	<tiles:put name="displayEvents" value="true"/> 
																</tiles:insert>
																<div class=spacer4px></div>
																
																<!-- BEGIN DETAIL TABLE -->
																<tiles:insert page="fieldVisitDetailsTile.jsp" flush="true">
																	<tiles:put name="fieldVisit" beanName="csCalendarFVForm" beanProperty="currentFieldVisit" />
																</tiles:insert>
																<!-- END DETAIL TABLE -->
																
																<!-- BEGIN BUTTON TABLE -->
																
																
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="view">
																	<logic:notEqual name="csCalendarFVForm" property="currentFieldVisit.outcomeCd" value="RE">
																		<table border="0" width="100%" id=normalButtons>
																			<tr>
																				<td align="center">
																				<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_UPDATE%>'>
																					<input type="submit" value="<bean:message key='button.update'/>" name="submitAction" id="updateButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSFVSelection.do',false);" >
																				</jims2:isAllowed>	
																					<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="O">
																					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFVR_CREATE%>'>
																						<input type="submit" value="<bean:message key='button.enterResults'/>" name="submitAction" id="enterResultsButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSFVSelection.do',false);" >
																					</jims2:isAllowed>
																					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_RESCHEDULE%>'>	
																						<input type="submit" value="<bean:message key='button.reschedule'/>" name="submitAction" id="rescheduleButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSFVSelection.do',false);" >
																					</jims2:isAllowed>	
																					</logic:equal>
																					<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="O">
																					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOFV_DELETE%>'>
																					<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" id="rescheduleButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSFVSelection.do',false);" >
																					</jims2:isAllowed>	
																					</logic:equal>
																					<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="C">
																					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALCFV_DELETE%>'>
																					<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" id="rescheduleButton" onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSFVSelection.do',false);" >
																					</jims2:isAllowed>	
																					</logic:equal>
																				</td>
																			</tr>
																		</table>
																	</logic:notEqual>
																</logic:equal>
																
																<table border="0" width="100%">
																	<tr>
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																			<logic:equal name="status" value="summary">
																				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.finish" /></html:submit>
																				  <logic:equal name="csCalendarFVForm" property="activityFlow" value="enterResults">
	                                                                                <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.finishAndScheduleNext" /></html:submit>
                                                                                 </logic:equal>
																			</logic:equal>	
																			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																			
																		</td>
																	</tr>
																</table>
																<!--<script>
																	if (action=="updateSummary" || action=="createSummary" || action=="rescheduleSummary" || action=="delete"){
																		show("summaryButtons", 1, "table");
																	show("normalButtons", 0);
																	}
																</script>-->
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
				<!-- END  TABLE -->
			</div>
			<br>
		</html:form>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
