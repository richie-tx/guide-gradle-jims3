<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 11/10/2005	Leslie Deen		Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/06/2015 RYoung          #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - ruleList.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Rule List</td>
		</tr>
</table>
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
		<tr>
			<td class="bodyText">
				<ul>
					<li>Click Rules Category hyperlink to view Rule Details.</li>
					<li>Select Assign New Rules button to assign a new rule (See note below).</li>
				</ul>
			</td>
		</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN CASEFILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader"/>
</tiles:insert> 
<%-- END CASEFILE HEADER TABLE --%>

<html:form action="/displayCasefileSupervisionConditions" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|213">

<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign='top'> <%--tabs start--%> 
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="rulestab" />
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert> <%--tabs end--%>
					</td>
				</tr>
				<tr>
			  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
				</tr>
			</table>
			<%--end of blue tabs--%> 

			<%-- BEGIN DETAIL TABLE --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">

						<%-- BEGIN RULES TABLE --%>
						<div class='spacer'></div>
						<logic:iterate id="casefile" name="juvenileProfileSupervisionRulesForm" property="casefiles" type="ui.juvenilecase.Casefile">
							<%-- Begin Pagination item wrap --%>
						  <pg:item>
								<tiles:insert page="../caseworkCommon/ruleListTile.jsp" flush="false">
									<tiles:put name="title" value='<%= "Supervision #" + casefile.getSupervisionNum() + " - Rules" %>' />
									<tiles:put name="rules" beanName="casefile" beanProperty="rules" />
									<tiles:put name="detailAction" value="displayJuvenileProfileRuleDetail" />
									<tiles:put name="rulesForm" beanName="supervisionRulesForm" />
									<tiles:put name="from" value="profile" />
								</tiles:insert>	
							</pg:item>
					   <%-- End Pagination item wrap --%>	
							<div class='spacer'></div>
						</logic:iterate>
						<%-- END RULES TABLE --%> 

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
</html:form>
					<%-- End Pagination navigation Row--%>

					<%-- BEGIN BUTTON TABLE --%>
					<table border="0" align="center">
						<tr>
							<td align="center">
								<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
									<bean:message key="button.back"></bean:message>
								</html:button>&nbsp;
							</td>
							
							<html:form action="/displayJuvenileMasterInformation.do">
								<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
						  </html:form>
						</tr>
					</table>
					<%-- END BUTTON TABLE --%>
					
					</td>
				</tr>
			</table>
			<%-- END DETAIL TABLE --%></td>
		</tr>
	</table>
	<%-- Begin Pagination Closing Tag --%>
</pg:pager>


<%-- End Pagination Closing Tag --%>
<%-- END BLUE TABS TABLE --%>
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>