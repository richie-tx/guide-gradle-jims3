<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - csCalendar - conditions.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
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
	<table width="98%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
		<tr class="detailHead">
			<td>Conditions List</td>
		</tr>
		<tr>
			<td valign=top colspan=2>
				<table width=100% border=0 cellpadding=4 cellspacing=1>
				

					
					<logic:iterate id="caseConditionIter" name="csCalendarDisplayForm" property="conditions.caseConditions">
						<%-- Begin Pagination item wrap --%>
						<pg:item>
							<tr>
								<td class="formDeLabel" colspan=2>Case#: <bean:write name="caseConditionIter" property="key"/> - Conditions</td>
							</tr>
							<%String bgcolor = "";%>
							<logic:iterate indexId="conditionCount" id="conditionIter" name="caseConditionIter" property="value"> 
								<tr class=
									<%bgcolor = "alternateRow";
									if ((conditionCount.intValue()+1) % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);%>>
									<td class="boldText"><%=conditionCount.intValue()+1%></td>
									<td><bean:write name="conditionIter" filter="false"/></td>
								</tr>
							</logic:iterate>	
						</pg:item>
						<%-- End Pagination item wrap --%>
					</logic:iterate>
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
			</td>
		</tr>
	</table>
	
<br>
<input type=button value="Close Window" onclick="window.close()">
</div>
<br>

</pg:pager>
<%-- End Pagination Closing Tag --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>