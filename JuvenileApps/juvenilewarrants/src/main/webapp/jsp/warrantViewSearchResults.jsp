<!doctype html>
<!--MODIFICATIONS -->
<!-- 01/21/2005 Hien Rodriguez	Create JSP -->
<!-- 10/06/2005 C Shimek        ER#23756 revise titles -->
<!-- 12/21/2006 LDeen	        Revised help file code -->
<!-- 04/18/2007 LDeen	        Implemented new sort tag-Defect #41234-->
<%-- 07/25/2007 LDeen	    	Defect #42289-added logic not empty tag --%>
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

<title><bean:message key="title.heading"/> - warrantViewSearchResults.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>

<body>
<html:form action="/displayWarrantViewSearchResults" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|201">
<!-- BEGIN HEADING TABLE -->
<table width="98%">
    <tr>    
   		<td align="center" class="header">View&nbsp;<bean:message key="title.juvWarrant"/>&nbsp;Search Results</td> 
   	</tr>
</table>
<!-- END HEADING TABLE -->
<br>

<!-- INSTRUCTION TABLE -->	  
<table width="98%">
  	<tr><td><ul>
		<li>Select hyperlinked name to view Juvenile Warrant Details.</li>	
	</ul></td></tr> 
  	<tr>
		<td align="center"><b><bean:write name="juvenileWarrantForm" property="warrantsSearchResultsSize"/></b> warrants found in search results</td>		   
	</tr> 
</table>
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
   			 <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header stuff -->
<logic:notEmpty name="juvenileWarrantForm" property="searchResultSize">
<!-- BEGIN DETAIL TABLE -->   	
<%int RecordCounter = 0;
      String bgcolor = "";%>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class=borderTableBlue>
     <tr>
		<td>
			<table id="resultTableId" cellpadding=2 cellspacing=1 width=100%>
<!-- display detail header -->  
	<tr class="detailHead" id="firstRow">
	  	<td nowrap width="24%"><bean:message key="prompt.juvenileName" />
	  		<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="lastName" secondPropSort="firstName" primarySortType="STRING" secondarySortType="STRING" defaultSort="true" defaultSortOrder="ASC"
sortId="1" levelDeep="1"/>
		</td>
	  	<td><bean:message key="prompt.departmentName" />
	  	<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantOriginatorAgencyName" primarySortType="STRING" sortId="2" levelDeep="1"/>
	  	</td>
	  	<td width="8%"><bean:message key="prompt.warrantNumber" />
	   	<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantNum" primarySortType="INTEGER" sortId="3" levelDeep="1"/>
	   	</td>
		<td width="10%"><bean:message key="prompt.warrantActivationStatus" />
		<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantActivationStatus" primarySortType="STRING" sortId="4" levelDeep="1"/>
		</td>
	  	<td><bean:message key="prompt.warrantOriginatorName" />
	  	<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantOriginatorName" primarySortType="STRING" sortId="5" levelDeep="1"/>
	  	</td>
	  	<td><bean:message key="prompt.warrantStatus" />
	  	<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantStatus" primarySortType="STRING" sortId="6" levelDeep="1"/>
	  	</td>
	  	<td><bean:message key="prompt.warrantType" />
	  	<jims:sortResults beanName="juvenileWarrantForm" results="warrantsSearchResults" primaryPropSort="warrantType" primarySortType="STRING" sortId="7" levelDeep="1"/>
	  	</td> 	
   	</tr>
	
<!-- display detail info -->  
  <logic:iterate id="warrantIndex" name="juvenileWarrantForm" property="warrantsSearchResults">
  <!-- Begin Pagination item wrap -->
<pg:item>
  
		<tr align="left";
			class=<%RecordCounter++;
				bgcolor = "alternateRow";
				if (RecordCounter % 2 == 1)
					bgcolor = "normalRow";
				out.print(bgcolor);%>>
					
			<td><a href="/<msp:webapp/>displayWarrantViewSearchResults.do?warrantNum=<bean:write name="warrantIndex" property="warrantNum"/>&amp;submitAction=Submit">
				<bean:write name="warrantIndex" property="lastName"/>, 
                <bean:write name="warrantIndex" property="firstName"/> 
                <bean:write name="warrantIndex" property="middleName"/>
                <bean:write name="warrantIndex" property="nameSuffix"/></a>
                <logic:equal name="warrantIndex" property="juvRectype" value="I.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
				</logic:equal>
				<logic:equal name="warrantIndex" property="juvRectype" value="S.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
				</logic:equal>
				</td>
                </td>				
			<td><bean:write name="warrantIndex" property="warrantOriginatorAgencyName" /></td>
			<td><bean:write name="warrantIndex" property="warrantNum" /></td>
			<td><bean:write name="warrantIndex" property="warrantActivationStatus" /></td>
			<td><bean:write name="warrantIndex" property="warrantOriginatorName" /></td>
			<td><bean:write name="warrantIndex" property="warrantStatus" /></td>
			<td><bean:write name="warrantIndex" property="warrantType" /></td>
		</tr>	
		</pg:item>
 	   <!-- End Pagination item wrap -->
			
	</logic:iterate>
			</table>
		</td>
	</tr>	
	<tr>
	<td colspan="7">
	<!-- Begin Pagination navigation Row-->
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
     <!-- End Pagination navigation Row-->
	</td>
	</tr>	
</table>
<!-- END DETAIL TABLES -->
</logic:notEmpty>


<!-- Begin Pagination Header Closing Tag -->
</pg:pager>
<!-- End Pagination Header Closing Tag -->

<br>
</html:form>
<!-- END 1ST FORM -->

<!-- BUTTON TABLE -->  
<table width="98%">
	<tr>		  		    						 		   	
  <!--CANCEL BUTTON FORM-->
  		<td align="center">    		
			<html:form action="/displayWarrantViewSearch.do">
				<html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
			</html:form>
		</td>
		 <!--CANCEL BUTTON FORM-->			
	</tr>	  
</table>
<!-- END BUTTON TABLE -->   

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
