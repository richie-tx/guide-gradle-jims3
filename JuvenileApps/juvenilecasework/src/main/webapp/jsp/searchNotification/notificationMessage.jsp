<!DOCTYPE HTML>
<html>
<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->
<!-- LDeen	08/11/05	Revise JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/caswork.css" />
<html:base />
<title>Notice Message</title>
</head>

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header">Notice Message </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>

<!-- BEGIN INFORMATION TABLE -->
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width=98% align=center valign=top>
			<table class=borderTableBlue cellpadding=4 cellspacing=0 width=98%>
				<tr class=detailHead>
					<td class=detailHead>Notice Information</td>
				</tr>
				<tr>
					<td align=center>
					<!--error table start-->
						<table border=0 cellspacing=1 cellpadding=2 width=100%>
							<tr>
								<td class=formDeLabel width=1% nowrap>Message</td>
								<td class=formDe colspan=3><bean:write name="searchNotificationsForm" property="noticeMessage" filter="false" /></td>
							</tr>
						</table>
						<!--error table end-->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<br>
<br>
</body>
</html>