<!DOCTYPE HTML>
<!-- User enters details for creating new JIMS2 user account -->
<!-- 12/06/2005 - Uma Gopinath - Create JSP -->
<%-- 06/19/2007   CShimek   #42954 revise app.js for new build.xml --%>
<%-- 08/10/2007   CShimek   #44352 added JIMS2 Heading --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base/>

<title><bean:message key="title.heading"/> - loginOfficerCreateSummary.jsp</title>
<script type="text/JavaScript" src="/appshell/js/app.js"></script>
<script language="javascript">
function disableSubmit(button, form)
{
	var hasURLExtensions=-1;
	var myAction=form.action;
	
	hasURLExtensions=myAction.indexOf("?");
	
	var btnVal=button.value;
	var hasAmpersendInValue;
	
	hasAmpersendInValue=btnVal.indexOf("&");
	
	if(hasAmpersendInValue!=-1){
		btnVal=btnVal.replace(/&/,"%26");
	}
	
	if(hasURLExtensions!=-1){
		form.action += "&"+button.name+"="+btnVal;
	} else {
		form.action += "?"+button.name+"="+btnVal;
	}
	
	form.submit();
	button.disabled=true;
	return false;
}
</script>
<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>

</head> 

<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body topmargin=0 leftmargin="0" onKeyDown="checkEnterKey(event,true)">
<html:form action="/submitCreateOfficerProfileAction">
	<logic:equal name="loginForm" property="action" value="confirm">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|262">
	</logic:equal>
	<logic:equal name="loginForm" property="action" value="summary">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|244">
	</logic:equal>
<!-- BEGIN JIMS2 HEADING TABLES -->
	<tiles:insert page="jims2HeadingTile.jsp" flush="true"></tiles:insert>
<!-- END JIMS2 HEADING TABLES -->	
<!-- BEGIN HEADING TABLE -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<logic:equal name="loginForm" property="action" value="confirm">
				<td align="center" class="header"><bean:message key="title.createOfficerProfile"/> <bean:message key="prompt.account"/> <bean:message key="title.confirmation"/></td>
			</logic:equal>
			<logic:equal name="loginForm" property="action" value="summary">
				<td align="center" class="header"><bean:message key="title.createOfficerProfile"/> <bean:message key="prompt.account"/> <bean:message key="title.summary"/></td>
			</logic:equal>
		</tr>
	</TBODY>
</table>
<!-- END HEADING TABLE -->	
<br>
<!-- begin instruction table --> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul>
				<logic:equal name="loginForm" property="action" value="summary">
					<li>Verify that information is correct and select Finish button to create new JIMS2 account.</li> 
				</logic:equal>
			</ul>
			<ul>
				<logic:equal name="loginForm" property="action" value="confirm">
					<td class=confirm> The following Officer Profile has been successfully created</td>
				</logic:equal>      
			</ul>
		</td> 
	</tr> 
</table> 
<!-- END INSTRUCTIN TABLE --> 
<!-- BEGIN DETAIL TABLE --> 
<table width="98%" border="0" cellpadding="2" cellspacing="1" align=center class="borderTableBlue"> 
	<tr> 
		<td class="detailHead" colspan="2" valign="top"><bean:message key="prompt.officer"/> <bean:message key="prompt.info"/></td> 
	</tr>        
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.badgeNumber"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="badgeNumber"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.other"/> <bean:message key="prompt.id"/> <bean:message key="prompt.number"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="otherIdNumber"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.lawEnforcementAgency"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="agencyName"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.department"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="departmentName"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.name"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="userName"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.workPhone"/></td> 
		<td class="formDe">
			<bean:write name="loginForm" property="userWorkPhoneNumber"/>
			<bean:message key="prompt.ext"/>-<bean:write name="loginForm" property="userWorkPhoneNumber.ext"/>
		</td>
	</tr>
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="userCellPhoneNumber"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel" nowrap width="15%"><bean:message key="prompt.pager"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="userPagerNumber"/></td>
	</tr>  
	<tr> 
		<td class="formDeLabel" nowrap width="15%"><bean:message key="prompt.email"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="userEmail"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel" nowrap width=15%><bean:message key="prompt.JIMS2UserId"/> </td> 
		<td class="formDe"><bean:write name="loginForm" property="jims2UserId"/></td> 
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.JIMS2Password"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="displayPassword"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.forgottenPasswdPhrase"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="forgottenPasswdPhrase"/></td>
	</tr> 
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.answer"/></td> 
		<td class="formDe"><bean:write name="loginForm" property="answer"/></td>
	</tr> 
</table> 
<!-- END DETAIL TABLE --> 
<br> 
<!-- BEGIN BUTTON TABLE --> 
<table border="0" width="100%"> 
	<tr> 
		<td align="center"> 
			<logic:equal name="loginForm" property="action" value="confirm">
				<html:submit property="submitAction"><bean:message key="button.backToLogin"/></html:submit>	
			</logic:equal>
			<logic:equal name="loginForm" property="action" value="summary">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>     
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.finish"/>
				</html:submit>	
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>	
			</logic:equal>
		</td>
	</tr> 
</table> 
<!-- END BUTTON TABLE --> 
</html:form>
</body>
</html:html>