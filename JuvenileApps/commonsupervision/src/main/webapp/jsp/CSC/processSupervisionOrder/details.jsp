<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/08/2005	 Hien Rodriguez - Create JSP -->
<!-- 09/07/2006  Hien Rodriguez - Defect#34901 remove DefendantSignatureAcquired button from the View Order Versions page -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 04/15/2008	 Richard Young  - Pointed print references to the web focus action. -->
<!-- 07/28/2008	 Richard Young  - ER#53069 changed wording on Casenote prompt. -->
<!-- 08/08/2008  D Gibler		- ER#52277 Added logic tags to display title for migrated order differently -->
<!-- 10/01/2008  Debbie Williamson  - Defect#54283 Commented out Print Spanish Version button -->
<!-- 06/19/2009  RYoung - Activity #60241 Do not allow to print Migrated order -->
<!-- 06/19/2009  C Shimek       - #60481 commented out "Process Order Documents" buttons  -->
<!-- 10/08/2009  C Shimek       - #61938 revised checkTitle() to check both order titles -->
<!-- 10/15/2009  C Shimek       - #61938 revised checkTitle() to use hidden field with value of isMigratedOrder from record -->
<!-- 10/26/2009  C Shimek       - #60771 revised offense code to only display offense description -->
<!-- 11/03/2009  C Shimek       - #62542 revised to display print button when action equals "confirmPrepareToFile" -->
<!-- 11/11/2009  C Shimek       - #62440 Revised Historical to Pretrial Intervention -->
<!-- 11/19/2009  C Shimek       - #60771 revised offense code to printed offense description -->
<!-- 01/12/2010  C Shimek       - #63441 revised to display Defendant Signature data for Prepare To File confirmation -->
<!-- 03/18/2010  C Shimek       - #62933 added special court display field -->
<!-- 03/23/2010  C Shimek       - #61848 added expected new fields - Printed Offense, Printed Name, Defendant Signature etc. -->
<!-- 05/11/2010  C Shimek       - #65394 added hidden fields to hold selected order title and validate these fields on print to not allow value = MIGRTED or blank -->
<!-- 10/19/2010  C Shimek       - #67921 removed "intervention" from pretrial begin and end date labels -->
<!-- 11/05/2010  D Gibler       - #67855 PASO-Additional Signature for CLO/CSO and Judge -->
<!-- 11/11/2010  C Shimek       - #68208 corrected typo, selectedOrderVersion.judgeSignedDate to selectedOrderVersion.signedByJudgeDate -->
<!-- 05/06/2011  C Shimek       - #69623 commented out sortResults as this default sort and user resort disrupted conditions status color code displays -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@page import="naming.UIConstants"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - processSupervisionOrder/details.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
// Kiran Krishnamurthy
// For PASO Printing functionality.
function changeFormSettings(theForm, theTargetString, button, theActionString)
{
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);
	changeFormTarget(theForm,theTargetString) ;
	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new')
	disableSubmit(button, theForm);
}

// End of PASO Printing functionality.

// Added for Defect 34867
function initButtons(agencyId, versionTypeId, orderStatusId, createOrUpdate)
{
	show("nodefSigAquired", 0);
	show("defSigAquired", 0);
	if (createOrUpdate == 'create')
	{
		if ((agencyId == 'CSC') &&
		((orderStatusId == 'D') ||
		(versionTypeId == 'O' && orderStatusId == 'P')))
		{
			show("defSigAquired", 1);
		}
		else
			{
				show("nodefSigAquired", 1);
			}
		}
		else
			{
				if ((agencyId == 'CSC') &&
				(versionTypeId == 'M' || versionTypeId == 'A') &&
				(orderStatusId == 'D'))
				{
					show("defSigAquired", 1);
				}
				else
					{
						show("nodefSigAquired", 1);
					}
				}
}

function buildPrintButton(){
	var thePrintURL = "submitSupervisionOrderWebFocusPrintRequest.do?submitAction=Print&compositeKey=<bean:write name="supervisionOrderForm" property="orderId" />";
	openWindow(thePrintURL);
}

function checkTitle(){
// noPrintAllowed uses isMigratedOrder value on Form	
	if(document.forms[0].noPrintAllowed.value == 'true'){
		alert( "You cannot print a migrated order." );
		return false;
	}
	var otNames = document.getElementsByName("orderTitleName");
	if (otNames.length > 0){
		for (x=0; x < otNames.length; x++){
			if (otNames[x].value == "MIGRAGTED"){
				alert( "You cannot print a migrated order." );
				return false;
			}	
			if (otNames[x].value == ""){
				alert( "No title found on selected order to print." );
				return false;
			}
		}
	}
	return true;
}

</script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionOrderCreateUpdate" target="content" >
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> 
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"> 
							<tiles:put name="tab" value="processOrderTab" />
						</tiles:insert> <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img 	src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="98%">
						<tr>
							<td align="center" class="header">
								<logic:equal name="supervisionOrderForm" property="action" value="view">
									<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|6">
								    <logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									   <bean:message key="prompt.pretrialInterventionOrder" />
								    </logic:equal>
								    <logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									   <bean:message key="title.processSupervisionOrder" />
								    </logic:notEqual>	&nbsp;-&nbsp;<bean:message key="prompt.view" />&nbsp;<bean:message key="prompt.version" />
										<tr>
											<td>
												<ul>
													<li>Click the appropriate button below.</li>
												</ul>
											</td>
										</tr>
								</logic:equal> 
								<logic:equal name="supervisionOrderForm" property="action" value="confirmCreate">
									<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|15">
									<bean:message key="prompt.create" />
									<bean:message key="title.supervisionOrder" /> - <bean:message key="prompt.confirmation" />
									<tr>
										<td class="confirm">
											<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">Pretrial</logic:equal> 
											<logic:notEqual	name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">Supervision</logic:notEqual> Order successfully created.
										</td>
									</tr>
								</logic:equal> 
							<logic:equal name="supervisionOrderForm" property="action" value="activate">
								<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|26">
								<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="prompt.pretrialInterventionOrder" />
								</logic:equal>
								<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="title.processSupervisionOrder" />
								</logic:notEqual>-&nbsp;<bean:message key="prompt.activate" />&nbsp;<bean:message key="prompt.order" /> - <bean:message key="prompt.confirmation" />
								<tr>
									<td class="confirm">
										<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">Pretrial</logic:equal>
									    <logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">Supervision</logic:notEqual> Order successfully activated.
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="supervisionOrderForm" property="action" value="delete">
								<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|27">
								<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.delete" />&nbsp;<bean:message key="prompt.order" /> - <bean:message key="prompt.confirmation" />
								<tr>
									<td class="confirm">Supervision Order successfully deleted.</td>
								</tr>
							</logic:equal>
							<logic:equal name="supervisionOrderForm" property="action" value="confirmPrepareToFile">
								<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|25">
								<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="prompt.pretrialInterventionOrder" />
								</logic:equal>
								<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="title.processSupervisionOrder" />
								</logic:notEqual>-&nbsp;<bean:message key="prompt.prepareToFile" />&nbsp;<bean:message key="prompt.confirmation" />
								<tr>
									<td class="confirm">
										<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true"> Pretrial Intervention </logic:equal>
									    <logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true"> Supervision </logic:notEqual> Order prepared for filing.
									</td>
								</tr>
							</logic:equal>						 
							<logic:equal name="supervisionOrderForm" property="action" value="printSignature">								
								<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|39">
								<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="title.processSupervisionOrder" /> &nbsp;-&nbsp;<bean:message key="prompt.printSignature" />&nbsp;<bean:message key="prompt.confirmation" />
									<tr>
									    <td class="confirm">Supervision Order saved.</td>
								    </tr>
								</logic:notEqual>
								<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="prompt.pretrialInterventionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.printSignature" />&nbsp;<bean:message key="prompt.confirmation" />
									<tr>
									    <td class="confirm">Pretrial Order saved.</td>
								    </tr>
								</logic:equal>								
							</logic:equal> 
							<logic:equal name="supervisionOrderForm" property="action" 	value="confirmUpdate">
								<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|22">
								<bean:message key="prompt.update" />
								<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<bean:message key="prompt.pretrialInterventionOrder" />
								</logic:equal>
								<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">
										<bean:message key="title.migrated" />&nbsp;<bean:message key="title.supervisionOrder" />
									</logic:equal>
									<logic:notEqual name="supervisionOrderForm" property="isMigratedOrder" value="true">
										<bean:message key="title.supervisionOrder" />
									</logic:notEqual>
								</logic:notEqual>	 - <bean:message key="prompt.confirmation" />
								<tr>
									<td class="confirm">
										<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">Pretrial </logic:equal>
									    <logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
									    	<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">Migrated </logic:equal>
										   	<logic:notEqual name="supervisionOrderForm" property="isMigratedOrder" value="true">Supervision</logic:notEqual>
										</logic:notEqual> Order successfully updated.
									</td>
								</tr>
							</logic:equal></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> 
					<!-- BEGIN ERROR TABLE -->
					<table width="98%" align="center">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERROR TABLE -->
					<!-- BEGIN DETAIL HEADER TABLE --> 
					 	<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
					<!-- END DETAIL HEADER TABLE -->
					<!-- BEGIN OTHER VERSION TABLE --> 
						<logic:equal name="supervisionOrderForm" property="action" value="view">
						<logic:notEmpty name="supervisionOrderForm" property="orderVersionList">
							<br>
							<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.otherVersions" /></td>
								</tr>
								<tr>
									<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
											<td><bean:message key="prompt.orderActivated" /><%-- <jims2:sortResults levelDeep="3" beanName="supervisionOrderForm" results="orderVersionList" primaryPropSort="activationDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" /> --%></td>
											<td><bean:message key="prompt.version" /><%-- <jims2:sortResults levelDeep="3" beanName="supervisionOrderForm" results="orderVersionList" primaryPropSort="orderVersion" primarySortType="STRING" sortId="2" /> --%></td>
											<td><bean:message key="prompt.orderTitle" /><%-- <jims2:sortResults levelDeep="3" beanName="supervisionOrderSearchForm" results="orderVersionList" primaryPropSort="orderTitle" primarySortType="STRING" sortId="3" /> --%></td>
										</tr>
										<logic:iterate id="orderVersionListIndex" name="supervisionOrderForm" property="orderVersionList" indexId="index">
											<tr	class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td><bean:write name="orderVersionListIndex" property="activationDate" formatKey="datetime.format.mmddyyyyHHmmAMPM" /></td>
												<td title="View this version's Order">
													<a href="/<msp:webapp/>handleSupervisionOrderSelection.do?submitAction=<bean:message key="button.compareOrderVersions"/>&orderId=<bean:write name="orderVersionListIndex" property="orderId"/>"><bean:write
													name="orderVersionListIndex" property="orderVersion" /></a>
												</td>
												<td><bean:write name="orderVersionListIndex" property="orderTitle" /></td>
											</tr>
										</logic:iterate>
									</table>
									</td>
								</tr>
							</table>
						</logic:notEmpty>
					</logic:equal>
					<!-- END OTHER VERSION TABLE --> <br>
					<!-- BEGIN ORDER PRESENTATION TABLE -->
					<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr class="detailHead">
							<td class="detailHead"><bean:message key="prompt.orderPresentation" /></td>
							<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
						</tr>
						<tr>
							<td colspan="2">
							<logic:equal name="supervisionOrderForm" property="action" value="view">
								<!-- Begin ORDER PRESENTATION SECTION for single version -->
								<logic:equal name="supervisionOrderForm" property="previousOrderVersion" value="">
									<table width="100%" cellpadding="4" cellspacing="1" border="0">
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.versionType" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.versionType" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.specialCourt" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.specialCourtDesc" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" />
												<input type="hidden" name="orderTitleName" value="<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" />" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedOffenseDescription" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.printedOffenseDesc" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedName" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.printedName" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.dispositionType" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.dispositionType" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.fineAmount" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.fineAmountTotal" /></td>
										</tr>
										<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.confinementLength" /></td>
												<td class="formDe">
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears" /> Years 
													</logic:notEmpty>
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths" /> Months 
													</logic:notEmpty>
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays" /> Days 
													</logic:notEmpty>
												</td>
											</tr>
										</logic:equal>
										<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.supervisionLength" /></td>
												<td class="formDe">
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears" /> Years 
													</logic:notEmpty>
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths" /> Months 
													</logic:notEmpty>
													<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays">
														<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays" /> Days 
													</logic:notEmpty>
												</td>
											</tr>
										</logic:notEqual>
										<tr>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionBeginDate" />
												</logic:notEqual> 
												<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" 	value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.beginDate" />
												</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionBeginDateAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionEndDate" />
											    </logic:notEqual> 
												<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.endDate" />
												</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionEndDateAsString" /></td>
										</tr>
										<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.plea" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="plea" /></td>
											</tr>
										</logic:equal>

										<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.modificationReason">
											<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" value="">
												<tr>
													<td class="formDeLabel" colspan="2"><bean:message key="prompt.modificationReason" /></td>
												</tr>

												<tr>
													<td class="formDe" colspan="2"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" /></td>
												</tr>
											</logic:notEqual>
										</logic:notEmpty>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.originalJudge" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.origJudgeName" />
											</td>
										</tr> 
										<tr>
											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderSignedDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.presentedBy" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderPresentorName" />
											</td>
										</tr> 
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByJudgeDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.whoSignedOrder?" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.whoSignedOrder" />
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByName" />
											</td>
										</tr>  
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.defendantSignature" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.defendantSignature" />
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByDefendantDate" formatKey="date.format.mmddyyyy"/>
											</td>
										</tr>  
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.comments" /> for Printed Order</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.comments" /></td>
										</tr> 
									</table>
								</logic:equal>
								<!-- End ORDER PRESENTATION SECTION for single version -->
								<!-- Begin ORDER PRESENTATION SECTION for multiple versions -->
								<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion">
									<table width="100%" border="0" cellspacing="1" cellpadding="4">
										<tr>
											<td colspan="2" class="formDeLabel" width="50%">
												<bean:message key="prompt.selectedVersion" />(<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderVersion" />)
											</td>
											<td colspan="2" class="formDeLabel" width="50%">
												<bean:message key="prompt.previousVersion" />(<bean:write name="supervisionOrderForm" property="previousOrderVersion.orderVersion" />)
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.versionType" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.versionType" /></td>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.versionType" /></td>
											<td class="formDe"><bean:write 	name="supervisionOrderForm" property="previousOrderVersion.versionType" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.specialCourt" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.specialCourtDesc" /></td>
											<td class="formDeLabel"><bean:message key="prompt.specialCourt" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.specialCourtDesc" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" />
												<input type="hidden" name="orderTitleName" value="<bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderTitle" />" />
											</td>
											<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.orderTitle" /></td>
										</tr>
										
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedOffenseDescription" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.printedOffenseDesc" /></td>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedOffenseDescription" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.printedOffenseDesc" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedName" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.printedName" /></td>
											<td class="formDeLabel" nowrap><bean:message key="prompt.printedName" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.printedName" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.dispositionType" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.dispositionType" /></td>
											<td class="formDeLabel"><bean:message key="prompt.dispositionType" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.dispositionType" /></td>
										</tr>

										<tr>
											<td class="formDeLabel"><bean:message key="prompt.fineAmount" /></td>
											<td class="formDe"><bean:write 	name="supervisionOrderForm" property="selectedOrderVersion.fineAmountTotal" /></td>
											<td class="formDeLabel"><bean:message key="prompt.fineAmount" /></td>
											<td class="formDe"><bean:write 	name="supervisionOrderForm" property="previousOrderVersion.fineAmountTotal" /></td>
										</tr>
										<jims2:if name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>" op="equal">
											<jims2:or name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>" op="equal">
												<tr>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" 	value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class="formDeLabel"><bean:message key="prompt.confinementLength" /></td>
														<td class="formDe">
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthYears" /> Years </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthMonths" /> Months </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.confinementLengthDays" /> Days </logic:notEmpty>
														</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class="formDeLabel"><bean:message key="prompt.confinementLength" />&nbsp;</td>
														<td class="formDe">&nbsp;</td>
													</logic:notEqual>

													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class="formDeLabel"><bean:message key="prompt.confinementLength" /></td>
														<td class="formDe">
															<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.confinementLengthYears">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthYears" /> Years </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.confinementLengthMonths">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthMonths" /> Months </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm"	property="previousOrderVersion.confinementLengthDays">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.confinementLengthDays" /> Days </logic:notEmpty>
														</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>">
														<td class="formDeLabel"><bean:message key="prompt.confinementLength" />&nbsp;</td>
														<td class="formDe">&nbsp;</td>
													</logic:notEqual>
												</tr>
											</jims2:or>
										</jims2:if>
										<jims2:if name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>" op="notEqual">
											<jims2:or name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>" op="notEqual">
												<tr>
													<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
														<td class="formDeLabel"><bean:message key="prompt.supervisionLength" /></td>
														<td class="formDe">
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthYears" /> Years </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthMonths" /> Months </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays">
																<bean:write name="supervisionOrderForm" property="selectedOrderVersion.supervisionLengthDays" /> Days </logic:notEmpty>
														</td>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
														<td class="formDeLabel"><bean:message key="prompt.supervisionLength" />&nbsp;</td>
														<td class="formDe">&nbsp;</td>
													</logic:equal>
													<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
														<td class="formDeLabel"><bean:message key="prompt.supervisionLength" /></td>
														<td class="formDe">
															<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthYears">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthYears" /> Years </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthMonths">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthMonths" /> Months </logic:notEmpty>
															<logic:notEmpty name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthDays">
																<bean:write name="supervisionOrderForm" property="previousOrderVersion.supervisionLengthDays" /> Days </logic:notEmpty>
														</td>
													</logic:notEqual>
													<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" 	value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
														<td class="formDeLabel"><bean:message key="prompt.supervisionLength" />&nbsp;</td>
														<td class="formDe">&nbsp;</td>
													</logic:equal>
												</tr>
											</jims2:or>
										</jims2:if>
										<tr>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionBeginDate" />
													</logic:notEqual> <logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
														<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.beginDate" />
													</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionBeginDateAsString" /></td>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionBeginDate" />
												</logic:notEqual> <logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.beginDate" />
												</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.caseSupervisionBeginDateAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionEndDate" />
												</logic:notEqual>
												 <logic:equal name="supervisionOrderForm" property="selectedOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.endDate" />
												</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.caseSupervisionEndDateAsString" /></td>
											<td class="formDeLabel" nowrap="nowrap">
												<logic:notEqual name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.supervisionEndDate" />
												</logic:notEqual>
												<logic:equal name="supervisionOrderForm" property="previousOrderVersion.dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													<bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.endDate" />
												</logic:equal>
											</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.caseSupervisionEndDateAsString" /></td>
										</tr>
										<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.plea" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.plea" /></td>
												<td class="formDeLabel"><bean:message key="prompt.plea" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.plea" /></td>
											</tr>
										</logic:equal>
										<tr>
											<td class="formDeLabel" colspan="2"><bean:message key="prompt.modificationReason" /></td>
											<td class="formDeLabel" colspan="2"><bean:message key="prompt.modificationReason" /></td>
										</tr>
										<tr>
											<td class="formDe" colspan="2"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.modificationReason" /></td>
											<td class="formDe" colspan="2"><bean:write name="supervisionOrderForm" property="previousOrderVersion.modificationReason" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.originalJudge" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.origJudgeName" /></td>
											<td class="formDeLabel"><bean:message key="prompt.originalJudge" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.origJudgeName" /></td>
										</tr> 
										<tr>
											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderSignedDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel"><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.orderSignedDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.presentedBy" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.orderPresentorName" /></td>
											<td class="formDeLabel"><bean:message key="prompt.presentedBy" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.orderPresentorName" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByJudgeDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel"><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.signedByJudgeDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										 
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.whoSignedOrder?" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.whoSignedOrder" />
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByName" />
											</td>
											<td class="formDeLabel"><bean:message key="prompt.whoSignedOrder?" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.whoSignedOrder" />
												<bean:write name="supervisionOrderForm" property="previousOrderVersion.signedByName" />
											</td>
										</tr>  
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.defendantSignature" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.defendantSignature" />
												<bean:write name="supervisionOrderForm" property="selectedOrderVersion.signedByDefendantDate" formatKey="date.format.mmddyyyy"/>
											</td>
											<td class="formDeLabel"><bean:message key="prompt.defendantSignature" /></td>
											<td class="formDe">
												<bean:write name="supervisionOrderForm" property="previousOrderVersion.defendantSignature" />
												<bean:write name="supervisionOrderForm" property="previousOrderVersion.signedByDefendantDate" formatKey="date.format.mmddyyyy"/>
											</td>
										</tr>  
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.comments" /> for Printed Order</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="selectedOrderVersion.comments" /></td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.comments" /> for Printed Order</td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="previousOrderVersion.comments" /></td>
										</tr>
									</table>
								</logic:notEmpty>
								<!-- End ORDER PRESENTATION SECTION for multiple versions -->
							</logic:equal> <logic:notEqual name="supervisionOrderForm" property="action" value="view">
								<!-- Begin ORDER PRESENTATION SECTION for single version -->
								<tiles:insert page="orderPresentationTile.jsp" flush="true"></tiles:insert>
							</logic:notEqual></td>
						</tr>
					</table>
					<!-- END ORDER PRESENTATION TABLE --> <br>
					<!-- BEGIN CONDITIONS SECTION -->
					<table width="98%" border="0" cellspacing="0" cellpadding="2"
						class="borderTableBlue">
						<tr class="detailHead">
							<td>
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td class="detailHead"><bean:message key="prompt.conditions" /></td>
								  		<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td>
							<table border="0" align="center" width="100%">
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="4" cellspacing="1"
										class="borderTable">
										<tr>
											<td class="formDeLabel" width="1%"></td>
											<%-- slin: this column is only necessary in the view (for icons) --%>											
											<logic:equal name="supervisionOrderForm" property="action" value="view">
												<td class="formDeLabel" width="1%"></td>
											</logic:equal>
											<td class="formDeLabel"><bean:message key="prompt.condition" /></td>
										</tr>
										<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList" indexId="index2">
											<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="">
												<tr class="<%out.print((index2.intValue() % 2 == 1) ? "normalRow" : "alternateRow"); %>">
													<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
														<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
															<td class="impactedOrderBold" title="This is a Like Condition and Impacts other order(s)" />
																<bean:write name="conditionSelectedListIndex" property="sequenceNum" />
															</td>
														</logic:equal>
														<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
															<td class="boldText"><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
														</logic:equal>
													<%-- slin: this column is only necessary in the view (for icons) --%>
														<logic:equal name="supervisionOrderForm" property="action" value="view">
															<td>&nbsp;</td>
														</logic:equal>
														<td>
															<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
																<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false" />
															</logic:notEmpty>
															<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
																<bean:write name="conditionSelectedListIndex" property="description" filter="false" />
															</logic:empty>
														</td>
												</tr>
											</logic:equal>
											<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="updated">
												<tr class="versionUpdatedCondition" title="Updated from prior version">
													<td class="hidden"><bean:write 	name="conditionSelectedListIndex" property="conditionId" /></td>
													<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
														<td class="impactedOrderBold" title="This is a Like Condition and Impacts other order(s)" />
															<bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
													</logic:equal>
													<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
														<td class="boldText"><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
													</logic:equal>
													<td width="1%"><img src="/<msp:webapp/>images/blue_flag.gif"></td>
													<td>
														<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false" />
														</logic:notEmpty>
														<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="description" filter="false" />
														</logic:empty>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="added">
												<tr class="versionAddedCondition" title="Condition Added">
													<td class="hidden"><bean:write 	name="conditionSelectedListIndex" property="conditionId" /></td>
													<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
														<td class="impactedOrderBold" title="This is a Like Condition and Impacts other order(s)" />
															<bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
													</logic:equal>
													<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
														<td class="boldText"><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
													</logic:equal>
													<td width="1%" align="center"><span style="font-size: medium; font-weight: bold">+</span></td>
													<td>
														<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false" />
														</logic:notEmpty>
														<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="description" filter="false" />
														</logic:empty>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="removed">
												<tr class="versionRemovedCondition" title="Condition Removed">
													<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
													<td></td>
													<td width="1%" align="center"><span style="font-size: small; font-weight: bold">X</span></td>
													<td>
														<logic:notEmpty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="resolvedDescription" filter="false" />
														</logic:notEmpty> 
														<logic:empty name="conditionSelectedListIndex" property="resolvedDescription">
															<bean:write name="conditionSelectedListIndex" property="description" filter="false" />
														</logic:empty>
													</td>
												</tr>
											</logic:equal>
										</logic:iterate>
									</table>
									<logic:equal name="supervisionOrderForm" property="action" value="view">
										<table cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td><span style="font-size: small; font-weight: bold">X</span></td>
												<td class="legendSmallText"><span class="versionRemovedCondition">Red Conditions</span>
													signify that the condition was <b>removed</b> compared to prior version</td>
											</tr>
											<tr>
												<td><img src="/<msp:webapp/>images/blue_flag.gif"></td>
												<td class="legendSmallText"><span class="versionUpdatedCondition">Blue Conditions</span>
													signify that the condition has been <b>updated</b> compared to prior version</td>
											</tr>
											<tr>
												<td><span style="font-size: medium; font-weight: bold">+</span></td>
												<td class="legendSmallText"><span class="versionAddedCondition">Green Conditions</span>
												signify that the condition has been <b>added</b> compared to prior version</td>
											</tr>
										</table>
									</logic:equal></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<!-- END CONDITIONS SECTION --> <br>
					<!-- BEGIN SUMMARY OF CHANGES SECTION --> 
					<logic:equal name="supervisionOrderForm" property="action" 	value="confirmUpdate">
						<logic:notEmpty name="supervisionOrderForm" property="casenotes">
							<table width="98%" border="0" cellspacing="0" cellpadding="0" class="borderTableBlue">
								<tr class="detailHead">
									<td>
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.modificationReason" /> for Casenote</td>
											<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="0">
											<tr>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="casenotes" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEmpty>
					</logic:equal> 
					<!-- END SUMMARY OF CHANGES SECTION -->
				 	<!-- BEGIN ORDER PRINTS DETAIL TABLE -->
					<elogic:if name="supervisionOrderForm" property="action" op="equal" value="confirmPrepareToFile">
						<elogic:or name="supervisionOrderForm" property="action" op="equal" value="printSignature" />
						<elogic:then>
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.prepareToFile" /> <bean:message key="prompt.details" /></td>
									<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" cellpadding="4" cellspacing="1" border="0">
										<tr>
											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="signedDateAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.presentedBy" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="presentedBy" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="judgeSignedDateAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.whoSignedOrder?" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="whoSignedOrder" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<br>
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.defendantSignature" /></td>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" cellpadding="4" cellspacing="1" border="0">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="defendantSignedDateAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.defendantSignature" /></td>
											<td class="formDe"><bean:write name="supervisionOrderForm" property="defendantSignature" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<br>
						</elogic:then>
					</elogic:if> 
					 <!-- END ORDER PRINTS DETAIL TABLE -->
					 <input type="hidden" name="noPrintAllowed" value=<bean:write name="supervisionOrderForm" property="isMigratedOrder" /> >
					 <!-- BEGIN BUTTON TABLE -->
					<table align="center" width="98%">
						<logic:equal name="supervisionOrderForm" property="action" value="view">
							<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return checkTitle() && changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
											<bean:message key="button.print"></bean:message>
										</html:submit>&nbsp; 
						<%-- 			<html:button property="submitAction" onclick="alert('This will navigate to the Process Other Doccuments Use Case - not available yet')">
											<bean:message key="button.processOtherDocuments"></bean:message>
										</html:button>&nbsp; --%>
									</td>
									<%--<logic:equal name="supervisionOrderForm" property="agencyId" value="CSC">																																																																																																																																																						</logic:equal>--%>
								</tr>
							</logic:notEqual>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit>&nbsp; <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit>&nbsp; <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.cancel"></bean:message>
								</html:submit></td>
							</tr>
						</logic:equal>
						<logic:equal name="supervisionOrderForm" property="action"
							value="confirmCreate">
							<tr align="center" id="nodefSigAquired">
								<td>&nbsp; <jims2:isAllowed requiredFeatures="CS-PASO-PREPTOFILE">
											<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
											<bean:message key="button.prepareToFile"></bean:message>
											</html:submit>&nbsp;
									</jims2:isAllowed>
					<%-- 			<html:button property="submitAction"
									onclick="alert('This will navigate to the Process Other Doccuments Use Case - not available yet')">
									<bean:message key="button.processOtherDocuments"></bean:message>
								</html:button> --%>
								</td>
							</tr>
							<tr align="center" id="defSigAquired">
								<td>&nbsp; 
									<jims2:isAllowed requiredFeatures="CS-PASO-PREPTOFILE">
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
											<bean:message key="button.prepareToFile"></bean:message>
										</html:submit>&nbsp;
									</jims2:isAllowed>
						<%-- 		<html:button property="submitAction" onclick="alert('This will navigate to the Process Other Doccuments Use Case - not available yet')">
										<bean:message key="button.processOtherDocuments"></bean:message>
									</html:button>  --%>
								</td>
							</tr>
							<script>
								initButtons('<bean:write name="supervisionOrderForm" property="agencyId"/>', '<bean:write name="supervisionOrderForm" property="versionTypeId"/>', '<bean:write name="supervisionOrderForm" property="orderStatusId"/>', 'create'); 
							</script>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
									<bean:message key="button.printDraft" />
								</html:submit>&nbsp;</td>
							</tr>
							<tr>
								<td align="center">
								<%-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
						 		</html:submit>&nbsp;--%> <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit></td>
							</tr>
						</logic:equal>
						<logic:equal name="supervisionOrderForm" property="action" value="activate">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
										<bean:message key="button.print"></bean:message>
									</html:submit>&nbsp; 
								</td>
							</tr>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit> <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit>&nbsp;</td>
							</tr>
						</logic:equal>
						<logic:equal name="supervisionOrderForm" property="action" value="delete">
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit> <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit>&nbsp;</td>
							</tr>
						</logic:equal>
						<logic:equal name="supervisionOrderForm" property="action" value="confirmPrepareToFile">
							<tr>
								<td align="center">
								<jims2:isAllowed requiredFeatures="CS-PASO-ACTIVATE">
									<html:submit property="submitAction" onclick="return changeFormSettingsWithConfirm(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do','Are you sure you want to activate this order?');">
										<bean:message key="button.activateOrder"></bean:message>
									</html:submit>&nbsp;
								</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures="CS-PASO-UPDATE">
									<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
										<bean:message key="button.updateOrder"></bean:message>
									</html:submit>&nbsp;
								</jims2:isAllowed>
								</td>
							</tr>
							<!-- per defect #62542, print button should always display -->
					<%--		<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true"> --%>
								<tr>
									<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
										<bean:message key="button.print"></bean:message>
									</html:submit>&nbsp;</td>
								</tr>
					<%--		</logic:notEqual>  --%>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit>&nbsp; <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit></td>
							</tr>
						</logic:equal>
						<input type="hidden" name="printAction" value='<bean:write name="supervisionOrderForm" property="action"/>' />
						<logic:equal name="supervisionOrderForm" property="action" 	value="confirmUpdate">
							<tr>
								<td align="center">
									<jims2:isAllowed requiredFeatures="CS-PASO-PREPTOFILE">
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
											<bean:message key="button.prepareToFile"></bean:message>
										</html:submit>&nbsp;
									</jims2:isAllowed>
						<%-- 		<html:button property="submitAction" onclick="alert('This will navigate to the Process Other Doccuments Use Case - not available yet')">
										<bean:message key="button.processOtherDocuments"></bean:message>
									</html:button> --%>
								</td>
							</tr>
							<tr align="center" id="nodefSigAquired">
								<td><!-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.print"></bean:message></html:submit>&nbsp;-->
								<html:submit property="submitAction" onclick="return checkTitle() && changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
									<bean:message key="button.print"></bean:message>
								</html:submit>&nbsp; <%-- <html:button property="submitAction" onclick="buildPrintButton()"><bean:message key="button.printSpanishVersion"/></html:button>&nbsp;--%>
								</td>
							</tr>
							<tr align="center" id="defSigAquired">
								<td><!-- <html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');"><bean:message key="button.print"></bean:message></html:submit>&nbsp;-->
								<html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
									<bean:message key="button.print"></bean:message>
								</html:submit>&nbsp; <%-- <html:button property="submitAction" onclick="buildPrintButton()"><bean:message key="button.printSpanishVersion"/></html:button>&nbsp;--%>
								</td>
							</tr>
							<script>
								initButtons('<bean:write name="supervisionOrderForm" property="agencyId"/>', '<bean:write name="supervisionOrderForm" property="versionTypeId"/>', '<bean:write name="supervisionOrderForm" property="orderStatusId"/>', 'update');
							</script>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit>&nbsp; <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit></td>
							</tr>
						</logic:equal>
						<logic:equal name="supervisionOrderForm" property="action" value="printSignature">
							<tr>
								<td align="center">
								<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.back" />
								</html:submit>&nbsp;								
								<html:submit property="submitAction" onclick="changeFormSettings(this.form, 'new', this, 'submitSupervisionOrderPrintRequest.do');">
									<bean:message key="button.printSignature"></bean:message>
								</html:submit>
								</td>
							</tr>
							<tr>
								<td align="center"><html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToSearch"></bean:message>
								</html:submit>&nbsp; <html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'submitSupervisionOrderCreateUpdate.do');">
									<bean:message key="button.backToCaseOrderSearchResults"></bean:message>
								</html:submit></td>
							</tr>
						</logic:equal>
					</table>
					<!-- END BUTTON TABLE -->
				</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>