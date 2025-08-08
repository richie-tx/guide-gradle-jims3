<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/01/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="naming.UIConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<tiles:importAttribute name="superviseeList"/>

<table width="98%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
	<tr class="detailHead">
		<td width="1%">
			<a href="javascript:showHide('cond', 'row', '/<msp:webapp/>')" border="0">
			<img border="0" src="/<msp:webapp/>images/expand.gif" name="cond"></a>
		</td>
		<td>Selected Supervisee(s)</td>
	</tr>
	<tr id="condSpan" class="hidden">
		<td valign="top" colspan="2">
			<table width="100%" border="0" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.name" /></td>
					<td class="formDeLabel"><bean:message key="prompt.SPN" /></td>
				</tr>
				<logic:iterate id="superviseeIter" name="superviseeList" indexId="index1">
					<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td>
							<logic:notEqual name="superviseeIter" property="defendantName" value="" >
								<bean:write name="superviseeIter" property="defendantName"/>
							</logic:notEqual>
							<logic:equal name="superviseeIter" property="defendantName" value="" >
								<msp:formatter name="superviseeIter" property="name" format="L, F M"/>
							</logic:equal>
						</td>		
						<td><bean:write name="superviseeIter" property="spn"/></td>
					</tr>
				</logic:iterate>
			</table>
		</td>
	</tr>
</table>
</html:html>