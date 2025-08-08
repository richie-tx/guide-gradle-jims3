<!DOCTYPE HTML>

<!-- User selects to Update Testing Session Results -->
<!--MODIFICATIONS -->
<!-- 02/12/2007	Uma Gopinath	Create JSP -->

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

<html:javascript formName="dsmTestForm"/>
<!-- Javascript for emulated navigation -->
<title><bean:message key="title.heading"/> - mentalHealthDSMTestResultsCreate.jsp</title>

<!-- Javascript for emulated navigation -->
<script language="JavaScript" src="/<msp:webapp/>/js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>/js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>/js/juvTabInterview/dsmTestCreate.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>


</head> 

<body topmargin=0 leftmargin=0 styleId="mentalHealthDSMTestResultsCreate" >
<html:form action="/displayMentalHealthDSMIVSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|322">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
 <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
  </tr>
  <tr>
   	<td align="center" class="header"><bean:message key="prompt.create"/> DSM V <bean:message key="prompt.test"/> <bean:message key="prompt.results"/> 
		</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%" border="0">
  <tr >
    <td>
     <ul>
    		<li>All Diagnosis Scores can have a "v" at the beginning and a decimal point.</li>
    		<li>Enter values then select <b>Next</b> button to view the summary page.</li>
    		<li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
   <tr>
		 <td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
  </tr>
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
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
 <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
</table>
<!--END JUVENILE PROFILE HEADER-->

<!--BEGIN DETAIL TABLE-->
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
            <!--tabs start-->
           <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>		
            <!--tabs end-->
          </td>
        </tr>
       <tr>
	  	<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
	  </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign=top align=center>
					<div class=spacer></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  										<!--tabs start-->
  										<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="mentalhealthtab"/>
												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert>	
  										<!--tabs end-->
  										</td>
  									</tr>
    								<tr>
                      <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
                    </tr>
                 </table>
  
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">



 										<tr>
 											<td valign=top align=center>
												  
													<div class=spacer></div>                      										
  												<table width='98%' border="0" cellpadding="0" cellspacing="0">
  													<tr>
  														<td valign=top>
  															<!--tabs start-->
  																<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="dsm"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
  															<!--tabs end-->
  														</td>
  													</tr>
  													<tr>
												  	  <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												    </tr>
  												</table>
  
  												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
  												  <tr>
  												  	<td align=center>
															<div class=spacer></div>
  												  		<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  																<tr>
  																	<td class=detailHead>DSM V Results for Program Referral # <bean:write name='testingSessionForm' property='programReferralNum' /></td>
  																</tr>
  																<tr>
  																	<td align=center>  																		
  																														
  																		<table border=0 cellpadding=4 cellspacing=1 width='100%'>
  																			<tr>
																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.serviceProvider"/></td>
																				<td class='formDe' colspan=6><bean:write name="testingSessionForm" property="serviceProviderName"/>
																			</td>


																			</tr>
																			<tr>
																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.instructorName"/></td>
																				<td class='formDe' colspan=6><bean:write name="testingSessionForm" property="instructorName"/></td>
																			</tr>
  																			<tr>
  																				<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
  																				<td class='formDe' colspan=5>
															    		      		<html:text styleId="recTestDate" name="testingSessionForm" property="dsmRec.testDate" size="10" maxlength="10"/>						    		      
													    		      	  		</td>
  																			</tr>
  																			<tr>
  																			<td><input type="hidden" name="activeDiagnosisField" property="dsmRec.activeDiagnosisField"></td>
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.diagnosis1Code"/>  																				
	  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc">
	  																				<br><br><br>
	  																					<bean:message key="prompt.diagnosis1"/>
	  																				</logic:notEmpty>
							  													</td> 			
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																							<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisIPrimaryScore"  size="15" maxlength="15" />
																							    &nbsp;&nbsp;&nbsp;     		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IPrimaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit>
																							</td>
																						</tr>
																						<tr>
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIPrimaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td>
																						</tr>
																					
							  														<html:hidden name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc" styleId="dsmIPrimaryScoreDesc"/>		
																					<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc">
						  																<tr>
							  																<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc"/></td>
							  															</tr>
							  														</logic:notEmpty>
																						
																					</table>
																				</td>
  																				
  																				<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.diagnosis2Code"/>
  																					<logic:notEmpty name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc">	<br><br><br>
	  																					<bean:message key="prompt.diagnosis2"/>
	  																				</logic:notEmpty>
  																				</td>
  																				
  																			  	<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>  																					
																						<tr>
																							<td class='formDe' colspan=3>
																								
																									<html:text name="testingSessionForm" property="dsmRec.axisISecondaryScore"  size="15" maxlength="15"/>
		  																							 &nbsp;&nbsp;&nbsp; 
		  																							 <logic:notEmpty name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc">       		
																									<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_ISecondaryScore" >
																									<bean:message key="button.validateDiagnosisCode"></bean:message>
																									</html:submit>	</logic:notEmpty>																				
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIPrimaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisISecondaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td>
																							</logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc" styleId="dsmISecondaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc">
																							<tr>
																								<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc"/></td>
																							</tr>
																						</logic:notEmpty>																						
																					</table>
																				</td>
  																			<!-- ended -->
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel'><bean:message key="prompt.diagnosis3Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc">	<br><br><br>
	  																					<bean:message key="prompt.diagnosis3"/>
	  																				</logic:notEmpty>
	  																			</td>
  																				
  																				  	<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																							<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisITertiaryScore"  size="15" maxlength="15"/>
  																							&nbsp;&nbsp;&nbsp; 
  																							<logic:notEmpty name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc">        		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_ITertiaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisISecondaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisITertiaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td>
																						</logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc" styleId="dsmITertiaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc">
																							<tr>
																								<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc"/></td>
																							</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																				
  																				
  																				<td class='formDeLabel'><bean:message key="prompt.diagnosis4Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFourthDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis4"/>
	  																				</logic:notEmpty>
	  																			</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																							<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisIFourth"  size="15" maxlength="15"/>
  																							&nbsp;&nbsp;&nbsp;  
  																							    <logic:notEmpty name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc">   		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IFourth">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisITertiaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIFourthDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIFourthDesc" styleId="dsmIFourthDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFourthDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIFourthDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel'><bean:message key="prompt.diagnosis5Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFifthDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis5"/>
	  																			</logic:notEmpty>
  																				</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																							<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisIFifth"  size="15" maxlength="15" />																	
  																							&nbsp;&nbsp;&nbsp;  
  																							<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFourthDesc">      		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IFifth">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFourthDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIFifthDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIFifthDesc" styleId="dsmIFifthDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFifthDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIFifthDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																				
  																				
  																				<td class='formDeLabel'><bean:message key="prompt.diagnosis6Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis6"/>
	  																			</logic:notEmpty>
  																				</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																							<td class='formDe' ><html:text name="testingSessionForm" property="dsmRec.axisIIPrimaryScore"  size="15" maxlength="15" />
  																							&nbsp;&nbsp;&nbsp; 
  																							<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFifthDesc">       		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IIPrimaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIFifthDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIIPrimaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc" styleId="dsmIIPrimaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis7Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis7"/>
	  																			</logic:notEmpty>
	  																			</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																						<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisIISecondaryScore"  size="15" maxlength="15" />
  																						&nbsp;&nbsp;&nbsp; 
  																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc">        		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IISecondaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIPrimaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIISecondaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc" styleId="dsmIISecondaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																				
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis8Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis8"/>
	  																			</logic:notEmpty>
  																				</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																						<td class='formDe' colspan=3><html:text name="testingSessionForm" property="dsmRec.axisIIIPrimaryScore"  size="15" maxlength="15"/>
  																						&nbsp;&nbsp;&nbsp;
  																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc">         		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IIIPrimaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIISecondaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIIIPrimaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc" styleId="dsmIIIPrimaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																			</tr>
  																			<tr>
  																			      <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis9Code"/>
  																			      <logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis9"/>
	  																			  </logic:notEmpty>
  																			      </td>
  																			      <td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																						<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.axisIIISecondaryScore"  size="15" maxlength="15"/>																				
  																				        &nbsp;&nbsp;&nbsp;
  																				        <logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc">        		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_IIISecondaryScore">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIIPrimaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=axisIIISecondaryScoreDesc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc" styleId="dsmIIISecondaryScoreDesc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																				
  																				
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.diagnosis10Code"/>
  																				<logic:notEmpty name="testingSessionForm" property="dsmRec.diagnosis10Desc"><br><br><br>
	  																					<bean:message key="prompt.diagnosis10"/>
	  																			 </logic:notEmpty>
  																				</td>
  																				<td class='formDe'>
  																					<table width="100%" border="0" cellpadding=1 cellspacing=0>
																						<tr>
																						<td class='formDe'><html:text name="testingSessionForm" property="dsmRec.diagnosis10"  size="15" maxlength="15" /> 																		
  																				        
  																				        &nbsp;&nbsp;&nbsp;  
  																				        <logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc">       		
																								<html:submit property="submitAction" styleId="validateDSMVDiagnosisCode_diagnosis10">
																								<bean:message key="button.validateDiagnosisCode"></bean:message>
																								</html:submit></logic:notEmpty>
																							</td>
																						</tr>
																						<tr>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.axisIIISecondaryScoreDesc">
																							<td>&nbsp;Or&nbsp;
																			     				<a name="diagnosisLink" data-href="/<msp:webapp/>displayMentalHealthDSMVSearch.do?submitAction=Link&amp;diagnosisField=diagnosis10Desc"><bean:message key="prompt.searchForDiagnosis" /></a>
																							</td></logic:notEmpty>
																						</tr>
																						<html:hidden name="testingSessionForm" property="dsmRec.diagnosis10Desc" styleId="dsmDiagnosis10Desc"/>
																						<logic:notEmpty name="testingSessionForm" property="dsmRec.diagnosis10Desc">
																						<tr>
																							<td valign='top' class="formDe"><bean:write name="testingSessionForm" property="dsmRec.diagnosis10Desc"/></td>
																						</tr>
																						</logic:notEmpty>
																					</table>
																				</td>
  																			</tr>
  																		
  																		<tr>
  																			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.mentalHealthNeeds"/></td>
 																				<td class='formDe' colspan='3'>
 																					<html:hidden name="testingSessionForm" property="dsmRec.mentalHealthNeededStr" styleId="mhNeededStr"/>
 																						<html:radio name="testingSessionForm" property="dsmRec.mentalHealthNeeded" value="Yes" />Yes <html:radio name="testingSessionForm" property="dsmRec.mentalHealthNeeded" value="No"/>No
  																					<html:hidden name="testingSessionForm" property="dsmRec.mentalHealthNeeded" styleId="mhNeeded"/>
  																				</td>
  																			</tr>
  																			<tr id="diagnosis" class='hidden'>
 																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.mentalHealthDiagnosed"/></td>
 																				
 																				<td class="formDe" colspan="3">		
     																				<html:select name="testingSessionForm" property="dsmRec.dsmivId">
          																			<html:option value=""><bean:message key="select.generic"/></html:option>
           																			<html:optionsCollection name="testingSessionForm" property="dsmivDiagnosisList" value="code" label="description" />
     																				</html:select>
																				</td>																			
 																				
 																			</tr>
  																			<tr id="treatment" class='hidden'>
  																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.currentMentalHealthTreatment"/></td>
 																				<td class='formDe' colspan='3'>
 																				<html:hidden name="testingSessionForm" property="dsmRec.mentalHealthTreatmentStr" styleId="mhTreatmentStr"/>
 																					<html:radio name="testingSessionForm" property="dsmRec.mentalHealthTreatment" value="Yes"/>Yes <html:radio name="testingSessionForm" property="dsmRec.mentalHealthTreatment" value="No"/>No
  																					<html:hidden name="testingSessionForm" property="dsmRec.mentalHealthTreatment" styleId="mhTreatment"/>
  																				</td>
 																			</tr>
 																			
  																			<tr>
  																				<td colspan=6 class='formDeLabel' valign=top nowrap='nowrap'>Comments
								                  												  &nbsp;
								                                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                                  						<tiles:put name="tTextField" value="dsmRec.axisIVComments"/>
								                                   						<tiles:put name="tSpellCount" value="spellBtn1" />
								                                						</tiles:insert>  
								                                						(Max. characters allowed: 255)
																					</td>
  																			</tr>
  																			<tr>
  																				<td colspan=6 class='formDe'><html:textarea rows="4" cols="40" style="width:100%" name="testingSessionForm" property="dsmRec.axisIVComments" styleId="dsmaxisIVComments"></html:textarea></td>
  																			</tr>
  																			<tr>
  																				<td class='formDeLabel' colspan=6><bean:message key="prompt.medical"/> <bean:message key="prompt.diagnosis"/>
  																					  &nbsp;
								                                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
								                                  						<tiles:put name="tTextField" value="dsmRec.axisIVComments"/>
								                                   						<tiles:put name="tSpellCount" value="spellBtn1" />
								                                						</tiles:insert>  
								                                						(Max. characters allowed: 255)</td>
  																			</tr>
  																			<tr>
  																				<td colspan=6 class='formDe'><html:textarea rows="4" cols="40" style="width:100%" name="testingSessionForm" property="dsmRec.medicalDiagnosis" styleId="dsmmedicalDiagnosis"></html:textarea></td>
  																			</tr>
  																			</tr>
  																		</table>																
  																	</td>
  																</tr>
  															</table>
  															
                                <!-- BEGIN BUTTON TABLE -->
                               
                                <table border="0" width="100%">
                                	<tr>
                                	  <td align="center">
                                    	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>									 
                      								<html:submit property="submitAction" styleId="DSMTestResultsNext"><bean:message key="button.next"></bean:message></html:submit>
                      								<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                  									</td>
                                	</tr>
                                </table><!-- END BUTTON TABLE -->
                            </td>
                          </tr>
                        </table>
												<div class=spacer></div>
                      </td>
                    </tr>
                  </table><!-- END TABLE -->										
                </td>
              </tr>
            </table>
						<div class=spacer></div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!--END DETAIL TABLE-->

<!-- END FORM -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>