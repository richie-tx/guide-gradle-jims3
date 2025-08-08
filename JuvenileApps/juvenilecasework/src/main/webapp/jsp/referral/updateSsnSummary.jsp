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
<title><bean:message key="title.heading" />-updateSsnSummary.jsp</title>
<%@include file="../jQuery.fw"%>
<script>
	$(document).ready(function(){
		$("#finishBtn").click(function(){
			$("#S1").val('<bean:write name="juvenileReferralForm" property="SSN1" />');
			$("#S2").val('<bean:write name="juvenileReferralForm" property="SSN2" />');
			$("#S3").val('<bean:write name="juvenileReferralForm" property="SSN3" />');
		})
	})
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<table width='100%'>
	<tr>
		<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSsnNext">
			<td align="center" class="header">Process Referrals - Update Juvenile - SSN Update Summary</td>
		</logic:equal>
		<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSsnFinish">
			<td align="center" class="header">Process Referrals - Update Juvenile - SSN Update Confirmation</td
		</logic:equal>
	</tr>
</table>
<logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages>
					</td>		  
				</tr>   	  
			</table>
</logic:messagesPresent>
<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<table width="98%" border="0">
	<tr>
		<td class="required">
			<ul>
				<li>Review information and select the Finish button.</li>
				<li>Select the Back button to change information. </li>
					
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
		<td class='formDeLabel' nowrap='nowrap' width="1%" valign="top" width="40%" ><bean:message key="prompt.ssn" /></td>
			<td class='formDe' valign="top" colspan='4'>
				<bean:write name="juvenileReferralForm" property="SSN1" />-
				<bean:write name="juvenileReferralForm" property="SSN2" />-
				<bean:write name="juvenileReferralForm" property="SSN3" />
			</td>
	</tr>
</table>
&nbsp;
<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSsnNext">
	<table width="98%" border="0" align="center">
			<tr>
				<td align="center">
					<html:submit property="submitAction" styleId="backBtn"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction" styleId="finishBtn"> <bean:message key="button.finish" /></html:submit>&nbsp;
					<html:submit property="submitAction" styleId="cancelBtn"><bean:message key="button.cancel" /></html:submit>
					
				</td>
			</tr>
	</table>
</logic:equal>
<html:hidden styleId="S1"  property="SSN.SSN1"  />
<html:hidden styleId="S2"  property="SSN.SSN2"  />
<html:hidden styleId="S3"  property="SSN.SSN3"  />
</html:form>
<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSsnFinish">
<html:form action="/processReferralBriefing.do?submitAction=Update Juvenile Record">
<table width="98%" border="0" align="center">
			<tr>
				<td align="center">
					<html:submit property="submitAction">Return to Process Referrals - Update Juvenile</html:submit>
				</td>
			</tr>
	</table>
	
</html:form>
</logic:equal>
</body>
</html>