<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 04/30/2012	C Shimek	#73346 Revise hardcoded TYC prompts to TJJD --%>
<%-- 08/20/2015 R Capestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Referrals) --%>
<%-- 09/17/2015 R Capestani #30218 MJCW: Adapt to IE9 & IE11 - Remove the "F" in the Header of Juv Profile > Referral Page --%>

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/> - referralList.jsp</title>

<script type="text/javascript">
$(function() { 
	$("#referralLink").click(function(){		
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="juvenileProfileHeader" property="juvenileNum"/>';
		 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Link&juvnum=" +juvNum;
		 window.myVariable=juvNum;
		 var referralWindow = window.open(url, "referralWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 referralWindow.focus();	
		localStorage.setItem("referralWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
		});
 });

 </script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileProfileReferralList" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|217">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
  		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Referral List</td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
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
<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN PROFILE TABS TABLE --%>		
	    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
	  			<tr>
	  				<td valign="top">
	    				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	         			<tiles:put name="tabid" value="referralstab"/>
	         			<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
	         		</tiles:insert>	
	  				</td>
	  			</tr>
	  			<tr>  			
				  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END PROFILE TABS TABLE --%>
<%-- BEGIN GREEN BORDER TABLE --%>				
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<%-- BEGIN REFERRAL TABS TABLE --%>							
	  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  						<tr>
	  							<td valign="top">
								  	<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">    								
	   								</tiles:insert>
								</td>
	  						</tr>
	  						<tr>
	  							<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="10"> <span style='display:inline;float:right;background-color:#6699FF'><a href="#" id="referralLink"><bean:message key="prompt.referrals" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></td>
	  						</tr>
				  		</table>
<%-- END REFERRAL TABS TABLE --%>
<%-- BEGIN BLUE BORDER TABLE --%>						  		
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				  			<tr>
				  				<td valign="top" align="center">
				  					<div class="spacer"></div> 
<%-- BEGIN BEHAVIORAL HISTORY TABLE --%>			  					
						            <table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
				                			<td width='1%' class="detailHead"><a href="javascript:showHideMulti('behaviour', 'pchar', 1, '/<msp:webapp/>');" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="behaviour"></a></td>
				                			<td class="detailHead" nowrap="nowrap" align='left' valign='top'>Behavioral History</td>
				              			</tr>
				              			<tr id="pchar0" class='hidden'>
				                			<td valign="top" align="center" colspan="2">
<%-- BEGIN BEHAIORAL HISTORY DETAILS TABLE --%>				                			
				  								<table cellpadding="4" cellspacing="1" width='100%'>
				  									<tr>
				  										<td class="formDeLabel" align='right' width='50%' nowrap="nowrap" valign="top">Age First Referred</td>
				  										<td class="formDe"><bean:write name="juvenileBehaviorHistoryForm" property="ageFirstReferred"/></td>
				  									</tr>
				  									<tr>
							    						<td class="formDeLabel" align='right' width='1%' nowrap valign="top">Diversion Events</td>
							    						<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="diversionEvents"/></td>
							    					</tr>
				  									<tr>
				  										<td class="formDeLabel" align='right' nowrap="nowrap">Deferred Adjudication Events</td>
				  										<td class="formDe"><bean:write name="juvenileBehaviorHistoryForm" property="deferredAdjudicationEvents"/></td>
				  									</tr>
				  									<tr>
				  										<td class="formDeLabel" align='right' nowrap="nowrap">Adjudication Events</td>
				  										<td class="formDe"><bean:write name="juvenileBehaviorHistoryForm" property="adjudicationEvents"/></td>
				  									</tr>
				  									<tr>
				  										<td class="formDeLabel" align='right' nowrap="nowrap">TJJD Commitments</td>
				  										<td class="formDe"><bean:write name="juvenileBehaviorHistoryForm" property="TYCCommitments"/></td>
				  									</tr>
				  									<tr>
				  										<td class="formDeLabel" align='right' nowrap="nowrap">Seriousness Index</td>
				  										<td class="formDe"><bean:write name="juvenileBehaviorHistoryForm" property="seriousnessIndex"/></td>
				  									</tr>
													<tr>
							    						<td class=formDeLabel align='right' nowrap>Severity Index</td>
							    						<td class=formDe><bean:write name="juvenileBehaviorHistoryForm" property="severityIndex"/></td>
							    					</tr>
													<tr>
														<td colspan="2">
<%-- BEGIN REFERRAL HISTORY TABLE --%>														
															<table align='center' width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td width='1%' class="detailHead"><a href="javascript:showHideMulti('refHist', 'refHist', 1, '/<msp:webapp/>');" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="refHist"></a></td>
																	<td class="detailHead" nowrap="nowrap" align='left' valign='top'>Referral History</td>
																</tr>
																<tr id="refHist0" class='hidden'>
																	<td valign="top" align="center" colspan="2">
<%-- BEGIN REFERRAL HISTORY DETAILS TABLE --%>								          							
							          									<table width='100%' border="0" cellpadding="4" cellspacing="1" >
							          										<tr>
						        												<td class="formDeLabel" align='right' width='15%'> Referrals Count - Non-Administrative</td>
								          										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="totalReferralNonAdmin"/></td>
						        												<td class="formDe" colspan="2"></td>
						        											</tr>
							            									<tr>
							            										<td class="formDeLabel" align='right'>Referral Events by Date</td>
							            										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="referralByDateNonAdminEvents"/></td>
							            										<td class="formDeLabel" align='right'>Offenses</td>
							            										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="totalOffenses"/></td>
							            									</tr>
							  	        									<tr>
							  	        										<td class="formDeLabel" nowrap="nowrap" align='right' width='30%'>Felony - Capital</td>
							  	        										<td class="formDe" width="20%">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felonyCapital"/></td>
							  	        										<td class="formDeLabel" nowrap="nowrap" align='right' width='30%'>Misdemeanor-A</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="misdemeanorA"/></td>
							  	        									</tr>
							  	        									<tr>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Felony - 1</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony1"/></td>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Misdemeanor - B</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="misdemeanorB"/></td>
							  	        									</tr>
							  	        									<tr>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Felony - 2</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony2"/></td>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Violations of Probation</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="violationsOfProbation"/></td>
							  	        									</tr>
							  	        									<tr>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Felony - 3</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felony3"/></td>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Runaways</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="runaways"/></td>
							  	        										
							  	        									</tr>
							  	        									<tr>
							  	        										<td class="formDeLabel" nowrap="nowrap" align="right">Felony - State Jail</td>
							  	        										<td class="formDe">&nbsp;<bean:write name="juvenileBehaviorHistoryForm" property="felonyStateJail"/></td>
							  	        										<td class="formDe" colspan="2"></td>
							  	        									</tr>
							  	        								</table>
<%-- END REFERRAL HISTORY DETAILS TABLE --%>							  	        								
					  												</td>
					  											</tr>
						      								</table>
<%-- END REFERRAL HISTORY TABLE --%>						      								
						      							</td>
						      						</tr>
						      					</table>
<%-- END BEHAIORAL HISTORY DETAILS TABLE --%>							      					
											</td>
										</tr>
									</table>
<%-- END BEHAVIORAL HISTORY TABLE--%> 
	  								<div class="spacer"></div>
<%-- BEGIN REFERRALS BORDER TABLE--%>
					  				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
					  					<tr>
					  						<td class="detailHead">Referrals</td>
					  					</tr>
					  					<tr>
					    					<td>
<%-- BEGIN REFERRALS DETAILS TABLE--%>					    					
					    						<table cellpadding="2" cellspacing="1" border="0" width='100%'>
					    							<logic:empty name="assignedReferralsForm" property="referralList">
					  							  		<tr bgcolor='#cccccc'> 
					    									<td colspan="5" class="subHead">No Referrals Available</td> 
					  							  		</tr> 
					  								</logic:empty>
													<logic:notEmpty name="assignedReferralsForm" property="referralList">
														<tr bgcolor='#cccccc'> <%-- column titles --%>
															
															<td><bean:message key="prompt.refNo"/>
															<jims:sortResults sortId="1" beanName="assignedReferralsForm" results="referralList" primaryPropSort="referralNumber" primarySortType="String" defaultSort="true" defaultSortOrder="DESC"/>
															</td>
															<td><bean:message key="prompt.referralDt"/>	
															<jims:sortResults sortId="2" beanName="assignedReferralsForm" results="referralList" primaryPropSort="referralDate" primarySortType="Date"/>
															</td>
															<td><bean:message key="prompt.petitionAllegation"/>
															<jims:sortResults sortId="3" beanName="assignedReferralsForm" results="referralList" primaryPropSort="petitionAllegationDescr" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.levelUnit"/>
															<jims:sortResults sortId="4" beanName="assignedReferralsForm" results="referralList" primaryPropSort="supervisionCategoryId" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.JPO"/>
															<jims:sortResults sortId="5" beanName="assignedReferralsForm" results="referralList" primaryPropSort="jpoId" primarySortType="String"/>
															</td>
															<td><bean:message key="prompt.intake"/>
															<jims:sortResults sortId="6" beanName="assignedReferralsForm" results="referralList" primaryPropSort="intakeDecisionId" primarySortType="String" />
															</td>														
															<td><bean:message key="prompt.courtId"/>
															<jims:sortResults sortId="7" beanName="assignedReferralsForm" results="referralList" primaryPropSort="courtId" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.courtDate"/>
															<jims:sortResults sortId="8" beanName="assignedReferralsForm" results="referralList" primaryPropSort="courtDate" primarySortType="Date"/>
															</td>															
															<td><bean:message key="prompt.adjDSN"/>
															<jims:sortResults sortId="9" beanName="assignedReferralsForm" results="referralList" primaryPropSort="courtResult" primarySortType="String"/>
															</td>															
															<td><bean:message key="prompt.finalDisposition"/>
															<jims:sortResults sortId="10" beanName="assignedReferralsForm" results="referralList" primaryPropSort="finalDisposition" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.petition"/>
															<jims:sortResults sortId="11" beanName="assignedReferralsForm" results="referralList" primaryPropSort="petitionNumber" primarySortType="String"/>
															</td>														
															<td><bean:message key="prompt.petition"/> <bean:message key="prompt.status"/>
															<jims:sortResults sortId="12" beanName="assignedReferralsForm" results="referralList" primaryPropSort="petitionStatus" primarySortType="String"/>
															</td>															
															<td><bean:message key="prompt.probationStartDate"/>
															<jims:sortResults sortId="13" beanName="assignedReferralsForm" results="referralList" primaryPropSort="probationStartDate" primarySortType="Date"/>
															</td>	
															<td><bean:message key="prompt.probationEndDate"/>
															<jims:sortResults sortId="14" beanName="assignedReferralsForm" results="referralList" primaryPropSort="probationEndDate" primarySortType="Date"/>
															</td>
															<td><bean:message key="prompt.dateClosed"/>
															<jims:sortResults sortId="15" beanName="assignedReferralsForm" results="referralList" primaryPropSort="closeDate" primarySortType="Date"/>
															</td>																															
														</tr>
						  								<%String rowClass = "";%>  
														<logic:iterate indexId="index" id="myReferrals" name="assignedReferralsForm" property="referralList">
														    <% rowClass = ((index.intValue()) % 2 == 1) ? "normalRow" : "alternateRow"; %>
														     <logic:notEqual name="myReferrals" property="recType" value="REFERRAL">
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>'>
																    <tr class=<%=rowClass%> >
							  											<td >
							  												<a onclick="spinner()" 
							  													href="/<msp:webapp/>displayJuvenileProfileReferralDetails.do?juvnum=<bean:write name="assignedReferralsForm" property="juvenileNum"/>&refnum=<bean:write name="myReferrals" property="referralNumber"/>"><bean:write name="myReferrals" property="referralNumber"/>
							  												</a>
							  												<logic:equal  name="myReferrals" property="recType"  value="S.REFERRAL">
							  													<span style="color:blue; font-weight: bold;" title=" Sealed Referral"/>S</span>
							  												</logic:equal>
							  				
							  											</td>
																		<td ><bean:write name="myReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																		<td >
																				<logic:equal name="myReferrals" property="petitionsAvailable" value="true">
																					<span title="<bean:write name="myReferrals" property="petitionAllegationDescrExtended"/>"><bean:write name="myReferrals" property="petitionAllegation"/></span>
																			</logic:equal>
																		
																	</td>
																		<td>
																			<logic:notEmpty  name="myReferrals" property="supervisionCategory">
																				<span title="<bean:write name="myReferrals" property="supervisionCategory"/>"><bean:write name="myReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="myReferrals" property="supervisionType"/>"><bean:write name="myReferrals" property="supervisionTypeId"/></span>
																			</logic:notEmpty>
																		</td>
																		<td><span title="<bean:write name="myReferrals" property="jpo"/>"><bean:write name="myReferrals" property="jpoId"/></span></td>
																		<td><span title="<bean:write name="myReferrals" property="intakeDecision"/>"><bean:write name="myReferrals" property="intakeDecisionId"/></span></td>
																		<td><bean:write name="myReferrals" property="courtId"/></td>
																		<td><bean:write name="myReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																		<td><span title="<bean:write name="myReferrals" property="courtResultDesc"/>"><bean:write name="myReferrals" property="courtResult"/></span></td>
																		<td><span title="<bean:write name="myReferrals" property="finalDispositionDescription"/>"><bean:write name="myReferrals" property="finalDisposition"/></span></td>
																		<td><bean:write name="myReferrals" property="petitionNumber"/></td>
																		<td><span title="<bean:write name="myReferrals" property="petitionStatusDescr"/>"><bean:write name="myReferrals" property="petitionStatus"/></span></td>
																		<td><bean:write name="myReferrals" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
																		<td><bean:write name="myReferrals" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td> 
																		<td><bean:write name="myReferrals" property="closeDate" formatKey="date.format.mmddyyyy"/></td>																
																  	</tr>
																  	<tr class=<%=rowClass%> >
				      													<td></td> 
						      											<td colspan='14'><b><bean:message key="prompt.offenses"/></b>: 
									      									<logic:equal name="myReferrals" property="offensesAvailable" value="true">
								     											<logic:iterate id="offenseCode" name="myReferrals" property="offenseCodes" indexId="index"> 
						    		 												<span title='<bean:write name="offenseCode" property="longDescription"/>, <bean:write name="offenseCode" property="category"/>'>
						     															<bean:write name="offenseCode" property="offenseCode"/>
						     															<logic:notEqual name="myReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
						     																,
						     															</logic:notEqual>
						     														</span>				     														
						     													</logic:iterate>
						     												</logic:equal>
						     											</td>
				     												</tr>
															  	</jims2:isAllowed>
															</logic:notEqual>
															<logic:equal name="myReferrals" property="recType" value="REFERRAL">
															    <tr class=<%=rowClass%> >
						  											<td >
						  												<a onclick="spinner()" 
						  													href="/<msp:webapp/>displayJuvenileProfileReferralDetails.do?juvnum=<bean:write name="assignedReferralsForm" property="juvenileNum"/>&refnum=<bean:write name="myReferrals" property="referralNumber"/>"><bean:write name="myReferrals" property="referralNumber"/>
						  												</a>
						  											</td>
																	<td ><bean:write name="myReferrals" property="referralDate" format="MM/dd/yyyy"/></td>	
																	<td >
																			<logic:equal name="myReferrals" property="petitionsAvailable" value="true">
																				<span title="<bean:write name="myReferrals" property="petitionAllegationDescrExtended"/>"><bean:write name="myReferrals" property="petitionAllegation"/></span>
																		</logic:equal>
																	
																	</td>
																	<td>
																		<logic:notEmpty  name="myReferrals" property="supervisionCategory">
																			<span title="<bean:write name="myReferrals" property="supervisionCategory"/>"><bean:write name="myReferrals" property="supervisionCategoryId"/></span>/<span title="<bean:write name="myReferrals" property="supervisionType"/>"><bean:write name="myReferrals" property="supervisionTypeId"/></span>
																		</logic:notEmpty>
																	</td>
																	<td><span title="<bean:write name="myReferrals" property="jpo"/>"><bean:write name="myReferrals" property="jpoId"/></span></td>
																	<td><span title="<bean:write name="myReferrals" property="intakeDecision"/>"><bean:write name="myReferrals" property="intakeDecisionId"/></span></td>
																	<td><bean:write name="myReferrals" property="courtId"/></td>
																	<td><bean:write name="myReferrals" property="courtDate" format="MM/dd/yyyy"/></td>																
																	<td><span title="<bean:write name="myReferrals" property="courtResultDesc"/>"><bean:write name="myReferrals" property="courtResult"/></span></td>
																	<td><span title="<bean:write name="myReferrals" property="finalDispositionDescription"/>"><bean:write name="myReferrals" property="finalDisposition"/></span></td>
																	<td><bean:write name="myReferrals" property="petitionNumber"/></td>
																	<td><span title="<bean:write name="myReferrals" property="petitionStatusDescr"/>"><bean:write name="myReferrals" property="petitionStatus"/></span></td>
																	<td><bean:write name="myReferrals" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
																	<td><bean:write name="myReferrals" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td> 
																	<td><bean:write name="myReferrals" property="closeDate" formatKey="date.format.mmddyyyy"/></td>																
															  	</tr>
															  	<tr class=<%=rowClass%> >
				      											<td></td> 
					      											<td colspan='14'><b><bean:message key="prompt.offenses"/></b>: 
								      									<logic:equal name="myReferrals" property="offensesAvailable" value="true">
							     											<logic:iterate id="offenseCode" name="myReferrals" property="offenseCodes" indexId="index"> 
					    		 												<span title='<bean:write name="offenseCode" property="longDescription"/>, <bean:write name="offenseCode" property="category"/>'>
					     															<bean:write name="offenseCode" property="offenseCode"/>
					     															<logic:notEqual name="myReferrals" property="offenseCollectionSize" value="<%=index.toString()%>">
					     																,
					     															</logic:notEqual>
					     														</span>				     														
					     													</logic:iterate>
					     												</logic:equal>
					     											</td>
				     											</tr>
															</logic:equal>
				     										
				     										<tr>
																<td colspan="15"><logic:notEmpty name="myReferrals" property="adminReferrals">
																<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
																	<tr> 
																		<td width="1%" class="detailHead"><a href="javascript:showHideMulti('activity<bean:write name="myReferrals" property="referralNumber"/>', 'pchar<bean:write name="myReferrals" property="referralNumber"/>', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="activity<bean:write name="myReferrals" property="referralNumber"/>"></a></td> 
																		<td class="detailHead" nowrap='nowrap' align='left' valign='top'>Administrative</td>
																		<td class="hidden">
																		<jims:sortResults sortId="1" beanName="myReferrals" results="adminReferrals" primaryPropSort="referralNumber" primarySortType="int" defaultSort="true" defaultSortOrder="DESC"/>
																		</td>
																	</tr> 
																	<tr id="pchar<bean:write name="myReferrals" property="referralNumber"/>0" class='hidden'>
																		<td valign="top" align="center" colspan="14"> 
																			<table cellpadding="1" cellspacing="1" width="100%"> 
				     										
								     										<logic:iterate indexId="index1" id="children" name="myReferrals" property="adminReferrals">
																		    <% rowClass = ((index1.intValue()) % 2 == 1) ? "normalRow" : "alternateRow"; %>
																		    <tr class=<%=rowClass%> >
									  											<td width="4%"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralDetails.do?juvnum=<bean:write name="assignedReferralsForm" property="juvenileNum"/>&refnum=<bean:write name="children" property="referralNumber"/>"><bean:write name="children" property="referralNumber"/></a></td>
																				<td width="7%"><bean:write name="children" property="referralDate" format="MM/dd/yyyy"/></td>	
																				<td width="7%">
																						<logic:equal name="children" property="petitionsAvailable" value="true">
																							<span title="<bean:write name="children" property="petitionAllegationDescr"/>"><bean:write name="children" property="petitionAllegation"/></span>
																					</logic:equal>
																				
																			</td>
																				<td width="12%">
																					<logic:notEmpty  name="children" property="supervisionCategory">
																						<span title="<bean:write name="children" property="supervisionCategory"/>"><bean:write name="children" property="supervisionCategoryId"/></span>/<span title="<bean:write name="children" property="supervisionType"/>"><bean:write name="children" property="supervisionTypeId"/></span>
																					</logic:notEmpty>
																				</td>
																				<td width="5%><span title="<bean:write name="children" property="jpo"/>"><bean:write name="children" property="jpoId"/></span></td>
																				<td><span title="<bean:write name="children" property="intakeDecision"/>"><bean:write name="children" property="intakeDecisionId"/></span></td>
																				<td><bean:write name="children" property="courtId"/></td>
																				<td><bean:write name="children" property="courtDate" format="MM/dd/yyyy"/></td>																
																				<td><span title="<bean:write name="children" property="courtResultDesc"/>"><bean:write name="children" property="courtResult"/></span></td>
																				<td><span title="<bean:write name="children" property="finalDispositionDescription"/>"><bean:write name="children" property="finalDisposition"/></span></td>
																				<td><bean:write name="children" property="petitionNumber"/></td>
																				<td><span title="<bean:write name="children" property="petitionStatusDescr"/>"><bean:write name="children" property="petitionStatus"/></span></td>
																				<td><bean:write name="children" property="probationStartDate" formatKey="date.format.mmddyyyy"/></td>
																				<td><bean:write name="children" property="probationEndDate" formatKey="date.format.mmddyyyy"/></td> 
																				<td><bean:write name="children" property="closeDate" formatKey="date.format.mmddyyyy"/></td>																
																		  	</tr>
																		  		<tr class=<%=rowClass%> >
								      											<td></td> 
								      											<td colspan='14'><b><bean:message key="prompt.offenses"/></b>: 
											      									<logic:equal name="children" property="offensesAvailable" value="true">
										     											<logic:iterate id="offenseCode" name="children" property="offenseCodes" indexId="index"> 
								    		 												<span title='<bean:write name="offenseCode" property="longDescription"/>'>
								     															<bean:write name="offenseCode" property="offenseCode"/>
								     															<logic:notEqual name="children" property="offenseCollectionSize" value="<%=index.toString()%>">
								     																,
								     															</logic:notEqual>
								     														</span>
								     													</logic:iterate>
								     												</logic:equal>
								     											</td>
								     										</tr>
								     								  	</logic:iterate>
				     								  			</table>
				     								  			</td>
				     								  		</tr>
				     								  		</table>
				     								  		</logic:notEmpty>
				     								  		</td>
														  </tr>
				     								  	</logic:iterate>
													</logic:notEmpty>	
												</table>
<%-- END REFERRALS DETAILS TABLE--%>												
											</td>
										</tr>	
									</table>
<%-- END REFERRALS BORDER TABLE --%>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%" align="center">
										<tr>
											<td align="center">
												<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="goNav('back')">
													<bean:message key="button.back"></bean:message>
												</html:button>
												<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')">
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class="spacer"></div>
				  				</td>
				  			</tr>
				  		</table>
<%-- END BLUE BORDER TABLE --%>				  		
				  		<div class="spacer"></div>
				   	</td>
				</tr>
			</table>
<%-- END GREEN BORDER TABLE --%>				
   		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form> 
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>