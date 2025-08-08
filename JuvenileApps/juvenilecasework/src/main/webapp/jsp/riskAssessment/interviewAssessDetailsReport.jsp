<!DOCTYPE HTML>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>



<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
	
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewAssessDetailsReport.jsp</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<%-- Javascript for emulated navigation --%>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
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
</head>

<body footer="myfooter" footer-height="5mm" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="100%"><h2>
			 <logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="PREA FOLLOW-UP">PREA FOLLOW-UP </logic:equal> RISK ASSESSMENT REPORT</h2></td>
		</tr>
		<tr></tr>
</table>
</div>
<html:form action="/displayRiskAssessmentDetails?action=printReport" target="content">
<div class="spacer"></div> 			  

<%-- BEGIN Interview Information TABLE --%>
                      							<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1"  class="borderTableBlue">
                      								<tr>
                      									<td class="detailHead" colspan="2">
                      										
                          									 <logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="PREA FOLLOW-UP">PREA Follow-up </logic:equal>: <bean:write  name="riskAnalysisForm" property="assessmentId"/>
                      									</td>
                      								</tr>
                      								<tr>
                      									<td align="center">
                      										<table width='100%' cellpadding="4" cellspacing="1">				
                      											<tr>
                      												<td class="formDeLabel" width='70%'>Date Taken</td>
                      												<td class="formDe">
                      													<bean:write name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/>
                      												</td>
                      											</tr>
                      											<tr>
						                        					<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionNumber"/></td>
						                        					<td class='formDe' width="50%">
						                        						<bean:write  name="riskAnalysisForm" property="casefileID"/>
						                        					</td>
						                             			</tr>	
                      											<tr> 
                      												<td class="formDeLabel" width='70%'>Part</td>
                      												<td class="formDe">
                      													<bean:write name="riskAnalysisForm" property="riskAssessmentTypeDesc" />
                      												</td>
                      											</tr>

                      											<tiles:insert page="../caseworkCommon/riskAnalysisQuestionAnswersDetails.jsp" flush="false">
																	<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
																</tiles:insert>
																<logic:notEmpty name="riskAnalysisForm" property="modReason">
			                										<tr>
        																<td colspan='4'>
																			<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
																				<tr>
	                              													<td class='formDeLabel' colspan='4'>Reason for modification</td>
    	                          												</tr>
        	                      												<tr>
            	                  													<td class='formDe' colspan='4'>
            	                  														<bean:write name="riskAnalysisForm" property="modReason" />
            	                  													</td>
                	              												</tr>
                    	          											</table>
                        	      										</td>
        															</tr>
        														</logic:notEmpty>
                     										</table>
														</td>
													</tr>
                      							</table>
<%-- END Interview Information TABLE --%>
                      							
<%-- BEGIN RECOMMENDATION & OVERRIDE SECTION TABLE --%>
											  	<tiles:insert page="recommendationAndOverrideTile.jsp" flush="true">
											  		<tiles:put name="formName" type="String" value="riskAnalysisForm" />
											  	</tiles:insert>
<%-- END RECOMMENDATION & OVERRIDE SECTION TABLE --%>
											<div class='spacer'></div>                     
<%-- BEGIN BUTTON TABLE --%>
	<table border="0" width="100%">
	     <tr>
			<td align="center">
					<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
							<html:submit property="submitAction" onclick="displayRiskAssessmentDetails.do?action=printReport"><bean:message key="button.print"/></html:submit>
					</logic:equal>
				<html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>
		    </td>
	     </tr>                   							
	</table>           									
<%-- BEGIN DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
