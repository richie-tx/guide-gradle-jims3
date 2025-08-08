<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/20/2005		DWilliamson	Create Interview RA Summary jsp--%>
<%-- 05/31/2011		DGibler		#JIMS200058178 Risk: CLM modification of Completed Assessments --%>
<%-- 05/01/2012		CShimek		#73346 Revise hardcoded TJPC prompts to TJJD --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/c.tld" prefix="c" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<head>
<msp:nocache />
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- courtReferralCompletionRASummary.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<!-- Pointing to the same place, move this over to the summary -->
<logic:equal name="riskAnalysisForm" property="mode" value="update">
	<c:set var="actionURL" value="/submitCourtReferralCompletionUpdate" scope="request"/>  
</logic:equal>

<logic:notEqual name="riskAnalysisForm" property="mode" value="update">
	<c:set var="actionURL" value="/submitCourtReferralCompletion" scope="request"/> 
</logic:notEqual>

<html:form action="${actionURL}" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|205">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk Completion Summary</td>
  	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
    	<td>
  	  		<ul>
        		<li>Select Finish button to finish completion of TJJD Risk.</li>
        		<li>Select Back button to return to previous page.</li>
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

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign="top">
    		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    			<tiles:put name="tabid" value="casefiledetailstab"/>
    			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    		</tiles:insert>				

			<%-- BEGIN DETAIL TABLE --%>
  			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  				<tr>
  			  	<td valign="top" align="center">
  			  
    			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign="top">
    										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="riskAnalysis"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  											</tiles:insert>	
    										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
								    </tr>
						  	  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign="top" align="center">
  											<%-- BEGIN TABLE --%>
											<div class='spacer'></div>
                       						<table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">
	                       							<tr>
	                   									<td class="detailHead">
	                   										TJJD Risk Completion
	                   									</td>
	                     							</tr>
	                     							<tr>
								                    	<td align='center'>
								                    		<table width='100%' cellpadding='4' cellspacing='1'>
																	<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										JJS Court Decision  
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="jjsCourtDecision"/>
					            									</td>
					            								</tr>
	                       									                                
	                     										<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Collateral Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="collateralVisits"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Face To Face Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="face2FaceVisits"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Court Disposition TJJD 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<!-- <bean:write name="riskCourtReferralForm" property="courtDispositionTJPC"/> -->
					            										<bean:write name="riskCourtReferralForm" property="courtDispositionTJPCDesc"/>
					            									</td>
					            								</tr>
		                                					</table>
	                   									</td>
	                 								</tr>
	                       						</table>
                       						<%-- END TABLE --%>

                       						<%-- BEGIN BUTTON TABLE --%>
											<div class='spacer'></div>
                       						<table border="0" width="100%">
                         						<tr>
                           							<td align="center">
                             							<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
                        								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
                        								<html:submit property="submitAction" ><bean:message key="button.cancel"/></html:submit> 
                           							</td>
                         						</tr>
                       						</table>
                        					<%-- END BUTTON TABLE --%>
										</td>
									</tr>
								</table>
                			</td>
              			</tr>
            		</table>
					<div class='spacer'></div>
          			</td>
        		</tr>
      		</table>
      		<div class='spacer'></div>
    	</td>
	</tr>
</table>
<div class='spacer'></div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

