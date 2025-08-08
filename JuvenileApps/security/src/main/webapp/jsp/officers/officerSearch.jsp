<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	08/12/2005	Create JSP -->
<!-- C Shimek    		07/24/2006	Defect#33210 updated js include  -->
<!-- C Shimek			09/25/2006	Defect#35329 revise reset button to refresh button -->
<!-- C Shimek		    01/11/2007  Activity#38306 add multiple submit functionality  -->
<!-- C Shimek           06/11/2007  removed extra jims2-presentation.tld, found while testing researching defect #42107 this day  -->
<!-- C Shimek		    09/19/2007  Added pagination  -->
<!-- C Shimek		    10/02/2007  #45685 revised display of vertical bar before "Inactivate" link to not display if Edit link not displaying  -->
<!-- C Shimek           02/05/2009  #56860 add Back to Top  -->
<!-- R Capestani		10/05/2015  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading" /> - officerSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<!--JAVASCRIPT FILES FOR THIS PAGE -->
<html:javascript formName="officerSearchForm"/>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/officer/officerSearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">Search for&nbsp;<bean:message key="title.officerProfile"/></td>
  </tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click find to search for officer profiles.</li>
		<li>From results list, click officer name hyperlink to view officer profile details.</li>
       </ul>
	</td>
  </tr>
  <tr>
    <td class="required">+ Indicates Last Name is required to use this search field.</td>
  </tr>
<%-- 07/24/2006 CShimek  - added this required value based on edits found in js --%>
  <tr>
    <td class="required">++ Indicates at least one other search field required to search.</td>
  </tr>  
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
<html:form action="/displayOfficerProfileSearchResults" target="content" focus="lastNamePrompt">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|192">	
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.search" />&nbsp;for&nbsp;<bean:message key="prompt.officers" /></td>
				</tr>
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.lastName" /></td>
								<td class="formDe"><html:text property="lastNamePrompt" size="30" maxlength="75"/></td>
								<td class="formDeLabel">+<bean:message key="prompt.firstName" /></td>
								<td class="formDe"><html:text property="firstNamePrompt" size="25" maxlength="50"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap>++<bean:message key="prompt.officerType"/></td> 
								<td class="formDe">
								   <html:select property="officerTypeId">
									   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
									   <html:optionsCollection property="officerTypes" value="code" label="description" />
									</html:select>  
								</td>    
								<td class="formDeLabel"><bean:message key="prompt.userId"/></td> 
                                <td class="formDe"><html:text property="userIdPrompt" size="8" maxlength="8"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.departmentCode" /></td>
								<td class="formDe" colspan="3">
									<html:text property="departmentIdPrompt" size="3" maxlength="3"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.departmentName" /></td>
								<td class="formDe" colspan="3">
									<html:text property="departmentNamePrompt" size="60" maxlength="60"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.status"/></td>
								<td class="formDe" colspan="3">
									<html:select property="statusId">		
										<html:option value=""><bean:message key="select.generic" /></html:option>
									    <html:optionsCollection name="officerForm" property="officerStatuses" value="code"
										label="description" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.badgeNumber" /></td>
								<td class="formDe"><html:text property="badgeNumPrompt" size="11" maxlength="11"/></td>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.other"/> <bean:message key="prompt.id"/> <bean:message key="prompt.number"/></td>
								<td class="formDe"><html:text property="otherIdNumPrompt" size="10" maxlength="10"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.userId" /></td>
								<td class="formDe" colspan="3"><html:text property="managerId" size="5" maxlength="5"/></td>
							</tr>							
														
							<tr>
								<td class="formDeLabel"></td>
								<td colspan="3" class="formDe">
                                   <html:submit onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form)" property="submitAction">
					                   <bean:message key="button.find" />
				                   </html:submit>
                                   <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					                   <bean:message key="button.refresh" />
				                   </html:submit>
								</td>
							</tr>
						</table>
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
<!-- END PAGINATION HEADER TAG -->	
              <logic:notEmpty name="officerForm" property="officerProfiles">
				<tr>
					<td align="center">
					<bean:size id="officerProfileSize" name="officerForm" property="officerProfiles"/>
				    <bean:write name="officerProfileSize"/> search results found.
					  <div class="scrollingDiv200" >
						<table border="0" width="100%" cellspacing="1" cellpadding="2">
							<tr height="20%" bgcolor="#cccccc">
								<td class="formDeLabel"><bean:message key="prompt.officerName"/>
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="lastName" primarySortType="STRING"
                                       secondPropSort="firstName" secondarySortType="STRING" defaultSort="true" 
                                       defaultSortOrder="ASC" sortId="1" />
                                </td>
								<td class="formDeLabel"><bean:message key="prompt.deptCode"/>
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="departmentId" primarySortType="STRING"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="2" />
                                </td>
								<td class="formDeLabel"><bean:message key="prompt.departmentName"/>
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="departmentName" primarySortType="STRING"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="3" />
                                </td>
								<td class="formDeLabel"><bean:message key="prompt.other"/> <bean:message key="prompt.id"/>&nbsp;#
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="otherIdNum" primarySortType="NUMERIC"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="4" />
                                </td>
								<td class="formDeLabel"><bean:message key="prompt.badge"/>&nbsp;#
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="badgeNum" primarySortType="NUMERIC"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="5" /></td>
								<td class="formDeLabel"><bean:message key="prompt.userId"/>
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="userId" primarySortType="STRING"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="6" /></td>
                                       
                                <td class="formDeLabel">Mgr&nbsp;<bean:message key="prompt.userId" />
								   <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="managerId" primarySortType="STRING"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="7" /></td>
                                
								<td class="formDeLabel" colspan="4"><bean:message key="prompt.status"/>
								    <jims:sortResults beanName="officerForm" results="officerProfiles" 
                                       primaryPropSort="status" primarySortType="STRING"
                                       defaultSort="false" defaultSortOrder="ASC" sortId="8" /></td>
							</tr>
							<logic:iterate id="officerProfileIndex" name="officerForm" property="officerProfiles" indexId="index">
							 <pg:item>
							  <tr height="10%" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td>
						    		  <a href="/<msp:webapp/>handleOfficerProfileSelection.do?action=view&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='View <bean:write name="officerProfileIndex" property="lastName"/> details' ><bean:write name="officerProfileIndex" property="lastName"/>,&nbsp;<bean:write name="officerProfileIndex" property="firstName"/>&nbsp;<bean:write name="officerProfileIndex" property="middleName"/></a>
							    </td>
							    <td ><bean:write name="officerProfileIndex" property="departmentId"/></td>
								<td ><bean:write name="officerProfileIndex" property="departmentName"/></td>
								<td ><bean:write name="officerProfileIndex" property="otherIdNum"/></td>
								<td ><bean:write name="officerProfileIndex" property="badgeNum"/></td>
								<td ><bean:write name="officerProfileIndex" property="userId"/></td>
								<td ><bean:write name="officerProfileIndex" property="managerId"/></td>
								<td ><bean:write name="officerProfileIndex" property="status"/></td>

								<td>						
									
									 <logic:equal name='officerProfileIndex' property='updatableStatus' value='Y' >  
										<a href="/<msp:webapp/>handleOfficerProfileSelection.do?action=update&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='Edit OFFICER PROFILE'>Edit</a>
									</logic:equal>
									
									<logic:notEqual name='officerProfileIndex' property='updatableStatus' value='Y' >										
										<jims:isAllowed requiredFeatures="<%=Features.JCW_OFFICERPROFILEJPOUPDATE%>">
											<logic:equal name='officerProfileIndex' property='limitedUpdatableStatus' value='Y' >   
												<a href="/<msp:webapp/>handleOfficerProfileSelectionLimit.do?action=update&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='Edit OFFICER PROFILE'>Edit</a>
											</logic:equal>
										</jims:isAllowed>
									</logic:notEqual>
									
								</td>								
								<td>		
									<jims:isAllowed requiredFeatures="<%=Features.MOP_OFFICERTRAINING%>">
											&nbsp;|&nbsp;<a href="/<msp:webapp/>handleOfficerTraining.do?submitAction=Link&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='ADD OFFICER TRAINING'>Training</a>
									</jims:isAllowed>									
								</td>
								<td>
									
									<logic:equal name='officerProfileIndex' property='deletableStatus' value='Y' >  
											<logic:equal name='officerProfileIndex' property='updatableStatus' value='Y' >
												&nbsp;|&nbsp;<a href="/<msp:webapp/>handleOfficerProfileSelection.do?action=inactivate&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='Inactivate OFFICER PROFILE'>Inactivate</a>
											</logic:equal>
											<!-- 
											<logic:notEqual name='officerProfileIndex' property='updatableStatus' value='Y' >										
												<a href="/<msp:webapp/>handleOfficerProfileSelection.do?action=inactivate&officerProfileId=<bean:write name="officerProfileIndex" property="officerProfileId"/>" title='Inactivate OFFICER PROFILE'>Inactivate</a>
											</logic:notEqual>
											-->
									</logic:equal>
									
									
								</td>
							  </tr>
							  </pg:item>	
							</logic:iterate>  
						  </table>
						</div>
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
			</logic:notEmpty>
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->				
	</html:form>
			</table><br>
			<!-- BEGIN BUTTON TABLE -->
			<table>
			<jims:isAllowed requiredFeatures="MOP-CREATEOFF">
				<tr>
                  <td>
                    <html:form action="/displayOfficerProfileCreate" target="content">
	                  <html:hidden name="officerForm" property="action" value="create"/>
				      <html:submit property="submitAction">
					     <bean:message key="button.createNewOfficer" />
				      </html:submit>
				      
                    </html:form> </td>
				</tr>
			</jims:isAllowed>
			</table>
			<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>