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

<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.GANG_RISK_ASSESSMENT_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.GANG_RISK_ASSESSMENT_REPORT.getReportName()%>"/>
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
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="100%"><b>
			 <logic:notEqual name="riskAnalysisForm" property="riskAssessmentType" value="MH SCREEN">GANG </logic:notEqual>
			  <logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="MH SCREEN">MH Screen </logic:equal> RISK ASSESSMENT REFERRAL REPORT</b></td>
		</tr>
		<tr></tr>
</table>
</div>
<div class="spacer"></div>
<br></br>
<div style="border-top: 1; border-bottom: 1;border-left: 1; border-right:1;">
<table width="98%" border-style="none">
 <tr>
           <td class="detailHead" colspan="2">
           <b>
          	  <logic:notEqual name="riskAnalysisForm" property="riskAssessmentType" value="MH SCREEN"><bean:message key="prompt.gang"/>&nbsp;</logic:notEqual>
          	 <logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="MH SCREEN">MH Screen </logic:equal> <bean:message key="prompt.info"/>: <bean:write  name="riskAnalysisForm" property="assessmentId"/>
          	 </b>
		   </td>
 </tr>
 <tr><td align="left" colspan="4" border-top = "1" padding-bottom="10"></td></tr>
 <tr>
	<td align="center">
		<table width="100%" border-style="none">
		   <tr>
				<td class="formDeLabel" width="70%" >Date Taken</td>
				<td class="formDe"><bean:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionNumber"/></td>
				<td class='formDe' width="50%"><bean:write  name="riskAnalysisForm" property="casefileID"/></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="70%" >Part</td>
				<td class="formDe"><bean:write name="riskAnalysisForm" property="riskAssessmentTypeDesc" /></td>
			</tr>
			<logic:iterate id="questions" name="riskAnalysisForm" property="assessmentDetailsResponseList">
      			<tr>
               		<logic:notEqual name="questions" property="uiControlType" value="QUESTIONHEADER">
	     				<logic:notEqual name="questions" property="uiControlType" value="TEXTAREA">
			            	<td class="formDeLabel" width="70%">
		    	            	<bean:write name="questions" property="riskQuestionText" />
							</td>
		            	    <td class="formDe"> 
		                		<bean:write name="questions" property="answerText" />
		                  	</td>     
	                  </logic:notEqual>
					  <logic:equal name="questions" property="uiControlType" value="TEXTAREA">
		                  <td class="formDeLabel" width="70%">
			    	      	<bean:write name="questions" property="riskQuestionText" />
			        	  </td>
						  <td class='formDe'>
							<bean:write name="questions" property="answerText" />
						 </td>
					</logic:equal>               								
				</logic:notEqual>
				<logic:equal name="questions" property="uiControlType" value="QUESTIONHEADER">
	            	<td class='detailHead' colspan="2"> 
	                	<bean:write name="questions" property="riskQuestionText"/>
	                </td>
	            </logic:equal>
            </tr>
		</logic:iterate>
		
		<logic:notEmpty name="riskAnalysisForm" property="modReason">
		<tr><td align="left" colspan="4" border-top = ".7" padding-bottom="10"></td></tr>
			     <tr>
	                  <td class='formDeLabel' width="70%" ><b>Reason for modification</b></td>
	                  <td class='formDe'>
            	      	<bean:write name="riskAnalysisForm" property="modReason" />
            	      </td>
    	        </tr>
        </logic:notEmpty>
        <tr><td align="left" colspan="4" border-top = ".7" padding-bottom="10"></td></tr>
        <tr>
			<td class='detailHead'><b>Referral Recommendation</b></td>
		</tr>
		
        <logic:iterate id="recommendationsIndex" name="riskAnalysisForm" property="recommendations" indexId="index">
			<tr>
				<td class='formDeLabel' width='1%'>Recommendation</td>
				<td class='formDe'><bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/></td>
			</tr>
			<logic:notEmpty name="recommendationsIndex" property="riskAnalysisScore">
				<logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="NEWREFERRAL">
           			<tr>
						<td class="formDeLabel">Aggregate Score</td>
						<td class="formDe" ><bean:write name="recommendationsIndex" property="riskAnalysisScore"/></td>
					</tr>
				</logic:equal>
			</logic:notEmpty>
		</logic:iterate>
		<tr>
			<td class='formDeLabel'>Override Recommendation?</td>
			<td class='formDe'>
				<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">YES</logic:equal>
				<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">NO</logic:equal>
			</td>
		</tr>
      </table>
     </td>
 </tr> 
</table>
</div>
</body>
</pdf>
