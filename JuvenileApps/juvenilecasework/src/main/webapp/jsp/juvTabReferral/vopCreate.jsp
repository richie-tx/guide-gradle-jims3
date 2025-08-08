<!DOCTYPE HTML>
<%--
 ********************************************************************************* 
 * Used to create new VOP offense referrals                              * 
 *            *  
 ********************************************************************************* --%>
<%--MODIFICATIONS --%>
<%-- 06/08/2023 JSP Created NM --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - juvTabReferral - vopCreate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/vopOffenseReferralCreate.js"></script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form styleId="vopOffenseForm" action="/handleJuvenileProfileVOPsAction"  target="content" >


<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Juvenile Profile - Add Violation of Probation Details</td> 
	</tr> 

</table> 
<%-- END HEADING TABLE --%> 
<logic:messagesPresent message="true"> 
	<table width="100%">
		<tr>		  
			<td align="center" class="messageAlert">test<html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
		</tr>   	  
	</table>
</logic:messagesPresent> 
</br>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
</br>

<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Complete fields and select Next button to view the summary page.</li> 
				<li>Select Back button to return to previous page.</li> 
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">  *All date fields must be in the format of mm/dd/yyyy.</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class="spacer"></div>
<%-- BEGIN TABS ALIGNMENT TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	        	<tiles:put name="tabid" value="referralstab"/>
	         	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			</tiles:insert>				
		</td>
	</tr>	
	<tr>  			
	  	<td bgcolor='#33cc66' height="5"></td>
	</tr>
	<tr>
		<td>
<%--tabs end--%> 
<%-- BEGIN GREEN TABS BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
<%-- BEGIN BLUE TABS TABLE --%>						
  						<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								</tiles:insert>
								</td>
  							</tr>
  							<tr>
  								<td bgcolor='#6699FF' height="5"></td>
							</tr>
  						</table>
<%-- END BLUE TABS TABLE --%>	  						
<%-- BEGIN BLUE TABS BORDER TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
									<div class='spacer'></div>
									<tiles:insert page="/jsp/juvTabReferral/vopCreateTile.jsp" flush="true">
    								</tiles:insert>
								</td>
							</tr>
						</table>
<%-- END BLUE TABS BORDER TABLE --%> 						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>			
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