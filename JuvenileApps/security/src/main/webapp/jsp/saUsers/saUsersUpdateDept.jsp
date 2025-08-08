<!DOCTYPE HTML>
<!-- Used to  Results for Security Administrator Users. -->
<!--MODIFICATIONS -->
<!-- SPrakash 05/10/2005	Create JSP -->
<!-- CShimek  03/24/2006	Corrected spelling -->
<!-- CShimek  05/18/2006	defect#31301 add js and hidden fields to check existing user departments -->
<!-- LDeen    03/30/2007	defect#40530 duplicate page titles
<!-- CShimek  02/06/2009    #56860 add Back to Top  -->


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> saUserUpdateDept.jsp</title>

<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/saUsers/saUsersUpdateDept.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onload="setCurrentDepts();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
  <tr> 
    <td align="center" class="header"><bean:message key="title.saUpdateUserDept"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Select an SA User Type and select Next button.</li>
        <li>Select Back button to select a different user to update.</li>
      </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySAUserDeptUpdate" target="content" >	
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|156">	
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Select Departments for Liaisons</td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" width="100%" cellspacing="1" cellpadding="2">
							<tr class="formDeLabel">
								<logic:equal name="saUsersForm" property="selUserTypeLA" value="true">
									<td>
										<bean:message key="prompt.liaisonName" />
										<input type="hidden" name="uType" value="LA">
									</td>
								</logic:equal>
								<logic:equal name="saUsersForm" property="selUserTypeASA" value="true">
									<td>
										<bean:message key="prompt.asaName" />
										<input type="hidden" name="uType" value="ASA">
									</td>
								</logic:equal>
								<td><bean:message key="prompt.agencyDepartment" /></td>
							</tr>
							<tr>
								<td class="boldText" valign="top"> 
									<bean:write name="saUsersForm" property="selectedUser.userLastName" />
									<bean:write name="saUsersForm" property="selectedUser.userFirstName" />
								</td>
								<td>
									<table border="0">
										<tr>
											<td class="boldText">
												<bean:write name="saUsersForm" property="selectedUser.agencyName" /> <input type="checkbox" id="checkall" name="selectAll" value="<bean:write name="saUsersForm" property="selectedUser.agencyName" />" onclick="checkAllDepts(this, 'selectedDepartments')" />
											</td>
										</tr>										
										<tr>
											<td>  
												<logic:iterate id="deptIndex" name="saUsersForm" property="allDepartments" >
						                           <li>
						                           	<bean:write name="deptIndex" property="departmentName"/> 
<%--							                           <html:multibox property="selectedDepartments" onclick="uncheckCheckAll(this,'selectAll')"> --%>
							                           <input type="checkbox" id="<bean:write name="deptIndex" property="departmentId" />" name="selectedDepartments" value="<bean:write name="deptIndex" property="departmentId" />" onclick="uncheckCheckAll(this,'selectAll')"> 
						                           </li>
					                      		</logic:iterate> 
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END CONTENT TABLE -->
		<br>
<!-- BEGIN BUTTON TABLE -->	
	<table align="center">
	    <tr>
	       <td align="center">
	    		<html:button property="button.back" onclick="history.back();">
					<bean:message key="button.back"></bean:message>
			  	</html:button>
              <html:submit property="submitAction" onclick="return validateDepartmentSelect(this.form) && disableSubmit(this, this.form);">
                  <bean:message key="button.next"></bean:message>
              </html:submit>
				</html:form>
			</td>
			<td>	
              <html:form action="/displaySAUserSearch" target="content" >	
	              <html:submit property="submitAction">
	                  <bean:message key="button.cancel"></bean:message>
	              </html:submit>
              </html:form>
	       </td>
	    </tr>    
	</table>
<!-- END BUTTON TABLE -->
		</td>
</table>
<%-- hidden selectedDepartment fields for check box processing --%>
<table>
	<tr>
	<logic:iterate id="curIndex" name="saUsersForm" property="selectedDepartments" >
		<td>
			<input type="hidden" name="currentSelectedDepts" value="<%=curIndex%>">
		</td>
	</logic:iterate>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>