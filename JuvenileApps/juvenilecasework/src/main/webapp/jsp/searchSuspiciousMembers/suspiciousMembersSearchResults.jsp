<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek		05/09/2005	Create JSP --%>
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
<title><bean:message key="title.heading" /> - suspiciousMembersSearchResults.jsp</title>

<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSelect()
{
	var fld = document.getElementsByName("selectedOrigMemberNum");
	for (x=0; x<fld.length; x++)
	{
		if (fld[x].checked == true)
		{
			return true;
		}		
	}	
	alert("Member selection is required.");
	return false;	
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySuspiciousMembersSelect" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">

<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center"><bean:message key="prompt.suspiciousMembers" /> -
				<logic:equal name="suspiciousMemberForm" property="searchTypeId" value="FAM" > 
					<bean:message key="prompt.familyMember" /> 
				</logic:equal>
				<logic:equal name="suspiciousMemberForm" property="searchTypeId" value="JUV" > 
					<bean:message key="prompt.juvenile" />
				</logic:equal>
				Search Results 
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Member then Click Next button.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>

	<bean:size id="memcount" name="suspiciousMemberForm" property="searchResultsList"/>
	<table width="100%">
		<tr>
			<td align="center"><bean:write name="memcount"/> member matches found</td>
		</tr>
	</table>
	<logic:equal name="suspiciousMemberForm" property="searchTypeId" value="JUV" > 
		<div class="spacer4px"></div>

<%-- BEGIN PROFILE HEADER TABLE --%>		
		<table align="center" width='98%' border='0' cellpadding='2' cellspacing='1' bgcolor='#cccccc'>	
			<tr class='bodyText'>
				<td class='formDeLabel' colspan='6'>Profile Information</td>
			</tr>
			<tr>
				<td class='headerLabel' nowrap='nowrap'><bean:message key="prompt.juvenile#" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="juvenileNumber" /></td>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.juvenileName" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="juvenileName" /></td>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="dateOfBirthStr" /></td>
			</tr>	
			<tr>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.race" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="raceLit" /></td>
				<td class='headerLabel'><bean:message key="prompt.gender" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="genderLit" /></td>
				<td class='headerLabel' ><bean:message key="prompt.ethnicity" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="ethnicityLit" /></td>
			</tr>	
		</table>
<%-- END PROFILE HEADER TABLE --%>	
	<div class="spacer4px"></div>		
	</logic:equal>
<%-- BEGIN MEMBER SELECT TABLE --%>	
	<table align="center" width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td width="1%">&nbsp;</td>
			<td nowrap="nowrap"><bean:message key="prompt.member#" /></td>
			<td><bean:message key="prompt.name" /></td>
			<td><bean:message key="prompt.SSN" /></td>
			<td><bean:message key="prompt.gender" /></td>
			<td><bean:message key="prompt.nationality" /></td>
			<td><bean:message key="prompt.ethnicity" /></td>
			<td><bean:message key="prompt.dob" /></td>
		</tr>
		<logic:iterate id="famList" name="suspiciousMemberForm" property="searchResultsList" indexId="index">
			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				<td ><input type="radio" name="selectedOrigMemberNum" value="<bean:write name='famList' property='memberNum' />" ></td>
				<td><a href="javascript:newCustomWindow('/<msp:webapp/>displaySuspiciousMemberDetails.do?submitAction=Link&memberID=<bean:write name="famList" property="memberNum" />', 'winName',700,625);"><bean:write name='famList' property='memberNum' /></a></td>
				<td nowrap="nowrap"><bean:write name="famList" property="fullNamelfm" /></td>
				<td><bean:write name="famList" property="formattedSSN"/></td>
				<td><bean:write name="famList" property="sex" /></td>
				<td><bean:write name="famList" property="nationalityDesc" /></td>
				<td><bean:write name="famList" property="ethnicityDesc" /></td>
				<td><bean:write name="famList" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
			</tr>
		</logic:iterate>
	</table>
<%-- END MEMBER SELECT TABLE --%>		
	<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="return validateSelect() && disableSubmit(this, this.form)" styleId="memSearchBtn"> 
					<bean:message key="button.next" />
				</html:submit>
				<html:submit property="submitAction" styleId="submitButton"> 
					<bean:message key="button.cancel" />
				</html:submit>
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%> 
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>