<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/03/2008	 C Shimek       - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="naming.UIConstants"%>
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
<title><bean:message key="title.heading" /> - manageRecords/spnSplitSelectTopic.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript">
function checkSelect(){
	if (document.forms[0].selectedTopic.selectedIndex == 0){
		alert("Topic Selection required.");
		document.forms[0].selectedTopic.focus();
		return false;
	}
	return true;	
}
function resetTopic(){
	document.forms[0].topicSelected.value = "";
	document.forms[0].selectedTopic.selectedIndex = 0;
	document.forms[0].selectedTopic.focus();
}
function setTopic(){
	if (document.forms[0].topicSelected.value != ""){
		for (x = 0; x < document.forms[0].selectedTopic.length; x++){
			if (document.forms[0].selectedTopic.options[x].value == document.forms[0].topicSelected.value){
				document.forms[0].selectedTopic.selectedIndex = x;
				document.forms[0].selectedTopic.focus();
				break;
			}	
		}	
	}	
}
</script>

<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setTopic()">
<html:form action="/displaySpnSplitSelectedTopic" target="content" focus="selectedTopic">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|3">
<input type="hidden" name="topicSelected" value=<bean:write name="spnSplitForm" property="selectedTopic"/> >
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
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
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
<!-- BEGIN SUMMARY PAGE SECTION -->
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header"><bean:message key="prompt.spnSplit" />&nbsp;-&nbsp;<bean:message key="prompt.selectTopic" /></td>
							</tr>
						</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Select a topic and click Next.</li>
									</ul>
								</td>
							</tr>									
							<tr>
								<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
							</tr>
						</table>						
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SPN INFORMATION TABLE -->									
						<tiles:insert page="../common/spnSplitInfoTile.jsp" flush="true">
							<tiles:put name="erroneousSpn" beanName="spnSplitForm" beanProperty="erroneousSpn"/>
							<tiles:put name="validSpn" beanName="spnSplitForm" beanProperty="validSpn"/>
						</tiles:insert>		
<!-- END SPN INFORMATION TABLE -->
<!-- END SUMMARY PAGE SECTION -->
						<br>
<!-- BEGIN SPN TOPIC SELECT TABLE -->						
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="formDeLabel" width="1%" nowrap>
									<bean:message key="prompt.2.diamond"/>SPN Split Topic
								</td>
								<td class="formDe">
						 			<html:select property="selectedTopic">
										<html:option value=""><bean:message key="select.generic"/></html:option>
										<option value="<%=UIConstants.ASSESSMENT%>">ASSESSMENTS</option>
										<option value="<%=UIConstants.ASSOCIATE%>">ASSOCIATES</option>
<%-- as of 8/29/08 no LOS processing - see use case document	
										<option value="<%=UIConstants.LOS%>">LOS</option>  --%>
										<option value="<%=UIConstants.SUPERVISION_PLAN%>">SUPERVISION PLANS</option>
									</html:select>
								</td>
							</tr>
						</table>
<!-- END SPN TOPIC SELECT TABLE -->							
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<logic:equal name="spnSplitForm" property="showBackButton" value="<%=UIConstants.YES %>" >
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
									</logic:equal>
									<html:submit property="submitAction" onclick="return checkSelect(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
									<input type="reset" value="Reset" name="reset" onclick="resetTopic()">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
<!--  END BLUE BORDER TABLE -->
		</td>
	</tr>
</table>
</div>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>