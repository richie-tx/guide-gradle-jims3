<!DOCTYPE HTML>
<%-- Used to display search casefile results --%>
<%--MODIFICATIONS --%>
<%-- 12/11/2006	Awidjaja	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>



<%--BEGIN HEADER TAG--%>
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

<title>Juvenile Profile - Report History</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|293">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Report History</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click on a hyperlinked report date to view report details.</li>
		<li>Click on a hyperlinked Click here to email the report.</li>
      </ul>
	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>


<%--BEGIN FORM TAG--%>
<div class='spacer'></div>
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
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
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
				  						<td>&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link&juvnum=<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'>View Interviews</a> <b>|</b> </td>
				  						<td>&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Report History</a> <b>|</b> </td>
				  					</tr>
				  				</table>
				    		</td>
							</tr>
						</table>
					
						<%-- BEGIN ReportHistory TABLE --%>
  					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	  						<tr>
	  							<td align=center>
  									<logic:empty name="juvenileInterviewForm" property="reportMap">
  										<table width='99%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  											<tr>
  												<td class=detailHead nowrap valign=top>&nbsp;Report(s)</td>
  											</tr>
  											<tr bgcolor='#cccccc'>
  												<td class=subHead colspan=4>No Reports available.</td>
  											</td>
  										</table>
  									</logic:empty>

  									<logic:notEmpty name="juvenileInterviewForm" property="reportMap">
  										<logic:iterate id="keysIter" indexId="idx" name="juvenileInterviewForm" property="reportMap">
  											<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  												<tr>
  													<td class=detailHead width='1%'><a href="javascript:showHideMulti('toggle<bean:write name='idx'/>', 'phChar<bean:write name='idx'/>-', 1, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="toggle<bean:write name='idx'/>"></a></td>
  													<td class=detailHead nowrap valign=top>&nbsp;Report(s) for Supervision #<bean:write name="keysIter" property="key"/></td>
  												</tr>
  												<tr id="phChar<bean:write name='idx'/>-0" class=hidden>
  												<td colspan=2>
  													<table width='100%' cellpadding=4 cellspacing=1>
  														<tr class=formDeLabel>
  															<td valign=top nowrap width='20%'>Report Creation Date</td>
  															<td valign=top nowrap width='50%'>Report Type</td>
  															<td valign=top width='30%'>Email</td>
  														</tr>
  															<%int RecordCounter = 0;%>
  															<logic:iterate id="reportIter" indexId="reportIndex" name="keysIter" property="value">
  																<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
  																	<td valign=top>
  																		<bean:define id="reportId" name="reportIter" property="reportId"/>
  																		<a href='/<msp:webapp/>displayReportDetails.do?submitAction=Link&reportId=<%=reportId%>'>
  																		<bean:write name="reportIter" property="creationDate" formatKey="date.format.mmddyyyy"/></a>
  																		<bean:write name="reportIter" property="creationDate" formatKey="time.format.HHmm"/>
  																	</td>
  																	<td valign=top><bean:write name="reportIter" property="reportType"/></td>
  																	<td valign=top>
  																		<logic:equal name="reportIter" property="reportType" value="SOCIAL HISTORY REPORT">
  																			<a href="/<msp:webapp/>displayJuvenileProfileEmailSocialHistoryReport.do?submitAction=Link&reportId=<bean:write name='reportIter' property='reportId'/>">Click Here</a>
  																		</logic:equal>
  																	</td>
  																	
  																</tr>
  															</logic:iterate>	
  														</table>
  													</td>
  												</tr>
  											</table>
  										</logic:iterate>
  									</logic:notEmpty>
        						
                    <%-- BEGIN BUTTON TABLE --%>
                    <table border="0" align="center">
                    	<tr align="center">
                        <td>
                        	<html:submit property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:submit>
                    		</td>
                    		
                        <html:form action="/displayJuvenileMasterInformation" target="content"> 
                    	   	<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
                       	</html:form>
                    	</tr>
                    </table>
                    <%-- END BUTTON TABLE --%>
									
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
		
<%-- END DETAIL TABLE --%>
<div class='spacer'></div>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
