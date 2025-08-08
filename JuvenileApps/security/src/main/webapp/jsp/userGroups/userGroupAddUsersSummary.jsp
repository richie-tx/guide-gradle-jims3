<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for activate, inactivate, create, and update summary pages and view details. -->
<!--MODIFICATIONS -->
<!-- CShimek 05/10/2005	 Create JSP -->
<!-- CShimek 05/10/2005	 Defect#31150 - add missing "Assign Roles" button for SA user -->
<%-- CShimek 08/07/2006  Activity#34092, revised column heading to Name --%>
<!-- CShimek 01/10/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->

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
<title><bean:message key="title.heading"/> - userGroupAddUsersSummary.jsp</title>

<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY --%>
<logic:notEqual name="userGroupForm" property="pageType" value="confirm">
<table width="100%">
	<tr>
		<td align="center" class="header">
			<bean:message key="title.userGroupCreate"/> - Add Users Summary

		</td>
	</tr>
</table>
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>The following User Group will be created when you select the Finish or Add Users button.</li>
			</ul>
		</td>
	</tr>
</table>
</logic:notEqual>
<logic:equal name="userGroupForm" property="pageType" value="confirm">
<table width="100%">
	<tr>
		<td align="center" class="header">
			<bean:message key="title.userGroupCreate"/> - Add Users Confirmation
		</td>
	</tr>
</table>
<table align="center" width="98%" border="0">
	<tr>
		<td class="confirm" align="center">Users Successfully Saved</td>
	</tr>
</table>
</logic:equal>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<td width="98%" align="center" valign="top">
		<!-- BEGIN USER GROUP INFORMATION TABLE -->			
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupInfo" /></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0 border="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="4" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="userGroupName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.userGroupDescription" /></td>
								<td class="formDe">
	                                 <logic:equal name="userGroupForm" property="userGroupDescription" value=""></logic:equal>             
								     <bean:write name="userGroupForm" property="userGroupDescription"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.status" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="groupStatus"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="agencyName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.createdBy" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="creatorName"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END USER GROUP INFORMATION TABLE -->
			<!-- BEGIN AGENCY INFORMATION TABLE -->			
		    <logic:equal name="userGroupForm" property="userType" value="MA">
		    <br>
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.agencyInfo" /></td>
					<td align="right"><img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0 border="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="4" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="agencyName"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		    </logic:equal>			
			<!-- END AGENCY INFORMATION TABLE -->					    
			<br>
			<!-- BEGIN USER MEMBERS TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupMembers" /></td>
					<td align="right">
					    <logic:equal name="userGroupForm" property="userType" value="MA">
						    <img src=/<msp:webapp/>images/step_3.gif hspace=0 vspace=0 border="0">
					    </logic:equal>
					    <logic:notEqual name="userGroupForm" property="userType" value="MA">
						    <img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0 border="0">
					    </logic:notEqual>

					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<div class="scrollingDiv100">
						<table width="100%" border="0" cellpadding="2">
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.name"/></td>
								<td class="formDeLabel" class="detailHead" nowrap><bean:message key="prompt.userId"/></td>								
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agency"/></td>								
							</tr>
						<logic:iterate id="currentIndex" name="userGroupForm" property="currentUsers" indexId="index">
			            <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td class="boldText" nowrap>
							    <bean:write name="currentIndex" property="lastName"/>, <bean:write name="currentIndex" property="firstName"/> <bean:write name="currentIndex" property="middleName"/>
							    <input type="hidden" name="user">
							</td>
							<td class="boldText">
							    <bean:write name="currentIndex" property="logonId"/>
							</td>
							<td class="boldText">
       				            <bean:write name='currentIndex' property='agencyName'/>
       				            <logic:notEqual name='currentIndex' property='departmentName' value="">
       				               	/ <bean:write name='currentIndex' property='departmentName'/>
       				            </logic:notEqual>
							</td>
						</tr>
						</logic:iterate>
 						</table>
 						</div>
					</td>
				</tr>
			</table>
			<!-- END USER MEMBER TABLE -->							
			<br>
		    <tr>
		    <td align="center">
<%-- these buttons need to be refined per action --%>
	        <html:form action="/submitUserGroupAddUsers" target="content"> 
		        <logic:notEqual name="userGroupForm" property="pageType" value="confirm">
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	        	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
			        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|71">		
				</logic:notEqual>
    		    <logic:equal name="userGroupForm" property="pageType" value="confirm">
	   	    	    <html:submit property="submitAction"><bean:message key="button.mainPage"></bean:message></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|265">		
			    </logic:equal>
	    		<logic:equal name="userGroupForm" property="userType" value="MA">
	   	        	    <input type="submit" name="submitAction" value="<bean:message key='button.assignRoles'/>">
	    				<input type="hidden" name="userGroupName" value="<bean:write name='userGroupForm' property='userGroupName'/>">
	    				<input type="hidden" name="userGroupDescription" value="<bean:write name='userGroupForm' property='userGroupDescription'/>">
	    				<input type="hidden" name="userGroupId" value="<bean:write name='userGroupForm' property='groupId'/>">
		    	</logic:equal>
	    		<logic:equal name="userGroupForm" property="userType" value="SA">
	   	        	    <input type="submit" name="submitAction" value="<bean:message key='button.assignRoles'/>">
	    				<input type="hidden" name="userGroupName" value="<bean:write name='userGroupForm' property='userGroupName'/>">
	    				<input type="hidden" name="userGroupDescription" value="<bean:write name='userGroupForm' property='userGroupDescription'/>">
	    				<input type="hidden" name="userGroupId" value="<bean:write name='userGroupForm' property='groupId'/>">
		    	</logic:equal>		    	
		    </html:form>
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>