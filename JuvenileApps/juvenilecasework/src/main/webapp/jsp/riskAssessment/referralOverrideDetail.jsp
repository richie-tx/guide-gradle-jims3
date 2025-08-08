<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/19/2005		DWilliamson	Create Risk Analysis RA Summary jsp--%>
<%-- 03/15/2011     CShimek    replaced local js with assessDetails.js  --%>
<%-- 04/19/2012     CShimek    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/16/2012     CShimek    #73565 added age > 20 check (juvUnder21) to Submit button --%>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- referralOverrideDetail.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascripts --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assessDetails.js"></script>
<script>
	$(document).ready(function(){
		var recommendationOverridden = localStorage.getItem("recommendationOverridden");
		
		$('input[name="recommendationOverridden"]').change(function(){
			localStorage.setItem('recommendationOverridden', $('input[name="recommendationOverridden"]:checked').val() );
		})
		
		
		if (recommendationOverridden == "true") {
			show( 'overrideEditRow', SHOW_ITEM, 'row' ) 
			show( 'overRideTypeRow', SHOW_ITEM, 'row' ) ;
			
		} else {
			show( 'overrideEditRow', HIDE_ITEM, 'row' ) 
			show( 'overRideTypeRow', HIDE_ITEM, 'row' ) ;
			
		}
		
		$("#back").click(function(){
			localStorage.removeItem('recommendationOverridden');
			localStorage.removeItem('overRideType')
		})
	})
</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayRiskAssessmentDetails" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<%-- debugging field --%>
	<tr class='text'>
		<td> 
			<html:hidden name="riskAnalysisForm" property="action"/> 
		</td>
	</tr>
	
	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>">
	    
  		<%-- this is when we're in data entry mode --%>
  		<tr id='detailTitle'>
    		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.detentionRiskAssessmentInformation"/>&nbsp;Details</td>
    		<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="false">
    		   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|212">
    		</logic:equal>
  		</tr>

  		<tr id='overrideTitle' class='hidden'>
    		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.detentionRiskAssessmentInformation"/>&nbsp;Override</td>
    		<logic:equal name="riskAnalysisForm" property="recommendationOverridden" value="true">
    		   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|213">
    		</logic:equal>
	  </tr>
	</logic:equal>

	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|214">
		<tr>
	    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.detentionRiskAssessmentInformation"/>&nbsp;Override Summary</td>
	  	</tr>
	</logic:equal>

	<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
	    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|216">
		<tr>
	    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.detentionRiskAssessmentInformation"/>&nbsp;Override Confirmation</td>
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
					<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
			        		<li>Select Yes to Override Recommendation.</li>
				    	</jims2:isAllowed>
			    	</logic:equal>
	        		<li>Select Back button to return to Risk Analysis list.</li>
	      		</ul>
	      		<ul class='hidden' id='overrideInstruction'>
	      			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
				      	<li>Select a Release Override or Detention Override reason.</li>
				        <li>Select Override - Other (Explain) to enter a free-form text reason.</li>
				        <li>Select the Submit button to view the Summary.</li>
			        </logic:equal>
	      		</ul>
			</logic:equal>	

			<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
	      		<ul>
	        		<li>Review information - if changes are required, select the Back button.</li>
	        		<li>Select the Finish button to save information and view the Confirmation.</li>
	      		</ul>
			</logic:equal>

			<logic:equal name="riskReferralForm" property="secondaryAction" value="<%=UIConstants.SUCCESS%>">
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
									  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width='5'></td>
								    </tr>
							  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign='top' align='center'>
											<div class='spacer'></div>
											<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
                        							<tr>
                        								<td class="detailHead" colspan="2">
                        									<logic:notEqual name="riskAnalysisForm" property="effectiveDate" value="true">
                      											<bean:message key="prompt.detentionRiskAssessment"/>
                      										</logic:notEqual>
                      										<logic:equal name="riskAnalysisForm" property="effectiveDate" value="true">
                      											DETENTION SCREENING INSTRUMENT
                      										</logic:equal>
                      										: <nested:write  name="riskAnalysisForm" property="assessmentId"/>
                      									</td>
                      								</tr>
                        							<tr>
                        								<td align='center'>
                        									<table width='100%' cellpadding='4' cellspacing='1'>
                        										<tr>
						                        					<td class='formDeLabel' width="50%"><bean:message key="prompt.dateTaken"/></td>
						                        					<td class='formDe' width="50%">
						                        						<nested:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/>
						                        					</td>
						                             			</tr>
						                             			
						                             			<tr>
						                        					<td class='formDeLabel' width="50%"><bean:message key="prompt.supervisionNumber"/></td>
						                        					<td class='formDe' width="50%">
						                        						<nested:write  name="riskAnalysisForm" property="casefileID"/>
						                        					</td>
						                             			</tr>
						                         										                   			  			
									                   			<tr>
									                        		<td class='detailHead' colspan="2"><bean:message key="prompt.referral"/>&nbsp;<bean:message key="prompt.info"/></td>
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
											  <!-- BEGIN RECOMMENDATION & OVERRIDE SECTION TABLE -->
											  <tiles:insert page="recommendationAndOverrideTile.jsp" flush="true">
											  	<tiles:put name="formName" type="String" value="riskAnalysisForm" />
											  </tiles:insert>
											  <%-- END RECOMMENDATION & OVERRIDE SECTION TABLE --%> 
                        					<%-- BEGIN BUTTON TABLE --%>
											<div class='spacer'></div>
                        					<table border="0" width="100%">
												<logic:equal name="riskAnalysisForm" property="secondaryAction" value="<%=UIConstants.EDIT%>">
                        							<tr>
	                           							<td align="center">
							     							<%-- this is when we're in data entry mode --%>
			                         						<html:submit styleId="back" property="submitAction" onclick="document.forms[0].action.value = 'back';"><bean:message key="button.back"></bean:message></html:submit>
															<logic:equal name="riskAnalysisForm" property="allowUpdates" value="true">					
																<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_OVERRIDE_RECOMMENDATION%>'>  
				    	                     							<html:submit property="submitAction" onclick="document.forms[0].action.value = 'summary'; return checkOverrideRadioButtons(); return disableSubmit(this, this.form);"><bean:message key="button.submit" /></html:submit>
																	</jims2:isAllowed>
											
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_CLM_U%>'>  
																		<logic:equal name="riskAnalysisForm" property="overNinetyDays" value="<%=UIConstants.YES_FULL_TEXT%>">
																			<input type="button" value="Update" onclick="location.href='/<msp:webapp/>displayReferralUpdate.do?isNewReferral=true&mode=update'">
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