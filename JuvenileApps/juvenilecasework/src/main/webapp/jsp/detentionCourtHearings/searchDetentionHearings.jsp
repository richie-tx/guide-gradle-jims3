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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/detentionCourt/searchDetentionHearing.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/searchDetentionHearings.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileSearchDetentionHearings" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Search Detention Hearing Record</h2></td>
	</tr>
</table>
<br/>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0" align="center">
   <tr>
     <td>
  	  <ul>
        	<li>Enter/Select required field and select the button to search</li>
    		<li>Select the Facility if you want settings within.</li> 
     </ul>	
	 </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<br/>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
	<tr class='crtDetailHead'>
		<td align='left' colspan='4' class='paddedFourPix'>Search Detention Record</td>
	</tr>
	<tr colspan='2'>
		<td class='formDeLabel' width='4%' nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
		<td class='formDe' >	
			<html:text name="detentionHearingForm" property="date" styleId="date" size="10" maxlength="10" size="10"/>
		</td>
	    <td class='formDeLabel' width='7%'><bean:message key="prompt.facility"/></td>
		<td class='formDe'>
			<html:select name="detentionHearingForm" property="facility" styleId="facility">
				<html:option key="select.generic" value="" />
				<html:optionsCollection property="facilities" value="code" label="descriptionWithCode"/> 				
			</html:select>
		</td> 
	</tr>
</table>
<br>
 <table border="0" width="100%">
	<TBODY>
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="btnCrtDocket"><bean:message key="button.courtDocket"/></html:submit>
				<html:submit property="submitAction" styleId="btnCrtAction"><bean:message key="button.courtAction2"/></html:submit>				
					<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		  		</html:button>
			</td>
		</tr>
	</TBODY>
</table>
</html:form>

</body>
</html:html>