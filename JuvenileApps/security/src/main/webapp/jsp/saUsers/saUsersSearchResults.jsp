<!DOCTYPE HTML>
<!-- Used to search for Security Administrator Users. -->
<!--MODIFICATIONS -->
<!-- SPrakash 05/10/2005    Create JSP -->
<!-- Sprakash 05/12/2005    Modified -->  
<!-- CShimek  03/17/2006	Defect#29751 add comman after last name -->
<!-- CShimek  03/30/2006    Per ER#26357, revised reset button to refresh button -->
<!-- CShimek  12/05/2006    #37545 - revised display location of Next button -->
<!-- CShimek  01/12/2007    #38306 add multiple submit functionality  -->
<!-- LDeen	  03/29/2007    #40530 change page title due to duplication  -->
<!-- CShimek  06/07/2007    revised to use pagination -->
<!-- CShimek  07/26/2007    Added +indicator and increased agency name field length to match other MA Task search pages -->
<!-- CShimek  02/06/2009    #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> saUsersSearchResults.jsp </title>

<!-- INCLUDE JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/saUsers/saUsersSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

<!-- STRUTS VALIDATIONS-->
<html:javascript formName="saUsersForm" />

</head>
<!--END HEADER TAG-->  

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySAUserSearchResults" target="content" focus="lastName" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|155">	
<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.saUsersManage" /></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click find to search for users.</li>
				<li>Select radio button for user you want to update and select Next button.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr>
					<td class="detailHead"><bean:message key="prompt.searchUsers" /></td>
				</tr>
				<tr>
					<td align="center"><!-- BEGIN SEARCH TABLE -->				
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.lastName" /></td>
								<td class="formDe"><html:text property="lastName" size="25" maxlength="20" /></td>
								<td class="formDeLabel" nowrap width="1%">+<bean:message key="prompt.firstName" /></td>
								<td class="formDe"><html:text property="firstName" size="25" maxlength="20" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.userType" /></td>
								<td class="formDe" colspan="3"><html:select size="1" property="userTypeId" >
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="userTypes" value="code" label="description" />
								</html:select></td>
							</tr>
							<tr>	
								<td class="formDeLabel" nowrap><bean:message key="prompt.userId" /></td>
								<td class="formDe" colspan="3"><html:text property="logonId" size="25" maxlength="20" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.agencyName" /></td>
								<td class="formDe" colspan="3"><html:text property="agencyName" size="62" maxlength="60" /></td>
							</tr>
							<tr> 
								<td class="formDeLabel"></td>
								<td class="formDe" colspan="3">
								<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);;">
									<bean:message key="button.find"></bean:message>
								</html:submit>
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.refresh"></bean:message>
								</html:submit>
								</td>
							</tr>
						</table>					
					<!-- END SEARCH TABLE -->
					</td>
				</tr>
</html:form>			
				<tr>
					<td>
						<table cellpadding="4" cellspacing="0" border="0" width="98%">	
<html:form action="/displaySAUserUpdate" target="content">
<!-- BEGIN PAGINATION HEADER TAG -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
 <%--   <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
<!-- END PAGINATION HEADER TAG -->
							<tr>
								<td>
<!-- BEGIN SEARCH RESULTS CRITERIA TABLE -->
									<table width="98%" align="center" cellpadding="0" cellspacing="0" border="0">
						  				<tr>
											<td align="center"><b><bean:write name="saUsersForm" property="searchResultSize" /></b> 
												Search results for &nbsp;
												<logic:notEqual name="saUsersForm" property="lastName" value="">Last Name: 
													<bean:write name="saUsersForm" property="lastName" />
												</logic:notEqual> &nbsp;
												<logic:notEqual name="saUsersForm" property="firstName" value="">First Name: 
													<bean:write name="saUsersForm" property="firstName" />
												</logic:notEqual> &nbsp;
												<logic:notEqual name="saUsersForm" property="userTypeId" value="">User Type: 
													<bean:write name="saUsersForm" property="userTypeId" />
												</logic:notEqual> &nbsp;
												<logic:notEqual name="saUsersForm" property="logonId" value="">User ID: 
													<bean:write name="saUsersForm" property="logonId" />
												</logic:notEqual> &nbsp;
												<logic:notEqual name="saUsersForm" property="agencyName" value="">Agency Name: 
												<bean:write name="saUsersForm" property="agencyName" />
											</logic:notEqual>
											</td>
										</tr>     
									</table>
<!-- END Search RESULTS CRITERIA TABLE -->
								</td>
							</tr>
							<tr>
								<td>
<!-- BEGIN DETAIL TABLE -->
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class='borderTable'>	
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<!-- display detail header -->  
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.name" />
														    <jims:sortResults beanName="saUsersForm" results="users" 
        		                				                    primaryPropSort="userLastName" primarySortType="STRING"
                		                        				    secondPropSort="userFirstName" secondarySortType="STRING" defaultSort="true" 
				                        		                    defaultSortOrder="ASC" sortId="1" /></td>
														<td><bean:message key="prompt.userId" />
												    		<jims:sortResults beanName="saUsersForm" results="users" 
                                            						primaryPropSort="logonId" primarySortType="STRING"
						                                            defaultSort="false" defaultSortOrder="ASC" sortId="2" /></td>
														<td><bean:message key="prompt.agency" />
														    <jims:sortResults beanName="saUsersForm" results="users" 
                                						            primaryPropSort="agencyName" primarySortType="STRING"
                                        						    defaultSort="false" defaultSortOrder="ASC" sortId="3" /></td>
														<td nowrap align="center"><bean:message key="prompt.isLiaison" /></td>
														<td nowrap align="center"><bean:message key="prompt.isASA" /></td>
														<td nowrap align="center"><bean:message key="prompt.isSA" /></td>
														<td nowrap align="center"><bean:message key="prompt.isMA" /></td>
													</tr>
								<!-- display detail info -->
													<logic:iterate id="userIndex" name="saUsersForm" property="users" indexId="index">
														<pg:item>	
															<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<!-- user name should link to details page once security inquiries is ready -->		
																<td>
																	<html:radio name="saUsersForm" property="selectedLogonId" idName="userIndex" value="logonId"/>
																		<input type="hidden" name="userAgencyId" value=<bean:write name="userIndex" property="agencyId" /> >
																		<input type="hidden" name="userAgencyName" value='<bean:write name="userIndex" property="agencyName" />' >										
																</td>
																<td class="boldText">				
																	<bean:write name="userIndex" property="userLastName" />,&nbsp;
																	<bean:write name="userIndex" property="userFirstName" />
																</td>
																<td>
																	<bean:write name="userIndex" property="logonId" />
																</td>
																<td>
																	<bean:write name="userIndex" property="agencyName" />
																	<logic:equal name="userIndex" property="agencyHasSA" value="false">**
																	</logic:equal>
																</td>
																<td align="center" class="boldText">
																	<logic:equal name="userIndex" property="isLiason" value="true">X
																	</logic:equal>
																</td>
																<td align="center" class="boldText">
																	<logic:equal name="userIndex" property="isASA" value="true">X
																	</logic:equal>
																</td>
																<td align="center"  class="boldText">
																	<logic:equal name="userIndex" property="isSA" value="true">X
																	</logic:equal>
																</td>
																<td align="center" class="boldText">
																	<logic:equal name="userIndex" property="isMA" value="true">X
																	</logic:equal>
																</td>
														  </tr>
														</pg:item>						
													</logic:iterate>
													<logic:empty name="saUsersForm" property="users" >
														<tr><td align="center" bgcolor="#FFFFFF"><b>No records Found</b></td></tr>"
													</logic:empty>	
												</table>
											</td>
										</tr>		
										<tr>
											<td>	
<!-- BEGIN PAGINATION NAVIGATOIN TABLE -->							
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
<!-- END PAGINATION NAVIGATOIN TABLE -->	
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>					
									<table align="center">
										<tr>
											<td align="center" colspan="7">**This Agency does not have an SA Role defined - click <a id="addRole" href="/<msp:webapp/>displaySARoleCreate2.do?submitAction=Next" onclick="return nameSelectCheck('<msp:webapp/>')";>here</a> to Create a SA Role</td> 
										</tr>
									</table>
								</td>
							</tr>	
							<tr>
								<td>	
<!-- BEGIN BUTTONS TABLES -->      
									<table align="center">
										<tr>
											<td>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.next"></bean:message>
												</html:submit>
										 	</td>
									    </tr>	  
									</table>
<!-- BEGIN BUTTON TABLE -->									
								</td>
							</tr>
						</table>
					</td>
				</tr>
		</table>
<!-- END DETAIL TABLES -->
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->
</html:form>
<!-- END FORM -->
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
<!--END BODY TAG-->
</html:html>