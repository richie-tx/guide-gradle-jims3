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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.CommonAppReportPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.COMMON_APPLICATION_FOR_PLACEMENT_OF_CHILDREN.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.COMMON_APPLICATION_FOR_PLACEMENT_OF_CHILDREN.getReportName()%>"/>
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
tbody
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
p
{
 	padding: 0px;
	margin:0px;
}

img.display
    {	display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;}
span.underline
	{	border-bottom:1px solid #555;}
table.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
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
td.border
	{
		border:thin solid black; 
	}
td.border-medium
	{
		border: medium solid black; 
	}
td.times-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
td.times-bold-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.times-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
th.border
	{	color:#fffffff;
		background-color:#000000;
		font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
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
span.times-font-8{
		font-size:8;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;
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
p.line-space-115
	{line-height: 115%;}
p.line-space-125
	{line-height: 125%;}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="85%">
						Page <pagenumber/> of <totalpages/>
					</td>
					<td width="15%">TJPC-AGE-06-04</td>
				 </tr>
				</table>
			</div>
		</macro>	
		<macro id="myheader">
			<div class="header" align="center">
				<table  border-style="none" width="100%">
					<tr align="center">
						<td align="center"><p class="arial-font-11"><b>COMMON APPLICATION FOR PLACEMENT OF CHILDREN</b></p></td>
					</tr>
					<tr align="center">
						<td align="center"><p class="arial-font-11"><b>INTERAGENCY APPLICATION FOR PLACEMENT (IAP)</b></p></td>
					</tr>
				</table>
			</div>
		</macro>
	</macrolist>
</head>
<body footer="myfooter" footer-height="1mm" header="myheader" header-height="60pt">
	<div class="body" page-break-before="avoid">
		<p align="center" class="arial-font-11">
			<b>LEVEL OF CARE ASSESSMENT</b>
		</p>
		<p align="center" class="arial-font-10">
			<b>A. Screening Profile</b>
		</p>
	    <table  width="100%" border-style="1" page-break-inside="auto" padding-bottom="5" padding-left="5">
			<tr align="left" valign="top" colspan="8" cellmargin="0" cellpadding="0">
				<td class="border" colspan="3"><p  class="arial-font-8"><nobr>Child's Name</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvName}]]></span></td>
				<td class="border" colspan="2"><p  class="arial-font-8"><nobr>State Identification #:</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.stateID}]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Date of Birth</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvDOB}]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Age</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvAge}]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Social Security Number</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvSSN}]]></span></td>
			</tr>
			 <tr align="left" valign="top" colspan="8" cellmargin="0" cellpadding="0">
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Sex</nobr></p><span  class="times-font-8"><![CDATA[<%=reportInfo.getJuvSex()%>]]></span></td>
				<td class="border" colspan="2"><p  class="arial-font-8"><nobr>Ethnicity</nobr></p><span  class="times-font-8"><![CDATA[<%=reportInfo.getJuvEthnicity()%>]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8">Primary Language</p><span  class="times-font-8"><![CDATA[<%=reportInfo.getJuvPrimaryLanguage()%>]]></span></td>
				<!--Bug fix :11237 added state and county for printing  -->
				<td class="border" colspan="3"><p  class="arial-font-8">Place of Birth (city, state, county)</p><span class="times-font-8"><![CDATA[<%=reportInfo.getJuvBirthCity()%>]]>,<![CDATA[<%=reportInfo.getJuvBirthState()%>]]>,<![CDATA[<%=reportInfo.getJuvBirthCounty()%>]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Child's Agency ID Number</nobr></p><span class="times-font-8"><![CDATA[<%=reportInfo.getJuvAgencyID()%>]]></span></td>
			</tr>
			<tr align="left" valign="top" colspan="8" cellmargin="0" cellpadding="0">
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Height</nobr></p><span class="times-font-8"><![CDATA[${reportInfo.juvHeight}]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Weight</nobr></p><span class="times-font-8"><![CDATA[${reportInfo.juvWeight}]]></span></td>
				<td class="border" colspan="2"><p  class="arial-font-8"><nobr>Religious Preference</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvReligiousPreference}]]></span></td>
				<td class="border" colspan="3"><p  class="arial-font-8"><nobr>Child's Current Location or Placement</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.answer36}]]></span></td>
				<td class="border" colspan="1"><p  class="arial-font-8"><nobr>Country of Citizenship</nobr></p><span  class="times-font-8"><![CDATA[${reportInfo.juvCountryOfCitizenship}]]></span></td>
			</tr>
		</table>
	</div>
	
	<!-- Special Needs, Programs and Behaviors:-->
	<div class="body">	
		<table border-style="none" border="0" cellmargin="0" cellpadding="0" width="675px"  page-break-inside="auto">
			<tr padding-bottom="10">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>1.</nobr></p></td>
				<td vertical-align="top" width="98%" ><p class="arial-font-10-bold">Briefly describe your impressions of the child including present problems:</p></td>
			</tr>
			<tr  padding-bottom="10">
				<td  vertical-align="top" width="2%" align="left"></td>
				<td  vertical-align="top" align="left" width="98%"><span  class="times-font-8"><![CDATA[${reportInfo.answer1}]]></span></td>
			</tr>
			<tr  padding-bottom="10">
				<td  vertical-align="top" width="2%" align="left"></td>
				<td  vertical-align="top" align="left" width="98%"><p class="arial-font-9-bold">Briefly describe the child's strengths:</p></td>
			</tr>
			<tr  padding-bottom="10"  align="left">
				<td  vertical-align="top" width="2%" align="left"></td>
				<td  vertical-align="top" align="left" width="98%"><span  class="times-font-8"><![CDATA[${reportInfo.answer2}]]></span></td>
			</tr>
			<tr valign="top" padding-bottom="3">
				<td vertical-align="top" width="1%"><p class="arial-font-10-bold"><nobr>2.</nobr></p></td>
				<td vertical-align="top" width="98%"><p class="arial-font-10-bold">Special Needs, Programs and Behaviors:</p></td>
			</tr>
			<tr valign="top">
				<td valign="top"  colspan="4">
					<table border-style="none" border="0" cellmargin="0" cellpadding="0" padding-left="5" padding-bottom="5" width="100%">
						<tr valign="top">
							<td class="border"  align="left" colspan="1" width="20%"><p class="arial-font-8"><nobr>&nbsp;Is child considered a danger to self?</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.childConsideredDangerToSelf}]]></span></td>
							<td class="border"  align="left" colspan="1" width="20%"><p class="arial-font-8"><nobr>&nbsp;Is child considered a danger to others?</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.childConsideredDangerToOthers}]]></span></td>
							<td class="border"  align="left" colspan="1" width="20%"><p class="arial-font-8"><nobr>&nbsp;Number runaways from home:</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.noRunawaysFromHome}]]></span></td>
							<td class="border" height="30"  colspan="1" width="40%"><p class="arial-font-8"><nobr>&nbsp;Number runaways from placement:</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer33}]]></span></td> 
						</tr>
						<tr  valign="top">
							<td class="border" height="30" colspan="1" align="left" width="25%"><p class="arial-font-8"><nobr>&nbsp;Any History of setting fires?</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.historyOfSettingFires}]]></span></td>
							<!--bug fix: 11244 & 32220 starts-->
							<td class="border" height="30" colspan="1" align="left" width="25%">
								<p class="arial-font-8"><nobr>&nbsp;Special Program Needs?</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.specialNeedsOthers}]]></span>
							</td>
							<td class="border" colspan="2"  align="left" padding-left="1">
								<p class="arial-font-8"><nobr>&nbsp;Specify:</nobr><br/></p>
								<c:forEach var="splNeeds" items="${reportInfo.specialNeedsSpecify}" varStatus="loop">
									<span  class="times-font-8">&nbsp;<![CDATA[${splNeeds}]]></span><br/>
								</c:forEach>
							</td>
							<!--bug fix: 11244 & 32220 ends-->
						</tr>
						<tr  height="30"  valign="top" align="left">
							<td class="border" colspan="4" align="left">
								<p class="arial-font-8"><nobr>&nbsp;Other Significant Problems or Behaviors:</nobr><br/><span class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer21}]]></span></p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- Juvenile Justice History -->
	<div class="body">
		<table border-style="none" width="100%" cellmargin="0" cellpadding="0"  page-break-inside="auto">
			<tr  padding-bottom="3">
				<td vertical-align="top" width="2%">
					<p class="arial-font-10-bold"><nobr>3.</nobr></p>
				</td>
				<td vertical-align="top" align="left" width="98%">
					<p class="arial-font-10-bold"><nobr>Juvenile Justice History</nobr></p>
				</td>
			</tr>
			<tr padding-bottom="1"> 	
				<!-- ANSWER IS ALWAYS YES, PER REQUIREMENT-->
				<td  vertical-align="top" width="2%" align="left"></td>
				<td vertical-align="top" width="98%">
					<p class="arial-font-10"><nobr>Does the child have a history of involvement with the Juvenile Justice System?</nobr><span  class="times-font-8" ><nobr>&nbsp;YES</nobr></span></p>
				</td>
			</tr>
			<tr padding-bottom="10">
				<td colspan="4">
					<table>
						<tr>
							<td vertical-align="top" align="left" colspan="4"><p class="arial-font-10">If Yes:</p></td>
						</tr>
						<tr height="30">
							<td class="border" colspan="1"><p class="arial-font-8">Number of referrals to juvenile authorities:</p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.countReferrals}]]></span></td>
							<td class="border" colspan="1"><p class="arial-font-8">Number of adjudications for delinquent acts:</p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.countAdjudicationsTotal}]]></span></td>
							<td class="border" colspan="1"><p class="arial-font-8">Number of adjudication of CINS offenses:</p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.countCINSAdjudication}]]></span></td>
							<td class="border" colspan="1"><p class="arial-font-8">Current Offense:</p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.currentOffense}]]></span></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div> 
	
	<!-- Placement History -->
	<div class="body">
		<table border-style="none" width="100%" cellmargin="0" cellpadding="0" page-break-inside="auto">
			<tr  padding-bottom="3">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>4.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Placement History</nobr></p></td>
			</tr>
			<tr padding-bottom="3">
				<td vertical-align="top" width="2%" colspan="1"></td>
				<td  vertical-align="top" align="left" width="98%" colspan="2">
					<p class="arial-font-10"><nobr>Has the child been placed away from home before?</nobr>
						<c:choose>
							<c:when test="${reportInfo.averageNumberOfChildCareDays > 0}">
								<span  class="times-font-8">&nbsp;YES</span>
							</c:when>
							<c:otherwise>
								<span  class="times-font-8">&nbsp;NO</span>
							</c:otherwise>
						</c:choose>
					</p>
				</td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%" colspan="1"></td>
				<td vertical-align="top" align="left" width="98%" colspan="2">
					<p class="arial-font-10">Do not include stopover placements such as emergency shelters, detention, TYC Reception Center, informal placements with relatives, or return(s) to home</p>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table border-style="none" width="100%" cellmargin="0" cellpadding="0" padding-left="5" padding-bottom="5">
						<tr  valign="top">
							<td class="border" colspan="1" height="30" width="20%">
								<p class="arial-font-8">&nbsp;If Yes: Number of previous out-of-home placements:</p>
									<c:if test="${reportInfo.outofHomePlacements > 0}">
										<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.outofHomePlacements}]]></span>
									</c:if>
							</td>
							<td class="border" colspan="1" height="30" width="40%">
								<p class="arial-font-8"><nobr>&nbsp;Number of failed adoption placements:</nobr></p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.numberOfFailedAdoptionPlacement}]]></span>
							</td> 
							<td class="border" colspan="1" height="30" width="40%">
								<p class="arial-font-8"><nobr>&nbsp;LOC of current/most recent out-of-home placement:</nobr></p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.locCurrentOutofHomePlacement}]]></span>
							</td>
						</tr>
						<tr  valign="top" padding-bottom="20">
							<td class="border" vertical-align="top" align="left" colspan="3">
								<p class="arial-font-8"><nobr>&nbsp;Date of discharge from most recent out-of-home placement:<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.recentOutOfHomeDischargeDate}]]></span></nobr></p>
							</td>
						</tr>
						<tr>
							<td vertical-align="top" align="left" width="98%" colspan="3">
								<p class="arial-font-10"><nobr>Reason for Discharge:</nobr><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.dischargeReason}]]></span></p>
							</td>
						</tr> 
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- Substance Abuse History-->
	<div class="body">
		<table border-style="none" width="100%" cellmargin="0" cellpadding="0" page-break-inside="auto">
			<tr  padding-bottom="3">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>5.</nobr></p></td>
				<td  vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Substance Abuse History</nobr></p></td>
			</tr>
			<tr colspan="2">
				<td vertical-align="top" width="2%"></td>
				<td   vertical-align="top" align="left" width="98%">
					<p class="arial-font-10"><nobr>Does the child have a history of substance abuse?</nobr>
					<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.substanceAbuseHistory}]]></span></p> 
				</td>
			</tr>
			<c:if test="${reportInfo.substanceAbuseHistory=='YES'}">
				<tr padding-bottom="5">
					<td vertical-align="top" width="2%"></td>
					<td colspan="3"><p  class="arial-font-10"><br/>If yes, indicate degree of substance abuse:</p></td>
				</tr>
				
				<c:forEach var="drugTypes" items="${reportInfo.drugTypeAndDegree}" varStatus="loop">
					<tr  padding-bottom="3">
						<td vertical-align="top" width="2%"></td>
						<td vertical-align="top" align="left" width="98%"><span  class="times-font-8">&nbsp;<![CDATA[- ${drugTypes}]]></span></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr padding-bottom="10">
				<td vertical-align="top" width="2%"></td>
				<td  vertical-align="top" align="left" width="98%">
					<p class="arial-font-10"><br/>
						<nobr>Is specialized Program Required?</nobr><span  class="times-font-8"> &nbsp;<![CDATA[${reportInfo.specializedProgramReq}]]> <br/></span>
						<c:if test="${reportInfo.specializedProgramReq=='YES'}">
							<p  class="arial-font-10">If yes, specify:</p><span class="times-font-8">&nbsp;<![CDATA[${reportInfo.specializedProgramSpecify}]]></span>
						</c:if>
					</p>
				</td>
			</tr>
		</table>
	</div>
	
	<!--History of Abuse/Neglect of Referred Child-->
	<table border-style="none" width="100%" cellmargin="0" cellpadding="0">
		<tr padding-bottom="3">
			<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>6.</nobr></p></td>
			<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>History of Abuse/Neglect of Referred Child</nobr></p></td>
		</tr>
		<tr padding-bottom="3">
			<td vertical-align="top" width="2%"></td>
			<td  align="left" colspan="3" width="98%">
				<p class="arial-font-10">
					<nobr>Does the child have a history of abuse or neglect?</nobr><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.abuseNeglectHistory}]]></span>
				</p>
			</td>
		</tr>
		<c:if test="${reportInfo.abuseNeglectHistory=='YES'}">
			<tr padding-bottom="5" colspan="3">
				<td vertical-align="top" width="2%"></td>
				<td vertical-align="top" width="98%"><p class="arial-font-10"><br/>If yes, indicate degree of abuse/neglect:<br/></p></td>
			</tr>
			
			<c:forEach var="abuseDegree" items="${reportInfo.abuseTypeAndDegree}" varStatus="loop">
				<tr  padding-bottom="5">
					<td vertical-align="top" width="2%"></td>
					<td vertical-align="top" width="98%">
						<span  class="times-font-8">&nbsp;<![CDATA[- ${abuseDegree}]]></span><br/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr padding-bottom="3">
			<td vertical-align="top" width="2%"></td>
			<td vertical-align="top" width="98%">
				<p class="arial-font-10"><nobr>Abandonment?</nobr> <span class="times-font-8">&nbsp;<![CDATA[${reportInfo.abandonment}]]></span></p>
			</td>
		</tr>
	</table>
	
	
	<!--Family/Parental Involvement-->
	<div class="body"  page-break-before="always">
		<table border-style="none" width="100%" cellmargin="0" cellpadding="0"  page-break-inside="auto">
			<tr  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>7-8.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Family/Parental Involvement</nobr></p></td>
			</tr>
			<tr>
				<td colspan="3">
					<table border-style="none" width="100%" cellmargin="0" cellpadding="0" padding-left="5" padding-bottom="5">
						<tr valign="top">
							<td class="border" colspan="1" height="30" width="40%">
								<p class="arial-font-8"><nobr>&nbsp;Managing Conservator</nobr></p><p  class="times-font-8"><![CDATA[${reportInfo.managingConservator}]]></p>
							</td>
							<td class="border" colspan="1" height="30" width="30%">
								<p class="arial-font-8">&nbsp;Mother's Parental Rights Terminated</p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.motherParentalRightsTerminated}]]></span>
							</td> 
							<td class="border" colspan="1" height="30" width="30%">
								<p class="arial-font-8">&nbsp;Father's Parental Rights Terminated</p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.fatherParentalRightsTerminated}]]></span>
							</td>
						</tr>
						<tr  valign="top" padding-bottom="5">
							<td class="border" colspan="1" height="30">
								<p class="arial-font-8"><nobr>&nbsp;Will family/others participate in treatment or cooperate with others?</nobr></p><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer3}]]></span>
							</td>
							<td class="border" colspan="2" height="30">
								<p class="arial-font-8"><nobr>&nbsp;Can child return home?</nobr><br/><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer32}]]></span></p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!--Education-->
	<div class="body">
		<table border-style="none" width="100%" cellmargin="0" cellpadding="0"  page-break-inside="auto" padding-left="5" padding-bottom="5">
			<tr  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>9.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Education</nobr></p></td>
			</tr>
			<tr>
				<td colspan="3"> 
					<table border-style="none" width="100%" cellmargin="0" cellpadding="0">
						<tr  class="margin-top-6 margin-bottom-6">
							<td class="border" colspan="1" height="30" width="30%">
								<p  class="arial-font-8"><nobr>&nbsp;Highest Grade Completed</nobr></p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.currentGradeLevel}]]></span>
							</td>
							<td class="border" colspan="1" height="30" width="30%">
								<p class="arial-font-8"><nobr>&nbsp;Currently Enrolled in School?</nobr></p>
								<span class="times-font-8">&nbsp;<![CDATA[${reportInfo.currentlyAttending}]]></span>
							</td> 
							<td class="border" colspan="1" height="30" width="40%">
								<p class="arial-font-8"><nobr>&nbsp;Educational Needs</nobr></p>
								<span class="times-font-8"><![CDATA[${reportInfo.programAttending}]]></span>
							</td>
						</tr>
						<tr>
							<td class="border" colspan="3" height="30">
								<p class="arial-font-8" colspan="3"><nobr>&nbsp;History of Truancy?</nobr><br/><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.trauncyHistory}]]></span></p>
							</td>
						</tr>
						<tr>
							<td class="border" colspan="1" height="30">
								<p class="arial-font-8"><nobr>&nbsp;IQ Score</nobr><br/></p>
									<c:if test="${reportInfo.checkIQScore}">
										<p class="arial-font-8">&nbsp;Full Score:<span  class="times-font-8"><![CDATA[${reportInfo.fullScore}]]><br/></span> </p>
										<p class="arial-font-8">&nbsp;Verbal:<span  class="times-font-8"><![CDATA[${reportInfo.verbalScore}]]><br/></span></p>
										<p class="arial-font-8">&nbsp;Performance:<span  class="times-font-8"><![CDATA[${reportInfo.performanceScore}]]><br/></span></p>
									</c:if>
							</td>
							<td class="border" colspan="1" height="30"> 
								<p class="arial-font-8"><nobr>&nbsp;Date of Most Recent IQ Test</nobr></p> <fmt:formatDate value="${reportInfo.testDate}" pattern="MMM dd, yyyy" var="formattedDate"/> <span class="times-font-8">&nbsp;<![CDATA[${formattedDate}]]><br/></span>
							</td>
							<td class="border" colspan="1" height="30">
								<p class="arial-font-8"><nobr>&nbsp;Name of Test</nobr></p>
								<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.testName}]]></span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!--Physical Health/Disabilities-->
	<div class="body" page-break-inside="auto">
		<table border-style="none" border-width="0" width="100%">
			<tr  padding-bottom="3"  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>10.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Physical Health/Disabilities</nobr></p></td>
			</tr>
			<tr>
				<td vertical-align="top" colspan="1"></td>
				<td  vertical-align="top" align="left" colspan="2">
					<p class="arial-font-10"><nobr>Does the child have a diagnosed or suspected health condition or disability?</nobr><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.hasMedicalIssue}]]></span></p>
				</td>
			</tr>
			<tr>
				<td vertical-align="top" colspan="1"></td>
				<td  vertical-align="top" align="left">
					<p class="arial-font-10">If yes, describe the condition and treatment required, if any:<span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer6}]]></span></p>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table border-style="none" border-width="0" width="100%">
						<tr  height="10">
							<td class="border"  colspan="1" width="50%">
								<p class="arial-font-8"><nobr><u><b>Issue</b></u></nobr></p>
							</td>
							<td class="border" colspan="1"  width="25%">
								<p class="arial-font-8"><nobr><u><b>Condition</b></u></nobr></p>
							</td>
							<td class="border"  colspan="1" width="25%">
								<p class="arial-font-8"><nobr><u><b>Severity</b></u></nobr></p>
							</td>
						</tr>
						<c:forEach var="healthIssue" items="${reportInfo.healthIssues}" varStatus="loop">
							<tr  height="10">
								<td class="border"  colspan="1" width="50%">
									<p  class="times-font-8"><![CDATA[${healthIssue.issueDesc}]]></p>
								</td>
								<td class="border"  colspan="1" width="25%">
									<p  class="times-font-8"><![CDATA[${healthIssue.conditionLevelDesc}]]></p>
								</td>
								<td class="border"  colspan="1"  width="25%">
									<p  class="times-font-8"><![CDATA[${healthIssue.conditionSeverityDesc}]]></p>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="border"  colspan="3"><p class="arial-font-8">Required Specialized Treatment?  <span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer7}]]></span></p></td>
						</tr>
						<tr>
							<td class="border"  colspan="3">
								<p class="arial-font-8">List Current  Medications<br/>
									<c:forEach var="medication" items="${reportInfo.medications}" varStatus="loop">
										<p  class="times-font-8">&nbsp;<![CDATA[${medication}]]></p>
									</c:forEach>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- Mental Health -->
	<div class="body">
		<table border-style="none" border-width="0" width="100%"  page-break-inside="auto">
			<tr  padding-bottom="3"  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>11.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Mental Health</nobr></p></td>
			</tr>
			<tr padding-bottom="3">
				<td vertical-align="top" width="2%"></td>
				<td  vertical-align="top" align="left" width="98%">
					<p class="arial-font-10 line-space-115"><nobr>Does the child have mental health needs requiring treatment?</nobr><span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer8}]]></span></p>
				</td>
			</tr>
			<tr padding-bottom="3">
				<td vertical-align="top" width="2%"></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10 line-space-115">Date of most recent psychologial/psychiatric evaluation:
					<fmt:formatDate value="${reportInfo.testingSessionDate}" pattern="MMM dd, yyyy" var="formattedDate"/> <span  class="times-font-8">&nbsp;<![CDATA[${formattedDate}]]></span></p>
				</td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10 line-space-115">DSM Diagnosis:</p></td>
			</tr>
			<tr>
				<td class="border" colspan="3">
					<table border-style="none" width="100%" cellmargin="0" cellpadding="0">
					 	<tr>
					 		<td colspan="3">
					 			<table border-style="none" width="100%" cellmargin="0" cellpadding="0">
						 			<tr height="20">
										<td  colspan="1" vertical-align="top" width="40%"><p class="arial-font-8"><nobr><u><b>DSM Diagnosis</b></u></nobr></p></td>
										<td  colspan="1" vertical-align="top" width="30%"><p class="arial-font-8"><nobr><u><b>Condition</b></u></nobr></p></td>
										<td  colspan="1" vertical-align="top" width="30%"><p class="arial-font-8"><nobr><u><b>Severity</b></u></nobr></p></td>
									</tr>
									<c:forEach var="dsmResult" items="${reportInfo.dsmResults}" varStatus="loop">
										<tr  height="10">
											<td  colspan="1" width="85%"><p  class="times-font-8"><![CDATA[${dsmResult.diagnosis}]]><br/></p></td>
											<td  colspan="1" width="7%"><p  class="times-font-8"><![CDATA[${dsmResult.condition}]]></p></td>
											<td  colspan="1" width="7%"><p  class="times-font-8"><![CDATA[${dsmResult.severity}]]></p></td>
										</tr>
									</c:forEach>
					 			</table>
					 		</td>
					 	</tr>
					</table>
				</td>
			</tr>
			<tr  align="left">
				 <td class="border"  colspan="3"  height="20"><p class="arial-font-8">Required Specialized Treatment? <span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer8}]]></span></p></td>
			</tr>
			<tr  align="left" padding-bottom="3">
				<td class="border"  colspan="3" height="20" width="98%">
					<p class="arial-font-8">Psychotropic Medications Prescribed?&nbsp;&nbsp;&nbsp;See list of Current Medications in 10. Physical Health/Disabilities.</p>
				</td>
			</tr>
		</table>
		<table  padding-bottom="5" width="100%">
			<tr align="left" valign="top">
				<td width="33%" colspan="1" class="border"><p class="arial-font-8">Referring Agency/Organization<br/></p><span  class="times-font-8">Harris County Juvenile Probation</span></td>
				<td width="33%" colspan="1" class="border"><p class="arial-font-8">Agency Contact Person<br/></p><span  class="times-font-8">Henry Gonzales</span></td>
				<!--Bug Fix : 31301 change of telephone no.-->
				<td width="34%" colspan="1" class="border"><p class="arial-font-8">Telephone No. (Inc. A/C)<br/></p><span  class="times-font-8">713-222-4100</span></td>
			</tr>
			<tr align="left" valign="top" >	
				<td colspan="3" class="border"><p class="arial-font-8">Agency Address: <span  class="times-font-8">1200 Congress Houston, Texas 77002</span></p></td>
			</tr>
			<tr align="left" valign="top" colspan="3">
				<td width="33%" colspan="1" class="border"><p class="arial-font-8">Name of Person Completing Form<br/></p><span  class="times-font-8"><![CDATA[${reportInfo.userCompletingCommonAppFormName}]]></span></td>
				<td width="33%" colspan="1" class="border"><p class="arial-font-8">Title<br/></p><span  class="times-font-8">Juvenile Probation Officer</span></td>
				<td width="34%" colspan="1" class="border"><p class="arial-font-8">Date Completed</p><span  class="times-font-8"><fmt:formatDate value="${reportInfo.currentDate}" pattern="MMM dd, yyyy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></td>
			</tr>
			<tr align="left" valign="top" padding-bottom="20" >	
				<td colspan="3" class="border"><p class="arial-font-8">Where Placed?Facility Name and Location:<span  class="times-font-8">Texas Juvenile Justice Department</span></p></td>
			</tr>
		</table>
	</div>
	
	<!-- Recommended Level of Care -->
	<div class="body" page-break-before="always" page-break-after="always">
		<table border-style="none" border-width="0" width="100%"  page-break-inside="auto" padding-left="5" padding-bottom="5">
			<tr  padding-bottom="3"  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>B.</nobr></p></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Recommended Level of Care</nobr></p></td>
			</tr>
			<tr padding-bottom="5">
				<td vertical-align="top" width="2%"></td>
				<td  vertical-align="top" align="left" width="98%">
					<p class="arial-font-10 line-space-115"><nobr>List the key elements, in order of importance, that led you to the recommended Level of Care:</nobr></p>
				</td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="arial-font-10-bold" width="98%">1.	Most important:</p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="times-font-8" width="98%">&nbsp;<![CDATA[${reportInfo.answer11}]]></p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="arial-font-10-bold" width="98%">2.	Next most important:</p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="times-font-8" width="98%">&nbsp;<![CDATA[${reportInfo.answer12}]]></p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="arial-font-10-bold" width="98%">3.	Third most important:</p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td><p class="times-font-8" width="98%">&nbsp;<![CDATA[${reportInfo.answer13}]]></p></td>
			</tr>
			<tr>
				<td vertical-align="top" width="2%"></td>
				<td vertical-align="top" align="left" width="98%"><p class="arial-font-10 line-space-115">Other considerations or comments, if any: </p><p  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer40}]]></p></td>
			</tr>
		</table>
	</div>
	
	<!-- Referral/Admissions Packet: -->
		<div class="body" page-break-before="always" page-break-after="always">
			<table border-style="none" border-width="0" width="100%"  page-break-inside="auto" padding-bottom="10">
				<tr  padding-bottom="3"  class="margin-top-6 margin-bottom-6">
					<td vertical-align="top" width="2%"><p class="arial-font-10-bold"><nobr>C.</nobr></p></td>
					<td vertical-align="top" align="left" width="98%"><p class="arial-font-10-bold"><nobr>Referral/Admissions Packet:</nobr></p></td>
				</tr>
			</table>
			<table border-style="none" border-width="0" width="100%"  page-break-inside="auto" padding="2">
						<tr class="margin-top-6 margin-bottom-6">
							<td vertical-align="top" align="left" class="border" width="40%"><p class="arial-font-8-bold"><nobr>SECTION 1-Social And Developmental Assessments</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 5-Substance Abuse History</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 9-Education</nobr></p></td>
						</tr>
						<tr   class="margin-top-6 margin-bottom-6">
							<td vertical-align="top" align="left" class="border" width="40%"><p class="arial-font-8-bold"><nobr>SECTION 2-Special Needs, Problems, and Behaviors</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 6-History of Abuse/Neglect</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 10-Physical Health/Disabilities</nobr></p></td>
						</tr>
						<tr  class="margin-top-6 margin-bottom-6">
							<td vertical-align="top" align="left" class="border" width="40%"><p class="arial-font-8-bold"><nobr>SECTION 3-Juvenile Justice History</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 7-Family History</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 11-Mental Health</nobr></p></td>
						</tr>
						<tr  class="margin-top-6 margin-bottom-6">
							<td vertical-align="top" align="left" class="border" width="40%"><p class="arial-font-8-bold"><nobr>SECTION 4-Placement History</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 8-Financial Information</nobr></p></td>
							<td vertical-align="top" align="left" class="border" width="30%"><p class="arial-font-8-bold"><nobr>SECTION 12-Other Attachments</nobr></p></td>
						</tr>
			</table>
		</div>
					
		<table border-style="none" border-width="0" width="100%"  page-break-inside="auto">
			<tr  padding-bottom="3"  class="margin-top-6 margin-bottom-6">
				<td vertical-align="top" align="left" width="100%" colspan="2" ><p class="arial-font-10-bold"><nobr>SECTION 1-Social and Developmental Assessment</nobr></p></td>
			</tr>
			<tr padding-bottom="3">	
				<td colspan="5"  class="margin-top-6 margin-bottom-6" padding-bottom="6">
					<p class="arial-font-10">Describe the child's general social and developmental history. Feel free to expand the description of your impressions of the child.<br/>
					Be certain that you include all of the following:</p>
				</td>
			</tr>
			<tr  class="margin-top-6 margin-bottom-6" padding-bottom="10">
				<td vertical-align="top" align="left" width="98%" colspan="2"><p class="arial-font-8-bold"><nobr>A.  A description of the circumstances that led to the child's referral:</nobr></p><p class="times-font-8"><![CDATA[${reportInfo.answer39}]]></p></td>
			</tr>
			<tr  class="margin-top-6 margin-bottom-6" padding-bottom="10">
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="arial-font-8-bold"><nobr>B. The immediate and long range goals of placement:</nobr></p><p class="times-font-8"><![CDATA[${reportInfo.answer14}]]></p></td>
			</tr>
			<tr  padding-bottom="1">
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="arial-font-8-bold"><nobr>C.  A description of the child's relationship with other significant adults and children:</nobr></p></td>
			</tr>
			<tr  padding-bottom="10">
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="times-font-8"><nobr><b>Delinquent Peer/Sibling/Associations:</b></nobr><![CDATA[${reportInfo.answer16}]]></p></td>
			</tr>
			<tr padding-bottom="10">
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="times-font-8"><nobr><b>Adults and children in the family:</b></nobr><![CDATA[${reportInfo.answer29}]]></p></td>
			</tr>
			<tr>
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="arial-font-8-bold"><nobr>D. A description of the child's behavior including both appropriate and inappropriate behavior</nobr></p><p  class="times-font-8"><![CDATA[${reportInfo.answer15}]]></p></td>
			</tr>
			<tr>
				<td vertical-align="top" align="left" width="98%" colspan="1"><p class="arial-font-8-bold"><nobr>E. The child's developmental history and current level of functioning</nobr></p><p  class="times-font-8"><![CDATA[${reportInfo.answer17}]]></p></td>
			</tr>
		</table>
			
	
	<!-- SECTION 2 - Special Need, Problems and Behaviors -->
	<div class="body" page-break-after="always" page-break-before="always">
		<p class="arial-font-10-bold" padding-bottom="5" >
			SECTION 2 - Special Need, Problems and Behaviors<br/>
		</p>
		<p class="arial-font-10" padding-bottom="10">
			Describe in detail the special needs, problems, or behaviors identified in Section 2 of the Screening Profile.<br/>
		</p>
		<p class="arial-font-8-bold" padding-bottom="10"><br/> A. Suicide History.<br/> Describe in detail suicide attempts and suicidal gestures. Include the number of suicide attempts, 
			and the date of the last known suicide attempt.</p><span  class="times-font-8"><![CDATA[${reportInfo.answer20}]]></span>
		
		<c:forEach var="suicideHistoryTraits" items="${reportInfo.suicideHistoryTraitsInfo}" varStatus="loop">
			<span  class="times-font-8"><![CDATA[${suicideHistoryTraits}]]></span><br/>
		</c:forEach>
		
		<p class="arial-font-8-bold" padding-bottom="10"><br/>B. History of Child's assaultive behavior.</p> <span  class="times-font-8"><![CDATA[${reportInfo.answer26}]]></span>
		<c:forEach var="eachAssaultiveBehaviorTraitsInfo" items="${reportInfo.assaultiveBehaviorTraitsInfo}" varStatus="loop">
			<span  class="times-font-8"><![CDATA[${eachAssaultiveBehaviorTraitsInfo}]]></span><br/>
		</c:forEach>
		
		<p class="arial-font-8-bold" padding-bottom="10"><br/>C. Runaway history.</p><p  class="times-font-8"><![CDATA[${reportInfo.answer38}]]></p>
		<c:forEach var="eachRunawayHistoryTraitsInfo" items="${reportInfo.runawayHistoryTraitsInfo}" varStatus="loop">
			<span  class="times-font-8"><![CDATA[${eachRunawayHistoryTraitsInfo}]]></span><br/>
		</c:forEach>
		
		<p class="arial-font-8-bold" padding-bottom="10"><br/>D. Other significant needs, problems and behaviors (including setting fires, maternity, etc.).</p><span  class="times-font-8"><![CDATA[${reportInfo.answer21}]]></span>
	</div>
	
	<!-- SECTION 3 - Juvenile Justice History -->
	<p class="arial-font-10-bold" align="center">
		SECTION 3 - Juvenile Justice History
	</p>
		
	<table border-style="none" border-width="0" width="100%"   page-break-inside="auto" padding-top="0">
		<c:forEach var="referral" items="${reportInfo.referrals}" varStatus="loop">
			<c:if test="${not empty referral.offenses}">
				<tr valign="top">
					<td></td>
					<td align="left" padding-bottom="2">
						<table border-style="none" border-width="0" width="100%">
							<tr valign="top">
								<td  padding-bottom="2" align="left" colspan="5">
									<p class="arial-font-8-bold"><nobr><u>Referral Date:</u>&nbsp;<fmt:formatDate value="${referral.referralDate}" pattern="MMM dd, yyyy" var="formattedDate"/><span  class="times-font-8"><![CDATA[${formattedDate}]]> &nbsp;&nbsp;&nbsp;&nbsp;<![CDATA[${referral.referralId}]]></span></nobr></p>
								</td>
							</tr>
							<tr>
								<td align="left" padding-bottom="2" colspan="5"><p class="arial-font-8-bold">List of Offenses:</p></td>
							</tr>
							<tr valign="middle">
								<td align="left" padding-bottom="2" width="50%" colspan="3"><p class="arial-font-8"><u>Offense</u></p></td>
								<td align="left" padding-bottom="2" width="25%" colspan="1"><p class="arial-font-8"><u>Level</u></p></td>
								<td align="left" padding-bottom="2" width="25%" colspan="1"><p class="arial-font-8"><u>Penal Code</u></p></td>
							</tr>
							<c:forEach var="offense" items="${referral.offenses}" varStatus="loop">
								<tr>
									<td align="left" padding-bottom="2"  width="50%" colspan="3">
										<span  class="times-font-8"><![CDATA[${offense.offenseDescription}]]></span>
									</td>
									<td align="left" padding-bottom="2" width="25%" colspan="1">
										<c:if test="${(offense.catagory == 'CF')|| (offense.catagory == 'JF')|| (offense.catagory == 'F1') || (offense.catagory == 'F2') || (offense.catagory == 'F3')}">
											<span  class="times-font-8">FL</span>
										</c:if>
										<c:if test="${(offense.catagory == 'MA')|| (offense.catagory == 'MB')}">
											<span  class="times-font-8">MI</span>
										</c:if>
										<c:if test="${(offense.catagory == 'SO') || (offense.catagory == 'MC')}">
											<span  class="times-font-8">FC</span>
										</c:if>	
										<c:if test="${offense.catagory == 'AC'}">
											<span  class="times-font-8">AC</span>
										</c:if>	
									</td>
									<td align="left" padding-bottom="2" width="25%" colspan="1">
										<span  class="times-font-8"><![CDATA[${offense.citationSource}]]> <![CDATA[${offense.citationCode}]]></span>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${not empty referral.dispositions}"> 
								<tr>
									<td  padding-bottom="2" align="left" colspan="5"><p class="arial-font-8-bold"><nobr>List of Dispositions:</nobr></p></td>
								</tr>
								<tr valign="middle">
									<td align="left" padding-bottom="2" width="5%"  colspan="1"><p class="arial-font-8"><u>Type</u></p></td>
									<td align="left" padding-bottom="2" width="10%" colspan="1"><p class="arial-font-8"><nobr><u>Disposition Date</u></nobr></p></td>
									<td align="left" padding-bottom="2" width="30%" colspan="1"><p class="arial-font-8"><u>Offense</u></p></td>
									<td align="left" padding-bottom="2" width="25%" colspan="1"><p class="arial-font-8"><u>Level</u></p></td>
									<td align="left" padding-bottom="2" width="25%" colspan="1"><p class="arial-font-8"><u>Penal Code</u></p></td>
								</tr>
								<c:forEach var="offense" items="${referral.dispositions}" varStatus="loop">
									<tr>
										<td align="left" padding-bottom="2" width="5%">
											<p  class="times-font-8"><![CDATA[${offense.dispositionCode}]]></p>
										</td>
										<td align="left" padding-bottom="2" width="10%">
											<p  class="times-font-8"><fmt:formatDate value="${offense.dispositionDate}" pattern="MMM dd, yyyy" var="formattedDate"/><span  class="times-font-8"><![CDATA[${formattedDate}]]></span></p>
										</td>
										<td align="left" padding-bottom="2"  width="30%">
											<span  class="times-font-8"><![CDATA[${offense.offenseDescription}]]></span>
										</td>
										<td align="left" padding-bottom="2" width="25%">
											<c:if test="${(offense.catagory == 'CF')|| (offense.catagory == 'JF')|| (offense.catagory == 'F1') || (offense.catagory == 'F2') || (offense.catagory == 'F3')}">
												<span  class="times-font-8">FL</span>
											</c:if>
											<c:if test="${(offense.catagory == 'MA')|| (offense.catagory == 'MB')}">
												<span  class="times-font-8">MI</span>
											</c:if>
											<c:if test="${(offense.catagory == 'SO') || (offense.catagory == 'MC')}">
												<span  class="times-font-8">FC</span>
											</c:if>	
											<c:if test="${offense.catagory == 'AC'}">
												<span  class="times-font-8">AC</span>
											</c:if>		
										</td>
										<td align="left" padding-bottom="2" width="25%">
											<span  class="times-font-8"><![CDATA[${offense.citationSource}]]> <![CDATA[${offense.citationCode}]]></span>
										</td>	
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</td>
				</tr>
				<tr><td  padding-bottom="10"></td></tr>
			</c:if>
		</c:forEach>
	</table>
	
	<div class="body">
	<p class="arial-font-8"  align="center">* LEVEL OF OFFENSE CODES: &nbsp;&nbsp;&nbsp; **TYPE OF DISPOSITION CODES:</p>
	<table  width="100%">
		<tr  padding-left="5" align="left" valign="top">
			<td colspan="1" class="border">
				<p class="arial-font-8">Total Number of Referrals<br/></p><span  class="times-font-8"><![CDATA[${reportInfo.countReferrals}]]></span>
			</td>
			<td colspan="1" class="border">
				<p class="arial-font-8">FL = Felony<br/></p>
				<p class="arial-font-8">MI = Misdemeanor<br/></p>
				<p class="arial-font-8">FC = Family Code<br/></p>
				<p class="arial-font-8"><nobr>AC = Administrative Code</nobr></p>
			</td>
			<td border-style="top|left|bottom" colspan="1" class="border">
				<p class="arial-font-8"><nobr>CR = Counseled and Released </nobr><br/></p>
				<p class="arial-font-8">IA = Informal Adjustment<br/></p>
				<p class="arial-font-8">AP = Adjudicated to Probation<br/></p>
				<p class="arial-font-8">PT = Proven by TYC Hearing</p>
			</td>
			<td border-style="top|right|bottom" colspan="1" class="border">
				<p class="arial-font-8">RD = Refused/Dismissed<br/></p>
				<p class="arial-font-8">AT = Adjudicated to TYC<br/></p>
				<p class="arial-font-8">CA = Certified as Adult<br/></p>
			</td>
		</tr>
		<tr padding-left="5" align="left" valign="top">
			<td colspan="1" class="border">
				<p class="arial-font-8">Total Number of Adjudications/Certifications (AP, AT, PT, or CA):<br/></p>
				<p  class="times-font-8"><![CDATA[${reportInfo.totalNumberOfAdjudications}]]></p>
			</td>
			<td border-style="left|top" colspan="4"></td>
		</tr>
	</table>
	</div>
	
	
	<div class="body" page-break-before="always">
		<p class="arial-font-10" padding-bottom="10">
			Briefly describe the child's history of delinquency. Include a description of contributing factors, and any delinquency patterns you detect. Indicate whether the child is a follower or a leader.<br/>
			<span  class="times-font-8"><![CDATA[${reportInfo.answer18}]]></span>
		</p>
		
		<p class="arial-font-10" padding-bottom="10">
			Describe the child's most recent criminal episode, contributing factors, the child's actions or role in the episode, and how this episode fits into the child's history of delinquency.<br/>
			<span  class="times-font-8"><![CDATA[${reportInfo.answer19}]]></span>
		</p>
		<p class="arial-font-10" padding-bottom="10">
			Does the child admit gang affiliation? <span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer43}]]></span><br/>
			<c:if test="${reportInfo.answer43 == 'Yes'}">
				If yes, gang name: <span  class="times-font-8"><![CDATA[${reportInfo.answer44}]]></span><br/>
			</c:if>
		</p>
		<p class="arial-font-10" padding-bottom="10">
			Do any family members or relatives have gang affiliation? <span  class="times-font-8">&nbsp;<![CDATA[${reportInfo.answer41}]]></span><br/>
			<c:if test="${reportInfo.answer41 == 'Yes'}">
				If yes, gang name: <span  class="times-font-8"><![CDATA[${reportInfo.answer42}]]></span><br/>
			</c:if>
		</p>
	</div>	
	<div class="body" page-break-before="always" page-break-after="always">
		<p class="arial-font-10-bold"><nobr>TYC COMMITMENT</nobr></p>
		<table  width="100%"  page-break-inside="auto" padding-bottom="8">
			<tr align="left" valign="top">
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">County<br/></p><p class="times-font-8">HARRIS</p></td>
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Commitment Date<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.commitmentDate}]]></span></td>
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Judge's Last Name<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.judgeName}]]></span></td>
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Court Name<br/></p><span class="times-font-8"><nobr><![CDATA[${reportInfo.courtOrder.courtName}]]></nobr></span></td>
			</tr>
			<tr align="left" valign="top">
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Cause No.<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.causeNumber}]]></span></td>
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Prosecuting Attorney's Name<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.prosecutingAttorneyName}]]></span></td>
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">Probation I.D. No.<br/></p><span class="times-font-8"><![CDATA[${reportInfo.contrlrefID}]]></span></td> <!-- // Controlling refernece Number * 35996* -->
				<td height="30" width="25%" colspan="1" class="border"><p class="arial-font-7">SID/TRN Number<br/></p><span class="times-font-8"><![CDATA[${reportInfo.sidNumber}]]></span></td>
			</tr>
		</table>
		
		<p class="arial-font-10-bold">TYPE OF COMMITMENT<span  class="times-font-8"><![CDATA[${reportInfo.courtOrder.typeOfCommitment}]]></span></p>
		<table width="100%"  page-break-inside="auto">
			<tr align="left" valign="top">
				<td height="30" class="border" colspan="1">
					<p class="arial-font-7">Probation Failure <br/>
						<c:choose>
							<c:when test="${reportInfo.courtOrder.probationFailure != 'false'}">
								<span  class="times-font-8">&nbsp;YES</span>
							</c:when>
							<c:otherwise>
								<span  class="times-font-8">&nbsp;NO</span>
							</c:otherwise>
						</c:choose>
					</p>
				</td>
				<td height="30" colspan="2" class="border"><p class="arial-font-7">If Yes, describe most serious offense for which on probation:<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.mostSeriousOffense}]]></span></td>
				<td height="30" class="border" colspan="1"><p class="arial-font-7">Offense Code<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.offenseCode}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<td  height="30" class="border" colspan="4"><p class="arial-font-7">Reason for Failure<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.failureReason}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<td height="30" class="border" colspan="3"><p class="arial-font-7">Description of Current Offense<br/></p><nobr><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.currentOffense}]]></span></nobr></td>
				<td height="30" class="border" colspan="1"><p class="arial-font-7">Offense Code<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.offenseCode}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<td height="30" class="border" colspan="3"><p class="arial-font-7">Weapon Used<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.weaponType}]]></span></td>
				<td height="30" class="border" colspan="1"><p class="arial-font-7">Gang Related<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.gangRelated}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<td  height="30" class="border" colspan="4"><p class="arial-font-10-bold">OFFENSE LEVEL<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.offenseLevel}]]></span></td>
			</tr>
			<tr align="left" valign="top" colspan="4">
				<td height="30" class="border" colspan="1"><p class="arial-font-7">Determinate Sentence<br/></p>
					<c:choose>
						<c:when test="${reportInfo.courtOrder.determinateSentence != 'false'}">
							<span  class="times-font-8">&nbsp;YES</span>
						</c:when>
						<c:otherwise>
							<span  class="times-font-8">&nbsp;NO</span>
						</c:otherwise>
					</c:choose>
				</td>
				<td height="30" class="border" colspan="1"><p class="arial-font-7">Time (yrs/mo)<br/></p><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.timeStr}]]></span></td>
				<td height="30" colspan="2" valign="top" class="border"><p class="arial-font-7">Time in Detention in Connection with this Offense:</p> <span class="times-font-8"><![CDATA[${reportInfo.courtOrder.detentionTime}]]></span></td>
			</tr>
			<tr align="left" valign="middle" colspan="4">
				<td class="border" colspan="4">
					<p class="arial-font-7">
						Date of Prior TYC Commitment:<span class="times-font-8"><![CDATA[${reportInfo.courtOrder.priorTJJDCommitmentDate}]]></span><br/>
						Offense Code:<span class="times-font-8"><![CDATA[${reportInfo.courtOrder.offenseCode}]]></span><br/>
						Description of Offense:<nobr><span class="times-font-8"><![CDATA[${reportInfo.courtOrder.currentOffense}]]></span></nobr><br/>
						<b>See Section 3: Juvenile Justice History</b>
					</p>
				</td>
			</tr>
		</table>
		<p class="arial-font-11-bold" align="center">ATTACH ALL COURT ORDERS INVOLVING THE JUVENILE JUSTICE SYSTEM</p>
	</div>
	
	<!-- SECTION 4-Placement History -->
		<p class="arial-font-12-bold"  padding-bottom="3">SECTION 4-Placement History</p>
		<p class="arial-font-10" padding-left="5">Start with the child's first out-of-home placement:</p>
		
		<c:choose>
			<c:when test="${empty reportInfo.placements}">
				<!-- Fixed height cell, since the data will come in as 1 string, not a collection of strings (so that the cell need to expand) -->
				<div>
					<table border-style="none" border-width="0" width="100%"  page-break-inside="auto">
						<tr align="left" valign="top">
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">Date Placed</p></td>
							<td height="25" width="60%" colspan="1" class="border-medium"><p class="arial-font-7">Name of Living Arrangement</p></td>
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">License Type</p></td>
						</tr>
						<tr align="left" valign="top" padding-left="5">
							<td height="25" width="50%" colspan="1" class="border-medium"><p class="arial-font-7">Address</p></td>
							<td height="25" width="30%" colspan="1" class="border-medium"><p class="arial-font-7">Contact Person</p></td>
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">Telephone No.</p></td>
						</tr>
						<tr align="left" valign="top" padding-left="5">
							<td height="25" width="25%" colspan="1" class="border-medium"><p class="arial-font-7">Date Placement Ended</p></td>
							<td height="25" width="75%" colspan="2" class="border-medium"><p class="arial-font-7">Reason Placement Ended</p></td>
						</tr>
						<tr align="left" valign="top" padding-left="5">
							<td height="25" width="50%" colspan="2" class="border-medium"><p class="arial-font-7">LOC and Dates Assigned</p></td>
							<td height="25" width="50%" colspan="1" class="border-medium"><p class="arial-font-7">Continued Contact of Child with Placement Recommended</p></td>
						</tr>
					</table>
				</div>
			</c:when>
			<c:otherwise>
			  <c:forEach var="eachPlacement" items="${reportInfo.placements}" varStatus="loop">
				  <div>
					<table  border-style="none" border-width="0" width="100%"  page-break-inside="auto">
						<tr align="left" valign="top"  >
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">Date Placed<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.admitDate}]]></span></td>
							<td height="25" width="60%" colspan="1" class="border-medium"><p class="arial-font-7">Name of Living Arrangement<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.facilityName}]]></span></td>
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">License Type<br/></p></td>
						</tr>
						<tr align="left" valign="top"  padding-left="5"  >
							<td height="25" width="50%" colspan="1" class="border-medium"><p class="arial-font-7">Address<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.facilityInfo.address}]]></span></td>
							<td height="25" width="30%" colspan="1" class="border-medium"><p class="arial-font-7">Contact Person<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.facilityInfo.contactPerson}]]></span></td>
							<td height="25" width="20%" colspan="1" class="border-medium"><p class="arial-font-7">Telephone No.<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.facilityInfo.facilityPhone}]]></span></td>
						</tr>
						<tr align="left" valign="top"  padding-left="5" >
							<td height="25" width="25%" colspan="1" class="border-medium"><p class="arial-font-7">Date Placement Ended<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.releaseDate}]]></span></td>
							<td height="25" width="75%" colspan="2" class="border-medium"><p class="arial-font-7">Reason Placement Ended<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.admitReason}]]></span></td>
						</tr>
						<tr align="left" valign="top"  padding-left="5" >
							<td height="25" width="50%" colspan="2" class="border-medium"><p class="arial-font-7">LOC and Dates Assigned<br/></p><span class="times-font-8"><![CDATA[${eachPlacement.facilityInfo.levelOfCare}]]></span></td>
							<td height="25"  width="50%" colspan="1" class="border-medium">
								<p class="arial-font-7"><nobr>Continued Contact of Child with Placement Recommended</nobr> <br/>
									<c:choose>
										<c:when test="${eachPlacement.continuedStay != 'false'}">
											<span  class="times-font-8">&nbsp;YES</span>
										</c:when>
										<c:otherwise>
											<span  class="times-font-8">&nbsp;NO</span>
										</c:otherwise>
									</c:choose> 
								</p>
							</td>
						</tr>
					</table>
				  </div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	
	<!-- SECTION 5-Substance Abuse History -->
	<div class="body" page-break-after="always" page-break-before="always">
		<p class="arial-font-12-bold"  padding-bottom="5">SECTION 5-Substance Abuse History</p>
		
		<p class="arial-font-10" padding-bottom="20">
			A.	Describe the child's history of substance use, abuse, manufacture, possession, and/or delivery.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer22}]]></span>
		</p>
		
		<p class="arial-font-10" padding-bottom="20">
			B.  Describe the child's family history of substance use, abuse, manufacture, possession, and/or delivery.  
			Include not only parents and siblings, but also extended family members, (such as grandparents, aunts, uncles) 
			, even if they do not live in the same household as the child.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer23}]]></span>
		</p>
	
		<p class="arial-font-10" padding-bottom="20">
			C.	Describe any treatment the child has received for substance abuse and the success or failure of this treatment.  
			Include the lengths and dates of treatment, whether the program was residential or outpatient, 
			whether the child completed the program, whether the family was included in the treatment and so on.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer24}]]></span>
		</p>
	</div>
	
	<!-- SECTION 6-History of Abuse/Neglect of Referred Child -->

		<p  class="arial-font-12-bold"  padding-bottom="5">SECTION 6-History of Abuse/Neglect of Referred Child</p>
			<c:choose>
				<c:when test="${empty reportInfo.juvenileAbuseResponseEvent}">
				<div>
					<table style="border:1.02px solid black" width="100%" padding-top="5">
						<tr>
							<td vertical-align="top" width="2%">A.</td>
							<td vertical-align="top" align="left" width="98%"><p class="arial-font-10 line-space-115"><nobr>Type of Abuse/Neglect</nobr></p></td>
						</tr>
						<tr>	
							<td colspan="2">
								<table width="100%">
									<tr><td><p class="arial-font-10" >Abuse Type: <br/></p></td></tr>
									<tr><td><p class="arial-font-10" >Entry Date: <br/></p></td></tr>
									<tr><td><p class="arial-font-10" >Information Basis: <br/></p></td></tr>
									<tr><td><p class="arial-font-10" >Perpetrator Name: <br/></p></td></tr>
									<tr><td><p class="arial-font-10" >Relationship to Juvenile:  <br/></p></td></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td vertical-align="top" width="2%">B.</td>
							<td><p class="arial-font-10">What did the parent/perpetrator do?&nbsp;&nbsp;&nbsp;Summarize the role of each parent/perpetrator.<br/></p></td>
						</tr>
						<tr height="5px"><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td vertical-align="top" width="2%">C.</td>
							<td>
								<p class="arial-font-10">
									What happened to the child?&nbsp;&nbsp;&nbsp;Summarize the extent of harm (or the substantial risk of harm) to the child.<br/>
								</p>
							</td>
						</tr>
					</table>
				</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="eachJuvenileAbuse" items="${reportInfo.juvenileAbuseResponseEvent}" varStatus="loop">
					<div>
						<table style="border:1.02px solid black " width="100%" padding-top="8">
							<tr>
								<td vertical-align="top" width="2%">A.</td>
								<td vertical-align="top" align="left" width="98%"><p class="arial-font-10 line-space-115"><nobr>Type of Abuse/Neglect</nobr></p></td>
							</tr>
							<tr>	
								<td colspan="2">
									<table width="100%">
										<tr><td><p class="arial-font-10">Abuse Type: <span class="times-font-8"><![CDATA[${eachJuvenileAbuse.traitTypeName}]]></span><br/></p></td></tr>
										<tr><td><p class="arial-font-10">Entry Date: <fmt:formatDate value="${eachJuvenileAbuse.entryDate}" pattern="MMM dd, yyyy" var="formattedDate"/><span class="times-font-8"><![CDATA[${formattedDate}]]></span><br/></p></td></tr>
										<tr><td><p class="arial-font-10">Information Basis: <span class="times-font-8"><![CDATA[${eachJuvenileAbuse.abuseInformationBasisDescription}]]></span><br/></p></td></tr>
										<tr><td><p class="arial-font-10">Perpetrator Name: <span class="times-font-8"><![CDATA[${eachJuvenileAbuse.lastName} ${eachJuvenileAbuse.firstName}]]></span><br/></p></td></tr>
										<tr><td><p class="arial-font-10">Relationship to Juvenile:  <span class="times-font-8"><![CDATA[${eachJuvenileAbuse.relationshipToJuvenileDescription}]]></span><br/></p></td></tr>
									</table>
								</td>
							</tr>
							<tr>
								<td vertical-align="top">B.</td>
								<td><p class="arial-font-10">What did the parent/perpetrator do?&nbsp;&nbsp;&nbsp;Summarize the role of each parent/perpetrator.<br/><span class="times-font-8"><![CDATA[${eachJuvenileAbuse.abuseEvent}]]></span></p></td>
							</tr>
							
							<tr>
								<td vertical-align="top">C.</td>
								<td>
									<p class="arial-font-10">
										What happened to the child?&nbsp;&nbsp;&nbsp;Summarize the extent of harm (or the substantial risk of harm) to the child.<br/><span class="times-font-8"><![CDATA[${eachJuvenileAbuse.abuseDetails}]]></span>
									</p>
								</td>
							</tr>
							</table>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	
	
	<!-- SECTION 7-Family History -->
	<div class="body" page-break-after="always" page-break-before="always">
		<p class="arial-font-12-bold" padding-left="5">SECTION 7-Family History</p>
	
		<!-- Fixed height cell, since the data will come in as 1 string, not a collection of strings (so that the cell need to expand) -->
		<table  width="100%">
			<tr valign="top" >
				<td height="25" class="border" width="75%" colspan="1"><p class="arial-font-7">Home Address (Street, City, State, County, ZIP)</p><br/>
					<c:if test="${reportInfo.familyAddress!=null}">
						<span class="times-font-8"><![CDATA[${reportInfo.familyAddress.streetAddress}]]></span>
						<c:if test="${not empty reportInfo.familyAddress.streetAddress}"> 
							<span class="times-font-8">, <![CDATA[${reportInfo.familyAddress.cityStateZip}]]></span>
						</c:if>
					</c:if>
				</td>
				<td height="25" width="75%" colspan="1" class="border"><p class="arial-font-7">Telephone No. (inc. Area Code)</p>
					<c:if test="${reportInfo.familyPhone!=null}">
						<span class="times-font-8"><![CDATA[${reportInfo.familyPhone.contactPhoneNumber.formattedPhoneNumber}]]></span>
					</c:if>
				</td>
			</tr>
			<tr align="left" valign="top" >
				<td  height="25" colspan="2" class="border"><p class="arial-font-7">Marital Status of Birth Parents<br/></p><span class="times-font-8"><![CDATA[${reportInfo.birthParentsMaritalStatus}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<td  height="25" colspan="2" class="border"><p class="arial-font-7">Deaths In immediate family (list names, relationships, and age of referred child at the time of death):<br/></p>
					<c:forEach var="deceasedFamilyMember" items="${reportInfo.deceasedFamilyMembers}" varStatus="loop">
						<span class="times-font-8"><![CDATA[${deceasedFamilyMember.deceasedMemberName.formattedName}]]></span>
						<c:if test="${not empty deceasedFamilyMember.relationshipToJuvenile}">
							<span class="times-font-8"><![CDATA[${deceasedFamilyMember.relationshipToJuvenile}]]></span>
						</c:if>
						<c:if test="${not empty deceasedFamilyMember.juvenileAgeAtDeath}">
							<span class="times-font-8"><![CDATA[${deceasedFamilyMember.juvenileAgeAtDeath}]]></span>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr align="left" valign="top">
				<td  height="25" colspan="2" class="border"><p class="arial-font-7">If adopted, what does the child know about his or her birth parents? <br/></p>
					<span class="times-font-8"><![CDATA[${reportInfo.adoptionComments}]]></span>
				</td>
			</tr>
			<tr align="left" valign="top" class="arial-font-7" >
				<td  height="25" colspan="2" class="border"><p class="arial-font-7">Marital Status of Adoptive Parents<br/></p><span class="times-font-8"><![CDATA[${reportInfo.adoptedParentsMaritalStatus}]]></span></td>
			</tr>
		</table>
		
		
		<c:if test="${not empty reportInfo.personsInHome}">
			<p class="arial-font-12-bold" padding-left="3">Persons in Home</p>
			<table  width="100%" page-break-inside="auto">
				<tr align="left" valign="top" colspan="4">
					<td colspan="1" width="40%" class="border"><p class="arial-font-7">Name</p></td>
					<td colspan="1" width="10%" class="border"><p class="arial-font-7">Date of Birth*</p></td>
					<td colspan="1" width="25%" class="border"><p class="arial-font-7">Relationship Type</p></td>
					<td colspan="1" width="25%" class="border"><p class="arial-font-7">Social Security No.</p></td>
				</tr>
				
				<c:forEach var="eachPersonsInHome" items="${reportInfo.personsInHome}" varStatus="loop">
					<tr align="left" valign="top">
						<td height="25" class="border">
							<c:if test="${eachPersonsInHome.nameOfPerson!=null}">
								<span class="times-font-8"><![CDATA[${eachPersonsInHome.nameOfPerson.formattedName}]]></span>
							</c:if>
						</td>
						<td height="25" class="border"><span class="times-font-8"><fmt:formatDate value="${eachPersonsInHome.dateofBirth}" pattern="MMM dd, yyyy" var="formattedDate"/><![CDATA[${formattedDate}]]></span></td>
						<td height="25" class="border"><span class="times-font-8"><![CDATA[${eachPersonsInHome.relationshipType}]]></span></td>
						<td height="25" class="border"><span class="times-font-8"><![CDATA[${eachPersonsInHome.SSN}]]></span></td>
					</tr>
				</c:forEach>
			</table>
		
			<p class="arial-font-8" padding-left="5">
				*&nbsp;&nbsp;Give approximate age if date of birth is not known <br/>
			</p>
		</c:if>
	</div>
	<c:if test="${not empty reportInfo.personsOutHome}">
			<p class="arial-font-12-bold" padding-left="1" padding="3">Significant Persons Out of Home</p>
			 <table width="100%" page-break-inside="never">
			 	<c:forEach var="eachPersonsOutHome" items="${reportInfo.personsOutHome}" varStatus="loop">
				 	<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">
				 		<tr align="left" valign="top" colspan="4">
				 			<td colspan="1" class="border">
				 				<p class="arial-font-8" width="30%">Father: 
					 				<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">
							 			<c:if test="${eachPersonsOutHome.nameOfPerson!=null}">
											<span  class="times-font-8"><nobr><![CDATA[${eachPersonsOutHome.nameOfPerson.formattedName}]]></nobr></span>
										</c:if>
									</c:if>
								</p>
				 			</td>
				 			<td colspan="1" class="border" width="10%">
				 				<p  class="arial-font-8">Date of Birth*:
					 				<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}"> 
							 			<c:if test="${eachPersonsOutHome.dateofBirth!=null}">
											<span class="times-font-8"><fmt:formatDate value="${eachPersonsOutHome.dateofBirth}" pattern="MMM dd, yyyy" var="formattedDate"/><![CDATA[${formattedDate}]]></span>
										</c:if>
									</c:if>
								</p>
				 			</td>
				 			<td colspan="1" class="border" width="40%">
				 				<p  class="arial-font-8">Relationship Type:
					 				<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">
							 			<c:if test="${eachPersonsOutHome.relationshipType!=null}">
											<span class="times-font-8"><nobr><![CDATA[${eachPersonsOutHome.relationshipType}]]></nobr></span>
										</c:if>
									</c:if>
								</p>
				 			</td>
				 			<td colspan="1" class="border" width="20%">
				 				<p  class="arial-font-8"><nobr>Social Security No.:</nobr>
					 				<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">
							 			<c:if test="${eachPersonsOutHome.SSN!=null}">
											<span class="times-font-8"><![CDATA[${eachPersonsOutHome.SSN}]]></span>
										</c:if>
									</c:if>
								</p>
				 			</td>
				 		</tr>
				 		<tr>
				 			<td colspan="1" class="border" width="50%">
				 				<p  class="arial-font-8">Address:
				 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}"> 
										<span class="times-font-8"><![CDATA[${eachPersonsOutHome.personAddress.formattedAddress}]]></span>
									</c:if>
								</p>	
				 			</td>
				 			<td colspan="1" class="border" width="30%">
				 				<p  class="arial-font-8"><nobr>Telephone No. (Inc. A/C):</nobr>
				 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">  
										<span class="times-font-8"><![CDATA[${eachPersonsOutHome.personPhone.formattedPhoneNumber}]]><br/></span>
									</c:if>
								</p>
				 			</td>
				 			<td colspan="2" class="border" width="20%">
				 				<p  class="arial-font-8">Currently Involved with Child:
				 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BF')|| (eachPersonsOutHome.relationshipTypeId=='AF')|| (eachPersonsOutHome.relationshipTypeId=='SF')}">
										<span class="times-font-8"><![CDATA[${eachPersonsOutHome.involvementLevel}]]></span>
									</c:if>
								</p>
				 			</td>
				 		</tr>
						</c:if>
						<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}">
					 		<tr>
					 			<td colspan="1" class="border" width="30%">
					 				<p  class="arial-font-8">Mother:
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}"> 
								 			<c:if test="${eachPersonsOutHome.nameOfPerson!=null}">
												<span class="times-font-8"><nobr><![CDATA[${eachPersonsOutHome.nameOfPerson.formattedName}]]></nobr></span>
											</c:if>
										</c:if>
									</p>
					 			</td>
					 			<td colspan="1" class="border" width="10%">
					 				<p  class="arial-font-8">Date of Birth*:
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}"> 
								 			<c:if test="${eachPersonsOutHome.dateofBirth!=null}">
												<span class="times-font-8"><fmt:formatDate value="${eachPersonsOutHome.dateofBirth}" pattern="MMM dd, yyyy" var="formattedDate"/><![CDATA[${formattedDate}]]></span>
											</c:if>
										</c:if>
									</p>
					 			</td>
					 			<td colspan="1" class="border" width="40%">
					 				<p  class="arial-font-8">Relationship Type:
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}">
								 			<c:if test="${eachPersonsOutHome.relationshipType!=null}">
												<span class="times-font-8"><nobr><![CDATA[${eachPersonsOutHome.relationshipType}]]></nobr></span>
											</c:if>
										</c:if>
									</p>
					 			</td>
					 			<td colspan="1" class="border" width="20%">
					 				<p  class="arial-font-8"><nobr>Social Security No.:</nobr>
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}">
							 				<c:if test="${eachPersonsOutHome.SSN!=null}">
												<span class="times-font-8"><![CDATA[${eachPersonsOutHome.SSN}]]></span>
											</c:if>
										</c:if>
									</p>
					 			</td>
					 		</tr>
					 		<tr>
					 			<td colspan="1" class="border" width="40%">
					 				<p  class="arial-font-8">Address:
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}"> 
											<span class="times-font-8"><![CDATA[${eachPersonsOutHome.personAddress.formattedAddress}]]></span>
										</c:if>
									</p>	
					 			</td>
					 			<td colspan="1" class="border" width="40%">
					 				<p  class="arial-font-8">Telephone No. (Inc. A/C):
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}">  
											<span class="times-font-8"><![CDATA[${eachPersonsOutHome.personPhone.formattedPhoneNumber}]]><br/></span>
										</c:if>
									</p>
					 			</td>
					 			<td colspan="2" class="border" width="20%">
					 				<p  class="arial-font-8">Currently Involved with Child:
					 					<c:if test="${(eachPersonsOutHome.relationshipTypeId == 'BM')|| (eachPersonsOutHome.relationshipTypeId=='AM')|| (eachPersonsOutHome.relationshipTypeId=='SM')}">
											<span class="times-font-8"><![CDATA[${eachPersonsOutHome.involvementLevel}]]></span>
										</c:if>
									</p>
					 			</td>
					 		</tr>
				 		</c:if>
			 		</c:forEach>
				 		<tr style="background-color:#dfdfdf">
				 			<td colspan="1" style="border-left:0.5px solid black">Others</td>
				 			<td colspan="1" >Date of Birth *</td>
				 			<td colspan="2" style="border-right:0.5px solid black">Relationship/Role</td>
				 		</tr>
				 		<logic:notEmpty name="reportInfo" property="personsOutHome"> 
					 		<c:forEach var="eachPersonsOutHome" items="${reportInfo.personsOutHome}" varStatus="loop">
					 		<c:if test="${(eachPersonsOutHome.relationshipTypeId != 'BF')&& (eachPersonsOutHome.relationshipTypeId!='AF')&& (eachPersonsOutHome.relationshipTypeId!='SF')&& (eachPersonsOutHome.relationshipTypeId != 'BM')&& (eachPersonsOutHome.relationshipTypeId!='AM')&& (eachPersonsOutHome.relationshipTypeId!='SM')}">
					 		<c:if test="${(eachPersonsOutHome.relationshipTypeId != 'BF')&& (eachPersonsOutHome.relationshipTypeId!='AF')&& (eachPersonsOutHome.relationshipTypeId!='SF')&& (eachPersonsOutHome.relationshipTypeId != 'BM')&& (eachPersonsOutHome.relationshipTypeId!='AM')&& (eachPersonsOutHome.relationshipTypeId!='SM')}">
					 		<c:if test="${(eachPersonsOutHome.relationshipTypeId != 'BF')&& (eachPersonsOutHome.relationshipTypeId!='AF')&& (eachPersonsOutHome.relationshipTypeId!='SF')&& (eachPersonsOutHome.relationshipTypeId != 'BM')&& (eachPersonsOutHome.relationshipTypeId!='AM')&& (eachPersonsOutHome.relationshipTypeId!='SM')}">
							 	<tr height="10">
							 		<td colspan="1" class="border">
										<c:if test="${eachPersonsOutHome.nameOfPerson!=null}">
											<span class="times-font-8"><nobr><![CDATA[${eachPersonsOutHome.nameOfPerson.formattedName}]]></nobr></span>
										</c:if>
									</td>
							 		<td colspan="1" class="border">
										<c:if test="${eachPersonsOutHome.dateofBirth!=null}">
											<span class="times-font-8"><fmt:formatDate value="${eachPersonsOutHome.dateofBirth}" pattern="MMM dd, yyyy" var="formattedDate"/><![CDATA[${formattedDate}]]></span>
										</c:if>
								 	</td>
							 		<td colspan="2" class="border">
										<c:if test="${eachPersonsOutHome.relationshipType!=null}">
											<span class="times-font-8"><![CDATA[${eachPersonsOutHome.relationshipType}]]></span>
										</c:if>
							 		</td>
							 	</tr>
							 </c:if>
							 </c:if>
							 </c:if>
							</c:forEach>
						</logic:notEmpty>
			 	</table>
			<p class="arial-font-7" padding-left="5">
				<br/>*&nbsp;&nbsp;Give approximate age if date of birth is not known <br/>
			</p>
	</c:if>
	
	<!-- Characteristics of Individual Family Member with Whom child has Lived: -->
	<c:if test="${not empty reportInfo.familyMembersTraits}">
	<div class="body" page-break-before="always" page-break-after="always">
		<table  padding-bottom="5" width="100%">
				<tr colspan="3">
					<td width="98%" colspan="3">
						<p class="arial-font-10">
							Characteristics of Individual Family Member with Whom child has Lived:
						</p>
					</td>
				</tr>
			
				<tr style="background-color:#dfdfdf" align="center" valign="top" colspan="4">
					<td width="20%" colspan="1" class="border"><p class="arial-font-10">Family Member Name</p></td>
					<td width="30%" colspan="1" class="border"><p class="arial-font-10">Characteristics</p></td>
					<td width="50%" colspan="1" class="border"><p class="arial-font-10">Relationship</p></td>
				</tr>
			
				<c:forEach var="eachFamilyMembersTrait" items="${reportInfo.familyMembersTraits}" varStatus="loop">
					<tr>
						<td class="border" width="20%" colspan="1"><span class="times-font-8"><![CDATA[${eachFamilyMembersTrait.familyMemberName.formattedName}]]></span></td>
						<td class="border" width="30%" colspan="1">
							<c:forEach var="trait" items="${eachFamilyMembersTrait.traits}" varStatus="loop">
								<span class="times-font-8"><![CDATA[${trait}]]><br/></span>
							</c:forEach>
						</td>
						<!-- ER changes 11119 -->
						<td class="border" width="50%" colspan="1"><span class="times-font-8"><![CDATA[${eachFamilyMembersTrait.relationship}]]></span></td>  
					</tr>
				</c:forEach>
		 </table>
	 </div>
	</c:if>
	<c:if test="${not empty reportInfo.familyDynamicTraits}">
		<div>
			<table  padding-bottom="5" width="100%">
				<tr colspan="2">
					<td width="98%" colspan="2">
						<p class="arial-font-10">
							Characteristics of the Family as a Whole with Whom the Child has Lived:
						</p>
					</td>
				</tr>
				<tr style="background-color:#dfdfdf" align="center" valign="top" colspan="2">
					<td width="75%" colspan="1" class="border"><p class="arial-font-10">Family Dynamic Trait</p></td>
					<td width="25%" colspan="1" class="border"><p class="arial-font-10">Family Dynamic Trait Level</p></td>	
				</tr>	
				
				 <c:forEach var="familyDynamicTrait" items="${reportInfo.familyDynamicTraits}" varStatus="loop">
					<tr colspan="2">
						<td width="75%" colspan="1" class="border"><span class="times-font-8"><![CDATA[${familyDynamicTrait.traitDescId}]]></span></td>
						<td width="25%" colspan="1" class="border">
							<span class="times-font-8"><![CDATA[${familyDynamicTrait.traitLevel}]]></span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	
 	<div class="body" page-break-before="always" page-break-after="always">
		<p class="arial-font-10" padding-bottom="40">
			Briefly describe the child's relationships with family members and significant others, 
			both in and out of the home.  Address both strengths and weaknesses.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer28}]]></span>
		</p>
	
		<p class="arial-font-10" padding-bottom="40">
			Briefly describe the overall family situation, highlighting the positive and negative 
			aspects of the child's family environment including all the 'Family Characteristics' 
			checked affirmatively on the previous page.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer27}]]></span>		
		</p>
	
		<p class="arial-font-10" padding-bottom="30">
			Other significant information:<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.familyMemberComments}]]></span>
		</p>
	</div>
	
	<!-- SECTION 8-Financial Information -->
	<div class="body" page-break-before="always" padding-bottom="5" page-break-inside="auto">
		<p class="arial-font-12-bold">SECTION 8-Financial Information</p>
		
		 <c:forEach var="guardian" items="${reportInfo.guardianFinancialInfo}" varStatus="loop">
			<!-- Fixed height cell, since the data will come in as 1 string, not a collection of strings (so that the cell need to expand) -->
			<table  padding-right="20" padding-top="0" width="100%">
				<tr align="left" valign="top">
					<td colspan="2"><p class="arial-font-12-bold">Attach:  A copy of client's Medicaid card, if any.</p></td>
				</tr>
				<tr align="left" valign="top">
					<td class="border" colspan="2">Name of Responsible Guardian<br/><span class="times-font-8"><![CDATA[${guardian.guardian}]]></span></td>
				</tr>
				<c:if test="${empty guardian.employmentHistory}">
					<tr align="left" valign="top">
						<td class="border">Occupation<br/><br/></td>
						<td class="border">Salary<br/><br/></td>
					</tr>
					<tr align="left" valign="top">
						<td class="border">Place Employed<br/><br/></td>
						<td class="border">Employment Status<br/><br/></td>
					</tr>
				</c:if>
				
				 <c:forEach var="eachEmploymentHistory" items="${guardian.employmentHistory}" varStatus="loop">
					<c:if test="${eachEmploymentHistory.included}">
						<tr align="left" valign="top">
							<td>Occupation<br/><span class="times-font-8"><![CDATA[${eachEmploymentHistory.jobDescription}]]></span></td>
							<td>Salary<br/><span class="times-font-8">$ <![CDATA[${eachEmploymentHistory.annualGrossIncome}]]></span></td>
						</tr>
						<tr align="left" valign="top">
							<td>Place Employed<br/><span class="times-font-8"><![CDATA[${eachEmploymentHistory.placeEmployed}]]></span></td>
							<td>Employment Status<br/><span class="times-font-8"><![CDATA[${eachEmploymentHistory.employmentStatus}]]></span></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:forEach>
		<p class="arial-font-10" padding-left="5">
			Is the family eligible for Medicaid? <span class="times-font-8"><![CDATA[${reportInfo.eligibleForMedicaid}]]></span>
		</p>
		
		<p class="arial-font-10" padding-left="5" padding-bottom="10">
			Is the family currently receiving Medicaid? <span class="times-font-8"><![CDATA[${reportInfo.receivingMedicaid}]]></span>
		</p>
	</div>
	<div>	
		<c:if test="${not empty reportInfo.guardianFinancialInfo}">
			<p class="arial-font-10" padding-left="5">Funds Applicable to Child</p>
			<table  width="100%" border-width="1"  padding-left="5">
				<tr>
					<td>
						 <c:forEach var="guardian" items="${reportInfo.guardianFinancialInfo}" varStatus="loop">
							<b>Guardian ${guardianIdx+1}</b><br/>
						    Foodstamps: <span class="times-font-8">$ <![CDATA[${guardian.foodStamps}]]></span><br/> 
							Intangible property: <span class="times-font-8">$ <![CDATA[${guardian.intangibleValue}]]></span><br/>
							Life Insurance: <span class="times-font-8">$ <![CDATA[${guardian.lifeInsurancePremium}]]></span><br/>
							Other Income: <span class="times-font-8">$ <![CDATA[${guardian.otherIncome}]]></span><br/>
							Property Value: <span class="times-font-8">$ <![CDATA[${guardian.propertyValue}]]></span><br/>
							TANF: <span class="times-font-8">$ <![CDATA[${guardian.TANFAssistance}]]></span><br/><br/>
						</c:forEach>
					</td>
				</tr>
			</table>
		</c:if>
		<p><br/></p>
		<c:if test="${not empty reportInfo.insuranceApplicableToChild}">
			<p class="arial-font-10">Insurance Applicable to Child:</p>
			<table width="100%" border-width="1">
				<tr padding-bottom="5">
					<td>
						 <c:forEach var="insurance" items="${reportInfo.insuranceApplicableToChild}" varStatus="loop">
							Insurance Type: <span class="times-font-8"><![CDATA[${insurance.insuranceType}]]></span><br/>
							Insurance Carrier:<span class="times-font-8"><![CDATA[${insurance.insuranceCarrier}]]></span><br/>
							Policy #: <span class="times-font-8"><![CDATA[${insurance.policyNumber}]]></span><br/><br/>
						</c:forEach>
					</td>
				</tr>
			</table>
		</c:if>
	</div>
	
	<!-- SECTION 9-Education -->
	<div  class="body" page-break-before="always">
		<p class="arial-font-12-bold">SECTION 9-Education</p>
		<table width="100%">
			<tr align="left" valign="top" >
				<td width="5%" colspan="1"><p class="arial-font-10">Attach:</p></td>
				<td width="95%" colspan="1"><p class="arial-font-10">A. Current IEP(Individualized Education Plan)</p></td>
			</tr>
			<tr align="left" valign="top" >
				<td></td>
				<td width="95%" colspan="2"><p class="arial-font-10">B. Most Recent ARD Committee Report (if any) </p></td>
			</tr>
			<tr align="left" valign="top" >
				<td></td>
				<td width="95%" colspan="2"><p class="arial-font-10">C. Transcript</p></td>
			</tr>
			<tr align="left" valign="top">
				<td></td>
				<td width="95%" colspan="2"><p class="arial-font-10">D.  Adaptive Behavior Level Information (if any)</p></td>
			</tr>
		</table>
	
		<table  width="100%">
			<tr align="left" valign="top">
				<td height="25" class="border" width="75%" colspan="1"><p class="arial-font-7">Name of Most Recent School Attended<br/></p><span class="times-font-8"><![CDATA[${reportInfo.schoolName}]]></span></td>
				<td height="25" class="border" width="25%" colspan="1"><p class="arial-font-7">School District<br/></p><span class="times-font-8"><![CDATA[${reportInfo.schoolDistrict}]]></span></td>
			</tr>
			<tr align="left" valign="top">
				<!--Bug Fix : 11239 added font data-->
				<td height="25" colspan="2" width="100%" class="border"><p class="arial-font-7">Address (fill in city and state at least, and street address if known)<br/></p><span class="times-font-8"><![CDATA[${reportInfo.schoolAddress}]]>,<![CDATA[${reportInfo.schoolCity}]]>,<![CDATA[${reportInfo.schoolState}]]>,<![CDATA[${reportInfo.schoolZip}]]></span></td>
			</tr>
		</table>
		<p><br/></p>
		
		<!--  changes for ER 11502 starts -->
		<p class="arial-font-10" padding-left="3">School behavior in the last 12 months:</p>
		<table width="100%" border="1" padding-left="10">
			<tr>
				<td align="left">
					<p class="arial-font-10">
						Number of documented school disruptive incidents: <span class="times-font-8"><![CDATA[${reportInfo.answer5}]]></span><br/>
						Number of school suspensions:<span class="times-font-8"><![CDATA[${reportInfo.answer37}]]></span><br/>
						Number of school expulsions: <span class="times-font-8"><![CDATA[${reportInfo.answer35}]]></span><br/>
					</p>
				</td>
			</tr>
		</table>
		
	<!--  changes for ER 11502 ends -->
		<p class="arial-font-10"><br/>
			Describe any educational problems, needs, or behaviors not otherwise documented.  
			Add any additional information you feel is important.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer34}]]></span>
		</p>
	</div>
	
	<!-- SECTION 10-Physical Health/Disabilities -->
	<div class="body" page-break-before="always">
		<p class="arial-font-12-bold">SECTION 10-Physical Health/Disabilities</p>
		
		<table width="100%">
			<tr>
				<td width="7%" colspan="1">Attach:</td>
				<td width="93%" colspan="2">
					<p class="arial-font-10">A. Medical Records<br/>
						(1) Physical Examination<br/>
						(2) Immunization Records
					</p>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><p class="arial-font-10">B. Dental Records </p></td>
			</tr>
		</table>
		
		<p class="arial-font-10">
			Describe any physical health problems or disability not otherwise documented.  
			Add any additional information you feel is important.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer6}]]></span>
		</p>
	</div>
	
	<!-- SECTION 11-Mental Health -->
	<div class="body" page-break-before="always">
		<p class="arial-font-12-bold">SECTION 11-Mental Health</p>
		
		<table width="100%">
			<tr align="left" valign="top">
				<td width="100%" colspan="2"><p class="arial-font-10">Attach (as appropriate):</p></td>
			</tr>
			<tr align="left" valign="top">
				<td width="5%" colspan="1"></td>
				<td width="95%" colspan="1"><p class="arial-font-10">A. Physchological Report(s)</p></td>
			</tr>
			<tr align="left" valign="top">
				<td width="5%" colspan="1"></td>
				<td width="95%" colspan="1"><p class="arial-font-10">B. Psychiatric Report(s)</p></td>
			</tr>
		</table>
		
		<p class="arial-font-10">
			Describe any mental health problems not other wise documented.  
			Add any additional information you feel is important.<br/>
			<span class="times-font-8"><![CDATA[${reportInfo.answer9}]]></span>
		</p>
	</div>
	
	<!-- SECTION 12-Other Attachments -->
	<div page-break-after="always" class="body">
		<p class="arial-font-12-bold">SECTION 12-Other Attachments</p>
		
		<table width="100%">
			<tr>
				<td width="7%"><p class="arial-font-10">Attach:</p></td>
				<td width="93%"><p class="arial-font-10">A. Birth Certificate or Other Birth Verification</p></td>
			</tr>
			<tr>
				<td width="7%"></td>
				<td width="93%"><p class="arial-font-10">B. Legal Records (if any)</p></td>
			</tr>
			<tr>
				<td width="7%"></td>
				<td width="93%"><p class="arial-font-10">C. Authorization Forms</p></td>
			</tr>
		</table>
		
		<p class="arial-font-12-bold">ATTACHMENT CHECKLIST</p>
		<table width="100%" padding-bottom="3">
			<tr>
				<td align="left" valign="top" height="30" padding-left="5"  width="80%" class="border"><p class="arial-font-10">Child's Name<br/></p><span class="times-font-8"><![CDATA[${reportInfo.juvName}]]></span></td>
				<td align="left" valign="top" height="30" padding-left="5"  width="20%" class="border"><p class="arial-font-10">Date Completed<br/></p><fmt:formatDate value="${reportInfo.currentDate}" pattern="MMM dd, yyyy" var="formattedDate"/><span class="times-font-8"><![CDATA[${formattedDate}]]></span></td>
			</tr>
		</table>
		
		<table width="100%">
			<tr align="center" valign="top" padding-top="1">
				<td width="30%" colspan="1" class="border"><p class="arial-font-10-bold">DOCUMENT</p></td>
				<td width="10%" colspan="1" class="border"><p class="arial-font-7">ATTACHED</p></td>
				<td width="10%" colspan="1" class="border"><p class="arial-font-7">FORTH- COMING</p></td>
				<td width="10%" colspan="1" class="border"><p class="arial-font-7"><nobr>NOT RELEVANT</nobr></p></td>
				<td width="40%" colspan="1" class="border"><p class="arial-font-10-bold">NOT AVAILABLE BECAUSE</p></td>
			</tr>
			<tr align="left" valign="top">
				<td  padding-left="5" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10-bold">Birth Verification</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr  align="left" valign="top"> 
				<td padding-left="30" style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Birth Certificate</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="5" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10-bold">Legal records</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30"  style="border-left:0.5px solid black"><p class="arial-font-10">Commitment Order</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10">Other Court Orders</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30" padding-bottom="3"   style="border-left:0.5px solid black"><p class="arial-font-10">Police Records</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10">Divorce Decree</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30"  style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Custody Order</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="5" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10-bold">Education</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10">Individual Education Plan (IEP)</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="30" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10">Admission, Review, Dismissal (ARD) Report</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10">Transcript</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30"  style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Adaptive Behavior Level</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="5" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10-bold">Physical Health/Disabilities</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10">Physical Examination</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td  padding-left="30" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10">Immunization Record</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Dental Record</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="5" padding-bottom="3"  style="border-left:0.5px solid black"><p class="arial-font-10-bold">Mental Health</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" padding-bottom="3" style="border-left:0.5px solid black"><p class="arial-font-10">Psychological Report(s)</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Psychiatric Report(s)</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr align="left" valign="top">
				<td padding-left="5" style="border-left:0.5px solid black"><p class="arial-font-10-bold">Other</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" style="border-left:0.5px solid black"><p class="arial-font-10">Medicaid Approval/Application</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" style="border-left:0.5px solid black"><p class="arial-font-10">Medicaid Card</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
			<tr>
				<td padding-left="30" style="border-left:0.5px solid black;border-bottom:0.5px solid black"><p class="arial-font-10">Social Security Card</p></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
				<td class="border"></td>
			</tr>
		</table>
	</div> 
</body>
</pdf>