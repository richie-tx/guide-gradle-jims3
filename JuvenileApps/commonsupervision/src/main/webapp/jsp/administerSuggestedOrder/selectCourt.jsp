<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/13/2006	 dgibler		- Added displayOffenseNote -->
<!-- 01/23/2006  Hien Rodriguez - Add validateSelectedCourts -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.supervision.suggestedorder.helper.SuggestedOrderListHelper"/>

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/selectCourt.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/displaySuggestedOrderSelectCondition" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>    	
  	</tr>
  	<tr>
    	<td valign="top">
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>						
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">							
					<!-- BEGIN HEADING TABLE -->
						<table width="98%">
							<tr>
								<td align="center" class="header">
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|6">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|13">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|20">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.selectCourts" />
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
								<td><ul>
									<li>Select all courts or specific courts and click next.</li>
									<li>"All Courts" automatically limited based on prior selection.</li>
								</ul></td>
							</tr>
						</table>
					<!-- END INSTRUCTION TABLE -->
					<!-- BEGIN SUGGESTED ORDER SECTION -->
						<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHideMulti('suggestedOrder', 'so', 2,'/<msp:webapp/>')" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="suggestedOrder"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.suggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="so0" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.name" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderName"/></td>
							</tr>
							<tr id="so1" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.description" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderDescription" /></td>
							</tr>
					<!-- END SUGGESTED ORDER SECTION -->				
										
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SELECTED OFFENSES SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHide('offenses','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="offenses"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedOffenses" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:empty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">			
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.selectedOffenses" /></td>
								<td class="formDe">None Selected</td>
							
							</tr>
							</logic:empty>	
							<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">
								<td colspan="2">								
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
										</tr>
										<%int RecordCounter = 0;
										String bgcolor = "";%>
										<logic:iterate id="offenseSelectedListIndex" name="suggestedOrderForm" property="offenseSelectedList">   
										<tr
											class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											
											<td><bean:write name="offenseSelectedListIndex" property="offenseCodeId" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="description" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="stateCodeNum" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="penalCode" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="level" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="degree" /></td>
										</tr> 
										</logic:iterate>																			
									</table>									
								</td>
							</tr>
						</logic:notEmpty>
					<!-- END SELECTED OFFENSES SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>					
						</table>	
					<!-- BEGIN SELECT COURTS SECTION -->							
						<table width="98%" cellpadding="4" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead">
											<logic:equal name="suggestedOrderForm" property="action" value="create">
												<bean:message key="prompt.selectCourtsAndIncludeStandardNonstandard" />
											</logic:equal>
											<logic:notEqual name="suggestedOrderForm" property="action" value="create">
												<bean:message key="prompt.selectCourts" />
											</logic:notEqual>
											</td>
											<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
																		
							<tr>																		
								<tiles:insert page="../common/courts.jsp" flush="true">
									<tiles:put name="beanName" beanName="suggestedOrderForm" />
									<tiles:put name="ASOSpecialDisplay" value="ASOSpecialDisplay" />
									<tiles:put name="mode" value="select" />
								</tiles:insert>
							</tr>																			

							<logic:equal name="suggestedOrderForm" property="action" value="create">
								<tr>
									<td colspan="2">
										<table width="100%" cellpadding="4" cellspacing="0">
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.includeConditions" /></td>
												<td class="formDe"><html:select property="standardId" size="1">
													<%--<html:option value=""><bean:message key="select.generic" /></html:option>--%>
													<html:optionsCollection name="codeHelper" property="conditionTypes" value="code" label="description" />
												</html:select></td>											
											</tr>
										</table>	
									</td>
								</tr>
								<script>
									document.getElementsByName("standardId")[0].value="SNS";
								</script>
							</logic:equal>	
							<logic:notEqual name="suggestedOrderForm" property="action" value="create">
								<tr>
									<td colspan="2">
										<table width="100%" cellpadding="4" cellspacing="0">
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.includeConditions" /></td>
												<td class="formDe">
									
												
												
											<logic:equal name="suggestedOrderForm" property="standardId" value="SNS">
												Standard and Non-Standard
											</logic:equal>
											<logic:equal name="suggestedOrderForm" property="standardId" value="SO">
												Standard Only
											</logic:equal>
											<logic:equal name="suggestedOrderForm" property="standardId" value="NSO">
												Non-Standard Only
											</logic:equal>
													</td>											
											</tr>
										</table>	
									</td>
								</tr>
							</logic:notEqual>	
						</table>									
						<!-- END SELECT COURTS SECTION -->
				<br>
						<!-- BEGIN BUTTON TABLE -->				
						<table align="center" width="98%">				
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return validateSelectedCourts(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
									<input type="reset" onclick="return customCourtReset(this.form)"/>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

