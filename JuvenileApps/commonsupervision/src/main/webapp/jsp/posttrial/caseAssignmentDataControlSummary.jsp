<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/26/2009	 CShimek        - Create JSP -->

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
<title><bean:message key="title.heading" /> - posttrial/caseAssignmentDataControlSummary.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitCaseAssignmentDataControlUpdate" target="content">
<input type="hidden" name="helpFile" value="">
<div align="center">
<!-- Begin Pagination Header Tag -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN TAB TABLE -->    	
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
<!-- END TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">CSCD -
									<logic:equal name="caseAssignmentDataControlForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
										<bean:message key="prompt.update"/>
									</logic:equal>	
									<logic:equal name="caseAssignmentDataControlForm" property="secondaryAction" value="<%=UIConstants.CORRECT%>">
										<bean:message key="prompt.correct"/>
									</logic:equal>	
									<logic:equal name="caseAssignmentDataControlForm" property="secondaryAction" value="<%=UIConstants.DELETE%>">
										<bean:message key="prompt.delete"/>
									</logic:equal>	
									<bean:message key="prompt.caseAssignment"/> <bean:message key="prompt.summary"/>
								</td>						
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
										<li>Review. Click Finish.</li>
									</ul>
								</td>
							</tr>
						</table>
<!-- END INSTRUCTION TABLE -->	
<!-- BEGIN SUPERVISEE INFO TABLE -->    	
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top" align="center">
									<tiles:insert page="../common/caseAssignmentDataControlHeader.jsp" flush="true"></tiles:insert> 		
								</td>
							</tr>
						</table>  
<!-- END SUPERVISEE INFO TABLE -->	
						<div class="spacer4px"></div>	
<!-- BEGIN PGM UNIT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /> <bean:message key="prompt.date" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="pgmUnitAssignmentDateStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.division" /> / <bean:message key="prompt.programUnit" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="divisionPgmUnitDesc"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>  
<!-- END PGM UNIT TABLE -->
						<div class="spacer4px"></div>
<!-- BEGIN OFFICER ASSIGNMENT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.officer" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.position" /> <bean:message key="prompt.assignment" /> <bean:message key="prompt.date" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="positionAssignmentDateStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.officer" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="officerName"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>  
<!-- END OFFICER ASSIGNMENT TABLE -->
			
<!-- BEGIN BUTTON TABLE  -->
			 			<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table> 
<!-- END BUTTON TABLE2 -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>