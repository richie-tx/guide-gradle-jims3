<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 05/28/2008	 D Williamson	Create JSP -->

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
<title><bean:message key="title.heading" /> - supervisee/losEntry.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">

	function validateField(theForm) {
		
		if(theForm.effectiveDate.value == "") {
			alert("Effective Date is required.");
		   	theForm.effectiveDate.focus();
		   	return false;			
		} 
		
		if (!isDate(theForm.effectiveDate.value)) {
			alert("Effective Date is invalid. Valid format is mm/dd/yyyy.");
			theForm.effectiveDate.focus();
			return false;
		}
		
		
		if(theForm.supervisionLevel != null) {
			if(theForm.supervisionLevel.selectedIndex == "") {		
	     		alert("Please select Supervision Level.");
	    		theForm.supervisionLevel.focus();
	    		return false;
	   		}
	   	}
	   	
	   	if (theForm.mostRecentEffectiveDate.value.length > 0) {
	   	
	   		var currentDate = getCurrentDate();
	   		//var isEffectiveDateGTECurrentDate = compareDate1GreaterEqualDate2(theForm.effectiveDate.value, currentDate);
	   		var isEffectiveDateGTPriorEffectiveDate = compareDate1GreaterDate2(theForm.effectiveDate.value, theForm.mostRecentEffectiveDate.value);
	   	
			//if(!isEffectiveDateGTECurrentDate) {
			//	alert('Effective Date must be greater then or equal to today\'s date of: ' + currentDate);
    		//	theForm.supervisionLevel.focus();
    		//	return false;
			//} else {
				if(!isEffectiveDateGTPriorEffectiveDate) {
					alert('Effective Date must be greater than the prior LOS history\'s effective date of: ' + theForm.mostRecentEffectiveDate.value);
    				theForm.supervisionLevel.focus();
    				return false;
				}
			//}	   	
		
		}
	   	
	}   	

</script>



</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayAddLOSSummary" target="content" focus="effectiveDate">

<html:hidden name="superviseeForm" property="mostRecentEffectiveDate"/>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign=top>
				<table width=100% border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top>
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					   		   <tiles:put name="tab" value="caseloadTab"/> 
				     	    </tiles:insert>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table>
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
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
							<!--casefile tabs start-->
							<table width=98% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign=top>
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">										    
										    <tiles:put name="tab" value="SuperviseeTab"/> 
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
								<tr>
									<td valign=top align=center>
										<!-- BEGIN HEADING TABLE -->
										<div class=header><bean:message key="title.CSCD" />&nbsp;-
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.UPDATE%>">
												<bean:message key="prompt.correct"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|7">
											</logic:equal>
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CREATE%>">
												<bean:message key="prompt.add"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|5">
											</logic:equal>
										    <bean:message key="prompt.LOS"/></div>
                                            <!-- BEGIN ERROR TABLE -->
				                        <table width="98%" align="center">
					                        <tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>
				                        <!-- END ERROR TABLE -->
										<div class=instructions><li>Enter required fields and click Submit.</li></div>
										<div class=spacer></div>
										<div class=required><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
										<div class=spacer></div>
										<!-- END HEADING TABLE -->
										<!-- BEGIN LOS NAME INFORMATION TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class=borderTableBlue>
											<tr>
												<td class="detailHead" colspan="2"><bean:message key="prompt.LOS"/>&nbsp;<bean:message key="prompt.information"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class=formDeLabel nowrap width=15%><bean:message key="prompt.2.diamond"/><bean:message key="prompt.effectiveDate"/></td>
															<td class=formDe>
                                                             <SCRIPT LANGUAGE="JavaScript" ID="js1">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															 </SCRIPT>
															 <html:text name="superviseeForm" property="effectiveDate" maxlength="10" size="10" />
															 <A HREF="#" onClick="cal1.select(document.forms[0].effectiveDate,'anchor1','MM/dd/yyyy'); return false;"
																NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A> </td>
														</tr>
														<tr>
															<td colspan=2 class=formDeLabel nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.supervisionLevel"/></td>
														</tr>
														<tr>
															<td colspan=2 class=formDe>
                                                                <html:select property="supervisionLevel" size="1">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="superviseeForm" property="supervisionLevelList" value="code" label="description" />
																</html:select>        
																
														</td>
													</tr>

													<tr>
														<td class=formDeLabel><bean:message key="prompt.comments"/></td>
														<td class=formDe><html:text property="losComments" maxlength="50" size="50" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!-- END LOS INFORMATION TABLE -->
									<!--button start -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return (validateField(this.form));"> <bean:message key="button.submit" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!--button end -->
								</td>
							</tr>
						</table>
						<div class=spacer4px></div>
						<!-- END DETAIL TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
