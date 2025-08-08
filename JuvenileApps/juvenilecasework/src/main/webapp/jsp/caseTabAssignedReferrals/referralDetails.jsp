<!DOCTYPE HTML>

<%-- ************** NOTES ***************************
    Used to display casefile petition details
    06/10/2005 glyons  Create JSP 
**************************************************--%>
<%-- 06/29/2007	Uma Gopinath Modify JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/21/2015 RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Referrals) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - referralDetails.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
</head>
<%--END HEAD TAG--%>


<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<form name="referralDetailsForm">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|104">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Casefile Referral Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%> <br>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
		<ul>
			<li>Click on a hyperlinked Disposition Date to view Disposition detail.</li>
			<li>Select the Back button to return to the previous page.</li>
		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 

<%-- BEGIN CASEFILE HEADER INFO --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END CASEFILE HEADER INFO --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
  		<%--tabs start--%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="assignedreferralstab" />
  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
  		</tiles:insert>
  		<%--tabs end--%>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td valign="top">
								  <tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan" />
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>
								</td>
  						</tr>
  						<tr>
  							<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
  					</table>
						<%-- 10apr08 - mjt removing for hacked fix </html:form> --%>

						<%-- BEGIN DETAILS --%> 
					  <html:form action="/displayJuvenileCasefilePetitionDetails.do" target="content">
							<tiles:insert page="../caseworkCommon/referralDetailsTile.jsp" flush="true">				
					  		<tiles:put name="assignedReferralsForm" beanName="assignedReferralsForm"/>		
							</tiles:insert>
					  </html:form>				
					 </td>
					</tr>
				</table>
			</td>
		</tr>
</table>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
