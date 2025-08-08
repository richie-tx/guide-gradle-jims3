
<!-- START - This is the physical characteristics tile -->
<!--MODIFICATIONS -->
<!-- 06/17/2005	Hien Rodriguez	Create JSP -->
<%-- 02/28/2006 Clarence Shimek Defect#27703 corrected row count in showSomethingFromDropDown() function call from 4 to 6 for new rows added --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title>Common Supervision - common/objectNotAvailable.jsp</title>
<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script> 
</head> 
<!--END HEAD TAG-->

<!--BEGIN BODY TAG-->
<body topmargin=0 leftmargin="0">
<form action="nothing">
<!-- BEGIN pics TABLE -->
<table width=98% border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign=top class=detailHead>Not Found</td>
	</tr>
	<tr>
		<td align=center>
			
			
				<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
					<!-- END ERROR TABLE -->
				
		</td>
	</tr>
</table>
<br>
<input type=button value=Close onclick=window.close()>
<br>
</form>
<!-- END FORM -->
</body>
</html:html>