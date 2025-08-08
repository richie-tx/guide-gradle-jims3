<!DOCTYPE HTML>

<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - clientSatisfactionSurvey.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="displayCasefileClosingActivities.do" target="content">

	<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	  <logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
	     <logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>">
	        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|67">
	     </logic:notEqual> 
	  </logic:notEqual>   
	</logic:notEqual>   
	<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|162">
	</logic:equal>
	<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
		<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>" op="equal"/>
		<jims:then>
	       <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|62">		
		</jims:then>
	</jims:if>
	<logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>"><jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
		<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
		<jims:then>
		  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|164">
		</jims:then>
		</jims:if>
	</logic:notEqual>

	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr>   	  
	</table>
	<%-- END HEADING TABLE --%>
	
	<%-- BEGIN INSTRUCTION TABLE --%>
	<div class=spacer></div>
	<table width="98%" border="0">
	   <tr>
	     <td>
	      <ul>
	     	<li>Click tabs to navigate.</li>							
	    		<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
	    			<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
		    			<li>Select which version of document to print, and click Generate Document to continue.</li>
	    			</logic:equal>
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

	<%-- BEGIN MAIN BODY TABLE --%>
	<div class=spacer></div>
	<table align="center" border="0" width="98%" cellpadding="0" cellspacing="0">
	  <tr>
	   	<td valign=top>
	        <%-- BEGIN CASEFILE TABS TABLE --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
    	    <%-- END CASEFILE TABS TABLE --%>	

     	    <%-- BEGIN CASEFILE TABLE --%>
			<table align="center" border="0" width='100%' cellpadding="2" cellspacing="0" class='borderTableBlue'>
			    <tr>
					<td valign=top align=center>
						<div class='spacer'></div>	
					    <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
						    <tr>
						        <td valign=top class='detailHead' colspan=4 nowrap>Document Version</td>
							</tr>
							<tr>
								<td class='formDeLabel' width='1%' nowrap>English</td>
								<td class='formDe'><html:radio name="casefileClosingForm" property="spanishText" value="false"/></td>
							</tr>
							<div class='spacer8px'></div>	
							<tr>
								<td class='formDeLabel' width='1%' nowrap>Spanish</td>
								<td class='formDe'><html:radio name="casefileClosingForm" property="spanishText" value="true"/></td>
							</tr>
						</table>	
	    				<%-- BEGIN BUTTON TABLE --%>
	    				<div class=spacer></div>
	    				<table width="100%">
              				<tr>
              					<td valign=top align=center>
              						<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
              						<html:submit property="submitAction"><bean:message key="button.generateDocument"/></html:submit> 
          							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
       						    </td>
	                        </tr>
						</table>
						<%-- END BUTTON TABLE --%>
						<div class=spacer></div>
					</td>
			 	</tr>
			 </table>
	         <%-- END CASEFILE TABLE --%>
			</td>
		</tr>
	</table>
	<%-- END MAIN BODY  TABLE --%>

</html:form>

<div class=spacer></div>



<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

