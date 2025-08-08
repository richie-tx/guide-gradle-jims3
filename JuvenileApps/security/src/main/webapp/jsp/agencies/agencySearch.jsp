<!DOCTYPE HTML>
<!-- 07/05/2005	 Aswin Widjaja  - Create JSP -->
<!-- 03/29/2006	 C Shimek       - Per ER#26357 change Reset to Refresh button -->
<!-- 07/10/2006  C Shimek       - Activity 32909, relocate error message between instructions and search block -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 07/02/2007  C Shimek       - added pagination -->
<!-- 02/04/2009  C Shimek      - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
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
<title><bean:message key="title.heading" /> - agencySearch.jsp</title>

<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/agencies/agencySearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
</head>
<!--END HEADER TAG-->

<!-- BEGIN BODY TABLE -->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.manageAgencySearch"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->	

<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<td> 
			<ul>
				<li>Enter 1 or more search values then select Find button to search for agencies.</li>
				<li>To create a new agency, click Create New Agency button. </li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">At least one field is required for search.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/displayAgencySearchResults" target="content" focus="agencyNamePrompt">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|147">
<!-- BEGIN CONTENT TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center"  valign="top">
	 		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="title.searchForAgencies"/></td>
				</tr>
				<tr>
					<td align="center" >
<!--  BEGIN SEARCH TABLE -->
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
								<td class="formDe"><html:text property="agencyNamePrompt" size="60" maxlength="60" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyType"/></td>
								<td class="formDe">
									<html:select property="agencyTypeId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="agencyTypes" value="code" label="description" /> 
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
								<td class="formDe"><html:text property="agencyIdPrompt" size="3" maxlength="3" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.jmcRepresentative"/></td>
								<td class="formDe">
									<html:select property="jmcRep">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="jmcReps" value="code" label="description" /> 
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateAgencySearchFields(this.form)&& disableSubmit(this, this.form)"><bean:message key="button.findAgencies"/></html:submit>
									<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
								</td>
							</tr>
						</table>
<!--  END SEARCH TABLE -->						
					</td>
				</tr>
			
				<logic:notEmpty name="agencyForm" property="agencies">
							<!-- Begin Pagination Header Tag-->
					<bean:define id="paginationResultsPerPage" type="java.lang.String">
						<bean:message key="pagination.recordsPerPage"></bean:message>
					</bean:define>
					<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
						<!-- End Pagination header stuff -->
					<tr>
						<td align="center" > 
							<bean:size id="agencySize" name="agencyForm" property="agencies"/>
							<bean:write name="agencySize"/> search results found.
						</td>
					</tr>		
		<%-- 		<div class=scrollingDiv200> --%>
		    		<tr>
		    			<td>	
<!-- BEGIN RESULTS TABLE -->		    				
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTable">		
								<tr>
									<td align="center">
										<table border="0" width="100%" cellspacing="1" cellpadding="2">
											<tr bgcolor="#cccccc">
												<td class="boldText"><bean:message key="prompt.agencyName"/>
                    					            <jims:sortResults beanName="agencyForm" results="agencies" 
						                                 primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" 
                        						         defaultSortOrder="ASC" sortId="1" />   
					                            </td>
                    					        <td class="boldText"><bean:message key="prompt.type"/>
					                                <jims:sortResults beanName="agencyForm" results="agencies" 
                    						             primaryPropSort="agencyType" primarySortType="STRING" defaultSort="false" 
						                                 defaultSortOrder="ASC" sortId="2" />
                        					    </td>
					                            <td class="boldText"><bean:message key="prompt.code"/>
                    					            <jims:sortResults beanName="agencyForm" results="agencies" 
						                                 primaryPropSort="agencyId" primarySortType="STRING" defaultSort="false" 
                        						         defaultSortOrder="ASC" sortId="3" /></td>
						                        <td class="boldText"><bean:message key="prompt.jmc"/></td>
												<td width="1%"></td>
											</tr>
<!--  BEGIN AGENCY ITERATER -->											
											<logic:iterate id="agencyIterator" name="agencyForm" property="agencies">
												<pg:item>
												<tr class="alternateRow">
													<td class="boldText">
														<%--a href="/<msp:webapp/>handleAgencySelection.do?action=view&agencyId=<bean:write name="agencyIterator" property="agencyId"/>" title="View <bean:write name="agencyIterator" property="agencyName"/> Details"--%>
														<bean:write name="agencyIterator" property="agencyName"/><%--/a--%>
													</td>
					                                <td class="boldText"><bean:write name="agencyIterator" property="agencyType"/></td>
                    					            <td class="boldText"><bean:write name="agencyIterator" property="agencyId"/></td>
					                                <td class="boldText">
                    					                 <logic:equal name="agencyIterator" property="projectAnalystInd" value="Y">YES</logic:equal>
					                                     <logic:equal name="agencyIterator" property="projectAnalystInd" value="N">NO</logic:equal>
                    					            </td>
													<td><a href="/<msp:webapp/>handleAgencySelection.do?action=update&agencyId=<bean:write name="agencyIterator" property="agencyId"/>" title="Edit <bean:write name="agencyIterator" property="agencyName"/> Details">Edit</a>&nbsp;|&nbsp;<a href="/<msp:webapp/>handleAgencySelection.do?action=delete&agencyId=<bean:write name="agencyIterator" property="agencyId"/>" title="Delete <bean:write name="agencyIterator" property="agencyName"/> Details">Delete</a>
													</td>
												</tr>
							
												<logic:notEmpty name="agencyIterator" property="departments">
											    	<tr>
											        	<td class="boldText" style="padding-left:30px" colspan="3"><bean:message key="prompt.departmentName"/></td>
							    					    <td width="1%"></td>
											        </tr>
<!-- BEGIN DEPARTMENT ITERATER -->											        
													<logic:iterate id="departmentIterator" name="agencyIterator" property="departments">
														<tr>
															<td style="padding-left:30px"><a href="/<msp:webapp/>handleDepartmentSelection.do?action=view&departmentId=<bean:write name="departmentIterator" property="departmentId"/>"><bean:write name="departmentIterator" property="departmentName"/></a></td>
                					                        <td></td>
                    	            				        <td><bean:write name="departmentIterator" property="departmentId"/></td>
				        	                                <td></td>
														</tr>
													</logic:iterate>
<!-- END DEPARTMENT ITERATER -->													
												</logic:notEmpty>
												</pg:item>
											</logic:iterate>
<!-- BEGIN AGENCY ITERATER -->											
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
							</pg:pager>
<!--  END RESULTS TABLE -->							
						</td>
					</tr>				
				</logic:notEmpty>
		</table>
	
		</td>
	</tr>
</table>	
<!-- END CONTENT TABLE -->
<div class='spacer'></div>
<!--BEGIN BUTTON TABLE--> 
<table align="center" border="0" width="100%">
	<tr> 
		<td align="right">
		<td>
			<input type="button" onclick="goNav('/<msp:webapp/>displayAgencyCreate.do')" value="<bean:message key="button.createNewAgency"/>" />
		</td> 
	</tr> 
</table>
<!--END BUTTON TABLE-->
</html:form>
<!-- BEGIN PAGINATION CLOSING TAG -->

<!-- END PAGINATION CLOSING TAG -->
<div align="center">[<script type="text/javascript">renderBackToTop()</script>]</div>
</body>
</html:html>