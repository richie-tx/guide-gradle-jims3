<!DOCTYPE HTML>

<%-- Used for searching of juvenile JIMS reports in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 10/28/2013	CShimek  	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>



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
<title><bean:message key="title.heading" /> - facilityCurrentObservationReport.jsp</title>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">

$(document).ready(function(){	
	$("#printBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/displayFacilityCurrentObservationReport.do?submitAction=Print',
	        type: 'post',
	        data: $('form#facilityCurrentObservationReport').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
})
function findInfo()
{
	var forwardURL = "/<msp:webapp/>displayFacilityCurrentObservationReport.do?submitAction=Submit";	
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
<html:form styleId="facilityCurrentObservationReport" action="/displayFacilityCurrentObservationReport" target="content" focus="facilityId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">
    	<bean:message key="title.juvenileCasework"/> - 
    	<bean:message key="prompt.facility"/> <bean:message key="prompt.current"/> Observation Report
    </td>
  </tr>
  	<%-- <tr>
		<td class="header" align="center">
			As of <bean:write name="juvenilePopulationForm"  property="currentTime" formatKey="datetime.format.MMMddyyyyHHmm" /> 
		</td>
	</tr> --%>	
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
		<td>
			<ul>
				<li>Select Facility from Facility drop down to view report for a different facility.</li>
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
	<tr>
		<td align="center">
			<bean:message key="prompt.2.diamond"/> <b><bean:message key="prompt.facility" /></b>:
			<html:select name="juvenilePopulationForm" property="facilityId" onchange="findInfo()">
				<html:optionsCollection name="juvenilePopulationForm" property="facilities" value="code" label="descriptionWithCode" />
			</html:select>
		</td>
	</tr>

</table>
<%-- END SEARCH FOR TABLE  --%>
<%-- BEGIN INFORMATION TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
		<tr>
			<td class="detailHead"><bean:message key="prompt.juvenile#" />
			 <jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="juvenileNum" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
						
          	</td>		
			<td class="detailHead"><bean:message key="prompt.referral" />#
				<jims2:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="referralNumber" primarySortType="STRING" secondPropSort="formattedAdmitDate" secondarySortType="DATE" defaultSortOrder="ASC" sortId="8" />
			</td>
			<td class="detailHead"><bean:message key="prompt.juvenileName" />
				<jims2:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" finalPropSort="middleName" finalSortType="STRING" defaultSortOrder="ASC" sortId="10" />
			</td>
			<td class="detailHead"><bean:message key="prompt.rsAge" /></td>
			<td class="detailHead"><bean:message key="prompt.location" />
				<jims2:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="floorNum" primarySortType="STRING" secondPropSort="unit" secondarySortType="STRING" finalPropSort="roomNum" finalSortType="STRING" defaultSortOrder="ASC" sortId="2" />
			</td>
			<td class="detailHead">JPO
				<jims2:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="officerUVCode" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
			</td>
			<td class="detailHead"><bean:message key="prompt.date" /> <bean:message key="prompt.in" />
				<jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="formattedAdmitDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="5" /> <%-- #32059 fix --%>
			</td>
			<td class="detailHead"><bean:message key="prompt.time" /> <bean:message key="prompt.in" />
				<jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="admitTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
			</td>			
			<td class="detailHead"><bean:message key="prompt.reason" />
				<jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="admitReason" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" />
			</td>
			<td class="detailHead"><bean:message key="prompt.specialAttention" />
				<jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="specialAttention" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
			</td>
			<td class="detailHead"><bean:message key="prompt.day" />s <bean:message key="prompt.in" />
				<jims:sortResults beanName="juvenilePopulationForm" results="currentPopulations" primaryPropSort="diffInDays" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" /></td> 
		</tr>
		<logic:iterate id="tots" name="juvenilePopulationForm" property="currentPopulations">
		<%-- Begin Pagination item wrap --%>
          	<pg:item>
			<tr>
				<td align="left"><bean:write name="tots" property="juvenileNum"/></td>
				<td align="left"><bean:write name="tots" property="referralNumber"/></td>
				<td align="left"><bean:write name="tots" property="lastName"/>,&nbsp<bean:write name="tots" property="firstName"/>&nbsp<bean:write name="tots" property="middleName"/></td>
				<td align="left"><bean:write name="tots" property="raceId"/>&nbsp<bean:write name="tots" property="sexId"/>&nbsp<bean:write name="tots" property="ageInYears"/></td>
				<td align="left"><bean:write name="tots" property="floorNum"/>&nbsp<bean:write name="tots" property="unit"/>&nbsp<bean:write name="tots" property="roomNum"/>
				 <logic:notEqual name="tots" property="mou" value=""> - <bean:write name="tots" property="mou"/></logic:notEqual></td>
				<td align="left">	<span title='<bean:write name="tots" property="officerFullName"/>'><bean:write name="tots" property="officerUVCode"/></span></td>
				<td align="left"><bean:write name="tots" property="formattedAdmitDate"/></td>
				<td align="left"><bean:write name="tots" property="admitTime"/></td>				
				<td align="left">
					<span title='<bean:write name="tots" property="reasonDescription"/>'>
						<bean:write name="tots" property="admitReason"/>-<bean:write name="tots" property="secureStatus"/>
					</span>	
				</td>
				<td align="left"> <span title='<bean:write name="tots" property="specialAttentionDesc"/>'> <bean:write name="tots" property="specialAttention"/></span></td>
				<td align="left"><bean:write name="tots" property="diffInDays"/></td> 						
			</tr>
			</pg:item>
  		   <%-- End Pagination item wrap --%>
		</logic:iterate>
		<tr>
			<td align="left"colspan="8"><b>TOTAL &nbsp;(<bean:write name="juvenilePopulationForm" property="listTotal"/>) JUVENILES CURRENTLY IN FACILITY AS OF 
					<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="HH:mm:ss MM/dd/yyyy" var="formattedDate" />
					<c:out value="${formattedDate}"/></b>
				</td>
		</tr>
	</table>
<%-- END INFORMATION TABLE --%>
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

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<div class="spacer4px" />
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
			<html:submit styleId="printBtn" property="submitAction"><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<div class="spacer4px" />
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>