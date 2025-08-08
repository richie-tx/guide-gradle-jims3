<!DOCTYPE HTML>

<!-- User selects the "Mental Health" tab on Juvenile Profile Detail page then selects the Adaptive Behavior Tab -->
<!--MODIFICATIONS -->
<!-- 08/27/2010	Debbie Nikolis Create JSP -->
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

<html:javascript formName="abTestForm"/>
<!-- Javascript for emulated navigation -->
<title><bean:message key="title.heading"/> - mentalHealthABTestResultsCreate.jsp</title>
<!-- Javascript for emulated navigation -->

<script type="text/javascript" src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>  
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

</head> 

<body topmargin='0' leftmargin='0' >
<html:form action="/displayMentalHealthABSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|13">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
  </tr>
  <tr>
 	  <td align="center" class="header"><bean:message key="prompt.create"/> 
      <bean:message key="prompt.adaptiveBehavior"/> 
      <bean:message key="prompt.test"/> 
      <bean:message key="prompt.results"/> 
    </td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>

<table width="98%" border="0">
		<!-- instructions for data entry page -->
  <tr>
    <td>
      <ul>
        <li>Enter information as appropriate.</li>
      	<li>Select <b>Next</b> button to view the summary page.</li>
      	<li>Select <b>Back</b> button to return to the previous page.</li>
      </ul>
    </td>
  </tr>
  <tr>
     <td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
   </tr>
</table>
<!-- end instruction table -->


<!--juv profile header start-->

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>  
<!--juv profile header end-->


<!-- begin summary detail table -->
<div class='spacer'></div>
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
	          <!-- BEGIN TABLE -->
	          <div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											</td>
											</tr>
										<tr>
	                    <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
	                  </tr>
									</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top align=center>
												<div class='spacer'></div>
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign=top>
														 <tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
															<tiles:put name="tabid" value="adaptiveBehavior"/>
																<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' /> 
															</tiles:insert>	 
														</td>
													</tr>
													<tr>
											  	  <td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
											    </tr>
												</table>
												
												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td align=center>
															<!-- this table holds the data entry page -->
															<div class='spacer'></div>															
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td class=detailHead>Adaptive Behavior Test Results for Program Referral #<bean:write name='testingSessionForm' property='programReferralNum' /></td>
																</tr>
																<tr>
																	<td align=center>
																		<table border=0 cellpadding=4 cellspacing=1 width='100%'>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.serviceProvider"/></td>
																				<td class='formDe' colspan=3><bean:write name="testingSessionForm" property="serviceProviderName"/>
																			</td>
																			</tr>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.instructorName"/></td>
																				<td class='formDe' colspan=3><bean:write name="testingSessionForm" property="instructorName"/></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.test"/> <bean:message key="prompt.name"/></td>
																				<td class='formDe' colspan=3><bean:write name="testingSessionForm" property="abRec.testNameId"/></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
																				<td class='formDe'>
																					<html:text styleId="recTestDate" name="testingSessionForm" property="abRec.testDate" size="10" maxlength="10"/>
																				</td>
																			</tr>
																			<tr>
																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.communication"/></td>
																			  <td class='formDe'><html:text name="testingSessionForm" property="abRec.communicationScore" size="3" maxlength="3" /></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dailyLiving"/></td>
																			  <td class='formDe'><html:text name="testingSessionForm" property="abRec.livingScore" size="3" maxlength="3" /></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.socialization"/></td>
																			  <td class='formDe'><html:text name="testingSessionForm" property="abRec.socialScore" size="3" maxlength="3" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table><!-- borderTableBlue -->

                            <!-- BEGIN BUTTON TABLE -->                           
                            <table border="0" width="100%">																
                              <tr>
                                <td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>									 
																  <html:submit property="submitAction" styleId="ABTestResultsNext"><bean:message key="button.next"></bean:message></html:submit>	
																	<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                                </td>
                              </tr>															
                            </table>
                            <!-- END BUTTON TABLE -->

														</td>
													</tr>
												</table>
												<div class='spacer'></div> <!-- borderTableRed -->
											</td>
										</tr>
									</table>
									<div class='spacer'></div> <!-- borderTableBlue -->
								</td>
							</tr>
						</table> 
					</td>
				</tr>
			</table> <!-- borderTableGreen -->
		</td>
	</tr>
</table>

<!-- end summary detail table -->

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div> 
</body>
</html:html>
