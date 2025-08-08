<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/28/09  RYoung    Created new jsp by removing tiles -->
<!-- 08/24/2010  D Williamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>
<%@page import="naming.Features"%>
<%@page import="ui.common.UIUtil"%>

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
<%@ page import="naming.PDCodeTableConstants" %>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - assignSupervisee/tasknoteCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script> 
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>

<script LANGUAGE="JavaScript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/addcasenoteforofficerreassignment" target="content">
  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|28">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--blue tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--blue tabs end--></td>
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

					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;																					
									Reassign Supervisee to Officer - Casenote
							</td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> 
					
					<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td>
							<ul>
								<li>Enter Casenote. Click Next</li>
							</ul>
							</td>
						</tr>
						<tr>
							<td class="required"><img src="/<msp:webapp/>images/required.gif"
								title="required" alt="required image" border="0" width="10" height="9">
							Required Field</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE -->
					
					<!--header start--> 
					<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
					</tiles:insert> 
					<!--header end-->
					<br>
					
					<!-- BEGIN DETAIL TABLE --> 
					
					   
						<!-- OFFICER ASSIGNMENT START -->
						<tiles:insert page="../../common/selectedOffToReassignSupToTile.jsp" flush="true">
						</tiles:insert>
						<!-- OFFICER ASSIGNMENT END -->
						 <br>
						<!-- PROGRAM UNIT ASSIGNMENT START -->
						<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>Program Referral Information</td>
							</tr>
							
							<tr>
								<td>
								<table width="100%" cellpadding="4" cellspacing="1" >
								
									<logic:equal name="caseAssignmentForm" property="programUnitRef" value="true">
									<tr>
										<td colspan="4" class="formDeLabel">Program Referral Closure Due To Program Unit Change - Mental Health Regular / Mental Health Regular</td>
									</tr>
							
									<tr>
										<td class="formDeLabel" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programEndDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="programEndDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].programEndDateAsStr,'anchor0','MM/dd/yyyy'); return false;" NAME="anchor0" ID="anchor0" border="0"><bean:message key="prompt.3.calendar"/></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForDischarge"/></td>
										<td class="formDe" colspan="3">
											<html:select size="1" name="caseAssignmentForm" property="reasonForDischargeId" >
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<jims2:codetable codeTableName="<%=PDCodeTableConstants.JIMS2_DISCHARGE_REASON%>" sort="true"></jims2:codetable>
											</html:select>
										</td>
									</tr>
									</logic:equal>	
									<tr class="formDeLabel">
										<td colspan="4" class="formDeLabel">New Program Referral Due To Program Unit Change - Domestic Violence / Domestic Violence Counseling</td>
									</tr>
							
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="referralDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].referralDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programBeginDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="programBeginDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].programBeginDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForPlacement"/></td>
										<td class="formDe" colspan="3">
											<html:select size="1" name="caseAssignmentForm" property="reasonForPlacementId" >
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<jims2:codetable codeTableName="<%=PDCodeTableConstants.REASON_FOR_PLACEMENT%>" sort="true"></jims2:codetable>
											</html:select>
										</td>
									</tr>
								</table>
								</td>
							</tr> 
						</table>
						<!-- PROGRAM UNIT ASSIGNMENT END -->
				
						 <br>
				    <!-- add casenote start-->
					<span id="addCN"> 
					<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
						<tr class="detailHead">
							<td>New Casenote Information</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><img
										src="/<msp:webapp/>images/required.gif" title="required"
										alt="required image" border="0" width="10" height="9">Casenote</td>
								</tr>
								<tr class="formDe">
									<td>
										<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,1000);"  ondblclick="myReverseTinyMCEFix(this)"
													styleClass="mceEditor" style="width:100%" rows="15" >
										</html:textarea> 
											<bean:define id="userAgency" name="caseAssignmentForm" property="agencyId"/>
													<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
													<tiles:put name="agencyCode"><%=userAgency%></tiles:put> 
										</tiles:insert>	
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<br>
					<!--add casenotes end--> 
					</span> 
					<!-- END DETAIL TABLE --> 
					<script>
					    customValRequired("casenoteText","Casenote Text is required","");
		                customValMaxLength("casenoteText","Casenote Text cannot be more than 3500 characters","3500");
		                addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
		            </script>
		            <!-- BEGIN BUTTON TABLE -->
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:submit property="submitAction">
									<bean:message key="button.back" />
								</html:submit> 
								<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form);">
									<bean:message key="button.allocatesuperviseetosupervisor.selectsupervisor.addCasenote" />
								</html:submit> 
								<html:reset property="submitAction">
									<bean:message key="button.reset" />
								</html:reset> 
								<html:submit property="submitAction">
									<bean:message key="button.cancel" />
								</html:submit>
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
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
