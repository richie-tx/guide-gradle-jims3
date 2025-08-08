<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/05/2008	 Richard Young - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.PDCodeTableConstants" %>
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
<title><bean:message key="title.heading" /> - manageTask/searchTransferWorkgroup.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">

function validateSearch(theForm)
{	
	if (theForm.workgroupName.value == "" &&
	    theForm.workgroupTypeId.value == "")
	{		
     	alert("At least 1 search field is required for search.");
     	theForm["workgroupName"].focus();
     	return false;
   	}
	
   	return true;
}
function refreshInput(theForm)
{
	theForm.workgroupName.value = "";
    theForm.workgroupTypeId.value = "";
    theForm.workgroupName.focus();
}
</script> 

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayTransferWorkgroupSearchResults" target="content" focus="workgroupName">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|13">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<!-- BEGIN TAB TABLE --> 			
			<table width=100% border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign=top>
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
<!-- END TAB TABLE --> 						
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign=top align=center>
						
					<!--	<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>							
											<td align="center" class="header"><bean:message key="title.CSCD" /> -&nbsp;
												<bean:message key="prompt.transfer"/> Task <bean:message key="prompt.searchFor"/> Workgroups</td>
										</tr>						
									</table>
								<!-- END HEADING TABLE -->
								<!-- BEGIN ERROR TABLE -->
									<table width=98% align=center>							
										<tr>
											<td align="center" class="errorAlert"><html:errors/></td>
										</tr>		
									</table>
								<!-- END ERROR TABLE -->
								<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td><ul>
												<li>Enter at least 1 search criteria.</li>
												<li>Click Submit button to retrieve workgroups or click Create Workgroup to add a new one.</li>
											</ul></td>
										</tr>
										<tr>
											<td class="required">At least 1 is required</td>												
										</tr>
									</table>
								<!-- END INSTRUCTION TABLE -->
								<!-- BEGIN SELECT SEARCH TABLE -->
									<table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
					                	<tr>
											<td class="detailHead" colspan="2"><bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.search" /></td>
										</tr>
										<tr>
											<td>
												<table width=100% border="0" cellpadding="2" cellspacing="1">
									                <tr>
									                   	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.workgroupName"/></td>
									                    <td class="formDe"><html:text property="workgroupName" maxlength="25" size="25"/></td>
									                </tr>
								                  	<tr>
								                    	<td class="formDeLabel"><bean:message key="prompt.type" /></td>
														<td class="formDe">
															<html:select property="workgroupTypeId" size="1">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<jims:codetable codeTableName="<%=PDCodeTableConstants.WORKGROUP_TYPE%>"/>
															</html:select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									 </table>
						 		<!-- END SELECT SEARCH TABLE -->	
									<br>
								<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>&nbsp;
													
												<html:submit property="submitAction" onclick="return (validateSearch(this.form) && validateWorkgroupSearchForm(this.form) && disableSubmit(this, this.form));">
													<bean:message key="button.submit"></bean:message></html:submit>&nbsp;
													
												<input type="button" name="refresh" value=<bean:message key="button.refresh" /> onclick="refreshInput(this.form)">
																		
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>  																																		 			
											</td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						<!-- </table><br>-->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>