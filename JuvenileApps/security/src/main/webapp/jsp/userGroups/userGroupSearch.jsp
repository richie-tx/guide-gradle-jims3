<!DOCTYPE HTML>
<!-- Used to search for User Groups. -->
<!--MODIFICATIONS -->
<!-- CShimek 06/07/2005	 Create JSP -->
<!-- CShimek 03/30/2006  Per ER#26357, revised reset button to refresh button -->
<%-- CShimek 07/10/2006  Activity#32909, relocate error message between instructions and search block --%>
<%-- CShimek 10/09/2006  Activity#36031, changed User Group Name hyperlink to function --%>
<!-- CShimek 01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->
<!-- RYoung  10/19/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<title><bean:message key="title.heading"/> - userGroupSearch.jsp</title>

<%-- html:javascript formName="roleSAForm" /--%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/userGroups/userGroupSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.userGroupManage"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<td> <ul>
			<li>Enter 1 or more search values then select Find button to search for user groups.</li>
			<logic:equal name="userGroupForm" property="userType" value="MA">
				<li>To create a new User Group, click Create New User Group button. </li>
			</logic:equal>
			<logic:equal name="userGroupForm" property="userType" value="SA">
				<li>To create a new User Group, click Create New User Group button. </li>
			</logic:equal>
		</ul></td>
	</tr>
	<tr>
		<td class="required">At least one field is required for search.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->				

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displayUserGroupSearchResults" target="content" focus="userGroupName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|160">			
	<tr>	
	<td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
			<tr class="detailHead">
				<td class="detailHead">Search for User Groups</td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.userGroupName"/></td>
							<td class="formDe"><html:text property="userGroupName" size="25" maxlength="25"/></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.userGroupDescription"/></td>
							<td class="formDe"><html:text property="userGroupDescription" size="52" maxlength="50"/></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.status"/></td>
							<td class="formDe">
			            	       <html:select property="statusId">
            					      <html:option value=""><bean:message key="select.generic" /></html:option>
					            	  <html:optionsCollection property="statuses" value="code" label="description" /> 
			  	               	   </html:select> 								
							</td>
						</tr>
						<logic:equal name="userGroupForm" property="userType" value="MA">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
								<td class="formDe"><html:text property="agencyName" size="60" maxlength="60"/></td>
							</tr>
						</logic:equal> 
						<tr>
							<td class="formDeLabel"></td>
							<td class="formDe">
									<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
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
			
</html:form>			
            <logic:notEmpty name="userGroupForm" property="userGroups"> 
			
			<tr>
				<td align="center"> 
					<bean:write name="userGroupForm" property="userSearchResultSize"/> search results found.				    
			    </td>
			</tr>
			<tr>
				<td>       
				<div class="scrollingDiv200">
					<table border="0" width="100%" cellspacing="1" cellpadding="2">
						<tr bgcolor="#CCCCCC" height="100%">
							<td class="boldText"><bean:message key="prompt.userGroupName"/>s
								&nbsp;
								<jims:sortResults beanName="userGroupForm" results="userGroups" 
                                 primaryPropSort="name" primarySortType="STRING" defaultSort="true" 
                                 defaultSortOrder="ASC" sortId="1" />
							</td>
						
							<td class="boldText"><bean:message key="prompt.agency"/>
								&nbsp;
								<jims:sortResults beanName="userGroupForm" results="userGroups" 
                                 primaryPropSort="agencyName" primarySortType="STRING" defaultSort="false" 
                                 defaultSortOrder="ASC" sortId="2" />
							</td>
							<td width="1%"></td>
						</tr>
						<logic:iterate id="groupIndex" name="userGroupForm" property="userGroups" indexId="index">
							  <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
               				    <td> 
									<a href="/<msp:webapp/>handleUserGroupSelection.do?action=view&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='View <bean:write name="groupIndex" property="name"/> details' ><bean:write name="groupIndex" property="name"/></a>
								</td>
               				    <td> 
									<bean:write name="groupIndex" property="agencyName"/>
								</td>
								<td nowrap>
									<logic:notEqual name="userGroupForm" property="userType" value="ASA">
									    <logic:equal name="groupIndex" property="statusId" value="A"> 
										    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=update&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='Edit <bean:write name="groupIndex" property="name"/>' ><bean:message key="prompt.edit"/></a>|
										    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=inactivate&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='Inactivate <bean:write name="groupIndex" property="name"/>' ><bean:message key="prompt.inactivate"/></a>
										</logic:equal>
									    <logic:equal name="groupIndex" property="statusId" value="I"> 
										    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=delete&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='Delete <bean:write name="groupIndex" property="name"/>' ><bean:message key="prompt.delete"/></a>|
										    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=activate&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='Activate <bean:write name="groupIndex" property="name"/>' ><bean:message key="prompt.activate"/></a>
										</logic:equal>
									</logic:notEqual>	
									<logic:equal name="userGroupForm" property="userType" value="ASA">
									    <logic:equal name="groupIndex" property="statusId" value="A"> 
										    <a href="/<msp:webapp/>handleUserGroupSelection.do?action=update&groupId=<bean:write name="groupIndex" property="userGroupId"/>" title='Update Users for <bean:write name="groupIndex" property="name"/>' ><bean:message key="prompt.updateUser"/>s</a>
										</logic:equal>
									</logic:equal>
								</td>
							</tr>
				            </logic:iterate>
					</table>
				</div>
				</td>
			</tr>
		</table>
	</td>
</tr> 

</logic:notEmpty>
</table>
<logic:equal name="userGroupForm" property="userType" value="SA">
<html:form action="/displayUserGroupCreate" target="content" >		
<!--BEGIN BUTTON TABLE--> 
	<table align="center" width="98%">
		<tr><td>&nbsp;</td></tr>
	    <tr>
	       <td align="center">
              <html:submit property="submitAction">
                  <bean:message key="button.createNewUserGroup"></bean:message>
              </html:submit>
	       </td>
	    </tr>    
	</table>
<!--END BUTTON TABLE-->
</html:form>
</logic:equal>
<logic:equal name="userGroupForm" property="userType" value="MA">
<html:form action="/displayUserGroupCreate" target="content" >		
<!--BEGIN BUTTON TABLE--> 
	<table align="center" width="98%">
		<tr><td>&nbsp;</td></tr>
	    <tr>
	       <td align="center">
              <html:submit property="submitAction">
                  <bean:message key="button.createNewUserGroup"></bean:message>
              </html:submit>
	       </td>
	    </tr>    
	</table>
<!--END BUTTON TABLE-->
</html:form>
</logic:equal>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>