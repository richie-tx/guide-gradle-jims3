<!DOCTYPE HTML>
<!-- 12/07/2006 CShimek     - Activity 37546, added missing block title(activity for buttons).  -->
<!-- 02/06/2009 CShimek     - #56860 add Back to Top  -->
<!-- 10/19/2015 RYoung      - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --> 


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<script type="text/javascript" src="/<msp:webapp/>js/userProfiles/validateDepartment.js"></script>
<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<html:javascript formName="departmentForm"/>
<meta http-equiv="Content-Language" content="en-us">
<%--@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading"/> - userProfileTransferSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!-- BEGIN HEADING TABLE -->
<table align="center">
	<tr>
      <td class="header" align="center">
        <logic:equal name="userProfileForm" property="action" value="summary">
         	<bean:message key="title.transferProfileAgency"/> <bean:message key="title.summary"/>
         </logic:equal>
         	<logic:equal name="userProfileForm" property="action" value="confirm">
         		<bean:message key="title.transferProfileAgency"/> <bean:message key="title.confirmation"/>
         	</logic:equal>
        
      </td>	
	</tr>
</table>
<table width="98%" align="center">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<table width="98%" align="center">
	<tr>
	 <logic:equal name="userProfileForm" property="action" value="summary">  
   	 <td>
      	  <ul>           
     		<li>Select Finish button to transfer.</li>
	      </ul>
     </td>
     </logic:equal>
     <logic:equal name="userProfileForm" property="action" value="confirm">
     		<td align="center" class="confirm">Request for Department transfer successfully submitted for the User Profile listed below.</td>
     </logic:equal>
  </tr>
  
</table>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitUserProfileDeptTransfer">
<logic:equal name="userProfileForm" property="action" value="confirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|275">	
</logic:equal>
<logic:equal name="userProfileForm" property="action" value="summary">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|276">	
</logic:equal>
<!-- BEGIN CONTENT TABLE -->
<table width="98%" border="0" cellpadding="4" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.transferInfo" /></td>
	</tr>
	<tr>
		<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="1">
				
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.name" /></td>
					<td class="formDe"><bean:write name="userProfileForm" property="userName.formattedName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.userId" /></td>
					<td class="formDe"><bean:write name="userProfileForm" property="logonId" /></td>
				</tr>
				
				<tr>
					<td class="formDeLabel">
					<bean:message key="prompt.new" /> <bean:message key="prompt.agencyName" /></td>
					<td class="formDe"><bean:write name="userProfileForm" property="newDepartmentName" /></td>
				</tr>
            	<tr>
					<td class="formDeLabel">
					<bean:message key="prompt.new" /> <bean:message key="prompt.departmentName" /></td>
					<td class="formDe"><bean:write name="userProfileForm" property="newDepartmentName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.department" />
					 <bean:message key="prompt.transferDate" /></td>
					<td class="formDe"> <bean:write name="userProfileForm" property="transferDate" formatKey="date.format.mmddyyyy"/>
                   </td>
				</tr>
			
				<tr>
					 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.department" />
					 <bean:message key="prompt.transferTime" /></td>
					<td class="formDe"><bean:write property="transferTime" name="userProfileForm"/>						
					</td>		
				</tr>
				
			</table>
		</td>
	</tr>       
</table>
<!-- END CONTENT TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table align="center" border="0">
	<tr>
	  	
		<td valign="top">		
		 <logic:equal name="userProfileForm" property="action" value="summary">				  
			<html:submit property="submitAction">
				<bean:message key="button.back"></bean:message>
			</html:submit>&nbsp;					  
			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
				<bean:message key="button.finish"></bean:message>
			</html:submit>&nbsp;	
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		</logic:equal>	
		<logic:equal name="userProfileForm" property="action" value="confirm">	
			<html:submit property="submitAction">
				<bean:message key="button.mainPage"></bean:message>
			</html:submit>&nbsp;
        	<html:submit property="submitAction">
            	<bean:message key="button.searchUserProfile"></bean:message>
        	</html:submit>&nbsp;
         	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
            	<bean:message key="button.userProfileSearchResults"></bean:message>
        	</html:submit>       
		</logic:equal>
		</td>
	
	</tr>
	
</table>
<!-- END BUTTON TABLE -->
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>