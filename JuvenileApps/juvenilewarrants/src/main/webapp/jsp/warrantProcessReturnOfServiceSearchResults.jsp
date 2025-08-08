<!DOCTYPE HTML>
<!-- Used to search for juvenile Process Return of Service search results. -->
<!--MODIFICATIONS -->
<%-- 02/25/2005	CShimek		Create JSP --%>
<%-- 08/23/2005	JFisher		Update form fields to reflect use of java.util.Date instead of String --%>
<%-- 02/02/2006	CShimek		Add hidden field for HelpFile name --%>
<%-- 12/21/2006 CShimek		revised helpfile reference value--%>
<%-- 04/18/2007 LDeen	    Implemented new sort tag-Defect #41234 --%>
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
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantProcessReturnOfServiceSearchResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<body>

<html:form action="/displayWarrantProcessReturnOfServiceDetails" target="content">
   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|178">
<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header">
        <bean:message key="title.juvWarrantProcessReturnOfService"/> Search Results
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
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
		<!-- display detail header -->
	<tr class="detailHead" >  
        <td><bean:message key="prompt.warrantNumber"/>
        <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="warrantNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC"
sortId="1" levelDeep="1"/>
        </td>
        <td><bean:message key="prompt.juvenileName"/>
       <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="juvLastName" secondPropSort="juvFirstName" primarySortType="STRING" secondarySortType="STRING"
sortId="2" levelDeep="1"/> </td>
  	    <td>&nbsp;<bean:message key="prompt.serviceDate"/> and Time
  	    <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="serviceTimeStamp" primarySortType="DATE" sortId="3" levelDeep="1"/>
  	    </td>
        <td>&nbsp;<bean:message key="prompt.serviceStatus"/>
        <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="serviceStatus" primarySortType="STRING" sortId="4" levelDeep="1"/>
        </td>
        <td>&nbsp;<bean:message key="prompt.executorName"/>
        <jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="officerLastName" secondPropSort="officerFirstName" primarySortType="STRING" secondarySortType="STRING" sortId="5" levelDeep="1"/>
        </td>
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
	    %> align="left">
   		<td> 
	       <a href="/<msp:webapp/>displayWarrantProcessReturnOfServiceSearchResults.do?warrantTypeUI=processReturn&warrantNum=<bean:write name="warrantIndex" property="warrantNum"/>">
		   <bean:write name="warrantIndex" property="warrantNum"/></a>
		 </td>
		 <td nowrap><bean:write name="warrantIndex" property="juvLastName"/>, <bean:write name="warrantIndex" property="juvFirstName"/> <bean:write name="warrantIndex" property="juvMiddleName"/></td>
	    <td nowrap>
	    	<bean:write name="warrantIndex" property="serviceTimeStamp" formatKey="date.format.mmddyyyy" />&nbsp;
	    	<bean:write name="warrantIndex" property="serviceTimeStamp" formatKey="time.format.HHmm" /></td>
   	    <td nowrap><bean:write name="warrantIndex" property="serviceStatus" /></td>
		<td nowrap><bean:write name="warrantIndex" property="officerLastName"/>, <bean:write name="warrantIndex" property="officerFirstName"/> <bean:write name="warrantIndex" property="officerMiddleName"/></td>
	</tr>
	</pg:item>
 	   <!-- End Pagination item wrap -->
	
    </logic:iterate>  
    	<tr>
	<td colspan="4">
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
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
   <tr>
  	 <td align="center">
	 <html:form action="/displayGenericSearch.do?warrantTypeUI=processReturn"> 
        <html:submit onclick="return disableSubmit(this, this.form)">
            <bean:message key="button.cancel"></bean:message>
        </html:submit>
     </html:form> 
	 </td>
   </tr>
</table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>