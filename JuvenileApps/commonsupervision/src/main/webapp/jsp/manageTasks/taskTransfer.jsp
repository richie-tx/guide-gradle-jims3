<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/04/2008 R Young  -->
<!-- 10/29/2008 C Shimek    added nowrap to required "To" so it will display correctly, found while working on defect#55216  -->
<!-- 11/26/2008 L Deen		Defect #52850-added width="1%" to TD tag to required "To" so it will display correctly, found while working on defect#55216  -->
<!-- 12/09/2008 C Shimek    removed unnecessary imported js found while testing defect#52525 --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageTasks/taskTransfer.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" 	src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>

function resetForm(theSelect){
	theSelect.selectedIndex = 0
	checkWG(theSelect);
}

function checkSelection(el){
	if(document.getElementById("transferToTypeId").value == ""){
			alert("Please make your transfer To selection");
			document.getElementById("transferToTypeId").focus();
			return false;
		}
	}

</script>
</head>
<body onKeyDown="checkEnterKeyAndSubmit(event,true)">

	<div align="center">
	<html:form action="/handleTasksTransferSelection" target="content" focus="transferToTypeId">
							<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|3">
<!--  BEGIN MAIN PAGE TABLE -->	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">  
<!--  BEGIN TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign=top>
						<!-- TABS START -->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="tasksTab" />
							</tiles:insert>
						<!-- TABS END -->
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table>

<!--  END TABS TABLE -->						
<!--  BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD" /> - &nbsp;<bean:message key="prompt.transfer" /> Task</td>
								</tr>
							</table>
<!-- END HEADING TABLE --> 
<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
<!-- END ERROR TABLE --> 
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<bean:message key="prompt.2.diamond"/> &nbsp;Enter required fields. Click Next.
									</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE --> 
							
<!-- BEGIN SELECT SEARCH TABLE -->
							<!-- BEGIN DETAIL TABLE -->
										<table width="98%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
											<tr class=detailHead>
												<td>Task Information</td>

											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.2.diamond"/> &nbsp;<bean:message key="prompt.to" /></td>
															<td class=formDe>
																<select name=transferToTypeId>
																	<option>Please Select</option>
																	<option value=position>Position / User</option>
																	<option value=workgroup>Workgroup</option>
																</select>
															</td>
														</tr>
													</table>

												</td>
											</tr>
										</table>
										<!-- END DETAIL TABLE -->
										<br>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">

													<input type="button" value="Back" onClick="goNav('back')">
													<html:submit property="submitAction" onclick="return checkSelection(this.form);"><bean:message key="button.next"></bean:message>
													</html:submit> 
													<input type="button" value="Cancel" onClick="goNav('back')">
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
<!-- END SELECT SEARCH TABLE --> 
							<br>

						</td>
					</tr>
				</table>
<!--  END BLUE BORDER TABLE -->				
			</td>
		</tr>
	</table>
<!--  END MAIN PAGE TABLE -->
	
	</html:form>
	</div>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>