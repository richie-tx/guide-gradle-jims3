<!DOCTYPE HTML>
<%--
 ************************************************************************** 
 * Used to create new transferred offense referrals                       * 
 * This page is identical to juvTabReferral/transferredOffensesCreate.jsp *  
 * Any changes made to this page may also need to be done to its twin     *  
 ************************************************************************** --%>
<%--MODIFICATIONS --%>
<%-- 06/05/2013	CShimek		ER#75613 Create JSP  --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - caseTabAssignedReferrals - transferredOffensesCreate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/transferredOffenseReferralCreate.js"></script>
<script>
$(document).ready(function(){
	setTimeout(function(){ setTransOffense() }, 100);
})

</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/handleJuvenileProfileTransferredOffensesSelection"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Casefile Transferred Offenses Create</td> 
	</tr> 
	<logic:notEqual name="transferredOffenseForm" property="errMessage" value="">
		<tr> 
			<td align="center" class="errorAlert" ><bean:write name="transferredOffenseForm" property="errMessage" /></td> 
		</tr> 
	</logic:notEqual>
	<logic:notEqual name="transferredOffenseForm" property="confirmMsg" value="">
		<tr> 
			<td align="center" class="confirm" ><bean:write name="transferredOffenseForm" property="confirmMsg" /></td> 
			<input type="hidden" name="validateMsg" value="<bean:write name="transferredOffenseForm" property="validateMsg" />"  id="valOffMsgId" />
		</tr> 
	</logic:notEqual>
</table> 
<%-- END HEADING TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Complete fields and select Add button to add to list.</li> 
				<li>Select Back button to return to previous page.</li> 
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"> *All date fields must be in the format of mm/dd/yyyy.</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class="spacer"></div>
<%-- BEGIN TABS ALIGNMENT TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="toIndextab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
		</td>
	</tr>
	<tr>
		<td>	
<%--tabs end--%> 
<%-- BEGIN BLUE TABS BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
<%-- BEGIN GREEN TABS TABLE --%> 						
  						<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								</tiles:insert>
								</td>
  							</tr>
  							<tr>
  								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
  						</table>
<%-- END GREEN TABS TABLE --%>   						
<%-- BEGIN GREEN TABS BORDER TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
									<div class='spacer'></div>
									<tiles:insert page="/jsp/juvTabReferral/transferredOffenseCreateTile.jsp" flush="true">
    								</tiles:insert>
								</td>
							</tr>
						</table>
<%-- END GREEN TABS BORDER TABLE --%> 				
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- BEGIN BLUE TABS BORDER TABLE --%>			
			<div class='spacer4px'></div>
		</td>
	</tr>
</table>
<%-- END TABS ALIGNMENT TABLE --%>			
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>