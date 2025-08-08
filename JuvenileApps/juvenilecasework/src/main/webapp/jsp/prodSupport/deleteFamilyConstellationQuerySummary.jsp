<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/deleteFamilyConstellationQueryResult</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<style>
	#command div{
		margin-top: 20px;
		display: inline-block;
	}
</style>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

</head>
<body>
	<div id="container">
		<div align="center">
			<h2>Production Support - Family Constellation(s) Deleted</h2>
			<h3>Family Constellation(s) Deleted</h3>
		</div>
		<hr>
		<p align="center"><font color="green"><b>The selected family constellation was successfully deleted.</b></font></p>
		<div>
			<table class="resultTbl" border="1" width="1000" align="center">
				<thead>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Family #</font></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><bean:write name="ProdSupportForm" property="constellationId"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="command">
			<div>
				<html:form method="post" action="/deleteFamilyConstellationQuery.do?clr=Y">
					<input type="submit" value="Delete Another Family Constellation"/>
				</html:form>
			</div>
			<div>
				<html:form method="post" action="/MainMenu">
					<input type="submit" value="Back to Main Menu"/>
				</html:form>
			</div>
		</div>
	</div>
</body>