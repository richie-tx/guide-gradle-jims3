<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/20/2005		DWilliamson	Create Interview RA jsp--%>
<%-- 07/20/2009     CShimek     #61004 added timeout.js  --%>
<%-- 05/01/2012		CShimek		#73346 Revise hardcoded TJPC prompts to TJJD --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm" %>
<%@ page import="ui.juvenilecase.form.riskanalysis.RiskAnalysisForm" %>
<%@ page import="java.awt.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="messaging.juvenilecase.reply.RiskQuestionResponseEvent" %>



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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- courtReferralRA.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>


</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:hidden name="riskAnalysisForm" property="mode" styleId="theMode"/>
<html:form action="/displayCourtReferralSummary"> 
<logic:notEqual name="riskAnalysisForm" property="mode" value="update">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|196">
</logic:notEqual>
<logic:equal name="riskAnalysisForm" property="mode" value="update">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|208">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
    	<td align="center" class="header">
	     	<bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk Information	
	    	<logic:equal name="riskAnalysisForm" property="mode" value="update">
				Update 
			</logic:equal>
    	</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
    	<td>
			<ul>
        		<li>Complete <strong>all</strong> TJJD Risk questions and then select Next button to view summary.</li>
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

<%-- hyperlink table for access to viewing traits --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td valign='top'>
      		<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
		        <tr>
		          <td align='center' class='detailHead'>
		      	    <a href='javascript:openCustomRestrictiveWindow("/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&casefileId=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>&submitAction=Find", 600, 700);'>View Traits&nbsp;&nbsp;(in new window)</a>
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
                      										TJJD Risk 
                      									</td>
                      								</tr>
                      								
                      								
                      								<%-- <logic:equal name="riskAnalysisForm" property="mode" value="update">
													          		<tr id='modificationReasonRow' class=''>
				        												<td colspan='2'>
														          			
																		 		<tr>
						                 											<td class='formDeLabel' colspan='2'>Supervision Number&nbsp;
						                 											</td>
						                 											<td class='formDe' colspan='2'><html:textarea name="riskAnalysisForm" property="modReason" rows='3' style="width:100%" onmouseout="textCounter(this,550)" onkeydown="textCounter(this,550)" styleId="reasonMod"></html:textarea></td>
							  	              									</tr>
					      	          										
					          	      									</td>
				        											</tr>
																</logic:equal> --%>
																
																
										<tr>
								             <td align='center'>
												<table width='100%' cellpadding="4" cellspacing="1">
													<tr>
														<logic:equal name="riskAnalysisForm" property="mode" value="update"> 
	      													<td class='formDeLabel'>
																<%--  <logic:notEqual name="riskAnalysisForm" property="action" value="update">
																</logic:notEqual> --%>  
																<bean:message key="prompt.supervision"/> <bean:message key="prompt.number"/>
															</td>
	      													<td class='formDe'>      														
																<html:select name="riskAnalysisForm" property="casefileID" styleId="tFId" onchange="checkSelect(this)" >
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="riskAnalysisForm" property="riskActiveCaseFiles" value="supervisionNum" label="supervisionNum" />
																</html:select>  
																    														
	      													</td>	      												
	      												 </logic:equal>
	      											</tr>
                      							</table>
                      						</td>
                      					</tr>                   							                     								
                      								
                      								
	                    							<tr>
								                    	<td align='center'>
                        									<table width='100%' cellpadding="4" cellspacing="1">
                        									                                
                      											<tiles:insert page="../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
																	<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
																	<tiles:put name="tilesImageLevel" value=""/>
																</tiles:insert>
																
																<!-- Java Code is needed to clear out a question's answers after setting them in the JSP -->
									                        	<!-- A questions answer's are set when the user comes from a the details page in update mode -->									                        	
									              				<% 
									              				/* RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)session.
									              			    getAttribute("riskCourtReferralForm");
									              				RiskAnalysisForm riskForm = (RiskAnalysisForm)session.
									              			    getAttribute("riskAnalysisForm");
									              				
									              				if (riskForm.getMode() != null && riskForm.getMode().equalsIgnoreCase("update")) 
									              				{
																	ArrayList myList = new ArrayList(); //Master List of Questions & Answers
										              				Iterator ite = refForm.getQuestionAnswers().iterator();
										              				while( ite.hasNext() )
										              				{
										              					 
										              					RiskQuestionResponseEvent question = (RiskQuestionResponseEvent)ite.next();
										              					question.setSelectedAnswerID(null);
										              					question.setSelectedAnswerIDs(null);
										              					question.setSelectedAnswerWeight(null);
										              					question.setSelectedChronicID(null);
										              					question.setSelectedChronicIDs(null);
										              					 
										              					myList.add(question);
										              					 
										              				 }	     
										              			     refForm.setQuestionAnswers(myList);
									              				}*/
																%> 
																
															<logic:equal name="riskAnalysisForm" property="mode" value="update">
													          		<tr id='modificationReasonRow' class=''>
				        												<td colspan='2'>
														          			<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
																		 		<tr>
						                 											<td class='formDeLabel' colspan='2'>Reason for modification&nbsp;
						                 												<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                  											<tiles:put name="tTextField" value="modReason"/>
								                   											<tiles:put name="tSpellCount" value="spellBtn1" />
						                												</tiles:insert>
						                												(Max. characters allowed: 550)
					                												</td>
						                 										</tr>
							                 									<tr>
							                 										<td class='formDe' colspan='2'><html:textarea name="riskAnalysisForm" property="modReason" rows='3' style="width:100%" onmouseout="textCounter(this,550)" onkeydown="textCounter(this,550)" styleId="reasonMod"></html:textarea></td>
							  	              									</tr>
					      	          										</table>
					          	      									</td>
				        											</tr>
																</logic:equal>
                       										
                       										</table>
                    									</td>
                  									</tr>
                  								</table>
                        						
                        						<logic:equal name="riskAnalysisForm" property="mode" value="update">
                        						
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
												
												</logic:equal>

							                  	<%-- BEGIN BUTTON TABLE --%>
							                  	<div class='spacer'></div>
							                  	<table border="0" width="100%">
							                    	<tr>
								                      	<td align="center">
								                  	    	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
								                  	    	<html:submit property="submitAction" onclick="return validateFields(this.form)"><bean:message key="button.next" /></html:submit>
								                  	    	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
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
    	</td>
	</tr>
</table>

</html:form>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
