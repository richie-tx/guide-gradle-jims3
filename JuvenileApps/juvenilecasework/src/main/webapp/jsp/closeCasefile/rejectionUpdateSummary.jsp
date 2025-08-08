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
<title><bean:message key="title.heading"/> - rejectionUpdateSummary.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="submitCasefileRejection.do" target="content">
<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|51">
</logic:notEqual>    
<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|52">
</logic:equal>
    
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
 <tr>
   <td align="center" class="header">
   <bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.casefile"/> <bean:message key="title.closing"/> <bean:message key="title.rejection"/>
		<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
		<bean:message key="title.summary"/>
		</logic:notEqual>
	<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	<bean:message key="title.confirmation"/>
	</logic:equal>
   </td>
 </tr>
</table>

<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
<%-- BEGIN MESSAGE TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="confirm">Casefile has been rejected and notifications sent.</td>
	</tr>
</table>
</logic:equal>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class=spacer></div>
<table width="98%">
 	<tr>
   	<td>
     	<ul>
       	<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    			<li>Verify rejection reason then click "Save" button.</li>
    			<li>If any changes are needed, click "Back" button.</li>
        </logic:notEqual>
   			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
    			<li>Click "Return to Casefile" to return to casefile home</li>
    		</logic:equal>
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


<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  	<td valign=top>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				

      <%-- END CASEFILE TABS TABLE --%>	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  	  	<tr>
 					<td valign=top align=center>
            <%-- BEGIN CASEFILE TABLE --%>
  					<div class=spacer></div>
              <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
              	<tr>
              		<td valign=top class=detailHead><bean:message key="prompt.reject"/> - <bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
              	</tr>
              	<tr>
              		<td>
              			<table width='100%' cellpadding=2 cellspacing=1>
              				<tr>
              					<td class="formDeLabel" nowrap><bean:message key="prompt.rejection"/> <bean:message key="prompt.reason"/></td>
              				</tr>
              				<tr>
              					<td class="formDe"><bean:write  name="casefileClosingForm" property="rejectReason"/>&nbsp;</td>
              				</tr>
              			</table>
              		</td>
              	</tr>
              </table>
              <%-- END CASEFILE TABLE --%>
              <div class=spacer></div>
  					</td>
 			  	</tr>
   			</table>
   		</td>
	  </tr>
  </table>
  <%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class=spacer></div>
<table width="100%">
	<tr>
  	<td align="center">
     	<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
		 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
   		</logic:notEqual>
   		<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
 				<html:submit property="submitAction"><bean:message key="button.returnToNotifications"></bean:message></html:submit>
      </logic:equal>
  	</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

</html:form>

<div class=spacer></div>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
