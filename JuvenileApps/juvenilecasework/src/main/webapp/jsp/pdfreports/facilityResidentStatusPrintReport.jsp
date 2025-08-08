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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.populationReport.FacilityResidentStatusReportBean" scope="session"/>
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.RESIDENT_STATUS_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.RESIDENT_STATUS_REPORT_NAME.getReportName()%>"/>
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
		padding:11;
		text-align:left;}
div.body
	{	font-size:9;
		font-family:"Times New Roman", Times, serif;		
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
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

tr.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}

tr.times-font-notbold-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;		
		line-height: 80%;}
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

p.dashed
{
border-bottom-style:dashed;
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
<body  size="A4-landscape" footer="myfooter" footer-height="10mm">
<div class="photo">
<table width="100%" border-style="none" >
		<tr>
			<td align="left" width="1%">				
				    	<img  src="images/HarrisCountySeal.jpg" width="50" height="50"/>				   
			</td>
			<td align="center"  colspan="3">
				<table>				
					<tr>					
						<td colspan="3" align="center">
							<p class="times-font-12" align="center" ><b>JIMS2</b></p>
						</td>						
					</tr>
					<tr>						
						<td colspan="3" align="center">
							<p class="times-font-12" align="center" ><b>FACILITY/RESIDENT STATUS</b></p>
						</td>
						
					</tr>					
					<tr>									
						<td  colspan="3" align="center" ><p class="times-font-12"><b>FACILITY: <span class="underline"><%=StringEscapeUtils.escapeXml(reportInfo.getFacilityName())%></span></b></p></td>
										
					</tr>
				</table>
			</td>
			<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			
		</tr>
</table>

</div>

<table width="100%">
	<thead>
	<tr class="times-font-9">
	
		<td><p class="dashed">JUV NO.</p></td>
		<td> REF NO.</td>
		<td colspan="3">JUVENILE NAME</td>
		<td>R/S/AGE</td>
		<td>LOC</td>
		<td>DATE IN</td>
		<td>TIME IN</td>
		<td>REASON</td>

	</tr>
	
	<tr class="times-font-9">
		<td align="left" width="100%" colspan="10">
		
			<p class="arial-font-9">
			------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			</p>
		</td>
	</tr>
	</thead>
	<tbody>
	<c:set var="residentList" value="${reportInfo.residentsList}" scope="session"/>
		<c:forEach var="residents" items="${residentList}" varStatus="status">
			<tr class="times-font-notbold-9">
				<td><c:out value="${residents.juvNum}"/></td>
				<td ><c:out value="${residents.referralNumber}"/></td>
				<td colspan="3"><c:out value="${residents.juvName}"/></td>
				<td><c:out value="${residents.juvRace}"/> &nbsp;<c:out value="${residents.juvSex}"/>&nbsp;<c:out value="${residents.age}"/></td>
				<td ><c:out value="${residents.floorNum}"/> <c:out value="${residents.unit}"/> <c:out value="${residents.roomNum}"/>
					<c:if test="${not empty residents.multipleOccupyUnit}" >-<c:out value="${residents.multipleOccupyUnit}"/>
					</c:if>
				</td>
				<td><fmt:formatDate value="${residents.admitDate}" pattern="MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></td>
				<td><c:out value="${residents.admitTime}"/></td>
				<td><c:out value="${residents.admitReason}"/>-<c:out value="${residents.secureStatus}"/></td>
			</tr>
			<c:if test="${residents.facilityStatus =='E'}">
			<tr class="times-font-9">
				<td></td>
				<td></td>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ESCAPED</td>
				<td>
					<fmt:formatDate value="${residents.relocationDate}" pattern="MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/>
				</td>
			</tr> </c:if>
			
			<c:if test="${ (not empty residents.returnReason) && (residents.returnReason =='RE')}">
			<tr class="times-font-9">
				<td></td>
				<td></td>
				<td></td>
				<td colspan="3">RETURN FROM ESCAPE</td>
				<td colspan="2"> AUTH = <c:out value="${residents.admitAuthority}"/>
					<fmt:formatDate value="${residents.returnDate}" pattern="MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/>
				</td>
			</tr> </c:if>
			
			<c:if test="${ (not empty residents.returnReason) && (residents.returnReason =='HV')}">
			<tr class="times-font-9">
					<td></td>
				<td></td>
				<td></td>
				<td colspan="3">RETURN FROM HOME VISIT</td>
				<td colspan="2"> AUTH = <c:out value="${residents.admitAuthority}"/>
					<fmt:formatDate value="${residents.returnDate}" pattern="MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/>
				</td>
			</tr> </c:if>
			
			<c:if test="${residents.facilityStatus =='N'}">
			<tr class="times-font-notbold-9">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>TRANSFERRING					
				</td>
			</tr> </c:if>
			<c:if test="${residents.facilityStatus =='T'}">
			<tr class="times-font-notbold-9">
				<td></td>
				<td colspan="3" >TEMPORARILY RELEASED BY = 
					<c:out value="${residents.releaseBy}"/></td>
				<td colspan="3">TO = 
					<c:out value="${residents.releaseTo}"/></td>
				<td>AUTH = 
					<c:out value="${residents.releaseAuthorizedBy}"/></td>
				<td colspan="3">
					<fmt:formatDate value="${residents.releaseDate}" pattern="MM/dd/yyyy" var="formattedDate" />					
					<c:out value="${formattedDate}"/> 
					<c:out value="${residents.releaseTime}"/>
				</td>
				
				<td></td>
				<td>		
				</td>
			</tr> </c:if>
			
			<c:if test="${ (empty residents.facilityStatus) && (not empty residents.releaseDate) && (residents.releaseReason == 'R') }"> 
				<tr class="times-font-9">
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"> RELEASED BY = <c:out value="${residents.releaseBy}"/></td>
					<td> TO = <c:out value="${residents.releaseTo}"/></td>
					<td colspan="2">AUTH = <c:out value="${residents.releaseAuthorizedBy}"/>
						<fmt:formatDate value="${residents.releaseDate}" pattern="MM/dd/yyyy" var="formattedDate" />
						<c:out value="${formattedDate}"/> <c:out value="${residents.releaseTime}"/>
					</td>
				</tr>
			
			</c:if>
		</c:forEach>
	</tbody>
	<tfoot></tfoot>
</table>
<br></br>
<table width="100%">
	<thead>
	<tr class="times-font-9">
	
		<td><p class="dashed">JUV NO.</p></td>
		<td> REF NO.</td>
		<td colspan="3">JUVENILE NAME</td>
		<td>R/S/AGE</td>
		<td>LOC</td>
		<td>DATE IN</td>
		<td>TIME IN</td>
		<td>REASON</td>

	</tr>
	
	<tr class="times-font-9">
		<td align="left" width="100%" colspan="10">
		
			<p class="arial-font-9">
			------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			</p>
		</td>
	</tr>
	</thead>
	<tbody>
	<c:set var="residentWithOtherChangesList" value="${reportInfo.residentsWithOtherChanges}" scope="session"/>
		<c:forEach var="residents" items="${residentWithOtherChangesList}" varStatus="status">
			<tr class="times-font-notbold-9">
				<td><c:out value="${residents.juvNum}"/></td>
				<td ><c:out value="${residents.referralNumber}"/></td>
				<td colspan="3"><c:out value="${residents.juvName}"/></td>
				<td><c:out value="${residents.juvRace}"/> &nbsp;<c:out value="${residents.juvSex}"/>&nbsp;<c:out value="${residents.age}"/></td>
				<td ><c:out value="${residents.floorNum}"/> <c:out value="${residents.unit}"/> <c:out value="${residents.roomNum}"/>
					<c:if test="${not empty residents.multipleOccupyUnit}" >-<c:out value="${residents.multipleOccupyUnit}"/>
					</c:if></td>
				<td><fmt:formatDate value="${residents.admitDate}" pattern="MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></td>
				<td><c:out value="${residents.admitTime}"/></td>
				<td><c:out value="${residents.admitReason}"/>-<c:out value="${residents.secureStatus}"/></td>
			</tr>
			<tr class="times-font-notbold-9"><td></td>
				
				<td align="right" colspan="4">REASON:
					<c:if test="${residents.locationChangeFlag == 'true'}">LOCATION WITHIN FACILITY CHANGED
					</c:if>
					<c:if test="${residents.reasonChangeFlag == 'true'}">SECURE INDICATOR CHANGED
					</c:if>
					<c:if test="${residents.secureIndicatorChangeFlag == 'true'}">ADMIT REASON CHANGED
					</c:if>
					<c:if test="${residents.otherChangeFlag == 'true'}">OTHER CHANGE
					</c:if>
				</td>
				<td></td>
				<td colspan="3">MODIFY DATE/TIME= <fmt:formatDate value="${residents.lastChangeDate}" pattern="MM/dd/yyyy HH:mm:ss" var="formattedDate" />					
					<c:out value="${formattedDate}"/> 
					</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	

<br></br>
<table  width="100%" >
	<tr class="times-font-9">
		<td  align="left"><b>TOTAL (<%=reportInfo.getFacilityTotal() %>) FACILITY STATUS AS OF 
			<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm:ss MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></b></td>
	
	</tr>
</table>
</body>
</pdf>
