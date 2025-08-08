<!DOCTYPE HTML>
<!-- Used to Show Update Confirmation for Security Administrator Users. -->

<!--MODIFICATIONS -->
<!-- SPrakash 05/10/2005	 Create JSP -->
<!-- Modified 05/24/2005 -->
<!-- CShimek  03/17/2006	 defect#29751 add comman after last name -->
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
<title><bean:message key="title.heading"/> saUsersUpdateConfirm.jsp</title>
<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saUpdateUserConfirm"/></td>
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
<table align="center" width="98%" border="0">
   <tr>
     <td align="center" class="confirm">The following SA User has been successfully updated.</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENTR TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySAUserSummary" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|158">		
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.manageSAUsers" /></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" width="100%" cellspacing="1" cellpadding="2">
							<tr class="formDeLabel">
								<td><bean:message key="prompt.name" /></td>
								<td><bean:message key="prompt.userType" /></td>
								<td><bean:message key="prompt.agencyDepartment" /></td>
							</tr>
							<tr>
								<td class="boldText" valign="top"> 
									<bean:write name="saUsersForm" property="selectedUser.userLastName" />,&nbsp;
									<bean:write name="saUsersForm" property="selectedUser.userFirstName" />
								</td>
								<td valign="top">
									<bean:write name="saUsersForm" property="selectedUserTypeDesc" />
								</td>
								<td>
									<table border="0">
										<tr>
											<td class="boldText">
												<bean:write name="saUsersForm" property="selectedUser.agencyName" />
											</td>
										</tr>										
										<tr>
											<td>
												<logic:iterate id="deptIndex" name="saUsersForm" property="selDepartments" >
						                           <li>
						                           	<bean:write name="deptIndex" property="departmentName"/> 
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
		<br>
<!-- BEGIN BUTTON TABLE -->	
	<table align="center" width="98%">
	    <tr>
			<td align="center">			
				<html:button property="org.apache.struts.taglib.html.CANCEL" 
		   			onclick="document.location.href='/appshell/jsp/mainMenu.jsp'">
					<bean:message key="button.mainPage"></bean:message>
		  		</html:button> 
			</td>
	    </tr>    
	</table>
<!-- END BUTTON TABLE -->		
		</td>
	</tr>		
</html:form>	
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>