<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/moveCommonAppReportQueryResult</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	.command{
		margin-top: 10px;
	}
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>
	$(document).ready(function(){
		$("#mainMenuBtn").click(function(){
			$('form').attr("action","DisplayProductionSupportMainPopup.do");
			$('form').submit();
		})
		
		$("#moveBtn").click(function(){
			clearAllValArrays();
			customValRequired ("newcasefileId","New Casefile Id is required.","");
			customValMinLength("newcasefileId","New Casefile Id must be 8 digits.","8");
			addNumericValidation("newcasefileId","<bean:message key='errors.numeric' arg0='Casefile ID'/>", "");
			var status = validateCustomStrutsBasedJS(this.form);
			if ( status ) {
				 spinner();
				 $('form').attr("action","PerformMoveCommonAppReport.do");
				 $('form').submit();
			 }
			
		})
	})
</script>
</head>
<body>
<html:form styleId="moveCommonAppReportForm" action="/PerformMoveCommonAppReport">
<div>
	<h2 align="center">Results for Casefile ID = 
			<bean:write name="ProdSupportForm" property="casefileId" />
	</h2>

    <logic:notEqual name="ProdSupportForm" property="msg" value="">
		<table border="0" width="700" align="center">
			<tr align="center">
					<td colspan="4">
						<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
							<bean:write name="ProdSupportForm" property="msg" />
			 			</font>
			 		</td>
			</tr>
		</table>
	</logic:notEqual>

	<p align="center">
		<b><i>Enter the new Casefile_ID to move the associated record for the Common App.</i></b>
	</p>
	<table class="resultTbl" border="1" width="800" align="center">
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMONAPPDOC_ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="commonAppDocument.reportId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileId" />&nbsp;</font></td>
			<td><i>New Value:</i>&nbsp;<html:text styleId="newCasefileId" name="ProdSupportForm" property="newcasefileId" size="10" maxlength="10" /></td>
		</tr>
	</table>	
</div>
<div class="command">
	<input id="mainMenuBtn" type="button" value="Back to Main Menu" />
	<input id="moveBtn" type="button" value="Move Common App Report" />
</div>
</html:form>
</body>