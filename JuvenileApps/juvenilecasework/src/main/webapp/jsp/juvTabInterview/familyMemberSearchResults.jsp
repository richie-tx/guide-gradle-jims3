<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 12/11/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features  --%>
<%-- 10/19/2007 L Deen		    Add instruction re red circle icon --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 09/30/2011 C Shimek        #71465 revised suspicious member icon from red dot to red flag --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/sorttable.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - familyMemberSearchResults.jsp</title>
</head>
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyMemberSearchResults" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|233">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.member"/> <bean:message key="prompt.searchResults"/></td>
	</tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Select member or click Add Selected member to Constellation button.</li> 
				<li>If the member is not any of these listed, Click the Add New Member button.</li> 
				<li>To change information entered click Back button. </li>
				<li>Note: <img src=../../images/flag_red.gif title="Indicates Member which has been identified as a potential duplicate" alt="Indicates Member which has been identified as a potential duplicate" hspace='0' vspace='0'> Indicates member which has been identified as a potential duplicate.</li> 
			</ul>
		</td> 
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%-- BEGIN DETAIL TABLE --%> 
<table align="center" cellpadding='1' cellspacing='0' border='0' width='100%'> 
	<tr> 
		<td>  
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> 
		</td> 
	</tr> 
</table> 
<div class='spacer'></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" > 
				<tr> 
					<td valign='top'> 
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td> 
				</tr> 
				<tr> 
					<td bgcolor='#33cc66' height="5"></td> 
				</tr> 
			</table> 
	  
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class='borderTableGrey'> 
							<tr> 
								<td valign='top' height="5"></td> 
							</tr>
							<logic:empty name="juvenileMemberSearchForm" property="memberSearchResults">
								<tr>
									<td>&nbsp;<strong>No Search Results Found </strong></td>
								</tr>
							</logic:empty> 

							<logic:notEmpty name="juvenileMemberSearchForm" property="memberSearchResults">
								<tr> 
									<td valign='top' align='center'> <%-- BEGIN Other members List TABLE --%> 
										<div id='searchParameters'>
											<bean:size id="searchResultsSize" name="juvenileMemberSearchForm" property="memberSearchResults"/> 
											<STRONG><bean:write name="searchResultsSize"/> &nbsp;Results for Last Name: <bean:write name="juvenileMemberSearchForm" property="name.lastName"/></STRONG></div> 
																	
											<table width='98%' cellspacing='0' cellpadding='2' class='borderTableBlue'> 
												<tr> 
													<td class="detailHead" valign='top'><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.searchResults"/></td> 
												</tr> 
												<tr> 
													<td> 
														<table width='100%' cellspacing='1'> 
															<tr bgcolor='#cccccc'> 
																<td width='1%'></td> 
																<td valign='top' class='subHead'>
																	<bean:message key="prompt.member"/> #
																	<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="memberNumber" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="1" />				
																</td> 
								                          		<td valign='top' class='subHead'>
								      								<bean:message key="prompt.name"/>
								                  					<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="name.formattedName" primarySortType="STRING" defaultSort="true"  defaultSortOrder="ASC" sortId="2" />				
								    							</td> 
	
																<td valign='top' class='subHead'>
	      															<bean:message key="prompt.sex"/>
	                  												<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="sex" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />				
	    														</td> 
																<td valign='top' class='subHead'>
																	<bean:message key="prompt.dateOfBirth"/>
																	<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="dateOfBirth" primarySortType="DATE" defaultSortOrder="ASC" sortId="4" />				
																</td> 
																<td valign='top' class='subHead'>
																	<bean:message key="prompt.SSN"/>
																	<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="ssn.formattedSSN" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />				
																</td> 
															</tr> 
<%-- Begin Pagination Header Tag--%>
								                            <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
								                            <pg:pager
								                           		 index="center"
								                           		 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
								                           		 maxIndexPages="10"
								                           		 export="offset,currentPageNumber=pageNumber"
								                           		 scope="request">
								                           			 <input type="hidden" name="pager.offset" value="<%= offset %>">
							                              <%-- End Pagination header stuff --%>
            					
						                              		<logic:iterate id="memberSearchResults" name="juvenileMemberSearchForm" property="memberSearchResults" indexId="index1">
<%-- Begin Pagination item wrap --%>
								                            	<pg:item>
																	<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									                               		<td><html:checkbox indexed="true" name="memberSearchResults" property="checked" value="true"/></td> 
								            	                      	<td>
																			<logic:notEmpty name="memberSearchResults" property="suspiciousMember">
																				<logic:notEqual name="memberSearchResults" property="suspiciousMember" value=""><bean:message key="prompt.suspiciousMember"/></logic:notEqual>
																			</logic:notEmpty>
																			<a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name="memberSearchResults" property="memberNumber"/>">
																				<bean:write name="memberSearchResults" property="memberNumber"/></a>
									                                  	</td>
																		<td valign='top'><bean:write name="memberSearchResults" property="name.formattedName"/></td> 
																		<td valign='top'><bean:write name="memberSearchResults" property="sex"/></td>
																		<td valign='top'><bean:write name="memberSearchResults" property="dateOfBirth"/></td>
																		<td valign='top' nowrap='nowrap'><bean:write name="memberSearchResults" property="ssn.formattedSSN"/></td> 
																	</tr> 
																</pg:item>
<%-- End Pagination item wrap --%>
															</logic:iterate>
														</table>
														<div class='spacer'></div>
<%-- Begin Pagination navigation Row--%>
														<table align="center">
															<tr>
																<td>
																	<pg:index>
																		<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																			<tiles:put name="pagerUniqueName"    value="pagerSearch"/>
																			<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
																		</tiles:insert>
																	</pg:index>
																</td>
															</tr>
														</table>
<%-- End Pagination navigation Row--%>
<%-- Begin Pagination Header Closing Tag --%>
													</pg:pager>
<%-- End Pagination Header Closing Tag --%>
												</td> 
											</tr> 
										</table> 
							            <%-- END Other members List TABLE --%> 
							            <div class="paddedFourPix" align="center">
							           		<html:submit property="submitAction"><bean:message key="button.addSelectedMemberToConstellation"></bean:message></html:submit>  
							  			   	<input type='button' value="Add New Member" id="addNewMember" data-href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=<bean:message key="button.addNewMember"/>">
							           	</div>
										<div class='spacer'></div>
							            <logic:notEmpty name="juvenileMemberSearchForm" property="selectedMembers">
											<table width='98%' cellspacing='0' cellpadding='2' class='borderTableBlue'> 
												<tr> 
													<td class="detailHead" valign='top'><bean:message key="prompt.constellation"/> <bean:message key="prompt.member"/> <bean:message key="prompt.list"/> - <bean:message key="prompt.family"/> #
														<bean:write name="juvenileMemberSearchForm" property="constellationNumber"/>
													</td> 
												</tr> 
												<tr> 
													<td> 
														<table width='100%' cellspacing='1'> 
															<tr bgcolor='#cccccc'> 
																<td width='1%'></td> 
																<td valign='top' class='subHead'><bean:message key="prompt.member"/> #</td> 
																<td valign='top' class='subHead'><bean:message key="prompt.name"/></td> 
																<td valign='top' class='subHead'><bean:message key="prompt.relationship"/></td> 
																<td valign='top' class='subHead'><bean:message key="prompt.deceased"/></td> 
																<td valign='top' class='subHead'><bean:message key="prompt.SSN"/></td> 
															</tr> 
						
							                    			 <logic:iterate id="constellationMembers" name="juvenileMemberSearchForm" property="selectedMembers" indexId="index2">
								          						 <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								                        			<td><html:checkbox indexed="true" name="constellationMembers" property="delete"/></td> 
								                        			<td valign='top'><a href="../juvTabInterview/familyMemberDetails.htm?addMember"><bean:write name="constellationMembers" property="memberNumber"/></a></td> 
								                        			<td valign='top'><bean:write name="constellationMembers" property="name.formattedName"/></td> 
								                        			<td valign='top'>
								                        				<html:select indexed="true" size="1" name="constellationMembers" property="relationshipId">
								  											<option value="">Select Relationship</option>
								  											<html:options name="juvenileMemberSearchForm" property="relationshipList"/>
								  										</html:select>
								  									</td> 
																	<td valign='top'><bean:write name="constellationMembers" property="deceased"/></td> 
																	<td valign='top' nowrap='nowrap'><bean:write name="constellationMembers" property="ssn.SSN"/></td> 
																</tr> 
															</logic:iterate>
														</table>
													</td> 
												</tr> 
											</table> 
											<div class='paddedFourPix' align="center">
												<input type='button' value="Remove Selected Members" onClick="javascript:alert('Remove Members')"> 
											</div>
										</logic:notEmpty> 
									</td> 
								</tr> 
							</table>
						</logic:notEmpty>
						<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
									<logic:empty name="juvenileMemberSearchForm" property="memberSearchResults">
										<input type='button' value="Add New Member" id="addNewMemberOther" data-href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=<bean:message key="button.addNewMember"/>">
									</logic:empty>
									<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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
<div class='spacer'></div>
</html:form> 
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>