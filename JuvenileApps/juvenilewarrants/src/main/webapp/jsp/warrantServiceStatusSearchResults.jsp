<!DOCTYPE HTML>
<!-- Used to search for juvenile service status search results. -->
<!--MODIFICATIONS -->
<%-- 02/25/2005	CShimek		Create JSP --%>
<%-- 10/05/2005	CShimek		ER#23756 - reivse titles --%>
<%-- 02/02/2006	CShimek     Add hidden field for HelpFile name --%>
<%-- 12/21/2006	LDeen	    Revised help file code --%>
<%-- 04/18/2007 LDeen	    Implemented new sort tag-Defect #41234 --%>
<%-- 06/04/2007 LDeen		#42564 changed path to app.js --%>
<%-- 07/25/2007 LDeen	    Defect #42289-added logic not empty tag --%>
<%-- 08/06/2015 RYoung      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantServiceStatusSearchResults.jsp</title>

</head>
<body>

<html:form action="/displayWarrantServiceStatusSearchResults" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|141">

<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header">
          Update <bean:message key="title.juvWarrantWarrantServiceStatus"/> Search Results
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTURCTION TABLE -->
<table width="98%">
	<tr>
       <td>
     	  <ul>
             <li>Click on underlined warrant number to select warrant.</li>
	    	 <li>Select cancel to return to search page.</li>
      	  </ul>
      </td>
  	</tr>
   <tr>
     <td align="center">
		<bean:write name="juvenileWarrantForm" property="searchResultSize"/> warrants found in search results.
  	 </td>
   </tr>   
</table>
<!-- END INSTRUCTION TABLE -->
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
<pg:pager
   		 index="center"
   		 maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
   		 maxIndexPages="10"
   		 export="offset,currentPageNumber=pageNumber"
   		 scope="request">
   			 <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header stuff -->

<logic:notEmpty name="juvenileWarrantForm" property="searchResultSize">
<!-- BEGIN DETAIL TABLE -->
  <table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
		<td>
			<table id="resultTableId" cellpadding=2 cellspacing=1 width=100%>          
		<!-- display detail header -->
	<tr class="detailHead" id="firstRow">  
  	    <td width="20%">&nbsp;<bean:message key="prompt.warrantNumber"/>
  	    <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="warrantNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC"
sortId="1" levelDeep="1"/>
  	    </td>
        <td width="31%">&nbsp;<bean:message key="prompt.warrantType"/>
        <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="warrantType" primarySortType="STRING" sortId="2" levelDeep="1"/>
        </td>
        <td width="49%"><bean:message key="prompt.juvenileName"/>
        <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="lastName" secondPropSort="firstName" primarySortType="STRING" secondarySortType="STRING"
sortId="3" levelDeep="1"/></td>
    </tr>
    <% int RecordCounter = 0; %>
    <logic:iterate id="warrantIndex" name="juvenileWarrantForm" property="warrants"> 
    <!-- Begin Pagination item wrap -->
<pg:item>
    
    <tr class= 
	   <% RecordCounter++;
		  if (RecordCounter % 2 == 1)
		    out.print("normalRow");
		  else
		    out.print("alternateRow");	
	    %> >
	    <td> 
	      <a href="/<msp:webapp/>displayWarrantServiceStatusSearchResults.do?warrantTypeUI=warrantService&warrantNum=<bean:write name="warrantIndex" property="warrantNum"/>">
	      <bean:write name="warrantIndex" property="warrantNum"/></a>
		 </td>
	    <td nowrap><bean:write name="warrantIndex" property="warrantType" /></td>
		<td nowrap>
		  <bean:write name="warrantIndex" property="lastName"/>, <bean:write name="warrantIndex" property="firstName"/>&nbsp;<bean:write name="warrantIndex" property="middleName"/>		
		</td>
	</tr>
	</pg:item>
 	   <!-- End Pagination item wrap -->
	
    </logic:iterate>
     			</table>
			</td>
		</tr>   
    	<tr>
	<td colspan="3">
	<!-- Begin Pagination navigation Row-->
<table align="center">
 		  <tr>
			<td>
		   		<pg:index>
				<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
    		<tiles:put name="pagerUniqueName"    value="pagerSearch"/>
   		 <tiles:put name="resultsPerPage" 
      value="<%=paginationResultsPerPage%>"/>
					</tiles:insert>
	   	   		</pg:index>
      		</td>
   	  	   </tr>
</table>
     <!-- End Pagination navigation Row-->
	</td>
	</tr>	
  </table>
<!-- END DETAIL TABLE -->
</logic:notEmpty>

<!-- Begin Pagination Header Closing Tag -->
</pg:pager>
<!-- End Pagination Header Closing Tag -->

<br>

</html:form>
<!-- END 1ST FORM -->

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
   <tr>
  	 <td align="center">
     	
    <html:form action="/displayGenericSearch.do?warrantTypeUI=warrantService"> 
       <html:submit>
           <bean:message key="button.cancel"></bean:message>
       </html:submit>
    </html:form> 
	 </td>
   </tr>
</table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>