<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- dec 2006 - mjt - create jsp --%>
<%-- jul 20 2009 - cshimek  #61004 added timeout.js  --%>

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarEventDetails.jsp</title>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/displayViewCalendarDetails" target="content">


<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Program Referral Details</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" align="center">
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
                           <bean:write name="calendarEventListForm" property="programReferral.providerProgramName"/></td>
  </tr>
  <tr>
    <td>
      <table width='100%' cellpadding='2' cellspacing='1' align="center">
      <tr>
        <td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.beginDate" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.beginDateStr"/></td>
        <td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.endDate" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.endDateStr"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.assignedHours" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.assignedHours"/></td>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.courtOrdered" /></td>
        <td class='formDe'>
		<bean:define id="progRef" name="calendarEventListForm" property="programReferral"/>
		<jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.referralState.description"/></td>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.sentDate" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.acknowledgementDate" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
        <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.outcome" /></td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.outComeDescription"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' nowrap='nowrap'>&nbsp</td>
        <td class='formDe'>&nbsp</td>
        <td class='formDeLabel' nowrap='nowrap'>Total Hours</td>
        <td class='formDe'><bean:write name="calendarEventListForm" property="programReferral.totalCreditHours"/></td>
      </tr>
      <tr>
        <td class='formDeLabel' colspan='4' nowrap='nowrap'><bean:message key="prompt.comments" /></td>
      </tr>
		<tr>
			<td class="formDe" colspan="4">
				<div  class='scrollingDiv100'>
					<table>
						<logic:notEmpty name="calendarEventListForm" property="programReferral.referralComments">																							
							<logic:iterate  id="refComment" name="calendarEventListForm" property="programReferral.referralComments">
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
      <table width="100%" align="center">  
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>


