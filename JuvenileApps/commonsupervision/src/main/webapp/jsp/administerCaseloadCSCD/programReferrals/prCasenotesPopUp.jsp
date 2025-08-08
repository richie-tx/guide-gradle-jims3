<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/04/2008 Debbie Williamson - Converted PT to JSP -->
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prCasenotesPopUp.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
	<%-- Begin Pagination Header Tag--%>
	<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
	    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
	  <input type="hidden" name="pager.offset" value="<%= offset %>">
	<%-- End Pagination header stuff --%>

	<div class="spacer4px"></div>
	<div align="center" class="header"><bean:message key="title.programReferral"/>&nbsp;<bean:message key="prompt.casenotes"/></div>
	<div align="center">
		<table width="98%" border="0" cellpadding="2" cellspacing="1" >
			<logic:iterate id="prCasenote" name="cscProgRefForm" property="prCasenotesList">											
				<!-- Begin Pagination item wrap -->
				<pg:item>
					<tr class="detailHead">
						<td class="detailHead"><bean:message key="prompt.date"/></td>
						<td class="detailHead"><bean:message key="prompt.subject"/></td>
						<td class="detailHead"><bean:message key="prompt.type"/></td>
						<td class="detailHead"><bean:message key="prompt.contactMethod"/></td> 
						<td class="detailHead"><bean:message key="prompt.createdBy"/></td>
						<td class="detailHead"><bean:message key="prompt.createDate"/></td>
					</tr>
					<tr class="formDe">
						<td class="formDe">
							<bean:write name="prCasenote" property="casenoteDate" formatKey="date.format.mmddyyyy" />
						</td>
						<td class="formDe"><bean:write name="prCasenote" property="subjects"/></td>
						<td class="formDe"><bean:write name="prCasenote" property="casenoteType"/></td>
						<td class="formDe"><bean:write name="prCasenote" property="contactMethod"/></td>
						<td class="formDe"><bean:write name="prCasenote" property="createdByName.formattedName"/></td>
						<td class="formDe"><bean:write name="prCasenote" property="createDate" formatKey="date.format.mmddyyyy"/></td>
					</tr>
					<tr>
						<td class="borderTableBlue" colspan="6">
							<bean:write name="prCasenote" property="casenoteText" filter="false" />
						</td>
					</tr>
					<tr>
						<td colspan="6"><img src=../../../common/images/spacer.gif height="5"></td>
					</tr>
				</pg:item>
				<!-- End Pagination item wrap -->
			</logic:iterate>
		</table>
	</div>
		
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

		<!-- begin BUTTON TABLE -->
		<table width="100%" cellpadding="2" cellspacing="1" border="0">
			<tr>
				<td align="center">
					<input type="button" value="Close Window" onclick="window.close()">
				</td>
			</tr>
		</table>
		<!-- End Button Table -->

</pg:pager>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
