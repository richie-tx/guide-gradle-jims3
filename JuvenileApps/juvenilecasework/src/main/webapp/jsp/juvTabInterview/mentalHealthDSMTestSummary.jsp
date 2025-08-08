<!DOCTYPE HTML>
<!-- User selects to Add Testing Session Results-->
<!--MODIFICATIONS -->
<!-- 02/12/2007	 Uma Gopinath	Create JSP -->
<!-- 07/20/2009  C Shimek   - #61004 added timeout.js  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

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

<title><bean:message key="title.heading"/> - mentalHealthDSMTestSummary.jsp</title>

<!-- Javascript for emulated navigation -->
<script language="JavaScript" src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

</head> 

<body topmargin="0" leftmargin="0" >
<html:form action="/submitMentalHealthDSMIV" target="content">
<logic:equal name="testingSessionForm" property="actionType" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|323">
</logic:equal>
<logic:equal name="testingSessionForm" property="actionType" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|324">
</logic:equal>
<logic:equal name="testingSessionForm" property="actionType" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|456">
</logic:equal>    

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/>  
                                     </td>
  </tr>
  <logic:notEqual name="testingSessionForm" property="actionType" value="view">
	  <tr>
	    <td align="center" class="header">Create DSM V Test Results
	    <logic:equal name="testingSessionForm" property="actionType" value="summary"> Summary</logic:equal>
	    <logic:equal name="testingSessionForm" property="actionType" value="confirm"> Confirmation</logic:equal></td>
	  </tr>
  </logic:notEqual> 
	<logic:equal name="testingSessionForm" property="actionType" value="view">
		<logic:equal name="testingSessionForm" property="showIV" value="Y">
		  <tr>
		    <td align="center" class="header" >DSM IV Test Details
					</td>
		  </tr>
		</logic:equal>
		<logic:notEqual name="testingSessionForm" property="showIV" value="Y">
			 <tr>
		    <td align="center" class="header" >DSM V Test Details
					</td>
		  </tr>
		</logic:notEqual>
	 </logic:equal>
  <logic:notEqual name="testingSessionForm" property="confirmMessage" value="">	
  	<tr><td align="center" class='confirm'><bean:write  name="testingSessionForm" property="confirmMessage"/></td></tr>
  </logic:notEqual>





</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  
  <logic:equal name="testingSessionForm" property="actionType" value="summary">
	  <tr>
	    <td>
	     <ul>
	    		<li>Select <b>Finish</b> button to save DSM IV Test results.</li>
	    		<li>Select <b>Back</b> button to return to previous page.</li>
	      </ul>
	    </td>
	  </tr>
  </logic:equal>
  <logic:equal name="testingSessionForm" property="actionType" value="view">
	  <tr>
	    <td align="left">
	     <ul>
	    		<li>Select <b>Back</b> button to return to previous page.</li>
	      </ul>
	    </td>
	  </tr>
  </logic:equal>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE --> 

<!--BEGIN JUVENILE PROFILE HEADER-->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<!--END JUVENILE PROFILE HEADER-->

<!--BEGIN DETAIL TABLE-->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
            <!--tabs start-->
	           <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
            <!--tabs end-->
          </td>
        </tr>
        <tr>
			  	<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			  </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
  				
	  				<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign="top">
  										<!--tabs start-->
  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="mentalhealthtab"/>
												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert>	
  										<!--tabs end-->
  										</td>
  									</tr>
    								<tr>
                      <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
                    </tr>
                  </table>
  
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 										<tr>
 											<td valign="top" align="center">
 												<div class='spacer'></div>
 												<table width='98%' border="0" cellpadding="0" cellspacing="0">
 													<tr>
 														<td valign="top">
 															<!--tabs start-->
 															<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																<tiles:put name="tabid" value="dsm"/>
																<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
															</tiles:insert>	
 															<!--tabs end-->
 														</td>
 													</tr>
 													<tr>
 												  	<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
 												  </tr>
 												</table>
  
 												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
 												  <tr>
 												  	<td align="center">
 												  		<div class='spacer'></div>
 												  		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
 												  			<logic:equal name="testingSessionForm" property="showIV" value="Y">
 																<tr>
 																	<td class="detailHead">DSM IV Results for Program Referral # <bean:write name="testingSessionForm" property="programReferralNum"/></td>
 																</tr>
 															</logic:equal>
 															<logic:notEqual name="testingSessionForm" property="showIV" value="Y">
 																<tr>
 																	<td class="detailHead">DSM V Results for Program Referral # <bean:write name="testingSessionForm" property="programReferralNum"/></td>
 																</tr>
 															</logic:notEqual>
 																<tr>
 																	<td align="center">			
 																		
 																		<table border="0" cellpadding="4" cellspacing="1" width='100%'>
 																			<tr>
																			<td class='formDeLabel'><bean:message key="prompt.serviceProvider"/></td>
																			<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="serviceProviderName"/>
																		</td>
																		</tr>
																		<tr>
																			<td class='formDeLabel'><bean:message key="prompt.instructorName"/></td>
																			<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="instructorName"/></td>
																		</tr>
 																			<tr>
 																				<td class='formDeLabel'><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
 																				<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="dsmRec.testDate"/></td>
 																			</tr>
 																		<logic:equal name="testingSessionForm" property="showIV" value="Y">
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.axisIPrimaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.axisISecondaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel'><bean:message key="prompt.axisITertiaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc"/></td>
 																				<td class='formDeLabel'><bean:message key="prompt.axisIFourth"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIFourthDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel'><bean:message key="prompt.axisIFifth"/></td>
 																				<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="dsmRec.axisIFifthDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.axisIIPrimaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.axisIISecondaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.axisIIIPrimaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.axisIIISecondaryScore"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc"/></td>
 																			</tr>
 
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.educationalProblems"/></td>
 																				<bean:define id="edu" name="testingSessionForm" property="dsmRec.isEducationalProblems" type="java.lang.Boolean" />
 																				<td class='formDe' colspan='3'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.isEducationalProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																				<jims2:else>No</jims2:else></jims2:if>
 																				</td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.housingProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.housingProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.occupationalProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.occupationalProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
 																				</td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.healthCareProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.healthCareProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
	  																					<jims2:else>No</jims2:else>
  																					</jims2:if>
  																				</td>
 																				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.legalSystemProblems"/></td>
 																				<td class='formDe'>
 																				  <jims2:if name="testingSessionForm" property="dsmRec.legalSystemProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																				<jims2:else>No</jims2:else></jims2:if>
  																			</td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' valign="top"><bean:message key="prompt.otherPsychoEnvProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.psychoEnvProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
 																				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.socialEnvProblems"/></td>
 																				<td class='formDe' >
 																					<jims2:if name="testingSessionForm" property="dsmRec.socioEnvProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.suppGrpProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.suppGrpProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																				<jims2:else>No</jims2:else></jims2:if>
  																				</td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.economicProblems"/></td>
 																				<td class='formDe'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.economicProblems" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																				<jims2:else>No</jims2:else></jims2:if>
  																			</td>
  																			</tr>
  																			<tr>
  																			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.mentalHealthNeeds"/></td>
 																				<td class='formDe' colspan='3'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.mentalHealthNeeded" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.currentMentalHealthTreatment"/></td>
 																				<td class='formDe' colspan='3'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.mentalHealthTreatment" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.mentalHealthDiagnosed"/></td>
 																				<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="dsmRec.dsmivDesc"/>
 																				</td>
 																			</tr>
 																																					  
 																			<tr>
 																				<td colspan='4' class='formDeLabel' valign="top" nowrap='nowrap'>Additional Axis IV Comments</td>
 																			</tr>
 																			<tr>
 																				<td colspan='4' class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIVComments"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.axisVScore"/></td>
 																				<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="dsmRec.axisVScore"/></td>
 																			</tr>
 																		</logic:equal>
 																		
 																		<logic:notEqual name="testingSessionForm" property="showIV" value="Y">
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.diagnosis1"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.diagnosis2"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel'><bean:message key="prompt.diagnosis3"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc"/></td>
 																				<td class='formDeLabel'><bean:message key="prompt.diagnosis4"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIFourthDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel'><bean:message key="prompt.diagnosis5"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIFifthDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis6"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis7"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis8"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis9"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc"/></td>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis10"/></td>
 																				<td class='formDe'><bean:write name="testingSessionForm" property="dsmRec.diagnosis10Desc"/></td>
 																			</tr>
 																			<tr>
  																			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.mentalHealthNeeds"/></td>
 																				<td class='formDe' colspan='3'>
 																					<jims2:if name="testingSessionForm" property="dsmRec.mentalHealthNeeded" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																					<jims2:else>No</jims2:else></jims2:if>
  																				</td>
  																			</tr>
  																			
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.mentalHealthDiagnosed"/></td>
 																				<td class='formDe' colspan='3'><bean:write name="testingSessionForm" property="dsmRec.dsmivDesc"/>
 																				</td>
 																			</tr>
 																			<tr>
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.currentMentalHealthTreatment"/></td>
 																				<td class='formDe' colspan='3'>
 																					<logic:equal name="testingSessionForm" property="dsmRec.mentalHealthNeeded" value="true">
	 																					<jims2:if name="testingSessionForm" property="dsmRec.mentalHealthTreatment" value="true" op="equal"><jims2:then>Yes</jims2:then>
	  																					<jims2:else>No</jims2:else></jims2:if>
	  																				</logic:equal>
  																				</td>
 																			</tr>
 																			<tr>
 																				<td colspan='4' class='formDeLabel' valign="top" nowrap='nowrap'>Comments</td>
 																			</tr>
 																			<tr>
 																				<td colspan='4' class='formDe'><bean:write name="testingSessionForm" property="dsmRec.axisIVComments"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDeLabel' nowrap='nowrap' colspan='4'><bean:message key="prompt.medical"/> <bean:message key="prompt.diagnosis"/></td>
 																			</tr>
 																			<tr>
 																				<td class='formDe' colspan='4'><bean:write name="testingSessionForm" property="dsmRec.medicalDiagnosis"/></td>
 																			</tr>
 																		</logic:notEqual>
 																		</table>
 																	</td>
 																</tr>
 															</table>
 															
                               <!-- BEGIN BUTTON TABLE -->
                               <table border="0" width="100%">
					    									<logic:equal name="testingSessionForm" property="actionType" value="view">							
                            			<tr>
                                		<td align="center">
                             			  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>														
										  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                                		</td>
                            			</tr>
                            		</logic:equal>
                            		
                            		<logic:equal name="testingSessionForm" property="actionType" value="summary">
                            			<tr>
                                		<td align="center">
                             					<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
												<html:submit property="submitAction" ><bean:message key="button.finish"></bean:message></html:submit>		
											  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                                		</td>
                            			</tr>
                           			</logic:equal>
                           			
                           			<logic:equal name="testingSessionForm" property="actionType" value="confirm">
                            			<tr>
                                		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.returnToTestSession"></bean:message></html:submit>
                                		</td>
                            			</tr>
                            		</logic:equal>
                               </table><!-- END BUTTON TABLE -->
 
                            </td>
                          </tr>
                        </table>
                        <div class='spacer'></div>
                      </td>
                    </tr>
                  </table><!-- END TABLE -->										
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
<!--END DETAIL TABLE-->
<!-- END FORM -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>