<!DOCTYPE HTML>

<!--MODIFICATIONS -->
<!-- 10/14/2015 Richard Capestani #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- programReferralDetails.jsp</title>
<%--Javascript for emulated navigation --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" >
	<html:form action="/handleProgramReferral" target="content">

       <input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|391">

		<%--BEGIN HEADING TABLE --%>
		<table width='100%'>
		  <tr>
		    <td align="center" class="header" ><bean:message key="title.serviceProvider" /> - View Social History Reports</td> 
		  </tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr>
		  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
		  </tr>
		</table>
		<%--END HEADING TABLE --%>
		
		<%-- Begin Pagination Header Tag--%>
		<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
		
		<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
				maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
		<input type="hidden" name="pager.offset" value="<%= offset %>">
		<%-- End Pagination header stuff --%>	
		

		<%--BEGIN INSTRUCTION TABLE --%>
		<div class='spacer'></div>
		<table width="98%">
		  <tr>
		    <td>
			   <ul>
		    		<li>Select Report Creation Date to see the report details.</li>
			   </ul>
		    </td>
		  </tr>
		</table>
		<%--END INSTRUCTION TABLE --%>

		<%--BEGIN HEADER INFO TABLE --%>
		<tiles:insert page="/jsp/common/juvenileHeaderDetails.jsp" flush="true">
		  <tiles:put name="headerType" value="casefileheader" />
		</tiles:insert>
		<%--END HEADER INFO TABLE --%>

		<%--BEGIN DETAIL TABLE --%>
		<div class='spacer'></div>
		<table width="96%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr>
			   <td width='1%' class="detailHead">Report History </td>
		  	</tr>
		  	<tr>
				<td>
	    	        <table align="center" width="100%" cellpadding="2" cellspacing="1">  
						<logic:empty name="programReferralForm" property="reportHistory">
							<tr bgcolor='#cccccc'> 
								<td colspan="4" class=subHead>No Report History Records Available </td> 
							</tr> 
						</logic:empty>
						<logic:notEmpty name="programReferralForm" property="reportHistory">
							<tr class=formDeLabel>
	        					<td valign=top nowrap><bean:message key="prompt.reportCreationDate"/></td>
	        					<td valign=top nowrap><bean:message key="prompt.reportType"/></td>
	   						</tr>						
	        				<%int RecordCounter = 0; String bgcolor = "";%>
	        				<logic:iterate id="reportIter" name="programReferralForm" property="reportHistory">
	        					<%-- Begin Pagination item wrap --%>
	        					<pg:item>
	        						<tr class=<%RecordCounter++;  
												bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
	        									out.print(bgcolor);%>
									>
	        							<td valign=top>
	        								<a href="/<msp:webapp/>handleProgramReferral.do?submitAction=Link&reportId=<bean:write name='reportIter' property='reportId'/>">
	        									<bean:write name="reportIter" property="creationDate" formatKey="date.format.mmddyyyy"/></a>&nbsp;
	        									<bean:write name="reportIter" property="creationDate" formatKey="time.format.hhmma"/>
	        							</td>
	        							<td valign=top><bean:write name="reportIter" property="reportType"/></td>
	        						</tr>
	        					</pg:item>
	        					<%-- End Pagination item wrap --%>
	        				</logic:iterate>
	        			 </logic:notEmpty>
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
		<table align='center'>
		   	<tr align="center">
		       	<td>
		           	<div class='spacer'></div>
		           	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
		           	<html:submit property="submitAction"><bean:message key="button.generateReport"/></html:submit> 
		           	<html:submit property="submitAction"><bean:message key="button.refreshReportList"/></html:submit> 
		           	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		         </td>
		    </tr>
		</table>
	</pg:pager>
</html:form>
</body>
</html:html>
