<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 26 oct 2007 - mjt - create JSP --%>

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

<%--LOCALE USED FOR INTERNATIONALIZATION - NOT USED YET --%>

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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationContextEntitySearchResults.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>


<body topmargin=0 leftmargin='0'>
<html:form action="/handleCodetableRegistrationEntity" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage <bean:message key="prompt.codeTableRegistration" /> - <bean:message key="prompt.codeTableRegistration" /> 
    	<logic:equal name="searchType" value="context">
        Context
    	</logic:equal>
    	<logic:equal name="searchType" value="entity">
        Entity
    	</logic:equal>
  		<bean:message key="prompt.searchResults" />
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
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select a radio button associated for the desired choice, then select the Next button.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center"><bean:write name="codetableRegistrationForm" property="searchResultsCount"/> search results</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table width='98%'  cellpadding="0" cellspacing="0"  class=borderTableBlue>
  <tr>
	  <td>
      <table width='100%' align="center" border="0" cellpadding="2" cellspacing="1">
			  <%-- column headings --%>
      	<tr class="detailHead">
				  <td></td>

        	<logic:equal name="searchType" value="context">
            <td>Code Table Context Name</td>
        	</logic:equal>
        	<logic:equal name="searchType" value="entity">
            <td>Code Table Registration Entity</td>
        	</logic:equal>
      	</tr>  
					<%-- repeating row info --%>
          <% int RecordCounter = 0; %>
        	<logic:iterate id="contextOrEntity" name="codetableRegistrationForm" property="codetableContextOrEntityList">
        	<bean:define id="contextOrEntityValue">
                           <bean:write name="contextOrEntity"/>
            		</bean:define>
            <pg:item>
              <tr class="
                <% RecordCounter++;
                	if (RecordCounter % 2 == 1)
                		out.print("normalRow");
                	else
                		out.print("alternateRow");
                %>"
              >		 
          		  <td valign='top' nowrap><html:radio property="selectedContextOrEntity" 
                       value="<%=contextOrEntityValue%>" /></td>
          		  <td><bean:write name="contextOrEntity"/></td>          		  
          		</tr>
            </pg:item>
        	</logic:iterate>
      	
      </table>
	  </td>
  </tr>
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
<br>
<table border="0" width="100%">
  <tr>
    <td align="center">
			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>


<%-- BEGIN NOTES TABLE --%>
<%--
<br>
<table width="100%" class='hidden'>
  <tr>
    <td class="subhead">Notes:</td>
  </tr>
 <tr>
    <td>
			<ol>
    		<li>This page is displayed only if more than one match is found from search.</li>
    		<li>If only one search result is found, then the Code Table Data List screen is displayed.</li>
			</ol>
		</td>
  </tr>
</table>
--%>
<%-- END NOTES TABLE --%>
</pg:pager>
</html:form>

<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

