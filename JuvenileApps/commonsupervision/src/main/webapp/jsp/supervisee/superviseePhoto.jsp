<!-- 12/14/2009  RYoung     ER 62850 - Revise code to access the Supervisee Photo-CSCD -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>  
<%-- <%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %> --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="naming.UIConstants" %>

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
<title><bean:message key="title.heading" /> - supervisee/superviseeDetails.jsp</title>

<!-- JavaScripts -->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<!-- FUNCTIONS FOR FILTER CONDITIONS GROUPS  -->
<script type="text/javascript">
</script>  
</head>

<html>
<head>
<title>caseloadCSCD/profile/singlePic.htm</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- Javascript for emulated navigation -->
<link href="../../../common/base.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../case_util.js"></script>
</head>
<body topmargin=0 leftmargin="0">

<form name="myForm">
<!-- BEGIN pics TABLE -->
<table width=98% border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>

		<td valign=top class=detailHead><bean:write name="superviseeHeaderForm" property="superviseeNameDesc" /> Photo</td>
	</tr>
	<tr>
		<td align=center>
			<table>
				<tr>
						<td><img alt="Mug Shot Not Available" title="Supervisee Photo" src="/<msp:webapp/>getSuperviseePhoto.do?submitAction=Photo Detail" border=1></td>
					</tr>

					<tr>
						<td align=center>
							<bean:write name="superviseeHeaderForm" property="superviseePhotoCreateDate" />
						</td>
					</tr>
				</table>
		</td>
	</tr>
</table>
<br>
<!-- END pics TABLE -->
<input type=button value=Close onclick=window.close()>

<br>
</Form>
<div align=center>[<a href=#top>Back to Top</a>]</div></body>
</html:html>
