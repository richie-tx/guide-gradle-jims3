<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/04/2006	 Clarence Shimek  - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - contract/contractSearchResults.jsp</title>

<!-- JavaScript files -->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>
function switchText(theLink, theLiteralRowID){
	if (document.getElementById(theLink).innerHTML == "View")
	{
		show(theLiteralRowID, 1, 'row');
		document.getElementById(theLink).innerHTML = "Hide"
	}else {
		show(theLiteralRowID, 0);
		document.getElementById(theLink).innerHTML = "View"
	}
}
</script>
</head>
<body>
<html:form action="/displayContractSelect" target="content">
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> 
					<tr> 
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
					</tr> 
					<tr> 
						<td valign="top" align="center">
				<!-- BEGIN HEADING TABLE --> 
							<table width="100%"> 
								<tr> 
									<td align="center" class="header"><bean:message key="prompt.funding" />&nbsp;<bean:message key="prompt.searchResults" /></td> 
								</tr> 
							</table> 
				<!-- END HEADING TABLE --> 
				<!-- BEGIN PAGINATION HEADER TAG -->
				<br>
				<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

				<pg:pager
					index="center"
					maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					maxIndexPages="10"
					export="offset,currentPageNumber=pageNumber"
					scope="request">
					<input type="hidden" name="pager.offset" value="<%= offset %>">
				<!-- END PAGINATION HEADER TAG -->
				
				<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
				<!-- END ERROR TABLE -->
				
                <!-- BEGIN INSTRUCTION TABLE --> 
							<table width="98%" border="0"> 
								<tr> 
									<td>
										<ul> 
											<li>Select a contract to Update or Renew the funding.</li> 
											<li>Select the hyperlink to view details or select Create Contract button.</li> 
										</ul>
									</td> 
								</tr> 
							</table> 
                <!-- END INSTRUCTION TABLE --> 							
                <!-- BEGIN  TABLE --> 
							<div align="center">
								<bean:size id="contractSize" name="contractForm" property="contracts"/>
								<b><bean:write name="contractSize"/></b>&nbsp;results for Type:<bear:write name="contractForm" property="contractType" /> 							
							</div> 
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue"> 
								<tr class="detailHead"> 
									<td><bean:message key="prompt.name" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="number" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
									</td> 
									<td><bean:message key="prompt.number" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="number" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="2" />
									</td> 
									<td><bean:message key="prompt.type" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="contractType" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="3" />
									</td> 
									<td><bean:message key="prompt.startDate" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="startDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="4" />
									</td> 
									<td><bean:message key="prompt.endDate" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="endDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="5" />
									</td> 
									<td colspan="2"><bean:message key="prompt.renewal#" />
									<jims2:sortResults beanName="contractForm" results="availableContract" primaryPropSort="renewalNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="6" />
									</td> 
								</tr> 
								<%  int RecordCounter = 0;
									String bgcolor = "";%>  								
								<logic:iterate id="contractIterator" name="contractForm" property="contracts">
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
											<a href="javascript:switchText('viewHide<%out.print(RecordCounter); %>', 'desc<%out.print(RecordCounter); %>');" id="viewHide"<%out.print(RecordCounter); %>>View</a>
										</span>										
									</td> 
									<td><bean:write name="contractIterator" property="number"/></td> 
									<td><bean:write name="contractIterator" property="contractType"/></td> 
									<td><bean:write name="contractIterator" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
									<td><bean:write name="contractIterator" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
									<td><bean:write name="contractIterator" property="renewalNum"/></td>
									<td>
										<a href="/<msp:webapp/>handleContractSelection.do?action=update&contractId=<bean:write name="contractIterator" property="contractId"/>" ><bean:message key="prompt.edit" /></a>&nbsp;|&nbsp;
										<a href="/<msp:webapp/>handleContractSelection.do?action=renew&contractId=<bean:write name="contractIterator" property="contractId"/>" ><bean:message key="prompt.renew" /></a>
									</td>
								</tr> 
								<tr id="desc"<%out.print(RecordCounter); %> class="hidden"> 
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
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
					<tr>
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
					</tr>	
<!-- END PAGINATION NAVIGATOIN ROW -->						
				</table> 
				<br> 
          <!-- BEGIN  BUTTON TABLE --> 
				<table border="0" width="100%"> 
					<tr> 
						<td align="center">
							<input type="button" value="Back" onclick="history.go(-1)">&nbsp;
							<html:submit property="submitAction"><bean:message key="button.createContract"/></html:submit>&nbsp;							
							<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
						</td> 
					</tr> 
				</table> 
          <!-- END BUTTON TABLE --> </td> 
			</tr> 
		</table> 
    <!-- END  TABLE --> 
  </div> 
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->  
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div> 
</body>
</html:html>
