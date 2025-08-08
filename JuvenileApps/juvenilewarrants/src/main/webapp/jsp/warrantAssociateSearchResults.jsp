<!DOCTYPE HTML>
<!-- THIS JSP IS NOT BEING UTILIZED 4/19/07 LDEEN -->
<!-- Used to show search results of associates relating to warrant -->
<!-- when selecting Create Associate from navigation menu -->
<!--MODIFICATIONS -->
<!-- 06/28/2004	LDeen		Create JSP -->
<%-- 04/12/2006 CShimek 	Updated helpfile reference, no mapId available at this time --%>
<%-- 12/21/2006 CShimek		revised helpfile reference value--%>
<%-- 03/01/2007	CShimek		#39097 added multiple submit button logic --%>
<!-- 04/18/2007 LDeen	    Implemented new sort tag-Defect #41234 -->
<%-- 06/04/2007 LDeen		#42564 changed path to app.js --%>
<%-- 08/6/2015  RYoung      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<title><bean:message key="title.heading"/> - warrantAssociateSearchResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" /> 

<!-- JAVASCRIPT FILE --> 
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>	
</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/submitwarrantJJSSearchResultsForm" target="content"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|##">	

<!-- BEGIN HEADING TABLE -->
  <table width="100%">
    <tr>
      <td class="header">
        <bean:parameter id="action" name="action" />
          <logic:equal name="action" value="OIC">
            <bean:message key="title.warrantOrderOfImmediateCustodySearchResults"/>
          </logic:equal>
          <logic:equal name="action" value="VOP">
            <bean:message key="title.warrantViolationOfProbationSearchResults"/>
          </logic:equal>
      </td>	
    </tr>
  </table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
  <table width="98%" border="0">
    <tr>
      <td class="bodyText">
	<ul>
          <li>Click on underlined petition to select petition needing update.</li>
          <li>Select cancel to return to search page.</li>
        </ul>
      </td>
    </tr>
  </table>
<!-- END INSTRUCTION TABLE -->
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
   			 <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header stuff -->

<!-- BEGIN DETAIL TABLE -->
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="borderTableBlue">          
		<!-- display detail header -->
		<tr class="detailHead" >  
  	    <td width="2%">&nbsp;</td>
            <td><bean:message key="prompt.warrantNumber"/>
            <jims:sortResults beanName="juvenileMatch" results="juvenileMatch" primaryPropSort="warrantNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC"
sortId="1" levelDeep="1"/>
            </td>
            <td><bean:message key="prompt.warrantType"/>
            
            </td>
            <td><bean:message key="prompt.juvenileName"/>
            
            </td>
          </tr>
          <% int RecordCounter = 0; %>
          <logic:iterate id="juvenileMatch" name="juvenileMatchs"> 
          <!-- Begin Pagination item wrap -->
<pg:item>
          
          <tr bgcolor= 
			<% RecordCounter++;
		  	  if (RecordCounter % 2 == 1)
			    out.print("#FFFFFF");
		      else
			    out.print("#f0f0f0");	
		    %>	 
	      >
	     	 <td width="46%"><a
				href="/<msp:webapp/>submitSearchJJSForm.do?oId=<bean:write name="juvenileMatch" property="oId"/>">
				<bean:write name="juvenileMatch" property="warrantNum" /></a></td>
			 <td width="19%" nowrap><bean:write name="juvenileMatch" property="warrantType" /></td>
			 <td width="35%" nowrap><bean:write name="juvenileMatch" property="juvenileName" /></td>
			</tr>
			</pg:item>
 	   <!-- End Pagination item wrap -->
			
		  </logic:iterate>  
		  	<tr>
	<td colspan="3">
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
     
<!-- END DETAIL TABLE -->

    	<!-- Begin Pagination Header Closing Tag -->
</pg:pager>
<!-- End Pagination Header Closing Tag -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%" align="center">
  <tr>
    <td>
  	   <html:submit><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
        <html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
        <html:submit onclick="gotoURL('/<msp:webapp/>main.jsp')"><bean:message key="button.cancel"></bean:message></html:submit>
          </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
