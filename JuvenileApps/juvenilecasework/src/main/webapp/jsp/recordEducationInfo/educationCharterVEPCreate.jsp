<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 01/27/2011 D Williamson Create JSP  --%>
<%-- 08/6/2015  R Young      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - educationCharterVEPCreate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="displayVEPSummary" target="content">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.vocationalEducationProgram"/>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter required program information.</li>
                <li>Enter date through the calendar icon.</li>
				<li>Select Next button to view Summary information.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>&nbsp;Required Fields&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!--juv profile header start-->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<!--juv profile header end-->
<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
          <!--tabs start-->
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
				            <tiles:put name="tabid" value="interviewinfotab"/>
				            <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			            </tiles:insert>
          <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align='center'>
  					<!-- BEGIN TABLE -->
						<div class="spacer"></div>
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
  										<!--tabs start-->
											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										        <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										        <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									        </tiles:insert>
  										<!--tabs end-->
											</td>
										</tr>
										<tr>
											<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
										</tr>
									</table>

									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
										<td valign="top" align="center">
											<div class="spacer"></div>
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
												           <tiles:put name="tabid" value="charter" />
												           <tiles:put name="juvnum"	value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											           </tiles:insert>
													</td>
												</tr>
												<tr>
													<td bgcolor="#ff6666"><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
												</tr>
											</table>
											<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
												<tr>
											       <td valign="top" align="center">
        								<!-- BEGIN SCHOOL INFO TABLE -->
												     <div class="spacer"></div>
										 		     <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													   <tr>
													     <td valign="top" class="detailHead"><bean:message key="title.vocationalEducationProgram"/>&nbsp;<bean:message key="prompt.information"/></td>
													   </tr>

													   <tr>
													     <td>
															<table border="0" cellpadding="2" cellspacing="1" width="100%" >
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.program"/></td>
																	<td class='formDe' colspan='6'>
																		<html:select property="vepProgramCodeId" styleId="vepProgramCode">
				  												              <html:option value=""><bean:message key="select.generic"/></html:option>
	  															              <html:optionsCollection property="vepProgramCodeList" value="code" label="description"/>
	  															        </html:select>  
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.startDate"/></td>
																	<td class="formDe">
																	    <html:text name="educationCharterDetailsForm" property="programStartDateStr" size="10" maxlength="10" readonly="true" title="Calendar must be used for date." styleId="verifiedDate"/>
																	</td>
																</tr>
															</table>  <!-- END SCHOOL INFO TABLE -->
														</td>
													</tr>
												</table> <!-- END TABLE -->
									<!-- BEGIN BUTTON TABLE -->
												<div class="spacer"></div>
												<table border="0" width="100%">
													<tr>
												        <td align="center">
												            <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
												            <html:submit property="submitAction" styleId="nextVEP"><bean:message key="button.next" /></html:submit>
												            <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												        </td>
											        </tr>
												</table>
												<div class="spacer"></div>
                        <!-- END BUTTON TABLE -->
											</td>
										</tr>
									</table>
									<div class="spacer"></div>
									</td>
								</tr>
							</table>	
						</td>
					</tr>
					</table>
					<div class="spacer"></div>
						
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->

</html:form>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>