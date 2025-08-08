<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/25/2006		AWidjaja Create JSP--%>
<%-- 07/17/2009     C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- caseplanList.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleCaseplan" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - Caseplan Versions</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td class="bodyText">
      <ul>
        <li>Select Caseplan Create Date hyperlink to view Details.<li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign="top">
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<div class='spacer'></div>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<%-- BEGIN Today's Interview TABLE --%>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2" nowrap="nowrap">Caseplan Versions</td>
							</tr>
						
							<tr>
								<td>
									<table cellpadding="2" cellspacing="1" width='100%'>
										<tr bgcolor='#cccccc'>
											<td class="subHead" width='1%'>Entry Date</td>
											<td class="subHead" width='1%'>Status</td>
											<td class="subHead" width='1%'>Review Date</td>
										</tr>
										<tr bgcolor='#ffffff'>
											<td valign="top"><a href="caseplanDocument.htm">01/01/2006</a></td>
											<td valign="top" width='1%'>Reviewed</td>
											<td valign="top" width='1%'>01/10/2006</td>
										</tr>
										<tr bgcolor='#f0f0f0'>
											<td valign="top"><a href="caseplanDocument.htm">12/25/2005</a></td>
											<td valign="top" width='1%'>Final</td>
											<td valign="top" width='1%'>12/30/2005</td>
										</tr>
										<tr bgcolor='#ffffff'>
											<td valign="top"><a href="caseplanDocument.htm">12/10/2005</a></td>
											<td valign="top" width='1%'>Final</td>
											<td valign="top" width='1%'>12/13/2005</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<%-- END GOALS TABLE --%>							
						<div class='spacer'></div>
	
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class='spacer'></div>
<table width="100%">
  	<tr>
    	<td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
    	</td>
  	</tr>
</table>
<%-- END BUTTON TABLE --%>


</html:form>

<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
