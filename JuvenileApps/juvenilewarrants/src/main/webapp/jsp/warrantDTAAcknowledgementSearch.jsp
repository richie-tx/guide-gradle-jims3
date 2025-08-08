<!DOCTYPE HTML>
<!-- Used to search for warrants. -->
<!--MODIFICATIONS -->
<%-- DWilliamson	10/06/2004	Create JSP --%>
<%-- LDeen			10/22/2004	Revise JSP --%>
<%-- LDeen			03/07/2006	Changed required prompt to promp.1.diamond --%>
<%-- CShimek    	04/05/2006  ER#26357 change Reset button to Refresh button --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />

<html:base />
<title><bean:message key="title.heading"/> - warrantDTAAcknowledgementSearch.jsp</title>

<!-- VALIDATION JAVASCRIPT FILE FOR THIS PAGE --> 
<%-- tiles:insert page="../js/warrantGenericSearch.js" flush="true" / --%>
<script type="text/javascript" src="/<msp:webapp/>js/warrantGenericSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>

<body>
 
<html:form action="/displayWarrantDTAAcknowledgementSearchResults" focus="warrantNum" target="content" >
    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|24">
    <input type="hidden" name="warrantType" value="<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>">

<!-- BEGIN HEADING TABLE -->
	<table width="100%">
    <tr>
      <td class="header" align="center">
            <bean:message key="title.juvWarrantDTA"/>Acknowledgement Search
      </td>	
    </tr>
  </table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%"  border="0" align="center">
    <tr>
       <td>
	    <ul>
         <li>Enter the Warrant number or Juvenile name.</li>            
	     <li>Select Submit button to retrieve Warrant information.</li>
        </ul></td>
    </tr>
</table>
<table width="98%" align="center">
    <tr>
		<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>      
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INQUIRY TABLES -->
  <!-- SHOWING WARRANT # & JUVENILE NAME FOR ACKNOWLEDGING DTA WARRANT -->  
   <table width="98%" align="center" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
    <tr>
    <td class=formDeLabel ><bean:message key="prompt.warrantNumber"/></td>
    <td class=formDe><html:text property="warrantNum" size="10" maxlength="10"/></td>
    </tr>
    <tr>
     <td class=formDeLabel colspan="2">OR</td>
    </tr>
    <tr>
      <td class=formDeLabel >Juvenile <bean:message key="prompt.lastName"/></td>
      <td class=formDe><html:text property="lastName" size="30" maxlength="30"/></td>
    </tr>
    <tr>
      <td class=formDeLabel >Juvenile <bean:message key="prompt.firstName"/></td>
      <td class=formDe><html:text property="firstName" size="25" maxlength="25"/></td>
    </tr>
    </table>
<!-- END INQUIRY TABLE --> 
<br>
<!-- BEGIN BUTTON TABLE -->
 <table border="0" width="100%">
   <tr>
     <td align="center">
      	<html:submit property="submitAction" onclick="return checkJOTRequired(this);">
      		<bean:message key="button.submit"/>
      	</html:submit>&nbsp;
		<html:submit property="submitAction">
			<bean:message key="button.refresh"/>
		</html:submit>&nbsp;      
      	<html:reset><bean:message key="button.reset"/></html:reset>&nbsp;
      	<html:button property="org.apache.struts.taglib.html.CANCEL" 
	   		onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
			<bean:message key="button.cancel"></bean:message>
	  	</html:button> 
    </td>
  </tr>		
</table>
<!-- END BUTTON TABLE -->

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>