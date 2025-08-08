<!DOCTYPE HTML>
<%-- Used to display casefile activiation page --%>
<%--MODIFICATIONS --%>
<%--LDeen    8/17/06 	Revised calendar image source & added required & mm/dd/yyyy instruction--%>
<%--DNikolis 03/14/2012 Revised logic for displaying controlling Referral drop down, now works with 3 superviseeCategoryIds --%>
<%--CShimek	 05/08/2012 Revised jims:equal to logic:equal statements to correct double Next button display, found while testing for activity 73382.  --%>
<%--CShimek	 05/09/2012 Revised simplified validation scripts into single script and corrected some of the alert messages, found while doing more testing for activity 73382. --%>
<%--CShimek	 06/04/2012 #73668 replaced isDate() form date.js with local function. --%>
<%--CShimek	 09/11/2012 #74186 added court ordered probation start date input/display field. --%>
<%--CShimek	 11/05/2012 #74186 commented out changes made 9/11/12 per request of JP department --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- activateCasefileAssignment -casefileActivate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<%-- <script type="text/javascript" src="/<msp:webapp/>js/date.js"></script>  --%>

<script type="text/javascript">

$(function(){
	datePickerSingle($("#supervisionEndDateStr"),"Expected End date", false);
	
});
var cal1 = new CalendarPopup();
cal1.showYearNavigation();

function validateInputs(theForm, checkCntrlRef)
{  //Begin Supervision End Date Validation
	var msg = "";
	var dateStr = Trim( theForm.supervisionEndDateStr.value ) ;
	  
	result = validateDate(dateStr, "Expected End Date ");
	if (result != "") {
		msg = result;
		theForm.supervisionEndDateStr.focus();
	} else {	
		var dt = dateStr + " 23:59:59";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime < curDateTime){
			theForm.supervisionEndDateStr.focus();
			msg = "Expected End Date must be current or future date.\n";
		}
	}	

	if (checkCntrlRef == "Y"){
//		dateStr = Trim( theForm.courtOrderedProbationStartDateStr.value ) ;
//		if (dateStr == ""){
//			if (msg == ""){
//				theForm.supervisionEndDateStr.focus();
//			}
//			msg += "Court Ordered Probation Start Date is required.\n";
//			
//		} else {
//			var dt = dateStr + " 00:00:00";
//			var fldDateTime = new Date(dt);
//			var curDateTime = new Date();
//			if (fldDateTime > curDateTime){
//				if (msg == ""){
//					theForm.supervisionEndDateStr.focus();
//				}
//				msg += "Court Ordered Probation Start Date cannot be future date.\n";
//			}
//		}
		
		var ref = document.getElementsByName("controllingReferral");
		if (ref[0].selectedIndex == 0)
		{
			if (msg == ""){
				ref[0].focus();
			}
			msg += "Controlling Referral selection is required.";
		}
	}
	if (msg == ""){
		if ( true ) {
			spinner();
		}
		return true;
	}
	alert(msg);
	return false;
}

function validateDate(fldValue, fldLit, checkFutureDate)
{
	var numericRegExp = /^[0-9]*$/;
	var msg = "";
	if (fldValue == "")
	{
		msg = fldLit + "is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldLit + "must be in mm/dd/yyyy format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldLit + "is not a valid date.\n";
		return msg; 
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldLit + "must be in mm/dd/yyyy format.\n";
		return msg;
	} 

    if (month < 1 || month > 12)
    {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if (day < 1 || day > 31) {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
    		msg = fldLit + "is not valid.\n";
    		return msg;
        }
    }  

 	return "";
}
</script>
</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >

<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
   <logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
      <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|450">
   </logic:notEqual>   
</logic:notEqual>
<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|451">
</logic:equal>
<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|452">
</logic:equal>   

<%-- BEGIN HEADING TABLE --%>
<table width='98%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/> <bean:message key="title.assignment"/> <bean:message key="title.activation"/>
			<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
				<bean:message key="title.confirmation"/>
			</logic:equal>
			<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
				<bean:message key="title.summary"/>
			</logic:equal>      
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table align='center' width="98%" border="0">
	<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
		<tr>
			<td align="center" class="confirm">Casefile Assignment Activated.</td>
		</tr>
	</logic:equal>

	<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
		<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
			<logic:notEqual  name="casefileActivationForm" property="casefileAlreadyActivated" value="true">
				<tr>
					<td>
						<ul><li>Enter Expected End Date and select the Next button to activate case.</li></ul>
					</td>
				</tr>
				<tr>	
					<td class="required">&nbsp;<bean:message key="prompt.diamond"/> Required Fields&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
				</tr>
			</logic:notEqual>
			<logic:equal  name="casefileActivationForm" property="casefileAlreadyActivated" value="true">
				<tr>
					<td align='center' class='confirm'>Casefile has already been activated and cannot be activated again</td>
				</tr>
			</logic:equal>
		</logic:notEqual>
	</logic:notEqual>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<html:form action="handleCasefileActivation.do" target="content">
<html:hidden property="supervisionTypeId" />

<%-- BEGIN SET INDICATOR TO HANDLE UNIQUE SUPERVISION ID --%>
<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>">
	<bean:define id="AdjSupervisionCategory" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>" />
</logic:equal>
<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
	<bean:define id="AdjSupervisionCategory" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>" />
</logic:equal>
<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM %>">
	<bean:define id="AdjSupervisionCategory" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>" />
</logic:equal>
<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT %>">
	<bean:define id="AdjSupervisionCategory" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>" />
</logic:equal>
<%-- END SET INDICATOR TO HANDLE UNIQUE SUPERVISION ID --%>
<%-- BEGIN edit detail TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign='top' class='detailHead' colspan="4"><bean:message key="prompt.casefile"/> <bean:message key="prompt.information"/></td>
	</tr>
	<tr>
		<td align="center">
<%-- BEGIN INNER TABLE --%>
			<table width="100%" cellpadding="4" cellspacing="1">       
				<tr>
					<logic:notEqual name="casefileActivationForm" property="action" value="<%=naming.UIConstants.UPDATE%>">
						<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.expectedSupervisionEndDate"/></td>
						<logic:notPresent name="AdjSupervisionCategory">
							<td class='formDe' colspan='3'>
								<bean:write name="casefileActivationForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />
							</td>
						</logic:notPresent>	
						<logic:present name="AdjSupervisionCategory">
							<td class='formDe' colspan='3'>
								<bean:write name="casefileActivationForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />
							</td>
					<%--		<td class='formDeLabel' nowrap='nowrap'> <bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
							<td class='formDe'>
								<bean:write name="casefileActivationForm" property="courtOrderedProbationStartDateStr" />
							</td>  --%>
						</logic:present>							
					</logic:notEqual>
					<logic:equal name="casefileActivationForm" property="action" value="<%=naming.UIConstants.UPDATE%>">
						<logic:equal  name="casefileActivationForm" property="casefileAlreadyActivated" value="false">
							<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.expectedSupervisionEndDate"/></td>
							<logic:notPresent name="AdjSupervisionCategory">
								<td class='formDe' colspan='3'>
									<html:text name="casefileActivationForm" property="supervisionEndDateStr" styleId="supervisionEndDateStr" size="10" maxlength="10" />						    		      
									
								</td>
							</logic:notPresent>	
							<logic:present name="AdjSupervisionCategory">
								<td class='formDe' colspan='3'>
									<html:text name="casefileActivationForm" property="supervisionEndDateStr" styleId="supervisionEndDateStr" size="10" maxlength="10" />						    		      
									
								</td>
						<%--		<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
						 		<td class='formDe'>
									<html:text name="casefileActivationForm" property="courtOrderedProbationStartDateStr" size="10" maxlength="10" readonly='true'/>						    		      
									<a href="#" onClick="cal1.select(document.forms[0].courtOrderedProbationStartDateStr,'anchor2','MM/dd/yyyy'); return false;"
										NAME='anchor2' ID='anchor2'><bean:message key='prompt.2.calendar'/></a>
								</td>	 --%>
							</logic:present>	 
						</logic:equal>
						<logic:equal  name="casefileActivationForm" property="casefileAlreadyActivated" value="true">
   							<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.expectedSupervisionEndDate"/></td>
							<logic:notPresent name="AdjSupervisionCategory">
								<td class='formDe' colspan='3'>
									<bean:write name="casefileActivationForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />
								</td>
							</logic:notPresent>	
							<logic:present name="AdjSupervisionCategory">
								<td class='formDe' colspan='3'>
									<bean:write name="casefileActivationForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />
								</td>
						<%--		<td class='formDeLabel' nowrap='nowrap'> <bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
								<td class='formDe'>
									<bean:write name="casefileActivationForm" property="courtOrderedProbationStartDateStr" />
								</td>   --%>
							</logic:present>
   						</logic:equal>
					</logic:equal>
				</tr>
				<tr>
					<td valign='top' class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.juvenile"/> <bean:message key="prompt.master"/> <bean:message key="prompt.status"/></td>
					<td valign='top' class='formDe'><bean:write name="casefileActivationForm" property="juvMasterStatus"/></td>
					<td valign='top' class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.dateOfBirth"/></td>
					<td valign='top' class='formDe'><bean:write name="casefileActivationForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
				</tr>
				<tr>
					<td valign='top' class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.maysi" />&nbsp;<bean:message key="prompt.needed"/>?</td>
					<td valign='top' class='formDe' colspan="3"><bean:write name="casefileActivationForm" property="maysi"/></td>
					<!-- 
					<td valign='top' class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.risk" />&nbsp;<bean:message key="prompt.analysis" />&nbsp;<bean:message key="prompt.needed" />?</td>
					<td valign='top' class='formDe'><bean:write name="casefileActivationForm" property="riskAnalysis" /></td>
					-->
				</tr>
				
				<!-- 
				<tr>
					<td valign='top' class='formDeLabel' nowrap='nowrap' width="1%">Title IV-E&nbsp;<bean:message key="prompt.assessment" />&nbsp;<bean:message key="prompt.needed" />?</td>
					<td valign='top' class='formDe' colspan="3"><bean:write name="casefileActivationForm" property="titelIVcompleted"/></td>
				</tr>
				-->
				
				<logic:present name="AdjSupervisionCategory">
			<%-- 	<tr id="contRef" class="hidden"> --%>
					<tr>
						<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
							<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
								<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.controlling"/> <bean:message key="prompt.referral"/> </td>
								<td class="formDe" colspan='4'>
									<html:select property="controllingReferral" name="casefileActivationForm">
										<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
										<html:optionsCollection property="referrals" value="referralNumber" label="referralNumber"  name="casefileActivationForm"/>
									</html:select>	
								</td>
							</logic:notEqual>	
						</logic:notEqual>	
						<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
						    <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.controlling"/> <bean:message key="prompt.referral"/> </td>
							<td class="formDe" colspan='4'><bean:write name="casefileActivationForm" property="controllingReferral" /></td>
						</logic:equal>
						<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
						    <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.controlling"/> <bean:message key="prompt.referral"/> </td>
							<td class="formDe" colspan='4'><bean:write name="casefileActivationForm" property="controllingReferral" /></td>
						</logic:equal>
					</tr>
				</logic:present>

				<tr>
					<td valign='top' class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.juvenile"/> <bean:message key="prompt.picture"/></td>
					<td valign='top' class='formDe' colspan="3">
<%-- BEGIN PICTURE TABLE --%>
						<table>
							<tr>
								<td valign='top' class='formDe'>
									<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
										<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)"  > 
											<img alt="Mug Shot Not Available" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>" width="128" border='1'> 
										</a>
									</logic:notEmpty>

									<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
										<b>Juvenile has no photos</b>
									</logic:empty>
								</td>
							</tr>
						</table>
<%-- END PICTURE TABLE --%>
					</td>
				</tr> 
  			</table>
<%-- END INNER TABLE --%>
		</td>
	</tr> 
</table>
<%-- END CASEFILE TABLE --%>
<%-- coding to show/hide controlling Referral dwop down and Court-Order Probation Start Date based on supervisionCategoryId --%>
<%--	<html:hidden name="juvenileCasefileForm" property="supervisionCategoryId" styleId="supCatId"/>			
	<script type="text/javascript">
		var fld = document.getElementById("supCatId").value;
		if (fld == "<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>" ||
			fld == "<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>" ||
			fld == "<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM %>" )  {
			show("contRef", 1);
		} 
</script>  --%>
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
				<html:submit styleId="viewCasefileDetails" property="submitAction">
						<bean:message key="button.casefileDetails"></bean:message>
				</html:submit><br></br>
				<a href="/appshell/displayHome.do"><bean:message key="button.taskList"/></a>
			</logic:equal>
			<logic:notEqual name="casefileActivationForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
				<logic:notEqual name="casefileActivationForm" property="casefileAlreadyActivated" value="true">
					<logic:equal name="casefileActivationForm" property="action" value="<%=naming.UIConstants.UPDATE%>">
						<logic:present name="AdjSupervisionCategory">
							<html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form,'Y'); "><bean:message key="button.next"/></html:submit>
						</logic:present>
						<logic:notPresent name="AdjSupervisionCategory">
							<html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form,'N'); "><bean:message key="button.next"/></html:submit>
						</logic:notPresent>
						
<%-- 					   <logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ %>">
						   <html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form,'Y'); "><bean:message key="button.next"/></html:submit>
					   </logic:equal>
					   <logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
						   <html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form, 'Y');"><bean:message key="button.next"/></html:submit>
					   </logic:equal>
					   <logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM %>">
						   <html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form, 'Y');"><bean:message key="button.next"/></html:submit>
					   </logic:equal>

					   <logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>">
					      <logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
					         <logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM %>">
						         <html:submit title="Next" alt="5" property="submitAction" onclick="return validateInputs(this.form, 'N');"><bean:message key="button.next"/></html:submit>
					         </logic:notEqual>
					      </logic:notEqual>
					   </logic:notEqual>	--%>
						<html:submit onclick="spinner()" titleKey="button.cancel" alt="1" property="submitAction"><bean:message key="button.cancel"/></html:submit>
					</logic:equal>
			
					<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.UPDATE%>">
						<html:submit onclick="spinner()"  title="Back" alt="5" property="submitAction"><bean:message key="button.back"/></html:submit>
						<html:submit onclick="spinner()" title="Activate" alt="5" property="submitAction"><bean:message key="button.activate"/></html:submit>
						<logic:notEqual  name="casefileActivationForm" property="action" value="<%=naming.UIConstants.SUMMARY%>">
							<a href="/appshell/displayHome.do"><bean:message key="button.taskList"/></a>
						</logic:notEqual>
					</logic:notEqual>
				</logic:notEqual>
			
				<logic:equal  name="casefileActivationForm" property="casefileAlreadyActivated" value="true">
					<a href="/appshell/displayHome.do"><bean:message key="button.taskList"/></a>
				</logic:equal>
			</logic:notEqual>
		</td>
	</tr>  
</table>
<%-- END BUTTON TABLE --%>

</html:form>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>