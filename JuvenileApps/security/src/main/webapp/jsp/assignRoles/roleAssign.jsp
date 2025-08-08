<!DOCTYPE HTML>
<!-- 05/27/2005	 Aswin Widjaja  - Create JSP -->
<!-- 03/28/2006  CShimek 		- Activity#30038 Revise Reset to Refresh button -->
<%-- 05/24/2006  CShimek        - Defect#31738 added checkEnterKey() to body tag --%>
<%-- 07/10/2006  CShimek        - Activity#32909, relocate error message between instructions and search block --%>
<%-- 01/04/2007  CShimek        - Defect#37980 corrected problem with Search By field described in defect --%>
<%-- 01/10/2007  CShimek        - #38306 add multiple submit functionality  --%>
<%-- 01/22/2007  CShimke        - relocated find and refresh to be consistant with other search pages --%>
<!-- 02/04/2009  C Shimek       - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young        - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<title><bean:message key="title.heading"/> - roleAssign.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assignRoles/roleAssign.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script type="text/javascript">
function clearInputFields(){
	for(var i="0"; i< document.forms[0].length; i++)
	{
		if(document.forms[0].elements[i].type == 'text')
		{
			document.forms[0].elements[i].value = "";
		}
	}
}

function evalSearch(el){
	var rpt = el.options[el.selectedIndex].value;
	clearInputFields();
	
	if (rpt == "userGroups" || rpt == ""){
			showHide('userGroups', 1);
    		showHide('users', 0);
			showHide('noReqPrompt', 1);    		
			showHide('reqPrompt', 0);				    		
			document.forms[0].userGroupName.focus();	
		}else if (rpt == "users"){
				showHide('userGroups', 0);
				showHide('users', 1);
				showHide('noReqPrompt', 0);    						
				showHide('reqPrompt', 1);				
				document.forms[0].userLastName.focus();	
			}
        }
</script>

</head>
<!--END HEADER TAG-->

<body onload="evalSearch(document.forms[0].searchType)" onKeyDown="return checkEnterKeyAndSubmit(event,true)"> 
<html:form action="/displayAssignRolesSearchResults" target="content">
<!-- HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" valign="top"><bean:message key="title.assignRoles" /></td>
	</tr>
</table>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>    
		<td align="center" class="errorAlert">
        	  <bean:write name='assignRolesForm' property='errorMessage'/>
      	</td>    
   	</tr>      
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<span id="noReqPrompt" class="hidden"> 
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select either JIMS2 User Groups or JIMS2 Users from <B>Search By</B> drop down list.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</span>
<span id="reqPrompt" class="hidden"> 
<table align="center" width="98%" border="0"> 
   <tr>
     <td>
	  <ul>
		<li>Select either JIMS2 User Groups or JIMS2 Users from <B>Search By</B> drop down list.</li>
      </ul>
	</td>
  </tr>
	<tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
	</tr>
</table>
</span>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN INQUIRY TABLE -->
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
   <tr>
     <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.searchBy"/></td>
     <td class="formDe">
     	<select name="searchType" onChange="evalSearch(this)">
			<logic:notEqual name="assignRolesForm" property="searchType" value="users"> 		     			
				<option value="userGroups" selected><bean:message key="prompt.JIMS2UserGroups"/></option>
				<option value="users"><bean:message key="prompt.JIMS2Users"/></option>
			</logic:notEqual>
			<logic:equal name="assignRolesForm" property="searchType" value="users"> 		     			
				<option value="userGroups" ><bean:message key="prompt.JIMS2UserGroups"/></option>
				<option value="users" selected><bean:message key="prompt.JIMS2Users"/></option>
			</logic:equal>
	    </select> 
     </td>
   </tr>
</table>
<br>
<!-- VARIABLES NEEDED FOR DISPLAY -->
<span id="userGroups" class="hidden">  
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="0" cellspacing="0" border="0" width="98%">				
				<tr>
					<td align="center">
					  <!-- SEARCH BY USER GROUPS -->
						<table border="0" cellspacing="1" cellpadding="2" width="100%">		
							<tr>
							    <td><input type="hidden" name="refreshButton" value=""></td>
							</tr>
							<tr class="detailHead">
								<td colspan="2" class="detailHead"><bean:message key="title.searchForUserGroups"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.userGroupName" /></td>
								<td class="formDe"><html:text size="25" property="userGroupName" maxlength="25" /></td>
							</tr><tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.description" /></td>
								<td class="formDe"><html:text size="50" property="userGroupDescription" maxlength="50" /></td>
							</tr>						
							<logic:equal name="assignRolesForm" property="userType" value="MA">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
									<td class="formDe"><html:text size="50" property="userGroupAgencyName" maxlength="50" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyCode" /></td>
									<td class="formDe"><html:text size="3" property="userGroupAgencyCode" maxlength="3" /></td>
								</tr>
							</logic:equal>
						     <tr>
								<td class="formDeLabel"></td>						     
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateUserGroupSearchFields(this.form) && disableSubmit(this, this.form);">
										<bean:message key="button.findUserGroups"/>
									</html:submit>
									<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value ='UserGroups' && disableSubmit(this, this.form);">
										<bean:message key="button.refresh" />
									</html:submit>
								</td>
			                </tr>
							
						 </table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>				
</span>
                      
  <!-- SEARCH BY USERS -->
<span id="users" class="hidden">
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="0" cellspacing="0" border="0" width="98%">				
				<tr>
					<td align="center">
                         <table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr class="detailHead">
								<td colspan="2" class="detailHead"><bean:message key="title.searchForUsers"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.name"/></td>
								<td class="formDe">
									<table border="0">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.last"/></td>
											<td class="formDeLabel">+<bean:message key="prompt.first"/></td>											
										</tr>
										<tr>
											<td class="formDe"><html:text size="30" property="userLastName" maxlength="50"/></td>											
											<td class="formDe"><html:text size="25" property="userFirstName" maxlength="50"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
								<logic:equal name="assignRolesForm" property="userType" value="MA">
									<td class="formDe"><html:text size="50" property="userAgencyNamePrompt" maxlength="50"/></td>
								</logic:equal>
								<logic:equal name="assignRolesForm" property="userType" value="SA">
									<td class="formDe"><bean:write name="assignRolesForm" property='agencyName'/></td>
								</logic:equal>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyCode" /></td>
									<logic:equal name="assignRolesForm" property="userType" value="MA">
										<td class="formDe"><html:text size="3" property="userAgencyCode" maxlength="3" /></td>
									</logic:equal>
									<logic:equal name="assignRolesForm" property="userType" value="SA">
										<td class="formDe"><bean:write name="assignRolesForm" property='agencyId'/></td>
									</logic:equal>
								</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.department"/></td>
     							<td class="formDe"><html:text property="departmentName" size="50" maxlength="50" /></td>
     						</tr>
     						<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode" /></td>
								<td class="formDe"><html:text property="departmentId" size="3" maxlength="3" /></td>
							</tr>
 					    	<tr>
							    <td class="formDeLabel"><bean:message key="prompt.userId"/></td>
							    <td class="formDe"><html:text property="userId" size="8" maxlength="8"/></td>
							</tr>
			    			<tr>
								<td class="formDeLabel"></td>						     
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateUserSearchFields(this.form) && disableSubmit(this, this.form);">
										<bean:message key="button.findUsers"/>
									</html:submit>
									<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value ='Users';">
										<bean:message key="button.refresh" />
									</html:submit>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</span>
<table>
    	
</table>
<table>	
	<tr>
		<td>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|15">	
		</td>
	</tr>
</table>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>