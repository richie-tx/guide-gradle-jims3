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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- profileEmailSocialHistoryReport.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>

<html:form action="/displayJuvenileProfileEmailSocialHistoryReport" target="content"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 


	<%-- BEGIN HEADING TABLE --%> 
	<table width='100%'> 
		<tr> 
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - E-mail Social History Report</td>
		</tr> 
	</table> 
	<%-- END HEADING TABLE --%> 

	<%-- BEGIN INSTRUCTION TABLE --%> 
	<br> 
	<table width="98%" border="0"> 
		<tr> 
			<td> 
				<ul> 
					<li>Enter the recepient's e-mail address and select Send button to send the report.</li>
				</ul>
			</td>
		</tr> 
	</table> 
	<%-- END INSTRUCTION TABLE --%> 

	<br>

	<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
	<%-- END HEADER INFO TABLE --%>
	<br>  

	<%--BEGIN FORM TAG--%>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	  	<tr>
	    	<td valign=top>
	    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top>
							<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="casefilestab"/>
								<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
							</tiles:insert>				
						</td>
					</tr>
					<tr>
						<td bgcolor='#33cc6'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
					</tr>
	            </table>

		
				<%-- BEGIN DETAIL TABLE --%>

				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
					<tr>
						<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
					</tr>
					<tr>
						<td valign=top align=center>
							<%-- begin blue tabs (2nd row) --%>
							<table width='98%' border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign=top> 
										<%--tabs start--%>
										<tiles:insert page="../caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
											<tiles:put name="tabid" value="interview"/>
											<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
										</tiles:insert>			
										<%--tabs end--%>
									</td>
								</tr>
								<tr>
					  				<td bgcolor='#6699FF'>
					  					<table border=0 cellpadding=2 cellspacing=1>
					  						<tr>
					  							<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link'>View Interviews</a> <b>|</b> </td>
					  							<td  align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Report History</a> <b>|</b> </td>
					  						</tr>
					  					</table>
					    			</td>
								</tr>
							</table>

							<%-- BEGIN BORDER TABLE BLUE TABLE --%>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr><td><div class=spacer></div></td></tr>
								<tr align=center>
									<td>
										<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td class=detailHead>Report Detail</td>
											</tr>
											<tr>
												<td valign=top align=center>
													<table width='100%' border="0" cellpadding="4" cellspacing="1" >
														<tr>
															<td class=formDeLabel nowrap width='1%'>Creation Date</td>
															<td class=formDe><bean:write name="juvenileInterviewForm" property="currentReportHeaderInfo.creationDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr>
															<td class=formDeLabel nowrap width='1%'>Report Type</td>
															<td class=formDe><bean:write name="juvenileInterviewForm" property="currentReportHeaderInfo.reportType"/></td>
														</tr>
														<tr>
															<td class=formDeLabel nowrap width='1%'>Email Address</td>
															<td class=formDe>
																<html:text name="juvenileInterviewForm" property="emailAddress" styleId="emailAddress" size="60" maxlength="100"  />
															</td>
														</tr>
														<tr>
															<td class=formDeLabel nowrap width='1%'>Subject</td>
															<td class=formDe>The attached Social History Report has been generated for <bean:write name="juvenileInterviewForm" property="juvenileName.formattedName"/>.</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr><td><div class=spacer></div></td></tr>
								<tr>
									<td valign=top align=center>
										<input type="button" name="" value="Back" onclick="goNav('back');">
										<input type="submit" name="submitAction" value="Send" onclick="alert('Email has been sent.')">
										<input type="submit" name="submitAction" value="Cancel" onclick="goNav('interviewList.htm');">
									</td>
								</tr>
								<tr><td><div class=spacer4px></div></td></tr>
							</table>
						</td>
					</tr>
					<tr><td><div class=spacer></div></td></tr>
				</table>
			</td>
		</tr>
	</table>
							
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>