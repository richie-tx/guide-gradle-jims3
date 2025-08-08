<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 06/20/2011	CShimek 	Create jsp --%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarScheduleSPSelectEventsSummary.jsp</title>

<html:base />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>
<html:form action="/submitCalendarSPSelectEvents" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|"> 
<input type="hidden" name="uniqueId" value=""/>

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework" /> - Service Provider Events <bean:write name="scheduleNewEventForm"  property="pageState" />
		</td>
  	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
	<logic:equal name="scheduleNewEventForm"  property="pageState" value="Confirmation">
		<tr>
			<td class="confirm"><bean:write name="scheduleNewEventForm"  property="confirmationMsg" /></td>
		</tr>	
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<logic:equal name="scheduleNewEventForm"  property="pageState" value="Summary">
					<li>Verify all information is correct the click Finish.</li>
					<li>To make corrections, click Back.</li>
				</logic:equal>	
				<li>Select a hyperlinked date to view the Event details.</li>
			</ul>	
		</td>
	</tr>
</table>
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
			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>		  
						<div class='spacer'></div>	
<%-- END PROGRAM REFERRAL DETAILS TABLE --%>						
					 	<table width='98%' border="0" cellpadding="2" cellspacing="0" align='center' class="borderTableBlue">
 							<tr>
 								<td class='detailHead'><bean:message key="prompt.programReferral" /> <bean:message key="prompt.details" /></td>
 							</tr>
 							<logic:notEqual  name="scheduleNewEventForm" property="referralFound" value="true" >
 								<tr>
 									<td>No Program Referral Found</td>
 								</tr>	
 							</logic:notEqual>
 							<logic:equal  name="scheduleNewEventForm" property="referralFound" value="true" >
		 						<tr>
		  							<td valign='top' align='center' colspan='2'>
		  								<table width='100%' cellpadding='2' cellspacing='1' align='center'>
											<tr>
												<td class="formDeLabel" ><bean:message key="prompt.beginDate" /></td>
		    									<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.beginDateStr" /></td>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.assignedHours" /></td>
												<td class="formDe" width="40%"><bean:write name="scheduleNewEventForm" property="programReferral.assignedHours" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.courtOrdered" /></td>
												<td class="formDe">
													<logic:equal  name="scheduleNewEventForm" property="programReferral.courtOrdered" value="true" >
														<bean:message key="prompt.yes" />
													</logic:equal>
													<logic:notEqual  name="scheduleNewEventForm" property="programReferral.courtOrdered" value="true" >
														<bean:message key="prompt.no" />
													</logic:notEqual>
												</td>
												<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
												<td class="formDe">
													<logic:equal name="scheduleNewEventForm" property="programReferralNew" value="true">
														<bean:write name="scheduleNewEventForm" property="programReferral.referralState.description" />
													</logic:equal>
													<logic:equal name="scheduleNewEventForm" property="programReferralNew" value="false">
														<bean:write name="scheduleNewEventForm" property="programReferral.referralStatus" />
													</logic:equal>

												</td>
											</tr>
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan="4">
													<div  class='scrollingDiv100'>
														<table>
															<tr style="height:100%">
																<td><bean:write name="scheduleNewEventForm" property="programReferral.comments" /></td>
															</tr>	
														<%-- 	<logic:notEmpty name="scheduleNewEventForm" property="programReferral.referralComments" />
																<logic:iterate id="commentIndex" name="scheduleNewEventForm" property="programReferral.referralComments"  indexId="index">
																	<tr>
																		<td><bean:write name="commentIndex" property="comment" /></td>
																	</tr>
																	<tr><td>&nbsp;</td></tr>
																</logic:iterate> 
															</logic:notEmpty>  --%>
														</table>
													</div>											
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:equal>
						</table>
<%-- END PROGRAM REFERRAL DETAILS TABLE --%>						
						<div class='spacer'></div>	
<%-- BEGIN PROPOSED EVENT DETAILS TABLE --%>						  
						<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">      
							<tr> 
								<td class="detailHead" colspan='2'>
									<logic:equal name="scheduleNewEventForm"  property="pageState" value="Summary">
										Proposed 
									</logic:equal>
									<logic:equal name="scheduleNewEventForm"  property="pageState" value="Confirmation">
										Scheduled
									</logic:equal>
									<bean:message key="prompt.eventDetails" />
								</td>
							</tr>
							<tr>
								<td valign='top' align='center'>
									<table width='100%' cellpadding="2" cellspacing="1" class="borderTableGrey">
										<tr>
											<td class="formDeLabel"  width="1%" nowrap="nowrap"><bean:message key="prompt.serviceProvider" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="serviceProviderName"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.program" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="programName"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.serviceName" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="serviceName"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.eventType" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="eventType"/></td>
										</tr>
									</table>
									<div class='spacer'></div>
									<table width='100%' border='0' cellpadding="2" cellspacing="1">	
										<tr class='formDeLabel'>
											<td>&nbsp;</td>
											<td><bean:message key="prompt.eventDate" /></td>
											<td><bean:message key="prompt.eventTime" /></td>
										</tr>
										<logic:iterate id="eventIndex" name="scheduleNewEventForm" property="selectedEventsList"  indexId="index">
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td>&nbsp;</td>
												<td>
													<a href="javascript:openWindow('/<msp:webapp/>displayCalendarSPSelectEventsSummary.do?submitAction=<bean:message key='button.details'/>&eventId=<bean:write name="eventIndex" property='eventId'/>&currentJuvenileId=<bean:write name="scheduleNewEventForm" property='juvenileNum'/>')"><bean:write name="eventIndex" property="eventDate"  formatKey="date.format.mmddyyyy"/></a>
												</td>
												<td><bean:write name="eventIndex" property="eventDate" formatKey="time.format.hhmma"/></td>
											</tr>
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td class="boldText" width="1%" nowrap="nowrap"><bean:message key="prompt.comments" /></td>
												<td colspan="2"><bean:write name="eventIndex" property="eventComments" /></td>
											</tr>
										</logic:iterate> 
									</table>
								</td>
							</tr>			
						</table>
						<div class='spacer'></div>	
					</td>
				</tr>
			</table>
			<div class='spacer'></div>	
<%-- BEGIN BUTTON TABLE --%>			
			<table width="100%">
				<tr>
					<td align="center">
						<logic:equal name="scheduleNewEventForm"  property="pageState" value="Summary">
							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
							<logic:notEqual  name="scheduleNewEventForm" property="referralFound" value="true" >
								<html:submit property="submitAction"><bean:message key="button.createProgramReferral" /></html:submit>
							</logic:notEqual>
							<logic:equal name="scheduleNewEventForm" property="referralFound" value="true" >
								<%--Bug fix #28488  starts--%>		
								<html:submit property="submitAction" styleId="spFinishButton"> 
									<bean:message key="button.finish" />
								</html:submit>
								<%--Bug fix #28488  ends--%>	
							</logic:equal>
							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						</logic:equal>	
						<logic:equal name="scheduleNewEventForm"  property="pageState" value="Confirmation">
							<html:submit property="submitAction"><bean:message key="button.returnToCalendar"/></html:submit>
						</logic:equal>
					</td>
				</tr>
			</table>	
<%-- END BUTTON TABLE --%>				
		</td>
	</tr>			
</table>	
<%-- END DETAILS TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>