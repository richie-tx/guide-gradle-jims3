<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>
<html:base />
<title><bean:message key="title.heading" /> - maysiDetailUpdate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/maysi.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update Mental Health History Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<br>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td align="left">
			<ul>
				<li>Select Subsequent Assessment button to go to Subsequent Mental Health Assessment form.</li>
				<li>Select Back button to return to previous page.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert> 
<%-- END JUVENILE HEADER INCLUDE  --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	  	<td valign="top">
	  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td valign="top">
						<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
	    			</td>
	    		</tr>
	    		<tr>
	    			<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
	    		</tr>
			</table>
			  	
  			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="mentalhealthtab"/>
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
				  				<td bgcolor='#6699FF' height="5"></td>
				  			</tr>
						</table>

						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class='spacer'></div>
  									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
	  									<tr>
	  										<td valign="top">
	  											<tiles:insert page="/jsp/caseworkCommon/mentalHealthTabs.jsp" flush="true">
	  												<tiles:put name="tabid" value="maysi"/>
	  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
	  											</tiles:insert>	
	  										</td>
	  									</tr>
	  									<tr>
		  						  			<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
	 						  			</tr>
	  								</table>   	
  
            						<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
            							<tr>
            								<td valign="top" align="center">
<html:form action="/displaySubsequentMAYSI" target="content">
            									<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|191">
            									<input type="hidden" name="useCase" value="manageJuvenileCasework">
												<div class='spacer'></div>
												<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
				           							<tr>
				           								<td valign="top" class="detailHead" colspan="4">General Assessment Details</td>
				           							</tr>
				           							<tr>
				           								<td>
<%-- BEGIN DETAIL TABLE --%>
							           						<table width='100%' cellpadding="4" cellspacing="1">
						            							<tr>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Referral Number</td>
																	<td valign="top" class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="referralNum"/></td>	
						            							</tr>	
																<tr>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Sex</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="sex" /></td>
						            								<td valign="top" class="formDeLabel" width='1%' nowrap="nowrap">Race</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="race"/></td>
						            							</tr>
						            							<tr>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Assessment Date</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="assessmentDate" formatKey="date.format.mmddyyyy" /></td>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Assessment Time</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="assessmentTime" formatKey="time.format.HHmm" /></td>
						            							</tr>
						            							<tr>
						            								<td valign="top" class="formDeLabel" width="1%" nowrap="nowrap">Assessment Review Date</td>
						            								<td valign="top" class="formDe">
																		<logic:equal name="mentalHealthForm" property="hasSubAssessment" value="true">
																			<bean:write name="mentalHealthForm" property="assessmentReviewDate" formatKey="date.format.mmddyyyy" />
																		</logic:equal>
																	</td>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Assessment Review Time</td>
						            								<td valign="top" class="formDe">
																		<logic:equal name="mentalHealthForm" property="hasSubAssessment" value="true">
																			<bean:write name="mentalHealthForm" property="assessmentReviewDate" formatKey="time.format.HHmm" />
																		</logic:equal>
																	</td>
						            							</tr>
						            							<tr>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Assessment Officer</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="assessmentOfficerName"/></td>
						            								<td valign="top" class="formDeLabel" width='1%' nowrap="nowrap">Assessment Option</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="assessmentOption"/></td>
						            							</tr>
						            							<tr>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Test Age</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="testAge"/></td>
						            								<td valign="top" class="formDeLabel" width='1%'>Has the youth taken a MAYSI before?</td>
						            								<td valign="top" class="formDe">
						            									<jims2:switch name="mentalHealthForm" property="hasPreviousMAYSI">
						            										<jims2:case value="true">YES</jims2:case>
						            										<jims2:default>NO</jims2:default>
						            									</jims2:switch>
						            								</td>
						            							</tr>
					      										<tr>
						            								<td valign="top" class="formDeLabel" width='1%'><bean:message key="prompt.wasTheMaysiAdministered"/></td>
						            								<td valign="top" class="formDe">
						            									<jims2:switch name="mentalHealthForm" property="administer">
						            										<jims2:case value="true">YES</jims2:case>
						            										<jims2:default>NO</jims2:default>
						            									</jims2:switch>
						            								</td>
						           									<td valign="top" class="formDeLabel" width='1%'>Identify reason MAYSI was not administered</td>
						           									<td valign="top" class="formDe">
						           										<bean:write name="mentalHealthForm" property="reasonNotDone"/>
						       											<logic:notEqual name="mentalHealthForm" property="scheduledOffIntDateStr" value="">
						       											 	<div>  
						       												  (Scheduled for <bean:write name="mentalHealthForm" property="scheduledOffIntDateStr" /> )
																			</div>           										
						       											</logic:notEqual>
						           									</td>
						            							</tr>
						            							<logic:notEqual name="mentalHealthForm" property="otherReasonNotDone" value="">
							            						  <tr>
							            							<td valign="top" class="formDeLabel" width='1%'>Detailed reason for other</td>
							            							<td valign="top" class="formDe">
							           										<bean:write name="mentalHealthForm" property="otherReasonNotDone"/>
						           									</td>
						            								<td valign="top" class="formDe"></td>
						            							</tr>
						            							</logic:notEqual>
						            							<tr valign="top">					
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">Location Unit</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="locationUnit"/></td>
						            								<td valign="top" class="formDeLabel" nowrap="nowrap">How long has youth been here?</td>
						            								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="lengthOfStay"/></td>
						          								</tr>
				  												<tr><td><div class="spacer"></div></td></tr>
				  												<tr><td><div class="spacer"></div></td></tr>
						            							<tr>
						            								<td valign="top" class="detailHead" colspan="4">Health Assessment Details</td>
						            							</tr>
						            							<logic:notEqual name="mentalHealthForm" property="hasDetails" value="true">
							              							<tr>
							              								<td valign="top" class="formDe" colspan="4" nowrap="nowrap">No Test Record on File</td>
							              							</tr>
						            							</logic:notEqual>
			
						            							<logic:equal name="mentalHealthForm" property="hasDetails" value="true">
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Screen Date</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="screenDate" formatKey="date.format.mmddyyyy"/></td>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Type of Facility</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="facilityType"/></td>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Alcohol/Drug</td>
							              								<td valign="top"  class="formDe"><bean:write name="mentalHealthForm" property="alcoholDrug"/></td>
							              								<logic:equal name="mentalHealthForm" property="alcoholDrugWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="alcoholDrugCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="alcoholDrugWarning" value="true">
							              									<logic:notEqual name="mentalHealthForm" property="alcoholDrugCaution" value="true">
							              										<td valign="top" class="formDe" colspan="2">&nbsp;</td>
							              									</logic:notEqual>
							              								</logic:notEqual>
							              							</tr>
							              							<tr>
							  											<td valign="top" class="formDeLabel" nowrap="nowrap">Angry/Irritable</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="angryIrritable"/></td>
							              								<logic:equal name="mentalHealthForm" property="angryIrritableWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="angryIrritableCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="angryIrritableWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="angryIrritableCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Depression/Anxiety</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="depressionAnxiety"/></td>
							              								<logic:equal name="mentalHealthForm" property="depressionAnxietyWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="depressionAnxietyCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="depressionAnxietyWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="depressionAnxietyCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							             							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Somatic Complaint</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="somaticComplaint"/></td>
							              								<logic:equal name="mentalHealthForm" property="somaticComplaintWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="somaticComplaintCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="somaticComplaintWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="somaticComplaintCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Suicide/Ideation</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="suicideIdeation"/></td>
							              								<logic:equal name="mentalHealthForm" property="suicideIdeationWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="suicideIdeationCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="suicideIdeationWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="suicideIdeationCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							             							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap"> Thought Disturbance</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="thoughtDisturbance"/></td>
							              								<logic:equal name="mentalHealthForm" property="thoughtDisturbanceWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="thoughtDisturbanceCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="thoughtDisturbanceWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="thoughtDisturbanceCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							                						</tr>
							                						<tr>
							              								<td valign="top" class="formDeLabel" nowrap="nowrap">Traumatic Expression</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="traumaticExpression"/></td>
							              								<logic:equal name="mentalHealthForm" property="traumaticExpressionWarning" value="true">
							              									<td valign="top" class="warningText" colspan="2">Warning</td>
							              								</logic:equal>
							              								<logic:equal name="mentalHealthForm" property="traumaticExpressionCaution" value="true">
							              									<td valign="top" class="cautionText" colspan="2">Caution</td>
							              								</logic:equal>
							              								<logic:notEqual name="mentalHealthForm" property="traumaticExpressionWarning" value="true">
								              								<logic:notEqual name="mentalHealthForm" property="traumaticExpressionCaution" value="true">
								              									<td valign="top" class="formDe" colspan="2">&nbsp;</td>
								              								</logic:notEqual>
							              								</logic:notEqual>
							              						  	</tr>
							            						</logic:equal>
													
						           								<tr><td><div class="spacer"></div></td></tr>
						           								<tr><td><div class="spacer"></div></td></tr>
																
																<%-- Subsequent Assessment Comments will only display if Assessment Option=Subsequent Done --%>
				    											<tr>
			    			        								<td valign="top" class="detailHead" colspan="4">Subsequent Assessment Details</td>
			            										</tr>		
			      												<logic:equal name="mentalHealthForm" property="hasSubAssessment" value="false">
			        												<tr>
			              												<td valign="top" class="formDe" colspan="4" nowrap="nowrap">No Subsequent Assessment Entered</td>
			              											</tr>
			            										</logic:equal>
			
						            							<logic:equal name="mentalHealthForm" property="hasSubAssessment" value="true">
							              							<tr>
							                							<td valign="top" class="formDeLabel" width='1%' colspan="3">Was the child referred to a mental health professional for a subsequent assessment based on the MAYSI results?</td>
							              								<td valign="top" class="formDe">
							              									<jims2:switch name="mentalHealthForm" property="subsAssessmentReferral">
							              										<jims2:case value="true">YES</jims2:case>
							              										<jims2:default>NO</jims2:default>
							              									</jims2:switch>
							              								</td>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDeLabel" width='1%' colspan="3">To what type of provider was the juvenile referred?</td>
							              								<td valign="top" class="formDe"><bean:write name="mentalHealthForm" property="providerReferredType"/></td>
							              							</tr>		
							              							<tr>
							              								<td valign="top" class="formDeLabel" width='1%' colspan="3">Was an assessment completed on this youth following the mental health assessment indicating the juvenile may have mental health issues?</td>
							              								<td valign="top" class="formDe">
							                								<jims2:switch name="mentalHealthForm" property="wasSubsAssessmentCompleted">
							              										<jims2:case value="true">YES</jims2:case>
							              										<jims2:default>NO</jims2:default>
							              									</jims2:switch>
							              								</td>
							              							</tr>		
							              							<tr>
							              								<td valign="top" class="formDeLabel" colspan="4">Subsequent Assessment Comments</td>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDe" colspan="4">
							              									<html:textarea name="mentalHealthForm" property="updatedMaysiComments" style="width:100%" rows="3" styleId="maysiComment"/>
							              								</td>
							              							</tr>
							              							<tr>
							              								<td valign="top" class="formDe" colspan="4">
							              									<bean:write name="mentalHealthForm" property="subsAssessmentComments"/>
							              								</td>
							              							</tr>
					      										</logic:equal>
						            						</table>
						            					</td>
						            				</tr>	
						            			</table>		
<%-- END DETAIL TABLE --%>
			            					</td>	
            							</tr>
			            				<tr><td colspan='4'><div class="spacer"></div></td></tr>
            							<tr>
            								<td>
            									<table border="0" cellpadding="1" cellspacing="1" align="center">
            										<tr>
            											<td align="center">
            													<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
            													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
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
						<div class='spacer'></div>
      				</td>
      			</tr>
      		</table>
      		<div class='spacer'></div>
		</td>
	</tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>