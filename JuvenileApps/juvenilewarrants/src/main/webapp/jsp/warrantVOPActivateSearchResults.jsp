<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<%-- Used for Violation of Probation(VOP) Activate Search Results --%>
<!-- 01/30/2008 Richard Capestani-Knape	Create JSP -->
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
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />


<title><bean:message key="title.heading"/> - warrantVOPActivateSearchResults.jsp</title>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body>
<!-- BEGIN FORM -->			
<html:form action="/displayWarrantVOPActivateDetails" target="content">
   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|147">
<!-- BEGIN HEADING TABLE -->
<table width=98% align="center">	
	<tr>
		<td align="center" class="header">Activate&nbsp;<bean:message key="title.juvWarrantVOP"/>&nbsp;Search Results</td>		 
    <tr><td>&nbsp;</td></tr>
    <tr>
        <td><ul>	       
	     	 <li>Click the underlined warrant number to select warrant to process.</li>
       		 <li>Select Cancel to return to search page.</li>
       	   </ul>
        </td>
    </tr>          	         		
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN MESSAGE TABLE -->

<logic:notEmpty name="juvenileWarrantForm" property="searchResultSize">
<table align=center>
	<tr>
		<td align="center"><bean:write name="juvenileWarrantForm" property="searchResultSize"/> warrants found in search results
		</td>
  	</tr>
</table>  
</logic:notEmpty>
<!-- END MESSAGE TABLE -->
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

<!-- BEGIN DETAIL TABLE -->
<% int RecordCounter = 0; %>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
   <tr>
		<td>
			<table id="resultTableId" cellpadding=2 cellspacing=1 width=100%>
<!-- display detail header -->  
	<tr class="detailHead" id="firstRow">
    	<td class="subhead"><bean:message key="prompt.warrantNumber"/>
    	<jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="warrantNum" primarySortType="INTEGER" sortId="1" levelDeep="1"/>
    	</td>			
   		<td class="subhead"><bean:message key="prompt.juvenileNumber"/>
   		<jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="juvenileNum" primarySortType="STRING" sortId="2" levelDeep="1"/>
	   	</td>
     	<td class="subhead"><bean:message key="prompt.referralNumber"/>
     	<jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="referralNumber" primarySortType="INTEGER" sortId="3" levelDeep="1"/>
     	</td>
     	<td class="subhead"><bean:message key="prompt.juvenileName"/>
     	<jims:sortResults beanName="juvenileWarrantForm" results="warrants" primaryPropSort="lastName" secondPropSort="firstName" primarySortType="STRING" secondarySortType="STRING" defaultSort="true" defaultSortOrder="ASC"
sortId="4" levelDeep="1"/>
     	</td>
  	</tr>
 <logic:iterate id="juvenileWarrantIndex" name="juvenileWarrantForm" property="warrants"> 
 <!-- Begin Pagination item wrap -->
<pg:item>
 
  				<tr class= 
					<% RecordCounter++;
	  					if (RecordCounter % 2 == 1)
		 					out.print("normalRow");
	  					else
							out.print("alternateRow");
					%>>
					<td><a href="/<msp:webapp/>displayWarrantVOPActivateSearchResults.do?warrantTypeUI=singleItem&actVOP=true&warrantNum=<bean:write name="juvenileWarrantIndex" property="warrantNum"/>">
						<bean:write name="juvenileWarrantIndex" property="warrantNum"/></a></td>
					<td><bean:write name="juvenileWarrantIndex" property="juvenileNum"/></td>
					<td><bean:write name="juvenileWarrantIndex" property="referralNumber"/></td>
					<td><bean:write name="juvenileWarrantIndex" property="lastName"/>, <bean:write name="juvenileWarrantIndex" property="firstName"/>&nbsp;<bean:write name="juvenileWarrantIndex" property="middleName"/>&nbsp;<bean:write name="juvenileWarrantIndex" property="nameSuffix"/></td>
				 </tr>
				 </pg:item>
 	   <!-- End Pagination item wrap -->
				 
    </logic:iterate> 
    	    	</table>
			</td>
		</tr> 
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
<!-- END 1ST FORM -->

<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr align="center">
    <!--CANCEL BUTTON FORM-->
			<td>
			  <html:form action="/displayGenericSearch.do?warrantTypeUI=actVOP">
                <html:submit property="submitAction">
	              <bean:message key="button.cancel"></bean:message>
                </html:submit>
              </html:form>  
			</td>
  </tr>
</table>
<!-- END BUTTON TABLE -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>