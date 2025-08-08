<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>

<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
 <%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />


<title><bean:message key="title.heading"/> - activitiesForYouthCreateSummary.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/activityForYouth.js"></script>
<script>
	$(document).ready(function(){
		
	})
</script>
</head>
<body>
<html:form action="/handleActivitiesForYouthCreate" target="content">
	<table width='100%'>
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - 
	     	Activity for Youth Summary
	  	</td>
	  </tr>
	</table>
	<table width='100%'>
		  <td class="confirm">
			<logic:notEqual name="processActivitiesForMultipleYouthForm" property="message" value="">
				<bean:write name="processActivitiesForMultipleYouthForm" property="message" />
			</logic:notEqual> 
				
		</td>
	</table>
	<br>
	<table align="center" width='99%' cellpadding='4' cellspacing='2' bgcolor='#999999'>
		<tr bgcolor='#cccccc'>
			<td class="subhead" valign='top' nowrap='nowrap'>Activity Date</td>
			<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.category"/></td>
			<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.type"/></td>
			<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
		</tr>
		<tr class="normalRow">
			<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="activityDate" /></td>
			<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedCategoryIdDesc" /></td>
			<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedTypeIdDesc" /></td>
			<td valign='top'><bean:write name="processActivitiesForMultipleYouthForm" property="selectedActivityIdDesc" /></td>
		</tr>
	</table>
	 <br>
	<table align="center" width='99%' cellpadding='4' cellspacing='2' bgcolor='#999999'>
		<tr bgcolor='#cccccc'>
			<td class="subhead" valign='top' nowrap='nowrap'>Juvenile Number</td>
			<td class="subhead" valign='top' nowrap='nowrap'>Activity ID</td>
			<td class="subhead" valign='top' nowrap='nowrap'>Juvenile Name</td>
			<td class="subhead" valign='top' nowrap='nowrap'>Casefile Number</td>
			<td class="subhead" valign='top' nowrap='nowrap'>Activity Comments</td>
		</tr>
		<logic:iterate indexId="index" id="juvenile" name ="processActivitiesForMultipleYouthForm" property="juveniles">
			<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
				<td valign='top'><bean:write name="juvenile" property="juvenileNum" /></td>
				<td valign='top'><bean:write name="juvenile" property="activityId" /></td>
				<td valign='top'><bean:write name="juvenile" property="formattedName" /></td>
				<td valign='top'><bean:write name="juvenile" property="latestCasefileId" /></td>
				<td valign='top'><bean:write name="juvenile" property="comments" /></td>
			</tr>
		</logic:iterate>				
	</table>
	<br>
	<table  align="center" width="99%" border="0" cellpadding="2" cellspacing="0"  >
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
