<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="journalSummaryTO" class="messaging.journal.to.JournalSummaryTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.CASEFILE_REVIEW_SUMMARY.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.CASEFILE_REVIEW_SUMMARY.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size:80; font-family:Helvetica; color:#F0F0F0; }
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;}
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
tr.border-bottom {
border-bottom: 1pt solid black;
}
tr.border-top {
border-top: 1pt solid black;
}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.row0 {
}
td.row1 {
background-color:#bfc1c2
}
p.arial-font-20
	{font-size:20;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
p.left
	{text-align:left;}
hr.left
	{text-align:left;}
</style>
	<macrolist>
		<macro id="watermark" >
			<p id="watermarkbody" rotate="-30" valign="middle" align="center">
			DRAFT
			</p>
		</macro>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				<tr>
				 	<td align="center" width="27%">
					</td>
				 	<td align="center" width="45%">
					</td>
					<td align="center" width="27%">
					</td>
				 </tr>
				<tr>
				 	<td align="center" width="27%">
					</td>
				 	<td align="center" width="45%">
					</td>
					<td align="center" width="27%">
					</td>
				 </tr>
				 <tr>
				 	<td align="center" width="27%">
				 		<p class="arial-font-8 left">
							<b>Date Range: </b><%=journalSummaryTO.getFromDateRange()%> - <%=journalSummaryTO.getToDateRange()%>
						</p>
					</td>
				 	<td align="center" width="45%">
				 		<p class="arial-font-8 centered">
							Page <pagenumber/> of <totalpages/>
						</p>
					</td>
					<td align="center" width="27%">
						<p class="arial-font-8 right">
							<fmt:formatDate value="${journalSummaryTO.currentDate}" pattern="MM/dd/yyyy" var="formattedDate" />
							<%=journalSummaryTO.getUserName()%> : <c:out value="${formattedDate}"/>
						</p>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<c:if test="${journalSummaryTO.draft == 'Y'}">
<body background-macro="watermark" footer="myfooter" footer-height="20mm">
</c:if>
<c:if test="${journalSummaryTO.draft != 'Y'}">
<body footer="myfooter" footer-height="20mm">
</c:if>
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr width="100%">
			<td width="10%" valign="top">
				<p class="centered">
					<img height="45" width="45" src="images/HarrisCountySeal.jpg"/>
				</p>
			</td>
			<td width="90%" class="helvetica-font-16" align="center">
			<p class="text-margin-0px bold centered">HARRIS COUNTY</p>
			<p class="text-margin-10px bold centered">JUVENILE PROBATION DEPARTMENT</p></td>
		</tr>	
	</table>
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" valign="top" width="92%">
				<p class="arial-font-12 bold centered">Probation Services Division/Court Services</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" valign="top" width="92%">
				<p class="arial-font-10 centered">1200 Congress Houston, TX 77002</p>
			</td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  START CASEFILE REPORT SECTION HEADER -->
	<table width="100%" border-style="none" align="center" class="margin-top-10px">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" width="92%" valign="top"><p class="arial-font-12"><b>JIMS2 Monthly Case Review</b></p></td>
		</tr>
	</table>
	<br/>
	<table width="100%" border-style="none" align="left" class="margin-top-5px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b>Date Range: </b><%=journalSummaryTO.getFromDateRange()%> - <%=journalSummaryTO.getToDateRange()%></p></td>
		</tr>
	</table>
	<br/>
	<table width="100%" border-style="none" align="center" class="margin-top-10px">
		<tr align="center">
			<td align="left" width="40%"><p class="arial-font-10 centered"><b>Juvenile:</b> <%=journalSummaryTO.getJuvenileName()%></p></td>
			<td align="left" width="60%"><p class="arial-font-10 centered"><b>Juvenile ID#:</b> <%=journalSummaryTO.getJuvenileId()%></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center" class="margin-top-0px" cellpadding="0">
		<tr align="center">
			<td align="left" width="40%"><p class="arial-font-10 centered"><b>Casefile:</b> <%=journalSummaryTO.getCaseFileId()%></p></td>
			<td align="left" width="60%"><p class="arial-font-10 centered"><b>Supervision Type:</b> <%=journalSummaryTO.getSupervisionType()%></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center" class="margin-top-0px" padding-top="0" padding-bottom="10">
		<tr align="center">
			<td align="left" width="40%"><p class="arial-font-10 centered"><b>Probation Officer:</b> <%=journalSummaryTO.getProbationOfficer()%></p></td>
			<td align="left" width="60%"></td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
</div>	
<!-- END CASEFILE REPORT SECTION HEADER -->
<!-- START REFERRALS SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Referrals Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Assigned Referrals:</b> <%=journalSummaryTO.getSummaryAssignedReferrals()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Scheduled Court Date(s):</b> <%=journalSummaryTO.getSummaryScheduledCourtDates()%>
				</p>
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END REFERRALS SUMMARY SECTION -->
<!-- START MAYSI-2 SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>MAYSI-2 Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-5px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Subsequent Needed:</b><%=journalSummaryTO.getSummaryMaysiSubsequentNeeded()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-5px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Subsequent Done:</b><%=journalSummaryTO.getSummaryMaysiSubsequentDone()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-5px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Subsequent Not Needed:</b><%=journalSummaryTO.getSummaryMaysiSubsequentNotNeeded()%>
				</p>
			</td>
		</tr>
	</table>
		<table width="100%" border-style="none" align="left" class="margin-top-5px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>No MAYSI:</b><%=journalSummaryTO.getSummaryMaysiNone()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END MAYSI-2 SUMMARY SECTION -->
<!-- START ACTIVITIES SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Activities Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Total Count:</b>
				<%=journalSummaryTO.getSummaryActivityEntriesCount()%>
				</p>
			</td>
		</tr>
	</table>	
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<thead>
		<tr align="left" width="100%" class="border-top border-bottom">
			<td align="left" width="40%" valign="top"><p class="arial-font-10 left"><b>Activity</b></p></td>
			<td align="left" width="10%" valign="top"><p class="arial-font-10 left"><b>Date</b></p></td>
			<td align="left" width="10%" valign="top"><p class="arial-font-10 left"><b>Time</b></p></td>		
		</tr>
		</thead>
		<tbody>
			<c:set var="counter" value="${0}" var="activitiesList" value="${journalSummaryTO.summaryActivityEntries}"  scope="session"/>
			<c:set var="total" value="${journalSummaryTO.summaryActivityEntriesCount}" scope="session"/>
			<c:forEach var="activity" items="${activitiesList}" varStatus="loop">	
			<c:if test="${loop.count == total}">
				<tr align="left"  width="100%" class="border-bottom">
			</c:if>
			<c:if test="${loop.count != total}">
				<tr align="left"  width="100%">
			</c:if>
			<td align="left" width="40%" valign="top" class="${counter % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
				<fmt:formatDate value="${activity.activityDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				<b>
					<c:out value="${activity.activityDesc}"/>
				</b>
				</p>
			</td> 
			<td align="left" width="10%" valign="top" class="${counter % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${formattedDate}"/>
				</p>
			</td>
			<td align="left" width="10%" valign="top" class="${counter % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${activity.activityTime}"/>
				</p>
			</td>
			</tr>
			<c:set var="counter" value="${counter + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END ACTIVITIES SUMMARY SECTION -->

<!-- START FACILITY ADMIT/RELEASE ACTIVITIES SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Facility Activities Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Total Count:</b>
				<%=journalSummaryTO.getSummaryFacilityActivityEntriesCount()%>
				</p>
			</td>
		</tr>
	</table>	
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<thead>
		<tr align="left" width="100%" class="border-top border-bottom">
			<td align="left" width="40%" valign="top"><p class="arial-font-10 left"><b>Facility Activity</b></p></td>
			<td align="left" width="10%" valign="top"><p class="arial-font-10 left"><b>Date</b></p></td>		
		</tr>
		</thead>
		<tbody>
			<c:set var="counter2" value="${0}" var="facilityActivitiesList" value="${journalSummaryTO.summaryFacilityActivityEntries}"  scope="session"/>
			<c:set var="total2" value="${journalSummaryTO.summaryFacilityActivityEntriesCount}" scope="session"/>
			<c:forEach var="facilityActivity" items="${facilityActivitiesList}" varStatus="loop">	
			<c:if test="${loop.count == total2}">
				<tr align="left"  width="100%" class="border-bottom">
			</c:if>
			<c:if test="${loop.count != total2}">
				<tr align="left"  width="100%">
			</c:if>
			<td align="left" width="40%" valign="top" class="${counter2 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
				<fmt:formatDate value="${facilityActivity.activityDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				<b>
					<c:out value="${facilityActivity.activityDesc}"/>
				</b>
				</p>
			</td> 
			<td align="left" width="10%" valign="top" class="${counter2 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${formattedDate}"/>
				</p>
			</td>
			</tr>
			<c:set var="counter2" value="${counter2 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table> 
<!-- END FACILITY ADMIT/RELEASE ACTIVITIES SUMMARY SECTION -->

<!-- START CALENDAR EVENTS SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Calendar Events Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Total Count:</b>
				<%=journalSummaryTO.getSummaryCalendarEntriesCount()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="30%" valign="top"><p class="arial-font-10 left"><b>Event Type</b></p></td>
				<td align="left" width="10%" valign="top"><p class="arial-font-10 left"><b>Date</b></p></td>				
				<td align="left" width="15%" valign="top"><p class="arial-font-10 left"><b>Status</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter3" value="${0}" var="calendarEventList" value="${journalSummaryTO.summaryCalendarEntries}"  scope="session"/>
			<c:set var="total3" value="${journalSummaryTO.summaryCalendarEntriesCount}" scope="session"/>
			<c:forEach var="event" items="${calendarEventList}" varStatus="loop">	
			<c:if test="${loop.count == total3}">
				<tr align="left"  width="100%" class="border-bottom">
			</c:if>
			<c:if test="${loop.count != total3}">
				<tr align="left"  width="100%">
			</c:if>
			<td align="left" width="30%" valign="top" class="${counter3 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
				<fmt:formatDate value="${event.eventDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				<b>
					<c:out value="${event.eventType}"/>
				</b>
			</p>
			</td> 
			<td align="left" width="10%" valign="top" class="${counter3 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${formattedDate}"/>
				</p>
			</td>
			<td align="left" width="15%" valign="top" class="${counter3 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${event.juvenileAttendanceStatus}"/>
				</p>
			</td>
			</tr>
			<c:set var="counter3" value="${counter3 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END CALENDAR EVENTS SUMMARY SECTION -->
<!-- START PROGRAM REFERRALS SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Program Referrals Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Accepted:</b>   <%=journalSummaryTO.getSummaryAcceptedProgramReferralsCount()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Closed:</b>      <%=journalSummaryTO.getSummaryClosedProgramReferralsCount()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Tentative Referred:</b>   <%=journalSummaryTO.getSummaryTentativeReferredProgramReferralsCount()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Total:</b>        <%=journalSummaryTO.getSummaryProgramReferralsTotalCount()%>
				</p>
			</td>
		</tr>
	</table>
<!-- START SERVICE PROVIDER SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-10px">
		<thead>
		</thead>
		<tbody>
			<c:set var="programReferralList" value="${journalSummaryTO.summaryAllReferredProgramReferrals}"  scope="session"/>
			<c:forEach var="referral" items="${programReferralList}" varStatus="loop">	
				<tr align="left">
					<td colspan="3" align="left" width="100%" valign="top"></td>
				</tr>
				<tr align="left">
					<td colspan="3" align="left" width="100%" valign="top"></td>
				</tr>
				<tr align="left">
					<td colspan="3" align="left" width="100%" valign="top">
						<p class="arial-font-10 left"><b>Service Provider:</b>  <c:out value="${referral.juvServiceProviderName}"/>
						</p>
					</td>
				</tr>
				<tr align="left">
					<td colspan="3"  align="left" width="100%" valign="top">
						<p class="arial-font-10 left"><b>Program:</b>   <c:out value="${referral.providerProgramName}"/>
						</p>
					</td>
				</tr>
				<tr align="left">
					<td colspan="3"  align="left" width="100%" valign="top">
						<p class="arial-font-10 left"><b>Status:</b>  <c:out value="${referral.referralStatusDescription}"/>
						</p>
					</td>
				</tr>
				<tr align="left" width="100%">
					<td align="left" width="33%" valign="top">
						<p class="arial-font-10 left"><fmt:formatDate value="${referral.beginDate}" pattern="MM/dd/yyyy" var="formattedDate" />
							<b>Begin Date:</b>   <c:out value="${formattedDate}"/>
						</p>
					</td>
					<td align="left" width="33%" valign="top">
						<p class="arial-font-10 left"><fmt:formatDate value="${referral.endDate}" pattern="MM/dd/yyyy" var="formattedDate" />
							<b>End Date:</b>   <c:out value="${formattedDate}"/>
						</p>
					</td>
					<td align="left" width="33%" valign="top">
						<p class="arial-font-10 left"><b>Outcome:</b>   <c:out value="${referral.outComeDesc}"/>
						</p>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>	
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END SERVICE PROVIDER SECTION -->
<!-- START RISK ANALYSIS SUMMARY SECTION -->
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><b><u>Risk Analysis Summary</u></b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="12%" valign="top"><p class="arial-font-10 left"><b>Date</b></p></td>
				<td align="left" width="15%" valign="top"><p class="arial-font-10 left"><b>Risk Analysis</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>Supervision Level</b></p></td>
				<td align="left" width="25%" valign="top"><p class="arial-font-10 left"><b>Recommendation</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter4" value="${0}" var="riskAnalysisList" value="${journalSummaryTO.summaryRiskAnalysis}"  scope="session"/>
			<c:set var="total4" value="${journalSummaryTO.summaryRiskAnalysisCount}" scope="session"/>
			<c:forEach var="riskAnalysis" items="${riskAnalysisList}" varStatus="loop">	
				<c:if test="${loop.count == total4}">
					<tr align="left"  width="80%" class="border-bottom">
				</c:if>
				<c:if test="${loop.count != total4}">
					<tr align="left"  width="100%">
				</c:if>
					<td align="left" width="12%" valign="top" style="background-color:#bfc1c2" class="${counter4 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-10 left" ><c:out value="${riskAnalysis.entryDate}"/>
						</p>
					</td>
					<td align="left" width="15%" valign="top" style="background-color:#bfc1c2" class="${counter4 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-10 left"><c:out value="${riskAnalysis.riskAnalysis}"/>
						</p>
					</td>
					<td align="left" width="20%" valign="top" style="background-color:#bfc1c2" class="${counter4 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-10 left"><c:out value="${riskAnalysis.supervisionLevel}"/>
						</p>
					</td>
					<td align="left" width="25%" valign="top" style="background-color:#bfc1c2" class="${counter4 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-10 left"><c:out value="${riskAnalysis.recommendation}"/>
						</p>
					</td>
				</tr>
				<c:set var="counter4" value="${counter4 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-0px" page-break-after="always">
		<tr>
			<td align="left" width="100%" colspan="2">
				<p class="arial-font-9">
				------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				</p>
			</td>
		</tr>
	</table>
<!-- END RISK ANALYSIS SUMMARY SECTION -->
<!-- START CASEFILE REQUIREMENTS OVERVIEW SECTION -->
	<table width="100%" border-style="none" align="center" class="margin-top-0px">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" width="92%" valign="top"><p class="arial-font-10"><b>Casefile Requirements Overview</b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-10px">
		<tr align="left">
			<td width="0%" >
			</td>
			<td align="left" width="100%" valign="top">
				<p class="arial-font-10 left"><b>Supervision Level:</b><%=journalSummaryTO.getSummaryOverviewSupervisionLevel()%>
				</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-20px">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="50%" valign="top"><p class="arial-font-10 left"><b>Activities</b></p></td>
				<td align="left" width="15%" valign="top"><p class="arial-font-10 left"><b>Quantity</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter5" value="${0}" var="overviewActivitiesList" value="${journalSummaryTO.overviewActivitySummaryEntries}"  scope="session"/>
			<c:set var="total5" value="${journalSummaryTO.overviewActivitySummaryEntriesCount}" scope="session"/>
			<c:forEach var="overviewActivity" items="${overviewActivitiesList}" varStatus="loop">	
			<c:if test="${loop.count == total5}">
				<tr align="left"  width="100%" class="border-bottom">
			</c:if>
			<c:if test="${loop.count != total5}">
				<tr align="left"  width="100%">
			</c:if>	
			<td align="left" width="50%" valign="top" class="${counter5 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
				<b>
					<c:out value="${overviewActivity.activityDescription}"/>
				</b>
				</p>
			</td> 
			<td align="left" width="15%" valign="top" class="${counter5 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${overviewActivity.quantity}"/>
				</p>
			</td>
			</tr>
			<c:set var="counter5" value="${counter5 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	
	<table width="100%" border-style="none" align="left" class="margin-top-20px">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="50%" valign="top"><p class="arial-font-10 left"><b>Facility Activities</b></p></td>
				<td align="left" width="15%" valign="top"><p class="arial-font-10 left"><b>Quantity</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter6" value="${0}" var="overviewFacilityActivitiesList" value="${journalSummaryTO.overviewFacilityActivitySummaryEntries}"  scope="session"/>
			<c:set var="total6" value="${journalSummaryTO.overviewFacilityActivitySummaryEntriesCount}" scope="session"/>
			<c:forEach var="overviewFacilityActivity" items="${overviewFacilityActivitiesList}" varStatus="loop">	
			<c:if test="${loop.count == total6}">
				<tr align="left"  width="100%" class="border-bottom">
			</c:if>
			<c:if test="${loop.count != total6}">
				<tr align="left"  width="100%">
			</c:if>	
			<td align="left" width="50%" valign="top" class="${counter6 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
				<b>
					<c:out value="${overviewFacilityActivity.activityDescription}"/>
				</b>
				</p>
			</td> 
			<td align="left" width="15%" valign="top" class="${counter6 % 2 == 0 ? 'row0' : 'row1'}">
				<p class="arial-font-8 left">
					<c:out value="${overviewFacilityActivity.quantity}"/>
				</p>
			</td>
			</tr>
			<c:set var="counter6" value="${counter6 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	
	<table width="100%" border-style="none" align="left" class="margin-top-20px">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>Calendar Events</b></p></td>
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>SCHEDULED</b></p></td>
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>ATTENDED</b></p></td>
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>ABSENT</b></p></td>
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>EXCUSED</b></p></td>
				<td align="left" width="17%" valign="top"><p class="arial-font-10 left"><b>% ATTENDED</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter7" value="${0}" var="overviewCalendarEventList" value="${journalSummaryTO.overviewCalendarEventSummaryEntries}"  scope="session"/>
			<c:set var="total7" value="${journalSummaryTO.overviewCalendarEventSummaryEntriesCount}" scope="session"/>
			<c:forEach var="overviewEvent" items="${overviewCalendarEventList}" varStatus="loop">	
				<c:if test="${loop.count == total7}">
					<tr align="left"  width="100%" class="border-bottom">
				</c:if>
				<c:if test="${loop.count != total7}">
					<tr align="left"  width="100%">
				</c:if>	
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.eventDescription}"/>
						</b>
						</p>
					</td> 
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.scheduled}"/>
						</b>
						</p>
					</td> 				
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.attended}"/>
						</b>
						</p>
					</td> 	
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.absent}"/>
						</b>
						</p>
					</td> 					
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.excused}"/>
						</b>
						</p>
					</td> 	
					<td align="left" width="17%" valign="top" class="${counter7 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${overviewEvent.percentAttended}"/>%
						</b>
						</p>
					</td> 				
				</tr>
				<c:set var="counter7" value="${counter7 + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-20px">
		<c:if test="${journalSummaryTO.numProgressRisks > 0}">
		<thead>
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="40%" valign="top"><p class="arial-font-10 left"><b>Progress Risk</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>IMPROVEMENT</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>REGRESSION</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>NO CHANGE</b></p></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="counter8" value="${0}" var="overviewProgressRiskEventList" value="${journalSummaryTO.overviewProgressRiskSummaryEntries}"  scope="session"/>
			<c:set var="total8" value="${journalSummaryTO.overviewProgressRiskSummaryEntriesCount}" scope="session"/>
			<c:forEach var="progressRiskEvent" items="${overviewProgressRiskEventList}" varStatus="loop">	
			<c:if test="${progressRiskEvent.assessmentType == 'PROGRESS'}">
				<c:if test="${loop.count == total8}">
					<tr align="left"  width="100%" class="border-bottom">
				</c:if>
				<c:if test="${loop.count != total8}">
					<tr align="left"  width="100%">
				</c:if>	
					<td align="left" width="40%" valign="top" class="${counter8 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${progressRiskEvent.progressRiskQuestionText}"/>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter8 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.improvement == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter8 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.regression == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter8 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.noChange == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
				</tr>
			<c:set var="counter8" value="${counter8 + 1}"/>
			</c:if>
			</c:forEach>
		</tbody>
		</c:if>
	</table>
	<table width="100%" border-style="none" align="left" class="margin-top-20px">
		
		<c:if test="${journalSummaryTO.numResProgRisks > '0'}"> 
		<thead>
			
			<tr align="left" width="100%" class="border-top border-bottom">
				<td align="left" width="40%" valign="top"><p class="arial-font-10 left"><b>Residential Progress Risk</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>IMPROVEMENT</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>REGRESSION</b></p></td>
				<td align="left" width="20%" valign="top"><p class="arial-font-10 left"><b>NO CHANGE</b></p></td>
			</tr>
			
		</thead>
		<tbody>		 
				
			<c:set var="counter9" value="${0}" var="overviewProgressRiskEventList" value="${journalSummaryTO.overviewProgressRiskSummaryEntries}"  scope="session"/>
			<c:set var="total9" value="${journalSummaryTO.overviewProgressRiskSummaryEntriesCount}" scope="session"/>
			<c:forEach var="progressRiskEvent" items="${overviewProgressRiskEventList}" varStatus="loop">	
			<c:if test="${progressRiskEvent.assessmentType == 'RESIDENTIAL PROGRESS'}">
				<c:if test="${loop.count == total9}">
					<tr align="left"  width="100%" class="border-bottom">
				</c:if>
				<c:if test="${loop.count != total9}">
					<tr align="left"  width="100%">
				</c:if>	
				
					<td align="left" width="40%" valign="top" class="${counter9 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 left">
						<b>
							<c:out value="${progressRiskEvent.progressRiskQuestionText}"/>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter9 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.improvement == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter9 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.regression == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
					<td align="center" width="20%" valign="top" class="${counter9 % 2 == 0 ? 'row0' : 'row1'}">
						<p class="arial-font-8 center">
						<b>
							<c:if test="${progressRiskEvent.noChange == 'true'}">
							X
							</c:if>
						</b>
						</p>
					</td> 
				</tr>
			<c:set var="counter9" value="${counter9 + 1}"/>
			</c:if>
			</c:forEach>
			
		</tbody>
		</c:if>
	</table>
	<c:if test="${not empty journalSummaryTO.overviewProgressRiskSummaryEntries }">
		<table width="100%" border-style="none" align="left" class="margin-top-0px" page-break-after="always">
			<tr align="left" width="100%">
				<td align="left" width="100%" valign="top"><p class="arial-font-10 left"><i>* In comparison to previous risk assessment</i></p></td>
			</tr>
		</table>
	</c:if>
<!-- END CASEFILE REQUIREMENTS OVERVIEW SECTION -->			
</body>
</pdf>
