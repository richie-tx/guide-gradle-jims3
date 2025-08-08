<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/25/2009	 CShimek - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<title>Common Supervision - common/caseAssignmentDataControlHeader.jsp</title>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<!-- BEGIN SUPERVISEE INFO HEADER -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0"> 
 		<tr>
 			<td bgcolor="#cccccc">
 				<table width="100%" border="0" cellpadding="2" cellspacing="1"> 
					<tr> 
						<td class="headerLabel"><bean:message key="prompt.name"/></td> 
						<td class="headerData" colspan="3">
							<logic:equal name="caseAssignmentDataControlHeaderForm" property="superviseeActivelySupervised" value="true">
								<img style='float:left; padding-right:1px' src="/<msp:webapp/>images/greenStarCircleIcon.png" name="redLight" title="Supervisee Actively Supervised" border="0">
							</logic:equal>
							<bean:write name="caseAssignmentDataControlHeaderForm" property="superviseeName"/></td> 
						<td class="headerLabel"><bean:message key="prompt.SPN"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="spn"/></td> 
					</tr> 
					<tr> 
						<td class="headerLabel"><bean:message key="prompt.SSN"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="ssn"/></td> 
						<td class="headerLabel"><bean:message key="prompt.sex"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="sex"/></td> 
						<td class="headerLabel"><bean:message key="prompt.dob"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="dob"/></td> 
					</tr> 
					<tr> 
						<td class="headerLabel"><bean:message key="prompt.case#"/></td> 
						<td class="headerData">
							<logic:equal name="caseAssignmentDataControlHeaderForm" property="caseActivelySupervised" value="true">
								<img style='float:left; padding-right:1px' src="/<msp:webapp/>images/blueStarCircleIcon.png" name="redLight" title="Case Actively Supervised" border="0">
							</logic:equal>
							<bean:write name="caseAssignmentDataControlHeaderForm" property="caseNum"/></td> 
						<td class="headerLabel"><bean:message key="prompt.CDI"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="cdi"/></td> 
						<td class="headerLabel"><bean:message key="prompt.CRT"/></td> 
						<td class="headerData"><bean:write name="caseAssignmentDataControlHeaderForm" property="courtNum"/></td> 
					</tr>
				</table> 
			</td> 
		</tr> 
	</table> 
<!-- END SUPERVISEE INFO HEADER -->