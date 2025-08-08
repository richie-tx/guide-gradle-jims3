<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/29/2008  Mo Chowdhury    Convert to JSP  -->
<!-- 03/02/2010  C Shimek        Added onload to reset selection and commented out Cancel button because it currently does nothing - made these changes while testing defect 64141  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
<title><bean:message key="title.heading" /> - posttrial/cscdTask.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function checkSelection(theForm){
	if (theForm.searchById.selectedIndex == 0){
		alert("To selection required.");
		theForm.searchById.focus();
		return false;
	}
	return true;
}
function setDefaultSelect(){
	var fld = document.getElementsByName("searchById");
	fld[0].selectedIndex = 0;
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="setDefaultSelect()">
<html:form action="/displayWorkGroupPosition" target="content" focus="searchById">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|8">
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->    	
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">	
					<tiles:put name="tab" value="tasksTab" />					
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">
									<bean:message key="title.CSCD" /> - <bean:message key="button.createTask"/>
								</td>						
							</tr>
						</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Enter required fields. Click Next.</li>
									</ul>
								</td>
							</tr>		
							<tr>
								<td class="required" colspan="2"><bean:message key="prompt.requiredFields"/></td>												
							</tr>								
						</table>
<!-- END INSTRUCTION TABLE -->																					
<!-- BEGIN SEARCH TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
							    <td><bean:message key="prompt.taskInfo" /></td>
						    </tr>  
						    <tr>
						    	<td>
						    		<table cellpadding=2 cellspacing=1 width=100%> 								
										<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.to" /> </td>
											<td class="formDe">
												<html:select name="cscdTaskForm" property="searchById">
													<html:option value=""><bean:message key="select.generic"/></html:option>
													<html:option value="Position">POSITION</html:option>
													<html:option value="WorkGroup" >WORKGROUP</html:option>
												</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
<!-- END SEARCH TABLE -->									
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="return checkSelection(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>
						<%-- 			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>  --%>
								</td>
							</tr>										  	
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr> 
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>