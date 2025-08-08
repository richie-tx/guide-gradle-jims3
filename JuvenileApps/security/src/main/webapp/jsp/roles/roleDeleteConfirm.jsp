<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used to display associated user and groups before deleting -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<!-- CShimek 04/03/2007  #40922 add hyperlinks to user name and user group display values -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<title><bean:message key="title.heading"/> - roleDeleteConfirm.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function openWindow(url)
{
	var newWin = window.open(url, "pictureWindow", "height=400,width=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}
</script>

</head>
<!--END HEADER TAG--> 

<!--BEGIN BODY TAG--> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.deleteRole"/> <bean:message key="prompt.summary"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<html:form action="/submitRole" target="content">  
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|93"> 
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Select Finish to delete this role.</li>
      </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Delete <bean:write name="roleForm" property="roleName"/></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">						
						<table width="100%" cellpadding="4">
							<tr>
								<td colspan="2" class="formDeLabel"><bean:message key="prompt.followingUsersHaveRole"/></td>								
							</tr>
							<tr>
								<td colspan="2" class="formDeLabel"><bean:message key="prompt.userName"/></td>								
							</tr>
							<logic:empty  name="roleForm" property="users">
								<tr><td colspan="2" class="formDe">NOT AVAILABLE</td></tr>
							</logic:empty>
							<logic:iterate id="userNameIndex" name="roleForm" property="users" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td class="boldText">
									<a href="javascript:openWindow('displaySecurityInquiriesUserProfileDetails.do?action=view&logonId=<bean:write name="userNameIndex" property="userId"/>')" >          					    								
								   <bean:write name="userNameIndex" property="lastName" />, <bean:write name="userNameIndex" property="firstName" /> <bean:write name="userNameIndex" property="middleName" /></a>
								</td>  						
							</tr>
							</logic:iterate> 
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.userGroups"/></td>								
							</tr>
							<logic:empty  name="roleForm" property="userGroups">
								<tr><td colspan="2" class="formDe">NOT AVAILABLE</td></tr>
							</logic:empty>
							<!-- RESET VARIABLES NEEDED FOR DISPLAY -->
							<logic:iterate id="userGroupIndex" name="roleForm" property="userGroups" indexId="index2">
							<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td class="boldText" >
 									<a href="javascript:openWindow('displaySecurityInquiriesUserGroupDetails.do?action=view&groupId=<bean:write name="userGroupIndex" property="userGroupId"/>')"> 
									<bean:write name="userGroupIndex" property="name" /></a>
								</td>								
							</tr>
							</logic:iterate> 
						</table> 
					</td> 
				</tr>  
			</table> 
		</td>
	</tr>	
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">
		    <table cellpadding="4" cellspacing="0" width="98%">
		    	<tr>
		    		<td align="center">
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>&nbsp;		
   				    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
   				    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
   				    </td> 
   				</tr>
			</table>		   				     		    
		</td> 
	</tr>
</table>		
</html:form>		
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>