<!DOCTYPE HTML>

<%-- Used for searching of juvenile JIMS reports in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 10/28/2013	CShimek  	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - facilityPopulationTotalsReportSearch.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">

$(document).ready(function(){	
	$("#printBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/displayFacilityPopulationReportTotals.do?submitAction=Print',
	        type: 'post',
	        data: $('form#facilityPopulationReport').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
})
function findInfo()
{
	var forwardURL = "/<msp:webapp/>displayFacilityPopulationReportTotals.do?submitAction=Submit";	
	document.forms[0].action = forwardURL;
	document.forms[0].submit();	
}
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form styleId="facilityPopulationReport" action="/displayFacilityPopulationReportTotals" target="content" focus="facilityId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="prompt.facility"/> Population Totals Report</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Facility from Facility drop down to view report for a different facility.</li>
				<li>Select Print button to print the generated report.</li>
				<li>Select Cancel button to return to search page.</li>
					    					   
			</td>			
			</ul>	
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- BEGIN SEARCH FOR TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td align="center"> <b>
			<bean:message key="prompt.current" /> <bean:message key="prompt.facility" /> <bean:message key="prompt.population" />:
			<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></b>
		</td>
		
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>	
	<tr >
		<td align="center">
			<b><bean:message key="prompt.facility" /></b>
			<html:select name="juvenilePopulationForm" property="facilityId" onchange="findInfo()">
				<html:optionsCollection name="juvenilePopulationForm" property="facilities" value="code" label="descriptionWithCode" />
			</html:select>
		</td>
	</tr>
</table>
<%-- END SEARCH FOR TABLE  --%>
<%-- BEGIN INFORMATION TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
<tr>
<td>
	<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
		<tr>
			<td style='background-color: #B3C9F5; font-weight: bold' width="25%" align="center"><bean:message key="prompt.secure" /></td>
			<td style='background-color: #B3C9F5; font-weight: bold' width="25%" align="center"><bean:message key="prompt.nonSecure" /></td>
			<td style='background-color: #B3C9F5; font-weight: bold' width="25%" align="center"><bean:message key="prompt.diversion" /></td>
			<td style='background-color: #B3C9F5; font-weight: bold' width="25%" align="center">Temporary Release</td>
		</tr>
	</table>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
		<tr>
		
			<td></td>
			<td ><u><bean:message key="prompt.male" /></u></td>
			<td ><u><bean:message key="prompt.female" /></u></td>
			<td ></td>
			
			<td><u><bean:message key="prompt.male" /></u></td>
			<td ><u><bean:message key="prompt.female" /></u></td>
			<td ></td>
		
			<td ><u><bean:message key="prompt.male" /></u></td>
			<td ><u><bean:message key="prompt.female" /></u></td>
			<td ></td>
			
			<td ><u><bean:message key="prompt.male" /></u></td>
			<td ><u><bean:message key="prompt.female" /></u></td>
		</tr>

		<tr>	
			
			<td ><bean:message key="prompt.race" /></td>
			<td></td>
			<td></td>					
			<td  ><bean:message key="prompt.race" /></td>
			<td ></td>
			<td></td>
			<td ><bean:message key="prompt.race" /></td>
			<td></td>
			<td></td>
			<td ><bean:message key="prompt.race" /></td>
			<td></td>
			<td></td>						
		</tr>
		<logic:iterate id="results" name="juvenilePopulationForm" property="facilityPopTots" indexId='indexer'> 	
			<tr>
				<td >&nbsp;&nbsp;(<bean:write name="results" property="race"/>)</td>   
				<td><bean:write name="results" property="secureMaleCount"/></td>  
				<td><bean:write name="results" property="secureFemaleCount"/></td>  
				<td  align="center">(<bean:write name="results" property="race"/>)</td>  
				<td><bean:write name="results" property="nonSecureMaleCount"/></td> 
				<td><bean:write name="results" property="nonSecureFemaleCount"/></td> 
				<td  align="center">(<bean:write name="results" property="race"/>)</td>
				<td ><bean:write name="results" property="diversionMaleCount"/></td>   
				<td><bean:write name="results" property="diversionFemaleCount"/></td>
				<td  align="center">(<bean:write name="results" property="race"/>)</td>
				<td ><bean:write name="results" property="tempReleaseMaleCount"/></td>       	
				<td ><bean:write name="results" property="tempReleaseFemaleCount"/></td> 
			<tr>			
		</logic:iterate>
		<tr>
			<td ></td>
			<td>------- </td>
			<td>-------</td>
			<td ></td>
			<td>-------</td>
			<td>-------</td>
			<td></td>
			<td>-------</td>
			<td>-------</td>
			<td></td>
			<td>-------</td>
			<td>-------</td>
		</tr>
		<tr>
					<td width="1%"><bean:message key="prompt.total" /></td>
					<td><bean:write name="juvenilePopulationForm" property="totalSecureMalecount"/></td>
					<td><bean:write name="juvenilePopulationForm" property="totalFemaleSecureCount"/> </td>
					<td></td>
					<td><bean:write name="juvenilePopulationForm" property="totalNonSecMaleCount"/> </td>
					<td><bean:write name="juvenilePopulationForm" property="totalNonSecFemaleCount"/> </td>
					<td></td>					
					<td><bean:write name="juvenilePopulationForm" property="totalDivMaleCount"/> </td>
					<td><bean:write name="juvenilePopulationForm" property="totalDivFemaleCount"/> </td>
					<td></td>					
					<td><bean:write name="juvenilePopulationForm" property="totalTempReleaseMaleCount"/> </td>
					<td><bean:write name="juvenilePopulationForm" property="totalTempReleaseFemaleCount"/> </td>
					
					
				</tr>
	
	</table>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
					<tr>
									
					<td align=center>-----------------------------------------------------------</td>
					<td></td>
					<td></td>
					<td width="10%"></td>
					<td align="center">-----------------------------------------------------------</td>
					<td></td>
					<td></td>
					<td width="10%"></td>
					<td align="center">-----------------------------------------------------------</td>
					<td></td>
					<td></td>
					<td width="10%"></td>
					<td align="center">-----------------------------------------------------------</td>
				</tr>
				
				<tr>
			
					
						<td align="center"><bean:write name="juvenilePopulationForm" property="totalSecureInmates"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center"><bean:write name="juvenilePopulationForm" property="totalNonSecInmates"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center"><bean:write name="juvenilePopulationForm" property="totalDivInmates"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center"><bean:write name="juvenilePopulationForm" property="totalTempReleaseInmates"/></td>
						<td></td>
					</tr>
					<tr>	
						<td></td>
						<td colspan="2">
							<table width="100%">
								<tr>
									<td width="30%"></td>
									<td><%--  <bean:write name="juvenilePopulationForm" property="populationEvent.getSecureTotal()/> --%></td>
								</tr>	
							</table>
						</td>
					</tr>
	</table>
</td>
</tr>
</table>
<br><br><br>
<table align="center" width="10%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" style='background-color: #B3C9F5; font-weight: bold' align="left">Risk/Need Summary </td>
	</tr>
	<tr>
		<td align="center" style='background-color:  #D3D3D3;' >Criminal/Social History Score</td>
		<td align="center" style='background-color:  #D3D3D3;'>Percentage</td>
	</tr>
	<tr>
		<td align="left">High/High</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="highHighPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">High/Moderate</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="highModeratePercent"/> %</td>
	</tr>
	<tr>
		<td align="left">High/Low</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="highLowPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/High</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="moderateHighPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/Moderate</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="moderateModeratePercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Moderate/Low</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="moderateLowPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Low/High</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="lowHighPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Low/Moderate</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="lowModeratePercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Low/Low</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="lowLowPercent"/> %</td>
	</tr>
	<tr>
		<td align="left">Missing Score</td>
		<td align="center"><bean:write name="juvenilePopulationForm" property="missingScorePercent"/> %</td>
	</tr>
	
</table>
<div class="spacer4px" />
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>
			<html:submit styleId="printBtn" property="submitAction" ><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>