<!DOCTYPE HTML>
<!-- 12/12/2006	 C Shimek   - Create JSP -->
<!-- 02/05/2009  C Shimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->  
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - jims2AccountDetails.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayJIMS2AccountSummary" target="content" > 
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|137">
<!-- BEGIN HEADING TABLE --> 
<table width="100%">
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>&nbsp;<bean:message key="prompt.details"/>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE --> 
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->	
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
      <td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%"  align="center">
			<tr class="detailHead">
				<td class="detailHead"><bean:message key="prompt.JIMS2"/> <bean:message key="prompt.account"/> <bean:message key="prompt.information"/></td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%"  align="center">
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
							<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="jimsLogonId"/></td>
						</tr>	
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.userName"/></td>
							<td class="formDe" colspan="3">
							<bean:write name="jims2AccountForm" property="lastName"/>,&nbsp;<bean:write name="jims2AccountForm" property="firstName"/>&nbsp;<bean:write name="jims2AccountForm" property="middleName"/> 
<%-- 								<bean:write name="jims2AccountForm" property="userName"/> --%>
							</td>
						</tr>
						<logic:equal name="jims2AccountForm" property="userType" value="S">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.serviceProviderName"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="serviceProviderName"/></td>
							</tr>	
						</logic:equal>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
							<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="departmentName"/></td>
						</tr>							
						<logic:equal name="jims2AccountForm" property="userType" value="L">
							<tr>
								<td class="formDeLabel" ><bean:message key="prompt.badgeNumber"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="badgeNumber"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" ><bean:message key="prompt.otherIdNumber"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="otherIdNumber"/></td>
							</tr>	
						</logic:equal>
						<logic:equal name="jims2AccountForm" property="userType" value="S">
							<tr>
								<td class="formDeLabel" ><bean:message key="prompt.employeeId"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="employeeId"/></td>
							</tr>	
						</logic:equal>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS2UserId"/></td>
							<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="jims2LogonId"/></td>
						</tr>	
						<logic:notEqual name="jims2AccountForm" property="userType" value="N">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.password"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="jims2Password"/></td>
							</tr>
							<tr>		
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/> <bean:message key="prompt.question"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="passwordQuestion"/></td>
							</tr>
							<tr>		
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/> <bean:message key="prompt.answer"/></td>
								<td class="formDe" colspan="3"><bean:write name="jims2AccountForm" property="passwordAnswer"/></td>
							</tr>
						</logic:notEqual>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.status"/></td>
							<td class="formDe" colspan="3">
							<logic:equal name="jims2AccountForm" property="accountStatus" value="A">
								ACTIVE
							</logic:equal>	
							<logic:equal name="jims2AccountForm" property="accountStatus" value="I">
								INACTIVE
							</logic:equal>	
							<logic:notEqual name="jims2AccountForm" property="accountStatus" value="A">
								<logic:notEqual name="jims2AccountForm" property="accountStatus" value="I">
								UNKNOWN
								</logic:notEqual>	
							</logic:notEqual>	
							</td>
						</tr>	
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.activationDate"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="activateDate" formatKey="date.format.mmddyyyy" /></td>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.by"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="activatedBy"/></td>							
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.inactivationDate"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="inactivateDate" formatKey="date.format.mmddyyyy" /></td>
							<td class="formDeLabel"><bean:message key="prompt.by"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="inactivatedBy"/></td>								
						</tr>	
					</table>			
				</td>
			</tr>
        </table>
        </td>
    </tr> 
</table>		
<br>
<!--BEGIN BUTTON TABLE--> 
<table width="98%" align="center"> 
	<tr> 
		<td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>
			<logic:equal name="jims2AccountForm" property="accountStatus" value="A">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>								
				<logic:equal  name="jims2AccountForm" property="showInactive" value="Y">
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.inactivate"/></html:submit>
				</logic:equal>
			</logic:equal>
		</td> 
	</tr> 
</table> 
<!--END BUTTON TABLE-->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
