<!DOCTYPE>

<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005		DWilliamson	Create JSP --%>
<%-- 09/17/2013		CShimek		#76047 - added update trait status confirm message --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;Juvenile Casework - casefileTraitsView.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|122">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/>&nbsp;- Casefile Traits History</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
		<br>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
		<tr>
			<td class="confirm">Traits status successfully updated.</td>
		</tr>
		<br>
	</logic:equal>
	<tr>
		<td>
			<ul>
				<li>Select a Trait Type and Click View to see list of traits for that type.</li>

				<%-- 9 aug 2006 - mjt - this is the first of a number of *logic* tags 
				     that are used with viewing this traits page in a "lookup only mode",
				     i.e., without the ability to *add* new traits. this is used when 
				     this page is called from various Risk Analysis pages, and this page
				     is poppped up in its own browser window. the key is when the logic
				     tag is used with the *value* attribute of *FIND*.
				     all in all, we want to hide the tabs, *Add* button, and the casefile
				     hyperlink menu found at the bottom of the page.
				--%>
			  	<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
	        		<li>Select a Trait Type and Click Add More Traits to add more traits of that type.</li>
	      		</logic:notEqual>
      		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
<%-- BEGIN JUVENILE HEADER INCLUDE --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	  <tiles:put name="headerType" value="casefileheader"/>
	</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">

<%-- 9 aug 2006 - mjt - dont show tabs while in lookup mode only --%>
			<logic:notEqual name="juvenileTraitsForm" property="action" value="<%=naming.UIConstants.FIND%>">
				<bean:define id="casefileId" name="juvenileTraitsForm" property="supervisionNum"/>
				<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="traitstab" />
					<tiles:put name="casefileid" value="<%=casefileId%>"/>
				</tiles:insert>				
			</logic:notEqual>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<%-- BEGIN TRAITS TABLE --%>
						<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp">
							<tiles:put name="actionPath" value="/handleJuvenileCasefileTraits"/>
						</tiles:insert>
<%-- END TRAITS TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>