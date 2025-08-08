<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--5/14/2008	LDeen	Defect#51757 -->
<!--6/6/2008    C Shimek  defect#52169 Added missing UI standard alternate row shading  -->
<!--9/19/2008   C Shimek  defect#54359 revised default sortResult to POI and ascending -->
<!--10/8/2008   D Gibler  defect#51759 Changed POI to positionId on retired positions hyperlink -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
<%@ page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - adminStaff/historyReport.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>
	function checkReportDates(){
		if(compareDate1GreaterEqualDate2(document.forms[0].endDateAsStr.value,document.forms[0].startDateAsStr.value)){
			return true;
		}
		else{
			alert("Start date in date range must be less than or equal to end date.");
			return false;
		}
	}
	
	function setFocus()
	{
<logic:equal name="adminStaffReportForm" property="reportType" value="position">

	var dateValue = document.getElementsByName("startDateAsStr");
	if (dateValue != null && dateValue.length > 0 )
	{
		dateValue[0].focus();
		return;
	}

</logic:equal>
<logic:notEqual name="adminStaffReportForm" property="reportType" value="position">
	<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
	
		var posStatus = document.getElementsByName("positionStatusId");
	    if (posStatus != null && posStatus.length > 0 )
		   {	
			posStatus[0].focus();
			return;
		   }
	
	</logic:notEqual>
</logic:notEqual>
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setFocus()">
<html:form action="/handlePositionReport" target="content">
<logic:equal name="adminStaffReportForm" property="reportType" value="position">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|7"> 
	<logic:equal name="adminStaffReportForm" property="positionStatusId" value="R">
	 <%-- Retired Position --%><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|16">
	</logic:equal>
</logic:equal>
<logic:equal name="adminStaffReportForm" property="reportType" value="staff">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|8">
</logic:equal>
<logic:equal name="adminStaffReportForm" property="reportType" value="pu">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|5">
</logic:equal>
<logic:equal name="adminStaffReportForm" property="reportType" value="retire">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|16">
</logic:equal>

	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="/jsp/common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="/jsp/common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="positionsTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD" /> 
									    <bean:message key="prompt.position" /> <bean:message key="prompt.history" /> <bean:message key="prompt.report" /> - 
										<logic:equal name="adminStaffReportForm" property="reportType" value="position">
											<logic:notEqual name="adminStaffReportForm" property="positionStatusId" value="R">
										        <bean:message key="prompt.by" /> <bean:message key="prompt.position" />
										    </logic:notEqual>     
											<logic:equal name="adminStaffReportForm" property="positionStatusId" value="R">
											  <bean:message key="prompt.retired" /> <bean:message key="prompt.position" />
											</logic:equal>
										</logic:equal>
										<logic:equal name="adminStaffReportForm" property="reportType" value="staff">
											<bean:message key="prompt.by" /> <bean:message key="prompt.staff" />
										</logic:equal>
										<logic:equal name="adminStaffReportForm" property="reportType" value="pu">
											<bean:message key="prompt.by" /> <bean:message key="prompt.programUnit" />
										</logic:equal>
										<logic:equal name="adminStaffReportForm" property="reportType" value="retire">
											<bean:message key="button.retiredPositions" />
										</logic:equal>
										
									</td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
							<!-- END ERROR TABLE --> 
						<!-- END HEADING TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
										<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
											<li>Enter filter criteria and click Submit.</li>
											<li>Default search results contains all active and retired positions.</li>
											</logic:notEqual>
											<li>Click Print to print report.</li>
										</ul>
									</td>
								</tr>
								<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
								<tr>
									<td class="required" colspan="2"> *All dates fields must be in the format of mm/dd/yyyy</td>
								</tr>
								</logic:notEqual>
							</table>
						<!-- END INSTRUCTION TABLE -->
							<script>
								clearAllValArrays();					
							</script>
							<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
								<!--filter start-->
								<span id="filterStatus">
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td><bean:message key="button.filter"/> <bean:message key="prompt.report"/></td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr>
												<logic:notEqual name="adminStaffReportForm" property="reportType" value="position">
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.position"/> <bean:message key="prompt.status"/></td>
													<td class="formDe">
														<html:select property="positionStatusId">
															<html:option value=""><bean:message key="select.generic"/></html:option>
															<html:optionsCollection property="positionStatuses" label="description" value="code"/>
														</html:select>
														
													</td>
												</logic:notEqual>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateRange"/></td>
													<td class="formDe">
														
														<SCRIPT LANGUAGE="JavaScript" ID="js1">
															var cal1 = new CalendarPopup();
															cal1.showYearNavigation();
														</SCRIPT>
															<html:text property="startDateAsStr" size="10"/>
															<A HREF="#" onClick="cal1.select(document.forms[0].startDateAsStr,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A> -
															<html:text property="endDateAsStr" size="10"/>
															<A HREF="#" onClick="cal1.select(document.forms[0].endDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A>
															
															<script>
															addMMDDYYYYDateValidation("startDateAsStr","Start Date is invalid.  Valid format is mm/dd/yyyy.");
															addMMDDYYYYDateValidation("endDateAsStr","End Date is invalid.  Valid format is mm/dd/yyyy.");
															</script>
															
													</td>
												</tr>
												<tr>
													<td class="formDeLabel"></td>
													<td class="formDe" colspan="3">
														<html:submit property="submitAction" onclick="return (validateCustomStrutsBasedJS(this.form) && checkReportDates())"><bean:message key="button.submit"/> </html:submit>
														<input type="button" onclick="goNav('/<msp:webapp/>handlePositionReport.do?submitAction=Reset Filter')" value='<bean:message key="button.resetFilter"/>'/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</span>
								<!--filter end-->
							<br>
							</logic:notEqual>
						<!-- BEGIN Results TABLE -->
														
								<div class="legendSmallText" style="width:98%; text-align:left"><span class="inactivePOI">Italic POI codes</span> signify they are retired.</div>
								
								<!-- program unit history start-->
								<span id="puReport">
								<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
									<tr class="detailHead">
										<td title="Probation Officer Indicator">
											<bean:message key="prompt.poi"/>
											<jims:sortResults sortId="1" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="probOfficerInd" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" />
										</td>
										<td title="Position Name">
											<bean:message key="prompt.pos"/> <bean:message key="prompt.name"/>
											<jims:sortResults sortId="2" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="positionName" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td title="Position Type">
											<bean:message key="prompt.pos"/> <bean:message key="prompt.type"/>
											<jims:sortResults sortId="3" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="positionTypeDesc" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td>
											<bean:message key="prompt.name"/>
											<jims:sortResults sortId="4" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="user.userName.formattedName" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td>
											<bean:message key="prompt.cjad"/>
											<jims:sortResults sortId="5" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="user.cjad" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td>
											<bean:message key="prompt.cstsOfficerType"/>
											<jims:sortResults sortId="6" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="officerTypeDesc" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td>
											<bean:message key="prompt.jobTitle"/>
											<jims:sortResults sortId="7" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="jobTitleDesc" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td title="Program Unit">
											<bean:message key="prompt.pu"/>
											<jims:sortResults sortId="8" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="programUnitDesc" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										<td>
											<bean:message key="prompt.user"/>
											<jims:sortResults sortId="9" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="createdBy.userName.formattedName" primarySortType="STRING" defaultSortOrder="DESC" />
										</td>
										
										<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
										<td>
											<bean:message key="prompt.effectiveDate"/>
											<jims:sortResults sortId="10" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="effectiveDateAsStr" primarySortType="DATE" defaultSortOrder="DESC" secondPropSort="assignedDateAsTimeStr" secondarySortType="STRING"/>
										</td>
										</logic:notEqual>
										
										<logic:equal name="adminStaffReportForm" property="reportType" value="retire">
										<td>
											Retirement Date
											<jims:sortResults sortId="10" beanName="adminStaffReportForm" results="filteredList" primaryPropSort="retirementDateAsStr" primarySortType="DATE" defaultSortOrder="DESC" secondPropSort="assignedDateAsTimeStr" secondarySortType="STRING"/>
										</td>
										</logic:equal>

									</tr>
									<logic:empty  name="adminStaffReportForm" property="filteredList">
										<tr>
											<td class="boldText" colspan="9">
												  NO RESULTS AVAILABLE FOR REPORT CRITERIA SELECTED
											</td>
										</tr>
									</logic:empty>
									<%int ctr = 0; %>
									<logic:notEmpty  name="adminStaffReportForm" property="filteredList">
									<nest:iterate  id="retiredPOI" name="adminStaffReportForm" property="filteredList">
									
									<tr class="<% out.print( ((++ctr) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
										<td class="boldText">
											<nest:equal property="retired" value="true">
												<a href="/<msp:webapp/>handlePositionReport.do?submitAction=View&positionId=<nest:write property='positionId'/>">																																						
																									<i><nest:write property="probOfficerInd"/></i></a>
											</nest:equal>
											<nest:equal property="retired" value="false"><nest:write property="probOfficerInd"/></nest:equal>
										</td>
										
										<td class="boldText"><nest:write property="positionName"/></td>
										<td><nest:write property="positionTypeDesc"/></td>
										<td><nest:write property="user.userName.formattedName"/></td>
										<td><nest:write property="user.cjad"/></td>
										<td><nest:write property="officerTypeDesc"/></td>
										<td><nest:write property="jobTitleDesc"/></td>
										<td><nest:write property="programUnitDesc"/></td>
										<td>
											<nest:notEmpty property="createdBy">
											<nest:notEmpty property="createdBy.userName">
												<nest:write property="createdBy.userName.formattedName"/>
											</nest:notEmpty>
											</nest:notEmpty>
											&nbsp;
										</td>
										<logic:notEqual name="adminStaffReportForm" property="reportType" value="retire">
											<td>
											<nest:write property="effectiveDateAsStr"/>
											</td>
										</logic:notEqual>
										
										<logic:equal name="adminStaffReportForm" property="reportType" value="retire">
											<td>
											<nest:write property="retirementDateAsStr"/>
											</td>	
										</logic:equal>									
									</tr>
									</nest:iterate>
									</logic:notEmpty>
								</table>
								<div class="legendSmallText" style="width:98%; text-align:left"><span class="inactivePOI">Italic POI codes</span> signify they are retired.</div>
								</span>
								<!-- program unit history end-->
							
								<!-- END Results TABLE -->
								<br>
								<!-- BEGIN BUTTON TABLE -->
								<table border="0" width="100%">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
											<logic:notEmpty name="adminStaffReportForm" property="filteredList">
											<input type="button" value="Print" onClick="window.print()">
											</logic:notEmpty>
											<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
										</td>
									</tr>
								</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<br>
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
