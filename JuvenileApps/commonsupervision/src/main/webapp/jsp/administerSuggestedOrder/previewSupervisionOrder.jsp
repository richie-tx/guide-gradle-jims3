<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/26/2005	 HRodriguez - Create JSP -->
<!-- 05/15/2009	 LDeen		- #59503 remove S and NS -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerSuggestedOrder/previewSupervisionOrder.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<br>
<table border="0" align="center" width="98%" cellspacing="1" cellpadding="6" class="borderTableBlue">
	<%int RecordCounter = 0;
	String bgcolor = "";%>
	<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="previewConditionSelectedList">  
	<tr
		class=<%RecordCounter++;
		bgcolor = "alternateRow";
		if (RecordCounter % 2 == 1)
			bgcolor = "normalRow";
		out.print(bgcolor);%>>
																
		<td class="boldText" width="1%"><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
		<td><bean:write name="conditionSelectedListIndex" property="conditionLiteralPreview" filter="false"/></td>																																									
	</tr>	
	</logic:iterate>																						
</table>
<br>
														
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  	<tr>
    	<td align="center">	      		
      		<input type="button" value="<bean:message key='button.close'/>" name="close" onClick="javascript:window.close()">
    	</td>
  	</tr>
</table>
<!-- END BUTTON TABLE -->
<br>					

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
