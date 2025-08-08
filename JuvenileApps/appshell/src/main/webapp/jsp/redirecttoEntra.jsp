<!DOCTYPE HTML>
<!-- associated object List-->


<%-- LDeen   3/6/2006  Added HTML:error tag --%>
<%-- CShimek 03/16/2006 #29680 Revised content in Helpful Information --%>
<%-- CShimek 04/03/2006 #30242 Revised Helpful Information content to point to pdf document --%>
<%-- CShimek 04/13/2006 #30463 Revised href for Usability Tips --%>
<%-- CShimek 02/02/2007 Corrected problem of "About" not displaying correctly, would display JIMS2 Log In instead
						of aboutjims2.htm after user logged out.  Worked fine on initial entry --%>
<%-- LDeen   05/23/2007 #42239 Remove Cancel button --%>
<%-- LDeen   05/23/2007 #46265 Add instruction re pop up blocker--%>
<%-- LDeen   07/14/2009 #60717 Revise Usability tips URL & add info re copy and paste--%>
<%-- L Deen  09/18/2009 #62066 Add PASO Instructions for CLOs link page --%>
<%-- L Deen  11/18/2009	#62845 Revise news & notes section --%>
<%-- L Deen  11/18/2009	#59843 Revise news & notes section to add Help Instructions--%>
<%-- L Deen  06/10/2010	#65923 Revise news & notes section to add CSCD Faqs--%>
<%-- L Deen  06/25/2010 Revise news & notes section to add IE settings & System Downtimes--%>
<%-- L Deen  08/05/2010 Revise news & notes section to fix link for help instructions PDF--%>
<%-- D James 06/15/2010 #73337 Revise news & notes section to add link for MJCW FAQs--%>


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--JQUERY FRAMEWORK-->
<%@include file="jQuery.fw"%>



<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Redirect to Entra</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<msp:nocache />

<script type="text/javascript" src="/<msp:webapp/>js/login.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/OpenHelper.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>
<base href="$url" />
<!-- <html:base/> -->

<body topmargin="0" leftmargin="0" onLoad="checkIfTopFrame(); document.forms[0].logonId.focus();">
<html:form>

<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|87">

<!-- BEGIN HEADING TABLE -->
<table width='100%' cellspacing="0">
	<tr bgcolor='#003366'>
		<td align="left"><img src="/<msp:webapp/>images/Header.jpg"></td>
		<td align="right" valign="top">
		<br>
			<table cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td class="banner"><script>document.write( getCurrentDay() );</script></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="banner"><script>document.write( getCurrentDate() );</script></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	
</table>

<table width="98%" align="center" cellspacing="0" cellpadding="2">
	<tr valign="middle">
		<td align="center">


 <table align="center" width="70%" cellspacing="0" cellpadding="2" class="bannerText">
	<tr>
		<td colspan="2" align="left" class="errorAlert">Warning:</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" align="left" class="bannerText">
		    Token Expired. Please close the browser and start over!
		</td>
	</tr>	
	<tr>
		<td colspan="2"></td>
	</tr>
</table>

</html:form>

</body>

