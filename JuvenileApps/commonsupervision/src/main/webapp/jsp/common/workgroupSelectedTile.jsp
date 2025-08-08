<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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
<title>Common Supervision - common/workgroupSelectedTile.jsp</title>

</head>
<body>
	<table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
		<tr class="detailHead">
			<td>Workgroup Selected</td>
		</tr>
		<tr>
			<td>
				<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr class="formDeLabel">															
									<td>Name</td>
									<td>Description</td>
								</tr>
								<tr>
									<td><a href="javascript: openWindow('../workgroupCSCD/workgroupDetailPopUp.htm')">Court Services Receiving</a></td>
									<td>This is a the Court Services Receiving Workgroup</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
</body>
</html:html>