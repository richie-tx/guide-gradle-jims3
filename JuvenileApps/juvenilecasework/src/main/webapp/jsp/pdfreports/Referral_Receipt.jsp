<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.common.StringUtil"%>
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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.referral.ReferralReceiptPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.REFERRAL_RECEIPT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.REFERRAL_RECEIPT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size:150; font-family:Helvetica; color:#F0F0F0; }
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .0in; 
		margin-bottom: .6in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
tbody
	{	margin-left: .0in;
    	margin-right: .0in;
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
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.border{
		border:thin solid black; 
}
table.border-top-left {
    border-top: double;
    border-bottom: blank;
    border-right: single
}
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
	{
		border:thin solid black; 
	}
td.border-medium
	{
		border: medium solid black; 
	}
td.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
td.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
td.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}
td.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.times-font-7{
		font-size:7;
		font-family:"Times New Roman", Times, serif;
		font-weight: Italic;
}
p.times-font-9{
		font-size:9;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;
}
span.arial-font-6{
		font-size:6;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;
		text-align:left;
}
span.arial-font-8{
		font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
}
span.times-font-9{
		font-size:9;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;
}
p.times-font-8{
		font-size:8;
		font-family:"Times New Roman", Times, serif;
 		font-style: Italic;
}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-7
	{font-size:7;
		font-family:"Arial", Helvetica, sans-serif;}

p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}

p.arial-font-12-bold
{font-size:12;
		font-family:"Arial", Helvetica, sans-serif; font-weight: bold;}

p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}

p.arial-font-11-bold
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif; font-weight: bold;}

p.arial-font-6-bold
	{font-size:6;
		font-family:"Arial", Helvetica, sans-serif; font-weight: bold; text-align:left;}

p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif; text-align:left;}

p.arial-font-8-bold
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif; font-weight: bold; text-align:left;}

p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}

p.arial-font-9-bold
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		font-weight: bold;}

p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}

p.arial-font-10-bold
	{	
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		font-weight: bold;
	}
p.times-10
	{
		font-size:10;
		font-family:"Times New Roman", Times, serif;
	}
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
				<!--  REFERRAL SOURCE INFORMATION -->
				<table class="border" width="100%">
				 <tr>
				 	<td colspan="6" align="center" width="100%"><span class="arial-font-8"><b>CURRENT REFERRAL DATA</b></span></td>
				 </tr>
				 <tr>
				 	<td colspan="2" align="left"><span class="arial-font-6">Referral Source</span> <span class="arial-font-8"><%=reportInfo.getReferralSource()%></span></td>
				 	<td align="left"><span class="arial-font-6">(entered by </span><span class="arial-font-8"><%=reportInfo.getLcUser()%></span><span class="arial-font-6">)</span></td>
				 	<td colspan="3" align="left"><span class="arial-font-6">Current Assignment Level/JPO </span><logic:notEmpty name="reportInfo" property="inAssignUnitOfficer">
				 	    <span class="arial-font-8"><%=reportInfo.getInAssignUnitOfficer()%></span></logic:notEmpty></td>
				 </tr>
				 <tr>
				 	<td align="left"><span class="arial-font-6">Referral Date </span><span class="arial-font-8"><fmt:formatDate value="${reportInfo.getReferralDate()}" pattern="MM/dd/yy" var="formattedDate"/><![CDATA[${formattedDate}]]>
				 		</span></td>
				 	<td align="left"><span class="arial-font-6">Investigation Number </span><span class="arial-font-8"><%=reportInfo.getInvestigationNumber()%></span></td>
				 	<td align="left"><span class="arial-font-6">Number of Co-Actors </span><span class="arial-font-8"><%=reportInfo.getNumberOfCoActors()%></span></td>
				 	<td align="left"><span class="arial-font-6">Detained </span><span class="arial-font-8"><%=reportInfo.getDetained()%></span></td>
				 	<td colspan="2" align="left"><span class="arial-font-6">Facility<br/> </span><span class="arial-font-8"></span></td>
				 </tr>				 
				 <tr>
				 	<td align="left"><span class="arial-font-6"></span><span class="arial-font-6">Offense Date</span> <span class="arial-font-8"><fmt:formatDate value="${reportInfo.getOffenseDate()}" pattern="MM/dd/yy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></td>
				 	<td colspan="2" align="left"><span class="arial-font-6"></span><span class="arial-font-6">Offense Alpha Code &nbsp;&nbsp;&nbsp;</span><span class="arial-font-8"><%=reportInfo.getOffenseAlphaCode()%></span></td>
				 	<td align="left"><span class="arial-font-6"></span><span class="arial-font-8"></span></td>
				 	<td colspan="2" align="left"><span class="arial-font-6"></span><span class="arial-font-8"><%=reportInfo.getFacility()%></span></td>
				 </tr>
				</table>
				<!--  END REFERRAL SOURCE INFORMATION -->
			</div>
		</macro>
		<macro id="myheader">
			<div class="header" align="center">
				
			</div>
		</macro>	
	</macrolist>
</head>

	<body footer="myfooter" footer-height="5mm">

<!--  Header information -->

<div class="body">
<!--  JUVENILE IDENTIFICATION -->
	<table  border-style="none" width="100%">
		<tr align="center">
			<td align="center"><p class="arial-font-11"><b>HARRIS COUNY JUVENILE PROBATION DEPARTMENT</b></p></td>
		</tr>
		<tr align="center">
			<td align="center"><p class="arial-font-11"><b>REFERRAL RECEIPT</b></p></td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="17%"></td>
			<td align="left" width="15%"></td>
			<td align="left" width="15%"></td>
			<td align="left" width="13%"></td>
			<td align="left" width="12%"></td>
			<td align="left" width="13%"></td>
			<td align="left" width="15%"></td>
		</tr>
		<tr align="left">
			<td colspan="7" class="arial-font-10" align="left"><b>JUVENILE IDENTIFICATION ------------------------------------------------------------------------------------------------------------------ </b></td>
		</tr>
		<tr align="left">
			<td><nobr><span class="arial-font-6">NAME AND ADDRESS</span></nobr></td>
			<td><nobr><span class="arial-font-6">RACE/SEX/AGE</span></nobr></td>
			<td><nobr><span class="arial-font-6">DATE OF BIRTH</span></nobr></td>
			<td><nobr><span class="arial-font-6">VERI-?</span></nobr></td>
			<td><nobr><span class="arial-font-6">STATUS</span></nobr></td>
			<td><nobr><span class="arial-font-6">REF CNT</span></nobr></td>
			<td><nobr><span class="arial-font-6">CASE NUMBER</span></nobr></td>
		</tr>
		<tr align="left">
			<td><nobr><span class="arial-font-8"><%=reportInfo.getFormattedName()%></span></nobr></td>
			<td><nobr><span class="arial-font-8"><%=reportInfo.getRaceSexAge()%></span></nobr></td>
			<td><logic:notEmpty name="reportInfo" property="dateOfBirth"><nobr><span class="arial-font-8"><%=reportInfo.getDateOfBirth()%></span></nobr></logic:notEmpty></td>
			<td><nobr><span class="arial-font-8"><%=reportInfo.getVerifiDOB()%></span></nobr></td>
			<td><logic:notEmpty name="reportInfo" property="status"><nobr><span class="arial-font-8"><%=reportInfo.getStatus()%></span></nobr></logic:notEmpty></td>
			<td><nobr><span class="arial-font-8"><%=reportInfo.getRefCnt()%></span></nobr></td>
			<td><nobr><span class="arial-font-8"><%=reportInfo.getCaseNumber()%></span></nobr></td>
		</tr>
		<tr align="left">
			<td colspan="2"><logic:notEmpty name="reportInfo" property="juvFormattedAddress"><nobr><span class="arial-font-8"><%=reportInfo.getJuvFormattedAddress()%></span></nobr></logic:notEmpty></td>
			<td colspan="2"><logic:notEmpty name="reportInfo" property="county"><nobr><span class="arial-font-8"><%=reportInfo.getCounty()%> COUNTY</span></nobr></logic:notEmpty></td>
			<td colspan="2"><nobr><span class="arial-font-8">SSN <%=reportInfo.getFormattedSSN()%></span></nobr></td>
			<td valign="bottom"><span class="arial-font-6"><nobr>LAST ASSIGNED</nobr><br/>UNIT/OFFICER</span></td>
		</tr>
		<tr align="left">
			<td><logic:notEmpty name="reportInfo" property="keyMap"><span class="arial-font-6">KEY MAP: </span><span class="arial-font-8"><%=reportInfo.getKeyMap()%></span></logic:notEmpty></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><logic:notEmpty name="reportInfo" property="inAssignUnitOfficer"><nobr><span class="arial-font-8"><%=reportInfo.getInAssignUnitOfficer()%></span></nobr></logic:notEmpty></td>
		</tr>
	</table>
</div>
<!--  END JUVENILE IDENTIFICATION -->
<!--  FAMILY AND SOCIAL INFORMATION -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="left">
			<td colspan="2" class="arial-font-10" align="left"><b>FAMILY AND SOCIAL INFORMATION -------------------------------------------------------------------------------------------------------</b></td>
		</tr>
		<tr align="left">
			<td align="left">
				<table border-style="border-top-right">
					<tr align="left">
						<td><nobr><span class="arial-font-6">LEGAL FATHER'S NAME AND ADDRESS</span></nobr></td>
					</tr>
					<logic:notEmpty name="reportInfo" property="fathersName">
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getFathersName()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getFathersAddress()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getFathersPhone()%></span></nobr></td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="fathersName">
						<tr align="left">
							<td><nobr><span class="arial-font-8">NONE LISTED</span></nobr></td>
						</tr>
					</logic:empty>
					<tr align="left">
						<td><nobr><span class="arial-font-6">LEGAL MOTHER'S NAME AND ADDRESS</span></nobr></td>
					</tr>
					<logic:notEmpty name="reportInfo" property="mothersName">
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getMothersName()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getMothersAddress()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getMothersPhone()%></span></nobr></td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="mothersName">
						<tr align="left">
							<td><nobr><span class="arial-font-8">NONE LISTED</span></nobr></td>
						</tr>
					</logic:empty>
					<tr align="left">
						<td><nobr><span class="arial-font-6">RELATIVE OR SIGNIFICANT OTHER PERSON</span></nobr></td>
					</tr>
					<logic:notEmpty name="reportInfo" property="otherName">
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getOtherName()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getOtherAddress()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getOtherPhone()%></span></nobr></td>
						</tr>
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getOtherRelationship()%></span></nobr></td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="otherName">
						<tr align="left">
							<td><nobr><span class="arial-font-8">NONE LISTED</span></nobr></td>
						</tr>
					</logic:empty>
				</table>
			</td>
			<td align="left">
				<table border-style="border-top-right">
					<tr align="left">
						<td><nobr><span class="arial-font-6">SCHOOL NAME</span></nobr></td>
					</tr>
					<logic:notEmpty name="reportInfo" property="school">
						<tr align="left">
							<td><nobr><span class="arial-font-8"><%=reportInfo.getSchool()%></span></nobr></td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="school">
						<tr align="left">
							<td><nobr><span class="arial-font-8">NONE LISTED</span></nobr></td>
						</tr>
					</logic:empty>
					<tr align="left">
						<td class="arial-font-8"><nobr></nobr></td>
					</tr>
					<tr align="left">
						<td align="left" width="50%"></td>
					</tr>
					<tr align="left">
						<td><nobr><span class="arial-font-6">Alias</span></nobr></td>
					</tr>
					<tr align="left">
						<td><span class="arial-font-8"><%=reportInfo.getAlias()%></span></td>
					</tr>
				</table>
			</td>
		</tr>
		<logic:notEmpty name="reportInfo" property="contactPhone">
		<tr align="left">
			<td colspan="2"><nobr><span class="arial-font-6">Phone </span><span class="arial-font-8"><%=reportInfo.getContactPhone()%></span></nobr></td>
		</tr>
		</logic:notEmpty>
	</table>
</div>
<!--  END FAMILY AND SOCIAL INFORMATION -->
<div class="body">
	<table width="100%" border-style="1">
		<tr>
			<td align="center" colspan="13"><span class="arial-font-8">(+++++++++++++CONFIDENTIAL++++++++++++CONFIDENTIAL+++++++++++++CONFIDENTIAL++++++++++++CONFIDENTIAL+++++++++++++)</span></td>
		</tr>
		<tr>
			<td align="center" colspan="6"><span class="arial-font-6">INTAKE HISTORY</span></td>
			<td align="center" colspan="6"><span class="arial-font-6">COURT HISTORY</span></td>
		</tr>
		<tr align="center" valign="bottom">
			<td valign="bottom"><span class="arial-font-6">REFERRAL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b/>DATE</span></td>
			<td valign="bottom"><span class="arial-font-6">OFFENSE OR &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b/>CONDITION</span></td>
			<td valign="bottom"><span class="arial-font-6">JPO</span></td>
			<td valign="bottom"><span class="arial-font-6">DECISION</span></td>
			<td valign="bottom"><span class="arial-font-6">DATE</span></td>
			<td valign="bottom"><span class="arial-font-6">DETAIN &nbsp;&nbsp;&nbsp; <b/>DAYS</span></td>
			<td valign="bottom"><span class="arial-font-6">COURT</span></td>
			<td valign="bottom"><span class="arial-font-6">ALLEGATION</span></td>
			<td valign="bottom"><span class="arial-font-6">PETITION</span></td>
			<td valign="bottom"><span class="arial-font-6">ADJUDI- &nbsp;&nbsp; <b/>CATION</span></td>
			<td valign="bottom"><span class="arial-font-6">DISPO- &nbsp;&nbsp;&nbsp; <b/>SITION</span></td>
			<td valign="bottom"><span class="arial-font-6">DATE</span></td>
			<td valign="bottom"><span class="arial-font-6">PROB &nbsp;&nbsp;&nbsp;&nbsp; <b/>JPO</span></td>
		</tr>
		<logic:notEmpty name="reportInfo" property="juvProfRefList">
			<c:forEach var="juvProfRef" items="${reportInfo.juvProfRefList}" varStatus="loop">
				<c:if test="${not empty juvProfRef.referralDate}">
					<tr align="left">
						<td><nobr><span class="arial-font-8"><fmt:formatDate value="${juvProfRef.referralDate}" pattern="MM/dd/yy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.offense}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.jpoId}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.intakeDecisionId}</span></nobr></td>
						<td><nobr><span class="arial-font-8"><fmt:formatDate value="${juvProfRef.closeDate}" pattern="MM/dd/yy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></nobr></td>
						<td align="right"><span class="arial-font-8">${juvProfRef.detDays} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.courtId}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.petitionAllegation}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.petitionNumber}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.courtResult}</span></nobr></td>
						<td><nobr><span class="arial-font-8">${juvProfRef.finalDisposition}</span></nobr></td>
						<td><nobr><span class="arial-font-8"><fmt:formatDate value="${juvProfRef.courtDate}" pattern="MM/dd/yy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></nobr></td>
						<c:if test="${not empty juvProfRef.courtId}">
							<td><nobr><span class="arial-font-8">${juvProfRef.ctAssignJpoId}</span></nobr></td>
						</c:if>
					</tr>
				</c:if>
			</c:forEach>
		</logic:notEmpty>			
	</table>
</div>
</body>
</pdf>