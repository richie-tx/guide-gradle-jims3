<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.UIConstants"%>

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
	<title><bean:message key="title.heading" /> - assignSupervisee/supervisorSelectList.jsp</title>

	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/case_util.js" ></script>
		
	<script type="text/javascript">
		function hideButton(){
			show("Next", 0);
		}
		
		function showButton(){
			show("Next", 1);
		}
	</script>
</head>

<body topmargin="0" leftmargin="0" onload="hideButton();">

	
		<html:form action="/selectofficerforofficerassignment" target="content">
		  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|23">
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
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
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
											<td align="center" class="header" >Allocate Supervisee to Supervisor - Select Supervisor</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									
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
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding=4 cellspacing=1 class=borderTableBlue>
										<tr>
											<td class=formDeLabel width=1% nowrap>
												Division
											</td>
											<td class=formDe>
<%--
 												<a href="programUnitList.htm?superviseeFName=
													<bean:write name="caseAssignmentForm" property="relationship"/>
													&superviseeLName=
													<bean:write name="caseAssignmentForm" property="relationship"/>
													"
												>

												<html:link forward="programUnitList" > Reassign division </html:link>
--%>
												<html:link action="programUnitListForReassignDivision" paramId="submitAction" paramName="caseAssignmentForm" paramProperty="reassignDivisionLiteral" > Reassign division </html:link>
<%--
											</a> 
--%>											
											</td>
										</tr>
										<tr>
											<td class=formDeLabel width=1% nowrap>
												Select Program Unit
											</td>
											<td class=formDe>
<%--										   		<nest:select property="associateIds" size="3" multiple="true" onchange="show('supervisorsList', 0)">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection  name="caseAssigmentForm" property="programUnitList" value="associateId" label="displayLabel" />
												</nest:select>--%>
												<html:submit property="submitAction">
													<bean:message key="button.viewSupervisors" />
												</html:submit>
											</td>
										</tr>
									</table>
									<br>
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
										<tr class="detailHead">
											<td width=1%></td>
											<td>Name</td>
											<td>Staff Type</td>
										</tr>
										<% int RecordCounter=0;
										   String bgcolor="";
										%>
					                    <logic:iterate id="staffIndex" name="caseAssignmentForm" property="supervisionStaff">
					  						<tr class= <% RecordCounter++; 
								              bgcolor = "alternateRow";                      
								              if (RecordCounter % 2 == 1)
								                  bgcolor = "normalRow";
							                   out.print(bgcolor); %>>
										<%--		<logic:equal name="staffIndex" property="" value="">--%>
													<td class="assistantSupervisorIndent">
														<input type=radio name="supervisorPositionId" value=<bean:write name="staffIndex" property="staffPositionId" /> onclick="showButton();"/>
													</td>
<%--												</logic:equal>--%>
												<td><bean:write name="staffIndex" property="supervisorName"/></td>
												<td><bean:write name="staffIndex" property="staffPositionType"/></td>
											</tr>
										</logic:iterate>
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
												<html:submit property="submitAction">
													<bean:message key="button.assignsuperviseetoofficer.selectofficer.selectofficer" />
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
