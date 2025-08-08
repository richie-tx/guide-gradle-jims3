<!DOCTYPE HTML>
<!-- 12/07/2006 CShimek		- removed "Not Available" in comments (found while testing other change).  -->
<!-- 01/10/2007 CShimek		- #38306 add multiple submit functionality  -->
<!-- 03/20/2007 CShimek		- #40528 added logic tag around Manage User Group and Assign Roles but for user type not equal LA	 -->
<!-- 05/04/2007 CShimek		- #41450 added "Search Officer" button for create summary page  -->
<!-- 05/08/2007 CShimek		- #41271 remove badge and other Id number -->
<!-- 08/10/2007 CShimek     - #44357 remove public indicator display -->
<!-- 02/06/2009 CShimek     - #56860 add Back to Top  -->
<!-- 10/20/2015 RYoung      - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="naming.Features" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />

<title><bean:message key="title.heading"/> - userProfileCreateUpdateConfirm.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!-- BEGIN HEADING TABLE -->
<table align="center">
	<tr>
      <td class="header" align="center">
        <logic:equal name="userProfileForm" property="action" value="createConfirm">
         	<bean:message key="prompt.create"/> <bean:message key="prompt.userProfile"/> <bean:message key="prompt.confirmation"/>         
         </logic:equal>
          <logic:equal name="userProfileForm" property="action" value="createSummary">
         	<bean:message key="prompt.create"/> <bean:message key="prompt.userProfile"/> <bean:message key="prompt.summary"/>          
         </logic:equal>
         <logic:equal name="userProfileForm" property="action" value="updateConfirm">
         	<bean:message key="prompt.update"/> <bean:message key="prompt.userProfile"/> <bean:message key="prompt.confirmation"/>          
         </logic:equal>
          <logic:equal name="userProfileForm" property="action" value="updateSummary">
         	<bean:message key="prompt.update"/> <bean:message key="prompt.userProfile"/> <bean:message key="prompt.summary"/>    
         </logic:equal> 
      </td>	
	</tr>
</table>

<table width="98%" border="0">
	<TBODY>
		<tr>		
		<logic:equal name="userProfileForm" property="action" value="createConfirm">
			<td align="center" class="confirm"> The following User Profile has been successfully created:
			<bean:write name="userProfileForm" property="logonId"/></td>	
		</logic:equal>
		 <logic:equal name="userProfileForm" property="action" value="createSummary">
         	<li> The following User Profile will be created when you select finish button. </li>
			
         </logic:equal>
		<logic:equal name="userProfileForm" property="action" value="updateConfirm">
			<td align="center" class="confirm"> The following User Profile has been successfully updated:
			<bean:write name="userProfileForm" property="logonId"/></td>	
		</logic:equal>
		  <logic:equal name="userProfileForm" property="action" value="updateSummary">
         		<li> The following User Profile will be updated when you select finish button.</li>				
         </logic:equal> 
		</tr>
	</TBODY>
</table>
<!-- END HEADING TABLE -->
<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->						
<html:form action="/submitUserProfileCreateUpdate">
<logic:equal name="userProfileForm" property="action" value="createConfirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|76">	
</logic:equal>
<logic:equal name="userProfileForm" property="action" value="createSummary">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|77">	
</logic:equal>	
<logic:equal name="userProfileForm" property="action" value="updateConfirm">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|239">	
</logic:equal>
<logic:equal name="userProfileForm" property="action" value="updateSummary">	
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|240">	
</logic:equal>	

<!-- begin detail table --> 
 
<!--BEGIN DETAIL TABLE -->
    <table width="98%" border="0" cellspacing="0" align="center" class="borderTableBlue">
	  <tr>
	 	 <td class="detailHead" nowrap><bean:message key="prompt.generalInfo" /></td>
	  </tr>
	  <tr bgcolor="#f0f0f0">
	    <td>
            <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
		    <tr>
		     	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="userName.formattedName" /></td>		     
	        </tr>
	        <tr>
		         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateOfBirth"/></td>
		         <td class="formDe" colspan="3"><bean:write name="userProfileForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>		     	         
	         </tr>
	         <tr>
		         <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ssn"/></td>
		         <td class="formDe" colspan="3"><bean:write name="userProfileForm" property="ssn.formattedSSN" /></td>		     	                
	       	</tr>
	      	<logic:equal name="userProfileForm" property="action" value="updateSummary">
	      	<tr>
	      		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId"/></td>
	          	<td class="formDe"><bean:write name="userProfileForm" property="logonId" /></td>	
	         	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userStatus"/></td>
	          	<td class="formDe"><bean:write name="userProfileForm" property="userStatus" /></td>		     
	      	</tr>
	       	<tr>
	      <%-- 	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.public"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="publicIndString" /></td> --%>	
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="genericUserType" /></td>		     
	      	</tr>  
	       	<tr>
	      		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agency"/></td>
	         	<td class="formDe" colspan=3><bean:write name="userProfileForm" property="agencyId" /> <bean:write name="userProfileForm" property="agencyName" /></td>	
	       	</tr>
	      	<tr>
	          <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.department"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentId" /> <bean:write name="userProfileForm" property="departmentName" /></td>		     
	     	</tr>   
	      	</logic:equal>
	       	<logic:equal name="userProfileForm" property="action" value="updateConfirm">
	      	<tr>
	      		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userId"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="logonId" /></td>	
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userStatus"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="userStatus" /></td>		     
	      	</tr>
	       	<tr>
	   <%--   		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.public"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="publicIndString" /></td> --%> 	
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="genericUserType" /></td>		     
	      	</tr>  
	       	<tr>
	      		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agency"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="agencyId" /> <bean:write name="userProfileForm" property="agencyName" /></td>	
		    </tr>
		    <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.department"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentId" /> <bean:write name="userProfileForm" property="departmentName" /></td>		     
	      	</tr>   
	      	</logic:equal>
	      
	        <logic:equal name="userProfileForm" property="action" value="createSummary">
	       	<tr>
	       		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentId" /></td>		     
	       	</tr>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentName" /></td>		     
	       	</tr>
	      	</logic:equal>
	        <logic:equal name="userProfileForm" property="action" value="createConfirm">
	       	<tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentCode"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentId" /></td>		     
	       	</tr>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.departmentName"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="departmentName" /></td>		     
	       	</tr>
	      	</logic:equal>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.orgCode"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="orgCode" /></td>		     
	       	</tr> 
	       	<tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone"/></td>
	         	<td class="formDe" colspan="3">
	         		<bean:write name="userProfileForm" property="phoneNum.formattedPhoneNumber" /> <bean:message key="prompt.ext"/>  <bean:write name="userProfileForm" property="phoneNum.ext" />
	         	</td>		     	         
	       	</tr>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="email" /></td>		     	         
	      	</tr> 
	       	<logic:equal name="userProfileForm" property="action" value="createSummary">
	       	<tr>
	    <%--	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.public"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="publicIndString" /></td> --%> 
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
	        	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="genericUserType" /></td>		     
	      	</tr>   
	      <jims2:isAllowed requiredFeatures='<%=Features.JCW_SECSERVPROV%>'>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.uvCodeGeneration"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="uvCodeGenerationStr" /></td>		     
	       	</tr>      
	       	</jims2:isAllowed>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.customCodeGeneration"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="customCodeGeneration" /></td>		     	         
	       	</tr>
	       	</logic:equal>
	       	<logic:equal name="userProfileForm" property="action" value="createConfirm">
	        <tr>
	   <%--   		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.public"/></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="publicIndString" /></td>  --%>	
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.genericUserType"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="genericUserType" /></td>		     
	      	</tr>   
	       <jims2:isAllowed requiredFeatures='<%=Features.JCW_SECSERVPROV%>'>
	      	<tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.uvCodeGeneration"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="uvCodeGenerationStr" /></td>		     
	       	</tr>
	       	</jims2:isAllowed>
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.customCodeGeneration"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="customCodeGeneration" /></td>		     	         
	       	</tr>
	       	</logic:equal>	        
	        <tr>
	        	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.requestor"/></td>
	         	<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="requestorName.formattedName" /></td>		     	         
	       	</tr>
	       	
	       	<logic:equal name="userProfileForm" property="action" value="updateConfirm">
	       	 <tr> 
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.date"/></td>
				<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferDate"/></td>    				
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.time"/></td>
				<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferTime"/></td> 
 			</tr>
	       	<tr>
         		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivate"/> <bean:message key="prompt.date" /></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="inactivationDate" formatKey="date.format.mmddyyyy" /></td>		     	            
	         	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivate"/> <bean:message key="prompt.time" /></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="inactivationTime" /></td>		     	         
	         </tr>
	        
		 			
         	</logic:equal>
          	<logic:equal name="userProfileForm" property="action" value="updateSummary">
          	<tr> 
			<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.date"/></td>
    			<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferDate"/></td>    				
    		</tr>
    		<tr>
    			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transfer"/> <bean:message key="prompt.time"/></td>
    			<td class="formDe" colspan="3"><bean:write name="userProfileForm" property="transferTime"/></td> 
		 	</tr>
      		<tr>
         		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivate"/> <bean:message key="prompt.date" /></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="inactivationDate" formatKey="date.format.mmddyyyy"/></td>		     	            
	          	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactivate"/> <bean:message key="prompt.time" /></td>
	         	<td class="formDe"><bean:write name="userProfileForm" property="inactivationTime" /></td>
         	</tr>
         	</logic:equal> 
          	</table>
		</td>
	</tr>
</table>        
<!--END INPUT FIELD TABLE -->
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
  	                   <logic:equal name="userProfileForm" property="comments" value="">&nbsp;</logic:equal>		   			
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
     <logic:equal name="userProfileForm" property="action" value="createSummary">
        <html:submit property="submitAction">
			<bean:message key="button.back"></bean:message>
		</html:submit>&nbsp;
        <html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
          <bean:message key="button.finish"></bean:message>
        </html:submit>&nbsp;
        <html:submit property="submitAction">
            <bean:message key="button.cancel"></bean:message>
        </html:submit>         
    </logic:equal>
    <logic:equal name="userProfileForm" property="action" value="updateSummary">
        <html:submit property="submitAction">
			<bean:message key="button.back"></bean:message>
		</html:submit>&nbsp;
        <html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
          <bean:message key="button.finish"></bean:message>
        </html:submit>&nbsp;
        <html:submit property="submitAction">
            <bean:message key="button.cancel"></bean:message>
        </html:submit>         
    </logic:equal>
     <logic:equal name="userProfileForm" property="action" value="createConfirm">
        <html:submit property="submitAction">
			<bean:message key="button.mainPage"></bean:message>
		</html:submit>&nbsp;
		<logic:equal name="userProfileSearchForm" property="userIsLA" value="N">
	        <!-- JIMS200041193 -->
	        <!--  
	        <html:submit property="submitAction">
    	      <bean:message key="button.assignRoles"></bean:message>
        	</html:submit>&nbsp;
        	 -->
	        <html:submit property="submitAction">
    	        <bean:message key="button.manageUserGroup"></bean:message>
        	</html:submit>&nbsp;
        </logic:equal>	
         <html:submit property="submitAction">
            <bean:message key="button.createOfficerProfile"></bean:message>
        </html:submit> 
         <html:submit property="submitAction">
            <bean:message key="button.searchOfficerProfile"></bean:message>
        </html:submit>                   
    </logic:equal>
      <logic:equal name="userProfileForm" property="action" value="updateConfirm">
        <html:submit property="submitAction">
			<bean:message key="button.mainPage"></bean:message>
		</html:submit>&nbsp;
		<logic:equal name="userProfileSearchForm" property="userIsLA" value="N">
	      
	      <!-- JIMS200041193 -->
	      <!--  
	        <html:submit property="submitAction">
    	      <bean:message key="button.assignRoles"></bean:message>
        	</html:submit>&nbsp;
	      -->
	        <html:submit property="submitAction">
    	        <bean:message key="button.manageUserGroup"></bean:message>
        	</html:submit>&nbsp;
        </logic:equal>	
         <html:submit property="submitAction">
            <bean:message key="button.createOfficerProfile"></bean:message>
        </html:submit>   
        <html:submit property="submitAction">
            <bean:message key="button.searchOfficerProfile"></bean:message>
        </html:submit>     
    </logic:equal>
    </td>  
  </tr>
  <logic:equal name="userProfileForm" property="action" value="updateConfirm">
  	<tr>
  	<td align="center">
  		 <html:submit property="submitAction">
            <bean:message key="button.userProfileSearchResults"></bean:message>
        </html:submit> 
     </td>
    </tr>
   </logic:equal>  
  
</table>
<!-- END BUTTON TABLE -->  
</html:form>
<!-- END FORM -->
<html:errors></html:errors>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>