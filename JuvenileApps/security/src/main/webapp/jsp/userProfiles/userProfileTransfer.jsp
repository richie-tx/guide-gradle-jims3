<!DOCTYPE HTML>
<!-- 02/14/2006 Uma Gopinath - Create JSP -->
<%-- 05/24/2006 CShimek      - Defect#37013 added checkEnterKey() to body tag to correct enter key problem --%>
<!-- 12/07/2006 CShimek      - Activity 37546, added missing block title(activity for buttons).  -->
<!-- 01/11/2007 CShimek      - #38306 add multiple submit functionality  -->
<!-- 02/06/2009 CShimek      - #56860 add Back to Top  -->
<!-- 10/19/2015 RYoung       - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ include file="../../jQuery.fw" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --> 


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<html:javascript formName="transferDateForm"/>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/ %>
<html:base />
<title><bean:message key="title.heading" /> - userProfileTransfer.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/userProfiles/validateDepartment.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/security_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!-- BEGIN HEADING TABLE -->
<table align="center">
	<tr>
      <td class="header" align="center">
        
         	<bean:message key="prompt.transfer"/>  <bean:message key="prompt.userProfile"/>  
        
      </td>	
	</tr>
</table>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<li>Select New Department Code, Select Validate Department Code button or click hyperlinked "Search For Departments".</li>
				<li>Enter transfer date and time.</li>
				<li>Select submit button to update.</li>
			</ul>
		</td>
	</tr>
</table>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayUserProfileDeptTransferSummary"  >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|213">	
	
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center">	
	<tr>
  		<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;
  	      <bean:message key="prompt.dateFieldsInstruction" />
  	    </td>
  	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<br>
<!-- BEGIN CONTENT TABLE -->
<table width="98%" border="0" cellpadding="4" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.transferInfo" /></td>
	</tr>
	<tr>
		<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="1">
				
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name" /></td>
					<td class="formDe" colspan="2"><bean:write	name="userProfileForm" property="userName.formattedName" />
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId" /></td>
					<td class="formDe" colspan="2"><bean:write name="userProfileForm" property="logonId" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.currentAgency" /></td>
					<td class="formDe" colspan="2"><bean:write name="userProfileForm" property="agencyName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.current" /> <bean:message key="prompt.department" /></td>
					<td class="formDe" colspan="2"><bean:write name="userProfileForm" property="departmentName" /></td>
				</tr>
				
				 <tr>
               <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/>
               		<bean:message key="prompt.new" /> <bean:message key="prompt.departmentCode" /></td>
                 <td class="formDe">
	                  	 <table width="100%" cellpadding="3" cellspacing="0" border="0">
                      		<tr>
                        		<td class="formDe">
		               				<html:text name="userProfileForm" property="newDepartmentId" size="3" />
		               			</td>
               					
               					<td class="formDe" ><html:submit property="submitAction" onclick="return validateNewDepartment();">
					<bean:message key="button.validateDepartmentCode"></bean:message>
				</html:submit>Or </td><td> <a href="/<msp:webapp/>jsp/userProfiles/userProfileDepartmentSearch.jsp"><bean:message key="prompt.searchForDepartments"/></a>                                                                 
                        			</td>
               				</tr>
               			</table>
               		</td>
               		
            </tr>
            	<tr>
					<td class="formDeLabel"  width="1%" nowrap>
					<bean:message key="prompt.new" /> <bean:message key="prompt.departmentName" /></td>
					<td class="formDe" colspan="2"><bean:write name="userProfileForm" property="newDepartmentName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.department" />
					 <bean:message key="prompt.transferDate" /></td>
					<td class="formDe" colspan="2"> <html:text name="userProfileForm" property="transferDate" size="10" maxlength="10" styleId="transferDate" />
                    </td>
				</tr>
			
				<tr>
					 <td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.department" />
					 <bean:message key="prompt.transferTime" /></td>
					<td class="formDe" colspan="2">
					<html:select property="transferTime" name="userProfileForm">
					   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
					   <html:optionsCollection property="workDays" value="description" label="description"  name="userProfileForm"/>
					</html:select>  </td>		
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
					<html:submit property="submitAction">
						<bean:message key="button.back"></bean:message>
					</html:submit>					  
					<html:submit property="submitAction" onclick="return validateDeptTransferFields();">
						<bean:message key="button.submit"></bean:message>
					</html:submit>	
					<html:submit property="submitAction">
						<bean:message key="button.reset"></bean:message>
					</html:submit>									  
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>		
		</td>
	
	</tr>
	<tr>
		<td valign="top">						  
					<html:submit property="submitAction">
						<bean:message key="button.userProfileSearchResults"></bean:message>
					</html:submit>	
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
