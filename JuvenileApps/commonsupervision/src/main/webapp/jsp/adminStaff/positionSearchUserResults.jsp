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
<title><bean:message key="title.heading" /> - adminStaff/positionSearchUserResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">
<html:form action="/displayPositionAssignCasenote" target="content">
<logic:equal name="adminStaffForm" property="action" value="assign">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|24"> 
</logic:equal>
<logic:equal name="adminStaffForm" property="action" value="reassign">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|13"> 
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
											<logic:equal name="adminStaffForm" property="action" value="assign">
											Assign 
											</logic:equal>
											<logic:equal name="adminStaffForm" property="action" value="reassign">
											Reassign 
											</logic:equal>
											<bean:message key="prompt.position" /> -
											<bean:message key="prompt.user" /> <bean:message key="prompt.search" /> <bean:message key="prompt.results" />
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
														<!-- BEGIN INSTRUCTION TABLE -->
														<table width="98%" cellpadding="0" cellspacing="0">
															<tr>
																<td>
																	<ul>
																		<li>Select a user and click Select User.</li>
																	</ul>
																</td>
															</tr>
														</table>
														<!-- END INSTRUCTION TABLE -->
														<!--position header start-->
														<table width="98%" cellpadding="0" cellspacing="0">
															<tr class="paddedFourPix">
																<td class="formDeLabel"><bean:message key="prompt.position" /> <bean:message key="prompt.information" /></td>
																<td align="right" class="formDeLabel">&nbsp;</td>
															</tr>
															<tr>
																<td bgcolor="#cccccc" colspan="2">
																	<tiles:insert page="positionInfoHeaderTile.jsp" flush="true">
																			<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
																		</tiles:insert>
																		</td>
																	</tr>
																</table>
														<!--position header end-->
														<br>
														<table width="98%" border="0">
															<tr>
																<td align="center"> <b><bean:size id="searchSize" name="adminStaffSearchForm" property="foundUsers"/><%=searchSize %> </b> search results for 
																<bean:write name="adminStaffSearchForm" property="userSearchString" filter="false"/>
																 </td>
															</tr>
														</table>
														<!-- BEGIN supervisor list TABLE -->
															<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
															<tr class="detailHead">
																<td title="Position Type"><bean:message key="prompt.pos" /> <bean:message key="prompt.type" /></td>
																<td><bean:message key="prompt.name" /></td>
																<td><bean:message key="prompt.cjad" /></td>
																<td><bean:message key="prompt.cstsOfficerType" /></td>
																<td title="Probation Officer Indicator"><bean:message key="prompt.poi" /></td>
																<td title="Position Name"><bean:message key="prompt.positionName" /></td>
																<td><bean:message key="prompt.jobTitle" /></td>
																<td title="Program Unit"><bean:message key="prompt.pu" /></td>
															</tr>
															<bean:size id="totalResults" name="adminStaffSearchForm" property="foundUsers"/>
															<logic:equal name="totalResults" value="0">
															<tr>
																<td colspan="8"> No Users Available</td>
															
															</tr>
															</logic:equal>
															
															<%int RecordCounter = 0;
															String bgcolor = "";%>  
															<nest:iterate name="adminStaffSearchForm" property="foundUsers">
																
																
																<tr class='<%RecordCounter++;
																	bgcolor = "alternateRow";
																	if (RecordCounter % 2 == 1)
																		bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																		
																	<td>&nbsp;
																	<nest:notEmpty property="user">
																			<nest:notEqual property="user.userId" value="">
																			<input type="radio" name="selectedValue" value='<nest:write property="user.userId"/>' onclick="show('selUserBtn',1,'row')"><nest:write property="positionTypeDesc"/>
																		</nest:notEqual>
																	</nest:notEmpty>
																	</td>
																	<td>
																		<nest:notEmpty property="user">
																			<nest:notEmpty property="user.userName">
																				<nest:write property="user.userName.formattedName"/>
																			</nest:notEmpty>
																		</nest:notEmpty>
																	</td>
																	<td>
																		<nest:notEmpty property="user">
																			<nest:write property="user.cjad"/>
																		</nest:notEmpty>
																	</td>
																	<td><nest:write property="officerTypeDesc"/></td>
																	<td class="boldText"><nest:write property="probOfficerInd"/></td>
																	<td class="boldText"><nest:write property="positionName"/></td>
																	<td><nest:write property="jobTitleDesc"/></td>
																	<td><nest:write property="programUnitDesc"/></td>
																</tr>
																<nest:notEmpty property="subordinates">
																<nest:iterate property="subordinates">
																
																	<tr class='<%RecordCounter++;
																		bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																	<td class="supervisorIndent">
																	&nbsp;
																	<nest:notEmpty property="user">
																			<nest:notEqual property="user.userId" value="">
																			<input type="radio" name="selectedValue" value='<nest:write property="user.userId"/>' onclick="show('selUserBtn',1,'row')"><nest:write property="positionTypeDesc"/>
																		</nest:notEqual>
																	</nest:notEmpty>
																	</td>
																		<td>
																			<nest:notEmpty property="user">
																				<nest:notEmpty property="user.userName">
																					<nest:write property="user.userName.formattedName"/>
																				</nest:notEmpty>
																			</nest:notEmpty>
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
																	
																		<tr class='<%RecordCounter++;
																			bgcolor = "alternateRow";
																			if (RecordCounter % 2 == 1)
																				bgcolor = "normalRow";
																			out.print(bgcolor);%>'>
																		
																			<td class="assistantSupervisorIndent">
																			&nbsp;
																	<nest:notEmpty property="user">
																			<nest:notEqual property="user.userId" value="">
																			<input type="radio" name="selectedValue" value='<nest:write property="user.userId"/>' onclick="show('selUserBtn',1,'row')"><nest:write property="positionTypeDesc"/>
																		</nest:notEqual>
																	</nest:notEmpty>
																			</td>
																
																				<td>
																				<nest:notEmpty property="user">
																					<nest:notEmpty property="user.userName">
																						<nest:write property="user.userName.formattedName"/>
																					</nest:notEmpty>
																				</nest:notEmpty>
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
																
																	<tr class='<%RecordCounter++;
																		bgcolor = "alternateRow";
																		if (RecordCounter % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>'>
																	<td class="officerIndent">
																	
																	<input type="radio" name="selectedValue" onclick='renderButtons(<nest:write property="retired"/>,<nest:write property="vacant"/>)' value='<nest:write property="positionId"/>'><nest:write property="positionTypeDesc"/>
																	
																	</td>
																		<td>
																			<nest:notEmpty property="user">
																				<nest:notEmpty property="user.userName">
																					<nest:write property="user.userName.formattedName"/>
																				</nest:notEmpty>
																			</nest:notEmpty>
																		</td>
																		<td>
																			<nest:notEmpty property="user">
																				<nest:write property="user.cjad"/>
																			</nest:notEmpty>
																		</td>
																		<td><nest:write property="officerTypeDesc"/></td>
																		<td class="boldText"><nest:write property="probOfficerInd"/></td>
																		<td class="boldText"><nest:write property="positionName"/></td>
																		<td><nest:write property="jobTitleDesc"/></td>
																		<td><nest:write property="programUnitDesc"/></td>
																	</tr>
																	
																	</nest:iterate>
																	</nest:notEmpty>
																		
																	</nest:iterate>
																	</nest:notEmpty>
																	
																</nest:iterate>
																</nest:notEmpty>
																
															</nest:iterate>
															
														</table>
														<!-- END Results TABLE -->
														<div class="legendSmallText" style="width:98%; text-align:left"><span class="inactivePOI">Italic POI codes</span> signify they are retired.</div>
														<div class="spacer"></div>
														<!-- BEGIN BUTTON TABLE -->
														<table border="0" width="100%" cellpadding="1" cellspacing="1">
															<tr id="selUserBtn" class="hidden">
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.selectUser" /></html:submit>
																</td>
															</tr>
															<tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																	<input type="reset" value="Reset" onclick="show('selUserBtn',0,'row')">
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
