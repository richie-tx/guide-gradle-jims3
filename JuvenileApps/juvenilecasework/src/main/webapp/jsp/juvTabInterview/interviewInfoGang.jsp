<!DOCTYPE HTML>
<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>
<%-- 07/20/2009     C Shimek    #61004 added timeout.js  --%>
<%-- 07/20/2013     C Shimek    #61004 started changes but put on hold --%>
<%-- 09/12/2013     C Shimek    #76047 made changes for Trait Status Update  --%>
<%-- 10/25/2013     C Shimek    #76302 commented out logic tag for gangsForm confirmation message --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewInfoGang.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/gangReferrals.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Gang Information Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<div class="spacer4px" />

<%-- BEGIN CONFIRMATION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="juvenileGangsForm" property="action" value="confirm">			
		<tr>
			<td class="confirm">Gang Information successfully added.</td>
		</tr>   				
	</logic:equal> 
	<logic:equal name="juvenileTraitsForm" property="action" value="confirm">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="action" value="confirmUpdate">
		<tr>
			<td class="confirm">Trait status successfully updated.</td>
		</tr>
	</logic:equal>
</table>	
<%-- END CONFIRMATION TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Trait Type Description and Click View button to see list.</li>
				<li>Select Add More Traits button to add more traits.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<br/>
<%-- BEGIN DETAILS TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN PROFILE/GREEN TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END PROFILE/GREEN TABS TABLE --%>

<%-- BEGIN GREEN TABS BORDER TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
							<div class="spacer"/>							
							<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
									<%-- BEGIN BLUE TABS --%>
										<tiles:insert page="/jsp/caseworkCommon/interviewInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
											<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
										</tiles:insert>	
									<%-- END BLUE TABS --%>
									</td>
								</tr>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ARL%>'> 
									<tr>
										<td align="left" bgcolor='#6699ff' height="5">
											&nbsp;<a href="/<msp:webapp/>displayGangAssessmentReferralList.do?submitAction=Link">Assessment Referral</a> 
										</td>
				  	   				</tr>
			  	   			   </jims2:isAllowed> 
							</table>
<%-- END GREEN TABS BORDER TABLE --%>	

<%-- BEGIN BLUE TABS BORDER TABLE --%>									
							<table width="98%" align="center" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td>
									  <table>
										 <tr>
										 	<td>
										 		<div class='spacer'/>
										 	</td>
										 </tr>
									  </table>
									</td>
								</tr>
								<tr>
									<td valign="top" align="center">
											<%-- BEGIN TRAITS TABLE --%>
											<span class="spacer4px"></span>
											<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
												<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
											</tiles:insert>
											<%-- END TRAITS TABLE --%>
									  <%-- BEGIN GANGS TABLE --%> 						
										<html:form action="/displayJuvenileGangsCreate" target="content">
											<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|171">
											<%-- Begin Pagination Header Tag--%>
											<bean:define id="paginationResultsPerPage" type="java.lang.String">
				  				 				<bean:message key="pagination.recordsPerPage"></bean:message>
											</bean:define>
											<pg:pager index="center"  maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
												<input type="hidden" name="pager.offset" value="<%= offset %>">
													<%-- End Pagination header stuff --%>								
													<%-- BEGIN GANG DATA TABLE --%>
												<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">	
													<%-- display detail header --%>		
													<logic:notEmpty name="juvenileGangsForm" property="gangsList">
														<tr class="detailHead">
															<td><bean:message key="prompt.entryDate"/></td>
															<td><bean:message key="prompt.gang"/></td>
															<td><bean:message key="prompt.cliqueSet"/></td>
															<td><bean:message key="prompt.status"/></td>
															<td><bean:message key="prompt.associationType"/></td>
														</tr>
													</logic:notEmpty>
																		
													<logic:empty name="juvenileGangsForm" property="gangsList" >
														<tr>
															<td colspan="6">No Gang Information Available</td>
														</tr>
													</logic:empty> 
													<%-- display detail info --%>	
										
													<logic:iterate id="gangsIndex" name="juvenileGangsForm" property="gangsList" indexId="index">
													<%-- Begin Pagination item wrap --%>
												 	  <pg:item>
															<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow");%>">
																<td><bean:write name="gangsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td>															
																<td><bean:write name="gangsIndex" property="gangName"/></td>
																<td><bean:write name="gangsIndex" property="cliqueSet"/></td>
																<td><bean:write name="gangsIndex" property="currentStatus"/></td>
																<td><bean:write name="gangsIndex" property="associationType"/></td>
															</tr>
															<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow");%>">
																<td></td>
																<td colspan="4">
																	<table width="100%">
																		<logic:notEmpty name="gangsIndex" property="descHybrid">
																			 <tr>
																				<td align="right" valign="top" width="1%" nowrap="nowrap"><b><bean:message key="prompt.descHybrid"/></b>&nbsp;</td> 
																				<td>
																					<bean:write name="gangsIndex" property="descHybrid"/>
																				</td>
																			</tr>
												 						</logic:notEmpty>
																		<tr>
																			<td  align="right" width="1%" nowrap="nowrap"><b><bean:message key="prompt.alias"/>/<bean:message key="prompt.moniker"/></b></td>
																			<td valign='top'><bean:write name="gangsIndex" property="aliasMoniker" /></td>	
																		</tr>
																		<tr>	
																			<td valign='top' align="right"><b><bean:message key="prompt.rank"/></b></td>
																			<td valign='top'><bean:write name="gangsIndex" property="rank" /></td>
																		</tr>
																	 </table>
																 </td>														    
															</tr>
													</pg:item>
												</logic:iterate>
											</table>	
										<%-- Begin Pagination navigation Row--%>
											<table align="center">
												<tr>
													<td>
														<pg:index>
															<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																<tiles:put name="pagerUniqueName" value="pagerSearch" />
																<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
															</tiles:insert>
														</pg:index>
													</td>
												</tr>
											</table>
										<%-- End Pagination navigation Row--%>
										</pg:pager>
										<%-- End Pagination Closing Tag --%>
										<div class="spacer"/>
										<table align="center">
											<tr align="center">
												 <td>
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_GANG_U%>'>
													<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">	
													<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
														<html:submit property="submitAction"><bean:message key="button.addGangInformation" /></html:submit>
													</logic:notEqual>
													<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
														<html:submit property="submitAction"><bean:message key="button.addGangInformation" /></html:submit>
													</jims2:isAllowed>
													</logic:equal>
													</logic:equal>
									  				</jims2:isAllowed>
									  			   <div class="spacer"></div>
												 </td>
							  		 		  </tr>
										</table>
									</html:form>
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							</tr>
							<%--  ER-11051 GANG TATTOO STARTS --%>
							<tr>
								<td>		
									<table width="98%" align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="detailHead" colspan="2">
												<bean:message key="prompt.tattoo"/> <bean:message key="prompt.photo"/>s
											</td>
										</tr>
										<logic:empty name='juvenilePhotoForm' property='mostRecentTattoo'>
										<tr>
											<td><b>Juvenile has no Tattoos</b></td>
										</tr>
										</logic:empty>
										<logic:notEmpty name='juvenilePhotoForm' property='mostRecentTattoo'>
											<tr>
												<td width="1%">
													<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&isTattoo=true&juvenileNumber=<bean:write name="juvenileGangsForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentTattoo.photoName"/>&source=photo&rrand=<%out.print((Math.random()*246));%>','juvTattoo',400,400);"> 																
					  									<img alt="Mug Shot Not Available" title="Juvenile Tattoo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&isTattoo=true&juvenileNumber=<bean:write name="juvenileGangsForm" property="juvenileNum"/>" width="128" border='1'>
					  								</a>
												</td>
									  			<logic:greaterThan name='juvenilePhotoForm' property='totalTattoosAvailable' value='1'>
								    			<td>
								    				&nbsp;&nbsp;<input type='button' value="View All Tattoos" onclick="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&isTattoo=true&selectedValue=&source=main&rrand=<%out.print((Math.random()*246));%>','juvTattoo',450,450);"/>
								    			</td>
								    			</logic:greaterThan>		
								  			</tr>
								  			<tr>
								  				<td align="left"><bean:write name='juvenilePhotoForm' property='mostRecentTattoo.entryDate' formatKey="date.format.mmddyyyy"/></td>
								  				<logic:greaterThan name='juvenilePhotoForm' property='totalTattoosAvailable' value='1'>
								    				<td>&nbsp;</td>
								    			</logic:greaterThan>		
								  			</tr>								
							  			</logic:notEmpty>
									</table>
								</td>
							</tr> 
							<%-- ER-11051 GANG TATTOO ENDS --%>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							</tr>
							<div class="spacer"/>	
						</table>	<%-- END BLUE TABS BORDER TABLE --%>  	
<%-- END GANG DATA TABLE --%>
						<table align="center">
							<tr>
								<td>
									<td>
										<html:button property="button.back" styleId="infoGangBack">
											<bean:message key="button.back" />
										</html:button>
									</td>
					  				<html:form action="/displayJuvenileMasterInformation" target="content">
						  				<td>
						  					<html:submit property="submitAction">
						  						<bean:message key="button.cancel"/>
						  					</html:submit>
						  				</td>
					  				</html:form>   
								</td>
							</tr>
						</table>
					    <div class="spacer"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table><%-- END DETAIL TABLE --%>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>