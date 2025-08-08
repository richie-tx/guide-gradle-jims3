<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006	  AWidjaja Create JSP--%>
<%-- 07/17/2009   C Shimek #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script  type='text/javascript'>
function loadView(file, selectVal)
{
	var myURL = file  + "?selectedValue=" +selectVal;	

	load(myURL,top.opener);
	window.close();
}

function load(file,target) 
{
  window.location.href = file;
}

</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayAssociateRulesSummary" target="content">


<logic:equal name="status" value="confirm">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|56">
</logic:equal>
<logic:notEqual name="status" value="confirm">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|55">
</logic:notEqual> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Process Caseplan - Associate Goal to Rule</td>
	</tr>
	<logic:equal name="status" value="confirm">
		<tr><td align="center" class='confirm'>Goal has been successfully created.</td></tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN ERROR TABLE --%>
	<table width='98%' align="center">															
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>		
	</table>
<%-- END ERROR TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">

			<ul id='editInstruct'>
				<li>Select Rules to associate with this Goal.</li>
				<li>Select Next button to continue to Summary page.</li>
			</ul>

			<ul id='summaryInstruct' class='hidden'>
				<li>Verify that information is correct and select Save &amp; Continue button to save this Goal.</li>
				<li>If changes are required, select the Back button.</li>
			</ul>
		</td>
	</tr>
	<tr id='reqFieldsInstruct'> 
		<td class="required"><bean:message key="prompt.diamond" />&nbsp;Required Fields</td> 
	</tr> 
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign="top">
  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				
  		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
    					<tr>
  							<td valign="top">
    							<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
  					  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
  					  </tr>
  					</table>
						
						
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
    				  <tr>
  		          <td valign="top" align="center">

									<div class='spacer'></div>
      						<tiles:insert page="/jsp/casetabCaseplan/goalInformationTile.jsp" flush="true">
      							<tiles:put name="goalInfo" beanName="caseplanForm" beanProperty="currentGoalInfo" />	
      						</tiles:insert>
								
              		<div class='spacer'></div>
      						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      							<tr>
      							<td class="detailHead"><bean:message key="prompt.associated"/> <bean:message key="prompt.rules"/></td>
      						</tr>
      						<tr>
      							<td>
      								<table cellpadding="2" cellspacing="1" width='100%'>
      								  <tr bgcolor='#cccccc'>
      										<td></td>
      										<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.id"/></td>
      										<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.name" /></td>
                					<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.type" /></td>
                					<td class="subHead"><bean:message key="prompt.standard" /></td>
                					<td class="subHead"><bean:message key="prompt.monitor"/> <bean:message key="prompt.frequency" /></td>
      										<td class="subHead"><bean:message key="prompt.completion"/> <bean:message key="prompt.date"/></td>
      										<td class="subHead"><bean:message key="prompt.completion"/> <bean:message key="prompt.status"/></td>
      									</tr>
      
        								<logic:notEmpty name="caseplanForm" property="currentGoalInfo.associatedRules">
        									<logic:iterate indexId="index" id="rulesIndex" name="caseplanForm" property="currentGoalInfo.associatedRules">
      										
      											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
      												<td>
      													<html:multibox property="currentGoalInfo.selectedRules"><bean:write name="rulesIndex" property="ruleId"/></html:multibox>
      												</td>
      												<td>						
      													<a href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display Rule Details&selectedValue=<bean:write name="rulesIndex" property="ruleId"/>"><bean:write name="rulesIndex" property="ruleId"/></a>																		
      												</td>
      													<td><bean:write name="rulesIndex" property="ruleName" /></td>
                    						<td><bean:write name="rulesIndex" property="ruleTypeDesc" /></td>
                    						<td>
      													  <logic:equal name="rulesIndex" property="standard" value="true">
                    							 	STANDARD
                    							</logic:equal>
                    							<logic:equal name="rulesIndex" property="standard" value="false">
                    								CUSTOM
                    							</logic:equal>
                    						</td>
                    						<td><bean:write name="rulesIndex" property="ruleMonitorFreqDesc" /></td>
              									<td nowrap="nowrap"><bean:write name="rulesIndex" property="ruleCompletionDate" formatKey="date.format.mmddyyyy"/></td>
              									<td><bean:write name="rulesIndex" property="ruleCompletionStatus"/></td>										
              								</tr>
              							</logic:iterate>
              						</logic:notEmpty>
      				
      									</table>
      								</td>
      							</tr>
      						</table>

            <%-- BEGIN BUTTON TABLE --%>
						<div class="spacer"></div> 
						<table border="0" width="100%">
							<tr>
								<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						    </td>
						  </tr>
						</table>
						<div class="spacer"></div>
						<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<div class="spacer"></div> 
		</td>
	</tr>
</table>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

