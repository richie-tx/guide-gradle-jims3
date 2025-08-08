<!DOCTYPE HTML>
<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005		DWilliamson	Create JSP --%>
<%-- 07/20/2009     C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileTraitsView.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|219">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Traits List</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
		<br>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
		<tr>
			<td class="confirm">Trait successfully updated.</td>
		</tr>
		<br>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.UPDATE_FAILURE%>">
		<tr>
			<td class="confirm">No active supervision found, update not allowed.</td>
		</tr>
		<br>
	</logic:equal>
	<tr>
		<td>
			<ul>
				<li>Select a Trait Type and Trait Description</li>
				<li>Click View Traits button to see list of traits for that type.</li>
				<logic:notEmpty name="juvenileProfileHeader" property="status">
					<li>Click Add More Traits to add more traits of that type. </li>
				</logic:notEmpty>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
<%--tabs start--%> 
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="traitstab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
<%--tabs end--%> 
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<%-- BEGIN TRAITS TABLE --%>
       					<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
							<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
						</tiles:insert>
						<%-- END TRAITS TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>