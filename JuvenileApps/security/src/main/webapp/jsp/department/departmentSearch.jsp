<!DOCTYPE HTML>
<!-- 08/22/2005	 Hien Rodriguez - Create JSP -->
<!-- 03/29/2006	 C Shimek       - Per ER#26357 change Reset to Refresh button -->
<!-- 07/10/2006  C Shimek       - Activity 32909, relocate error message between instructions and search block -->
<!-- 12/05/2006  C Shimek       - Activity 37546, correct placement of buttons. Also corrected departmentStatus dropdown to use codeId instead of code -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 07/02/2007  C Shimek       - added pagination -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

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
<title><bean:message key="title.heading" /> - departmentSearch.jsp</title>
<html:javascript formName="departmentSearchForm"/>
<!--JAVASCRIPT FILE FOR THIS PAGE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/department/departmentSearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
	<!-- BEGIN HEADING TABLE -->
	<table width="98%">
		<TBODY>
			<tr>
				<td align="center" class="header"><bean:message key="title.departmentSearch" /></td>
			</tr>
		</TBODY>
	</table>
	<!-- END HEADING TABLE -->
	<!-- BEGIN ERROR TABLE -->
	<table width="98%" align="center">
		<TBODY>
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</TBODY>
	</table>
	<!-- END ERROR TABLE -->	
	<!-- BEGIN INSTRUCTION TABLE -->
	<table width="98%" align="center">
		<TBODY>
			<tr>
				<td>
				<ul>
					<li>Enter search criteria and then select "Find Departments" button
					to perform search.</li>
					<li>Select "Create New Department" button to create new department.

					</li>
				</ul>
				</td>
			</tr>
			<tr>
				<td class="required">At least one field is required for search.</td>
			</tr>
		</TBODY>
	</table>
	<!-- END INSTRUCTION TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/departmentFindDepartments" target="content" focus="agencyName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|107">	
	<TBODY>
		<tr>
			<td align="center" valign="top">
<!-- BEGIN SEARCH TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<TBODY>
					<tr>
						<td class="detailHead"><bean:message key="prompt.searchForDepartments" /></td>
					</tr>
					<tr>
						<td align="center">
						<table border="0" cellspacing=1 cellpadding="2" width="100%">
							<TBODY>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
									<td class="formDe"><html:text property="agencyName" size="60" maxlength="60" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentName" /></td>
									<td class="formDe"><html:text property="departmentName" size="60" maxlength="60" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentCode" /></td>
									<td class="formDe"><html:text property="departmentId" size="3" maxlength="3" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentStatus" /></td>
									<td class="formDe"><html:select property="statusId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="statusTypes" value="codeId" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.originatingAgencyId" /></td>
									<td class="formDe"><html:text property="originatingAgencyId" size="9" maxlength="9" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.setcicAccess" /></td>
									<td class="formDe"><html:select property="setcicAccessId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="setcicAccessTypes" value="code" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel"></td>
									<td class="formDe"><html:submit onclick="return validateDepartmentSearchFields(this.form) && disableSubmit(this, this.form);" property="submitAction">
											<bean:message key="button.findDepartments"></bean:message></html:submit>
										 	<html:submit  property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh" /></html:submit>
	      							</td>
								</tr>
							</TBODY>
						</table>
<!-- END SEARCH TABLE -->
					</td>
				</tr>
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
			<logic:notEmpty name="departmentForm" property="agencies">
				<tr>
					<td align="center">
    					<bean:size id="DepartmentsSize" name="departmentForm" property="departmentList"/>
		                <bean:write name="DepartmentsSize"/>&nbsp;search results found.	
					</td>
				</tr>
				<tr>
					<td>
<!-- BEGIN RESULTS TABLE -->		    				
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class='borderTable'>									
							<tr>
								<td align="center">
			<!-- 		<div class=scrollingDiv200>  -->
									<table border="0" width="100%" cellspacing="1" cellpadding="2">
									    <jims:sortResults beanName="departmentForm" results="departmentList" 
    		                                    primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" 
            		                            defaultSortOrder="ASC" sortId="1" hideMe="true" />
										<logic:iterate id="agencyIndex" name="departmentForm" property="agencies">
											<pg:item>
											<tr bgcolor="#999999">
												<td class="subhead" colspan="9"><bean:message key="prompt.agencyName" />:&nbsp;<bean:write name="agencyIndex" property="agencyName" />
												</td>
											</tr>
											<tr bgcolor="#cccccc">
												<td width="1%"></td>
												<td class="subhead"><bean:message key="prompt.departmentName" />
												    <jims:sortResults beanName="departmentForm" results="departmentList" 
                    				                    primaryPropSort="departmentName" primarySortType="STRING" defaultSort="true" 
                                    				    defaultSortOrder="ASC" sortId="2" hideMe="true" />
	                                            </td>
												<td class="subhead"><bean:message key="prompt.deptCode" /></td>
												<td class="subhead"><bean:message key="prompt.orgCode" /></td>
												<td class="subhead"><bean:message key="prompt.originatingAgency#" /></td>
												<td class="subhead"><bean:message key="prompt.setcicAccess" /></td>
												<td class="subhead"><bean:message key="prompt.deptStatus" /></td>
											</tr>
											<logic:iterate id="departmentIndex" name="agencyIndex" property="departments" indexId="index">
												<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<td align="center">
														<%boolean inUse=true;
														  boolean inActive=true; %>
														<logic:equal name="departmentIndex" property="inUse" value="false">
														 	 <%inUse=false;%>
														</logic:equal>	
														<logic:equal name="departmentIndex" property="status" value="Active">
														 	 <%inActive=false;%>
														</logic:equal>
														<logic:equal name="departmentIndex" property="status" value="ACTIVE">
														 	 <%inActive=false;%>
														</logic:equal>
														<logic:equal name="departmentIndex" property="statusId" value="A">
														 	 <%inActive=false;%>
														</logic:equal>	
														<input type="radio" name="selectedDept" value=<bean:write name='departmentIndex' property='departmentId'/> onClick="displaySearchButtonList(<%=inUse%>,<%=inActive%>);changeDeptId('<bean:write name="departmentIndex" property="departmentId"/>');">
																</td>
																<td><a href="/<msp:webapp/>handleDepartmentSelection.do?action=view&departmentId=<bean:write name="departmentIndex" property="departmentId"/>" title='View <bean:write name="departmentIndex" property="departmentName" /> details'>
  																	<bean:write name="departmentIndex" property="departmentName" /></a>
															</td>
															<td><bean:write name="departmentIndex" property="departmentId" /></td>
															<td><bean:write name="departmentIndex" property="orgCode" /></td>
															<td><bean:write name="departmentIndex" property="originatingAgencyId" /></td>
															<td><bean:write name="departmentIndex" property="setcicAccess" /></td>
															<td><bean:write name="departmentIndex" property="status" /></td>
												</tr>
											</logic:iterate>
											</pg:item>			
										</logic:iterate>
									</table>
					<!-- 		</div>  -->
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
			</logic:notEmpty>
		</TBODY>
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->						
</html:form>
<!--  END FIRST FORM -->
</table>
<table width="98%" border="0" cellpadding="0" cellspacing="0">					
<!--  BEGIN SECOND FORM -->
<html:form styleId="secondForm" name="departmentForm" action="/departmentDisplayDetails" target="content">
					<tr>
						<td align="center">
							<input type="hidden" id="deptId" name="departmentId" value=""/>
							<table border="0" cellpadding="0" cellspacing="2"> 
								<tr>
									<td id="btnUpdate" class="hidden" >							
										<input type="submit" value="<bean:message key='button.update'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentDisplayDetails.do?action=deptUpdate', false);">
									</td>
									<td id="btnDelete" class="hidden">
										<input type="submit" value="<bean:message key='button.delete'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentDisplayDetails.do?action=deptDelete', false);">
									</td>
									<td id="btnCopy" class="hidden">
										<input type="submit" value="<bean:message key='button.copy'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentDisplayDetails.do?action=deptCopy', false);">
									</td>
									<td id="btnCreateContact" class="hidden" >
										<input type="submit" value="<bean:message key='button.createContact'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactCreate', false);">
									</td>
									<td id="btnModifyContact" class="hidden" >
										<input type="submit" value="<bean:message key='button.modifyContact'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactModify', false);">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</TBODY>
	<table align="center" border="0" width="100%">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" id="btnCreateDepartment">
					<input type="submit" value="<bean:message key='button.createNewDepartment'/>" name="submitAction" onclick="javascript:changeFormActionURL('secondForm', '/<msp:webapp/>departmentCreateDepartments.do?action=deptCreate', false);">
				</td>
			</tr>
		</table>
		</html:form>
<!--  END SECOND FORM -->	
	</table>

<!--END BUTTON TABLE/FORM -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
