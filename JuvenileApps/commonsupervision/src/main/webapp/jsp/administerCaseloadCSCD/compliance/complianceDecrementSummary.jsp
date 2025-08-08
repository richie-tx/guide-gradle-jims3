<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/12/2007	 C Shimek - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/complianceDecrementSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>

<link href="/<msp:webapp/>css/base.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/submitDecrementNCCount" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|12">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|12"--%>
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
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tabid" value="conplianceTab"/>
						</tiles:insert> 
<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- BEGIN BLUE BORDER TABLE -->			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFO TABLE  -->
	 					<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFO TABLE -->	
					</td>
				</tr>
				<tr>
					<td valign="top" align="center">						
<!-- BEGIN SUPERVISEE TABS TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>						
							<tr>
								<td valign="top">
								<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
					   				 	<tiles:put name="tab" value="ComplianceTab"/> 
						     		</tiles:insert>	 
								<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE TABS TABLE -->
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-&nbsp;Decrement NC Count Summary</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
								</td>
							</tr>
							<tr>	
								<td>
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->
								</td>
							</tr>
							<tr>
								<td valign="top" align="center">	
<!-- BEGIN INSTRUCTION TABLE -->
					                <table width="98%" border="0">
        						    	<tr>
                    						<td>
			            						<ul>
													<li>Review Selection.  Click Finish.</li>
            			            			</ul>
                        					</td>
			                  			</tr>
            			    		</table>
<!-- END INSTRUCTION TABLE -->
								</td>
							</tr>
							<tr>
								<td valign="top" align="center">	
<!-- BEGIN SELECTED CONDITION TABLE -->
									<table width="98%" border="0" cellspacing=0 cellpadding=2 class="borderTableBlue">
										<tr class="detailHead">
											<td>Selected Condition</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td><bean:message key="prompt.condition"/></td>
														<td title="Court" align="center"><bean:message key="prompt.CRT"/></td>
														<td><bean:message key="prompt.case#"/></td>
														<td align="center">NC Count</td>
													</tr>
													<tr>
														<td class="boldText"><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='complianceForm' property='sprOrderConditionId'/>')" title="" class="tooltip"><bean:write name='complianceForm' property='conditionName'/><span><bean:write name='complianceForm' property='conditionLiteral'/></span></a></td> 
														<td align="center"><bean:write name='complianceForm' property='court'/></td>
														<td><bean:write name='complianceForm' property='caseNumber'/></td>
														<td align="center"><bean:write name='complianceForm' property='nonComplianceCount'/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>				
<!-- END SELECTED CONDITION TABLE -->	
<br>	
<!-- BEGIN NONCOMPLIACE EVENTS CONDITION TABLE -->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td>Selected Noncompliance Event to be removed</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td><bean:message key="prompt.date"/> | <bean:message key="prompt.time"/></td>
														<td><bean:message key="prompt.eventType"/>s</td>
														<td><bean:message key="prompt.details"/></td>
													</tr>										
													<tr>
														<td nowrap>
															<bean:write name="complianceForm" property="eventDate" formatKey="date.format.mmddyyyy" /> |
															<bean:write name="complianceForm" property="eventDate" formatKey="time.format.hhmma" />	
														</td> 
														<td>
															<bean:write name='complianceForm' property='eventTypes' />
														</td>
														<td>
															<bean:write name='complianceForm' property='eventDetails'/>
														</td>
													</tr>
												</table>
											</td>
										</tr>			
									</table>
								</td>
							</tr>		
							<tr>
								<td valign="top" align="center">
<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back" /></html:submit> 
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit> 
												<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit> 											
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GEEEN BORDER TABLE -->						
<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->				
		</td>
	</tr>
</table>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>