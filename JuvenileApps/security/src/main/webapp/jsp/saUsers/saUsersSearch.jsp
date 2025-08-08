<!DOCTYPE HTML>
<!-- Used to search for Security Administrator Users. -->
<!--MODIFICATIONS -->
<!-- SPrakash 05/05/2005    Create JSP -->
<!-- SPrakash 07/05/2005    Modified -->
<!-- CShimek  03/30/2006    Per ER#26357, revised reset button to refresh button -->
<!-- CShimek  12/05/2006    Revised search field to match changes in saUserSearchResults.jsp -->
<!-- CShimek  01/12/2007    #38306 add multiple submit functionality  -->
<!-- LDeen	  05/11/2007    Add width attribute to First Name  -->
<!-- CShimek  07/26/2007    Added +indicator and increased agency name field length to match other MA Task search pages -->
<!-- CShimek  02/06/2009    #56860 add Back to Top  -->


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


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
<title><bean:message key="title.heading" /> saUsersSearch.jsp</title>

<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/saUsers/saUsersSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<%--bean:define id="agency" name="agency" /--%>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.saUsersManage" /></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click find to search for users.</li>
				<li>Select radio button for user you want to update and select Next button.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySAUserSearchResults" target="content" focus="lastName" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|155">	
		<tr>
			<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr>
					<td class="detailHead"><bean:message key="prompt.searchUsers" /></td>
				</tr>
				<tr>
					<td align="center"><!-- BEGIN SEARCH TABLE -->
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel"><bean:message
								key="prompt.lastName" /></td>
							<td class="formDe"><html:text property="lastName" size="25"
								maxlength="20" /></td>
							<td class="formDeLabel" nowrap width="1%">+<bean:message key="prompt.firstName" /></td>
							<td class="formDe" nowrap width="1%"><html:text property="firstName" size="25"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.userType" /></td>
							<td class="formDe" colspan="3"><html:select size="1" property="userTypeId" >
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="userTypes" value="code" label="description" />
							</html:select></td>
						</tr>
						<tr>	
							<td class="formDeLabel" nowrap><bean:message key="prompt.userId" /></td>
							<td class="formDe" colspan="3"><html:text property="logonId" size="25" maxlength="20" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyName" /></td>
							<td class="formDe" colspan="3"><html:text property="agencyName" size="62" maxlength="60" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"></td>
							<td class="formDe" colspan="3">
								<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
									<bean:message key="button.find"></bean:message>
								</html:submit>
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.refresh"></bean:message>
								</html:submit>
							</td>
						</tr>
					</table>
					<!-- END SEARCH TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</html:form>
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>