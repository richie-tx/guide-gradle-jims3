<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/1/209  CShimek    corrected officer assignment date prompt, found testing defect 55685 -->
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
<title>Common Supervisoin - common/selectedOffToAssignSupToTile.jsp</title>

	<table align="center" width=98% border="0" cellpadding="2"
					cellspacing="0" class=borderTableBlue>
		<tr class="detailHead">
			<td>Selected Officer to Assign Supervisee to</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.poi" /></td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="officerPOI" /></td>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.positionName" /></td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="officerPositionName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name" /></td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="officerName.formattedName" /></td>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.officer"/> <bean:message key="prompt.assignmentDate" /></td>
					<td class="formDe"><bean:write name="caseAssignmentForm" property="officerAssignmentDate" /></td>					
				</tr>
			</table>
			</td>
		</tr>
	</table>