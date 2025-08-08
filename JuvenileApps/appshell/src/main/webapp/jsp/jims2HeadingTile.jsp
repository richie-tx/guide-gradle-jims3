<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%-- jsp:useBean id="userInfo" scope="session" class="messaging.appshell.UserEvent" type="messaging.appshell.UserEvent"/ --%>
<jsp:useBean id="regionInfo" scope="page" class="pd.security.RegionType" type="pd.security.RegionType"/>

<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
	<html:base />
</head> 

<!--  BEGIN JIMS2 HEADING TABLES -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr bgcolor="#000080">
	<td width="1%">
		<font size=4 color="#d0d0d0" face=arial>&nbsp;JIMS2</font>
	</td>
    <td width="85%" align="center" height="26">
       <font size=4 color="#d0d0d0" face=arial>Justice Information Management System</font>
    </td>
	<td width="2%">
		<font size=4 color="#d0d0d0" face=arial><jsp:getProperty name="regionInfo" property="region"/>&nbsp;</font>
		<input type="hidden" name="region" value="<jsp:getProperty name="regionInfo" property="region"/>" > 
	</td>
  </tr>
</table>

<table background="../images/BGHeaderShadow.gif" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1" nowrap width="1" height="26"></td>
    <%--
	<td width="5%" align="right" nowrap class="subhead"><b>User ID:</b>&nbsp;</td>
	<td width="10%" align="left" nowrap class="bodyText"><jsp:getProperty name="userInfo" property="userID"/> &nbsp; </td>
	<td width="5%" align="right" nowrap class="subhead"><b>User Name:</b>&nbsp; </td>
	<td width="32%" align="left" nowrap class="bodyText"><jsp:getProperty name="userInfo" property="userName"/></td>
	--%>
	   <!-- non-functional Logout and Help -->
    <td width="30%" align="left" nowrap class="bodyText">
        <script Language="Javascript">
		   var monthlit = new Array ("January","February","March","April","May","June","July","August","September","October","November","December");
	   	   var daylit = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
	   	   today = new Date();
	  	   document.write('&nbsp;&nbsp;' + daylit[today.getDay()] + '&nbsp;&nbsp;' + monthlit[today.getMonth()] + ' ' +
	                       today.getDate() + ', ' + today.getFullYear());
	  	</script>

    </td>
	<td width="10%" align="right" nowrap class="subhead"><b><a target="_top" href="/<msp:webapp/>displayLogout.do">Logout</a></b>&nbsp;</td>
	<td width="7%" align="right" nowrap class="subhead"><b><a href="#" onclick="return helpPopUp()">Help</a></b>&nbsp;</td>
  </tr>
</table>
<!-- END JIMS2 HEADING TABLES -->
