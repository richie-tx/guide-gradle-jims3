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
<title><bean:message key="title.heading"/> - rejectionUpdate.jsp</title>

<%-- Javascript for emulated navigation --%>
<%--  <html:javascript formName="casefileClosingRejectionForm" /> --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   
<script type="text/javascript">
function validateCasefileClosingRejectionForm(theForm){
	if(theForm.rejectionReason.value == null || theForm.rejectionReason.value == ""){
	 alert("Rejection Reason is required.");
	 return false;
	}
}

</script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="displayCasefileRejection.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|47">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
 <tr>
   <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.casefile"/> <bean:message key="title.closing"/> <bean:message key="title.rejection"/></td>
 </tr>
</table>
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
       	<li>Enter Rejection Reason then click "Next" button to view summary.</li>
       	<li>Click "Back" button to return to the previous page.</li>
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

      <%-- BEGIN CASEFILE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		  	<tr>
					<td valign=top align=center>

					  <div class=spacer></div>
            <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
            	<tr>
            		<td class=detailHead><bean:message key="prompt.reject"/> - <bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
            	</tr>
            	<tr>
            		<td>
            			<table width='100%' cellpadding=2 cellspacing=1>
            				<tr>
            					<td class="formDeLabel" nowrap><bean:message key="prompt.rejectionReason"/></td>
            				</tr>
            				<tr>
            					<td class="formDe"><html:textarea styleId="rejectionReason" name="casefileClosingForm" property="rejectReason" style="width:100%" rows="5" onkeyup="textCounter(this, 4000)"/></td>
            				</tr>
            			</table>
            		</td>
            	</tr>
            </table>
						<div class=spacer></div>
					</td>
		  	</tr>
			</table>
			<%-- END CASEFILE TABLE --%>
 		</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class=spacer></div>
<table width="100%">
 	<tr>
   	<td align="center">
     	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
 		  <html:submit property="submitAction" onclick="return validateCasefileClosingRejectionForm(this.form)"><bean:message key="button.next"></bean:message></html:submit>
  		<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
   	</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

</html:form>

<div class=spacer></div>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

