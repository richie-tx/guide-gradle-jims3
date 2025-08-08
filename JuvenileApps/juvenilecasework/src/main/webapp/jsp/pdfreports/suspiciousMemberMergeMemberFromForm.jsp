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
<jsp:useBean id="smForm" class="ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm" scope="session"/>
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.SUSPICIOUS_MEMBER_MERGE_FORM.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.SUSPICIOUS_MEMBER_MERGE_FORM.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style> 
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .0in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:12;
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
table.margin-bottom-5
	{	margin-bottom: 5;}
table.margin-bottom-10
	{	margin-bottom: 10;}
table.margin-bottom-15
	{	margin-bottom: 15;}
table.margin-bottom-20
	{	margin-bottom: 20;}
table.margin-bottom-20px
	{	margin-bottom: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-15
	{font-size:15;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.border-bottom
{
	border-bottom:1pt solid black;
}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.line-space-115
	{line-height: 115%;}
p.line-space-125
	{line-height: 125%;}
p.text-margin-10px
	{margin: 10px;}
p.text-margin-top-6
	{margin-top: 6px;}
p.text-margin-top-10
	{margin-top: 10px;}
p.text-margin-top-25
	{margin-top: 25px;}
p.text-margin-bottom-25
	{margin-bottom: 25px;}
p.text-margin-bottom-40
	{margin-bottom: 40px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
p.rightAlign
	{text-align:right;}
table.title
	{
		
	}

</style>
	
</head>
<body>

<!-- 
	Detail Pages
-->

<div class="header">
	<table class="margin-bottom-20px" border-style="none" width="100%">
		<tr>
			<td class="helvetica-font-15" vertical-align="top" align="center">
			<p class="line-space-125"><b><span class="underline">Family Member Details for merge ${smForm.memberFirstName} ${smForm.memberLastName} ${smForm.selectedFromId} to ${smForm.memToName} ${smForm.selectedToId}</span></b></p>
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="5.5%"><p class="arial-font-11">SS#: </p></td>
			<td width="18.5%"><p class="arial-font-11">${smForm.memberSSN}</p></td>
			<td width="6.2%"><p class="arial-font-11">DOB:</p></td>
			<td width="21.8%"><p class="arial-font-11">${smForm.memberDOB}</p></td>
			<td width="9%"><p class="arial-font-11">Gender: </p></td>
			<td width="15%"><p class="arial-font-11">${smForm.sexLit}</p></td>
			<td width="12%"><p class="arial-font-11">US Citizen: </p></td>
			<td width="12%"><p class="arial-font-11">${smForm.usCitizenId}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="16%"><p class="arial-font-11">State ID#(SID): </p></td>
			<td width="34%"><p class="arial-font-11">${smForm.sidNum}</p></td>
			<td width="13%"><p class="arial-font-11">Alien Reg.#: </p></td>
			<td width="37%"><p class="arial-font-11">${smForm.alienRegNumber}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="12%"><p class="arial-font-11">Nationality: </p></td>
			<td width="38%"><p class="arial-font-11">${smForm.nationalityLit}</p></td>
			<td width="10%"><p class="arial-font-11">Ethnicity: </p></td>
			<td width="40%"><p class="arial-font-11">${smForm.ethnicityLit}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="19.5%"><p class="arial-font-11">Primary Language: </p></td>
			<td width="30.5%"><p class="arial-font-11">${smForm.primaryLanguageLit}</p></td>
			<td width="22.5%"><p class="arial-font-11">Secondary Language: </p></td>
			<td width="27.5%"><p class="arial-font-11">${smForm.secondaryLanguageLit}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="30.5%"><p class="arial-font-11">Is Family Member Deceased?: </p></td>
			<td width="19.5%"><p class="arial-font-11">${smForm.deceasedLit}</p></td>
			<td width="17%"><p class="arial-font-11">Cause of Death: </p></td>
			<td width="33%"><p class="arial-font-11">${smForm.causeOfDeathLit}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-20px" border-style="none" border-width="1" width="100%">
		<tr>
			<td width="31.5%"><p class="arial-font-11">Is Family Member Incarcerated?: </p></td>
			<td width="68.5%"><p class="arial-font-11">${smForm.incarcetatedLit}</p></td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td><p class="arial-font-11">Family Member Comments: </p></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><p class="arial-font-11 line-space-125">${smForm.comments}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-5" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="18.2%"><p class="arial-font-11">Driver's License#: </p></td>
			<td width="31.8%"><p class="arial-font-11">${smForm.dlNumber}</p></td>
			<td width="6.7%"><p class="arial-font-11">State: </p></td>
			<td width="43.3%"><p class="arial-font-11">${smForm.dlStateLit}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-20" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="7.5%"><p class="arial-font-11">Class: </p></td>
			<td width="42.5%"><p class="arial-font-11">${smForm.dlClassId}</p></td>
			<td width="16.5%"><p class="arial-font-11">Expiration Date: </p></td>
			<td width="33.5%"><p class="arial-font-11">${smForm.dlExpDateStr}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-20" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="23.5%"><p class="arial-font-11">State Issued ID Card #: </p></td>
			<td width="26.5%"><p class="arial-font-11">${smForm.stateIssuedCardNumber}</p></td>
			<td width="6.7%"><p class="arial-font-11">State: </p></td>
			<td width="42.3%"><p class="arial-font-11">${smForm.stateIssuedCardStateLit}</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-10" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td><p class="arial-font-11 line-space-125" align="center"><span class="underline">Associated Juveniles</span></p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-5" border-style="none" border-width="1px" padding="0px" width="100%">
		<tr>
			<td width="20%"><p class="arial-font-11">Juvenile Number</p></td>
			<td width="40%"><p class="arial-font-11">Juvenile Name</p></td>
			<td width="25%"><p class="arial-font-11">Relationship</p></td>
			<td width="15%"><p class="arial-font-11">Guardian</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-15" border-style="none" border-width="1px" padding="0px" width="100%">
		<c:forEach var="juvList" items="${smForm.associatedJuvenilesList}" varStatus="loop">
			<tr>
				<td width="20%"><p class="arial-font-11 line-space-115">${juvList.juvId}</p></td>
				<td width="40%"><p class="arial-font-11 line-space-115">${juvList.juvName}</p></td>
				<td width="25%"><p class="arial-font-11 line-space-115">${juvList.relationType}</p></td>
				<td width="15%">
					<c:choose>
						<c:when test="${juvList.guardian}">
						<p class="arial-font-11 line-space-115">YES</p>
						</c:when>
						<c:otherwise>
						<p class="arial-font-11 line-space-115">NO</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<table class="margin-bottom-10" border-style="none" border-width="1" width="100%">
		<tr>
			<td><p class="arial-font-11 line-space-125" align="center"><span class="underline">Marital Status</span></p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-5" border-style="none" border-width="1" padding="0" width="100%">
		<tr>
			<td width="13%"><p class="arial-font-11">Entry Date</p></td>
			<td width="17%"><p class="arial-font-11">Marital Status</p></td>
			<td width="27%"><p class="arial-font-11">Relationship With</p></td>
			<td width="16%"><p class="arial-font-11">Marriage Date</p></td>
			<td width="14%"><p class="arial-font-11">Divorce Date</p></td>
			<td width="13%"><p class="arial-font-11"># of Children</p></td>
		</tr>
	</table>
	
	<table class="margin-bottom-5" border-style="none" border-width="1" padding="0" width="675px">
		<c:forEach var="msList" items="${smForm.maritalStatusDetailsList}" varStatus="loop">
			<tr align="bottom">
				<td width="140px"><p class="arial-font-11 line-space-115">${msList.entryDate}</p></td>
				<td width="166px"><p class="arial-font-11 line-space-115">${msList.maritalStatus}</p></td>
				<td width="235px"><p class="arial-font-11 line-space-115">${msList.relatedFamMemId} - ${msList.relatedFamMemName}</p></td>
				<td width="160px"><p class="arial-font-11 line-space-115">${msList.marriageDate}</p></td>
				<td width="147px"><p class="arial-font-11 line-space-115">${msList.divorceDate}</p></td>
				<td width="139px"><p class="arial-font-11 line-space-115">${msList.numOfChildren}</p></td>
			</tr>
		</c:forEach>
	</table>
	
</div>
</body>
</pdf>