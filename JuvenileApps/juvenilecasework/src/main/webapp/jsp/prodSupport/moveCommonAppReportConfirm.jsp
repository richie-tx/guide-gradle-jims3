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
<title>Juvenile Casework -/prodSupport/moveCommonAppReportConfirm</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	.command{
		margin-top: 20px;
	}
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
 </head>
 <body>
 <html:form styleId="moveCommonAppReportForm" action="/MainMenu">
 	<div>
 		<h2 align="center">Production Support - Move Common App Report</h2>
 	</div>
 	<div>
 		<logic:notEqual name="ProdSupportForm" property="msg" value="">
			<table border="0" width="700" align="center">
				<tr align="center">
						<td colspan="4">
							<font color="#00e500" size="3" face="Arial"> 
								<bean:write name="ProdSupportForm" property="msg" />
				 			</font>
				 		</td>
				</tr>
			</table>
		</logic:notEqual>
 	</div>
 	<div class="command">
 		<html:submit onclick="return this;" value="Back to Main Menu"/>
 	</div>
 </html:form>
 </body>