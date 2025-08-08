<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/12/2006	 Hien Rodriguez - Create JSP -->

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
<title>Common supervision - common/index.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin=0 leftmargin="0">
<html:form action="/displaySuggestedOrderIndex" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
  	</tr>
  	<tr>
    	<td valign=top>
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>						
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>								
						<table width=98% border="0" cellpadding="0" cellspacing="0" >								
							<tr>
								<td valign=top align=center class=subHead><br><br><br><br>Welcome To Supervision Case Management<br><br><br><br><br><br><br><br><br><br><br></td>
							</tr>
						</table>																		
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>			
</div>
</html:form>

</body>
</html:html>
