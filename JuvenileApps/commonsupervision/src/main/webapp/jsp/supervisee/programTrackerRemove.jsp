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
<title><bean:message key="title.heading" /> - supervisee/programTrackerRemove.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();

	function validateField(theForm) {
		
		if(theForm.programTrackerEndDate.value == "") {
			alert("End Date is required.");
		   	theForm.programTrackerEndDate.focus();
		   	return false;			
		} 
		if (theForm.programTrackerEffectiveDate.value.length > 0) {
		   	
	   		var isEndDateGTEffectiveDate = compareDate1GreaterDate2(theForm.programTrackerEndDate.value, theForm.programTrackerEffectiveDate.value);
	   	
			if(!isEndDateGTEffectiveDate) {
				alert('End Date must be greater than the effective date of: ' + theForm.programTrackerEffectiveDate.value);
   				theForm.programTrackerEndDate.focus();
   				return false;
			}
	   		var currentDate = getCurrentDate();
	   		var isEndDateGTCurrentDate = compareDate1GreaterDate2(theForm.programTrackerEndDate.value, currentDate);
	   		
			if(isEndDateGTCurrentDate) {
				alert('End Date cannot be greater than today\'s date of: ' + currentDate);
   				theForm.programTrackerEndDate.focus();
   				return false;
			}
		} else {
			alert('Effective Date missing' + currentDate);
				theForm.programTrackerEndDate.focus();
				return false;
		}
	}   	

</script>
</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayRemoveProgramTrackerSummary" target="content" focus="programTrackerEndDate">

<html:hidden name="superviseeForm" property="programTrackerEffectiveDate"/>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
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
							<table width=98% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">										    
										    <tiles:put name="tab" value="SuperviseeTab"/> 
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
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
										<table width="100%">
											<tr>
												<td class="header"align="center"><bean:message key="title.CSCD" />&nbsp;-
													<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.UPDATE%>">
														<bean:message key="prompt.correct"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|14">
													</logic:equal>
													<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CREATE%>">
														<bean:message key="prompt.add"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|11">
													</logic:equal>
													<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.REMOVE%>">
														<bean:message key="prompt.remove"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|17">
													</logic:equal>
												    <bean:message key="prompt.program"/> <bean:message key="prompt.tracker"/>
												</td>
											</tr>	
										</table>	
<!-- BEGIN ERROR TABLE -->
				                        <table width="98%" align="center">
					                        <tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTIONS TABLE -->
										<table width="98%">
											<tr>
												<td>
													<ul>
														<li>Enter required fields and click Submit.</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class=required>
													<bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>
												</td>
											</tr>
										</table>			
<!-- END INSTRUCTIONS TABLE -->
<!-- BEGIN PROGRAM TACKER INFORMATION TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class=borderTableBlue>
											<tr>
												<td class="detailHead" colspan="2"><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.tracker"/>&nbsp;<bean:message key="prompt.information"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.effectiveDate"/></td>
															<td class="formDe"><bean:write name="superviseeForm" property="programTrackerEffectiveDate" formatKey="format.date.mmddyyyy" /></td>
														</tr>	
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.endDate"/></td>
															<td class="formDe">
 															 <html:text name="superviseeForm" property="programTrackerEndDate" maxlength="10" size="10" />
															 <A HREF="#" onClick="cal1.select(document.forms[0].programTrackerEndDate,'anchor1','MM/dd/yyyy'); return false;"
																NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A> 
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.program"/> <bean:message key="prompt.tracker"/> <bean:message key="prompt.name"/></td>
															<td class="formDe"><bean:write name="superviseeForm" property="programTrackerDesc" />
															</td>
													</tr>

												</table>
											</td>
										</tr>
									</table>
<!-- ENd PROGRAM TACKER INFORMATION TABLE -->
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
			</table>
<!-- END BLUE BORDER TABLE -->					
		</td>
	</tr>
</table>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>