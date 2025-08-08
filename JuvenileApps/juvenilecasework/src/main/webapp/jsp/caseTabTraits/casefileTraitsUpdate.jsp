<!DOCTYPE HTML>
<%-- Used to display juvenile traits details off Traits Tab in both Casefile and Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 09/16/2013		CShimek	ER 75751 Create page --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileTraitsUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript'>
function validateSelect()
{
	var fld= document.getElementById("statSel");
	if (fld.selectedIndex == 0)
	{
		alert("New Trait Status selection is required.");
		fld.focus();
		return false;
	}
	return true;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<bean:define id="juvenileNumberDef" name="juvenileTraitsForm" property="juvenileNumber"/>

<html:form action="/submitJuvenileCasefileTraitUpdate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/> - Update Trait Status</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul> 
				<li>Select a new Trait Status.</li>
				<li>Click Finish to complete status update.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Fields</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN CASEFILE HEADER INCLUDE --%> 
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END CASEFILE HEADER INCLUDE  --%> 
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top">
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"> 
			 	    	<bean:define id="casefileId" name="juvenileTraitsForm" property="supervisionNum"/>
      					<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        					<tiles:put name="tabid" value="traitstab"/>
        					<tiles:put name="casefileid" value='<%=casefileId%>' />
      					</tiles:insert>	
					</td>
			  	</tr>
  			</table>

  			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  				<tr>
  					<td valign="top" align="center">
 			            <div class=spacer></div>
<%-- BEGIN TRAIT STATUS TABLE --%>
            				<tiles:insert page="../caseworkCommon/juvenileTraitStatusUpdate.jsp" flush="true"/>
<%-- END TRAITS TABLE --%> 			            
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class='spacer'></div>
</html:form>
<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>