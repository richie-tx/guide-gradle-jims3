<%-- 11/09/2005	Aswin Widjaja - Create JSP --%>
<%-- 10/18/2007	LDeen - Revise to add instructions-Defect #46240 --%>
<%-- 07/17/2009 C Shimek  -  #61004 added timeout.js  --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/> - benefitsAssessmentRequestList.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0" onLoad=maintainChks('0')> 
<html:form action="/displayBenefitsAssessmentMemberList.do" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|4"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.benefitsAssessmentRequestList"/></td> 
  </tr> 
	<tr>
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
	</tr>
</table> 
<%-- END HEADING TABLE --%> 
 
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  
<%-- End Pagination header stuff --%>

<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
  <tr> 
    <td> 
      <ul> 
	      <li>Select radio button for Juvenile and then click Next button to choose which Benefit Assessment to process.</li> 
	      <li>Requests more than 7 days old are highlighted in yellow.</li> 
	      <li>Requests more than 30 days old are highlighted in red.</li> 
      </ul>
    </td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%-- BEGIN DETAIL TABLE --%> 
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align=center> 
  <tr> 
    <td colspan=4 class=detailHead><bean:message key="title.benefitsAssessments"/></td> 
  </tr>
  <tr> 
    <td> 
    	<table cellpadding=2 cellspacing=1 width='100%'> 
        <tr class=formDeLabel> 
          <td valign=top></td> 
          <td valign=top><bean:message key="prompt.juvenile#"/>
                         <jims:sortResults beanName="processBenefitsAssessmentForm" results="requestList" primaryPropSort="juvNumber" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
          </td> 
          <td valign=top><bean:message key="prompt.juvenileName"/>
                         <jims:sortResults beanName="processBenefitsAssessmentForm" results="requestList" primaryPropSort="juvName.formattedName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
          </td> 
          <td valign=top><bean:message key="prompt.guardianNames"/>
                         <jims:sortResults beanName="processBenefitsAssessmentForm" results="requestList" primaryPropSort="guardiansNames" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
          </td>
          <td valign=top><bean:message key="prompt.requestorName"/>
                         <jims:sortResults beanName="processBenefitsAssessmentForm" results="requestList" primaryPropSort="requesterName" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
          </td>
          <td valign=top><bean:message key="prompt.date"/>
                         <jims:sortResults beanName="processBenefitsAssessmentForm" results="requestList" primaryPropSort="requestDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="5" />
          </td>
          <td valign=top><bean:message key="prompt.time"/></td>
        </tr> 

				<logic:empty name="processBenefitsAssessmentForm" property="requestList">
					<tr>
						<td align=center colspan='7'>No Records Found.</td>
					</tr>
				</logic:empty>
				 
				<logic:notEmpty name="processBenefitsAssessmentForm" property="requestList">
					<logic:iterate indexId="listIndex" id="listIter" name="processBenefitsAssessmentForm" property="requestList">
					 <%-- Begin Pagination item wrap --%>
						  <pg:item>
						  <tr
				  			<logic:equal name="listIter" property="requestDateStatus" value="<%=UIConstants.OVER_7_DAYS%>">
				  				bgcolor=#ffffcc title="Older than 7 days"
				  			</logic:equal>
				  			<logic:equal name="listIter" property="requestDateStatus" value="<%=UIConstants.OVER_30_DAYS%>">
				  				bgcolor=#ffcccc title="Older than 30 days"
				  			</logic:equal>
				  			<logic:equal name="listIter" property="requestDateStatus" value="">			 
									<%if( listIndex.intValue() %2 == 0)
										out.println("bgcolor=#f0f0f0");
									%>
								</logic:equal>
							>
								<bean:define id="assessmentId" name="listIter" property="assessmentId" type="java.lang.String"/>
								<td valign=top width='1%'><html:radio name="processBenefitsAssessmentForm" property="currentAssessment.assessmentId" value="<%=assessmentId%>"/></td> 
								<td valign=top><bean:write name="listIter" property="juvNumber"/></td> 
								<td valign=top><bean:write name="listIter" property="juvName.formattedName"/></td> 
								<td valign=top><bean:write name="listIter" property="guardiansNames"/></td>
								<td valign=top><bean:write name="listIter" property="requesterName"/></td>
								<td valign=top><bean:write name="listIter" property="requestDate" formatKey="date.format.mmddyyyy"/></td>
								<td valign=top><bean:write name="listIter" property="requestTime"/></td> 
							</tr> 
						</pg:item>
				   <%-- End Pagination item wrap --%>
					</logic:iterate>
				</logic:notEmpty>
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
    </td> 
  </tr> 
</table> 
<%-- END DETAIL TABLE --%> 

<%-- BEGIN BUTTON TABLE --%> 
<div class='spacer'></div>
<table border="0" width="100%"> 
	<tr> 
		<td align="center"> <%--<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>--%> 
			<logic:notEmpty name="processBenefitsAssessmentForm" property="requestList">
				<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
			</logic:notEmpty>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
		</td> 
	</tr> 
</table> 
<%-- END BUTTON TABLE --%> 

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form> 

<div class='spacer'></div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div> 
</body>
</html:html>
