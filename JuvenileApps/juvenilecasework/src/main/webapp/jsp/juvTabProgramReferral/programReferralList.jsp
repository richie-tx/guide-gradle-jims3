<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- 10/29/2007	CShimek  	#46470 added column sorting --%>
<%-- 08/02/2013 CShimek     #75902 corrected location of Supervision # in Closed Program Referrals and changed display to expand/contract to match requirements --%>

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

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - programReferralList.jsp</title>

<%-- Javascript for emulated navigation --%>
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">

<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/juvProgramReferral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
var path = '/<msp:webapp/>';
</script>
</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayJuvenileProfileProgramReferralDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|435">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.juvenileProfile" />&nbsp;-&nbsp;<bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.list" /></td>
  </tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>

<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select a hyperlinked Last Action Date/Time to view the Referral details.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader" />
</tiles:insert> 
<%--juv profile header end--%>
<%-- BEGIN DETAIL TABLE --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" 
  maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">		
<input type="hidden" name="pager.offset" value="<%= offset %>">

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign='top'>
<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="programreferraltab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<logic:empty name="programReferralForm" property="activeReferralList">
							<div class='spacer'></div>
							<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr>
									<td class='detailHead'> <bean:message key="prompt.active" />&nbsp;<bean:message key="prompt.programReferrals" /></td>
								</tr>
								<tr class="formDeLabel">
									<td class="subHead" align='center'> No Active Program Referrals Available.</td>
								</tr>
							</table>
						</logic:empty>
<%-- BEGIN Active Program Referral TABLE --%>
	  					<logic:notEmpty name="programReferralForm" property="activeReferralList">
	    					<div class='spacer'></div>
	    					<logic:iterate id="casefileReferrals" name="programReferralForm" property="activeReferralList">
		      					<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		      						<tr>
		      							<td colspan='6' class='detailHead'><bean:message key="prompt.active" />&nbsp;<bean:message key="prompt.programReferrals" />- Supervision #&nbsp;<span title="<bean:write name="casefileReferrals" property="supervisionName" />"><bean:write name="casefileReferrals" property="casefileId" /></span>&nbsp;</td>
		      						</tr>
		      						<tr>
		      							<td>
		      								<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" >
												<tr valign="top">
													<td class="formDeLabel" width='1%'><bean:message key="prompt.lastActionDateTime" />&nbsp;
														<jims:sortResults sortId="11" beanName="casefileReferrals" results="programReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC"/>
													</td>
													<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name" />
														<jims:sortResults sortId="12" beanName="casefileReferrals" results="programReferralList" primaryPropSort="officerName" primarySortType="STRING"/>						  
													</td>
													<td class="formDeLabel"><bean:message key="prompt.referralStatus" />&nbsp;
														<jims:sortResults sortId="13" beanName="casefileReferrals" results="programReferralList" primaryPropSort="referralSubStatusDescription" primarySortType="STRING"/>						  
													</td>
													<td class="formDeLabel"><bean:message key="prompt.serviceProvider" />&nbsp;
														<jims:sortResults sortId="14" beanName="casefileReferrals" results="programReferralList" primaryPropSort="juvServiceProviderName" primarySortType="STRING"/>						  
													</td>								  
													<td class="formDeLabel"><bean:message key="prompt.program" />&nbsp;
														<jims:sortResults sortId="15" beanName="casefileReferrals" results="programReferralList" primaryPropSort="providerProgramName" primarySortType="STRING"/>						  
													</td>
													<td class="formDeLabel"><bean:message key="prompt.beginDate" />&nbsp;
														<jims:sortResults sortId="16" beanName="casefileReferrals" results="programReferralList" primaryPropSort="beginDate" primarySortType="DATE"/>						  
													</td>
												</tr>
				      							
				      							<logic:iterate id="programReferralIndex" name="casefileReferrals" property="programReferralList" indexId="indexer">							
													<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>">
														<td>
															<a href="/<msp:webapp/>displayJuvenileProfileProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndex' property='referralId' />">
															<bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
														</td>
														<td><bean:write name="programReferralIndex" property="officerName" /></td>
														<td><bean:write name="programReferralIndex" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>
														<td><bean:write name="programReferralIndex" property="juvServiceProviderName" /></td>								  
														<td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
														<td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
													</tr>
												</logic:iterate>
					      					</table>
										</td>
									</tr>
								</table>
								<div class='spacer'></div>				
							</logic:iterate>			
						</logic:notEmpty>   
<%-- END Active Program Referral TABLE --%>																			
						<div class='spacer'></div>    
<%-- BEGIN Closed Program Referral History TABLE --%>
						<logic:empty name="programReferralForm" property="closedReferralList">
							<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr>
									<td class='detailHead'> <bean:message key="prompt.closed" />&nbsp;<bean:message key="prompt.programReferrals" /></td>
								</tr>
								<tr class="formDeLabel">
									<td class="subHead" align='center'> No Closed Program Referrals Available.</td>
								</tr>
							</table>
							<div class='spacer'></div>	
						</logic:empty>	
						<logic:notEmpty name="programReferralForm" property="closedReferralList">
	   						<table align="center" width="98%" border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">
	   							<tr>
	   								<td class='detailHead' colspan="8">
	   									<a href="javascript:showHideMulti('closedreferral', 'cprInfo', 1, '/<msp:webapp/>');" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="closedreferral"></a>
	   									<bean:message key="prompt.closed" />&nbsp;<bean:message key="prompt.programReferrals" />
	   								</td>
	   							</tr>
								<tr id="cprInfo0" class="hidden" valign="top">
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr>
										  		<td class="formDeLabel" width='1%'><bean:message key="prompt.lastActionDateTime" />&nbsp;
									  				<jims:sortResults sortId="21" beanName="programReferralForm" results="closedReferralList" primaryPropSort="lastActionDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC"/>
										  		</td>
												<td class="formDeLabel" ><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name"/>
													<jims:sortResults sortId="22" beanName="programReferralForm" results="closedReferralList" primaryPropSort="officerName" primarySortType="STRING"/>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.supervision" />&nbsp;<bean:message key="prompt.#" />&nbsp;
													<div>
														<jims:sortResults sortId="25" beanName="programReferralForm" results="closedReferralList" primaryPropSort="casefileId" primarySortType="STRING"/>
													</div>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.referralStatus" />&nbsp;
													<jims:sortResults sortId="23" beanName="programReferralForm" results="closedReferralList" primaryPropSort="referralSubStatusDescription" primarySortType="STRING"/>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.serviceProvider" />&nbsp;
													<jims:sortResults sortId="24" beanName="programReferralForm" results="closedReferralList" primaryPropSort="juvServiceProviderName" primarySortType="STRING"/>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.program" />&nbsp;
													<jims:sortResults sortId="28" beanName="programReferralForm" results="closedReferralList" primaryPropSort="providerProgramName" primarySortType="STRING"/>						  
												</td>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.beginDate" />
													<div>
														<jims:sortResults sortId="26" beanName="programReferralForm" results="closedReferralList" primaryPropSort="beginDate" primarySortType="DATE"/>
													</div>
												</td>
												<td class="formDeLabel"><bean:message key="prompt.endDate" />
													<div>
														<jims:sortResults sortId="27" beanName="programReferralForm" results="closedReferralList" primaryPropSort="endDate" primarySortType="DATE"/>
													</div>
												</td>
											</tr>
											<logic:iterate id="programReferralIndex" name="programReferralForm" property="closedReferralList" indexId="indexer">
<%-- Begin Pagination item wrap --%>
												<pg:item>
													<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>">
														<td width="1%"><a href="/<msp:webapp/>displayJuvenileProfileProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name='programReferralIndex' property='referralId' />">
															<bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
														</td>
														<td><bean:write name="programReferralIndex" property="officerName" /></td>
														<td><span title="<bean:write name="programReferralIndex" property="supervisionName" />"><bean:write name="programReferralIndex" property="casefileId" /></span></td>	
														<td><bean:write name="programReferralIndex" property="referralStatusDescription" /> &nbsp;<bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>
														<td><bean:write name="programReferralIndex" property="juvServiceProviderName" /></td>								  
														<td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
														<td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
														<td><bean:write name="programReferralIndex" property="endDate" formatKey="date.format.mmddyyyy" /></td>
													</tr>
												</pg:item>
<%-- End Pagination item wrap --%>
					    					</logic:iterate>
				    				
<%-- END Closed Program Referrals TABLE --%>	
			        						<tr>
			        							<td colspan="8">
			        								<table align="center">  
			        									<tr>
			        										<td>
			        								<%-- End Pagination navigation Row--%>
						        								<pg:index>
						        									<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
						        										<tiles:put name="pagerUniqueName" value="pagerSearch"/>
						        										<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
						        									</tiles:insert>
						        								</pg:index>
						        							</td>
						        						</tr>
						        					</table>
						        				</td>
						        			</tr>			
			       				  		</table>
       				  				</td>
       				  			</tr>
       				  		</table>	
       				  		<div class='spacer'></div>						  
						</logic:notEmpty>
					</td>
				</tr>
			</table>  
<%-- END Program Referral History TABLE --%>					   
    	 	<div class='spacer'></div>				   
<%-- BEGIN BUTTON TABLE --%>
			<table border="0" width="100%">
				<tr>
					<td align="center">
						<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
						<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 						      	
					</td>
				</tr>
			</table>
<%-- END BUTTON TABLE --%>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</pg:pager>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>