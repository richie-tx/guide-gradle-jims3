<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- conductInterviewDetention.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<script type='text/javascript'> 
function validateDetentionReason(theForm)
{
	var selection = theForm["detentionReasonIndex"];
	for(i = 0; i< selection.length; i++)
	{
		if(selection[i].checked)
			return true;
	}
	alert("Please select a detention reason to continue.");
	return false;
}
</script>
</head>

<html:form action="/displayDetentionReason" target="content"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header">Juvenile Casework - Conduct Interview<br>Social History Data - Detention Reason</td>
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul>
				<li>Check appropriate Detention Reason box(s) then select Next button to enter detention notification information.</li>
				<li>Click Back button to return to the previous page.</li>
			</ul>
		</td>
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%-- BEGIN HEADER INFO TABLE --%>
<div class='spacer'></div>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign=top>
			<%-- BEGIN TAB TABLE --%>
 			<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
 				<tiles:put name="tabid" value="casefiledetailstab"/>	
 				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
 			</tiles:insert>	

			<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top align=center>
					<div class='spacer'></div>
					 <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
											<%--tabs start--%>
											<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
											<%--tabs end--%>
										</td>
									</tr>
									<tr>
								  	<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
								  </tr>
					  		</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>
					
                     <div class='spacer'></div>						
	       							<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	       								<tr>
	       									<td valign=top>
	       										<table width='100%' border="0" cellpadding="0" cellspacing="0" >
	       											<tr>
	       												<td valign=top>
	       													<%--tabs start--%>
	       													<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
	       														<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
	       													</tiles:insert>
	       													<%--tabs end--%>
	       												</td>
	       											</tr>
	       											<tr><td bgcolor=#ff6666><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
	       										</table>
	       
	       										<%--begin outer blue border --%>
	       										<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
	       											<tr>
	       												<td valign=top align=center>
																	<div class='spacer'></div>
	       													<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align=center>
	       														<tr>
	       															<td valign=top class=detailHead>Detention Notification Information</td>
	       														</tr>
	       														<tr>
	       															<td align=center>
	       																<table border="0" cellpadding="2" cellspacing="1" width='100%'>
	       																	<tr>
	       																		<td class=formDeLabel nowrap>Hearing Date</td>
	       																		<td class=formDe><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtDate" formatKey="date.format.mmddyyyy"/></td>
	       																		<td class=formDeLabel nowrap>Hearing Time</td>
	       																		<td class=formDe><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtDate" formatKey="time.format.hhmma"/></td>
	       																	</tr>
	       																	<tr>
	       																		<td class=formDeLabel nowrap width='1%'>Assigned Court</td>
	       																		<td class=formDe><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtName"/></td>
	       																		<td class=formDeLabel nowrap width='1%'>Judge Name</td>
	       																		<td class=formDe><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.judgesName"/></td>
	       																	</tr>
	       																	<tr>
	       																		<td class=formDeLabel nowrap>Referral #</td>
	       																		<td class=formDe colspan=3><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.referralNumber"/></td>
	       																	</tr>
	       																</table>
	       															</td>
	       														</tr>
	       													</table>
		       											</td>
		       										</tr>
		       										<tr>
		       											<td valign=top><br></td>
		       										</tr>
		       										<tr>
		       											<td>
		       												<table width='98%' border="0" cellpadding="2" cellspacing="2" class="borderTableBlue" align=center>
		       													<tr>
		       														<td valign=top class=detailHead colspan=4>Detention Reasons</td>
		       													</tr>
		       													<logic:iterate id="detentionIter" indexId="index" name="socialHistoryForm" property="detentionReasonsList" indexId="index">
		                                   							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
		       															<td><html:radio name="socialHistoryForm" property="detentionReasonIndex" value="<%=index.toString()%>"/></td>
		       															<td><bean:write name="detentionIter"/></td>
		       														</tr>
		       													</logic:iterate>
		       												</table>
																	
		       												<%-- BEGIN BUTTON TABLE --%>
																	<div class='spacer'></div>
		       												<table border="0" width="100%">
		       													<tr>
		       														<td align="center">
		       															<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
		       															<html:submit property="submitAction" onclick="return validateDetentionReason(this.form);"><bean:message key="button.next"/></html:submit> 
		       															<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
		       														</td>
		       													</tr>
		       												</table><%-- END BUTTON TABLE --%>
		       												<div class='spacer'></div>
		       											</td>
		       										</tr>
		       									</table>
		       								</td>
		       							</tr>
		       						</table>
		       						<div class='spacer'></div>
		       					</td>
		       				</tr>
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
								</td>
							<tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

