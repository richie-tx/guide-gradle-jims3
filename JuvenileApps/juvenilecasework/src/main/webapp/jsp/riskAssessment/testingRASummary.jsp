<!DOCTYPE HTML>
<%-- 05/04/2011		DGibler		#69838 added CLM Update --%>
<%-- 04/13/2012		CShimek 	#72377, add errorAlert code --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/c.tld" prefix="c" %>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- testingRASummary.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<logic:equal name="riskAnalysisForm" property="mode" value="update">
	<c:set var="actionURL" value="/submitTestingUpdate" scope="request"/>  
</logic:equal>

<logic:notEqual name="riskAnalysisForm" property="mode" value="update">
	<c:set var="actionURL" value="/submitTesting" scope="request"/> 
</logic:notEqual>

<html:form action="${actionURL}" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|43">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.riskAssessmentTestingInformationSummary"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>       
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Finish button to view Testing Recommendation.</li>
				<li>Select Back button to return to previous page.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
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
    <td valign=top>
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="casefiledetailstab"/>
        <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>				

			<%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
  			  
    			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
						<div class='spacer'></div>       
	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="riskAnalysis"/>
    												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  											</tiles:insert>	
  										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
  								  </tr>
							  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign=top align=center>

                        <%-- BEGIN Program Referral History TABLE --%>
                        <div class='spacer'></div>       
                        <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" class="borderTableBlue">
                        	<tr>
                         		<td class="detailHead" colspan="4" align="left"><bean:message key="prompt.testing"/>&nbsp;<bean:message key="prompt.info"/></td>
                        	</tr>
                          <tr>
                            <td align="center">
                              <table width="100%" align="center" cellpadding="4" cellspacing="1">
                                <tr>
                                  <td class="formDeLabel" nowrap><bean:message key="prompt.dateTaken"/></td>
                                  <td class="formDe"><nested:write  name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/></td>
                                </tr>
                        				
								<logic:iterate id="questions" name="riskAnalysisForm" property="processedViewQuestionAnswers">
                        			<tr>
        	               				<td class=formDeLabel nowrap><bean:write name="questions" property="questionText" /></td>
    	                    			<td class=formDe align="left"><logic:equal name="questions" property="answerText" value="">No trait(s) selected</logic:equal>
	                                		<logic:notEqual name="questions" property="answerText" value="">
                               					<bean:write name="questions" property="answerText" />
                                			</logic:notEqual>
                        				</td>                      
                        			</tr>	
                  				</logic:iterate>
                                <logic:notEmpty name="riskAnalysisForm" property="modReason">
			                		<tr>
        								<td colspan='4'>
											<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
												<tr>
	                              					<td class='formDeLabel' colspan='4'>Reason for modification</td>
    	                          				</tr>
        	                      				<tr>
            	                  					<td class='formDe' colspan='4'>
            	                  						<bean:write name="riskAnalysisForm" property="modReason" />
            	                  					</td>
                	              				</tr>
                    	          			</table>
                        	      		</td>
        							</tr>
        						</logic:notEmpty>
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
                              <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" />
                          		</html:submit>&nbsp;
                              <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

