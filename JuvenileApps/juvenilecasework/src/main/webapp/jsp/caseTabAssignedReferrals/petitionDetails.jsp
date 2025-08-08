<!DOCTYPE HTML>

<%-- ************** NOTES ***************************
    Used to display casefile petition details
    06/10/2005 glyons  Create JSP 
**************************************************--%>

<%-- 07/05/2007	Uma Gopinath Modify JSP --%>
<%-- 08/21/2015 RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Referrals) --%>
<%-- 08/31/2015 RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Referrals) fix Summary of Facts text box --%>
<%-- 09/17/2015 RCapestani #29476 MJCW: Search Casefile/Referrals tab/select a Referral/Click on a Petition Number/Click on Cancel Button - Exception Thrown --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - petitionDetail.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
</head>
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0"> 
<html:form action="/displayMentalHealthAdaptiveFuncSummary" target="content">


<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|106">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Assigned Referral Petition Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Select a hyperlinked Petition # to view Petition Details.</li>
        <li>Select Back button to return to previous page.</li>
	  </ul>
	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER INFO --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END CASEFILE HEADER INFO --%>

<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>

      <!--tabs start-->
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="assignedreferralstab"/>
        <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>
      <!--tabs end-->

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  		<tr>
  			<td valign='top' align='center'>
            <!-- BEGIN TABLE -->
				<div class='spacer'></div>
  				<table width='98%' border="0" cellpadding="0" cellspacing="0">
  					<tr><td>
  						<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  							<tr>
  								<td valign='top'>
  							    <!--tabs start-->
								  	<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false"> 
 									    <tiles:insert page="../caseworkCommon/casefilePetitionTabs.jsp" flush="true">
		  				  				    <tiles:put name="tabid" value="petition"/>
		  				  				    <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
		  				  			    </tiles:insert>
	  					 			    </logic:equal>	
								  	<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="true"> 
 									    <tiles:insert page="../caseworkCommon/referralTabs.jsp" flush="true">
		  				  			    </tiles:insert>
	  					 			    </logic:equal>	
  									  <!--tabs end-->
  								</td>
  							</tr>
  							<tr>
							  	<td bgcolor='33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
							</tr>
  						</table>
					</td></tr>
					<tr><td>
  						<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
						  <tr>
							<td valign='top' align='center'>
	 							<div class='spacer'></div>
	 							<table width='98%' border="0" cellpadding="2" cellspacing="1">
								  	<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false"> 
	 								  <tr>
	 									<td valign='top' align='center' width='100%'>
							  		      <table width='100%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
						  		  			<tr>
											    <td class='detailHead'><bean:message key="prompt.petition"/> <bean:message key="prompt.#"/> - <bean:write name="petitionDetailsForm" property="petitionNum"/></td>
											</tr>
											<tr>
									  			<td valign='top' align='center'>
										  			<table cellpadding="4" cellspacing="1" width='100%'>
										  				 <tr>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.petitionFiledDate"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="petitionFiledDate" formatKey="date.format.mmddyyyy"/></td>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.petition"/> <bean:message key="prompt.status"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="petitionStatus"/></td>
													    </tr>
													     <tr>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.penal"/> <bean:message key="prompt.category"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="penalCategory"/></td>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.level"/> <bean:message key="prompt.degree"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="levelDegree"/></td>
													    </tr>
													     <tr>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.petition"/> <bean:message key="prompt.amendment"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="petitionAmendment"/></td>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.petition"/> <bean:message key="prompt.amendment"/> <bean:message key="prompt.date"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="petitionAmendmentDate" formatKey="date.format.mmddyyyy"/></td>
													    </tr>
													     <tr>
															  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.petition"/> <bean:message key="prompt.allegation"/></td>
															  <td class='formDe' colspan='3'><bean:write name="petitionDetailsForm" property="petitionAllegation"/></td>
															   <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.DPS"/> <bean:message key="prompt.code"/></td>
													  			<td class="formDe" colspan='3'><bean:write name="petitionDetailsForm" property="dpsCode"/></td>	
											    		</tr>
													</table>
												</td>
											</tr>
								  		</logic:equal>
										<logic:equal name="petitionDetailsForm" property="notDetailed" value="false">
											<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="true">
										     <tr>
										      <td valign='top' colspan="5" width='100%'>
												<table cellpadding="4" cellspacing="1" width='100%' class='borderTableBlue'>
													<tr>
													    <td class=detailHead colspan=5><bean:message key="prompt.JOTCharges"/></td>
													</tr>
													<logic:iterate indexId="index" id="charges" name="petitionDetailsForm" property="jotCharges">
													<logic:notEqual name="charges" property="recType" value="D.CHARGE">
													  <tr>
													     <td valign='top' align='center' colspan="5">
													       <table cellpadding="4" cellspacing="1" width='100%' border="0" align="center" class='borderTable'>
													        <tr>
													          <td width='1%' class='detailHead' colspan=5>
													          	<a href="javascript:showHideMulti('jotCharge<bean:write name="index"/>', 'pchar<bean:write name="index"/>', 1, '/<msp:webapp/>');" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name='jotCharge<bean:write name="index"/>'></a>
													         	 JOT Charge : (<bean:message key="prompt.CJIS#"/>
													          	<bean:write name="charges" property="CJISNum"/><logic:notEmpty name="charges" property="CJISSuffixNum">-<bean:write name="charges" property="CJISSuffixNum"/></logic:notEmpty>)
													          </td>
													        </tr>
													        <tr id='pchar<bean:write name="index"/>0' class='hidden'>
													         <td valign="top" align="center" colspan="2">
															     <table cellpadding="4" cellspacing="1" width='100%'>
																	<tr>
																	  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.arrestDate"/></td>
																	  <td class='formDe'><bean:write name="petitionDetailsForm" property="arrestDate" formatKey="date.format.mmddyyyy"/></td>
																	  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.arrestTime"/></td>
																	  <td class='formDe'><bean:write name="petitionDetailsForm" property="arrestTime" formatKey="date.format.HHmm"/></td>
																    </tr>
																    <tr>
																	  <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.charge"/></td>
																	  <td class="formDe" colspan=5><bean:write name="charges" property="charge"/></td>
																    </tr>
																    <tr>
																	  <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.CJIS#"/></td>
																	  <td class="formDe" colspan=5><bean:write name="charges" property="CJISNum"/><logic:notEmpty name="charges" property="CJISSuffixNum">-<bean:write name="charges" property="CJISSuffixNum"/></logic:notEmpty></td>									 
																    </tr>
															        <tr>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.gangRelated"/></td>
																	  <td class="formDe"><jims2:displayBoolean name="charges" property="gangRelated" trueValue="Yes" falseValue="No"/></td>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.underTheInfluence"/></td>
																	  <td class="formDe"><bean:write name="charges" property="alcoholOrDrugInfluence"/></td>
																	</tr>
																	<tr>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.lawEnforcementAgency"/></td>
																	  <td class="formDe"><bean:write name="charges" property="lawEnforcementAgency"/></td>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.levelOfOffense"/></td>
																	  <td class="formDe"><bean:write name="charges" property="levelOfOffense"/></td>
																	</tr>
																	<tr>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.offenseDate"/></td>
																	  <td class="formDe"><bean:write name="charges" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
																	  <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.offense"/> <bean:message key="prompt.degree"/></td>
																	  <td class="formDe"><bean:write name="charges" property="offenseDegree"/></td>										 
																	</tr>
																	<tr>
																	  <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.propertyLoss"/></td>
																	  <td class="formDe" colspan=5>
																	  <logic:notEqual name="charges" property="totalPropertyLossAmount" value=""> 
																	  <a href="javascript:newCustomWindow('/<msp:webapp/>handleJuvenileProfilePetitionDetails.do?submitAction=<bean:message key="button.viewPropertyLoss"/>', 'propertyLoss',450,450)"><bean:message key="currency.prefix"/><bean:write name="charges" property="totalPropertyLossAmount" formatKey="currency.format" /></a>
																	  </logic:notEqual>
																	  </td>									 
																	</tr>
																	<tr>
																	  <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.weaponUsed"/></td>
																	  <td class="formDe" colspan=5><jims2:displayBoolean name="charges" property="weaponUsed" trueValue="Yes" falseValue="No"/></td>									 
																	</tr>
																	<tr>
																	  <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.weaponType"/></td>
																	  <td class="formDe" colspan=5><bean:write name="charges" property="weaponType"/></td>									 
																	</tr> 
																  </table>
															  </td>
															</tr>
														   </table>
													     </td>
													    </tr>
													    </logic:notEqual>
													</logic:iterate>
											     </table>
											   </td>
											  </tr>
											</logic:equal>
											<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false">
											<tr>
									  			<td valign='top' align='center'>
											   	<table cellpadding="4" cellspacing="1" width='100%'>
													<tr>
													    <td class='detailHead' colspan='5'><bean:message key="prompt.JOTCharges"/></td>
													</tr>
													<tr>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.arrestDate"/></td>
													  <td class='formDe'><bean:write name="petitionDetailsForm" property="arrestDate" formatKey="date.format.mmddyyyy"/></td>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.arrestTime"/></td>
													  <td class='formDe'><bean:write name="petitionDetailsForm" property="arrestTime" formatKey="date.format.HHmm"/></td>
													</tr>
													<tr>
														<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.charge"/></td>
														<td class='formDe' colspan='5'><bean:write name="petitionDetailsForm" property="charge"/></td>
													</tr>
													<tr>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.CJIS#"/></td>
													  <td class='formDe' colspan='5'>
													  	<bean:write name="petitionDetailsForm" property="CJISNum"/><logic:notEmpty name="petitionDetailsForm" property="cjisSuffixNum">-<bean:write name="petitionDetailsForm" property="cjisSuffixNum"/></logic:notEmpty>
													  </td>									 
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.gangRelated"/></td>
														<td class='formDe'><jims2:displayBoolean name="petitionDetailsForm" property="gangRelated" trueValue="Yes" falseValue="No"/></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.underTheInfluence"/></td>
														<td class='formDe'><bean:write name="petitionDetailsForm" property="underTheInfluence"/></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.lawEnforcementAgency"/></td>
														<td class='formDe'><bean:write name="petitionDetailsForm" property="lawEnforcementAgency"/></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.levelOfOffense"/></td>
														<td class='formDe'><bean:write name="petitionDetailsForm" property="levelOfOffense"/></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.offenseDate"/></td>
														<td class='formDe'><bean:write name="petitionDetailsForm" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.offense"/> <bean:message key="prompt.degree"/></td>
														<td class='formDe'><bean:write name="petitionDetailsForm" property="offenseDegree"/></td>										 
													</tr>
													<tr>
													  	<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.propertyLoss"/></td>
														<td class='formDe' colspan='5'>
														<logic:notEqual name="petitionDetailsForm" property="totalPropertyLossAmount" value=""> 
														<a href="javascript:newCustomWindow('/<msp:webapp/>handleJuvenileCasefilePetitionDetails.do?submitAction=<bean:message key="button.viewPropertyLoss"/>', 'propertyLoss',450,450)"><bean:message key="currency.prefix"/><bean:write name="petitionDetailsForm" property="totalPropertyLossAmount" formatKey="currency.format"/></a>
														</logic:notEqual>
														</td>									 
													</tr>
													<tr>
														<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.weaponUsed"/></td>
														<td class='formDe' colspan='5'><jims2:displayBoolean name="petitionDetailsForm" property="weaponUsed" trueValue="Yes" falseValue="No"/></td>									 
													</tr>
													<tr>
														<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.weaponType"/></td>
														<td class='formDe' colspan='5'><bean:write name="petitionDetailsForm" property="weaponType"/></td>									 
													</tr>
											</logic:equal>
											<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="true">	
										     <tr>
										      <td valign='top' colspan="5" width='100%'>
												<table cellpadding="4" cellspacing="1" width='100%'>
											</logic:equal>
													<tr>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.identificationType"/></td>
													  <td class='formDe' colspan='5'><bean:write name="petitionDetailsForm" property="identificationType"/></td>									 
													</tr>
													<tr>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.adultCoActor"/></td>
													  <td class='formDe' colspan='5'><bean:write name="petitionDetailsForm" property="adultCoActor"/></td>									 
													</tr>
													<tr>
													  <td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.juvenileCoActor"/></td>
													  <td class='formDe' colspan='5'><bean:write name="petitionDetailsForm" property="juvenileCoActor"/></td>									 
													</tr>
													<tr>
													  <td class='formDeLabel' valign='top' colspan='6' nowrap='nowrap'><bean:message key="prompt.summaryOfFacts"/></td>
													</tr>
													<logic:notEmpty name="petitionDetailsForm" property="summaryOfFacts">
														<tr height='100%'>
															<td class='formDe' colspan="6" width='100%'>
																<span id='firstLine' class='visible'>
																	<bean:write name="petitionDetailsForm" property="summaryTextShort"/>&nbsp;<a href="javascript:showHide('moreSummText', 1);showHide('firstLine', 0)">more...</a>
																</span>
							  									<span class='hidden' id='moreSummText'>
							    									<div class='scrollingDiv100NoBorder'>
							    									  	<table border='0' width='100%' class="borderTable">
									    									<logic:iterate id="petitions" name="petitionDetailsForm" property="summaryOfFacts">
									    										<tr height='100%'>
									    									  		<td><bean:write name="petitions"/></td>
								    									  		</tr>
											    							</logic:iterate>
								    									    <tr>
								    									  	  <td><a href="javascript:showHide('moreSummText', 0);showHide('firstLine', 1)">hide</a></td>
								    									    </tr>
								    									</table>
							    									 </div>
							  								   </span>
															</td>
													 	</tr>
												 	</logic:notEmpty>
												 	<logic:empty name="petitionDetailsForm" property="summaryOfFacts">
													 	<tr>
													 		<td colspan='4' align='left'>No summary of facts.</td>
													 	</tr>
												 	</logic:empty>
													<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="true">	 
												  		</td></tr></table>
														<tr>
															<td colspan="6" width='100%'>
									  							<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											  		 </logic:equal>
						  				       		<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false"> 
								  						</td></tr></table>
									  					<table width='100%' border="0" cellpadding="2" cellspacing="0">
								  					</logic:equal>
									  					<tr>
															<td class='detailHead'>List of Victim/Witnesses</td>
														</tr>
														<logic:empty name="petitionDetailsForm" property="victimOrWitness"> 
															<tr>
														 		<td colspan='4' align='left'>No Victim/Witnesses.</td>
														 	</tr>
														</logic:empty>
														<logic:notEmpty name="petitionDetailsForm" property="victimOrWitness"> 
															<tr>
																<td>
																	<table width='100%' border="0" cellpadding="2" cellspacing="1">
																	 	<tr class='formDeLabel'>  
														                  <td valign='top'><bean:message key="prompt.victimOrWitnessName"/></td>
														                  <td valign='top'><bean:message key="prompt.relationshipToJuvenile"/></td>
														                  <td valign='top'><bean:message key="prompt.associationType"/></td>						                 
														                </tr>
																		<logic:iterate id="resultsIndex" name="petitionDetailsForm" property="victimOrWitness" indexId="index">
																			 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	    								                                        <td valign='top'><a href="javascript:newCustomWindow('/<msp:webapp/>handleJuvenileCasefilePetitionDetails.do?submitAction=View Victim&selectedSeqNum=<bean:write name='resultsIndex' property='sequenceNum'/>&selectedDALogNum=<bean:write name='resultsIndex' property='daLogNum'/>', 'victim',450,450)" >
	                                    								          <bean:write name="resultsIndex" property="name" /></a>
																				</td>
	                                            								<td valign='top'><bean:write name="resultsIndex" property="relationshipToJuvenile" /></td>
	                                            								<td valign='top'><bean:write name="resultsIndex" property="associationType" /></td>												                  
	                                          								</tr>																						
	                                        							</logic:iterate>
													            	</table>
													         	</td>
													     	</tr>
													  	</logic:notEmpty>
												  	</td>
												</tr>
									<div class='spacer'></div>
									<logic:equal name="petitionDetailsForm" property="jotChargesDisplayOnly" value="false">	 
									  </table>
									  </td>
										</tr>
									  </table>
								</logic:equal>
									
								</logic:equal>
							</table>
					  		 </td></tr>
					  		  </table>

					  		  <%-- BUTTON TABLE --%>
  								<table border="0" cellpadding='1' cellspacing='1' align='center'>						
									<tr>
			    			      		<td align="center"><input type='button' value='Back' onclick="goNav('back')">
			    			      			<html:button property="button.back" onclick="history.back();"><bean:message key="button.cancel"></bean:message></html:button>
			    			      		<td align="center"><html:submit property="submitAction"><bean:message key="button.print"/></html:submit>
			    			      			
			    			      		</td>
									</tr>					
								</table>
								<div class='spacer'></div>
								<%-- BUTTON TABLE END --%>	
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

<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
