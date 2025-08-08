<!DOCTYPE HTML>
<%-- User selects the "Activities" tab --%>
<%--MODIFICATIONS --%>
<%-- 11/15/2006	Debbie Williamson	Create JSP --%>
<%-- 02/08/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 04/19/2012 C Shimek	    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/16/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to Add/Update buttons --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>





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

<!-- JQUERY LOCAL FRAMEWORK -->
 <%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/groups.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>
<!-- JQuery function -->
<script type="text/javaScript" src="/<msp:webapp/>js/searchactivity.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/gangAssessmentReferralsSearchResults.jsp</title>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayGangAssessmentReferrals" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|129">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:write name="assessmentReferralForm" property="status"/> Gang Assessment Referrals</td>
	</tr>
	<%-- <tr>
		<td align="right">From Date: <bean:write name="assessmentReferralForm" property="startDateAsStr"/> </td>
		<td align="left">To Date: <bean:write name="assessmentReferralForm" property="endDateAsStr"/> </td>
	</tr> --%>
	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
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

<%-- BEGIN GROUPING TABLE --%>
<div class='spacer'></div>

<div class='spacer'></div>
<%-- END INSTRUCTION TABLE --%>

<div class='spacer'></div><div class='spacer'></div>
<logic:notEmpty name="assessmentReferralForm" property="gangAssessmentRefSearchList">
   <tr>
   	 <td align="center"><bean:write name="assessmentReferralForm" property="listTotal"/>&nbsp;&nbsp;<bean:write name="assessmentReferralForm" property="status"/>&nbsp;Record(s) Found </td>
   	 <br />
  </tr>
 </logic:notEmpty>

	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
		<tr>
			
		   <td class="formDeLabel" width='20%'><bean:message key="prompt.juvenileName" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="juvenileName" primarySortType="STRING" sortId="1"/>
		  </td>
		  <td class="formDeLabel" width='10%'><bean:message key="prompt.juvenile#" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="juvenileNum" primarySortType="INT" sortId="2"/>
		  </td>
		  <td class="formDeLabel" width='12%'><bean:message key="prompt.referralDate" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="referralDate" primarySortType="DATE" sortId="3"/>	
		  </td>
		  <td class="formDeLabel" width='25%'>Person Making Referral
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="personMakingReferral" primarySortType="STRING" sortId="4"/>
		  </td>
		  <td class="formDeLabel"><bean:message key="prompt.recommendation" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="recommendations" primarySortType="STRING" sortId="5"/>
		  </td>
		  <td class="formDeLabel"><bean:message key="prompt.status" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="acceptedStatus" primarySortType="STRING" sortId="6"/>
		  </td>
		  <td class="formDeLabel"><bean:message key="prompt.jpo" />
			  <jims2:sortResults beanName="assessmentReferralForm" results="gangAssessmentRefSearchList" primaryPropSort="jpoOfRecord" primarySortType="STRING" sortId="7"/>
		  </td>
			 
		</tr>
		<logic:iterate id="gangAssessmentIndex" name="assessmentReferralForm" property="gangAssessmentRefSearchList" indexId="index">
		<%-- Begin Pagination item wrap --%>
          	<pg:item>
				<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">				
				  <td><bean:write name="gangAssessmentIndex" property="juvenileName" /></td>
				  <td><a onclick="spinner()" href="/<msp:webapp/>displayGangAssessmentReferrals.do?submitAction=Link&juvenileNum=<bean:write name="gangAssessmentIndex" property="juvenileNum"/>" /><bean:write name="gangAssessmentIndex" property="juvenileNum" /></td>
				  <td><bean:write name="gangAssessmentIndex" property="referralDate" formatKey="date.format.mmddyyyy" /></td>
				  <td><bean:write name="gangAssessmentIndex" property="personMakingReferral" /></td>
				  <td><bean:write name="gangAssessmentIndex" property="recommendations" /></td>
				  <td><bean:write name="gangAssessmentIndex" property="acceptedStatus" /></td>
				  <td><bean:write name="gangAssessmentIndex" property="jpoOfRecord" /></td>
				</tr>
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

</pg:pager>
<%-- End Pagination Closing Tag --%>
<div class="spacer4px" />
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	
	<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
			<html:submit styleId="printBtn" property="submitAction"><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
</table>
<%-- END DETAIL TABLE --%>

</pg:pager>

<!-- <script  type='text/javascript'>updateTypeForView(document.forms[0]);</script> -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>