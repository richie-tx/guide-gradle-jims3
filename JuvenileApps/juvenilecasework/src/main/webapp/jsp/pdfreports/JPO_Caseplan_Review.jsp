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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.caseplan.form.JPOReviewPrintBean" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.JPO_CASEPLAN_REVIEW.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.JPO_CASEPLAN_REVIEW.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->

<!-- entry for PDFReporting.java -->
<!-- JPO_CASEPLAN_REVIEW("/jsp/pdfreports/JPO_Caseplan_Review.jsp","BFO_JPO_CASEPLAN_REVIEW.pdf"), -->

<style>
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
tbody
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:15;
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
img.display
    {	display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;}
span.underline
	{	border-bottom:1px solid #555;}
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
td.border
	{border-width: 1; 
		border: solid;
		font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
td.times-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
td.times-bold-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.times-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
th.border
	{	color:#fffffff;
		background-color:#000000;
		font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.times-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
p.times-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
p.times-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
p.times-white-10
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">		
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none"> 
		<tr>
			<td align="left" colspan="2"><img class="display" height="70" width="70" src="images/HarrisCountySeal.jpg"/></td>
		</tr>
		<tr>
			<td align="center" colspan="2" width="100%" padding-bottom="15"><b>BFO Harris County Juvenile Probation Department (HCJPD)</b></td>
		</tr>
		<tr>
			<td align="center" colspan="2" width="100%" padding-bottom="25"><b>Juvenile Probation Officer Case Plan Review</b></td>
		</tr>
	</table>
</div>
<div class="body">
	<table border-style="1" width="100%" padding-bottom="15">
		<tr>
			<th class="border"  align="center" colspan="2" width="100%" >
				<p class="times-12" align="center"><b>IDENTIFYING INFORMATION</b></p>
			</th>
		</tr>
		<tr >
			<td class="border" align="left" width="50%"><b>Child's Name: </b><%=reportInfo.getJuvDetails().getFullNameFirst()%></td>
			<td class="border"  align="left" width="50%"><b>County: </b>Harris</td>
		</tr>
		<tr >
			<td class="border" align="left" width="50%"><b>Child's Date of Birth: </b>
				<fmt:formatDate value="${reportInfo.juvDetails.dob}" pattern="MMM d, yyyy" var="formattedDob" />
										<c:out value="${formattedDob}"/>
			</td>
			<td class="border"  align="left" width="50%"><b>Caseworker PID: </b><%=reportInfo.getJuvDetails().getJuvNum()%></td>
		</tr>
		<tr >
			<td class="border" align="left" width="50%"><b>Harris County Case#: </b><%=reportInfo.getCasefileId()%></td>
			<td class="border"  align="left" width="50%"><b>Expected Supervision End Date: </b>
				<fmt:formatDate value="${reportInfo.expectedSupervisionEndDate}" pattern="MMM d, yyyy" var="formattedExpectedSupervisionEndDate" />
										<c:out value="${formattedExpectedSupervisionEndDate}"/>
			</td>
		</tr>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15">
		<tr padding-bottom="3">
			<th class="border" align="center" colspan="2" width="100%">
				<p class="times-12" align="center"><b>ASSIGNED GOALS</b></p>
			</th>
		</tr>
		<c:set var="goalsList" value="${reportInfo.goals}" scope="session"/>
			<c:forEach var="goal" items="${goalsList}" varStatus="status">
				<tr>
					<td align="left" colspan="2" width="100%">
						<p class="times-10" align="left"><b>Goal: </b>
							<c:out value="${goal.goal}"/>
						</p>
					</td>
				</tr>
				<tr align="left">
					<td align="left" colspan="2" width="100%">
						<p class="times-10" align="left"><b>Goal Status: </b>
							<c:out value="${goal.statusStr}"/>
						</p>
					</td>
				</tr>
				<tr align="left">
					<td align="left" colspan="2" width="100%">
						<p class="times-10" align="left"><b>Progress Notes: </b>
							<c:out value="${goal.progressNotes}"/>
						</p>
					</td>
				</tr>
			</c:forEach>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15">
		<tr padding-bottom="3">
			<th class="border" align="center" colspan="2" width="100%">
				<p class="times-12" align="center"><b>SUPERVISION LEVEL ASSIGNMENT</b></p>
			</th>
		</tr>
		<c:set var="riskAnalysisProgressAssessmentsList" value="${reportInfo.riskAnalysisProgressAssessments}" scope="session"/>
			<c:forEach var="riskAnalysisProgressAssessment" items="${riskAnalysisProgressAssessmentsList}" varStatus="status">
				<tr><td align="left" colspan="2">	
					<table width="100%" border-style="none">
						<tr	align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Date: </b>
									<fmt:formatDate value="${riskAnalysisProgressAssessments.entryDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
										<c:out value="${formattedEntryDate}"/>
									<c:out value="${}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Recommendations: </b>
									<c:out value="${riskAnalysisProgressAssessments.recommendation}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>JPO Comments: </b>
									<c:out value="${riskAnalysisProgressAssessments.comments}"/>
								</p>
							</td>
						</tr>
					</table>
				</td></tr>
			</c:forEach>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15" page-break-inside="auto">
		<thead>
		<tr padding-bottom="3">
			<th class="border" align="center" colspan="5" width="100%">
				<p class="times-12" align="center"><b>SERVICES PROVIDED</b></p>
			</th>
		</tr>
		<tr	align="left">
			<td align="left" width="40%">
				<p class="times-10" align="left"><u>Program Service Provider</u></p>
			</td>
			<td align="left" width="5%">
				<p class="times-10" align="left"><u>Start Date</u></p>
			</td>
			<td align="left" width="5%">
				<p class="times-10" align="left"><u>End Date</u></p>
			</td>
			<td align="left" width="39%">
				<p class="times-10" align="left"><u>Status</u></p>
			</td>
			<td align="left" width="10%">
				<p class="times-10" align="left"><u>Outcome</u></p>
			</td>
		</tr>
		</thead>
		<tbody>
		<c:set var="programReferralsList" value="${reportInfo.programReferrals}" scope="session"/>
			<c:forEach var="programReferral" items="${programReferralsList}" varStatus="status">
				<tr	align="left">
					<td align="left" width="40%">
						<p class="times-10" align="left">
							<c:out value="${programReferral.juvServiceProviderName}"/>
						</p>
					</td>
					<td align="left" width="5%">
						<p class="times-10" align="left">
							<c:out value="${programReferral.beginDateStr}"/>
						</p>
					</td>
					<td align="left" width="5%">
						<p class="times-10" align="left">
							<c:out value="${programReferral.endDateStr}"/>
						</p>
					</td>
					<td align="left" width="40%">
						<p class="times-10" align="left">
							<c:out value="${programReferral.referralState.description}"/>
						</p>
					</td>
					<td align="left" width="10%">
						<p class="times-10" align="left">
							<c:out value="${programReferral.outComeDescription}"/>
						</p>
					</td>
				</tr>
				<c:set var="referralCommentsList" value="${programReferral.referralComments}" scope="session"/>
					<tr	align="left">
						<td align="left" colspan="5">
							<p class="times-10" align="left"><u>Progress Notes:</u></p>
						</td>
					</tr>
					<c:forEach var="referralComment" items="${referralCommentsList}" varStatus="status">
						<tr><td align="left" colspan="5">	
							<p class="times-10" align="left">
								<fmt:formatDate value="${referralComment.commentsDate}" pattern="MMM d, yyyy" var="formattedCommentsDate" />
								[<c:out value="${formattedCommentsDate}"/> - 
								<c:out value="${referralComment.userName}"/>]
								<c:out value="${referralComment.commentText}"/>
							</p>
						</td></tr>
					</c:forEach>
				<c:set var="juvenileEventsList" value="${programReferral.juvenileEvents}" scope="session"/>
					<tr	align="left">
						<td align="left" width="40%">
							<p class="times-10" align="left"><u>Date of Service</u></p>
						</td>
						<td align="left" colspan="4">
							<p class="times-10" align="left"><u>Service Comments</u></p>
						</td>
					</tr>
					<c:forEach var="juvenileEvent" items="${juvenileEventsList}" varStatus="status">
						<tr	align="left">
							<td align="left" width="40%">
								<p class="times-10" align="left">
									<fmt:formatDate value="${juvenileEvent.eventDate}" pattern="MMM d, yyyy" var="formattedEventDate" />
									<c:out value="${formattedEventDate}"/>
								</p>
							</td>
							<td align="left" colspan="4">
								<p class="times-10" align="left">
									<c:out value="${juvenileEvent.eventComments}"/>
								</p>
							</td>
						</tr>
					</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15" page-break-inside="auto">
	<thead>
		<tr padding-top="15">
			<th class="border" align="center" colspan="5" width="100%">
				<p class="times-12" align="center"><b>CASEPLAN COMPLIANCE</b></p>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="revActivitiesList" value="${reportInfo.revActivities}" scope="session"/>
			<c:forEach var="revActivity" items="${revActivitiesList}" varStatus="status">
				<tr align="left">
							<td align="left" colspan="5" width="100%">
								<p class="times-10" align="left"><b>Entry Date: </b>
									<fmt:formatDate value="${revActivity.activityDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
									<c:out value="${formattedEntryDate}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="5" width="100%">
								<p class="times-10" align="left"><b>Comments: </b>
									<c:out value="${revActivity.comments}"/>
								</p>
							</td>
						</tr>
			</c:forEach>
	</tbody>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15">
		<tr padding-bottom="3">
			<th class="border" align="center" colspan="2" width="100%">
				<p class="times-12" align="center"><b>CONDITIONS OF PROBATION COMPLIANCE</b></p>
			</th>
		</tr>
		<c:set var="rulesList" value="${reportInfo.rules}" scope="session"/>
			<c:forEach var="rule" items="${rulesList}" varStatus="status">
				<tr><td align="left" colspan="2">	
					<table width="100%" border-style="none">
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Rule: </b>
									<c:out value="${rule.resolvedDesc}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Rule Status: </b>
									<c:out value="${rule.completionStatus}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Additional Information: </b>
									<c:out value="${rule.additionalInformation}"/>
								</p>
							</td>
						</tr>
					</table>
				</td></tr>
			</c:forEach>
	</table>
</div>
<div class="body">
	<table align="center"  width="100%" padding-bottom="15">
		<tr padding-bottom="3">
			<td colspan="2" width="100%">
				<p class="times-10" align="left"><u>Probation Compliance Comments:</u></p>
			</td>
		</tr>
		<c:set var="msrActivitiesList" value="${reportInfo.msrActivities}" scope="session"/>
			<c:forEach var="msrActivity" items="${msrActivitiesList}" varStatus="status">
				<tr><td align="left" colspan="2">	
					<table width="100%" border-style="none">
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Entry Date: </b>
									<fmt:formatDate value="${msrActivity.activityDate}" pattern="MMM d, yyyy" var="formattedActivityDate" />
									<c:out value="${formattedActivityDate}"/>
								</p>
							</td>
						</tr>
						<tr align="left">
							<td align="left" colspan="2" width="100%">
								<p class="times-10" align="left"><b>Comments: </b>
									<c:out value="${msrActivity.comments}"/>
								</p>
							</td>
						</tr>
					</table>
				</td></tr>
			</c:forEach>
	</table>
</div>
<div class="body">
	<table border-style="1" width="100%" padding-bottom="15">
		<tr >
			<td class="border" align="left" width="50%"><b>CHILD: </b></td>
			<td class="border"  align="left" width="50%"><b>DATE: </b></td>
		</tr>
		<tr >
			<td class="border" align="left" width="50%"><b>FAMILY: </b></td>
			<td class="border"  align="left" width="50%"><b>DATE: </b></td>
		</tr>
		<tr >
			<td class="border" align="left" width="50%"><b>JPO: </b></td>
			<td class="border"  align="left" width="50%"><b>DATE: </b></td>
		</tr>
	</table>
</div>
</body>
</pdf>