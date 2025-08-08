<!DOCTYPE HTML>

<%-- To display Violation of Probation (VOP) SUMMARY in Juvenile Casework --%>
<%--MODIFICATIONS --%>
<%-- 05/22/2023 NM US 160069 Create JSP  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

<%@ page import="naming.Features" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta charset="UTF-8" />
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

<title><bean:message key="title.heading"/> - juvTabReferral - vopSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/vopOffenseReferralCreate.js"></script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/submitVOPCreateAction"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Juvenile Profile - Summary of Violation of Probation Details</td> 
	</tr> 
	<logic:notEqual name="vopOffenseForm" property="confirmMsg" value="">
 	<tr>
		<td class="confirm"><bean:write name="vopOffenseForm" property="confirmMsg" /></td>
	</tr> 
	</logic:notEqual>
</table> 
<%-- END HEADING TABLE --%> 

<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<logic:equal name="vopOffenseForm" property="confirmMsg" value="">
<table width="98%" border="0">
	<tr>
		<td style="font-family: Geneva, Arial, Helvetica, sans-serif; font-size: medoium; font-weight: bold; 	color: #008000;">
			<ul>
				<li><b>The following values have been entered. Please review for accuracy before clicking the Finish button.</b></li> 
			</ul>
		</td>
	</tr>
</table>
</logic:equal>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	        	<tiles:put name="tabid" value="referralstab"/>
	         	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			</tiles:insert>	
		</td></tr>
		<tr>  			
		  	<td bgcolor='#33cc66' height="5"></td>
		</tr></table>
<%--tabs end--%> <tr><td>
			
						<div class=spacer></div>
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
  						</table></td>
	</tr>
<%-- BEGIN DETAIL TABLE --%> 
						
									<div class='spacer'></div>
<%-- BEGIN OF VOP SUMMARY TABLE--%> 
			<table width='98%' border="0" cellpadding="2" cellspacing="3" class="borderTableBlue"> 
				<tr> 
					<td class="detailHead" colspan="2">Violation of Probation Details</td> 
				</tr> 
				<tr>
					<td class="formDeLabel" colspan="1" width="1%"  nowrap><bean:message key="prompt.vopShort"/></td>
					<td class='formDe' colspan="3"> 
					<bean:write name="vopOffenseForm" property="referralNum"/> -  
					<logic:iterate id="offenseList" name="vopOffenseForm" property="offensesVOPs" indexId="indexOff"> 
    				<bean:write name="offenseList" property="offenseDescription"/>  
    					 <logic:notEqual name="vopOffenseForm" property="offenseCollectionSize" value="<%=indexOff.toString()%>">,&nbsp;&nbsp; 
    					</logic:notEqual> 
    				</logic:iterate>&nbsp;&nbsp;&nbsp;<bean:write name="vopOffenseForm" property="petitionNumVOP"/>
					</td>
				</tr>
				<tr>
				<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.inCountry"/><bean:message key="prompt.referral"/></td>
				<td class='formDe' colspan="3"> <bean:write name="vopOffenseForm" property="selectedPetition"/> - <span title='<bean:write name="vopOffenseForm" property="petitionAllegationDesc"/>'><bean:write name="vopOffenseForm" property="petitionAllegation"/></span>, <bean:write name="vopOffenseForm" property="petitionNum"/></td>
				</tr>
					<logic:equal name="vopOffenseForm" property="selectedSubSevType4VOP" value="E">
				<tr>
				<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/> <bean:message key="prompt.adult"/> <bean:message key="prompt.indicator"/></td>
				<td class='formDe' colspan="3"> <bean:write name="vopOffenseForm" property="adultIndicatorStr"/></td>
				</tr>
				<tr>
				<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.charge"/> <bean:message key="prompt.location"/> </td>
				<td class='formDe' colspan="3"> <bean:write name="vopOffenseForm" property="locationIndicator"/></td>
				</tr>
				<tr>
				<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/></td>
				<td class='formDe' colspan="3"> <bean:write name="vopOffenseForm" property="offenseCharge"/></td>
				</tr>
				<tr>
				<td class="formDeLabel" colspan="1" width="20%"  nowrap><bean:message key="prompt.newIncoming"/> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/> <bean:message key="prompt.date"/></td>
				<td class='formDe' colspan="3"> <bean:write name="vopOffenseForm" property="offenseChargeDate"/></td>
				</tr>
				</logic:equal>
			</table>
<%-- END OF SUMMARY TABLE--%> 
			<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
			<logic:notEqual name="vopOffenseForm" property="confirmMsg" value="">
				<tr width='98%'>
					<td align="center"><input type="button"
						onclick="spinner(); goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum=<bean:write name='vopOffenseForm' property='juvenileNum'/>')"
						value="<bean:message key='button.referralBriefingDetails'/>" /></td>
				</tr>
			</logic:notEqual>
			<logic:equal name="vopOffenseForm" property="confirmMsg" value="">
			<tr>
				<td align="center">
				<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>			
				<html:submit property="submitAction" styleId="finishAddVOPDetailsBtn"><bean:message key="button.finish" /></html:submit>
				<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')">
				</td>
			</tr>
			</logic:equal>
	</table>
<%-- END BUTTON TABLE --%>
						<div class='spacer'></div> 
								
<%-- END DETAIL TABLE --%> 						
						<div class='spacer'></div>

		

<div class='spacer4px'></div>

</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>