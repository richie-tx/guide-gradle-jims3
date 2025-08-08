<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 11/16/2011 C Shimek    #71847 revised scripting for Select Member button  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<html:base/>

<%-- Javascript for emulated navigation --%>
<html:javascript formName="juvenileMemberSearchForm" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>


<title><bean:message key="title.heading"/> - familyMemberSelectionList.jsp</title>
</head> 
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayFamilyMemberSearch" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|279">
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.selection"/></td>	  	    	 
  	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<br> 
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td>
			<ul> 
				<li>Select the member to use rather than creating a new member.</li> 
			</ul>
		</td> 
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding=1 cellspacing="0" border="0" width='98%'> 
	<tr> 
		<td> 
		<%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
		<%--header info end--%> 
		</td> 
	</tr> 
</table>
<%--header info end--%> 
<br> 
<%-- BEGIN SELECTION ALIGNMENT TABLE --%> 
<table width='98%' border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" align="center">
<%-- BEGIN FAMILY MEMBER SELECTION LIST TABLE --%> 
			<table width='98%' cellspacing="0" cellpadding="2" align="center" class="borderTableBlue"> 
				<tr> 
					<td class="detailHead" valign="top"><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.selection"/> <bean:message key="prompt.list"/></td> 
				</tr> 
				<tr> 
					<td> 
						<table width='100%' cellspacing='1'> 
							<tr bgcolor="#cccccc"> 
								<td width='1%'></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
								<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.sex"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.dateOfBirth"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.SSN"/></td> 
							</tr> 
							<nested:iterate id="memberList" name="juvenileMemberSearchForm" property="memberSearchResults" indexId="index">
<%-- Begin Pagination item wrap --%>
								<pg:item>											
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">	
										<td><input type="radio" name="twoLane" value="<bean:write name="memberList" property="memberNumber"/>" /></td> 
			 							<td>
											<logic:notEmpty name="memberList" property="suspiciousMember"/>
												<logic:notEqual name="memberList" property="suspiciousMember" value="">
													<bean:message key="prompt.suspiciousMember"/>
												</logic:notEqual>
												<bean:write name="memberList" property="memberNumber"/>
										</td> 
										<td><bean:write name="memberList" property="name.formattedName"/></td>
										<td valign="top"><bean:write name="memberList" property="sex"/></td>
										<td valign="top"><bean:write name="memberList" property="dateOfBirth"/></td>
										<td valign="top"><bean:write name="memberList" property="ssn.formattedSSN"/></td> 
									</tr> 
								</pg:item>
<%-- End Pagination item wrap --%>
		                   </nested:iterate>
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
						 <html:hidden  property="selectedValue" styleId="hiddenVal"/>
  					</td> 
				</tr> 
            </table> 
<%-- END FAMILY MEMBER SELECTION LIST TABLE --%> 
<%-- BEGIN BUTTON TABLE --%>
			<table width="100%">
				<tr>
					<td align="center" nowrap="nowrap">	
						<input type="button" value="Select Member" id="checkSelectedMember"> 
						<html:submit property="submitAction" styleId="setSelectedBypass"><bean:message key="button.bypassMatch" /></html:submit>
					</td>
				</tr>
			</table>			
<%-- END BUTTON TABLE --%>
		</td>
	</tr>
</table>
<%-- END  SELECTION ALIGNMENT TABLE --%>
<%-- Begin Pagination Header Closing Tag --%>
</pg:pager>
<%-- End Pagination Header Closing Tag --%>
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>