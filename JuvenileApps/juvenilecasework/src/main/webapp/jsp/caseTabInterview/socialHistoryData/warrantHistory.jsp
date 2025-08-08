<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF//pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>

<html:form action="/handleSocialHistoryData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|103"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 


	<%-- BEGIN HEADING TABLE --%> 
	<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				Juvenile Casework - Conduct Interview<br>
				Social History Data - Warrant History
			</td>
		</tr> 
	</table> 
	<%-- END HEADING TABLE --%> 
	
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
	
	<%-- BEGIN INSTRUCTION TABLE --%> 
	<div class='spacer'></div>
	<table width="98%" border="0"> 
		<tr> 
			<td> 
				<ul> 
					<li>Click check box to exclude from report, and click on Save Changes button to save the changes.</li>
			        <li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
			        <li>Click Back button to return to previous page. </li>
				</ul>
			</td>
		</tr> 
	</table> 
	<%-- END INSTRUCTION TABLE --%> 

	<div class='spacer'></div>

	<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader"/>
	</tiles:insert>
	<%-- END HEADER INFO TABLE --%>

	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top>
				<%-- BEGIN TAB TABLE --%>
  			<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
  				<tiles:put name="tabid" value="casefiledetailstab"/>	
  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  			</tiles:insert>	

				<%-- BEGIN BORDER TABLE BLUE TABLE --%>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td valign=top align=center>
						<div class='spacer'></div>
						 <table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
											<%--tabs start--%>
												<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="interviewtab"/>
	  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
											</td>
										</tr>
										<tr>
									  	<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
									  </tr>
						  		</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign=top align=center>
						
          						<div class='spacer'></div>						
          							<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
          								<tr>
          									<td valign=top>
          										<table width='100%' border="0" cellpadding="0" cellspacing="0" >
          											<tr>
          												<td valign=top>
          													<%--tabs start--%>
          													<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
          														<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
          													</tiles:insert>
          													<%--tabs end--%>
          												</td>
          											</tr>
          											<tr><td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
          										</table>
          
          										<%--begin outer blue border --%>
          										<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
          											<tr>
          												<td valign=top align=center>
          
         														<div class='spacer'></div>												
          													<table width='98%' border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
          													
          														<tr class=detailHead>
          															<td colspan='7'>Warrant History</td>
          														</tr>
          														<tr class=formDeLabel>
          															<td class="subhead" valign=top width='1%' nowrap>Exclude?</td>
          															<td>Warrant #</td>
          															<td>Date Issued</td>
          															<td>Warrant Type</td>
          															<td>Warrant Status</td>
          															<td>Service Date</td>
          															<td>Service Status</td>
          														</tr>

          														<logic:empty name="socialHistoryForm" property="socialHistoryData.warrantHistory">
          															<tr><td colspan=7 class=subHead>No Data Available</td></tr>
          														</logic:empty>

          														<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.warrantHistory">
          															<%int RecordCounter = 0; String bgcolor = "";%>
          															<nested:iterate id="warrantIter" name="socialHistoryForm" property="socialHistoryData.warrantHistory" indexId="index">
          																<%-- Begin Pagination item wrap --%>
          																<pg:item>
                                            <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
            																	<td valign=top><nested:checkbox property="excluded" value="true"/></td>
            																	<td valign=top><nested:write property="warrantNumber"/></td>
            																	<td valign=top><nested:write property="dateOfIssue" formatKey="date.format.mmddyyyy"/></td>
            																	<td valign=top><nested:write property="warrantType"/></td>
            																	<td valign=top><nested:write property="warrantStatus"/></td>
            																	<td valign=top><nested:write property="serviceDate" formatKey="date.format.mmddyyyy"/></td>
            																	<td valign=top><nested:write property="serviceStatus"/></td>
            																</tr>
          																</pg:item>
          																<%-- End Pagination item wrap --%>
          															</nested:iterate>
          														</logic:notEmpty>
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
          													
          													<div class='spacer'></div>
          													<table border="0" width="100%">
          														<tr>
          															<td align="center">
          																<input type="hidden" name="resetTabName" value="warrantHistory"/>
          																<input type="submit" name="submitAction" value="<bean:message key='button.back'/>"
          																	onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
          																<html:submit property="submitAction"><bean:message key="button.saveChanges"/></html:submit> 
          																<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
          															</td>
          														</tr>
        														<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
																		<html:submit property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit>
																	</td>
																</tr>
          													</table>
          													
          												</td>
          											</tr>
          										</table>
          										<div class='spacer'></div>
          									</td>
          								</tr>
          							</table>
          						</td>
          					</tr>
          				</table>
          			</td>
          		</tr>
          	</table>
          	<div class='spacer'></div>
          	</td>
        </tr>
        </table>
        <div class='spacer'></div>
       </td>
        </tr>
        </table>
	</pg:pager>
<%-- End Pagination Closing Tag --%>
							
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>