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
<title><bean:message key="title.heading" /> - processSupervisionOrder/specialConditionSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="group1Caption" name="supervisionOrderForm" property="group1Caption" type="java.lang.String"/>
<bean:define id="group2Caption" name="supervisionOrderForm" property="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="supervisionOrderForm" property="group3Caption" type="java.lang.String"/>
<bean:define id="literalCaption" name="supervisionOrderForm" property="conditionLiteralCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSpecialConditionSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|11">
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
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.create" />&nbsp;<bean:message key="title.specialSupervisionCondition" /> - <bean:message key="prompt.summary" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Please review entries and click Continue With Order. </li>												
									</ul></td>
								</tr>							
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN SUPERVISION CONDITION TABLE -->                      
							<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.supervisionCondition" /></td>				                        	
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="4" cellspacing="1">
											<tr>				                          	
												<td class="formDeLabel" width="1%" nowrap valign="top"><bean:message key="prompt.supervisionCondition" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="conditionLiteral" filter="false" /></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="<%=group1Caption%>"/></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="group1Name" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" valign="top"><bean:message key="prompt.notes" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="conditionNotes" /></td>
											</tr>
										</table>
									</td>
								</tr>
							 </table>
						<!-- END SUPERVISION CONDITION TABLE -->
							<br>                     
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">											
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveContinueWithOrder"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>         