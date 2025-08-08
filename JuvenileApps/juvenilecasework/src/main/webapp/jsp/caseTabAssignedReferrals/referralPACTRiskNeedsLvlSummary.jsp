<!DOCTYPE HTML>

<%-- Used to create PACT Risk/Needs Level --%>
<%--MODIFICATIONS --%>
<%-- 02/08/2017	 ugopinath	Create JSP --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>



<%--BEGIN HEADER TAG--%>
<head>

<msp:nocache />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

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

<title><bean:message key="title.heading"/> - caseTabAssignedReferrals - referralPACTRiskNeedsLvlSummary.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 

<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>

<script>
	$(document).ready(function(){
		$("#finish").click(function(){
			sessionStorage.removeItem("selectedReferralNumber");
			sessionStorage.removeItem("offenses");
			sessionStorage.removeItem("lastPactDate");
			sessionStorage.removeItem("pdaReadOnly");
		})
	})
</script>
 
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/submitJuvenileCasefileRiskNeedLvl"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Casefile Referral - Create Referral PACT Risk/Need Levels 
		<logic:equal value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">Confirmation</logic:equal>
		<logic:notEqual value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">Summary</logic:notEqual></td> 
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<logic:equal value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">
				<ul>
					
					<li>Select the Referral List button to return to list.</li>
				</ul>
			</logic:equal>
			<logic:notEqual value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">
			<ul>
				<li>Review information, then select the Finish button to save the information.</li> 
				<li>Select the Back button to return to the previous page to change information.</li>
			</ul>
			</logic:notEqual>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="assignedreferralstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
		</td>
	</tr>
	<tr>	
		<td>
<%--tabs end--%> 
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
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
<%-- BEGIN DETAIL TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
<%-- NOT PART OF I10 --%> 		
									
									<table width='98%' border="0" cellpadding="2" cellspacing="0"> 
									<tr>
										<td>
											<table width='100%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
												<tr>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.referral" /></td>
													<td class="formDe" width='3%'><bean:write name="juvenileCasefileForm" property="selectedValue"/>	</td>
													<td align='left' class="formDeLabel" width='1%' nowrap><bean:message key="prompt.pactDate" /></td>
													<td class="formDe" width='3%'> <bean:write property="userPACTDate" name="juvenileCasefileForm"/> 	</td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.riskLevel" /></td>
													<td class="formDe" width='3%'><bean:write property="riskLvlDesc" name="juvenileCasefileForm"/> </td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.needLevel" /></td>
													<td class="formDe" width='3%'>	<bean:write property="needLvlDesc" name="juvenileCasefileForm"/></td>
													<td class="formDeLabel" align='left' width='1%' nowrap><bean:message key="prompt.pactId" /></td>
													<td class="formDe" width='3%'>	<bean:write property="userPactId" name="juvenileCasefileForm"/></td>
												</tr>
												<tr></tr>
											
		      								</table>
			      						</td>
			      					</tr>
								</table>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" cellpadding=1 cellspacing=1 align=center>
										<tr>										
											<td align="center">		
												<logic:notEqual value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">								
													<html:submit property="submitAction" styleId="back">
														<bean:message key="button.back"></bean:message>
													</html:submit>		
													<html:submit property="submitAction" styleId="finish">
														<bean:message key="button.finish"></bean:message>
													</html:submit>			
													<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
												</logic:notEqual>
												<logic:equal value="riskNeedConfirm" name="juvenileCasefileForm" property="secondaryAction">
														<input type="button" value="Return To Referral List" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&casefileId=casefileId&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
												</logic:equal>												
											</td>											
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class='spacer'></div> 
								</td>
							</tr>
						</table>
<%-- END DETAIL TABLE --%> 						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class='spacer4px'></div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>