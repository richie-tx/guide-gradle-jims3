<!DOCTYPE HTML>
<!-- 02/04/2009  C Shimek   - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young    - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<title><bean:message key="title.heading"/> - roleAssignSearchResults.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assignRoles/roleAssign.js"></script>

</head>
<!--END HEADER TAG-->

<body>
<!-- HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" valign="top"><bean:message key="title.assignRoles" />
		                                               <bean:message key="title.searchResults" /></td>
	</tr>
</table>
<!-- END HEADING TABLE  -->
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
		<li>Click hyperlinked Name to view details.</li>
	    <li>Click Edit Assignments to update Roles.  </li>
	  </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="0" cellspacing="0" border="0" width="98%">
 <!-- USER GROUP RESULTS -->							
			  <logic:notEmpty name="assignRolesForm" property="userGroups">
				<tr>
					<td align="center" style="padding:2px">
							<bean:size id="userGroupSize" name="assignRolesForm" property="userGroups"/>
							<bean:write name="userGroupSize"/>
							search results for User Group:
						<div class="scrollingDiv200">
						<table border="0" width="100%" cellspacing="1" cellpadding="2">
							<tr bgcolor="#cccccc"  height="100%">
								<td class="boldText"><bean:message key="prompt.JIMS2" />
                                                   <bean:message key="prompt.userGroup" />
                                    <jims:sortResults beanName="assignRolesForm" results="userGroups" 
                                       primaryPropSort="name" primarySortType="STRING" defaultSort="true" 
                                       defaultSortOrder="ASC" sortId="1" />               
								</td>
							
								<td class="boldText"><bean:message key="prompt.agency"/>(s)
								    <jims:sortResults beanName="assignRolesForm" results="userGroups" 
                                       primaryPropSort="agencyName" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="2" />
								</td>
								<td width="1%"></td>
							</tr>
							
			            	<logic:iterate id="userGroupIndex" name="assignRolesForm" property="userGroups" indexId="index">
			           		<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								<td class="boldText">
				  				    <input type="hidden" name="userGroupIndex" value="userGroupId"/>
				  				    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=view&groupId=<bean:write name="userGroupIndex" property="userGroupId"/>" 
				  				    	title='View <bean:write name="userGroupIndex" property="name" /> details'>
				  				    	<bean:write name="userGroupIndex" property="name"/>
				  				    </a>
								</td>
								<td><bean:write name="userGroupIndex" property="agencyName"/></td>
								<td><a href="/<msp:webapp/>displayAssignUserGroupRoles.do?userGroupId=<bean:write name="userGroupIndex" property="userGroupId"/>"><bean:message key="button.editAssignment"/></a></td>
							</logic:iterate>
						</table>
						</div>
						<br>
					</td>
				</tr>
			  </logic:notEmpty>
  
  <!-- USER RESULTS -->

			  <logic:notEmpty name="assignRolesForm" property="users">
 				<tr>
					<td align="center" style="padding:2px">
						<bean:size id="userSize" name="assignRolesForm" property="users"/>
						<bean:write name="userSize"/>
						search results for User:
					<%--div class="scrollingDiv200"--%>
					<table border="0" width="100%" cellspacing="1" cellpadding="2">
						<tr bgcolor="#cccccc">
							<td class="boldText"><bean:message key="prompt.name"/>
							    <jims:sortResults beanName="assignRolesForm" results="users" 
                                       primaryPropSort="lastName" secondPropSort="firstName" primarySortType="STRING" secondarySortType="STRING"
                                       defaultSort="true" defaultSortOrder="ASC" sortId="1" />
							</td>
							<td class="boldText"><bean:message key="prompt.userId"/>
							    <jims:sortResults beanName="assignRolesForm" results="users" 
                                       primaryPropSort="logonId" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="2" />
                            </td>
           					<td class="boldText"><bean:message key="prompt.agency"/> / Dept
           					    <jims:sortResults beanName="assignRolesForm" results="users" 
                                       primaryPropSort="agencyName" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="3" />
           					</td>
							<td width="1%"></td>
						</tr>
						
		            	<logic:iterate id="userIndex" name="assignRolesForm" property="users" indexId="index2">
		           		  <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						    <td>
								<a href="javascript:newCustomWindow('/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?action=view&logonId=<bean:write name="userIndex" property="logonId" />','', '500','500')"
                              	title='View <bean:write name="userIndex" property="lastName" />,&nbsp;<bean:write name="userIndex" property="firstName" />&nbsp;<bean:write name="userIndex" property="middleName" /> details' >
     				              	<bean:write name="userIndex" property="lastName" />,&nbsp;<bean:write name="userIndex" property="firstName" />&nbsp;<bean:write name="userIndex" property="middleName" /> </a>
							</td>							
							
							<td class="boldText"><bean:write name="userIndex" property="logonId"/>&nbsp;</td>
							<td><bean:write name="userIndex" property="agencyName"/>
								<logic:notEmpty name="userIndex" property="departmentName">
								/ <bean:write name="userIndex" property="departmentName"/>
								</logic:notEmpty>
							</td>
							<td> <a href="/<msp:webapp/>displayAssignUserRoles.do?userId=<bean:write name="userIndex" property="logonId"/>"><bean:message key="button.editAssignment"/></a></td>
						  </tr>	
						</logic:iterate>
					</table>
					<br>
				  </td>	
				</tr>
				</logic:notEmpty>
			</table>
			<br>
		</td>
	</tr>
</table>
<!-- BEGIN BUTTON TABLE  -->
<table align="center" width="98%" border="0">	
	<tr>
		<td>
	        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
	          <bean:message key="button.back"></bean:message></html:button>
		</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|15">	
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE  -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>