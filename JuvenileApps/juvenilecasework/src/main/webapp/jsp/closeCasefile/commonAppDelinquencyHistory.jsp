<!DOCTYPE HTML>

<%--Common App ER 11046 changes--%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




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
<title><bean:message key="title.heading" /> - commonAppDelinquencyHistory.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/spellcheck.js"></script>

<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>


<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayCommonAppExitReportDetails.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|16">


<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
  		- <bean:message key="title.commonApp"/> <bean:message key="title.report"/> - <bean:message key="title.update"/>
		</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
    	<td>
      		<ul>
        		<li>Click tabs to navigate.</li>
        		<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
        			<li>Answer all questions then click "Next" button.</li>
					<li>Select the <b>Spell Check All Text Fields</b> button to check spelling for all text fields on page.</li>
				</logic:equal>
        		<li>Click "Back" button to return to the previous page.  </li>
      		</ul>
    	</td>
  	</tr>
  	<tr>
    	<td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- Begin DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="commonApp" />
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>
<%-- Begin blue tabs border table --%>					
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
<%-- Begin Common App Detail Tabs --%>
		        		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
      					  	<tr>
      		    				<td valign='top'>
<%-- Begin Juvenile Details Tabs table --%>      		    				
      		    					<table width='100%' border="0" cellpadding="0" cellspacing="0" >
        								<tr>
      										<td valign='top'>
      											<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
													<tiles:put name="tabid" value="ExitReport"/>
													<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  												</tiles:insert>	
            								</td>
                    					</tr>
                    					<tr>
      					  					<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
      					  				</tr>
      					  			</table>
<%-- End Juvenile Details Tabs table --%> 
<%-- Begin Red Tabs border table --%> 
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
										<tr>
											<td valign='top' align='center'>
												<div class='spacer'></div>
<%-- Begin Exit Report tabs table --%>												
												<table width='98%' border="0" cellpadding="0" cellspacing="0" >
													<tr>
														<td valign='top'>
															<!--tabs start-->
															<tiles:insert page="../caseworkCommon/commonAppExitReportTabs.jsp" flush="true">
																<tiles:put name="tabid" value="delinquencyHistory"/>
																<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
															</tiles:insert>	
															<!--tabs end-->
														</td>
													</tr>
													<tr>
														<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
													</tr>
												</table>
<%-- End Exit Report tabs table --%>
<%-- Begin Turquoise tabs border table --%>
												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
													<tr>
														<td valign='top' align='center'>
															<div class='spacer'></div>
<%-- BEGIN Questions TABLE --%>
															<table width='98%' cellpadding="2" cellspacing="1" class='borderTableBlue'>
																<tr>
																	<td class='detailHead' valign='top' colspan='2'><bean:message key="title.commonApp"/> <bean:message key="title.report"/> <bean:message key="prompt.questions"/></td>
																</tr>
																<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																	<tr>
																		<td>
																			<table width='100%' cellpadding='2' cellspacing='1' border='0'>
																				<jims:questions name="commonAppForm" property="exitReportQuestions" type="input" requiredGif="../../images/required.gif" columns="4"/>
																			</table>
																		</td>
																	</tr>
																</logic:equal>
																<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
																	<tr>
																		<td class="subhead"> Juvenile is 21 or more years old - no updating is allowed.</td>
																	</tr>	
																</logic:notEqual>
															</table>
 <%-- END Questions TABLE --%>
                        									<div class='spacer'></div>																
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
																		<input type="hidden" name="previousTab" value="CASA" /> 
																		<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																			<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
																		</logic:equal>	
																		<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
																	</td>
																</tr>
															</table>
<%-- END BUTTON TABLE --%>
															<div class='spacer'></div>
							                          	</td>
           											</tr>
           										</table>
<%-- Begin Turquoise tabs border table --%>           										
           										<div class='spacer'></div>
           		   							</td>
           								</tr>
           							</table>
<%-- End Red Tabs border table --%>           							
           							<div class='spacer'></div>
		  						</td>
  							</tr>
  						</table>
<%-- End Common App Detail Tabs --%>  						
		   			</td>
				</tr>
		  	</table>
<%-- End blue tabs border table --%>
	    </td>
	</tr>
</table>
<%-- End DETAIL TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>