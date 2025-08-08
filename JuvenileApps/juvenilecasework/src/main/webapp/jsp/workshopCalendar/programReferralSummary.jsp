<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2006 - daw - create jsp --%>
<%-- 06/28/2011 CShimek aligned code for better display --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
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
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;programReferralCreate.jsp</title>
<!-- Javascript for emulated navigation -->

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<html:base />

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="">
<html:form action="/submitProgramReferralfromWorkshop" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|347">

<!-- BEGIN HEADING TABLE -->

<table width='100%'>
	<tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework" />&nbsp; - Workshop Calendar - &nbsp;<bean:message key="title.create" />&nbsp;<bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.summary" /></td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<%-- Begin Pagination Header Tag--%>

<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Review information, then select the Finish button to save the information.</li>	    
        <li>Select the Back button to return to the previous page to change information.</li>
      </ul>
    </td>
  </tr>
  <tr>
		<td class="required"><img src=/<msp:webapp/>images/required.gif title=required alt="required image" hspace=0 vspace=0>&nbsp;<bean:message key="prompt.requiredFieldsInstruction" /></td>
	</tr>
</table>

<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<%-- <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert> --%>
<!-- END HEADER INFO TABLE -->
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
		<%--tabs start--%>
			<%-- <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="programreferraltab" />
				<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
			</tiles:insert> --%>
		<%--tabs end--%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align=center>                
						<div class=spacer></div>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td width='1%' class=detailHead><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');"><img border=0 src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
							</tr>
							<tr id="achar0" class='hidden'>
								<td>
<!-- BEGIN ATTENDEE TABLE -->
									<table align="center" width="100%" cellpadding="2" cellspacing="1">  
										<tr>
											<logic:empty name="scheduleNewEventForm" property="programReferral.existingReferrals">
												<td valign="top">Juvenile has no past attendance for program</td>
											</logic:empty>

											<logic:notEmpty name="scheduleNewEventForm" property="programReferral.existingReferrals">
												<td valign="top">
													<table width='100%' cellpadding="2" cellspacing="1">
														<tr bgcolor='#cccccc' class=subHead>
															<td><bean:message key="prompt.programReferral#" /></td>
															<td><bean:message key="prompt.date" /></td>
															<td><bean:message key="prompt.service" /></td>
															<td align=center><bean:message key="prompt.attended" /></td>
															<td align=center><bean:message key="prompt.absent" /></td>
															<td align=center><bean:message key="prompt.excused" /></td>
														</tr>
														<logic:iterate id="programRef" name="scheduleNewEventForm" property="programReferral.existingReferrals" indexId="index">
															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																<td><bean:write name="programRef" property="programReferralId"/></td>
																<td><bean:write name="programRef" property="startDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
																<td><bean:write name="programRef" property="serviceName"/></td>
																<td align="center">			
																	<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>						
																</td>	
																<td align="center">			
																	<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
																</td>		
																<td align="center">			
																<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
																</td>	
															</tr>
														</logic:iterate>     
													</table>
												</td>
											</logic:notEmpty>
										</tr>
									</table>
								</td>
							</tr>
						</table>
<!-- END ATTENDEE TABLE -->
						<div class=spacer></div>
<!--program referral start-->
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class=detailHead><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:message key="prompt.program" />:&nbsp;<bean:write name="scheduleNewEventForm" property="programName"/></td>
							</tr>
 							<tr>
								<td>
									<table cellpadding=2 cellspacing=1 width='100%'>
										<tr>								
											<td class="formDeLabel"><bean:message key="prompt.beginDate" /></td>
											<td valign="top" class="formDe" width="50%">
												<bean:write name="scheduleNewEventForm" property="programReferral.beginDateStr"/> 
											</td>						   
											<td class="formDeLabel"><bean:message key="prompt.endDate" /></td>
											<td valign="top" class="formDe">
												<bean:write name="scheduleNewEventForm" property="programReferral.endDateStr"/> 
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.assignedHours" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.assignedHours"/></td>
											<td class="formDeLabel"><bean:message key="prompt.courtOrdered" /></td>	
											<bean:define id="progRef" name="scheduleNewEventForm" property="programReferral"/>		
											<td class="formDe"><jims2:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.referralStatus" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.referralState.description"/></td>
											<td class="formDeLabel"><bean:message key="prompt.sentDate" /></td>
											<td valign="top" class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.outcome" /></td>
											<td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.outComeDescription"/></td>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.acknowledgementDate" /></td>
											<td valign="top" class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" /></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4">
												<div class="scrollingDiv100">
													<table>
														<tr style="height:100%"><td><bean:write name="scheduleNewEventForm" property="programReferral.comments"/></td></tr>
													</table>
												</div>								
											</td>							 
										</tr>
									</table>
								</td>
							</tr>
						</table>
<!-- program referral end -->
						<div class=spacer></div>
<!-- BEGIN BUTTON TABLE -->
						<table width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>				  
									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>