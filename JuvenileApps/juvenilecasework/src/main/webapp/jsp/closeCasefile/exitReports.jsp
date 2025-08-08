<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 11/19/2007		AWidjaja JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - exitReports.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body  topmargin='0' leftmargin="0">
<html:form action="displayCasefileClosingActivities.do" target="content">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
 <tr>
   <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Common App Report List</td>	     
 </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on any of the report date to view report details.</li>							
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN MAIN BODY TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign='top'>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				

      <%-- BEGIN CASEFILE TABLE --%>
			<table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
		    <tr>
					<td valign='top' align='center'>

            <div class='spacer'></div>						
						<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class='detailHead'>Common App Report List</td>
							</tr>
							<tr>
								<td valign='top' align='center'>
									<table width='100%' cellpadding='4' cellspacing="1">
										
										<logic:empty name="commonAppForm" property="exitPlanList">
											<tr class='alternateRow'>
												<td width="1%" nowrap='nowrap' colspan='2'>No Reports</td>
											</tr>
										</logic:empty>

										<logic:notEmpty name="commonAppForm" property="exitPlanList">
											<tr>
												<td class="formDeLabel" width="1%" nowrap='nowrap'>Date Generated</td>
												<td class="formDeLabel">Report Type</td>
											</tr>

											<logic:iterate id="exitPlanIter" name="commonAppForm" property="exitPlanList">
												<tr>
													<td class="formDe" width="1%" nowrap='nowrap'>
														<a href="/<msp:webapp/>displayExitReports.do?submitAction=View&selectedExitPlanId=<bean:write name='exitPlanIter' property='commonAppDocId'/>">
														<bean:write name="exitPlanIter" property="createDate" formatKey="date.format.mmddyyyy"/></a>
														<bean:write name="exitPlanIter" property="createDate" formatKey="time.format.hhmma"/>
													</td>
													<td class="formDe" colspan='3'><bean:write name="exitPlanIter" property="docTypeDescription"/></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>	
									</table>
								</td>
							</tr>
						</table>		
					</td>
				</tr>
				
				<tr>
					<td align='center'>
						<div class='spacer'></div>
						<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
						<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
					</td>
				</tr>
			</table>
      <%-- END CASEFILE TABLE --%>
		</td>
	</tr>
</table>
<%-- END MAIN BODY TABLE --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>

</html:html>
