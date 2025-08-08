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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.interviewinfo.form.RequestAppointedAttorneyPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.REQUEST_ATTORNEY_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.REQUEST_ATTORNEY_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: 0.0in;
    	margin-right: 0.0in;
		margin-top: 0.0in; 
		margin-bottom: 0.0in;}
div.body
	{	font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
img.display
    {
   		display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;
    }
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.bold 
	{font-weight: bold;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
p.text-margin-0px 
	{margin: 0em;}
p.text-margin-10px
	{margin: 10px;}
span.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
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
td.arial-font-9
	{font-size:9;
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
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="right" width="100%"><b>QUALIFIES FOR COURT APPOINTED ATTORNEY: </b>
				<img class="display" height="10" width="10" src="images/unchecked.jpg"/> <b>YES</b> 
				<img class="display" height="10" width="10" src="images/unchecked.jpg"/> <b>NO</b>
			</td>
		</tr>
		<tr align="center">
			<td align="right" width="100%"><b>OFFENSE LEVEL: </b><span class="times-font-9"><i>${reportInfo.offenseLevel}</i></span></td>
		</tr>
		<tr align="center">
			<td align="center" width="100%" padding-top="10"><b>HARRIS COUNTY JUVENILE PROBATION DEPARTMENT test 1</b></td>
		</tr>
		<tr align="center">
			<td align="center" width="100%"><b>FINANCIAL STATEMENT</b></td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="2"><b>RESPONDENT: </b>
				<span class="times-font-9"><i>${reportInfo.juvenileName.formattedName}</i></span>
			</td>
			<td align="left" width="15%"></td>
			<td align="left" width="35%"><b>JUVENILE#: </b>
				<span class="times-font-9"><i><%=reportInfo.getJuvenileNum()%></i></span>
			</td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" width="25%"><b>COURT: </b>
				<span class="times-font-9"><i><%=reportInfo.getCourtId()%><sup>TH</sup></i></span>
			</td>
			<td align="left" colspan="2"><b>PETITION #: </b>
				<span class="times-font-9"><i><%=reportInfo.getPetitionNum()%></i></span>
			</td>
			<td align="left" width="35%"><b>PENDING COURT DATE: </b>
				<fmt:formatDate value="${reportInfo.pendingCourtDate}" pattern="MMM d, yyyy" var="formattedDate" />
				<span class="times-font-9"><i>${formattedDate}</i></span>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4"><b>WHO IS FINANCIALLY RESPONSIBLE FOR YOUTH: </b>(WHOM DOES THE YOUTH LIVE WITH?)</td>
		</tr>
		<c:set var="guardianList" value="${reportInfo.guardians}" scope="session"/>
			<c:forEach var="guardian" items="${guardianList}" varStatus="status">
				<tr>
					<td align="left" colspan="4"><span class="times-font-9"><i>
						<c:out value="${guardian.name.formattedName}"/> (<c:out value="${guardian.relationshipToJuv}"/>)</i></span>
					</td>
				</tr>
			</c:forEach>
		<tr>
			<td align="left" colspan="4">
				<b>=====================================================================================================</b>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4"><img class="display" height="10" width="10" src="images/unchecked.jpg"/>
				 <b> FAMILY TO RETAIN ATTORNEY (name): </b>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="4">
				<b>=====================================================================================================</b>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4"><b>RESPONSIBLE PARENT(S) / GUARDIAN(S) </b>(IF YOUTH LIVES IN A TWO PARENT / GUARDIAN HOUSEHOLD; 
				BOTH MEMBER'S FINANCIAL INFOMATION MUST BE LISTED / STEP-PARENT AND, OF LIVE-INS SHOULD BE LISTED AS WELL)
			</td>
		</tr>
		<c:set var="guardianList" value="${reportInfo.guardians}" scope="session"/>
			<c:forEach var="guardian" items="${guardianList}" varStatus="status">
				<tr>
					<td align="left" colspan="2"><b><c:out value="${guardian.memberNumber+1}"/>. NAME: </b>
						<span class="times-font-9"><i><c:out value="${guardian.name.formattedName}"/></i></span>
					</td>
					<td align="left" colspan="2"><b>RELATION TO YOUTH: </b>
						<span class="times-font-9"><i><c:out value="${guardian.relationshipToJuv}"/></i></span>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="3"><b>Address: </b><span class="times-font-9"><i>
						<c:out value="${guardian.address.formattedAddress}"/></i></span>
					</td>
					<td align="left" width="25%"><b>Phone: </b><span class="times-font-9"><i>
						<c:out value="${guardian.phone.formattedPhoneNumber}"/></i></span>
					</td>
				</tr>
				<c:forEach var="employment" items="${guardian.employmentRecord}" varStatus="status">
					<tr>
						<td align="left" colspan="3"><b>Place of Employment: </b><span class="times-font-9"><i>
							<c:out value="${employment.currentEmployer}"/> (
							<c:out value="${employment.employmentStatus}"/>)</i></span>
						</td>
						<td align="left" width="25%"><b>Annual Income: </b><span class="times-font-9"><i>
							<fmt:formatNumber value="${employment.annualNetIncomeAsDouble}" type="currency"/>
							</i></span>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td align="left" colspan="4">
						<b>=====================================================================================================</b>
					</td>
				</tr>
			</c:forEach>	
		<logic:notEmpty name="reportInfo" property="otherKnownParent">
			<tr>
				<td align="left" colspan="4"><b>OTHER KNOWN PARENT / GUARDIAN </b>(if applicable)</td>
			</tr>
			<c:set var="otherList" value="${reportInfo.otherKnownParent}" scope="session"/>
				<c:forEach var="other" items="${otherList}" varStatus="status">
					<tr>
						<td align="left" colspan="2"><b><c:out value="${other.memberNumber+1}"/>. NAME: </b>
							<span class="times-font-9"><i><c:out value="${other.name.formattedName}"/></i></span>
						</td>
						<td align="left" colspan="2"><b>RELATION TO YOUTH: </b>
							<span class="times-font-9"><i><c:out value="${other.relationshipToJuv}"/></i></span>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="3"><b>Address: </b><span class="times-font-9"><i>
							<c:out value="${other.address.formattedAddress}"/></i></span>
						</td>
						<td align="left" width="25%"><b>Phone: </b><span class="times-font-9"><i>
							<c:out value="${other.phone.formattedPhoneNumber}"/></i></span>
						</td>
					</tr>
					<c:forEach var="employment" items="${other.employmentRecord}" varStatus="status">
						<tr>
							<td align="left" colspan="3"><b>Place of Employment: </b><span class="times-font-9"><i>
								<c:out value="${employment.currentEmployer}"/> (
								<c:out value="${employment.employmentStatus}"/>)</i></span>
							</td>
							<td align="left" width="25%"><b>Annual Income: </b><span class="times-font-9"><i>
								<fmt:formatNumber value="${employment.annualNetIncomeAsDouble}" type="currency"/>
								</i></span>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="left" colspan="4">
							<b>=====================================================================================================</b>
						</td>
					</tr>
				</c:forEach>	
		</logic:notEmpty>
		<tr align="center">
			<td align="left" colspan="2">IS THE FAMILY RECEIVING: <b>SSI: </b>
				<span class="times-font-9"><i><%=reportInfo.getSsi()%></i></span>
			</td>
			<td align="left" width="25%"><b>FOOD STAMPS: </b>
				<span class="times-font-9"><i><%=reportInfo.getFoodStamps()%></i></span>
			</td>
			<td align="left" width="25%"><b>CHILD SUPPORT: </b>
				<span class="times-font-9"><i><%=reportInfo.getChildSupport()%></i></span>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2">OTHER: TANF 
				<span class="times-font-9"><i><%=reportInfo.getTanf()%></i></span>
			</td>
			<td align="left" colspan="2">IS YOUTH COVERED BY MEDICAID? 
				<span class="times-font-9"><i><%=reportInfo.getYouthCoveredByMedicaid()%></i></span>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="4">
				<b>=====================================================================================================</b>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2"><b>TOTAL ANNUAL INCOME: </b>
				
				<span class="times-font-9"><i><fmt:formatNumber value="${reportInfo.totalAnnualIncome}" type="currency"/></i></span>
			</td>
			<td align="left" colspan="2"><b>NUMBER OF HOUSEHOLD: </b>
				<span class="times-font-9"><i><%=reportInfo.getNumberInHousehold()%></i></span>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="4">
				<b>=====================================================================================================</b>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4"><b>ADDITIONAL COMMENTS: </b>
				<span class="times-font-9"><i><%=reportInfo.getAdditionalComments()%></i></span>
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4" padding-top="10"><b>Signature of responsible party: 
				</b>_______________________________________________________________________________
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4">Under penalties of perjury I certify that the above information is true and correct to the best of my 
				knowledge and that it is a full and accurate disclosure of all sources of income and expenses.  I further understand that I may be 
				required to supplay additional documentation to verify any of the above information.
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4" padding-top="10"><b>Juvenile Probation Officer: 
				</b>_______________________________________________________________
				<b>Date: </b>______________
			</td>
		</tr>
		<tr align="center">
			<td align="left" colspan="4" padding-top="10"><b>Supervision Approval: 
				</b>______________________________________________________________________________________
			</td>
		</tr>
	</table>	
</div>
</body>
</pdf>
