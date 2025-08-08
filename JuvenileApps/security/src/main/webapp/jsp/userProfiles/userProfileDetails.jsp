<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- UGopinath	02/01/06	Create JSP -->
<!-- 10/03/2006	CShimek     Defect#35098 added logic tags to only display "Close Window" button when page accessed as popup(Officer Profile - Manager Search)  -->
<!-- 01/10/2007 CShimek     #38306 add multiple submit functionality  -->
<!-- 02/08/2007 CShimek     #39282 corrected problem of JIMS Info displaying for non-generic users, added note about action values -->
<!-- 03/20/2007 CShimek 	#40528 added logic tag around Manage User Group and Assign Roles but for user type not equal LA	 -->
<!-- 03/29/2007 CShimek     #40790 revised logic tag for userIsLA to use userProfileForm -->
<!-- 04/23/2007 CShimek     #40454 added logic tags for allowUserUpdate around buttons -->
<!-- 05/08/2007 CShimek		#41271 remove badge and other Id number -->
<!-- 08/10/2007 CShimek     #44357 remove public indicator display -->
<!-- 10/03/2007 CShimek		#45742 added logic tags around delete button based on training value -->
<!-- 04/09/2008 DWilliamson	ER #46085 Changed Delete to Inactivate -->
<!-- 02/06/2009 CShimek     #56860 add Back to Top  -->
<!-- 10/09/2015 RCapestani  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --> 


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - userProfileDetails.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
 
  function loadUser(file)
  {  	
  	var myURL = file+ "?userId=" + document.forms[0].uId.value; 
  	load( myURL,top.opener ) ;
  	window.close();
  }
  
  function load(file,target) 
  {
     window.location.href = file;
  }
 
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/handleUserProfileSelection">

<!-- BEGIN HEADING TABLE -->
<logic:notEqual name="userProfileForm" property="action" value="inactivate">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|250">	
<table align="center">
	<tr>
     	<td class="header"><bean:message key="title.viewDetails"/></td>
   	</tr>
</table>
</logic:notEqual>
<logic:equal name="userProfileForm" property="action" value="inactivate">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|97">	
<table align="center">
	<tr>
      <td class="header" align="center">
           <bean:message key="prompt.inactivate"/>&nbsp;<bean:message key="prompt.user"/>&nbsp;<bean:message key="title.summary"/>                
      </td>	
	</tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- BEGIN INSTRUCTION TABLE --> 
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<li>The following User Profile will be inactivated when you select the Finish button.</li>
				<li>Select the Cancel button if you do NOT want to inactivate this User Profile.</li>  
			</ul> 
		</td>
	</tr>
</table>

</logic:equal>
<logic:equal name="userProfileForm" property="action" value="submit">
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<li>Select the Close Window button at bottom of page to exit.</li>  
			</ul> 
		</td>
	</tr>
</table>	
</logic:equal>
<!-- END HEADING TABLE -->
<br>
<%-- this page displays user information 2 ways:
	1. Using collection userProfileDetails (part of change for matching user list)
	2. Using individual values from UserProfileForm (needed for hyperlink pop-up, uses UserResponseEvent)--%>
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
	<tr>
	   	<td class="detailHead" nowrap><bean:message key="prompt.generalInfo"/></td>
	</tr>		
	<tr>
		<td align="center" bgcolor="#f0f0f0">
			<table cellpadding=2 cellspacing=1 border=0>
			<logic:notEqual name="userProfileForm" property="action" value="submit">
			<logic:iterate id="userIndex" name="userProfileForm" property="userProfileDetails">
 				<tr> 
    				<td class="formDeLabel"width="1%" nowrap><bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userName.formattedName"/> 
    					</td>
    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="logonId"/></td> 
    				<td><bean:define name="userIndex" property="logonId" id="logon"/></td> 
    				<td><input type="hidden" name="uId" value="<%=logon%>"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateOfBirth"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ssn"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="ssn.formattedSSN"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
    				<td class="formDe" colspan="3"><bean:write name="userIndex" property="genericUserType"/></td>    				
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userType"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userType"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userStatus"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userStatus"/></td> 
 				</tr>
				<tr>
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agency"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="agencyName"/></td>
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="agencyId"/></td>   				
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="departmentName"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="departmentId"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.operatorId"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="OPID"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.orgCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="orgCode"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="phoneNum.formattedPhoneNumber"/> <logic:notEmpty name="userIndex" property="phoneNum.ext"><bean:message key="prompt.ext"/> <bean:write name="userIndex" property="phoneNum.ext"/></logic:notEmpty></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="email"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createDate"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="createDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createTime"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="createTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createdBy"/></td>
    				<td class="formDe" colspan=3><bean:write name="userIndex" property="createdBy"/></td>    				    				
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.activationDate"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="activationDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.activationTime"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="activationTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap>Activated By</td>
    				<td class="formDe" colspan=3><bean:write name="userIndex" property="activatedBy"/></td>    				    				
 				</tr> 
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationTime"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.request"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationReqDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.request"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationReqTime"/></td> 
 				</tr>		
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivatedBy"/></td>
    				<td class="formDe" colspan=3><bean:write name="userIndex" property="inactivatedBy"/></td>    				    				
 				</tr> 
 					<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="transferDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="transferTime"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferRequest"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="deptTransferReqDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferRequest"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="deptTransferReqTime"/></td> 
 				</tr>	
 				 <tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.requestor"/> <bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="requestorName.formattedName"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.training"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="trainingInd"/></td> 
 				</tr>	
<%--  enableDelete value needed for disabling delete button  --%>
<%--  Commented out the following logic because the user should be able to inactivate a user profile per ER #46085. --%>				
				<%--  <logic:equal name="userIndex" property="trainingInd" value="Y">
				<logic:notPresent name="enableDelete" >
					<bean:define id="enableDelete" value="Y" />
				</logic:notPresent>	
				</logic:equal> --%>
 			</logic:iterate>
			</logic:notEqual>
			<logic:equal name="userProfileForm" property="action" value="submit">
 				<tr> 
    				<td class="formDeLabel"width="1%" nowrap><bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="userName.formattedName"/> 
    					</td>
    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="logonId"/></td> 
    				<td><%-- <bean:define name="userProfileForm" property="logonId" id="logon"/> --%></td> 
    				<td><%-- <input type="hidden" name="uId" value="<%=logon%>"/> --%></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateOfBirth"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ssn"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="ssn.formattedSSN"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
    				<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="genericUserType"/></td>    				
    				<%--<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.public"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="publicIndString"/></td> --%> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userType"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="userType"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userStatus"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="userStatus"/></td> 
 				</tr>
				<tr>
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agency"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="agencyName"/></td>
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyCode"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="agencyId"/></td>   				
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="departmentName"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="departmentId"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.operatorId"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="OPID"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.orgCode"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="orgCode"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="phoneNum.formattedPhoneNumber"/> <logic:notEmpty name="userProfileForm" property="phoneNum.ext"><bean:message key="prompt.ext"/> <bean:write name="userProfileForm" property="phoneNum.ext"/></logic:notEmpty></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="email"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createDate"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="createDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createTime"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="createTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.createdBy"/></td>
    				<td class="formDe" colspan=3><bean:write name="userProfileForm" property="createdBy"/></td>    				    				
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.activationDate"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="activationDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.activationTime"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="activationTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap>Activated By</td>
    				<td class="formDe" colspan=3><bean:write name="userProfileForm" property="activatedBy"/></td>    				    				
 				</tr> 
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="inactivationDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="inactivationTime"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.request"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="inactivationReqDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.request"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="inactivationReqTime"/></td> 
 				</tr>		
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivatedBy"/></td>
    				<td class="formDe" colspan=3><bean:write name="userProfileForm" property="inactivatedBy"/></td>    				    				
 				</tr> 
 					<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="transferDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="transferTime"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferRequest"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="deptTransferReqDate"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferRequest"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="deptTransferReqTime"/></td> 
 				</tr>	
 				 <tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.requestor"/> <bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="requestorName.formattedName"/></td>    				
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.training"/></td>
    				<td class="formDe"><bean:write name="userProfileForm" property="trainingInd"/></td> 
 				</tr>	
 			</logic:equal>
			</table>
			<html:hidden name="userProfileForm" property="action" value="details"/>
<%--  enableDelete value needed for disabling delete button  --%>
<%--  Commented out the following logic because the user should be able to inactivate a user profile per ER #46085. --%>			
		<%--	<logic:equal name="userProfileForm" property="trainingInd" value="Y">
				<logic:notPresent name="enableDelete" >
					<bean:define id="enableDelete" value="Y" />
				</logic:notPresent>	--%>
			</logic:equal>			
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN JIMS2 INFORMATION TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
<%--  Note about action values:
	Action equals "View" on all hyperlinks except from Officer Profile - Manager Search
	Action equals "details" when only 1 match is found for User Profile Search 
	Action equal "submit" on hyperlink from Officer Profile - Manager Search 
 --%>
<logic:notEqual name="userProfileForm" property="action" value="submit"> 
	<logic:iterate id="userIndex" name="userProfileForm" property="userProfileDetails">
		<logic:equal name="userIndex" property="genericUserType" value="NON-GENERIC">
			<tr>
			   <td class="detailHead" nowrap><bean:message key="title.heading"/> <bean:message key="prompt.info"/></td>
			</tr>		
			<tr bgcolor="#f0f0f0">
				<td>
					<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#f0f0f0">
							<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userName"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="jims2UserName.formattedName" /></td> 
				 			</tr>
				 			<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS2UserId"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="jims2UserId"/></td> 
				 			</tr>
						   		<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="jims2DepartmentName"/></td> 
				 			</tr>
				 			<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS2Password"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="jims2Password"/></td> 
				 			</tr>
				 			<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.forgottenPasswdPhrase"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="forgottenPasswdPhrase"/></td> 
				 			</tr>
				 			
				 			<tr> 
				    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.answer"/></td>
				    			<td class="formDe"><bean:write name="userIndex" property="answer"/></td> 
				 			</tr>
					</table>
				</td>
			</tr>
		</logic:equal>
	</logic:iterate>
</logic:notEqual>
<logic:notEqual name="userProfileForm" property="action" value="submit">
	<logic:equal name="userProfileForm" property="genericUserType" value="Non-Generic">
		<logic:empty name="userProfileForm" property="userProfileDetails">	
			<tr>
			   <td class="detailHead" nowrap><bean:message key="title.heading"/> <bean:message key="prompt.info"/></td>
			</tr>		
			<tr bgcolor="#f0f0f0">
				<td>
					<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#f0f0f0">
						<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userName"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="jims2UserName.formattedName" /></td> 
			 			</tr>
			 			<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS2UserId"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="jims2UserId"/></td> 
			 			</tr>
					   		<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="jims2DepartmentName"/></td> 
			 			</tr>
			 			<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMS2Password"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="jims2Password"/></td> 
			 			</tr>
			 			<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.forgottenPasswdPhrase"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="forgottenPasswdPhrase"/></td> 
			 			</tr>
			 			<tr> 
			    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.answer"/></td>
			    			<td class="formDe"><bean:write name="userProfileForm" property="answer"/></td> 
			 			</tr>
					</table>
				</td>
			</tr>
		</logic:empty>	
	</logic:equal>
</logic:notEqual>
</table>
<!-- END JIMS2 INFORMATION TABLE -->
<br>
<!-- BEGIN COMMENTS TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
	   <td class="detailHead" nowrap><bean:message key="prompt.comments"/></td>
	</tr>		
	<tr bgcolor="#f0f0f0">
		<td>
			<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#f0f0f0">
			<logic:iterate id="userIndex" name="userProfileForm" property="userProfileDetails">
		   		<tr>
		   			<td valign="top">
 		   			   <bean:write name="userIndex" property="comments"/>
		   			</td>						
				</tr>
			</logic:iterate>
			</table>
		</td>
	</tr>
</table>
<!-- END COMMENTS TABLE -->
<br>

<!-- BEGIN BUTTON TABLE -->
<html:form action="/handleUserProfileSelection.do"> 
<logic:equal name="userProfileForm" property="action" value="submit">
	<table border="0" align="center">
		<tr>
			<td class="boldText" align="center">
				<input type="button" name="close" value="Close Window" onclick="javascript:window.close();">
			</td>
		</tr>
	</table>	
</logic:equal>
<logic:notEqual name="userProfileForm" property="action" value="inactivate">
<logic:notEqual name="userProfileForm" property="action" value="submit">
<table border="0" align="center">
	<tr>
		<td valign="top">
			<html:submit property="submitAction" >
			  	<bean:message key="button.back"></bean:message>
		  	</html:submit>					
		</td>
		<logic:equal name="userProfileForm" property="allowUserUpdate" value="Y">
			<td valign="top">
				<html:submit property="submitAction">
					<bean:message key="button.update" ></bean:message>
				</html:submit>
			</td>
			<td valign="top">
				<html:submit property="submitAction">
					<bean:message key="button.inactivate"></bean:message>
				</html:submit>
			</td>	
			<td valign="top">	
   				<html:submit property="submitAction">
	    			<bean:message key="button.transferUserProfile"></bean:message>
	   			</html:submit>
			</td>  
			<td valign="top">
				<html:submit property="submitAction" >
					<bean:message key="button.viewHistory"></bean:message>
				</html:submit>
			</td>     			 
		</logic:equal>	
	</tr>
</table>
<logic:equal name="userProfileForm" property="userIsLA" value="N">
	<logic:equal name="userProfileForm" property="allowUserUpdate" value="Y">
	<table border="0" align="center">
		<tr>		
			<td valign="top">
				<html:submit property="submitAction">
					<bean:message key="button.manageUserGroup"></bean:message>
				</html:submit>	
				<input type="button" name="assignRoles" value="Assign Roles" onclick="loadUser('/<msp:webapp/>displayAssignUserRoles.do');"/>		  
			</td>
		</tr>
	</table>
	</logic:equal>
</logic:equal>
</logic:notEqual>
</logic:notEqual>
</html:form>
<html:form action="/submitUserProfileDelete" target="content">
<logic:equal name="userProfileForm" property="action" value="inactivate">
<table align="center">
   <tr>
      <td align="center">
      <html:submit property="submitAction">
			<bean:message key="button.back"></bean:message>
		</html:submit>&nbsp; 
        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
			<bean:message key="button.finish"></bean:message>
		</html:submit>&nbsp; 
        <html:submit property="submitAction">
            <bean:message key="button.cancel"></bean:message>
        </html:submit>    
      </td>
   </tr>
    <tr>
      <td align="center">
      	<html:submit property="submitAction">
			<bean:message key="button.returnProfile"></bean:message>
		</html:submit>
	  </td>
	</tr>
</table>
</logic:equal>
</html:form>	
<!-- END BUTTON TABLE -->   	 

</html:form>	

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>