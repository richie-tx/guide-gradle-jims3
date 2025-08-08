<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/29/2008	 C Shimek - Create JSP -->
<!-- 06/01/2009  C Shimek - #59896 remove occurence time in reporting history  -->
<!-- 03/31/2010  C Shimek - #64667 revised PCA block for Type of Court Action comments and removed comments display  -->
<!-- 05/06/2010  C Shimek - added () around comments in PCA block-->
<!-- 06/24/2011  R Young  - #70294 Sort Employment History by CLS -->
<!-- 03/05/2013  R Young  - #75066 CSCD:  Add new fields to VR and CS(UI) -->
<!-- 10/08/2013  R Capestani  - #71520 Format case summary in others section -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->
<% String displayClass = ""; %>  
<% int indexCounter = 0; %>  
<table width="100%">		
	<tr>
		<td>
<!-- BEGIN REASON FOR TRANSFER TABLE -->
 			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td>
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.reasonForTransfer" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=reasonForTransfer&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>										
							</tr>
						</table>
					</td>
				</tr>
				<logic:iterate id="rftIter" name="violationReportsForm" property="currentReasonForTransferList" indexId="index">
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td>
							<bean:write name="rftIter" property="description" />
						</td>
					</tr>	
				</logic:iterate>
				<tr class="formDeLabel">
					<td>Extended?&nbsp;</td>
				</tr>
				<tr>
					<td class="formDe">
						<bean:write name="violationReportsForm" property="isExtended"/>
					</td>
				</tr>
				<%--
				<tr class="formDeLabel">
					<td><bean:message key="prompt.reasonForTransfer" />&nbsp;<bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td class="formDe">
						<bean:write name="violationReportsForm" property="currentReasonForTransferComments"/>
					</td>
				</tr>
				--%>
			</table> 
<!--END REASON FOR TRANSFER TABLE -->
		</td>
	</tr>
	<tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
	<tr>
		<td>	
<!-- BEGIN MENTAL HEALTH TABLE -->	
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.mentalHealth" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=mentalHealth&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>	
				<tr class="formDeLabel">
					<td colspan="2"><bean:message key="prompt.mentalHealth" />&nbsp;<bean:message key="prompt.diagnosis" /></td>
				</tr>
				<tr>
					<td colspan="2" class="formDe">
			 			<bean:write name="violationReportsForm" property="currentMentalHealthDiagnosis"/> 
					</td>
				</tr>	
				<tr class="formDeLabel">
					<td colspan="2"><bean:message key="prompt.mentalHealth" />&nbsp;<bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td colspan="2" class="formDe">
			 			<bean:write name="violationReportsForm" property="currentMentalHealthComments"/> 
					</td>
				</tr>
			</table> 
<!-- END MENTAL HEALTH TABLE -->
		</td>
	</tr>
	<tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
	<tr>
		<td>				
<!-- BEGIN LAW VIOLATION TABLE -->
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="6">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.lawViolations" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=lawViolation&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.case#" /></td>
					<td><bean:message key="prompt.CRT" /></td>
					<td><bean:message key="prompt.offenseDate" /></td>
					<td><bean:message key="prompt.offenseLiteral" /></td>
					<td><bean:message key="prompt.offense" />&nbsp;<bean:message key="prompt.level" /></td>
					<td><bean:message key="prompt.offense" />&nbsp;<bean:message key="prompt.degree" /></td>
				</tr>
				<logic:iterate id="lvIter" name="violationReportsForm" property="currentLawViolationsList" indexId="index"> 
			    	<% displayClass = ""; %>
				 	<logic:equal name="lvIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal>  
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>><bean:write name="lvIter" property="caseId"/></td>
						<td <%= displayClass %>><bean:write name="lvIter" property="courtId"/></td>
						<td <%= displayClass %>><bean:write name="lvIter" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
						<td <%= displayClass %>><bean:write name="lvIter" property="offenseLitrel"/></td>
						<td <%= displayClass %>>
							<logic:equal name="lvIter" property="offenseLevel" value="F">
								<bean:message key="prompt.felony"/>
							</logic:equal>
							<logic:equal name="lvIter" property="offenseLevel" value="M">
								<bean:message key="prompt.misdemeanor"/>
							</logic:equal>
						</td>
						<td <%= displayClass %>><bean:write name="lvIter" property="offenseDegree"/></td>
					</tr>
				</logic:iterate> 
				<tr>
					<td class="formDeLabel" colspan="6">
						<bean:message key="prompt.lawViolationComments" />
					</td>
				</tr>
				<tr>
					<td class="formDe" colspan="6">
						<bean:write name="violationReportsForm" property="currentLawViolationsComments"/>
					</td>
				</tr>
			</table>  
<!--END LAW VIOLATION TABLE -->
		</td>
	</tr> 
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>			
<!-- BEGIN FEE HISTORY TABLE -->
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.feeHistory" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=feeHistory&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="formDeLabel">
					<td>
						<bean:message key="prompt.payType" />
					</td>
					<td><bean:message key="prompt.amtOrdered" /></td>
					<td><bean:message key="prompt.paidToDate" /></td>
					<td><bean:message key="prompt.delinquentAmt" /></td>
				</tr>
				<logic:iterate id="fhIter" name="violationReportsForm" property="currentFeeHistoryList" indexId="index"> 
			    	<% displayClass = ""; %>
					<logic:equal name="fhIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal> 
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>><bean:write name="fhIter" property="payType"/></td>
						<td <%= displayClass %>><bean:write name="fhIter" property="amountOrdered" formatKey="currency.format"/></td>
						<td <%= displayClass %>><bean:write name="fhIter" property="paidToDate" formatKey="currency.format" /></td>
						<td <%= displayClass %>><bean:write name="fhIter" property="delinquentAmount" formatKey="currency.format" /></td>
					</tr>
				</logic:iterate>	
				<tr>
					<td class="formDeLabel" colspan="4">
						<bean:message key="prompt.feeHistory" />&nbsp;<bean:message key="prompt.comments" />
					</td>
				</tr>
				<tr>
					<td class="formDe" colspan="4">
						<bean:write name="violationReportsForm" property="currentFeeHistoryComments"/>
					</td>
				</tr>
			</table> 
<!-- END FEE HISTORY TABLE -->
		</td>
	</tr> 
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN REPORTING HISTORY TABLE -->
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="3">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.reportingHistory" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=reportingHistory&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.occurrenceDate" /></td>
					<td><bean:message key="prompt.eventType" />(s)</td>
					<td><bean:message key="prompt.details" /></td>
				</tr>
				<logic:iterate id="rhIter" name="violationReportsForm" property="currentReportingHistoryList" indexId="index"> 
			    	<% displayClass = ""; %>
					<logic:equal name="rhIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal>  
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>>
							<logic:notEqual name="rhIter" property="dateTime" value="">
								<bean:write name="rhIter" property="dateTime" formatKey="date.format.mmddyyyy" />
							</logic:notEqual>
						</td>
						<td <%= displayClass %>><bean:write name="rhIter" property="eventTypes" /></td>
						<td <%= displayClass %>><bean:write name="rhIter" property="details" /></td>
					</tr>
				</logic:iterate>	
				<tr>
					<td class="formDeLabel" colspan="3">
						<bean:message key="prompt.reportingHistory" />&nbsp;<bean:message key="prompt.comments" />
					</td>
				</tr>
				<tr>
					<td class="formDe" colspan="3">
						<bean:write name="violationReportsForm" property="currentReportingHistoryComments"/>
					</td>
				</tr>
			</table> 
<!-- END REPORTING HISTORY TABLE -->					
		</td>
	</tr> 
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN EMPLOYEMENT HISTORY TABLE -->
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.employmentHistory" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=employmentHistory&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.employerName"/></td>
					<td><bean:message key="prompt.jobTitle" /></td>
					<td><bean:message key="prompt.status" /></td>
				</tr>
				<logic:iterate id="ehIter" name="violationReportsForm" property="currentEmploymentHistoryList" indexId="index"> 
			    	<% displayClass = ""; %>
					<logic:equal name="ehIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal> 
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>><bean:write name="ehIter" property="employerName"/></td>
						<td <%= displayClass %>><bean:write name="ehIter" property="jobTitle" /></td>
						<td <%= displayClass %>><bean:write name="ehIter" property="statusDesc" /></td>
					</tr>
				</logic:iterate>	
				<tr>
					<td class="formDeLabel" colspan="4">
						<bean:message key="prompt.employmentHistory" />&nbsp;<bean:message key="prompt.comments" />
					</td>
				</tr>
				<tr>
					<td class="formDe" colspan="4">
						<bean:write name="violationReportsForm" property="currentEmploymentHistoryComments"/>
					</td>
				</tr>
			</table>  
<!-- END EMPLOYEMENT HISTORY TABLE -->					
		</td>
	</tr> 
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN PREVIOUS COURT ACTIVITY TABLE -->					
		<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.previous" />&nbsp;<bean:message key="prompt.court" />&nbsp;<bean:message key="prompt.activity" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=previousCourtActivity&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>
<!-- BEGIN VIOLATION REPORTS DISPLAY -->						
				<tr class="detailHead">
					<td colspan="4"><bean:message key="title.violationReport" />s</td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.date" /></td>
					<td><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)</td>
					<td colspan="2"><bean:message key="prompt.summaryOfCourtActions" /></td>
				</tr>
				<logic:iterate id="vrIter" name="violationReportsForm" property="currentCourtActivityVRList" indexId="index"> 
			    	<% displayClass = ""; %>
			    	<% indexCounter=0; %>
			 		<logic:equal name="vrIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>							
					</logic:equal>  
					<% indexCounter++; %>
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %> valign="top"><bean:write name="vrIter" property="occurenceDate" formatKey="date.format.mmddyyyy" /></td>
						<td <%= displayClass %> valign="top"><bean:write name="vrIter" property="typeOfCourtActionComment"  filter="false"/></td>
						<td colspan="2" <%= displayClass %> valign="top">
						<logic:notEmpty name="vrIter" property="shortTruncatedSummaryOfCourtAction">
							<span id='summaryActionTruncatedVR<%=index.intValue()%>' class='visible'>
								<bean:write name="vrIter" property="shortTruncatedSummaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullVR<%=index.intValue()%>',1);showHideComments('summaryActionTruncatedVR<%=index.intValue()%>',0)">More</a></p>
							</span>
							<span id='summaryActionFullVR<%=index.intValue()%>' class='hidden'>
								<p><bean:write name="vrIter" property="summaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullVR<%=index.intValue()%>',0);showHideComments('summaryActionTruncatedVR<%=index.intValue()%>',1)">Less</a></p>
							</span>
						</logic:notEmpty>
						<logic:empty name="vrIter" property="shortTruncatedSummaryOfCourtAction">
							<bean:write name="vrIter" property="summaryOfCourtAction"  filter="false"/>
						</logic:empty>
						</td>
					</tr>
				</logic:iterate>	
<!-- END VIOLATION REPORTS DISPLAY -->
<!-- BEGIN MOTIONS DISPLAY -->	
				<tr class="detailHead">
					<td colspan="4"><bean:message key="prompt.motions" /></td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.date" /></td>
					<td><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)</td>
					<td><bean:message key="prompt.summaryOfCourtActions" /></td>
					<td><bean:message key="prompt.disposition" /></td>
				</tr>
				<logic:iterate id="motIter" name="violationReportsForm" property="currentMotionsList" indexId="index"> 
			    	<% displayClass = ""; %>
			    	<% indexCounter=0; %>
			 		<logic:equal name="motIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal>  
					<% indexCounter++; %>
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %> valign="top"><bean:write name="motIter" property="occurenceDate" formatKey="date.format.mmddyyyy" /></td>
						<td <%= displayClass %> valign="top"><bean:write name="motIter" property="typeOfCourtActionComment"/></td>
						<td <%= displayClass %> valign="top">
							<logic:notEmpty name="motIter" property="shortTruncatedSummaryOfCourtAction">
								<span id='summaryActionTruncatedMotion<%=index.intValue()%>' class='visible'>
									<bean:write name="motIter" property="shortTruncatedSummaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullMotion<%=index.intValue()%>',1);showHideComments('summaryActionTruncatedMotion<%=index.intValue()%>',0)">More</a></p>
								</span>
								<span id='summaryActionFullMotion<%=index.intValue()%>' class='hidden'>
									<bean:write name="motIter" property="summaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullMotion<%=index.intValue()%>',0);showHideComments('summaryActionTruncatedMotion<%=index.intValue()%>',1)">Less</a>
								</span>
							</logic:notEmpty>
							<logic:empty name="motIter" property="shortTruncatedSummaryOfCourtAction">
								<bean:write name="motIter" property="summaryOfCourtAction"  filter="false"/>
							</logic:empty>
						</td>
						<td <%= displayClass %> valign="top"><bean:write name="motIter" property="disposition" /></td>
					</tr>
				</logic:iterate>	
<!-- END MOTIONS DISPLAY -->
<!-- BEGIN OTHERS DISPLAY -->	
				<tr class="detailHead">
					<td colspan="4"><bean:message key="prompt.others" /></td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.date" /></td>
					<td><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)</td>
					<td colspan="2"><bean:message key="prompt.summaryOfCourtActions" /></td>
				</tr>
				<logic:iterate id="otherIter" name="violationReportsForm" property="currentOthersList" indexId="index"> 
			    	<% displayClass = ""; %>
			    	<% indexCounter=0; %>
			    	<logic:equal name="otherIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal>
					<% indexCounter++; %>
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %> valign="top"><bean:write name="otherIter" property="occurenceDate" formatKey="date.format.mmddyyyy" /></td>
						<td <%= displayClass %> valign="top"><bean:write name="otherIter" property="typeOfCourtActionComment"/></td>
						<td colspan="2" <%= displayClass %> valign="top">
							<logic:notEmpty name="otherIter" property="shortTruncatedSummaryOfCourtAction">
								<span id='summaryActionTruncatedOther<%=index.intValue()%>' class='visible'>
									<bean:write name="otherIter" property="shortTruncatedSummaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullOther<%=index.intValue()%>',1);showHideComments('summaryActionTruncatedOther<%=index.intValue()%>',0)">More</a></p>
								</span>
								<span id='summaryActionFullOther<%=index.intValue()%>' class='hidden'>
									<bean:write name="otherIter" property="summaryOfCourtAction"  filter="false"/>&nbsp;<a href="javascript:showHideComments('summaryActionFullOther<%=index.intValue()%>',0);showHideComments('summaryActionTruncatedOther<%=index.intValue()%>',1)">Less</a>
								</span>
							</logic:notEmpty>
							<logic:empty name="otherIter" property="shortTruncatedSummaryOfCourtAction">
								<bean:write name="otherIter" property="summaryOfCourtAction"  filter="false"/>
							</logic:empty>
						</td>
					</tr>
				</logic:iterate>	
<!-- END OTHERS DISPLAY -->		
	</table>  
<!-- END PREVIOUS COURT ACTIVITY TABLE -->					
	</td>
</tr> 
<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
<tr>
	<td>
<!-- BEGIN TREATMENT ISSUES TABLE -->	
		<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="5">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.treatmentIssues" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=treatmentIssues&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>
				<tr class="formDeLabel">
					<td><bean:message key="prompt.referralType"/></td>
					<td><bean:message key="prompt.serviceProviderName"/></td>
					<td><bean:message key="prompt.beginDate"/></td>
					<td><bean:message key="prompt.exit"/>&nbsp;<bean:message key="prompt.date"/></td>														
					<td><bean:message key="prompt.dischargeReason"/></td>
				</tr>
				<logic:iterate id="tiIter" name="violationReportsForm" property="currentTreatmentIssuesList" indexId="index"> 
			    	<% displayClass = ""; %>
					<logic:equal name="tiIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal> 
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>><bean:write name="tiIter" property="referralTypeCode"/></td>
						<td <%= displayClass %>><bean:write name="tiIter" property="newServiceProviderName" /></td>
						<td <%= displayClass %>><bean:write name="tiIter" property="programBeginDate" formatKey="date.format.mmddyyyy" /></td>
						<td <%= displayClass %>><bean:write name="tiIter" property="programEndDate" formatKey="date.format.mmddyyyy" /></td>							
						<td <%= displayClass %>><bean:write name="tiIter" property="dischargeReason" /></td>
					</tr>
				</logic:iterate>	
				<tr>
					<td class="formDeLabel" colspan="5">
						<bean:message key="prompt.treatmentIssues" />&nbsp;<bean:message key="prompt.comments" />
					</td>
				</tr>
				<tr>
					<td class="formDe" colspan="5">
						<bean:write name="violationReportsForm" property="currentTreatmentIssuesComments"/>
					</td>
				</tr>  
		</table> 
<!-- END TREADMENT ISSUES TABLE -->					
		</td>
	</tr> 
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN COMMUNITY SERVICE TABLE -->
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.communityService" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=communityService&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.hoursOrdered" /></td>
					<td class="formDe"><bean:write name="violationReportsForm" property="currentHoursOrdered"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.hoursCompleted" /></td>
					<td class="formDe"><bean:write name="violationReportsForm" property="currentHoursCompleted"/></td>
				</tr>
				<tr class="formDeLabel">
					<td colspan="2"><bean:message key="prompt.communityService" />&nbsp;<bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td colspan="2" class="formDe">
						<bean:write name="violationReportsForm" property="currentCommunityServiceComments"/>
					</td>
				</tr>
			</table> 
<!-- END COMMUNITY SERVICE TABLE -->
		</td>
	</tr>  
	<tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
	<tr>
		<td>
<!-- BEGIN ALCOHOL/DRUG TESTING TABLE -->	
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td><bean:message key="prompt.alcoholDrug" />&nbsp;<bean:message key="prompt.testing" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=positiveUrinalysis&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.totalSpecimensAnalyzed" />&nbsp; </td>
					<td class="formDe"><bean:write name="violationReportsForm" property="currentTotalSpecimensAnalyzed"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.date" /></td>
					<td class="formDeLabel" nowrap><bean:message key="prompt.substance" /></td>
				</tr>
				<logic:iterate id="puIter" name="violationReportsForm" property="currentPositiveUrinalysisList" indexId="index"> 
			    	<% displayClass = ""; %>
					<logic:equal name="puIter" property="manualAdded" value="true">
						<% displayClass = "class='pendingSP'"; %>	
					</logic:equal>  
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td <%= displayClass %>><bean:write name="puIter" property="dateTime" formatKey="date.format.mmddyyyy" /></td>
						<td <%= displayClass %>><bean:write name="puIter" property="details" /></td> 
					</tr>
				</logic:iterate>	
				<tr class="formDeLabel">
					<td colspan="2"><bean:message key="prompt.alcoholDrug" />&nbsp;<bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td colspan="2" class="formDe">
			 			<bean:write name="violationReportsForm" property="currentPositiveUrinalysisComments"/> 
					</td>
				</tr>
			</table> 
<!-- END ALCOHOL/DRUG TESTING TABLE -->
		</td>
	</tr>
	<tr><td><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN RECOMMENDATIONS TABLE -->	
			<table border="0" width="100%" cellpadding="2" cellspacing="1" class="borderTable">
				<tr class="detailHead">
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td ><bean:message key="prompt.suggestedCourtActions" /></td>
								<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
									<td width="1%" align="right">
										<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=recommendations&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
									</td>
								</logic:equal>	
							</tr>
						</table>
					</td>
				</tr>			
				<logic:iterate id="scaIter" name="violationReportsForm" property="currentSuggestedCourtActionsList" indexId="index"> 
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td><bean:write name="scaIter" property="description" /></td>
					</tr>
				</logic:iterate>	
				<tr class="formDeLabel">
					<td><bean:message key="prompt.statusCommentsandRecommendations" /></td>
				</tr>
				<tr>
					<td class="formDe"><bean:write name="violationReportsForm" property="currentRecommendations" filter="false"/></td>
				</tr>
			</table> 
<!-- END RECOMMENDATIONS TABLE -->
		</td>
	</tr>
<tr><td ><bean:message key="prompt.rowsManuallyAdded" /></td></tr>
	<tr>
		<td>
<!-- BEGIN COURT ACTIONS TABLE -->
			<logic:equal name="violationReportsForm" property="statusId" value="FL"> 
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="borderTable">
					<tr class="detailHead">
						<td colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr class="detailHead">
									<td ><bean:message key="prompt.courtActions" /></td>
									<logic:equal name="violationReportsForm" property="allowUpdate" value="<%=UIConstants.YES%>">								
										<td width="1%" align="right">
											<a href="/<msp:webapp/>handleViolationReportUpdates.do?submitAction=Link&type=updateCourtActions&Id=<bean:write name='violationReportsForm' property='superviseeId'/>" class="editLink"><bean:message key="prompt.update" /></a>
										</td>
									</logic:equal>	
								</tr>
							</table>
						</td>
					</tr>	
					<logic:iterate id="caIter" name="violationReportsForm" property="currentCourtActionsList" indexId="index"> 
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td><bean:write name="caIter" property="description"  /></td>
						</tr>
					</logic:iterate>
					<tr>
						<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.summary" /> of <bean:message key="prompt.courtActions" /></td>
					</tr>
					<tr>	
						<td class="formDe"><bean:write name="violationReportsForm" property="summaryOfCourtActions" filter="false"/></td>
					</tr>
				</table>
			</logic:equal>
<!-- END COURT ACTIONS TABLE -->		
		</td>
	</tr>  
	<tr><td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td></tr>
</table>	
