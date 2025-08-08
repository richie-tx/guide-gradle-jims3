<!DOCTYPE HTML>
<!-- 06/02/2005	 Aswin   Widjaja - Create JSP -->
<!-- 03/28/2006  CShimek Activity#30038 Revise Reset to Refresh button -->
<!-- 07/10/2006  CShimek Moved error message to display above User Information block -->
<!-- 01/10/2007  CShimek #38306 add multiple submit functionality  -->
<!-- 02/04/2009  CShimek #56860 add Back to Top  -->
<!-- 12/14/2009  CShimek #63102 revised href on role name to access pop-up page -->
<!-- 10/19/2015  RYoung  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
<title><bean:message key="title.heading"/> - roleAssignUser.jsp</title>

<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assignRoles/roleAssignUser.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleAssignUserRoles" target="content" focus="roleName">  
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|22">	
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" valign="top"><bean:message key="title.assignUserRoles" /></td>
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
        <li>Click find to search for roles to assign.</li>
        <li>Select Remove to remove Role from list.</li>
	    <li>Select roles to assign and click Add to List button. </li>
	  </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN FORM -->

<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
	<tr>
		<td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.user" /> <bean:message key="prompt.info"/></td>
					
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.user" /> <bean:message key="prompt.name"/></td>
								<td class="formDe"><bean:write property="userLastName" name="assignRolesForm"/> <logic:notEqual name="assignRolesForm" property="userFirstName" value=""> , </logic:notEqual> <bean:write property="userFirstName" name="assignRolesForm"/> <bean:write property="userMiddleName" name="assignRolesForm"/></td>								
							</tr>
							<tr>
								<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.userId"/></td>
								<td class="formDe"><bean:write property="userId" name="assignRolesForm"/></td>
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
								<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.roleName"/></td>
								<td class="formDe"><html:text size="20" property="roleName" maxlength="30" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><html:text size="20" property="roleDescription" maxlength="50" /></td>
							</tr>
							<logic:equal name="assignRolesForm" property="userType" value="MA">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
									<td class="formDe"><html:text size="60" property="roleAgencyName" maxlength="60" /></td>
								</tr>
							</logic:equal>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateRoleSearchFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.find"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.refresh"></bean:message></html:submit>									
								</td>
						</table>
					</td>
				</tr>
				<logic:notEmpty name="assignRolesForm" property="availableRoles">
				<tr>
					<td colspan="2" align="center">
						<bean:size id="availableRolesSize" name="assignRolesForm" property="availableRoles"/>
						<bean:write name="availableRolesSize"/> search results for Role Name:						
						<script>
						renderScrollingArea(<bean:write name="availableRolesSize" />); 
						</script>
						<!--checkboxes start-->
						<table width="100%" cellspacing="1" cellpadding="4">
						<thead>
							<tr class="formDeLabel">
								<td nowrap="nowrap">
								<logic:lessEqual name="availableRolesSize" value="100">
									<input type="checkbox" name="checkall" value="test" onclick="allRolesSelect(this, 'selectedRoles')">			 
		 						</logic:lessEqual>	
								JIMS2 Role Names
								   <jims:sortResults beanName="assignRolesForm" results="availableRoles" 
                                       primaryPropSort="roleName" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="1" /> 
								</td>
								<td class="boldText"><bean:message key="prompt.agency"/></td>
							</tr>
							</thead>
				</logic:notEmpty>

				<logic:notEmpty name="assignRolesForm" property="availableRoles">
							<tbody>								
								<logic:iterate id="availableRolesIndex" name="assignRolesForm" property="availableRoles" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
									<td>
									<logic:lessEqual name="availableRolesSize" value="100">
										<input type="checkbox" name="selectedRoles" value="<bean:write name="availableRolesIndex" property='roleId'/>" onclick="parent(this,'checkall')"> 		 
			 						</logic:lessEqual>	
			 						<logic:greaterEqual name="availableRolesSize" value="101">
										<input type="checkbox" name="selectedRoles" value="<bean:write name="availableRolesIndex" property='roleId'/>"> 														
				 					</logic:greaterEqual> 	
										<a href="javascript:newCustomWindow('/<msp:webapp/>displayRoleDetails.do?roleId=<bean:write name="availableRolesIndex" property="roleId"/>', 400,460)"
										 title="View <bean:write name="availableRolesIndex" property='roleDescription'/> details" >
										<bean:write name="availableRolesIndex" property="roleName"/></a>
									</td>
									<td><bean:write name="availableRolesIndex" property="agencyName"/></td>
								</tr>
								</logic:iterate>
							</tbody>					
								
						</table>
						</div>
						<html:submit property="submitAction" onclick="return checkAddRoles(this.form, 'selectedRoles')"><bean:message key="button.addToList"/></html:submit>
						
					</td>
				</tr>
				</logic:notEmpty>
				
				<logic:notEmpty name="assignRolesForm" property="currentRoles">
				<tr>
					<td colspan="2" align="center">
						<br>
						<bean:size id="rolesSize" name="assignRolesForm" property="currentRoles" />
						<script>
						renderScrollingArea(<bean:write name="rolesSize" />); 
						</script>
							<table width="100%" cellspacing="1" cellpadding="4" height="100%">
							<thead>
							<tr class="formDeLabel">
								<td colspan="2" class="boldText"><bean:message key="title.currentOrSelectedRolesList"/></td>
								<td class="boldText"><bean:message key="prompt.roleDescription"/></td>
							</tr>
							</thead>
							<tbody>
							<tr>	
								<logic:iterate id="currentRolesIndex" name="assignRolesForm" property="currentRoles" indexId="index2">
								<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
									<td width="1%" valign="top">
									    <a href="/<msp:webapp/>handleAssignUserRoleRemove.do?roleId=<bean:write name="currentRolesIndex" property="roleId"/>" title='Remove <bean:write name="currentRolesIndex" property="roleName"/>'>Remove</a>
									</td>
									<td class="boldText">
										<bean:write name="currentRolesIndex" property="roleName"/>
									</td>
									<td><bean:write name="currentRolesIndex" property="roleDescription"/></td>
								</tr>
								</logic:iterate>
							</tr>
							</tbody>
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
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.returnProfileSearchResults"/></html:submit>
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