<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/04/2008  C. Shimek   activity#51908 added AM/PM drop down and revised validation accordingly -->
<!-- 06/19/2008  C. Shimek   corrected PM calculation to not add 12 to 12:xx PM time -->
<!-- 08/21/2008	 C. Shimek   defect#52794 revised buttons per defect, mainly adding reset -->
<!-- 12/20/2009  C. Shimek   activity#51908 reopenned - added time example to casenote time popup message -->
<!-- 02/09/2009  C. Shimek   #56973 added missing spellcheck script call -->
<!-- 02/19/2009  C. Shimek   #57332 revise casenote time edit to correctly edit when input hour = 12 -->
<!-- 05/26/2010  C. Shimek   #65373 removed validateForBROnly() call which was causing new line error in casenote textarea -->
<!-- 03/17/2011  C. Shimek   #69626 corrected syntax error on Add Associate href -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@page import="naming.UIConstants"%>

<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title></title>
<script language="JavaScript"
	src="/<msp:webapp/>js/common_supervision_util.js"
	type="text/javascript"></script>

</head>
<table width="98%" border="0" cellspacing="0" cellpadding="2"
	class="borderTableBlue">
	<tr class="detailHead">
		<td>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="detailHead"><bean:message key="prompt.new" />&nbsp;<bean:message
					key="prompt.casenoteInfo" /></td>
				<td align="right"><img src="/<msp:webapp/>images/step_2.gif"
					alt="" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" cellpadding="2" cellspacing="1">
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
					key="prompt.3.diamond" /><bean:message key="prompt.casenoteDate" />
				<!-- value for casenote date validation --> <input type="hidden"
					name="sprvnBeginDate"
					value='<bean:write name="complianceForm" property="supervisionPeriodBeginDateAsString"/>'>
				</td>
				<td class="formDe"><html:text name="complianceForm"
					property="casenoteDateAsString" maxlength="10" size="10" /> <a
					href="#"
					onClick="cal1.select(casenoteDateAsString,'anchorCN','MM/dd/yyyy'); return false;"
					NAME="anchorCN" ID="anchorCN" border="0"><bean:message
					key="prompt.3.calendar" /></a> <script type="text/javascript">
								var elem1=document.getElementById('casenoteDateAsString');
								elem1.value = getCurrentDate();
							</script></td>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
					key="prompt.3.diamond" /><bean:message key="prompt.casenoteTime" /></td>
				<td class="formDe"><html:text name="complianceForm"
					property="casenoteTime" maxlength="5" size="5" /> <html:select
					name="complianceForm" property="AMPMId">
					<option value="AM">AM</option>
					<option value="PM">PM</option>
				</html:select> <script type="text/javascript">
							var elem2=document.getElementById('casenoteTime');
							elem2.value = getCurrentTime12Hour();
							var ap = convertTimeto2DigitHr(getCurrentTime(false));
							var hm = ap.split(":");
							if (hm[0] > 11){
								var elem3 = document.getElementById("AMPMId");
								elem3.selectedIndex = 1;
							}
						</script></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.3.diamond" /><bean:message
					key="prompt.contactMethod" /></td>
				<td class="formDe" colspan="3"><html:select
					name="complianceForm" property="contactMethodId" size="1">
					<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection name="complianceForm"
						property="contactMethodList" value="code" label="description" />
				</html:select></td>
			</tr>
			<tr>
				<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message
					key="prompt.3.diamond" /><bean:message
					key="prompt.casenoteSubjects" /></td>
				<td class="formDe" colspan="3"><html:select
					name="complianceForm" property="subjectIds" size="5"
					multiple="true">
					<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection name="complianceForm"
						property="casenoteSubjectList" value="code" label="description" />
				</html:select></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.collateral" /></td>
				<td class="formDe" colspan="3">
				<table cellpadding="2" cellspacing="0">
					<tr>
						<td valign="top">
							<html:select name="complianceForm" property="collateralId"
								onchange="showHideCollateralAssociates(this.value, 'collateralAssociates', 'row');">
								<html:option value=""> <bean:message key="select.generic" /></html:option>
								<html:optionsCollection name="complianceForm" property="collateralList" value="code" label="description" />
							</html:select>
						</td>
						<td id="collateralAssociates" class="hidden">
							<html:select name="complianceForm" property="selectedAssociateIds" size="3" multiple="true">
								<html:option value=""><bean:message key="select.generic" /></html:option>
								<html:optionsCollection name="complianceForm" property="associatesList" value="associateId" label="displayLabel" />
							</html:select>
							<a href="javascript:changeFormActionURL(document.forms[0].name,
								'/<msp:webapp/>complianceAssociateCasenoteDisplay.do?submitAction=<bean:message key="button.link"/>
								&amp;selectedValue=<bean:write name="complianceForm" property="superviseeId" />
								&amp;fromPath=<bean:write name="complianceForm" property="compliancePage" />',true)">
								<bean:message key="button.addAssociate" /> </a>
						</td>		
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="formDeLabel"><bean:message
					key="prompt.3.diamond" /><bean:message key="prompt.casenote" /></td>
			</tr>
		</table>
		<table width="100%" cellpadding="2" cellspacing="0">
			<tr class="formDe">
				<td><nest:define id="userAgency" name="complianceForm"
					property="agencyId" /> <html:textarea styleClass="mceEditor"
					name="complianceForm" property="casenoteText" style="width:100%"
					rows="15" ondblclick="myReverseTinyMCEFix(this)" /> <tiles:insert
					page="../../common/spellCheckButtonTile.jsp" flush="false">
					<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
				</tiles:insert></td>
			</tr>
			<table>
				<td></td>
				<tr></tr>
			</table>
			<!-- BEGIN BUTTON TABLE  -->
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td><img src="/<msp:webapp/>js/images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td align="center"><script type="text/javascript">
			function validateFields(theForm) {
				  clearAllValArrays();		      
				  trimCasenote('casenoteText');
				customValRequired("casenoteDateAsString","Casenote Date is required","");
				addMMDDYYYYDateValidation("casenoteDateAsString","Casenote Date must be in the MM/DD/YYYY format","");
				add12HrTimeValidation("casenoteTime","Casenote Time is not in proper 12 hour format, ie 03:15","");
				customValRequired("casenoteTime","Casenote Time is required","");
				customValRequired("contactMethodId", "Casenote Method is required","");
				customValRequired("subjectIds","Casenote Subjects are required","");
				customValRequired("casenoteText","Casenote Text is required","");
				addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
				customValMaxLength('casenoteText','Casenote Text cannot be more than 3500 characters',7000);
				if (validateCustomStrutsBasedJS(theForm) ){
			  		return true;
				}else {
					return false;
				}
			}
			</script> <script type="text/javascript">
				function validateCasenoteEntries(theForm){
				// compare date and time to supervision period begin date and time	
					var curDateTime = new Date();
					var casenoteTime = theForm.casenoteTime.value;
					var theTime = casenoteTime.split(':');
					var hr = Number(theTime[0]);
					if (theForm.AMPMId.selectedIndex == 1){
						if (hr < 12){
							hr = hr + 12;
						}
					} else { 
						if (theTime[0] == 12){
							hr = "00";
						}
					} 
					casenoteTime = hr + ":" + theTime[1];
					var dt = theForm.casenoteDateAsString.value + " " + casenoteTime;
					var casenoteDateTime = new Date(dt);
					var sprBegDate = theForm.sprvnBeginDate.value;
					var sprBegDateTime = new Date(sprBegDate);
					if (casenoteDateTime < sprBegDateTime)
					{
						alert("Casenote Date can not be previous to Supervision begin date " + sprBegDate.substring(0,sprBegDate.length - 3) + ".");
						theForm.casenoteDateAsString.focus();
						return false;
					}	
					if (casenoteDateTime > curDateTime)
					{
						alert("Casenote Date and Time can not be future value.");
						theForm.casenoteDateAsString.focus();
						return false;
					}
				// associate selection required if collateral selection is assocaites
					if (theForm.collateralId.value == "AS")
					{
						if (theForm.selectedAssociateIds.selectedIndex < 1)
						{
							alert("An Associate must be selected.");
							theForm.selectedAssociateIds.focus();
							return false;
						}
					}						
				}
			</script> <!-- this should only be true after adding associate --> <logic:equal
						name="complianceForm" property="confirmMessage" value="">
						<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					</logic:equal> <html:submit property="submitAction"
						onclick="return myTinyMCEFix() &amp;&amp; validateFields(this.form) &amp;&amp; validateCasenoteEntries(this.form) &amp;&amp; disableSubmit(this, this.form)">
						<bean:message key="button.next" />
					</html:submit> <html:submit property="submitAction"><bean:message key="button.reset" />
					</html:submit> <html:submit property="submitAction"><bean:message key="button.cancel" />
					</html:submit></td>
				</tr>
			</table>
			<!-- END BUTTON TABLE -->
		</table>
		</td>
	</tr>
</table>