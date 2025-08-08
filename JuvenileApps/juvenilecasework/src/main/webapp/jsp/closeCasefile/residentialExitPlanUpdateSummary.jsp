<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

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
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - residentialExitPlanUpdateSummary.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="submitResidentialExitPlan.do" target="content">


<logic:notEqual name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|278"> 
</logic:notEqual>
<logic:equal name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|277"> 
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
				- <bean:message key="title.residentialExitPlan"/> <bean:message key="title.update"/> 
				<logic:notEqual name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
					Summary
				</logic:notEqual>
				<logic:equal name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
					Confirmation
				</logic:equal>
		</td>
	</tr>
</table>
	
<logic:equal name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
<%-- BEGIN MESSAGE TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="confirm">
			Residential Exit Plan Information has been saved.
		</td>
	</tr>
</table>
<%-- END MESSAGE TABLE --%>
</logic:equal>

<div class=spacer></div>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
 	<tr>
   	<td>
   	<logic:notEqual name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    	<ul>
      	<li>Verify information then click "Save and Continue" button.</li>
      	<li>Click "Back" button to return to the previous page.  </li>
    	</ul>
    </logic:notEqual>		
 	</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
	<%-- END HEADER INFO TABLE --%>

<%-- BEGIN MAIN BODY TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign=top>

			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing" />
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert></td>

      <%-- BEGIN RULE DETAIL TABLE --%>
      <table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
      	<tr>
      		<td valign=top></td>
        </tr>
        <tr>
      	  <td valign=top align=center>
            <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
            		<td class=detailHead><bean:message key="prompt.generalInfo"/> </td>
            	</tr>
            	<tr>
            		<td>
            			<table width='100%' cellpadding=2 cellspacing=1>
            				<tr>
            					<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.expectedReleaseDate"/></td>
            					<td class="formDe"><bean:write name="residentialExitPlanForm" property="expectedReleaseDate"/></td>
            					<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.facility"/></td>
            					<td class="formDe"><bean:write name="residentialExitPlanForm" property="facility"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap><bean:message key="prompt.facilityReleaseReason"/></td>
            					<td class="formDe"><bean:write name="residentialExitPlanForm" property="facilityReleaseReason"/></td>
            					<td class="formDeLabel" nowrap><bean:message key="prompt.levelOfCare"/></td>
            					<td class="formDe"><bean:write name="residentialExitPlanForm" property="levelOfCare"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap><bean:message key="prompt.permanencyPlan"/></td>
            					<td class="formDe" colspan="3"><bean:write name="residentialExitPlanForm" property="permanencyPlan"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" colspan="4" nowrap><bean:message key="prompt.specialNotes"/></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan="4"><bean:write name="residentialExitPlanForm" property="specialNotes"/> </td>
            				</tr>
            			</table>
           	  	</td>
             	</tr>
            </table>

            <div class=spacer></div>
            <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
            		<td class=detailHead><bean:message key="prompt.casePlan"/> <bean:message key="prompt.questions"/></td>
            	</tr>
            	<tr>
            		<td>
            			<table width='100%' cellpadding=2 cellspacing=1>
            				<jims:questions name="residentialExitPlanForm" property="exitPlanQuestions" type="summary" requiredGif="../../images/required.gif" columns="4"/>
            			</table>
            		</td>
            	</tr>
            </table>

            <%-- BEGIN BUTTON TABLE --%>
            <div class=spacer></div>
            <table width="100%">
             	<tr>
               	<td align="center">
                	<logic:notEqual name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
            				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
            		 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
            				<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
               		</logic:notEqual>
               		<logic:equal name="residentialExitPlanForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
            				<html:submit property="submitAction"><bean:message key="button.generate"></bean:message></html:submit>
            		 		<html:submit property="submitAction"><bean:message key="button.returnToCasefileClosingDetails"></bean:message></html:submit>
                  </logic:equal>
            		</td>
             	</tr>
            </table>
            <%-- END BUTTON TABLE --%>
            <div class=spacer></div>
          </td>
				</tr>
			</table>
    </td>
	</tr>
</table>
<%-- END MAIN BODY  TABLE --%>

</html:form>

<div class=spacer></div>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

