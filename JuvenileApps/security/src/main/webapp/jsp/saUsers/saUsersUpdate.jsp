<!DOCTYPE HTML>
<!-- Used to  Results for Security Administrator Users. -->
<!--MODIFICATIONS -->
<!-- SPrakash 05/10/2005	Create JSP -->
<!-- CShimek  03/17/2006	defect#29751 add comma after last name -->
<!-- CShimek  01/12/2007    #38306 add multiple submit functionality  -->
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
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />

<html:base />

<title><bean:message key="title.heading"/> saUsersUpdate.jsp</title>
<!-- STRUTS VALIDATIONS-->
<%--html:javascript formName="SAUsersForm" />--%>

<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/saUsers/saUsersUpdate.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)"> 

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saUpdateUserType"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
    <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
    </tr>   	  
</table>
<!-- END ERROR TABLE -->
					
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Select which departments the user will be a liaison for.</li>
        <li>If any changes are needed, select the Back button.</li>
      </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<html:form action="/displaySAUserDept" target="content" >
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|159">				
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.updateSAUser" /></td>
				</tr>
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<!-- VARIABLES NEEDED FOR DISPLAY -->		
							<tr class="formDeLabel">
								<td><bean:message key="prompt.name" /></td>
								<td><bean:message key="prompt.agency" /></td>
								<td width="1%" align="center" nowrap><bean:message key="prompt.isLiaison" /></td>
								<td width="1%" align="center" nowrap><bean:message key="prompt.isASA" /></td>
								<td width="1%" align="center" nowrap><bean:message key="prompt.isSA" /></td>
								<td width="1%" align="center" nowrap><bean:message key="prompt.isMA" /></td>
							</tr>
					<!-- display detail info -->
							<tr>
							<td class="boldText"><bean:write name="saUsersForm" property="selectedUser.userLastName" />,&nbsp;
							<bean:write name="saUsersForm" property="selectedUser.userFirstName" /></td>
							<td><bean:write name="saUsersForm" property="selectedUser.agencyName" /></td>
							<td align="center">
				               <html:multibox property="selectedUserTypes" onclick="uncheckOther(this)">
				               	<bean:write name="saUsersForm" property="userTypeIdLA" /> 
				               </html:multibox>																			
							</td>
							<td align="center">
								<logic:equal name="saUsersForm" property="selectedUser.agencyHasSA" value="true">
					               <html:multibox property="selectedUserTypes" onclick="uncheckOther(this)">
					               	<bean:write name="saUsersForm" property="userTypeIdASA" /> 
					               </html:multibox>																			
								</logic:equal>
							</td>
							<td align="center">
								<logic:equal name="saUsersForm" property="selectedUser.agencyHasSA" value="true">
					               <html:multibox property="selectedUserTypes" onclick="uncheckOther(this)">
					               	<bean:write name="saUsersForm" property="userTypeIdSA" /> 
					               </html:multibox>																			
								</logic:equal>
							</td>
							<td align="center">
				               <html:multibox property="selectedUserTypes" onclick="uncheckOther(this)">
				               	<bean:write name="saUsersForm" property="userTypeIdMA" /> 
				               </html:multibox>																			
							</td>
						</tr>
			</table>
		</td>
		</tr>
	</table>
	</td>
</tr>

	<br>
<!-- BEGIN BUTTON TABLE -->	
	<table align="center">
	    <tr>
	       <td align="center">
	    		<html:button property="button.back" onclick="history.back();">
					<bean:message key="button.back"></bean:message>
			  	</html:button>
              	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
                  <bean:message key="button.next"></bean:message>
              	</html:submit>

			</td>
			<html:form action="/displaySAUserSearch" target="content" >
			<td>
                   <html:submit property="submitAction">
	                  <bean:message key="button.cancel"></bean:message>
	              </html:submit>
             </td>
	       </html:form>
	    </tr>    
	</table>
<!-- END BUTTON TABLE -->
</html:form>
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>