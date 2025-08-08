<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/25/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prConditionsListTile.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<tiles:importAttribute name="condList"/>
</head>
<body>
<div align="center">
<table width="98%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
	<tr class="detailHead">
		<td width="1%"><a href="javascript:showHide('cond', 'row', '/<msp:webapp/>' )"><img border="0" src="/<msp:webapp/>images/expand.gif" name="cond"></a></td>
		<td><bean:message key="prompt.conditions"/>&nbsp;<bean:message key="prompt.list"/></td>
	</tr>
	<tr id="condSpan" class="hidden">
		<td valign="top" colspan="2">
			<table width="100%" border="0" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%"></td>
					<td class="formDeLabel"><bean:message key="prompt.literal"/></td>
				</tr>
				<% String bgcolor; %>
				<logic:iterate id="conditionsIndex" name="condList" indexId="conditionSeq">
					<tr class=
									<%bgcolor = "alternateRow";
									if ((conditionSeq.intValue()+1) % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);%>>
						<td class="boldText" align="center" width="1%"><%= conditionSeq.intValue()+1%></td>
						<td><bean:write name="conditionsIndex" property="conditionDesc" filter="false"/></td>
					</tr>
				</logic:iterate>     
			</table>
		</td>
	</tr>
</table>
</div>
</body>
</html:html>
