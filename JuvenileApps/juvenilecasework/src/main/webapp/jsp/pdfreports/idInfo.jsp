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
<jsp:useBean id="idInfoData" class="ui.juvenilecase.schedulecalendarevent.IdInfoPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.ID_INFO.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.ID_INFO.getReportName()%>"/>
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
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		line-height: 150%;}
td.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		line-height: 150%;}
td.arial-font-10-narrow
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		line-height: 100%;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.borderBottom
{
	border-bottom:2pt solid gray;
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
		text-align:left
		line-height: 150%;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.line-space-125
	{line-height: 125%;}
p.line-space-150
	{line-height: 150%;}
p.text-margin-10px
	{margin: 10px;}
p.text-margin-top-6
	{margin-top: 6px;}
p.text-margin-top-25
	{margin-top: 25px;}
p.text-margin-top-50
	{margin-top: 50px;}
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
	<macrolist>
		<macro id="myfooter">
			<div class="footer" align="center">
				<img margin-top="0" width="500" src="images/JuvenileJustice_Footer.gif"/>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="left" cellpadding="0">
		<tr align="left">
			<td valign="top" margin-left="0">
				<p class="leftAlign">
					<img margin-top="0" height="75" width="75" src="images/HarrisCountySeal.jpg"/>
				</p>
			</td>
			<td width="90%" class="arial-font-12" align="left">
				<table width="100%" align="left" padding="3">
				<tr>
					<td class="borderBottom" colspan="2">
						<p class="leftAlign bold">HARRIS COUNTY</p>
						<p class="leftAlign bold">JUVENILE PROBATION DEPARTMENT</p>
					</td>
				</tr>
				<tr>
					<td class="arial-font-10-narrow" align="left" colspan="3">
						<c:if test="${not empty idInfoData.locationUnitName}">
							<b>${idInfoData.locationUnitName}</b><br/>
							${idInfoData.locationUnitAddress.streetAddress}<br/>
							${idInfoData.locationUnitAddress.cityStateZip}
						</c:if>
					</td>
					<td class="arial-font-8" align="right">
						<b>${idInfoData.managerFirstName} ${idInfoData.managerLastName}</b><br/>
						Administrator<br/>
						Office ${idInfoData.managerPhone}
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr padding-top="50">
			<td class="arial-font-10" align="left" colspan="3">
				${idInfoData.memberRelationship}<br/>
				${idInfoData.guardianFirstName} ${idInfoData.guardianMiddleName} ${idInfoData.guardianLastName}<br/>
				${idInfoData.guardianStreetNo} ${idInfoData.guardianStreetName} ${idInfoData.guardianStreetType}<br/>
				${idInfoData.guardianCity}, ${idInfoData.guardianState} ${idInfoData.guardianZip}
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="left" cellpadding="0">
		<tr>
			<td align="left" colspan="4">
				<p class="arial-font-10 text-margin-top-25"><c:out value="${idInfoData.currentDate}"/></p>
				<p class="arial-font-10 text-margin-top-25">Identification Information:</p>
				<p class="arial-font-10 text-margin-top-25">
					${idInfoData.juvenileFirstName}  ${idInfoData.juvenileMiddleName} ${idInfoData.juvenileLastName} ${idInfoData.juvenileSuffix}
				</p>
			</td>
		</tr>
		<tr>
			<td align="left"><p class="arial-font-10 line-space-150" align="left">Race: ${idInfoData.race}</p></td>
			<td align="left"><p class="arial-font-10 line-space-150" align="left">Sex: ${idInfoData.sex}</p></td>
			<td align="left"><p class="arial-font-10 line-space-150" align="left">Date of Birth: ${idInfoData.dateOfBirth}</p></td>
			<td align="left"><p class="arial-font-10 line-space-150" align="left">Primary Language: ${idInfoData.primaryLanguage}</p></td>
		</tr>
		<tr>
			<td class="arial-font-10" align="left" colspan="2">Birth City: ${idInfoData.birthCity}</td>
			<td class="arial-font-10" align="left">Birth County: ${idInfoData.birthCounty}</td>
			<td class="arial-font-10" align="left">Birth Country: ${idInfoData.birthCountry}</td>
		</tr>
		<tr>
			<td class="arial-font-10" align="left" colspan="2">Is a US Citizen: ${idInfoData.citizenship}</td>
			<td class="arial-font-10" align="left" colspan="2">Nationality: ${idInfoData.nationality}</td>
		</tr>
		<tr>
			<td class="arial-font-10" align="left" colspan="4">Alien Number: ${idInfoData.alienNumber}</td>
		</tr>
		<c:forEach var="guardian" items="${idInfoData.guardians}" varStatus="loop">
			<tr>
				<td align="left" colspan="4">
					<p class="arial-font-10 text-margin-top-25">
						${guardian.name.completeFullNameFirst} (Guardian)
					</p>
				</td>
			</tr>
			<tr>
				<td class="arial-font-10" align="left" colspan="2">Date of Birth: ${guardian.dateOfBirth}</td>
				<td class="arial-font-10" align="left" colspan="2">SSN: ${guardian.ssn.formattedSsn}</td>
			</tr>
			<tr>
				<td class="arial-font-10" align="left" colspan="4">Address: ${guardian.address.formattedAddress}</td>
			</tr>
			<tr>
				<td class="arial-font-10" align="left" colspan="4">Phone: ${guardian.phoneNumber.formattedPhoneNumber}</td>
			</tr>		
		</c:forEach>
		<tr>
			<td align="left" colspan="4">
				<p class="arial-font-10 text-margin-top-25 text-margin-bottom-40"></p>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3"></td>
			<td>
				<p class="arial-font-10 text-margin-top-25 text-margin-bottom-40">Sincerely,</p>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3"></td>
			<td>
				<p class="arial-font-10">
					${idInfoData.officerFirstName} ${idInfoData.officerLastName}<br/><br/>
				    Juvenile Probation Officer
				</p>
			</td>
		</tr>
	</table>
	
	
	
</div>
</body>
</pdf>