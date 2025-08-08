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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.MaysiDataReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.MAYSI_MENTAL_HEALTH_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.MAYSI_MENTAL_HEALTH_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: 5px;
    	margin-right: 5px;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
	}
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
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
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

.table-detail {
	width: 100%;
}
	
.table-detail table {	
	width: 100%;
	font-family: sans-serif, Arial;
	table-layout: fixed;
}

.table-detail tr {
	width: 100%;
	padding: 5px;
}

.table-detail td {
	width: 300px;
	padding: 1px;
	margin: 0;
}
.table-detail p {
	margin-left: 3px;
}

td.label {
	font-weight: bold;
	text-align: left;
}

.line {
	background-color: #7CB9E8;
	height: 1.5px;
	width: 100%;
}

.warningText {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: small;
	font-weight: normal;
	color: #000000;
	background-color: #FF0000;
}


.cautionText {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: small;
	font-weight: normal;
	color: #000000;
	background-color: #FFFF00;
}

.section-header {
	font-size: 14px;
	font-weight: bold;
	padding: 0;
}

.sub-header {
	font-size: 14px;
	margin-top: 15px;
}
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

<body footer="myfooter" footer-height="2mm">
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr>
			<td align="center" colspan="2" font-size="14"><b>JIMS2 MAYSI - Assessment Details</b></td>
		</tr>
</table>

<c:set var="maysi" value="${reportInfo}" scope="session"/>
	<table class="sub-header" align="center" width="100%" border-style="none">
		<tr>
			<td align="right"><b>Juvenile Number: </b></td>
			<td><p><%=reportInfo.getJuvenileNum()%></p></td>
			<td align="right"><b>Juvenile Name: </b></td>
			<td><p><%=reportInfo.getJuvenileName()%></p></td>
		</tr>
		<tr>
			<td align="right"><b>Current Age: </b></td>
			<td><p><%=reportInfo.getJuvenileAge()%></p></td>
			<td align="right"><b>Juvenile Status: </b></td>
			<td><p><%=reportInfo.getJuvenileStatus()%></p></td>
		</tr>
	</table>
	
</div>
<hr class="line" />

<%-- END HEADER  --%>
<div class='spacer'></div>
<div class="body">
	<p class="section-header">General Assesment Details</p>
	<table class="table-detail">
		<tr>
			<td class="label">Referral Number</td>
			<td><p><%=reportInfo.getReferralNumber()%></p></td>
			<td class="label">&nbsp;</td>
			<td><p>&nbsp;</p></td>
		</tr>
		<tr>
			<td class="label">Sex</td>
			<td><p><%=reportInfo.getSex()%></p></td>
			<td class="label">Race</td>
			<td><p><%=reportInfo.getRace()%></p></td>
		</tr>
		<tr>
			<td class="label">Assessment Date</td>
			<td><p><%=reportInfo.getAssessmentDate()%></p></td>
			<td class="label">Assessment Time</td>
			<td><p><%=reportInfo.getAssessmentTime()%></p></td>
		</tr>
		<tr>
			<td class="label">Assessment Review Date</td>
			<td>
				<p><%=reportInfo.getAssessmentReviewDate()%></p>
			</td>
			<td class="label">Assessment Review Time</td>
			<td><p><%=reportInfo.getAssessmentReviewTime()%></p></td>
		</tr>
		<tr>
			<td class="label">Assessment Officer</td>
			<td><p><%=reportInfo.getAssessmentOfficerName()%></p></td>
			<td class="label">Assessment Option</td>
			<td><p><%=reportInfo.getAssessmentOption()%></p></td>
		</tr>
		<tr>
			<td class="label">Test Age</td>
			<td><p><%=reportInfo.getTestAge()%></p></td>
			<td class="label"><p style="text-align: left">Has the youth taken a MAYSI before?</p></td>
			<td>
				<p><%=reportInfo.getHasPreviousMAYSI()%></p>
			</td>			
		</tr>
		<tr>
			<td class="label"><p style="text-align: left">Was the MAYSI administered?</p></td>
			<td><p><%=reportInfo.getAdminister()%></p></td>
			<td class="label"><p style="text-align: left">Identify reason MAYSI was not administered?</p></td>
			<td>
				<p style="text-align: left"><%=reportInfo.getReasonNotDone()%></p>
				<logic:notEqual name="maysi" property="scheduledOffIntDateStr" value="">
					<p>  
						(Scheduled for <p><%=reportInfo.getScheduledOffIntDateStr()%></p> )
					</p>           										
				</logic:notEqual>
			</td>
		</tr>
		<logic:notEqual name="maysi" property="otherReasonNotDone" value="">
			<tr>
				<td class="label">Detailed reason for other</td>
				<td>
					<p style="text-align: left"><%=reportInfo.getOtherReasonNotDone()%></p>
				</td>
				<td class="label">&nbsp;</td>
				<td><p>&nbsp;</p></td>
			</tr>
		</logic:notEqual>
		<tr>
			<td class="label">Location Unit</td>
			<td><p style="text-align: left"><%=reportInfo.getLocationUnit()%></p></td>
			<td class="label"><p style="text-align: left">How long has youth been at this location?</p></td>
			<td><p style="text-align: left"><%=reportInfo.getLengthOfStay()%></p></td>
		</tr>

	</table>
		
	<hr class="line" />
		<p class="section-header">Health Assesment Details</p>
		<logic:notEqual name="maysi" property="hasDetails" value="true">
			<table class="table-detail">
				<tr>
					<td colspan="4">No Test Record on File</td>
				</tr>
			</table>
		</logic:notEqual>
		<logic:equal name="maysi" property="hasDetails" value="true">
			<table class="table-detail">
			<tr>
				<td class="label">Screen Date</td>
				<td><p><%=reportInfo.getScreenDate()%></p></td>
				<td class="label"><p>Type of Facility </p></td>
				<td><p><%=reportInfo.getFacilityType()%></p></td>
			</tr>
			<tr>
				<td class="label">Alcohol/Drug</td>
				<td><p><%=reportInfo.getAlcoholDrug()%></p></td>
				<logic:equal name="maysi" property="alcoholDrugWarning" value="true">
						<td class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="alcoholDrugCaution" value="true">
						<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="alcoholDrugWarning" value="true">
						<logic:notEqual name="maysi" property="alcoholDrugCaution" value="true">
							<td valign="top" class="formDe" colspan="2">&nbsp;</td>
						</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Angry/Irritable</td>
				<td><p><%=reportInfo.getAngryIrritable()%></p></td>
				<logic:equal name="maysi" property="angryIrritableWarning" value="true">
					<td valign="top" class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="angryIrritableCaution" value="true">
					<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="angryIrritableWarning" value="true">
					<logic:notEqual name="maysi" property="angryIrritableCaution" value="true">
						<td valign="top" class="formDe" colspan="2">&nbsp;</td>
					</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Depression/Anxiety</td>
				<td><p><%=reportInfo.getDepressionAnxiety()%></p></td>
				<logic:equal name="maysi" property="depressionAnxietyWarning" value="true">
					<td valign="top" class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="depressionAnxietyCaution" value="true">
					<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="depressionAnxietyWarning" value="true">
					<logic:notEqual name="maysi" property="depressionAnxietyCaution" value="true">
						<td valign="top" class="formDe" colspan="2">&nbsp;</td>
					</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Somatic Complaint</td>
				<td><p><%=reportInfo.getSomaticComplaint()%></p></td>
				<logic:equal name="maysi" property="somaticComplaintWarning" value="true">
					<td valign="top" class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="somaticComplaintCaution" value="true">
					<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="somaticComplaintWarning" value="true">
					<logic:notEqual name="maysi" property="somaticComplaintCaution" value="true">
						<td valign="top" class="formDe" colspan="2">&nbsp;</td>
					</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Suicide/Ideation</td>
				<td><p><%=reportInfo.getSuicideIdeation()%></p></td>
				<logic:equal name="maysi" property="suicideIdeationWarning" value="true">
					<td valign="top" class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="suicideIdeationCaution" value="true">
					<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="suicideIdeationWarning" value="true">
					<logic:notEqual name="maysi" property="suicideIdeationCaution" value="true">
						<td valign="top" class="formDe" colspan="2">&nbsp;</td>
					</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Thought Disturbance</td>
				<td><p><%=reportInfo.getThoughtDisturbance()%></p></td>
				<logic:equal name="maysi" property="thoughtDisturbanceWarning" value="true">
					<td valign="top" class="warningText" colspan="2">Warning</td>
				</logic:equal>
				<logic:equal name="maysi" property="thoughtDisturbanceCaution" value="true">
					<td valign="top" class="cautionText" colspan="2">Caution</td>
				</logic:equal>
				<logic:notEqual name="maysi" property="thoughtDisturbanceWarning" value="true">
					<logic:notEqual name="maysi" property="thoughtDisturbanceCaution" value="true">
						<td valign="top" class="formDe" colspan="2">&nbsp;</td>
					</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<td class="label">Traumatic Expression</td>
				<td><p><%=reportInfo.getTraumaticExpression()%></p></td>				
				 <td>&nbsp;</td>
				 <td>&nbsp;</td>
			</tr>			
	
		</table>
	</logic:equal>
		<hr class="line" />
</div>
<div class="body" page-break-after="always">
	<p class="section-header">Subsequent Assessment Details</p>
	
	<logic:equal name="maysi" property="hasSubAssessment" value="false">
		<table class="table-detail">
			<tr>
				<td colspan="4">No Subsequent Assessment Entered</td>
			</tr>
		</table>
	</logic:equal>
	<logic:equal name="maysi" property="hasSubAssessment" value="true">		
		
		<table class="table-detail">
		<tr>
			<td class="label" colspan="3">
				<p style="text-align: left">
					Was the child referred to a mental health professional for a subsequent assessment based on the MAYSI results?</p>
				</td>
			<td><p>&nbsp;&nbsp;&nbsp;<%=reportInfo.getSubsAssessmentReferral()%></p></td>
		</tr>
		<tr>
			<td class="label" colspan="3">To what type of provider was the juvenile referred?</td>
			<td>
				<p>&nbsp;&nbsp;&nbsp;<%=reportInfo.getProviderReferredType()%></p>
			</td>
		</tr>		
		<tr>
			<td class="label"  colspan="3">
				<p style="text-align: left">Was an assessment completed on this youth following the mental health assessment indicating the juvenile may have mental health issues?</p>
			</td>
			<td>
				<p>&nbsp;&nbsp;&nbsp;<%=reportInfo.getWasSubsAssessmentCompleted()%></p>
			</td>
		</tr>		
		<tr>
			<td class="label" colspan="4">Subsequent Assessment Comments</td>
		</tr>
		<tr>
			<td colspan="4">
				<p><%=reportInfo.getSubsAssessmentComments()%></p>
			</td>
		</tr>
		</table>
	</logic:equal>
	
</div>

</body>
</pdf>