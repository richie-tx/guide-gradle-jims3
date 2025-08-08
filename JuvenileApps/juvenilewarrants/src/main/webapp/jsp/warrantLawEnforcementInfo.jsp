<!DOCTYPE HTML>
<%-- CShimek    02/02/2006  Add hidden field for HelpFile name --%>
<%-- CShimek	12/21/2006  Revised helpfile reference value --%>
<%-- LDeen	06/04/2007  #42564 changed path to app.js --%>
<%-- DWilliamson 04/17/2008 #51017 Increase Officer ID field to 11. --%>
<%-- RYoung      08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantLawEnforcementInfo.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="lawEnforcementInfoForm" />

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<tiles:insert page="../js/warrantJOTCreate.js" />

</head>

<!--BEGIN BODY TAG-->
<body>
<html:form action="/createOfficerAndDisplaySummary" target="content">
   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|38">
<!-- BEGIN HEADING TABLE -->
 <table align="center" >
    <tr>
      <td align="center" class="header"><bean:message key="title.officerCreate"/>
      </td>	
    </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
<table width="98%" align="center">
  <tr>
    <td>
      <ul>
        <li>Verify that officer information previously entered is correct and if any changes are needed, select Back button. 
        <li>An existing Officer was not found so you will be required to create an Officer. </li>
        <li>Enter officer information and select Next button to continue initiating warrant. </li>
      </ul>
    </td>
  </tr>
</table>
<table width="98%" align="center" >
  <tr>
    <td class="required"><bean:message key="prompt.1.diamond" /><bean:message key="prompt.requiredFieldsInstruction"/>
	 &nbsp;&nbsp;&nbsp;
	 <bean:message key="prompt.1.diamond" />&nbsp;Either Badge Number or Other ID Number is Required</td>
  </tr>
 </table>
 <!-- END INSTRUCTION/REQUIRED INFORMATION TABLE --> 
 
 <!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
	

<!-- BEGIN LAW ENFORCEMENT INFO TABLES -->
  <table width="98%" border="0" cellspacing="0" cellpadding="2" align="center" class="borderTableBlue">
	<tr>
	 	<td class=detailHead nowrap><bean:message key="prompt.lawEnforcementInfo" /></td>
	</tr>
  <tr>
    <td>
      <table width=100% align="center" cellpadding="4" cellspacing="1">
        <tr>
           <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.otherIdNumber"/></td>
           <logic:notEmpty name="juvenileWarrantForm" property="officerOtherIdNumber">
           <td class="formDe">
           		<bean:write name="juvenileWarrantForm" property="officerOtherIdNumber"/>
           		<html:hidden name="juvenileWarrantForm" property="officerOtherIdNumber" />
           </td>
           </logic:notEmpty>
           <logic:empty name="juvenileWarrantForm" property="officerOtherIdNumber">
           <td class="formDe"><html:text name="juvenileWarrantForm" property="officerOtherIdNumber" size="10" maxlength="10"/></td>
           </logic:empty>
        </tr>
        <tr>   
           <td class="formDeLabel"><bean:message key="prompt.badgeNumber"/></td>
           <logic:notEmpty name="juvenileWarrantForm" property="officerBadgeNumber">
           <td class="formDe">
           		<bean:write name="juvenileWarrantForm" property="officerBadgeNumber" />
           		<html:hidden name="juvenileWarrantForm" property="officerBadgeNumber" />
           </td>
           </logic:notEmpty>
           <logic:empty name="juvenileWarrantForm" property="officerBadgeNumber">
           <td class="formDe"><html:text name="juvenileWarrantForm" property="officerBadgeNumber" size="11" maxlength="11"/></td>
           </logic:empty>
        </tr>
        <tr>   
        <td class="formDeLabel" nowrap><bean:message key="prompt.department"/></td>
           <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>
        </tr>
        <tr>
            <td class="formDeLabel"><bean:message key="prompt.1.diamond" />&nbsp;<bean:message key="prompt.lastName"/></td>
            <td class="formDe"><html:text property="officerName.lastName" size="30" maxlength="30"/></td>
        </tr>
         <tr>   
            <td class="formDeLabel"><bean:message key="prompt.1.diamond" />&nbsp;<bean:message key="prompt.firstName"/></td>
            <td class="formDe"><html:text property="officerName.firstName" size="25" maxlength="25"/></td>
         </tr>
         <tr>
            <td class="formDeLabel"><bean:message key="prompt.middleName"/></td>
            <td class="formDe"><html:text property="officerName.middleName" size="25" maxlength="25"/></td>   
         </tr>
         
        <tr>
           <td class="formDeLabel"><bean:message key="prompt.1.diamond" />&nbsp;<bean:message key="prompt.workPhone"/></td>
           <td class="formDe">
            <html:text property="workPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="workPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="workPhone.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>  
         </td>
        </tr>
        <tr>
           <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
           <td class="formDe">
            <html:text property="cellPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="cellPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="cellPhone.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>  
         </td>
        </tr>
        <tr>
           <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
           <td class="formDe">
            <html:text property="pager.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="pager.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            <html:text property="pager.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>  
         </td>
        </tr>
        <tr>
        <td class="formDeLabel"><bean:message key="prompt.email"/></td>
           <td class="formDe"><html:text property="email" value="" size="50" maxlength="100" /></td>
        </tr>
       </table>
     </td>
  </tr>        
</table>   
<!-- END LAW ENFORCEMENT INFORMATION TABLES -->

<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr valign="top" align="center">
	  <td>
	    <html:submit property="submitAction">
			<bean:message key="button.back" />
		</html:submit>&nbsp;	  
	    <html:submit property="submitAction" onclick="return validateOfficerInformation(this.form)">
	        <bean:message key="button.next" />
	    </html:submit>&nbsp;
		<html:reset />&nbsp;
	  </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<html:errors></html:errors>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>