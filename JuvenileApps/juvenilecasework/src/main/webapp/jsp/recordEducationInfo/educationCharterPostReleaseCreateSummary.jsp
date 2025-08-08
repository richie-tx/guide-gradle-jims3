<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 01/28/2011 D Williamson Create JSP  --%>

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
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - educationCharterPostReleaseCreateSummary.jsp</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="submitPostReleaseCreate" target="content">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
    <logic:equal name="educationCharterDetailsForm" property="action" value="view">
	  <tr id="detailHeading" class="hidden">
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		    <bean:message key="title.postReleaseTracking"/>&nbsp;<bean:message key="title.details"/></td>
	  </tr>
	</logic:equal>

	<logic:equal name="educationCharterDetailsForm" property="action" value="summary">
	  <tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.postReleaseTracking"/>
		    <bean:message key="title.summary"/>
		</td>
	  </tr>
	</logic:equal>
	<logic:equal name="educationCharterDetailsForm" property="action" value="confirm">
	  <tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -
		    <bean:message key="title.create"/>&nbsp;<bean:message key="title.postReleaseTracking"/>
		    <bean:message key="title.confirmation"/>
		</td>
	  </tr>
	  <tr><td align="center" class="confirm">Post Release Tracking Information successfully added.</td></tr>
	</logic:equal>
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
<logic:equal name="educationCharterDetailsForm" property="action" value="summary">
  <table width="98%" border="0">
	<tr>
		<td>
			<ul id="summaryInstructions" class="hidden">
				<li>Review information and select Finish to add Tracking information.</li>
				<li>Select Back button to return to Create Tracking Information page.</li>
			</ul>
		</td>
	</tr>
  </table>
</logic:equal>  
<!-- END INSTRUCTION TABLE -->
<!--juv profile header start-->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<!--juv profile header end-->
<!-- BEGIN DETAIL TABLE -->
<div class="spacer"></div>
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
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
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
											<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>

									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
										<td valign="top" align="center">
											<div class="spacer"></div>
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<!--tabs start-->
														<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
												           <tiles:put name="tabid" value="charter" />
												           <tiles:put name="juvnum"	value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											           </tiles:insert>
														<!--tabs end-->
													</td>
												</tr>
												<tr>
													<td bgcolor="#ff6666"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
												</tr>
											</table>	
											<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
												<tr>
													<td valign="top" align="center">
														<div class="spacer"></div>												
														
        								<!-- BEGIN SCHOOL INFO TABLE -->
												<div class="spacer"></div>
												<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td valign="top" class="detailHead"><bean:message key="title.postReleaseTracking"/>&nbsp;<bean:message key="prompt.information"/></td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="2" cellspacing="1" width="100%" >
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.postReleaseStatusDate"/></td>
																	<td class="formDe"><bean:write name="educationCharterDetailsForm" property="postReleaseStatusDate" formatKey="date.format.mmddyyyy"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.employed"/></td>
																	<td class="formDe"><bean:write name="educationCharterDetailsForm" property="postReleaseEmployedCode"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.continuingEducation"/></td>
																	<td class="formDe">
																	<logic:iterate id="selIter" name="educationCharterDetailsForm" property="selectedList" indexId="index">
																		<%out.print((index.intValue() == 1) ? ", " : ""); %>
               															<bean:write name="selIter" property="description"/>
     																</logic:iterate>
     																</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.comments"/></td>
																	<td class="formDe"><bean:write name="educationCharterDetailsForm" property="otherText"/></td>
																</tr>
															</table>  <!-- END SCHOOL INFO TABLE -->
														</td>
													</tr>
												</table> <!-- END TABLE -->
									<!-- BEGIN BUTTON TABLE -->
												<div class="spacer"></div>
												<table border="0" width="100%">
												  <logic:equal name="educationCharterDetailsForm" property="action" value="view">
													 <tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.charterDetails" /></html:submit>
														</td>
													 </tr>
												  </logic:equal>
												  <logic:equal name="educationCharterDetailsForm" property="action" value="confirm">
													<tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.charterDetails" /></html:submit>
														</td>
													</tr>
												  </logic:equal>
												  <logic:equal name="educationCharterDetailsForm" property="action" value="summary">	
													<tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
															<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
														</td>
													</tr>
												  </logic:equal>
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
<br>
<!-- END DETAIL TABLE -->
</html:form>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>