<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>

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
	<title><bean:message key="title.heading" /> - assignSupervisee/workgroupList.jsp</title>
	
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
	<script type="text/javascript">
		function hideButton(){
			show("Next", 0);
		}
		
		function showButton(){
			show("Next", 1);
		}
	</script>
</head>

<body topmargin=0 leftmargin="0" onload="hideButton();">
	<html:form action="/selectWorkgroupForReAssignSuperviseeToProgramUnit" target="content">
	  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|15"> 
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
									<!--blue tabs start-->
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>
									<!--blue tabs end-->
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
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header" >
											<%--	<logic:equal name="caseAssignmentForm" property="action" value="<%=UIConstants.CREATE%>">
													Change Supervisee's Division
												</logic:equal>
												<logic:equal name="caseAssignmentForm" property="action" value="<%=UIConstants.CREATE%>">
													Reassign Supervisee to Program Unit
												</logic:equal>--%>
													Reassign Supervisee to Program Unit - Select Workgroup
											</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select a Workgroup.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!--header start-->
									<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									<br>
									
									<!--PROGRAM UNIT ASSIGNMENT START-->
									<tiles:insert page="../../common/programUnitAssignmentTile.jsp" flush="true">
									</tiles:insert>
									<!--PROGRAM UNIT ASSIGNMENT END-->
									
									<br>
									
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
										<tr class="detailHead">
											<td width=1%></td>
											<td>Name</td>
											<td>Description</td>
										</tr>
										
										<% int RecordCounter=0;
								   			String bgcolor="";
										%>
					                    <logic:iterate id="workGroupIndex" name="caseAssignmentForm" property="workGroupsList">
										<tr class= <% RecordCounter++; 
							              	bgcolor = "alternateRow";                      
							              	if (RecordCounter % 2 == 1)
							                	bgcolor = "normalRow";
						                    out.print(bgcolor); %>>
											<td width=1%>
												<input type=radio name="workGroupId" value=<bean:write name="workGroupIndex" property="workgroupId" /> onclick="showButton();"/>
											</td>
											<td>
												<a href="javascript:openWindow('/<msp:webapp/>handleWorkgroupSelection.do?submitAction=<bean:message key="button.details"/>&workgroupId=<bean:write name="workGroupIndex" property="workgroupId"/>')"><bean:write name="workGroupIndex" property="workgroupName"/></a>
											</td>
											<td><bean:write name="workGroupIndex" property="workgroupDescription"/></td>
										</tr>
										</logic:iterate>
									</table>
							<!-- 		<script>
										if (superviseeFName=="Larry"){
											document.myForm.pu[1].checked=true;
										}
									</script>-->
									<!-- END DETAIL TABLE -->
									<br>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0">
										<tr align="center">
											<td>
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
											</td>
											<td id="Next">
												<html:submit property="submitAction">
													<bean:message key="button.assignsuperviseetoprogramunit.selectworkgroup.workgroupSelection" />
												</html:submit> 
											</td>
											<td>
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
						<br>
					</td>
				</tr>
			</table>
		</div>
	</pg:pager>	
	</html:form>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
