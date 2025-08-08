<!DOCTYPE HTML>

<%-- Modified version of familyMemberCreate.jsp --%>
<%--MODIFICATIONS --%>
<%-- 10/05/2011	 CShimek	Create JSP --%>
<%-- 05/17/2012	 CShimek	73509 added member number display to right of member name --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - suspiciousMemberDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body topmargin="0" leftmargin="0">
<html:form action="/displaySuspiciousMemberDetails">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|00">
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" /> <bean:message key="prompt.details" />
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
<%-- BEGIN INSTRUCTIONS TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the <b>Close Window</b> button to close this window.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTIONS TABLE --%>
<div class='spacer'></div>
<%-- BEGIN FAMILY INFO INPUT TABLE --%>
<table width="98%" align="center" border="0" cellpadding="2"  cellspacing="1" class="borderTableBlue">
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.name" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="memberName" /></td>
		<td class="formDeLabel"><bean:message key="prompt.member" /> #</td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="memberNumber"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.socialSecurity#" /></td>
		<td class="formDe"><bean:write name="suspiciousMemberForm" property="memberSSN"  formatKey="ssn.format"/></td>
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
		<td class="formDe" colspan="3"><bean:write name="suspiciousMemberForm" property="usCitizenId" /></td>
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
		<td class="formDeLabel" width="1%"><bean:message key="prompt.isFamilyMemberDeceased" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="deceasedLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.causeOfDeath" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="causeOfDeathLit" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.isFamilyMemberIncarcerated" /></td>
		<td class="formDe" colspan='3'><bean:write name="suspiciousMemberForm" property="incarcetatedLit" /></td>
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
	<tr class="detailHead">
		<td width='1%' class=detailHead><a href="javascript:showHideMulti('dlInfo', 'dlTable', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/contract.gif" name="dlInfo"></a></td>
		<td class="detailHead"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
	</tr>
	<tr id="dlTable0" class='visibleTR' >	
		<td colspan="2">
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.number" /></td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="dlNumber" /></td>
					<td class="formDeLabel"><bean:message key="prompt.state" /></td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="dlStateLit" /></td>
				</tr>
				<tr>	
					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
					<td class='formDe'><bean:write name="suspiciousMemberForm" property="dlExpDateStr" /></td>
					<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.class" /></td>
					<td class='formDe'><bean:write name="suspiciousMemberForm" property="dlClassId" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap='nowrap' width="1%">
						<bean:message key="prompt.state" /> <bean:message key="prompt.issued" /> <bean:message key="prompt.idCard#" />
					</td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="stateIssuedCardNumber"/></td>
					<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.state" /> </td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="stateIssuedCardStateLit" /></td>
				</tr>
			</table>
		</td>
	</tr>			
</table>
<%-- END DRIVER LICENSE/ID CARD INFORMATION --%>
 	<div class='spacer4px'></div>
<%-- BEGIN ASSOCIATED JUVENILE LIST TABLE --%> 	
<table width='98%' align="center" border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">
	<tr>
		<td width='1%' class=detailHead><a href="javascript:showHideMulti('assocInfo', 'assocTable', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/contract.gif" name="assocInfo"></a></td>
		<td class='detailHead'><bean:message key="prompt.associatedJuvenilesList" /></td>
	</tr>
	<tr id="assocTable0" class='visibleTR'>	
		<td colspan="2">
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.juvenile#" /></td>
					<td class="formDeLabel"><bean:message key="prompt.family#" /></td>
					<td class="formDeLabel"><bean:message key="prompt.juvenileName" /></td>
					<td class="formDeLabel"><bean:message key="prompt.relationship" /></td>
					<td class="formDeLabel"><bean:message key="prompt.guardian" /></td>
				</tr>
				<logic:empty name="suspiciousMemberForm" property="associatedJuvenilesList">
					<tr> 
						<td class="formDe" colspan="5">No Associated Juveniles </td> 
					</tr> 
				</logic:empty>
				<logic:iterate id="ajList" name="suspiciousMemberForm" property="associatedJuvenilesList" indexId="index1">
					<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td><bean:write name="ajList" property="juvId" /></td>
						<td><bean:write name="ajList" property="constellationId" /></td>
						<td><bean:write name="ajList" property="juvName" /></td>
						<td><bean:write name="ajList" property="relationType" /></td>
						<td>
							<logic:equal name="ajList" property="guardian" value="true">
           						YES
                			</logic:equal>
                			<logic:notEqual name="ajList" property="guardian" value="true">
                				NO
                			</logic:notEqual>
                		</td>
					</tr>
				</logic:iterate>
			</table>
		</td>
	</tr>			
</table>
<%-- END ASSOCIATED JUVENILE LIST TABLE --%> 				
 	<div class='spacer4px'></div>
<%-- BEGIN MARITAL STATUS TABLE --%>
<table width='98%' align="center"  border="0" cellpadding="0" cellspacing="1" class="borderTableBlue">
	<tr>
		<td width='1%' class=detailHead><a href="javascript:showHideMulti('maritalInfo', 'maritalTable', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/contract.gif" name="maritalInfo"></a></td>
		<td class='detailHead'><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
	</tr>
	<tr id="maritalTable0" class='visibleTR'>	
		<td colspan="2">
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
					<td class='formDeLabel'><bean:message key="prompt.relationship"/> With</td>
					<td class="formDeLabel"><bean:message key="prompt.marriage" /><bean:message key="prompt.date" /></td>
					<td class="formDeLabel"><bean:message key="prompt.divorce" /><bean:message key="prompt.date" /></td>
					<td class="formDeLabel"><bean:message key="prompt.#OfChildren" /></td>
				</tr>
				<logic:empty name="suspiciousMemberForm" property="maritalStatusDetailsList">
					<tr> 
						<td class="formDe" colspan="5">No Marital Status History </td> 
					</tr> 
				</logic:empty>
				<logic:iterate id="msList" name="suspiciousMemberForm" property="maritalStatusDetailsList" indexId="index2">
					<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td><bean:write name="msList" property="maritalStatus" /></td>
						<td>
							<logic:notEmpty name="msList" property="relatedFamMemId">
          						<logic:notEqual name="msList" property="relatedFamMemId" value=""> 
          							<bean:write name="msList" property="relatedFamMemId"/> - <bean:write name="msList" property="relatedFamMemName.formattedName"/>
          						</logic:notEqual>
          					</logic:notEmpty>
							&nbsp;
						</td>
						<td><bean:write name="msList" property="marriageDate"/></td>
						<td><bean:write name="msList" property="divorceDate"/> </td>
						<td><bean:write name="msList" property="numOfChildren"/></td>
					</tr>
				</logic:iterate>
			</table>
		</td>
	</tr>			
</table>
<%-- END MARITAL STATUS TABLE --%>
	<div class='spacer4px'></div>
<%-- BEGIN ADDRESS TABLE --%>
<table width='98%' align="center"  border="0" cellpadding="0" cellspacing="1" class="borderTableBlue">
	<tr>
		<td width='1%' class=detailHead><a href="javascript:showHideMulti('addressInfo', 'addressTable', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/contract.gif" name="addressInfo"></a></td>
		<td class='detailHead'><bean:message key="prompt.address" /></td>
	</tr>
	<tr id="addressTable0" class='visibleTR' >	
		<td colspan="2">
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td width="20%" class="formDeLabel"><bean:message key="prompt.address" /></td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="address" /></td>
				</tr>
				
			</table>
		</td>
	</tr>			
</table>
<%-- END ADDRESS TABLE --%>
<div class='spacer4px'></div>
<%-- BEGIN CONTACT TABLE --%>
<table width='98%' align="center"  border="0" cellpadding="0" cellspacing="1" class="borderTableBlue">
	<tr>
		<td width='1%' class=detailHead><a href="javascript:showHideMulti('contactInfo', 'contactTable', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/contract.gif" name="contactInfo"></a></td>
		<td class='detailHead'><bean:message key="prompt.contactInfo" /></td>
	</tr>
	<tr id="contactTable0" class='visibleTR' >	
		<td colspan="2">
			<table width="100%" cellpadding="1" cellspacing="1">
				<tr>
					<td width="20%" class="formDeLabel"><bean:message key="prompt.phone" /></td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="phone" />&nbsp;</td>
				</tr>
				<tr>
					<td width="20%" class="formDeLabel"><bean:message key="prompt.email" /></td>
					<td class="formDe"><bean:write name="suspiciousMemberForm" property="email" />&nbsp;</td>
				</tr>
				
			</table>
		</td>
	</tr>			
</table>
<%-- END CONTACT TABLE --%>				
	<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr id="closeOnly">
		<td align="center">
			<input type="button" name="closeMe" value="Close Window" onClick="window.close();">
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>