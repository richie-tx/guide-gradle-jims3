<!DOCTYPE HTML>
<!-- 08/30/2006 Uma Gopinath - Create JSP -->
<%-- 12/21/2006 CShimek		 - revised helpfile reference value--%>
<%-- 02/07/2007 CShimek		 - #39318 completed changes to make page functional --%>
<%-- 06/04/2007 LDeen		 - #42564 changed path to app.js --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - juvenileWarrantDepartmentSearch.jsp</title>

<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/warrantDepartmentSearch.js"></script>
<script type='text/javascript'>
var currentSelectedDepartment=0;

function setDepartment(deptId){
	
	var idValue = document.getElementById(deptId);
	var deptName = idValue.value;
	var element=document.getElementById('hiddenVal');
	element.value=deptId + "+" + deptName;	
	currentSelectedDepartment=deptId + "+" + deptName;

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
</head>
<body>

<!-- BEGIN HEADING TABLE  -->
<table width="100%" border="0" align="center">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.department"/> <bean:message key="prompt.search"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE  -->
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter search criteria and then select "Find Departments" button to perform search.</li>
				<li>Select Department then click "Select" button.</li>
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
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<html:form action="/displayDepartmentSearchResults" target="content" focus="searchDepartmentName">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|##">
<table width="98%" border="0" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
				<tr>
					<td class=formDeLabel width=1% nowrap><bean:message key="prompt.departmentName" /></td>
					<td class=formDe><html:text name="juvenileWarrantForm" property="searchDepartmentName" size="60" maxlength="60" /></td>
				</tr>
				<tr>
					<td class=formDeLabel><bean:message key="prompt.departmentCode" /></td>
					<td class=formDe><html:text name="juvenileWarrantForm" property="searchDepartmentId" size="3" maxlength="3"/></td>
				</tr>
				<tr>
					<td class=formDeLabel></td>
                  	<td class=formDe>
						<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
							<bean:message key="button.findDepartments"></bean:message>
						</html:submit>  <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
							<bean:message key="button.refresh"></bean:message>
						</html:submit>
					</td>
				</tr>
			</table>
		</td>
	</tr>  

<logic:notEmpty name="juvenileWarrantForm" property="departments">
	<tr><td>&nbsp;</td></tr>   
	<tr>
		<td align="center" width="98%"> 
		    <bean:write name="juvenileWarrantForm" property="departmentSize"/> search results found.
    	</td>
	</tr>
<!--BEGIN SEARCH RESULTS TABLE-->
	<tr>
		<td valign=top width=99%>
			<div class=scrollingDiv200 >
				<table width="100%" border="0" cellpadding="2" cellspacing="1">
					<logic:iterate id="agencyIndex" name="juvenileWarrantForm" property="departments">
						<tr>
							<td class=formDeLabel colspan="3"><bean:message key="prompt.agencyName" />:&nbsp;<bean:write name="agencyIndex" property="agencyName" />
							</td>
						</tr>
						<tr>
							<td class=formDeLabel width="3%">&nbsp;</td>
							<td class=formDeLabel valign=top><bean:message key="prompt.departmentName"/></td>
							<td class=formDeLabel valign=top><bean:message key="prompt.departmentCode"/></td>
						</tr>
						<% int RecordCounter=0;
							String bgcolor="";
						%>
							<logic:iterate id="departmentIndex" name="agencyIndex" property="departments">
								<tr class= <% RecordCounter++;
									bgcolor = "alternateRow";                      
									if (RecordCounter % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor); %>>
									<td class=formDe>
							<!-- HR- Make this change to accommodate the single quote (') in DepartmentName text -->  	
										<input type="radio" name="selectedDepartment" onclick="setDepartment('<bean:write name="departmentIndex" property="departmentId"/>')" />
									</td>
									<td class=formDe><bean:write name="departmentIndex" property="departmentName"/><input type="hidden" id="<bean:write name="departmentIndex" property="departmentId"/>" value="<bean:write name="departmentIndex" property="departmentName" filter="false"/>" ></td>
									<td class=formDe><bean:write name="departmentIndex" property="departmentId"/></td>
								</tr>
							</logic:iterate>
					</logic:iterate>
					<html:hidden  name="juvenileWarrantForm" property="selectedValue" styleId="hiddenVal"/>
				</table>
			</div>
		</td>
	</tr>
</table>
</logic:notEmpty>

<table border="0" width="100%">
	<TBODY>
		<tr><td>&nbsp;</td></tr>   	
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.back"></bean:message>			
				</html:submit> 
				<logic:notEmpty name="juvenileWarrantForm" property="departments">
					<html:submit property="submitAction" onclick="return validateRadioFields() && disableSubmit(this, this.form);">
						<bean:message key="button.select"></bean:message>
					</html:submit>
				</logic:notEmpty>
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
			</td>
		</tr>
	</TBODY>
</table>
    
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
