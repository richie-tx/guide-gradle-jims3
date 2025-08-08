<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- ?          ?           Created JSP -->
<!-- 01/10/2007 CShimek     - #38306 add multiple submit functionality  -->
<!-- 05/08/2007 CShimek		- #41271 remove badge and other Id number -->
<!-- 08/10/2007 CShimek     - #44357 remove public indicator display -->
<!-- 02/06/2009 CShimek     - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
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

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title>Security-userProfiles/userProfileDeleteSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!-- BEGIN HEADING TABLE -->
<table align="center">
	<tr>
      <td class="header" align="center">
           <bean:message key="prompt.inactivate"/>&nbsp;<bean:message key="prompt.user"/>&nbsp;Profile&nbsp;<bean:message key="prompt.confirmation"/>                
      </td>	
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE  -->
<table width="98%" border="0">
	<TBODY>
		<tr>
			<td align="center" class="confirm"> The following User Profile has been successfully inactivated:
			<bean:write name="userProfileForm" property="logonId"/> </td>
		</tr>
	</TBODY>
</table>
<!-- END INSTRUCTION TABLE -->


<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/submitUserProfileCreateUpdate">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|98">	
<!-- begin detail table --> 
<br> 
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
	<tr>
	   	<td class="detailHead" nowrap><bean:message key="prompt.generalInfo"/></td>
	</tr>		
	<tr>
		<td align="center" bgcolor="#f0f0f0">
			<table cellpadding="2" cellspacing="1" border="0">
			<logic:iterate id="userIndex" name="userProfileForm" property="userProfileDetails">
 				<tr> 
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userName.formattedName"/></td>
    				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="logonId"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.dateOfBirth"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.ssn"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="ssn.formattedSSN"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
    				<td class="formDe" colspan="3"><bean:write name="userIndex" property="genericUserType"/></td>    				
 				</tr>
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.userType"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userType"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.userStatus"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="userStatus"/></td> 
 				</tr>
				<tr>
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.agency"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="agencyName"/></td>
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.agencyCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="agencyId"/></td>   				
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="departmentName"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="departmentId"/></td> 
 				</tr>
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.operatorId"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="OPID"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.orgCode"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="orgCode"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.phone"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="phoneNum.formattedPhoneNumber"/> <bean:message key="prompt.ext"/> <bean:write name="userIndex" property="phoneNum.ext"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.email"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="email"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.createDate"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="createDate"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.createTime"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="createTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.createdBy"/></td>
    				<td class="formDe" colspan="3"><bean:write name="userIndex" property="createdBy"/></td>    				    				
 				</tr>
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.activationDate"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="activationDate"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.activationTime"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="activationTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap>Activated By</td>
    				<td class="formDe" colspan="3"><bean:write name="userIndex" property="activatedBy"/></td>    				    				
 				</tr> 
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.date"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationDate"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.inactivation"/> <bean:message key="prompt.time"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="inactivationTime"/></td> 
 				</tr>	
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap>Inactivated By</td>
    				<td class="formDe" colspan="3"><bean:write name="userIndex" property="inactivatedBy"/></td>    				    				
 				</tr> 
 				<tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.transferDate"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="transferDate"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.transferTime"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="transferTime"/></td> 
 				</tr>	
 				 <tr> 
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.requestor"/> <bean:message key="prompt.name"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="requestorName.formattedName"/></td>    				
    				<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.training"/></td>
    				<td class="formDe"><bean:write name="userIndex" property="trainingInd"/></td> 
 				</tr>	
 			</logic:iterate>
			</table>
			<html:hidden name="userProfileForm" property="action" value="details"/>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN JIMS2 INFORMATION TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
	   <td class="detailHead" nowrap><bean:message key="title.heading"/> <bean:message key="prompt.info"/></td>
	</tr>		
	<tr bgcolor="#f0f0f0">
		<td>
			<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#f0f0f0">
			<logic:iterate id="userIndex" name="userProfileForm" property="userProfileDetails">
			<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.userName"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="jims2UserName.formattedName" /></td> 
 			</tr>
 			<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.JIMS2UserId"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="jims2UserId"/></td> 
 			</tr>
		   		<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="jims2DepartmentName"/></td> 
 			</tr>
 			<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.JIMS2Password"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="jims2Password"/></td> 
 			</tr>
 			<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.forgottenPasswdPhrase"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="forgottenPasswdPhrase"/></td> 
 			</tr>
 			<tr> 
    			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.answer"/></td>
    			<td class="formDe"><bean:write name="userIndex" property="answer"/></td> 
 			</tr>
			</logic:iterate>
			</table>
		</td>
	</tr>
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
		   		<tr>
		   			<td valign="top">
  	                   <logic:equal name="userProfileForm" property="comments" value="">Not Available</logic:equal>		   			
		   			   <bean:write name="userProfileForm" property="comments"/>
		   			</td>						
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END COMMENTS TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table align="center">
   <tr>
     <td align="center">
    
        <html:submit property="submitAction">
			<bean:message key="button.mainPage"></bean:message>
		</html:submit>&nbsp;
      
        <html:submit property="submitAction">
            <bean:message key="button.searchUserProfile"></bean:message>
        </html:submit>&nbsp;
         <html:submit property="submitAction">
            <bean:message key="button.userProfileSearchResults"></bean:message>
        </html:submit>           
    
    </td>  
  </tr>
</table>
<!-- END BUTTON TABLE -->  
</html:form>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>