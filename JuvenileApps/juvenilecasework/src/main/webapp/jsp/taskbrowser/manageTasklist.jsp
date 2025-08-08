<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--MODIFICATIONS --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>


<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>



<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- manageTaskList.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Java script for emulated navigation --%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">

<html:form action="/removeSelectedTasks" target="content">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Tasks</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
<br>
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
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click hyperlinked Click HERE to transfer to appropriate screen.</li>
      </ul>
      <ul>
        <li>Select the checkbox and click Remove Selected Tasks to remove the selected tasks from the list.</li>
      </ul>
	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:messagesPresent>
<BR/>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
	</tr>   	  
</table>
<br/>
</logic:messagesPresent> 
<br>
<%-- BEGIN Active Task list TABLE --%>
<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td class="detailHead">Tasks</td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="2" cellspacing="1" width="100%">										
				<tr>
					<td valign="top" class="formDeLabel">Remove</td>
					<td valign="top" class="formDeLabel">Date</td>
					<td valign="top" class="formDeLabel">Task Title</td>
					<td valign="top" class="formDeLabel">Juvenile Name</td>
					<td valign="top" class="formDeLabel">URL</td>
				</tr>
				<%           
				int RecordCounter = 0; 
                %>
            <logic:iterate id="taskIndex" name="taskBrowserForm" property="taskList">
             <%-- Begin Pagination item wrap --%>
		  <pg:item>
				<tr class=" 
			        <% RecordCounter++;
				       if (RecordCounter % 2 == 1)
					      out.print("normalRow");
				       else
					      out.print("alternateRow");
			        %>">
			        <td valign="top" class="formDe">
			        	<html:multibox property="selectedTasks">
							<bean:write name="taskIndex" property="taskID"/> 
						</html:multibox>
					</td>
					<td valign="top" class="formDe"><bean:write name="taskIndex" property="creationDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
					<td valign="top" class="formDe"><bean:write name="taskIndex" property="title"/></td>
					<td valign="top" class="formDe"><bean:write name="taskIndex" property="juvenileName"/></td>
					<td valign="top" class="formDe"><a href="<bean:write name='taskIndex' property='URL'/>">CLICK here</a></td>
				</tr>
				</pg:item>
		   <%-- End Pagination item wrap --%>
            </logic:iterate>
 
			</table>
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
		</td>
	</tr>
</table>

<%-- END Program Referral History TABLE --%>
<br>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
  <tr>
  	
    <td align="center">
       <html:submit property="submitAction"><bean:message key="button.removeSelectedTasks"/></html:submit>
	   &nbsp;
	  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>
<br>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
