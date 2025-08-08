<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 07/20/2009     CShimek    #61004 added timeout.js  --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- testingRADevelop.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayTestingPeer?action=family" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|32">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.riskAssessmentDevelopmentalInformation"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Check all relevant boxes then select Next button for Family Relationship Questionaire.</li>
        <li>Select Back button to return to previous page. </li>
  	  </ul>
  	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				
	
		  <%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign="top" align="center">
  			  
    			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
						<div class='spacer'></div>
	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign="top">
  										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="riskAnalysis"/>
    												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  											</tiles:insert>	
  										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
  								  </tr>
							  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign="top" align="center">

                        <%-- BEGIN Program Referral History TABLE --%>
                        <div class='spacer'></div>
                        <table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
                        	<tr>
                        		<td class="detailHead"><bean:message key="prompt.testing"/>&nbsp;<bean:message key="prompt.info"/></td>
                        	</tr>
                        	<tr>
                        		<td align="center">
                        			<table width="100%" cellpadding="4" cellspacing="1">
                        				<tr>
                        					<td class="detailHead"><bean:message key="prompt.dateTaken"/></td>
                        					<td class="formDe" colspan="3"><bean:write name="riskTestingForm" property="testingAssessmentDate"/></td>
                        				</tr>
                        				
                        				<tr>
                          				<%int formatIdentifier = 0;%>
                          				<%-- rest of the question answers being dynamically pulled from the db  --%>
                          	      <nested:iterate id="questionAnswers" name="riskTestingForm" property="questionAnswers" indexId="outer">
                          	      	<%if(outer.intValue() == 3) { %>
                          						<tr>
                            						<td class="detailHead" colspan="6"> <bean:write name="questionAnswers" property="questionText"/> </td>						
                              						<logic:notEmpty name="questionAnswers" property="answers">
                                						<logic:equal name="questionAnswers" property="uiControlType" value="CHECKBOX">
                                  						<logic:iterate id="answer" name="questionAnswers" property="answers" indexId="inner">
                                    						<% if(inner.intValue() == 0 || inner.intValue() %2 == 0)
    																						  {
                                      						  out.print( "<tr>" ) ;
                                      						}
    																						%>
                                    						<td class="formDe">
                                      						<bean:define id="answerValue" name="answer" property="weightedResponseID" type="java.lang.Integer"/>
                                      						<nested:multibox property="selectedAnswerIDs" value="<%=answerValue.toString()%>" />
                                      						<bean:write name="answer" property="answerText" />						
                                    						</td>
                                    						<% if(inner.intValue() %2 != 0)
    																						  {
                                      						  out.print( "</tr>" ) ;
                                      						}
    																						  formatIdentifier = inner.intValue();
																								%>
                                  						</logic:iterate>	
                                  						<%if(formatIdentifier%2 == 0)
                                  						  { 
  																							  out.print( "<td class="formDe"></td>" ) ;
                                  						  }
  																						%>					
                                 						</logic:equal>						
                              						</logic:notEmpty>
													</td>
                            					</tr>
                          					<%}%>
                          				</nested:iterate>
                                </table>
                              </td>
                            </tr>
                          </table>
                          <%-- END Program Referral History TABLE --%>
                        
                        <%-- BEGIN BUTTON TABLE --%>
                        <div class='spacer'></div>
                        <table border="0" width="100%">
                          <tr>
                            <td align="center">
                              <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp;
                              <html:submit property="submitAction">
                              <bean:message key="button.next" />
                              </html:submit>&nbsp;
                              <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
                            </td>
                          </tr>
                        </table>
                        <%-- END BUTTON TABLE --%>
                      </td>
                    </tr>
                  </table>
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