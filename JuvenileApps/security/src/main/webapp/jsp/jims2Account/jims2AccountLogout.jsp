<!DOCTYPE HTML>
<!-- 03/26/2007	 CShimek   - Create JSP -->
<!-- 02/05/2009  C Shimek  - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<html:base />
<title>JIMS2 - JIMS2AccountLogout.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function logout(){
	var location = "/appshell/displayLogout.do";	
	alert('Your JIMS2 Account has been successfully updated.\nPlease click OK and log in again.');
	window.location.href=location;
}
</script>
</head>
<body onload="logout();">
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>