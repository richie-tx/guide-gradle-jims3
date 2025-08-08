<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- ?          ?           Created JSP -->
<!-- 01/10/2007 CShimek     - #38306 add multiple submit functionality  -->
<!-- 05/08/2007 CShimek		- #41271 remove badge and other Id number -->
<!-- 08/10/2007 CShimek     - #44357 remove public indicator display -->
<!-- 02/06/2009 CShimek     - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>

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
<title><bean:message key="title.heading"/> - userProfileDelete.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<!-- BEGIN HEADING TABLE -->
<table align="center">
	<tr>
      <td class="header" align="center">
           <bean:message key="prompt.inactivate"/>&nbsp;<bean:message key="prompt.user"/>&nbsp;Profile&nbsp;<bean:message key="title.summary"/>                
      </td>	
	</tr>
</table>
<!-- END HEADING TABLE -->
<!--END HEADER TAG-->
<!--  BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!--  END ERROR TABLE -->
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
<!-- END INSTRUCTION TABLE -->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)"> 
<!-- BEGIN FORM -->			
<html:form action="/submitUserProfileDelete" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|97">		

<!-- BEGIN DETAILS TABLE -->
<table width="98%" border="0" cellspacing="0" align="center" class="borderTableBlue">
  <tr>
 	 <td class="detailHead" nowrap><bean:message key="prompt.generalInfo" /></td>
  </tr>
  <tr bgcolor="#f0f0f0">
     <td>
       <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
				
         <tr>
           <td class="formDeLabel"width="1%" nowrap><bean:message key="prompt.name"/></td>
           <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="userName.formattedName"/>
          </td>
         </tr>
         <tr>
           <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateOfBirth"/></td>
           <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
         </tr>
         <tr>  
           <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ssn"/></td>
            <td class="formDe" colspan="2">
            <bean:write name="userProfileForm" property="ssn.formattedSSN"/></td>
         </tr> 
         <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="departmentId" /></td>		     
		</tr>
	    <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="departmentName" /></td>		     
	    </tr>
	    <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.orgCode"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="orgCode" /></td>		     
	    </tr> 
	    <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone"/></td>
	         <td class="formDe" colspan="2">
	         <bean:write name="userProfileForm" property="phoneNum.formattedPhoneNumber" /></td>		     	         
	    </tr> 
	    <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userType"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="userType" /></td>		     
	    </tr> 
	    <tr> 
           <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
           <td class="formDe" colspan="2">   	                             
              <bean:write name="userProfileForm" property="email"/>
           </td> 
         </tr>
	        <tr>
	          <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="genericUserType" /></td>		    		     	     
	       </tr>
	       <jims2:isAllowed requiredFeatures='<%=Features.JCW_SECSERVPROV%>'>
	       <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.uvCodeGeneration"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="uvCodeGeneration" /></td>		     
	       </tr>
	       </jims2:isAllowed>
	        <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.customCodeGeneration"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="customCodeGeneration" /></td>		     	         
	       </tr>
	        <tr>
	         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.requestor"/> <bean:message key="prompt.name"/></td>
	         <td class="formDe" colspan="2"><bean:write name="userProfileForm" property="requestorName.formattedName" /></td>		     	         
	       </tr> 
      	
 	   </table>
     </td>
   </tr>            
 </table>
 <!-- END DETAILS TABLE -->
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
			<bean:message key="button.back"></bean:message>
		</html:submit>&nbsp; 
        <html:submit property="submitAction">
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
<!-- END BUTTON TABLE -->  

</html:form>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>