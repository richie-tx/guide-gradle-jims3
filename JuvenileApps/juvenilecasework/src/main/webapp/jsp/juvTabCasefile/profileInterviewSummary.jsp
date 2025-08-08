<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/30/2006		AWidjaja Create JSP--%>
<%-- 07/16/2012     CShimek     #73565 added age > 20 check (juvUnder21) to Update button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>
<%@ page import="naming.InterviewConstants" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- profileInterviewSummary.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
} 
	
</script>
	
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileProfileUpdateSummaryNotes" target="content">
<logic:equal name="action" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|207">
</logic:equal>
<logic:equal name="action" value="updateSummaryNotes">
    <logic:notEqual name="action" value="summary">
        <logic:notEqual name="status" value="confirm"> 
            <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|208">
        </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="status" value="summary">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|210">
    </logic:equal>
    <logic:equal name="status" value="confirm">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|209">
    </logic:equal>
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >Juvenile Casework - Juvenile Profile - 
			<logic:equal name="action" value="view"> Interview Details</logic:equal>
			<logic:equal name="action" value="updateSummaryNotes"> Update Summary Notes</logic:equal>
			<logic:equal name="status" value="summary">	Summary</logic:equal>
			<logic:equal name="status" value="confirm">	Confirmation</logic:equal>
		</td>
	</tr>
	<logic:equal name="status" value="confirm">
		<tr><td align='center' class='confirm'>Additional Summary Notes have been added.</td></tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>	


<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
	<tr>
		<td>
			<input id='juvStatus' type='hidden' value='<bean:write name="juvenileProfileHeader" property="status"/>' >		
			<ul>
				<logic:equal name="action" value="view">
					<logic:equal name="juvenileInterviewForm" property="currentInterview.interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
						<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
							<li>Select the Update Summary Notes button to update summary notes.</li>
						</logic:equal>
						<li>Select Back button to return to the previous page.</li>
					</logic:equal>
				</logic:equal>
				<logic:equal name="action" value="updateSummaryNotes">
					<logic:equal name="status" value="">
						<li>Fill in the Summary Notes and click Next to view summary.</li>
						<li>Select Back button to return to the previous page.</li>
					</logic:equal>
					<logic:equal name="status" value="summary">
						<li>Verify that information is correct and select Finish button to proceed.</li>
						<li>If any changes are needed, select Back button to return to previous page.</li>
					</logic:equal>
				</logic:equal>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%--BEGIN FORM TAG--%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign='top'>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="casefilestab"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert>				
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
				</tr>

            </table>

	
			<%-- BEGIN DETAIL TABLE --%>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<%-- begin blue tabs (2nd row) --%>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign='top'> 
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
									<table border='0' cellpadding='2' cellspacing='1'>
										<tr>
											<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link'>View Interviews</a> <b>|</b> </td>
											<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Report History</a> <b>|</b> </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
						<%String mode = "";%>
						<logic:equal name="action" value="updateSummaryNotes">
							<logic:equal name="status" value="">
								<%mode="updateSummaryNotes";%>
							</logic:equal>
						</logic:equal>
						
						<tiles:insert page="../caseworkCommon/interviewDetailsTile.jsp" flush="true">
							<tiles:put name="currentInterview" beanName="juvenileInterviewForm" beanProperty="currentInterview"/>
							<tiles:put name="juvenileNum" value="<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>"/>
							<tiles:put name="juvenilePhotoForm" beanName="juvenilePhotoForm"/>
							<tiles:put name="mode" value="<%=mode%>"/>
						</tiles:insert>	

					</td>
				</tr>
				<tr>
					<td>
						<%-- BEGIN BUTTON TABLE --%>
						<div class='spacer'></div>
						<table width="100%">
							<logic:equal name="action" value="view">
								<logic:equal name="juvenileInterviewForm" property="currentInterview.interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
												<html:submit styleId="updateSummaryNotesId" property="submitAction"><bean:message key="button.updateSummaryNotes"/></html:submit>
											</logic:equal>
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>
									</tr>
								</logic:equal>
							</logic:equal>
							
							<logic:equal name="action" value="updateSummaryNotes">
								<logic:equal name="status" value="">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>
									</tr>
								</logic:equal>
								<logic:equal name="status" value="summary">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<input type="submit" name="submitAction" value="<bean:message key='button.finish'/>"
													onclick="changeFormActionURL('/<msp:webapp/>submitJuvenileProfileUpdateSummaryNotes.do', false);">
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>
									</tr>
								</logic:equal>
								
								<logic:equal name="status" value="confirm">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<input type="button" name="submitAction" value="<bean:message key='button.interviewList'/>" 
												onclick="changeFormActionURL('/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link', true);">
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>
									</tr>								
								</logic:equal>
							</logic:equal>
						</table>
						<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div class='spacer' align='center'><script type="text/javascript">renderBackToTop()</script></div>

<script type="text/javascript">

	var isClosedJuvStatus = document.getElementById("juvStatus");
	var btnUpateSummaryNotes = document.getElementById("updateSummaryNotesId");
	
	console.log('IsClosedJuvStatus - profile interview: ', isClosedJuvStatus.value);
	
	if(isClosedJuvStatus.value.toLowerCase() !== "closed"){
		btnUpateSummaryNotes.disabled = false;
	} 
	
	if(isClosedJuvStatus.value.toLowerCase() === "closed"){
		btnUpateSummaryNotes.disabled = true;
	} 
</script>
</body>
</html:html>
