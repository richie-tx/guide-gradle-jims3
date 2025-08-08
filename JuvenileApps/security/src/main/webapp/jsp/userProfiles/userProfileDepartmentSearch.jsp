<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/14/2006 Uma Gopinath - Create JSP -->
<!-- 01/05/2007 C Shimek     - Defect#37991 removed value="" on search field text tags -->
<!-- 01/10/2007 CShimek      - #38306 add multiple submit functionality  -->
<!-- 02/01/2007 CShimek      - #38454 changed search field names to prevent users value displaying upon initial page entry  -->
<!-- 03/01/2007 CShimek      - #40035 revised title to include User Profile to match prototype and make title unique in use case -->
<!-- 02/06/2009 CShimek      - #56860 add Back to Top  -->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->
<!--html:html locale="true"-->
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - userProfileDepartmentSearch.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<script language="JavaScript" src="/<msp:webapp/>js/userProfiles/userProfileDepartmentSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script language="JavaScript">
var currentSelectedDepartment=0;

function setDepartment(deptId,orgCode)
{
	var idValue = document.getElementById(deptId);
	var deptName = idValue.value;
	var element=document.getElementById('hiddenVal');
	element.value=deptId+"+" + deptName+"+" +orgCode;	
	currentSelectedDepartment=deptId+"+" +deptName+"+" +orgCode;

}

function load(file,target) {
   
        window.location.href = file;
}

function loadDepartment(file){
	var myURL=file + "&selectedValue=" + currentSelectedDepartment;
	load(myURL,top.opener);
	window.close();
}

</script>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE  -->
<table width="100%" border="0" align="center">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.userProfile"/> - <bean:message key="title.departmentSearch"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE  -->
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter search criteria and then select "Find Departments" button to perform search.</li>
				<li>Select Department/UserProfile then click "Select" button.</li>
			</ul>
		</td>
	</tr>
</table>
<table width="98%">
	<tr>
    	<td class="required">At least one field is required for search.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE  -->

<html:form action="/displayDepartmentSearchResults" target="content" focus="searchDepartmentName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|252">	
<table width="98%" border="0" cellspacing="0" align="center" class="borderTableBlue">
	<tr bgcolor="#f0f0f0">
		<td>
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName" /></td>
					<td class="formDe" colspan="2"><html:text name="userProfileForm" property="searchDepartmentName" size="60" maxlength="60"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode" /></td>
					<td class="formDe" colspan="2"><html:text name="userProfileForm" property="searchDepartmentId" size="3" maxlength="3"/></td>
				</tr>
				<tr>
					<td class="formDeLabel"></td>
					<td class="formDe" colspan="3">
						<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
							<bean:message key="button.findDepartments"></bean:message>
						</html:submit>
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
							<bean:message key="button.refresh"></bean:message>
						</html:submit></td>
				</tr>
			</table>
		</td>
	</tr>       
</table>

<logic:notEmpty name="userProfileForm" property="departments">
	 <tr>
        <td align="center">
<!-- BEGIN INSTRUCTION TABLE  -->
           	<table width="98%">
				<tr>
					<td align="center"> 
					    <bean:write name="userProfileForm" property="departmentsSize"/> search results found.
				    </td>
				</tr>
			</table>
<!-- END INSTRUCTION TABLE  -->
		</td>
	</tr>
         
<!--BEGIN SEARCH RESULTS TABLE-->
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td valign="top" width="99%">
				<div class="scrollingDiv200" >
				<table width="100%" border="0" cellpadding="2" cellspacing="1">
				   <jims:sortResults beanName="userProfileForm" results="departments" 
                          primaryPropSort="agencyName" primarySortType="STRING"
                          defaultSort="true" defaultSortOrder="ASC" sortId="1" hideMe="true" />
					<logic:iterate id="agencyIndex" name="userProfileForm" property="departments">
						<tr>
							<td class="formDeLabel" colspan="3"><bean:message key="prompt.agencyName" />:&nbsp;<bean:write name="agencyIndex" property="agencyName" /></td>
						</tr>
						<tr>
							<td width="3%" class="formDeLabel">&nbsp;</td>
							<td class="formDeLabel" valign="top"><bean:message key="prompt.departmentName"/></td>
							<td class="formDeLabel" valign="top"><bean:message key="prompt.departmentCode"/></td>
						</tr>
					   <jims:sortResults beanName="userProfileForm" results="departments" 
                          primaryPropSort="agencyName" primarySortType="STRING"
                          secondPropSort="departmentName" secondarySortType="STRING"
                          defaultSort="true" defaultSortOrder="ASC" sortId="2" />                
						<logic:iterate id="departmentIndex" name="agencyIndex" property="departments" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td>
									<input type="radio" name="selectedDepartment" onclick="setDepartment('<bean:write name="departmentIndex" property="departmentId"/>','<bean:write name="departmentIndex" property="orgCode"/>')" >								
								</td>
								<td><bean:write name="departmentIndex" property="departmentName"/><input type="hidden" id="<bean:write name="departmentIndex" property="departmentId"/>" value="<bean:write name="departmentIndex" property="departmentName" filter="false"/>" >  </td>
								<td><bean:write name="departmentIndex" property="departmentId"/></td>
							</tr>
						</logic:iterate>
					</logic:iterate>
					<html:hidden  name="userProfileForm" property="selectedValue" styleId="hiddenVal"/>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<br>
	
</logic:notEmpty>
    <table border="0" width="100%">
		<TBODY>
			<tr>
				<td align="center"><html:submit property="submitAction">
					<bean:message key="button.back"></bean:message>			
				</html:submit> 
				<logic:notEmpty name="userProfileForm" property="departments">
				<html:submit property="submitAction" onclick="return validateRadioFields(this.form);">
					<bean:message key="button.select"></bean:message>
				</html:submit>
			</logic:notEmpty>
			<html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit></td>
			</tr>
		</TBODY>
	</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>