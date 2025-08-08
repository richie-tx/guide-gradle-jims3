<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson		Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

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
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<title><bean:message key="title.heading" /> - guardianPhoneList.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitWorkshopCancellation" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Guardian Telephone List</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<br>
<table align="center" width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">  
	<tr>
		<td class="detailHead" colspan="2">Guardian List</td>
	</tr>
		<logic:empty name="calendarEventDetailsForm" property="currentCancellationEvent.guardianResponseEvents">
			<tr>
				<td class="formDeLabel" width='1%' nowrap="nowrap">There are no Guardians for this Juvenile</td>
			</tr>			
		</logic:empty>
		
		<logic:notEmpty name="calendarEventDetailsForm" property="currentCancellationEvent.guardianResponseEvents">

			<logic:iterate indexId="recordCounter" id="personsIter" name="calendarEventDetailsForm" property="currentCancellationEvent.guardianResponseEvents">

				  <tr>
				    <td class="formDeLabel" width='1%' nowrap="nowrap">Name</td>
				    <td class="formDe" nowrap="nowrap"><bean:write name="personsIter" property="lastName"/>,<bean:write name="personsIter" property="firstName"/></td>
				  </tr>
				  <tr>
				    <td class="formDeLabel" width='1%' nowrap="nowrap">Home Phone</td>
				    <td class="formDe" nowrap="nowrap"><bean:write name="personsIter" property="homePhoneNumber"/></td>
				  </tr>
				  <tr>
				    <td class="formDeLabel" width='1%' nowrap="nowrap">Office Phone</td>
				    <td class="formDe" nowrap="nowrap"><bean:write name="personsIter" property="workPhoneNumber"/></td>
				  </tr>
				  <tr>
				    <td class="formDeLabel" width='1%' nowrap="nowrap">Mobile Phone</td>
				    <td class="formDe" nowrap="nowrap"><bean:write name="personsIter" property="mobilePhoneNumber"/></td>
				  </tr>
					
				  <tr><td><img src="../images/spacer.gif" width="5"></td></tr>
				  <br>
			</logic:iterate>	
		</logic:notEmpty>

  <tr><td><img src="../images/spacer.gif" width="5"></td></tr>
  <tr>
    <td colspan='2' align='center'>
      <input type='button' value='Close Window'  onclick="javascript:window.close();">
    </td>
  </tr>
  
</table>
<%-- END INSTRUCTION TABLE --%>


</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>