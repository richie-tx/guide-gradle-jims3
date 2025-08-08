<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 11/07/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- supervisionRuleTile.jsp<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

	<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue"> 
	  <tr> 
		<td class=detailHead>Rule Details</td> 
	  </tr> 
	  <tr> 
		<td> <table width='100%' border="0" cellpadding="4" cellspacing="1"> 
			<tr> 
			  <td valign=top class=formDeLabel width='1%' nowrap>Entry Date</td> 
			  <td valign=top class=formDe><bean:write name="supervisionRulesForm" property="selectedRule.entryDate" formatKey="date.format.mmddyyyy"/></td> 
			  <td valign=top class=formDeLabel width='1%' nowrap>Rule ID </td> 
			  <td valign=top class=formDe><bean:write name="supervisionRulesForm" property="selectedRule.ruleId"/></td> 
			</tr> 
			<tr> 
			  <td class=formDeLabel nowrap>Monitor Frequency </td> 
			  <td class=formDe><bean:write name="supervisionRulesForm" property="selectedRule.monitorFreq"/></td> 
			  <td valign=top class=formDeLabel width='1%' nowrap>Rule Type</td> 
			  <td valign=top class=formDe><bean:write name="supervisionRulesForm" property="selectedRule.ruleTypeDesc"/></td> 
			</tr> 
			<tr> 
			  <td class=formDeLabel nowrap>Completion Date</td> 
			  <td class=formDe><bean:write name="supervisionRulesForm" property="selectedRule.completionDate" formatKey="date.format.mmddyyyy"/></td> 
			  <td nowrap class="formDeLabel">Completion Status</td> 
			  <td class="formDe"><bean:write name="supervisionRulesForm" property="selectedRule.completionStatus"/></td> 
			</tr> 
			<tr> 
			  <td valign=top class=formDeLabel nowrap colspan=4>Rule Literal</td> 
			</tr> 
			<tr> 
			  <td valign=top class=formDe colspan=4><bean:write name="supervisionRulesForm" property="selectedRule.ruleLiteral"/></td> 
			</tr> 
			<tr> 
			  <td valign=top class=formDeLabel nowrap>Additional Information</td> 
			  <td valign=top class=formDe colspan="3"><bean:write name="supervisionRulesForm" property="selectedRule.additionalInformation"/></td> 
			</tr> 
		  </table></td> 
	  </tr> 
	</table> 
	<br> 
	<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
	  <tr> 
		<td class=detailHead>Associated Goals</td> 
	  </tr> 
	  <tr> 
		<td> <table width='100%' border="0" cellpadding="4" cellspacing="1"> 
			<tr> 
			  <td nowrap class="formDeLabel">Goal #</td> 
			  <td class="formDeLabel" colspan="3">Goal</td> 
			</tr> 
			
			<tr bgcolor=#ffffff> 
			  <td>1213</td> 
			  <td colspan="3">Goal is ...</td> 
			</tr> 
			<tr bgcolor=#f0f0f0> 
			  <td>501212</td> 
			  <td colspan="3">Goal is ...</td> 
			</tr> 
			<tr bgcolor=#ffffff> 
			  <td>54533</td> 
			  <td colspan="3">Goal is ...</td> 
			</tr> 
		  </table></td> 
	  </tr> 
	</table> 		
	
	<img src='../../common/images/spacer.gif' width=5>
	<table border=0 width='98%'>
		<tr>
			<td align=center>
				<input type=button name=close value=Close onclick="javascript:window.close()">
			</td>
		</tr>	
	</table>
