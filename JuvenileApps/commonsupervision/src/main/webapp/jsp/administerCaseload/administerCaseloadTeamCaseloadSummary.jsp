<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--02/28/2008	LDeen	Activity #49819 integrate help    --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="messaging.administercaseload.domintf.ICaseAssignment"%>
<%@page import="messaging.administercaseload.to.CaseloadSummaryTO"%>

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
	<title>CommonSupervision/administerCaseload/administerCaseloadTeamCaseloadSummary.jsp</title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script language="JavaScript" src="/<msp:webapp/>js/case_util.js"></script>
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

		function validateOfficerAssignmentDate(theForm) {
			clearAllValArrays();
		    customValRequired("officerAssignmentDate","<bean:message key='errors.required' arg0='Position Assignment Date'/>","");	    
		    var assignmentDateId = "officerAssignmentDateId";
		    var assignmentDateErrorMessage = "Position Assignment Date is invalid.  Valid format is mm/dd/yyyy.";
		    addMMDDYYYYDateValidation(assignmentDateId, assignmentDateErrorMessage);
		    
		    var returnVal = validateCustomStrutsBasedJS(theForm);
		    if (returnVal) {
		    	returnVal = validateRadios(theForm, "officerPositionId", "Select an Officer.");
		    }			
		    return returnVal;		
		}

		function checkForSingleResult() {
		    var rbs = document.getElementsByName("officerPositionId");
			if (rbs.length == 1){
				rbs[0].checked = true;
				showButton();
			}	
		}
		
	</script>
	
</head>
<body topmargin="0" leftmargin="0" onload="hideButton(); checkForSingleResult();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
	<html:form action="/reassignSuperviseeToOfficerAction" target="content">
	  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|27">
	
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--blue tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--blue tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
										
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;
												Reassign Supervisee to Officer - <bean:message key="prompt.caseload"/> <bean:message key="title.summary"/>
											</td>
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
													<li>Review caseload summary and click Next.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
										
									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									<br>
										
									<!-- BEGIN DETAIL TABLE -->
									<span id="supervisorSelect">
										<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap">
													Program Unit
												</td>
												<td class="formDe">
												<!-- 
													<html:select name="caseAssignmentForm" property="programUnitId">
															<html:optionsCollection name="caseAssignmentForm" property="programUnitList" value="OID" label="description"/>													
													</html:select>
													
													<html:submit property="submitAction">
														<bean:message key="button.getSupervisors" />
													</html:submit>
												  -->
												  	<bean:write name="caseAssignmentForm" property="programUnitName"/>
												</td>
											</tr>
											<tr id="supervisorRow">
												<td class="formDeLabel" width="1%" nowrap="nowrap">
													Select Supervisor
												</td>
												<td class="formDe">												
													<html:select name="caseAssignmentForm" property="supervisorPositionId">														
														<html:optionsCollection name="caseAssignmentForm" property="supervisionStaff" value="staffPositionId" label="assignedName.formattedName"/>													
													</html:select>											
													<html:submit property="submitAction">
														<bean:message key="button.viewCaseloadSummary" />
													</html:submit>
												</td>
											</tr>
										</table>
										<br>
									</span>
									
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Caseload Summary for Supervisor <bean:write name="caseAssignmentForm" property="supervisorName.formattedName"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border="0">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td>POI</td>
														<td>Position Name</td>
														<td>CJAD</td>
														<td>Officer Name</td>
														<td align="center">LOS0</td>
														<td align="center">LOS1</td>
														<td align="center">LOS2</td>
														<td align="center">LOS3</td>
														<td align="center">LOS4</td>
														<td align="center">IND</td>
														<td align="center">Workload</td>
													</tr> 

													<bean:define id="defendantActiveCases" name="caseAssignmentForm" property="activeCases" type="java.util.List" />
													<% ArrayList casesAssignedToStaffPositions = new ArrayList();
														for (Iterator iterator = defendantActiveCases.iterator(); iterator.hasNext();) {
															ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
															String assignedStaffPostionId = caseAssignment.getAssignedStaffPositionId();
															casesAssignedToStaffPositions.add(assignedStaffPostionId);
														}
													 %>
													
												    <logic:iterate id="caseloadIndex" name="caseAssignmentForm" property="caseloads">
							  						<tr class= <% String currentOfficerId = ((CaseloadSummaryTO)caseloadIndex).getOfficerPositionId();
							  									  if (casesAssignedToStaffPositions.contains(currentOfficerId)) {
							  									     out.print("caseloadCurrentlyAssigned");
							  									  } else {
							  									     out.print("caseloadSummaryHighlight");
							  									  }
							  									%> >
															<td >
																<input type="radio" name="officerPositionId" value=<bean:write name="caseloadIndex" property="officerPositionId" /> onclick="showButton();"/>
															</td>
															<td><bean:write name="caseloadIndex" property="probationOfficerInd"/></td>
															<td><bean:write name="caseloadIndex" property="positionName"/></td>
															<td><bean:write name="caseloadIndex" property="cjad"/></td>
															<td><bean:write name="caseloadIndex" property="officerName"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los0" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los1" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los2" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los3" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los4" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="ind" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="workload" formatKey="number.format.double"/></td>	
														</tr>
														<tr>
															<td colspan="5" align="right"><bean:message key="prompt.numberOfSpns" /></td>
															<%-- <td align="center"><bean:write name="caseloadIndex" property="countTotal"/></td> --%>
															<td align="center"><bean:write name="caseloadIndex" property="los0Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los1Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los2Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los3Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los4Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="indCount"/></td>
														</tr>
													</logic:iterate>												
												</table>
											</td>
										</tr>
									</table>
									<table width="98%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="legendSmallText"><span class="caseloadCurrentlyAssigned">Green</span> caseload records indicate that the officer is currently assigned the supervisee.
											</td>
										</tr>
									</table>
									<!-- END DETAIL TABLE -->
									<br/>
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Position Assignment</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" nowrap="nowrap" width="1%"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Position Assignment Date </td>
														<td class="formDe" nowrap="nowrap" width="78%">
															<script type="text/javascript">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</script>
			                                                <html:text name="caseAssignmentForm" property="officerAssignmentDate" size="10" maxlength="10" styleId="officerAssignmentDateId" />
															<A HREF="#" onClick="cal1.select(document.getElementById('officerAssignmentDateId'),'puAnchor1','MM/dd/yyyy'); return false;" NAME="puAnchor1" ID="puAnchor1">
																<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
															</A>
														</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>								
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
												<html:submit property="submitAction" onclick="return validateOfficerAssignmentDate(this.form);">
													<bean:message key="button.next" />
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
						<br>
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div>	
</body>
</html:html>
