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
<%@ taglib uri="/WEB-INF/ruleLiteral.tld" prefix="rule" %>
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
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />

<title><bean:message key="title.heading" /> - ruleAssign.jsp</title>

<script type='text/javascript'>
function textCounter( field, maxlimit )
{
  if( field.value.length > maxlimit )
  {
    alert( 'Maximum text length of ' + maxlimit + ' reached for this field - input has been truncated.' );
    field.value = field.value.substring( 0, maxlimit -1 );
  } 
  else
  {
    maxlimit = maxlimit - field.value.length;
  }
}
</script>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displaySupervisionRulesSummary">

<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|113">
</logic:equal>
<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|110">
</logic:notEqual>     


<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework" /> - Casefile - Assign 
			<logic:equal name="juvenileCaseworkConditionsForm" property="standard" value="true"> Standard</logic:equal>
			<logic:notEqual name="juvenileCaseworkConditionsForm" property="standard" value="true"> Custom</logic:notEqual> 
			Rules
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
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
		<ul>
			<li>Select <b>Monitor Frequency</b> and <b>Type</b>, then select <b>Next</b> button
			to view Summary.</li>
		</ul>
		</td>
	</tr>
	<tr>
			<td class="required"><bean:message key="prompt.requiredFields"/></td>												
		</tr>	
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END CASEFILE HEADER TABLE --%>

<div class='spacer'></div>
<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
		
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert>

      <script type="text/javascript">
        clearAllValArrays();
        var cusVarCal = new CalendarPopup();
        cusVarCal.showYearNavigation();
      </script>

		<%-- BEGIN DETAIL TABLE --%>
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			<tr>
				<td valign='top' align='center'>
				
				   <%-- data detail table --%>
					 <div class='spacer'></div>
					<table align="center" width='98%' border="0" cellpadding="4"  cellspacing="1" class="borderTableBlue">
						<tr>
							<td valign='top' class='detailHead' colspan='4'>Rules of Probation</td>
						</tr>
						<% int counter = 0; %>

							<logic:iterate id="ruleList" name="juvenileCaseworkConditionsForm" property="rules" indexId="index">
								<% if( index.intValue() != 0){ %>
  								<tr>
  									<td class="selectedRow" colspan='4'>&nbsp;</td>
  								</tr>
								<% } %>	
								
								<tr>
									<td>
									<table align="center" width='100%' cellpadding="2" cellspacing="1" class="borderTableGrey">
											<tr>
												<td class='detailHead' colspan='4'>Rule: <bean:write name="ruleList" property="ruleName" filter="false"/></td>
											</tr>

											<tr>
												<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond"/>Monitor Frequency</td>
												<td class='formDe'>
												  <bean:define id="freqVal" name="ruleList" property="freqKeyId" /> 
													<bean:define id="conditionForm" name="juvenileCaseworkConditionsForm" type="ui.juvenilecase.form.SupervisionConditionForm" /> 
													<select name="value(<%=freqVal%>)" id="freq<%=freqVal%>">
															<option value=""><bean:message key="select.generic" /></option>
													<%
        									 	ui.juvenilecase.form.SupervisionConditionForm condForm = conditionForm;
        									 	java.util.Collection monitorFreqCodes = condForm.getMonitorFreqCodes();
        									 	for (java.util.Iterator iter = monitorFreqCodes.iterator(); iter.hasNext(); /*empty arg*/)
        										{
        											messaging.codetable.reply.CodeResponseEvent codeResponse = (messaging.codetable.reply.CodeResponseEvent) iter.next();
        											out.print("<option value=" + codeResponse.getCode() + ">");
        											out.print(codeResponse.getDescription());
        											out.print("</option>");
        										}
        									 %>
													</select>

													<script type="text/javascript">
    												customValRequired('freq<%=freqVal%>','<bean:write name="ruleList" property="ruleName" filter="false"/>' +  ' Monitor Frequency is required');
													</script>
												</td>

												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/>Type</td>
												<td class='formDe' nowrap='nowrap'>
												  <bean:define id="typeVal" name="ruleList" property="ruleTypeKeyId" /> 
													<select name="value(<%=typeVal%>)" id="type<%=typeVal%>">
														<option value=""><bean:message key="select.generic" /></option>
														<%
          									 	java.util.Collection ruleCodes=condForm.getRuleTypeCodes();
          									 	for (java.util.Iterator iterRules = ruleCodes.iterator(); iterRules.hasNext(); /*empty arg*/)
          										{
          											messaging.codetable.reply.CodeResponseEvent codeResponseRules = (messaging.codetable.reply.CodeResponseEvent) iterRules.next();
          											out.print("<option value=" + codeResponseRules.getCode() + ">");
          											out.print(codeResponseRules.getDescription());
          											out.print("</option>");
          										}
          									 %>
													</select>

													<script type="text/javascript">
														customValRequired('type<%=typeVal%>','<bean:write name="ruleList" property="ruleName" filter="false"/>' +  ' Type is required');
													</script>
												</td>
											</tr>
											<tr>
												<td class='formDeLabel'><bean:message key="prompt.2.diamond"/>Rule Literal</td>
												<td class='formDe' colspan='3'><rule:ruleLiteral name="ruleList" /></td>
											</tr>
											<tr>
												<td class='formDeLabel' colspan='4'>Additional Information&nbsp;
													<bean:define id="additInfoVal" name="ruleList" property="additInfoKeyId" />
													<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
   						              <tiles:put name="tTextField" value='<%="value("+additInfoVal+")"%>' />
   						              <tiles:put name="tSpellCount" value='<%="spellBtn1"+additInfoVal%>' />
   						           </tiles:insert>
   						           (Max. characters allowed: 255)
												</td>
											</tr>
											<tr>
												<td class='formDe' colspan='4'>
												  <textarea name="value(<%=additInfoVal%>)" style="width: 100%" rows='3' onmouseout="textCounter(this,255)" onkeydown="textCounter(this,255)"></textarea>
												</td>
												<script type="text/javascript">
													customValMaxLength('value(<%=additInfoVal%>)','<bean:write name="ruleList" property="ruleName" filter="false"/>' +  ' Additional Info cannot be more than 255 characters','255');
													addDB2FreeTextValidation('value(<%=additInfoVal%>)','<bean:write name="ruleList" property="ruleName" filter="false"/>' +  ' Additional Info cannot contain invalid charcters such as < > +','');
												</script>
											</tr>
									</table>
									</td>
								</tr>
							</logic:iterate>
					</table>

						<%-- BEGIN BUTTON TABLE --%>
            <div class='spacer'></div>
    				<table border="0" width="100%">
   						<tr>
   							<td align="center">
   								<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
    							<html:submit property="submitAction" onclick="return validateCustomStrutsBasedJS(this.form)">
    								<bean:message key="button.next" ></bean:message></html:submit> 
    							<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
   							</td>
   						</tr>
    				</table>
    				<div class='spacer'></div>
    				<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>

</html:form>
<%-- END FORM --%>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

