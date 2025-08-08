<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtHearings.js"></script>

<html:base />
<script type="text/javascript">

function checkPager()
{
	//alert("In pager");
	var theForm = document.forms[0];	
	var dt = document.getElementsByName('pager.offset')[0].value;
	<logic:equal name="reset" value="yes">		
		document.getElementsByName('pager.offset')[0].value = 0;		
	</logic:equal>
}

$(document).ready(function(){
	$("#stdCrtDktBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleJuvenileDistrictCourtDocketDisplay.do?submitAction=Standard Court Docket',
	        type: 'post',
	        data: $('form#crtdocketDisplay').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	$("#alpCrtDktBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/handleJuvenileDistrictCourtDocketDisplay.do?submitAction=Alphabetized Court Docket',
	        type: 'post',
	        data: $('form#crtdocketDisplay').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
})
</script>

<html:base />
<title><bean:message key="title.heading"/>/courtDocketDisplay.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onLoad="checkPager();">
<html:form styleId="crtdocketDisplay"  action="/handleJuvenileDistrictCourtDocketDisplay" target="content">
					
<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Court Hearings - <bean:write name="courtHearingForm" property="courtIdWithSuffix"/> Court Docket For <bean:write name="courtHearingForm" property="courtDate"/></td>
	</tr>
</table>
<br/>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Atleast one of the actions fields is required:Displaying.</li>
        <li>Click Standard Court Docket to generate report.</li>
        <li>Click Alphabetized Court Docket to generate report.</li>
        <li>Click Cancel button to return to the court main menu page.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5">          
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='2%' align="right" nowrap>&nbsp;<bean:message key="prompt.displaying" /></td>
		<td class='formDe' width="49%" colspan='4' nowrap>
			<html:text name="courtHearingForm" styleId="displayingType" property="displaying" size="1" maxlength="1" /> (A=ANCILLARY, D=DELINQUENCY, B=BOTH)&nbsp;
			<html:submit onclick="spinner()" property="submitAction" styleId="goBtn"><bean:message key="button.go"/></html:submit>
		</td>
	</tr>
</table>
<br><br/>
<%-- Begin Pagination Header Tag--%>

<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="6" export="offset,currentPageNumber=pageNumber" scope="request">
<%-- <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
<%-- End Pagination header stuff --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
 	<tr>
		<td>
	  		<table width='100%' cellpadding="2" cellspacing="1">
 				<tr class="crtDetailHead">   
    				<td>   
						<bean:message key="prompt.time" />
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" defaultSort="true"/>
          			</td>
          			<td>   
						<bean:message key="prompt.petition" /><br/>NO-STA-AMEND 
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionNumUI" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
          			</td>
          			<td> 
						<bean:message key="prompt.name" /><br/><bean:message key="prompt.attorneyName" />
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3"/>
          			</td>
          			<td> 
						<bean:message key="prompt.allegation"/> <br/> PRE SET-NOTE 
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionAllegationDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="4"/>
          			</td>
					 <td>
  						<bean:message key="prompt.hearingType" />
         					<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" /> 
	          		</td>
				</tr>
  				<logic:iterate id="dockets" name="courtHearingForm" property="dktSearchResults" indexId='indexer'> 
          			<%-- Begin Pagination item wrap --%>
          			<pg:item>
	    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10">
	    					<td class='formDe'><bean:write name="dockets" property="courtTime" formatKey="time.format.HHmm"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="petitionNumUI"/>
	    					<logic:notEmpty  name="dockets" property="probationOfficerCd"><span title='<bean:write name="dockets" property="probationOfficer"/>'>
	    					&nbsp;(<bean:write name="dockets" property="probationOfficerCd"/>)</span>
	    					</logic:notEmpty>
	    					<br/>
	    					<logic:notEmpty name="dockets" property="juvenileCoactors">
   							<span class='formDeLabel'>Co-Actor(s):</span>							
							<logic:iterate id="coActors" name="dockets" property="juvenileCoactors" indexId="index">
							<bean:define id="coActorsList" name="dockets" property="juvenileCoactors" type="java.util.List" />
								<span title='<bean:write name="coActors" property="attorneyName"/>'><bean:write name="coActors" property="juvenileNum"/>
								<% if( index.intValue() != coActorsList.size()-1){ %>
                                  ,
                                <% } %>
							</logic:iterate>							
							</logic:notEmpty>
	    					</td>
							<td class='formDe'><bean:write name="dockets" property="juvenileName"/><br/><bean:write name="dockets" property="attorneyName"/></td>
							<td class='formDe'><bean:write name="dockets" property="petitionAllegationDesc"/><br/>
							<logic:notEmpty  name="dockets" property="prevHearingDate"><bean:write name="dockets" property="prevHearingDate"/></logic:notEmpty>
							<bean:write name="dockets" property="setNote"/></td>
	    					<td class='formDe'>
	    					<span title="<bean:write name="dockets" property="hearingTypeDesc"/>"><bean:write name="dockets" property="hearingType"/></span>
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
								<tiles:put name="resultsPerPage"
									value="<%=paginationResultsPerPage%>" />
							</tiles:insert>
						</pg:index></td>
				</tr>
			</table>

			<%-- End Pagination navigation Row--%>
</pg:pager>
<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:submit property="submitAction" styleId="stdCrtDktBtn"><bean:message key="button.standardCourtDocket"/></html:submit>
			<html:submit property="submitAction" styleId="alpCrtDktBtn"><bean:message key="button.alphabetizedCourtDocket"/></html:submit>
    	</td>
	</tr>
	<tr>
		<td align="center"><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
		
	</tr>
</table>
<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>