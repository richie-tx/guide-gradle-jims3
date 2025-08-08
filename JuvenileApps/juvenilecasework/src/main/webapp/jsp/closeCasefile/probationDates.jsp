<!DOCTYPE HTML>

<%-- Used for Create Juvenile Referrals --%>
<%--MODIFICATIONS --%>
<%-- UGopinath 10/25/2018	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- Changes for JIMS200077276 ends -->
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> -
	probationDates.jsp</title>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/referral/updateReferralProbDate.js"></script>
<script>
	$(document).ready(function(){
		$("#referralBriefingBtn").click(function(e){
			e.preventDefault();
			spinner();
			$('form').attr('action','/JuvenileCasework/displayJuvenileBriefingDetails.do?fromProfile=profileBriefingDetails&submitAction=referralLink&juvenileNum=<bean:write name="casefileClosingForm" property="juvenileNum"/>');
			$('form').submit();
		})
	})
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
	<%--BEGIN FORM TAG--%>
	<html:form action="processCasefileClosing.do" target="content">

		<%-- BEGIN HEADING TABLE --%>
		</br>
		<table width='100%'>
			<tr>
				<td align="center" class="header">Juvenile Casework - Close
					Casefile - Referral Probation Dates and Close Date Entry</td>
			</tr>
		</table>
		<%-- END HEADING TABLE --%>

		<logic:messagesPresent message="true">
			<table width="100%">
				<tr>
					<td align="center" class="messageAlert"><html:messages
							id="message" message="true">
							<bean:write name="message" />
						</html:messages>
					</td>
				</tr>
			</table>
		</logic:messagesPresent>
		<%-- BEGIN ERROR TABLE  --%>
		<table width="98%" border="0" align="center">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors>
				</td>
			</tr>
		</table>
		<%-- END ERROR TABLE --%>
		</br>
		<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
 	<tr>
	   	<td>
			<ul>
       			<li>Enter information, then click Next button.</li>
       			<li>Click "Back" button to return to the previous page.</li>
       			<%-- <li><bean:message key="prompt.diamond" /> &nbsp; Required Fields.</li> --%>
       			<li>*All date fields must be in the format of mm/dd/yyyy.</li>
	  		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
		<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp"
			flush="true">
			<tiles:put name="headerType" value="casefileheader" />
		</tiles:insert>
		</br>


		<%-- BEGIN DETAIL TABLE --%>
 <%-- BEGIN CASEFILE TABS TABLE --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
      <%-- END CASEFILE TABS TABLE --%>	
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2"
			cellspacing="1" class='borderTableBlue'>	
			<logic:empty name="casefileClosingForm" property="activereferralList">
				<tr>
					<td class="detailHead" nowrap="nowrap" align="left" valign="top">No Records Found.</td>
				</tr>
			</logic:empty>	
			<logic:notEmpty name="casefileClosingForm" property="activereferralList">	
			<tr><td class="detailHead" nowrap="nowrap" align="left" valign="top"> Referral Probation Dates Entry</td></tr>
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
					
						<tr class="detailHead" colspan=6>
							<td></td>
							<td><bean:message key="prompt.refNo" />
							</td>
							<td valign='top'><bean:message key="prompt.referralDate" />
							</td>
							<td valign='top'><bean:message key="prompt.offense" />
							</td>
							<td valign='top'><bean:message key="prompt.petitionNumber" />
							</td>
							<td><bean:message key="prompt.disposition" />
							</td>
							<td valign='top'><bean:message key="prompt.dispositionDate" />
							</td>
							<td valign='top'><bean:message
									key="prompt.probationStartDate" />
							</td>
							<td valign='top'><bean:message key="prompt.probationEndDate" />
							</td>

							<!-- probation start date and end date -->
						</tr>
						
						
						<logic:iterate id="referralList" name="casefileClosingForm"
							property="activereferralList" indexId="indexer">
							<bean:define id="referralId" name="referralList"
								property="referralNumber" type="java.lang.String"></bean:define>
							<%-- Begin Pagination item wrap --%>
							<pg:item>
								
								<tr
									class="<%out.print((indexer.intValue() % 2 == 1)
								? "alternateRow"
								: "normalRow");%>">
									<%-- </logic:equal>	 --%>
									<td class='formDe'><input type=checkbox
										name="selectedToupdate" id="<%="manageRefNum-" + referralId%>"
										value="<bean:write name="referralList" property="referralNumber"/>"></input>
									</td>
									<td class='formDe'><bean:write name="referralList"
											property="referralNumber" />
									</td>
									<td class='formDe'><bean:write name="referralList"
											property="referralDate" formatKey="date.format.mmddyyyy" /><html:hidden styleId='<%="referralDate-"+referralId%>' name="referralList" property="referralDate"/>
									</td>									
									<td class='formDe' ><span title="<bean:write name="referralList" property="offenseDesc"/>"><bean:write name="referralList" property="offense"/></span></td>
									<td class='formDe'><bean:write name="referralList"
											property="petitionNumber" />
									</td>
									<td class='formDe'><bean:write name="referralList"
											property="finalDisposition" />
									</td>
									<td class='formDe'><bean:write name="referralList"
											property="courtDate" formatKey="date.format.mmddyyyy" /><html:hidden styleId='<%="courtDate-"+referralId%>' name="referralList" property="courtDate"/>
									</td>
									<td class='formDe'><bean:write name="referralList" 
											property="probationstartDate"
											formatKey="date.format.mmddyyyy" /><html:hidden styleId='<%="probationstartDate-"+referralId%>' name="referralList" property="probationstartDate"/>
									</td>
									<td class='formDe'><bean:write name="referralList" 
											property="probationendDate" formatKey="date.format.mmddyyyy" /><html:hidden styleId='<%="probationEndDate-"+referralId%>' name="referralList" property="probationendDate"/>
									</td>
									<html:hidden styleId='<%="intakeDate-"+referralId%>' name="referralList" property="intakeDate"/>
									<html:hidden styleId='<%="intakeDecisionTJPCCode-"+referralId%>' name="referralList" property="intakeDecisionTJPCCode"/>
									<html:hidden styleId='<%="finalDispositionTJPCCode-"+referralId%>' name="referralList" property="finalDispositionTJPCCode"/>
								
									<%-- <td class='formDe'><logic:notEmpty name="referralList"
											property="supervisionCategory">
											<span
												title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write
													name="referralList" property="supervisionCategoryId" />
											</span>/<span
												title="<bean:write name="referralList" property="supervisionType"/>"><bean:write
													name="referralList" property="supervisionTypeId" />
											</span>
										</logic:notEmpty></td>
									<td class='formDe'><span
										title="<bean:write name="referralList" property="jpo"/>"><bean:write
												name="referralList" property="jpoId" />
									</span>
									</td> --%>
								</tr>
							</pg:item>
							<%-- End Pagination item wrap --%>
						</logic:iterate>
						
					</table>
				</td>
			</tr>

			<div class='spacer'></div>
			<div class='spacer'></div>
			<div class='spacer'></div>
			<div class='spacer'></div>
			<div class='spacer'></div>

			</tr>
			<tr>


				<td>
					<table width="100%" cellpadding="5" cellspacing="1" border='0'>
						<tr>
							<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message
									key="prompt.probationStartDate" /></td>
							<td class='formDe'><html:text styleId="probationStartDate"
									property="probationStartDate" size="8" maxlength="10" /></td>
							<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message
									key="prompt.probationEndDate" /></td>
							<td class='formDe'><html:text styleId="probationEndDate"
									property="probationEndDate" size="8" maxlength="10" /></td>
							<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message
									key="prompt.referralcloseDate" /></td>
							<td class='formDe'><html:text styleId="updtateRefCloseDt"
									property="refCloseDate" size="8" maxlength="10" /></td>
							<td><html:submit property="submitAction" styleId="updateBtn">
									<bean:message key="button.update" />
								</html:submit>
							<html:submit property="submitAction" styleId="refreshBtn">Refresh</html:submit>
							</td>
						</tr>
						<logic:notEmpty name="casefileClosingForm" property="newDates">
							<table table width='100%' cellspacing=1>								
								<tr><td class="detailHead" nowrap="nowrap" colspan=5 align="left" valign="top"> Referral Dates Summary</td></tr>
								<tr bgcolor='#cccccc'>
									<td class="subhead" valign=top width="8%">&nbsp;</td>
									<td class="subhead" valign=top width="14%" align="left"><bean:message
											key="prompt.refNo" />
									</td>
									<td class="subhead" valign=top width="14%" align="left"><bean:message
											key="prompt.probationStartDate" />
									</td>
									<td class="subhead" valign=top width="14%" align="left"><bean:message
											key="prompt.probationEndDate" />
									</td>
									<td class="subhead" valign=top width="14%" align="left"><bean:message
											key="prompt.referralcloseDate" />
									</td>
								</tr>

								<logic:iterate id="date" name="casefileClosingForm"
									property="newDates" indexId="index">
									<tr>

										<td align="left"><a
											href="/<msp:webapp/>processCasefileClosing.do?submitAction=Remove&selectedValue=<%=(index.intValue())%>">Remove&nbsp;</a>
										</td>
										<td nowrap align="left"><bean:write name="date"
												property="referralNumber" />&nbsp;&nbsp;</td>
										<td nowrap align="left"><bean:write name="date"
												property="probationStartDate" />&nbsp;&nbsp;</td>
										<td nowrap align="left"><bean:write name="date"
												property="probationEndDate" />&nbsp;&nbsp;</td>
										<td nowrap align="left"><bean:write name="date"
												property="refCloseDate" />&nbsp;&nbsp;</td>

									</tr>
								</logic:iterate>

							</table>
						</logic:notEmpty>
					</table>
				</td>
			</tr>
			</logic:notEmpty>
		</table>
		</table>
		</td>
		</tr>
		<html:hidden styleId="action" name="casefileClosingForm"
			property="action" />
			<html:hidden styleId="hdnDates" name="casefileClosingForm"
			property="cartRefnums" />
		<%-- <html:hidden styleId="action" name="casefileClosingForm" property="selectedReferrals"/>		
		 --%>
		<%-- <html:hidden styleId="hascasefiles" name="juvenileBriefingDetailsForm" property="hasCasefiles"/>	
<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>	
<html:hidden styleId="selectedReferrals" name="juvenileReferralForm" property="selectedRefToOverride"/> <!-- Using this in JS -->	
<html:hidden styleId='juvNum'  name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/>
		 --%>
		<%-- END INFORMATION TABLE --%>
		<%-- END DETAIL TABLE --%>
		<!-- BEGIN BUTTON TABLE -->
		<div class='spacer'></div>
		<table width="98%" border="0" align="center">
			<tr>			
				<td align="center">
				<%-- <html:submit property="submitAction" styleId="backBtn">
						<bean:message key="button.back" />
				</html:submit>&nbsp; --%>
				<logic:notEmpty name="casefileClosingForm" property="activereferralList"><html:submit property="submitAction"
						styleId="submitNext">
						<bean:message key="button.next" /></html:submit>&nbsp; 
				</logic:notEmpty>						
				<%-- <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td> --%>
				<html:submit property="submitAction" styleId="btnCancel">Back</html:submit>
				<input id="referralBriefingBtn" type="button" value="Referral Briefing"/>
			</tr>
		</table>
		<%-- END BUTTON TABLE --%>
		<br>
	</html:form>

	<br>
	<div align='center'>
		<script type="text/javascript">
			renderBackToTop()
		</script>
	</div>

</body>
</html:html>
