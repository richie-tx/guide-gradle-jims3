<!DOCTYPE HTML>
<!-- User selects the "Medical" tab on Juvenile Profile Detail page -->
<%--MODIFICATIONS --%>
<%--05/05/07 Uma Gopinath CREATE JSP --%>
<%-- 11/07/2011 C Shimek     #71787 added script for expandTraits expand/contract state -- %>
<%-- 07/10/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to Add buttons --%>

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



<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">


<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - interviewInfoMedical.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/medical.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->


<body  topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<%
/* Added the variable to check if the request is coming from the action or from the pagination of trait to load and refresh the page DEFECT JIMS200076236*/
String sFromAction = request.getParameter("fromAction");
String sFromMedication = request.getParameter("fromMedication");
String sFromHealthIssue = request.getParameter("fromHealthIssue");
String sFromHospital = request.getParameter("fromHospital");	/* bug fix 20658 */
String sFromTraits = request.getParameter("fromTraits"); 	/* bug fix 20658 */
%>
<script type="text/javascript">
var fromAction = "<%=sFromAction%>";
var fromMedication = "<%=sFromMedication%>"; 
var fromHealthIssue = "<%=sFromHealthIssue%>"
/* bug fix 20658 */
var fromHospital = "<%=sFromHospital%>"
var fromTraits = "<%=sFromTraits%>"
</script>

<%-- <html:form action="/handleMedicalSelection"  target="content"> --%>
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|300">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.medical"/></td>
	</tr>
	<logic:present name="traitAdded">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
		<br>
	</logic:present>
</table>
<%-- END HEADING TABLE --%>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
<!--juv profile header start-->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>
<div class=spacer></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
<%-- BEGIN GREEN TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
					<!--tabs start-->
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>				
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
			</table>
<%-- END GREEN TABS TABLE --%>	
<%-- BEGIN GREEN BORDER TABLE --%>	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>
						<div class=spacer></div>   
<!-- BEGIN BLUE TABS TABLE -->											
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top align=center>
<!-- BEGIN HEALTH ISSUES TABLE -->				<html:form action="/handleMedicalSelection"  target="content">
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MED_ISS%>'>
													<div class=spacer></div>
													<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
														<tr>
															<td valign=top align=center>
																<table width='100%' border="0" cellpadding="1" cellspacing="1" >
																	<tr>
																		<td class=detailHead width='1%'><a href="javascript:showHideMulti('Health', 'pHealth', 2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Health"></a></td>
												  						<td valign=top class=detailHead><bean:message key="prompt.healthIssues"/>
															  				<logic:empty name="medicalForm" property="healthIssuesList" >
																				(No Previous Health Issues)
																			</logic:empty>
																		</td>
												  					</tr>
												  					<tr id="pHealth0" class=hidden>
												  						<td colspan=2>
														  					<table width='100%' cellspacing=1>
																				<logic:notEmpty name="medicalForm" property="healthIssuesList">
														  							<tr bgcolor='#cccccc'>
														  								<td class="subhead" valign=top></td>
														        						<td class="subhead" valign=top><bean:message key="prompt.entry"/> <bean:message key="prompt.date"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.issue"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.issue"/> <bean:message key="prompt.status"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.status"/></td>
														  							</tr>		  			
																					<logic:iterate indexId="indexer" id="resultsIndex" name="medicalForm" property="healthIssuesList">												
																            			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
														                  					<td valign=top>
																								 <logic:equal name="resultsIndex" property="healthStatusId" value="ACTIVE"> 
																									<input type="radio" name="selectedValue" data-sect='health' value='<bean:write name="resultsIndex" property="healthIssueId"/>'>
																							  	</logic:equal> 
																							</td>
														                  					
														                  					<td valign=top>
														                  						<a name='medicalLinks' href="/<msp:webapp/>handleMedicalSelection.do?submitAction=View&selectedValue=<bean:write name="resultsIndex" property="healthIssueId"/>&actionType=viewHealthIssue" >
	                                              												<bean:write name="resultsIndex" property="entryDate" formatKey="date.format.mmddyyyy" /></a>
	                                              											</td>
														                  					<td valign=top><bean:write name="resultsIndex" property="issueId" /></td>
														                  					<td valign=top><bean:write name="resultsIndex" property="issueStatusId" /></td>	
														                  					<%-- <td valign=top><bean:write name="resultsIndex" property="healthStatusId" /></td> --%>
														                  					<td valign=top>
																								<span title='<bean:write name="resultsIndex" property="healthStatusFull"/>'>
																									<bean:write name="resultsIndex" property="healthStatusId" /></span>
																							</td>																								                 
																				    	</tr>
											                    					</logic:iterate>
														            			</logic:notEmpty>
														  					</table>
							  											</td>
							  										</tr>
							  										<tr id="pHealth1" class=hidden>
								  										<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MED_ISS_U%>'>
							      											<td colspan=2 align=center>
									      										<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
									      										<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
									      											<html:submit property="submitAction">
																						<bean:message key="button.addHealthIssues" />
																					</html:submit>
																					<html:submit property="submitAction" disabled="true" styleId="upthlth">
																						<bean:message key="button.updateHealthIssues" />
																					</html:submit>
																					<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																					<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																					<html:submit property="submitAction">
																						<bean:message key="button.addHealthIssues" />
																					</html:submit>
																					<html:submit property="submitAction" disabled="true" styleId="upthlth">
																						<bean:message key="button.updateHealthIssues" />
																					</html:submit>
																					</jims2:isAllowed>
																					</logic:equal>
																				</logic:notEqual>
																				</logic:equal>
																			</td>
																		</jims2:isAllowed>
								  									</tr>
																</table>
															</td>
														</tr>
													</table>
<!-- END HEALTH ISSUES TABLE -->
												</jims2:isAllowed>
<!-- BEGIN MEDICATION TABLE -->
				  								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MED_MEDS%>'>
													<div class=spacer4px></div>
							           				<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		  												<tr>
		  													<td valign=top align=center>
	  															<table width='100%' border="0" cellpadding="1" cellspacing="1" >
																	<tr>
							              								<td class=detailHead width='1%'><a href="javascript:showHideMulti('Medication', 'pMedication', 2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Medication"></a></td>
													  					<td valign=top class=detailHead><bean:message key="prompt.medication"/> <bean:message key="prompt.info"/>
												  							<logic:empty name="medicalForm" property="medicationList" > 
										  										(No Previous Medications)
																			</logic:empty>
										  								</td>
										  							</tr>
										  							<tr id="pMedication0" class=hidden>
										  								<td colspan='2'>
											  								<table width='100%' cellspacing='1'>
																				<logic:notEmpty name="medicalForm" property="medicationList">
																					<tr bgcolor='#cccccc'>
																						<td class="subhead" valign=top></td>
																						<td class="subhead" valign=top><bean:message key="prompt.entry"/> <bean:message key="prompt.date"/></td>
																						<td class="subhead" valign=top><bean:message key="prompt.medication"/></td>
																						<td class="subhead" valign=top><bean:message key="prompt.currentlyTaking"/></td>
																					</tr>
																						
																					<logic:iterate indexId="index" id="medicationIndex" name="medicalForm" property="medicationList">
																						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">																							
																							<td valign=top>
																							    <%-- <logic:empty name="medicationIndex" property="modificationReason">
																									<input type="radio" name="selectedValue" value='<bean:write name="medicationIndex" property="medicationId"/>' onclick="enableUpdateBtnMed()">
																							  	</logic:empty>  --%> 
																							    <logic:equal name="medicationIndex" property="currentlyTakingMedication" value="YES"> 
																									<input type="radio" name="selectedValue" data-sect='medical' value='<bean:write name="medicationIndex" property="medicationId"/>'>
																							  	</logic:equal> 
																							</td>
																							
																							<td valign=top>
																								<a name='medicalLinks' href="/<msp:webapp/>handleMedicalSelection.do?submitAction=View&selectedValue=<bean:write name="medicationIndex" property="medicationId"/>&actionType=viewMedication">
																									<bean:write name="medicationIndex" property="entryDate" formatKey="date.format.mmddyyyy" /></a></td>
																							<td valign=top><bean:write name="medicationIndex" property="medicationName" /></td>
																							<td valign=top>
																								<span title='<bean:write name="medicationIndex" property="currentlyTakingMedicationFull"/>'>
																									<bean:write name="medicationIndex" property="currentlyTakingMedication" /></span>
																							</td>
																						</tr>												
																					</logic:iterate>        
																				</logic:notEmpty>
																			</table>
																		</td>
																	</tr>
							  										<tr id="pMedication1" class=hidden>
								  										<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MED_MEDS_U%>'>
				      														<td colspan=2 align=center>
				      															<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
				      															<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
					      															<html:submit property="submitAction">
																						<bean:message key="button.addMedication" />
																					</html:submit>
																					<html:submit property="submitAction" disabled="true" styleId="uptmed">
																						<bean:message key="button.updateMedication" />
																					</html:submit>
																					</logic:notEqual>
																					<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																					<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																					<html:submit property="submitAction">
																						<bean:message key="button.addMedication" />
																					</html:submit>
																					<html:submit property="submitAction" disabled="true" styleId="uptmed">
																						<bean:message key="button.updateMedication" />
																					</html:submit>
																					</jims2:isAllowed>
																					</logic:equal>
																				</logic:equal>
																			</td>
																		</jims2:isAllowed>
				  													</tr>
																</table>
															</td>
														</tr>
													</table>
<!-- END MEDICATION TABLE -->
												</jims2:isAllowed>
<!-- BEGIN HOSPITALIZATION TABLE -->
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MED_HOSP%>'>
													<div class=spacer4px></div>
													<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
														<tr>
															<td valign=top align=center>
																<table width='100%' border="0" cellpadding="1" cellspacing="1" >
																	<tr>
									              						<td class=detailHead width='1%'><a href="javascript:showHideMulti('Hospitalization', 'pHospitalization', 2, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Hospitalization"></a></td>
												  						<td valign=top class=detailHead><bean:message key="prompt.hospitalization"/> <bean:message key="prompt.history"/>
												  							<logic:empty name="medicalForm" property="hospitalizationList" > 
																				(No Previous Hospitalization Records)
																			</logic:empty>
																		</td>
												  					</tr>
												  					<tr id="pHospitalization0" class=hidden>
												  						<td colspan=2>
													  						<table width='100%' cellspacing=1>
																				<logic:notEmpty name="medicalForm" property="hospitalizationList">
														  							<tr bgcolor='#cccccc'>
														        						<td class="subhead" valign=top><bean:message key="prompt.entry"/> <bean:message key="prompt.date"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.admissionDate"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.releaseDate"/></td>
														  								<td class="subhead" valign=top><bean:message key="prompt.facilityName"/></td>
														  							</tr>
																					<logic:iterate indexId="index" id="hospIndex" name="medicalForm" property="hospitalizationList">
																						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																							<td valign=top>
																								<a name='medicalLinks' href="/<msp:webapp/>handleMedicalSelection.do?submitAction=View&selectedValue=<bean:write name="hospIndex" property="hospitalizationId"/>&actionType=viewHospitalization">
																								<bean:write name="hospIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></a>
																							</td>
																							<td valign=top><bean:write name="hospIndex" property="admissionDate" formatKey="date.format.mmddyyyy"/></td>
																							<td valign=top><bean:write name="hospIndex" property="releaseDate" formatKey="date.format.mmddyyyy"/></td>
																							<td valign=top><bean:write name="hospIndex" property="facilityName" /></td>																									                 
																						</tr>												
																					</logic:iterate>        
																				</logic:notEmpty>
																			</table>
																		</td>
																	</tr>
							  										<tr id="pHospitalization1" class=hidden>
								  										<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MED_HOSP_U%>'>
								      										<td colspan=2 align=center>
								      											<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
								      											<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
								      												<html:submit property="submitAction">
																						<bean:message key="button.addHospitalization"></bean:message>
																					</html:submit>
																					</logic:notEqual>
																					<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																					<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																					<html:submit property="submitAction">
																						<bean:message key="button.addHospitalization"></bean:message>
																					</html:submit>
																					</logic:notEqual>
																					</jims2:isAllowed>
																					</logic:equal>
																				</logic:equal>	
																			</td>
																		</jims2:isAllowed>
							  										</tr>
																</table>
															</td>
														</tr>
													</table>
<!-- END HOSPITALIZATION TABLE -->
												</jims2:isAllowed>
												</html:form>						
  												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MED_MEDS%>'>
  												<div class=spacer4px></div>					  			
					  								<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
														<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
														<tiles:put name="source" value="medical"/>
													</tiles:insert>
												<div class=spacer4px></div>	
												</jims2:isAllowed>
												
<!-- END TRAITS TABLE -->
<html:form action="/handleMedicalSelection"  target="content">
											</td>
										</tr>
			   						</table>
							<div class=spacer></div>
<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->    
		    			</table>
<!-- END PROFILE TABS TABLE -->
					</td>
				</tr>
			</table>
 <!-- END GREEN TABLE -->
	    </td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<div class=spacer></div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>