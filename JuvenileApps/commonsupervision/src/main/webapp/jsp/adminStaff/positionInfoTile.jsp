<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
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
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js" type="text/javascript"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>

<script language="JavaScript" id="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>
	
</head> 

<tiles:importAttribute name="position"/>

<table width="100%" cellpadding="4" cellspacing="1">
<logic:equal parameter="retirePositionFlag" value="true">
	<tr>
		<td nowrap="" class="formDeLabel"><bean:message key="prompt.2.diamond"/> Retirement Date</td>
		<td class="formDe" colspan="3">
			<html:text styleId="retirementDateAsStr" property="position.retirementDateAsStr" size="10" maxlength="10"/>
			<a href="#" onClick="cal1.select(document.forms[0].retirementDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar"/></a>
		</td>
	</tr>
</logic:equal>
<logic:equal parameter="retirePositionSummaryFlag" value="true">
	<tr>
		<td nowrap="" class="formDeLabel">Retirement Date</td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="retirementDateAsStr" />
		</td>
	</tr>
</logic:equal>

	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.hasCaseload"/></td>
		<td colspan="3" class="formDe">
			<bean:write name="position" property="caseloadAsYESNO"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.probationOfficerIndicator"/></td>
		<td colspan="3" class="formDe">
			<bean:write name="position" property="probOfficerInd"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.positionName"/></td>
		<td colspan="3" class="formDe">
			<bean:write name="position" property="positionName"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.positionType"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="positionTypeDesc"/>
		</td>
	</tr>
	<%--NOT IMPLEMENTED YET AS OF 04/17/2007 --%>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.cstsOfficerType"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="officerTypeDesc"/>
		</td>
	</tr>	
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.jobTitle"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="jobTitleDesc"/>
		</td>
	</tr>
	<tr id="divisionSelect">
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.division"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="divisionDesc"/>
		</td>
	</tr>
	<tr id="programUnitSelect">
		<td class="formDeLabel" nowrap><bean:message key="prompt.programUnit"/></td>
		<td class="formDe" colspan="3"><bean:write name="position" property="programUnitDesc"/>
		</td>
	</tr>
	<tr id="programSectionSelect">
		<td class="formDeLabel" nowrap><bean:message key="prompt.programSection"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="programSectionDesc"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="phone.formattedPhoneNumber"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.location"/></td>
		<td class="formDe" colspan="3"><bean:write name="position" property="locationDesc"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.locationDetails"/></td>
		<td class="formDe" colspan="3"><bean:write name="position" property="locationDetails"/>
		</td>
	</tr>
	
	<tr id="currentlyFilled">
		<td class="formDeLabel" nowrap><bean:message key="prompt.currentlyAssignedTo"/></td>
		<td class="formDe" colspan="3">
		<logic:equal name="position" property="vacant" value="false">
			<bean:write name="position" property="user.userName.formattedName"/>
			</logic:equal>
			<logic:notEqual name="position" property="vacant" value="false">
			<bean:message key="prompt.noOfficerAssigned"/>
			</logic:notEqual>
		</td>
	</tr>
	
	<logic:equal name="position" property="vacant" value="false">
	<logic:notEqual name="position" property="user.cjad" value="">
	
	<tr id="cjadRow">
		<td class="formDeLabel" nowrap><bean:message key="prompt.cjad"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="user.cjad"/>
		</td>
	</tr>
	</logic:notEqual>
	</logic:equal>
	<logic:notEqual name="position" property="divisionManager" value="true">
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.supervisor"/></td>
		<td class="formDe" colspan="3">
		
			<logic:notEmpty name="position" property="supervisor">
			<logic:equal name="position" property="supervisor.vacant" value="false">
					<bean:write name="position" property="supervisor.user.userName.formattedName"/> 
					
					| 
					<bean:write name="position" property="supervisor.probOfficerInd"/>  
			</logic:equal>
			<logic:notEqual name="position" property="supervisor.vacant" value="false">
			<bean:message key="prompt.noOfficerAssigned"/>
			</logic:notEqual>
				</logic:notEmpty>
					<bean:write name="position" property="supervisor.positionTypeDesc"/> 
				</td></tr></logic:notEqual>

	<tr class="visibleTR" id="effectiveDate">
		<td class="formDeLabel" nowrap><bean:message key="prompt.effectiveDate"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="position" property="effectiveDateAsStr"/>
		</td>
	</tr>
				
		
</table>
