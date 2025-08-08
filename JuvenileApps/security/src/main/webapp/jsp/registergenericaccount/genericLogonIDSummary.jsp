<!DOCTYPE HTML>
<!-- 09/22/2006	 CShimek   - Create JSP -->
<!-- 01/11/2007  CShimek   - #38306 add multiple submit functionality  -->
<!-- 02/20/2007  CShimek   - #39739 change generic Logon Id values to register generic account	-->
<!-- 02/05/2009  CShimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - genericLogonIDSummary.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitGenericLogonIDCreateUpdate" target="content">
<logic:equal name="genericLogonIdForm" property="action" value="createSummary">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|52">
</logic:equal>	
<logic:equal name="genericLogonIdForm" property="action" value="createConfirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|51">
</logic:equal>	
<logic:equal name="genericLogonIdForm" property="action" value="inactivateSummary">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|277">
</logic:equal>	
<logic:equal name="genericLogonIdForm" property="action" value="inactivateConfirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|278">
</logic:equal>	
<logic:equal name="genericLogonIdForm" property="action" value="updateSummary">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|221">
</logic:equal>	
<logic:equal name="genericLogonIdForm" property="action" value="updateConfirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|222">
</logic:equal>	

<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
	<logic:equal name="genericLogonIdForm" property="action" value="createSummary">
		<bean:message key="prompt.register"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.summary"/>
	</logic:equal>	
	<logic:equal name="genericLogonIdForm" property="action" value="createConfirm">
		<bean:message key="prompt.register"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.confirmation"/>
	</logic:equal>	
	<logic:equal name="genericLogonIdForm" property="action" value="inactivateSummary">
		<bean:message key="prompt.inactivate"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.summary"/>
	</logic:equal>	
	<logic:equal name="genericLogonIdForm" property="action" value="inactivateConfirm">
		<bean:message key="prompt.inactivate"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.confirmation"/>
	</logic:equal>	
	<logic:equal name="genericLogonIdForm" property="action" value="updateSummary">
		<bean:message key="prompt.update"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.summary"/>
	</logic:equal>	
	<logic:equal name="genericLogonIdForm" property="action" value="updateConfirm">
		<bean:message key="prompt.update"/> <bean:message key="prompt.genericAccount"/> <bean:message key="prompt.confirmation"/>
	</logic:equal>
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
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<logic:equal name="genericLogonIdForm" property="action" value="createSummary">
			<td><ul><li>Select Finish button to register this generic account.</li></ul></td>
		</logic:equal>	
		<logic:equal name="genericLogonIdForm" property="action" value="createConfirm">
			<td align="center" class="confirm">This generic account has been successfully registered.</td>
		</logic:equal>	
		<logic:equal name="genericLogonIdForm" property="action" value="inactivateSummary">
			<td><ul><li>Select Inactivate button to inactivate this generic account.</li></ul></td>
		</logic:equal>	
		<logic:equal name="genericLogonIdForm" property="action" value="inactivateConfirm">
			<td align="center" class="confirm">This generic account has been successfully inactivated.</td>
		</logic:equal>	
		<logic:equal name="genericLogonIdForm" property="action" value="updateSummary">
			<td><ul><li>Select Finish button to update this generic account.</li></ul></td>
		</logic:equal>	
		<logic:equal name="genericLogonIdForm" property="action" value="updateConfirm">
			<td align="center" class="confirm">This generic account has been successfully updated.</td>
		</logic:equal>			
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
      <td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
			<tr class="detailHead">
				<td class="detailHead"><bean:message key="prompt.genericAccount"/> <bean:message key="prompt.information"/></td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS"/>2 <bean:message key="prompt.userId"/></td>
							<td class="formDe"><bean:write name="genericLogonIdForm" property="logonId" /></td>
						</tr>	
						<logic:equal name="genericLogonIdForm" property="action" value="inactivateSummary">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="currentPassword" /></td>
							</tr>
						</logic:equal>	
						<logic:equal name="genericLogonIdForm" property="action" value="inactivateConfirm">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="currentPassword" /></td>
							</tr>
						</logic:equal>	

						<logic:equal name="genericLogonIdForm" property="action" value="updateSummary">
							<tr>
								<td class="formDeLabel" width="1%" nowrap>Old <bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="currentPassword" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.newPassword"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="newPassword" /></td>
							</tr>							
						</logic:equal>	
						<logic:equal name="genericLogonIdForm" property="action" value="updateConfirm">
							<tr>
								<td class="formDeLabel" width="1%" nowrap>Old <bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="currentPassword" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.newPassword"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="newPassword" /></td>
							</tr>							
						</logic:equal>	
						<logic:equal name="genericLogonIdForm" property="action" value="createSummary">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="newPassword" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reEnterPassword"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="reenterPassword" /></td>
							</tr>							
						</logic:equal>	
						<logic:equal name="genericLogonIdForm" property="action" value="createConfirm">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="newPassword" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reEnterPassword"/></td>
								<td class="formDe"><bean:write name="genericLogonIdForm" property="reenterPassword" /></td>
							</tr>							
						</logic:equal>	
					</table>
				</td>
			</tr>
        </table>
        <br>
<!--BEGIN BUTTON TABLE--> 
        <table> 
			<tr> 
				<td>
				<logic:equal name="genericLogonIdForm" property="action" value="createSummary">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>&nbsp;		
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>	
				<logic:equal name="genericLogonIdForm" property="action" value="createConfirm">
					<html:submit property="submitAction"><bean:message key="button.returnToSearch"/></html:submit>
				</logic:equal>	
				<logic:equal name="genericLogonIdForm" property="action" value="inactivateSummary">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>&nbsp;		
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.inactivate"/></html:submit>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>	
				<logic:equal name="genericLogonIdForm" property="action" value="inactivateConfirm">
					<html:submit property="submitAction"><bean:message key="button.returnToSearch"/></html:submit>
				</logic:equal>	
				<logic:equal name="genericLogonIdForm" property="action" value="updateSummary">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>&nbsp;		
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>	
				<logic:equal name="genericLogonIdForm" property="action" value="updateConfirm">
					<html:submit property="submitAction"><bean:message key="button.returnToSearch"/></html:submit>
				</logic:equal>						
				</td> 
			</tr> 
        </table>
<!--END BUTTON TABLE-->
        </td>
    </tr> 
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
