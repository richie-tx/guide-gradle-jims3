<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<jsp:useBean id="reportInfo" class="ui.juvenilecase.populationReport.FacilityCurrentPopulationReportBean" scope="session"/>
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.CURRENT_POPULATION_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.CURRENT_POPULATION_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->

<style>
span.underline
	{	border-bottom:1px solid #555;
		}
table.margin-top-8px
	{	margin-top: 8px;}
table.margin-top-12px
	{	margin-top: 12px;}
table.margin-top-24px
	{	margin-top: 24px;}
table.margin-top-30px
	{	margin-top: 30px;}
table.margin-top-40px
	{	margin-top: 40px;}
table.margin-top-50px
	{	margin-top: 50px;}
table.margin-top-60px
	{	margin-top: 60px;}
table.margin-top-70px
	{	margin-top: 70px;}
table.margin-top-80px
	{	margin-top: 80px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
table.indent-50px
	{	text-indent:50px;}
body
	{	margin-left: .2in;
    	margin-right: .2in;
		margin-top: .0in; 
		margin-bottom: .0in;}
div.header
	{ 	font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
div.photo
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		padding:11;
		text-align:left;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:9;
		font-family:"Times New Roman", Times, sans-serif;
		text-align:left;}

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
td.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, sans-serif;}
p.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, sans-serif;}
p.times-font-7
	{font-size:7;
		font-family:"Times New Roman", Times, sans-serif;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
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
<body size="A4-landscape" footer="myfooter" footer-height="10mm">
<div class="photo">

<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td align="left" width="1%">				
				    	<img  src="images/HarrisCountySeal.jpg" width="50" height="50"/>				   
			</td>
			<td align="center"  colspan="3">
				<table>
					<tr>
						<td colspan="3" align="center">
							<p class="times-font-12" align="center" >JIMS2</p>
						</td>
					</tr>
					<tr>						
						<td  colspan="3" align="center" ><p class="times-font-12">JUVENILES CURRENTLY IN FACILITY:&nbsp;
							<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm MM/dd/yyyy" var="formattedDate" />
							<c:out value="${formattedDate}"/></p>
						</td>		
					</tr>
					<tr>			
						<td  colspan="3" align="center" ><p class="times-font-12">FACILITY: <span class="underline"><%= reportInfo.getFacilityName()%></span></p></td> 							
					</tr>
				</table>
			</td>
			<td width="20%"></td>
			
		</tr>
</table>

</div>
<table>
<thead>



	<tr>
		<td width="5%"><p class="times-font-8"><b>JUVNO</b></p></td>							
		<td width="12%"><p class="times-font-8">&nbsp;<b>JUVENILE NAME</b></p></td>						
		<td width="3%" align="center"><p class="times-font-8"><b>AGE</b></p></td>
		<td width="7%" align="right"><p class="times-font-8"><b>DATE IN</b></p></td>				
		<td width="12%"><p class="times-font-8"><b>PRIMARY GUARDIAN</b></p></td>	
		<td width="8%" align="left"><p class="times-font-8"><b>RELATIONSHIP</b></p></td>	
		<td width="5%" align="left"><p class="times-font-8"><b>LANGUAGE</b></p></td>	
		<td width="13%" align="center"><p class="times-font-8"><b>ADDRESS</b></p></td>	
		<td width="5%" align="center"><p class="times-font-8"><b>PHONE<br/>NUMBER</b></p></td>
		<td width="5%" align="center"><p class="times-font-8"><b>EMAIL<br/>ADDRESS</b></p></td>
		
	</tr>


	<tr>
		<td width="5%">--------</td>		
		<td width="12%">---------------------</td>
		<td width="3%">-----</td>		
		<td width="7%">---------</td>
		<td width="12%">-------------------------</td>
		<td width="8%">----------------</td>
		<td width="5%">-----------</td>
		<td width="13%">-------------------------</td>
		<td width="5%">-----------</td>
		<td width="5%">-----------------------------</td>
	</tr>

</thead><tbody>

	<c:set var="facilityTots" value="${reportInfo.currentPopulations}" scope="session"/>
		<c:forEach var="facility" items="${facilityTots}" varStatus="status">
			<tr>
				<td width="5%"><p class="times-font-7"><c:out value="${facility.juvenileNum}"/></p></td>
				<td width="12%"><p class="times-font-7"><c:out value="${facility.lastName}"/>,&nbsp;<c:out value="${facility.firstName}"/><br/><c:out value="${facility.middleName}"/></p> </td>												
				<td width="3%" align="center"><p class="times-font-7"><c:out value="${facility.ageInYears}"/></p></td>
				<td width="7%"><p class="times-font-7"><c:out value="${facility.formattedAdmitDate}"/></p></td>				
				<td width="12%"><p class="times-font-7"><c:out value="${facility.primaryGuardianName}" escapeXml="false"/></p></td>	
				<td width="8%"><p class="times-font-7"><c:out value="${facility.guardianRelationship}"/></p></td>
				<td width="5%"><p class="times-font-7" align="left"><c:out value="${facility.guardianPrimaryLanguage}"/></p></td>	
				<td width="13%"><p class="times-font-7"><c:out value="${facility.guardianAddress}" escapeXml="false"/></p></td>
				<td width="5%"><p class="times-font-7"><c:out value="${facility.guardianPhoneNumber}"/></p></td>
				<td width="5%"><p class="times-font-7"><c:out value="${facility.guardianEmailAddress}"/></p></td>
			</tr>
		</c:forEach>
</tbody>
<tfoot></tfoot>
</table>
</body>
</pdf>
