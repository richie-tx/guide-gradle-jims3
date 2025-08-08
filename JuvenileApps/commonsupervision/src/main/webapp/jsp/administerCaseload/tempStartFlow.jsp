<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 1/20/2011 LDeen doesn't think this jsp is used -->
<!-- 1/20/2011 This page temporarily substitutes Task Details page -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>

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

<title><bean:message key="title.heading" /> - administerCaseload/tempStartFlow.jsp</title>
			
</head>

<body>
	<html:form action="/handleCaseAssignment" target="content">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<table width="50%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td class="formDeLabel" nowrap colspan="4" align="center">This page temporarily substitutes Task Details page</td>
			</tr>
	
			<tr><td><a href="/<msp:webapp/>executeTask.do?taskId=928">Execute Task - Assign supervisee to program unit</a></td></tr>
			<tr><td><a href="/<msp:webapp/>executeTask.do?taskId=931">Execute Task - Allocate supervisee to supervisor</a></td></tr>
			<tr><td><a href="/<msp:webapp/>executeTask.do?taskId=933">Execute Task - Assign supervisee to officer</a></td></tr>
			<tr><td><a href="/<msp:webapp/>executeTask.do?taskId=576">Execute Task - Process officer new case assignment</a></td></tr>
			<tr>
				<td class="formDeLabel" nowrap colspan="4" align="center">Refactored : Task Details page</td>
			</tr>

			
			<tr><td><a href="/<msp:webapp/>reviewActiveCasesForAssignSuperviseeToProgramUnit.do?submitAction=reviewActiveCases&taskId=1320">Execute Task - Assign supervisee to program unit</a></td></tr>
			<tr><td><a href="/<msp:webapp/>reviewActiveCasesForAllocateSuperviseeToSupervisor.do?submitAction=reviewActiveCases&taskId=1177">Execute Task - Allocate supervisee to supervisor</a></td></tr>
			<tr><td><a href="/<msp:webapp/>reviewActiveCasesForAssignSuperviseeToOfficer.do?submitAction=reviewActiveCases&taskId=1230">Execute Task - Assign supervisee to officer</a></td></tr>
			<tr><td><a href="/<msp:webapp/>reviewActiveCasesForProcessOfficerNewCaseAssignment.do?submitAction=reviewActiveCases&taskId=1281">Execute Task - Process officer new case assignment</a></td></tr>
			<tr><td><a href="/<msp:webapp/>reviewActiveCasesForReassignCasesToNewOfficer.do?submitAction=reviewActiveCases&taskId=1281">Execute Task - Process officer reassignment</a></td></tr>
		</table>
	</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
