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

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<html:base />
<title>Common Supervison - common/selectedSupervisorTile.jsp</title>


	<table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
		<tr class="detailHead">
			<td>Selected Supervisor</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" width=1% nowrap>Division</td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="divisionName" /></td>
					<td class="formDeLabel" nowrap>Program Unit</td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="programUnitName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap>Name</td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="supervisorName.formattedName" /></td>
					<td class="formDeLabel" width=1% nowrap>Staff Type</td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="staffPositionDescription" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>