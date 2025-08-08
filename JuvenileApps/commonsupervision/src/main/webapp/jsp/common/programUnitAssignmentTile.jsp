<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.CaseloadConstants"%>

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
<title>Common Supervision - common/programUnitAssignmentTile.jsp</title>

	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
			<td>Program Unit Assignment</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Program Unit</td>
					<td class="formDe"><bean:write name="caseAssignmentForm"
						property="programUnitName" /></td>
				</tr>
				<logic:notEqual name="caseAssignmentForm" property="<%=CaseloadConstants.WORKFLOW_IND%>" 
								value="<%=CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT_CREATE_CN%>">
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Workgroup</td>
					<td class="formDe"><bean:write name="caseAssignmentForm"
						property="workGroupName" /></td>
				</tr>
				</logic:notEqual>
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Program Unit Assignment Date</td>
					<td class="formDe"><bean:write name="caseAssignmentForm"
						property="programUnitAllocationDate" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>