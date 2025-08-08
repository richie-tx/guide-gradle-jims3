<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/20/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<title><bean:message key="title.heading" /> - conditionSpecialView.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.category"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionConditionSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|6">
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
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
										<bean:message key="prompt.view" />&nbsp;<bean:message key="title.specialSupervisionCondition" />
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
										<li>Click update to modify this condition. </li>												
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
												<td class="formDeLabel" valign="top"><bean:message key="prompt.literal"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="fixedLiteral"  filter="false"/></td>
											</tr>
											<tr>				                          	
												<td class="formDeLabel" width="1%" nowrap ><bean:message key="prompt.effectiveDate" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="effectiveDate" formatKey="date.format.mmddyyyy" /></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="<%=conditionGroup1Caption%>"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group1Name" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" valign="top"><bean:message key="prompt.notes" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="notes" /></td>
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
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>		
										<logic:notEmpty name="supervisionConditionForm" property="fixedLiteral">
										<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
										</jims2:isAllowed>
										</logic:notEmpty>
										<logic:empty name="supervisionConditionForm" property="fixedLiteral">
										<jims2:isAllowed requiredFeatures="CS-COND-DELETE">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
									</jims2:isAllowed>
										</logic:empty>
										<%-- <html:reset><bean:message key="button.reset"></bean:message></html:reset> --%>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>         