<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek		10/04/2011	Create JSP --%>
<%-- DGibler		01/13/2012	Removed Family#,Relationship and Guardian --%>
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
<title><bean:message key="title.heading" /> - suspiciousMembersReplaceSelect.jsp</title>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="casefileSearchForm" />
<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSelects()
{
	var fromSelected = false;
	var withSelected = false;
	var fromVal = "";
	var withVal = "";
	var fromfld = document.getElementsByName("selectedFromId");
	var withfld = document.getElementsByName("selectedToId");
	msg = "";

	for (x=0; x<fromfld.length; x++)
	{
		if (fromfld[x].checked == true)
		{
			fromSelected = true;
			fromVal = fromfld[x].value;
			break;
		}		
	}
	
	for (y=0; y<withfld.length; y++)
	{
		if (withfld[y].checked == true)
		{
			withSelected = true;
			withVal = withfld[y].value;
			break;
		}		
	}	
		
	if (fromSelected == true && withSelected == true)
	{
		if (fromVal != withVal)
		{
			return true;
		}	
		msg = "Replace Member# selection and With Member# selection can not be same member number.";
	}
	
	if (fromSelected == false) 
	{
		msg += "Replace Member# selection is Required.\n";
	}
	if (withSelected == false)
	{
		msg += "With Member# selection is Required.";
	}
		
	alert(msg);
	return false;	

}
function newCustomWindow( tURL, windowName, width, height ) 
{
	var widthString = "width=" + width;
	var heightString = "height=" + height;
	var params = "resizable=yes, scrollbars=yes, " + widthString + "," + heightString;

	msgWindow = open( tURL, windowName, params );
	if( msgWindow.opener == null ) 
	{
		msgWindow.opener = self;
	}
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySuspiciousMembersReplaceSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center">
				<bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" /> <bean:message key="prompt.replace" />
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
					<li>Make replace selections then click Next.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- BEGIN ASSOCIATED JUVENILES LIST TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
			<td class="detailHead" colspan="2">Associated Juveniles List</td>
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
					<logic:empty name="suspiciousMemberForm" property="associatedJuvenilesList">
						<tr>
							<td align="left" colspan="4">No Associated Juveniles found</td>
						</tr>		
					</logic:empty>
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
				</table>
			</td>
		</tr>
	</table>  
<%-- END ASSOCIATED JUVENILES LIST TABLE --%>		
	<div class="spacer4px"></div>
<%-- BEGIN FAMILY MEMBER SELECT TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
			<td class="detailHead" colspan="2"><bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /></td>
		</tr>
		<tr>
			<td align="center"> 
				<table width="100%" cellpadding="2" cellspacing="1"> 
					<logic:iterate id="matchList" name="suspiciousMemberForm" property="processList" indexId="index2"> 
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.member" /> #</td>
							<td><a href="javascript:newCustomWindow('/<msp:webapp/>displaySuspiciousMemberDetails.do?submitAction=Link&memberID=<bean:write name="matchList" property="memberNum" />', 'winName',600,525);"><bean:write name='matchList' property='memberNum' /></a></td>
							<td class="formDeLabel"><bean:message key="prompt.name" /></td>
							<td width="45%"><bean:write name="matchList" property="fullNamelfm" /></td>
						</tr>	
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td class="formDeLabel"><bean:message key="prompt.gender" /></td>
							<td><bean:write name="matchList" property="sex" /></td>
							<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.dob" /></td>
							<td><bean:write name="matchList" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
						</tr>	
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td class="formDeLabel"><bean:message key="prompt.SSN" /></td>
							<td colspan="3"><bean:write name="matchList" property="formattedSSN" /></td>
						</tr>
						<tr bgcolor="blue"><td heigth="3" colspan="4"></td></tr>	
					</logic:iterate>
				</table>
			</td>
		</tr>	
	</table>	
<%-- END FAMILY MEMBER SELECT TABLE --%> 
	<div class="spacer4px"></div>
<%-- BEGIN REPLACE SELECTIONS TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
			<td class="detailHead"><bean:message key="prompt.replace" /> <bean:message key="prompt.selection" />s</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="formDeLabel" width="1%" nowrap="nowrap" valign="center"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.replace" /> <bean:message key="prompt.member" /> #</td>
						<td align="left" width="43%">
							<logic:iterate id="replaceList" name="suspiciousMemberForm" property="processList">
								<div><input type="radio" name="selectedFromId" value="<bean:write name="replaceList" property="memberNum" />"><bean:write name="replaceList" property="memberNum" /></div>
							</logic:iterate>
						</td>
						<td class="formDeLabel"  width="1%" nowrap="nowrap" valign="center"><bean:message key="prompt.2.diamond" />With <bean:message key="prompt.member" /> #</td>
						<td align="left">
							<logic:iterate id="withList" name="suspiciousMemberForm" property="processList">
								<div><input type="radio" name="selectedToId" value="<bean:write name="withList" property="memberNum" />" ><bean:write name="withList" property="memberNum" /></div>
							</logic:iterate>
						</td>
					</tr>
				</table>
			</td>
		</tr>		
	</table>
<%-- END REPLACE SELECTIONS TABLE --%>	
	<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="backeButton">
					<bean:message key="button.back" />
				</html:submit>
				<html:submit property="submitAction" styleId="nextButton" onclick="return validateSelects() && disableSubmit(this, this.form);">
					<bean:message key="button.next" />
				</html:submit>
				<html:submit property="submitAction" styleId="cancelButton">
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