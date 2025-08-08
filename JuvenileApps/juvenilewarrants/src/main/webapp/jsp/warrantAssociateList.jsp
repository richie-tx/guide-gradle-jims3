<!DOCTYPE HTML>
<!-- User goes here when they click to Update Associate from a Warrant Confirm page -->
<!-- This page shows all associates connected to this warrant. -->
<!--MODIFICATIONS -->
<!-- L Deen		06/23/2004	Create JSP -->
<!-- L Deen		02/23/2004	Revise JSP -->
<%-- C Shimek   04/12/2006  Updated helpfile reference, no mapId available at this time --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek	03/15/2007  added mapId value to helpfile reference --%>
<%-- CShimek 	04/12/2007	#41031 removed target=content in form tag to allow page to display in popup window --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%> 


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css"/>
<html:base />
<title><bean:message key="title.heading" /> - warrantAssociateList.jsp</title>

</head>
<!--END HEADER TAG -->

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN FORM -->
<html:form action="/displayWarrantAssociateCreateUpdate">
	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|209">
    <html:hidden property="oId" value="" />
    <html:hidden property="action" value="update" /> 
<%-- this script is used to set value to determine if page is accessed in frame or popup window --%>    
<script>
	document.write("<input type=hidden name=flowInd value=" + window.name + ">"); 
</script>    

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvWarrantAssociate" /> List</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click on underlined Name to select Responsible Adult to update information.</li>
	    <li>Select cancel to return to warrant confirmation.</li>
      </ul>
     </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN WARRANT NUMBER TABLE -->
  <table width="98%" border="0" align="center">
	<tr>
	  <td align="center" class="subhead"><bean:message key="prompt.warrantNumber"/>: <span class="warrantNumber"><bean:write name="juvenileWarrantForm" property="warrantNum"/></span></td>
    </tr>
  </table>
<%-- END WARRANT NUMBER TABLE --%>
<br>
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
   			 <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header stuff -->

<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
<!-- display detail header -->  
	<tr class="detailHead">
      <td class="subhead"><bean:message key="prompt.associateName"/></td>
      <td class="subhead"><bean:message key="prompt.relationshipToJuvenile"/></td>
	</tr>
	<!-- SET RECORD COUNTER VARIABLE -->
 <%int RecordCounter = 0;%>
 <logic:iterate id="associateIndex" name="juvenileWarrantForm" property="associates">
 <!-- Begin Pagination item wrap -->
<pg:item>
 
	<tr	class=<%RecordCounter++;
		if (RecordCounter % 2 == 1)
			out.print("normalRow");
		else
			out.print("alternateRow");%>>
 	    <td class="hyperlink" nowrap>
			<a href="/<msp:webapp/>displayWarrantAssociateCreateUpdate.do?action=update&associateNum=<bean:write name="associateIndex" property="associateNum"/>">
				<bean:write name="associateIndex" property="associateName.lastName" />,&nbsp;
           		<bean:write name="associateIndex" property="associateName.firstName" />&nbsp;
               	<bean:write name="associateIndex" property="associateName.middleName" />
            </a> 
         </td>
	     <td><bean:write name="associateIndex" property="relationshipToJuvenile"/>  
	     </td>
      </tr>
      </pg:item>
 	   <!-- End Pagination item wrap -->
      
      </logic:iterate>
   </table>
<!-- END DETAIL TABLE -->
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
    	<!-- Begin Pagination Header Closing Tag -->
</pg:pager>
<!-- End Pagination Header Closing Tag -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">
		<logic:notEqual name="juvenileAssociateForm" property="flowInd" value="popupWindow">
    		<html:submit property="submitAction">
    			<bean:message key="button.cancel" />
	    	</html:submit>
		</logic:notEqual>	
		<logic:equal name="juvenileAssociateForm" property="flowInd" value="popupWindow">
    		<input type="button" name="cancel" value="Cancel" onclick="window.close()">
		</logic:equal>	
	</td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
