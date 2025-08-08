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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- conductInterviewDetentionNotificationForm.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<html:javascript formName="socialHistoryForm"/>

<script type='text/javascript'>

$(function() {  
	datePickerSingle($("#personNotificationDateStr"),"Person Notification Date", false);
 });
 
$(function() {  
	datePickerSingle($("#attorneyNotificationDateStr"),"Attorney Notification Date", false);
 });
 
//constructor
function court(courtId, judgeName)
{
	this.courtId = courtId;
	this.judgeName = judgeName;
}

var courts = new Array(); //the array of courts

<logic:iterate indexId="courtsIndex" id="courtsIter" name="socialHistoryForm" property="juvenileCourts">
	courts[<bean:write name="courtsIndex"/>] = new court("<bean:write name='courtsIter' property='code'/>", "<bean:write name='courtsIter' property='judgeName'/>");
</logic:iterate>

		
function updateJudgeName(theForm)
{
	var dropdown = theForm["assignedCourtId"];
	var judgeNameObj = document.getElementById("judgeName");
	
	if(dropdown != null)
	{
		var index = dropdown.selectedIndex;
		var courtName = dropdown.options[index].value;
		
		if(courtName != "")
		{
			for(i in courts)
			{
				if(courts[i].courtId == courtName)
				{
					judgeNameObj.innerHTML = courts[i].judgeName;
					theForm["socialHistoryData.presentOffense.judgesName"].value = courts[i].judgeName;
					break;
				}
			}
		}
	}
}
</script>
</head>

<html:form action="/displayDetentionNotification" target="content"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="updateJudgeName(document.forms[0]);"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header">Juvenile Casework - Conduct Interview<br>Social History Data - Detention Notification
			<logic:equal name="state" value="summary"> Summary</logic:equal>
			<logic:equal name="state" value="confirm"> Confirmation</logic:equal>
		</td>
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

	

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul>
				<logic:present name="state">
					<li>Verify information is correct, then select Finish button to save Detention Reason & Notification.</li>
					<li>To change information entered, select Back button.</li>
				</logic:present>
				<logic:notPresent name="state">
					<li>Enter information, then select Next button to view summary.</li>
					<li>Select Back button to return to the previous page.</li>
				</logic:notPresent>
			</ul>
		</td>
	</tr> 
	<logic:notPresent name="state">
			<tr> 
				<td class="required"><img src="/<msp:webapp/>images/required.gif">&nbsp;Required Fields
					&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/>
				</td>
			</tr>
	</logic:notPresent>
		
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
		<td valign='top'>
			<%-- BEGIN TAB TABLE --%>
 			<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
 				<tiles:put name="tabid" value="casefiledetailstab"/>	
 				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
 			</tiles:insert>	

			<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
					 <div class='spacer'></div>
					 <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign='top'>
											<%--tabs start--%>
											<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
											<%--tabs end--%>
										</td>
									</tr>
									<tr>
								  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>spacer.gif" width='5'></td>
									</tr>
					  		</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'>
										<div class='spacer'></div>
											<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
												<tr>
													<td valign='top'>
														<table width='100%' border="0" cellpadding="0" cellspacing="0" >
															<tr>
																<td valign='top'>
																	<%--tabs start--%>
																	<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
																		<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
																	</tiles:insert>
																	<%--tabs end--%>
																</td>
															</tr>
															<tr><td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td></tr>
														</table>

														<%--begin outer blue border --%>
														<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
															<tr>
																<td valign='top' align='center'>
																  
																  <div class='spacer'></div>
																	<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align='center'>
																		<tr>
																			<td valign='top' class='detailHead'>Detention Notification Information</td>
																		</tr>
																		<tr>
																			<td align='center'>
																				<table border="0" cellpadding="2" cellspacing="1" width='100%'>
																					<tr>
																						<td class='formDeLabel' nowrap='nowrap'>Hearing Date</td>
																						<td class='formDe'><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtDate" formatKey="date.format.mmddyyyy"/></td>
																						<td class='formDeLabel' nowrap='nowrap'>Hearing Time</td>
																						<td class='formDe'><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtDate" formatKey="time.format.hhmma"/></td>
																					</tr>
																					<tr>
																						<td class='formDeLabel' nowrap='nowrap' width='1%'>Assigned Court</td>
																						<td class='formDe'>
																							<logic:notPresent name="state">	
																								<html:select name="socialHistoryForm" property="assignedCourtId" onchange="updateJudgeName(this.form);"> 
																									<html:option value=""><bean:message key="select.generic" /></html:option>
																									<html:optionsCollection name="socialHistoryForm"
																										property="juvenileCourts" value="code" label="description" />
																								</html:select>
																							</logic:notPresent>
																							<logic:present name="state">
																								<bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.courtName"/>
																							</logic:present>
																						</td>
																						<td class='formDeLabel' nowrap='nowrap' width='1%'>Judge Name</td>
																						<td class='formDe'>
																							<html:hidden name="socialHistoryForm" property="socialHistoryData.presentOffense.judgesName"/>
																							<span id='judgeName'><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.judgesName"/></span>
																						</td>
																					</tr>
																					<tr>
																						<td class='formDeLabel' nowrap='nowrap'>Referral #</td>
																						<td class='formDe' colspan='3'><bean:write name="socialHistoryForm" property="socialHistoryData.presentOffense.referralNumber"/></td>
																					</tr>
																					<tr>
																						<td class='formDeLabel' nowrap='nowrap'>
																							<logic:notPresent name="state">
																								<img src="/<msp:webapp/>images/required.gif">
																							</logic:notPresent>
																							<bean:message key="prompt.personNotifiedDate"/></td>
																						<td class='formDe'>
																							<logic:notPresent name="state">
																								<html:text name="socialHistoryForm" property="personNotificationDateStr" size="8" styleId="personNotificationDateStr"/>
																								
																							</logic:notPresent>
																							<logic:present name="state">
																								<bean:write name="socialHistoryForm" property="socialHistoryData.personNotificationDate" formatKey="date.format.mmddyyyy"/>
																							</logic:present>
																						</td>
																						<td class='formDeLabel' nowrap='nowrap'>
																							<logic:notPresent name="state">
																								<img src="/<msp:webapp/>images/required.gif">
																							</logic:notPresent>
																							<bean:message key="prompt.notificationMethod"/>
																						</td>
																						<td class='formDe'>
																							<logic:notPresent name="state">
																								<html:select name="socialHistoryForm" property="socialHistoryData.notifiedMethod"> 
																									<html:option value=""><bean:message key="select.generic" /></html:option>
																									<html:optionsCollection name="socialHistoryForm"
																										property="detentionNotificationMethod" value="description" label="description" />
																								</html:select>
																							</logic:notPresent>
																							<logic:present name="state">
																								<bean:write name="socialHistoryForm" property="socialHistoryData.notifiedMethod"/>
																							</logic:present>	
																						</td>
																					</tr>
																					<tr>
																						<td class='formDeLabel'>
																							<logic:notPresent name="state">
																								<img src="/<msp:webapp/>images/required.gif">
																							</logic:notPresent>
																							<bean:message key="prompt.notifiedPerson"/></td>
																						<td class='formDe' colspan='3'>
																							<logic:notPresent name="state">
																								<html:select name="socialHistoryForm" property="notifiedPersonId"> 
																									<html:option value=""><bean:message key="select.generic" /></html:option>
																									<html:optionsCollection name="socialHistoryForm"
																										property="notifiedPersonList" value="memberNumber" label="memberName.formattedName" />
																								</html:select>
																							</logic:notPresent>
																							<logic:present name="state">
																								<bean:write name="socialHistoryForm" property="socialHistoryData.notifiedPerson"/>
																							</logic:present>
																						</td>
																					</tr>
																					<tr>
																						<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.attorneyNotificationDate"/></td>
																						<td class='formDe' colspan='3'>
																							<logic:notPresent name="state">
																								<html:text name="socialHistoryForm" property="attorneyNotificationDateStr" size="8" styleId="attorneyNotificationDateStr"/>
																								
																							</logic:notPresent>
																							<logic:present name="state">
																								<bean:write name="socialHistoryForm" property="socialHistoryData.attorneyNotificationDate" formatKey="date.format.mmddyyyy"/>
																							</logic:present>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>

																	<div class='spacer'></div>
																	<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" align='center'>
																		<tr>
																			<td valign='top' class='detailHead' colspan='4'>Detention Reason</td>
																		</tr>
																		<tr bgcolor='#f0f0f0'>
																			<td align="left"><bean:write name="socialHistoryForm" property="socialHistoryData.detentionReasons"/></td>
																		</tr>
																	</table>
											
													        <%-- BEGIN BUTTON TABLE --%>
													        <div class='spacer'></div>
																	<table border="0" width="100%">
																		<tr>
																			<td align='center'>
																				<logic:notPresent name="state">
																					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
																					<html:submit property="submitAction" onclick="return validateSocialHistoryForm  (this.form);"><bean:message key="button.next"/></html:submit> 
																					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
																				</logic:notPresent>
																				<logic:equal name="state" value="summary">
																					<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
																					<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit> 
																					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
																				</logic:equal>
																				<logic:equal name="state" value="confirm">
																					<html:submit property="submitAction"><bean:message key="button.backToSocialHistoryData"/></html:submit>
																				</logic:equal>
																			</td>
																		</tr>
													        </table><%-- END BUTTON TABLE --%>
																</td>
															<tr>
														</table>
														<div class='spacer'></div>
													</td>
												</tr>
											<tr>
										<td>
										<div class='spacer'></div>
									</td>
								</tr>
											</table>
										</td>
									</tr>
								</table>
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

</body>
</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:html>

