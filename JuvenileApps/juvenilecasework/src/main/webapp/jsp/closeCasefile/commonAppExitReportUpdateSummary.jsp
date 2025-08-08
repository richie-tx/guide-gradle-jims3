<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
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
<title><bean:message key="title.heading" /> - commonAppExitReportUpdateSummary.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>
</head>
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="submitCommonAppExitReport.do" target="content">


<logic:notEqual name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|17">
</logic:notEqual>    
<logic:equal name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|18">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.commonApp"/> <bean:message key="title.report"/> - <bean:message key="title.update"/>
		 <logic:notEqual name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
			<bean:message key="title.summary"/>
		 </logic:notEqual>
<logic:equal name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
			<bean:message key="title.confirmation"/>
		</logic:equal>
		</td>
	</tr>
  <%-- BEGIN MESSAGE Row --%>
  <logic:equal name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
  	<tr>
  		<td align="center" class="confirm">Common App Exit Report Information has been saved.</td>
  	</tr>
  </logic:equal>
  <%-- END MESSAGE Row --%>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>



<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Click tabs to navigate.</li>

        <logic:notEqual name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
  				<li>Verify all information the click "Save and Continue" button.</li>
					<li>Click "Back" button to return to the previous page.</li>
    		</logic:notEqual>

    		<logic:equal name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
					<li>Click "Print Common App" to print Common App Report</li>
					<li>Click "Return to Closing Home Page" to go back to the Casefile Closing Home Page</li>
    		</logic:equal>
  	  </ul>
  	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>



<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign=top>
  	  <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  				<tiles:put name="tabid" value="commonApp" />
  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
 			</tiles:insert>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		  	<tr>
					<td valign=top align=center>
            <%-- Begin Common App Tabs --%>
						<div class='spacer'></div>
        		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" >
        		  <tr>
        		    <td valign=top>
                  <%-- Begin Common App Detail Tabs --%>
              		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
            		  	<tr>
            		    	<td valign=top>
            		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
              						<tr>
              							<td valign=top>
														  <tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
                    						<tiles:put name="tabid" value="ExitReport" />
                    						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
                    					</tiles:insert>
    												</td>
              						</tr>
              					 	<tr>
                        		<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
                         	</tr>
                				</table>

                  					<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
                					  	<tr>
                      					<td valign=top align=center>
  
  																<div class='spacer'></div>
                        					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
                        						<tr>
                        							<td valign=top>
                        								<!--tabs start-->
                        								<tiles:insert page="../caseworkCommon/commonAppExitReportTabs.jsp" flush="true">
                        									<tiles:put name="tabid" value="Questions"/>
                        									<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
                        								</tiles:insert>	
                        								<!--tabs end-->
                        							</td>
                        						</tr>
                        					 	<tr>
                        					  	<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
                        					  </tr>
                        					</table>
                  					
                        					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
                          					<tr>
                            					<td>
                  
                                      	<%-- BEGIN Questions TABLE --%>
        																<div class='spacer'></div>
                            						<table width='98%' cellpadding="2" cellspacing="1" class=borderTableBlue>
                            							<tr>
                            								<td class=detailHead valign=top colspan=2><bean:message key="title.commonApp"/> <bean:message key="title.report"/> <bean:message key="prompt.questions"/></td>
                            							</tr>
                            							<tr>
                            							<td>
                            							<table width='100%' cellpadding=2 cellspacing=1 border=0>							
                            								<jims:questions name="commonAppForm" property="exitReportQuestions" type="summary" requiredGif="../../images/required.gif" columns="4"/>								
                            							</table>
                            							</td>
                            						</tr>
                            							<logic:notEmpty name="commonAppForm" property="dsmResults">
                    										<tr>
                    											<td>
                    												<nested:iterate name="commonAppForm" property="dsmResults">          																
                    											
                    												<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">               																		
                															<nested:equal property="dsmDiagnosisFound" value="true">
                  															<tr class="detailHead">
                      														  <td colspan=2>DSM Diagnosis</td>
                      														</tr>														
                  															<tr valign=top>
                  															  <td colspan=2>																								 
                      															<table cellpadding=2 cellspacing=1 width='100%'>
                      															 	<tr bgcolor='#cccccc' class=subHead>
                  																	  <td>DSM Diagnosis</td>
                  																	  <td>Condition</td>
                  																	  <td>Severity</td>																										  
                  																	</tr>
            
                  																	<nested:iterate property="diagnosisResults" indexId="index">
                  																		<tr class="<% out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>"> 
                    																		<nested:notEqual property="diagnosis" value="">
                    																			 <td><nested:write property="diagnosis"/></td>
                                										   		 <td><nested:write property="condition"/></td>
                                										   		 <td><nested:write property="severity"/></td>
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
                  								</logic:notEmpty>							
                  						</table>
                              <%-- BEGIN BUTTON TABLE --%>
                              <div class='spacer'></div>
                              <table width="100%">
                              	<tr>
                                	<td align="center">
                                  	<logic:notEqual name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
                                				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
                                		 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
                                				<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                                 		</logic:notEqual>
                              
                                 		<logic:equal name="commonAppForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
                               				<html:submit property="submitAction"><bean:message key="button.printCommonApp"></bean:message></html:submit> 
                                  		<logic:notEqual name="commonAppForm" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
                                  		 		<html:submit property="submitAction"><bean:message key="button.returnToCasefileClosingDetails"></bean:message></html:submit>
                                  		 </logic:notEqual>
                                    </logic:equal>
                                  </td>
                                </tr>
                              </table>
                              <%-- END BUTTON TABLE --%>
                          	  <%-- END Questions  TABLE --%>
                          	</td>
                         	</tr>
                       	</table>															
              				  <%-- End Common App Detail Tabs --%>
              				  <div class='spacer'></div>
              				</td>
   							  	</tr>
    							</table>
   				   		</td>
   					  </tr>
    				</table>
    				<div class='spacer'></div>
    				<%-- End Common App Tabs --%>
        	</td>
      	</tr>
      </table>
      <%-- END DETAIL TABLE --%>
 		</td>
  </tr>
</table>

<div class='spacer'></div>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
