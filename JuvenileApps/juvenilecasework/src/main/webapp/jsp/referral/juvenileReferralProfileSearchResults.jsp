<!DOCTYPE HTML>

<%-- Used for displaying results of juvenile profile search in MJCW --%>
<%-- MODIFICATIONS --%>
<%-- DWilliamson  06/06/2005	Create JSP --%>
<%-- CShimek	  07/13/2011	ER 70827 - add Name Suffix field to juvenile name display --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %> <!-- 89291 -->

<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>

<%-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --%>

<%-- BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">
$(function(){
	
	$(".juvNumId").on("click", function(){	
		if($(this).data("href") == 'Y')
		{
			alert("Juvenile's records are no longer accessible.");
			return false;
		}
		else
			changeFormActionURL($(this).attr("href") , true);
		
	});
	
	var recordCtr = $("#countr").val();
	if( recordCtr == 1){
		$('input:radio[name=juvenileProfilesRec]').attr('checked', true);
	}
});
</script>

<html:base />
<title><bean:message key="title.heading" /> - juvenileFacilityProfileSearchResults.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayJuvenileMasterInformation" target="content">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<%-- <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
<%-- End Pagination header stuff --%>

  <tr>
    <td align="center"><bean:write name="juvenileProfileSearchForm" property="searchResultSize"/> juvenile profiles found in search results</td>
  </tr>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  <tr>
	 <td>
		<table width='100%' cellpadding="2" cellspacing="1">
			<tr class="referralDetailHead">
				<td></td>
				<td width="14%">   
					<bean:message key="prompt.juvenileNumber" /><br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
				</td>
				 <logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM">
				<td width="4%"> 
					<bean:message key="prompt.alias" />&nbsp;  <br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="juvenileType" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
				</td>
				</logic:equal>
				<td>   
					<bean:message key="prompt.juvenileName" /><br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" sortId="3" />
				</td>
				<td width="5%">   
					<bean:message key="prompt.race" /><br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="race" primarySortType="STRING" sortId="4" />				
				</td>
				<td width="5%">   
					<bean:message key="prompt.sex" /><br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="sex" primarySortType="STRING" sortId="5" />
				</td>
				<td width="9%">   
					<bean:message key="prompt.dob" /><br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="dateOfBirth" primarySortType="DATE" sortId="6" />
				</td>
				<td width="11%">   
					SSN <br/>
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="SSN" primarySortType="STRING" sortId="7" />
				</td>
				<logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM">
					<td width="12%">   
						JPO of Record<br/>
						<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="sprvsionTypeCd" primarySortType="STRING" sortId="9" />
					</td>
				</logic:equal>
				<td width="12%">   
					<bean:message key="prompt.juvenilemasterStatus" /><br/>   
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="masterStatusId" primarySortType="STRING" sortId="10" />
				</td>
				<td width="12%">   
					<bean:message key="prompt.casefile" /> <bean:message key="prompt.status" /><br/>   
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="status" primarySortType="STRING" sortId="11" />
				</td>
			</tr>

		<logic:iterate id="juvenileProfiles" name="juvenileProfileSearchForm" property="juvenileProfiles" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" heignt="100%">	
				<td class='formDe'  width="1%">
				 <input type="radio" name=juvenileProfilesRec value=<bean:write name='juvenileProfiles' property='juvenileNum'/> styleId = "juvId" >
<%-- 				<html:radio name="juvenileProfiles" property="juvenileNum" value="juvenileNum" styleId="juvenileNum"></html:radio> --%>
				</td>  	
				<td><a class="juvNumId" href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=referralLink&isPopup=YesPopUp&juvenileNum=<bean:write name='juvenileProfiles' property='juvenileNum'/>','viewDetails',1000,800)" data-href="<bean:write name="juvenileProfiles" property="restrictedAccess"/>"><bean:write name="juvenileProfiles" property="juvenileNum"/></a></td>
			    <%--<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>','viewDetails',1000,800)">  Referral History</a>--%>
			    <%--<a class="juvNumId" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=referralLink&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileProfiles' property='juvenileNum'/>" data-href="<bean:write name="juvenileProfiles" property="restrictedAccess"/>"><bean:write name="juvenileProfiles" property="juvenileNum"/></a> --%>
				
				 <logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM">
					<td>
					<logic:equal name="juvenileProfiles" property="juvenileType" value="P">
		   				<span title='<bean:write name="juvenileProfiles" property="preferredFirstName"/>'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal> 
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="A">
					     <span title='ALIAS'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="M">
					   <span title='SOCIAL MEDIA IDENTIFIER'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="N">
					   <span title='NICKNAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="S">
					   <span title='STREET NAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="G">
					   <span title='GANG NAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 <logic:equal name="juvenileProfiles" property="juvenileType" value="F">
					   <span title='FALSE IDENTIFICATION'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 </logic:equal>
					 </td>    		
			  	</logic:equal>
				<td><bean:write name="juvenileProfiles" property="lastName"/>,&nbsp;<bean:write name="juvenileProfiles" property="firstName"/>&nbsp;<bean:write name="juvenileProfiles" property="middleName"/>&nbsp;<bean:write name="juvenileProfiles" property="nameSuffix"/>
				<logic:equal name="juvenileProfiles" property="recType" value="I.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
				</logic:equal>
				<logic:equal name="juvenileProfiles" property="recType" value="S.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
				</logic:equal></td>
				<td><bean:write name="juvenileProfiles" property="race"/></td>
				<td><bean:write name="juvenileProfiles" property="sex"/></td>
				<td><bean:write name="juvenileProfiles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
				<td><bean:write name="juvenileProfiles" property="formattedSSN"/></td>
				<logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM">
					<td>
						<span title="<bean:write name="juvenileProfiles" property="sprvsionType"/>"><bean:write name="juvenileProfiles" property="sprvsionTypeCd"/></span>
							<logic:notEmpty name="juvenileProfiles" property="sprvsionTypeCd">/</logic:notEmpty>
						<span title="<bean:write name="juvenileProfiles" property="jpoOfRecord"/>"><bean:write name="juvenileProfiles" property="jpoOfRecID"/></span>
					</td>
				</logic:equal>
				<td><span title="<bean:write name="juvenileProfiles" property="masterStatus"/>"><bean:write name="juvenileProfiles" property="masterStatusId"/></span></td>
				<td><span title="<bean:write name="juvenileProfiles" property="status"/>"><bean:write name="juvenileProfiles" property="statusId"/></span></td>
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
<%-- END DETAIL TABLE --%>
</pg:pager>
<html:hidden styleId="countr" name="juvenileProfileSearchForm" property="searchResultSize"/>
<table align="center" border="0" width="100%">
<tr><td></td></tr>
	<tr>
		<td align="center">
		   <html:submit property="submitAction"  styleId="refBriefgBtn"> <bean:message key="button.juvenileReferralBriefing" /></html:submit>&nbsp;
  		 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CREATEJUVENILE%>'>	
  		  	<html:submit property="submitAction"  styleId="createJuvenileBtn"> <bean:message key="button.createJuvenile" /></html:submit>&nbsp;
  		  </jims2:isAllowed>
		</td>
	</tr>
</table>
<%-- Pagination Closing Tag --%>
</html:form>
</body>
</html:html>
