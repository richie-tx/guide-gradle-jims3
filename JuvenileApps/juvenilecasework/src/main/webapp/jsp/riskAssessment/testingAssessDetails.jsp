<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/20/2005		DWilliamson	Create Testing AssessDetails jsp--%>
<%-- 07/20/2009     CShimek    #61004 added timeout.js  --%>
<%-- 03/15/2011     CShimek    replaced local js with assessDetails.js  --%>
<%-- 05/01/2011     CShimek    #69765 Add code to display Summary/Conformation in heading --%>
<%-- 05/04/2011		DGibler	   #69838 added CLM Update --%>
<%-- 04/19/2012     CShimek    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/16/2012 	CShimek    #73565 added age > 20 check (juvUnder21) to Submit button --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.RiskAnalysisConstants" %>
<%@ page import="naming.Features" %>



<head>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- testingAssessDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assessDetails.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayRiskAssessmentDetails" target="content">

<html:hidden name="riskAnalysisForm" property="action"/> 
<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|152">
</logic:equal>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/> - Risk Assessment Previous Testing Information
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'> 
				<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
					<bean:message key="prompt.override"/>
					<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|152">
				</logic:equal>	
				<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
					<bean:message key="prompt.summary"/>
					<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|230">
				</logic:equal>
				<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
					<bean:message key="prompt.confirmation"/>
					<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|231">
				</logic:equal>
			</jims2:isAllowed>	
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN CONFIRMATION TABLE --%>
<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
		<table width="98%" border="0">
			<tr>
				<td class="confirm">Referral Override saved.</td>
			</tr>
		</table>
	</logic:equal>
</logic:equal>
<%-- END CONFIRMATION TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
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

<%-- BEGIN Program Referral History TABLE --%>
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
									  	<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
  								  </tr>
							  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign="top" align="center">

                        <div class='spacer'></div>
                        <table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
                        	<tr>
                        		<td class="detailHead" colspan="2"><bean:message key="prompt.testing"/>&nbsp;<bean:message key="prompt.info"/>: <nested:write  name="riskAnalysisForm" property="assessmentId"/></td>
                        	</tr>
                        	<tr>
                        		<td align="center">
                        			<table width="100%"  cellpadding="4" cellspacing="1">				
                        				<tr>
                        					<td class="formDeLabel" colspan="3" nowrap="nowrap">Date Taken</td>
                        					<td class="formDe"><nested:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/></td>
                        				</tr>	
                        				<tr>
                        					<td class='formDeLabel' colspan="3" nowrap="nowrap"><bean:message key="prompt.supervisionNumber"/></td>
                        					<td class='formDe'>
                        						<nested:write  name="riskAnalysisForm" property="casefileID"/>
                        					</td>
                             			</tr>
                        				<tr>
                        					<td class="formDeLabel" colspan="3" nowrap="nowrap">Part</td>
                        					<td class="formDe"><bean:write name="riskAnalysisForm" property="riskAssessmentTypeDesc" /></td>
                        				</tr>
                        				<logic:iterate id="questions" name="riskAnalysisForm" property="assessmentDetailsResponseList">
                        					<tr>
                        						<td class="formDeLabel" nowrap="nowrap" colspan="3"><bean:write name="questions" property="riskQuestionText" /></td>
                        						<td class="formDe"><bean:write name="questions" property="answerText" /></td>
                        					</tr>	
                        				</logic:iterate>
                        			</table>
                        		</td>
                        	</tr>		
                        	<logic:notEmpty name="riskAnalysisForm" property="modReason">
			                	<tr>
        							<td colspan='4'>
										<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
											<tr>
	                        					<td class='formDeLabel' colspan='4'>Reason for modification</td>
    	                  					</tr>
        	                    			<tr>
            	                				<td class='formDe' colspan='4'>
            	                					<bean:write name="riskAnalysisForm" property="modReason" />
            	               					</td>
                	           				</tr>
                    	       			</table>
                        	     	</td>
        						</tr>
        					</logic:notEmpty>	
                        </table>
                        <%-- END Program Referral History TABLE --%>
                          <!-- BEGIN RECOMMENDATION & OVERRIDE SECTION TABLE -->
						  <tiles:insert page="recommendationAndOverrideTile.jsp" flush="true">
						  	<tiles:put name="formName" type="String" value="riskAnalysisForm" />
						  </tiles:insert>
						  <%-- END RECOMMENDATION & OVERRIDE SECTION TABLE --%> 
						  <div class='spacer'></div>
                 			<%-- BEGIN BUTTON TABLE --%>
                        					<table border="0" width="100%">
												<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>">
                        							<tr>
	                           							<td align="center">
							     							<%-- this is when we're in data entry mode --%>
			                         						<html:submit property="submitAction" onclick="document.forms[0].action.value = 'back';"><bean:message key="button.back"></bean:message></html:submit>
															<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																<logic:equal name="riskAnalysisForm" property="allowUpdates" value="true">					
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
				    	                     							<html:submit property="submitAction" onclick="document.forms[0].action.value = 'summary'; return checkOverrideRadioButtons(); return disableSubmit(this, this.form);"><bean:message key="button.submit" /></html:submit>
																	</jims2:isAllowed>
		
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_CLM_U%>'>  
																		<logic:equal name="riskAnalysisForm" property="overNinetyDays" value="<%=UIConstants.YES_FULL_TEXT%>">
																			<input type="button" value="Update" onclick="location.href='/<msp:webapp/>displayTestingUpdate.do?mode=update'">
																		</logic:equal>
																	</jims2:isAllowed>
																</logic:equal>
															</logic:equal>
															<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
		        	                 							<html:submit property="submitAction" onclick="document.forms[0].action.value = 'cancel';"><bean:message key="button.cancel"/></html:submit>
															</jims2:isAllowed>
	                    	       						</td>
                         							</tr>
												</logic:equal>
									
												<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>"> 
	                        						<tr>
	                           							<td align="center">
        					                     			<html:submit property="submitAction" onclick="document.forms[0].action.value = 'back';"><bean:message key="button.back"></bean:message></html:submit>
	      						                       		<html:submit property="submitAction" onclick="document.forms[0].action.value = 'confirm'; "><bean:message key="button.finish" /></html:submit>
    	   						                      		<html:submit property="submitAction" onclick="document.forms[0].action.value = 'cancel';"><bean:message key="button.cancel"/></html:submit>
	    	                       						</td>
	                         						</tr>
                           						</logic:equal>

						   						<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
	                       	    					<tr>
	                           							<td align="center">
                               								<html:submit property="submitAction" onclick="document.forms[0].action.value = 'cancel';"><bean:message key="button.returnToRiskAnalysis" /></html:submit>
	                           							</td>
	                         						</tr>
                           						</logic:equal>
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

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>">
		<logic:equal name="riskAnalysisForm" property="overRiddenReasonCd" value="<%=naming.RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_CODE%>">
			<script type='text/javascript'>
				show( 'overrideTextAreaRow', SHOW_ITEM, 'row' ) ;
			</script> 
		</logic:equal>
	</logic:equal>
</jims2:isAllowed>

</html:form>

</body>
</html:html>