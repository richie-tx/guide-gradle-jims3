<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title>Juvenile Casework
	-/prodSupport/viewAncillaryCalendarListResult</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/updateAncillaryCourtCalendar.js"></script>

</head>

<body>

	<html:form action="/UpdateAncillaryCalendarQuery" onsubmit="return this;">
		<BR>		
		<div>

			<h2 align="center">
				Results for Updating Petition Number  =
				<bean:write name="ProdSupportForm" property="petitionNum" />
			</h2>
			<BR>
			<!-- Error Message Area -->
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<table border="0" width="700" align="center">

					<tr align="center">
						<td colspan="4"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"> <bean:write
									name="ProdSupportForm" property="msg" /> </font></td>
					</tr>
				</table>
			</logic:notEqual>
			<BR>
			<!-- End Error Message Area -->

			
			
			<logic:notEmpty name="ProdSupportForm" property="ancillaryCalendarRecords">
			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition
							Number</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="petitionNum" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Respondent
							Name</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="respondentName" />&nbsp;</font>
					</td>
				</tr>

			</table>
			<p align="center">
				<b><i>Select the radio button next to the record <br>
						you want to update and click the Submit button.</i> </b>
			</p>
				<h3 align="center">Ancillary Court Details</h3>
				<%-- Begin Pagination Header Tag--%>
				<bean:define id="paginationResultsPerPage" type="java.lang.String">
					<bean:message key="pagination.recordsPerPage"></bean:message>
				</bean:define>

				<pg:pager index="center"
					maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					maxIndexPages="10" export="offset,currentPageNumber=pageNumber"
					scope="request">
					<input type="hidden" name="pager.offset" value="<%=offset%>">
					<%-- End Pagination header stuff --%>
					<table class="resultTbl" border="1" width="800" align="center">

						<tr class="detailHead">
							<td bgcolor="gray"></td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court</font>
								<jims:sortResults beanName="ProdSupportForm"
									results="ancillaryCalendarRecords" primaryPropSort="court"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court
									Date</font> <jims:sortResults beanName="ProdSupportForm"
									results="ancillaryCalendarRecords" primaryPropSort="courtDate"
									primarySortType="DATE" defaultSortOrder="ASC" sortId="2" />
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Chain
									Number</font> <jims:sortResults beanName="ProdSupportForm"
									results="ancillaryCalendarRecords" primaryPropSort="chainNumber"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence
									Number</font> <jims:sortResults beanName="ProdSupportForm"
									results="ancillaryCalendarRecords" primaryPropSort="seqNum"
									primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
						</tr>

						<logic:iterate id="ancillaryCalendarRecord" name="ProdSupportForm"
							property="ancillaryCalendarRecords">
							<pg:item>
								<tr>
									<td width="1%"><input type="radio" name="docketEventId"
										value="<bean:write name='ancillaryCalendarRecord' property='docketEventId'/>" />
									</td>
									<td><font size="-1"><bean:write
												name="ancillaryCalendarRecord" property="court" />&nbsp;</font></td>
									<td><font size="-1"><bean:write
												name="ancillaryCalendarRecord" property="courtDate"
												formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
									<td><font size="-1"><bean:write
												name="ancillaryCalendarRecord" property="chainNumber" />&nbsp;</font>
									</td>
									<td><font size="-1"><bean:write
												name="ancillaryCalendarRecord" property="seqNum" />&nbsp;</font></td>
								</tr>
							</pg:item>
						</logic:iterate>

					</table>
					<%-- Begin Pagination navigation Row--%>
					<table align="center">
						<tr>
							<td><pg:index>
									<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
										<tiles:put name="pagerUniqueName" value="pagerSearch" />
										<tiles:put name="resultsPerPage" value="10" />
									</tiles:insert>
								</pg:index>
							</td>
						</tr>
					</table>
					<%-- End Pagination navigation Row--%>
				</pg:pager>
			</logic:notEmpty>
			<BR>

			<table align="center"">

				<logic:notEmpty name="ProdSupportForm"
					property="ancillaryCalendarRecords">
					<td>
						<p align="center">
						<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="selectRefBtn">
							<bean:message key="button.submit" />
						</html:button>
						</p>
					</td>
				</logic:notEmpty>

			</table>

			
		</div>


		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>
		</table>
		
	</html:form>

	<html:form action="/UpdateAncillaryCalendarQuery?clr=Y"
		onsubmit="return this;">
		<table align="center"">
			<tr>
				<td><html:submit value="Back to Query" />
				</td>
			</tr>
		</table>
	</html:form>


</body>
</html:html>
