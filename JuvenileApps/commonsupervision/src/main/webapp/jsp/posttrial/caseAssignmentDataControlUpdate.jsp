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
<title><bean:message key="title.heading" /> - posttrial/caseAssignmentDataControlUpdate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/posttrial/caseAssignmentDataControlUpdate.js"></script>
<script type="text/javaScript">
function setCurrentDivisionPgmUnit(){
	var rbs = document.getElementsByName("divisionPgmUnitId");
	var dpu = document.forms[0].currentDPU.value;
	var pcdpu = dpu.split("/");
	var imgName = "img" + pcdpu[0];
	for (x=0; x < rbs.length; x++){
		if (rbs[x].value == pcdpu[1]){
			rbs[x].checked = true;
			showHide(imgName,"","/<msp:webapp/>");
			break;
		}	
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setCurrentDivisionPgmUnit();">
<html:form action="/displayCaseAssignmentDataControlOfficer" target="content" focus="pgmUnitAssignmentDateStr">
<input type="hidden" name="helpFile" value="">
<input type="hidden" name="currentDPU" value="<bean:write name="caseAssignmentDataControlForm" property="currentDivisionPgmUnitId" />" >
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
								<bean:message key="prompt.caseAssignment"/> - <bean:message key="prompt.programUnit"/></td>						
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
										<li>Enter required field(s). Click Next.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.requiredFields"/></td>												
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
						<script type="text/javascript" ID="js1">
							var cal1 = new CalendarPopup();
							cal1.showYearNavigation();
						</script>																			
<!-- BEGIN PGM UNIT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2"><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /> <bean:message key="prompt.date" /></td>
											<td>
												<html:text name="caseAssignmentDataControlForm" property="pgmUnitAssignmentDateStr" maxlength="10" size="10" />
												<A HREF="#" onClick="cal1.select(document.getElementById('pgmUnitAssignmentDateStr'),'anchor1','MM/dd/yyyy'); return false;"
					                                NAME="anchor1" ID="anchor1"><bean:message key="prompt.2.calendar"/></A>&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="detailHead" colspan="2"><bean:message key="prompt.division" />/<bean:message key="prompt.programUnit" />s</td>	
							</tr>
							<logic:iterate id="divisionIter" name="caseAssignmentDataControlForm" property="divisionPgmUnitsList" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td width="1%">
										<a href="javascript:showHide('img<bean:write name="divisionIter" property="organizationId"/>', '','/<msp:webapp/>')" border="0">
										   <img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="divisionIter" property="organizationId"/>" border="0" > </a>
									</td>
									<td class="boldText"><bean:write name="divisionIter" property="description"/></td>
								</tr>
								<tr>
									<td colspan="2">
										<span id="img<bean:write name="divisionIter" property="organizationId"/>Span" class="hidden">
											<table width="100%" cellpadding="2" cellspacing="1">
												<logic:notEmpty name="divisionIter" property="children">	
													<logic:iterate id="programUnitIter" name="divisionIter" property="children">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type="radio" name="divisionPgmUnitId" value=<bean:write name="programUnitIter" property="organizationId" /> />
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
<!-- END PGM UNIT TABLE -->
			
<!-- BEGIN BUTTON TABLE  -->
			 			<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="return validateInputs(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
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