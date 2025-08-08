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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.PreCourtStaffingPrintBean" scope="session"/>
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.PRE_COURT_STAFFING_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.PRE_COURT_STAFFING_REPORT.getReportName()%>"/>
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
		margin-bottom: .1in;}
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
<body footer="myfooter" footer-height="0mm" >

<div class="body" page-break-before="avoid">

<table width="100%" border-style="none">
		<tr align="center">
			<td width="80%" align="center"> <p class="arial-font-10"><b>
			Pre-Court Staffing</b></p></td>
		</tr>
		<tr></tr>
</table>
</div>
<div class="body">	
<table width="100%" border-style="none" align="center" class="margin-top-10px"> 
 <tr>
           <td class="detailHead" colspan="2">
           <b>
          	   <p><b><bean:message key="prompt.name"/>:</b> &nbsp;&nbsp;${reportInfo.juvenileName} </p>
          	 </b>
		   </td>
		   
 </tr>
 
 <tr>
	<td align="center">
		<table width="100%" border-style="none">
		   <tr>
		   		<td>
					 <p><b><bean:message key="prompt.juvenile"/>#:</b> &nbsp;&nbsp;${reportInfo.juvenileNumber} </p>
				</td>
			 	<td>
				 	<p><b><bean:message key="prompt.sex"/>:</b> &nbsp;&nbsp;${reportInfo.sex} </p>
				</td>
			 	<td>
				 	<p><b><bean:message key="prompt.age"/>:</b> &nbsp;&nbsp;${reportInfo.age} </p>
				</td>				
			 	<td>
				 	<p><b><bean:message key="prompt.dob"/>:</b> &nbsp;&nbsp;${reportInfo.dob} </p>
				</td>
			</tr>	
      </table>
     </td>
 </tr> 
 </table>
 </div>
<div class="body">	
		
	<table width="100%" border-style="none" page-break-inside="auto">
		   <tr>
		   		<td>
					 <p><b>JPO:</b> &nbsp;&nbsp;${reportInfo.jpoOfRec} </p>
				</td>
			</tr>
	</table>
</div>

			
			
				
					<logic:iterate id="questions" name="riskAnalysisForm" property="processedViewQuestionAnswers">
						<logic:equal  name="questions" property="isAllowPrint" value="true">
							<div class="body">	
								<table width="100%" border-style="none">
									<tr>
										
										<logic:notEqual name="questions" property="uiControlType" value="QUESTIONHEADER">
												
											<logic:notEqual name="questions" property="uiControlType" value="TEXTAREA">
												<logic:notEqual name="questions" property="uiControlType" value="CHECKBOX">
													<logic:notEqual name="questions" property="questionName" value="JPO PRIMARY">
														<logic:notEqual name="questions" property="questionName" value="JPO SECONDARY">
															<td class="formDeLabel" >
																<b><bean:write name="questions" property="questionText" />:</b>
																	<bean:write name="questions" property="filteredAnswerText" />	
															 </td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
												<logic:equal name="questions" property="uiControlType" value="CHECKBOX">
													<logic:notEqual name="questions" property="questionName" value="JPO PRIMARY">
														<logic:notEqual name="questions" property="questionName" value="JPO SECONDARY">
															<td class="formDeLabel" >
																<b><bean:write name="questions" property="questionText" />:</b>
																	<bean:write name="questions" property="answerText" />	
															 </td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:equal>
											</logic:notEqual>
													
											<logic:equal name="questions" property="uiControlType" value="TEXTAREA">
												<td class="formDeLabel" colspan='2'>
													<b><bean:write name="questions" property="questionText" />:</b>								
													<logic:equal name="questions" property="answerText" value="">
														NONE IDENTIFIED
													</logic:equal>
													<logic:notEqual name="questions" property="answerText" value="">
														<bean:write name="questions" property="filteredAnswerText" />
													</logic:notEqual>
											
												</td>
											</logic:equal>
														
										</logic:notEqual>
													
										<logic:equal name="questions" property="uiControlType" value="QUESTIONHEADER">
											<td class='detailHead' colspan="2"> 
												<b><bean:write name="questions" property="questionText"/>:</b>
											</td>
										</logic:equal>
								</tr>
									<logic:equal name="questions" property="questionText" value="Special Notes">
														<tr><td></td></tr>
														<tr><td></td></tr>
													</logic:equal>
								</table>	
							</div>
						</logic:equal>			  		
					  </logic:iterate>
					
			

<div class="body">	
		
	<table width="100%" border-style="none" page-break-inside="auto">
		<c:if test="${reportInfo.action == 'updatePCSRisk'}">
		<tr>
		   		<td>
					 <p><b>Reason for modification:</b> &nbsp;&nbsp;${reportInfo.filteredModReason} </p>
				</td>
			</tr>
	 </c:if>
			<tr>
		   		<td>
					 <p><b>Entered By:</b> &nbsp;&nbsp;${reportInfo.enteredBy} </p>
				</td>
			</tr>
				<tr>
		   		<td>
					 <p><b>Completion Date:</b> &nbsp;&nbsp;${reportInfo.completionDate} </p>
				</td>
			</tr>
		</table>	
</div>
</body>
</pdf>
