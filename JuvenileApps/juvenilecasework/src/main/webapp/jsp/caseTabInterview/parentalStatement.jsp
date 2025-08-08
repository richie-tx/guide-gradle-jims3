<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/30/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF//pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- parentalStatement.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>
 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/handleJuvInterviewSelection" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|93"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
  	<td align="center" class="header"><bean:message key="title.juvenileCasework"/>&nbsp;-&nbsp;<bean:message key="title.mjcwConductInterview"/> - Print Parental Statement</td>
  </tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Select which version of document to print, and click <b>Generate Document</b> to continue.</li>
			</ul>
		</td>
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign=top>
			<%-- BEGIN TAB --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>	
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>	

			<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
	  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
            				<td bgcolor='#33cc66'>
            					<table border=0 cellpadding=2 cellspacing=1>
            						<tr>
            							<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message key="prompt.viewInterviews"/></a> <b>|</b> </td>
            							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a> <b>|</b> </td>
            						</tr>
            					</table>
              			</td>
            	    </tr>
							  	</table>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>
										<div class='spacer'></div>							
										<table id="financialHistoryTable" width='98%' cellspacing=1 cellpadding=4 class="borderTableBlue">
											<tr>
												<td valign=top class=detailHead colspan=4 nowrap>Document Version</td>
											</tr>
											<tr>
												<td class=formDeLabel width='1%' nowrap>English</td>
												<td class=formDe><html:radio name="juvenileInterviewForm" property="spanishText" value="false"/></td>
											</tr>
											<tr>
												<td class=formDeLabel width='1%' nowrap>Spanish</td>
												<td class=formDe><html:radio name="juvenileInterviewForm" property="spanishText" value="true"/></td>
											</tr>
										</table>


										<div class='spacer'></div>
										<table align='center'>
              					<tr>
              						<td valign=top align=center>
              							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
              							<html:submit property="submitAction"><bean:message key="button.generateDocument"/></html:submit> 
              							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
              						 </td>
                        </tr>
                      </table>
                    <div class='spacer'></div>
                 </td>
               </tr>
            </table>
            <div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>


</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
