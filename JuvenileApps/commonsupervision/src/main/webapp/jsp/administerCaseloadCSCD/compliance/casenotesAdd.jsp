<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2007	 Debbie Williamson - Create JSP -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->
<!-- 05/26/2010  Clarence Shimek   - #65373 revised to use tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/casenotesAdd.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/casenotesAdd.js"></script>
</head>
<body topmargin="0" leftmargin="0" onload="displayAssociatesDropDown();" onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/displayAddCasenotesSummary" target="content" focus="casenoteDateAsString">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|2"> 
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|2"--%>
<div align="center">
<!-- BEGIN CONTENT TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="conplianceTab"/>
							</tiles:insert>
						</td>  
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
							<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
						</td>
					</tr>
<!-- BEGIN GREEN TABS TABLE -->	
					<tr>
						<td valign="top" align="center"> 
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>						
								<tr>
									<td valign="top">
										<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
						   				 	<tiles:put name="tab" value="ComplianceTab"/> 
							     		</tiles:insert>					
									</td>
								</tr>
								<tr>
									<td  bgcolor="#33cc66"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
								</tr>
							</table>
<!-- END GREEN TABS TABLE -->							
						</td>
					</tr>		
					<tr>
						<td valign="top" align="center"> 
<!-- BEGIN GREEN BORDER TABLE -->					
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header">
													<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-&nbsp;<bean:message key="title.create" />&nbsp;<bean:message key="prompt.casenote" />
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
<!-- BEGIN CONFIRMATION TABLE -->
							      	  	<logic:notEqual name="complianceForm" property="confirmMessage" value="">  	  	
											<table width="98%" align="center">							
												<tr>
									        		<td align="center" class="confirm"><bean:write name="complianceForm" property="confirmMessage" /></td>
												</tr>		
											</table>
								    	</logic:notEqual>  
<!-- BEGIN CONFIRMATION TABLE -->  
<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Enter required fields, click Next</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9"> Required Fields *All date fields must be in the format of mm/dd/yyyy.</td>
											</tr>
										</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SELECTED CONDITIONS TABLE  -->			
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.selectedConditions" /></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1" id="compliantList">
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.condition" /></td>
															<td class="formDeLabel" title="Court" align="center"><bean:message key="prompt.CRT" />&nbsp;<bean:message key="prompt.#" /></td>
															<td class="formDeLabel"><bean:message key="prompt.case" />&nbsp;<bean:message key="prompt.#" /></td>
														</tr>	
														<% int RecordCounter = 0;
														   String bgcolor = "";%>  
														<logic:iterate id="conditionIter" name="complianceForm" property="selectedConditions">  
															<tr class= <% RecordCounter++;
																		  bgcolor = "alternateRow";                      
							        						             if (RecordCounter % 2 == 1)
							                     						 {
														                    bgcolor = "normalRow";
								    						             }  
								                 						 out.print(bgcolor);  %>  >
																<td>
																	<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='conditionIter' property='sprOrderConditionId'/>')" title=""><bean:write name='conditionIter' property='orderConditionName'/></a>
																</td>
																<td align="center"><bean:write name="conditionIter" property="courtId" /></td>
																<td><bean:write name="conditionIter" property="displayCaseNum" /></td>
															</tr>
														</logic:iterate>
													</table>
												</td>
											</tr>
										</table>
<!-- END SELECTED CONDITIONS TABLE  -->				
										<br>	
<%-- SCRIPT USED FOR CALANDER POPUP --%>								
						<SCRIPT type="text/javascript" ID="js1">
							var cal1 = new CalendarPopup();
							cal1.showYearNavigation();
						</SCRIPT>													
<!-- BEGIN ADD CASENOTE INFORMATION TABLE -->
										<tiles:insert page="../../administerCaseloadCSCD/compliance/complianceCasenoteTile.jsp" flush="true">
							     		</tiles:insert>		
<!-- END ADD CASENOTE INFORMATION TABLE -->							     					
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->									
						</td>
					</tr>
					<tr>
						<td><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
					</tr>
				</table>
<!-- END BLUE BORDER TABLE  -->
			</td>
		</tr>
		<tr>
			<td><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
		</tr>
	</table>
<!-- END CONTENT TABLE -->
<br>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>