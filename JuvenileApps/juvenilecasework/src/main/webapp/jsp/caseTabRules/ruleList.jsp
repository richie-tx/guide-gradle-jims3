<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 11/10/2005	Leslie Deen		Create JSP --%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012 CShimek		    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/11/2012 C Shimek        #73565 added age > 20 check (juvUnder21) to hide Assign buttons --%>
<%-- 08/06/2015 RYoung          #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %> 

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - ruleList.jsp</title>
</head>
<%--END HEADER TAG--%>
<script type="text/javascript">

$(function(){
	
	 $("#transferRules").click(function () {
		// alert("transfer");
		 var selectedRules=$('input[name=selectedRulesToTransfer]');
		 var ruleSelected = "false";
		 if(typeof  selectedRules!= "undefined"){			
			 $.each(selectedRules,function(idx,element){ 
				if($(this).is(':checked') )
					ruleSelected = "true";
			 });			 
		 }
		 
		 if(ruleSelected == "false")
		 {
			 alert("Select one or more rule(s) to complete rule(s) transfer.");
			 return false;
		}
	 });
});
</script>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayCasefileSupervisionConditions" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|108">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Casefile Rules</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

	
<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<li>Select a Rule ID hyperlink to view Rule Details.</li>
				<li>Select Assign Standard Rules button or Assign Custom Rules button to assign a new rule.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>


<%-- END CASEFILE HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">

      <%--tabs start--%> 
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="rulestab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> 
      <%--tabs end--%>

			<%-- BEGIN DETAIL TABLE --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
					<div class="spacer"></div>
					<%-- BEGIN RULES TABLE --%>
					<tiles:insert page="../caseworkCommon/ruleListTile.jsp" flush="true">
						<tiles:put name="title" value="Rules" />
						<tiles:put name="rules" beanName="supervisionRulesForm" beanProperty="assignedRulesList" />
						<tiles:put name="detailAction" value="displayCasefileSupervisionRuleList" />
						<tiles:put name="rulesForm" beanName="supervisionRulesForm" />
						<tiles:put name="from" value="casefile" />
					</tiles:insert>					
					<%-- END RULES TABLE --%> <%-- BEGIN BUTTONS INSIDE DETAIL TABLE --%>
					
					<div class="spacer"></div>
					<table border="0" cellpadding="1" cellspacing="1" align="center">
						<tr>
							<td align='center'>
							  <html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
								<logic:equal name="supervisionRulesForm" property="allowUpdates" value="true">
									<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_SR%>'> <!--  added for user-story #35092 -->
											<html:submit property="submitAction">
												<bean:message key="button.assignStandardRules"></bean:message>
											</html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_CR%>'> <!--  added for user-story #35092 -->
											<html:submit property="submitAction">
												<bean:message key="button.assignCustomRules"></bean:message>
											</html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RULES_TR%>'> <!--  added for user-story #22839 -->
											<html:submit property="submitAction" styleId="transferRules">
												<bean:message key="button.transferRules"></bean:message>
											</html:submit>&nbsp;
										</jims2:isAllowed>
									</logic:equal>	
								</logic:equal>
							  <input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
							</td>

						</tr>
					</table>
					<div class="spacer"></div>
					<%-- END BUTTONS INSIDE DETAIL TABLE --%></td>
					</td>
				</tr>
			</table>
			<%-- END DETAIL TABLE --%>
		</td>
	</tr>
</table>
<%-- END BLUE TABS TABLE --%>

</html:form>

<div class="spacer"></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

