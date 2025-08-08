<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/28/2008	 Richard Young  - ER#53069 changed wording on Casenote prompt. -->
<!-- 10/30/2008	 RYoung         - Defect#55115  Fixed summary of changes for an historical order. -->
<!-- 11/06/2008	 RYoung 		- ER#55319 Add summary notes. -->
<!-- 06/19/2009  RYoung - Activity #60241 Do not allow to print Migrated order -->
<!-- 10/26/2009  C Shimek       - #60771 revise offense code to only display description  -->
<!-- 11/19/2009  C Shimek       - #60771 revised offense code to printed offense description -->
<!-- 10/19/2010  C Shimek       - #67921 removed "intervention" from pretrial begin and end date labels -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@page import="naming.UIConstants"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title></title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head> 
<table border="0" align="center" cellpadding="4" cellspacing="1" width="100%">											
	<tr>
		<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.versionType" /></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="versionType" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.specialCourt" /></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="specialCourt" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="orderTitle" /></td>
		<input type="hidden" maxlength="15" name="orderTitleCD" value="<bean:write name='supervisionOrderForm' property='orderTitle' />" >		
	</tr>
	
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.printedOffenseDescription" /></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="printedOffenseDesc" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.printedName" /></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="printedName" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.dispositionType"/></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="dispositionType" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.fineAmount"/></td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="fineAmountTotal" /></td>
	</tr>
	<logic:equal name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.confinementLength"/></td>
			<td class="formDe">
				<logic:notEmpty name="supervisionOrderForm" property="confinementLengthYears"><bean:write name="supervisionOrderForm" property="confinementLengthYears" /> Years </logic:notEmpty>
				<logic:notEmpty name="supervisionOrderForm" property="confinementLengthMonths"><bean:write name="supervisionOrderForm" property="confinementLengthMonths" /> Months </logic:notEmpty>
				<logic:notEmpty name="supervisionOrderForm" property="confinementLengthDays"><bean:write name="supervisionOrderForm" property="confinementLengthDays" /> Days </logic:notEmpty>	
			</td>
		</tr>
	</logic:equal>
	<logic:notEqual name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.supervisionLength"/></td>
			<td class="formDe">
				<logic:notEmpty name="supervisionOrderForm" property="supervisionLengthYears"><bean:write name="supervisionOrderForm" property="supervisionLengthYears" /> Years </logic:notEmpty>
				<logic:notEmpty name="supervisionOrderForm" property="supervisionLengthMonths"><bean:write name="supervisionOrderForm" property="supervisionLengthMonths" /> Months </logic:notEmpty>
				<logic:notEmpty name="supervisionOrderForm" property="supervisionLengthDays"><bean:write name="supervisionOrderForm" property="supervisionLengthDays" /> Days </logic:notEmpty>	
			</td>
		</tr>
	</logic:notEqual>
	<tr>
		<td class="formDeLabel" nowrap>
			<logic:notEqual name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
				<bean:message key="prompt.supervisionBeginDate"/>
			</logic:notEqual>
			<logic:equal name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
				<bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.beginDate"/>
			</logic:equal>
		</td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="caseSupervisionBeginDateAsString" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap>
			<logic:notEqual name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
				<bean:message key="prompt.supervisionEndDate"/>
			</logic:notEqual>
			<logic:equal name="supervisionOrderForm" property="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
				<bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.endDate"/>
			</logic:equal>
		</td>
		<td class="formDe"><bean:write name="supervisionOrderForm" property="caseSupervisionEndDateAsString" /></td>
	</tr>
											
	<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
		<tr>				                          	
			<td class="formDeLabel"><bean:message key="prompt.plea" /></td>
			<td class="formDe"><bean:write name="supervisionOrderForm" property="plea" /></td>		
		</tr>
	</logic:equal>
										
<%--		<logic:equal name="supervisionOrderForm" property="limitedSupervisonPeriod" value="true">
			<tr>				                          	
				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.supervisionBeginDate" /></td>
				<td class="formDe"><bean:write name="supervisionOrderForm" property="supervisionBeginDateAsString" /></td>		
			</tr>
			<tr>				                          	
				<td class="formDeLabel"><bean:message key="prompt.supervisionEndDate" /></td>
				<td class="formDe"><bean:write name="supervisionOrderForm" property="supervisionEndDateAsString" /></td>		
			</tr>
		</logic:equal>
--%>
	<logic:notEmpty name="supervisionOrderForm" property="summaryOfChanges">
		<logic:notEqual name="supervisionOrderForm" property="summaryOfChanges" value="">
			<tr>				                          	
				<td class="formDeLabel" colspan="2"><bean:message key="prompt.summaryOfchanges"/> for Printed Order</td>
			</tr>
			<tr>
				<td class="formDe" colspan="2"><bean:write name="supervisionOrderForm" property="summaryOfChanges" /></td>		
			</tr>
		</logic:notEqual>
	</logic:notEmpty>
	<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
		<tr>				                          	
			<td class="formDeLabel" colspan="2"><bean:message key="prompt.comments" /></td>
		</tr>
		<tr>	
			<td class="formDe" colspan="2"><bean:write name="supervisionOrderForm" property="comments" /></td>		
		</tr>
	</logic:equal>
</table>