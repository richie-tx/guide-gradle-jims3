<!DOCTYPE HTML>

<%-- Used to display transferred offenses listing off Transferred Offenses tab in Juvenile Casefile --%>
<%--MODIFICATIONS --%>
<%-- 06/04/2013	CShimek		ER#75613 Create JSP  --%>
<%-- 10/16/2013	CShimek		#76247 replace form value check with Feature check on Add More button  --%>
<%-- 08/21/2015 RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Referrals) --%>
<%-- 08/31/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI)  fixed blue border alignment--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>



<%--BEGIN HEADER TAG--%>
<head>

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - caseTabAssignedReferrals - transferredOffensesList.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/displayJuvenileCasefileAssignedReferralsList"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Casefile Transferred Offenses</td> 
	</tr> 
	<tr>
		<td class="confirm"><bean:write name="transferredOffenseForm" property="confirmMsg" /></td>
	</tr>
</table> 
<%-- END HEADING TABLE --%> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on a hyperlinked Referral # to view Referral details.</li> 
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="toIndextab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
		</td>
	</tr>
	<tr>	
		<td>
<%--tabs end--%> 
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
  						<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								</tiles:insert>
								</td>
  							</tr>
  							<tr>
  								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
  						</table>
<%-- BEGIN DETAIL TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
									<div class='spacer'></div>
<%-- BEGIN OF REFERRALS TABLE--%> 
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
										<tr> 
											<td class="detailHead"><bean:message key="prompt.transferredOffense" /> <bean:message key="prompt.list" /></td> 
										</tr> 
								        <tr>
								        	<td> 
								        		<table cellpadding="2" cellspacing="1" width='100%'> 
													<tr bgcolor='#cccccc'> 
														<td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.referral" /> #
												<%--		<jims:sortResults beanName="transferredOffenseForm" results="transferredOffensesList" primaryPropSort="referralNumber" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" /> --%>
														</td> 
														<td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.from" /> <bean:message key="prompt.county" /></td> 
														<td align='left' class="subHead"><bean:message key="prompt.PID"/> #</td>	
														<td align='left' class="subHead"><bean:message key="prompt.offense"/> <bean:message key="prompt.description"/></td> 
														<td align='left' class="subHead"><bean:message key="prompt.severityLevel"/></td> 
														<td align='left' class="subHead"><bean:message key="prompt.DPS"/> <bean:message key="prompt.code"/></td>
														<td align='left' class="subHead"><bean:message key="prompt.offenseDate"/></td>
														<td align='left' class="subHead"><bean:message key="prompt.adjudication"/> <bean:message key="prompt.date"/></td>		             
													</tr> 
					        						<logic:iterate id="toIndex" name="transferredOffenseForm" property="existingTransferredOffensesList" indexId="index"> 
						        						<%-- Begin Pagination item wrap --%>
					              						<pg:item>
					            							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
					            								<td><bean:write name="toIndex" property="referralNum"/></td>
					             								<td width="1%" nowrap="nowrap"><bean:write name="toIndex" property="countyDesc"/></td>
					             								<td><bean:write name="toIndex" property="personId"/></td>
					             								<td><bean:write name="toIndex" property="offenseShortDesc"/></td>
					             								<td><bean:write name="toIndex" property="category"/></td>
					             								<td><bean:write name="toIndex" property="dpsCode"/></td>
					      										<td><bean:write name="toIndex" property="offenseDate" format="MM/dd/yyyy"/></td>														
					          									<td><bean:write name="toIndex" property="adjudicationDate" format="MM/dd/yyyy"/></td>
							          						</tr>
						      							</pg:item>
<%-- End Pagination item wrap --%>
													</logic:iterate> 
							    					<logic:empty name="transferredOffenseForm" property="existingTransferredOffensesList">
							    						<tr>
							    							<td align='left' colspan='7'>No Transfered Offenses found for this casefile.</td>
							    						</tr>
							    					</logic:empty>
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
<%-- END OF REFERRALS TABLE--%> 
									<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" cellpadding="1" cellspacing="1" align="center">
										<tr>
											<td align="center">
												<input type=button value=Back onclick="goNav('back')">
													<jims:isAllowed requiredFeatures='<%=Features.JCW_CF_TRANSOFF_ADD%>'>	
														<html:submit property="submitAction"><bean:message key="button.addMoreTransferredOffenses" /></html:submit>
													</jims:isAllowed>
												<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class='spacer'></div> 
								</td>
							</tr>
						</table>
<%-- END DETAIL TABLE --%> 						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class='spacer4px'></div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>