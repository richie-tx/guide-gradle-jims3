<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
<title><bean:message key="title.heading" /> - adminStaff/supervisorSelectList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>
function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">
<html:form action="/displayPositionSummary" target="content">
<logic:equal name="adminStaffForm" property="action" value="create">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|2"> 
</logic:equal>
<logic:equal name="adminStaffForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|18"> 
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
						page="../common/commonSupervisionTabs.jsp" flush="true">
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
								page="../common/manageFeaturesTabs.jsp" flush="true">
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
									<td align="center" class="header"><bean:message
										key="title.CSCD" /> - 
										<logic:equal name="adminStaffForm" property="action" value="create">
										<bean:message key="prompt.create" /> 
										</logic:equal>
										<logic:equal name="adminStaffForm" property="action" value="update">
										<bean:message key="prompt.update" /> 
										</logic:equal> 
										<bean:message key="prompt.position" /> - <bean:message
										key="prompt.select" /> <bean:message key="prompt.supervisor" />
									</td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
							<!-- END ERROR TABLE --> 															<!-- BEGIN INSTRUCTION TABLE -->
															<table width="98%" border="0">
																<tr>
																	<td>
																		<ul>
																			<li>Select a Supervisor/Asst Supervisor and click Next.</li>
																		</ul>
																	</td>
																</tr>
															</table>
															<!-- END INSTRUCTION TABLE -->
															<!-- BEGIN pos info TABLE -->
															<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
																<tr>
																	<td class="detailHead">
																		<table width="100%" cellpadding="2" cellspacing="0">
																			<tr>
																				<td width="1%"><a href="javascript:showHide('spn', 'row','/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="spn"></a></td>
																				<td class="detailHead"><bean:message key="prompt.position"/> <bean:message key="prompt.information"/></td>
																				<td align="right"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr id="spnSpan" class="hidden">
																	<td>
																		<tiles:insert page="positionInfoTile.jsp" flush="true">
																			<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
																		</tiles:insert>
																	</td>
																</tr>
															</table>
															<!--end pos info table-->
															<br>
															<!-- BEGIN supervisor list TABLE -->
															<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
															<tr class="detailHead">
																<td title="Position Type"><bean:message key="prompt.pos" /> <bean:message key="prompt.type" /></td>
																<td><bean:message key="prompt.name" /></td>
																<td title="Probation Officer Indicator"><bean:message key="prompt.cjad" /></td>
																<td title="Probation Officer Indicator"><bean:message key="prompt.poi" /></td>
																<td title="Position Name"><bean:message key="prompt.positionName" /></td>
																<td><bean:message key="prompt.jobTitle" /></td>
																<td title="Program Unit"><bean:message key="prompt.pu" /></td>
															</tr>
															<bean:size id="totalResults" name="adminStaffForm" property="availableSupervisors"/>
															<logic:equal name="totalResults" value="0">
															<tr>
																<td colspan="7"> No Supervisors Available</td>
															
															</tr>
															</logic:equal>
															
															<%int RecordCounter = 0;
															String bgcolor = "";%>  
															<nest:iterate name="adminStaffForm" property="availableSupervisors">
																<nest:notEmpty property="positionId">
																<nest:notEqual property="positionId" value="">
																
																<tr class='<%RecordCounter++;
																	bgcolor = "alternateRow";
																	if (RecordCounter % 2 == 1)
																		bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																		
																	<td>
																	<bean:define id="mySupPosId"><nest:write property="positionId"/></bean:define>
																	<html:radio property="selectedValue" value='<%=mySupPosId %>'/><nest:write property="positionTypeDesc"/></td>
																	
																	<td>
																		<nest:notEqual property="vacant" value="true">
																			<nest:notEmpty property="user">
																				<nest:notEmpty property="user.userName">
																					<nest:write property="user.userName.formattedName"/>
																				</nest:notEmpty>
																			</nest:notEmpty>
																		</nest:notEqual>
																		<nest:equal property="vacant" value="true">
																			<bean:message key="prompt.noOfficerAssigned"/>
																		</nest:equal>
																	</td>
																	<td>
																		<nest:notEmpty property="user">
																			<nest:write property="user.cjad"/>
																		</nest:notEmpty>
																	</td>
																	<td class="boldText"><nest:write property="probOfficerInd"/></td>
																	<td class="boldText"><nest:write property="positionName"/></td>
																	<td><nest:write property="jobTitleDesc"/></td>
																	<td><nest:write property="programUnitDesc"/></td>
																</tr>
																<nest:notEmpty property="subordinates">
																<nest:iterate property="subordinates">
																	<nest:notEmpty property="positionId">
																<nest:notEqual property="positionId" value="">
																	<tr class='<%RecordCounter++;
																		bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																	<td class="supervisorIndent">
																	<bean:define id="mySupPosId2"><nest:write property="positionId"/></bean:define>
																	<html:radio property="selectedValue" value='<%=mySupPosId2 %>'/><nest:write property="positionTypeDesc"/>
																	</td>
																	<td>
																			<nest:notEqual property="vacant" value="true">
																				<nest:notEmpty property="user">
																					<nest:notEmpty property="user.userName">
																						<nest:write property="user.userName.formattedName"/>
																					</nest:notEmpty>
																				</nest:notEmpty>
																			</nest:notEqual>
																			<nest:equal property="vacant" value="true">
																				<bean:message key="prompt.noOfficerAssigned"/>
																			</nest:equal>
																		</td>
																		<td>
																			<nest:notEmpty property="user">
																				<nest:write property="user.cjad"/>
																			</nest:notEmpty>
																		</td>
																		<td class="boldText"><nest:write property="probOfficerInd"/></td>
																		<td class="boldText"><nest:write property="positionName"/></td>
																		<td><nest:write property="jobTitleDesc"/></td>
																		<td><nest:write property="programUnitDesc"/></td>
																	</tr>
																	<nest:notEmpty property="subordinates">
																	<nest:iterate property="subordinates">
																	<nest:notEmpty property="positionId">
																<nest:notEqual property="positionId" value="">
																		<tr class='<%RecordCounter++;
																			bgcolor = "alternateRow";
																			if (RecordCounter % 2 == 1)
																				bgcolor = "normalRow";
																			out.print(bgcolor);%>'>
																		
																			<td class="assistantSupervisorIndent">
																			<bean:define id="mySupPosId3"><nest:write property="positionId"/></bean:define>
																	<html:radio property="selectedValue" value='<%=mySupPosId3 %>'/><nest:write property="positionTypeDesc"/>
																			</td>
																			<td>
																				<nest:notEqual property="vacant" value="true">
																					<nest:notEmpty property="user">
																						<nest:notEmpty property="user.userName">
																							<nest:write property="user.userName.formattedName"/>
																						</nest:notEmpty>
																					</nest:notEmpty>
																				</nest:notEqual>
																				<nest:equal property="vacant" value="true">
																					<bean:message key="prompt.noOfficerAssigned"/>
																				</nest:equal>
																			</td>
																			<td>
																				<nest:notEmpty property="user">
																					<nest:write property="user.cjad"/>
																				</nest:notEmpty>
																			</td>
																			<td class="boldText"><nest:write property="probOfficerInd"/></td>
																			<td class="boldText"><nest:write property="positionName"/></td>
																			<td><nest:write property="jobTitleDesc"/></td>
																			<td><nest:write property="programUnitDesc"/></td>
																		</tr>
																		<nest:notEmpty property="subordinates">
																<nest:iterate property="subordinates">
																<nest:notEmpty property="positionId">
																<nest:notEqual property="positionId" value="">
																	<tr class='<%RecordCounter++;
																		bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																	<td class="officerIndent"><input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>)' value='<nest:write property="positionId"/>'><nest:write property="positionTypeDesc"/></td>
																		<td>
																			<nest:notEqual property="vacant" value="true">
																				<nest:notEmpty property="user">
																					<nest:notEmpty property="user.userName">
																						<nest:write property="user.userName.formattedName"/>
																					</nest:notEmpty>
																				</nest:notEmpty>
																			</nest:notEqual>
																			<nest:equal property="vacant" value="true">
																				<bean:message key="prompt.noOfficerAssigned"/>
																			</nest:equal>
																		</td>
																		<td>
																			<nest:notEmpty property="user">
																				<nest:write property="user.cjad"/>
																			</nest:notEmpty>
																		</td>
																		<td class="boldText"><nest:write property="probOfficerInd"/></td>
																		<td class="boldText"><nest:write property="positionName"/></td>
																		<td><nest:write property="jobTitleDesc"/></td>
																		<td><nest:write property="programUnitDesc"/></td>
																	</tr>
																		</nest:notEqual>
																
																</nest:notEmpty>
																	</nest:iterate>
																	</nest:notEmpty>
																		</nest:notEqual>
																
																</nest:notEmpty>
																	</nest:iterate>
																	</nest:notEmpty>
																	</nest:notEqual>
																
																</nest:notEmpty>
																</nest:iterate>
																</nest:notEmpty>
																</nest:notEqual>
																
																</nest:notEmpty>
															</nest:iterate>
															
														</table>
															
															<br>
															<!-- BEGIN BUTTON TABLE -->
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'A supervisor must be selected.', 'There must be a supervisor to choose from in order to continue.') && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>
																		<input type="reset" value="Reset">
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
