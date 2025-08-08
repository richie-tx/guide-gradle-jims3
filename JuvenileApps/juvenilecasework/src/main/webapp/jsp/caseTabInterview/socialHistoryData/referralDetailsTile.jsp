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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- referralDetailsTile.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>
	
<table border="0" cellpadding="2" cellspacing="0" width='98%' class="borderTableBlue" align=center>
  <tr>
  	<td class=detailHead>Referral Details</td>
  </tr>
  <tr>
  	<td valign=top align=center>
  		<table cellpadding="4" cellspacing="1" width='100%' border=0>
  			<tr>
  				<td class=formDeLabel width='1%' nowrap>Court ID</td>
  				<td class=formDe width=50%><bean:write name="assignedReferralsForm" property="courtId"/></td>
  				<td class=formDeLabel width='1%' nowrap>Court Date</td>
  				<td class=formDe width=50%><bean:write name="assignedReferralsForm" property="courtDate"/></td>
  			</tr>
  			<tr>
  				<td class=formDeLabel nowrap>Court Result</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="courtResultDesc"/></td>
  				<td class=formDeLabel nowrap>Court Disposition</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="courtDispositionDesc"/></td>
  			</tr>
  			<tr>
  				<td class=formDeLabel nowrap>Intake Date</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="intakeDate"/></td>
  				<td class=formDeLabel nowrap>Intake Decision</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="intakeDecision"/></td>
  			</tr>
  			<tr>
  				<td class=formDeLabel nowrap>Close Date</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="closeDate"/></td>
  				<td class=formDeLabel nowrap>Transaction #</td>
  				<td class=formDe><bean:write name="assignedReferralsForm" property="transactionNum"/></td>
  			</tr>
  		</table>
  	</td>
  </tr>
</table>

<%--offenses start--%>
<br>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align=center>
  <tr>
  	<td class=detailHead>Offenses</td>
  </tr>
  <tr>
  	<td>
  	<table cellpadding=2 cellspacing=1 width='100%' border="0">
  		<tr bgcolor=#cccccc>
  			<td class=subHead align="left">Offense Date</td>
  			<td class=subHead align="left">Offense Level</td>
  			<td class=subHead align="left">Citation Source</td>
  			<td class=subHead align="left">Citation </td>
  			<td class=subHead align="left">Description</td>
  		</tr>
  		<logic:notEmpty name="assignedReferralsForm" property="offenses">
  		<logic:iterate id="offenses" name="assignedReferralsForm" property="offenses"> 								
  		<tr class=normalRow>
  			<td valign=top>
  				<bean:write name="offenses" property="offenseDateFormatted"/>
  			</td>
  			<td valign=top>
  				<bean:write name="offenses" property="catagory"/>
  			</td>	
  			<td valign=top>
  				<bean:write name="offenses" property="citationSource"/>
  			</td>	
  			<td valign=top>
  				<bean:write name="offenses" property="citationCode"/>
  			</td>									
  			<td valign=top>
  				<bean:write name="offenses" property="offenseDescription"/>
  			</td>	
  		</tr>
  		</logic:iterate>
  		</logic:notEmpty>
  		<logic:empty name="assignedReferralsForm" property="offenses">
  		<tr>
  			<td colspan="2">No offenses exist for this referral.</td>
  		</tr>
  		</logic:empty>								
  	</table>
  	</td>
  </tr>
</table>
<%--offenses end--%> 

<%-- petitions begin --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align=center>
  <tr>
  	<td class=detailHead>Petitions</td>
  </tr>
  <tr>
  	<td>
  	<table width='100%' cellpadding=4 cellspacing=1 border=0>						
  		<tr bgcolor=#cccccc>
  			<td class=formDeLabel align="left">Petition #</td>
  			<td class=formDeLabel>Petition Filed</td>
  			<td class=formDeLabel>Petition Allegation</td>
  		</tr>				
  		<logic:notEmpty name="assignedReferralsForm" property="petitions">			
  		<logic:iterate id="petitions" name="assignedReferralsForm" property="petitions">
  							
  		<tr class=normalRow>
  			<td valign=top>
  				<bean:write name="petitions" property="petitionNum"/>
  			</td>
  			<td valign=top>
  				<bean:write name="petitions" property="petitionDateFormatted"/>
  			</td>									
  			<td valign=top>
  				<bean:write name="petitions" property="offense"/>
  			</td>																		
  		</tr>
  		</logic:iterate>
  		</logic:notEmpty>
  		<logic:empty name="assignedReferralsForm" property="petitions">			
  		<tr class=normalRow>
  			<td colspan="3" valign=top>No petitions exist for this referral.</td>																		
  		</tr>								
  		</logic:empty> 							
  	</table>
  
  	</td>
  </tr>
</table>
<%-- petitions end--%>

<br>

<table border=0 width='98%'>
  <tr>
  	<td align=center>
  		<input type=button name=close value=Close onclick="javascript:window.close()">
  	</td>
  </tr>	
</table>
				
</body>
</html:html>