<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- 02/28/2008	LDeen		Activity #49819 integrate help    --%>
<%-- 03/27/2009 CShimek     #581925 correct spelling of Liaison --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

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
	<title>CommonSupervision/administerCaseload/administerCaseloadReassignSelect.jsp</title>
	
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>	
</head>
	
<body topmargin="0" leftmargin="0">
	<html:form action="/superviseeReassignmentAction" target="content">		
	  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|4">
			
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
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;Reassign Supervisee</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select reassignment type.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->

									<div class="spacer4px"></div>
									<!-- begin select TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Reassign Supervisee</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr><td class="formDe">
															<input type="radio" name="reassignmentType" value="off"> to another Officer
														</td></tr>
													<tr><td class="formDe">
															<input type="radio" name="reassignmentType" value="pu"> to another Program Unit
														</td></tr>
													<tr><td class="formDe">
															<input type="radio" name="reassignmentType" value="clo"> to a Court Liaison Officer
														</td></tr>
												</table>
												</td>
											</tr>
									</table>
									<!-- end select TABLE -->
									<div class="spacer4px"></div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
												<html:submit property="submitAction" onclick="return validateRadios(this.form, 'reassignmentType', 'Please select a reassignment type.', '');">
													<bean:message key="button.next" />
												</html:submit> 
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
												</html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->									
								</td>
							</tr>
						</table>
						<!-- END DETAIL TABLE -->
					</td>
				</tr>
			</table>
			<!-- END  TABLE -->
		</div>
		<br>
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>