<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="naming.Features" %>
<%@ page import="ui.common.UIUtil" %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title><bean:message key="title.heading" />-updateSsn.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<%-- <script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script> --%>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/referral/updateSsnJuvenile.js"></script>
<script>
	$(document).ready(function(){
		$("#SSN1").val('<bean:write name="juvenileReferralForm" property="SSN.SSN1"/>');
		$("#SSN2").val('<bean:write name="juvenileReferralForm" property="SSN.SSN2"/>');
		$("#SSN3").val('<bean:write name="juvenileReferralForm" property="SSN.SSN3"/>');
	})
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Referrals - Update Juvenile - SSN Update</td>
	</tr>
</table>
<table width="98%" border="0">
	<tr>
		<td class="required">
			<ul>
				<li>Enter required fields then click Next</li>
					
			</ul>
		</td>
	</tr>
</table>
<html:form action="/submitUpdateJuvenileSsn" target="content">
<table cellpadding='1' cellspacing='1' border='0' width="99%" class="borderTableBlue" align="center">

	<tr height='30px' class='referralDetailHead'>
		<td  colspan="6">Update Juvenile - SSN Update</td>
	</tr>
	<tr>
		<td class='formDeLabel' nowrap='nowrap' width="1%" valign="top" width="40%" >&#x2BC1<bean:message key="prompt.ssn" /></td>
			<td class='formDe' valign="top" colspan='4'>
				<html:text styleId="SSN1"  property="SSN1"  size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>- 
				<html:text styleId="SSN2" property="SSN2"  size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>- 
				<html:text styleId="SSN3" property="SSN3"  size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> &nbsp;
			</td>
	</tr>
</table>
&nbsp;
<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="backBtn"><bean:message key="button.back" /></html:submit>
				<html:submit property="submitAction" styleId="nextBtn"> <bean:message key="button.next" /></html:submit>&nbsp;
				<html:submit property="submitAction" styleId="cancelBtn"><bean:message key="button.cancel" /></html:submit>
				
			</td>
		</tr>
	</table>

</html:form>
</body>
</html>