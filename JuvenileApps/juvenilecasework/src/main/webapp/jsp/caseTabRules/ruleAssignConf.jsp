<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- Used to display search casefile results --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/condition.tld" prefix="jims2" %>

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
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>

<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<html:base />

<script type='text/javascript'>
function changeActionFormURL(myForm, URL, doSubmit)
{
	myForm.action = URL;
	if( doSubmit )
		myForm.submit();
}
</script>
<title><bean:message key="title.heading" /> - ruleAssignConf.jsp</title>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >

<html:form action="/submitAssignStandardRules">


<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="summary">
	<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true">
	  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|115">
	</logic:equal>
	<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">
	  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|112">
	</logic:notEqual> 
</logic:equal>

<logic:notEqual name="juvenileCaseworkConditionsForm" property="action" value="summary">
	<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true">
	  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|114">
	</logic:equal>
	<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">
	  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|111">
	</logic:notEqual>  
</logic:notEqual>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework" /> - Casefile - Assign 
			<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true">
				Standard
			</logic:equal> 
			
			<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">
				Custom
			</logic:notEqual> 
			Rules<br>

		<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="summary"><bean:message key="title.summary" /></logic:equal> 
		<logic:notEqual name="juvenileCaseworkConditionsForm" property="action" value="summary"><bean:message key="title.confirmation" /></logic:notEqual>
		</td>
	</tr>
</table>

<table width="98%">
	<tr>
		<td align="center" class="confirm">
			
			<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
					<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true"> Standard</logic:equal>
					<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">	Custom</logic:notEqual>
					Rules have been applied.
			</logic:equal>
		</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<logic:equal name="juvenileCaseworkConditionsForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">			
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
		<ul>
			<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">
				<li>Verify the information is correct and select <b>Finish</b>button to update Custom Rules.</li>
				<li>If any changes are required, select <b>Back</b> button.</li>
			</logic:notEqual>
			<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true">
				<li>Select <b>Finish</b> button to apply Standard Rules.</li>
			</logic:equal>
		</ul>
		</td>
	</tr>
</table>
</logic:equal>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END CASEFILE HEADER TABLE --%>

<div class='spacer'></div>
<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td valign="top">
  
      <tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert>
  		<%--tabs end--%>
  
  		<%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  				<tr>
  					<td valign=top></td>
  				</tr>
  				<tr>
  					<td valign=top align=center><%-- data detail table --%>
  
  					<div class='spacer'></div>
  							<table align="center" width='98%' border="0" cellpadding="4" cellspacing="1" class="borderTableBlue">
  							<tr>
  								<td valign=top class=detailHead colspan=4>Rules of Probation</td>
  							</tr>
  							<logic:iterate id="ruleList" name="juvenileCaseworkConditionsForm" property="rules" indexId="index">
  								<%if( index.intValue() != 0){ %>
    								<tr>
    									<td class="selectedRow" colspan=4>&nbsp;</td>
    								</tr>
  								<% } %>
  
  								<tr>
  									<td>
  									<table align="center" width='100%' cellpadding="2"
  										cellspacing="1" class="borderTableGrey">
  											<tr>
  												<td class=detailHead colspan=4>Rule: <bean:write name="ruleList" property="ruleName" filter="false"/></td>
  											</tr>
  
  											<tr>
  												<td class=formDeLabel nowrap>Monitor Frequency</td>
  												<td class=formDe><bean:write name="ruleList" property="monitorFreq" /></td>
  												<td class=formDeLabel>Type</td>
  												<td class=formDe nowrap><bean:write name="ruleList" property="ruleTypeDesc" /></td>
  											</tr>
  											<tr>
  												<td class=formDeLabel>Rule Literal</td>
  												<td class=formDe colspan=3><bean:write name="ruleList" property="ruleLiteralResolved" filter="false"/></td>
  											</tr>
  											<tr>
  												<td class=formDeLabel colspan=4>Additional Information</td>
  											</tr>
  											<tr>
  												<td class=formDe colspan=4><bean:write name="ruleList" property="additionalInformation" /></td>
  											</tr>
  									</table>
  									</td>
  								</tr>
  
  							</logic:iterate>
  
  					</table>
  
  					<%-- BEGIN BUTTON TABLE --%>
  					<div class=spacer></div>
        				<table width="98%" border="0" align="center">
        						<tr valign="top" align="center">
        							<td>
  										<logic:notEqual name="juvenileCaseworkConditionsForm" property="action" value="confirm">
        								   <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>&nbsp;
   				                   <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
        									    <bean:message key="button.finish" />
        								   </html:submit>&nbsp;
        								   <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
  	                   </logic:notEqual>
  
  	                   <logic:equal name="juvenileCaseworkConditionsForm" property="action" value="confirm">
        								   <input type="button" name="submitAction" value="<bean:message key='button.returnToCasefileRules'/>"
        									onclick="changeActionFormURL(this.form, '/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display All', true)">
        							</logic:equal>
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

</html:form>
<%-- END FORM --%>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

