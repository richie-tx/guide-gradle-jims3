<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/15/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
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
<title><bean:message key="title.heading" /> - reports/assessmentsReport.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/AnchorPosition.js"></script>
	
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
var searchType = "programUnit";

function renderSearch(theSelect){
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	switch (theSelectValue)
	{
		case "programUnit":
		show("puSearch", 1)
		show("superviseeSearch", 0)
		searchType = "programUnit";
		break

		case "supervisee":
		show("puSearch", 0)
		show("superviseeSearch", 1)
		searchType = "supervisee";
		break
	}
}

function determineSubmit(){
	goNav('assessmentsResults.htm?' + searchType);
}

function renderSearch1(theSelectSupervisee){
	theSelectSuperviseeValue = theSelectSupervisee.options[theSelectSupervisee.selectedIndex].value;

	switch (theSelectSuperviseeValue)
	{
		case "SPN":
			document.forms[0].superviseeLast.value="";
			document.forms[0].superviseeFirst.value="";
			show("superviseeSpnSearchHeader",1,"row");
			show("superviseeNameSearchHeader",0,"row");
			show("searchSuperviseeSpn",1,"row");
			show("searchSuperviseeName",0,"row");
			show("searchSuperviseeDate",1,"row");
		break;

		case "NAME":
		document.forms[0].superviseeSpn.value="";
			show("superviseeSpnSearchHeader",0,"row");
			show("superviseeNameSearchHeader",1,"row");
			show("searchSuperviseeSpn",0,"row");
			show("searchSuperviseeName",1,"row");
			show("searchSuperviseeDate",1,"row");
		break;
	}
}

</script>
</head>
<body topmargin=0 leftmargin="0" onload="renderSearch(document.myForm.searchBy); renderSearch1(document.myForm.superviseeSearchBy);"  onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayAssessmentsSearchResults" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
	</tr>
	<tr>
		<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="setupTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
						<!--tabs start-->
							<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="reportsTab"/>
							</tiles:insert>	
						<!--tabs end-->
								</td>
							</tr>
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header"><bean:message key="title.cscd"/>&nbsp;-
																			  <bean:message key="prompt.assessments"/>&nbsp;<bean:message key="title.search"/></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select Search By to change the search type.</li>
													<li>Click Submit button to retrieve report.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->

									<!-- BEGIN SELECTED SEARCH TABLE -->
									<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr>
											<td class=formDeLabel width=1% nowrap>
												Search By
											</td>
											<td class=formDe>
												<select name=searchBy onchange="renderSearch(this)">
													<option value="programUnit" selected>PROGRAM UNIT/OFFICER</option>
													<option value="supervisee">SUPERVISEE</option>
												</select>
											</td>
										</tr>
									</table>
									<!-- END SELECTED SEARCH TABLE -->

									<!-- BEGIN PROGRAM UNIT/OFFICER SELECTED -->
									<span class=visible id="puSearch">
										<br>
										<table width="98%" cellpadding="0" cellspacing="0">
											<tr>
												<td class=required colspan=2>At Least 1 is required.&nbsp;&nbsp;Last Name is required to search by First Name *All date fields must be in the format of mm/dd/yyyy.</td>
											</tr>
										</table>
										<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
											<tr class=detailhead>
												<td>Search by Program Unit / Officer</td>
											</tr>
											<tr>
												<td>
													<table align="center" width=100% border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width=1% nowrap>Assessment Date Range</td>
															<td class="formDe" colspan=3>
																<table cellpadding="2" cellspacing="1">
																	<tr>
																		<td class=formDeLabel><bean:message key="prompt.begin"/></td>
																		<td></td>
																		<td class=formDeLabel><bean:message key="prompt.end"/></td>
																	</tr>
																	<tr class=formDe>
																		<td>
																			<SCRIPT LANGUAGE="JavaScript" ID="js1">
																				var cal1 = new CalendarPopup();
																				cal1.showYearNavigation();
																			</SCRIPT>
																				<html:text name="searchAssessmentForm" property="beginDateStr"  size="10" maxlength="10" />
																				<A HREF="#" onClick="cal1.select(beginDateStr,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1"><bean:message key="prompt.3.calendar" title="(mm/dd/yyyy)"/></A> </td>
																			<td>-</td>
																			<td>
																				<html:text name="searchAssessmentForm" property="endDateStr"  size="10" maxlength="10" />
																				<A HREF="#" onClick="cal1.select(endDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.3.calendar" title="(mm/dd/yyyy)"/></A>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.programUnit"/></td>
																<td class="formDe" colspan=3>
																	<html:select property="programUnit">
																		<html:option value="">Please Select</html:option>
																		<html:option value="dr">Day Reporting</html:option>
																		<html:option value="dv">Domestic Violence</html:option>
																		<html:option value="mh">Mental Health </html:option>
																		<html:option value="mh">North Region</html:option>
																	</html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.supervisionOfficer"/></td>
																<td class="formDe" colspan=3>
																	<table>
																		<tr>
																			<td class=formDeLabel><bean:message key="prompt.last"/></td>
																			<td class=formDeLabel><bean:message key="prompt.first"/></td>
																		</tr>
																		<tr>
																			<td class=formDe><html:text name="searchAssessmentForm" property="supervisionOfficer.lastName"  size="30" maxlength="75" /></td>
																			<td class=formDe><html:text name="searchAssessmentForm" property="supervisionOfficer.firstName"  size="30" maxlength="50" /></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.CSOPOI"/></td>
																<td class="formDe" colspan=3>
																	<html:text name="searchAssessmentForm" property="CSOPOI"  size="10" maxlength="10" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.CSOPositionName"/></td>
																<td class="formDe" colspan=3>
																	<html:text name="searchAssessmentForm" property="CSOPositionName"  size="30" maxlength="30" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</span>
									<!-- END PROGRAM UNIT/OFFICER SELECTED -->

									<!-- BEGIN SUPERVISEE SELECTED -->
										<span class=hidden id=superviseeSearch>
											<br>
											<table width="98%" cellpadding="0" cellspacing="0">
												<tr>
													<td class=required colspan=2><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
												</tr>
											</table>
											<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
												<tr>
													<td class=formDeLabel width=1% nowrap><bean:message key="prompt.searchBy"/></td>
													<td class=formDe>
														<html:select property="superviseeSearchBy" onchange="renderSearch1(this)">
															<html:option value="SPN" selected>SPN</html:option>
															<html:option value="NAME" >NAME</html:option>
														</html:select>
													</td>
												</tr>
											</table>
											<br>
											<table align="center" width=98% cellpadding="2" cellspacing="0" class=borderTableBlue>
												<tr id="superviseeSpnSearchHeader" >
													<td class=detailHead colspan="2"><bean:message key="prompt.searchBySPN"/></td>
												</tr>
												<tr id="superviseeNameSearchHeader" >
													<td class=detailHead colspan="2"><bean:message key="prompt.searchBy"/>&nbsp;<bean:message key="prompt.name"/></td>
												</tr>
												<tr>
													<td>
														<table width=100% border="0" cellpadding="2" cellspacing="1">
															<tr id="searchSuperviseeSpn" >
																<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.SPN"/></td>
																<td class="formDe"><html:text name="searchAssessmentForm" property="superviseeSPN"  size="8" maxlength="8" /></td>
															</tr>
															<tr id="searchSuperviseeName" >
																<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.superviseeName"/></td>
																<td class="formDe" colspan=3>
																<table>
																	<tr>
																		<td class=formDeLabel><bean:message key="prompt.last"/></td>
																		<td class=formDeLabel><bean:message key="prompt.first"/></td>
																	</tr>
																	<tr>
																		<td class="formDe"><html:text name="searchAssessmentForm" property="superviseeName.Last"  size="30" maxlength="75" /></td>
																		<td class="formDe"><html:text name="searchAssessmentForm" property="superviseeNameFirst"  size="30" maxlength="50" /></td>
																	</tr>
																</table>
																</td>
															</tr>
															<tr id="searchSuperviseeDate" >
																<td class="formDeLabel" width=1% nowrap>Assessment Date Range</td>
																<td class="formDe" colspan=3>
																	<table cellpadding="2" cellspacing="1">
																		<tr>
																			<td class=formDeLabel><bean:message key="prompt.begin"/></td>
																			<td></td>
																			<td class=formDeLabel><bean:message key="prompt.end"/></td>
																		</tr>
																		<tr class=formDe>
																			<td>
																				<html:text name="searchAssessmentForm" property="assessmentBeginDateStr"  size="10" maxlength="10" />
																				<A HREF="#" onClick="cal1.select(assessmentBeginDateStr,'anchor7','MM/dd/yyyy'); return false;" NAME="anchor7" ID="anchor7"><bean:message key="prompt.3.calendar" title="(mm/dd/yyyy)"/></A> </td>
																			<td>-</td>
																			<td>
																				<html:text name="searchAssessmentForm" property="assessmentEndDateStr"  size="10" maxlength="10" />
																				<A HREF="#" onClick="cal1.select(assessmentEndDateStr,'anchor8','MM/dd/yyyy'); return false;" NAME="anchor8" ID="anchor8"><bean:message key="prompt.3.calendar" title="(mm/dd/yyyy)"/></A>
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
																			
										<br>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return (validateSearch(this.form) && validateWorkgroupSearchForm(this.form) && disableSubmit(this, this.form));">
														<bean:message key="button.submit"></bean:message></html:submit>&nbsp;
													<html:reset><bean:message key="button.reset"/></html:reset>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
														<bean:message key="button.cancel"></bean:message></html:submit>  																																		 			
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
