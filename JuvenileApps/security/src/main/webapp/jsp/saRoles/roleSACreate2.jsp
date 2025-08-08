<!DOCTYPE HTML>
<!-- Used to search create Security Administrator Roles - page 2 or 2. -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<%-- CShimek 02/13/2006  Corrected prompt values for required field indicator --%> 
<%-- CShimek 04/18/2006  Defect#30559 removed nogo() js --%>
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleSACreate2.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/saRoles/roleSACreate2.js"></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saRoleCreate"/></td>
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
        <li>Enter Role Name and Description then select Next.</li>	  
      </ul>
	</td>
  </tr>
  <tr>
  	<td class="required"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySARoleCreate3" target="content" focus="roleName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|64">
	<tr>
		<td width="98%" align="center" valign="top">
			<!-- BEGIN AGENCY INFORMATION TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Selected <bean:message key="prompt.agencyInfo"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" border="0" cellpadding=2>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyName"/></td>
         					    <td class="formDe"><bean:write name="roleSAForm" property="agencyName" />
         					    </td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END AGENCY INFORMATION TABLE -->	
			<br>	
			<!-- BEGIN ROLE ENTRY TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead"> 
					<td class="detailHead"><bean:message key="title.saRoleCreate"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%">
							<tr>
								<td nowrap class="formDeLabel">
  									<bean:message key="prompt.msa.diamond" /><bean:message key="prompt.roleName"/>
								</td>
								<td class="formDe"><html:text property="roleName" size="25" maxlength="20"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap>
									<bean:message key="prompt.roleDescription"/>
								</td>
								<td class="formDe"><html:text property="roleDescription" size="50" maxlength="50"/></td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>
			<!-- END ROLE ENTRY TABLE -->			
		<br>
	        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
			   <bean:message key="button.back"></bean:message></html:button>
		       <html:submit property="submitAction" onclick="return validateFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
		<br>
		</td>
</tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>