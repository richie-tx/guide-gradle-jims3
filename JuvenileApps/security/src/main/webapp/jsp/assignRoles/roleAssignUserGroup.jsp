<!DOCTYPE HTML>
<!-- 06/02/2005	 Aswin Widjaja  - Create JSP -->
<!-- 03/28/2006  CShimek 		- Activity#30038 Revise Reset to Refresh button -->
<!-- 07/11/2006  CShimek 		- Activity#32977 Increase search result display size -->
<!-- 01/10/2007  CShimek        - #38306 add multiple submit functionality  -->
<!-- 03/28/2007  CShimek		- #40562 add hidden field and validation call to verify at least 1 role has been selected -->
<!-- 02/04/2009  CShimek        - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young        - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleAssignUserGroup.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assignRoles/roleAssignUserGroup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->


<!-- HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" valign="top"><bean:message key="title.assignUserGroupRoles" /></td>
	</tr>
</table>
<br>

<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click find to search for roles to assign.</li>
        <li>Select Roles to assign and click Add to List button.</li>
	    <li>Select Remove button to remove roles from list. </li>
	  </ul>
	</td>
  </tr>
  <tr>
  	<td class="required">
		<img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0">
		<bean:message key="prompt.requiredFieldsInstruction"/>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<html:form action="/handleAssignUserGroupRoles" target="content" focus="roleName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|16">	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center" valign="top">

			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="title.userGroupInformation"/></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="4" width="100%">

							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupName"/></td>
								<td class="formDe"><bean:write name="assignRolesForm" property="userGroupName"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.description"/></td>
								<td class="formDe"><bean:write name="assignRolesForm" property="userGroupDescription"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>

			<!--assign SA start-->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">

				<tr class="detailHead">
					<td class="detailHead"><bean:message key="title.searchForRolesToAssign"/></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.roleName"/></td>
								<td class="formDe"><html:text size="20" property="roleName" maxlength="30" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><html:text size="20" property="roleDescription" maxlength="50" /></td>
							</tr>
							<logic:equal name="assignRolesForm" property="userType" value="MA">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agency" /></td>
									<td class="formDe"><html:text size="20" property="userGroupAgencyName" maxlength="50" /></td>
								</tr>
							</logic:equal>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateRoleSearchFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.find"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.refresh"></bean:message></html:submit>																		
								</td>
						</table>
						<!-- BEGIN ERROR TABLE -->
						<table width="100%">
							<tr>		  
								<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
							</tr>   	  
						</table>
						<!-- END ERROR TABLE -->
					</td>
				</tr>
			
				<logic:notEmpty name="assignRolesForm" property="availableRoles">						
				<tr>
					<td colspan="2" align="center">
						<bean:size id="availableRolesSize" name="assignRolesForm" property="availableRoles"/>
						<bean:write name="availableRolesSize"/> search results for Role Name:
						<div class="scrollingDiv200">
						<!--checkboxes start-->
						<table width="100%">
							<tr class="formDeLabel"  height="100%">
								<td nowrap><input type="checkbox" name="checkall" value="test" onclick="allRolesSelect(this, 'selectedRoles')">JIMS2 Role Names
								   <jims:sortResults beanName="assignRolesForm" results="availableRoles" 
                                       primaryPropSort="roleName" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="1" />
								</td>
								<td><bean:message key="prompt.agency"/></td>
							</tr>
				</logic:notEmpty>
</html:form>					
<html:form action="/handleAssignUserGroupRoles" target="content">
				<logic:notEmpty name="assignRolesForm" property="availableRoles">								
							<tr  height="100%">	
								<logic:iterate id="availableRolesIndex" name="assignRolesForm" property="availableRoles" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
									<td>
										<input type="checkbox" name="selectedRoles" value="<bean:write name="availableRolesIndex" property='roleId'/>"> 
									    <a href="javascript:newCustomWindow('/<msp:webapp/>displayRoleDetails.do?roleId=<bean:write name="availableRolesIndex" property="roleId"/>', 400,460)" >
										<bean:write name="availableRolesIndex" property="roleName"/></a>
									</td>
									<td>
										<bean:write name="availableRolesIndex" property="agencyName"/>
									</td>
								</tr>
								</logic:iterate>
							</tr>
						</table>
						</div>
							<br>
					   <html:submit property="submitAction" onclick="return checkAddRoles(this.form, 'selectedRoles')"><bean:message key="button.addToList"/></html:submit>
					</td>
				</tr>
				</logic:notEmpty>
				
				<logic:notEmpty name="assignRolesForm" property="currentRoles">
				<tr  height="100%">
					<td colspan="2" align="center">
						<br>
						<div class="scrollingDiv100">
							<table width="100%" cellspacing="0" cellpadding="4">
							<tr class="formDeLabel" height="100%">
								<td colspan="2" class="boldText">
									<bean:message key="title.currentOrSelectedRolesList"/>
									<input type="hidden" name="roleSelected" value="">
								</td>
								<td class="boldText"><bean:message key="prompt.roleDescription"/></td>
							</tr>
							<tr  height="100%">	
								<logic:iterate id="currentRolesIndex" name="assignRolesForm" property="currentRoles" indexId="index2">
								<tr height="100%" class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td width="1%" valign="top">
									    <a href="/<msp:webapp/>handleAssignUserGroupRoleRemove.do?roleId=<bean:write name="currentRolesIndex" property="roleId"/>" title='Remove <bean:write name="currentRolesIndex" property="roleName"/>'>Remove</a>
									</td>
									<td>
										<bean:write name="currentRolesIndex" property="roleName"/>
									</td>
									<td><bean:write name="currentRolesIndex" property="roleDescription"/>
								</tr>
								</logic:iterate>
							</tr>
							</table>
						</div>
						<br>
					</td>
				</tr>
				</logic:notEmpty>
			</table>
			<!--assign SA end-->
			<br>
			<!-- BEGIN BUTTON TABLE -->
			<table align="center">
				<tr>
					<td>
						<input type="button" value="Back" onclick="history.go(-1)">
						<html:submit property="submitAction" onclick="return validateSelect() && disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
					</td>
				</tr>
			</table>
		<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
</html:form>	
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>