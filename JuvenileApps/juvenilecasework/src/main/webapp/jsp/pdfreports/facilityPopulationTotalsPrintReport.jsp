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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.populationReport.FacilityPopulationTotalsReportBean" scope="session"/>
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.POPULATION_TOTALS_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.POPULATION_TOTALS_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->

<style>
span.underline
	{	border-bottom:1px solid #555;
		}
table.margin-top-0px
	{	margin-top: 0px;}
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
		margin-top: .3in; 
		margin-bottom: .3in;}
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
	{	font-size:9;
		font-family:"Times New Roman", Times, serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:9;
		font-family:"Times New Roman", Times, serif;
		text-align:center;}

table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-weight: bold;
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
td.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
tr.bold 
	{font-weight: bold;}
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
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}

.RiskNeed{
	margin-top: 15px;
}
.RiskNeed  td {
	 border: 0.5px solid black;
  	 border-collapse: collapse;
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
<body footer="myfooter" footer-height="8mm">
<div class="photo">
<table width="100%" border-style="none" class="margin-top-0px">
		<tr>
			<td align="left" width="1%">				
				<img src="images/HarrisCountySeal.jpg" width="50" height="50"/>	    					   
			</td>
			<td align="center"  colspan="3">
				<table>
					<tr>
						<td colspan="3" align="center">
							<p class="times-font-12" align="center" ><b>JIMS2</b></p>
						</td>
					</tr>
					<tr>						
						<td  colspan="3" align="center" ><p class="times-font-12">CURRENT FACILITY POPULATION:
						<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></p></td>		
					</tr>
					<tr>			
						<td  colspan="3" align="center" ><p class="times-font-12">FACILITY: <span class="underline"><%=StringEscapeUtils.escapeXml(reportInfo.getFacilityName())%></span></p></td>							
					</tr>
				</table>
			</td>
			<td width="20%"></td>
			
		</tr>
</table>

</div>
<div class="body">
<table width="100%" border-style="1" class="indent-15px">
	<tr class="bold">
		<td width="10%"></td>
		<td> SECURE</td>
		<td width="10%"></td>
		<td>NON-SECURE</td>
		<td width="10%"></td>
		<td>DIVERSION</td>
		<td width="6%"></td>
		<td>TEMPORARY RELEASE</td>
		<td></td>
	</tr>
</table>
<table  width="100%" border-style="1" class="indent-15px">
	<tr class="bold">
		<td width="10%"></td>
		<td width="6%"> MALE</td>
		<td width="6%">FEMALE</td>
		<td width="10%"></td>
		<td width="6%">MALE</td>
		<td width="6%">FEMALE</td>
		<td width="10%"></td>
		<td width="6%">MALE</td>
		<td width="6%">FEMALE</td>
		<td width="10%"></td>
		<td width="6%">MALE</td>
		<td width="6%">FEMALE</td>
		<td width="10%"></td>
	</tr>
</table>
<table  width="100%" border-style="1" class="indent-15px">
	<tr>
		<td width="5%"></td>
		<td width="10%">&nbsp;&nbsp;--------</td>
		<td width="10%">----------</td>
		<td width="5%"></td>
		<td width="10%">--------</td>
		<td width="10%">----------</td>
		<td width="5%"></td>
		<td width="10%">--------</td>
		<td width="10%">----------</td>
		<td width="5%"></td>
		<td width="10%">--------</td>
		<td width="10%">----------</td>
		
	</tr>
</table>
<table  width="100%" >
	<tr class="bold">
		<td width="15">RACE</td>
		<td width="10%"></td>	
		<td width="15">RACE</td>
		<td width="10%"></td>	
		<td width="13%">RACE</td>
		<td width="10%"></td>
		<td width="15%">RACE</td>
		<td width="10%"></td>
	</tr>
</table>
<table  width="100%" >
	<c:set var="facilityTots" value="${reportInfo.facilityPopTots}" scope="session"/>
		<c:forEach var="facility" items="${facilityTots}" varStatus="status">
			<tr>
				<td width="11%">&nbsp;&nbsp;&nbsp;(<c:out value="${facility.race}"/>)</td>
				<td width="7%"><c:out value="${facility.secureMaleCount}"/> </td>
				<td width="7%"><c:out value="${facility.secureFemaleCount}"/></td>
				<td align="left" width="11%">(<c:out value="${facility.race}"/>)</td>
				<td width="7%"><c:out value="${facility.nonSecureMaleCount}"/></td>
				<td width="7%"><c:out value="${facility.nonSecureFemaleCount}"/></td>
				<td align="left" width="11%">(<c:out value="${facility.race}"/>)</td>
				<td width="7%"><c:out value="${facility.diversionMaleCount}"/></td>
				<td width="7%"><c:out value="${facility.diversionFemaleCount}"/></td>
				<td align="left" width="11%">(<c:out value="${facility.race}"/>)</td>
				<td width="7%"><c:out value="${facility.tempReleaseMaleCount}"/></td>
				<td width="7%"><c:out value="${facility.tempReleaseFemaleCount}"/></td>
				
			</tr>
		</c:forEach>
</table>
<table  width="100%" >
	<tr class="bold">
		<td  align="left" width="9%"></td>
		<td width="7%">------ </td>
		<td width="7%">------</td>
		<td width="11%"></td>
		<td width="7%">------</td>
		<td width="7%">------</td>
		<td width="11%"></td>
		<td width="7%">------</td>
		<td width="7%">------</td>
		<td width="11%"></td>
		<td width="7%">------</td>
		<td width="7%">------</td>
		
	</tr>
</table>

<table  width="100%" >
	<tr class="bold">
		<td  align="left" width="11%">TOTAL</td>
		<td width="7%"><%= reportInfo.getTotalSecureMalecount()%></td>
		<td width="7%"><%= reportInfo.getTotalFemaleSecureCount()%></td>
		<td width="12%"></td>
		<td width="8%"><%= reportInfo.getTotalNonSecMaleCount()%></td>
		<td width="7%"><%= reportInfo.getTotalNonSecFemaleCount()%></td>
		<td width="12%"></td>
		<td width="8%"><%= reportInfo.getTotalDivMaleCount()%></td>
		<td width="8%"><%= reportInfo.getTotalDivFemaleCount()%></td>
		<td width="11%"></td>
		<td width="7%"><%= reportInfo.getTotalTempReleaseMaleCount()%></td>
		<td width="7%"><%= reportInfo.getTotalTempReleaseFemaleCount()%></td>
		
	</tr>
</table>

<table  width="100%" >
	<tr class="bold">
		<td  align="left" width="8%"></td>
		<td width="15%">----------------------</td>
		<td width="10%"></td>		
		<td width="15%">----------------------</td>
		<td width="10%"></td>		
		<td width="15%">----------------------</td>
		<td width="10%"></td>
		<td width="15%">----------------------</td>
				
	</tr>
</table>
<table  width="100%" >
	<tr class="bold">
		<td  align="left" width="15%"></td>
		<td width="10%"><%= reportInfo.getTotalSecureInmates()%></td>
		<td width="15%"></td>		
		<td width="10%"><%= reportInfo.getTotalNonSecInmates()%></td>
		<td width="15%"></td>		
		<td width="10%"><%= reportInfo.getTotalDivInmates()%></td>		
		<td width="15%"></td>
		<td width="10%"><%= reportInfo.getTotalTempReleaseInmates()%></td>	
	</tr>
</table>

<table class="RiskNeed" align="center" width="35%"  >
	<tr class="bold">
		<td colspan="2" style="background-color: #B3C9F5; font-weight: bold" align="left">Risk/Need Summary </td>
	</tr>
	<tr>
		<td align="center" style="background-color:  #D3D3D3;" >Criminal/Social History Score</td>
		<td align="center" style="background-color:  #D3D3D3;" >Percentage</td>
	</tr>
	<tr>
		<td align="left">High/High</td>
		<td align="center"><%= reportInfo.getHighHighPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">High/Moderate</td>
		<td align="center"><%= reportInfo.getHighModeratePercent()%> %</td>
	</tr>
	<tr>
		<td align="left">High/Low</td>
		<td align="center"><%= reportInfo.getHighLowPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/High</td>
		<td align="center"><%= reportInfo.getModerateHighPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/Moderate</td>
		<td align="center"><%= reportInfo.getModerateModeratePercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/Low</td>
		<td align="center"><%= reportInfo.getModerateLowPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Low/High</td>
		<td align="center"><%= reportInfo.getLowHighPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Low/Moderate</td>
		<td align="center"><%= reportInfo.getLowModeratePercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Low/Low</td>
		<td align="center"><%= reportInfo.getLowLowPercent()%> %</td>
	</tr>
	<tr>
		<td align="left">Missing Score</td>
		<td align="center"><%= reportInfo.getMissingScorePercent()%> %</td>
	</tr>
	
	
</table>
</div>
</body>
</pdf>
