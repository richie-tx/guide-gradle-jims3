<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- CShimek		10/04/2011	Create JSP --%>
<%-- DGibler		01/17/2012	Removed family number, relationship and guardian --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - suspiciousMembersMergeSummary.jsp</title>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="casefileSearchForm" />
<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitSuspiciousMembersMerge" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center">
				<bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" />
				<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
					Merge Summary
				</logic:equal> 
				<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="confirm">
					Merge Confirmation
				</logic:equal> 
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
	<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
<%-- BEGIN INSTRUCTION TABLE --%>
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Verify all data is correct then click Finish.</li>
					</ul>
				</td>
			</tr>
		</table>
<%-- END INSTRUCTION TABLE --%>
	</logic:equal>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
	<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="confirm">
<%-- BEGIN CONFIRMATION MESSAGE TABLE --%>
		<table width="98%" border="0" align="center">
			<tr>
				<td align="center" class="confirm"><bean:write name="suspiciousMemberForm" property="confirmationMsg" /></td>
			</tr>
		</table>
<%-- END CONFIRMATION MESSAGE TABLE --%>
	</logic:equal>
<%-- BEGIN ASSOCIATED JUVENILES LIST TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
			<td class="detailHead" colspan="2"><bean:message key="prompt.associatedJuvenilesList" /></td>
		</tr>
		<tr>
			<td align="center"> 
				<table width="100%" cellpadding="2" cellspacing="1">
					<tr bgcolor="#cccccc">
						<td align="left" class="subHead"><bean:message key="prompt.juvenile#" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.juvenileName" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.race" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.dateOfBirth" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.ethnicity" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.gender" /></td>
					</tr>
					<logic:empty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
						<tr>
							<td align="left">No Associated Juveniles found.</td>
						</tr>
					</logic:empty> 
					<logic:notEmpty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
						<logic:iterate id="juvList" name="suspiciousMemberForm" property="associatedJuvenilesList" indexId="index1">
							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td align="left"><bean:write name="juvList" property="juvId"/></td>
								<td align="left"><bean:write name="juvList" property="juvName"/></td>
								<td align="left"><bean:write name="juvList" property="raceDesc"/></td>
								<td align="left"><bean:write name="juvList" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
								<td align="left"><bean:write name="juvList" property="ethnicityDesc"/></td>
								<td align="left"><bean:write name="juvList" property="sexDesc"/></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
			</td>
		</tr>	
	</table>  
<%-- END ASSOCIATED JUVENILES LIST TABLE --%>		
	<div class="spacer4px"></div>
<%-- BEGIN MERGED FAMILY MEMBER INFO TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
			<td class="detailHead" colspan="2">Merged <bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /></td>
		</tr>
		<tr>
			<td align="center"> 
				<table width="100%" cellpadding="2" cellspacing="1"> 
					<logic:iterate id="memTo" name="suspiciousMemberForm" property="mergeMemberToList" >
						<tr>
							<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.member" /> #</td>
							<td class="formDe"><bean:write name="memTo" property="memberNum" /></td>
						</tr>
						<tr>	
							<td class="formDeLabel"><bean:message key="prompt.name" /></td>
							<td class="formDe"><bean:write name="memTo" property="lastName" />, <bean:write name="memTo" property="firstName" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.gender" /></td>
							<td class="formDe"><bean:write name="memTo" property="sex" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.dob" /></td>
							<td class="formDe"><bean:write name="memTo" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.SSN" /></td>
							<td class="formDe"><bean:write name="memTo" property="formattedSSN" /></td>
						</tr>
					</logic:iterate>
				</table>
			</td>
		</tr>	
	</table>	
<%-- END MERGED FAMILY MEMBER INFO TABLE --%> 
	<div class="spacer4px"></div>	
<%-- BEGIN MERGE SELECTION TABLE --%>
	<table  align="center" border="0" cellpadding="2" width='98%' cellspacing="1" class="borderTableBlue">
		<tr>
			<td colspan="4" valign="top" class="detailHead">Merge Selections</td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap">Merge </td>
			<td class="formDe">member# <bean:write name="suspiciousMemberForm" property="selectedFromId" /> to member# <bean:write name="suspiciousMemberForm" property="selectedToId" /></td>
		</tr>
	</table>	
<%-- END MERGE SELECTION TABLE --%>
		<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
					<html:submit property="submitAction" styleId="backButton">
						<bean:message key="button.back" />
					</html:submit>
					<html:submit property="submitAction" styleId="finishButton"  onclick="disableSubmit(this, this.form)">
						<bean:message key="button.finish" />
					</html:submit>
					<html:submit property="submitAction" styleId="cancelButton">
						<bean:message key="button.cancel" />
					</html:submit>
				</logic:equal>
				<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="confirm">
					<logic:equal name="suspiciousMemberForm" property="returnToSearch" value="">	
						<html:submit property="submitAction" styleId="backToResolveButton" onclick="disableSubmit(this, this.form)">
							<bean:message key="button.backToResolutionSelection" />
						</html:submit>
					</logic:equal>
					<logic:equal name="suspiciousMemberForm" property="returnToSearch" value="Y">
						<html:submit property="submitAction" styleId="backToSearchButton" onclick="disableSubmit(this, this.form)">
							<bean:message key="button.backToSearch" />
						</html:submit>
					</logic:equal>	
				</logic:equal>	
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%> 
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>