<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek		10/04/2011	Create JSP --%>
<%-- CShimek		04/23/2012	73176 added DOB Family Member Information dispaly block  --%>
<%-- CShimek		05/17/2012	73509 added Juvenile# href to Constellation List page. --%>
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
<title><bean:message key="title.heading" /> - suspiciousMembersConnection.jsp</title>

<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSearch(el)
{
	var famMemID = document.getElementsByName("searchMemberBId");
	var fldValue = Trim(famMemID[0].value);
	if( fldValue == ""){
		
		alert("Suspicious Member ID  is required!.");
		return false;
	}else{
		return true;
	}	

}


function refreshPage()
{
	var fld = document.getElementsByName("selectedValue");
	if (fld != null)
	{	
		for (x=0; x<fld.length; x++)
		{
			fld[x].checked = false
		}
	}
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleSuspiciousMembersSelection" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center">
				<bean:message key="prompt.suspiciousMembers" /> - Connection Selection
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Member to Correct/Remove Flag then Click corresponding button to begin resolving issue.</li>
					<li>Click Merge/Replace button to begin resolving corresponding issue.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
<%-- Begin Pagination Header Tag --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="10" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
   <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- <logic:equal name="suspiciousMemberForm" property="searchTypeId" value="FAM" > --%>
<%-- BEGIN FAMILY MEMBER INFORMATION TABLE --%>
		<table width='98%' align="center" border='0' cellpadding='2' cellspacing='1' bgcolor='#cccccc'>		
			<tr class='bodyText'>
				<td class='formDeLabel' colspan='6'><bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /></td>
			</tr>
			<logic:iterate id="fmInfo" name="suspiciousMemberForm" property="familyMemberInfoList" >
				<tr>
					<td class='headerLabel' valign="bottom" width="1%" nowrap="nowrap">
						<bean:message key="prompt.member" /> #
					</td>
					<td class='headerData'><a href="javascript:newCustomWindow('/<msp:webapp/>displaySuspiciousMemberDetails.do?submitAction=Link&memberID=<bean:write name="fmInfo" property="memberId" />', 'winName',600,525);"><bean:write name='fmInfo' property='memberId' /></a></td>
					<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.member" /> <bean:message key="prompt.name" /></td>
					<td class='headerData'><bean:write name="fmInfo" property="fullNamelfm" /></td>
					<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dob" /></td>
					<td class='headerData'><bean:write name="fmInfo" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
					<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.SSN" /></td>
					<td class='headerData'><bean:write name="fmInfo" property="completeSSN" /></td>
				</tr>	
				<tr>
					<td class='headerLabel'><bean:message key="prompt.gender" /></td>
					<td class='headerData'><bean:write name="fmInfo" property="sexDesc" /></td>
					<td class='headerLabel'><bean:message key="prompt.ethnicity" /></td>
					<td class='headerData'><bean:write name="fmInfo" property="ethnicityDesc" /></td>
					<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.nationality" /></td>
					<td class='headerData' colspan="3"><bean:write name="fmInfo" property="nationalityId" /></td>
				</tr>
			</logic:iterate>			
		</table>
<%-- END FAMILY MEMBER INFORMATION TABLE  --%>
<%--	</logic:equal>  --%>
<%--	<logic:equal name="suspiciousMemberForm" property="searchTypeId" value="JUV" >
<%-- BEGIN JUVENILE PROFILE INFORMATION TABLE
		<table width='98%' border='0' cellpadding='2' cellspacing='1' bgcolor='#cccccc'>		
			<tr class='bodyText'>
				<td class='formDeLabel' colspan='6'><bean:message key="prompt.profile" /> <bean:message key="prompt.info" /></td>
			</tr>
			<tr>
				<td class='headerLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile#" /></td>
				<td class='headerData' width="20%"><bean:write name="suspiciousMemberForm" property="juvenileNumber" /></td>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.juvenileName" /></td>
				<td class='headerData' width="30%"><bean:write name="suspiciousMemberForm" property="juvenileName" /></td>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
				<td class='headerData' width="20%"><bean:write name="suspiciousMemberForm" property="dateOfBirth" /></td>
			</tr>	
			<tr>
				<td class='headerLabel'><bean:message key="prompt.race" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="raceLit" /></td>
				<td class='headerLabel'><bean:message key="prompt.gender" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="genderLit" /></td>
				<td class='headerLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.ethnicity" /></td>
				<td class='headerData'><bean:write name="suspiciousMemberForm" property="ethnicityLit" /></td>
			</tr>			
		</table>
<%-- END JUVENILE PROFILE INFORMATION TABLE --%>
<%--	</logic:equal>	--%>
	<div class="spacer4px"></div>
<%-- BEGIN ASSOCIATED JUVENILES LIST TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
			<td class="detailHead" colspan="2"><bean:message key="prompt.associatedJuvenilesList" /></td>
		</tr>
		<tr>
			<td align="center"> 
				<table width="100%" cellpadding="2" cellspacing="1">
					<logic:empty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
						<tr>
							<td align="left">No Associated Juveniles found.</td>
						</tr>
					</logic:empty> 
					<logic:notEmpty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
						<tr class="formDeLabel">
							<td align="left" class="subHead"><bean:message key="prompt.juvenile#" /></td>
							<td align="left" class="subHead"><bean:message key="prompt.juvenileName" /></td>
							<td align="left" class="subHead"><bean:message key="prompt.race" /></td>
							<td align="left" class="subHead"><bean:message key="prompt.dateOfBirth" /></td>
							<td align="left" class="subHead"><bean:message key="prompt.ethnicity" /></td>
							<td align="left" class="subHead"><bean:message key="prompt.gender" /></td>
						</tr>
						<logic:iterate id="juvList" name="suspiciousMemberForm" property="associatedJuvenilesList" indexId="index1">
							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td align="left"><a href="/<msp:webapp/>displayFamilyConstellationList.do?juvnum=<bean:write name="juvList" property="juvId" />">
									<bean:write name="juvList" property="juvId" />
									</a>
								</td>
								<td align="left"><bean:write name="juvList" property="juvName" /></td>
								<td align="left"><bean:write name="juvList" property="raceDesc" /></td>
								<td align="left"><bean:write name="juvList" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
								<td align="left"><bean:write name="juvList" property="ethnicityDesc" /></td>
								<td align="left"><bean:write name="juvList" property="sexDesc" /></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>	
				</table>
			</td>
		</tr>	
	</table>  
<%-- END ASSOCIATED JUVENILES LIST TABLE --%>		
	<div class="spacer4px"></div>
<%-- BEGIN MATCHING MEMBERS TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
			<td class="detailHead" colspan="2">Matching Suspicious Member</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.familyMember"/> ID</td>
		</tr>
		<tr>
		<td class="formDe"><html:text property="searchMemberBId" styleId="searchFamId1"  size="10" maxlength="15" /></td>
		</tr>	
	</table>	
<%-- END MATCHING MEMBERS TABLE --%>
<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="10" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
	<div class="spacer4px"></div>	
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="nxtButton" onclick="return validateSearch(this.form) && disableSubmit(this, this.form);">
					<bean:message key="button.next" />
				</html:submit>
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="backButton">
					<bean:message key="button.back" />
				</html:submit>
				<input type="button" name="refreshIt" value="Refresh" onclick="refreshPage();"/>
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