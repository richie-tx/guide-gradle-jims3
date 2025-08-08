<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- UGopinath	02/01/2006	Create JSP -->
<!-- CShimek    01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek    07/06/2007  #43456 replaced casework_uitl.js with app.js  -->
<!-- CShimek    02/06/2009  #56860 add Back to Top  -->

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
<title><bean:message key="title.heading"/> - <bean:message key="title.matchingUser"/></title>

</head>  
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript">
var currentSelectedUser=0;

function setUser(userNum){
	var element=document.getElementById('hiddenVal');
	element.value=userNum;		
	currentSelectedUser=userNum;
	
}

function loadUser(file){
	var myURL=file + "&selectedValue=" + currentSelectedUser;	
	load(myURL,top.opener);
	window.close();
}
function load(file,target) {
   
        window.location.href = file;
}
</script>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN FORM -->						
 <html:form action="/displayUserProfileCreateUpdateSummary" target="content" > 
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|164">	
<!-- BEGIN HEADING TABLE -->
<table align="center">
   <tr>
     <td class="header"><bean:message key="title.matchingUser"/></td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>

<br>
<!-- BEGIN NEW USER INFORMATION TABLE -->
<table width="98%" border="0" cellspacing="0" align="center">
   <tr>
      <td>
        <table width="100%" border="0" cellspacing="0">          
  	 		<tr>
  	 		  <td>
  	 		      <bean:write name="userProfileForm" property="userName.formattedName" />
  	 		       &nbsp;with&nbsp;<bean:message key="prompt.ssn"/>:&nbsp;<bean:write name="userProfileForm" property="ssn.formattedSSN" />
			  &nbsp;with&nbsp;<bean:message key="prompt.dateOfBirth"/>:&nbsp;<bean:write name="userProfileForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
       		</tr> 
	   </table>
	 </td>
   </tr>
</table>
<!-- END NEW USER INFORMATION TABLE -->

<br>
<!-- BEGIN MATCHING USERS TABLE  -->
<table width="98%" border="0" cellspacing="1" class="borderTableBlue" align="center">
  <tr class="detailHead">
     <td><bean:message key="prompt.name"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="userName.lastName" primarySortType="STRING"
             secondPropSort="userName.firstName" secondarySortType="STRING" defaultSort="true" 
             defaultSortOrder="ASC" sortId="1" /></td>
     <td><bean:message key="prompt.userId"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="logonId" primarySortType="STRING"
             defaultSort="false" defaultSortOrder="ASC" sortId="2" /></td>
     <td><bean:message key="prompt.deptCode"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="departmentId" primarySortType="STRING"
             defaultSort="false" defaultSortOrder="ASC" sortId="3" /></td>
     <td><bean:message key="prompt.SSN"/></td>
     <td><bean:message key="prompt.phone"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="phoneNum.phoneNumber" primarySortType="STRING"
             defaultSort="false" defaultSortOrder="ASC" sortId="5" /></td>
     <td><bean:message key="prompt.dateOfBirth"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="dateOfBirth" primarySortType="DATE"
             defaultSort="false" defaultSortOrder="ASC" sortId="6" /></td>
     <td><bean:message key="prompt.userStatus"/>
         <jims:sortResults beanName="userProfileForm" results="matchingProfiles" 
             primaryPropSort="userStatus" primarySortType="STRING"
             defaultSort="false" defaultSortOrder="ASC" sortId="7" /></td>
  </tr>
 <logic:iterate id="userIndex" name="userProfileForm" property="matchingProfiles" indexId="index">
  <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
    <td>
		<a href="javascript:loadUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=View')" onclick="setUser('<bean:write name="userIndex" property="logonId"/>')">
			<bean:write name="userIndex" property="userName.formattedName"/>
		</a>
	</td> 
    <td><bean:write name="userIndex" property="logonId"/></td>
    <td><bean:write name="userIndex" property="departmentId"/></td>
    <td><bean:write name="userIndex" property="ssn.formattedSSN"/></td>
    <td><bean:write name="userIndex" property="phoneNum.formattedPhoneNumber"/></td>
    <td><bean:write name="userIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
    <td><bean:write name="userIndex" property="userStatus"/></td>
 </tr>
</logic:iterate>
   <html:hidden  name="userProfileForm" property="selectedValue" styleId="hiddenVal"/>
<!-- END iterate TAG -->    
</table>
<!-- END MATCHING USER DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
 <table align="center" width="1%">
   <tr>
      <td align="center"> 
          <logic:equal name="userProfileForm" property="action" value="createSummary">   
	         <html:submit property="submitAction">
               <bean:message key="button.continueCreate"></bean:message>
	         </html:submit>
          </logic:equal>  
          <logic:equal name="userProfileForm" property="action" value="updateSummary">   
	        <html:submit property="submitAction">
               <bean:message key="button.continueUpdate"></bean:message>
	        </html:submit>&nbsp;
          </logic:equal>  
    
         <html:submit property="submitAction">
            <bean:message key="button.back"></bean:message>
         </html:submit>
      </td>
   </tr>
 </table>   
<!-- END BUTTON TABLE -->
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>