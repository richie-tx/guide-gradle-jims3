<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/> 
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>  
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>


<title><bean:message key="title.heading"/>/facilityTraits.jsp</title>
</head>
<body>

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Traits</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<div class='spacer'></div>
<%-- BEGIN CONFIRMATION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="juvenileTraitsForm" property="action" value="confirm">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="action" value="confirmUpdate">
		<tr>
			<td class="confirm">Trait status successfully updated.</td>
		</tr>
	</logic:equal>
</table>	
<%-- END CONFIRMATION TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Trait Type Description and Click View button to see list.</li>
				<li>Select Add More Traits button to add more traits.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td valign="top" colspan="8">
           <tiles:insert page="facilityTraitsSearch.jsp"> 
				<tiles:put name="actionPath" value="/handleJuvenileFacilityTraits"/>
		    </tiles:insert>
        </td>
    </tr> 
</table>

</body>
</html:html>