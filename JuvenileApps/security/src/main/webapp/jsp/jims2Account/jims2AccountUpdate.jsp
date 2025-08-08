<!DOCTYPE HTML>
<!-- 12/13/2006	 C Shimek   - Create JSP -->
<!-- 02/12/2007  C Shimek   - #39448 Added Main Page button for basic users -->
<!-- 02/15/2007  C Shimek   - Correct reset button functionality found while testing create -->
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
<title><bean:message key="title.heading" /> - JIMS2AccountUpdate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jims2Account/jims2AccountUpdate.js" ></script> 
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setCursor()">
<html:form action="/displayJIMS2AccountSummary" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|222">
<input type="hidden" name='userTypeInd' value="<bean:write name="jims2AccountForm" property="userType" />" > 
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
    	<bean:message key="prompt.update"/>&nbsp;<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>
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
		<td>
			<ul>
    	    	<li>Enter required fields and select Next button to view summary.</li>
      		</ul>
      	</td>
  	</tr>
  	<logic:notEqual name="jims2AccountForm" property="userType" value="G">	
		<tr>
			<td class="required">+ Indicates required if New JIMS2 User ID is entered.</td>
		</tr>  
  	</logic:notEqual>
  	<logic:equal name="jims2AccountForm" property="userType" value="G">	  
		<tr>
			<td class="required"><bean:message key="prompt.requiredFields"/></td>
		</tr>						
		<tr>
			<td class="required">+ Indicates required if New JIMS2 User ID is entered.</td>
		</tr>
		<tr>
			<td class="required">++ Indicates required if New Password is entered.</td>
		</tr>  
  	</logic:equal>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
      <td width="98%" align="center" valign="top">
 		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%" align="center">
			<tr class="detailHead">
				<td class="detailHead">
					<bean:message key="prompt.JIMS2"/> <bean:message key="prompt.account"/> <bean:message key="prompt.information"/>
				</td>
			</tr>
			<tr>
				<td align="center">
 					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel">
								<bean:message key="prompt.JIMS"/>&nbsp;<bean:message key="prompt.userId"/>
							</td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="jimsLogonId"/></td>
						</tr>	
						<logic:equal name="jims2AccountForm" property="userType" value="N">	
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.userName"/></td>
								<td class="formDe">
									<bean:write name="jims2AccountForm" property="lastName"/>,&nbsp;<bean:write name="jims2AccountForm" property="firstName"/>&nbsp;<bean:write name="jims2AccountForm" property="middleName"/> 
								</td>
							</tr>	
						</logic:equal>
						<logic:notEqual name="jims2AccountForm" property="userType" value="N">	
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.user"/> <bean:message key="prompt.lastName"/></td>
								<td class="formDe"><html:text property="lastName" size="30" maxlength="30" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.user"/> <bean:message key="prompt.firstName"/></td>
								<td class="formDe"><html:text property="firstName" size="25" maxlength="25" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.user"/> <bean:message key="prompt.middleName"/></td>
								<td class="formDe"><html:text property="middleName" size="25" maxlength="25" /></td>
							</tr>
						</logic:notEqual> 	
						<logic:equal name="jims2AccountForm" property="userType" value="S"> 
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.serviceProviderName"/></td>							
								<td class="formDe"><bean:write name="jims2AccountForm" property="serviceProviderName"/></td>
							</tr>	
						</logic:equal>						
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.departmentName"/></td>
							<td class="formDe"><bean:write name="jims2AccountForm" property="departmentName"/></td>
						</tr>			 				
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMS2UserId"/></td>
							<td class="formDe">
								<bean:write name="jims2AccountForm" property="jims2LogonId"/>
								<input type="hidden" name="currentJIMS2LogonId" value="<bean:write name="jims2AccountForm" property="jims2LogonId"/>" >
							</td>
						</tr>							
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.new"/> <bean:message key="prompt.JIMS2UserId"/></td>
							<td class="formDe"><html:text property="newJIMS2LogonId" size="50" maxlength="50" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap>+ <bean:message key="prompt.reEnter"/> <bean:message key="prompt.new"/> <bean:message key="prompt.JIMS2UserId"/></td>
							<td class="formDe"><html:text property="reenterNewJIMS2LogonId" size="50" maxlength="50" /></td>
						</tr>
				 		<logic:notEqual name="jims2AccountForm" property="userType" value="N">	
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.password"/></td>
								<td class="formDe"><bean:write name="jims2AccountForm" property="jims2Password"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.new"/> <bean:message key="prompt.password"/></td>
								<td class="formDe"><html:text property="newPassword" size="32" maxlength="32" /></td>
							</tr>
							<tr>
								<td class="formDeLabel">++ <bean:message key="prompt.reEnter"/> <bean:message key="prompt.new"/> <bean:message key="prompt.password"/></td>
								<td class="formDe"><html:text property="reenterNewPassword" size="32" maxlength="32" /></td>
							</tr>
							<tr>		
								<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.password"/> <bean:message key="prompt.question"/></td>
								<td class="formDe">
									<html:select property="passwordQuestionId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="passwordQuestions" value="code" label="description" /> 
									</html:select>
									<input type="hidden" name="passwordQuestion" value="">
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.password"/> <bean:message key="prompt.answer"/></td>
								<td class="formDe"><html:text property="passwordAnswer" size="25" maxlength="20" /></td>
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
					<logic:notEqual name="jims2AccountForm" property="basicUser" value="Y">
						<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					</logic:notEqual>	
						<html:submit property="submitAction" onclick="return checkInput(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>								
						<html:submit property="submitAction"><bean:message key="button.reset"/></html:submit> 
					<logic:notEqual name="jims2AccountForm" property="basicUser" value="Y">						
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>		
					</logic:notEqual>
					<logic:equal name="jims2AccountForm" property="basicUser" value="Y">						
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.mainPage"/></html:submit>		
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