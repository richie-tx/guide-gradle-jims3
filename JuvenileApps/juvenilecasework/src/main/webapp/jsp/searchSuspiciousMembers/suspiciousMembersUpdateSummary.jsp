<!DOCTYPE HTML>

<%-- Modified version of familyMemberCreate.jsp --%>
<%--MODIFICATIONS --%>
<%-- 10/05/2011	 CShimek	Create JSP --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading" /> - suspiciousMembersUpdate.jsp</title>
</head>
<%--END HEADER TAG--%>


<%--BEGIN BODY TAG--%>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/submitSuspiciousMembersUpdate">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|00">
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" /> <bean:message key="prompt.update" />
			<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
				Summary
			</logic:equal> 
			<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="confirm">
				Confirmation
			</logic:equal> 
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="100%">
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
<div class='spacer'></div>
<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
<%-- BEGIN INSTUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
  		<ul>
  			<li>Verify all fields are correct then click Finish to complete update.</li>
   			<li>Select Back button to return to the previous page. </li>
  		</ul>
		</td>
	</tr>
</table>
<%-- END INSTUCTION TABLE --%>
</logic:equal>
<div class='spacer'></div>
<%-- BEGIN FAMILY INFO INPUT TABLE --%>
<table width="98%" align="center" border="0" cellpadding="2"  cellspacing="1" class="borderTableBlue">
	<tr>
		<td colspan="4" valign="top" class="detailHead">
			<bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /> 
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.name" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="memberName" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.socialSecurity#" /></td>
		<td class="formDe" width="45%"><bean:write name="suspiciousMemberForm" property="memberSSN" /></td>
		<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="memberDOB" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="sexLit" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.stateidSID" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="sidNum" /></td>
		<td class="formDeLabel"><bean:message key="prompt.alienReg#" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="alienRegNumber" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.usCitizen" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="usCitizenLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.nationality" /></td>		
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="nationalityLit" /></td>
	</tr>
	<tr>		
		<td class="formDeLabel"><bean:message key="prompt.ethnicity" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="ethnicityLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.primaryLanguage" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="primaryLanguageLit" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.secondaryLanguage" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="secondaryLanguageLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"  width="1%" nowrap='nowrap'><bean:message key="prompt.isFamilyMemberDeceased" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="deceasedLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.causeOfDeath" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="causeOfDeathLit" /></td>
	</tr>
<%-- 	<tr id="juvAge">
		<td class="formDeLabel"><bean:message key="prompt.juvenileAgeAtFamilyMemberDeath" /></td>
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="juvenileAgeAtDeath" /></td>
	</tr>  --%>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.isFamilyMemberIncarcerated" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="incarcetatedLit"  /></td>
	</tr>
	<tr>
		<td class="formDeLabel" colspan="4" valign="top"><bean:message key="prompt.family" /> <bean:message key="prompt.member" /> <bean:message key="prompt.comments" /></td>
	</tr>
	<tr>
		<td class="formDe" colspan="4"><bean:write name="suspiciousMemberForm" property="comments" /></td>
	</tr>
</table>
<%-- END FAMILY INFO INPUT TABLE --%>			
	<div class='spacer4px'></div>
<%-- BEGIN DRIVER LICENSE/ID CARD INFORMATION --%>			
<table width="98%" align="center" border="0" cellpadding="2"  cellspacing="1" class="borderTableBlue">
	<tr>
		<td colspan='4' class="detailHead"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.number" /></td>
		<td class="formDe" width="45%"><bean:write name="suspiciousMemberForm" property="dlNumber" /></td>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.state" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="dlStateLit" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
		<td class='formDe'><bean:write name="suspiciousMemberForm" property="dlExpDateStr" /></td>
		<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.class" /></td>
		<td class='formDe'><bean:write name="suspiciousMemberForm" property="dlClassIdLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap='nowrap' width="1%">
			<bean:message key="prompt.state" /> <bean:message key="prompt.issued" /> <bean:message key="prompt.idCard#" />
		</td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="stateIssuedCardNumber"/></td>
		<td class="formDeLabel"><bean:message key="prompt.state" /> </td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="stateIssuedCardStateLit" /></td>
	</tr>
</table>
<%-- END DRIVER LICENSE/ID CARD INFORMATION --%>
 	<div class='spacer4px'></div>
<%-- BEGIN ASSOCIATED JUVENILE LIST TABLE --%> 	
<table width='98%' align="center" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td class='detailHead' colspan="5"><bean:message key="prompt.associatedJuvenilesList" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenile#" /></td>
		<td class="formDeLabel"><bean:message key="prompt.family#" /></td>
		<td class="formDeLabel"><bean:message key="prompt.juvenileName" /></td>
		<td class="formDeLabel"><bean:message key="prompt.relationship" /></td>
		<td class="formDeLabel"><bean:message key="prompt.gender" /></td>
	</tr>
	<logic:empty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
		<tr>
			<td>No Associated Juveniles found.</td>
		</tr>
	</logic:empty> 
	<logic:notEmpty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
		<logic:iterate id="ajList" name="suspiciousMemberForm" property="associatedJuvenilesList" indexId="index1">
			<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				<td><bean:write name="ajList" property="juvId" /></td>
				<td><bean:write name="ajList" property="famMemberId" /></td>
				<td><bean:write name="ajList" property="juvName" /></td>
				<td><bean:write name="ajList" property="relationType" /></td>
				<td><bean:write name="ajList" property="sexDesc" /></td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
</table>
<%-- END ASSOCIATED JUVENILE LIST TABLE --%> 				
 	<div class='spacer4px'></div>
<%-- END MARITAL STATUS TABLE --%>			
<%--	<div class='spacer'></div> --%>	
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="suspiciousMemberForm" property="secondaryAction" value="summary">
				<html:submit property="submitAction">
					<bean:message key="button.back"></bean:message>
				</html:submit> 
			<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
				<bean:message key="button.finish"></bean:message>
			</html:submit> 
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		</logic:equal>	
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>