<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 01/18/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- associateRulesSummary.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script  type='text/javascript'>
$(document).ready(function(){
	sessionStorage.removeItem("timeFrame");
})

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

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitAssociateRules" target="content">


<logic:equal name="status" value="confirm">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|56">
</logic:equal>
<logic:notEqual name="status" value="confirm">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|57">
</logic:notEqual> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Casework - Process Caseplan - Associate Goal to Rule
			<logic:equal name="status" value="summary">Summary</logic:equal>
			<logic:equal name="status" value="confirm">Confirmation</logic:equal>
		</td>
	</tr>

	<logic:equal name="status" value="confirm">
		<!-- commeneted ER 11225<tr id='confMessage'><td align=center class='confirm'>Goal successfully associated to rule(s).</td></tr>-->
	<tr id='confMessage'><td align=center class='confirm'>Goal successfully created.</td></tr>
	</logic:equal>
	<logic:equal name="status" value="noRules">
		<!--commeneted ER11225<tr id='confMessage'><td align=center class='confirm'>No Rules are available for association.</td></tr>-->
	<tr id='confMessage'><td align=center class='confirm'>Goal successfully created.</td></tr>
	</logic:equal>
	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
        <logic:equal name="status" value="summary">
  				<li>Verify that information is correct and select Save &amp; Continue button to save this Goal.</li>
  				<li>If changes are required, select the Back button.</li>
        </logic:equal>

        <logic:equal name="status" value="confirm">
  				<li>Select Add Another Goal button to add a new Goal.</li>
          <li>Select Add Placement Plan button to add Placement information.</li>
          <li>Select Add Caseplan Comments button to add a Caesplan Comments activity.</li>
        </logic:equal>
			</ul>
		</td>
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
    <td valign=top>

  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  			<tiles:put name="juvNumId" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top align=center>
						<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  						<tr>
  							<td valign=top>
    							<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
  					  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
  					  </tr>
  					</table>
						
						
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
    				  <tr>
  		          <td valign=top align=center>
  		          	<div class='spacer'></div>
      						<tiles:insert page="/jsp/casetabCaseplan/goalInformationTile.jsp" flush="true">
      							<tiles:put name="goalInfo" beanName="caseplanForm" beanProperty="currentGoalInfo" />	
      						</tiles:insert>
      					</td>
      				</tr>

      				<!--commented as apart of ER 11225 -removing rules from goals
      				<logic:notEqual name="status" value="noRules">
        				<tr><td bgcolor='#ffffff'><br></td></tr>
        				<tr>
        					<td>
        						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
              				<tr>
              					<td class=detailHead nowrap colspan=8>Associated Rules</td>
        							</tr>
        
        							<tr bgcolor='#cccccc'>
        								<td></td>
        								<td class=subHead><bean:message key="prompt.rule"/> <bean:message key="prompt.id"/></td>
        								<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.name" /></td>
                  			<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.type" /></td>
                  			<td class="subHead"><bean:message key="prompt.standard" /></td>
                  			<td class="subHead"><bean:message key="prompt.monitor"/> <bean:message key="prompt.frequency" /></td>
        								<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.date"/></td>
        								<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.status"/></td>
        							</tr>
        
        							<logic:notEmpty name="caseplanForm" property="currentGoalInfo.selectedRulesList">
          							<logic:iterate indexId="index" id="rulesIndex" name="caseplanForm" property="currentGoalInfo.selectedRulesList">
        										
          							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
          							  <td></td>
          							  <td>						
          								  <a href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display Rule Details&selectedValue=<bean:write name="rulesIndex" property="ruleId"/>"><bean:write name="rulesIndex" property="ruleId"/></a>																	
          						  	</td>
          							  <td><bean:write name="rulesIndex" property="ruleName" /></td>
             						  <td><bean:write name="rulesIndex" property="ruleTypeDesc" /></td>
             						  <td>
          								  <logic:equal name="rulesIndex" property="standard" value="true">STANDARD</logic:equal>
               							<logic:equal name="rulesIndex" property="standard" value="false">CUSTOM</logic:equal>
               						</td>
               						<td><bean:write name="rulesIndex" property="ruleMonitorFreqDesc" /></td>
          								<td nowrap><bean:write name="rulesIndex" property="ruleCompletionDate" formatKey="date.format.mmddyyyy"/></td>
          								<td><bean:write name="rulesIndex" property="ruleCompletionStatus"/></td>										
          							</tr>
          						  </logic:iterate>
          					  </logic:notEmpty>
        						</table>
        					</td>
        				</tr>
      				</logic:notEqual>-->
      				
      				<tr>
      				 <td>
      				  <%-- BEGIN BUTTON fdas TABLE --%>
                <table border="0" width="100%">
                	<tr>
                		<td align="center">
  
              				<logic:equal name="status" value="noRules">
              					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
              						<input type="submit" name="submitAction" value="<bean:message key='button.addAnotherGoal'/>" 
              							onclick="changeFormActionURL('/<msp:webapp/>handleCaseplan.do', false);">
              					</jims2:isAllowed>
              					<input type="button" name="submitAction" value="<bean:message key='button.backToCaseplanDetails'/>" 
              						onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do?submitAction=Link', true);">
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
              						<input type="submit" name="submitAction" value="<bean:message key='button.addCaseplanComments'/>" 
              							onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanComments.do', false);">
              					</jims2:isAllowed>
              					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
              				</logic:equal>
              			<logic:notEqual name="status" value="noRules">					
              				<logic:notEqual name="status" value="confirm">				
              					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
              					<html:submit property="submitAction"><bean:message key="button.saveContinue"/></html:submit>
              					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
              				</logic:notEqual>
              				<logic:equal name="status" value="confirm">
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
              						<input type="submit" name="submitAction" value="<bean:message key='button.addAnotherGoal'/>" 
              							onclick="changeFormActionURL('/<msp:webapp/>handleCaseplan.do', false);">
              					</jims2:isAllowed>
              					<input type="button" name="submitAction" value="<bean:message key='button.backToCaseplanDetails'/>" 
              						onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do?submitAction=Link', true);">
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
              						<input type="submit" name="submitAction" value="<bean:message key='button.addCaseplanComments'/>" 
              							onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanComments.do', false);">
              					</jims2:isAllowed>
              				</logic:equal>
              			</logic:notEqual>
  
                  </td>
                </tr>
              </table>
              <%-- END BUTTON TABLE --%>
              <div class=spacer></div>
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

