<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--Main Navigation Menu for JIMS2 -->
<!--MODIFICATIONS -->
<!-- LDeen			03/29/2004	Create JSP -->
<!-- LDeen  		06/16/2004	Revise to add Manage Juv Warrants -->
<!-- HRodriguez  	08/16/2004	Revise Manage Role -->
<!-- HRodriguez  	08/23/2004	Add Action to CreateAgency -->
<!-- DWilliamson	10/01/2004	Add additional Juvenile Warrant links -->
<!-- DWilliamson 	10/21/2004  Add links for Manage Party -->
<!-- HRodriguez  	11/10/2004	ER JIMS200015516 Split View/Modify Manage Agency -->
<!-- LDeen		  	12/02/2004	Change warrantType to warrantTypeUI on Juv Warrant Menu Items -->
<!-- HRodriguez  	12/07/2004	ER JIMS200015551 Split View/Modify Manage CodeTable -->
<!-- HRodriguez  	12/10/2004	ER JIMS200015522 Split View/Modify Manage User -->
<!-- HRodriguez		02/04/2005	Add Warrant Recall & Inactivate-->
<!-- HRodriguez		02/17/2005	Add View Warrant-->
<!-- CShimek        03/31/2005  Add links for Master Administator Tasks -->  
<!-- LDeen	        05/10/2005  Add links for MJCW -->  
<!-- CShimek        06/16/2005  Comment out deprecated links for Manage Role and Manage Party -->
<!-- DWilliamson 	08/02/2005	Add links for Manage Officer Profile. -->
<!-- DGibler        08/16/2005  Added associateUpdatable to juvenile warrant requests. -->
<!-- CShimek        08/25/2005  Corrected Initiate Probable Cause Warrant warrantTypeUI from PC to pc -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login --/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
</head>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="../css/navmenu.css" />
<html:base />
<title>
<bean:message key="title.heading"/> - Navigation Menu</title>

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" rightmargin="0">
<%--
<!-- USE CASE TABLE - MANAGE AGENCY -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Agency</td>
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/<msp:webapp/>displayAgencyCreate.do?action=create" target="content" class="hyperlink">
		&nbsp;&nbsp;- Create Agency</a>
     </td>        
   </tr>    
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/<msp:webapp/>displayAgencySearch.do?action=modify" target="content" class="hyperlink">
		&nbsp;&nbsp;- Modify Agency</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/<msp:webapp/>displayAgencySearch.do?action=view" target="content" class="hyperlink">
		&nbsp;&nbsp;- View Agency</a>
     </td>    
   </tr>        
</table>

<!-- USE CASE TABLE - MANAGE CODE TABLE RECORDS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Code Table Records</td>
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CodeTable/displaySearchCodeTable.do?action=modify" target="content" class="hyperlink">
		&nbsp;&nbsp;- Modify Code</a>
     </td>    
   </tr> 
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CodeTable/displaySearchCodeTable.do?action=view" target="content" class="hyperlink">
		&nbsp;&nbsp;- View Code</a>
     </td>    
   </tr>    
</table>
--%>

<!-- USE CASE TABLE - MANAGE JUVENILE WARRANTS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Common Supervision</td>
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/CommonSupervision/displaySupervisionConditionCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;- Supervision Condition</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displaySupervisionConditionCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Create Supervision Condition</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displaySupervisionConditionSearch.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Search Supervision Condition</a>
     </td>    
   </tr>
	
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/CommonSupervision/displayCourtPolicyCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;- Court Policy</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displayCourtPolicyCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Create Court Policy</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displayCourtPolicySearch.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Search Court Policy</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/CommonSupervision/displayDepartmentPolicyCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;- Department Policy</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displayDepartmentPolicyCreate.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Create Department Policy</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/CommonSupervision/displayDepartmentPolicySearch.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Search Department Policy</a>
     </td>    
   </tr>
</table>

<!-- USE CASE TABLE - MANAGE JUVENILE CASE WORK -->

<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#FFFFFF">

<tr>

<td valign="bottom" nowrap class="header">

Manage Juvenile Casework</td>

</tr>

<tr>

<td>

<a href="/JuvenileCasework/displayJuvenileCasefileSearch.do" target="content" class="hyperlink">

&nbsp;&nbsp;- Search Juvenile Casefile</a></td>

</tr>
<tr>

<td>

<a href="/JuvenileCasework/displayJuvenileProfileSearch.do" target="content" class="hyperlink">
&nbsp;&nbsp;- Search Juvenile Profile</a></td>
</tr>

</table>
	
<!-- USE CASE TABLE - MANAGE JUVENILE WARRANTS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Juvenile Warrants</td>
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=inactivate&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Inactivate Juvenile Warrant</a>
     </td>    
   </tr>  
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/JuvenileWarrants/displayJOTSearch.do?warrantTypeUI=arr&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Initiate Arrest Warrant</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=actARR&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Activation</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/JuvenileWarrants/displayJOTSearch.do?warrantTypeUI=dta&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Initiate Directive To Apprehend</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=reqAckDTA&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Acknowledgement</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=actDTA&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Activation</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/JuvenileWarrants/displayJJSSearch.do?warrantTypeUI=oic&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Initiate Order of Immediate Custody</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=reqSigOIC&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Signature</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%" nowrap>
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=updateOIC&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Update Immediate Custody Warrant</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayJOTSearch.do?warrantTypeUI=pc&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Initiate Probable Cause Warrant</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayJJSSearch.do?warrantTypeUI=vop&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Initiate Violation of Probation</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=actVOP&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Activation</a>
     </td>    
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=updateVOP&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Update Violation of Probation Warrant</a>
     </td>    
   </tr>
   <tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=recall&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;- Recall Juvenile Warrant</a>
     </td>    
   </tr> 
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;&nbsp;&nbsp;- Release Juvenile</td>
   </tr>
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=releaseDecision&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Manage Release Decision</a>
     </td>    
   </tr> 
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=releaseToJP&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Manage Release to JP Info</a>
     </td>    
   </tr> 
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=releaseToPerson&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Manage Release to Person Info</a>
     </td>    
   </tr> 
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;&nbsp;&nbsp;- Manage Juvenile Warrant Service Status</td>
   </tr>
  <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=warrantService&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Manage Warrant Service</a>
     </td>    
   </tr> 
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=processReturn&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Process Return of Service</a>
     </td>    
   </tr> 
    <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=retSigStatus&associateUpdatable=false" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Request Return of Service Signature</a>
     </td>    
   </tr> 
	<tr>
     	<td width="3%">&nbsp;</td>
     	<td width="97%">
       		<a href="/JuvenileWarrants/displayWarrantViewSearch.do?associateUpdatable=true" target="content" class="hyperlink">
				&nbsp;&nbsp;- View Juvenile Warrant</a>
     	</td>    
   	</tr>                 
</table>

<!-- USER CASE TABLE - MANAGE PARTY -->
<%-- <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Party</td>
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/party/displayPartyCreateUpdate.do?action=create" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Create Party</a>
      </td>    
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/party/displayPartySearch.do" target="content" class="hyperlink">
		  &nbsp;&nbsp;- View/Modify Party</a>
      </td>    
   </tr>
 </table> --%>

<!-- USE CASE TABLE - MANAGE ROLE-->
<%-- <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Role</td>
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">       
        <a href="/<msp:webapp/>displayRoleUpdate.do?action=create" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Create Role</a>
      </td>   
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>        
        <a href="/<msp:webapp/>displayRoleSearch.do?action=modify" target="content" class="hyperlink">
      	  &nbsp;&nbsp;- Modify Role</a>
      </td>
   </tr>   
   <tr>
      <td>&nbsp;</td>
      <td>        
        <a href="/<msp:webapp/>displayRoleSearch.do?action=view" target="content" class="hyperlink">
      	  &nbsp;&nbsp;- View Role</a>
      </td>
   </tr>  
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayRoleGroupUpdate.do?action=create" target="content" class="hyperlink">
      	  &nbsp;&nbsp;- Create Role Group</a>
      </td>
   </tr>  
   <tr>
      <td>&nbsp;</td>
      <td>        
        <a href="/<msp:webapp/>displayRoleGroupList.do?action=modify" target="content" class="hyperlink">
      	  &nbsp;&nbsp;- View/Modify Role Group</a>
      </td>
   </tr>
</table> --%>

<!-- USE CASE TABLE - MANAGE OFFICER PROFILE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage Officer Profile</td>
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/<msp:webapp/>displayOfficerUserProfileSearch.do" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Create Officer</a>
      </td>    
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayOfficerProfileSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Search Officer</a>
      </td>
   </tr>
</table>

<!-- USE CASE TABLE - MANAGE USER PROFILE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Manage User Profile</td>
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/<msp:webapp/>displayUserProfileCreate.do?action=create" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Create User Profile</a>
      </td>    
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayUserProfileSearch.do?action=modify" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Modify User Profile</a>
      </td>
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayUserProfileSearch.do?action=view" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- View User Profile</a>
      </td>
   </tr>
</table> 
<!-- USE CASE TABLE - MANAGE SA ROLES -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Master Administrator Tasks</td>
   </tr>
   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/<msp:webapp/>displaySARoleSearch.do?action=menu" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Manage SA Roles</a>
      </td>    
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displaySAUserSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Manage SA Users</a>
      </td>
   </tr>
    <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayAgencySearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Manage Agency</a>
      </td>
   </tr>
    <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayDepartmentSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Manage Department</a>
      </td>
   </tr>
</table> 
<!-- USE CASE TABLE - MANAGE SA ROLES -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
      <td colspan="2" class="header" height="20" valign="bottom" nowrap>
        &nbsp;Security Administrator Tasks</td>
   </tr>
   <!-- place holder until Use Case is ready -->
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayAssignRolesSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Assign Roles</a>
      </td>
   </tr>
   <!-- place holder until Use Case is ready -->
   <!-- tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayEmailNotificationSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Manage Email Notification</a>
      </td>
   </tr --> 

   <tr>
      <td width="3%">&nbsp;</td>
      <td width="97%">
        <a href="/<msp:webapp/>displayRoleSearch.do" target="content" class="hyperlink">
		  &nbsp;&nbsp;- Manage Roles</a>
      </td>    
   </tr>
   <!-- place holder until Use Case is ready -->
   <tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayUserGroupSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Manage User Groups</a>
      </td>
   </tr>
   
   <!-- place holder until Use Case is ready -->
   <!--tr>
      <td>&nbsp;</td>
      <td>       
        <a href="/<msp:webapp/>displayReportsSearch.do" target="content" class="hyperlink">
       	  &nbsp;&nbsp;- Reports</a>
      </td>
   </tr -->
</table> 

<html:errors></html:errors>

</body>
</html>