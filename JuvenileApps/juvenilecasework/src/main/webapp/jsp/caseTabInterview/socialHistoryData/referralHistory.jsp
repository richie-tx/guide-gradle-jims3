<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 12/06/2006		AWidjaja Create JSP--%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- referralHistory.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<html:form action="/handleSocialHistoryData" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|100"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
<tr> 
	<td align="center" class="header">
		Juvenile Casework - Conduct Interview<br>
		Social History Data - Referral History
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
			<li>Click check box to exclude from report, and click on Save Changes button to save the changes.</li>
	        <li>Click Court Disposition Alternatives button or Detention Reason button to proceed with report generation.</li>
	        <li>Click Back button to return to previous page. </li>
		</ul>
	</td>
</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
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
											<table width='98%' border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
											
												<tr class=detailHead>
													<td colspan='6'>Referrals</td>
												</tr>
												<tr bgcolor='#cccccc'>
													<td align="left" class="subhead" valign=top width='1%' nowrap>Exclude from CRIS?</td>
													<td align="left" class=subHead>Referral #</td>
													<td align="left" class=subHead>Court Date</td>
													<td align="left" class=subHead>Intake Decision</td>
													<td align="left" class=subHead>Court ID</td>
													<td align="left" class=subHead>Referral Date</td>
												</tr>
												<%int RecordCounter = 0; String bgcolor = "";%>
												<logic:empty name="socialHistoryForm" property="socialHistoryData.referralHistory">
													<tr>
														<td colspan="2">No data available</td>
													</tr>
												</logic:empty>
												<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.referralHistory">
													<nested:iterate id="warrantIter" name="socialHistoryForm" property="socialHistoryData.referralHistory">
														<tr class='<%RecordCounter++;  bgcolor = "alternateRow";
															if (RecordCounter % 2 == 1)
																bgcolor = "normalRow";						
															out.print(bgcolor);%>'>
															<td align="left" valign=top align=center><nested:checkbox property="excluded" value="true"/></td>
															<td align="left" valign=top>
																
																<nested:define id="casefileId" name="socialHistoryForm" property="casefileId"/>
																<nested:define id="juvnum" name="socialHistoryForm" property="socialHistoryData.juvenileNumber"/>
																<nested:define id="referralNumber" property="referralData.referralNumber"/>
																<a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayReferralDetails.do?casefileId=<%=casefileId%>&juvnum=<%=juvnum%>&refnum=<%=referralNumber%>', 400, 400);">
																
																<nested:write property="referralData.referralNumber"/>
															</td>
															<td align="left" valign=top><nested:write property="referralData.courtDate" formatKey="date.format.mmddyyyy"/></td>
															<td align="left" valign=top><nested:write property="referralData.intakeDecision"/></td>
															<td align="left" valign=top><nested:write property="referralData.courtId"/></td>
															<td align="left" valign=top><nested:write property="referralData.referralDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
													</nested:iterate>
												</logic:notEmpty>
											</table>

											<div class='spacer'></div>
											<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr>
													<td align="left" class=detailHead>Justice of Peace Court Referrals</td>
												</tr>

												<tr>
													<td>
														<table cellpadding=2 cellspacing=1 width='100%'>
															<tr bgcolor='#cccccc'>
																<td align="left" class="subhead" valign=top width='1%' nowrap>Include?</td>
																<td align="left" class=subHead>Name</td>
																<td align="left" class=subHead>Date of Birth</td>
																<td align="left" class=subHead>Race</td>
															</tr>
															<logic:empty name="socialHistoryForm" property="socialHistoryData.JPCourtReferrals">
																<tr>
																	<td align="left" colspan="2">No data available</td>
																</tr>
															</logic:empty>
															<logic:notEmpty name="socialHistoryForm" property="socialHistoryData.JPCourtReferrals">
																<%RecordCounter = 0; bgcolor = "";%>
																<nested:iterate id="referralIter" name="socialHistoryForm" property="socialHistoryData.JPCourtReferrals">
																	<tr class='<%RecordCounter++;  bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";						
																		out.print(bgcolor);%>'>
																		<td align="left" valign=top><nested:checkbox property="included" value="true"/></td>
																		<td align="left" valign=top>
																			<nested:define id="m204JuvNumber" property="m204JuvNumber"/>
																			<a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayJPCourtReferralDetails.do?selJuvNumber=<%=m204JuvNumber%>&submitAction=Link', 200, 400);">
																			<nested:write property="name"/></a>
																		</td>
																		<td align="left" valign=top><nested:write property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
																		<td align="left" valign=top><nested:write property="race"/></td>
																	</tr>
																</nested:iterate>
															</logic:notEmpty>	
														</table>
														<%-- END petition TABLE --%>
													</td>
												</tr>
											</table>
											
											
											
											<div class='spacer'></div>
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<input type="hidden" name="resetTabName" value="referralHistory"/>
														<input type="submit" name="submitAction" value="<bean:message key='button.back'/>"
															onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
														<html:submit property="submitAction"><bean:message key="button.saveChanges"/></html:submit> 
														<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> 
													</td>
												</tr>
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.generateDraftReport"/></html:submit>
														<html:submit property="submitAction"><bean:message key="button.generateFinalReport"/></html:submit>
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
<%-- End Pagination Closing Tag --%>
							
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:form>
</html:html>