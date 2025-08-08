<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	10/04/2011	Create JSP --%>
<%-- DGibler	01/13/2012	Removed Relationship and Juvenile # --%>
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
<title><bean:message key="title.heading" /> - suspiciousMembersMergeSelect.jsp</title>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="casefileSearchForm" />
<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript'>
var webApp = /<msp:webapp/>;
function validateSelect()
{
	var fld = doucument.getElementsByName("selectedMemberId");
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
function checkSelects() {
	var fromSel = document.getElementsByName("selectedFromId");
	var intoSel = document.getElementsByName("selectedToId");
	var fromId = document.getElementsByName("fmId");
	var toId = document.getElementsByName("tmId");
	var fromFound = -1;
	var intoFound = -1;
	for (x=0; x<fromSel.length; x++)
	{
		if (fromSel[x].checked)
		{
			fromFound = x;
			fromId.value = fromSel[x].value;
			for (y=0; y<intoSel.length; y++)
			{
				if (intoSel[y].checked)
				{
					intoFound = y;
					toId.value = intoSel[y].value;
					break;
				}
			}	
		}
	}
	if (intoFound > -1 && fromFound > -1)
	{
		var pb = document.getElementById("printButton");
		if (fromFound != intoFound )
		{
			pb.disabled = false;
			alert("Please click the 'Print Merger' for verification.");
			pb.focus();
		} else {
			pb.disabled = true;
			alert("Merge From and Merge Into selections can not be same Family Member.");
		}
	} 	
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
function printMerger()
{
	var selFrom = document.getElementsByName("selectedFromId");
	var selTo = document.getElementsByName("selectedToId");
	var fm = document.getElementById("fmId");
	var tm = document.getElementById("tmId");
	for (x=0; x< selFrom.length; x++)
	{
		if (selFrom[x].checked == true)
		{
			fm.value = selFrom[x].value;
		}
		if (selTo[x].checked == true)
		{
			tm.value = selTo[x].value;
		}
		selFrom[x].disabled = true;
		selTo[x].disabled = true;
	}
	document.getElementById("nextButton").disabled = false;
	
	return true;
}

function setSelectedState()
{
	var st = document.getElementById("actionId").value;
//alert(st);	
	if (st != "")
	{
		var selFrom = document.getElementsByName("selectedFromId");
		var selTo = document.getElementsByName("selectedToId");
		for (x=0; x< selFrom.length; x++)
		{
			selFrom[x].disabled = true;
			selTo[x].disabled = true;
		}
		document.getElementById("printButton").disabled = false;
		document.getElementById("nextButton").disabled = false;
	}	
}

function nextSelected()
{
	var st = document.getElementById("actionId");
	st.value = "summary";
}
function resetPage()
{
	var selFrom = document.getElementsByName("selectedFromId");
	var selTo = document.getElementsByName("selectedToId");
	for (x=0; x< selFrom.length; x++)
	{
		selFrom[x].disabled = false;
		selFrom[x].checked = false;
		selTo[x].disabled = false;
		selTo[x].checked = false;
	}
	document.getElementById("printButton").disabled = true;
	document.getElementById("nextButton").disabled = true;
}
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="setSelectedState();">
<html:form action="/displaySuspiciousMembersMergeSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<input type="hidden" name="selFromId" value="" id="fmId">
<input type="hidden" name="selToId" value="" id="tmId">
<input type="hidden" name="actionState" value="<bean:write name="suspiciousMemberForm" property="secondaryAction" />"  id="actionId">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center">
				<bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" /> Merge
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select a "Merge From" and a "Merge Into".</li>
					<li>Click Print Merger.</li>
					<li>Click Next.</li>					
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
<!-- BEGIN ASSOCIATED JUVENILES LIST TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
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
<%-- BEGIN FAMILY MEMBER SELECT TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr bgcolor='#f0f0f0'>
			<td class="detailHead" colspan="2"><bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /></td>
		</tr>
		<tr>
			<td align="center"> 
				<table width="100%" cellpadding="2" cellspacing="1">  
					<tr bgcolor="#cccccc">
						<td align="center" class="subHead" width="1%">Merge From</td>
						<td align="center" class="subHead" width="1%">Merge Into</td>
						<td align="left" class="subHead" nowrap="nowrap"><bean:message key="prompt.member" /> #</td>
						<td align="left" class="subHead"><bean:message key="prompt.name" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.gender" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.nationality" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.ethnicity" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.dob" /></td>
						<td align="left" class="subHead"><bean:message key="prompt.SSN" /></td>
					</tr>  
					<logic:iterate id="matchList" name="suspiciousMemberForm" property="mergeMembersList"  indexId="index2">
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td align="center"><input type="radio" name="selectedFromId" value="<bean:write name="matchList" property="memberNum" />" onclick="checkSelects()" ></td>
							<td align="center"><input type="radio" name="selectedToId" value="<bean:write name="matchList" property="memberNum" />" onclick="checkSelects()" ></td>
							<td><a href="javascript:newCustomWindow('/<msp:webapp/>displaySuspiciousMemberDetails.do?submitAction=Link&memberID=<bean:write name="matchList" property="memberNum" />', 'winName',600,525);"><bean:write name='matchList' property='memberNum' /></a></td>
							<td><bean:write name="matchList" property="lastName" />, <bean:write name="matchList" property="firstName" /></td>
							<td><bean:write name="matchList" property="sex" /></td>
							<td><bean:write name="matchList" property="nationalityDesc" /></td>
							<td><bean:write name="matchList" property="ethnicityDesc" /></td>
							<td><bean:write name="matchList" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
							<td nowrap="nowrap"><bean:write name="matchList" property="formattedSSN" /></td>
						</tr>
					</logic:iterate>
				</table>
			</td>
		</tr>	
	</table>	
<%-- END FAMILY MEMBER SELECT TABLE --%> 
	<div class="spacer4px"></div>	
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="backeButton">
					<bean:message key="button.back" />
				</html:submit>
				<html:submit property="submitAction" styleId="printButton" onclick="return printMerger()" disabled="true">
					<bean:message key="button.printMergerBFO" />
				</html:submit> 
		<%--		<input type="button" value="<bean:message key="button.printMergerBFO" />" onclick="return printMerger()" id="printButton" disabled="true" >  --%>
				<html:submit property="submitAction" styleId="nextButton" onclick="return nextSelected()" disabled="true">
					<bean:message key="button.next" />
				</html:submit>
				<input type="button" name="resetIt" value="Reset" onclick="resetPage()"/>
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