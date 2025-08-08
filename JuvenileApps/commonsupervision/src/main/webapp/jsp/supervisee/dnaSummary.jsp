<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/29/2012	 D Williamson	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - supervisee/dnaSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.UPDATE%>">
	<%
         pageContext.setAttribute("submitURL", "/submitCorrectDNA");
    %>
</logic:equal>

<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CREATE%>">
	<%
         pageContext.setAttribute("submitURL", "/submitAddDNA");
    %>
</logic:equal>

	<%
       String submitURL = (String)pageContext.getAttribute("submitURL");
	%>

<html:form action="<%=submitURL%>" target="content">
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
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					   		   <tiles:put name="tab" value="caseloadTab"/> 
				     	    </tiles:insert>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table><!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
							<!--casefile tabs start-->
							<table width=98% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign=top>
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
										    <tiles:put name="tab" value="SuperviseeTab" />
									    </tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
								<tr>
									<td valign=top align=center>
										<!-- BEGIN HEADING TABLE -->
										<div class=header><bean:message key="title.CSCD" />&nbsp;-
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.UPDATE%>">
												<bean:message key="prompt.correct"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|24">
											</logic:equal>
											<logic:equal name="superviseeForm" property="action" value="<%=UIConstants.CREATE%>">
												<bean:message key="prompt.add"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|21">
											</logic:equal>                                            
										    <bean:message key="prompt.dna"/>&nbsp;<bean:message key="title.summary"/>
										</div>
                                        <!-- BEGIN ERROR TABLE -->
				                        <table width="98%" align="center">
					                        <tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>
				                        <!-- END ERROR TABLE -->
										<div class=instructions>
											<li>           
											    Verify that information is correct and select Finish button. If any changes are needed, select Back button.
											</li></div>
										<div class=spacer></div>
										<!-- END HEADING TABLE -->
										<!-- BEGIN DNA INFORMATION TABLE -->
									    <table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class=borderTableBlue>
											<tr>
												<td class="detailHead" colspan="2"><bean:message key="prompt.dna"/>&nbsp;<bean:message key="prompt.information"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="4" cellspacing="1">																	
														<tr>
															<td class=formDeLabel width=1% nowrap><bean:message key="prompt.dnaCollectedDate"/></td>
															<td class=formDe><bean:write name="superviseeForm" property="dnaCollectedDate" formatKey="format.date.mmddyyyy" /></td>
														</tr>
													</table>
												</td>
											</tr>
									    </table>
										<!-- END DNA INFORMATION TABLE -->
										
										<!--button start -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"> <bean:message key="button.back" /></html:submit>
                                                    <html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
													<html:submit property="submitAction" onclick="javascript: disableSubmit(this, this.form);"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
										<!--button end -->
									</td>
								</tr>
							</table>
							<div class=spacer4px></div>
							<!-- END DETAIL TABLE -->
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
	<br>
	<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
