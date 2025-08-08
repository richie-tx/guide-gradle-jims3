<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/12/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/withdrawReinstateSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionOrderWithdrawReinstate" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="processOrderTab"/>
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
					<td valign="top" align="center">
						
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<bean:message key="title.processSupervisionOrder" />&nbsp;-&nbsp;
										<logic:equal name="supervisionOrderForm" property="action" value="withdraw">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|29">
											<bean:message key="prompt.withdrawOrder" />&nbsp;<bean:message key="prompt.summary" />
												<tr>
													<td><ul>
														<li>Click the appropriate button below.</li>
													</ul></td>
												</tr>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="action" value="reinstate">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|32">
											<bean:message key="prompt.reinstateOrder" />&nbsp;<bean:message key="prompt.summary" />
												<tr>
													<td><ul>
														<li>Click the appropriate button below.</li>
													</ul></td>
												</tr>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="action" value="confirmWithdraw">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|30">
											<bean:message key="prompt.withdrawOrder" />&nbsp;<bean:message key="prompt.confirmation" />										
												<tr>
													<td class="confirm">Supervision Order successfully withdrawn.</td>
												</tr>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="action" value="confirmReinstate">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|33">
											<bean:message key="prompt.reinstateOrder" />&nbsp;<bean:message key="prompt.confirmation" />										
												<tr>
													<td class="confirm">Supervision Order successfully reinstated.</td>
												</tr>
										</logic:equal>							 						  
									</td>
								 </tr>						 						  
							</table>											
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align="center">							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->								
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
						<br>
						<!-- BEGIN DETAIL FORM TABLE -->                      
						<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
							<logic:equal name="supervisionOrderForm" property="action" value="withdraw">
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.withdrawInfo" /></td>		                          
								</tr>
							</logic:equal>
							<logic:equal name="supervisionOrderForm" property="action" value="confirmWithdraw">
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.withdrawInfo" /></td>		                          
								</tr>
							</logic:equal>	
							<logic:equal name="supervisionOrderForm" property="action" value="reinstate">
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.reinstateInfo" /></td>
								</tr>
							</logic:equal>
							<logic:equal name="supervisionOrderForm" property="action" value="confirmReinstate">
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.reinstateInfo" /></td>
								</tr>
							</logic:equal>
							<tr>
								<td colspan=2>
									<table width=100% cellpadding=2 cellspacing=1>
								<tr>
									<logic:equal name="supervisionOrderForm" property="action" value="withdraw">				                          	
										<td class="formDeLabel"><bean:message key="prompt.date" /></td>
									</logic:equal>
									<logic:equal name="supervisionOrderForm" property="action" value="confirmWithdraw">
										<td class="formDeLabel"><bean:message key="prompt.date" /></td>
									</logic:equal>
									<logic:equal name="supervisionOrderForm" property="action" value="reinstate">				                          	
										<td class="formDeLabel" nowrap><bean:message key="prompt.reinstatementDate" /></td>
									</logic:equal>
									<logic:equal name="supervisionOrderForm" property="action" value="confirmReinstate">
										<td class="formDeLabel"><bean:message key="prompt.reinstatementDate" /></td>
									</logic:equal>
									<td class="formDe"><bean:write name="supervisionOrderForm" property="statusChangeDateAsString" /></td>	
										</tr>
									<tr>
										<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.reason" /></td>
										<td class="formDe"><bean:write name="supervisionOrderForm" property="reason" /></td>
									</tr>			                    
									<tr>
										<td class="formDeLabel" colspan=2><bean:message key="prompt.notes" /></td>
									</tr>
									<tr>
									<td class="formDe" colspan=2><bean:write name="supervisionOrderForm" property="casenotes" /></td>
							</tr>
						</table>
						<!-- END DETAIL FORM TABLE -->	
					</td>
				</tr>						
			</table>                          		
                  			

			<!-- BEGIN BUTTON TABLE -->
				<br>
				<table border="0" width="100%">
					<logic:equal name="supervisionOrderForm" property="action" value="withdraw">
						<tr>
							<td align="center">			  		
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="supervisionOrderForm" property="action" value="reinstate">
						<tr>
							<td align="center">			  		
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="supervisionOrderForm" property="action" value="confirmWithdraw">
						<tr>
							<td align="center">
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToCaseOrderSearchResults"></bean:message></html:submit>
							</td>
						</tr>
					</logic:equal>		
					<logic:equal name="supervisionOrderForm" property="action" value="confirmReinstate">
						<tr>
							<td align="center">			  		
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToCaseOrderSearchResults"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.printOrder"></bean:message></html:submit>
							</td>
						</tr>
					</logic:equal>														
				</table>
			<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>		

</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>

</html:html>