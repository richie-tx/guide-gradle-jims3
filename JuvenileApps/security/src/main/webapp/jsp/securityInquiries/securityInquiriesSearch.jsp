<!DOCTYPE HTML>
<!-- 07/28/2006	 C Shimek   - Create JSP -->
<!-- 10/02/2006  C Shimek   - Revised User Id search to use separated search field for JIMS and JIMS2 User IDs  -->
<!-- 01/11/2007  C Shimek   - #38306 add multiple submit functionality  -->
<!-- 08/24/2007  C Shimek   - #41892 per request of RAC, commented out search by "EmployeeId"  -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young    - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<title><bean:message key="title.heading" /> - securityInquiriesSearch.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type="text/javascript" src="/<msp:webapp/>js/securityInquiriesSearch.js" ></script> 
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<%--END HEADER TAG --%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="evalSearch(document.forms[0].searchTypeId)">
<html:form action="/handleSecurityInquiriesSearchSelection" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|198">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.securityInquiries"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<%-- BEGIN INSTRUCTION TABLE --%>
<span id='generalINSTR' class='visible'>
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Inquire By type, enter search criteria and then click Submit.</li>
				<li>At least 1 search field is required to search.</li>				
			</ul>
		</td>
	</tr>
</table>	
</span>	
<span id='userIDINSTR' class='hidden'>
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Inquire By type, enter search criteria and then click Submit.</li>
				<li>At least 1 search field is required to search.</li>				
				<li>User ID is used for JIMS and JIMS2 logon IDs.</li>				
			</ul>
		</td>
	</tr>
</table>   
</span>		
<span id='userProfileINSTR' class='hidden'>
<table align="center" width="98%" border="0">
	<tr>
	    <td>
			<ul>
				<li>Select Inquire By type, enter search criteria and then click Submit.</li>
				<li>Last Name is required.</li>				
			</ul>
		</td>	
		<td></td>
	</tr>
	<tr> 
      <td class="required"><bean:message key="prompt.2.diamond" />&nbsp;Required Fields</td> 
    </tr>
</table>
</span>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN INQUIRE TYPE TABLE --%>		
<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
	<tr>
		<td class="formDeLabel" width="1%" nowrap>Inquire By</td>
		<td class="formDe">
		  	<html:select property="searchTypeId" onchange="evalSearch(this)">
				<html:optionsCollection property="securityInquires" value="code" label="description" /> 
			</html:select>
		</td>
	</tr>
</table>
<%-- END INQUIRE BY TABLE --%>	
<br>
<%-- BEGIN AGENCY SEARCH TABLE --%>		
<span id="agency" class="visible">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyName"/></td>
			<td class="formDe"><html:text property="agencyName" size="60" maxlength="60" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
			<td class="formDe"><html:text property="agencyIdPrompt" size="5" maxlength="3" /></td>
		</tr> 
	</table>
</span>  
<%-- END AGENCY SEARCH TABLE --%>
<%-- BEGIN ROLE SEARCH TABLE --%>					
<span id="role" class="hidden">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" ><bean:message key="prompt.roleName"/></td>
			<td class="formDe"><html:text property="roleName" size="30" maxlength="30" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.roleDescription"/></td>
			<td class="formDe"><html:text property="roleDescription" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
			<td class="formDe"><html:text property="roleAgencyName" size="60" maxlength="60" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap><bean:message key="prompt.agencyCode"/></td>
			<td class="formDe"><html:text property="roleAgencyId" size="5" maxlength="3" /></td>
		</tr>
	</table>
</span>  
<%-- END ROLE SEARCH TABLE --%>	
<%-- BEGIN SUBSYSTEM SEARCH TABLE --%>							
<span id="subsystem" class="hidden">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr> 
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.subsystem"/></td>
			<td class="formDe">
           		<html:select property="featureCategoryId"> 
		      		<html:option value=""><bean:message key="select.generic" /></html:option>
		       		<html:optionsCollection property="jims2Subsystems" value="code" label="description" /> 
				</html:select> 								
				<html:submit property="submitAction" onclick="return checkSubsystemSelect(this.form) && disableSubmit(this, this.form)"><bean:message key="button.go"/></html:submit>   		
			</td>  
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.feature"/></td>
			<td class="formDe">
           		<html:select property="featureId">
		      		<html:option value=""><bean:message key="select.generic" /></html:option>
    	   			<html:optionsCollection property="features" value="featureId" label="featureName" /> 
   				</html:select> 	
			</td>
		</tr>		
	</table> 
</span>
<%-- END SUBSYSTEM SEARCH TABLE --%>	
<%-- BEGIN USER GROUP SEARCH TABLE --%>
<span id="userGroup" class="hidden">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.userGroupName"/></td>
			<td class="formDe"><html:text property="userGroupName" size="25" maxlength="25" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.userGroupDescription"/></td>
			<td class="formDe"><html:text property="userGroupDescription" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
			<td class="formDe"><html:text property="userGroupAgencyName" size="60" maxlength="60" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
			<td class="formDe"><html:text property="userGroupAgencyId" size="5" maxlength="3" /></td>
		</tr> 
	</table>
</span>
<%-- END USER GROUP SEARCH TABLE --%>	
<%-- BEGIN USERID SEARCH TABLE --%>				
<span id="userID" class="hidden">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
			<td class="formDe"><html:text property="searchJIMSLogonId" size="8" maxlength="8" /></td>
		</tr>
		<%-- 79250<tr>
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS2UserId"/></td>
			<td class="formDe"><html:text property="searchJIMS2LogonId" size="30" maxlength="50" /></td>
		</tr> --%>

	</table>
</span>
<%-- END USERID SEARCH TABLE --%>
<%-- BEGIN USER PROFILE SEARCH TABLE --%>						
<span id="userProfile" class="hidden">
	<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.lastName"/></td>
			<td class="formDe"><html:text property="lastNamePrompt" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.firstName"/></td>
			<td class="formDe"><html:text property="firstNamePrompt" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
			<td class="formDe"><html:text property="userAgencyNamePrompt" size="60" maxlength="60" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
			<td class="formDe"><html:text property="userAgencyIdPrompt" size="5" maxlength="3" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.departmentName"/></td>
			<td class="formDe"><html:text property="userDeptNamePrompt" size="60" maxlength="60" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.departmentCode"/></td>
			<td class="formDe"><html:text property="userDeptIdPrompt" size="5" maxlength="3" /></td>
		</tr> 
	</table>
</span>
<%-- END  USER PROFILE  SEARCH TABLE --%>
<br>
<%-- BEGIN BUTTONS TABLE --%>
<table width="100%">	
   	<tr>
   		<td align="center">
			<html:submit property="submitAction" onclick="return submitPage(this.form) && disableSubmit(this, this.form)"><bean:message key="button.submit"/></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.refresh"/></html:submit>			
        </td>
    </tr>				  
</table>
<%-- END BUTTONS TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
