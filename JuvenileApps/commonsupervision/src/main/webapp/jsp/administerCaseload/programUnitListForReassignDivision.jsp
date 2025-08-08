<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- LDEEN 1/20/2011 I DO NOT THINK THIS JSP IS BEING USED-CAN'T LOCATE IN STRUTS-CONFIG.XML -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
	<title>CommonSupervision/administerCaseload/programUnitListForReassignDivision.jsp</title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<%--	<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>--%>
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
	<html:form action="/programUnitListForReassignDivision" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|9">
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
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
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
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
													Change Supervisee's Division - Select Program Unit											
											</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Expand Division and select a program unit.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									<br>
									
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class=detailHead>
											<td>Divisions/Program Units</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
												<% int RecordCounter=0;
												   String bgcolor="";
												%>
												<logic:iterate id="divisionIter" name="caseAssignmentForm" property="organizationList">
													<tr class= <% RecordCounter++; 
										              bgcolor = "alternateRow";                      
										              if (RecordCounter % 2 == 1)
										                  bgcolor = "normalRow";
									                   out.print(bgcolor);%>>
														<td width=1%>
															<a href="javascript:showHide('img<bean:write name="divisionIter" 
																	property="organizationId"/>', '','/<msp:webapp/>')" 
																border=0><img src="/<msp:webapp/>images/expand.gif" 
																			name="img<bean:write name="divisionIter" property="organizationId"/>" 
																			border="0" >
															</a>
														</td>
														<td class=boldText><bean:write name="divisionIter" property="description"/></td>
													</tr>
													<tr>
														<td colspan=2>
															<span id="img<bean:write name="divisionIter" property="organizationId"/>Span" class="hidden">
																<table width="100%" cellpadding="2" cellspacing="1">
																	<logic:notEmpty name="divisionIter" property="children">	
																	<logic:iterate id="programUnitIter" name="divisionIter" property="children">
																	<tr class=<%=bgcolor%>>
																		<td width=1%>
																			<input type=radio name="programUnitId" value=<bean:write name="programUnitIter" property="organizationId" /> onclick="showButton();"/>
																		</td>
																		<td>
																			<bean:write name="programUnitIter" property="description"/>
																		</td>
																	</tr>
																	</logic:iterate>
																	</logic:notEmpty>
																</table>
															</span>
														</td>
													</tr>
												</logic:iterate>
												</table>
											</td>
										</tr>
									</table>
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
												<html:submit property="submitAction" >
													<bean:message key="button.next" />
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
	</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
