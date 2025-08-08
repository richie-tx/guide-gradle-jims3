<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />

<html:base />
<title><bean:message key="title.heading"/> - administerCaseloadCSCD/assessments/conditions.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<%-- Begin Pagination Header Tag--%>
<!-- CUSTOMIZED PAGINATION DUE TO SMALL WINDOW SIZE: 2 result per page-->
<bean:define id="paginationResultsPerPage" type="java.lang.String">2</bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<body topmargin="0" leftmargin="0">
<div align="center">
	<div class="spacer"></div>
	
	<%-- BEGIN ERROR TABLE --%>
	<table width="98%" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>								
	<!-- END ERROR TABLE -->
									
	<table width="98%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
		<tr class="detailHead">
			<td>Conditions List</td>
		</tr>
		<tr>
			<td valign="top" colspan="2">
				<table width="100%" cellpadding="4" cellspacing="1">
					<logic:iterate id="eachCase" name="supervisionPlanForm" property="conditionsMap" >
						<%-- Begin Pagination item wrap --%>
						<pg:item>
							<tr>
								<td class="formDeLabel" colspan="2">Case#: <bean:write name="eachCase" property="key" /> - Conditions</td>
							</tr>
							<% String bgcolor=""; %>
							<logic:iterate id="eachCondition" name="eachCase" property="value" indexId="conditionCount">
								<tr class=
									<%bgcolor = "alternateRow";
									if ((conditionCount.intValue()+1) % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);%>>
									<td class="boldText"><%= conditionCount.intValue()+1 %></td>
									<td><bean:write name="eachCondition" filter="false" /></td>
								</tr>
							</logic:iterate>
						</pg:item>	
						<%-- End Pagination item wrap --%>
					</logic:iterate>
				</table>
			</td>
		</tr>
	</table>
			
	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
		<td>
			<pg:index>
				<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch"/>
					<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
				</tiles:insert>
			</pg:index>
		</td>
		</tr>
	</table>
	<%-- End Pagination navigation Row--%>
		
	<br>
	<%-- this should only be true when jsp is accessed from spnSplit popop for supervisionPlan, View Conditions link  --%>
	<logic:equal name="supervisionPlanForm" property="secondaryAction" value="spnSplit">
		<input type="button" value="Back" onclick="history.go(-1)">&nbsp;
	</logic:equal>
	<input type="button" value="Close Window" onclick="window.close()">
	</div>
	
</pg:pager>
<%-- End Pagination Closing Tag --%>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>