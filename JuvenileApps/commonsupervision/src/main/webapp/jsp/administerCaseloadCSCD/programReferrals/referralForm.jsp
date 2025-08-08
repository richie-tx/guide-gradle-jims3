<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/30/2008 D Williamson - Converted PT to JSP -->
<!-- 07/22/2009 C Shimek     - #61019 added validation for end and begin program dates -->
<!-- 08/03/2009 C Shimek     - #61048 revised to not display/validate input for Contract Pgm and Tracer Num when service provider Contract Pgm is No on Submit Referral -->
<!-- 09/28/2009 C Shimek     - #62094 corrected comment validations to only allow 255 characters as stated in requirements -->
<!-- 06/14/2010 D Williamson - #65942 Program Referral Update - Begin Date cannot be in the future -->
<!-- 06/22/2010 L Deen		 - #65942 Undo per MP-Program Referral Update - Begin Date cannot be in the future -->
<!-- 08/23/2010 D Williamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
<!-- 01/05/2011 C Shimek     - #68480 revised programId to correct value of programIdentifier in Identifier href to correct null value exception -->
<!-- 05/03/2011 C Shimek     - #70127 added code needed to make spell check function -->
<!-- 07/12/2011 R Young      - #68612 added code needed to make referral date and entry field -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@page import="naming.UIConstants"%>
<%@ page import="naming.CSAdministerProgramReferralsConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
  
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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/referralForm.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
	function setPeriod(val)
	{
		period=val;
	}

	function showContractServices(val)
	{
		if (val=="true")
		{
			document.getElementById('contractTD').colSpan=1;
			
			document.getElementById('tracerLabelTD').colSpan=1;
			document.getElementById('tracerFieldTD').colSpan=1;

			document.getElementById('tracerLabelTD').className ="formDeLabel";
			document.getElementById('tracerFieldTD').className="formDe";
		}
		else if (val=="false")
		{
			show('tracerLabelTD', 0);
			show('tracerFieldTD', 0);
			document.getElementById('contractTD').colSpan=3;
		}
	}


	
	function validate(theForm)
	{
		clearAllValArrays();
		var myAction = "<bean:write name="cscProgRefForm" property="action" />";
		var contractPgm = "<bean:write name="cscProgRefForm" property="referralProgramLocBean.contractProgramDesc" />";

		if(myAction == "submitReferral")
		{
			customValRequired("referralDateAsStr","<bean:message key='errors.required' arg0='Referral Date'/>","");
			addMMDDYYYYDateValidation("referralDateAsStr","<bean:message key='errors.date' arg0='Referral Date'/>","");
			
			customValRequired("programBeginDateAsStr","<bean:message key='errors.required' arg0='Program Begin Date'/>","");
			addMMDDYYYYDateValidation("programBeginDateAsStr","<bean:message key='errors.date' arg0='Program Begin Date'/>","");
	
			if (contractPgm != null && contractPgm != "No"){
				var contractProgramElemts = document.getElementsByName('contractProgram');
				var contractLen = contractProgramElemts.length;

				customValRequired("contractProgram","<bean:message key='errors.required' arg0='Contract Program'/>","");
				var isContractProgram = "";
				for(var index=0; index < contractLen; index++)
				{
					if(contractProgramElemts[index].checked)
					{
						isContractProgram = contractProgramElemts[index].value;
						break;
					}
				}

				if(isContractProgram=="true")
				{
					customValRequired("tracerNum","<bean:message key='errors.required' arg0='Tracer Number'/>","");
					addDefinedDB2Mask("tracerNum","<bean:message key='errors.freeTextDB2' arg0='Tracer Number'/>");
					customValMaxLength("tracerNum","Tracer number cannot be more than 10 characters","10");
				}
			}
			customValRequired("reasonForPlacementId","<bean:message key='errors.required' arg0='Reason for Placement'/>","");
			customValMaxLength("submitComments","Comments cannot be more than 255 characters","255");
			addDefinedTinyMCEFieldMask("submitComments","<bean:message key='errors.freeTextDB2' arg0='Comments'/>","");

			var isIncarcerationRef = document.getElementsByName('incarcerationReferral')[0].value;
			if(isIncarcerationRef=="true")
			{
		        if(theForm.confinementLengthYears.value=="" && theForm.confinementLengthMonths.value == "" && theForm.confinementLengthDays.value==""){
		   			alert("Confinement Length must have a value.");
		   			theForm.confinementLengthYears.focus();
		   			return false;
		   		}
		   		if(theForm.confinementLengthYears.value=="00" && theForm.confinementLengthMonths.value == "00" && theForm.confinementLengthDays.value=="00"){
		   			alert("Confinement Length must have a value.");
		   			theForm.confinementLengthYears.focus();
		   			return false;
		   		}
		   		if(theForm.confinementLengthYears.value=="0" && theForm.confinementLengthMonths.value == "0" && theForm.confinementLengthDays.value=="0"){
		   			alert("Confinement Length must have a value.");
		   			theForm.confinementLengthYears.focus();
		   			return false;
		   		}
		   			
		   		customValRequired("confinementLengthYears", "Confinement Length in Years is required.","");
		   		customValRequired("confinementLengthMonths", "Confinement Length in Months is required.","");
		   		customValRequired("confinementLengthDays", "Confinement Length in Days is required.","");
		   		addNumericValidation("confinementLengthYears", "Confinement Length in Years must be an integer and nonnegative.");
		   		addNumericValidation("confinementLengthMonths", "Confinement Length in Months must be an integer and nonnegative.");
		   		addNumericValidation("confinementLengthDays", "Confinement Length in Days must be an integer and nonnegative.");

		   		customValIntegerRange("confinementLengthYears", "Confinement Length Years must be in the range of 0 - 10.", 0, 10);
		   		customValIntegerRange("confinementLengthMonths", "Confinement Length Months must be in the range of 0 - 12.", 0, 12);
		   		customValIntegerRange("confinementLengthDays", "Confinement Length Days must be in the range of 0 - 31.", 0, 31);
			}
		}
		else
		if(myAction == "exitReferral")
		{
			customValRequired("programEndDateAsStr","<bean:message key='errors.required' arg0='Program End Date'/>","");
			addMMDDYYYYDateValidation("programEndDateAsStr","<bean:message key='errors.date' arg0='Program End Date'/>","");

			customValRequired("reasonForDischargeId","<bean:message key='errors.required' arg0='Reason for Discharge'/>","");

			customValMaxLength("exitComments","Comments cannot be more than 255 characters","255");
			addDefinedTinyMCEFieldMask("exitComments","<bean:message key='errors.freeTextDB2' arg0='Comments'/>","");
		}	
		else
		if(myAction == "updateSubmit")
		{
			customValRequired("referralDateAsStr","<bean:message key='errors.required' arg0='Referral Date'/>","");
			addMMDDYYYYDateValidation("referralDateAsStr","<bean:message key='errors.date' arg0='Referral Date'/>","");
			
			customValRequired("programBeginDateAsStr","<bean:message key='errors.required' arg0='Program Begin Date'/>","");
			addMMDDYYYYDateValidation("programBeginDateAsStr","<bean:message key='errors.date' arg0='Program Begin Date'/>","");
	
			customValRequired("contractProgram","<bean:message key='errors.required' arg0='Contract Program'/>","");
			var contractProgramElemts = document.getElementsByName('contractProgram');
			var contractLen = contractProgramElemts.length;
			var isContractProgram = "";
			for(var index=0; index < contractLen; index++)
			{
				if(contractProgramElemts[index].checked)
				{
					isContractProgram = contractProgramElemts[index].value;
					break;
				}
			}
			if(isContractProgram=="true")
			{
				customValRequired("tracerNum","<bean:message key='errors.required' arg0='Tracer Number'/>","");
				addDefinedDB2Mask("tracerNum","<bean:message key='errors.freeTextDB2' arg0='Tracer Number'/>");
				customValMaxLength("tracerNum","Tracer number cannot be more than 10 characters","10");
			}
	
			customValRequired("reasonForPlacementId","<bean:message key='errors.required' arg0='Reason for Placement'/>","");
		}	
		else
		if(myAction == "updateExit")
		{
			customValRequired("programEndDateAsStr","<bean:message key='errors.required' arg0='Program End Date'/>","");
			addMMDDYYYYDateValidation("programEndDateAsStr","<bean:message key='errors.date' arg0='Program End Date'/>","");

			customValRequired("reasonForDischargeId","<bean:message key='errors.required' arg0='Reason for Discharge'/>","");
		}
		else
		if(myAction == "reReferral")
		{
			addMMDDYYYYDateValidation("scheduledDateAsStr","<bean:message key='errors.date' arg0='Scheduled Date'/>","");
			
			customValMaxLength("submitComments","Comments cannot be more than 255 characters","255");
			addDefinedTinyMCEFieldMask("submitComments","<bean:message key='errors.freeTextDB2' arg0='Comments'/>","");
		}
		var result = validateCustomStrutsBasedJS(theForm);
		if(myAction != "submitReferral" && myAction != "updateSubmit" && myAction != "exitReferral"){
			return result;
		}	
		if (result == true){
			var curDateTime = new Date();
			if(myAction == "submitReferral" || myAction == "updateSubmit"){
				var begdt = theForm.programBeginDateAsStr.value + " " + "00:00";
				var begDateTime = new Date(begdt);
			    if (begDateTime > curDateTime){
				    alert("Program Begin Date can not be future date.");
				    theForm.programBeginDateAsStr.focus();
				    result = false;
			    }

			    var refdt = theForm.referralDateAsStr.value + " " + "00:00";
				var refDateTime = new Date( refdt );
			    if (refDateTime > curDateTime){
				    alert("Referral Date can not be future date.");
				    theForm.referralDateAsStr.focus();
				    result = false;
			    }


			    var refdt = theForm.referralDateAsStr.value + " " + "00:00";
			    var begdt = theForm.programBeginDateAsStr.value + " " + "00:00";
				var refDateTime = new Date( refdt );
				var begDateTime = new Date( begdt );
			    if ( begDateTime < refDateTime ){
				    alert("Program Begin Date can not be less than Referral Date.");
				    theForm.programBeginDateAsStr.focus();
				    result = false;
			    }
			}        
			if(myAction == "exitReferral"){
				var begdate = theForm.programBeginDateAsStr.value + " " + "00:00";
				var enddt = theForm.programEndDateAsStr.value + " " + "00:00";
				var beginDateTime = new Date(begdate);
				var endDateTime = new Date(enddt);
				
			    if (endDateTime > curDateTime){
				    alert("Program End Date can not be future date.");
				    theForm.programEndDateAsStr.focus();
				    result = false;
			    }

			    if (beginDateTime > endDateTime) {
				    alert("Program End Date can not be prior to the Program Begin Date.");
				    theForm.programEndDateAsStr.focus();
				    result = false;
			    }    
			}        
		}
		return result; 
	}
	
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayProgRefSummary" target="content">
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
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<%-- BEGIN BLUE BORDER TABLE --%>			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!--header area start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<!--header start-->
									<tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!--header area end-->
<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="ProgramReferralsTab" />
									</tiles:insert>
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<%-- BEGIN GREEN BORDER TABLE --%>						
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;- 
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL%>">
												   <bean:message key="title.submit"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|22">
											    </logic:equal>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL%>">
												   <bean:message key="prompt.exit"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|15">
											    </logic:equal>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT%>">
												   <bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|20">
											    </logic:equal>
											    <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT%>">
												   <bean:message key="title.update"/>
											    </logic:equal>
												<bean:message key="prompt.program"/>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
                                                   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|26">
												   <bean:message key="prompt.rereferral"/>
											    </logic:equal>													
                                                <logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
												   <bean:message key="prompt.referral"/>
											    </logic:notEqual>													
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
<!-- END ERROR TABLE -->
<%-- BEGIN INSTRUCTIONS TABLE --%>
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td><ul>
												  <li>Enter the required information, and select Next.</li>
											    </ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
<%-- END INSTRUCTIONS TABLE --%>									
<!--Conditions List start-->
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<!--conditions list tile start-->
												<tiles:insert page="prConditionsListTile.jsp" flush="true">
													<tiles:put name="condList" beanName="cscProgRefForm" beanProperty="conditionsList"></tiles:put>
												</tiles:insert> 
												<!--conditions list tile end-->
											</td>
										</tr>
									</table>
<!--Conditions List end-->
									<div class="spacer4px"></div>  
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="2" cellspacing="1">
																<tr>
																	<td colspan="8">
																		<span id="submitProgramForm" class="hidden">
																			<table width="100%" cellpadding="1" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralDate"/></td>
																					<td colspan="3" class="formDe">
																						<html:text name="cscProgRefForm" property="referralDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
																						<A HREF="#" onClick="cal1.select(referralDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
																					</td>
																				 </tr>
																				 <tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programBeginDate"/></td>
																					<td colspan="3" class="formDe">
																						<html:text name="cscProgRefForm" property="programBeginDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
																						<A HREF="#" onClick="cal1.select(programBeginDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
																					</td>
																				</tr>
																				<tr id="contratPgmTracerNum" class="visible" >
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.contractProgram"/></td>
																					<td class="formDe" id="contractTD">
																						<html:radio name="cscProgRefForm" property="contractProgram" value="true" onclick="showContractServices(this.value)" />Yes <html:radio name="cscProgRefForm" property="contractProgram" value="false" onclick="showContractServices(this.value)"/>No
																					</td>
																					<td class="formDeLabel" id="tracerLabelTD" width="1%" nowrap="nowrap">
																						<bean:message key="prompt.3.diamond"/><bean:message key="prompt.tracerNumber"/>
																					</td>
																					<td class="formDe" id="tracerFieldTD">
																						<html:text  name="cscProgRefForm" property="tracerNum" size="15" maxlength="10" />
																					</td>
																					<logic:equal name="cscProgRefForm" property="contractProgram" value="true">
																						<script>showContractServices("true");</script>
																					</logic:equal>
																					<logic:equal name="cscProgRefForm" property="contractProgram" value="false">
																						<script>showContractServices("false");</script>
																					</logic:equal>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForPlacement"/></td>
																					<td class="formDe" colspan="3">
																						<html:select size="1" name="cscProgRefForm" property="reasonForPlacementId" >
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<jims2:codetable codeTableName="<%=PDCodeTableConstants.REASON_FOR_PLACEMENT%>" sort="true"></jims2:codetable>
																						</html:select>
																					</td>
																				</tr>
																				<html:hidden name="cscProgRefForm" property="incarcerationReferral" />
																				<logic:equal name="cscProgRefForm" property="incarcerationReferral" value="true">
																					<tr>
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.confinementLength"/></td>
																						<td class="formDe" colspan="3">
																							<html:text property="confinementLengthYears" size="2" maxlength="2"/>
																							Years
																							<html:text property="confinementLengthMonths" size="2" maxlength="2"/>
																							Months
																							<html:text property="confinementLengthDays" size="2" maxlength="2"/>
																							Days
																						</td>
																					</tr>
																				</logic:equal>
																			</table>
																		</span>
																		<span id="exitProgramForm" class="hidden">
																			<table width="100%" cellpadding="1" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programEndDate"/></td>
																					<td colspan="3" class="formDe">
																						<SCRIPT LANGUAGE="JavaScript" ID="js1">
																							var cal2 = new CalendarPopup();
																							cal2.showYearNavigation();
																						</SCRIPT>
																						<html:text name="cscProgRefForm" property="programEndDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
																						<A HREF="#" onClick="cal2.select(programEndDateAsStr,'anchor33','MM/dd/yyyy'); return false;" NAME="anchor33" ID="anchor33"><bean:message key="prompt.3.calendar" /></A>
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForDischarge"/></td>
																					<td class="formDe" colspan="3">
																						<html:select size="1" name="cscProgRefForm" property="reasonForDischargeId" >
																							<html:option value=""><bean:message key="select.generic" /></html:option>
																							<jims2:codetable codeTableName="<%=PDCodeTableConstants.JIMS2_DISCHARGE_REASON%>" sort="true"></jims2:codetable>
																						</html:select>
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.programBeginDate"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="programBeginDateAsStr" />
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.contractProgram"/></td>
																					<td class="formDe">
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																							Yes
																						</logic:equal>
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="false" >
																							No
																						</logic:equal>
																					</td>
																					<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.tracerNumber"/></td>
																						<td class="formDe">
																							<bean:write name="cscProgRefForm" property="tracerNum"/>
																						</td>
																					</logic:equal>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.reasonForPlacement"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="reasonForPlacementDesc"/>
																					</td>
																				</tr>
																				<logic:equal name="cscProgRefForm" property="incarcerationReferral" value="true">
																					<tr>
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.confinementLength"/></td>
																						<td class="formDe" colspan="3">
																							<bean:write name="cscProgRefForm" property="confinementLengthYears" />&nbsp;Years&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthMonths" />&nbsp;Months&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthDays" />&nbsp;Days&nbsp;
																						</td>
																					</tr>
																				</logic:equal>
																			</table>
																		</span>
																		<span id="reReferralForm" class="hidden">
																			<table width="100%" cellpadding="1" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.programBeginDate"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="programBeginDateAsStr" />
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.contractProgram"/></td>
																					<td class="formDe">
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																							Yes
																						</logic:equal>
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="false" >
																							No
																						</logic:equal>
																					</td>
																					<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.tracerNumber"/></td>
																						<td class="formDe">
																							<bean:write name="cscProgRefForm" property="tracerNum"/>
																						</td>
																					</logic:equal>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.reasonForPlacement"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="reasonForPlacementDesc"/>
																					</td>
																				</tr>
																				<logic:equal name="cscProgRefForm" property="incarcerationReferral" value="true">
																					<tr>
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.confinementLength"/></td>
																						<td class="formDe" colspan="3">
																							<bean:write name="cscProgRefForm" property="confinementLengthYears" />&nbsp;Years&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthMonths" />&nbsp;Months&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthDays" />&nbsp;Days&nbsp;
																						</td>
																					</tr>
																				</logic:equal>
																			</table>
																		</span>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL%>">
																				<script> show("submitProgramForm", 1) </script>
																		</logic:equal>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL%>">
																				<script> show("exitProgramForm", 1) </script>
																		</logic:equal>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT%>">
																				<script> show("exitProgramForm", 1) </script>
																		</logic:equal>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT%>">
																				<script> show("submitProgramForm", 1) </script>
																		</logic:equal>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																				<script> show("reReferralForm", 1) </script>
																		</logic:equal>
																	</td>
																</tr>
																<tr class="formDeLabel">
																	<td><bean:message key="prompt.serviceProvider"/></td>
																	<td><bean:message key="prompt.referralType"/></td>
																	<td><bean:message key="prompt.identifier"/></td>
																	<td><bean:message key="prompt.name"/></td>
																	<td width="5%"><bean:message key="prompt.CSTSCode"/></td>
																	<td width="10%"><bean:message key="prompt.languagesOffered"/></td>
																	<td><bean:message key="prompt.sexSpecific"/></td>
																	<td><bean:message key="prompt.contractProgram"/></td>
																</tr>
																<tr class="programRow">
																	<td class="bottomBorder">
																		<logic:equal name="cscProgRefForm" property="userEnteredServiceProvider" value="true">
																			<bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderName" />&nbsp;
																		</logic:equal>
																		<logic:notEqual name="cscProgRefForm" property="userEnteredServiceProvider" value="true">
																			<a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderId" />')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderName" /></a>&nbsp;
																		</logic:notEqual>
																	</td>	
																	<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.referralTypeDesc" /></td>
																	<td class="bottomBorder"><a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.programIdentifier"/>')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.programIdentifier" /></a>&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.programName" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.cstsCode" />&nbsp;</td>
																	<td class="bottomBorder">
																		<logic:iterate id="eachLanguage" name="cscProgRefForm" property="referralProgramLocBean.languagesOffered" >
																			<bean:write name="eachLanguage" />,
																		</logic:iterate>
																		&nbsp;
																	</td>
																	<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.sexSpecificDesc" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.contractProgramDesc" />&nbsp;</td>
																	<logic:equal name="cscProgRefForm" property="referralProgramLocBean.contractProgramDesc"  value="No">
																		<script> show("contratPgmTracerNum", 0) </script>
																	</logic:equal>
																</tr>
																<tr>
																	<td colspan="8">
																		<span id="singleLocationForm" class="hidden">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<tr>
																					<td colspan="2" width="50%">
																						<div>
																							<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetNumber" />
					                                                                        <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetName" />
					                                                                        <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetTypeCd" />
					                                                                        <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.aptNum" />
																						</div>
																						<div>
																							<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.city" />,
					                                                                        <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.state" />
					                                                                        <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.zipCode" />
					                                                                    </div>
					                                                                </td>
																					<td nowrap="nowrap" colspan="6">
																						<div>
																							<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" value="">
																								<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" />&nbsp;Ph<br>
																							</logic:notEqual>
																							<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" value="">
																								<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" />&nbsp;F
																							</logic:notEqual>
																							&nbsp;
																						</div>
																					</td>
																				</tr>
																			</table>
																		</span>
																		<span id="multipleLocationForm" class="hidden">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				 <logic:iterate id="locationIndex" name="cscProgRefForm" property="referralProgramLocBean.programLocationsList">
																					<tr>
																						<td width="1%">
																							<logic:equal name="locationIndex" property="selected" value="true">
																								<input type="radio" name="selectedPrgmLocIds" value="<bean:write name='cscProgRefForm' property='referralProgramLocBean.programId'/>-<bean:write name='locationIndex' property='locationId'/>" checked="checked" />
																							</logic:equal>
																							<logic:equal name="locationIndex" property="selected" value="false">
																								<input type="radio" name="selectedPrgmLocIds" value="<bean:write name='cscProgRefForm' property='referralProgramLocBean.programId'/>-<bean:write name='locationIndex' property='locationId'/>" />
																							</logic:equal>
																						</td>
																						<td>
																							<div>
																								<bean:write name="locationIndex" property="streetNumber" />
						                                                                        <bean:write name="locationIndex" property="streetName" />
						                                                                        <bean:write name="locationIndex" property="streetTypeCd" />
						                                                                        <bean:write name="locationIndex" property="aptNum" />
						                                                                    </div>
						                                                                    <div>
						                                                                    	<bean:write name="locationIndex" property="city" />,
						                                                                        <bean:write name="locationIndex" property="state" />
						                                                                        <bean:write name="locationIndex" property="zipCode" />
						                                                                    </div>
						                                                                </td>
																						<td nowrap="nowrap" colspan="6">
																							<div>
																								<logic:notEqual name="locationIndex" property="locationPhone.formattedPhoneNumber" value="">
																									<bean:write name="locationIndex" property="locationPhone.formattedPhoneNumber" />&nbsp;ph<br>
																								</logic:notEqual>
																								<logic:notEqual name="locationIndex" property="locationFax.formattedPhoneNumber" value="">
																									<bean:write name="locationIndex" property="locationFax.formattedPhoneNumber" />&nbsp;f
																								</logic:notEqual>
																								&nbsp;
																							</div>
																						</td>
																					</tr>
																				</logic:iterate>
																			</table>
																		</span>
																		<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																			<script> show("singleLocationForm", 1) </script>
		                                                               	</logic:notEqual>
		                                                               	<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																			<script> show("multipleLocationForm", 1) </script>
																		</logic:equal>
																	</td>
																</tr>
																
																<tr>
																	<td colspan="8">
																		<span id="rereferralDateForm" class="hidden">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="5%" nowrap="nowrap"><bean:message key="prompt.scheduledDate"/></td>
																					<td class="formDe" width="45%">
																						<html:text name="cscProgRefForm" property="scheduledDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
																						<A HREF="#" onClick="cal1.select(cscProgRefForm.scheduledDateAsStr,'anchor100','MM/dd/yyyy'); return false;" NAME="anchor100" ID="anchor100"><bean:message key="prompt.3.calendar"/></A> 
																					</td>
																					<td class="formDeLabel" width="5%" nowrap="nowrap"><bean:message key="prompt.time"/></td>
																					<td class="formDe" width="45%">
																						<html:select name="cscProgRefForm" property="scheduledTime">
																							<option value="">Please Select</option>
																							<jims2:codetable codeTableName="<%=PDCodeTableConstants.WORKDAY%>" sort="true" sortCode="true" value="description"/>
																						</html:select>
																					</td>
																				</tr>
																			</table>
																		</span>
																		<span id="rereferralDateView">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.scheduledDate"/></td>
																					<td class="formDe">
																						<bean:write name="cscProgRefForm" property="scheduledDateAsStr" formatKey="date.format.mmddyyyy"/>
																					</td>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.time"/></td>
																					<td class="formDe">
																						<bean:write name="cscProgRefForm" property="scheduledTime" formatKey="time.format.hhmma"/>
																					</td>
																				</tr>
																			</table>
																		</span>
																		<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																			<script> show("rereferralDateForm", 1) </script>
																			<script> show("rereferralDateView", 0) </script>
																		</logic:equal>
																	</td>
																</tr>
																<tr id="commentsLabelRow">
																	<td colspan="8" class="formDeLabel">
																		<bean:message key="prompt.comments"/>
																	</td>
																</tr>
																<bean:define id="userAgency" name="cscProgRefForm" property="userAgency"></bean:define>
																<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
																	<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
																</tiles:insert>
																<tr id="submitCommentsRow">
																	<td colspan="8">
																		<html:textarea name="cscProgRefForm" property="submitComments" styleClass="mceEditor" style="width:100%" rows="6" ondblclick="myReverseTinyMCEFix(this)" />
																	</td>
																</tr>
																<tr id="exitCommentsRow">
																	<td colspan="8">
																		<html:textarea name="cscProgRefForm" property="exitComments" styleClass="mceEditor" style="width:100%" rows="6" ondblclick="myReverseTinyMCEFix(this)" />
																	</td>
																</tr>
															</table>
															<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL%>">
																<script> show("exitCommentsRow",0) </script>  
															</logic:equal>
															<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL%>">
																<script> show("submitCommentsRow",0) </script>  
															</logic:equal>
															<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT%>">
																<script> show("commentsLabelRow",0) </script> 
																<script> show("submitCommentsRow",0) </script>
																<script> show("exitCommentsRow",0) </script>  
															</logic:equal>
															<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT%>">
																<script> show("commentsLabelRow",0) </script> 
																<script> show("submitCommentsRow",0) </script> 
																<script> show("exitCommentsRow",0) </script> 
															</logic:equal>
															<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																<script> show("exitCommentsRow",0) </script>  
															</logic:equal>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return (myTinyMCEFix() && validate(this.form));" > <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<%-- END GREEN BORDER TABLE --%>						
						<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
<%-- END BLUE BORDER TABLE --%>				
			<br>
		</td>
	</tr>
</table>
<br>
</div>
</html:form>
<div align="center">[<a href="#top">Back to Top</a>]</div></body>
</html:html>