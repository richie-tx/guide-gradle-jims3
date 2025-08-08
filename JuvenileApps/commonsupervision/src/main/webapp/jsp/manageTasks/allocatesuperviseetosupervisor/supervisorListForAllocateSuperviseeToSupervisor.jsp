<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/03/2010	 RYoung - Defect # 65628 Empty PU in Select Program Unit field on Allocate to Supervisor task -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>CommonSupervision/manageTasks/allocatesuperviseetosupervisor/supervisorListForAllocateSuperviseeToSupervisor.jsp</title>

	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
    <script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
		
	<script type="text/javascript">
		function hideButton(){
			show("Next", 0);
		}
		
		function showButton(){
			show("Next", 1);
		}
		
		function validateProgramUnitAssignmentDate(theForm) {
			clearAllValArrays();
		    customValRequired("programUnitAllocationDate","<bean:message key='errors.required' arg0='Program Unit Assignment Date'/>","");	    
		    var assignmentDateId = "puAssignmentDateText";
		    var assignmentDateErrorMessage = "Program Unit Assignment Date is invalid.  Valid format is mm/dd/yyyy.";
		    addMMDDYYYYDateValidation(assignmentDateId, assignmentDateErrorMessage);
		    
		    var returnVal = validateCustomStrutsBasedJS(theForm);
		    if (returnVal) {
		    	returnVal = validateRadios(theForm, "supervisorPositionId", "Select a Supervisor.");
		    }			
		    return returnVal;		
		}

		function checkForSingleResult() {
		    var rbs = document.getElementsByName("supervisorPositionId");
			if (rbs.length == 1){
				rbs[0].checked = true;
				showButton();
			}	
		}
		
	</script>
</head>

<body topmargin="0" leftmargin="0" onload="hideButton(); checkForSingleResult()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
		<html:form action="/selectsupervisorforsupervisorallocation" target="content">
			<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|23">
		<div align="center">
		<!-- OUTSIDE TABLE -->
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top">
						<!-- BLUE TABS TABLE -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--blue tabs start-->
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--blue tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<!-- BLUE TABS TABLE -->
						<!-- BORDER TABLE BLUE TABLE -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header" >Allocate Supervisee to Supervisor - Select Supervisor</td>
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
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select a supervisor.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<!--header start-->
									<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									
									<br>
									<!-- BEGIN DETAIL 1 TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap">
												Division</td>
											<td class="formDe">
												<bean:write name="caseAssignmentForm" property="divisionName"/>													
												<html:link action="/selectprogramunitforprogramunitassignment" paramId="submitAction" paramName="caseAssignmentForm" paramProperty="reassignDivisionLiteral" > Reassign division </html:link>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap">
												<img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Select Program Unit
											</td>
											<td class="formDe">										
												<html:select name="caseAssignmentForm" property="programUnitId">	
													<html:optionsCollection name="caseAssignmentForm" property="programUnitList" value="programUnitId" label="programUnitName"/>													
												</html:select>											
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap">
												<img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Program Unit Assignment Date
											</td>														
											<td class="formDe">
												<script type="text/javascript">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</script>
                                                   <html:text name="caseAssignmentForm" property="programUnitAllocationDate" size="10" maxlength="10" styleId="puAssignmentDateText" />
												<A HREF="#" onClick="cal1.select(document.getElementById('puAssignmentDateText'),'puAnchor1','MM/dd/yyyy'); return false;" NAME="puAnchor1" ID="puAnchor1">
													<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
												</A>
											</td>
										</tr>
									</table>
									<!-- END DETAIL 1 TABLE -->
									<br>
									<!-- BEGIN DETAIL 2 TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr class="detailHead">
											<td width="1%"></td>
											<td>Name</td>
											<td>Staff Type</td>
											<td>Position Program Unit</td>
										</tr>
										<% int RecordCounter=0;
										   String bgcolor="";
										%>
					                    <logic:iterate id="staffIndex" name="caseAssignmentForm" property="supervisionStaff">
					  						<tr class= <% RecordCounter++; 
					  									  bgcolor = (RecordCounter % 2 == 1)?"normalRow":"alternateRow";
							                   			  out.print(bgcolor); %>>							                   			  
												<td>
													<input type="radio" name="supervisorPositionId" value=<bean:write name="staffIndex" property="staffPositionId" /> onclick="showButton();"/>
												</td>
												<logic:empty name="staffIndex" property="assignedName"><td></td></logic:empty>
												<logic:notEmpty name="staffIndex" property="assignedName">
													<td><bean:write name="staffIndex" property="assignedName.formattedName"/></td>
												</logic:notEmpty>
												<td><bean:write name="staffIndex" property="positionName"/></td>
												<td><bean:write name="staffIndex" property="programUnitName"/></td>
											</tr>
										</logic:iterate>
									</table>
									<!-- END DETAIL 2 TABLE -->
									<br>
									
									<!-- BEGIN BUTTON TABLE -->
									<table border="0">
										<tr align="center">
											<td>
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
											</td>
											<td id="Next">
												<html:submit property="submitAction" onclick="return validateProgramUnitAssignmentDate(this.form);">
													<bean:message key="button.allocatesuperviseetosupervisor.selectsupervisor.selectsupervisor" />
												</html:submit> 
											</td>	
											<td>
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
												</html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
									
								</td>
							</tr>
						</table>
						<!--  END BORDER TABLE BLUE -->
						<br>
					</td>
							</tr>
						</table>
						<!-- OUTSIDE TABLE -->
		</div>
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
