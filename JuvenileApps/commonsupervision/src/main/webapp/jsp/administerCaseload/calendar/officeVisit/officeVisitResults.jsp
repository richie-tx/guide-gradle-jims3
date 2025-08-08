<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/10/2008	 A Widjaja	Create JSP -->
<!-- 03/24/2007  CShimek    corrected Back and Cancel buttons from performing validation, found testing defect 58068 -->
<!-- 04/02/2009  slin       #58458 various UI fixes -->
<!-- 06/25/2010  L Deen     #66169 change to tinyMCECustomInitCasenote.js-->
<!-- 08/19/2011  RCapestani #71051 added code to display and hide client address and employment information -->
<!-- 05/06/2013  R.Young   ER #75492 RapidSpellWeb file changesV3.2.0 for CS -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - csCalendar - officeVisitResults.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>		
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<%--html:javascript formName="officeVisitGenericEnterResults"/--%>
		
<script type="text/javascript">
  

function validateField(theForm){
    clearAllValArrays();
    trimCasenote('currentOfficeVisit.narrative');
    customValRequired("currentOfficeVisit.outcomeCd", "<bean:message key='errors.required' arg0='Outcome'/>","");
    customValRequired("currentOfficeVisit.narrative", "<bean:message key='errors.required' arg0='Narrative'/>","");
    addDefinedTinyMCEFieldMask("currentOfficeVisit.narrative","Narrative Text cannot have % or _ entries","");
    customValMinLength("currentOfficeVisit.narrative","<bean:message key='errors.minlength' arg0='Narrative' arg1='1'/>","1");
    customValMaxLength("currentOfficeVisit.narrative","<bean:message key='errors.maxlength' arg0='Narrative' arg1='3500'/>","7000");
    add12HrTimeValidation("currentOfficeVisit.startTime","Start Time is not in proper 12 hour format, ie 03:15","");
    add12HrTimeValidation("currentOfficeVisit.endTime","End Time is not in proper 12 hour format, ie 03:15","");  
    if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(document.getElementsByName('currentOfficeVisit.narrative')[0].value, 'Narrative is required')){
		return true;
	}else {
		return false;
	}
 }

function flipText( idOfTD, textToSet ){
	if (document.getElementById( idOfTD ).innerHTML.indexOf("Address") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Address")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Address" ;
			show("addressRow", 1, "row")
		}else {
			document.getElementById( idOfTD ).innerHTML = "View Address" ;
			show("addressRow", 0)
		}
	}else if (document.getElementById( idOfTD ).innerHTML.indexOf("Employment") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Employment")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Employment" ;
			show("employmentRow", 1, "row")
		}else 
		{
			document.getElementById( idOfTD ).innerHTML = "View Employment" ;
			show("employmentRow", 0)
		}
	}
}

</script>      
</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displayCSOVDetails" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|6">
	
		<bean:define id="userAgency" name="csCalendarOVForm" property="userAgency"/>
		<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="false">
	 	<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
	 	</tiles:insert>	
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
<!-- BEGIN SUPERVISION TABS TABLE -->				
					<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign="top">
								<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tab" value="caseloadTab"/>
								</tiles:insert>	
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
						</tr>
					</table>
<!-- END SUPERVISION TABS TABLE -->	
<!-- BEGIN BLUE BORDERS TABLE -->						
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">							
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td align=center>
								<!--header start-->
								<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
								<!--header end-->
							</td>
						</tr>
						<tr>
							<td valign="top" align="center">										
								<!--casefile tabs start-->
								<table width="98%" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
									<tr>
										<td valign="top">
											<!--tabs start-->
											<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
												<tiles:put name="tab" value="CalendarTab"/> 
											</tiles:insert>	
											<!--tabs end-->
										</td>
									</tr>
									<tr>
										<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
								</table>
<!-- END CASELOAD TABS TABLE -->
<!-- BEGIN GREEN BORDER TABLE -->										
								<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
									<tr>
										<td valign="top" align="center">
<!-- BEGIN PAGE HEADING TABLE -->
											<table width="100%">
												<tr>
													<td align="center" class="header">CSCD - Office Visit Results</td>
												</tr>
											</table>
<!-- END PAGE HEADING TABLE -->												
<!-- BEGIN INSTRUCTION TABLE -->
											<div class=instructions><ul><li>Enter required fields and click Next.</li></ul></div>												
											<div class=required><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;*All date fields must be in the format of mm/dd/yyyy.</div>
											<div class=spacer></div>													
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
											<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td><bean:message key="prompt.officeVisit" /> <bean:message key="prompt.info" /></td>																		
													<td align="right">
														<a href="javascript:flipText('vAddress', 'View Address')" id="vAddress">Hide Address</a> | <a href="javascript:flipText('vEmployment', 'View Employment')" id="vEmployment">Hide Employment</a> | 
														<a href="javascript:openWindow('/<msp:webapp/>displayComplianceConditions.do?submitAction=Link&superviseeId=<bean:write name='csCalendarOVForm' property='currentOfficeVisit.superviseeId'/>')" class=blackSubNav>View Conditions</a>
													</td>
												</tr>
												<tr>
													<td colspan="2">
<!-- BEGIN DETAIL INPUT TABLE  -->														
														<table width='100%' cellpadding="2" cellspacing="1">
															<tr>
																<td class="formDeLabel" nowrap>
																	<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">
																		<bean:message key="prompt.4.diamond"/>
																	</logic:equal>
																	<bean:message key="prompt.officeVisit" /> <bean:message key="prompt.date" />
																</td>
																<td class="formDe">
																	<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="reschedule">
																		<bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventDate" formatKey="date.format.mmddyyyy"/>
																	</logic:notEqual>
																						
																	<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">
																		<html:text property="currentOfficeVisit.eventDateStr" size="10"/>
																		<%-- Calendar popup javascript --%>
																			<script type="text/javascript" id="js1">
																					var cal1 = new CalendarPopup();
																					cal1.showYearNavigation();
																			</script>
																			<A HREF="#" onClick="cal1.select(document.forms[0]['currentOfficeVisit.eventDateStr'],'anchor1','MM/dd/yyyy'); return false;"
																					NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.4.calendar" /></A>
																	</logic:equal>
																</td>
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.eventName" /></td>
																	<td class="formDe">
																		<bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventName"/>
																	</td>	
																</logic:equal>
																<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="enterResults">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.eventName" /></td>
																	<td class="formDe">
																		<html:text property="currentOfficeVisit.eventName" size="25"/>
																	</td>
																</logic:notEqual>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.startTime" /></td>
																<td class="formDe">
																	<html:text name="csCalendarOVForm" size="6" maxlength="5" property="currentOfficeVisit.startTime"/>
																             <html:select name="csCalendarOVForm" property="currentOfficeVisit.AMPMId1">
																	           <html:option value="AM">AM</html:option>
																	           <html:option value="PM">PM</html:option>
																           </html:select>
																</td>
																<td class="formDeLabel"><bean:message key="prompt.endTime" /></td>
																<td class="formDe">
																	<html:text name="csCalendarOVForm" size="6" maxlength="5" property="currentOfficeVisit.endTime"/>
																             <html:select name="csCalendarOVForm" property="currentOfficeVisit.AMPMId2">
																	           <html:option value="AM">AM</html:option>
																	           <html:option value="PM">PM</html:option>
																           </html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel">Supervisee Phone</td>
																<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.superviseePhone.formattedPhoneNumber"/></td>
																<td class="formDeLabel">Alternate Phone</td>
																<td class="formDe">
																	<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">
																		<bean:write name="csCalendarOVForm" property="currentOfficeVisit.altPhone.formattedPhoneNumber"/>
																	</logic:equal>
																	<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="enterResults">
																		<html:text property="currentOfficeVisit.altPhone.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentOfficeVisit.altPhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentOfficeVisit.altPhone.fourDigit" size="4" maxlength="4"/>
																	</logic:notEqual>
																</td>
															</tr>
															<tr id="addressRow">
																<td class="formDeLabel"><bean:message key="prompt.address"/></td>
																<td colspan="3" class="formDe"><bean:write name="superviseeForm" property="superviseeStreetNumber"/> <bean:write name="superviseeForm" property="superviseeStreetName"/><logic:notEmpty  name="superviseeForm" property="superviseeStreetName"></logic:notEmpty> <logic:notEmpty  name="superviseeForm" property="superviseeApartmentNumber"> <bean:message key="prompt.aptSuite"/> <bean:write name="superviseeForm" property="superviseeApartmentNumber"/><br> </logic:notEmpty>
																	<bean:write name="superviseeForm" property="superviseeCity"/><logic:notEmpty  name="superviseeForm" property="superviseeCity">, </logic:notEmpty><bean:write name="superviseeForm" property="superviseeState"/> <bean:write name="superviseeForm" property="superviseeZipCode"/> <logic:notEmpty  name="superviseeForm" property="superviseeZipCode"><br> </logic:notEmpty>
																	<%-- 	 <a href=# disabled>Update Address</a> --%>
																</td>
															</tr>
															<tr id="employmentRow">
																<td class="formDeLabel"><bean:message key="prompt.employment"/></td>
																<td colspan="3" class="formDe"><bean:write name="superviseeForm" property="employerName"/><logic:notEmpty name="superviseeForm" property="employePhoneNum">, <bean:write name="superviseeForm" property="employePhoneNum"/></logic:notEmpty><logic:notEmpty name="superviseeForm" property="employerOccupation">, <bean:write name="superviseeForm" property="employerOccupation"/></logic:notEmpty> 
																	<%--     <a href=# disabled>Update Employment</a> --%>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" colspan="4">Purpose of <bean:message key="prompt.officeVisit" /></td>
															</tr>
															<tr class="formDe">
																<td colspan="4">
																	<bean:write name="csCalendarOVForm" property="currentOfficeVisit.purpose" filter="false"/>&nbsp;
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.outcome" /></td>
																<td class="formDe" colspan="3">
																	<html:select name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd">
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																		<html:optionsCollection name="csCalendarOVForm" property="filteredOVOutcomeList" value="supervisionCode" label="description"/>
																	</html:select>
																	
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" colspan="4"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.narrative" /></td>
															</tr>
															<style>
																.mceEditor{
																width: "100%";
																height: "150"
																}
															</style>
															<tr class="formDe">
																<td colspan="4" align="center">
																	<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="currentOfficeVisit.narrative"/>
																</td>
															</tr>
														</table>
<!-- END DETAIL INPUT TABLE  -->														
													</td>
												</tr>
											</table>
<!-- END DETAIL TABLE -->
<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateField(this.form))"><bean:message key="button.next" /></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
														<input type="hidden" name="buttonSelected" value="" >
													</td>
												</tr>
											</table>
<!-- END BUTTON TABLE -->
										</td>
									</tr>
								</table>
<!-- END GREEN BORDER TABLE -->								
								<br>
							</td>
						</tr>
					</table>
<!-- END BLUE BORDER TABLE -->					
					<br>
				</td>
			</tr>
		</table>
	</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>