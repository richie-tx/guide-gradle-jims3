<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- programReferralDetails.jsp</title>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin=0 leftmargin="0">
<html:form action="/handleSocialHistoryData" target="content">


<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Program Referral Details</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Select the <b>Close</b> button to close this window. </li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<br>
<table id='referralDetailsView' align='center' width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue'>
  <tr>
    <td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> -
                           <bean:message key="prompt.program" />:&nbsp;
                           <bean:write name="socialHistoryForm" property="programReferral.providerProgramName"/></td>
  </tr>
  <tr>
    <td>
      <table width='100%' cellpadding='2' cellspacing='1'>
      <tr>
        <td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.beginDate" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.beginDateStr"/></td>
        <td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.endDate" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.endDateStr"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap><bean:message key="prompt.assignedHours" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.assignedHours"/></td>
        <td class='formDeLabel' nowrap><bean:message key="prompt.courtOrdered" /></td>
        <td class='formDe'>
		<bean:define id="progRef" name="socialHistoryForm" property="programReferral"/>
		<jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap><bean:message key="prompt.referralStatus" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.referralState.description"/></td>
        <td class='formDeLabel' nowrap><bean:message key="prompt.sentDate" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap><bean:message key="prompt.acknowledgementDate" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
        <td class='formDeLabel' nowrap><bean:message key="prompt.outcome" /></td>
        <td class='formDe'><bean:write name="socialHistoryForm" property="programReferral.outComeDescription"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' colspan='4' nowrap><bean:message key="prompt.comments" /></td>
      </tr>
		<tr>
			<td class="formDe" colspan="4">
				<div  class='scrollingDiv100'>
					<table>
						<logic:notEmpty name="socialHistoryForm" property="programReferral.referralComments">																							
							<logic:iterate  id="refComment" name="socialHistoryForm" property="programReferral.referralComments">
								<tr><td>[<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ] <bean:write name="refComment" property="commentText"/></td></tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
				</div>											
			</td>			
		</tr>
      </table>

      <!-- BEGIN BUTTON TABLE -->
      <br>
      <table width="100%">  
        <tr>
          <td align="center">
            <input type="button" value="Close" onClick="self.close();">
          </td>
        </tr>
      </table>
      <!-- END BUTTON TABLE -->

    </td>
  </tr>
</table><br>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>


