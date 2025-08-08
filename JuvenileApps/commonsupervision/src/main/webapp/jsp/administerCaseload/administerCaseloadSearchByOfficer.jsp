<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--02/28/2008	LDeen	Defect #49819 integrate help    --%>
<%--09/10/2008	CShimek	Defect #54107 set cursor on SPN select and defect #54135 revise default sort field on search results  --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<head>
<!-- This is the SPN Search for Officer -->
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
<title>CommonSupervision/administerCaseload/administerCaseloadSearchByOfficer.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
	function renderSearch() {
		var defendant = document.getElementById("selectedDefendantId");
		defendant.value = "";
		selectedDefendant = "";
		
		var theElementId = "searchCriteria";
		var theSelect = document.getElementById(theElementId);
		var theSelectValue = theSelect.options[theSelect.selectedIndex].value;	
		switch (theSelectValue) {
			case "SPN":
				show("spSearchSPN", 1);
				show("caseloadSpan", 0);
				document.forms[0].defendantId.focus();
				break;
			case "NAME":
				show("spSearchSPN", 0);
				show("caseloadSpan", 0);
				alert("Feature not available currently");
				break;
			case "CASELOAD":
				show("spSearchSPN", 0);
				show("caseloadSpan", 1);
				break;
		} 
	}
	
	function getSupervisorsForDivision(theForm) {
		theForm.action = theForm.action + "?submitAction=Get%2BSupervisors";
		theForm.submit();
	}
	
	selectedDefendant = "";

	function selectSupervisee(theSelectedRadio) {
		selectedDefendant = theSelectedRadio.value;
		var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
		var length = casesSelected.length;
		for (var i = 0; i < length; i++) {
			if (casesSelected[i].checked) {
				casesSelected[i].checked = false;
			}
		}
		var caseloadSubmitButton = document.getElementById("caseloadSubmitBtnId");
		caseloadSubmitButton.disabled = true;		
	}
	
	function selectSuperviseeCase(theSelectedCase, defendantId) {
		if (defendantId != selectedDefendant) {
			theSelectedCase.checked = false;
		} else if (defendantId == selectedDefendant) {
			var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
			var length = casesSelected.length;
			var noOfCasesSelected = 0;
			for (var i = 0; i < length; i++) {
				if (casesSelected[i].checked) {
					noOfCasesSelected++;
				}
			}
			var caseloadSubmitButton = document.getElementById("caseloadSubmitBtnId");
			if (noOfCasesSelected > 0) {
				caseloadSubmitButton.disabled = false;		 
			} else {
				caseloadSubmitButton.disabled = true;		
			}
		}
	}
	
	function caseloadSearchSubmit(theForm) {
		var defendant = theForm["defendantId"];
		defendant.value = selectedDefendant;
		return true;
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("defendantId");
		if (rbs.length == 1){
			rbs[0].checked = true;
		}	
	}
</script>
</head>

<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)"
	onload="renderSearch(); checkForSingleResult();">
<html:form action="/superviseeSearchSetupByOfficerAction"
	target="content">
	<input type="hidden" name="helpFile"
		value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|1">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header"><bean:message
								key="title.CSCD" />&nbsp;-&nbsp; <bean:message
								key="prompt.caseload" /> Search - Officer</td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
					<table width="98%" align="center">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Select Search By to change the search type.</li>
								<li>Enter the required field(s) and click Submit to see
								results.</li>
							</ul>
							</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE --> <!-- begin search TABLE -->
					<table width="98%" cellpadding="2" cellspacing="1"
						class="borderTableBlue">
						<tr>
							<td class="formDeLabel" width="1%" nowrap="nowrap">Search By</td>
							<td class="formDe"><html:select name="caseAssignmentForm"
								property="defendantSearchCriteria" styleId="searchCriteria"
								onchange="renderSearch()">
								<html:option value="CASELOAD">CASELOAD</html:option>
								<html:option value="SPN">SPN</html:option>
							</html:select></td>
						</tr>
					</table>
					<!-- end search TABLE -->
					<div class="spacer4px"></div>

					<!-- start search by spn --> <span class="hidden" id="spSearchSPN">
					<table align="center" width="98%" border="0" cellpadding="2"
						cellspacing="0" class="borderTableBlue">
						<tr class="detailHead">
							<td>Search By SPN</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><img
										src="/<msp:webapp/>images/required.gif" title="required"
										alt="required image" border="0" width="10" height="9">SPN</td>
									<td class="formDe"><html:text name="caseAssignmentForm"
										property="defendantId" styleId="selectedDefendantId"
										maxlength="8" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<table border="0" width="100%">
						<tr>
							<td align="center"><html:submit property="submitAction">
								<bean:message key="button.submit" />
							</html:submit> <html:submit property="submitAction">
								<bean:message key="button.refresh" />
							</html:submit> <html:submit property="submitAction">
								<bean:message key="button.cancel" />
							</html:submit></td>
						</tr>
					</table>
					</span> <!-- end search by spn --> <!-- start search by officer --> <span
						class="visible" id="caseloadSpan">
					<table align="center" width="98%" border="0" cellpadding="2"
						cellspacing="0" class="borderTableBlue">
						<tr class="detailHead">
							<td>Search By Caseload</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<logic:notEmpty name="caseAssignmentForm"
									property="officerCaseload.divisions">
									<tr id="divisionRow">
										<td class="formDeLabel" width="1%" nowrap="nowrap">Division</td>
										<td class="formDe" colspan="3"><html:select
											name="caseAssignmentForm"
											property="officerCaseload.selectedDivisionId"
											onchange="getSupervisorsForDivision(this.form);">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection name="caseAssignmentForm"
												property="officerCaseload.divisions" label="description"
												value="OID" />
										</html:select></td>
									</tr>
								</logic:notEmpty>
								<logic:notEmpty name="caseAssignmentForm"
									property="officerCaseload.selectedDivisionId">
									<tr id="supervisorRow">
										<td class="formDeLabel" width="1%">Supervisor</td>
										<td class="formDe"><html:select name="caseAssignmentForm"
											property="officerCaseload.selectedSupervisorId">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection name="caseAssignmentForm"
												property="officerCaseload.supervisorsInDivision"
												label="assignedNameQualifiedByPositionName"
												value="staffPositionId" />
										</html:select> <html:submit property="submitAction">
											<bean:message key="button.getOfficers" />
										</html:submit></td>
										<td class="formDeLabel" width="1%" nowrap="nowrap">Court</td>
										<td class="formDe"><html:text name="caseAssignmentForm"
											property="officerCaseload.courtIdFilter"></html:text></td>
									</tr>
									<logic:notEmpty name="caseAssignmentForm"
										property="officerCaseload.selectedSupervisorId">
										<logic:notEmpty name="caseAssignmentForm"
											property="officerCaseload.officersUnderSupervisor">
											<tr id="officerRow">
												<td class="formDeLabel" width="1%" nowrap="nowrap">Officer</td>
												<td class="formDe" colspan="3"><html:select
													name="caseAssignmentForm"
													property="officerCaseload.selectedOfficerId">
													<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
													<html:optionsCollection name="caseAssignmentForm"
														property="officerCaseload.officersUnderSupervisor"
														label="assignedNameQualifiedByPositionName"
														value="staffPositionId" />
												</html:select></td>
											</tr>
										</logic:notEmpty>
									</logic:notEmpty>
									<tr>
										<td class="formDeLabel"></td>
										<td class="formDe" colspan="3"><html:submit
											property="submitAction">
											<bean:message key="button.viewCaseload" />
										</html:submit> <html:submit property="submitAction">
											<bean:message key="button.refresh" />
										</html:submit></td>
									</tr>
								</logic:notEmpty>
							</table>
							</td>
						</tr>
					</table>
					<div class="spacer4px" /><logic:notEmpty name="caseAssignmentForm"
						property="officerCaseload.defendantsSupervised">
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr class="paddedFourPix">
								<td class="formDeLabel">Current Caseload</td>
							</tr>
							<tr>
								<td bgcolor="#cccccc" colspan="2">
								<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<tr>
										<td class="headerLabel">Supervisor</td>
										<td class="headerData"><bean:write
											name="caseAssignmentForm"
											property="officerCaseload.selectedSupervisorName" /></td>
										<td class="headerLabel">Officer</td>
										<td class="headerData"><bean:write
											name="caseAssignmentForm"
											property="officerCaseload.selectedOfficerName" /></td>
									</tr>
									<tr>
										<td class="headerLabel" width="1%" nowrap="nowrap"># of
										Supervisees</td>
										<td class="headerData"><bean:write
											name="caseAssignmentForm"
											property="officerCaseload.totalSuperviseesInCaseload" /></td>
										<td class="headerLabel" width="1%" nowrap="nowrap"># of Cases</td>
										<td class="headerData"><bean:write
											name="caseAssignmentForm"
											property="officerCaseload.toalCasesInCaseload" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<div class="spacer4px" />
						<table width="98%" border="0" cellpadding="2" cellspacing="0"
							class="borderTableBlue">
							<tr class="detailHead">
								<td>Caseload Supervisee(s)</td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<tr class="formDeLabel">
										<td width="1%"></td>
										<td width="1%"></td>
										<td width="1%"></td>
										<td>SuperviseeName <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="defendantName.formattedName"
											primarySortType="STRING" defaultSort="true"
											defaultSortOrder="ASC" sortId="1" /></td>
										<td>SPN <jims2:sortResults beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="defendantId" primarySortType="INTEGER"
											defaultSortOrder="ASC" sortId="2" /></td>
										<td title="Level Of Supervision">LOS <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="levelOfSupervision" primarySortType="STRING"
											defaultSortOrder="ASC" sortId="3" /></td>
										<td title="In Jail">J <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="jailIndicator" primarySortType="STRING"
											defaultSortOrder="ASC" sortId="4" /></td>
										<td title="Open Warrant">W <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="warrantIndicator" primarySortType="STRING"
											defaultSortOrder="ASC" sortId="5" /></td>
										<td title="Next Office Visit">Next OV <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="nextOfficeVisitDate" primarySortType="DATE"
											defaultSortOrder="ASC" sortId="6" /></td>
										<td title="Last Face to Face Visit">Last F2F <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="lastFaceToFaceDate" primarySortType="DATE"
											defaultSortOrder="ASC" sortId="7" /></td>
										<td title="Days Left">Days Left <jims2:sortResults
											beanName="caseAssignmentForm"
											results="officerCaseload.defendantsSupervised"
											primaryPropSort="daysLeft" primarySortType="INTEGER"
											defaultSortOrder="ASC" sortId="8" /></td>
									</tr>
									<logic:notEmpty name="caseAssignmentForm"
										property="officerCaseload.defendantsSupervised">
										<logic:iterate id="supervisee" name="caseAssignmentForm"
											property="officerCaseload.defendantsSupervised">
											<tr>
												<td width="1%"><html:radio idName="supervisee"
													value="defendantId"
													property="officerCaseload.selectedDefendantId"
													onclick="selectSupervisee(this);" /></td>
												<td width="1%"><a
													href='/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&amp;superviseeReport.defendantId=<bean:write name="supervisee" property="defendantId"/>'>
												<img src="/<msp:webapp/>images/page_go.png"
													alt="View Casenotes" width="16" height="16" border="0" />
												</a></td>
												<td width="1%"><a href="javascript:showHide(' img
													<bean:write name="supervisee" property="defendantId" />', 
																									 '', '/<msp:webapp/>');" title="View Cases">
												<img src="/<msp:webapp/>images/expand.gif"
													name='img<bean:write name="supervisee" property="defendantId" />'
													border="0" alt="" /> </a></td>
												<td><a
													href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&amp;superviseeId=<bean:write name='supervisee' property='defendantId'/>">
												<msp:formatter name="supervisee" property="defendantName"
													format="L,F" /> </a></td>
												<td><bean:write name="supervisee"
													property="defendantId" /></td>
												<td><bean:write name="supervisee"
													property="levelOfSupervision" /></td>
												<td><bean:write name="supervisee"
													property="jailIndicator" /></td>
												<td><bean:write name="supervisee"
													property="warrantIndicator" /></td>
												<td><bean:write name="supervisee"
													property="formattedNextOfficeVisitDate" /></td>
												<td><bean:write name="supervisee"
													property="formattedLastFaceToFaceDate" /></td>
												<logic:lessEqual name="supervisee" property="daysLeft"
													value="90">
													<logic:greaterEqual name="supervisee" property="daysLeft"
														value="0">
														<td><bean:write name="supervisee" property="daysLeft" /></td>
													</logic:greaterEqual>
													<logic:lessThan name="supervisee" property="daysLeft"
														value="0">
														<td class="changedRed"><bean:write name="supervisee"
															property="daysLeft" /></td>
													</logic:lessThan>
												</logic:lessEqual>
												<logic:greaterThan name="supervisee" property="daysLeft"
													value="90">
													<td />
												</logic:greaterThan>
											</tr>
											<tr>
												<td></td>
												<td colspan="10"><span
													id='img<bean:write name="supervisee" property="defendantId" />Span'
													class="hidden">
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td width="5%" title="Degree of Offense">DEG</td>
														<td width="35%" title="Case No">Case</td>
														<td width="1%" title="Court">CRT</td>
														<td width="15%"
															title="Start Date of Community Supervision">DOCS</td>
														<td width="15%" title="End Date of Community Supervision">End
														Date</td>
														<td width="15%">Days Left</td>
														<td width="1%" title="Case Status">C</td>
														<td width="1%" title="Defendant Status">D</td>
													</tr>
													<% int RecordCounter=0;
																				   String bgcolor="";
																				%>
													<logic:iterate id="activeCase" name="supervisee"
														property="caseAssignments">
														<tr
															class='<% RecordCounter++; 
																					              bgcolor = "alternateRow";                      
																					              if (RecordCounter % 2 == 1)
																					                  bgcolor = "normalRow";
																				                  out.print(bgcolor); %>'>
															<td><%-- 
																				        	<html:multibox property="casesSelectedForReassignment">
																				        		<bean:write name="activeCase" property="caseAssignmentId"/>
																				        	</html:multibox>
																				         --%> <input type="checkbox"
																name="officerCaseload.selectedCasesForReassignment"
																value="<bean:write name='activeCase' property='caseAssignmentId'/>"
																onclick="selectSuperviseeCase(this, ' <bean:write name="activeCase" property="defendantId"/>');"/>
															</td>
															<td><bean:write name="activeCase"
																property="degreeOfOffense" /></td>
															<td><a href="javascript:openWindow(' /<msp:webapp/>
																displaySupervisionOrderDetails.do?submitAction=Link&amp;selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">
															<bean:write name="activeCase" property="criminalCaseId" />
															</a></td>
															<td><bean:write name="activeCase" property="courtId" /></td>
															<td><bean:write name="activeCase"
																property="probationStartDate" /></td>
															<td><bean:write name="activeCase"
																property="probationEndDate" /></td>
															<logic:equal name="activeCase" property="daysLeft"
																value="<%=String.valueOf(Long.MAX_VALUE)%>">
																<td />
															</logic:equal>
															<logic:notEqual name="activeCase" property="daysLeft"
																value="<%=String.valueOf(Long.MAX_VALUE)%>">
																<td><bean:write name="activeCase"
																	property="daysLeft" /></td>
															</logic:notEqual>
															<td><bean:write name="activeCase"
																property="caseStatus" /></td>
															<td><bean:write name="activeCase"
																property="defendantStatus" /></td>
														</tr>
													</logic:iterate>
												</table>
												</span></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</td>
							</tr>
						</table>
					</logic:notEmpty>
					<table border="0" width="100%">
						<tr>
							<td align="center"><html:submit property="submitAction"
								onclick="caseloadSearchSubmit(this.form);" disabled="true"
								styleId="caseloadSubmitBtnId">
								<bean:message key="button.reassign" />
							</html:submit> <input type="button" value="Transfer In" disabled /> <input
								type="button" value="Transfer Out" disabled /></td>
						</tr>
						<tr>
							<td align="center"><html:submit property="submitAction">
								<bean:message key="button.back" />
							</html:submit> <html:submit property="submitAction">
								<bean:message key="button.cancel" />
							</html:submit></td>
						</tr>
					</table>
					</span> <!-- end search by officer --></td>
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
<script type="text/javascript"></script>
<head></head>
<html:html></html:html>
