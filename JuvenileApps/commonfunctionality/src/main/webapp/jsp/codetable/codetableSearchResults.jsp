<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/07/2007		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonfunctionality.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;Manage Code Table&nbsp;- codetableSearchResults.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>


<body topmargin=0 leftmargin='0'>
<html:form action="/displayCodetableDataList" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage Code Table - Code Table Search Results</td>
  </tr>
  <tr>
	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
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
        <li>Select a hyperlinked Code Table Name to view the Code Table Data List screen.</li>
        <li>Select the Cancel button to return to search page.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center"><bean:write name="codetableForm" property="searchResultsCount"/> search results</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
	<tr class="detailHead">
    <td>Code Table Name
      <jims2:sortResults beanName="codetableForm" results="codetables" primaryPropSort="codetableName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
		</td>
    <td>Description
      <jims2:sortResults beanName="codetableForm" results="codetables" primaryPropSort="codetableDescription" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />				
		</td>
	</tr>

  <% int RecordCounter = 0; %>
	<logic:iterate id="codetableIter" name="codetableForm" property="codetables">
  <pg:item>
    <tr class="
      <% RecordCounter++;
      	if (RecordCounter % 2 == 1)
      		out.print("normalRow");
      	else
      		out.print("alternateRow");
      %>"
    >		 	
		  <td valign='top' nowrap>
				<%-- Preferably use a POST method here to pass in parameters due to GET limitation of 255 chars on most browsers--%>
				<a href="/<msp:webapp/>displayCodetableDataList.do?submitAction=Link&codetableRegId=<bean:write name='codetableIter' property='codetableRegId'/>"><bean:write name="codetableIter" property="codetableName"/></a>
			</td>
		  <td><bean:write name="codetableIter" property="codetableDescription"/></td>
		</tr>
  </pg:item>
	</logic:iterate>
 
</table>
<%-- END DETAIL TABLE --%>

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

<%-- BEGIN BUTTON TABLE --%>
<div class='spacer'></div>
<table border="0" width="100%">
  <tr>
    <td align="center">
      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
	  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>

</span>
</pg:pager>
</html:form>

<span align=center><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

