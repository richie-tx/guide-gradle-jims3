<!DOCTYPE HTML>
<!-- Used to Show Update Summary for Security Administrator Users. -->
<!--MODIFICATIONS -->
<!-- SPrakash 05/10/2005 Create JSP -->
<!-- CShimek  03/17/2006 defect#29751 add comman after last name -->
<!-- CShimek  05/18/2006 defect#31301 revised back button to use action instead of history so selectedDepartments could be set on from -->
<!-- CShimek  01/12/2007 #38306 add multiple submit functionality  -->
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

<title><bean:message key="title.heading"/> saUsersUpdateSummary.jsp</title>

<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saUpdateUserSummary"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>SA User will be updated when you select the Finish button.</li>
        <li>If any changes are needed, select the Back button.</li>
      </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySAUserSummary" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|154">	
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
											<logic:equal name="saUsersForm" property="selUserTypeMA" value="true">
												<td>
													All
												</td>
											</logic:equal>
											<logic:equal name="saUsersForm" property="selUserTypeMA" value="false">
												<td>
													<bean:write name="saUsersForm" property="selectedUser.agencyName" />
												</td>
											</logic:equal>
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
		</td>
	</tr>		
	<br>
	<tr>
		<td width="98%" align="center">
<!-- BEGIN BUTTON TABLE -->	
		<table align="center">
		    <tr>
	    	   <td align="center">
					<html:submit property="submitAction">
						<bean:message key="button.back" />
				  	</html:submit>
    	          <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
        	          <bean:message key="button.finish" />
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
	</tr>	
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>