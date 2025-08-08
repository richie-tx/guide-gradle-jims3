<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2007	 Debbie Williamson - Create JSP -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/complianceReason.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/complianceReason.js"></script>
<link href="/<msp:webapp/>css/base.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/displayNCCasenote" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|8">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|8"--%>
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
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-
												<bean:message key="prompt.resolveNoncompliance" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;<bean:message key="prompt.reason" />
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
			            							<li>Click Finish to Complete.</li>
			                        			</ul>
			                        		</td>
			                  			</tr>
			                		</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN CONTENT TABLE -->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.selectedConditions" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;<bean:message key="prompt.reason" /></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td><bean:message key="prompt.condition" />
														<jims:sortResults beanName="complianceForm" results="selectedConditions" 
                    					                        primaryPropSort="orderConditionName" primarySortType="STRING" defaultSort="true" 
                                        					    defaultSortOrder="ASC" sortId="3" hideMe="true" />
														</td>
														<td title="Court" align="center"><bean:message key="prompt.CRT" /></td>
														<td><bean:message key="prompt.case#" /></td>
														<td><bean:message key="prompt.complianceReason" /></td>
														<td title="Non-Compliance Count" align="center"><bean:message key="prompt.NCCount" /></td>                            
													</tr>
													<% int RecordCounter = 0;
													   String bgcolor = "";	%>
													<logic:iterate id="selectedConditions" name="complianceForm" property="selectedConditions" >
														<tr class=<%RecordCounter++;
																	bgcolor = "alternateRow";
																	if (RecordCounter % 2 == 1)
																	{
																		bgcolor = "normalRow";
																	}	
																	out.print(bgcolor);%> >
															<td class="boldText">
																<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='selectedConditions' property='sprOrderConditionId'/>')" title=""><bean:write name='selectedConditions' property='orderConditionName'/></a>
															</td> 
															<td align="center"><bean:write name="selectedConditions" property="courtId" /></td>
															<td><bean:write name="selectedConditions" property="displayCaseNum" /></td>
															<td>
																<html:select name="selectedConditions" property="complianceReasonId" indexed="true">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection property="complianceReasons" value="code" label="description" />
																</html:select> 
															</td>  
															<td align="center">
																<bean:write name="selectedConditions" property="ncCount" />
															</td>
														</tr>  
													</logic:iterate>    
												</table>
											</td>
										</tr> 
									</table>  
<!--END CONTENT TABLE-->
									<br>
<!-- BEGIN BUTTON TABLE -->
			<!--remove  when converting to JSP-->
									<table border="0" cellpadding="2" cellspacing="1" id="summaryTable">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return checkReasonSelect()&& disableSubmit(this, this.form);"><bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->
					</td>
				</tr>
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE  -->
		</td>
	</tr>
</table>		
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>