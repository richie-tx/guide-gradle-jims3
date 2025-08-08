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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.TEAStudentDataReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.JUVENILE_DEMOGRAPHICS_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.JUVENILE_DEMOGRAPHICS_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
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
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
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
			<td align="center" colspan="2" font-size="20"><b>HCJPD Juvenile Details -- Confidential</b></td>
		</tr>
</table>
</div>
<br></br>
<table width="100%" border-style="none">		
	<tr>
		<td align="center" colspan="2"><p class="arial-font-15"><b>Juvenile Information:</b></p></td>
	</tr>
</table>

<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="4" width="25%"><p align="left"><b>Name:</b><%=reportInfo.getJuvenileName()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="65%"><p align="left"><b>DOB:</b><%=reportInfo.getDateOfBirth()%></p></td>
			<td align="left"  width="35%"><p align="left"><b>Current Age:</b><%=reportInfo.getCurrentAge()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="65%"><p align="left"><b>Race:</b><%=reportInfo.getRace()%></p></td>
			<td align="left" width="35%"><p align="left"><b>Sex:</b><%=reportInfo.getGender()%></p></td>			
		</tr>
		<tr align="center">
			<td align="left" colspan="4" width="25%"><p align="left"><b>Ethnicity:</b><%=reportInfo.getEthnicityDesc()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="65%"><p align="left"><b>Multiracial:</b><%=reportInfo.getMultiracialDesc()%></p></td>
			<td align="left"  width="35%"><p align="left"><b>Hispanic:</b><%=reportInfo.getHispanicDesc()%></p></td>
		</tr>
		<tr align="center">			
			<td align="left" colspan="3" width="65%"><p align="left"><b>Natural Eye Color: </b><%=reportInfo.getEyeColor()%></p></td>
			<td align="left" width="35%"><p align="left"><b>Natural Hair Color: </b><%=reportInfo.getHairColor()%></p></td>
		</tr>
	</table>
</div>
<br/>

<!--  SCHOOL INFORMATION -->
		<logic:notEmpty name="reportInfo" property="currentSchool">
		<div class="body">
			<table width="100%" border-style="none">
				<tr align="center">
					<td align="center" colspan="2"><p class="arial-font-15"><b>School Information:</b></p></td>
				</tr>
			</table>
			<table width="100%" border-style="none" cellpadding="5">
				<tr align="center">
					<td align="left" width="15%"><u><b>District/School</b></u></td>
					<td align="left" width="25%"><u><b>Grade Level</b></u></td>
					<td align="left" width="15%"><u><b>Enrollment Date</b></u></td>
					<td align="left" width="25%"><u><b>Attendance Status</b></u></td>
				</tr>
				<tr align="center">
					<td align="left" width="25%">
						<p class="arial-font-10" align="left"><c:out value="${reportInfo.currentSchool.schoolDistrict}"/>/<c:out value="${reportInfo.currentSchool.school}"/></p>
					</td>
					<td align="left" width="15%"><c:out value="${reportInfo.currentSchool.gradeLevelDescription}"/></td>
					<td align="left" width="15%"><p class="arial-font-10" align="left">
						<c:out value="${reportInfo.currentSchool.lastAttendedDateString}"/></p>
					</td>
					<td align="left" width="45%"><p class="arial-font-10" align="left">
						<c:out value="${reportInfo.currentSchool.schoolAttendanceStatusDescription}"/></p>
					</td>
					</tr>
			</table>
			</div>
		</logic:notEmpty>
<br/>		
<table width="100%" border-style="none">		
	<tr>
		<td align="center" colspan="2"><p class="arial-font-15"><b>Residential Information:</b></p></td>
	</tr>
</table>
<div class="body">
<!--  RESIDENTIAL INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="4" width="50%"><p align="left"><b>Address: </b><%=reportInfo.getFullAddress()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4" width="50%"><p align="left"><%=reportInfo.getCityStateZip()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="25%"><p align="left"><b>County: </b><%=reportInfo.getCounty()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="1" width="25%"><p align="left"><b>Phone: </b><%=reportInfo.getPhoneNum()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="1" width="45%"><p align="left"><b>Phone Type: </b><%=reportInfo.getPhoneType()%></p></td>
		</tr>
	</table>
</div>
<br/>
<!--  FAMILY INFORMATION -->
	<table width="100%" border-style="none">		
	<tr>
		<td align="center" colspan="2"><p class="arial-font-15"><b>Guardian(s) Information:</b></p></td>
	</tr>
</table>
	<br/>
		<c:set var="guardians" value="${reportInfo.guardians}" scope="session"/>
		<c:forEach var="guardian" items="${guardians}" varStatus="status">
				<table width="100%" border-style="none">
					<tr align="center">
						<td align="left" colspan="3" width="50%"><p align="left"><b>Name: </b><c:out value="${guardian.nameOfPerson.formattedName}"/></p></td>
						<td align="left" colspan="1" width="50%"><p align="left"><b>Relationship:</b><c:out value="${guardian.relationshipType}"/></p></td>
					</tr>
					<tr align="center">
						<td align="left" colspan="3" width="50%"><p align="left"><b>Language:</b><c:out value="${guardian.language}"/></p></td>
					</tr>
			
					<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
				</table>
		</c:forEach>	
		
</body>
</pdf>
