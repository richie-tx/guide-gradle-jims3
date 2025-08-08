<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- RCapestani 10/13/2015  Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- programReferralEventDetails.jsp</title>

<html:base />

<%-- Javascript for emulated navigation --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin='0' leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.serviceProvider"/>&nbsp;- <bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.eventDetails"/></td>
  </tr>
</table>
<table width="100%">
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td> 
			<ul>
        <li>Select Close Window button to close this window.</li>
      </ul>
		</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>
    	<%-- BEGIN OUTER TABLE --%>
    
      <table width='100%' border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td valign='top' align='center'> 
					
      			<%-- BEGIN INNER TABLE --%>
			      <div class='spacer'></div>
            <table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              <tr>
                <td class='detailHead' ><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.eventDetails"/></td>
              </tr>
              <tr>
                <td>
             		 <table width='100%' border="0" cellpadding="4" cellspacing="1">
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.serviceProvider"/></td>
            					<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="currentServiceEvent.serviceProviderName"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventDate"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.eventDate" formatKey="date.format.mmddyyyy"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventTime"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.eventDate" formatKey="time.format.hhmma"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventStatus"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.eventStatus"/></td>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.locationUnit"/></td>
            					<td class="formDe" colspan='3'><bean:write name="programReferralForm" property="currentServiceEvent.serviceLocationName"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sessionLength"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.eventSessionLength"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.eventType"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.eventType"/></td>
            				</tr>

            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.minimumAttendance"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.minAttendance"/></td>
            					<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.maximumAttendance"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.maxAttendance"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.totalScheduled"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.currentEnrollment"/></td>
            					<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.instructor"/></td>
            					<td class="formDe"><bean:write name="programReferralForm" property="currentServiceEvent.instructorName"/></td>
            				</tr>
            				<tr>
            					<td class="formDeLabel" nowrap='nowrap' colspan='4'><bean:message key="prompt.comments"/></td>
            				</tr>
            				<tr>
            					<td class="formDe" colspan='4'><bean:write name="programReferralForm" property="currentServiceEvent.eventComments"/></td>
            				</tr>
                  </table>
        				</td>
              </tr>
            </table>

            <div class='spacer'></div> 
            <table border="0" cellpadding='1' cellspacing='1' align='center'>
              <tr>
                <td align="center"><input name="button" type='button' value='Close Window' onClick="javascript:window.close();"></td>
              </tr>
            </table>
						<div class='spacer'></div> 

          </td>
        </tr>
      </table>
  	</td>
  </tr>
</table>
  <%-- END DETAIL TABLE --%>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

