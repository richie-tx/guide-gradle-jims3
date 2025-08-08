<!DOCTYPE HTML>

<%-- Used to display casefile details off Casefile Tab --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>
<%-- 07/27/2007		LDeen	Defect #42646 --%>
<%-- 04/30/2012 	CShimek	#73346 Revise hardcoded TYC prompts to TJJD --%>
<%-- 05/22/2012		DWilliamson ER #68282 Display Formal Referral code --%>
<%-- 03/12/2013		RYoung  75232 changed the action to use the response instead of the entity --%>
<%-- 08/21/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>
<%-- 08/31/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI)  fixed blue border alignment--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>


<%--BEGIN HEADER TAG--%>
<head>

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

<title><bean:message key="title.heading"/> - caseTabAssignedReferrals - referralList.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script>
	$(document).ready(function(){
		
		$("#addRiskNeedLevelsBtn").click(function(){
			sessionStorage.removeItem("selectedReferralNumber");
			sessionStorage.removeItem("offenses");
			sessionStorage.removeItem("lastPactDate");
			sessionStorage.removeItem("pdaReadOnly");
		})
		
	})
</script>

<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/displayJuvenileCasefileAssignedReferralsList"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Casefile Referral History</td> 
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>


<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->


<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on a hyperlinked Referral # to view Referral details.</li> 
			</ul>
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
									<div class="spacer"></div>
<%--BEGIN OF BEHAVIORAL HISTORY TABLE --%> 
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						        		<tr>
						          			<td width='1%' class=detailHead><a href="javascript:showHideMulti('behaviour', 'pchar', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="behaviour"></a></td>
						          			<td class=detailHead nowrap align='left' valign='top'>Behavioral History</td>
						        		</tr>
						        		<tr id="pchar0" class='hidden'>
						          			<td valign="top" align="center" colspan="2">
						    					<table cellpadding="4" cellspacing="1" width='100%'>
							    					<tr>
							    						<td class="formDeLabel" align='right' width='1%' nowrap valign="top">Age First Referred</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="ageFirstReferred"/></td>
							    					</tr>
							    					<tr>
							    						<td class="formDeLabel" align='right' width='1%' nowrap valign="top">Diversion Events</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="diversionEvents"/></td>
							    					</tr>
							    					<tr>
							    						<td class="formDeLabel" align='right'  width='1%' nowrap>Deferred Adjudication Events</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="deferredAdjudicationEvents"/></td>
							    					</tr>
							    					<tr>
							    						<td class="formDeLabel" align='right' width='1%'  nowrap>Adjudication Events</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="adjudicationEvents"/></td>
							    					</tr>
							    					<tr>
							    						<td class="formDeLabel" align='right' width='1%' nowrap>TJJD Commitments</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="TYCCommitments"/></td>
							    					</tr>
							    					<tr>
							    						<td class="formDeLabel" align='right' width='1%' nowrap>Seriousness Index</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="seriousnessIndex"/></td>
							    					</tr>
													<tr>
							    						<td class="formDeLabel" align='right'width='1%'  nowrap>Severity Index</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="severityIndex"/></td>
							    					</tr>
							    					<tr>
							    						<td colspan="2">
							    							<table align='center' width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
						              							<tr>
						                      						<td width='1%' class=detailHead><a href="javascript:showHideMulti('refHist', 'refHist', 1, '/<msp:webapp/>');" border="0"><img border=0 src="/<msp:webapp/>images/expand.gif" name="refHist"></a></td>
						                      						<td class=detailHead nowrap align='left' valign='top' >Referral History</td>
						                    					</tr>
						                    					<tr id="refHist0" class='hidden'>
						                      						<td valign="top" align=center colspan=2>
						        										<table width='100%' border="0" cellpadding="4" cellspacing="1" >
						        											<tr>
						        												<td class="formDeLabel" align='right' width='15%'> Referrals Count - Non-Administrative</td>
								          										<td class="formDe" colspan="3">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="totalReferralNonAdmin"/></td>
						        											</tr>
								          									<tr>
								          										<td class="formDeLabel" align='right' width='1%'>Referral Events by Date</td>
								          										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="referralByDateNonAdminEvents"/></td>
								          										<td class="formDeLabel" align='right' width='1%'>Offenses</td>
								          										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="totalOffenses"/></td>
								          									</tr>
								        									<tr>
								        										<td class="formDeLabel" nowrap align='right'>Felony - Capital</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felonyCapital"/></td>
								        										<td class="formDeLabel" nowrap align='right'>Misdemeanor-A</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="misdemeanorA"/></td>
								        									</tr>
								        									<tr>
								        										<td class="formDeLabel" nowrap align="right">Felony - 1</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony1"/></td>
								        										<td class="formDeLabel" nowrap align="right">Misdemeanor - B</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="misdemeanorB"/></td>
								        									</tr>
								        									<tr>
								        										<td class="formDeLabel" nowrap align="right">Felony - 2</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony2"/></td>
								        										<td class="formDeLabel" nowrap align="right">Violations of Probation</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="violationsOfProbation"/></td>
								        										
								        									</tr>
								        									<tr>
								        										<td class="formDeLabel" nowrap align="right">Felony - 3</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony3"/></td>
								        										<td class="formDeLabel" nowrap align="right">Runaways</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="runaways"/></td>
								        										
								        									</tr>
								        									<tr>
								        										<td class="formDeLabel" nowrap align="right">Felony - State Jail</td>
								        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felonyStateJail"/></td>
								        										<td class="formDe" colspan=2></td>
								        									</tr>
								        								</table>
								       								</td>
							     								</tr>
															</table>
						    							</td>
						    						</tr>
						    					</table>
						    				</td>
						    			</tr>
						    		</table>
<%--END OF BEHAVIORAL HISTORY TABLE--%> 
									<div class='spacer'></div>
<%-- BEGIN OF REFERRALS TABLE--%> 
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
										<tr> 
											<td class="detailHead"><bean:message key="prompt.referrals" /></td> 
										</tr> 
								        <tr>
								        	<td> 
								        		<table cellpadding="2" cellspacing="1" width='100%'> 
									                <tr bgcolor='#cccccc'> 
											              <td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.referral" /> #
												              <jims:sortResults beanName="juvenileCasefileForm" results="juvenileCasefileReferralsList" primaryPropSort="referralNumber" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC" sortId="1" />
											              </td> 
										                	<td><bean:message key="prompt.referralDt"/>	</td>
															<td><bean:message key="prompt.petitionAllegation"/></td>
															<td><bean:message key="prompt.levelUnit"/></td>
															<td><bean:message key="prompt.JPO"/></td>
															<td><bean:message key="prompt.intake"/></td>														
															<td><bean:message key="prompt.courtId"/></td>														
															<td><bean:message key="prompt.courtDate"/></td>															
															<td><bean:message key="prompt.adjDSN"/></td>															
															<td><bean:message key="prompt.finalDisposition"/></td>														
															<td><bean:message key="prompt.petition"/></td>														
															<td><bean:message key="prompt.petition"/> <bean:message key="prompt.status"/></td>															
															<td><bean:message key="prompt.dateClosed"/></td>
															<td align='left' class="subHead"><bean:message key="prompt.riskNeedLevel" /></td>										                 
										                   <td align='left' class="subHead"><bean:message key="prompt.pactDate" /></td>
										                   <td align='left' class="subHead"><bean:message key="prompt.pactStatus" /></td>			
										            </tr> 
										            <logic:notEmpty name="juvenileCasefileForm" property="juvenileCasefileReferralsList">
									              		<% String rowClass = "alternateRow" ;
									              			 String casefileUrl = naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM);
										              		%> 
						        						<logic:iterate id="assignedReferrals" name="juvenileCasefileForm" property="juvenileCasefileReferralsList" indexId="index"> 
						        						<%-- Begin Pagination item wrap --%>
						              						<pg:item>
						          								<bean:define id="juvenileNum" name="juvenileCasefileForm" property="juvenileNum" type="java.lang.String"/>
						          								<bean:define id="referralNum" name="assignedReferrals" property="referralNumber" type="java.lang.String"/>
						          								<logic:equal name="assignedReferrals" property="referralFound" value="true">
																	<% rowClass = ((index.intValue()) % 2 == 1) ? "normalRow" : "alternateRow"; %>
																	<logic:notEqual name="assignedReferrals" property="recType" value="REFERRAL">
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>'>
									            							<tr class=<%=rowClass%> height="100%">
																				<%StringBuffer url = new StringBuffer();
										            								url.append(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=");
										            								url.append(request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM));
										            								url.append("&" + naming.PDJuvenileCaseConstants.JUVENILENUM_PARAM + "=" + juvenileNum);
										            								url.append("&" + naming.PDJuvenileCaseConstants.REFERRALNUM_PARAM + "=" + referralNum);
										            								String queryUrl = url.toString();							
									             								%>
											             						<td><a href="/<msp:webapp/>displayJuvenileCasefileReferralDetails.do?<%out.print(queryUrl);%>">
											             								<bean:write name="assignedReferrals" property="referralNumber"/>
											             							</a>
											             							<logic:equal  name="assignedReferrals" property="recType"  value="S.REFERRAL">
											             								<span style="color:blue; font-weight: bold;" title="Sealed Referral">S</span>
											             							</logic:equal>
											             						</td>
									      										<td ><bean:write name="assignedReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																				<td>
																						<logic:equal name="assignedReferrals" property="petitionsAvailable" value="true">
									      												<span title="<bean:write name="assignedReferrals" property="petitionAllegDscr"/>"><bean:write name="assignedReferrals" property="petitionAllegation"/></span>
									      											</logic:equal>
																			</td>
																				<td>
																					<logic:notEmpty  name="assignedReferrals" property="supervisionCategory">
																						<span title="<bean:write name="assignedReferrals" property="supervisionCategory"/>"><bean:write name="assignedReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="assignedReferrals" property="supervisionType"/>"><bean:write name="assignedReferrals" property="supervisionTypeId"/></span>
																					</logic:notEmpty>
																				</td>
																				<td><span title="<bean:write name="assignedReferrals" property="jpo"/>"><bean:write name="assignedReferrals" property="jpoId"/></span></td>
																				<td><span title="<bean:write name="assignedReferrals" property="intakeDecision"/>"><bean:write name="assignedReferrals" property="intakeDecisionCode"/></span></td>
																				<td><bean:write name="assignedReferrals" property="courtId"/></td>
																				<td><bean:write name="assignedReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																				<td><span title="<bean:write name="assignedReferrals" property="courtResultDesc"/>"><bean:write name="assignedReferrals" property="courtResult"/></span></td>
																				<td><span title="<bean:write name="assignedReferrals" property="finalDispositionDescription"/>"><bean:write name="assignedReferrals" property="finalDisposition"/></span></td>
																				<td><bean:write name="assignedReferrals" property="petitionNumber"/></td>
																				<td><span title="<bean:write name="assignedReferrals" property="petitionStatusDescr"/>"><bean:write name="assignedReferrals" property="petitionStatus"/></span></td>
																				<td><bean:write name="assignedReferrals" property="refCloseDate" formatKey="date.format.mmddyyyy"/></td>
																				<td>
										      									<logic:notEmpty name="assignedReferrals" property="riskNeedLvl">
										      										 <span title='<bean:write name="assignedReferrals" property="riskNeedLvlDesc"/>'><bean:write name="assignedReferrals" property="riskNeedLvl"/></span>
										      									</logic:notEmpty></td>								      									
										      									<td><bean:write name="assignedReferrals" property="lastPactDate"/></td>
										      									<logic:notEqual name="assignedReferrals" property="pactStatus" value="SYS GENERATE">
										      										<td><bean:write name="assignedReferrals" property="pactStatus"/></td>
										      									</logic:notEqual>
																		  	</tr>
																		  	<tr class=<%=rowClass%> >
								      											<td></td> 
								      											<td colspan='16'><b><bean:message key="prompt.offenses"/></b>: 
											      									<logic:equal name="assignedReferrals" property="offensesAvailable" value="true">
										      											<logic:iterate id="offenseCode" name="assignedReferrals" property="offenseCodes" indexId="index"> 
										      												<span title='<bean:write name="offenseCode" property="longDescription"/>'>
										      													<bean:write name="offenseCode" property="offenseCode"/>
										      													<logic:notEqual name="assignedReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
										      														,
									    	  													</logic:notEqual>
									      													</span>
									      												</logic:iterate>
									      											</logic:equal>
								     											</td>
								     										</tr>
							     										</jims2:isAllowed>
						     										</logic:notEqual>
						     										<logic:equal name="assignedReferrals" property="recType" value="REFERRAL">
								            							<tr class=<%=rowClass%> height="100%">
																			<%StringBuffer url = new StringBuffer();
									            								url.append(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=");
									            								url.append(request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM));
									            								url.append("&" + naming.PDJuvenileCaseConstants.JUVENILENUM_PARAM + "=" + juvenileNum);
									            								url.append("&" + naming.PDJuvenileCaseConstants.REFERRALNUM_PARAM + "=" + referralNum);
									            								String queryUrl = url.toString();							
								             								%>
										             						<td><a href="/<msp:webapp/>displayJuvenileCasefileReferralDetails.do?<%out.print(queryUrl);%>">
										             								<bean:write name="assignedReferrals" property="referralNumber"/>
										             							</a>
										             						</td>
								      										<td ><bean:write name="assignedReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																			<td>
																					<logic:equal name="assignedReferrals" property="petitionsAvailable" value="true">
								      												<span title="<bean:write name="assignedReferrals" property="petitionAllegDscr"/>"><bean:write name="assignedReferrals" property="petitionAllegation"/></span>
								      											</logic:equal>
																		</td>
																			<td>
																				<logic:notEmpty  name="assignedReferrals" property="supervisionCategory">
																					<span title="<bean:write name="assignedReferrals" property="supervisionCategory"/>"><bean:write name="assignedReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="assignedReferrals" property="supervisionType"/>"><bean:write name="assignedReferrals" property="supervisionTypeId"/></span>
																				</logic:notEmpty>
																			</td>
																			<td><span title="<bean:write name="assignedReferrals" property="jpo"/>"><bean:write name="assignedReferrals" property="jpoId"/></span></td>
																			<td><span title="<bean:write name="assignedReferrals" property="intakeDecision"/>"><bean:write name="assignedReferrals" property="intakeDecisionCode"/></span></td>
																			<td><bean:write name="assignedReferrals" property="courtId"/></td>
																			<td><bean:write name="assignedReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																			<td><span title="<bean:write name="assignedReferrals" property="courtResultDesc"/>"><bean:write name="assignedReferrals" property="courtResult"/></span></td>
																			<td><span title="<bean:write name="assignedReferrals" property="finalDispositionDescription"/>"><bean:write name="assignedReferrals" property="finalDisposition"/></span></td>
																			<td><bean:write name="assignedReferrals" property="petitionNumber"/></td>
																			<td><span title="<bean:write name="assignedReferrals" property="petitionStatusDescr"/>"><bean:write name="assignedReferrals" property="petitionStatus"/></span></td>
																			<td><bean:write name="assignedReferrals" property="refCloseDate" formatKey="date.format.mmddyyyy"/></td>
																			<td>
									      									<logic:notEmpty name="assignedReferrals" property="riskNeedLvl">
									      										 <span title='<bean:write name="assignedReferrals" property="riskNeedLvlDesc"/>'><bean:write name="assignedReferrals" property="riskNeedLvl"/></span>
									      									</logic:notEmpty></td>								      									
									      									<td><bean:write name="assignedReferrals" property="lastPactDate"/></td>
									      									<logic:notEqual name="assignedReferrals" property="pactStatus" value="SYS GENERATE">
									      										<td><bean:write name="assignedReferrals" property="pactStatus"/></td>
									      									</logic:notEqual>
																	  	</tr>
																	  	<tr class=<%=rowClass%> >
							      											<td></td> 
							      											<td colspan='16'><b><bean:message key="prompt.offenses"/></b>: 
										      									<logic:equal name="assignedReferrals" property="offensesAvailable" value="true">
									      											<logic:iterate id="offenseCode" name="assignedReferrals" property="offenseCodes" indexId="index"> 
									      												<span title='<bean:write name="offenseCode" property="longDescription"/>'>
									      													<bean:write name="offenseCode" property="offenseCode"/>
									      													<logic:notEqual name="assignedReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
									      														,
								    	  													</logic:notEqual>
								      													</span>
								      												</logic:iterate>
								      											</logic:equal>
							     											</td>
							     										</tr>
						     										</logic:equal>	
							        							</logic:equal>
							      							</pg:item>
<%-- End Pagination item wrap --%>
														</logic:iterate> 
													</logic:notEmpty>
							    					<logic:empty name="juvenileCasefileForm" property="juvenileCasefileReferralsList">
							    						<tr><td colspan=5 align="left">No referrals exist for this casefile.</td></tr>
							    					</logic:empty>
								         		</table> 
<%-- Begin Pagination navigation Row--%>
												<table align="center">
													<tr>
														<td>
															<pg:index>
																<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																	<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																	<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
																</tiles:insert>
															</pg:index>
														</td>
													</tr>
												</table>
<%-- End Pagination navigation Row--%>
								        	</td>
								    	</tr> 
									</table> 
<%-- END OF REFERRALS TABLE--%> 
									<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" cellpadding=1 cellspacing=1 align=center>
										<tr>
											<td align="center"><input type=button value=Back onclick="goNav('back')">
												<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
												 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PACT_RNLEVELS%>'>
													<logic:notEmpty name="juvenileCasefileForm" property="juvenileCasefileReferralsList">
														<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="A">
															<html:submit onclick="spinner();" styleId="addRiskNeedLevelsBtn" property="submitAction"><bean:message key="button.addRiskNeedLevels" /></html:submit>
														</logic:equal>
													</logic:notEmpty>
												</jims2:isAllowed>
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