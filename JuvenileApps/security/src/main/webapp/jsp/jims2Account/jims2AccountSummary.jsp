<!DOCTYPE HTML>
<!-- 12/12/2006	 C Shimek   - Create JSP -->
<!-- 01/09/2007	 C Shimek   - #38252 revised to display logout information -->
<!-- 08/21/2007  C Shimek	- #44349 removed "click OK' confirmation message added by mistake -->
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
<title><bean:message key="title.heading" /> - jims2AccountSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitJIMS2AccountCreateUpdate" target="content" >
<!-- BEGIN HELPFILE VALUES --> 
	<logic:notEqual name="jims2AccountForm" property="pageType" value="confirm">
		<logic:equal name="jims2AccountForm" property="action" value="inactivate">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|125">
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="update">	
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|224">
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="create">	
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|280">
		</logic:equal>	
	</logic:notEqual>
	<logic:equal name="jims2AccountForm" property="pageType" value="confirm">
		<logic:equal name="jims2AccountForm" property="action" value="inactivate">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|124">
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="update">	
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|223">
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="create">	
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|281">
		</logic:equal>	
	</logic:equal>	
<!-- END HELPFILE VALUES --> 	
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
	    <bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>
		<logic:equal name="jims2AccountForm" property="action" value="inactivate">
			<bean:message key="prompt.inactivate"/>
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="update">	
			<bean:message key="prompt.update"/>
		</logic:equal>	
		<logic:equal name="jims2AccountForm" property="action" value="create">	
			<bean:message key="prompt.create"/>
		</logic:equal>	
		<logic:notEqual name="jims2AccountForm" property="pageType" value="confirm">
			<bean:message key="prompt.summary"/>
		</logic:notEqual>
		<logic:equal name="jims2AccountForm" property="pageType" value="confirm">
			<bean:message key="prompt.confirmation"/>
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
<!-- BEGIN CONFIRMATION TABLE -->
<table align="center" width="98%" border="0" align="center">
	
	<logic:notEqual name="jims2AccountForm" property="pageType" value="confirm">
	  <tr>
		<td>
			<ul>
			<logic:equal name="jims2AccountForm" property="action" value="inactivate">
				<li>Select Inactivate button to Inactivate this JIMS2 Account.</li>
			</logic:equal>	
			<logic:equal name="jims2AccountForm" property="action" value="update">	
				<li>Select Finish button to update this JIMS2 Account.</li>
			</logic:equal>	
			<logic:equal name="jims2AccountForm" property="action" value="create">	
				<li>Select Finish button to create this JIMS2 Account.</li>
			</logic:equal>	
			</ul>
		</td>
	  </tr>	
	</logic:notEqual>
	<logic:equal name="jims2AccountForm" property="pageType" value="confirm">
	  <tr>
		<td align="center" class="confirm">
			<logic:equal name="jims2AccountForm" property="action" value="inactivate">
				This JIMS2 Account has been successfully Inactivated.
			</logic:equal>	
			<logic:equal name="jims2AccountForm" property="action" value="update">	
				This JIMS2 Account has been successfully updated.
			</logic:equal>	
			<logic:equal name="jims2AccountForm" property="action" value="create">	
				This JIMS2 Account has been successfully created.
			</logic:equal>	
		</td>
	  </tr>
	</logic:equal>	
	
	<logic:equal name="jims2AccountForm" property="pageType" value="confirm">
		<logic:equal name="jims2AccountForm" property="currentUserLogonIdChanged" value="Y">
			<tr>
				<td align="center" class="confirm">
					JIMS2 User Id has been changed so you must login again.
				</td>
			</tr>
		</logic:equal>
	</logic:equal>	

</table>
<!-- END CONFIRMATION TABLE --> 

<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
      <td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%" align="center">
			<tr class="detailHead">
				<td class="detailHead">
					<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>&nbsp;<bean:message key="prompt.information"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="jimsLogonId"/></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.userName"/></td>
							<td class="formDe">
								<bean:write name="jims2AccountForm" property="lastName"/>,&nbsp;<bean:write name="jims2AccountForm" property="firstName"/>&nbsp;<bean:write name="jims2AccountForm" property="middleName"/> 
							</td>
						</tr>	
						<logic:equal name="jims2AccountForm" property="userType" value="S">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.serviceProviderName"/></td>
								<td class="formDe"><bean:write name="jims2AccountForm" property="serviceProviderName"/></td>							
							</tr> 
						</logic:equal>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="departmentName"/></td>							
						</tr>
						<logic:equal name="jims2AccountForm" property="action" value="create">
							<logic:equal name="jims2AccountForm" property="userType" value="S">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.employeeId"/></td>
									<td class="formDe"><bean:write name="jims2AccountForm" property="employeeId"/></td>							
								</tr>							
							</logic:equal>						
							<logic:equal name="jims2AccountForm" property="userType" value="L">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.badgeNumber"/></td>
									<td class="formDe"><bean:write name="jims2AccountForm" property="badgeNumber"/></td>							
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.otherIdNumber"/></td>
									<td class="formDe"><bean:write name="jims2AccountForm" property="otherIdNumber"/></td>							
								</tr>
							</logic:equal>						
						</logic:equal>							
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS2UserId"/></td>
							<td class="formDe">
								<logic:equal name="jims2AccountForm" property="action" value="update">	
									<logic:equal name="jims2AccountForm" property="newJIMS2LogonId" value="">
										<bean:write name="jims2AccountForm" property="jims2LogonId"/>
									</logic:equal>								
									<logic:notEqual name="jims2AccountForm" property="newJIMS2LogonId" value="">
										<bean:write name="jims2AccountForm" property="newJIMS2LogonId"/>
									</logic:notEqual>								
								</logic:equal>	
								<logic:notEqual name="jims2AccountForm" property="action" value="update">	
									<bean:write name="jims2AccountForm" property="jims2LogonId"/>
								</logic:notEqual>
							</td>
						</tr>
						<logic:notEqual name="jims2AccountForm" property="userType" value="N">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.password"/></td>
								<td class="formDe">
									<logic:equal name="jims2AccountForm" property="action" value="update">	
										<logic:equal name="jims2AccountForm" property="newPassword" value="">
											<bean:write name="jims2AccountForm" property="jims2Password"/>
										</logic:equal>								
										<logic:notEqual name="jims2AccountForm" property="newPassword" value="">
											<bean:write name="jims2AccountForm" property="newPassword"/>
										</logic:notEqual>								
									</logic:equal>	
									<logic:equal name="jims2AccountForm" property="action" value="inactivate">	
										<bean:write name="jims2AccountForm" property="jims2Password"/>
									</logic:equal>
									<logic:equal name="jims2AccountForm" property="action" value="create">	
										<bean:write name="jims2AccountForm" property="password"/>
									</logic:equal>
								</td>
							</tr>
							<tr>		
								<td class="formDeLabel"><bean:message key="prompt.password"/>&nbsp;<bean:message key="prompt.question"/></td>
								<td class="formDe"><bean:write name="jims2AccountForm" property="passwordQuestion"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.password"/>&nbsp;<bean:message key="prompt.answer"/></td>
								<td class="formDe"><bean:write name="jims2AccountForm" property="passwordAnswer"/></td>
							</tr>						
						</logic:notEqual>	
					</table>
				</td>
			</tr>
        </table>
        <br>
<!--BEGIN BUTTON TABLE--> 
        <table align="center"> 
			<tr> 
				<td>
<!-- Summary Page buttons -->
				<logic:notEqual name="jims2AccountForm" property="pageType" value="confirm">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message>
					</html:button>
					<logic:equal name="jims2AccountForm" property="action" value="inactivate">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.inactivate"/></html:submit>	
					</logic:equal>						
					<logic:equal name="jims2AccountForm" property="action" value="update">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>										
					</logic:equal>						
					<logic:equal name="jims2AccountForm" property="action" value="create">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>										
					</logic:equal>	
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</logic:notEqual>
<!-- Confirmation Page button -->				
				<logic:equal name="jims2AccountForm" property="pageType" value="confirm">
					<logic:equal name="jims2AccountForm" property="basicUser" value="N">
						<logic:notEqual name="jims2AccountForm" property="currentUserLogonIdChanged" value="Y">				
							<html:submit property="submitAction"><bean:message key="button.backToSearch"/></html:submit>
						</logic:notEqual>	
						<logic:equal name="jims2AccountForm" property="currentUserLogonIdChanged" value="Y">				
							<a target="_top" href="/appshell/displayLogout.do"><img src="/<msp:webapp/>images/logOut.gif" border="1"></a>										
						</logic:equal>	
					</logic:equal>	
				</logic:equal>	
				<logic:equal name="jims2AccountForm" property="basicUser" value="Y">
					<logic:equal name="jims2AccountForm" property="currentUserLogonIdChanged" value="Y">				
						<a target="_top" href="/appshell/displayLogout.do"><img src="/<msp:webapp/>images/logOut.gif" border="1"></a>										
					</logic:equal>	
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
