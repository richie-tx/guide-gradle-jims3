<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 05/2007	DWilliamson		create jsp --%>
<%-- 04/21/2008	LDeen			Defect #50777-add sorting tag  --%>
<!-- 10/13/2015 RCapestani      Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>


<html:base />

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- programReferralList.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
<script type='text/javascript'>

$(function(){
	
	$(".activeProgRefId").on("click", function(){	
		
		if($(this).data("href") == 'Y')
		{
			alert("Juvenile's records are no longer accessible.");
			return false;
		}
		else
			changeFormActionURL($(this).attr("href") , true);
		
	});
	
});

</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayProgramReferralDetails" target="content">

<input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|388">

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.serviceProvider" />&nbsp;-&nbsp;<bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.list" /></td>
  </tr>
</table>
<table width="100%">
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber" scope="request">		
<input type="hidden" name="pager.offset" value="<%= offset %>">

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select a hyperlinked Last Action Date/Time to view Program Referral detail.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN Program Referral History TABLE --%>
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td colspan="4" class="detailHead"><a href="javascript:showHideMulti('activeReferralList', 'achar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/contract.gif" name="activeReferralList"></a>&nbsp;<bean:message key="prompt.active" />&nbsp;<bean:message key="prompt.programReferrals" /></td>
  </tr>		
	<tr id="achar0" class='visibleTR'>
		<td>							 
  		 <table cellpadding="2" cellspacing="1" width='100%'>
      		<logic:empty name="programReferralSearchForm" property="activeReferralList">
      			<tr bgcolor="#cccccc">
      				<td align="left" colspan="4" class="subHead">No Active Program Referrals Available.</td>
      			</tr>
      		</logic:empty>

				<logic:notEmpty name="programReferralSearchForm" property="activeReferralList">
					<tr valign="top">
					  <td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.lastActionDateTime" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.referralStatus" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="referralStatusDescription" primarySortType="STRING" sortId="2" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel">Juvnum
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="juvenileId" primarySortType="STRING" sortId="3" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.juvenileName" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="juvenileFullName" primarySortType="STRING" sortId="4" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel">Location
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="officerLocationUnit" primarySortType="STRING" sortId="5" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="officerName" primarySortType="STRING" sortId="6" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.program" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="providerProgramName" primarySortType="STRING" sortId="7" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.beginDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="beginDate" primarySortType="DATE" sortId="8" levelDeep="3" />
					  </td>
					   <td class="formDeLabel"><bean:message key="prompt.endDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="activeReferralList" primaryPropSort="endDate" primarySortType="DATE" sortId="9" levelDeep="3" />
					  </td>
					</tr>
			    <logic:iterate id="programReferralIndex" name="programReferralSearchForm" property="activeReferralList" indexId="index">
			    	<%-- Begin Pagination item wrap --%>
    			  <pg:item>
    					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
    					  <td><a class="activeProgRefId" href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndex' property='referralId' />" data-href="<bean:write name="programReferralIndex" property="restrictedAccess" />">
    						  <bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
    					  </td>
    					  <td><bean:write name="programReferralIndex" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>
    					  <td><bean:write name="programReferralIndex" property="juvenileId" /></td>
    					  <td><bean:write name="programReferralIndex" property="juvenileFullName" /></td>
    					  <td title='<bean:write name="programReferralIndex" property="officerLocationUnitName" />'><bean:write name="programReferralIndex" property="officerLocationUnit" /></td>
    					  <td><bean:write name="programReferralIndex" property="officerName" /></td>
    					  <td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
    					  <td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
    					  <td><bean:write name="programReferralIndex" property="endDate" formatKey="date.format.mmddyyyy" /></td>
    					</tr>
    			  </pg:item>
    			  <%-- End Pagination item wrap --%>
    			</logic:iterate>
  		  </logic:notEmpty>   
		  </table>

			<%-- END Program Referral History TABLE --%>						
		  <div class='spacer'></div>
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
<%-- END DETAIL TABLE --%>

</pg:pager>


 <%-- BEGIN Rejected Program Referral History TABLE --%>
<%-- <div class='spacer'></div>
<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  <tr>
    <td colspan="4" class="detailHead"><a href="javascript:showHideMulti('rejectedReferralList', 'rchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="rejectedReferralList"></a>&nbsp;Rejected&nbsp;<bean:message key="prompt.programReferrals" /></td>
  </tr>		
	<tr id="rchar0" class='hidden' height="100%">
		<td>

		  <div class="scrollingDiv150NoBorder">
			<table cellpadding="2" cellspacing="1" width='100%'>
				<thead>
				<logic:empty name="programReferralSearchForm" property="rejectedReferralList">
					<tr bgcolor1="#cccccc">
						<td align="left" colspan="4" class="subHead">No Rejected Program Referrals Available.</td>
					</tr>
				</logic:empty>

				<logic:notEmpty name="programReferralSearchForm" property="rejectedReferralList">
					<tr valign="top" height="100%">
					  <td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.lastActionDateTime" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="10" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.referralStatus" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="referralStatusDescription" primarySortType="STRING" sortId="11" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.juvenileName" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="juvenileFullName" primarySortType="STRING" sortId="12" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="officerName" primarySortType="STRING" sortId="13" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.program" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="providerProgramName" primarySortType="STRING" sortId="14" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.beginDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="beginDate" primarySortType="DATE" sortId="15" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.endDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="rejectedReferralList" primaryPropSort="endDate" primarySortType="DATE" sortId="16" levelDeep="3" />
					  </td>
					</tr>
				</thead>
				<tbody>
			   <logic:iterate id="programReferralIndexRejected" name="programReferralSearchForm" property="rejectedReferralList" indexId="index">
  				Begin Pagination item wrap
  					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
  					  <td><a href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndexRejected' property='referralId' />">
  						  <bean:write name="programReferralIndexRejected" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /> </a>
  					  </td>
  					  <td><bean:write name="programReferralIndexRejected" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndexRejected" property="referralSubStatusDescription" /></td>
  					  <td><bean:write name="programReferralIndexRejected" property="juvenileFullName" /></td>
  					  <td><bean:write name="programReferralIndexRejected" property="officerName" /></td>
  					  <td><bean:write name="programReferralIndexRejected" property="providerProgramName" /></td>	
  					  <td><bean:write name="programReferralIndexRejected" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
  					  <td><bean:write name="programReferralIndexRejected" property="endDate" formatKey="date.format.mmddyyyy" /></td>
  					</tr>
  			   End Pagination item wrap
  			 </logic:iterate>
		   </logic:notEmpty>
		   </tbody>   
		  </table>
		  </div>
			END Rejected Program Referral History TABLE						
		</td>
  </tr>
</table> --%>

<%-- END DETAIL TABLE --%>


<%-- BEGIN Closed Program Referral History TABLE --%>
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td colspan="4" class="detailHead"><a href="javascript:showHideMulti('closedReferralList', 'cchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="closedReferralList"></a>&nbsp;Closed&nbsp;<bean:message key="prompt.programReferrals" /></td>
  </tr>		
	<tr id="cchar0" class='hidden' height="100%">
		<td>

		  <div class="scrollingDiv150NoBorder">
			<table cellpadding="2" cellspacing="1" width='100%'>
				<thead>
				<logic:empty name="programReferralSearchForm" property="closedReferralList">
					<tr bgcolor1="#cccccc">
						<td align="left" colspan="4" class="subHead">No Closed Program Referrals Available.</td>
					</tr>
				</logic:empty>

				<logic:notEmpty name="programReferralSearchForm" property="closedReferralList">
					<tr valign="top" height="100%">
					  <td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.lastActionDateTime" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="10" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.referralStatus" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="referralStatusDescription" primarySortType="STRING" sortId="11" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.juvenileName" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="juvenileFullName" primarySortType="STRING" sortId="12" levelDeep="3" />	
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="officerName" primarySortType="STRING" sortId="13" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.program" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="providerProgramName" primarySortType="STRING" sortId="14" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.beginDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="beginDate" primarySortType="DATE" sortId="15" levelDeep="3" />
					  </td>
					  <td class="formDeLabel"><bean:message key="prompt.endDate" />
						  <jims:sortResults beanName="programReferralSearchForm" results="closedReferralList" primaryPropSort="endDate" primarySortType="DATE" sortId="16" levelDeep="3" />
					  </td>
					</tr>
				</thead>
				<tbody>
			   <logic:iterate id="programReferralIndexClosed" name="programReferralSearchForm" property="closedReferralList" indexId="index">
  				<%-- Begin Pagination item wrap --%>
  					<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
  					  <td><a class="closedProgRefId" href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndexClosed' property='referralId' />" data-href="<bean:write name="programReferralIndexClosed" property="restrictedAccess" />">
  						  <bean:write name="programReferralIndexClosed" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /> </a>
  					  </td>
  					  <td><bean:write name="programReferralIndexClosed" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndexClosed" property="referralSubStatusDescription" /></td>
  					  <td><bean:write name="programReferralIndexClosed" property="juvenileFullName" /></td>
  					  <td><bean:write name="programReferralIndexClosed" property="officerName" /></td>
  					  <td><bean:write name="programReferralIndexClosed" property="providerProgramName" /></td>	
  					  <td><bean:write name="programReferralIndexClosed" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
  					  <td><bean:write name="programReferralIndexClosed" property="endDate" formatKey="date.format.mmddyyyy" /></td>
  					</tr>
  			   <%-- End Pagination item wrap --%>
  			 </logic:iterate>
		   </logic:notEmpty>
		   </tbody>   
		  </table>
		  </div>
			<%-- END Closed Program Referral History TABLE --%>						
		</td>
  </tr>
</table>

<%-- END DETAIL TABLE --%>

 <!-- begin button table -->
<table border="0" cellpadding='1' cellspacing='1' align='center'>
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.printProgramReferralListBFO"/></html:submit>
		</td>
	</tr>
</table>
<!-- end button table -->

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

