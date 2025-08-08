<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/01/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/displayJuvenileProfileInterviewSummary" target="content"> 
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|208">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Update Interview Summary Notes</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Interview Date to see details.</li>
        <li>Select Add Interview button to create an interview.</li>
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
											<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Interviews</a> <b>|</b> </td>
											<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Report History</a> <b>|</b> </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
		
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign=top><br></td>
							</tr>
							<tr>
								<td valign=top align=center>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class=detailHead>Interview Detail</td>
										</tr>
										<tr>
											<td valign=top align=center>
												<table width='100%' border="0" cellpadding="4" cellspacing="1" >
													<tr>
														<td valign=top class=formDeLabel colspan=4>
															<table width='100%' cellpadding=0 cellspacing=0 border=0>
																<tr>
																	<td width='1%'><a href="javascript:showHideMulti('Photos', 'phChar', 1, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="Photos"></a></td>
																	<td class=formDeLabel nowrap valign=top>&nbsp;Photos</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr id="phChar0" class=hidden>
														<td valign=top>
															<table width='98%' cellpadding=2 cellspacing=2 border=0>
																<tr bgcolor=white>
																	<td valign=top width=70%>
																		<table width='98%' cellpadding=4 cellspacing=1>              								
																			<tr>
																				<td  width=6% nowrap align="left">
																					
																					<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
													  									<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)"  > 
													  									 <img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>" width="128" border=1> 
													  									</a>
													  								</logic:notEmpty>

													  								<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
													  									<b>Juvenile has no photos</b>
													  								</logic:empty>
																				</td>
																			</tr>
																			<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
																				<tr>
																					<td align=left><bean:write name="juvenilePhotoForm" property="mostRecentPhoto.entryDate" formatKey="date.format.mmddyyyy"/></td>
																				</tr>
																			</logic:notEmpty>	
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													
													<tr>
														<td class=formDeLabel nowrap width='1%'>Interview Date</td>
														<td class=formDe><bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="date.format.mmddyyyy"/></td>
														<td class=formDeLabel nowrap width='1%'>Interview Time</td>
														<td id='interviewTime' class=formDe><bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="time.format.HHmm"/></td>
													</tr>
													<tr> 
														<td class=formDeLabel nowrap valign=top width="1%">Person(s) Interviewed</td> 
														<td class=formDe colspan="3">
															<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
																<logic:iterate id="personsIter" name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
																	<bean:write name="personsIter" property="formattedName"/><br>
																</logic:iterate>
															</logic:notEmpty>
															
														</td> 
													 </tr> 
													
													<tr id='recordInventory'> 
														<td class=formDeLabel nowrap valign=top width='1%'>Records Inventory</td> 
														<td class=formDe colspan="3">
															<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay">
																<logic:iterate id="recordsIter" name="juvenileInterviewForm" property="currentInterview.recordsInventoryDisplay">
																	<logic:notEmpty name="recordsIter">
																		<bean:write name="recordsIter"/>
																	</logic:notEmpty>
																</logic:iterate>
															</logic:notEmpty>
														</td> 
													</tr> 
													<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.otherInventoryRecords">
														<tr>
															<td class=formDeLabel nowrap valign=top width='1%'>Other Records Inventory</td> 
															<td class=formDe colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.otherInventoryRecords" /></td> 
														</tr>
													</logic:notEmpty>
													<tr> 
														<td class=formDeLabel nowrap width='1%'>Interview Type</td> 
														<td class=formDe colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.interviewType"/></td> 
													</tr> 
													<tr id='interviewLocation'>
														<td class=formDeLabel nowrap width="1%">Interview Location</td> 
														<td class=formDe colspan="3"><bean:write name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription"/></td> 
													</tr>
															 
													<logic:empty name="juvenileInterviewForm" property="currentInterview.juvLocUnitDescription">
														<tr id='saddr0'>
															<td class=formDeLabel >Street number</td>
															<td class=formDe ><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetNum"/></td>
															<td class="formDeLabel">Street Name</td>
															<td class=formDe><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.streetName"/></td>
														</tr>

														<tr id='saddr1'>
															<td class="formDeLabel">Street Type</td>
															<td class=formDe>
																<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.newAddress.streetTypeCode">
																	<bean:define id="streetTypeCode" name="juvenileInterviewForm" property="currentInterview.newAddress.streetTypeCode" type="java.lang.String"/>
																		<%=CodeHelper.getCodeDescriptionByCode(JuvenileInterviewForm.streetTypeList
																		, streetTypeCode)%>
																</logic:notEmpty>
															</td>
															<td class="formDeLabel">Apt/Suite</td>
															<td class=formDe><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.aptNum"/></td>
														</tr>
														  <tr id='saddr2'>
														  <td class="formDeLabel">City</td>
														  <td class=formDe><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.city"/></td>
														  <td class="formDeLabel">State</td>
														  <td class=formDe>
															<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.newAddress.stateCode">
																<bean:define id="stateCode" name="juvenileInterviewForm" property="currentInterview.newAddress.stateCode" type="java.lang.String"/>
																	<%=CodeHelper.getCodeDescriptionByCode(JuvenileInterviewForm.stateList
																	, stateCode)%>
															</logic:notEmpty>
														  </td>
														</tr>
														  <tr id='saddr3'>
														  <td class="formDeLabel">Zip Code</td>
														  <td class=formDe colspan='3'><bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.zipCode"/>-<bean:write name="juvenileInterviewForm" property="currentInterview.newAddress.additionalZipCode"/></td>
														</tr>
													</logic:empty>							
																						
													<tr> 
														<td class=formDeLabel valign=top nowrap colspan="4">Summary Notes</td> 
													</tr> 
													<tr> 
														<td class=formDe colspan="4"><html:textarea name="juvenileInterviewForm" property="currentInterview.summaryNote" rows="3" style="width:100%"/></td> 
													</tr>
												</table>
											</td>
										</tr>
										<tr><td><div class=spacer></div></td></tr>
									</table>
									<%-- BEGIN BUTTON TABLE --%>
								</td>
							</tr>
							<tr><td><img src='/<msp:webapp/>images/spacer.gif' width=5></td></tr>
							<tr>
								<td>
									<table width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>		
												<html:submit property="submitAction"><bean:message key="button.submit"/></html:submit>
										        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
									    </tr> 
												
												
											</td>
										</tr>
									</table>
									<div class=spacer></div> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td><div class=spacer></div></td></tr>
			</table>
		</td>
	</tr>
</table>

<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
</table>
</span>
<%-- END DETAIL TABLE --%>




</html:form>
<br>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
