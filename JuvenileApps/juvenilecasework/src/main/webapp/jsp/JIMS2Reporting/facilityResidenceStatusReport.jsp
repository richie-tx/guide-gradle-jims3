<!DOCTYPE HTML>

<%-- Used for searching of juvenile JIMS reports in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 10/28/2013	CShimek  	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - facilityResidenceStatusReport.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function findInfo()
{
	var forwardURL = "/<msp:webapp/>displayFacilityResidenceStatusReport.do?submitAction=Submit";	
	document.forms[0].action = forwardURL;
	document.forms[0].submit();	
}

function checkPager()
{
	
	var theForm = document.forms[0];	
	var dt = document.getElementsByName('pager.offset')[0].value;
	<logic:equal name="reset" value="yes">		
		document.getElementsByName('pager.offset')[0].value = 0;		
	</logic:equal>
}
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onLoad="checkPager();">
<%--BEGIN FORM TAG--%>
<html:form action="/displayFacilityResidenceStatusReport" target="content" focus="facilityId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">
	    <bean:message key="title.juvenileCasework"/> - 
	    <bean:message key="prompt.facility"/>/<bean:message key="prompt.resident"/> <bean:message key="prompt.status"/> <bean:message key="prompt.report"/>
    </td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Print button to print the generated report.</li>
				<li>Select Cancel button to return to search page.</li>				
			</ul>	
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%-- BEGIN SEARCH FOR TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
	<tr >
		<td align="center">
			<bean:message key="prompt.2.diamond"/> <b><bean:message key="prompt.facility" /></b>:
			<html:select name="juvenilePopulationForm" property="facilityId"  onchange="findInfo()">
				<html:optionsCollection name="juvenilePopulationForm" property="facilities" value="code" label="descriptionWithCode" />
			</html:select>
		</td>
	</tr>
</table>
<%-- END SEARCH FOR TABLE  --%>

	<%-- Begin Pagination Header Tag--%>
			<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
			
			<pg:pager
				 index="center"
				 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
				 maxIndexPages="10"
				 export="offset,currentPageNumber=pageNumber"
				 scope="request">
				 <input type="hidden" name="pager.offset" value="<%= offset %>">
			<%-- End Pagination header stuff --%>
		<%-- BEGIN INFORMATION TABLE --%>
			<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
				<tr>
					<td class="detailHead"><bean:message key="prompt.juvenile#" />
					<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="juvNum" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
					<td class="detailHead"><bean:message key="prompt.referral" />#</td>
					<td class="detailHead"><bean:message key="prompt.juvenileName" /></td>
					<td class="detailHead"><bean:message key="prompt.rsAge" /></td>
					<td class="detailHead"><bean:message key="prompt.location" />
					<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="floorNum" primarySortType="STRING" secondPropSort="unit" secondarySortType="STRING" finalPropSort="roomNum" finalSortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" /></td>
					<td class="detailHead"><bean:message key="prompt.date" /> <bean:message key="prompt.in" />
						<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="admitDate" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="3" /></td></td>
					<td class="detailHead"><bean:message key="prompt.time" /> <bean:message key="prompt.in" />
						<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="admitTime" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="4" /></td></td></td>
					<td class="detailHead"><bean:message key="prompt.reason" /></td>
				</tr>
				<logic:iterate id="tots" name="juvenilePopulationForm" property="residentsStatusList">
				<%-- Begin Pagination item wrap --%>
				<pg:item>
					<tr>
						<td align="left"><bean:write name="tots" property="juvNum"/></td>
						<td align="left"><bean:write name="tots" property="referralNumber"/></td>
						<td align="left"><bean:write name="tots" property="juvName"/></td>
						<td align="left"><bean:write name="tots" property="juvRace"/>&nbsp;<bean:write name="tots" property="juvSex"/>&nbsp;<bean:write name="tots" property="age"/></td>
						<td align="left"><bean:write name="tots" property="floorNum"/>&nbsp;<bean:write name="tots" property="unit"/>&nbsp;<bean:write name="tots" property="roomNum"/>
						<logic:notEqual name="tots" property="multipleOccupyUnit" value="">- <bean:write name="tots" property="multipleOccupyUnit"/></logic:notEqual></td>
						<td align="left"><bean:write name="tots" property="admitDate" formatKey="date.format.mmddyyyy"/></td>
						<td align="left"><bean:write name="tots" property="admitTime"/></td>
						<td align="left">	<span title='<bean:write name="tots" property="reasonDescription"/>'>
							<bean:write name="tots" property="admitReason"/>-<bean:write name="tots" property="secureStatus"/>
							</span>
						</td>		
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<logic:equal name="tots" property="facilityStatus" value="E">					
							<td align="center">ESCAPED <bean:write name="tots" property="relocationDate" formatKey="date.format.mmddyyyy"/></td>	
						</logic:equal>
						<logic:equal name="tots" property="facilityStatus" value="T">					
							<td align="center"><span title='<bean:write name="tots" property="relByDesc"/>'>
							TEMPORARILY RELEASED BY =<bean:write name="tots" property="releaseBy"/> </span></td>	
							<td align="center"><span title='<bean:write name="tots" property="relToDesc"/>'>
							TO =<bean:write name="tots" property="releaseTo"/> </span></td>	
							<td align="center"><span title='<bean:write name="tots" property="relAuthByDesc"/>'>
							AUTH = <bean:write name="tots" property="releaseAuthorizedBy"/> </span></td>
								<td align="center"><bean:write name="tots" property="releaseDate" formatKey="date.format.mmddyyyy"/>
										 <bean:write name="tots" property="releaseTime"/></td>	
						</logic:equal>
					
						<logic:notEmpty name="tots" property="returnDate">
							<logic:notEmpty name="tots" property="returnReason">
								<logic:equal name="tots" property="returnReason" value="HV"><td align="center"> RETURN FROM HOME VISIT</td>
									<td align="center"> AUTH= <bean:write name="tots" property="admitAuthority"/></td>
										<td align="center"><bean:write name="tots" property="returnDate" formatKey="date.format.mmddyyyy"/>	<bean:write name="tots" property="returnTime"/></td>
								</logic:equal>
							</logic:notEmpty>
						</logic:notEmpty>
						<logic:notEmpty name="tots" property="returnDate">
							<logic:notEmpty name="tots" property="returnReason">
								<logic:equal name="tots" property="returnReason" value="RE"><td align="center"> RETURN FROM ESCAPE</td>
									<td align="center"> AUTH= <bean:write name="tots" property="admitAuthority"/></td>
									<td align="center"><bean:write name="tots" property="returnDate" formatKey="date.format.mmddyyyy"/>	<bean:write name="tots" property="returnTime"/></td>
								</logic:equal>
							</logic:notEmpty>
						</logic:notEmpty>
						<logic:equal name="tots" property="facilityStatus" value="N">					
							<td align="center">TRANSFERRING </td>	
						</logic:equal>
						<logic:empty name="tots" property="facilityStatus">
							<logic:notEmpty  name="tots" property="releaseDate">
							<logic:equal name="tots" property="releaseReason" value="R">
								<td align="center"><span title='<bean:write name="tots" property="relByDesc"/>'>
									RELEASED BY =<bean:write name="tots" property="releaseBy"/> </span></td>	
									<td align="center"><span title='<bean:write name="tots" property="relToDesc"/>'>
									TO =<bean:write name="tots" property="releaseTo"/> </span></td>	
									<td align="center"><span title='<bean:write name="tots" property="relAuthByDesc"/>'>
									AUTH = <bean:write name="tots" property="releaseAuthorizedBy"/> </span></td>
								<td align="center"><bean:write name="tots" property="releaseDate" formatKey="date.format.mmddyyyy"/>
										 <bean:write name="tots" property="releaseTime"/>
								</td>
								</logic:equal>	
							</logic:notEmpty>
						</logic:empty>
							
					</tr>
					 </pg:item>
					<%-- End Pagination item wrap --%>
					</logic:iterate>
				</table>				
				
				<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
					<tr></tr>
					<tr>
							<td colspan="8" align="left"><b> ==> ** &nbsp;(<bean:write name="juvenilePopulationForm" property="listTotal"/>) FACILITY STATUS AS OF 
								<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm:ss MM/dd/yyyy" var="formattedDate" />
								<c:out value="${formattedDate}"/></b>
							</td>
						</tr>
						
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
   										<%-- End Pagination navigation Row--%>
   									 </td>
 									 </tr>
								 </table>
						<%-- Begin Pagination Closing Tag --%>
				</table>
				</pg:pager>	
				<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
					<tr>
						<td class="detailHead" colspan="8"><bean:message key="prompt.other"/> <bean:message key="prompt.status"/> <bean:message key="prompt.change"/>s</td>
					</tr>
					<logic:empty name="juvenilePopulationForm" property="residentWithOtherStatusChanges">
						<tr>
							<td align="left"> NO APPLICABLE RECORDS FOUND </td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="juvenilePopulationForm" property="residentWithOtherStatusChanges">
						<tr>
							<td class="detailHead"><bean:message key="prompt.juvenile#" />
							<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="juvNum" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
							<td class="detailHead"><bean:message key="prompt.referral" />#</td>
							<td class="detailHead"><bean:message key="prompt.juvenileName" /></td>
							<td class="detailHead"><bean:message key="prompt.rsAge" /></td>
							<td class="detailHead"><bean:message key="prompt.location" />
							<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="floorNum" primarySortType="STRING" secondPropSort="unit" secondarySortType="STRING" finalPropSort="roomNum" finalSortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" /></td>
							<td class="detailHead"><bean:message key="prompt.date" /> <bean:message key="prompt.in" />
								<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="admitDate" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="3" /></td></td>
							<td class="detailHead"><bean:message key="prompt.time" /> <bean:message key="prompt.in" />
								<jims2:sortResults beanName="juvenilePopulationForm" results="residentsStatusList" primaryPropSort="admitTime" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="4" /></td></td></td>
							<td class="detailHead"><bean:message key="prompt.reason" /></td>
						</tr>
						<logic:iterate id="tots" name="juvenilePopulationForm" property="residentWithOtherStatusChanges">
								
							<tr>
								<td align="left"><bean:write name="tots" property="juvNum"/></td>
								<td align="left"><bean:write name="tots" property="referralNumber"/></td>
								<td align="left"><bean:write name="tots" property="juvName"/></td>
								<td align="left"><bean:write name="tots" property="juvRace"/>&nbsp;<bean:write name="tots" property="juvSex"/>&nbsp;<bean:write name="tots" property="age"/></td>
								<td align="left"><bean:write name="tots" property="floorNum"/>&nbsp;<bean:write name="tots" property="unit"/>&nbsp;<bean:write name="tots" property="roomNum"/>
								<logic:notEqual name="tots" property="multipleOccupyUnit" value=""> -<bean:write name="tots" property="multipleOccupyUnit"/></logic:notEqual></td>
								<td align="left"><bean:write name="tots" property="admitDate" formatKey="date.format.mmddyyyy"/></td>
								<td align="left"><bean:write name="tots" property="admitTime"/></td>
								<td align="left">	<span title='<bean:write name="tots" property="reasonDescription"/>'>
									<bean:write name="tots" property="admitReason"/>-<bean:write name="tots" property="secureStatus"/>
									</span>
								</td>		
							</tr>
							<tr>
								<td></td>
								<td align="left"> <jims2:if name="tots" property="locationChangeFlag" value="true" op="equal"><jims2:then>LOCATION WITHIN FACILITY CHANGED</jims2:then></jims2:if>
									<jims2:if name="tots" property="secureIndicatorChangeFlag" value="true" op="equal"><jims2:then>SECURE INDICATOR CHANGED</jims2:then></jims2:if>
									<jims2:if name="tots" property="reasonChangeFlag" value="true" op="equal"><jims2:then>ADMIT REASON CHANGED</jims2:then></jims2:if>
									<jims2:if name="tots" property="otherChangeFlag" value="true" op="equal"><jims2:then>OTHER CHANGE</jims2:then>
  								</jims2:if> 
  								</td>
  								
  								<td></td>  								
  								<td> MODIFY DATE/TIME= <bean:write name="tots" property="lastChangeDate" formatKey="date.format.mmddyyyy"/> <bean:write name="tots" property="lcTime"/></td>
  								<td></td>  								
  								<td>REASON: <bean:write name="tots" property="changeExplanation"/></td>
  								
							</tr>
							
						
						</logic:iterate>
					</logic:notEmpty>
				
				</table>	
				
	
		<%-- End Pagination Closing Tag --%>
</span>
<div class="spacer4px" />
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
			<html:submit property="submitAction"><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>