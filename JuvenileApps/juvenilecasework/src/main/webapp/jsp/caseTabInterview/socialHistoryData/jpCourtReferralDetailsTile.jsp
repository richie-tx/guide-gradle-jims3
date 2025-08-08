<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 12/07/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>




<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- JPCourtReferralDetailsTile.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>

	<logic:iterate id="referralIter" name="socialHistoryForm" property="jpCourtReferrals">
		<table border="0" cellpadding="2" cellspacing="0" width='98%'
			class="borderTableBlue" align=center>
			<tr>
				<td class=detailHead>Justice of Peace Court Referrals</td>
			</tr>
			<tr>
				<td valign=top align=center>
					<table cellpadding="4" cellspacing="1" width='100%' border=0>
						<tr>
							<td class=formDeLabel width='1%' nowrap>Case #</td>
							<td class=formDe width='50%'><bean:write name="referralIter" property="caseNumber"/></td>
							<td class=formDeLabel width='1%' nowrap>Conviction Date</td>
							<td class=formDe width='50%'><bean:write name="referralIter" property="convictionDate" formatKey="date.format.mmddyyyy"/></td>
						</tr>
						<tr>
							<td class=formDeLabel nowrap>Offense #</td>
							<td class=formDe><bean:write name="referralIter" property="offenseId"/></td>
							<td class=formDeLabel nowrap>File Date</td>
							<td class=formDe><bean:write name="referralIter" property="fileDate" formatKey="date.format.mmddyyyy"/></td>
						</tr>
						<tr>
							<td class=formDeLabel nowrap>Court</td>
							<td class=formDe colspan=3><bean:write name="referralIter" property="courtName"/></td>
						</tr>
						<tr>
							<td class=formDeLabel nowrap>Offense</td>
							<td class=formDe colspan=3><bean:write name="referralIter" property="offenseDescription"/></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>
	</logic:iterate>
	
	<img src='/<msp:webapp/>images/spacer.gif' width=5>
	<table border=0 width='98%'>
		<tr>
			<td align=center>
				<input type=button name=close value=Close onclick="javascript:window.close()">
			</td>
		</tr>	
	</table>
				
</body>
</html:html>