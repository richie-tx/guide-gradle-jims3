<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/26/2007	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
<title><bean:message key="title.heading" /> - manageWorkgroup/confirmation.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayWorkgroupSearch" target="content">
<logic:equal name="workgroupForm" property="action" value="confirmCreate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|9">										
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="confirmUpdate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|5">										
</logic:equal>
<logic:equal name="workgroupForm" property="action" value="confirmDelete">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Workgroups/Manage_Workgroups.htm#|10">										
</logic:equal>
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
												<logic:equal name="workgroupForm" property="action" value="confirmCreate">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="title.confirmation" />										
												</logic:equal>
												<logic:equal name="workgroupForm" property="action" value="confirmUpdate">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="title.confirmation" />										
												</logic:equal>
												<logic:equal name="workgroupForm" property="action" value="confirmDelete">
													<bean:message key="prompt.delete" />&nbsp;<bean:message key="prompt.workgroup" />&nbsp;<bean:message key="title.confirmation" />										
												</logic:equal>
											</td>
										 </tr>		
									</table>									
								<!-- END HEADING TABLE -->		
							<!-- BEGIN CONFIRMATION PAGE SECTION -->
								<table width=98% align=center>							
									<tr>
										<td align="center" class="errorAlert"><html:errors></html:errors></td>
									</tr>		
								</table>
								<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
									<tr><td>&nbsp;</td></tr>
									<logic:equal name="workgroupForm" property="action" value="confirmCreate">
										<tr>
											<td align="center" class="confirm">Workgroup successfully created.</td>
										</tr>
									</logic:equal>
									<logic:equal name="workgroupForm" property="action" value="confirmUpdate">
										<tr>
											<td align="center" class="confirm">Workgroup successfully updated.</td>
										</tr>
									</logic:equal>
									<logic:equal name="workgroupForm" property="action" value="confirmDelete">
										<tr>
											<td align="center" class="confirm">Workgroup successfully deleted.</td>
										</tr>
									</logic:equal>
									<tr><td>&nbsp;</td></tr>
								</table>
								
							<!-- END CONFIRMATION PAGE SECTION -->
							<br>
                      			<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<input type="submit" name="submitAction" onclick="return (changeFormActionURL(this.form.name, '/<msp:webapp/>displayWorkgroupSearch.do?submitAction=Link',false) && disableSubmit(this, this.form));" value='<bean:message key="button.searchWorkgroups"/>'></input>&nbsp;																														 			
											</td>
										</tr>										  	
									</table>
								<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
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