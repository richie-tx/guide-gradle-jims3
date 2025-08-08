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
	<meta name="title" value="<%=PDFReport.TEA_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.TEA_REPORT.getReportName()%>"/>
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
			<td align="center" colspan="2" font-size="20"><b>Enrollment Form</b></td>
		</tr>
</table>
<br></br>
<table width="100%" border-style="none">		
			<tr>
				<td align="center" colspan="2"><p class="arial-font-10"><b>Excel Academy ____________________________</b></p></td>
			</tr>
</table>
<br></br>
<table width="100%" border-style="none">	
		<tr>
			<td valign="middle"  colspan="4" width="64%">____________________________
				<br></br>
				<p class="arial-font-10"><b>Principal's Signature</b>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				<span><b>Date</b></span></p>
			</td>
			<td valign="bottom" width="36%">______________________
				<br></br>
				<p class="arial-font-10"><b>Date Of Enrollment</b></p>
			</td>
		</tr>
</table>	
</div>
<br></br>
<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="4" width="75%"><p align="left"><b>Name: </b><%=reportInfo.getJuvenileName()%></p></td>
			<td align="left" width="25%"><p align="left"><b>Juvenile #: </b><%=reportInfo.getJuvenileNumber()%></p></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" colspan="3" width="25%"><p align="left"><b>DOB: </b><%=reportInfo.getDateOfBirth()%></p></td>
			<td align="left" colspan="1" width="25%"><p align="left"><b>Verified? </b><%=reportInfo.getVerifiedDOB()%></p></td>
			<%--Changes for JIMS200077276 Starts --%>
			<td align="left"  width="50%"><p align="left"><b>Student ID: </b><%=reportInfo.getStudentId()%></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="25%"><p align="left"><b>SSN/TSDS Number: </b><%=reportInfo.getSsnPEIMSId()%></p></td>
			<td align="left" colspan="1" width="25%"><p align="left"><b>Gender: </b><%=reportInfo.getGender()%></p></td>
			<td align="left"  width="50%"><p align="left"><b>Current Age: </b><%=reportInfo.getCurrentAge()%></p></td>
		</tr>
		<tr><td align="left" colspan="4"  padding-bottom="10"></td></tr>
		<tr align="center">
			<td align="left" colspan="3" width="5%"><p align="left"><b>Education ID: </b><%=reportInfo.getEducationId()%></p></td>
			<td align="left" colspan="1" width="45%"><p align="left"><b>Race: </b><%=reportInfo.getRace()%></p></td>
			<td align="left" width="50%"><p align="left"><b>Ethnicity: </b><%=reportInfo.getEthnicityDesc()%></p></td>
		</tr>
		<%--Changes for JIMS200077276 ends --%>
		<tr align="center">
			<td align="left" colspan="3" width="25%"><p align="left"><b>Multiracial? </b><%=reportInfo.getMultiracialDesc()%></p></td>
			<td align="left" colspan="1" width="25%"><p align="left"><b>Hispanic? </b><%=reportInfo.getHispanicDesc()%></p></td>
			<!-- ER Changes JIMS200074578 Starts-->
			<td align="left" width="50%"><p align="left"><b>Dyslexia: </b><%=reportInfo.getDyslexia()%></p></td> 
			<!-- ER Changes JIMS200074578 Ends-->
		</tr>
		<tr align="center">
			<td align="left" colspan="3" width="40%"><p align="left"><b>Detention Admit Date: </b><%=reportInfo.getDetentionAdmitDate()%></p></td>
			<td align="left" colspan="1" width="30%"><p align="left"><b>Military: </b><%=reportInfo.getMilitary()%></p></td> 
			<td align="left"  width="30%"><p align="left"><b>Foster Parent(Y/N): </b><%=reportInfo.getFosterParent()%></p></td> 
		</tr>
	</table>
</div>
<br/>
<!--  FAMILY INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="left">
			<td align="center"><u><b>FAMILY</b></u></td>
		</tr>
	</table>
	<br/>
		<c:set var="familyInformationList" value="${reportInfo.familyInformation}" scope="session"/>
		<c:forEach var="familyMember" items="${familyInformationList}" varStatus="status">
			<logic:equal name="familyMember"  property="guardian" value="true">
				<table width="100%" border-style="none">
					<tr align="center">
						<td align="left" colspan="3" width="50%"><p align="left"><b>Name: </b><c:out value="${familyMember.fullName}"/></p></td>
						<td align="left" colspan="1" width="50%"><p align="left"><b>Relationship: </b><c:out value="${familyMember.relationToJuvenile}"/></p></td>
					</tr>
					<tr align="center">
						<td align="left" colspan="3" width="50%"><p align="left"><b>Address: </b><c:out value="${familyMember.formattedAddress1}"/><c:out value="${familyMember.formattedAddress2}"/></p></td>
						<td align="left" colspan="1" width="50%"><p align="left"><b>Phone #: </b><c:out value="${familyMember.formattedPhoneNumber}"/></p></td>
					</tr>
					<tr align="center">
						<logic:equal name="familyMember" property="guardian" value="true">
							<td align="left" colspan="3" width="50%"><p align="left"><b>Guardian: </b>YES</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="guardian" value="false">
							<td align="left" colspan="3" width="50%"><p align="left"><b>Guardian: </b>NO</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="inHome" value="true">
							<td align="left" colspan="1" width="50%"><p align="left"><b>Lives With: </b>YES</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="inHome" value="false">
							<td align="left" colspan="1" width="50%"><p align="left"><b>Lives With: </b>NO</p></td>
						</logic:equal>
					</tr>
					<tr align="center">
						<logic:equal name="familyMember" property="incarcerated" value="true">
							<td align="left" colspan="3" width="75%"><p align="left"><b>Incarcerated: </b>YES</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="incarcerated" value="false">
							<td align="left" colspan="3" width="75%"><p align="left"><b>Incarcerated: </b>NO</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="deceased" value="true">
							<td align="left" colspan="1" width="25%"><p align="left"><b>Deceased: </b>YES</p></td>
						</logic:equal>
						<logic:equal name="familyMember" property="deceased" value="false">
							<td align="left" colspan="1" width="25%"><p align="left"><b>Deceased: </b>NO</p></td>
						</logic:equal>
					</tr>
					<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
				</table>
			</logic:equal>
		</c:forEach>	
		
<!--  SCHOOL INFORMATION -->
		<logic:notEmpty name="reportInfo" property="schoolHistory">
		<div class="body" page-break-before="always" page-break-after="always">
			<table width="100%" border-style="none">
				<tr align="center">
					<td align="center" colspan="4" border = "0"><u><b>SCHOOL HISTORY</b></u></td>
				</tr>
			</table>
			<br/>		
			<table width="100%" border-style="none" cellpadding="5">
				<tr align="center">
					<td align="left" width="25%"><u><b>Name</b></u></td>
					<td align="left" width="15%"><u><b>Grade</b></u></td>
					<td align="left" width="15%"><u><b>Program</b></u></td>
					<td align="left" width="45%"><u><b>Enrollment Status</b></u></td>
				</tr>
				<c:set var="schoolHistoryList" value="${reportInfo.schoolHistory}" scope="session"/>
				<c:forEach var="school" items="${schoolHistoryList}" varStatus="status">
					<tr align="center">
						<td align="left" width="25%">
							<c:if test="${(school.specificSchoolName!=null)&& (not empty school.specificSchoolName)}">
								<p class="arial-font-10" align="left"><c:out value="${school.schoolDistrict}"/>/<c:out value="${school.specificSchoolName}"/></p>
							</c:if>
							<c:if test="${(school.specificSchoolName==null)||(empty school.specificSchoolName)}">
								<p class="arial-font-10" align="left"><c:out value="${school.schoolDistrict}"/>/<c:out value="${school.school}"/></p>
							</c:if>
						</td>
						<td align="left" width="15%"><c:out value="${school.gradeLevelDescription}"/></td>
						<td align="left" width="15%"><p class="arial-font-10" align="left">
							<c:out value="${school.programAttendingDescription}"/></p>
						</td>
						<td align="left" width="45%"><p class="arial-font-10" align="left">
							<c:out value="${school.exitTypeDescription}"/></p>
						</td>
					</tr>
					<tr><td align="center" colspan="4" border = "0" padding-top="10"></td></tr>
				</c:forEach>
			</table>
			</div>
		</logic:notEmpty>
		
<!--  DETENTION FACILITY INFORMATION -->

		<logic:notEmpty name="reportInfo" property="detentionFacilityHistory">
			<div class="body" page-break-before="always" page-break-after="always">
			<table width="100%" border-style="none">
				<tr align="center">
					<td align="center" colspan="7" border = "0"><u><b>DETENTION/FACILITY HISTORY</b></u></td>
				</tr>
			</table>
			<br/>
			<table width="100%" border-style="none">
			<tr>
				<td align="left"><u><b>Referral</b></u></td>
				<td align="left"><u><b>Facility</b></u></td>
				<td align="left"><u><b>Reason</b></u></td>
				<td align="left"><u><b>Detained</b></u></td>
				<td align="left"><u><b>Released</b></u></td>
				<td align="left"><u><b>Released To</b></u></td>
				<td align="left"><u><b>Released By</b></u></td>
			</tr>
			<c:set var="detentionFacilityHistoryList" value="${reportInfo.detentionFacilityHistory}" scope="session"/>
			<c:forEach var="detentionFacility" items="${detentionFacilityHistoryList}" varStatus="status">
				<tr>
					<td align="left"><p class="arial-font-10" align="left"><c:out value="${detentionFacility.referralNumber}"/></p></td>
					<td align="left"><p class="arial-font-10" align="left"><c:out value="${detentionFacility.facilityName}"/></p></td>
					<td align="left"><p class="arial-font-10" align="left"><c:out value="${detentionFacility.admitReason}"/></p></td>
					<td align="left"><p class="arial-font-10" align="left">
						<fmt:formatDate value="${detentionFacility.admitDate}" pattern="MM/dd/yyyy" var="formattedAdmitDate" />
						<c:out value="${formattedAdmitDate}"/></p>
					</td>
					<td align="left"><p class="arial-font-10" align="left">
						<fmt:formatDate value="${detentionFacility.releaseDate}" pattern="MM/dd/yyyy" var="formattedReleaseDate" />
						<c:out value="${formattedReleaseDate}"/></p>
					</td>
					<td align="left"><p class="arial-font-10" align="left"><c:out value="${detentionFacility.releaseTo}"/></p></td>
					<td align="left"><p class="arial-font-10" align="left"><c:out value="${detentionFacility.releaseBy}"/></p></td>
				</tr>
				<tr><td align="center" colspan="4" border = "0" padding-top="10"></td></tr>
			</c:forEach>
		</table>
		</div>
	</logic:notEmpty>	
</body>
</pdf>
