<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/11/2006  Hien Rodriguez  JIMS200027578 - Display blank on EventType when user doesn't select anything-->
<!-- 01/12/2006  Hien Rodriguez  JIMS200027504 - Add validation "periodValue" for numeric field -->
<!-- 01/13/2006  Hien Rodriguez  JIMS200027549 - Reload pre-selected group2 & group3 -->
<!-- 01/18/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 09/06/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for date fields -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 06/07/2007  C Shimek  	    - #39326 revised headings to remove extra spaces between words -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->
<!-- 07/28/2010  Debbie Williamson ER #55920 Add text counter  -->
<!-- 08/23/2010  D Williamson      #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - courtPolicyUpdate.jsp</title>

<logic:equal name="courtPolicyForm" property="action" value="update">
	<html:javascript formName="courtPolicyUpdate" />
</logic:equal>
<logic:notEqual name="courtPolicyForm" property="action" value="update">
 	<html:javascript formName="courtPolicyCreate" />
</logic:notEqual>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script>
<logic:iterate indexId="groupIterIndex" id="groupIter" name="courtPolicyForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	
	<logic:notEmpty name="groupIter" property="subgroups">
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		<logic:notEmpty name="groupIter2" property="subgroups">
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
		
		</logic:iterate>
		</logic:notEmpty>
	</logic:iterate>
	</logic:notEmpty>
</logic:iterate>

function eventTypeCheck(theSelect){
		var theForm = theSelect.form;
		var hasBlank = false;
		var selected = new Array();
		
		for (var i = 0; i < theSelect.options.length; i++) {
		if (theSelect.options[ i ].selected) {			
			selected.push(theSelect.options[ i ].value);
			if (theSelect.options[ i ].value == ""){
				hasBlank = true;
				}
			}
		}				
		if (!hasBlank){
			show("eventCount", 1, "row");
			checkEventCount(theForm.eventCount);
		}else {
			show("eventCount", 0, "row");
			show("periodRow", 0, "row");
		}
	}

function openPreviewWindow(theForm, path)
{
	
		if(theForm.courtPolicyLiteral.value == '' )
		{
	//		theForm.courtPolicyLiteral.focus();
			alert('Please enter value for Court Policy Literal');
			return false;			
		}
		else
		{
			var newWin = window.open(path, "newWin","width=400,height=350,border=no,menubar=no,status=no,resizable=yes, scrollbars=yes");
			theForm.target = "newWin";	
			return true;
		}
}

function checkTarget(theForm)
{
	theForm.target = "content";
	if(!validateCustomStrutsBasedJS(theForm)){
		return false;
	}
	<logic:equal name="courtPolicyForm" property="action" value="update">
		return validateCourtPolicyUpdate(theForm);
	</logic:equal>
	<logic:notEqual name="courtPolicyForm" property="action" value="update">
	 	return validateCourtPolicyCreate(theForm);
	</logic:notEqual>
}

function eventTypeCheck(theSelect){
		if(theSelect==null){
			theSelect=document.getElementsByName('selectedEventTypeIds')[0];
		}
		multiSelectFix(theSelect);
		var theForm = theSelect.form;
		var hasBlank = false;
		var somethingSelected=false;
		for (var i = 0; i < theSelect.options.length; i++) {
			if (theSelect.options[ i ].selected) {
				if(theSelect.options[i].value==""){
					hasBlank=true;
				}
				somethingSelected=true;
			}
		}
		if (!hasBlank && somethingSelected){
			show("eventCount", 1, "row");
			checkEventCount(theForm.eventCount);
		}else {
			var eventCountElem=document.getElementsByName('eventCount');
			if(eventCountElem!=null){
				eventCountElem[1].options[0].selected=true;
			}
			var periodValElem=document.getElementsByName('periodValue');
			if(periodValElem!=null){
				periodValElem[0].value="";
			}
			var periodElem=document.getElementsByName('periodId');
			if(periodElem!=null){
				periodElem[0].options[0].selected=true;
			}
			show("eventCount", 0, "row");
			show("periodRow", 0, "row");
		}
	}


function setFocus()
{
	var txtFld = document.getElementsByName("courtPolicyName");
	if (txtFld != null && txtFld.length > 0 )
	{
		txtFld[0].focus();
	} else {
		txtFld = document.getElementsByName("group1Id");
		if (txtFld != null && txtFld.length > 0 )
		{
			txtFld[0].focus();
		}	
	}
}
</script>

</head>

<bean:define id="courtPolicyTitle" value="title.CSCourtPolicy" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" value="prompt.group1" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" value="prompt.group3" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" value="title.CSCourtPolicy" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" value="prompt.group3" type="java.lang.String"/>

<body topmargin=0 leftmargin="0" onload="reloadGroup(document.forms[0], '<bean:write name="courtPolicyForm" property="group2Id"/>', '<bean:write name="courtPolicyForm" property="group3Id"/>'), setFocus()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayCourtPolicyCreateTasks" target="content" onsubmit="myTinyMCEFix()" >
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
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
					<td valign="top" align=center>
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>
								<td align="center" class="header">
									<logic:equal name="courtPolicyForm" property="action" value="copy">
										<bean:message key="prompt.copy" />
										<bean:message key="<%=courtPolicyTitle%>"/>
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|71">
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="create">
										<bean:message key="prompt.create" />
										<bean:message key="<%=courtPolicyTitle%>"/>										
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|52">
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="update">
										<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
											<bean:message key="prompt.update" />
											<bean:message key="<%=courtPolicyTitle%>"/>
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|59">
										</logic:notEqual>
										<logic:equal name="courtPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|60">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.inUse" />
											<bean:message key="<%=courtPolicyTitle%>"/>
										</logic:equal>
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul>
									<li>Enter the required fields </li>
									<li>Detail Dictionary: CTRL + Click to select the value and drag to the condition literal.</li>
									<li>Click Next.</li>
								</ul></td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellpadding=0 cellspacing=0>
							<tr>
								<td width=70% valign="top">
									<table width="100%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
										<tr>
											<td class="detailHead">
												<table width=100% cellpadding=0 cellspacing=0>
													<tr>
														<td class="detailHead"><bean:message key="<%=courtPolicyTitle%>"/></td>
														<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellspacing=1 cellpadding=2>
													<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.UPDATE%>">
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.4.diamond" /><bean:message key="prompt.name" /></td>
															<td class="formDe" colspan=3><html:text name="courtPolicyForm" property="courtPolicyName" size="50" maxlength="50" /></td>
														</tr>
													</logic:equal>
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="<%=courtGroup1Caption%>"/></td>
														<td colspan=3 class="formDe">
															<input type="hidden" name="selectedGroup1" value="<bean:write name="courtPolicyForm" property="group1Id"/>" >
															<logic:equal name="courtPolicyForm" property="action" value="create">
																<html:select property="group1Id" size="1" onchange="updateGroup2(this.form);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="groups" value="groupId" label="name" /> 
																</html:select>
															</logic:equal>
															<logic:notEqual name="courtPolicyForm" property="action" value="create">
																<html:select property="group1Id" size="1" onchange="updateGroup2(this.form);checkGroupChange(this, 1, courtPolicyForm);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="groups" value="groupId" label="name" /> 
																</html:select>
															</logic:notEqual>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=courtGroup2Caption%>"/></td>
														<td colspan=3 class="formDe">
															<input type="hidden" name="selectedGroup2" value="<bean:write name="courtPolicyForm" property="group2Id"/>" >
															<logic:equal name="courtPolicyForm" property="action" value="create">
																<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:equal>
															<logic:notEqual name="courtPolicyForm" property="action" value="create">
																
																<logic:empty name="courtPolicyForm" property="group2">
																	<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);checkGroupChange(this, 2, courtPolicyForm);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																	</html:select>
																</logic:empty>
																
																<logic:notEmpty name="courtPolicyForm" property="group2">
																	<html:select property="group2Id" size="1" onchange="updateGroup3(this.form);checkGroupChange(this, 2, courtPolicyForm);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="group2" value="groupId" label="name" /> 
																	</html:select>
																</logic:notEmpty>
															</logic:notEqual>
																		
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=courtGroup3Caption%>"/></td>
														<td colspan=3 class="formDe">
														<input type="hidden" name="selectedGroup3" value="<bean:write name="courtPolicyForm" property="group3Id"/>" >
															<logic:equal name="courtPolicyForm" property="action" value="create">
																<html:select property="group3Id" disabled="true">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:equal>
															
															<logic:notEqual name="courtPolicyForm" property="action" value="create">
																<logic:empty name="courtPolicyForm" property="group3">
																	<logic:empty name="courtPolicyForm" property="group2">
																		<html:select property="group3Id" disabled="true" onchange="checkGroupChange(this, 3, courtPolicyForm)">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</logic:empty>
																	<logic:notEmpty name="courtPolicyForm" property="group2">
																		<html:select property="group3Id" onchange="checkGroupChange(this, 3, courtPolicyForm)">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</logic:notEmpty>
																	
																		
																</logic:empty>
																
																<logic:notEmpty name="courtPolicyForm" property="group3">
																	<html:select property="group3Id" size="1" onchange="checkGroupChange(this, 3, courtPolicyForm)">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="group3" value="groupId" label="name" /> 
																	</html:select>
																</logic:notEmpty>
																
															</logic:notEqual>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">
															<bean:message key="prompt.4.diamond" />
															<bean:message key="prompt.standard" />
														</td>
														<td colspan=3 class="formDe">
															<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
															<html:radio name="courtPolicyForm" property="standard" value="true"  /><bean:message key="prompt.standard" /> 
															<html:radio name="courtPolicyForm" property="standard" value="false" /><bean:message key="prompt.nonstandard" />
															</logic:notEqual>
															<logic:equal name="courtPolicyForm" property="inUse" value="true">
																<html:hidden name="courtPolicyForm" property="standard"/> 
																<logic:equal name="courtPolicyForm" property="standard" value="true">
																Standard</logic:equal>
																<logic:notEqual name="courtPolicyForm" property="standard" value="true">
																Non Standard</logic:notEqual>
															</logic:equal>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap><bean:message key="prompt.policySource"/></td>
														<td colspan=3 class="formDe">		
														<logic:notEqual name="courtPolicyForm" property="inUse" value="true">																
															<html:radio name="courtPolicyForm" property="departmentPolicy" value="false">Court</html:radio>
															<html:radio name="courtPolicyForm" property="departmentPolicy" value="true">Department</html:radio>
														</logic:notEqual>
														<logic:equal name="courtPolicyForm" property="inUse" value="true">
														
															<logic:equal name="courtPolicyForm" property="departmentPolicy" value="true">
																<bean:message key="prompt.department"/>
															</logic:equal>
															<logic:notEqual name="courtPolicyForm" property="departmentPolicy" value="true">
																<bean:message key="prompt.court"/>
															</logic:notEqual>
														</logic:equal>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" valign="top" width="1%" nowrap>
															<bean:message key="prompt.4.diamond" /><bean:message key="<%=courtPolicyLiteralCaption%>"/></td>
															
															<logic:notEqual name="courtPolicyForm" property="inUse" value="true">	
															<style>
																.mceEditor{																				
																	width: "410";
																	height: "130";
																}
															</style>
															<td colspan=3 class="formDe">
																<html:textarea styleClass="mceEditor" name="courtPolicyForm" property="courtPolicyLiteral" rows="5" ondblclick="myReverseTinyMCEFix(this)"/>
																<script>
																	customValRequired('courtPolicyLiteral','Policy is required',null);
																	addDefinedTinyMCEFieldMask('courtPolicyLiteral','Policy  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
																	customValMinLength('courtPolicyLiteral','Policy  must be at least 10 characters',10);
																	customValMaxLength('courtPolicyLiteral','Policy  cannot be more than 1000 characters',2000);
																</script>
															</td>
															</logic:notEqual>
															<logic:equal name="courtPolicyForm" property="inUse" value="true">	
																<td colspan=3 class="formDe" align=left>
																<html:hidden name="courtPolicyForm" property="courtPolicyLiteral"/>
																<bean:write name="courtPolicyForm" property="courtPolicyLiteral" filter="false"/>
																</td>
															</logic:equal>
															<div style="text-align:center"><input type="submit" name="submitAction" value="Preview" onclick="return (myTinyMCEFix() && openPreviewWindow(this.form, '/<msp:webapp/>jsp/common/pleaseWait.html'))"></div>
														</td>
													</tr>
													<SCRIPT type="text/javascript" ID="js1">
														var cal1 = new CalendarPopup();
														cal1.showYearNavigation();
													</SCRIPT>
													<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="true">
															<tiles:put name="agencyCode" beanName="courtPolicyForm" beanProperty="agencyId"/>
													</tiles:insert>
													<tr>
														<td class="formDeLabel" nowrap>
															<bean:message key="prompt.4.diamond" /><bean:message key="prompt.effectiveDate" />
														</td>
														<td colspan=3 class="formDe">
														<logic:notEqual name="courtPolicyForm" property="inUse" value="true">	
															<html:text name="courtPolicyForm" property="effectiveDate" size="10" maxlength="10" />
																<A HREF="#" onClick="cal1.select(document.forms[0].effectiveDate,'anchor1','MM/dd/yyyy'); return false;"
																NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.4.calendar"/></A>
														</logic:notEqual>
														<logic:equal name="courtPolicyForm" property="inUse" value="true">	
															<html:hidden name="courtPolicyForm" property="effectiveDate"/>
															<bean:write name="courtPolicyForm" property="effectiveDate" />
														</logic:equal>
														</td>
													</tr>

													<logic:equal name="courtPolicyForm" property="action" value="update">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
													<td class="formDe" colspan=3>
														<html:text name="courtPolicyForm" property="inactiveDate" size="10" maxlength="10" />
														<A HREF="#" onClick="cal1.select(document.forms[0].inactiveDate,'anchor2','MM/dd/yyyy'); return false;"
														NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.4.calendar"/></A>
													</td>
												</tr>
											</logic:equal>
											
											<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<html:hidden property="clearEventTypes" value="true"/>
													<tr>
											
														<td class="formDeLabel" nowrap valign="top"><bean:message key="prompt.eventType"/></td>
														<td colspan=3 class="formDe">
															<html:select property="selectedEventTypeIds" multiple="true" size="10" onchange="eventTypeCheck(this)">
																<html:option value=""><bean:message key="select.multiple" /></html:option>
																<html:optionsCollection property="eventTypes" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
													<tr id=eventCount class=hidden>
														<td class="formDeLabel" nowrap>
															<bean:message key="prompt.4.diamond" />
															<bean:message key="prompt.eventCount" />
														</td>
														<td colspan=3 class="formDe">
															<html:select property="eventCount" onchange="checkEventCount(this);">
																<html:option value=""><bean:message key="select.generic"/></html:option>
																<html:option value="1">IMMEDIATE</html:option>
																<html:option value="2">2</html:option>
																<html:option value="3">3</html:option>
																<html:option value="4">4</html:option>
																<html:option value="5">5</html:option>
																<html:option value="6">6</html:option>
																<html:option value="7">7</html:option>
																<html:option value="8">8</html:option>
																<html:option value="9">9</html:option>
																<html:option value="10">10</html:option>
															</html:select>
														</td>
													</tr>
													<tr id="periodRow" class=hidden>
														<td class="formDeLabel" nowrap>
															<bean:message key="prompt.4.diamond" />
															<bean:message key="prompt.periodValue" />
														</td>
														<td class="formDe">
															<html:text name="courtPolicyForm" property="periodValue" size="4" maxlength="3"/>
														</td>
														<td class="formDeLabel" nowrap>
															<bean:message key="prompt.4.diamond" />
															<bean:message key="prompt.period" />
														</td>
														<td class="formDe">
															<html:select property="periodId" size="1">
																<html:optionsCollection property="periods" value="code" label="description" /> 
															</html:select>
														</td>
													</tr>
											  		<script>
														eventTypeCheck(null);
													</script>	
												</logic:notEqual>	
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
												<tr id="sc7" >
														<td class="formDeLabel" nowrap><bean:message key="prompt.eventType" /></td>
														<td class="formDe"><bean:write name="courtPolicyForm" property="selectedEventTypes" /></td>
													</tr>
													<tr id="sc8" >
														<td class="formDeLabel" nowrap><bean:message key="prompt.eventCount" /></td>
														<td class="formDe"><bean:write name="courtPolicyForm" property="eventCountDesc" /></td>
													</tr>
													<tr id="sc9">
														<td class="formDeLabel" nowrap><bean:message key="prompt.period" /></td>
														<td class="formDe">
																			<logic:notEmpty name="courtPolicyForm" property="periodValue">
																			<logic:notEqual name="courtPolicyForm" property="periodValue" value="0">
																			<bean:write name="courtPolicyForm" property="periodValue"/> <bean:write name="courtPolicyForm" property="period"/>
																			</logic:notEqual>
																			</logic:notEmpty>
																		</td>
													</tr>
												</logic:equal>
												
												
													<tr>
														<td class="formDeLabel" width="1%" valign="top"><bean:message key="prompt.notes"/><br>(Max Characters: 1000)</td>
														<td class="formDe" colspan=3>
															<html:textarea name="courtPolicyForm" property="notes" rows="3" style="width:100%" onmouseout="textLimit( this, 1000 )" onkeyup="textLimit( this, 1000 )"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>										
									</table>
									</td>
									<td width="1%" valign="top">&nbsp;</td>	
									<logic:notEqual name="courtPolicyForm" property="inUse" value="true">											
									<td width=30% valign="top">
									
										<!--detail dictionary start-->
										<tiles:insert page="../../../common/detailDictionary.jsp">
											<tiles:put name="ddbean" beanName="courtPolicyForm" /> 
											<tiles:put name="actionPath" value="/displayCourtPolicyCreateTasks"/>
											<tiles:put name="formName" value="courtPolicyForm"/>
											<tiles:put name="includedIn" value="courtPolicy"/>
											<tiles:put name="isSpecialCondition" value="false"/>
										</tiles:insert>	
										<!--detail dictionary end-->
									</td>
									</logic:notEqual>
								</tr>							
							</table>
							<!-- association should not show up during create -->
							<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<br>
							<table width=98% cellpadding=0 cellspacing=0>							
								<tr>
									<td>										
									<!-- Associations -->
									<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
										<tiles:put name="associatedConditions" beanName="courtPolicyForm" beanProperty="associatedConditions"/>
										<tiles:put name="conditionGroup2Caption" value="prompt.group2"/>
										<tiles:put name="conditionGroup3Caption" value="prompt.group3"/>
									</tiles:insert>										
									</td>
								</tr>
							</table>
							</logic:notEqual>									
									<!-- BEGIN BUTTON TABLE -->
						<br>
						<table border="0" width="100%">
							<tr>
								<td align="center">
								<input type="button" value="Back" name="return" onClick="history.go(-1)">
								
								<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
									<html:submit property="submitAction" onclick="return (myTinyMCEFix() && checkTarget(this.form) && validateEventType(this.form) && compareDate(this.form) && unhideGroups(this.form) && disableSubmit(this, this.form))"><bean:message key="button.next"/></html:submit>
								</logic:notEqual>
								<logic:equal name="courtPolicyForm" property="inUse" value="true">
									<html:submit property="submitAction" onclick="return (myTinyMCEFix() && checkTarget(this.form) && unhideGroups(this.form) && disableSubmit(this, this.form))"><bean:message key="button.next"/></html:submit>
								</logic:equal>
									<input type="reset" value="Reset" name="reset">

									<input type=button value='<bean:message key="button.cancel" />' 
									<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
										onclick="resetTarget(this.form);changeFormActionURL(this.form.name, '/<msp:webapp/>globalCancel.do', true);"
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
										onclick="resetTarget(this.form);changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);"
									</logic:notEqual>	
									>								
								</td>
							</tr>
						</table>
						
						<!-- END BUTTON TABLE -->																			
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END  TABLE -->

</div>
	<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
<html:hidden property="clearUpdateHidden" value="true"/>
</logic:notEqual>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
