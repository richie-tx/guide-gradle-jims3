<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="naming.PDCodeTableConstants"%>




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
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>


<title><bean:message key="title.heading"/> - juvenileMatchMemberList.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileProfileUpdateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|164">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Matching Juveniles</td>	  	    	 
  	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>The juveniles listed below have the same SSN, Last Name, and First Name as the juvenile that is trying to be created/updated.</li> 
			</ul>
		</td> 
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'> 
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
<%--END JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div> 
<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
<table width='98%' border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td align='center'>
			<div class='spacer'></div> 
<%-- BEGIN MEMBERS LIST BORDER TABLE --%>			            
            <table width='98%' cellspacing='0' cellpadding='2' class='borderTableBlue'> 
            	<tr> 
                	<td class="detailHead" valign='top'><bean:message key="prompt.juvenile"/> <bean:message key="prompt.list"/></td> 
              	</tr> 
              	<tr> 
                	<td> 
<%-- BEGIN MEMBERS LIST DETAILS TABLE --%>	                				
						<table width='100%' cellspacing='1'> 
                 			<tr bgcolor='#cccccc'> 
								<td valign='top' class='subHead'><bean:message key="prompt.juvenile"/> #</td> 
								<td valign='top' class='subHead'><bean:message key="prompt.name"/></td> 
								<td valign='top' class='subHead'><bean:message key="prompt.race"/></td>
								<td valign='top' class='subHead'><bean:message key="prompt.sex"/></td> 
								<td valign='top' class='subHead'><bean:message key="prompt.dateOfBirth"/></td> 
								<td valign='top' class='subHead'><bean:message key="prompt.SSN"/></td> 
							</tr> 
<%-- Begin Pagination Header Tag--%>
							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
							<pg:pager index="center"
							   	 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
							   	 maxIndexPages="10" export="offset,currentPageNumber=pageNumber"
							   	 scope="request">
				   				<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
							<nested:iterate indexId='index' id="juvenileList" name="juvenileProfileMainForm" property="matchingJuvs">
<%-- Begin Pagination item wrap --%>
								<pg:item>
									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
										<td><bean:write name="juvenileList" property="juvenileNum"/></td> 
										<td><bean:write name="juvenileList" property="lastName"/>, <bean:write name="juvenileList" property="firstName"/></td>
										<td valign='top'><bean:write name="juvenileList" property="raceDesc"/></td> 
										<td valign='top'><bean:write name="juvenileList" property="sexDesc"/></td>
										<td valign='top'><bean:write name="juvenileList" property="dateOfBirth"/></td>
										<td valign='top'><bean:write name="juvenileProfileMainForm" property="SSN"/></td> 
	                   				</tr> 
								</pg:item>
<%-- End Pagination item wrap --%>
							</nested:iterate>
						</table>
<%-- END MEMBERS LIST DETAILS TABLE --%>	 									
						<div class='spacer'></div>
<%-- Begin Pagination navigation Row--%>
						<table align="center">
							<tr>
								<td>
									<pg:index>
										<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
									   		<tiles:put name="pagerUniqueName"    value="pagerSearch"/>
									   		<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
										</tiles:insert>
						   	   		</pg:index>
					      		</td>
			   	  	   		</tr>
						</table>
<%-- End Pagination navigation Row--%>
						</pg:pager>
						<html:hidden  property="selectedValue" styleId="hiddenVal" />
					</td>
				</tr> 
         	</table> 
<%-- END MEMBERS LIST BORDER TABLE --%>
		</td>
	</tr>
</table>
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%> 
<table border="0" width="100%">
	<tr>
		<td align="center">
			<input type="button" value="Close Window" name="return" onclick="window.close();">
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%> 
</html:form> 
<div align='center'><script type="text/javascript">renderBackToTop();</script></div></body>
</html:html>