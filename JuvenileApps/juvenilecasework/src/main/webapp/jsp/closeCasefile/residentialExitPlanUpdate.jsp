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
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>
<html:base />
<title><bean:message key="title.heading" /> - residentialExitPlanUpdate.jsp</title>


<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/spellcheck.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/residentialExitPlan.js"></script>
</head>
<%--END HEAD TAG--%>

<%-- Javascript for emulated navigation --%>
<html:javascript formName="residentialExitPlanForm" />

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayResidentialExitPlan.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|276">

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> - <bean:message key="title.residentialExitPlan"/> 
		<logic:equal name="casefileClosingForm" property="action" value="update"><bean:message key="title.update"/></logic:equal>
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
<table width="98%">
	<tr>
		<td>
	  		<ul>
	  			<li>Enter information then click "Next" button to view summary.</li>
	  			<li>Click "Back" button to return to previous page.</li>
	  		</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.diamond"/>Required Fields (reason required if answer to question is No)&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp"flush="true">
<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN MAIN BODY TABLE --%>
<table align="center" width="98%" cellpadding="0" cellspacing="0">
	<tr>
  	<td>
		  <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  				<tiles:put name="tabid" value="closing" />
  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  			</tiles:insert>

    	<%-- BEGIN RULE DETAIL TABLE --%>
    	<table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
    		<tr>
    			<td valign=top align=center>
  				<div class=spacer></div>
      			<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
      				<tr>
      					<td class=detailHead><bean:message key="prompt.generalInfo"/></td>
      				</tr>
      				<tr>
      					<td>
      					<table width='100%' cellpadding=2 cellspacing=1>        				  
      						<tr>
      							<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.expectedReleaseDate"/></td>
      							<td class="formDe">
								  <html:text name="residentialExitPlanForm" property="expectedReleaseDate" size="10" styleId="expectRelDate" readonly="true"/>
								  <input type="hidden" name="expectedReleaseDatex" value="<bean:write name='residentialExitPlanForm' property='expectedReleaseDate' />" />
								</td>
        						</tr>
        						<tr>
        							<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.diamond"/>Facility</td>
        							<td class="formDe"> 
        								<bean:write name="residentialExitPlanForm" property="facility"/>   
        								<input type="hidden" name="residentialExitPlanForm" value="<bean:write name="residentialExitPlanForm" property="facility"/>" styleId="facility"/>    							
									</td>
        						</tr>
        						<tr>
        							<td class="formDeLabel" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.facilityReleaseReason"/></td>
        							<td class="formDe">
									 <html:select name="residentialExitPlanForm" property="facilityReleaseReasonId" styleId="facilityReleaseReason">
          								<option value="">Please Select</option>
          								<html:optionsCollection name="residentialExitPlanForm" property="facilityReleaseReasonList" value="code" label="description"/>
          							 </html:select>
									</td>
        						</tr>
        						<tr>
        							<td class="formDeLabel" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.levelOfCare"/></td>
        							<td class="formDe">
										<html:select name="residentialExitPlanForm" property="levelOfCareId" styleId="levelOfCare">
          									<option value="">Please Select</option>
          									<html:optionsCollection name="residentialExitPlanForm" property="levelOfCareList" value="code" label="description"/>
          								</html:select>
									</td>
        						</tr>
        						<tr>
        							<td class="formDeLabel" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.permanencyPlan"/></td>
        							<td class="formDe">
										<html:select name="residentialExitPlanForm" property="permanencyPlanId" styleId="permanencyPlan">
          									<option value="">Please Select</option>
          									<html:optionsCollection name="residentialExitPlanForm" property="permanencyPlanList" value="code" label="description"/>
          								</html:select>
									</td>
        						</tr>
        						<tr>
        							<td class="formDeLabel" colspan="4" nowrap><bean:message key="prompt.specialNotes"/></td>
        						</tr>
        						<tr>
        							<td class="formDe" colspan="4">
									  <html:textarea rows="5" style="width:100%" name="residentialExitPlanForm" property="specialNotes"  styleId="specialNotes"/>
									</td>
        						</tr>
        					</table>
        					</td>
        				</tr>
        			</table>

        			<div class=spacer></div>
        			<table align="center" width='98%' border="0" cellpadding="2"
        				cellspacing="0" class="borderTableBlue">
        				<tr>
        					<td valign=top class=detailHead><bean:message key="prompt.casePlan"/> <bean:message key="prompt.questions"/></td>
        				</tr>
        				<tr>
        					<td>
        					<table width='100%' cellpadding=2 cellspacing=1 border=1>
        						<jims:questions name="residentialExitPlanForm" property="exitPlanQuestions" type="input" requiredGif="../../images/required.gif" columns="4"/>
        					</table>
        					</td>
        				</tr>
        			</table>

        			<%-- BEGIN BUTTON TABLE --%>
         			<div class=spacer></div>
        			<table width="100%">
      					<tr>
      						<td align="center">
	      						<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
	      		    			<html:submit property="submitAction" styleId="next"><bean:message key="button.next"></bean:message></html:submit>
	      		    		 	<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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




<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

