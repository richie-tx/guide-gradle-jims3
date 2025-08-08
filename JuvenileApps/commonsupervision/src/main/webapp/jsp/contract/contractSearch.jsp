<!DOCTYPE HTML>
<!-- 10/04/2006	 Clarence Shimek    Create JSP -->
<!-- 12/11/2006  Clarence Shimek    Defect#37363 revised common instructions to separate rows. -->
<!-- 07/27/2007	 Leslie Deen	    Defect #43346-revise buttons -->
<!-- jjose	08/13/2007	ER#JIMS200044394-ADD Pagination -->
<!-- 09/13/2007  Clarence Shimek    #45128 revised results into seperate forms and removed clearPaginSpecial() call from submit button to correct pagination interfernce with sort. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ include file="../jQuery.fw" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<html:javascript formName="contractForm"/>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - contract/contractSearch.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/condition_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/contracts/contractSearch.js"></script>
<script type="text/javascript">
function checkAddSelected(theForm){
    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].name == "selectedContractIds"){
			if(theForm.elements[i].checked == true){  
				changeFormActionURL('contractForm', '/<msp:webapp/>handleServiceProviderContractSelection.do?submitAction=<bean:message key="button.addSelected"/>', false);
				return true;
			}
		}     
    }
	alert("No Contract selected to add to Selected List.");
	return false;
}
//function clearPaginSpecial(){
//var pagElemSpec=document.getElementsByName("pager.offset")[0];
//pagElemSpec.value=0;
//return true;
//}
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayContractSearchResults" target="content" focus="searchContractName">
<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|415">

<br>

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
								<tiles:put name="tabid" value=""/>
							</tiles:insert>							
						<!--tabs end-->
						</td>
					</tr>
				<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
					<!-- BEGIN HEADING TABLE -->
							<table width='100%'>
								<tr>
									<td align="center" class="header">
									    <logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
											<bean:message key="prompt.service" /> - <bean:message key="prompt.manageContract" />
										</logic:equal>									
									    <logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">										
											<bean:message key="title.search" />&nbsp;<bean:message key="prompt.funding" />
										</logic:notEqual>	
									</td>
								</tr>
							</table>
					<!-- END HEADING TABLE -->
					<!-- BEGIN PAGINATION HEADER TAG -->
<%--				<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

					<pg:pager
						index="center"
						maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
						maxIndexPages="10"
						export="offset,currentPageNumber=pageNumber"
						scope="request">
						--%>
					<!-- END PAGINATION HEADER TAG -->
					<!-- BEGIN ERROR TABLE -->
						<table width='98%' align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
					<!-- END ERROR TABLE -->
					
					<!-- BEGIN SP FLOW INSTRUCTION TABLE -->		
					    <logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<ul>
											<li>Enter Search Criteria and click Submit.  Select funding(s) and click Add Selected or Remove from selected list.</li>
										</ul>	
									</td>
								</tr>
							</table>
							<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert>							
						</logic:equal>						
					<!-- END SP FLOW INSTRUCTION TABLE -->	
					<!-- BEGIN COMMON INSTRUCTION TABLE -->		
						<table width="98%" cellpadding="1" cellspacing="1">
							<tr>
								<td class="required"><bean:message key="prompt.2.diamond"/> At Least 1 Criteria Required for Search.</td>
							</tr>
							<tr>
								<td class="required"> *All dates fields must be in the format of mm/dd/yyyy.</td>
							</tr>

						</table>
					<!-- END COMMON INSTRUCTION TABLE -->	

					<!-- BEGIN SEARCH TABLE -->		
							<table width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.searchFor" /> <bean:message key="prompt.funding" /></td>
								</tr>
								<tr>
									<td>
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.name" /></td>
												<td class="formDe">
													<html:text property="searchContractName" size="30" maxlength="50" />
												</td>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.number" /></td>
												<td class="formDe">
													<html:text property="searchContractNum" size="10" maxlength="10" />
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.type" /></td>
												<td class="formDe">
													<html:select property="contractTypeId">
														<html:option key="select.generic" value="" />
														<html:optionsCollection property="contractTypes" value="code" label="description"  name="contractForm"/>
													</html:select>  
												</td>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.showExpired" /></td>
												<td class="formDe">
													<html:checkbox property="showExpired"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.programFunding" />&nbsp;<bean:message key="prompt.description" /></td>
												<td class="formDe" colspan="3">
													<html:text property="searchProgramFundingDesc" size="50"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.startDate" />&nbsp;<bean:message key="prompt.range" /></td>
												<td class="formDe" colspan="3">
													<table>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.from" /></td>
															<td></td>
															<td class="formDeLabel"><bean:message key="prompt.to" /></td>
														</tr>
														<tr>
															<td>
														    	<html:text name="contractForm" property="startDateFrom" size="10" maxlength="10" styleId="startDate"/>
															</td>
															<td> - </td>
															<td>
																<html:text name="contractForm" property="startDateTo" size="10" maxlength="10" styleId="endDate"/>															
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap></td>
												<td class="formDe" colspan="3">
													<html:submit property="submitAction" styleId="btnSubmit"><bean:message key="button.submit"/></html:submit>&nbsp;
													<html:submit property="submitAction" ><bean:message key="button.refresh"/></html:submit>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 	
<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>					

					<%  int RecordCounter = 0;
						String bgcolor = "";%>  								
				
               <!-- BEGIN CONTRACT ONLY SEARCH RESULT TABLE -->
				    <logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">							
							<logic:notEmpty name="contractForm" property="availableContracts">
								<bean:size id="contractSize" name="contractForm" property="availableContracts"/>
           		 	<table width="100%" cellpadding="4" cellspacing="1">
									<tr>
										<td align="center">
											<b><bean:write name="contractSize"/></b>&nbsp;<bean:message key="prompt.searchResults" />&nbsp;<bean:message key="prompt.found" />
										</td>
									</tr>		 							
									<tr>
										<td align="center">
											<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTable"> 
												<tr class="formDeLabel"> 
													<td><bean:message key="prompt.name" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="contractName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
													</td> 
													<td><bean:message key="prompt.number" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="number" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
													</td> 
													<td><bean:message key="prompt.type" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="contractType" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
													</td> 
													<td><bean:message key="prompt.startDate" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="startDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="4" />
													</td> 
													<td><bean:message key="prompt.endDate" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="endDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="5" />
													</td> 
													<td><bean:message key="prompt.renewal#" />
														<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="renewalNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="6" />
													</td> 
													<td></td> 
												</tr> 
												<%  RecordCounter = 0;
													bgcolor = "";%>  								

												<logic:iterate id="contractIterator" name="contractForm" property="availableContracts">
							 					<pg:item>
												<tr
													class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
 													<td>
														<a href="/<msp:webapp/>handleContractSelection.do?action=view&contractId=<bean:write name="contractIterator" property="contractId"/>" ><bean:write name="contractIterator" property="contractName"/></a> 
														<span class="paddedFourPix">
															<a href="javascript:switchText('viewHide<%out.print(RecordCounter); %>', 'desc<%out.print(RecordCounter); %>');" id=viewHide<%out.print(RecordCounter); %>>View</a>
														</span>	
													</td> 
													<td><bean:write name="contractIterator" property="number"/></td> 
													<td><bean:write name="contractIterator" property="contractType"/></td> 
													<td><bean:write name="contractIterator" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
													<td><bean:write name="contractIterator" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
													<td align=center><bean:write name="contractIterator" property="renewalNum"/></td>
													<td>
														<a href="/<msp:webapp/>handleContractSelection.do?action=update&contractId=<bean:write name="contractIterator" property="contractId"/>" ><bean:message key="prompt.edit" /></a>
														<logic:equal name="contractIterator" property="expired" value="true">
															&nbsp;|&nbsp;
															<a href="/<msp:webapp/>handleContractSelection.do?action=renew&contractId=<bean:write name="contractIterator" property="contractId"/>" ><bean:message key="prompt.renew" /></a>
														</logic:equal>
													</td>
												</tr> 
												<tr id=desc<%out.print(RecordCounter); %> class="hidden"> 
													<td class=<% out.print(bgcolor);%>></td> 
													<td colspan="6" class=<% out.print(bgcolor);%> >
													 	<bean:write name="contractIterator" property="programFundingDescription"/>
													 </td> 
												</tr> 
												</pg:item>
												</logic:iterate>
											</table> 
										</td> 
									</tr>
								</table>
							</logic:notEmpty>	
							</logic:notEqual>	 
              <!-- END CONTRACT ONLY SEARCH RESULT TABLE -->


              <!-- BEGIN SP FLOW SEARCH RESULT TABLE -->	
					    <logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">							
							<logic:notEmpty name="contractForm" property="availableContracts">
								<bean:size id="contractSize" name="contractForm" property="availableContracts"/>
           		 	<table width="98%" cellpadding="1" cellspacing="0">
									<tr>
										<td align="center">
											<b><bean:write name="contractSize"/></b>&nbsp;<bean:message key="prompt.searchResults" />&nbsp;<bean:message key="prompt.found" />
										</td>
									</tr>	
									<tr>
										<td class="detailHead" width="100%">&nbsp;
											<bean:message key="prompt.funding" /> - <bean:message key="prompt.searchResults" />
										</td>
									</tr>	 							
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTable"> 
												<tr class="formDeLabel"> 
													<td width='1%'>
														<input type="checkbox" name="selectAllContracts" OnClick="allContractsSelect(this.form, 'selectedContractIds')">
													</td>
													<td>
											    	<bean:message key="prompt.name" />
										    		<jims:sortResults beanName="contractForm" results="availableContracts" primaryPropSort="contractName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="10" hideMe="true"/>
											    </td>
													<td><bean:message key="prompt.number" /></td> 
													<td><bean:message key="prompt.type" /></td> 
													<td><bean:message key="prompt.startDate" /></td> 
													<td><bean:message key="prompt.endDate" /></td> 
													<td width="3%"><bean:message key="prompt.renewal#" /></td> 
													<td width='1%'></td> 													
												</tr> 

												<%  RecordCounter = 0;
													bgcolor = "";%>  								
												<logic:iterate id="availIter" name="contractForm" property="availableContracts">
							 					<pg:item>
												<tr
													class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
 													<td>
												<%--	<input type="checkbox" name=selectedFeatures id="<bean:write name="featureCount"/>"  value="<bean:write name="featuresIndex" property="featureId"/>" > 	 --%>												
													<jims:if name="availIter" property="availableContractValue" value="0.00" op="notEqual">
													<jims:and name="availIter" property="expired" value="false" op="equal"/>
   													<jims:then> 
  	 													<input type="checkbox" name="selectedContractIds" value=<bean:write name='availIter' property='contractId'/>>
  													</jims:then>												
													</jims:if> 
													
													<jims:if name="availIter" property="availableContractValue" value="0.00" op="equal">
													<jims:or name="availIter" property="expired" value="true" op="equal"/>
   													<jims:then> 
  	 													&nbsp;
  													</jims:then>													
													</jims:if> 
													</td>
													<td>
														<a href="/<msp:webapp/>handleContractSelection.do?action=view&contractId=<bean:write name="availIter" property="contractId"/>" ><bean:write name="availIter" property="contractName"/></a> 
														<span class="paddedFourPix">
															<a href="javascript:switchText('availViewHide<%out.print(RecordCounter); %>', 'availDesc<%out.print(RecordCounter); %>');" id="availViewHide"<%out.print(RecordCounter); %>>View</a>
														</span>										
													</td> 
													<td><bean:write name="availIter" property="number"/></td> 
													<td><bean:write name="availIter" property="contractType"/></td> 
													<td><bean:write name="availIter" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
													<td><bean:write name="availIter" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
													<td align="center"><bean:write name="availIter" property="renewalNum"/></td>
													<td>
														<a href="/<msp:webapp/>handleContractSelection.do?action=update&contractId=<bean:write name="availIter" property="contractId"/>" ><bean:message key="prompt.edit" /></a>
														<logic:equal name="availIter" property="expired" value="true">
															&nbsp;|&nbsp;
															<a href="/<msp:webapp/>handleContractSelection.do?action=renew&contractId=<bean:write name="availIter" property="contractId"/>" ><bean:message key="prompt.renew" /></a>
														</logic:equal>	
													</td>
												</tr> 
												<tr id="availDesc"<%out.print(RecordCounter); %> class="hidden"> 
													<td class=<% out.print(bgcolor);%>></td> 
													<td colspan="8" class=<% out.print(bgcolor);%> >
													 	<bean:write name="availIter" property="programFundingDescription"/>
													 </td> 
												</tr> 
												</pg:item>
												</logic:iterate>
											</table> 
										</td> 
									</tr>
									<tr>
										<td align="center">
											<input type="submit" name="submitAction" onclick="return checkAddSelected(this.form)" value="<bean:message key="button.addSelected"/>">
										</td>
									</tr>		
								</table>
								<div class="spacer"></div>	
							</logic:notEmpty>
							
							
							<logic:notEmpty name="contractForm" property="currentContracts">
								<table width="98%" cellpadding="1" cellspacing="0">
									<tr>
										<td class="detailHead" width="100%">&nbsp;
											<bean:message key="prompt.funding" /> - Selected List
										</td>
									</tr>	 							
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTable"> 
												<tr class="formDeLabel"> 
													<td width="2%"></td>
													<td><bean:message key="prompt.name" /></td> 
													<td><bean:message key="prompt.number" /></td> 
													<td><bean:message key="prompt.type" /></td> 
													<td><bean:message key="prompt.startDate" /></td> 
													<td><bean:message key="prompt.endDate" /></td> 
													<td width="2%"><bean:message key="prompt.renewal#" /></td> 
													<td width='1%'></td>
												</tr> 
												<%  RecordCounter = 0;
													bgcolor = "";%>  								
												<logic:iterate id="currentIter" name="contractForm" property="currentContracts">
							 					   <!--  Deleted  -->
												<tr
													class=<%RecordCounter++;
													bgcolor = "alternateRow";
													if (RecordCounter % 2 == 1)
														bgcolor = "normalRow";
													out.print(bgcolor);%>>
 													<td>
														<a href="/<msp:webapp/>handleServiceProviderContractRemove.do?contractId=<bean:write name="currentIter" property="contractId"/>" title='Remove <bean:write name="currentIter" property="contractName"/>'>Remove</a> 													
													</td> 
 													<td>
	 													<a href="/<msp:webapp/>handleContractSelection.do?action=view&contractId=<bean:write name="currentIter" property="contractId"/>" ><bean:write name="currentIter" property="contractName"/></a> 
														<span class="paddedFourPix">
															<a href="javascript:switchText('curViewHide<%out.print(RecordCounter); %>', 'curDesc<%out.print(RecordCounter); %>');" id="curViewHide"<%out.print(RecordCounter); %>>View</a>
														</span>										
													</td> 
													<td><bean:write name="currentIter" property="number"/></td> 
													<td><bean:write name="currentIter" property="contractType"/></td> 
													<td><bean:write name="currentIter" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
													<td><bean:write name="currentIter" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
													<td align="center"><bean:write name="currentIter" property="renewalNum"/></td>
													<td>
														<a href="/<msp:webapp/>handleContractSelection.do?action=update&contractId=<bean:write name="currentIter" property="contractId"/>" ><bean:message key="prompt.edit" /></a>
													</td>
												</tr> 
												<tr id="curDesc"<%out.print(RecordCounter); %> class="hidden"> 
													<td class=<% out.print(bgcolor);%>></td> 
													<td colspan="6" class=<% out.print(bgcolor);%> >
													 	<bean:write name="currentIter" property="programFundingDescription"/>
													 </td> 
												</tr> 
											      <!--  Deleted -->
												</logic:iterate>
											</table> 
										</td> 
									</tr>
									<tr>
										<td><input type="hidden" name="selectionMade" value="Y"></td>
									</tr>	
								</table>
							</logic:notEmpty>	
							</logic:equal>	 
              <!-- BEGIN SP FLOW SEARCH RESULT TABLE -->            							
								
							<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
							<table>
								<tr>
									<td align="center">
									<pg:index>
										<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
											<tiles:put name="pagerUniqueName" value="pagerSearch"/>
											<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
										</tiles:insert>
									</pg:index>
									</td>
								</tr>
							</table> 
							<!-- END PAGINATION NAVIGATOIN ROW -->	
								
      				
							</td>	
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!-- BEGIN BUTTON TABLE -->
						<table width="98%" border="0">
						    <logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">															
							<tr>
								<td align="center">
									<input type="button" value="Back" onclick="history.go(-1)">&nbsp;	
									<html:submit property="submitAction" onclick="return checkSelect(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>&nbsp;										
									<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<td align="center">
									<html:submit property="submitAction" ><bean:message key="button.createContract" /></html:submit>
									<logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">	
									  <html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>
									</logic:notEqual>
								</td>
							</tr>		
						</table> 
					<!-- END BUTTON TABLE -->
				</td>
			</tr>
		</table>	
<!-- END BLUE BORDER TABLE -->
</div>
<!-- BEGIN PAGINATION CLOSING TAG -->

</pg:pager>
<!-- END PAGINATION CLOSING TAG -->  
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
