<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/23/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 06/06/2009 C Shimek          - #60028 revised Submit button to Next button -->
<!-- 11/18/2009 C Shimek          - #62476 revised checkForSingleResult() to handle multiple cases -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.CSAdministerProgramReferralsConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prActiveCaseList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>

function validate()
{
	var selectedCase = document.getElementsByName("criminalCaseId");
	var length = selectedCase.length;

	if(length==0)
	{
		return false;
	}
	
	var isCaseSelected = false;
	
	for(var index=0; index < length; index++)
	{
		if(selectedCase[index].checked)
		{
			isCaseSelected = true;
			break;
		}
	}
	
	if(!isCaseSelected)
	{
		alert("Please select a case.");
		return false;
	}
	return true;
}

function checkForSingleResult(selCaseId) {
    var rbs = document.getElementsByName("criminalCaseId");
    var svs = document.getElementsByName("selectedCaseId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}
	if (rbs.length > 1 && svs.length > 0)
	{
		for (x = 0; x < rbs.length; x++)
		{
			if (rbs[x].value == svs[0].value)
			{
				rbs[x].checked = true;
				break;
			}		
		}		
			
	}		
}

</script>



</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<html:form action="/displayProgRefCaseSelect" target="content">
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  <input type="hidden" name="selectedCaseId" value="<bean:write name='cscProgRefForm' property='criminalCaseId' />" >
<%-- End Pagination header stuff --%>
<input type="hidden" name="pager.offset" value="<%= offset %>">
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
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
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
					<!--header area start-->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true">
						</tiles:insert> 
						<!--header area end-->
					</td>
				</tr>
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td align="center">
						<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="ProgramReferralsTab" />
									</tiles:insert>
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
												<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|8">
											</logic:equal>
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
												<bean:message key="prompt.initiate"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|2">
											</logic:equal> 
											<bean:message key="prompt.programReferral"/>&nbsp;-&nbsp;<bean:message key="prompt.select"/>
																								  <bean:message key="prompt.case"/></td>
										</tr>
									</table>
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td>
												<ul>
													<li>Select a case and select Next.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!--orders list start-->
									<div class="borderTableBlue" style="width:98%">
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="notFirstColumn" id="uniqueID">
										<tr class="detailHead">
											<td width="1%"></td>
											<td title="Court Division Indicator"><bean:message key="prompt.CDI"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="cdi" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" levelDeep="3"/></td>
											<td><bean:message key="prompt.case#"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="caseNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3"/></td>
											<td title="Court" align="center"><bean:message key="prompt.CRT"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="crt" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/></td>
											<td><bean:message key="prompt.offense"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="offenseDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/></td>
											<td><bean:message key="prompt.caseFiledDate"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="casefileDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="5" levelDeep="3"/></td>
											<td><bean:message key="prompt.orderStatus"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="orderStatusDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" levelDeep="3"/> </td>
											<td title="Version"><bean:message key="prompt.version"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="versionDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" levelDeep="3"/></td>
											<td><bean:message key="prompt.orderFiledDate"/>
												<jims2:sortResults beanName="cscProgRefForm" results="casesAvailableList" primaryPropSort="orderFileDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="8" levelDeep="3"/></td>
										</tr>
										<logic:iterate id="caseIndex" name="cscProgRefForm" property="casesAvailableList" indexId="index">
										  <pg:item>
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
	                      						<td align="center">
	                      							<%--html:radio idName="caseIndex" property="criminalCaseId" value="criminalCaseId" /--%>
	                      							<input type="radio" name="criminalCaseId" value="<bean:write name='caseIndex' property='criminalCaseId'/>" />
	                      						</td>
												<td><bean:write name="caseIndex" property="cdi"/></td>
												<td><bean:write name="caseIndex" property="caseNum"/></td>
												<td align="center"><bean:write name="caseIndex" property="crt"/></td>
												<td><bean:write name="caseIndex" property="offenseDesc"/></td>
												<td><bean:write name="caseIndex" property="casefileDateAsStr"/></td>
												<td><bean:write name="caseIndex" property="orderStatusDesc"/></td>
												<td><bean:write name="caseIndex" property="versionDesc"/></td>
												<td><bean:write name="caseIndex" property="orderFileDateAsStr"/></td>
											</tr>
										  </pg:item>
									 	</logic:iterate>
									</table>
								</div>
									<!--orders list end-->
										<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return validate();"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
	<br>
	<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</pg:pager></html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
