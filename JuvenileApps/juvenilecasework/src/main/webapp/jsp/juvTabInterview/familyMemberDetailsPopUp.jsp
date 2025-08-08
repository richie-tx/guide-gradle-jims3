<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/26/2012	CShimek		Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%-- %@ page import="naming.PDJuvenileCaseConstants" --%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascripts --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<title><bean:message key="title.heading"/> - familyMemberDetailsPopUp.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body topmargin="0" leftmargin="0">
<html:form action="/displayFamilyMemberDetails" >
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
	   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/>&nbsp;<bean:message key="prompt.details"/></td>	  	    	 
 	</tr>  	
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td>
			<ul>
				<li>Select "Close Window" button to exit.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
<%-- BEGIN BLUE BORDER TABLE --%>
			<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<%-- member details start--%>
						<tiles:insert page="familyMemberDetailsTile.jsp" flush="true">
						</tiles:insert>
						<%-- member details end--%>
					</td>
				</tr>	
			</table>
<%-- END BLUE BORDER TABLE --%>
			<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
			<table border="0" width="100%">
			 	<tr>
					<td align="center">
						<input type="button" value="Close Window" onclick="window.close()">
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
  		</td> 
	</tr> 
</table> 
<%-- END DETAIL TABLE --%> 
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>