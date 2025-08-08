<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 10/09/2009 RYoung   - #62156 Sorted by Identifier by default -->
<!-- 11/19/2009 C Shimek - #62124 removed Click View Locations... instruction -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.Features" %>
<%@ page import="naming.PDCodeTableConstants" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCaseload/serviceProviderProgramLocations.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
	function onLocationViewCaseload(theForm)
	{
		var isLocationSelected = false;
		
		var selectedLocElements = document.getElementsByName('selectedId');
		for(var index=0; index < selectedLocElements.length; index++)
		{
			if(selectedLocElements[index].checked)
			{
				isLocationSelected = true;
				var url = "/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=View+Location+Caseload&selectedId=" + selectedLocElements[index].value;
				changeFormActionURL(theForm,url,false);
			}
		}
		if(isLocationSelected==false)
		{
			alert("Please select a Location.");
			return false;
		}
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("selectedId");
		if (rbs.length == 1){
			rbs[0].checked = true;
		}	
	}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">

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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
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
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.filterCaseload"/>-&nbsp;
									<bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.locations"/>
								</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<%-- BEGIN ERROR TABLE --%>
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
										<li>Select a Location. Click View Caseload to view all supervisees in the current caseload with program referrals at the selected Location.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<html:form action="/handleProgramReferralByCaseload" target="content">
							<!--header area start-->
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr class="paddedFourPix">
										<td class="formDeLabel"><bean:message key="prompt.currentCaseload" /></td>
									</tr>
									<tr>
										<td bgcolor="#cccccc" colspan="2">
											<!--header start-->
											<table width="100%" border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td class="headerLabel"><bean:message key="prompt.officer"/></td>
													<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="officerNamePosition"/></td>
												</tr>
												<tr>
													<td class="headerLabel"><bean:message key="prompt.serviceProvider"/></td>
													<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="spName" /></td>
												</tr>
												<tr>
													<td class="headerLabel"><bean:message key="prompt.program"/></td>
													<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="progIdentifier"/>&nbsp;|&nbsp;<bean:write name="cscProgRefCaseloadForm" property="progName"/></td>
												</tr>
											</table>
											<!--header end-->
										</td>
									</tr>
								</table>
							<!--header area end-->	
							
							<div class=spacer4px></div>
							
							<!-- BEGIN DETAIL TABLE -->
							<span id="spSearch">
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td><bean:message key="prompt.locations" /></td>
									</tr>
									<tr>
										<td>
											<!-- BEGIN LOCATIONS TABLE -->
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDeLabel">
													<td width="1%"></td>
													<td><bean:message key="prompt.name" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="locationsList" primaryPropSort="locationName" primarySortType="STRING"  defaultSort="true"  defaultSortOrder="ASC" sortId="1" levelDeep="4"/></td>
													<td><bean:message key="prompt.address" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="locationsList" primaryPropSort="streetNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="4"/></td>
													<td><bean:message key="prompt.phoneNo" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="locationsList" primaryPropSort="locationPhone" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="4"/></td>
												</tr>
												<logic:iterate id="eachLocation" name="cscProgRefCaseloadForm" property="locationsList" indexId="index11">
													<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
														<bean:define id="selectedLocId" type="java.lang.String" name="eachLocation" property="locationId" />
														<td width="1%"><input type="radio" name="selectedId" value="<%=selectedLocId%>"/></td>
														<td><a href="/<msp:webapp/>displayLocationSearchResultsForCSC.do?submitAction=View Link&selectedValue=<bean:write name='eachLocation' property='locationId'/>"><bean:write name="eachLocation" property="locationName"/></a></td>
														<td>
															<div>
															<bean:write name="eachLocation" property="streetNumber" />
                                                                  <bean:write name="eachLocation" property="streetName" />
                                                                  <bean:write name="eachLocation" property="streetTypeCd" />
                                                                  <bean:write name="eachLocation" property="aptNum" /></div>
                                                             <div><bean:write name="eachLocation" property="city" />
                                                                  <bean:write name="eachLocation" property="state" />
                                                                  <bean:write name="eachLocation" property="zipCode" /></div>
                                                         </td>
														<td><bean:write name="eachLocation" property="locationPhone" /></td>
													</tr>
												</logic:iterate>
											</table>
											<!-- END LOCATIONS TABLE -->
										</td>
									</tr>
								</table>
							</span>	
							<!-- END DETAIL TABLE -->	
							
							<br>
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<input type="submit" value="<bean:message key='button.viewCaseload'/>" name="submitAction" onclick="return onLocationViewCaseload(this.form);" >
									</td>
								</tr>
								<tr>
									<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</table>
							<!-- END BUTTON TABLE -->
							
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>			
</table>	
</div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>