<!DOCTYPE HTML>

<%-- Used to display casefile details off Casefile Tab --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	 		Create JSP --%>
<%-- 03/14/2006     CShimek  		Defect# 29612, remove colons from prompts --%>
<%-- 01/09/2007 	Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/17/2009     C Shimek       	#61004 added timeout.js  --%>
<%-- 07/16/2012 	C Shimek     	#73565 added age > 20 check (juvUnder21) to Edit hyperlink --%>
<%-- 07/23/2012 	C Shimek     	#73735 revised to display supervision outcome description field --%>
<%-- 09/10/2012 	C Shimek     	#74186 Add Court Ordered Probation Start Date display field --%>
<%-- 11/05/2012		C Shimek	  	#74186 commented out changes made 9/10/12 per request of JP department --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>




<%--BEGIN HEADER TAG--%>
<head>
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
<title><bean:message key="title.heading"/> - casefileDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<%-- Pact Invocation  Task 41028   &&   //# task 44099 . U.S #41378--%>
<script type="text/javascript"> 

$(document).ready(function () {
	$("#pactLink").click(function(){
		$("#pactForm").submit();
		});
	var target = $("#pactForm").attr('target');
	$("#pactForm").on('submit',function(){
		var pactWindow = window.open('', target, 'width=1000,height=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no');
		pactWindow.focus();
	});
});

</script>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayJuvenileCasefileDetails.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|45">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefileDetails"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td>
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
				<ul>
					<li>Select <b>Edit</b> hyperlink to edit Expected End Date.</li>
				</ul>
			</logic:equal>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION/REQUIRED INFORMATION TABLE --%> 

<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>
<%-- BEGIN CASEFILE TABS TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
			<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
<%-- BEGIN BLUE TABS BORDER TABLE --%
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align="center">
						<div class='spacer'></div>
<%-- BEGIN CASEFILE INFO TABS TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
											<%--tabs start--%>
												<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="casefiledetailstab"/>
													<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width="5"></td>
										 </tr>
									</table>
<%-- BEGIN GREEN TABS BORDER TABLE --%>
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign='top' align="center">
                                                <div class=spacer></div>
                                                <table width='98%' border="0" cellpadding="0" cellspacing="0" >
													<tr>
														<td valign='top'>
			                                                <tiles:insert page="/jsp/caseworkCommon/casefileAuditTabs.jsp" flush="true">
																<tiles:put name="tabid" value="CasefileAuditDetails"/>
																<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
															</tiles:insert>
														</td>
													</tr>
													<tr>
								  						<td bgcolor='#ff6666'>
								  							<img src='../../images/spacer.gif' width="5">
														    		<span style="display:inline;float:right;background-color:#ff6666">
															    		 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PACT_GEN%>'>
															    		  <logic:notEmpty name="juvenileCasefileForm" property="lastPactDate">
																				<span title='Recent PACT Results'><bean:write name="juvenileCasefileForm" property="lastPactDate"/> <bean:write name="juvenileCasefileForm" property="riskLvl"/>/<bean:write name="juvenileCasefileForm" property="needsLvl"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
																		   </logic:notEmpty>
																		  <a href="#" id="pactLink">Noble/PACT</a> &nbsp;
																		</jims2:isAllowed>
																	</span>
															 <%-- user-story #41378 --%>
								  						</td>
								  					</tr>
												</table>
<%-- BEGIN RED TABS BORDER TABLE --%>													
												<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td valign='top' align="center">			
                                                 			<div class=spacer></div>
<%-- BEGIN JPO ASSIGNMENT HISTORY TABLE--%>                                                 			
															<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
									                            <logic:empty name="juvenileCasefileForm" property="jpoAssignmentHistories">
									                                 <tr>
									                                   <td width="1%" class="detailHead"></td> 
									                                   <td class="detailHead" nowrap="nowrap" align="left" valign="top">JPO Assignment History				                                   
							          								        (No JPO Assignment History)		          								    
							          								   </td>
							          								</tr>
							          							</logic:empty>
				                              
																<logic:notEmpty name="juvenileCasefileForm" property="jpoAssignmentHistories">
																	<tr> 
																		<td width="1%" class="detailHead"><a href="javascript:showHideMulti('history', 'pchar', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="history"></a></td> 
																		<td class="detailHead" nowrap='nowrap' align='left' valign='top'>JPO Assignment History</td>
																	</tr> 
																	<tr id="pchar0" class='hidden'> 
																		<td valign="top" align="center" colspan="2"> 
																			<table cellpadding="4" cellspacing="1" width="100%"> 
																				<tr> 
																					<td valign='middle' class="formDeLabel">JPO Name
																						<jims2:sortResults beanName="juvenileCasefileForm" results="jpoAssignmentHistories" primaryPropSort="jpoName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" />
																					</td> 
																					<td valign='middle' class='formDeLabel' width="12%"><bean:message key="prompt.referralNumber"/></td>
																					<td valign='middle' class='formDeLabel' width="8%">UV Code</td>
																					<td valign='top' class="formDeLabel" width="21%">Referral Assignment Date
																						<jims2:sortResults beanName="juvenileCasefileForm" results="jpoAssignmentHistories" primaryPropSort="assignmentAddDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />
																					</td> 
																					<td valign='middle' class='formDeLabel' width="20%">JPO Assignment Date 
																						<jims2:sortResults beanName="juvenileCasefileForm" results="jpoAssignmentHistories" primaryPropSort="jpoAssignmentDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />
																					</td>
																					<td valign='middle' class='formDeLabel' width="8%">Assignment Type</td>
																				</tr>				                                    
																				<logic:iterate id="prevAssign" name="juvenileCasefileForm" property="jpoAssignmentHistories" indexId="index">
																					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">	                  					
																						<td><bean:write name="prevAssign" property="jpoName"/></td>
																						<logic:equal name="juvenileCasefileForm" property="isNewMAYSINeeded" value="true">
																						<td>																						
																							<logic:equal name="prevAssign" property="isMAYSINeededforReferrral" value="1"><bean:write name="prevAssign" property="referralNumber"/></logic:equal>
																							<logic:equal name="prevAssign" property="isMAYSINeededforReferrral" value="0"><font color="blue"><b><bean:write name="prevAssign" property="referralNumber"/></b></font></logic:equal><!-- style="border: 0;background-color: yellow"  -->
																						</td>
																						</logic:equal>
																						<logic:equal name="juvenileCasefileForm" property="isNewMAYSINeeded" value="false">
																						<td><bean:write name="prevAssign" property="referralNumber"/></td>
																						</logic:equal>
																						<td><bean:write name="prevAssign" property="jpoLogonId"/></td>
																						<td><bean:write name="prevAssign" property="assignmentAddDate" formatKey="date.format.mmddyyyy"/></td>
																						<td><bean:write name="prevAssign" property="jpoAssignmentDate" formatKey="date.format.mmddyyyy"/></td>
																						<td>
																						<logic:notEmpty name="prevAssign" property="assignmentType"><span title='<bean:write name="prevAssign" property="assignmentTypeDescr"/>'><bean:write name="prevAssign" property="assignmentType"/></span></logic:notEmpty>
																						<logic:empty name="prevAssign" property="assignmentType"><bean:write name="prevAssign" property="assignmentLevelId"/><logic:notEmpty name="prevAssign" property="serviceUnitId">/</logic:notEmpty><bean:write name="prevAssign" property="serviceUnitId"/></logic:empty></td>
																					</tr>
																				</logic:iterate>		                                      
																			</table>
																		</td> 
																	</tr>
																</logic:notEmpty> 
															</table>	
<%-- END JPO ASSIGNMENT HISTORY TABLE--%>  															
															<div class='spacer'></div>		            			  
<%-- BEGIN CASEFILE TABLE --%>
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td valign='top' class="detailHead">Casefile</td>
																</tr>
																<tr>
																	<td>
																		<table width='100%' cellpadding="4" cellspacing="1">
																			<tr>
																				<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.activationDate"/>/<bean:message key="prompt.time"/></td>
																				<td valign='top' class='formDe' colspan="3"><bean:write name="juvenileCasefileForm" property="activationDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
																	<%-- 			<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
																				<td valign='top' class='formDe'><bean:write name="juvenileCasefileForm" property="courtOrderedProbationStartDateStr" formatKey="datetime.format.mmddyyyyHHmm"/></td> --%>
																			</tr>
																			<tr>									
																				<td valign='top' class='formDeLabel' nowrap='nowrap' width="1%">Expected End Date</td>
																				<td valign='top' class="formDe" colspan="3" >									
																					<bean:write name="juvenileCasefileForm" property="supervisionEndDateStr"/>
																					<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																						<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CASEFILE_U%>'>
																							<a class='editLink' href="/<msp:webapp/>displaySupervisionEndDateUpdate.do?submitAction=<bean:message key='button.link'/>">&nbsp;Edit &gt;&gt;</a>
																						</jims2:isAllowed>
																					</logic:equal>	
																				</td>
																			</tr>
																			<!-- taken out for US 14459 
																			<tr>
																				<td class='formDeLabel' valign='top' nowrap='nowrap'><bean:message key="prompt.titleIVE"/>
																					<bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																				</td>
																				<td class='formDe' colspan="3"><jims2:displayBoolean name="juvenileCasefileForm"property="isBenefitsAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
																			</tr>
																			-->
																			<tr>
																				<td class='formDeLabel' valign='top' nowrap='nowrap'>MAYSI Needed?</td>
																				<logic:equal name="juvenileCasefileForm" property="isNewMAYSINeeded" value="true">
																				<td class='formDe'>
																					<a class='editLink' onclick="spinner()" href="/<msp:webapp/>displayMAYSIResults.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">
																						<font color="red"><jims2:displayBoolean name="juvenileCasefileForm" property="isNewMAYSINeeded" trueValue="Yes" falseValue="No"/></font>
																					</a>
																				</td>
																				</logic:equal>
																				<logic:equal name="juvenileCasefileForm" property="isNewMAYSINeeded" value="false">
																				<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isNewMAYSINeeded" trueValue="Yes" falseValue="No"/></td>
																				</logic:equal>
																				<td class='formDeLabel' valign='top' nowrap='nowrap'>Number of Active/Open Program Referrals:</td>
																				<logic:equal name="juvenileCasefileForm" property="activeProgramReferralsCnt" value="0">
																					<td class='formDe'><bean:write name="juvenileCasefileForm" property="activeProgramReferralsCnt"/></td>
																				</logic:equal>
																				<logic:notEqual name="juvenileCasefileForm" property="activeProgramReferralsCnt" value="0">
																					<td class='formDe'>
																						<a class='editLink' onclick="spinner()" href="/JuvenileCasework/displayProgramReferralList.do?submitAction=Link">
																							<bean:write name="juvenileCasefileForm" property="activeProgramReferralsCnt"/>
																						</a>
																					</td>
																				</logic:notEqual>
																			</tr>
								            								<tr>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>PACT Risk/Needs Entry Needed?</td>
								            									
								            									<logic:equal name="juvenileCasefileForm" property="isNoblePactNeeded" value="Yes">
								            										<td class='formDe'>
								            											<font color="red">
								            												<a class='editLink' onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals">
								            													<bean:write name="juvenileCasefileForm" property="isNoblePactNeeded"/>
								            												</a>
								            											</font>
								            										</td>
								            									</logic:equal>
								            									<logic:equal name="juvenileCasefileForm" property="isNoblePactNeeded" value="No">
								            										<td class='formDe'><bean:write name="juvenileCasefileForm" property="isNoblePactNeeded"/></td>
								            									</logic:equal>
								            									
								            									<td class='formDeLabel' valign='top'>VOP Entry Needed?</td>
								            									
								            									<logic:equal name="juvenileCasefileForm" property="isVOPEntryNeeded" value="Yes">
								            									 <td class='formDe'>
								            									 	<a class='editLink' onclick="spinner()" href="/<msp:webapp/>handleJuvenileProfileVOPsAction.do?submitAction=VOP&juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">
								            											<font color="red"><bean:write name="juvenileCasefileForm" property="isVOPEntryNeeded"/></font>
								            										</a>
								            									 </td>
								            									</logic:equal>
								            									<logic:equal name="juvenileCasefileForm" property="isVOPEntryNeeded" value="No">
								            									 <td class='formDe'><bean:write name="juvenileCasefileForm" property="isVOPEntryNeeded"/></td>
								            									</logic:equal>
								            								
								            									<!-- taken out for US 14459 
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Testing Assessment Needed?</td>
								            									<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isTestingAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
								            									-->
								            								</tr>
								            								<tr>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Hispanic Indicator Needs Update ?</td>
								            									<logic:equal name="juvenileCasefileForm" property="hispanicIndicatorNeedsUpdate" value="true">
								            											<td class='formDe'><font color="red">
								            												<a class='editLink' onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">
								            													Yes
								            												</a>
								            											</font></td>
								            									</logic:equal>
								            									<logic:equal name="juvenileCasefileForm" property="hispanicIndicatorNeedsUpdate" value="false">
								            										<td class='formDe'>No</td>
								            									</logic:equal>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Substance Abuse TJJD Required ?</td>
								            									<logic:equal name="juvenileCasefileForm" property="isSubstanceAbuseTjjdRequired" value="true">
								            										<td class='formDe'>
								            											<font color="red">
								            											<a class='editLink' onclick="spinner()" href="/<msp:webapp/>displayJuvenileDrugsList.do?submitAction=Tab&juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casenum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">
								            												Yes
								            											</a>
								            											</font>
								            										</td>
								            									</logic:equal>
								            									<logic:equal name="juvenileCasefileForm" property="isSubstanceAbuseTjjdRequired" value="false">
								            										<td class='formDe'>No</td>
								            									</logic:equal>
								            								</tr>
								            								<tr>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>School History Needed ?</td>
								            									<logic:equal name="juvenileCasefileForm" property="schoolHistoryNeedsUpdate" value="true">
								            										<td class='formDe' colspan="6">
								            											<font color="red">
								            												<a class='editLink' onclick="spinner()" href="/<msp:webapp/>displayJuvenileSchool.do?submitAction=Tab&action=View&juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">
								            													Yes
								            												</a>
								            											</font>
								            										</td>
								            									</logic:equal>
								            									<logic:equal name="juvenileCasefileForm" property="schoolHistoryNeedsUpdate" value="false">
								            										<td class='formDe' colspan="6">No</td>
								            									</logic:equal>
								            								</tr>
								            								<%-- <tr>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Residential Assessment Needed?</td>
								            									<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isResidentialAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Progress Assessment Needed?</td>
								            									<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isProgressAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
								            								</tr>
								            								<tr>								            									
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Residential Progress Assessment Needed?</td>
								            									<td class='formDe' colspan="3"><jims2:displayBoolean name="juvenileCasefileForm" property="isResidentialProgressAssessNeeded" trueValue="Yes" falseValue="No"/></td>
								            								</tr> --%>
								            								<tr>
								            									<td valign='top' class='formDeLabel' nowrap='nowrap'>Supervision End Date</td>
								            									<td valign='top' class='formDe'width="1%" nowrap='nowrap'><bean:write name="juvenileCasefileForm" property="closingDateStr"/></td>
								            									<td valign='top' class='formDeLabel'width="1%" nowrap='nowrap'>Controlling Referral</td>
								            									<td valign='top' class='formDe'width="1%" nowrap='nowrap'><bean:write name="juvenileCasefileForm" property="controllingReferralNumber"/></td>
								            								</tr>	
								            								<tr>
								            									<td valign='top' class='formDeLabel' nowrap='nowrap'>Supervision Outcome</td>
								            									<td valign='top' class='formDe' width="2%"><bean:write name="juvenileCasefileForm" property="supervisionOutcome"/></td>
								            									<td valign='top' class='formDeLabel' nowrap='nowrap'>Supervision Outcome Description</td>
								            									<td valign='top' class='formDe' width="2%"><bean:write name="juvenileCasefileForm" property="supervisionOutcomeDesc"/></td>
            																</tr>
<!-- begin override exceptions table/comments section -->
																			<logic:present name="exceptionOverrideComments">
																				<tr>
                											             			<td colspan='6'>
												                           				<table align='center' width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
											                             					<tr>
													                                   			<td class="detailHead">Exceptions Overridden</td>
												   				                            </tr>
											                     					        <tr>
														                                        <td>
           																		                    <table width='100%' cellpadding="2" cellspacing="1">
												                                						<tr>
												                                 							<td class='formDeLabel' width='1%'>Reason for overriding exceptions</td>
												                                						</tr>
												                                						<tr>
												                                 							<td class='formDe'><bean:write name="exceptionOverrideComments"/></td>
												                                 						</tr>
												                               						</table>
												                               					</td>
                             																</tr>
                             															</table>
											                           				</td>
											                          			</tr>
										                           			</logic:present>
<!-- end override exceptions table/comments section -->
								            								<tr>
								            									<td class='formDeLabel' valign='top' nowrap='nowrap'>Closing Evaluation</td>
								            									<td class='formDe' colspan="6"><bean:write name="juvenileCasefileForm" property="closingEvaluation"/></td>
								            								</tr>
									                       				</table>
			                      									</td>
			                    								</tr>
			                  								</table>
<%-- END CASEFILE TABLE --%>
			                  								<div class='spacer'></div>          
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:button property="button.back" onclick="goNav('back')"><bean:message key="button.back"></bean:message></html:button>
																		<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileSearch.do')"> 
																	</td>
																</tr>
															</table>
<%-- END BUTTON TABLE --%>
														</td>
													</tr>
												</table>
<%-- END RED TABS BORDER TABLE --%>												
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
<%-- END GREEN TABS BORDER TABLE --%>								
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
<%-- END CASEFILE INFO TABS TABLE --%>						
				    </td>
  				</tr>
			</table>
<%-- END BLUE TABS BORDER TABLE --%>	
	    </td>
	</tr>
</table>
<%-- END CASEFILE TABS TABLE --%>
</html:form>
<!-- Pact Invocation  Task 41028   &&   //# task 44099 . U.S #41378-->
<form id="pactForm" action="https://<bean:write name='juvenileCasefileForm' property='pactServerName'/>/<bean:write name='juvenileCasefileForm' property='pactApplicationName'/>/juvenile/Login/IntegrationLogon" method="POST"  target="newFormWindow">
	<input type="hidden" name="USER_EXTERNAL_ID" value="<bean:write name='juvenileCasefileForm' property='userId'/>">
	<input type="hidden" name="SUBJECT_EXTERNAL_ID" value="<bean:write name='juvenileCasefileForm' property='juvenileNum'/>">
	<input type="hidden" name="AUTH_TOKEN" value="<bean:write name='juvenileCasefileForm' property='pactAuthKey'/>">
</form>

<div class='spacer'></div>
<%-- END FORM --%>
<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
	<tiles:insert page="/jsp/caseworkCommon/casefileOperations.jsp" /> 
</logic:equal>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>