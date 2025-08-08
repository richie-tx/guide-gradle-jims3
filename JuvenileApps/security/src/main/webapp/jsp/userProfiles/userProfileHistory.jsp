<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- UGopinath	02/01/2006	Create JSP -->
<!-- CShimek    01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek    02/06/2009  #56860 add Back to Top  -->
<!-- RYoung     10/19/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
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
<title><bean:message key="title.heading"/> - userProfileHistory.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/displayUserProfileHistory">  					
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|251">	 	   					
<!-- BEGIN TITLE TABLE -->
<table align="center">
   <tr>
     <td class="header"><bean:message key="title.viewUserProfileHistory"/></td>
   </tr>
</table>
<!-- END TITLE TABLE -->
<br>

<!-- BEGIN SUBTITLE TABLE -->
<table width="95%" border="0" align="center">	            
	<tr>
      	<td>
			<table align="center">
				<tr>
        			<td align="right" class="subhead"><bean:message key="prompt.userName"/>:&nbsp;</td>
        			<td><bean:write name="userProfileForm" property="userName.formattedName" /></td>
      			 	<td>&nbsp;&nbsp;&nbsp;</td>
        			<td align="right" class="subhead"><bean:message key="prompt.userId"/>:&nbsp;</td>
        			<td><bean:write name="userProfileForm" property="logonId"/></td>
      			</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END SUBTITLE TABLE -->

<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
	<tr>
		<td class="detailHead" nowrap>&nbsp;<bean:message key="prompt.historyInfo"/> 
		</td> 	
	</tr>
	<tr>
		<td >
			<table cellpadding="2" cellspacing="1" border="0">
		    <!-- display detail info -->
   					
   				<logic:iterate id="historyIndex" name="userProfileForm" property="userProfileHistory">
                	<tr>
                		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.change"/> <bean:message key="prompt.date"/></td>
			            <td class="formDe"><bean:write name="historyIndex" property="transactionDate" formatKey="date.format.mmddyyyy"/></td>
			            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.change"/> <bean:message key="prompt.time"/></td>
			            <td class="formDe"><bean:write name="historyIndex" property="changeTime" formatKey="time.format.HHmm"/></td>
      				</tr>
      			
      				<tr>
                		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.change"/> <bean:message key="prompt.by"/></td>
			            <td class="formDe"><bean:write name="historyIndex" property="changedByUserLastName"/>,<bean:write name="historyIndex" property="changedByUserFirstName"/></td>
			            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.change"/> <bean:message key="prompt.by"/> <bean:message key="prompt.userId"/></td>
			            <td class="formDe"><bean:write name="historyIndex" property="changedByLogonId"/></td>
      				</tr>
                	<tr>	                						
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.action"/></td>
			            <td class="formDe"><bean:write name="historyIndex" property="action"/></td>
			            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transaction"/></td> 
      		 			<td class="formDe"><bean:write name="historyIndex" property="transaction"/></td>
					</tr>
					<tr>
      		 			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.fieldChanged"/></td>
             			<td class="formDe" colspan="4"><bean:write name="historyIndex" property="fieldName"/></td>	
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.newValue"/></td>
             			<td class="formDe"><bean:write name="historyIndex" property="newValue"/></td>
             			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.oldValue"/></td>
             			<td class="formDe"><bean:write name="historyIndex" property="oldValue"/></td>
					</tr>
					<tr><td></td></tr>
						
					<tr></tr>	
				</logic:iterate>
			</table>	    	 
		</td>   		  
	</tr>
</table> 
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" align="center">
	<tr>     
      	<td valign="top">
			<html:submit property="submitAction"> <bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.searchUserProfile"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.userProfileSearchResults"/></html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>