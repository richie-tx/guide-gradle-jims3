<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 22 oct 2007 - mjt - create JSP --%>

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

<title><bean:message key="title.heading"/>Manage <bean:message key="prompt.codeTableRegistration" /> - codeTableRegistrationSearchResults.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin=0 leftmargin='0'>
<html:form action="/handleCodetableRegistration" target="content"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage <bean:message key="prompt.codeTableRegistration" /> - <bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.searchResults" /></td>
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
        <li>Select a hyperlinked Code Table Registration to view the Code Table Registration Details screen.</li>
        <li>Select the Cancel button to return to the search page.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center"><bean:write name="codetableRegistrationForm" property="searchResultsCount"/> search results</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
	<tr class="detailHead">
    <td>
    	<bean:message key="prompt.codeTableRegistration" /> <bean:message key="prompt.name" />
		<jims2:sortResults beanName="codetableRegistrationForm" results="codetables" primaryPropSort="codetableName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
    </td>
    <td>
    	<bean:message key="prompt.description" />
		<jims2:sortResults beanName="codetableRegistrationForm" results="codetables" primaryPropSort="codetableDescription" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />				
    </td>
    <td>
    	<bean:message key="prompt.type" />
		<jims2:sortResults beanName="codetableRegistrationForm" results="codetables" primaryPropSort="type" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
    </td>
	</tr>

  <% int RecordCounter = 0; %>
	<logic:iterate id="codetableIter" name="codetableRegistrationForm" property="codetables">
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
  				<a href="/<msp:webapp/>handleCodetableRegistration.do?submitAction=Link&codetableRegId=<bean:write name='codetableIter' property='codetableRegId'/>"><bean:write name="codetableIter" property="codetableName"/></a>
  			</td>
  		  	<td><bean:write name="codetableIter" property="codetableDescription"/></td>
		    <td>
		    	<logic:equal name="codetableIter" property="type" value="SL">
            		Simple&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableIter" property="type" value="CD">
            		Compound&nbsp;
            	</logic:equal>
            	<logic:equal name="codetableIter" property="type" value="CX">
            		Complex&nbsp;
            	</logic:equal>
		    	<%--bean:write name="codetableIter" property="type"/--%>
		    </td>
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
<br>
<table border="0" width="100%">
  <tr align="center">
    <td>
      <%-- probably going to require the -isAllowed- that follows, for creation ... commented out for now --%>
      <%--       <jims2:isAllowed requiredFeatures='<%=Features.CF_UPDATE_CODETABLE%>'>  --%>
        <input type="button" onclick="goNav('/<msp:webapp/>displayCodetableRegistrationUpdate.do?submitAction=Create')" value="<bean:message key='button.create'/>"/>
      <%--      </jims2:isAllowed>   --%>
      <%-- end commented out for now --%>

      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>

</pg:pager>
</html:form>

<span style="text-align: center;"><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

