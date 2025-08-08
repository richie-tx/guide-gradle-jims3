<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/16/2007	 Hien Rodriguez - Create JSP -->
<!-- 06/07/2007  C Shimek		- #43382 revised ConfirmDelete() to set value so Delete button functions -->
<!-- 02/06/2008  L. Deen 		- defect#49259 fixed page titles to match prototypes -->
<!-- 10/29/2008  C Shimek		- defect#55216 added logic tags for action=popup -->
<!-- 12/30/2008  C Shimke       - defect#56216 added sort to users by name -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - manageWorkgroup/details.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">

function confirmDelete(){
	
	if (confirm("Are you sure you want to delete this Workgroup?")){
		var thisForm = document.forms[0];
		thisForm["secondaryAction"].value="delete";
		return true;
	}
	return false;
}

</script>

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitWorkgroupCreateUpdate" target="content">
<logic:equal name="workgroupForm" property="action" value="view">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|12">
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|11">										
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|6">										
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="delete">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|13">										
</logic:equal>
<input type="hidden" name="secondaryAction" value="" >
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
    	<logic:notEqual name="workgroupForm" property="action" value="popup">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="workgroupsTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								<!-- BEGIN HEADING TABLE -->
									<table width=98%>
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;
												<logic:equal name="workgroupForm" property="action" value="view">
													<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.details" />
												</logic:equal>
												<logic:equal name="workgroupForm" property="action" value="create">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.summary" />										
												</logic:equal>
												<logic:equal name="workgroupForm" property="action" value="update">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.summary" />										
												</logic:equal>
												<logic:equal name="workgroupForm" property="action" value="delete">
													<bean:message key="prompt.delete" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="prompt.summary" />										
												</logic:equal>
											</td>
										 </tr>		
									</table>									
								<!-- END HEADING TABLE -->								
								<!-- BEGIN ERROR TABLE -->
									<table width=98% align=center>							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
								<!-- END ERROR TABLE -->
								<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<logic:equal name="workgroupForm" property="action" value="view">
												<td><ul>
													<li>Click the appropriate button to Update or Delete this workgroup.</li>
												</ul></td>
											</logic:equal>
											<logic:equal name="workgroupForm" property="action" value="create">
												<td><ul>
													<li>Review entries and click Finish to create workgroup.</li>
												</ul></td>
											</logic:equal>
											<logic:equal name="workgroupForm" property="action" value="update">
												<td><ul>
													<li>Review entries and click Finish to update workgroup.</li>
												</ul></td>
											</logic:equal>
											<logic:equal name="workgroupForm" property="action" value="delete">
												<td><ul>
													<li>Review entries and click Finish to delete workgroup.</li>
												</ul></td>
											</logic:equal>				
										</tr>
									</table>
								<!-- END INSTRUCTION TABLE -->
			</logic:notEqual>					
								<!-- BEGIN WORKGROUP INFO TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class="detailHead">
											<td class="detailHead" ><bean:write name="workgroupForm" property="workgroupName" />&nbsp;<bean:message key="prompt.info" /></td>				                        	
											<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>				                          
										</tr>
										<tr>
											<td colspan=2>
												<table width=100% cellpadding=4 cellspacing=1 border=0>	
													<tr>				                          	
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.workgroupName" /></td>
														<td class="formDe"><bean:write name="workgroupForm" property="workgroupName" /></td>		
													</tr>
													<tr>				                          	
														<td class="formDeLabel"><bean:message key="prompt.description" /></td>
														<td class="formDe"><bean:write name="workgroupForm" property="workgroupDescription" /></td>		
													</tr>
													<tr>				                          	
														<td class="formDeLabel"><bean:message key="prompt.type" /></td>
														<td class="formDe"><bean:write name="workgroupForm" property="workgroupTypeDesc" /></td>
														<%-- %><td class="formDe"><bean:write name="workgroupForm" property="workgroupTypeDesc" /></td>--%>		
													</tr>
												</table>
											</td>
										</tr>
									</table>
								<!-- END WORKGROUP INFO TABLE -->			
								<br>
								<!-- BEGIN WORKGROUP USERS TABLE -->
									<table width=98% border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td class="detailHead" ><bean:write name="workgroupForm" property="workgroupName" />&nbsp;<bean:message key="prompt.users" /></td>				                        	
											<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>				                          
										</tr>
										<tr>
											<td colspan=2>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td><bean:message key="prompt.name" />
													 		<jims2:sortResults beanName="workgroupForm" results="userSelectedList" primaryPropSort="formattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" hideMe="true"/> 
														</td>
														<td><bean:message key="prompt.jobTitle" /></td>
														<td><bean:message key="prompt.division" /></td>
														<td><bean:message key="prompt.programUnit" /></td>
														<td><bean:message key="prompt.positionType" /></td>
													</tr>
													<logic:notEmpty name="workgroupForm" property="userSelectedList"> 
														<logic:iterate id="workgroupUserListIndex" name="workgroupForm" property="userSelectedList" indexId="index">
															<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																<td><bean:write name="workgroupUserListIndex" property="formattedName" /></td>
																<td><bean:write name="workgroupUserListIndex" property="jobTitleDesc" /></td>
																<td><bean:write name="workgroupUserListIndex" property="divisionDesc" /></td>
																<td><bean:write name="workgroupUserListIndex" property="programUnitDesc" /></td>
																<td><bean:write name="workgroupUserListIndex" property="positionTypeDesc" /></td>
															</tr>										                
														</logic:iterate>
													</logic:notEmpty>  
												</table>
											</td>
										</tr>
									</table>
								<!-- END WORKGROUP USERS TABLE -->
									
							<!-- END SUMMARY PAGE SECTION -->
								<br>
                      			<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<logic:notEqual name="workgroupForm" property="action" value="popup">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													
													<logic:equal name="workgroupForm" property="action" value="view">
														<jims2:isAllowed requiredFeatures="CS-WORKGRP-UPDATE">
															<input type="submit" name="submitAction" onclick="return (changeFormActionURL(this.form.name, '/<msp:webapp/>handleWorkgroupSelection.do?submitAction=<bean:message key="button.update"/>',false) && disableSubmit(this, this.form));" value='<bean:message key="button.update"/>'></input>&nbsp;
														</jims2:isAllowed>
														
														<logic:equal name="workgroupSearchForm" property="openedTaskInd" value="false">
															<jims2:isAllowed requiredFeatures="CS-WORKGRP-DELETE">	
																<html:submit property="submitAction" onclick="return confirmDelete() && disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message></html:submit>&nbsp;
															</jims2:isAllowed>
														</logic:equal>
													</logic:equal>
													
													<logic:notEqual name="workgroupForm" property="action" value="view">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													</logic:notEqual>
													
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</logic:notEqual>
												<logic:equal name="workgroupForm" property="action" value="popup">
													<input type="button" name="close" value="Close Window" onclick="window.close()"
												</logic:equal>
											</td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
			<logic:notEqual name="workgroupForm" property="action" value="popup">		
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
			</table>
			</logic:notEqual>	
		</td>
	</tr>
</table>

</div>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>