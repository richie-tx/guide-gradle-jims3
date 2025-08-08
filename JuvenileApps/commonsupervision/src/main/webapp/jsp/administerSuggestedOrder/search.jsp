<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006	 Hien Rodriguez - Add validationSelectedCourts -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/search.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuggestedOrderSearchResults" target="content" focus="orderName">
<input type="hidden" name="helpFile" value="commonsupervision/aso/Suggested_Order.htm#|1">
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
						<table width="100%">
							<tr>							
							    <td align="center" class="header">
									<bean:message key="title.suggestedOrders" />&nbsp;<bean:message key="title.search" />
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
									<li>Enter Search criteria and click Submit. </li>
								</ul></td>
							</tr>
						</table>
					<!-- END INSTRUCTION TABLE -->
					<!-- BEGIN SEARCH TABLE -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.suggestedOrder" /></td>								
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="1" cellpadding="2">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.name" /></td>
											<td class="formDe"><html:text property="orderName" maxlength="30" size="30"/></td>											
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.description" /></td>
											<td class="formDe"><html:text property="orderDescription" maxlength="100" size="40"/></td>											
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.cdi" /></td>
											<td class="formDe"><html:select property="courtDivisionId" size="1">
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<html:optionsCollection name="codeHelper" property="courtDivisions" value="code" label="description" />
											</html:select></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.conditionName" /></td>
											<td class="formDe"><html:text property="conditionName" maxlength="40" size="40"/></td>											
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.offenseCode" /></td>
											<td class="formDe"><html:text property="offenseId" maxlength="6" size="6"/></td>											
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDe"><html:text property="offenseLiteral" maxlength="40" size="40"/></td>											
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.penalCode" /></td>
											<td class="formDe"><html:text property="penalCodeId" maxlength="6" size="6"/></td>											
										</tr>
									</table>
									<table width="100%" border="0" cellpadding="2" cellspacing="0">
										<tr>
											<td class="formDeLabel" colspan="2"><bean:message key="prompt.selectCourts" /></td>
										</tr>																				
										<tr>
											<tiles:insert page="../common/courts.jsp" flush="true">
												<tiles:put name="beanName" beanName="suggestedOrderForm" />
												<tiles:put name="ASOSpecialDisplay" value="ASOSpecialDisplay" />
												<tiles:put name="mode" value="select" />
											</tiles:insert>								
								  		</tr>
								  	</table>																													
								</td>
							</tr>
						</table>
					<!-- END SEARCH TABLE -->
						<br>
					<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
									<input type="button" onclick="goNav('/<msp:webapp/>displaySuggestedOrderSearch.do')" value="<bean:message key='button.refresh'/>"/>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																											 			
								</td>
							</tr>										  	
						</table>
					<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table><br>	
			</td>
		</tr>
</table>
<!-- END  TABLE -->
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
