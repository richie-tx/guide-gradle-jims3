<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/02/2005	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<title><bean:message key="title.heading" /> - conditionPreview.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<form name="returnofServiceUpdate">
<div align="center">
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
			<ul>
				<li>See Example text below</li>
				<li>Click Close Button to return.</li>
			</ul>
		</td>
  </tr>
</table>

<br>
<!-- BEGIN  TABLE -->
<table width="98%" border="0" cellpadding="4" cellspacing="1" >
  <tr>
  	 <td valign=top class=formDeLabel><bean:message key="title.consequence" /></td>
 	</tr>
  <tr>  
    <td valign=top class=formDe>
		<bean:write name="courtPolicyForm" property="courtPolicyLiteral" filter="false"/>
	</td>
  </tr>
</table>

<br>	
<table width="98%" border="0" cellpadding="4" cellspacing="1" >
  <tr>
  	 <td valign=top class=formDeLabel>Example Consequence Policy</td>
 	</tr>
  <tr>    
    <td valign=top class=formDe><bean:write name="courtPolicyForm" property="courtPolicyLiteralPreview" filter="false"/> </td>
  </tr>
</table>

<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">
    	<input type="button" value="Close" name="close" onClick="window.close()">
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
</div>

<br>
<!-- BEGIN NOTES TABLE -->
<table width="100%">
  <tr>
      <td align="left" class="subhead"><bean:message key="prompt.notes" /></td>
  </tr>
  <tr>
    <td><ol>
	  	<li>This is a sample</li>
	</ol></td>
  </tr>
</table>
<!-- END NOTES TABLE -->
</Form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
