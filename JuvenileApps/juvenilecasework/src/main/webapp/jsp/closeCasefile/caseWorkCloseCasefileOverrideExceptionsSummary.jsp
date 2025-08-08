<!DOCTYPE HTML>

<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - caseWorkCloseCasefileOverrideExceptionsSummary.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEAD TAG--%>


<%-- BODY and FORM TAG --%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="processCasefileClosing.do" target="content">


<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
	  <td align='center' class="header">Juvenile Casework - Close Casefile - Casefile Closing Override Exceptions 
 	    <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
    		Summary
    	</logic:equal>
	    <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
            Confirmation
        </logic:equal>
      </td>
  </tr>
  <tr>    
   	    <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
        	<td align='center' class="confirm">The Exceptions have been overridden.</td>
		</logic:equal>

  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERRORS TABLE -->
<table align='center' width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align='center' valign=top class=errorAlert>
		</td>
	</tr>
</table>
<!-- END ERRORS TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table align='center' width="98%">
  <tr>
    <td>
      <ul>

        <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
          <li>The exceptions listed will be overriden.</li>
          <li>Select the <b>Finish</b> button to override the exceptions.</li>
        </logic:equal>

        <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
          <li>Select the <b>Continue to Closing</b> button to continue to the Closing page.</li>
        </logic:equal>

      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<!-- BEGIN CASEFILE TABLE -->
<!-- BEGIN EXCEPTIONS TABLE -->
<div class=spacer></div>
<table align='center' width='98%' cellpadding="2" cellspacing="0" class=borderTableBlue>
  <tr>
  	<td class=detailHead>
  		<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
			Exceptions that will be overridden
	    </logic:equal>

    	<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
        	Exceptions that have been overridden
	    </logic:equal>
    </td>
  </tr>
  <tr>
    <td>
      <table width='100%' cellpadding=2 cellspacing=1>
        <tr bgcolor='#cccccc'>
          <td class=subHead colspan=2>Message</td>
        </tr>

				<logic:empty name="casefileClosingForm" property="casefileExceptions">
          <tr bgcolor="#cccccc">
            <td colspan=4 class="subHead">No exceptions(s) found.</td>
          </tr>
				</logic:empty>

				<logic:notEmpty name="casefileClosingForm" property="casefileExceptions">
  				<logic:iterate id="casefileException" name="casefileClosingForm" property="casefileExceptions" indexId="index">
    				<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
						
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.MAYSI_NOT_DONE%>" >
                <td><bean:message key="error.AssessmentNotDone" arg0="MAYSI"/></td>
              </logic:equal>
                         
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.BENEFITS_NOT_DONE%>" >										 		
                <td><bean:message key="error.AssessmentNotDone" arg0="BENEFITS"/></td>
              </logic:equal>
  
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.RISK_NOT_DONE%>" >
                <td><bean:message key="error.AssessmentNotDone" arg0="RISK"/></td>
              </logic:equal>
  
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PROG_REFERRALS_NOT_DONE%>" >									 		
                <td>
				  <bean:message key="error.assessmentNeeded" arg0="PROGRAM REFERRAL"/>
                  <bean:message key="prompt.programReferral"/>												
                  <bean:write name="casefileException" property="exceptionId" /> 
  
                  <logic:notEqual name="casefileException" property="exceptionMessage" value="">
                    <bean:write name="casefileException" property="exceptionMessage" /> 
                  </logic:notEqual>
  
                  <logic:equal name="casefileException" property="exceptionMessage" value="">
                    <bean:message key="error.notFinalized" />
                  </logic:equal>
                </td>
              </logic:equal>	 
  
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.GOALS_NOT_DONE%>" >									 		
                <td>
								  <bean:message key="error.AssessmentNotDone" arg0="GOALS"/>
                  <bean:message key="prompt.goal"/>												
                  <bean:write name="casefileException" property="exceptionId" /> 
                  <bean:message key="error.notFinalized" />
                </td>
              </logic:equal>	                                 
  
              <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.RULES_NOT_DONE%>" >									 		
                <td>
								  <bean:message key="error.AssessmentNotDone" arg0="RULES"/>
                  <bean:message key="prompt.rule"/>												
                  <bean:write name="casefileException" property="exceptionId" /> 
                  <bean:message key="error.notFinalized" />
                </td>
              </logic:equal>
						</tr>
  				</logic:iterate>
				</logic:notEmpty>							

      </table>
    </td>
  </tr>
</table>
<!-- END EXCEPTIONS TABLE -->
<!-- END DETAIL TABLE -->

<!-- override exceptions table/comments section -->
<div class=spacer></div>
<table align='center' width='98%' cellpadding="2" cellspacing="0" class=borderTableBlue>
  <tr>
  	<td class=detailHead>Override Exceptions</td>
  </tr>
  <tr>
    <td>
      <table width='100%' cellpadding=2 cellspacing=1>
        <tr >
          <td class=formDeLabel width='1%'>Reason for overriding exceptions</td>
        </tr>
        <tr>
          <td class=formDe><bean:write name="casefileClosingForm" property="exceptionOverrideComments"/></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- end override exceptions table/comments section -->


<!-- BEGIN BUTTON TABLE -->
<div class='spacer'></div>
<table width="100%">
  <tr>
    <td align="center" >

      <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
    		<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
 		 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
    		<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
      </logic:equal>

      <logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.continueToClosing"></bean:message></html:submit>
		<input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefileClosingForm' property='supervisionNumber'/>')" value="<bean:message key='button.viewCasefile'/>"/>
      </logic:equal>

    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
