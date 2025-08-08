<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/12/2005	 Hien Rodriguez - Create JSP -->
<!-- 08/25/2006	 Hien Rodriguez - ER#34507 Implement new UI Guidelines for date field -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 01/27/2010  C Shimke       - fixed Reset button with js, found testing defect #63625 -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/withdrawReinstate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm3" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>
<script type="text/javascript">
function resetInputs(){
	if (document.forms[0].statusChangeDateAsString.value == "" )
	{	
		document.forms[0].statusChangeDateAsString.value = getCurrentDate();
	}
	document.forms[0].reasonId.selectedIndex = 0;
	document.forms[0].casenotes.value = "";
}
</script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderWithdrawReinstateSummary" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
  		<td valign="top">
<!-- BEGIN TABS TABLE -->  		
      		<table width="100%" border="0" cellpadding="0" cellspacing="0" >
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
						<table width=98%>
							<tr>
								<td align="center" class="header">
									<bean:message key="title.processSupervisionOrder" />&nbsp;-
									<logic:equal name="supervisionOrderForm" property="action" value="withdraw">
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|28">
										<bean:message key="prompt.withdrawOrder" />
									</logic:equal>									
									<logic:equal name="supervisionOrderForm" property="action" value="reinstate">
										<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|31">
										<bean:message key="prompt.reinstateOrder" />
									</logic:equal>
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
										<li>Enter Required Fields and Click Next.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>										
							</tr>									
						</table>
					<!-- END INSTRUCTION TABLE -->
					<!-- BEGIN DETAIL HEADER TABLE -->																											
						<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
					<!-- END DETAIL HEADER TABLE -->
						<br>
					<!-- BEGIN DETAIL FORM TABLE -->                      
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<logic:equal name="supervisionOrderForm" property="action" value="withdraw">
								<tr>
									<td class="detailHead" colspan="2"><bean:message key="prompt.withdrawInfo" /></td>
								</tr>
							</logic:equal>
							<logic:equal name="supervisionOrderForm" property="action" value="reinstate">
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.reinstateInfo" /></td>
								</tr>
							</logic:equal>
							<tr>
								<td colspan="2">
						<!-- BEGIN INPUT TABLE -->		
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr>
											<logic:equal name="supervisionOrderForm" property="action" value="withdraw">				                          	
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.date" /></td>
											</logic:equal>
	
											<logic:equal name="supervisionOrderForm" property="action" value="reinstate">				                          	
												<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reinstatementDate" /></td>
											</logic:equal>
	
											<td class="formDe">
												<html:text name="supervisionOrderForm" property="statusChangeDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].statusChangeDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
														<script type="text/javascript">
															if (document.forms[0].statusChangeDateAsString.value == "" )
															{	
																document.forms[0].statusChangeDateAsString.value = getCurrentDate();
															}
														</script>
											</td>
										</tr>				                        
					
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reason" /></td>
											<logic:equal name="supervisionOrderForm" property="action" value="withdraw">	
												<td class="formDe">
													<html:select property="reasonId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="withdrawReasonList" value="code" label="description" />
													</html:select>
												</td>
											</logic:equal>
											<logic:equal name="supervisionOrderForm" property="action" value="reinstate">				                          	
												<td class="formDe">
													<html:select property="reasonId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="reinstateReasonList" value="supervisionCode" label="description" />
													</html:select>
												</td>
											</logic:equal>
										</tr>                           
																					
										<tr>
											<td class="formDeLabel" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.notes" /></td>
										</tr>
										<tr>
											<td class="formDe" colspan="2">
												<textarea rows="3" style="width:100%" name="casenotes"></textarea>
											</td>   
										</tr>						
									</table>
						<!-- END INPUT TABLE -->			
								</td>
							</tr>
						</table>                          		
				<!-- END DETAIL FORM TABLE -->
						<br>
				<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">											
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
									<html:submit property="submitAction" onclick="return validateSupervisionOrderForm3(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp; 
									<input type="button" name="reset" value=<bean:message key="button.reset" /> onclick="resetInputs()"/>&nbsp; 
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>											
						</table>
				<!-- END BUTTON TABLE -->
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