<!DOCTYPE HTML>

<!-- 03/30/2004	 HRodriguez - Create JSP -->
<!-- 08/18/2004	 HRodriguez - Refactor Action & JSP with new PD Event/Command -->
<!-- 05/05/2005  CShimek  revised page to new use new layout/format -->
<!-- 03/30/2006  CShimek   Per ER#26357, revised reset button to refresh button -->
<!-- 07/10/2006  CShimek   Moved error message to display above search block -->
<!-- 01/12/2007  CShimek   #38306 add multiple submit functionality  -->
<!-- 07/26/2007  CShimek   add last name required search instruction and field indicator  -->
<!-- 09/06/2007  LDeen	   Defect #45039 role name length changed to 30  -->
<!-- 02/05/2009  CShimek   #56860 add Back to Top  -->
<!-- 10/19/2015  R Young   #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
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
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleSearch.jsp</title>
<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/roles/roleSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayRoleSearchResults" target="content" focus="roleName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|152">
<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%" >
  <tr>
    <td align="center" class="header"><bean:message key="title.roleManage"/></td>
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
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter one or more search values the then select Find to search.</li>
				<li>Select Create New Role to go to create page.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
	</tr>	
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td align="center" valign="top">
			<!--assign SA start -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead" colspan="2">Search for Roles</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.roleName"/></td>
								<td class="formDe"><html:text property="roleName" size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><html:text property="roleDescription" size="50" maxlength="50"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" valign="top"><bean:message key="prompt.roleCreator"/></td>
								<td class="formDe">
									<table>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.lastName"/></td>
											<td class="formDeLabel">+<bean:message key="prompt.firstName"/></td>
										</tr>
										<tr>
											<td><html:text property="lastName" size="30" maxlength="30"/></td>
											<td><html:text property="firstName" size="25" maxlength="25"/></td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:equal name="roleForm" property="masterAdmin" value="Y">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
									<td class="formDe"><html:text property="agencyName" size="60" maxlength="60"/></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyType"/></td>
									<td class="formDe">
				            	       <html:select property="agencyTypeId">
	            					      <html:option value=""><bean:message key="select.generic" /></html:option>
						            	  <html:optionsCollection property="agencyTypes" value="code" label="description" /> 
				  	               	   </html:select> 								
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateSearchFields(this.form)&& disableSubmit(this, this.form);">
								  		<bean:message key="button.find"></bean:message>
		  							</html:submit>	
									<html:submit property="submitAction">
								  		<bean:message key="button.refresh"></bean:message>
		  							</html:submit>			  									  
								</td>
							</tr>
						</table>
					</td>
				</tr> 
                <logic:empty name="roleForm" property="roles">
	                <logic:notEmpty name="roleForm" property="everyoneRoles">
					<tr>
					    <td align="center">
							<bean:write name="roleForm" property="searchResultSize"/> search results found.				    
					    </td>
					</tr>
					<tr>
					    <td align="center"> 
					    	<script type="text/javascript">
							renderScrollingArea(<bean:write name="roleForm" property="searchResultSize"/>);									
							</script>
            	       	    <table border="0" width="100%" cellspacing="1" cellpadding="4" height="100%">
            	       	    <thead>
							    <tr class="formDeLabel">
								    <td><bean:message key="prompt.roleName"/>
								        <jims:sortResults beanName="roleForm" results="everyoneRoles" 
                                            primaryPropSort="roleName" primarySortType="STRING" defaultSort="true" 
                                            defaultSortOrder="ASC" sortId="1" /></td>
								    <td>&nbsp;</td>
							    </tr>
							</thead>
							<tbody>
						    <logic:iterate id="everyOneRoleIndex" name="roleForm" property="everyoneRoles"> 
							<tr bgcolor="#ccffcc" height="100%">
								<td><a href="/<msp:webapp/>handleRoleSelection.do?action=view&roleId=<bean:write name="everyOneRoleIndex" property="roleId"/>" title='View <bean:write name="everyOneRoleIndex" property="roleName"/>'><bean:write name="everyOneRoleIndex" property="roleName"/></a></td>
								<td>
								<logic:equal name="roleForm" property="masterAdmin" value="Y">
								   <a href="/<msp:webapp/>handleRoleSelection.do?action=update&roleId=<bean:write name="everyOneRoleIndex" property="roleId"/>" title='Edit <bean:write name="everyOneRoleIndex" property="roleName"/>'><bean:message key="prompt.edit"/></a>
								</logic:equal>   
								<logic:notEqual name="roleForm" property="masterAdmin" value="Y">
								   &nbsp;
								</logic:notEqual>   
								</td>
							</tr>
							</logic:iterate>
							</tbody> 
	                	    </table>
    	               </div> 
						</td>
					</tr>
					</logic:notEmpty>	                	    
                </logic:empty>
		
                <logic:notEmpty name="roleForm" property="roles"> 				
				<tr>
				    <td align="center">
						<bean:write name="roleForm" property="searchResultSize"/> search results found.				    
				    </td>
				</tr>
                <tr>
				    <td align="center">
					  	<script type="text/javascript">
							renderScrollingArea(<bean:write name="roleForm" property="searchResultSize"/>);									
							</script>
                   	    <table border="0" width="100%" cellspacing="1" cellpadding="4" height="100%">
                   	    <thead>
						    <tr class="formDeLabel">
						        <td>
							        <bean:message key="prompt.roleName"/>s
							        <jims:sortResults beanName="roleForm" results="roles" 
                                            primaryPropSort="roleName" primarySortType="STRING" defaultSort="true" 
                                            defaultSortOrder="ASC" sortId="1" />
							    </td>
							    <td width="1%" nowrap></td>
						    </tr>
						    </thead>
						    <tbody>
						    <logic:iterate id="everyOneRoleIndex" name="roleForm" property="everyoneRoles"> 
								<tr bgcolor="#ccffcc" height="100%">
									<td><a href="/<msp:webapp/>handleRoleSelection.do?action=view&roleId=<bean:write name="everyOneRoleIndex" property="roleId"/>" title='View <bean:write name="everyOneRoleIndex" property="roleName"/>'><bean:write name="everyOneRoleIndex" property="roleName"/></a></td>
									<td>
										<logic:equal name="roleForm" property="masterAdmin" value="Y">
										   <a href="/<msp:webapp/>handleRoleSelection.do?action=update&roleId=<bean:write name="everyOneRoleIndex" property="roleId"/>" title='Edit <bean:write name="everyOneRoleIndex" property="roleName"/>'><bean:message key="prompt.edit"/></a>
										</logic:equal>   
										<logic:notEqual name="roleForm" property="masterAdmin" value="Y">&nbsp;</logic:notEqual>   
									</td>
								</tr>
							</logic:iterate> 
							<logic:iterate id="rolesIndex" name="roleForm" property="roles" indexId="index">
							  <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
               				    	<td> 
										<a href="/<msp:webapp/>handleRoleSelection.do?action=view&roleId=<bean:write name="rolesIndex" property="roleId"/>" title='View <bean:write name="rolesIndex" property="roleName"/> details' ><bean:write name="rolesIndex" property="roleName"/></a>
									</td>
									<td nowrap>
								    	<a href="/<msp:webapp/>handleRoleSelection.do?action=update&roleId=<bean:write name="rolesIndex" property="roleId"/>" title='Edit <bean:write name="rolesIndex" property="roleName"/>' ><bean:message key="prompt.edit"/></a>|
								    	<a href="/<msp:webapp/>handleRoleSelection.do?action=copy&roleId=<bean:write name="rolesIndex" property="roleId"/>" title='Copy <bean:write name="rolesIndex" property="roleName"/>' ><bean:message key="prompt.copy"/></a>|								    
								    	<a href="/<msp:webapp/>handleRoleSelection.do?action=delete&roleId=<bean:write name="rolesIndex" property="roleId"/>" title='Delete <bean:write name="rolesIndex" property="roleName"/>' ><bean:message key="prompt.delete"/></a>
									</td>
								</tr>
				            </logic:iterate>
				            </tbody>
                        </table>
                        </div>
				    </td>
				</tr>
			    </logic:notEmpty>
			</table>
			</td></tr></table>
<!-- END DETAIL TABLE -->			  	 
<!-- BEGIN BUTTON TABLE --> 
	<table align="center" width="98%">
		<tr><td>&nbsp;</td></tr>
	    <tr>
	       <td align="center">
              <html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
                  <bean:message key="button.createNewRole"></bean:message>
              </html:submit>
	       </td>
	    </tr>    
	</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
<!--END BODY TAG-->
</html:html>