<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/substanceAbuseDetail.jsp"</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function () {

		$("#updateRecordBtn").click(function(){
			spinner();
		})
		
	
		
	})
	
</script>

</head>
<body>
<html:form action="/PerformUpdateSubstanceAbuse" onsubmit="return this;">
<h2 align="center">Substance Abuse Detail for Substance Abuse ID <bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.substanceAbuseId"/> </h2>
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
<table  class="resultTbl" border="1" width="1000" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Abuse ID</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.substanceAbuseId"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile ID</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.juvenileId"/></td>
		<jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
			<td><html:text name="ProdSupportForm" property="substanceAbuseInfo.juvenileId"></html:text></td>
		</jims2:isAllowed>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.casefileId"/></td>
		<td>
			<html:select styleId="casefileId" name="ProdSupportForm" property="substanceAbuseInfo.casefileId">
				<html:option key="select.generic" value="" />
				<html:options name="ProdSupportForm" property="substanceAbuseInfo.casefileIds"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.referralNum"/></td>
		<td>
			<html:select styleId="referralNum" name="ProdSupportForm" property="substanceAbuseInfo.referralNum">
				<html:option key="select.generic" value="" />
				<html:options name="ProdSupportForm" property="substanceAbuseInfo.referrals"/>
			</html:select>
		</td>
		
		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SAbuse</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.sAbuse"/></td>
		<td>
			<html:select styleId="substanceAbuse" name="ProdSupportForm" property="substanceAbuseInfo.sAbuseCode">
				<html:option key="select.generic" value="" />
				<html:optionsCollection name="ProdSupportForm" property="tjjdSubstanceAbuseCodes" value="code" label="description"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Type</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.substanceType"/></td>
		<td>
			<html:select styleId="substancesType" name="ProdSupportForm" property="substanceAbuseInfo.substanceTypeCode" multiple = "true">
				<html:option key="select.generic" value="" />
				<html:optionsCollection name="ProdSupportForm" property="drugTypeCodes" value="code" label="description"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Treatment Location</font></td>
		<td><bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.treatmentLocation"/></td>
		<td>
			<html:text name="ProdSupportForm" property="substanceAbuseInfo.treatmentLocation"></html:text>
		</td>
		
		
	</tr>
</table>

<table align="center"">
	<tr>
	<td>
		<p align="center">
		<html:submit property="submitAction" styleId="updateRecordBtn"><bean:message key="button.updateRecord"/></html:submit>
		</p>
	</td>
	<td>
		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
	</td>
	</tr>

</table>
</html:form>

<html:form action="/UpdateSubstanceAbuseQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>