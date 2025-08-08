<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/17/2007	 Debbie Williamson - Create JSP -->
<%-- 03/04/2008	LDeen		Defect	#49819 	Integrate help    --%>
<!-- 10/08/2008	CShimek		Defect	#51411 	Removed unnecessary <BR> tags on supervisee and user search -->
<!-- 02/09/2009 CShimek     Defect	#56344 	Revised error message table and renderSearch() to correctly display page when no search results are found -->
<!-- 02/27/2009 CShimek     Defect	#56612 	Add validateCustomStrutsBasedJS to case statements in validateSearchInputs() to call validation -->
<!-- 03/27/2009 CShimek     Defect	#58192 	Revised and removed scripts so Assignment Dates keep values after search -->
<!-- 02/15/2010 RYoung     	Defect	#63973 	Cleared firstname and lastname fields after you de-select parent -->
<!-- 11/08/2011 TSVines		Defect	#71044 	Changed OID to ogranizationID -->



<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - reports/assignmentsSearch.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script>
	function renderSearch(selectVal){
		var sb = document.getElementsByName("SearchBy");
		for (x = 0; x<sb[0].options.length; x++){
			if (sb[0].options[x].value == selectVal){
				document.forms[0].searchBy.selectedIndex = x;
				break;
			}	
		}	
		switch (selectVal) {
		case "programUnit":
			document.getElementsByName("userReport.userId")[0].value="";
			document.getElementsByName("userReport.officerLastName")[0].value = "";
			document.getElementsByName("userReport.officerFirstName")[0].value="";
			document.getElementsByName("superviseeReport.defendantId")[0].value="";
			document.getElementsByName("singleCaseReport.criminalCaseId")[0].value="";
			document.getElementsByName("workgroupReport.selectedWorkgroupId")[0].value="";
			if (document.getElementsByName("programUnitReport.assignmentStartDate") [0].value == ""){
				document.getElementsByName("programUnitReport.assignmentStartDate") [0].value = getCurrentDate();
			}
			if (document.getElementsByName("programUnitReport.assignmentEndDate") [0].value == ""){
				document.getElementsByName("programUnitReport.assignmentEndDate") [0].value = getCurrentDate();
			}
			show("puSearch", 1);
			show("userSearch", 0);
			show("superviseeSearch", 0);
			show("caseSearch", 0);
			show("workGroupSearch", 0);
			document.getElementsByName("programUnitReport.selectedProgramUnits")[0].focus();
			break;
		case "user":
			document.getElementsByName("userReport.userId")[0].value="";
			document.getElementsByName("userReport.officerLastName")[0].value = "";
			document.getElementsByName("userReport.officerFirstName")[0].value="";
			document.getElementsByName("programUnitReport.selectedProgramUnits")[0].value="";
			document.getElementsByName("superviseeReport.defendantId")[0].value="";
			document.getElementsByName("singleCaseReport.criminalCaseId")[0].value="";
			document.getElementsByName("workgroupReport.selectedWorkgroupId")[0].value="";
			if (document.getElementsByName("userReport.assignmentStartDate") [0].value == ""){
				document.getElementsByName("userReport.assignmentStartDate") [0].value = getCurrentDate();
			}
			if (document.getElementsByName("userReport.assignmentEndDate") [0].value == ""){
				document.getElementsByName("userReport.assignmentEndDate") [0].value = getCurrentDate();
			}
			show("puSearch", 0);
			show("userSearch", 1);
			show("superviseeSearch", 0);
			show("caseSearch", 0);
			show("workGroupSearch", 0);
			document.getElementsByName("userReport.userId")[0].focus();
			break;
		case "supervisee":
			document.getElementsByName("programUnitReport.selectedProgramUnits")[0].value="";
			document.getElementsByName("userReport.userId")[0].value="";
			document.getElementsByName("userReport.officerLastName")[0].value = "";
			document.getElementsByName("userReport.officerFirstName")[0].value="";
			document.getElementsByName("singleCaseReport.criminalCaseId")[0].value="";
			document.getElementsByName("workgroupReport.selectedWorkgroupId")[0].value="";
			if (document.getElementsByName("superviseeReport.assignmentStartDate") [0].value == ""){
				document.getElementsByName("superviseeReport.assignmentStartDate") [0].value = getCurrentDate();
			}
			if (document.getElementsByName("superviseeReport.assignmentEndDate") [0].value == ""){
				document.getElementsByName("superviseeReport.assignmentEndDate") [0].value = getCurrentDate();
			}
			show("userSearch", 0);
			show("puSearch", 0);
			show("superviseeSearch", 1);
			show("caseSearch", 0);
			show("workGroupSearch", 0);
			document.getElementsByName("superviseeReport.defendantId")[0].focus();
			break;
		case "individualCase":
			document.getElementsByName("programUnitReport.selectedProgramUnits")[0].value="";
			document.getElementsByName("userReport.userId")[0].value="";
			document.getElementsByName("userReport.officerLastName")[0].value = "";
			document.getElementsByName("userReport.officerFirstName")[0].value="";
			document.getElementsByName("superviseeReport.defendantId")[0].value="";
			document.getElementsByName("workgroupReport.selectedWorkgroupId")[0].value="";
			show("userSearch", 0);
			show("puSearch", 0);
			show("superviseeSearch", 0);
			show("caseSearch", 1);
			show("workGroupSearch", 0);
			document.getElementsByName("singleCaseReport.criminalCaseId")[0].focus();
			break;
		case "workGroup":
			document.getElementsByName("programUnitReport.selectedProgramUnits")[0].value="";
			document.getElementsByName("userReport.userId")[0].value="";
			document.getElementsByName("userReport.officerLastName")[0].value = "";
			document.getElementsByName("userReport.officerFirstName")[0].value="";
			document.getElementsByName("superviseeReport.defendantId")[0].value="";
			document.getElementsByName("singleCaseReport.criminalCaseId")[0].value="";
			if (document.getElementsByName("workgroupReport.assignmentStartDate") [0].value == ""){
				document.getElementsByName("workgroupReport.assignmentStartDate") [0].value = getCurrentDate();
			}
			if (document.getElementsByName("workgroupReport.assignmentEndDate") [0].value == ""){
				document.getElementsByName("workgroupReport.assignmentEndDate") [0].value = getCurrentDate();
			}			
			show("userSearch", 0);
			show("puSearch", 0);
			show("superviseeSearch", 0);
			show("caseSearch", 0);
			show("workGroupSearch", 1);
			document.getElementsByName("workgroupReport.selectedWorkgroupId")[0].focus();
			break;
		}
	}
		
	function validateSearchInputs(theForm) {
		clearAllValArrays();
		
		var theSelect=theForm.searchBy;
		var theSelectedVal=theSelect.options[theSelect.selectedIndex].value;
		var thisForm = document.forms[0];
		var returnVal=true;
		switch (theSelectedVal) {
			case "programUnit":
			    customValRequired("programUnitReport.selectedProgramUnits","<bean:message key='errors.required' arg0='Program Unit'/>","");
				var startDateId = "puAssignmentStartDateText";
				var endDateId = "puAssignmentEndDateText";
				returnVal = validateCustomStrutsBasedJS(theForm) && validateDates(startDateId, endDateId, theForm);
				break;
			case "user":
			    //customValRequired("userReport.userId","<bean:message key='errors.required' arg0='User ID'/>","");
				var startDateId = "userAssignmentStartDateText";
				var endDateId = "userAssignmentEndDateText";
				returnVal = validateCustomStrutsBasedJS(theForm) && validateDates(startDateId, endDateId, theForm);
				break;
			case "supervisee":
			    customValRequired("superviseeReport.defendantId","<bean:message key='errors.required' arg0='SPN'/>","");
				var startDateId = "supAssignmentStartDateText";
				var endDateId = "supAssignmentEndDateText";
				returnVal = validateCustomStrutsBasedJS(theForm) && validateDates(startDateId, endDateId, theForm);
				break;
			case "individualCase":
			    customValRequired("singleCaseReport.criminalCaseId","<bean:message key='errors.required' arg0='Case Number'/>","");
			    customValRequired("singleCaseReport.courtDivisionIndicator","<bean:message key='errors.required' arg0='CDI'/>","");
			    returnVal = validateCustomStrutsBasedJS(theForm);
				break;
			case "workGroup":
			    customValRequired("workgroupReport.selectedWorkgroupId","<bean:message key='errors.required' arg0='Workgroup'/>","");
				var startDateId = "wgAssignmentStartDateText";
				var endDateId = "wgAssignmentEndDateText";
				returnVal = validateCustomStrutsBasedJS(theForm) && validateDates(startDateId, endDateId, theForm);
				break;
		}
		return returnVal;
	}
	
	function validateDates(startDate, endDate, theForm) {
		var startDateErrorMessage = "Start Date is invalid.  Valid format is mm/dd/yyyy.";
		var endDateErrorMessage = "End Date is invalid.  Valid format is mm/dd/yyyy.";

		addMMDDYYYYDateValidation(startDate, startDateErrorMessage);
		addMMDDYYYYDateValidation(endDate, endDateErrorMessage);
		returnVal=validateCustomStrutsBasedJS(theForm);	
		if(returnVal) {
			var startDateVal = document.getElementById(startDate).value;
			var endDateVal = document.getElementById(endDate).value;
			if (startDateVal != "" && endDateVal != "")
			{ 
			   returnVal = compareDate1GreaterEqualDate2(endDateVal, startDateVal);  
		       if(!returnVal) 
		       {
				   alert("Start date in date range must be less than or equal to end date.");
				   return false
		       }	   
			}
		}	   
		return returnVal;
	}

	function refreshInputs(theForm){
		var theSelect=theForm.searchBy;
		var theSelectedVal=theSelect.options[theSelect.selectedIndex].value;
				
		switch (theSelectedVal) {
		case "programUnit":
		    theForm["programUnitReport.selectedProgramUnits"].selectedIndex = -1;
	        theForm["programUnitReport.assignmentStartDate"].value = getCurrentDate();
	        theForm["programUnitReport.assignmentEndDate"].value = getCurrentDate();
		    break;
		case "user":  	   
			theForm["userReport.userId"].value = "";
			theForm["userReport.officerLastName"].value = "";
			theForm["userReport.officerFirstName"].value = "";
		    theForm["userReport.selectedProgramUnits"].selectedIndex = -1;		    
	        theForm["userReport.assignmentStartDate"].value = getCurrentDate();
	        theForm["userReport.assignmentEndDate"].value = getCurrentDate();
			break;
		case "supervisee":
		    theForm["superviseeReport.defendantId"].value = "";
	        theForm["superviseeReport.assignmentStartDate"].value = getCurrentDate();
	        theForm["superviseeReport.assignmentEndDate"].value = getCurrentDate();
			break;
		case "individualCase":     
		    theForm["singleCaseReport.criminalCaseId"].value = "";
		    theForm["singleCaseReport.courtDivisionIndicator"].value = "";
			break;
		case "workGroup": 	    
		    theForm["workgroupReport.selectedWorkgroupId"].selectedIndex = 0;
	    	theForm["workgroupReport.selectedProgramUnits"].selectedIndex = -1;
	        theForm["workgroupReport.assignmentStartDate"].value = getCurrentDate();
	        theForm["workgroupReport.assignmentEndDate"].value = getCurrentDate();
			break;
		}
		theSelect.focus();
	}

</script>
</head>
<body topmargin="0" leftmargin="0" onload="renderSearch(document.forms[0].searchBy.value);" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/caseAssignmentReportSearchAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Reports/View_Assignments.htm#|1">
	
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" id="mainTable">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="commonSupervisionTabsTable">
						<tr>
							<td valign="top">
								<!--tabs start-->
								<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tabid" value="setupTab"/>
								</tiles:insert>
								<!--tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue" id="blueBorderTable">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<table width="98%" border="0" cellpadding="0" cellspacing="0" id="managerFeaturesTabTable">
									<tr>
										<td valign="top">
											<!--tabs start-->
                                            <tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
												<tiles:put name="tabid" value="reportsTab"/>
											</tiles:insert>						
										</td>
									</tr>									
								</table>
								<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen" id="greenBorderTable">
									<tr>
										<td><img src="/<msp:webapp/>common/images/spacer.gif" height="5"></td>
									</tr>
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN HEADING TABLE -->
											<table width="100%" id="headingTable">
												<tr>
													<td align="center" class="header"><bean:message key="title.CSCD" /> - 
                                                                                      <bean:message key="title.assignment" />&nbsp;<bean:message key="title.search" /></td>
												</tr>
											</table>
											<!-- END HEADING TABLE -->
											<!-- BEGIN ERROR TABLE -->
											<table width="98%" align="center" id="errorTable">							
												<tr>
													<td align="center" class="errorAlert"><html:errors></html:errors></td>
												</tr>		
											</table>
											<logic:notEqual name="caseAssignmentReportForm" property="errorMsg" value="" >
											<table width="98%" align="center">							
												<tr>
													<td align="center" class="errorAlert"><bean:write name="caseAssignmentReportForm" property="errorMsg" /></td>
												</tr>		
											</table>
											</logic:notEqual>
											<!-- END ERROR TABLE -->

											<!-- BEGIN INSTRUCTION TABLE -->
											<table width="98%" border="0" id="generalInstructionsTable">
												<tr>
													<td>
														<ul>
															<li>Select Search By to change the search type.</li>
															<li>Click Submit button to retrieve report.</li>
															<li>User search is the user who performed the Assignment to Program Unit</li>
														</ul>
													</td>
												</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->
											
											<!-- BEGIN SELECTED SEARCH TABLE -->
											<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" id="searchContextTable">
												<tr>
													<td class="formDeLabel" width="1%" nowrap>
														<bean:message key="prompt.searchBy" />
													</td>
													<td class="formDe">
														<html:select property="searchBy" onchange="renderSearch(this.options[selectedIndex].value)" styleId="searchBySelect">
															<html:option value="individualCase">Case</html:option> 
															<html:option value="programUnit">Program Unit</html:option>
															<html:option value="supervisee">Supervisee</html:option>
															<html:option value="user">User</html:option>
															<html:option value="workGroup">Workgroup</html:option>
														</html:select>
													</td>
												</tr>
											</table>
											<!-- END SELECTED SEARCH TABLE -->
											<!-- common script for calendars -->	
											<script type="text/javascript">
												var cal1 = new CalendarPopup();
												cal1.showYearNavigation();
											</script>
											<!-- BEGIN PROGRAM UNIT -->
											<span class="visible" id="puSearch">
												<br>
												<table width="98%" cellpadding="0" cellspacing="0" id="programUnitSearchInstructionTable">
													<tr>
														<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/> Required Field.&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
													</tr>
												</table>
												<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" id="programUnitSearchBorderTable">
													<tr class="detailhead">
														<td><bean:message key="prompt.searchBy"/>&nbsp;<bean:message key="prompt.programUnit" /></td>
													</tr>
													<tr>
														<td>
															<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" id="programUnitSearchCriteriaTable">
																															
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programUnit"/>(s)</td>
																	<td class="formDe" colspan="3">
	                                                                    <html:select name="caseAssignmentReportForm" property="programUnitReport.selectedProgramUnits" multiple="true" styleId="programUnitSelect">
	                                                                         <html:option value=""><bean:message key="select.multiple" /></html:option>  			            	  	
										                                     <html:optionsCollection name="caseAssignmentReportForm" property="programUnitReport.programUnits"  value="organizationId" label="description"/>
									                                    </html:select>  
																	</td>
																</tr>
																<tr>															
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																	<td class="formDe" colspan="3">
																	<table cellpadding="2" cellspacing="1" id="programUnitCalendarTable">
																		<tr>
																			<td class="formDeLabel"><bean:message key="prompt.begin" /></td>
																			<td></td>
																			<td class="formDeLabel"><bean:message key="prompt.end" /></td>
																		</tr>
																		<tr class="formDe">
																			<td>
							                                                    <html:text name="caseAssignmentReportForm" property="programUnitReport.assignmentStartDate" size="10" maxlength="10" styleId="puAssignmentStartDateText" />
																				<A HREF="#" onClick="cal1.select(document.getElementById('puAssignmentStartDateText'),'puAnchor1','MM/dd/yyyy'); return false;" NAME="puAnchor1" ID="puAnchor1">
																					<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																				</A>
																			</td>
																		<%-- 	<script>
																			    var elem1=document.getElementById('programUnitReport.assignmentStartDate');
								                                                elem1.value = getCurrentDate();    
																			</script>  --%>
																			<td>-</td>    
																			<td>
							                                                    <html:text name="caseAssignmentReportForm" property="programUnitReport.assignmentEndDate" size="10" maxlength="10" styleId="puAssignmentEndDateText"/>
																				<A HREF="#" onClick="cal1.select(document.getElementById('puAssignmentEndDateText'),'puAnchor2','MM/dd/yyyy'); return false;" NAME="puAnchor2" ID="puAnchor2">
																					<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																				</A>
																			</td>
																		<%--	<script>
																			    var elem1=document.getElementById('programUnitReport.assignmentEndDate');
								                                                elem1.value = getCurrentDate();   
																			</script>  --%>
																			</tr>
																		</table>
																	</td>
																</tr>
																</table>
															</td>
														</tr>
													</table>
												</span>
											<!-- END PROGRAM UNIT -->
														
											<!-- BEGIN USER SELECTED -->
												<span class="hidden" id="userSearch">
													<br>
													<table width="98%" cellpadding="0" cellspacing="0" id="userSearchInstructionsTable">
														<tr>
															<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> 
																User ID or Last Name and First Name are required.&nbsp;  *All date fields must be in the format of mm/dd/yyyy.
															</td>
														</tr>
													</table>
													<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" id="userSearchBorderTable">
														<tr>
															<td class="detailhead" colspan="2"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.userId" /></td>
														</tr>
														<tr>
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1" id="userSearchCriteriaTable">
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/>&nbsp;<bean:message key="prompt.userId" /></td>
																		<td class="formDe"><html:text name="caseAssignmentReportForm" property="userReport.userId" maxlength="8" size="8"/></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/>&nbsp;<bean:message key="prompt.lastName" /></td>
																		<td class="formDe"><html:text name="caseAssignmentReportForm" property="userReport.officerLastName" maxlength="75" size="30"/></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/>&nbsp;<bean:message key="prompt.firstName" /></td>
																		<td class="formDe"><html:text name="caseAssignmentReportForm" property="userReport.officerFirstName" maxlength="50" size="30"/></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.programUnit" />(s)</td>
																		<td class="formDe" colspan="3">
                                                                            <html:select name="caseAssignmentReportForm" property="userReport.selectedProgramUnits" multiple="true" styleId="programUnitSelect">
                                                                                 <html:option value=""><bean:message key="select.multiple" /></html:option>  			            	  	
											                                     <html:optionsCollection name="caseAssignmentReportForm" property="userReport.programUnits"  value="organizationId" label="description"/>
										                                    </html:select>  										                                    
																		</td>
																	</tr>									
																	<tr id="searchUserDate" >
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																		<td class="formDe" colspan="3">
																			<table cellpadding="2" cellspacing="1" id="userSearchCalendarTable">
																				<tr>
																					<td class="formDeLabel"><bean:message key="prompt.begin" /></td>
																					<td></td>
																					<td class="formDeLabel"><bean:message key="prompt.end" /></td>
																				</tr>
																				<tr class="formDe">
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="userReport.assignmentStartDate" size="10" maxlength="10" styleId="userAssignmentStartDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('userAssignmentStartDateText'),'userAnchor1','MM/dd/yyyy'); return false;" NAME="userAnchor1" ID="userAnchor1">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>																					   																						   
																					</td>
																					<td>-</td>
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="userReport.assignmentEndDate" size="10" maxlength="10" styleId="userAssignmentEndDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('userAssignmentEndDateText'),'userAnchor2','MM/dd/yyyy'); return false;" NAME="userAnchor2" ID="userAnchor2">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</span>
											<!-- END USER SELECTED -->
											
											<!-- BEGIN SUPERVISEE SELECTED -->			
												<span class="hidden" id="superviseeSearch">
													<br>
													<table width="98%" cellpadding="0" cellspacing="0" id="superviseeSearchInstructionTable">
														<tr>
															<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/>Required Field.&nbsp;  *All date fields must be in the format of mm/dd/yyyy.</td>
														</tr>
													</table>
													<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue" id="superviseeSearchBorderTable">
														<tr>
															<td class="detailhead" colspan="2"><bean:message key="prompt.searchBySPN" /></td>
														</tr>
														<tr>
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1" id="superviseeSearchCriteriaTable">
																	<tr id="searchSuperviseeSpn" >
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/>&nbsp;<bean:message key="prompt.SPN" /></td>
																		<td class="formDe"><html:text name="caseAssignmentReportForm" property="superviseeReport.defendantId" maxlength="8" size="10"/></td>
																	</tr>
																	<tr id="searchSuperviseeDate" >
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																		<td class="formDe" colspan="3">
																			<table cellpadding="2" cellspacing="1" id="superviseeSearchCalendarTable">
																				<tr>
																					<td class="formDeLabel"><bean:message key="prompt.begin" /></td>
																					<td></td>
																					<td class="formDeLabel"><bean:message key="prompt.end" /></td>
																				</tr>
																				<tr class="formDe">
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="superviseeReport.assignmentStartDate" size="10" maxlength="10" styleId="supAssignmentStartDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('supAssignmentStartDateText'),'supAnchor1','MM/dd/yyyy'); return false;" NAME="supAnchor1" ID="supAnchor1">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>
																					</td>
																					<td>-</td>
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="superviseeReport.assignmentEndDate" size="10" maxlength="10" styleId="supAssignmentEndDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('supAssignmentEndDateText'),'supAnchor2','MM/dd/yyyy'); return false;" NAME="supAnchor2" ID="supAnchor2">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</span>
											<!-- END SUPERVISEE SELECTED -->
																
											<!-- BEGIN CASE SELECTED -->
												<span class="hidden" id="caseSearch">
													<br>
													<table width="98%" cellpadding="0" cellspacing="0" id="caseSearchInstructionTable">
														<tr>
															<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/>Required Fields.
														</tr>
													</table>
													<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" id="caseSearchBorderTable">
														<tr class="detailhead">
															<td><bean:message key="prompt.searchByCase" /></td>
														</tr>
														<tr>  
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1" id="caseSearchCriteriaTable">
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.case#" /></td>
																		<td class="formDe">
																			<html:text name="caseAssignmentReportForm" property="singleCaseReport.criminalCaseId" maxlength="15" size="15"/>
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.CDI" /></td>
																		<td class="formDe">
																			<html:text name="caseAssignmentReportForm" property="singleCaseReport.courtDivisionIndicator" maxlength="8" size="8"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</span>
											<!-- END CASE SELECTED -->
															
											<!-- BEGIN WORK GROUP SELECTED -->				
												<span class="hidden" id="workGroupSearch">
													<br>
													<table width="98%" cellpadding="0" cellspacing="0" id="workgroupSearchInstructionTable">
														<tr>
															<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/>Required Field. &nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
														</tr>
													</table>
													<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" id="workgroupSearchBorderTable">
														<tr class="detailhead">
															<td><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.workgroup" /></td>
														</tr>																															
														<tr>
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1" id="workgroupSearchCriteriaTable">																	
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.workgroup" /></td>
																		<td class="formDe">
                                                                            <html:select name="caseAssignmentReportForm" property="workgroupReport.selectedWorkgroupId" >
                                                                                 <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
											                                     <html:optionsCollection name="caseAssignmentReportForm" property="workgroupReport.workgroups"  value="workgroupId" label="workgroupName"/>
										                                    </html:select>  
																		</td>																					
																	</tr>																			
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.programUnit" />(s)</td>
																		<td class="formDe" colspan="3">
                                                                            <html:select name="caseAssignmentReportForm" property="workgroupReport.selectedProgramUnits" multiple="true" styleId="programUnitSelect">
                                                                                 <html:option value=""><bean:message key="select.multiple" /></html:option>  			            	  	
											                                     <html:optionsCollection name="caseAssignmentReportForm" property="workgroupReport.programUnits"  value="organizationId" label="description"/>
										                                    </html:select>  
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																		<td class="formDe" colspan="3">
																			<table cellpadding="2" cellspacing="1" id="workgroupSearchCalendarTable">
																				<tr>
																					<td class="formDeLabel"><bean:message key="prompt.begin" /></td>
																					<td></td>
																					<td class="formDeLabel"><bean:message key="prompt.end" /></td>
																				</tr>
																				<tr class="formDe">
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="workgroupReport.assignmentStartDate" size="10" maxlength="10" styleId="wgAssignmentStartDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('wgAssignmentStartDateText'),'wgAnchor1','MM/dd/yyyy'); return false;" NAME="wgAnchor1" ID="wgAnchor1">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>
																					</td>
																					<td>-</td>
																					<td>
									                                                    <html:text name="caseAssignmentReportForm" property="workgroupReport.assignmentEndDate" size="10" maxlength="10" styleId="wgAssignmentEndDateText"/>
																						<A HREF="#" onClick="cal1.select(document.getElementById('wgAssignmentEndDateText'),'wgAnchor2','MM/dd/yyyy'); return false;" NAME="wgAnchor2" ID="wgAnchor2">
																							<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
																						</A>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</span>
											<!-- END WORK GROUP SELECTED -->
												<br>
												<!-- BEGIN BUTTON TABLE -->
												<table border="0" width="100%" id="buttonsTable">
													<tr>
														<td align="center">
															<html:submit property="submitAction" onclick="return validateSearchInputs(this.form) && disableSubmit(this, this.form);" styleId="submitButton">
																<bean:message key="button.submit"></bean:message>
															</html:submit>&nbsp;
														<%-- 	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);" styleId="refreshButton">
																<bean:message key="button.refresh"></bean:message>
															</html:submit>&nbsp;  --%>
															<input type="button" name="refresh" value=<bean:message key="button.refresh"/> onclick="refreshInputs(this.form)" />
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);" styleId="cancelButton">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>  																																		 			
														</td>
													</tr>
												</table>
												<!-- END BUTTON TABLE -->
											</td>
										</tr>
									</table><br>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END  TABLE -->
		</div>
		<br>
	</html:form>	
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
