<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="naming.Features"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />-referralSchoolSearch.jsp</title>

<html:javascript formName="juvenileReferralForm"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/referral/createJuvenile.js"></script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<%--BEGIN FORM TAG--%>
<html:form action="/displayCreateJuvenile" target="content" focus="schoolName">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Create_Juvenile.htm#|5">
	<br/>
	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Referrals - Search for School <script type='text/javascript'>
 				if( location.search == "?results" )document.write( " Results" ) ;
 			</script></td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>

	<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<%-- END ERROR TABLE --%>
		<%-- BEGIN INSTRUCTION TABLE --%>
	<br>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" >
			<ul id='searchInstructions'>
				<li>Enter a school name.</li>
				<li>Select Find button to view search results</li>
			</ul>

			<ul id='resultsInstructions' class='hidden'>
				<li>Select a school radio button, then select the Select button to
				return to the Create School page.</li>
			</ul>
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>
	<%-- BEGIN SEARCH FIELDS TABLE --%>
    	<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue align = "center">
			<tr class=referralDetailHead>
				<td align="center" >School Search</td>
			</tr>
			<tr>
			<!-- begin data entry section -->
				<td align="center">
					<table border=0 cellpadding=2 cellspacing="1" width='100%' align="center">
						<tr align="center">
							<td class=formDeLabel width="1%" nowrap align="center"><bean:message key="prompt.schoolName" /></td>
							<td class=formDe><html:text name='juvenileReferralForm' property='schoolName' size="30" maxlength="30"/></td>
						</tr>
						<tr>
							<td class=formDeLabel></td>
							<td colspan=3 class=formDe>
                            	<html:submit property="submitAction" onclick="spinner();" styleId="findBtn"><bean:message key="button.find" /></html:submit>
                                <html:submit property="submitAction"><bean:message key="button.refresh" /></html:submit>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<logic:empty name="juvenileReferralForm" property="schoolNDistrictList"> 
			<table border="0" width="100%">
				<tr>
					<td align="center">
					  <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit> 
					</td>
				</tr>
			</table>
		</logic:empty>
		<br/><br/>
			<!-- END TABLE --> <!-- BEGIN SCHOOL Search Results TABLE -->
		<logic:notEmpty	name="juvenileReferralForm" property="schoolNDistrictList">
			<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue align="center">
  				<tr class=referralDetailHead>
  					<td valign=top align = "center">School Search Results</td>
  				</tr>
				<tr>
					<td>
					<bean:size id="collSize" name="juvenileReferralForm" property="schoolNDistrictList" />
					<center> <bean:write name="collSize" /> search results found.</center>
					<table width="98%" border="0" align="center">
						<tr>
							<td align="center" >
							<ul id='resultsInstructions'>
								<li>Select a school radio button, then select the Select button to return to the Create School page.</li>
							</ul>
							</td>
						</tr>
					</table>
					<table border=0 width=98% cellspacing=1 cellpadding=2>
						<tr bgcolor=#cccccc>
							<td class=subhead><bean:message key="prompt.schoolName" />
							<jims2:sortResults beanName="juvenileReferralForm" results="schoolNDistrictList" primaryPropSort="schoolDescription" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
							<td class=subhead><bean:message key="prompt.schoolDistrict" />
							<jims2:sortResults beanName="juvenileReferralForm" results="schoolNDistrictList" primaryPropSort="districtDescription" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" /></td>
						</tr>
									<%int RecordCounter = 0;
									String bgcolor = "";
									%>
							<%-- Begin Pagination Header Tag--%>
							<bean:define id="paginationResultsPerPage" type="java.lang.String">
							<bean:message key="pagination.recordsPerPage" />
							</bean:define>
							<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
							<input type="hidden" name="pager.offset" value="<%= offset %>">
						<%-- End Pagination header stuff --%>
							<logic:iterate id="schoolIndex"	name="juvenileReferralForm" property="schoolNDistrictList">
						<%-- Begin Pagination item wrap --%>
							<pg:item>
							<bean:define id="scId" name="schoolIndex" property="oid" type="java.lang.String"/>
							<tr	class=<%RecordCounter++; bgcolor = "alternateRow";
							if (RecordCounter % 2 == 1)
							bgcolor = "normalRow";
							out.print(bgcolor);%>>
								<td>
									<html:radio name="juvenileReferralForm" property="selectedSchoolId" value='<%=scId%>'/>
									<bean:write name='schoolIndex' property='schoolDescription' /></td>
								<td><bean:write name='schoolIndex' property='districtDescription' /></td>
							</tr>
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
								<td><pg:index>
									<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
									<tiles:put name="pagerUniqueName" value="pagerSearch" />
									<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
									</tiles:insert>
									</pg:index></td>
							</tr>
						</table>		
								<%-- End Pagination navigation Row--%>
								<%-- Begin Pagination Header Closing Tag --%>
								</pg:pager>
								<%-- End Pagination Header Closing Tag --%>
						<!-- BEGIN BUTTON TABLE -->		
								<table border="0" width="100%">
								  <tr>
								  	<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"></bean:message>
										</html:submit>
									<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedSchoolId', 'You must select a school to proceed.' );"><bean:message key="button.select"></bean:message>
										</html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
										</html:submit>
								    </td>
								  </tr>
								</table>
						<!-- END BUTTON TABLE -->
							</logic:notEmpty> 
					
	<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
