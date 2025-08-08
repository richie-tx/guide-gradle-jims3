<!DOCTYPE HTML>
<%-- Used for searching of juvenile profile in MJCW --%>
<%-- MODIFICATIONS --%>
<%-- mjt - 17 aug 2007 - create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" /> - notificationsSearchResults.jsp</title>

<%-- STRUTS VALIDATIONS--%>

<%-- Javascript for emulated navigation --%>

<%-- Do not move the script below it is need for the dhtmlwiindow.js to function properly --%>
<script>

var correctedPath='/<msp:webapp/>';
</script>
<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/dhtmlwindow.js"></script>
<script>
function openWindow(url)
{
	var newWin = window.open(url, "noticeWindow", "height=300,width=500,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}

function openCustomRestrictiveWindow(url,heightSize, widthSize)
{
		var newWin = window.open(url, "pictureWindow", "height=" + heightSize +",width=" + widthSize + ",toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
		newWin.focus();
}
</script>
</head>

<%--BEGIN BODY and FORM TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayNotificationSearch" target="content" >

<%-- 17 aug 2007 - mjt - help system ... currently commented out - i only guessed at the fully-qualified path/name
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchnotification/Search_Notification_Results.htm#|162">
--%>       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="prompt.notification"/> <bean:message key="prompt.searchResults"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0" ALIGN="center">
  <tr>
    <td class="bodyText">
	   <ul>
          <li>Click a hyperlinked Notification Name.</li>
	      <li>Select Cancel button to return to Search page.</li>		
       </ul>
	  </td>
  </tr>
  <tr>
    <bean:size id="searchResultsSize" name="searchNotificationsForm" property="searchResults"/> 
    <td align="center"><bean:write name="searchResultsSize"/> Notifications found in search results</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<table width='98%'  cellpadding="0" cellspacing="0"  class=borderTableBlue align="center">
  <tr>
	  <td>
		  <logic:empty name="searchNotificationsForm" property="searchResults">
		  	   No Tasks or Notifications Found that match your criteria.
		  </logic:empty>
		  <logic:notEmpty name="searchNotificationsForm" property="searchResults">
			 <table width='100%' align="center" border="0" cellpadding="2" cellspacing="1"  id="uniqueID">
			  <%-- column headings --%>
				  <tr class="detailHead">
				 	<td width='40%'>
				 		<bean:message key="prompt.notification"/> <bean:message key="prompt.name"/>
				    	<jims2:sortResults beanName="searchNotificationsForm" results="searchResults" primaryPropSort="subject" primarySortType="STRING"  secondPropSort="sentDate" secondarySortType="INT" defaultSortOrder="ASC" sortId="1" />	
				    </td>
				    <td>
				    	<bean:message key="prompt.status"/>
				    	<jims2:sortResults beanName="searchNotificationsForm" results="searchResults" primaryPropSort="status" primarySortType="STRING" secondPropSort="sentDate" secondarySortType="INT" defaultSortOrder="ASC" sortId="2" />	
				    </td>
				    <td>
				    	<bean:message key="prompt.type"/>
				      	<jims2:sortResults beanName="searchNotificationsForm" results="searchResults" primaryPropSort="type" primarySortType="STRING" secondPropSort="sentDate" secondarySortType="INT" defaultSortOrder="ASC" sortId="3" />	
				    </td>
				    <td>
				    	<bean:message key="prompt.date"/>
				      	<jims2:sortResults beanName="searchNotificationsForm" results="searchResults" primaryPropSort="sentDate" primarySortType="STRING" secondPropSort="sentDate" secondarySortType="INT" defaultSort="true" defaultSortOrder="ASC" sortId="4" />	
				    </td>
				    <td>
				    	<bean:message key="prompt.owner"/>
				      	<jims2:sortResults beanName="searchNotificationsForm" results="searchResults" primaryPropSort="ownerUserId" primarySortType="STRING" secondPropSort="sentDate" secondarySortType="INT" defaultSortOrder="ASC" sortId="5" />	
				    </td>
				   </tr>
				
				   <%-- list the search results --%>
					<% int RecordCounter = 0; %>
				  	 <logic:iterate id="notificationResults" name="searchNotificationsForm" property="searchResults"> 
				      <%-- Begin Pagination item wrap --%>
				      <pg:item>
				      		<tr class="<% RecordCounter++;
				     					if (RecordCounter % 2 == 1)
				     						out.print("normalRow");
				     					else
				     						out.print("alternateRow");
				      				%>">		 	
				             <%-- if the item is a Task, the hyperlink that follows is a URL to some web page --%>
				             <%-- for a sample, in the prototype, expand the [+] My Tasks, and select any Task --%>
				            <logic:equal name="notificationResults" property="task" value="true">
				        	<td>
								<a href="/appshell/acceptTask.do?taskId=<bean:write name='notificationResults' property='taskId'/>"><bean:write name="notificationResults" property="subject"/></a>
							</td>
				            </logic:equal>
				
				            <%-- if the item is a Notice, the hyperlink that follows generates a pop-up dialog box with a message --%>
				            <%-- for a sample, in the prototype, expand the [+] My Notices, then select the first Notice  --%>
				            <logic:equal name="notificationResults" property="task" value="false">
					        	<td>
									<div id="not<bean:write name="notificationResults" property="notificationId"/>" class=hidden><div class=dhtmlWindowContent><bean:write name="notificationResults" property="message"/></div></div>
									<a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayNotificationSearch.do?submitAction=View&notificationId=<bean:write name="notificationResults" property="notificationId"/>', 250, 400);">
				        				<bean:write name="notificationResults" property="subject"/>
			        				</a>
								</td>					
				            </logic:equal>
				
				             <td><bean:write name="notificationResults" property="status"/></td>
				             <td><bean:write name="notificationResults" property="type"/></td>
				             <td><bean:write name="notificationResults" property="sentDate"/></td>
				             <td><bean:write name="notificationResults" property="ownerUserId"/></td>
				            </tr>
				        </pg:item>
			    		  <%-- End Pagination item wrap --%>
		  		   </logic:iterate>
		      </table>
	       </logic:notEmpty>
			  </td>
		  </tr>
		</table>
<%-- END DETAIL TABLE --%>
<%-- Begin Pagination Closing Tag --%>
<%-- End Pagination Closing Tag --%>
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

</pg:pager>
<%-- BEGIN BUTTON TABLE --%>
<br>
<table border="0" width="100%">
  <tr>
    <td align="center">
    		<html:submit property="submitAction"><bean:message key='button.cancel'/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</body>
</html:html>

