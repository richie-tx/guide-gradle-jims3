<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/02/2005	 Aswin Widjaja - Create JSP -->
<!-- 06/04/2008  C. Shimek       activity#51908 revised date/time display format -->
<!-- 10/22/2008  C. Shimke       defect#54593/ER55121 removed compliance casenote highlighting -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%-- @ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" --%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/casenotePopUp.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<form name="listingOfCasenotes">
<DIV align="center">
<!-- BEGIN  TABLE -->
<table width="98%" border="0" cellpadding="4" cellspacing="1">
  <tr>
  	 <td valign="top" align="center" class="header">CSCD - <bean:message key="prompt.casenotes" /></td>
 	</tr>
</table>
<!-- BEGIN CASENOTES DISPLAY TABLE  -->	
<logic:empty name="complianceForm" property="currentCasenotes">	
<table width="98%" border="0" cellpadding="2" cellspacing="1">
	<tr class="detailHead">
		<td>No Casenotes found to display</td>
	</tr>
</table>							
</logic:empty>
<logic:notEmpty name="complianceForm" property="currentCasenotes">	
	<logic:iterate id="casenoteIter" name="complianceForm" property="currentCasenotes">
		<table width="98%" border="0" cellpadding="2" cellspacing="1" >
			<tr class="detailHead">
				<td><bean:message key="prompt.casenoteDate" /></td>
				<td><bean:message key="prompt.subject" /></td>
				<td><bean:message key="prompt.type" /></td>
				<td><bean:message key="prompt.contactMethod" /></td>
				<td><bean:message key="prompt.createdBy" /></td>
				<td><bean:message key="prompt.createDate"/></td>
			</tr>
			<tr class="formDe">
				<td><bean:write name="casenoteIter" property="casenoteDate" formatKey="date.format.mmddyyyy"/></td>
				<td><bean:write name="casenoteIter" property="subjects" /></td>
				<td><bean:write name="casenoteIter" property="casenoteType" /></td>
				<td><bean:write name="casenoteIter" property="contactMethod" /></td>
				<td><bean:write name="casenoteIter" property="createdByName" /></td>
				<td><bean:write name="casenoteIter" property="createDate" formatKey="date.format.mmddyyyy"/></td>
			</tr>
			<tr 
				<logic:equal name="casenoteIter" property="casenoteStatusId" value="D">
					class="draftCasenote"
				</logic:equal>
				>
				<td class="borderTableBlue" colspan="6" >
					<bean:write name="casenoteIter" property="collateralInfo" filter="false" />
					<bean:write name="casenoteIter" property="casenoteText" filter="false" />
				</td>
			</tr>
			<tr>
				<td colspan="5"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
		</table>	
	</logic:iterate> 
</logic:notEmpty>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">
    	<input type="button" value="Close Window" name="close" onClick="window.close()">
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
</DIV>
<%-- 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
--%>
</body>
</html:html>
