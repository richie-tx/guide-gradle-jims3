<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- 10/29/2007	CShimek  	#46470 added column sorting --%>
<%-- 05/08/2012	CShimek  	#73232 added logic to hide create button depending on case status and supervision type --%>
<%-- 07/12/2012 CShimek     #73565 added age > 20 check (juvUnder21) to Create button --%>
<%-- 08/02/2013 CShimek		Removed JPO Name in Closed Program Referrals list, found while working on Activity #75902 --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;caseTabProgramReferrals - programReferralList.jsp</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<html:base />

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayProgramReferralDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|340">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.list" /></td>
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
  <input type="hidden" name="pager.offset" value="<%= offset %>"--%>
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

<%--header info start--%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%--header info end--%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top">
  		<%--tabs start--%>
	  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="programreferraltab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert>
  		<%--tabs end--%>
			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
						<%-- BEGIN Active Program Referrals TABLE --%>
						<div class='spacer'></div>
						<table align="center" width="98%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
							<tr>
								<td colspan="4" class="detailHead"> <bean:message key="prompt.active" />&nbsp;<bean:message key="prompt.programReferrals" /></td>
							</tr>
							<tr>
								<td>							 
									<table cellpadding=2 cellspacing=1 width='100%'>
										<logic:empty name="programReferralForm" property="activeReferralList">
											<tr bgcolor="#cccccc">
												<td colspan="4" class="subHead" align="left">No Active Program Referrals Available.</td>
											</tr>
										</logic:empty>
	
										<logic:notEmpty name="programReferralForm" property="activeReferralList">
											<tr valign="top">
												<td class="formDeLabel" ><bean:message key="prompt.lastActionDateTime" />&nbsp;
													<jims:sortResults sortId="11" beanName="programReferralForm" results="activeReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC"/>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.referralStatus" />&nbsp;
													<jims:sortResults sortId="12" beanName="programReferralForm" results="activeReferralList" primaryPropSort="referralSubStatusDescription" primarySortType="STRING" />								  
												</td>
												<td class="formDeLabel"><bean:message key="prompt.serviceProvider" />&nbsp;
													<jims:sortResults sortId="13" beanName="programReferralForm" results="activeReferralList" primaryPropSort="juvServiceProviderName" primarySortType="STRING" />								  
												</td>								  
												<td class="formDeLabel"><bean:message key="prompt.program" />&nbsp;
													<jims:sortResults sortId="14" beanName="programReferralForm" results="activeReferralList" primaryPropSort="providerProgramName" primarySortType="STRING" />								  
												</td>
												<td class="formDeLabel"><bean:message key="prompt.beginDate" />&nbsp;
													<jims:sortResults sortId="15" beanName="programReferralForm" results="activeReferralList" primaryPropSort="beginDate" primarySortType="DATE" />								  
												</td>
											</tr>
	
											<logic:iterate id="programReferralIndex" name="programReferralForm" property="activeReferralList" indexId="index">
												<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
													<td><a onclick="spinner();" href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndex' property='referralId' />">
														<bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
													</td>
													<td><bean:write name="programReferralIndex" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>
													<td><bean:write name="programReferralIndex" property="juvServiceProviderName" /></td>								  
													<td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
													<td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
												</tr>
											</logic:iterate>
									   </logic:notEmpty>   
									</table>				
								</td>
					   		</tr>
					   	</table>
						<%-- END Active Program Referrals TABLE --%>	
	
						<%-- BEGIN Closed Program Referrals TABLE --%>
						<div class='spacer'></div>
						<table align="center" width="98%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
							<tr>
								<td colspan=4 class="detailHead"> <bean:message key="prompt.closed" />&nbsp;<bean:message key="prompt.programReferrals" /></td>
							</tr>
							<tr>
								<td>							 
									<table cellpadding=2 cellspacing=1 width='100%'>
										<logic:empty name="programReferralForm" property="closedReferralList">
											<tr bgcolor="#cccccc">
												<td colspan="4" class="formDeLabel" align="center">No Closed Program Referrals Available.</td>
											</tr>
										</logic:empty>
		    							<logic:notEmpty name="programReferralForm" property="closedReferralList">
		    								<tr valign="top">
	    										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.lastActionDateTime" />
	    							  				<jims:sortResults sortId="21" beanName="programReferralForm" results="closedReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC"/>
	    							  				</div>								  
	    								  		</td>
	    								 <%-- 		<td class="formDeLabel" ><bean:message key="prompt.JPO" /> <bean:message key="prompt.name" /><div>
	    							  				<jims:sortResults sortId="28" beanName="programReferralForm" results="closedReferralList" primaryPropSort="officerFullName" primarySortType="STRING" defaultSort="true" defaultSortOrder="DESC"/>								  
	    								  			</div>
	    								  		</td>  --%> 
	    								  		<td class="formDeLabel"><bean:message key="prompt.supervision" />&nbsp;<bean:message key="prompt.#" />
													<jims:sortResults sortId="24" beanName="programReferralForm" results="closedReferralList" primaryPropSort="casefileId" primarySortType="STRING"/>								  
													</div>
												</td>
	    								  		<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.referralStatus" />
	    							  				<jims:sortResults sortId="22" beanName="programReferralForm" results="closedReferralList" primaryPropSort="referralSubStatusDescription" primarySortType="STRING"/>
	    								  		</div>
	    								  		</td>
												<td class="formDeLabel"><bean:message key="prompt.serviceProvider" />
													<jims:sortResults sortId="23" beanName="programReferralForm" results="closedReferralList" primaryPropSort="juvServiceProviderName" primarySortType="STRING"/>								  
												</div>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.program" />
													<jims:sortResults sortId="27" beanName="programReferralForm" results="closedReferralList" primaryPropSort="providerProgramName" primarySortType="STRING" />								  
												</div>
												</td>								  
												
												<td class="formDeLabel" width="2%" nowrap="nowrap"><bean:message key="prompt.beginDate" />
													<jims:sortResults sortId="25" beanName="programReferralForm" results="closedReferralList" primaryPropSort="beginDate" primarySortType="DATE"/>								  
												</div>
												</td>
												<td class="formDeLabel" width="2%" nowrap="nowrap"><bean:message key="prompt.endDate" />
													<jims:sortResults sortId="26" beanName="programReferralForm" results="closedReferralList" primaryPropSort="endDate" primarySortType="DATE"/>								  
												</div>
												 </td>
											</tr>
	    							   		<logic:iterate id="programReferralIndex" name="programReferralForm" property="closedReferralList" indexId="index">
	      							<%-- Begin Pagination item wrap --%>
	      						  			<pg:item>
												<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
	      											<td><a onclick="spinner();" href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndex' property='referralId' />">
	      											  <bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /> </a>
	      								  			</td>
	      								  	<%-- 	<td><bean:write name="programReferralIndex" property="officerFullName" /></td>  --%>	
	      								  			<td><bean:write name="programReferralIndex" property="casefileId" /></td>	
													<td><bean:write name="programReferralIndex" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>
													<td><bean:write name="programReferralIndex" property="juvServiceProviderName" /></td>								  
													<td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
													<td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
													<td><bean:write name="programReferralIndex" property="endDate" formatKey="date.format.mmddyyyy" /></td>
												</tr>
			      						  	</pg:item>
	    							   <%-- End Pagination item wrap --%>
	    									</logic:iterate>
	   									</logic:notEmpty>   
									</table>				
								</td>
				   			</tr>
			  		 	</table>  
<%-- END Closed Program Referrals TABLE --%>	
						<div class='spacer'></div>
<%-- BEGIN PAGINATION NAVIGATION TABLE --%>							
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
<%-- END PAGINATION NAVIGATION TABLE --%>													  
<%-- BEGIN BUTTON TABLE --%>
						<table border="0" width="100%">
							<tr>
								<td align="center">							
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
							<%--  CWS 4/18/11 - while testing, found cancel goes to Referral Update with null value exception, should go to Casefile Details page.
								  Used input type button from caseTabAssignedReferrals/referralList.jsp --%> 		
							<%--	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>  --%> 
									<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="programReferralForm" property="programReferral.casefileId"/>')"> 						      	
								</td>
							</tr>
							<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
								<logic:equal name="programReferralForm" property="allowCreate" value="true">
									<tr>
										<td align="center">	
										<jims:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_C%>'>						
											<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.createProgramReferral"/></html:submit> 
										</jims:isAllowed>
										</td>
									</tr>
								</logic:equal>
							</logic:equal>
						</table>
<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</pg:pager>

</html:form>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>