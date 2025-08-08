<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 10/10/2007	Uma Gopinath Created JSP --%>
<%-- 07/13/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to buttons and update field display --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
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
<title><bean:message key="title.heading"/> - dsmDiagnosis.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<!-- Jquery Framework Local Reference -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/jquery-ui.css" />
<script type="text/javascript" src="/<msp:webapp/>js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.min.js"></script> 

</head>

<body topmargin=0 leftmargin="0">
<html:form action="/displayCommonAppDSMDiagnosis" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|80">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Close Casefile - Common App - Exit Report DSM Questions</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<div class=spacer></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Click tabs to navigate.</li>
				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
					<li>Answer all questions, then select the Next button.</li>
				</logic:equal>
				<li>Select the Back button to return to the previous page.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<div class=spacer></div>
<!-- Begin Casefile Tabs Table -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  		<td valign="top">
      		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
      			<tiles:put name="tabid" value="commonApp"/>
      			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      		</tiles:insert>	
		</td>
	</tr>
</table>	
<!-- End Casefile Tabs Table -->
<!-- Begin blue Tabs border Table -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign="top" align="center">
  			<div class='spacer'></div>
<!-- Begin Juvenile Details Tabs table -->
       		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
			  	<tr>
    				<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
							<tiles:put name="tabid" value="ExitReport"/>
							<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
						</tiles:insert>	
                 		<!--tabs end-->
	       			</td>
				</tr>
				<tr>
					<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>
<!-- End Juvenile Details Tabs table -->			
<!-- Begin red Tabs border Table -->
          	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
          		<tr>
          			<td valign="top" align="center">
       					<div class=spacer></div>
<!-- Begin Exit Report Tabs Table -->
				        <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->                                							
									<tiles:insert page="../caseworkCommon/commonAppExitReportTabs.jsp" flush="true">
										<tiles:put name="tabid" value="DSMDiagnosis"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
    	 					<tr>
    	  						<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
          					</tr>
        				</table>
<!-- End Exit Report Tabs Table -->
<!-- Begin Turquoise Tabs border Table -->
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>                                      
<!-- BEGIN Exit Report Questions TABLE -->
 									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">			                                                
<!-- BEGIN Current Constellation TABLE -->
										<tr class="detailHead">
											<td>Exit Report Questions</td>
										</tr>
										<logic:empty name="commonAppForm" property="dsmResults">
											<tr>
												<td colspan="4" class="formDe">No DSM diagnosis available for the Juvenile.</td>
											</tr>
										</logic:empty>

										<logic:notEmpty name="commonAppForm" property="dsmResults">
											<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
												<tr>
													<td>
														<nested:iterate name="commonAppForm" property="dsmResults">
															<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">               																		
														 		<nested:equal property="dsmDiagnosisFound" value="true"> 
																	<tr class="detailHead">
																		<td colspan="2">DSM Diagnosis</td> <!-- bug fix 11243:spelling issues -->
																	</tr>														
																	<tr valign="top">
																		<td>																								 
																			<table cellpadding="2" cellspacing="1" width='100%'>
																				<tr bgcolor='#cccccc' class="subHead">
																					<td>DSM Diagnosis</td>
																					<td>Condition</td>
																					<td>Severity</td>																										  
																				</tr>
																				<nested:iterate property="diagnosisResults" indexId="index">
																					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																						<nested:notEqual property="diagnosisCd" value="">
																							<td><nested:write property="diagnosis"/></td>
																							<td>
																								<nested:select property="conditionCd">
																									<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																									<jims2:codetable codeTableName='HEALTH_CONDITION_LEVEL'></jims2:codetable>
																								</nested:select> 
																							</td>
																							<td>
																								<nested:select property="severityCd">
																									<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																									<jims2:codetable codeTableName='HEALTH_CONDITION_SEVERITY'></jims2:codetable>
																								</nested:select>
																							</td>
		                    															</nested:notEqual>
		                    														</tr>
		                    													</nested:iterate>																								
		                                       								</table>
		                                     							</td>
		                                     						</tr>
																</nested:equal>  
	                              							</table>   																	
	                              						</nested:iterate>
	                              					</td>
	                              				</tr>
                              				</logic:equal>
                              				<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
												<tr>
													<td class="subhead"> Juvenile is 21 or more years old - no updating is allowed.</td>
												</tr>	
											</logic:notEqual>
                            			</logic:notEmpty>
                               		</table>
<!--END Exit Report Questions TABLE -->
									<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
									<table width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 			                                                         	
												<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
													<html:submit property="submitAction"><bean:message key="button.saveChanges"></bean:message></html:submit>
												</logic:equal>	                                                       			
												<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
											</td> 
										</tr>
									</table>
<!-- END BUTTON TABLE -->
               						<div class="spacer"></div>
								</td>
							</tr>
						</table>
<!-- Begin Turquoise Tabs border Table -->	
						<div class="spacer"></div> 					
					</td>
				</tr>
			</table>
<!-- END red Tabs border Table -->
  			<div class=spacer></div>
		</td>
	</tr>
</table>
<!-- END blue Tabs border Table -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
</body>
</html:html>