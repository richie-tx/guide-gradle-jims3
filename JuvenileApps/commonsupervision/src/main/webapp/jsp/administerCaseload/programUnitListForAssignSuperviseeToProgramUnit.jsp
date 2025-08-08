<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
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
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>CommonSupervision/administerCaseload/programUnitListForAssignSuperviseeToProgram.jsp</title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
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

		function validateAssignmentDate(theForm) {
			clearAllValArrays();
		    customValRequired("programUnitAllocationDate","<bean:message key='errors.required' arg0='Assignment Date'/>","");	    
		    var assignmentDateId = "puAssignmentDateText";
		    var assignmentDateErrorMessage = "Assignment Date is invalid.  Valid format is mm/dd/yyyy.";
		    addMMDDYYYYDateValidation(assignmentDateId, assignmentDateErrorMessage);
		    
		    var returnVal = validateCustomStrutsBasedJS(theForm);
		    if (returnVal) {
		    	returnVal = validateRadios(theForm, "programUnitId", "Select a program unit.");
		    }			
		    return returnVal;		
		}

		function checkForSingleResult() {
		    var rbs = document.getElementsByName("programUnitId");
			if (rbs.length == 1){
				rbs[0].checked = true;
				showButton();
			}	
		}
	</script>

</head>

<body topmargin=0 leftmargin="0" onload="hideButton(); checkForSingleResult();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
	<html:form action="/selectprogramunitforprogramunitassignment" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|9">
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
									<!--blue tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
									<!--blue tabs end-->
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
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header" >
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
												Assign Supervisee to Program Unit - Select Program Unit
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
													<li>Expand Division and select a program unit.</li>
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
									<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class=detailHead>
											<td>Program Unit Assignment Date</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" width=1% nowrap>
															<img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Assignment Date
														</td>														
														<td class=formDe>
															<script type="text/javascript">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</script>
		                                                    <html:text name="caseAssignmentForm" property="programUnitAllocationDate" size="10" maxlength="10" styleId="puAssignmentDateText" />
															<A HREF="#" onClick="cal1.select(document.getElementById('puAssignmentDateText'),'puAnchor1','MM/dd/yyyy'); return false;" NAME="puAnchor1" ID="puAnchor1">
																<img border=0 src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
															</A>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr class=detailHead>
											<td>Divisions/Program Units</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
												<% int RecordCounter=0;
												   String bgcolor="";
												%>
												<logic:notEmpty name="caseAssignmentForm" property="divisionList">
												<logic:iterate id="divisionIter" name="caseAssignmentForm" property="divisionList">
													<tr class= <% RecordCounter++; 
										              bgcolor = "alternateRow";                      
										              if (RecordCounter % 2 == 1)
										                  bgcolor = "normalRow";
									                   out.print(bgcolor);%>>
														<td width=1%>
															<a href="javascript:showHide('img<bean:write name="divisionIter" 
																	property="organizationId"/>', '','/<msp:webapp/>')" 
																border=0><img src="/<msp:webapp/>images/expand.gif" 
																			name="img<bean:write name="divisionIter" property="organizationId"/>" 
																			border="0" >
															</a>
														</td>
														<td class=boldText><bean:write name="divisionIter" property="description"/></td>
													</tr>
													<tr>
														<td colspan=2>
															<span id="img<bean:write name="divisionIter" property="organizationId"/>Span" class="hidden">
																<table width="100%" cellpadding="2" cellspacing="1">
																	<logic:notEmpty name="divisionIter" property="children">	
																	<logic:iterate id="programUnitIter" name="divisionIter" property="children">
																	<tr class=<%=bgcolor%>>
																		<td width=1%>
																			<input type="radio" name="programUnitId" value=<bean:write name="programUnitIter" property="organizationId" /> onclick="showButton();"/>
																		</td>
																		<td>
																			<bean:write name="programUnitIter" property="description"/>
																		</td>
																	</tr>
																	</logic:iterate>
																	</logic:notEmpty>
																</table>
															</span>
														</td>
													</tr>
												</logic:iterate>
												</logic:notEmpty>
												</table>
											</td>
										</tr>
									</table>
									<!-- END DETAIL TABLE -->
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
												<html:submit property="submitAction" onclick="return validateAssignmentDate(this.form);" > 
													<bean:message key="button.assignsuperviseetoprogramunit.selectprogramunit.programUnitSelection" />
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
