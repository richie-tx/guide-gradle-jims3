<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- RCapestani 10/13/2015  Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>


<%--BEGIN HEADER TAG--%>
<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- notifyClm.jsp</title>
	
	<!-- Javascript for emulated navigation -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" >
	<html:base />
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript">
		function onloadForm()
		{
			document.getElementById("message").focus();
		}
	</script>
</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="onloadForm();">
<html:form action="/handleProgramReferral" target="content">
    <input type="hidden" name="clmId" value="<bean:write name="juvenileCasefileForm" property="caseloadManagerId"/>"/>
    <input type="hidden" name="juvId" value="<bean:write name="juvenileCasefileForm" property="juvenileNum"/>"/>
    <input type="hidden" name="clmName" value="<bean:write name="juvenileCasefileForm" property="caseloadManagerName"/>"/>
	<!-- BEGIN HEADING TABLE -->
	<table width='100%'>
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.serviceProvider"/>&nbsp;- Notify Caseload Manager&nbsp;</td>
	  </tr>
	</table>
	<table width="100%">
	  <div class='spacer'></div>
	  <tr>
	  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
	  </tr>
	</table>
	<!-- END HEADING TABLE -->
	
	<!-- BEGIN INSTRUCTION TABLE -->
	<div class='spacer'></div>
	<table width="98%" border="0">
	  <tr>
	    <td>
		  <ul>
	        <li>Enter message to send, then select the Send to Caseload Manager button.</li>
	        <li>Select the Spell Check button to check spelling.</li>
	        <li>Select the Cancel button to close this window and not sent the message.</li>
	      </ul>
		</td>
	  </tr>
	</table>
	<!-- END INSTRUCTION TABLE -->

	<!-- BEGIN DETAIL TABLE -->
	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	  <tr>
	    <td valign=top>
			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign='top' align='center'>
						<!-- BEGIN MESSAGE TABLE -->
          				<div class='spacer'></div>
		      			<table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				            <tr>
				              <td class=detailHead>Notify Caseload Manager:&nbsp;&nbsp;<bean:write name="juvenileCasefileForm" property="caseloadManagerName"/></td>
				            </tr>
	      					<tr><td class=formDeLabel><bean:message key="prompt.message" />
	      					<tiles:insert page="../../common/spellCheckTile.jsp" flush="false">
	      						<tiles:put name="tTextField" value="message"/>
	      						<tiles:put name="tSpellCount" value="spellBtn1" />
    						</tiles:insert> 
    						(Max. characters allowed: 3300)
    						</td></tr> 
				            <tr>
				               <td class="formDe" colspan="4">
				               		<textarea id="message" name="message" rows="7" style="width:100%" 
				               				  onmouseout="textCounter(this,3300)" onkeyup="textCounter(this,3300)" >
				               		</textarea>
				               </td>
				            </tr>
			            </table>
						<!-- END MESSAGE TABLE -->
		          </td>
        		</tr>
      		</table>
		 </td>
  		</tr>
	</table>
	<!-- END DETAILS TABLE -->							

	<div class='spacer'></div>
    <table border="0" cellpadding='1' cellspacing='1' align='center'>
	    <tr>
			<td align="center"> <input name="button" type='button' value='Cancel' onClick="javascript:window.close()"> </td>
			<td align="center"> <html:submit property="submitAction" onclick="javascript:window.close()"><bean:message key="button.sendToCaseloadManager"/></html:submit> </td>
		</tr>
	</table>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

