<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2012	 R Carter	Create JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.Features" %>
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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - supervisee/dnaView.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
function checkSelect(theForm, sentToState, currentLOS){
	if (document.getElementById("correctButton") != null) {
		show("correctButton", 0);
     	if (currentLOS == false){
           show("correctButton", 1);
     	}
	}	
		
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<html:form action="/handleDNA" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|22">
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
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
										<!-- BEGIN HEADING TABLE -->
										<div class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.view"/> <bean:message key="prompt.dna"/>&nbsp;</div>
										<div class="confirm">
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CONFIRM_UPDATE%>">
												DNA record successfully updated.
                                            </logic:equal>
									<%--		<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CONFIRM_DELETE%>">
												DNA record successfully deleted.
											</logic:equal> --%>
										</div>
										<logic:equal name="superviseeForm" property="allowDNAUpdates" value="true">
											<div class="instructions"><li>Click the appropriate button below.</li></div>
										</logic:equal>
										<div class="spacer"></div>
										<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->										
<!-- BEGIN DNA NAME INFORMATION TABLE -->
									<div class="borderTableBlue" style="width:98%">
									<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center" class="notFirstColumn">
										<tr class="detailHead">
										  <td><bean:message key="prompt.dnaCollectedDate"/></td>
										  <td><bean:message key="prompt.userName"/></td>
										  <td><bean:message key="prompt.entryDate"/></td>
										</tr>
										<tr>
											 <td><bean:write name="superviseeForm" property="dnaCollectedDate" formatKey="date.format.mmddyyyy" /></td>
											 <td><bean:write name="superviseeForm" property="dnaEntryUser"/></td>
											 <td><bean:write name="superviseeForm" property="dnaEntryDate" formatKey="date.format.mmddyyyy" /></td>
										</tr>
									</table>
<!-- END DNA INFORMATION TABLE -->
										</div>
							<!--button start -->
										<table border="0" width="1%" align="center" nowrap="nowrap">
											<tr>
												<logic:notEqual name="superviseeForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">   
													<td width="1%">
													    <html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
                                                	</td>   
												</logic:notEqual>
                                                <logic:notEmpty name="superviseeForm" property="dnaCollectedDate">	
                                                   	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_DNA_CORRECT%>'>
														<td width="1%" id="correctButton">
														<td><html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.correct"/></html:submit>
														</td>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_DNA_DELETE%>'>
														<td width="1%" id="deleteButton">	
														<td><html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.delete"/></html:submit>
														</td>
													</jims2:isAllowed>
												</logic:notEmpty>
												<logic:notEqual name="superviseeForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">         
													<td width="1%"> 
													    <html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>&nbsp;
                                                    </td>
												</logic:notEqual> 
											</tr>
										</table>
										<!--button end -->
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>
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
<!-- BEGIN NOTES
   Back and Cancel not available when confirmation message displays.
END NOTES -->
</div>
</pg:pager>
<br>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
