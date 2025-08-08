<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/19/2005		DWilliamson	Create Risk Analysis RA Summary jsp--%>
<%-- 03/15/2011     CShimek     removed local js validation as it is no longer needed for this page --%>
<%-- 04/26/2011		DGibler		Implementation of multiple recommendations and scores. --%>
<%-- 05/04/2011		DGibler	   	#69838 added CLM Update --%>
<%-- 10/27/2011     CShimek    	#71686 added hidden field to accept button onclick setting of action value --%>
<%-- 04/19/2012     CShimek    	#73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 05/01/2012		CShimek		#73346 Revise hardcoded TJPC prompts to TJJD --%>
<%-- 07/16/2012 	CShimek     #73565 added age > 20 check (juvUnder21) to Update/Complete buttons --%>
<%-- 08/31/2015	 	RCarter	    #29426 html 5 compliance effort and jquery 5 (when required) --%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- courtReferralOverrideAssessDetail.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayRiskAssessmentDetails" target="content">

<%-- <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|206"> --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<%-- debugging field --%>
	<tr class='text'>
		<td> 
			<html:hidden name="riskAnalysisForm" property="secondaryAction"/> 
		</td>
	</tr>
	
	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|206">
  		<%-- this is when we're in data entry mode --%>
  		<tr id='detailTitle'>
    		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk&nbsp;Details</td>
  		</tr>

  		<tr id='overrideTitle' class='hidden'>
    		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk&nbsp;Override</td>
	    </tr>
	</logic:equal>   
	

	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|234">
		<tr>
	    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk&nbsp;Override Summary</td>
	  	</tr>
	</logic:equal>

	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|235">
		<tr>
	    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk&nbsp;Override Confirmation</td>
	  	</tr>
	  	<tr>
		    <td align="center" class='confirm'>Referral Override saved.</td>
	    </tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>"> 
				<%-- this is when we're in data entry mode --%>
				<ul id='detailInstruction'>
					<%-- 
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
			        		<li>Select Yes to Override Recommendation.</li>
				    	</jims2:isAllowed>
			    	--%>
	        		<li>Select Back button to return to Risk Analysis list.</li>
	      		</ul>
	      		<%--
	      		<ul class='hidden' id='overrideInstruction'>
			      	<li>Select a Release Override or Detention Override reason.</li>
			        <li>Select Override - Other (Explain) to enter a free-form text reason.</li>
			        <li>Select the Submit button to view the Summary.</li>
	      		</ul>
	      		--%>
			</logic:equal>	

			<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
	      		<ul>
	        		<li>Review information - if changes are required, select the Back button.</li>
	        		<li>Select the Finish button to save information and view the Confirmation.</li>
	      		</ul>
			</logic:equal>

			<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
	      		<ul>
	        		<li>Select the Return to Risk Analysis button to return to Risk Analysis.</li>
	      		</ul>
			</logic:equal>
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
	<td valign='top'>

      	<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
        	<tr>
          		<td align='center' class='detailHead'>
      	    		<a href='javascript:openCustomRestrictiveWindow("/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&casefileId=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&submitAction=Find", 600, 700);'>View Traits (in new window)</a>
      			</td>
        	</tr>
      	</table>

		<div class='spacer'></div>
    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				

        <%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  				<td valign='top' align='center'>
  			  		<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
				  	<div class='spacer'></div>
	  				<table width='98%' border="0" cellpadding="0" cellspacing="0">
				  		<tr>
  					  		<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign='top'>
	  										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="riskAnalysis"/>
    												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  											</tiles:insert>	
	  										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
								    </tr>
							  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign='top' align='center'>
											<div class='spacer'></div>
											<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
                        							<tr>
                        								<td class="detailHead" colspan="2">
                      										TJJD Risk: <nested:write  name="riskAnalysisForm" property="assessmentId"/>
                      									</td>
                      								</tr>
                        							<tr>
                        								<td align='center'>
                        									<table width='100%' cellpadding='4' cellspacing='1'>
                        										
                        										<tr>
						                        					<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionNumber"/></td>
						                        					<td class='formDe' width="50%">
						                        						<nested:write  name="riskAnalysisForm" property="casefileID"/>
						                        					</td>
						                             			</tr>
                        										
 								                      			<tiles:insert page="../caseworkCommon/riskAnalysisQuestionAnswersDetails.jsp" flush="false">
																	<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
																</tiles:insert>
                  												
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
	                          							</td>
	                        						</tr>
	                      						</table>
											
											<!-- BEGIN RECOMMENDATION SECTION -->
											<!-- TJJD (court referral) risk analysis does not allow for override. Also therefore, it does use the tile that the other
												 risk analysis use (recommendationAndOverrideTile.jsp). It is also the only risk analysis that is currently using
												 multiple recommendations and scores. All other Risk Analysis will need to be converted to multiple recommendations and scores
												 in the future. This will require a data conversion. For an example, please follow the TJPC Court Referral trail; from the 
												 UI Action Classes to the PD Event And Command Classes, the "how to" on how to make go from a single recommendation to being 
												 able to handle multiple is within the TJJD Risk Analysis.
											 -->
											<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>"> <%--  --%>
												<%-- this is the section shown for the Edit screen --%>
										 		<div class='spacer'></div>
						                        <table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">	
						                        	<tr>
					                            		<td class='detailHead'>Referral Recommendation</td>
					                          		</tr>
			                          				<tr>
			                            				<td align='center'>
			                            					<table width='100%' cellpadding='4' cellspacing='1'>
			                            						<logic:iterate id="recommendationsIndex" name="riskAnalysisForm" property="recommendations" indexId="index">
										
																	<tr bgcolor='#f0f0f0'>
																		<td align="center">
																			<table width='100%' cellpadding="4" cellspacing="0">
																				<tr>
																					<td class="formDeLabel" width='50%'><bean:write name="recommendationsIndex" property="resultGroupDisplayDesc"/> <bean:message key="prompt.recommendation" /></td>
																					<td class="formDe" width='50%'><bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/> - Total Score: <bean:write name="recommendationsIndex" property="riskAnalysisScore"/></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																
																</logic:iterate>
		                                					</table>
		                                				</td>
		                              				</tr>
			    	                			</table>
											</logic:equal>
						      							
						      			    <!-- BEGIN SUGGESTION CASE PLAN DOMAIN -->
						      			    <!-- Turn this into a tile if other risk analysis start to use them (see recommendationAndOverrideTile.jsp for example) -->
						      				<logic:notEmpty name="riskCourtReferralForm" property="suggestedCasePlanDomains">
												<%-- this is the section shown for the Edit screen --%>
										 		<div class='spacer'></div>
						                        <table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">	
						                        	<tr>
					                            		<td class='detailHead'>Suggested Case Plan Domains</td>
					                          		</tr>
			                          				<tr>
			                            				<td align='center'>
			                            					<table width='100%' cellpadding='4' cellspacing='1'>
																	<tr bgcolor='#f0f0f0'>
																		<td align="center">
																			<table width='100%' cellpadding="4" cellspacing="0">
																				<tr>
																					<td>
																						<logic:iterate id="suggestedCasePlanDomainsIndex" name="riskCourtReferralForm" property="suggestedCasePlanDomains" indexId="index">
							               													<bean:write name="suggestedCasePlanDomainsIndex"/>
							               													<logic:notEqual name="riskCourtReferralForm" property="suggestedCasePlanDomainsSizeMinusOne" value="<%=index.toString() %>">,</logic:notEqual>
							               												</logic:iterate>
							               											</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
		                                					</table>
		                                				</td>
		                              				</tr>
			    	                			</table>
											</logic:notEmpty>
																						
											<logic:empty name="riskCourtReferralForm" property="suggestedCasePlanDomains">
												<%-- this is the section shown for the Edit screen --%>
										 		<div class='spacer'></div>
						                        <table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">	
						                        	<tr>
					                            		<td class='detailHead'>Suggested Case Plan Domains</td>
					                          		</tr>
			                          				<tr>
			                            				<td align='center'>
			                            					<table width='100%' cellpadding='4' cellspacing='1'>
																	<tr bgcolor='#f0f0f0'>
																		<td align="center">
																			<table width='100%' cellpadding="4" cellspacing="0">
																				<tr>
																					<td>
																						None
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
		                                					</table>
		                                				</td>
		                              				</tr>
			    	                			</table>
											</logic:empty>	
											<!-- END SUGGESTION CASE PLAN DOMAIN -->
											
											<!-- BEGIN TJJD COMPLETION SECTION -->
											<logic:notEqual name="riskCourtReferralForm" property="completed" value="true">
												<%-- this is the section shown for the Edit screen --%>
										 		<div class='spacer'></div>
						                        <table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">	
						                        	<tr>
					                            		<td class='detailHead'>TJJD Risk Completion</td>
					                          		</tr>
			                          				<tr>
			                            				<td align='center'>
			                            					<table width='100%' cellpadding='4' cellspacing='1'>
																	<tr bgcolor='#f0f0f0'>
																		<td align="center">
																			<table width='100%' cellpadding="4" cellspacing="0">
																				<tr>
																					<td>
																						TJJD Risk has not been completed.
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
		                                					</table>
		                                				</td>
		                              				</tr>
			    	                			</table>
											</logic:notEqual>	
											
											<logic:equal name="riskCourtReferralForm" property="completed" value="true">
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
                  							</logic:equal>
                  							<!-- END TJJD COMPLETION SECTION -->
						      									
                        					<%-- BEGIN BUTTON TABLE --%>
											<div class='spacer'></div>
                        					<table border="0" width="100%">
												<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>"> <%-- return disableSubmit(this, this.form); --%>
                        							<tr id='editingAllButtonRow'>
	                           							<td align="center">
							     							<%-- this is when we're in data entry mode --%>
			                         						<html:submit property="submitAction" onclick="document.forms[0].action.value = 'back';"><bean:message key="button.back"></bean:message></html:submit>
															<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">													
																<logic:equal name="riskAnalysisForm" property="allowUpdates" value="true">
																	<logic:notEqual name="riskCourtReferralForm" property="completed" value="true">
		                                    							<input type="button" value="Update" onclick="location.href='/<msp:webapp/>displayCourtReferralUpdate.do?mode=update'">
																		<input type="button" value="Complete" onclick="location.href='/<msp:webapp/>displayCourtReferralCompletion.do?riskAnalysisId=<nested:write  name="riskAnalysisForm" property="assessmentId"/>'">
		                                    						</logic:notEqual>
																	
				        	                 						<logic:equal name="riskCourtReferralForm" property="completed" value="true">
				        	                 							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_TJPC_COURT_REF_UPDATE%>'>  
			                                   								<input type="button" value="Update" onclick="location.href='/<msp:webapp/>displayCourtReferralUpdate.do?mode=update'">
																			<input type="button" value="Complete" onclick="location.href='/<msp:webapp/>displayCourtReferralCompletion.do?riskAnalysisId=<nested:write  name="riskAnalysisForm" property="assessmentId"/>'">
		                                    							</jims2:isAllowed>
		                                    						</logic:equal>
			        	                 						</logic:equal>
		        	                 						</logic:equal>
		        	                 						<html:submit property="submitAction" onclick="document.forms[0].action.value = 'cancel';"><bean:message key="button.cancel"/></html:submit>
															
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
                           						<input type="hidden" name="action" value="" >
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

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
