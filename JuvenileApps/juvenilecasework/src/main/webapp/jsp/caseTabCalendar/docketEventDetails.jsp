<!DOCTYPE HTML>

<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson	Create JSP --%>
<%-- 07/17/2009 C Shimek            #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - docketEventDetails.jsp</title>
</head>

<body topmargin='0' leftmargin="0">
<html:form action="/handleCalendarEventDetails" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbspDocket Event Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Select Close button to close the window.</li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
  <tr>
    <td valign='top' align='center'>

      <!-- BEGIN Dockets TABLE -->
      <table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
        <tr>
          <td class="detailHead"><bean:message key="prompt.docketEvent" />&nbsp;<bean:message key="prompt.details" /></td>
        </tr>

        <tr>
          <td>
            <table width='100%' cellpadding="2" cellspacing="1">
              <tr>
                <td class="formDeLabel" width='1%'><bean:message key="prompt.petition#" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.petitionNumber"/></td>
                <td class="formDeLabel" width='1%'><bean:message key="prompt.court" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.court"/></td>
              </tr>
              <tr>
                <td class="formDeLabel"><bean:message key="prompt.juvenileName" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.juvenileName"/></td>
                <td class="formDeLabel"><bean:message key="prompt.docketType" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.docketType"/></td>
              </tr>
              <tr>
                <td class="formDeLabel"><bean:message key="prompt.dateTime" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.eventDateWithTime"  formatKey="datetime.format.mmddyyyyhhmmAMPM"  /></td>
                <logic:equal name="calendarEventListForm" property="currentDocketEvent.docketType" value="COURT"> 
	                <td class="formDeLabel"><bean:message key="prompt.petition" />&nbsp;<bean:message key="prompt.allegation" /></td>
	                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.petitionAllegationDesc"/></td>
                </logic:equal>
                <logic:notEqual name="calendarEventListForm" property="currentDocketEvent.docketType" value="COURT">
            		<td class="formDe"></td>
            		<td class="formDe"></td>
            	</logic:notEqual>
              </tr>
              <tr>
                <td class="formDeLabel"><bean:message key="prompt.courtResult" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.courtResultDesc"/></td>
                <logic:equal name="calendarEventListForm" property="currentDocketEvent.docketType" value="COURT">
	                <td class="formDeLabel"><bean:message key="prompt.courtDisposition" /></td>
	                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.dispositionDesc"/></td>
                </logic:equal>
                <logic:notEqual name="calendarEventListForm" property="currentDocketEvent.docketType" value="COURT">
            		<td class="formDe"></td>
            		<td class="formDe"></td>
            	</logic:notEqual>
              </tr>
              <tr>
                <td class="formDeLabel"><bean:message key="prompt.attorneyName" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.attorneyName"/></td>
                <td class="formDeLabel"><bean:message key="prompt.hearingType" /></td>
                <td class="formDe"><bean:write name="calendarEventListForm" property="currentDocketEvent.hearingTypeDesc"/></td>
              </tr>
            </table>
          </td>
        </tr>

      </table>
      <!-- END Dockets TABLE -->

      <!-- BEGIN BUTTON TABLE -->
      <div class='spacer'></div>
      <table width="100%">  
        <tr>
          <td align="center">
            <input type="button" value="Close" onClick="javascript:window.close();">
          </td>
        </tr>
      </table>
      <!-- END BUTTON TABLE -->
    </td>
  </tr>
</table>


</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

