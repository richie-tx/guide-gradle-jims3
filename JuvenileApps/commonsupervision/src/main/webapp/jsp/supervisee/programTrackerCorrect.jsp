<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/10/2010	 D Gibler	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - supervisee/programTrackerCorrect.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();

	function validateField(theForm) {

		if(theForm.programTrackerEffectiveDate.value == "") {
			alert("Effective Date is required.");
		   	theForm.programTrackerEffectiveDate.focus();
		   	return false;			
		} 
		
		if (!isDate(theForm.programTrackerEffectiveDate.value)) {
			alert("Effective Date is invalid. Valid format is mm/dd/yyyy.");
			theForm.programTrackerEffectiveDate.focus();
			return false;
		}
	   	if (theForm.programTrackerPriorEndDate.value.length > 0) {
		   	
	   		var isEffectiveDateGTPriorEndDate = compareDate1GreaterEqualDate2(theForm.programTrackerEffectiveDate.value, theForm.programTrackerPriorEndDate.value);
	   		
	   		if(!isEffectiveDateGTPriorEndDate) {
				alert('Effective Date must be greater than or equal to the prior Program Tracker history\'s end date of: ' + theForm.programTrackerPriorEndDate.value);
   				theForm.programTrackerEffectiveDate.focus();
   				return false;
	   		} 
	   	} else if (theForm.programTrackerPriorEffectiveDate.value.length > 0){
	   			var isEffectiveDateGTPriorEffectiveDate = compareDate1GreaterEqualDate2(theForm.programTrackerEffectiveDate.value, theForm.programTrackerPriorEffectiveDate.value);
	   		
	   			if(!isEffectiveDateGTPriorEffectiveDate) {
					alert('Effective Date must be greater than or equal to the prior Program Tracker history\'s effective date of: ' + theForm.programTrackerPriorEffectiveDate.value);
   					theForm.programTrackerEffectiveDate.focus();
   					return false;
	   			} 
		}

	   	if (theForm.programTrackerEnded.value.length > 0 && theForm.programTrackerEnded.value == "Y") {
			if(theForm.programTrackerEndDate.value == "") {
				alert("End Date is required.");
		   		theForm.programTrackerEndDate.focus();
		   		return false;			
			} 
		
			if (!isDate(theForm.programTrackerEndDate.value)) {
				alert("End Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.programTrackerEndDate.focus();
				return false;
			}
			var isEndDateGTEffectiveDate = compareDate1GreaterDate2(theForm.programTrackerEndDate.value, theForm.programTrackerEffectiveDate.value);
			if (!isEndDateGTEffectiveDate) {
				alert('End Date must be greater than the effective date of: ' + theForm.programTrackerEffectiveDate.value);
				theForm.programTrackerEndDate.focus();
				return false;
			}
		   	if (theForm.programTrackerSubsequentEffectiveDate.value.length > 0) {
			   	
		   		var isEndDateGTSubsequentEffectiveDate = compareDate1GreaterEqualDate2(theForm.programTrackerSubsequentEffectiveDate.value, theForm.programTrackerEndDate.value);
		   		
		   		if(!isEndDateGTSubsequentEffectiveDate) {
					alert('End Date must be greater than or equal to the subsequent Program Tracker history\'s effective date of: ' + theForm.programTrackerSubsequentEffectiveDate.value);
	   				theForm.programTrackerEndDate.focus();
	   				return false;
		   		} 
		   	}
				
		} else if ( theForm.programTrackerSubsequentEffectiveDate.value.length > 0){
	   		var isEffectiveDateGTSubsequentEffectiveDate = compareDate1GreaterEqualDate2(theForm.programTrackerSubsequentEffectiveDate.value, theForm.programTrackerEffectiveDate.value);
	   		
	   		if(!isEffectiveDateGTSubsequentEffectiveDate) {
				alert('Effective Date must be greater than or equal to the subsequent Program Tracker history\'s effective date of: ' + theForm.programTrackerSubsequentEffectiveDate.value);
   				theForm.programTrackerEffectiveDate.focus();
   				return false;
	   		} 
		}
		
//		if(theForm.programTrackerId != null) {
			if(theForm.programTrackerId.selectedIndex == 0) {		
	     		alert("Program Tracker Name selection required.");
	    		theForm.programTrackerId.focus();
	    		return false;
	   		}
//	   	}
	   	
	}   	

</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCorrectProgramTrackerSummary" target="content" focus="programTrackerEffectiveDate">
<html:hidden name="superviseeForm" property="programTrackerPriorEffectiveDate"/>
<html:hidden name="superviseeForm" property="programTrackerSubsequentEffectiveDate"/>
<html:hidden name="superviseeForm" property="programTrackerPriorEndDate"/>
<html:hidden name="superviseeForm" property="programTrackerSubsequentEndDate"/>
<%-- <html:hidden name="superviseeForm" property="programTrackerId"/>  --%>
<html:hidden name="superviseeForm" property="programTrackerEnded"/>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->			
				<table width=""100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					   		   <tiles:put name="tab" value="caseloadTab"/> 
				     	    </tiles:insert>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->					
<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
				<tr>
					<td valign="top" align="center"> 
<!-- BEGIN GREEN TABS TABLE -->	
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
										    <tiles:put name="tab" value="SuperviseeTab" />
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->								
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td>
<!-- BEGIN HEADING TABLE -->
										 <table width="98%" align="center">
										 	<tr>
												<td class="header" align="center"><bean:message key="title.CSCD" />&nbsp;-
													<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.UPDATE%>">
														<bean:message key="prompt.correct"/>
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|14">
													</logic:equal>
													<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CREATE%>">
														<bean:message key="prompt.add"/>
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|11">
													</logic:equal>
											    <bean:message key="prompt.program"/> <bean:message key="prompt.tracker"/>
											    </td>
											</tr>
					                        <tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
											<tr>
												<td>
													<ul>
														<li>Enter required fields and click Submit.</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										    </tr>
										</table>
<!-- END HEADING TABLE -->
<!-- BEGIN PROGRAM TACKER INFORMATION TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class=borderTableBlue>
											<tr>
												<td class="detailHead" colspan="2"><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.tracker"/>&nbsp;<bean:message key="prompt.information"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.effectiveDate"/></td>
															<td class="formDe">
																 <html:text name="superviseeForm" property="programTrackerEffectiveDate" maxlength="10" size="10" />
																 <A HREF="#" onClick="cal1.select(document.forms[0].programTrackerEffectiveDate,'anchor1','MM/dd/yyyy'); return false;"
																	NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A>
															</td>
														</tr>
														<logic:equal name="superviseeForm" property="programTrackerEnded" value="Y">
															<tr>
																<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.endDate"/></td>
																<td class="formDe">
                                                             	 	<html:text name="superviseeForm" property="programTrackerEndDate" maxlength="10" size="10" />
															 		<A HREF="#" onClick="cal1.select(document.forms[0].programTrackerEndDate,'anchor2','MM/dd/yyyy'); return false;"
																		NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.2.calendar"/></A>
																</td>
															</tr>
														</logic:equal>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.program"/> <bean:message key="prompt.tracker"/> <bean:message key="prompt.name"/></td>
															<td class="formDe">
                                                                <html:select property="programTrackerId" size="1">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="superviseeForm" property="programTrackerList" value="code" label="description" />
																</html:select>        
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
<!-- END PROGRAM TACKER INFORMATION TABLE -->
<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return (validateField(this.form));"> <bean:message key="button.submit" /></html:submit>
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->						
							<div class="spacer4px"></div>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
				<br>
			</td>
		</tr>
	</table>
	<br>
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align=""center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>