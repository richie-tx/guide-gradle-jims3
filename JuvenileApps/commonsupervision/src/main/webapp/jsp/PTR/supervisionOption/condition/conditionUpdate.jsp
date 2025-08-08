<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/10/2006  Hien Rodriguez  JIMS200027476 - add scrollbar=yes to window.open(); -->
<!-- 01/10/2006  Hien Rodriguez  JIMS200026255 - default value for Jurisdiction to "Harris County" -->
<!-- 03/03/2006  C Shimek  	   - ER#28542 add disable button to next button -->
<!-- 09/06/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for date fields -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->
<!-- 11/26/2007  Clarence Shimek - defect#47334 remove extra spaces in heading (merge removed this correction made under defedt39326) -->
<!-- 08/24/2010  D Williamson  - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

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
<title><bean:message key="title.heading" /> - conditionUpdate.jsp</title>

<logic:equal name="supervisionConditionForm" property="action" value="update">
		<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				<html:javascript formName="condJUVSpec"/>
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
					<html:javascript formName="condUpdateJUVNotSpec"/>
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				<html:javascript formName="condNonJUVSpec"/>
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
				<html:javascript formName="condUpdateNonJUVNotSpec"/>
			</logic:notEqual> 
		</logic:notEqual>
</logic:equal>
<logic:notEqual name="supervisionConditionForm" property="action" value="update">
	<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
		<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
			<html:javascript formName="condJUVSpec"/>
		</logic:equal>
		<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
				<html:javascript formName="condCreateJUVNotSpec"/>
		</logic:notEqual>	
	</logic:equal>
	<logic:notEqual name="supervisionConditionForm" property="agencyJUV" value="true">
		<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
			<html:javascript formName="condNonJUVSpec"/>
		</logic:equal>
		<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
			<html:javascript formName="condCreateNonJUVNotSpec"/>
		</logic:notEqual>
	</logic:notEqual>
</logic:notEqual>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>

<script>
<logic:iterate indexId="groupIterIndex" id="groupIter" name="supervisionConditionForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	
	
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;
		
		</logic:iterate>
	</logic:iterate>
</logic:iterate>


	
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
	
function openPreviewWindow(theForm, path)
{
	 	myTinyMCEFix();
		if(theForm.conditionLiteral.value == '' )
		{
			// 	theForm.conditionLiteral.focus();
			
            alert('Please enter value for Condition Literal');
            return false;
			
		}
		else
		{
			var newWin = window.open(path, "newWin","width=400,height=350,border=no,menubar=no,status=no,resizable=yes, scrollbars=yes");
			theForm.target = "newWin";
			return true;
		}
}

function checkSupervisionTypes(){
	var supType=document.getElementsByName('selSupervisionTypes');
	if(supType!=null){
		if(supType[0].value==""){
			alert("Supervision Type is a required field");
			return false;	
		}
	}
	return true;
}

function checkTarget(theForm)
{
	theForm.target = "content";
	if(!validateCustomStrutsBasedJS(theForm)){
		return false;
	}
	<logic:equal name="supervisionConditionForm" property="action" value="update">
		<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				return (checkSupervisionTypes() && validateCondJUVSpec(theForm));
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
					return (checkSupervisionTypes() && validateCondUpdateJUVNotSpec(theForm));
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				return validateCondNonJUVSpec(theForm);
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
				return validateCondUpdateNonJUVNotSpec(theForm);
			</logic:notEqual>
		</logic:notEqual>
	</logic:equal>
	<logic:notEqual name="supervisionConditionForm" property="action" value="update">
		<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				return (checkSupervisionTypes() && validateCondJUVSpec(theForm));
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
					return (checkSupervisionTypes() && validateCondCreateJUVNotSpec(theForm));
			</logic:notEqual>	
		</logic:equal>
		<logic:notEqual name="supervisionConditionForm" property="agencyJUV" value="true">
			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
				return  validateCondNonJUVSpec(theForm);
			</logic:equal>
			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
				return validateCondCreateNonJUVNotSpec(theForm);
			</logic:notEqual>
		</logic:notEqual>
	</logic:notEqual>
}

</script>

</head>

<bean:define id="conditionGroup1Caption" name="supervisionConditionForm" property="conditionGroup1Caption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="supervisionConditionForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="supervisionConditionForm" property="conditionGroup3Caption" type="java.lang.String"/>
<body topmargin=0 leftmargin="0" onload="reloadGroup(document.forms[0], '<bean:write name="supervisionConditionForm" property="group2Id"/>', '<bean:write name="supervisionConditionForm" property="group3Id"/>')"  onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionUpdateTasks" target="content" onsubmit="myTinyMCEFix()" focus="group1Id">
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
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>
								<td align="center" class="header">
									<logic:equal name="supervisionConditionForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|31">
										<bean:message key="prompt.copy" />
										<bean:message key="title.supervisionCondition" />										
									</logic:equal>
									
									<logic:equal name="supervisionConditionForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|7">
										<bean:message key="prompt.create" />
										<bean:message key="title.supervisionCondition" />
									</logic:equal>
									
									<logic:equal name="supervisionConditionForm" property="action" value="update">
										<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
											<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|14">
												<bean:message key="prompt.update" />
												<bean:message key="title.supervisionCondition" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="supervisionConditionForm" property="inUse" value="true">
											<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|15">
												<bean:message key="prompt.update" />
												<bean:message key="prompt.inUse" />
												<bean:message key="title.supervisionCondition" />
											</logic:notEqual>
										</logic:equal>
										<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|16">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.special" />
											<bean:message key="title.supervisionCondition" />
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
								<td>
								<ul>
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
										<tr class=detailHead>
											<td>
												<table width=100% cellpadding=0 cellspacing=0>
													<tr>
														<td class=detailHead><bean:message key="title.supervisionCondition" /></td>
														<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
													</tr>
												</table>
											</td>
										</tr>
																																				
										<tr>
											<td>
												<table width=100% cellpadding=2 cellspacing=1>
													<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.UPDATE%>">
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.name" /></td>
																<td colspan=3 class="formDe">
																	<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
																		<bean:write name="supervisionConditionForm" property="conditionName"/>
																	</logic:equal>
																	<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
																		<html:text name="supervisionConditionForm" property="conditionName" size="50" maxlength="50"/>
																	</logic:notEqual>
																</td>
															</tr>
													</logic:equal>
												    <tr>
														<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.4.diamond"/><bean:message key="<%=conditionGroup1Caption%>"/></td>
														<td colspan=3 class="formDe">
															<input type="hidden" name="selectedGroup1" value="<bean:write name="supervisionConditionForm" property="group1Id"/>" >
															<logic:equal name="supervisionConditionForm" property="action" value="create">
															        <html:select property="group1Id" size="1" onchange="updateGroup2(this.form);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="groups" value="groupId" label="name" /> 
																</html:select>
															</logic:equal>
															<logic:notEqual name="supervisionConditionForm" property="action" value="create">
																<html:select property="group1Id" size="1" onchange="updateGroup2(this.form);checkGroupChange(this, 1, supervisionConditionForm);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="groups" value="groupId" label="name" /> 
																</html:select>
															</logic:notEqual>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width=1% nowrap><bean:message key="<%=conditionGroup2Caption%>"/></td>
														<td colspan=3 class="formDe">
															<input type="hidden" name="selectedGroup2" value="<bean:write name="supervisionConditionForm" property="group2Id"/>" >
															<logic:equal name="supervisionConditionForm" property="action" value="create">
																<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:equal>
															
															<logic:notEqual name="supervisionConditionForm" property="action" value="create">
																<logic:empty name="supervisionConditionForm" property="group2">
																	<html:select property="group2Id" disabled="true" onchange="updateGroup3(this.form);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																	</html:select>
																</logic:empty>
																
																<logic:notEmpty name="supervisionConditionForm" property="group2">
																	<html:select property="group2Id" size="1" onchange="updateGroup3(this.form);checkGroupChange(this, 2, supervisionConditionForm);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="group2" value="groupId" label="name" /> 
																	</html:select>
																</logic:notEmpty>
															</logic:notEqual>
															
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width=1% nowrap><bean:message key="<%=conditionGroup3Caption%>"/></td>
														<td colspan=3 class="formDe">
															<logic:equal name="supervisionConditionForm" property="action" value="create">
																<html:select property="group3Id" disabled="true">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																</html:select>
															</logic:equal>
															
															<logic:notEqual name="supervisionConditionForm" property="action" value="create">
																<logic:empty name="supervisionConditionForm" property="group3">
																	<logic:empty name="supervisionConditionForm" property="group2">
																		<html:select property="group3Id" disabled="true">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</logic:empty>
																	<logic:notEmpty name="supervisionConditionForm" property="group2">
																		<html:select property="group3Id">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</logic:notEmpty>
																</logic:empty>
																
																<logic:notEmpty name="supervisionConditionForm" property="group3">
																	<html:select property="group3Id" size="1">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="group3" value="groupId" label="name" /> 
																	</html:select>
																</logic:notEmpty>
																
															</logic:notEqual>
														
														</td>
													</tr>
													
													<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
														<tr>
															<td class="formDeLabel">
																<bean:message key="prompt.4.diamond"/><bean:message key="prompt.standard" />
															</td>
															<td colspan=3 class="formDe">
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																<html:radio name="supervisionConditionForm" property="standard" value="true"/><bean:message key="prompt.standard" /> 
																<html:radio name="supervisionConditionForm" property="standard" value="false"/><bean:message key="prompt.nonstandard" />
																</logic:equal>
																<logic:equal name="supervisionConditionForm" property="inUse" value="true">
																	<input type="hidden" name="standard" value="<bean:write name="supervisionConditionForm" property="standard"/>" />
																	<logic:equal name="supervisionConditionForm" property="standard" value="false">
																		<bean:message key="prompt.nonstandard" />
																	</logic:equal>
																	<logic:equal name="supervisionConditionForm" property="standard" value="true">
																		<bean:message key="prompt.standard" />
																	</logic:equal>
																</logic:equal>
															</td>
														</tr>
													</logic:notEqual>
													
													<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
														<tr>
															<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.supervisionType" /></td>
															<td colspan=3 class="formDe">
																
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																	<html:select property="selSupervisionTypes" multiple="true" size="3" onchange="multiSelectFix(this);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="supervisionTypes" value="code" label="description" /> 
																	</html:select>	
																</logic:equal>
																<logic:equal name="supervisionConditionForm" property="standard" value="true">	
																<input type="hidden" name="selSupervisionTypes" value="<bean:write name="supervisionConditionForm" property="selSupervisionTypes"/>" />	
																	<bean:write name="supervisionConditionForm" property="dispSelSupTypes"/>
																</logic:equal>													
															</td>
														</tr>
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="inUse" value="true">
														<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
															<tr>
																<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="prompt.literal" /></td>
																<td colspan=3 class="formDe"><bean:write name="supervisionConditionForm" property="fixedLiteral"  filter="false"/></td>
															</tr>
														</logic:equal>
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
														<tr>
																<td class="formDeLabel" valign="top" width=1% nowrap>
																	<bean:message key="prompt.4.diamond"/><bean:message key="prompt.literal" /></td>
																<td colspan=3 class="formDe" >
															<%-- <input type="hidden" name="conditionLiteral" value="<bean:write name="supervisionConditionForm" property="conditionLiteral" filter="false"/>" />	--%>	
																	<bean:write
															name="supervisionConditionForm"
															property="conditionLiteral" filter="false"/>				
																</td>
															</tr>
														</logic:notEqual>
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="inUse" value="false">
														<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
															<tr>
																<td class="formDeLabel" valign="top" width=1% nowrap>
																	<bean:message key="prompt.4.diamond"/><bean:message key="prompt.literal" /></td>
																<td colspan=3 class="formDe" >
														<%--		<input type="hidden" name="conditionLiteral" value="<bean:write name="supervisionConditionForm" property="conditionLiteral" filter="false"/>" />	--%>
																	<bean:write
															name="supervisionConditionForm"
															property="conditionLiteral"  filter="false"/>				
																</td>
															</tr>
														</logic:equal>
														
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">	
															<style>
																.mceEditor{																				
																	width: "410";
																	height: "130";
																}
															</style>															
															<tr>
																<td class="formDeLabel" valign="top" width=1% nowrap>
																	<bean:message key="prompt.4.diamond"/><bean:message key="prompt.literal" /></td>
																<td colspan=3 class="formDe">																
																	<html:textarea styleClass="mceEditor" name="supervisionConditionForm" property="conditionLiteral" rows="7" ondblclick="myReverseTinyMCEFix(this)"/>
																	<script>
																	customValRequired('conditionLiteral','Literal is required',null);
																	addDefinedTinyMCEFieldMask('conditionLiteral','Literal  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
																	customValMinLength('conditionLiteral','Literal  must be at least 10 characters',10);
																	customValMaxLength('conditionLiteral','Literal  cannot be more than 1000 characters',2000);
																</script>
																	<div style="text-align:center"><input type="submit" name="submitAction" value="Preview" onclick="return openPreviewWindow(this.form, '/<msp:webapp/>jsp/common/pleaseWait.html')"></div>																</td>
															</tr>
														</logic:notEqual>
													</logic:equal>
												
													<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
														<tr>
															<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="prompt.spanishLiteral" /></td>
															<td colspan=3 class="formDe">
															
															<html:textarea name="supervisionConditionForm" property="conditionSpanishLiteral" style="width:100%" rows="5" /></td>
														</tr>
														<tr>
															<td class="formDeLabel" valign="top" width=1% nowrap>
																<%--<bean:message key="prompt.4.diamond"/>--%><bean:message key="prompt.details"/></td>
															<td colspan=3 class="formDe" align="center">
																<html:textarea name="supervisionConditionForm" property="conditionLiteral" style="width:100%" rows="5" />
																
															</td>
														</tr>	
													</logic:equal>
													
													<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">																
														<tr>
															<td class="formDeLabel" valign="top" width=1% nowrap>
																<bean:message key="prompt.spanishLiteral" /></td>
															<td colspan=3 class="formDe" align="center">
																<html:textarea name="supervisionConditionForm" property="conditionSpanishLiteral" style="width:100%" rows="5" />																
															</td>
														</tr>
													</logic:notEqual>
													
													<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="true">
															<tiles:put name="agencyCode" beanName="supervisionConditionForm" beanProperty="agencyId"/>
													</tiles:insert>
													<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.severityLevel" /></td>
															<td colspan=3 class="formDe">
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																	
																	 	<html:select property="severityLevelId" size="1">
																	 	<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="severityLevels" value="code" label="description" filter="true"/>
																		</html:select> 	
																	
																</logic:equal>	
																<logic:equal name="supervisionConditionForm" property="inUse" value="true">
																	<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
																		<input type="hidden" name="severityLevelId" value="<bean:write name="supervisionConditionForm" property="severityLevelId"/>" />	
																		<bean:write
																			name="supervisionConditionForm" property="severityLevel" />
																	</logic:notEqual>
																	<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
																		<html:select property="severityLevelId" size="1">
																	 	<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="severityLevels" value="code" label="description" />
																		</html:select> 	
																	</logic:equal>
																</logic:equal>														
															</td>
														</tr>
													</logic:equal>	
													<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">																			
														<tr>
															<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.documents" /></td>
															<td colspan=3 class="formDe">
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																	<html:select property="selDocumentIds" multiple="true" size="4" onchange="multiSelectFix(this);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection property="documents" value="code" label="description" /> 
																	</html:select>	
																	
																</logic:equal>
																<logic:equal name="supervisionConditionForm" property="inUse" value="true">
																
																	<bean:write
																	name="supervisionConditionForm" property="selDocuments" />	
																</logic:equal>												
															</td>
														</tr>
													</logic:notEqual>
														
														<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
														<tr>
															<td class="formDeLabel" nowrap>
																<bean:message key="prompt.4.diamond"/><bean:message key="prompt.jurisdiction" />
															</td>
															<td colspan=3 class="formDe">
																<html:select property="jurisdictionId" size="1">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="jurisdictions" value="code" label="description" /> 
																</html:select>																
															</td>
														</tr>
														
													</logic:equal>
												
													<SCRIPT type="text/javascript" ID="js1">
														var cal1 = new CalendarPopup();
														cal1.showYearNavigation();
													</SCRIPT>
															
													<!-- special condition shows effective date asread only -->
													<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
														<tr>
															<td class="formDeLabel" nowrap>
																<bean:message key="prompt.4.diamond"/><bean:message key="prompt.effectiveDate" />
															</td>
															<td colspan=3 class="formDe"><bean:write name="supervisionConditionForm" property="effectiveDate" /></td>
															<html:hidden name="supervisionConditionForm" property="effectiveDate"/>	
														</tr>
														<tr>
															<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
															<td class="formDe" colspan="3">
																<html:text name="supervisionConditionForm" property="inactiveDate" size="10" maxlength="10" />
																<A HREF="#" onClick="cal1.select(document.forms[0].inactiveDate,'anchor1','MM/dd/yyyy'); return false;"
																NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.4.calendar"/></A></td>
					                                	</tr>
													</logic:equal>	
													<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
														<tr>
															<td class="formDeLabel" nowrap>
																<bean:message key="prompt.4.diamond"/><bean:message key="prompt.effectiveDate" />
															</td>
															<td colspan=3 class="formDe">
															<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																<html:text name="supervisionConditionForm" property="effectiveDate" size="10" maxlength="10" />
																<A HREF="#" onClick="cal1.select(document.forms[0].effectiveDate,'anchor2','MM/dd/yyyy'); return false;"
																NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.4.calendar"/></A>
					                                		</logic:equal>
															<logic:equal name="supervisionConditionForm" property="inUse" value="true">
															<html:hidden name="supervisionConditionForm" property="effectiveDate"/>	
																<bean:write name="supervisionConditionForm" property="effectiveDate" />
															</logic:equal>
															</td>
														</tr>

														<logic:equal name="supervisionConditionForm" property="action" value="update">
															<tr>
																<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
																<td class="formDe" colspan="3">
																	<html:text name="supervisionConditionForm" property="inactiveDate" size="10" maxlength="10" />
																	<A HREF="#" onClick="cal1.select(document.forms[0].inactiveDate,'anchor3','MM/dd/yyyy'); return false;"
																	NAME="anchor3" ID="anchor3" border="0"><bean:message key="prompt.4.calendar"/></A></td>
					                                		</tr>
														</logic:equal>
													</logic:notEqual>
													
									
														  <logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
															<tr>
																<td class="formDeLabel" nowrap valign="top"><bean:message key="prompt.eventType" /></td>
																<td colspan=3 class="formDe">
																<logic:equal name="supervisionConditionForm" property="inUse" value="true">
																	<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
																		<bean:write name="supervisionConditionForm" property="selectedEventTypes" />
																	</logic:notEqual>
																	<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
																		<html:select property="selectedEventTypeIds" multiple="true" size="10" onchange="eventTypeCheck(this)">
																		<html:option value=""><bean:message key="select.multiple" /></html:option>
																		<html:optionsCollection property="eventTypes" value="code" label="description"/>				
																		</html:select>	
																	</logic:equal>
																</logic:equal>
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																	<html:select property="selectedEventTypeIds" multiple="true" size="10" onchange="eventTypeCheck(this)">
																		<html:option value=""><bean:message key="select.multiple" /></html:option>
																		<html:optionsCollection property="eventTypes" value="code" label="description"/>				
																	</html:select>	
																</logic:equal>														
																</td>
															</tr>
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
															<logic:equal name="supervisionConditionForm" property="inUse" value="true">
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.eventCount" /></td>
																	<td class="formDe" colspan=3><bean:write name="supervisionConditionForm" property="eventCountDesc" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.period" /></td>
																	<td class="formDe" colspan=3>
																		<logic:notEmpty name="supervisionConditionForm" property="periodValue">
																		<logic:notEqual name="supervisionConditionForm" property="periodValue" value="0">
																		<bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/>
																		</logic:notEqual>
																		</logic:notEmpty>
																	</td>
																		</tr>
															</logic:equal>
														</logic:notEqual>
															<tr id=eventCount class=hidden>
																<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.eventCount" /></td>
																<td colspan=3 class="formDe">
																	<html:select property="eventCount" onchange="checkEventCount(this);">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
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
																<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.periodValue" /></td>
																<td class="formDe"><html:text name="supervisionConditionForm" property="periodValue" size="4"  maxlength="3"/></td>
																<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.period" /></td>
																<td class="formDe">
																	<html:select property="periodId" size="1">
																		<html:optionsCollection property="periods" value="code" label="description" /> 
																	</html:select>															
																</td>
															</tr>	
															<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
																<logic:equal name="supervisionConditionForm" property="inUse" value="false">
																		<script>
																			eventTypeCheck(null);
																		</script>	
																	
																</logic:equal>
															</logic:notEqual>
															<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
																		<script>
																	eventTypeCheck(null);
																</script>	
															</logic:equal>					
														</logic:equal>
														
													<tr>
														<td class="formDeLabel" width=1% valign="top"><bean:message key="prompt.notes" /></td>
														<td colspan=3 class="formDe">
															<html:textarea name="supervisionConditionForm" property="notes" rows="3" style="width:100%" />
														</td>
													</tr>
												</table>
											</td>
										</tr>										
									</table>
								
								</td>
								<td width=1% valign="top">&nbsp;</td>
								<td width=20% valign="top">
									<!--detail dictionary start-->
									<tiles:insert page="../../../common/detailDictionary.jsp">
										<tiles:put name="ddbean" beanName="supervisionConditionForm" /> 
										<tiles:put name="actionPath" value="/displaySupervisionConditionUpdateTasks"/>
										<tiles:put name="formName" value="commonSupervisionForm"/>
										<tiles:put name="includedIn" value="condition"/>
										<tiles:put name="isSpecialCondition" beanName="supervisionConditionForm" beanProperty="specialCondition"/>
									</tiles:insert>	
									<!--detail dictionary end-->
								</td>
							</tr>
							</table>
							<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
							<!-- association should not show up during create -->
							<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<br>
							<table width=98% cellpadding=0 cellspacing=0>							
								<tr>
									<td>										
									<!-- Associations -->
									<tiles:insert page="../../../common/conditionAssociationsView.jsp" flush="true">
										<tiles:put name="associatedCourtPolicies" beanName="supervisionConditionForm" beanProperty="associatedCourtPolicies"/>
										<tiles:put name="associatedDepartmentPolicies" beanName="supervisionConditionForm" beanProperty="associatedDeptPolicies"/>
									</tiles:insert>							
									</td>
								</tr>
							</table>
							</logic:notEqual>
							</logic:notEqual>
							
									<!-- BEGIN BUTTON TABLE -->
									<br>
						<table border="0" width="98%">
							<tr>
								<td align="center">
								<logic:equal name="supervisionConditionForm" property="inUse" value="false">
									<html:submit property="submitAction" onclick="return (myTinyMCEFix() && checkTarget(this.form) && validateEventType(this.form) && compareDate(this.form) && unhideGroups(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit> 
						     	</logic:equal>
						     	<logic:equal name="supervisionConditionForm" property="inUse" value="true">
						     	
							     		<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
							     		<html:submit property="submitAction" onclick="return (myTinyMCEFix() && checkTarget(this.form) && validateEventType(this.form) && compareDate(this.form) && unhideGroups(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit> 
							     	</logic:equal>
							     		<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
										<html:submit property="submitAction" onclick="return (myTinyMCEFix() && checkTarget(this.form) &&  compareDate(this.form) && unhideGroups(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit> 
						     		</logic:notEqual>
						     
						     	</logic:equal>
						     		<input type="reset" value="Reset" name="reset">
									
									<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
										<input type=button value='<bean:message key="button.cancel" />' onclick="resetTarget(this.form);changeFormActionURL(this.form.name, '/<msp:webapp/>globalCancel.do', false) && disableSubmit(this, this.form)">
									</logic:equal>
									<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
										<input type=button value='<bean:message key="button.cancel" />' onclick="resetTarget(this.form);changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form)">
									</logic:notEqual>
								</td>	
							</tr>
						</table>
						<!-- END BUTTON TABLE -->							
						<br>
						
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<script>
updateGroup2(document.forms[0]);
</script>
<!-- END  TABLE -->
</div>
<html:hidden property="clearUpdateHidden" value="true"/>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
