<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/30/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>

<html:form action="/displayEmailSocialHistoryReport" target="content"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 
<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
		<tr> 
			<td align="center" class="header">
				<bean:message key="title.mjcwConductInterview"/> - E-mail Social History Report
				<logic:equal name="status" value="summary">
					Summary
				</logic:equal>
				<logic:equal name="status" value="confirm">
					Confirmation
				</logic:equal>
			</td>
		</tr> 
		<logic:equal name="status" value="confirm">
			<tr>
				<td class="confirm" align="center">Social History Report has been sent.</td>
			</tr>
		</logic:equal>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<logic:notEmpty name="status">
<div class=spacer></div>
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Enter the recepient's e-mail address and select Send button to send the report.</li>
			</ul>
		</td>
	</tr>
</table> 
</logic:notEmpty>
<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN HEADER INFO TABLE --%>
<div class=spacer></div>
<div class=spacer></div>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class=spacer></div>
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
							<div class=spacer></div>
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
												  <div class=spacer></div>
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
																			<logic:empty name="status">
																				<html:text name="juvenileInterviewForm" property="emailAddress"/>
																			</logic:empty>
																			<logic:notEmpty name="status">
																				<bean:write name="juvenileInterviewForm" property="emailAddress"/>
																			</logic:notEmpty>
																		</td>
																	</tr>
																	<tr>
																		<td class=formDeLabel nowrap width='1%'>Subject</td>
																		<td class=formDe><bean:write name="juvenileInterviewForm" property="emailSubject"/></td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>	
											<tr>
												<td valign=top align=center>
													<div class=spacer></div>
													<logic:empty name="status">
														<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
													</logic:empty>
													<logic:notEmpty name="status">
														<logic:equal name="status" value="summary">
															<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.send"/></html:submit>
															<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
														</logic:equal>
														<logic:equal name="status" value="confirm">
															<html:submit property="submitAction"><bean:message key="button.interviewList"/></html:submit>
														</logic:equal>
													</logic:notEmpty>
												</td>
			       				 </tr>
					        </table>
									<div class=spacer></div>
								</td>
			        </tr>
	        	</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>