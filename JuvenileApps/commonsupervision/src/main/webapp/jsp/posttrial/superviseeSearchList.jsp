<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose - Create JSP -->
<!-- 02/09/2007	 Dawn Gibler - Correct names in supervisee list -->
<!-- 02/12/2007  C. Shimek   - added feature check to compliance button  -->
<!-- 05/13/2008  L Deen		 - removed CSCD from page title  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.Features"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCasenotes/superviseeSearchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">

<html:form action="/handleSuperviseeSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|3">

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">				
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>							
									<td align="center" class="header">
									    CSCD - <bean:message key="prompt.supervisee" />&nbsp;<bean:message key="title.searchResults" />										
									</td>						
								</tr>
							</table>
						<!-- END HEADING TABLE -->

						<%-- Begin Pagination Header Tag--%>
						<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
						<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
							maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<%-- End Pagination header stuff --%>

						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<div class="instructions"><ul>
										<li>Select Supervisee and click appropriate button.</li>
									</ul>										
							</div>
						<!-- END INSTRUCTION TABLE -->
						<logic:notEmpty name="superviseeSearchForm" property="searchResults">	
							<div class="spacer">
										<bean:size id="searchResultsSize" name="superviseeSearchForm" property="searchResults"/>
										<b><bean:write name="searchResultsSize"/></b>&nbsp; supervisee(s) found in search results
							</div>            					
								 
						<!-- BEGIN DETAIL TABLE -->	
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr class="detailHead">
									<td width="1%"></td>
									<td><bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.name" />
										<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="name" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
                                    </td>
									<td><bean:message key="prompt.SPN" />
									<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="spn" primarySortType="INTEGER" sortId="2" />		
									</td>								
									<td><bean:message key="prompt.SSN" />
									<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="ssn" primarySortType="INTEGER" sortId="3" />		
									</td>										
									<td><bean:message key="prompt.dob" />
									  <jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="dateOfBirth" primarySortType="DATE" sortId="4" />
									</td>
									<td><bean:message key="prompt.race" />
									<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="dateOfBirth" primarySortType="STRING" sortId="5" />
									</td>
									<td><bean:message key="prompt.sex" />
									<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="sex" primarySortType="STRING" sortId="6" />
									</td>
									<td title="Under Active Supervision"><bean:message key="prompt.active" />
									<jims:sortResults beanName="superviseeSearchForm" results="searchResults" primaryPropSort="active" primarySortType="STRING" sortId="7" /></td>
								</tr>
			                    
								<%int RecordCounter = 0;
								String bgcolor = "";%>  
								
									<logic:iterate id="searchResultsIndex" name="superviseeSearchForm" property="searchResults">
									<logic:equal name="RecordCounter" value="0">	
									</logic:equal>
								
									 <%-- Begin Pagination item wrap --%>
  									 <pg:item>	
										<tr
										class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											<td width="1%"><input type="radio" name="selectedValue" value=<bean:write name="searchResultsIndex" property="spn"/> /></td>
											<td>
																						
											 <bean:write name="searchResultsIndex" property="name"/> <%-- TODO this will pop up a single picture of supervisee <img src="/<msp:webapp/>images/smallSupIcon.gif"> --%></td> 	
												
											<td><bean:write name="searchResultsIndex" property="spn" /></td>
											<td><bean:write name="searchResultsIndex" property="ssn" /></td>
											<td><bean:write name="searchResultsIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
											<td><bean:write name="searchResultsIndex" property="race" /></td>
											<td><bean:write name="searchResultsIndex" property="sex" /></td>
											<td><bean:write name="searchResultsIndex" property="active" /></td>							
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
										<tiles:put name="pagerUniqueName" value="pagerSearch"/>
										<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
									</tiles:insert>
								</pg:index>
						</td>
						</tr>
					  </table>
					  <%-- End Pagination navigation Row--%>
								</logic:notEmpty> 			
								
						<!-- END DETAIL TABLE -->                      
							<br>
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
									    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.') && disableSubmit(this, this.form);"><bean:message key="button.viewProfile"/></html:submit>
										<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervisee.') && disableSubmit(this, this.form);"><bean:message key="button.viewActiveCases"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>																																		 			
									</td>
								</tr>										  	
							</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>
 </pg:pager>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>