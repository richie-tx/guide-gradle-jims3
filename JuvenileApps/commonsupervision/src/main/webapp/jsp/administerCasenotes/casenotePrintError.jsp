<html>
<!--NOTE THIS FILE SHOULD ONLY BE CHANGED IN THE APP/SRC/UI/COMMON FOLDER -->

<!--MODIFICATIONS -->
<!-- CShimek	07/16/2010	Defect #66425, created this JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<title><bean:message key="title.heading"/> - casenotePrintError.jsp</title>
</head>

<!--BEGIN BODY TAG-->
<body>
<!-- BEGIN HEADING TABLE -->
<table width=""100%">
  <tr>
    <td align="center" class="header">Casenote Print Error Notice</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<table align="center" width="98%" border="0" class="borderTableBlue">
	<tr>
		<td> 
			<p>One of the casenotes has incorrect formatting.  You need to delete the casenote or have your supervisor delete the casenote and create a new one.
				It may help to search the casenotes by Date Range to determine which casenote has the incorrect formatting. 
		 		If there are any casenotes that have incorrect formatting, none of them will print.
			</p>
		</td>
	</tr>
	<tr>
		<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td><img src="/<msp:webapp/>images/casenotePrinting.jpg"></td>
	</tr>		
</table> 
<br>
<table width="100%">
	<tr>
		<td align="center">We apologize for the inconvenience.</td>
	</tr>
</table>
</body>
</html>