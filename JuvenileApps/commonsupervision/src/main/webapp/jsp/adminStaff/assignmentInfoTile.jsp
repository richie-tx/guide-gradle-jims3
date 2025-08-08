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
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
	
</head> 

<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>

<script language="JavaScript" id="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>

<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.assignment"/> <bean:message key="prompt.information"/></td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" nowrap width="1%">Selected <bean:message key="prompt.user"/></td>
					<td class="formDe">
						
							<logic:equal name="adminStaffForm" property="action" value="reassign">
								<bean:write name="adminStaffForm" property="reassignedUser.userName.formattedName"/> | 
								<logic:notEmpty name="adminStaffForm" property="position.probOfficerInd">
									<logic:notEqual name="adminStaffForm" property="position.probOfficerInd" value="">
										<bean:write name="adminStaffForm" property="position.probOfficerInd"/>, 
									</logic:notEqual>
								</logic:notEmpty>
								<bean:write name="adminStaffForm" property="position.positionName"/>
							</logic:equal>
							<logic:equal name="adminStaffForm" property="action" value="assign">
								<bean:write name="adminStaffForm" property="reassignedUser.userName.formattedName"/>
							</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.workgroup_s"/></td>
					<td class="formDe">
						<bean:write name="adminStaffForm" property="reassignedUser.workgroupDesc"/>
					</td>
				</tr>
			
				<logic:notEqual name="adminStaffForm" property="secondaryAction" value="summary">
					<tr>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.cjad"/></td>
						<td class="formDe">
							<logic:equal name="adminStaffForm" property="position.hasCaseload" value="true">
								<html:text size="10" name="adminStaffForm" property="reassignedUser.cjad" maxlength="9"/>					
							</logic:equal>
							<logic:equal name="adminStaffForm" property="position.hasCaseload" value="false">
								<bean:write name="adminStaffForm" property="reassignedUser.cjad"/>
							</logic:equal>	
						</td>		
					</tr>
					<tr>
						<td class="formDeLabel" nowrap width="1%"><logic:notEqual name="adminStaffForm" property="secondaryAction" value="summary"><bean:message key="prompt.diamond" /></logic:notEqual><bean:message key="prompt.effectiveDate"/></td>
						<td class="formDe">
							<html:text styleId="effectiveDateAsStr" size="10" name="adminStaffForm" property="position.effectiveDateAsStr" maxlength="10" size="10" />
							<a href="#" onClick="cal1.select(document.forms[0].effectiveDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar"/></a>													
						</td>
					</tr>
				</logic:notEqual>
	
				<logic:equal name="adminStaffForm" property="secondaryAction" value="summary">
					<tr>
						<td class="formDeLabel" width="1%"><bean:message key="prompt.cjad"/></td>
						<td class="formDe">
							<bean:write name="adminStaffForm" property="reassignedUser.cjad"/>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.effectiveDate"/></td>
						<td class="formDe">
							<bean:write name="adminStaffForm" property="position.effectiveDateAsStr"/>
						</td>	
					</tr>							
				</logic:equal>
			</table>
		</td>
	</tr>
</table>